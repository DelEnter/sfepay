package com.ecpss.action.pay;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import vpn.CaibaoMessage;
import vpn.CaibaoUtil;
import vpn.YinlianMessage;
import vpn.YinlianUtil;

import com.ecpss.action.BaseAction;
import com.ecpss.model.payment.InternationalTradeinfo;
import com.ecpss.model.risk.InternationalRiskItems;
import com.ecpss.model.risk.InternationalWhitelist;
import com.ecpss.model.shop.InternationalExchangerate;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.model.shop.InternationalPayVersion;
import com.ecpss.model.shop.InternationalWebchannels;
import com.ecpss.util.AES;
import com.ecpss.util.GetBatchNo;
import com.ecpss.util.MD5;

public class PayAction extends BaseAction {
	@Autowired
	@Qualifier("tradeManager")
	private TradeManager tradeManager;
	// 抛过来的商户，订单，信息
	private String MerNo; // 商户ID
	private String rorderno; // 交易流水号
	private Date tradetime; // 交易时间
	private Long merchantnoValue;
	private boolean checkMerch;// 交易商户是否存在
	private long isDredge;// 检验商户通道是否开通
	private String Amount; // 支付金额

	private Double tradeMoney;// 支付金额
	private double rmbmoney = 0; // 人民币交易金额

	private Double tradeAmount; // 交易金额（外币）

	private String channels; // 使用的商户通道F
	private String BillNo; // 订单编号

	private String Currency; // 币种
	private String currencyName;
	private String Language = "en"; // 支付语言
	private String ReturnURL; // 返回地址
	private String md5src; // 网关加密md5
	private String cookie; // cookies
	private String cooknumber;
	private String MD5info; // MD5加密
	// 程序处理需要的参数
	private int responseCode;// 响应代号；
	private String MD5key; // MD5key值
	private String tradeAdd;
	private Long moneyType;
	private String message;

	// md5 数据校验
	private String tradeMoneyType;
	private String ordercount; // 支付金额(转换为支付金额)
	private String md5Value; // 支付网关对商户信息进行加密
	private String merchantOrderNo;

	private String resultMd5;
	// Q 支付请求.校验顺序：1.交易商户是否存在 ，2.检验商户通道是否开通 ;3.返回网址必须注册 4.信息是否被篡改。

	// 持卡人信息带过来 billingAddress
	private String firstname;
	private String lastname;
	private String cardbank;
	private String email;
	private String phone;
	private String zipcode;
	private String address;
	private String city;
	private String state;
	private String country;
	private String products;
	// shippingAddress
	private String shippingFirstName;
	private String shippingLastName;
	private String shippingEmail;
	private String shippingPhone;
	private String shippingZipcode;
	private String shippingAddress;
	private String shippingCity;
	private String shippingSstate;
	private String shippingCountry;
	
	private String redirectPath;
	private boolean cardtype_visa = false;      //判断是否有visa
	private boolean cardtype_jcb = false;       //判断是否有jcb
	private boolean cardtype_master = false;    //判断是否有master
	private boolean cardtype_ae = false;        //判断是否有ae
	private boolean cardtype_dc = false;        //判断是否有dc
	private String cardnum;
	private String cvv2;
	private String year; // 年
	private String month; // 月
	private String newcardtype;
	private String sfeVersion;
	Logger logger = Logger.getLogger(PayAction.class.getName());
	public String payRequest(){
//		List list = commonService.getByList
//				("select f.id,f.cardNo6 from international_cardholdersinfo f, international_tradeinfo t where f.tradeid=t.id and f.cardNo6='"
//						+ AES.setCarNo("412345")
//						+ "' group by f.id,f.cardNo6");
//		System.out.println(list.size());
//		try{
//		CaibaoUtil ut=new CaibaoUtil();
//		CaibaoMessage ms=new CaibaoMessage();
//		ms.setMerNo("20668");
//		ms.setGatewayNo("20668001");
//		ms.setOrderNo("s000001");
//		ms.setOrderCurrency("CNY");
//		ms.setOrderAmount("100.02");
//		ms.setReturnURL("www.sfepay.com");
//		ms.setCardNo("4111111111111111");
//		ms.setCardExpireYear("2016");
//		ms.setCardExpireMonth("02");
//		ms.setCardSecurityCode("212");
//		ms.setIssuingBank("bank");
//		ms.setIp("180.64.48.98");
//		ms.setEmail("12345@126.com");
//		ms.setPaymentMethod("Credit Card");
//		ms.setPhone("02112345678");
//		ms.setCountry("USA");
//		ms.setState("Canada");
//		ms.setCity("tue");
//		ms.setAddress("asfdsfsdfsfs");
//		ms.setZip("518000");
//		ms.setFirstName("heh");
//		ms.setLastName("hehe");
//		String sign=ms.getMerNo()+ms.getGatewayNo()+ms.getOrderNo()+ms.getOrderCurrency()+ms.getOrderAmount()+ms.getFirstName()+ms.getLastName() + ms.getCardNo() + ms.getCardExpireYear()+ms.getCardExpireMonth()+ms.getCardSecurityCode()+ ms.getEmail() + "2pz6pL6f";
//		MessageDigest md = MessageDigest.getInstance("SHA-256");
//		md.update(sign.getBytes());
//        String strDes = bytes2Hex(md.digest()); // to HexString
//		ms.setSignInfo(strDes);
//		ms.setIsAuthor("");
//		ms.setRemark("");
//		ut.get(ms);
//		System.out.println(ms.getRes_orderStatus());
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		try {  
//			      String fileName = "D:\\aaa.xls"; // Excel文件所在路径  
//			          File file = new File(fileName); // 创建文件对象  
//			           Workbook wb = Workbook.getWorkbook(file); // 从文件流中获取Excel工作区对象（WorkBook）  
//			          Sheet sheet = wb.getSheet(0); // 从工作区中取得页（Sheet）  
//			             
//			          for (int i = 0; i < sheet.getRows(); i++) { // 循环打印Excel表中的内容  
//			               for (int j = 0; j < sheet.getColumns(); j++) {  
//		                  Cell cell = sheet.getCell(j, i); 
//		                  String aa=cell.getContents();
//		                  System.out.println(aa);
//		                  InternationalWebchannels iw=new InternationalWebchannels();
//			          		iw.setExecutetime(new Date());
//			          		iw.setMerchanid(new Long(4944));
//			          		iw.setOperator("jiahui");
//			          		iw.setTradeWebsite(aa);
//			          		iw.setWebsite("http://"+aa+"/index.php?main_page=checkout_payresult");
//			          		iw.setIsblack("0");
//			          		commonService.save(iw);
//		               }  
//			           }  
//		        } catch (Exception e) {  
//			            e.printStackTrace();  
//			        }

//
//		try {
//			HttpServletRequest request = ServletActionContext.getRequest();
//			// HttpSession session = request.getSession();
//			
//			logger.info("*********************发现交易请求***************************"+this.getReturnURL());
//			if (MerNo == null||MerNo.equals("")){
//			logger.error("*********************交易请求商户号为空，请检查**************"+this.getReturnURL());
//				return ERROR;
//			}
//			MD5 md5 = new MD5();
//			if(StringUtils.isNotBlank(sfeVersion)){
//				String hqlv="from InternationalPayVersion where merchantId is null and version='"+sfeVersion+"'";
//				String hqlv2="from InternationalPayVersion pay,InternationalMerchant me where me.id=pay.merchantId and me.merno='"+MerNo+"' and pay.version='"+sfeVersion+"'";
//				List allv = this.commonService.list(hqlv);
//				if(allv==null||allv.size()==0){
//					List onev = this.commonService.list(hqlv2);
//					if(onev!=null&&onev.size()>0){
//						responseCode = 36;
//						resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;
//						md5Value = md5.getMD5ofStr(resultMd5);
//						message = "Payment Declined";
//						logger.error("******************支付版本禁止使用****************"+this.sfeVersion);
//						return INPUT;
//					}
//				}else{
//					responseCode = 36;
//					resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;
//					md5Value = md5.getMD5ofStr(resultMd5);
//					message = "Payment Declined";
//					logger.error("******************支付版本禁止使用****************"+this.sfeVersion);
//					return INPUT;
//				}
//			}
//			if (StringUtils.isNotBlank(Amount)) {
//				ordercount = Amount.replace(",", "");
//				ordercount = ordercount.replace(" 0", "");
//				tradeMoney = Double.valueOf(ordercount);
//			} else {
//			logger.error("******************交易请求支付金额为空，请检查****************"+this.getReturnURL());	
//				responseCode = 26;
//				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;
//				md5Value = md5.getMD5ofStr(resultMd5);
//				message = "Payment Declined";
//				return INPUT;
//			}
//			//
//			if (StringUtils.isBlank(BillNo)) {
//				logger.error("******************交易请求商户订单号为空，请检查****************"+this.getReturnURL());
//				responseCode = 26;
//				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;
//				md5Value = md5.getMD5ofStr(resultMd5);
//				message = "Payment Declined";
//				// 不存在该商户号
//				return INPUT;
//			}
//			merchantOrderNo = BillNo;
//			tradeMoneyType = Currency;
//			currencyName = getStates().getCurrencyTypeByNo(
//					Integer.valueOf(tradeMoneyType));
//			Long begin = System.currentTimeMillis();
//			// 交易商户是否存在
//			String sql1 = " from InternationalMerchant t where t.merno='"
//					+ this.MerNo + "'";
//			// 获取商户资料
//
//			InternationalMerchant merchant = new InternationalMerchant();
//			List<InternationalMerchant> merchantList = this.commonService
//					.list(sql1);
//			if (merchantList == null || merchantList.size() == 0) {
//			logger.error("******************交易请求商户号错误，请检查****************"+this.getReturnURL());
//				responseCode = 10;
//				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;
//				md5Value = md5.getMD5ofStr(resultMd5);
//				message = "Payment Declined";
//				// 不存在该商户号
//				return INPUT;
//
//			}
//			merchant = merchantList.get(0);
//			// 获取mD5key值
//			if (merchant != null) {
//				if (merchant.getMd5key() == null) {
//					responseCode = 11;
//					resultMd5 = BillNo + Currency + Amount + responseCode
//							+ MD5key;
//					md5Value = md5.getMD5ofStr(resultMd5);
//					message = "Payment Declined";
//					return INPUT;
//				} else {
//					MD5key = merchant.getMd5key();
//
//				}
//			}
//			// 获取交易流水号
//			GetBatchNo ut = new GetBatchNo();
//			rorderno = ut.getOrderinfo(MerNo);
//			// 校验交易流水号是否重复
//			String hql = "select count(*) from international_tradeinfo t where t.ORDERNO='"
//					+ rorderno + "'";
//			int trlist = this.tradeManager.intBySql(hql);
//			if (trlist > 0) {
//				this.responseCode = 24;
//				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;
//				md5Value = md5.getMD5ofStr(resultMd5);
//				message = "Payment Declined";
//				return INPUT;
//			}
//			
//			if (Double.valueOf(Amount) > 100000L) {
//				this.responseCode = 26;
//				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;
//				md5Value = md5.getMD5ofStr(resultMd5);
//				return INPUT;
//			}
//
//			// 获取交易时间
//			tradetime = ut.getTime();
//			if (MerNo != null) {
//				merchantnoValue = Long.valueOf(MerNo);
//			} else {
//				merchantnoValue = 0l;
//			}
//
//			if (merchant.getIsopen().equals("0")) {
//				// 商户未开通
//				responseCode = 15;
//				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;
//				md5Value = md5.getMD5ofStr(resultMd5);
//				message = "Payment Declined";
//				return INPUT;
//
//			}
//
//			// 检验商户通道是否开通
//			String sql2 = "select count(*) from international_merchantchannels t where t.merchantid='"
//					+ merchant.getId() + "' and t.onoff='1'";
//			int isDredge = this.tradeManager.intBySql(sql2);
//			if (isDredge == 0) {
//				responseCode = 16;
//				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;
//				md5Value = md5.getMD5ofStr(resultMd5);
//				message = "Payment Declined";
//				return INPUT;
//			}
//
//			// 币种 //select t.*, t.rowid from international_moneykindname t where
//			// t.id=(select f.moneykindno from international_merchantcurrency f
//			// where f.merchanid='1' )
////			List<InternationalMoneykindname> moneykinds = this.commonService
////					.list(" from InternationalMoneykindname t "
////							+ "where t.id in (select f.moneyKindNo from InternationalMerchantCurrency f "
////							+ "where f.merchanId='" + merchant.getId() + "' )");
////			if (moneykinds.size() == 0 || moneykinds == null) {
////
////				responseCode = 12;
////				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;
////				md5Value = md5.getMD5ofStr(resultMd5);
////				message = "Payment Declined";
////				return INPUT;
////			}
////			InternationalMoneykindname moneykind = moneykinds.get(0);
////			// 检验币种
////			if (!(Currency.equals(moneykind.getMoneykindno() + ""))) {
////
////				responseCode = 12;
////				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;
////				md5Value = md5.getMD5ofStr(resultMd5);
////				message = "Payment Declined";
////				return INPUT;
////			}
//			// select t.*,t.rowid from international_exchangerate t where
//			// t.executetime in (select max(f.executetime) from
//			// international_exchangerate f where f.moneykindno=1 group by
//			// f.type)
//			// 获取交易汇率
//			List changerates = this.commonService
//					.getByList("select t.id,t.rate,t.type from international_exchangerate t,international_moneykindname m  where t.moneykindno=m.id "
//							+ "and m.moneykindno="
//							+ this.Currency
//							+ " and t.executetime<sysdate-1   and t.type='1' order by t.executetime desc  "); // 交易汇率
//			// 获取结算汇率
//			List changeratesbalance = this.commonService
//					.getByList("select t.id,t.rate,t.type from international_exchangerate t,international_moneykindname m  where t.moneykindno=m.id "
//							+ "and m.moneykindno="
//							+ this.Currency
//							+ " and t.executetime<sysdate-1   and t.type='2' order by t.executetime desc  "); // 结算汇率
//
//			if (changerates.size() < 1 && changeratesbalance.size() < 1) {
//				responseCode = 12;
//				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;
//				md5Value = md5.getMD5ofStr(resultMd5);
//				message = "Payment Declined";
//				return INPUT;
//			}
//			InternationalExchangerate changerate = new InternationalExchangerate();
//			InternationalExchangerate settlementrate = new InternationalExchangerate();
//			for (int i = 0; i < changerates.size(); i++) {
//				Object[] tem = (Object[]) changerates.get(0);
//				changerate.setId(Long.valueOf(tem[0].toString()));
//				changerate.setRate(Double.valueOf(tem[1].toString()));
//				changerate.setType("1");
//			}
//			for (int i = 0; i < changeratesbalance.size(); i++) {
//				Object[] tem = (Object[]) changeratesbalance.get(0);
//				settlementrate.setId(Long.valueOf(tem[0].toString()));
//				settlementrate.setRate(Double.valueOf(tem[1].toString()));
//				settlementrate.setType("2");
//			}
//			// 获取某个币种当前的汇率
//			Double traderate = Double.valueOf(changerate.getRate());
//			if (traderate != null) {
//				rmbmoney = traderate * tradeMoney;
//				
//				BigDecimal b = new BigDecimal(rmbmoney);  
//				rmbmoney = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();  
//
//			} else {
//				// 商户币种没设置
//				responseCode = 12;
//				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;
//				md5Value = md5.getMD5ofStr(resultMd5);
//				message = "Payment Declined";
//				return INPUT;
//			}
//
////			// 验证交易网址是否存在
////			String url = request.getHeader("Referer");
////			String a[] = url.split("/");
////			tradeAdd = a[2];
////			System.out.println("==========================================="
////					+ tradeAdd);
////			String sqlCheckWeb = " select count(*) from International_Webchannels t where t.tradeWebsite='"
////					+ tradeAdd
////					+ "' and t.merchanid='"
////					+ merchant.getId()
////					+ "' ";
////			int checkWeb = this.tradeManager.intBySql(sqlCheckWeb);
////			String sqlCheckWebReturn = " select count(*) from International_Webchannels t where t.website='"
////					+ this.ReturnURL
////					+ "' and t.merchanid='"
////					+ merchant.getId()
////					+ "' ";
////			int checkWebReturn = this.tradeManager.intBySql(sqlCheckWebReturn);
////			String sqlstatus="select count(*) from International_Webchannels t where t.tradeWebsite='"
////				+ tradeAdd
////				+ "'and t.isblack='0' and t.merchanid='"
////				+ merchant.getId()
////				+ "'";
////			int checkurlstatus=this.tradeManager.intBySql(sqlstatus);
////			// 交易网址必须注册
////			if (checkWeb == 0) {
////				responseCode = 22;
////				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;
////				md5Value = md5.getMD5ofStr(resultMd5);
////				message = "Payment Declined";
////				return INPUT;
////			}
////			// 返回网址必须注册
////			if (checkWebReturn == 0) {
////				responseCode = 14;
////				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;
////				md5Value = md5.getMD5ofStr(resultMd5);
////				message = "Payment Declined";
////				return INPUT;
////			}
////			// 交易网址必须是没有禁止交易的
////			if (checkurlstatus == 0) {
////				responseCode = 32;//交易网址禁止交易
////				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;
////				md5Value = md5.getMD5ofStr(resultMd5);
////				message = "Payment Declined";
////				return INPUT;
////			}
//			// 获取通道卡种
//			logger.info("********************************开始获取商户通道卡种*********************************");
//			String cardlistsql = "select cct.shortName from InternationalMerchantChannels mc," +
//					"InternationalMerchant m,InternationalMerCreditCard mcc,InternationalCreditCardType cct " +
//					"where m.id=mc.merchantId " +
//					"and mcc.merChannelId=mc.id " +
//					"and cct.id=mcc.creditCardId " +
//					"and mcc.onoff=1 and mc.onoff=1 " +
//					"and m.merno="+MerNo;
//			List<String> cardlist = this.commonService.list(cardlistsql);
//			//新增直连所需
//			boolean cType=false;
//			if(StringUtils.isNotBlank(newcardtype)){
//				for (String c : cardlist) {
//					if(c.equals(newcardtype)){
//						cType=true;
//					}
//				}
//			}
//			if(cType=false){
//				responseCode = 33;//商户没有开通和输入卡相同的通道
//				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;
//				md5Value = md5.getMD5ofStr(resultMd5);
//				message = "Payment Declined";
//				return INPUT;
//			}
//			for (String c : cardlist) {
//				if(c.equals("3")){
//					cardtype_jcb = true;
//				}
//				if(c.equals("4")){
//					cardtype_visa = true;
//				}
//				if(c.equals("5")){
//					cardtype_master = true;
//				}
//				if(c.equals("6")){
//					cardtype_ae = true;
//				}
//				if(c.equals("7")){
//					cardtype_dc = true;
//				}
//			}
//			
//			logger.info("***************************获取商户通道卡种结束***************************");
//			logger.info("***************************开始获取商户交易金额*******************************");
//			
//			// 获取交易金额
////			md5src = merchantnoValue + BillNo + Currency
////					+ Amount + Language + ReturnURL + MD5key;
////			logger.info("***************************获取商户交易金额结束*********************************");
////			logger.info("***************************开始验证商户交易签名**********************************");
////			logger.info("签名数据:"+md5src);
////			md5src = md5.getMD5ofStr(md5src);
////			logger.info("**********************传过来的签名："+MD5info);
////			logger.info("**********************计算的签名："+md5src);
////			// 信息被篡改
////			// System.out.println("========"+merchantnoValue +"========"+
////			// BillNo+"========" + moneykind.getMoneykindno()+"========" +
////			// Amount+"========" + Language+"========" + ReturnURL+"========" +
////			// MD5key+"========" +"========5555============"+md5src);
////			// System.out.println("===============666666============="+MD5info);
////			if (!(md5src.equals(MD5info))) 
////			{
////				responseCode = 13;
////				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;
////				md5Value = md5.getMD5ofStr(resultMd5);
////				message = "Payment Declined";
////				logger.info("**********************商户交易签名错误，请检查*********************************");
////				return INPUT;
////			}
//			logger.info("**********************商户交易签名验证结束*********************************");
//			logger.info("**********************开始插入商户交易数据**********************************");
//			tradeMoney = (double) (Math.round((double) tradeMoney * 100) / 100.00);
//			rmbmoney = (double) (Math.round((double) rmbmoney * 100) / 100.00);
//			InternationalTradeinfo trade = new InternationalTradeinfo();
//			trade.setOrderNo(rorderno);
//			trade.setMerchantOrderNo(merchantOrderNo);
//			trade.setMerchantId(merchant.getId());
//			trade.setTradeTime(tradetime);
//			trade.setTradeAmount(Double.valueOf(this.Amount));
//			trade.setRmbAmount(this.rmbmoney);
//			trade.setMoneyType(Long.valueOf(Currency));
//			trade.setTradeState("30000000000000000000");
//			trade.setTradeRate(changerate.getId());
//			trade.setBalanceRate(settlementrate.getId());
//			trade.setTradeUrl(tradeAdd);
//			trade.setReturnUrl(this.ReturnURL);
//			trade.setLastDate(new Date());
//			trade.setBackCount(0d);
//			this.commonService.saveOrUpdate(trade);
//			responseCode = 0;
//			List<InternationalPayVersion> ipv = this.commonService
//					.list(" from  InternationalPayVersion t where t.merchantId='"
//							+ merchant.getId() + "'");
//			logger.info("*****************商户交易数据插入结束********************");
//			logger.info("*****************开始跳转到支付页面********************");
//			if (ipv.size() == 0) {
//				logger.info("*****************开始跳转********************"+ipv.size());
//				redirectPath = "jsp/ibank/carderInfo.jsp";
//				return SUCCESS;
//			} else {
//				InternationalPayVersion ipvs = ipv.get(0);;
//				logger.info("*****************开始跳转版本********************"+ipvs.getVersion());
//				//ipvs = ipv.get(0);
//				if (ipvs.getVersion().equals("1")) {
//					redirectPath = "jsp/ibank/carderInfo.jsp";
//					return SUCCESS;
//				}
//				else if (ipvs.getVersion().equals("2")) {
//					return "success2";
//				}
//				else if (ipvs.getVersion().equals("3")) {
//					return "success3";
//				}
//				else if (ipvs.getVersion().equals("4")) {
//					return "success4";
//				}
//				else{
//					for (InternationalPayVersion ver : ipv) {
//						if(StringUtils.isNotBlank(ver.getVerurl())){
//							if(tradeAdd.equals(ver.getVerurl())){
//								redirectPath = ver.getVersion();
//							}else{
//								redirectPath = "jsp/ibank/carderInfo.jsp";
//							}
//						}else{
//							redirectPath = "jsp/ibank/carderInfo.jsp";
//						}
//					}
//					return SUCCESS;
//				}
//			}
//			//return SUCCESS;
//		} catch (Exception e){
//			logger.error("系统接受交易请求出现未知错误：");
//			e.printStackTrace();
//			return ERROR;
//		}
		String urlHql="select uc.channelName,uc.webUrl from InternationalURLChannel uc where uc.merchantId='4654' and uc.webUrl like'%www.baidu.com%'";
		List urllist=commonService.list(urlHql);
		if(urllist.size()>0){
				Object[] tem2 = (Object[]) urllist.get(0);
				String merchantChanel="VPN";
				if(tem2[0].toString().equals(merchantChanel)){
					System.out.println("123131");
				}
			}
		return "";
	}
	public Boolean valWhiteList(String merchantNo,String cardNo,String ip,String email,String webUrl,String country,String riskType){
		String hql=" from InternationalWhitelist t where t.risktype='"+riskType+"'";
		List<InternationalWhitelist> list=commonService.list(hql);
		for(InternationalWhitelist items:list){
			if("1".equals(items.getWhitetype())&&merchantNo.toLowerCase().indexOf(items.getWhitename().toLowerCase())>=0){
				System.out.println("商户号"+items.getWhitename());
				return true;
			}
			if("2".equals(items.getWhitetype())&&cardNo.toLowerCase().indexOf(items.getWhitename().toLowerCase())>=0){
				System.out.println("卡号"+items.getWhitename());
				return true;
			}
			if("3".equals(items.getWhitetype())&&ip.toLowerCase().indexOf(items.getWhitename().toLowerCase())>=0){
				System.out.println("ip"+items.getWhitename());
				return true;
			}
			if("4".equals(items.getWhitetype())&&email.toLowerCase().indexOf(items.getWhitename().toLowerCase())>=0){
				System.out.println("email"+items.getWhitename());
				return true;
			}
			if("5".equals(items.getWhitetype())&&webUrl.toLowerCase().indexOf(items.getWhitename().toLowerCase())>=0){
				System.out.println("weburl"+items.getWhitename());
				return true;
			}
			if("6".equals(items.getWhitetype())&&country.toLowerCase().indexOf(items.getWhitename().toLowerCase())>=0){
				System.out.println("国家"+items.getWhitename());
				return true;
			}
		}
		return false;
		
	}
	public Boolean validateRiskItems(String riskitems,String riskType){
		String hql=" from InternationalRiskItems t where t.itemType='"+riskType+"'";
		List<InternationalRiskItems> list=commonService.list(hql);
		for(InternationalRiskItems items:list){
			if("1".equals(riskType)){
				if(riskitems.toLowerCase().indexOf(items.getItemName().toLowerCase())>=0){
					logger.info("出现风控匹配项：国家>>"+riskitems);	
					return true;
				}
			}
			if("2".equals(riskType)){
				if(riskitems.toLowerCase().indexOf(items.getItemName().toLowerCase())>=0){
					logger.info("出现风控匹配项：区域>>"+riskitems);	
					return true;
				}
			}
			if("3".equals(riskType)){
				String hqlweb=" from InternationalWebchannels w where w.tradeWebsite='"+riskitems.trim()+"'";
				List<InternationalWebchannels> listWeb=commonService.list(hqlweb);
				for(InternationalWebchannels web:listWeb){
					if(StringUtils.isNotBlank(web.getWebSiteType()+"")&&StringUtils.isNotBlank(items.getItemName()+"")){
						if(web.getWebSiteType().toLowerCase().indexOf(items.getItemName().toLowerCase())>=0){
							logger.info("出现风控匹配项：网站分类>>"+web.getWebSiteType());	
							return true;
						}
					}
				}
	
			}
			}
	return false;
	}
	 public static String bytes2Hex(byte[] bts) {
	        String des = "";
	        String tmp = null;
	        for (int i = 0; i < bts.length; i++) {
	            tmp = (Integer.toHexString(bts[i] & 0xFF));
	            if (tmp.length() == 1) {
	                des += "0";
	            }
	            des += tmp;
	        }
	        return des;
	    }

	public TradeManager getTradeManager() {
		return tradeManager;
	}

	public void setTradeManager(TradeManager tradeManager) {
		this.tradeManager = tradeManager;
	}

	public String getMerNo() {
		return MerNo;
	}

	public void setMerNo(String merNo) {
		MerNo = merNo;
	}

	public String getRorderno() {
		return rorderno;
	}

	public void setRorderno(String rorderno) {
		this.rorderno = rorderno;
	}

	public Date getTradetime() {
		return tradetime;
	}

	public void setTradetime(Date tradetime) {
		this.tradetime = tradetime;
	}

	public Long getMerchantnoValue() {
		return merchantnoValue;
	}

	public void setMerchantnoValue(Long merchantnoValue) {
		this.merchantnoValue = merchantnoValue;
	}

	public boolean isCheckMerch() {
		return checkMerch;
	}

	public void setCheckMerch(boolean checkMerch) {
		this.checkMerch = checkMerch;
	}

	public long getIsDredge() {
		return isDredge;
	}

	public void setIsDredge(long isDredge) {
		this.isDredge = isDredge;
	}

	public String getAmount() {
		return Amount;
	}

	public void setAmount(String amount) {
		Amount = amount;
	}

	public String getOrdercount() {
		return ordercount;
	}

	public void setOrdercount(String ordercount) {
		this.ordercount = ordercount;
	}

	public Double getTradeMoney() {
		return tradeMoney;
	}

	public void setTradeMoney(Double tradeMoney) {
		this.tradeMoney = tradeMoney;
	}

	public double getRmbmoney() {
		return rmbmoney;
	}

	public void setRmbmoney(double rmbmoney) {
		this.rmbmoney = rmbmoney;
	}

	public Double getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(Double tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	public String getChannels() {
		return channels;
	}

	public void setChannels(String channels) {
		this.channels = channels;
	}

	public String getBillNo() {
		return BillNo;
	}

	public void setBillNo(String billNo) {
		BillNo = billNo;
	}

	public String getMerchantOrderNo() {
		return merchantOrderNo;
	}

	public void setMerchantOrderNo(String merchantOrderNo) {
		this.merchantOrderNo = merchantOrderNo;
	}

	public String getCurrency() {
		return Currency;
	}

	public void setCurrency(String currency) {
		Currency = currency;
	}

	public String getLanguage() {
		return Language;
	}

	public void setLanguage(String language) {
		Language = language;
	}

	public String getReturnURL() {
		return ReturnURL;
	}

	public void setReturnURL(String returnURL) {
		ReturnURL = returnURL;
	}

	public String getMd5src() {
		return md5src;
	}

	public void setMd5src(String md5src) {
		this.md5src = md5src;
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public String getCooknumber() {
		return cooknumber;
	}

	public void setCooknumber(String cooknumber) {
		this.cooknumber = cooknumber;
	}

	public String getMD5info() {
		return MD5info;
	}

	public void setMD5info(String md5info) {
		MD5info = md5info;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getMD5key() {
		return MD5key;
	}

	public void setMD5key(String md5key) {
		MD5key = md5key;
	}

	public String getTradeAdd() {
		return tradeAdd;
	}

	public void setTradeAdd(String tradeAdd) {
		this.tradeAdd = tradeAdd;
	}

	public Long getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(Long moneyType) {
		this.moneyType = moneyType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getResultMd5() {
		return resultMd5;
	}

	public void setResultMd5(String resultMd5) {
		this.resultMd5 = resultMd5;
	}

	public String getTradeMoneyType() {
		return tradeMoneyType;
	}

	public void setTradeMoneyType(String tradeMoneyType) {
		this.tradeMoneyType = tradeMoneyType;
	}

	public String getMd5Value() {
		return md5Value;
	}

	public void setMd5Value(String md5Value) {
		this.md5Value = md5Value;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getCardbank() {
		return cardbank;
	}

	public void setCardbank(String cardbank) {
		this.cardbank = cardbank;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getShippingEmail() {
		return shippingEmail;
	}

	public void setShippingEmail(String shippingEmail) {
		this.shippingEmail = shippingEmail;
	}

	public String getShippingPhone() {
		return shippingPhone;
	}

	public void setShippingPhone(String shippingPhone) {
		this.shippingPhone = shippingPhone;
	}

	public String getShippingZipcode() {
		return shippingZipcode;
	}

	public void setShippingZipcode(String shippingZipcode) {
		this.shippingZipcode = shippingZipcode;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getShippingCity() {
		return shippingCity;
	}

	public void setShippingCity(String shippingCity) {
		this.shippingCity = shippingCity;
	}

	public String getShippingSstate() {
		return shippingSstate;
	}

	public void setShippingSstate(String shippingSstate) {
		this.shippingSstate = shippingSstate;
	}

	public String getShippingCountry() {
		return shippingCountry;
	}

	public void setShippingCountry(String shippingCountry) {
		this.shippingCountry = shippingCountry;
	}

	public String getProducts() {
		return products;
	}

	public void setProducts(String products) {
		this.products = products;
	}

	public String getShippingFirstName() {
		return shippingFirstName;
	}

	public void setShippingFirstName(String shippingFirstName) {
		this.shippingFirstName = shippingFirstName;
	}

	public String getShippingLastName() {
		return shippingLastName;
	}

	public void setShippingLastName(String shippingLastName) {
		this.shippingLastName = shippingLastName;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getRedirectPath() {
		return redirectPath;
	}

	public void setRedirectPath(String redirectPath) {
		this.redirectPath = redirectPath;
	}

	public boolean isCardtype_visa() {
		return cardtype_visa;
	}

	public void setCardtype_visa(boolean cardtype_visa) {
		this.cardtype_visa = cardtype_visa;
	}

	public boolean isCardtype_jcb() {
		return cardtype_jcb;
	}

	public void setCardtype_jcb(boolean cardtype_jcb) {
		this.cardtype_jcb = cardtype_jcb;
	}

	public boolean isCardtype_master() {
		return cardtype_master;
	}

	public void setCardtype_master(boolean cardtype_master) {
		this.cardtype_master = cardtype_master;
	}

	public boolean isCardtype_ae() {
		return cardtype_ae;
	}

	public void setCardtype_ae(boolean cardtype_ae) {
		this.cardtype_ae = cardtype_ae;
	}

	public boolean isCardtype_dc() {
		return cardtype_dc;
	}

	public void setCardtype_dc(boolean cardtype_dc) {
		this.cardtype_dc = cardtype_dc;
	}

	public String getCardnum() {
		return cardnum;
	}

	public void setCardnum(String cardnum) {
		this.cardnum = cardnum;
	}

	public String getCvv2() {
		return cvv2;
	}

	public void setCvv2(String cvv2) {
		this.cvv2 = cvv2;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getNewcardtype() {
		return newcardtype;
	}

	public void setNewcardtype(String newcardtype) {
		this.newcardtype = newcardtype;
	}

	public String getSfeVersion() {
		return sfeVersion;
	}

	public void setSfeVersion(String sfeVersion) {
		this.sfeVersion = sfeVersion;
	}

}
