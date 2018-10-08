package com.ecpss.service.implservice;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sun.misc.BASE64Encoder;
import vpn.GooPayMessage;
import vpn.GooPayUtil;
import vpn.HJPayMessage;
import vpn.HJPayUtil;
import vpn.HJWPayMessage;
import vpn.HJWPayUtil;
import vpn.MotoDCCMessage;
import vpn.VpnUtil_Moto;
import vpn.YinlianMessage;
import vpn.YinlianUtil;

import com.ecpss.action.BaseAction;
import com.ecpss.action.filemanager.FileUpLoadAction;
import com.ecpss.action.pay.DirectCarderInfoAction;
import com.ecpss.action.pay.TradeManager;
import com.ecpss.dao.common.CommonDao;
import com.ecpss.model.FileManager;
import com.ecpss.model.channel.InternationalChannels;
import com.ecpss.model.payment.HuakuanedException;
import com.ecpss.model.payment.InternationalCardholdersInfo;
import com.ecpss.model.payment.InternationalTradeinfo;
import com.ecpss.model.refund.InternationalRefundManager;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.model.shop.InternationalTerminalManager;
import com.ecpss.service.iservice.RefundService;
import com.ecpss.service.iservice.ShopManagerService;
import com.ecpss.util.AES;
import com.ecpss.util.DateUtil;
import com.ecpss.util.GetBatchNo;
import com.ecpss.util.MD5;
import com.ecpss.util.StateUtils;
import com.ecpss.util.StatusUtil;
import com.ecpss.util.UploadUtils;
import com.ecpss.vo.MerchantBean;
import com.ecpss.vo.UserBean;
import com.opensymphony.xwork2.ActionContext;

@Service("refundService")
@Transactional
public class RefundServiceImpl implements RefundService{
	
	Logger logger = Logger.getLogger(RefundServiceImpl.class.getName());
	@Autowired
	@Qualifier("commonDao")
	private CommonDao commonDao;
	@Autowired
	@Qualifier("shopManagerService")
	private ShopManagerService shopManagerService;
//	@Autowired
//	@Qualifier("tradeManager")
//	private TradeManager tradeManager;
	public InternationalTradeinfo getTradeByMerNo(String tradeNo, String merOrderNo) {
		MerchantBean merbean = (MerchantBean)ActionContext.getContext().getSession().get("merchantBean");
		StringBuffer hql = new StringBuffer("select ti " +
		"from InternationalTradeinfo ti " +
		"where ti.merchantId=" + merbean.getMerchantId() + 
				" and substr(ti.tradeState,1,1)='1' ");
		if(StringUtils.isNotBlank(tradeNo)){
			hql.append(" and ti.orderNo='"+tradeNo.trim()+"' ");
		}
		if(StringUtils.isNotBlank(merOrderNo)){
			hql.append(" and ti.merchantOrderNo='"+merOrderNo.trim()+"' ");
		}
		InternationalTradeinfo ti = null;
		List<InternationalTradeinfo> list = this.commonDao.list(hql.toString());
		System.out.println("hql---------------"+hql);
		if(list.size()>0){
			ti = list.get(0);
		}
		return ti;
	}
	public List getRefundPreview() {
		StringBuffer sb = new StringBuffer();
		MerchantBean merchantbean = (MerchantBean)ActionContext.getContext().getSession().get("merchantBean");
		sb.append("select rm,ti " +
				"from InternationalRefundManager rm," +
				"InternationalTradeinfo ti " +
				"where rm.tradeId=ti.id " +
				"and rm.refundState=1" +  //已申请才可以进入预览退款
				"and ti.merchantId="+merchantbean.getMerchantId()+" order by rm.applyDate desc " );
		List list = this.commonDao.list(sb.toString());
		return list;
	}

	public List<InternationalRefundManager> getRefundByTradeId(Long tradeId){
		String hql="select rm from InternationalRefundManager rm where rm.tradeId="+tradeId;
		List<InternationalRefundManager> rm =  this.commonDao.list(hql);
//		InternationalRefundManager r = null;
//		if(rm.size()>0){
//			r = rm.get(0);
//		}
		return rm;
	}
	
	public void createRefund(Long [] refundIds,String batchNo) {
		try{
			Date d = new Date();
			UserBean user = (UserBean)ActionContext.getContext().getSession().get("user");
			for (Long r : refundIds) {
				InternationalRefundManager rm = (InternationalRefundManager) this.commonDao.load(InternationalRefundManager.class, r);
				InternationalTradeinfo ti  = (InternationalTradeinfo) this.commonDao.load(InternationalTradeinfo.class, rm.getTradeId());
				InternationalCardholdersInfo c = (InternationalCardholdersInfo) this.commonDao.uniqueResult("from InternationalCardholdersInfo where tradeId='"+ti.getId()+"'");
//				List<InternationalTerminalManager> listtm=this.commonDao.list("from InternationalTerminalManager where terminalNo='"+ti.getVIPTerminalNo()+"'");
				List listtm=this.commonDao.list("select it,ic.channelName from InternationalMerchantChannels im,InternationalTerminalManager it,InternationalChannels ic where im.channelId=ic.id and it.channelId=ic.id and im.id='"+ti.getTradeChannel()+"' and it.onoff='1'");
				InternationalTerminalManager tm=new InternationalTerminalManager();
				String chnals="0";
				String chnalType="";
				if(listtm.size()>0){
				Object[] tem = (Object[]) listtm.get(0);
				tm=(InternationalTerminalManager)tem[0];
//				InternationalChannels cl=(InternationalChannels) this.commonDao.load(InternationalChannels.class,tm.getChannelId());
				String chanelName1=tem[1].toString();
				chnals = chanelName1.split("-")[0];
				chnalType = chanelName1.split("-")[1];
				}
				logger.info("退款订单号:"+ti.getOrderNo());
				if("VPN".equals(chnals)){
//				if(StringUtils.isNotBlank(ti.getVIPAuthorizationNo())){
					logger.info("***进入VPN退款通道***");
//					YinlianMessage yl=new YinlianMessage();
//					YinlianUtil yu=new YinlianUtil();
//					SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMddHHmmss");
//					yl.setTrnxDatetime(sdf.format(new Date()));
//					yl.setCardNo(getCarNo(c.getCardNo()));
//					yl.setAmt(this.return12Amount(rm.getRefundRMBAmount()+""));
//					int posNo=this.tradeManager.intBySql("SELECT POS_SEQUENCE.NEXTVAL FROM DUAL");
//					logger.info("pos流水号："+posNo);
//					yl.setPosFlwNo(ti.getVIPBatchNo());
//					yl.setReferenceNo(ti.getRef_No());
//					yl.setAuthResCode("");
//					yl.setTermId(ti.getVIPTerminalNo());
//					yl.setMerchId(tm.getMerchantNo());
//					yl.setResCode("");
//					yl.setOriginalPosNo(ti.getVIPBatchNo());
//					yl.setOriginalDate(ti.getBoc_time());
//					yu.getYLRefundMessage(yl);
					MotoDCCMessage dcc=new MotoDCCMessage();
					dcc.setTrans_Type("refund");
					dcc.setMerchant_Id(tm.getMerchantNo());
					dcc.setAuthor_Str(tm.getAuthcode());
					dcc.setTerminal_Id(ti.getVIPTerminalNo());
					dcc.setInvoice_No(ti.getVIPAuthorizationNo());
					dcc.setCurrency_Code_T("156");
					BigDecimal AmountOri=new BigDecimal(Double.toString(ti.getRmbAmount())).multiply(new BigDecimal(Double.toString(100.0)));
					BigDecimal AmountLoc=new BigDecimal(Double.toString(rm.getRefundRMBAmount())).multiply(new BigDecimal(Double.toString(100.0)));
					dcc.setAmount_Ori(String.valueOf(AmountOri.intValue()));
					dcc.setAmount_Loc(String.valueOf(AmountLoc.intValue()));
					dcc.setCard_No(getCarNo(c.getCardNo()));
					SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");  
					dcc.setTran_Date_Ori(sdf.format(ti.getTradeTime()));
					dcc.setRef_No(ti.getRef_No());
					dcc.setHashCode(tm.getHashcode());
					dcc.setOrder_No(ti.getOrderNo());
					dcc.setCustom(ti.getOrderNo());
					VpnUtil_Moto vpn = new VpnUtil_Moto();
					dcc = vpn.getDCCvalue(dcc, "5");
					logger.info("***退款返回码***"+dcc.getResp_Code());
					if("0000".equals(dcc.getResp_Code())){
						BigDecimal backCount=new BigDecimal(ti.getBackCount());
						BigDecimal refundCount=new BigDecimal(rm.getRefundAmount());
						backCount=backCount.add(refundCount);
						if(Double.valueOf(backCount.toString())>ti.getTradeAmount()){
							ti.setBackCount(ti.getTradeAmount());
						}else{
						ti.setBackCount(Double.valueOf(backCount.toString()));
						}
						if(ti.getBackCount()<ti.getTradeAmount()){
							ti.setTradeState(StatusUtil.updateStatus(ti.getTradeState(), 2, "2"));
						}else{
							ti.setTradeState(StatusUtil.updateStatus(ti.getTradeState(), 2, "1"));
						}
						this.commonDao.update(ti);
						if(ti.getBackCount()<ti.getTradeAmount()){
							rm.setRefundState("5");
						}else{
							rm.setRefundState("4");
						}
						rm.setRefundDate(d);
						rm.setBatchNo(batchNo);
						rm.setLastMan(user.getUserName());
						rm.setLastDate(d);
						this.commonDao.update(rm);
					
						//**********************如果是已经划款过的交易退款插入划款异常表************************
							if(ti.getTradeState().substring(7,8).equals("1")){
								String ehql="select h.id from HuakuanedException h where h.tradeType in (4,5) and h.tradeId="+ti.getId();
								List list = this.commonDao.list(ehql);
								if(list.size()==0){
									HuakuanedException he = new HuakuanedException();
									if(rm.getRefundAmount()<ti.getTradeAmount()){
										he.setTradeType("4");
									}else{
										he.setTradeType("5");
									}
									he.setTradeId(ti.getId());
									he.setIsAudit("0");
									he.setLastDate(new Date());
									this.commonDao.save(he);
								}else{
									HuakuanedException hk  = (HuakuanedException) this.commonDao.load(HuakuanedException.class, new Long(list.get(0).toString()));
									if(ti.getBackCount()<ti.getTradeAmount()){
										hk.setTradeType("4");
									}else{
										hk.setTradeType("5");
									}
									this.commonDao.update(hk);
								}
							}
					}else{
							rm.setRefundState("7");
							rm.setRefundDate(d);
							rm.setBatchNo(batchNo);
							rm.setLastMan(user.getUserName());
							rm.setLastDate(d);
							this.commonDao.update(rm);
						}
				}else if("HJ".equals(chnals)){
					logger.info("****进入HJ退款通道******");
					HJPayUtil HJ=new HJPayUtil();
					HJPayMessage hm=new HJPayMessage();
					hm.setAcctNo(tm.getMerchantNo());
					hm.setAgent_AcctNo(tm.getTerminalNo());
					hm.setOrderID(ti.getOrderNo());
					hm.setCurrCode("156");
					Double amountAndFee=rm.getRefundRMBAmount();
					if(ti.getChannelFee()!=null){
						amountAndFee=amountAndFee*(ti.getChannelFee()+1.0);
						amountAndFee = (double) (Math.round((double) amountAndFee * 100) / 100.00);
					}
					hm.setAmount((int)(amountAndFee *100)+"");
					hm.setIpAddress(c.getIp());
					hm.setCardType(chnalType);
					hm.setCardPAN(getCarNo(c.getCardNo()));
					hm.setPname(c.getProductInfo());
					hm.setCname(c.getFirstName()+c.getLastName());
					hm.setExpDate("2111");
					hm.setCvv2("111");
					hm.setIssCountry(c.getCountry());
					hm.setBaddress(c.getAddress());
					hm.setPostCode(c.getZipcode());
					hm.setIversion("V5.0");
					hm.setTelephone(c.getPhone());
					hm.setRetURL("www.sfepay.com");
					hm.setEmail(c.getEmail());
					String jiamiqian="";
					jiamiqian=tm.getHashcode()+ hm.getAcctNo() + hm.getOrderID() + hm.getAmount() + hm.getCurrCode();
					MessageDigest getMd5;
					BASE64Encoder base64E = new BASE64Encoder();
					String value = null;
					try {
						getMd5 = MessageDigest.getInstance("MD5");
						value =  base64E.encode(getMd5.digest(jiamiqian.getBytes("UTF-8")));
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("加密前："+jiamiqian);
					System.out.println("加密后："+value);
					hm.setHashValue(value);
					hm.setTxnID(ti.getVIPAuthorizationNo());
					hm.setTxnType("03");
					HJ.get(hm);
					if("00".equals(hm.getRes_success())){
						BigDecimal backCount=new BigDecimal(ti.getBackCount());
						BigDecimal refundCount=new BigDecimal(rm.getRefundAmount());
						backCount=backCount.add(refundCount);
						if(Double.valueOf(backCount.toString())>ti.getTradeAmount()){
							ti.setBackCount(ti.getTradeAmount());
						}else{
						ti.setBackCount(Double.valueOf(backCount.toString()));
						}
						if(ti.getBackCount()<ti.getTradeAmount()){
							ti.setTradeState(StatusUtil.updateStatus(ti.getTradeState(), 2, "2"));
						}else{
							ti.setTradeState(StatusUtil.updateStatus(ti.getTradeState(), 2, "1"));
						}
						this.commonDao.update(ti);
						if(ti.getBackCount()<ti.getTradeAmount()){
							rm.setRefundState("5");
						}else{
							rm.setRefundState("4");
						}
						rm.setRefundDate(d);
						rm.setBatchNo(batchNo);
						rm.setLastMan(user.getUserName());
						rm.setLastDate(d);
						this.commonDao.update(rm);
					
						//**********************如果是已经划款过的交易退款插入划款异常表************************
						if(ti.getTradeState().substring(7,8).equals("1")){
							String ehql="select h.id from HuakuanedException h where h.tradeType in (4,5) and h.tradeId="+ti.getId();
							List list = this.commonDao.list(ehql);
							if(list.size()==0){
								HuakuanedException he = new HuakuanedException();
								if(rm.getRefundAmount()<ti.getTradeAmount()){
									he.setTradeType("4");
								}else{
									he.setTradeType("5");
								}
								he.setTradeId(ti.getId());
								he.setIsAudit("0");
								he.setLastDate(new Date());
								this.commonDao.save(he);
							}else{
								HuakuanedException hk  = (HuakuanedException) this.commonDao.load(HuakuanedException.class, new Long(list.get(0).toString()));
								if(ti.getBackCount()<ti.getTradeAmount()){
									hk.setTradeType("4");
								}else{
									hk.setTradeType("5");
								}
								this.commonDao.update(hk);
							}
						}
					}else{
						rm.setRefundState("7");
						rm.setRefundDate(d);
						rm.setBatchNo(batchNo);
						rm.setLastMan(user.getUserName());
						rm.setLastDate(d);
						this.commonDao.update(rm);
					}
				 }
//				else if("HW".equals(chnals)){
//						logger.info("****进入HW退款通道******");
//						HJWPayUtil hjw=new HJWPayUtil();
//						HJWPayMessage hwm=new HJWPayMessage();
//						hwm.setMerchantId(tm.getMerchantNo());
//						hwm.setMd5key(tm.getHashcode());
//						GetBatchNo ut = new GetBatchNo();
//						hwm.setBillNo(ut.getOrderinfo(ti.getOrderNo().substring(0, 4)));
//						hwm.setJcTradeId(ti.getVIPAuthorizationNo());
//						Double amountAndFee=rm.getRefundRMBAmount();
//						if(ti.getChannelFee()!=null){
//							amountAndFee=amountAndFee*(ti.getChannelFee()+1.0);
//							amountAndFee = (double) (Math.round((double) amountAndFee * 100) / 100.00);
//						}
//						hwm.setAmount(amountAndFee+"");
//						hwm.setTradType("2");
//						hjw.get(hwm);
//						if("00".equals(hwm.getRes_responseCode())){
//							BigDecimal backCount=new BigDecimal(ti.getBackCount());
//							BigDecimal refundCount=new BigDecimal(rm.getRefundAmount());
//							backCount=backCount.add(refundCount);
//							ti.setBackCount(Double.valueOf(backCount.toString()));
//							if(ti.getBackCount()<ti.getTradeAmount()){
//								ti.setTradeState(StatusUtil.updateStatus(ti.getTradeState(), 2, "2"));
//							}else{
//								ti.setTradeState(StatusUtil.updateStatus(ti.getTradeState(), 2, "1"));
//							}
//							this.commonDao.update(ti);
//							if(ti.getBackCount()<ti.getTradeAmount()){
//								rm.setRefundState("5");
//							}else{
//								rm.setRefundState("4");
//							}
//							rm.setRefundDate(d);
//							rm.setBatchNo(batchNo);
//							rm.setLastMan(user.getUserName());
//							rm.setLastDate(d);
//							this.commonDao.update(rm);
//						
//							//**********************如果是已经划款过的交易退款插入划款异常表************************
//							if(ti.getTradeState().substring(7,8).equals("1")){
//								String ehql="select h.id from HuakuanedException h where h.tradeType in (4,5) and h.tradeId="+ti.getId();
//								List list = this.commonDao.list(ehql);
//								if(list.size()==0){
//									HuakuanedException he = new HuakuanedException();
//									if(rm.getRefundAmount()<ti.getTradeAmount()){
//										he.setTradeType("4");
//									}else{
//										he.setTradeType("5");
//									}
//									he.setTradeId(ti.getId());
//									he.setIsAudit("0");
//									he.setLastDate(new Date());
//									this.commonDao.save(he);
//								}else{
//									HuakuanedException hk  = (HuakuanedException) this.commonDao.load(HuakuanedException.class, new Long(list.get(0).toString()));
//									if(ti.getBackCount()<ti.getTradeAmount()){
//										hk.setTradeType("4");
//									}else{
//										hk.setTradeType("5");
//									}
//									this.commonDao.update(hk);
//								}
//							}
//						}else{
//							rm.setRefundState("7");
//							rm.setRefundDate(d);
//							rm.setBatchNo(batchNo);
//							rm.setLastMan(user.getUserName());
//							rm.setLastDate(d);
//							this.commonDao.update(rm);
//						}
//					 }
//				 else if("GP".equals(chnals)){
//							logger.info("****进入GP退款通道******");
//							GooPayMessage msg=new GooPayMessage();
//							GooPayUtil yu=new GooPayUtil();
//							msg.setTrans_Type("refund");
//							msg.setBillNo(ti.getOrderNo());
//							Double oriAmountAndFee=ti.getRmbAmount();
//							Double amountAndFee=rm.getRefundRMBAmount();
//							if(ti.getChannelFee()!=null){
//								amountAndFee=amountAndFee*(ti.getChannelFee()+1.0);
//								oriAmountAndFee=oriAmountAndFee*(ti.getChannelFee()+1.0);
//								oriAmountAndFee = (double) (Math.round((double) oriAmountAndFee * 100) / 100.00);
//								amountAndFee = (double) (Math.round((double) amountAndFee * 100) / 100.00);
//							}
//							msg.setOriAmount(oriAmountAndFee+"");
//							msg.setRefundAmount(amountAndFee+"");
//							msg.setGrn(ti.getVIPBatchNo());
//							msg.setMerchantMID("2110");
//							MD5 md5 = new MD5();
//							String md5Hash=msg.getMerchantMID()+msg.getBillNo() +msg.getGrn() +msg.getOriAmount() +msg.getRefundAmount()+"Yu^HJXBd";
//							msg.setHASH(md5.getMD5ofStr(md5Hash));
//							
//							yu.get(msg);
//							if("00".equals(msg.getSucceed())){
//								BigDecimal backCount=new BigDecimal(ti.getBackCount());
//								BigDecimal refundCount=new BigDecimal(rm.getRefundAmount());
//								backCount=backCount.add(refundCount);
//								ti.setBackCount(Double.valueOf(backCount.toString()));
//								if(ti.getBackCount()<ti.getTradeAmount()){
//									ti.setTradeState(StatusUtil.updateStatus(ti.getTradeState(), 2, "2"));
//								}else{
//									ti.setTradeState(StatusUtil.updateStatus(ti.getTradeState(), 2, "1"));
//								}
//								this.commonDao.update(ti);
//								if(ti.getBackCount()<ti.getTradeAmount()){
//									rm.setRefundState("5");
//								}else{
//									rm.setRefundState("4");
//								}
//								rm.setRefundDate(d);
//								rm.setBatchNo(batchNo);
//								rm.setLastMan(user.getUserName());
//								rm.setLastDate(d);
//								this.commonDao.update(rm);
//							
//								//**********************如果是已经划款过的交易退款插入划款异常表************************
//								if(ti.getTradeState().substring(7,8).equals("1")){
//									String ehql="select h.id from HuakuanedException h where h.tradeType in (4,5) and h.tradeId="+ti.getId();
//									List list = this.commonDao.list(ehql);
//									if(list.size()==0){
//										HuakuanedException he = new HuakuanedException();
//										if(rm.getRefundAmount()<ti.getTradeAmount()){
//											he.setTradeType("4");
//										}else{
//											he.setTradeType("5");
//										}
//										he.setTradeId(ti.getId());
//										he.setIsAudit("0");
//										he.setLastDate(new Date());
//										this.commonDao.save(he);
//									}else{
//										HuakuanedException hk  = (HuakuanedException) this.commonDao.load(HuakuanedException.class, new Long(list.get(0).toString()));
//										if(ti.getBackCount()<ti.getTradeAmount()){
//											hk.setTradeType("4");
//										}else{
//											hk.setTradeType("5");
//										}
//										this.commonDao.update(hk);
//									}
//								}
//							}else{
//								rm.setRefundState("7");
//								rm.setRefundDate(d);
//								rm.setBatchNo(batchNo);
//								rm.setLastMan(user.getUserName());
//								rm.setLastDate(d);
//								this.commonDao.update(rm);
//							}
//						 }
				 else{
					BigDecimal backCount=new BigDecimal(ti.getBackCount());
					BigDecimal refundCount=new BigDecimal(rm.getRefundAmount());
					logger.info("历史退款金额："+backCount+"    本次退款金额："+refundCount);
					backCount=backCount.add(refundCount);
					logger.info("历史退款金额+本次退款金额="+backCount);
					if(Double.valueOf(backCount.toString())>ti.getTradeAmount()){
						ti.setBackCount(ti.getTradeAmount());
					}else{
					ti.setBackCount(Double.valueOf(backCount.toString()));
					}
					if(ti.getBackCount()<ti.getTradeAmount()){
						ti.setTradeState(StatusUtil.updateStatus(ti.getTradeState(), 2, "2"));
					}else{
						ti.setTradeState(StatusUtil.updateStatus(ti.getTradeState(), 2, "1"));
					}
					this.commonDao.update(ti);
					if(ti.getBackCount()<ti.getTradeAmount()){
						rm.setRefundState("5");
					}else{
						rm.setRefundState("4");
					}
					rm.setRefundDate(d);
					rm.setBatchNo(batchNo);
					rm.setLastMan(user.getUserName());
					rm.setLastDate(d);
					this.commonDao.update(rm);
				
					//**********************如果是已经划款过的交易退款插入划款异常表************************
						if(ti.getTradeState().substring(7,8).equals("1")){
							String ehql="select h.id from HuakuanedException h where h.tradeType in (4,5) and h.tradeId="+ti.getId();
							List list = this.commonDao.list(ehql);
							if(list.size()==0){
								HuakuanedException he = new HuakuanedException();
								if(rm.getRefundAmount()<ti.getTradeAmount()){
									he.setTradeType("4");
								}else{
									he.setTradeType("5");
								}
								he.setTradeId(ti.getId());
								he.setIsAudit("0");
								he.setLastDate(new Date());
								this.commonDao.save(he);
							}else{
								HuakuanedException hk  = (HuakuanedException) this.commonDao.load(HuakuanedException.class, new Long(list.get(0).toString()));
								if(ti.getBackCount()<ti.getTradeAmount()){
									hk.setTradeType("4");
								}else{
									hk.setTradeType("5");
								}
								this.commonDao.update(hk);
							}
						}
//						refundSynThread rf=new refundSynThread(ti.getOrderNo(), rm.getRefundRMBAmount()+"");
//						rf.start();
				}
			}
			
			}catch(Exception e){
				e.printStackTrace();
			}
//		Date d = new Date();
//		UserBean user = (UserBean)ActionContext.getContext().getSession().get("user");
//		for (Long r : refundIds) {
//			InternationalRefundManager rm = (InternationalRefundManager) this.commonDao.load(InternationalRefundManager.class, r);
//			InternationalTradeinfo ti  = (InternationalTradeinfo) this.commonDao.load(InternationalTradeinfo.class, rm.getTradeId());
//			ti.setBackCount(rm.getRefundAmount());
//			if(rm.getRefundAmount()<ti.getTradeAmount()){
//				ti.setTradeState(StatusUtil.updateStatus(ti.getTradeState(), 2, "2"));
//			}else{
//				ti.setTradeState(StatusUtil.updateStatus(ti.getTradeState(), 2, "1"));
//			}
//			this.commonDao.update(ti);
//			if(rm.getRefundAmount()<ti.getTradeAmount()){
//				rm.setRefundState("5");
//			}else{
//				rm.setRefundState("4");
//			}
//			rm.setRefundDate(d);
//			rm.setBatchNo(batchNo);
//			rm.setLastMan(user.getUserName());
//			rm.setLastDate(d);
//			this.commonDao.update(rm);
////			//**********************添加退款信息等待发送邮件***************************
////			String hql="select c from InternationalCardholdersInfo c where c.tradeId="+ti.getId();
////			List<InternationalCardholdersInfo> cList = (List<InternationalCardholdersInfo>) this.commonDao.list(hql);
////			if(cList.size()==1){
////				EmailInfo emaiinfo= new EmailInfo();
////				String maininfo=emaiinfo.getRefundEmail(cList.get(0).getEmail(), ti);
////				shopManagerService.addSendMessages(cList.get(0).getEmail(), "refund@ecpss.cc", maininfo, "1");
////			}
//			
//			//**********************如果是已经划款过的交易退款插入划款异常表************************
//			if(ti.getTradeState().substring(7,8).equals("1")){
//				String ehql="select h.id from HuakuanedException h where h.tradeType in (4,5) and h.tradeId="+ti.getId();
//				List list = this.commonDao.list(ehql);
//				if(list.size()==0){
//					HuakuanedException he = new HuakuanedException();
//					if(rm.getRefundAmount()<ti.getTradeAmount()){
//						he.setTradeType("4");
//					}else{
//						he.setTradeType("5");
//					}
//					he.setTradeId(ti.getId());
//					he.setIsAudit("0");
//					he.setLastDate(new Date());
//					this.commonDao.save(he);
//				}
//			}
//		}
	}

	public List getRefundDetailByBatchNo(String batchNo,String refStatus) {
		StringBuffer sb = new StringBuffer();
		sb.append("select rm,ti " +
				"from InternationalRefundManager rm," +
				"InternationalTradeinfo ti " +
				"where rm.tradeId=ti.id " +
				"and rm.batchNo='"+batchNo.trim()+"' ");
				if(StringUtils.isNotBlank(refStatus)&&"0".equals(refStatus)){
					sb.append("and rm.refundState='7'");
				}
				if(StringUtils.isNotBlank(refStatus)&&"1".equals(refStatus)){
					sb.append("and rm.refundState in(4,5)");
				}
				if(StringUtils.isBlank(refStatus)){
					sb.append("and rm.refundState in(4,5,7)");
				}
		sb.append("order by rm.refundDate desc");
		List list = this.commonDao.list(sb.toString());
		return list;
	}
	
	public List<InternationalMerchant> getMerchantList(){
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct m from InternationalMerchant m,InternationalTradeinfo ti,InternationalRefundManager rm ");
		sb.append("where rm.tradeId=ti.id and ti.merchantId=m.id and rm.refundState in(3,6) ");
		List<InternationalMerchant> list = this.commonDao.list(sb.toString());
		return list;
	}

	public List<String> getTerminalNoByRefund(Long refundstate) {
		StringBuffer sb = new StringBuffer();
		sb.append("select ti.VIPTerminalNo " +
				"from InternationalRefundManager rm," +
				"InternationalTradeinfo ti," +
				"InternationalMerchant m " +
				"where rm.tradeId=ti.id " +
				"and ti.merchantId=m.id " +
				"and rm.refundState="+refundstate+" " +
						"group by ti.VIPTerminalNo"); 
		return this.commonDao.list(sb.toString());
	}
	
	public String batchRefund() {
		StringBuffer sb = new StringBuffer();
		sb.append("select rm,ti,m,ci " +
				"from InternationalRefundManager rm," +
				"InternationalTradeinfo ti," +
				"InternationalMerchant m," +
				"InternationalCardholdersInfo ci " +
				"where rm.tradeId=ti.id " +
				"and ci.tradeId=ti.id " +
				"and ti.merchantId=m.id " +
				"and rm.refundState=3 ");  //已审核通过的
		sb.append(" and (ti.VIPTerminalNo like '7777%' or ti.VIPTerminalNo like '7669%' or ti.VIPTerminalNo like '8889%' or ti.VIPTerminalNo like '07%') ");
		sb.append(" order by ti.VIPTerminalNo,rm.applyDate ");
		System.out.println(sb.toString());
		List<Object[]> objList  = this.commonDao.list(sb.toString());
		InternationalTradeinfo ti;
		InternationalRefundManager rm;
		InternationalMerchant merchant;
		InternationalCardholdersInfo ci;
		StringBuffer write = new StringBuffer();
		String merchantidby = null;
		Boolean flag=true;
		String merchantId9 = null;
		for(Object[] obj:objList){
			try{
					
				StringBuffer writeRefund = new StringBuffer();
				rm = (InternationalRefundManager) obj[0];
				ti = (InternationalTradeinfo) obj[1];
				merchant = (InternationalMerchant) obj[2];
				ci = (InternationalCardholdersInfo) obj[3];
				String merchantId = (String) this.commonDao.list("select tm.merchantNo from InternationalTerminalManager tm where tm.terminalNo='"+ti.getVIPTerminalNo()+"' ").get(0);
				if(flag){
					merchantId9 = merchantId.substring(0, 9);
					flag = false;
				}
				if(StringUtils.isNotBlank(merchantidby)){
					if(!merchantidby.equals(merchantId)){
						//***********************头信息************************************
						//第一位 以1开头 (1位)
						writeRefund.append("1");
						//第二位 日期(YYYYMMDD)(8位)
						SimpleDateFormat timeh=new SimpleDateFormat("yyyyMMdd"); 
						writeRefund.append(timeh.format(new Date()));
						//第三位 M =Merchan    B =Bank
						writeRefund.append("M");
						//第四位 如果Unit Type=B 填写地区代号，否则填写Space(7)
						writeRefund.append("       ");
						//第五位 商户号
						writeRefund.append(merchantId);
						writeRefund.append("\n");
					}
				}else{
					//***********************头信息************************************
					//第一位 以1开头 (1位)
					writeRefund.append("1");
					//第二位 日期(YYYYMMDD)(8位)
					SimpleDateFormat timeh=new SimpleDateFormat("yyyyMMdd"); 
					writeRefund.append(timeh.format(new Date()));
					//第三位 M =Merchan    B =Bank
					writeRefund.append("M");
					//第四位 如果Unit Type=B 填写地区代号，否则填写Space(7)
					writeRefund.append("       ");
					//第五位 商户号
					writeRefund.append(merchantId);
					writeRefund.append("\n");
					merchantidby = merchantId;
					
				}
				
				
				//****************************************退款交易信息********************************
				//第一位以2开头(1位)
				writeRefund.append("2");
				//第二位是否DCC,否者EDC(3位)
				if(ti.getVIPTerminalNo().startsWith("7777") || ti.getVIPTerminalNo().startsWith("07")){
					writeRefund.append("DCC");
				}else{
					writeRefund.append("EDC");
				}
				//第三位终端号对应的商户号(15位)
				writeRefund.append(merchantId);
				//第四位终端号(8位)
				writeRefund.append(ti.getVIPTerminalNo().trim());
				//第五位   1个空格
				writeRefund.append(" ");
				//第六位  卡号(16位)
				
				writeRefund.append(AES.getCarNo(ci.getCardNo().trim()));
				//第七位  3个空格
				writeRefund.append("   ");
				//第八位 有效期(4位)  YYMM
				writeRefund.append(ci.getExpiryDate().substring(2, 4)+ci.getExpiryDate().substring(0, 2));
				//第九位  1个空格
				writeRefund.append(" ");
				//第十位 销售/退款 交易人民币金额Decimal place(2)    
				writeRefund.append(return12Amount(rm.getRefundRMBAmount().toString()));
				//第十一位 DCC销售交易外币金额Decimal place(2)  退款的外币交易金额
				if(ti.getVIPTerminalNo().startsWith("7777")){
					if(rm.getRefundAmount()!=null){
						writeRefund.append(return12Amount(rm.getRefundAmount().toString()));
					}else{
						writeRefund.append("000000000000");
					}
				}else{
					writeRefund.append("000000000000");
				}
				//第十二位 DCC销售外币货币代码  (不填写)  (3位)
				writeRefund.append("   ");
				//第十三位 1个空格
				writeRefund.append(" ");
				//第十四位 原交易人民币金额Decimal place(2)  (12位)
				writeRefund.append(return12Amount(ti.getRmbAmount().toString()));
				//第十五位 授权号码 (6位)
				writeRefund.append(ti.getVIPAuthorizationNo());
				//第十六位 原交易日期YYMMDD  (6位)
				SimpleDateFormat time=new SimpleDateFormat("yyMMdd"); 
				if(ti.getVIPDisposeDate()!=null){
					writeRefund.append(time.format(ti.getVIPDisposeDate()));
				}else{
					writeRefund.append(time.format(ti.getTradeTime()));
				}
				//第十七位 原交易票据号 (6位)
				writeRefund.append(ti.getVIPBatchNo());
				//第十八位 地区代号  (7位)
				writeRefund.append("       ");
				//第十九位  ‘C‘（销售Sales） / ‘D’（退款Refund）
				writeRefund.append("D ");
				//第二十位   1个空格  (1位)
				writeRefund.append(" ");
				//第二是一位  银行处理结果 (2位)Space(2)  由我司处理后置回应码
				//00—成功处理
				///01—	退款金额大于原始交易金额
				//02—	无法匹配原始交易
				//03—	匹配多笔原始交易
				//04—	退款金额大于可退金额
				writeRefund.append("  ");
				//第二十二位   1个空格  (1位)
				writeRefund.append(" ");
				//第二十三位   银行预留字段 (20位)  ECPSS系统流水单号 16887201041520162623425
				writeRefund.append(getOrderNoByRefund(ti.getOrderNo()));
				//第二十四位   1个空格  (1位)
				writeRefund.append(" ");
				//第二十五位   退款交易日期与时间    YYMMDDHHMMSS
				SimpleDateFormat time3=new SimpleDateFormat("yyMMddHHmmss"); 
				writeRefund.append(time3.format(rm.getApplyDate()));
				
				//一笔交易输入完成以后换行
				writeRefund.append("\n");
				
				//写入到退款文件中改变退款状态
				write.append(writeRefund.toString());
				rm.setRefundState("6");
				this.commonDao.update(rm);
			}catch (Exception e) {
				continue;
			}
		}
		
		//结尾以9结果   
		write.append("9000000            000000");
		
		//生成txt文件
		try{   
			SimpleDateFormat time1=new SimpleDateFormat("yyyyMMddHHmmss");
			//文件名
			String imageFileName = "REFUND_"+merchantId9+"_"+time1.format(new Date())+"(生成退款).txt";
			InputStream stream = FileUpLoadAction.class.getClassLoader().getResourceAsStream("/ecpss.properties");
			Properties p = new Properties();
			p.load(stream);
			File refund=new File(UploadUtils.getRefundUploadDir());
			if(!refund.exists()){
				refund.mkdir();				
			}
			File da = new File(refund+"/"+DateUtil.getDate());
			if(!da.exists()){
				da.mkdir();
			}
			File imageFile = new File(UploadUtils.getRefundUploadDir()+"/"+ UploadUtils.getUploadFileOpPath(imageFileName));
			FileOutputStream   fos=new   FileOutputStream(imageFile);
			DataOutputStream   out=new   DataOutputStream(fos);   
			out.writeBytes(write.toString()); 
			
			//保存文件   下载使用
			FileManager filemanager = new FileManager();
			filemanager.setFilename(imageFileName);
			filemanager.setFileroute(UploadUtils.getUploadFileOpPath(imageFileName));
			filemanager.setFilesize(imageFile.length());
			filemanager.setFiledate(new Date());
			filemanager.setUsetype("0");
			filemanager.setDownloadcount(0L);
			this.commonDao.save(filemanager);
			out.close();
		}catch(Exception e) {
			System.out.println("失败");
			e.printStackTrace();
		}
		return write.toString();
	}
	
	
	public String batchRefundByVC() {
		StringBuffer sb = new StringBuffer();
		sb.append("select rm,ti,m,ci " +
				"from InternationalRefundManager rm," +
				"InternationalTradeinfo ti," +
				"InternationalMerchant m," +
				"InternationalCardholdersInfo ci " +
				"where rm.tradeId=ti.id " +
				"and ci.tradeId=ti.id " +
				"and ti.merchantId=m.id " +
				"and rm.refundState=3 " +//已审核通过的
				" and ci.cardNo like '5%' ");  
		sb.append(" and ti.VIPTerminalNo like '021814%' ");
		sb.append(" order by ti.VIPTerminalNo,rm.applyDate ");
		System.out.println(sb.toString());
		List<Object[]> objList  = this.commonDao.list(sb.toString());
		InternationalTradeinfo ti;
		InternationalRefundManager rm;
		InternationalMerchant merchant;
		InternationalCardholdersInfo ci;
		StringBuffer write = new StringBuffer();
		String merchantidby = null;
		Boolean flag=true;
		String merchantId9 = null;
		for(Object[] obj:objList){
			try{
					
				StringBuffer writeRefund = new StringBuffer();
				rm = (InternationalRefundManager) obj[0];
				ti = (InternationalTradeinfo) obj[1];
				merchant = (InternationalMerchant) obj[2];
				ci = (InternationalCardholdersInfo) obj[3];
				//商户号
				String merchantId=ti.getVIPTerminalNo().substring(0, 9)+"000001";
				//终端号
				String terminal = "555500"+ti.getVIPTerminalNo().substring(7, 9);
//				String merchantId = (String) this.commonDao.list("select tm.merchantNo from InternationalTerminalManager tm where tm.terminalNo='"+ti.getVIPTerminalNo()+"' ").get(0);
				if(flag){
					merchantId9 = merchantId.substring(0, 9);
					flag = false;
				}
				if(StringUtils.isNotBlank(merchantidby)){
					if(!merchantidby.equals(merchantId)){
						//***********************头信息************************************
						//第一位 以1开头 (1位)
						writeRefund.append("1");
						//第二位 日期(YYYYMMDD)(8位)
						SimpleDateFormat timeh=new SimpleDateFormat("yyyyMMdd"); 
						writeRefund.append(timeh.format(new Date()));
						//第三位 M =Merchan    B =Bank
						writeRefund.append("M");
						//第四位 如果Unit Type=B 填写地区代号，否则填写Space(7)
						writeRefund.append("       ");
						//第五位 商户号
						writeRefund.append(merchantId);
						writeRefund.append("\n");
					}
				}else{
					//***********************头信息************************************
					//第一位 以1开头 (1位)
					writeRefund.append("1");
					//第二位 日期(YYYYMMDD)(8位)
					SimpleDateFormat timeh=new SimpleDateFormat("yyyyMMdd"); 
					writeRefund.append(timeh.format(new Date()));
					//第三位 M =Merchan    B =Bank
					writeRefund.append("M");
					//第四位 如果Unit Type=B 填写地区代号，否则填写Space(7)
					writeRefund.append("       ");
					//第五位 商户号
					writeRefund.append(merchantId);
					writeRefund.append("\n");
					merchantidby = merchantId;
					
				}
				
				
				//****************************************退款交易信息********************************
				//第一位以2开头(1位)
				writeRefund.append("2");
				//第二位是否DCC,否者EDC(3位)
				if(ti.getVIPTerminalNo().startsWith("7777")){
					writeRefund.append("DCC");
				}else{
					writeRefund.append("EDC");
				}
				//第三位终端号对应的商户号(15位)
				writeRefund.append(merchantId);
				//第四位终端号(8位)
				writeRefund.append(terminal);
				//第五位   1个空格
				writeRefund.append(" ");
				//第六位  卡号(16位)
				writeRefund.append(AES.getCarNo(ci.getCardNo().trim()));
				//第七位  3个空格
				writeRefund.append("   ");
				//第八位 有效期(4位)  YYMM
				writeRefund.append(ci.getExpiryDate().substring(2, 4)+ci.getExpiryDate().substring(0, 2));
				//第九位  1个空格
				writeRefund.append(" ");
				//第十位 销售/退款 交易人民币金额Decimal place(2)    
				writeRefund.append(return12Amount(rm.getRefundRMBAmount().toString()));
				//第十一位 DCC销售交易外币金额Decimal place(2)  退款的外币交易金额
				if(ti.getVIPTerminalNo().startsWith("7777")){
					if(rm.getRefundAmount()!=null){
						writeRefund.append(return12Amount(rm.getRefundAmount().toString()));
					}else{
						writeRefund.append("000000000000");
					}
				}else{
					writeRefund.append("000000000000");
				}
				//第十二位 DCC销售外币货币代码  (不填写)  (3位)
				writeRefund.append("   ");
				//第十三位 1个空格
				writeRefund.append(" ");
				//第十四位 原交易人民币金额Decimal place(2)  (12位)
				writeRefund.append(return12Amount(ti.getRmbAmount().toString()));
				//第十五位 授权号码 (6位)
				writeRefund.append(ti.getVIPAuthorizationNo());
				//第十六位 原交易日期YYMMDD  (6位)
				SimpleDateFormat time=new SimpleDateFormat("yyMMdd"); 
				if(ti.getVIPDisposeDate()!=null){
					writeRefund.append(time.format(ti.getVIPDisposeDate()));
				}else{
					writeRefund.append(time.format(ti.getTradeTime()));
				}
				//第十七位 原交易票据号 (6位)
				writeRefund.append("XXXXXX");
				//第十八位 地区代号  (7位)
				writeRefund.append("       ");
				//第十九位  ‘C‘（销售Sales） / ‘D’（退款Refund）
				writeRefund.append("D ");
				//第二十位   1个空格  (1位)
				writeRefund.append(" ");
				//第二是一位  银行处理结果 (2位)Space(2)  由我司处理后置回应码
				//00—成功处理
				///01—	退款金额大于原始交易金额
				//02—	无法匹配原始交易
				//03—	匹配多笔原始交易
				//04—	退款金额大于可退金额
				writeRefund.append("  ");
				//第二十二位   1个空格  (1位)
				writeRefund.append(" ");
				//第二十三位   银行预留字段 (20位)  ECPSS系统流水单号 16887201041520162623425
				writeRefund.append(getOrderNoByRefund(ti.getOrderNo()));
				//第二十四位   1个空格  (1位)
				writeRefund.append(" ");
				//第二十五位   退款交易日期与时间    YYMMDDHHMMSS
				SimpleDateFormat time3=new SimpleDateFormat("yyMMddHHmmss"); 
				writeRefund.append(time3.format(rm.getApplyDate()));
				
				//一笔交易输入完成以后换行
				writeRefund.append("\n");
				
				//写入到退款文件中改变退款状态
				write.append(writeRefund.toString());
				rm.setRefundState("6");
				this.commonDao.update(rm);
			}catch (Exception e) {
				continue;
			}
		}
		
		//结尾以9结果   
		write.append("9000000            000000");
		
		//生成txt文件
		try{   
			SimpleDateFormat time1=new SimpleDateFormat("yyyyMMddHHmmss");
			//文件名
			String imageFileName = "REFUND_"+merchantId9+"_"+time1.format(new Date())+"(MIGS生成退款).txt";
			InputStream stream = FileUpLoadAction.class.getClassLoader().getResourceAsStream("/ecpss.properties");
			Properties p = new Properties();
			p.load(stream);
			File refund=new File(UploadUtils.getRefundUploadDir());
			if(!refund.exists()){
				refund.mkdir();				
			}
			File da = new File(refund+"/"+DateUtil.getDate());
			if(!da.exists()){
				da.mkdir();
			}
			File imageFile = new File(UploadUtils.getRefundUploadDir()+"/"+ UploadUtils.getUploadFileOpPath(imageFileName));
			FileOutputStream   fos=new   FileOutputStream(imageFile);
			DataOutputStream   out=new   DataOutputStream(fos);   
			out.writeBytes(write.toString()); 
			
			//保存文件   下载使用
			FileManager filemanager = new FileManager();
			filemanager.setFilename(imageFileName);
			filemanager.setFileroute(UploadUtils.getUploadFileOpPath(imageFileName));
			filemanager.setFilesize(imageFile.length());
			filemanager.setFiledate(new Date());
			filemanager.setUsetype("0");
			filemanager.setDownloadcount(0L);
			this.commonDao.save(filemanager);
			out.close();
		}catch(Exception e) {
			System.out.println("失败");
			e.printStackTrace();
		}
		return write.toString();
	}

	/**
	 * 根据流水单号截取后20位
	 * @param Orderno
	 * @return
	 */
	public String getOrderNoByRefund(String orderno){
		String orderNoByRefund = "                    ";
		if(orderno.length()>20){
			orderNoByRefund = orderno.substring(orderno.length()-20,orderno.length());
		}else{
			for (int i = 0; i < 20-orderno.length(); i++) {
				orderNoByRefund+="0";
			}
			orderNoByRefund = orderNoByRefund.trim() + orderno;
		}
		return orderNoByRefund;
	}
	
	
	public String return12Amount(String refundRMBMoney){
		String refundRMB = "000000000000";
		String zero_12 = "000000000000";
		DecimalFormat decimalFormat = new DecimalFormat("##############0.00");
		if(StringUtils.isNotBlank(refundRMBMoney) && refundRMBMoney.replace(".", "").matches("\\d+")){		
				String refundRMBStr = Double.valueOf((decimalFormat.format(Double.valueOf(refundRMBMoney))))*100+"";
				String refundRMB_0 = zero_12 + refundRMBStr.substring(0, refundRMBStr.indexOf("."));
				refundRMB = refundRMB_0.substring(refundRMB_0.length()-12, refundRMB_0.length());
		}
		return refundRMB;
	}
public String  getCarNo(String message){
		
		if(message.length()>16){
			return AES.getCarNo(message);
		}else{
			return message;
		}
		
		
	}

//public TradeManager getTradeManager() {
//	return tradeManager;
//}
//
//public void setTradeManager(TradeManager tradeManager) {
//	this.tradeManager = tradeManager;
//}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
