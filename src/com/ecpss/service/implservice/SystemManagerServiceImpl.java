package com.ecpss.service.implservice;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecpss.action.pay.tc.ClientBoc;
import com.ecpss.action.pay.tc.XMLGetMessage;
import com.ecpss.dao.common.CommonDao;
import com.ecpss.excel.builder.RowResult;
import com.ecpss.excel.builder.jexcel.JExcelRowObjectBuilder;
import com.ecpss.excel.cell.jexcel.NumberCellValueConvertor;
import com.ecpss.excel.rule.impl.CellRuleImpl;
import com.ecpss.excel.rule.impl.RowRuleImpl;
import com.ecpss.model.payment.HuakuanedException;
import com.ecpss.model.payment.InternationalCardholdersInfo;
import com.ecpss.model.payment.InternationalTradeinfo;
import com.ecpss.model.shop.InternationalChargeBack;
import com.ecpss.model.shop.InternationalTerminalManager;
import com.ecpss.service.iservice.ImportExcelService;
import com.ecpss.service.iservice.SystemManagerService;
import com.ecpss.util.AES;
import com.ecpss.util.DateUtil;
import com.ecpss.util.StatusUtil;
import com.ecpss.util.StringUtil;
import com.ecpss.vo.ImportChargeBack;

@Service("systemManagerService")
@Transactional
public class SystemManagerServiceImpl implements SystemManagerService{

	@Autowired
	@Qualifier("commonDao")
	private CommonDao commonDao;
	@Autowired
	@Qualifier("importExcelService")
	private ImportExcelService importExcelService;

	public Long getTradeCountByTerminalNo(String TermialNo) {
		String daytime = DateUtil.getDate();
		String hql="select count(t.id) from InternationalTradeinfo t " +
				"where substr(t.tradeState,1,1)='1' " +
				"and trunc(t.tradeTime,'dd')=trunc(to_date('"+daytime+"','yyyy-MM-dd') ,'dd') "+
				"and t.VIPTerminalNo='"+TermialNo.trim()+"'";
		Long count = (Long) this.commonDao.uniqueResult(hql);
		return count;
	}

	public Long getTradeCountByTerminalNoMonth(String TermialNo) {
		String daytime = DateUtil.getDate();
		String hql="select count(t.id) from InternationalTradeinfo t " +
				"where substr(t.tradeState,1,1)='1' " +
				"and trunc(t.tradeTime,'MM')=trunc(to_date('"+daytime+"','yyyy-MM-dd') ,'MM') "+
				"and t.VIPTerminalNo='"+TermialNo.trim()+"'";
		Long count = (Long) this.commonDao.uniqueResult(hql);
		return count;
	}

		
	public void closeTerminalNoByMore() {
		String daytime = DateUtil.getDate();
		
		String hql1 = "select tm from InternationalTerminalManager tm where tm.onoff=1 ";
		List<InternationalTerminalManager> TMLIST = this.commonDao.list(hql1);
		for (InternationalTerminalManager tm : TMLIST) {
			String hql="select count(t.id) from InternationalTradeinfo t " +
			"where substr(t.tradeState,1,1)='1' " +
			"and trunc(t.tradeTime,'MM')=trunc(to_date('"+daytime+"','yyyy-MM-dd') ,'MM') "+
			"and t.VIPTerminalNo='"+tm.getTerminalNo().trim()+"'";
			
			Long count = (Long) this.commonDao.uniqueResult(hql);
			
			if(count >= tm.getTradeTimes()){
				tm.setOnoff("0");
				this.commonDao.update(tm);
			}
		}
		
		
	}

	public String importChargeBack(File fileName,String batchno) {
		try {
			String messageaction = null;
			if(fileName!=null){
				Long begin=System.currentTimeMillis();
				Workbook workBook=Workbook.getWorkbook(fileName);
				RowRuleImpl rowRule = new RowRuleImpl();
				rowRule.addCellRule(new CellRuleImpl("A", "ref"));
				rowRule.addCellRule(new CellRuleImpl("B", "productNo"));
				rowRule.addCellRule(new CellRuleImpl("C", "cardNo"));
				rowRule.addCellRule(new CellRuleImpl("D", "tradeDate"));
				rowRule.addCellRule(new CellRuleImpl("E", "tradeAmount",new NumberCellValueConvertor()));
				rowRule.addCellRule(new CellRuleImpl("F", "authorizationNo"));
				rowRule.addCellRule(new CellRuleImpl("G", "chargeBackRemark"));
				JExcelRowObjectBuilder reveBuilder = new JExcelRowObjectBuilder();
				reveBuilder.setSheet(workBook.getSheet(0));
				reveBuilder.setTargetClass(ImportChargeBack.class);
				reveBuilder.setRule(0, workBook.getSheet(0).getRows(), rowRule);
				
				RowResult<ImportChargeBack>[] cons = reveBuilder.parseExcel();
				ImportChargeBack cb;
				int count = 0;
				for (RowResult<ImportChargeBack> rowResult : cons) {
					cb = rowResult.getRowObject();
//					cb.setCardNo(StringUtil.deleteAll(cb.getCardNo(), '-'));
					List tList = getTradeListByCardNo(cb.getCardNo(),cb.getTradeAmount(),cb.getAuthorizationNo());
					if(tList.size()>0){
						for (int i=0;i<tList.size();i++) {
							Object[] obj=(Object[]) tList.get(i);
							InternationalTradeinfo t=(InternationalTradeinfo) obj[0];
							cb.setCardNo(AES.getCarNo(obj[1]+""));
							cb.setTradeAmount(t.getTradeAmount());
							cb.setTradeDate(t.getTradeTime()+"");
							this.saveChargeBack(t.getId(), cb,batchno);
							count++;
						}
					}else{
						this.saveChargeBack(null, cb,batchno);
						count++;
					}
				}
				Long end=System.currentTimeMillis() - begin;
				System.out.println("上传完毕,耗时:"+end+"毫秒.上传"+count+"条");
				messageaction =  "上传完毕,耗时:"+end+"毫秒.上传"+count+"条";
			}
			return messageaction;
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public List getTradeListByCardNo(String cardNo,Double tradeAmount,String orderNo){		
		String hql = "select ti,ci.cardNo from InternationalTradeinfo ti,InternationalCardholdersInfo ci " +
				"where ci.tradeId=ti.id " +
				" and substr(ti.tradeState,1,1)='1' "+
//				"and ci.cardNo='"+AES.setCarNo(cardNo.trim())+"' " +
//						"and ti.rmbAmount="+tradeAmount+" "+
						" and ti.orderNo='"+orderNo.trim()+"' ";
		List list = this.commonDao.list(hql);
		return list;
	}
	
	public void saveChargeBack(Long tradeId,ImportChargeBack icb,String batchno){
		
		InternationalChargeBack cb = new InternationalChargeBack();
		cb.setCardNoBy(icb.getCardNo());
		cb.setImportDate(new Date());
		cb.setLastDate(new Date());
		cb.setProductNo(icb.getProductNo());
		cb.setTradeAmountBy(icb.getTradeAmount());
		cb.setTradeDateBy(icb.getTradeDate());
		cb.setRef(icb.getRef());
		cb.setRemark(icb.getChargeBackRemark());
		cb.setTradeId(tradeId);
		cb.setIsChargeBack("4");
		cb.setUpbatchno(batchno);
//		if(tradeId!=null){
//			String hql = "select he.id from InternationalChargeBack he where he.tradeId="+tradeId;
//			List list = this.commonDao.list(hql);
//			if(list.size()==0){
//				this.commonDao.save(cb);
//			}
//		}else{
			this.commonDao.save(cb);
		//}
	}
	
	public boolean getByCardNoAndAmount(String cardNo,Double amount){
		String hql = "select cb from InternationalChargeBack cb " +
		"where cb.cardNoBy='"+cardNo.trim()+"' " +
				"and cb.tradeAmountBy="+amount;
		List<InternationalChargeBack> list = this.commonDao.list(hql);
		if(list.size()>1){
			return Boolean.TRUE;
		}else{
			return Boolean.FALSE;
		}
	}

	public void chargeBack(Long tradeId) {
		InternationalTradeinfo ti = (InternationalTradeinfo) this.commonDao.load(InternationalTradeinfo.class, tradeId);
		ti.setTradeState(StatusUtil.updateStatus(ti.getTradeState(), 3, "1"));
		ti.setProtestTime(new Date());
		this.commonDao.update(ti);
	}
	/**
	 * 拒付的时候把正在申请退款的交易干掉
	 * @param tradeId
	 */
	public void deleteRefundByChargeback(Long tradeId){
		String hql="select rm from InternationalRefundManager rm where rm.tradeId="+tradeId+" " +
				"and rm.refundState in (0,1,2,3)";
		List list = this.commonDao.list(hql);
		if(list.size()>0){
			String sql = "delete international_refundmanager rm where rm.tradeId="+tradeId;
			commonDao.deleteBySql(sql);
		}
	}
	
	/**
	 * 拒付操作的时候如果是已划款交易
	 * 保存到已划款异常表
	 */
	public void saveHuakuanExcetion(Long tradeId){
		InternationalTradeinfo ti = (InternationalTradeinfo) this.commonDao.load(InternationalTradeinfo.class, tradeId);
		HuakuanedException he = new HuakuanedException();
		if(ti.getTradeState().substring(7,8).equals("1")){
			String hql = "select he.id from HuakuanedException he where he.tradeId="+ti.getId()+" and he.tradeType=3";
			List list = this.commonDao.list(hql);
			if(list.size()==0){
				he.setTradeId(ti.getId());
				he.setTradeType("3");
				he.setIsAudit("0");
				he.setLastDate(new Date());
				this.commonDao.save(he);
			}
		}
	}
	
	public String chargeBackUpdate(InternationalChargeBack cb,Long tradeId,
			String isBackCard, String isRickCard) {
		try {
			System.out.println("****************"+tradeId);
			this.commonDao.update(cb);
			this.chargeBack(tradeId);
			this.deleteRefundByChargeback(tradeId);
			this.saveHuakuanExcetion(tradeId);
			if(isBackCard.equals("true")){
				if(importExcelService.getBackCardBean(cb.getCardNoBy())){
					//列为黑卡库
					importExcelService.saveBackCardInfo(null, cb.getCardNoBy(), null, null, null);
				}
			}
			if(isRickCard.equals("true")){
				//列为高风险库
				importExcelService.saveRickCardInfo(null, cb.getCardNoBy(), null, null, null);
			}
			return "拒付提交成功";
		} catch (Exception e) {
			e.printStackTrace();
			return "出现异常";
		}
	}

	public List getMerchantNoByCycle(Long cycle) {
		String hql = "select m.merno from SettlementCycleTime sc,InternationalMerchant m " +
				"where m.id=sc.merchant " +
				"and sc.cycleTime="+cycle+" order by m.id";
		List list = this.commonDao.list(hql);
		return list;
	}

	public XMLGetMessage BocCancelTrade(InternationalTradeinfo tradeinfo,
			InternationalCardholdersInfo cardinfo) {
		XMLGetMessage xgm2 = new XMLGetMessage();
		xgm2.setTxn_id("0220");
		xgm2.setSystrace(tradeinfo.getOrderNo().substring(tradeinfo.getOrderNo().length()-7,tradeinfo.getOrderNo().length()-1));
		xgm2.setInvoice(tradeinfo.getVIPBatchNo());
		xgm2.setTxn_date(tradeinfo.getBoc_date());
		xgm2.setTxn_time(tradeinfo.getBoc_time());
		xgm2.setRrn(tradeinfo.getBoc_rrn());
		xgm2.setTxn_amt(tradeinfo.getRmbAmount()+"");
		xgm2.setOrg_systrace(tradeinfo.getOrderNo().substring(tradeinfo.getOrderNo().length()-6));
		xgm2.setAuth_no(tradeinfo.getVIPAuthorizationNo());
		if(cardinfo.getCardNo().length()>20){
			xgm2.setPan(AES.getCarNo(cardinfo.getCardNo()));
		}else{
			xgm2.setPan(cardinfo.getCardNo());
		}
		xgm2.setPosid(tradeinfo.getVIPTerminalNo());
		ClientBoc cb2 = new ClientBoc();
		cb2.setXmlGetMessage(xgm2);
		try {
			xgm2 = cb2.getMessage("0220");
		} catch (Exception e) {
			xgm2 = null;
		}
		return xgm2;
	}

	public XMLGetMessage BocRefundTrade(InternationalTradeinfo tradeinfo,
			InternationalCardholdersInfo cardinfo,Double refundamount,String orderno) {
		XMLGetMessage xgm2 = new XMLGetMessage();
		xgm2.setTxn_id("0260");
		xgm2.setSystrace(orderno);
		xgm2.setInvoice(tradeinfo.getVIPBatchNo());
		xgm2.setTxn_date(tradeinfo.getBoc_date());
		xgm2.setTxn_time(tradeinfo.getBoc_time());
		xgm2.setRrn(tradeinfo.getBoc_rrn());
		xgm2.setTxn_amt(refundamount+"");   //退款金额
		//xgm2.setOrg_systrace(tradeinfo.getOrderNo().substring(tradeinfo.getOrderNo().length()-6));
		xgm2.setOrg_systrace(tradeinfo.getVIPBatchNo());
		xgm2.setAuth_no(tradeinfo.getVIPAuthorizationNo());
		if(cardinfo.getCardNo().length()>20){
			xgm2.setPan(AES.getCarNo(cardinfo.getCardNo()));
		}else{
			xgm2.setPan(cardinfo.getCardNo());
		}
		if(cardinfo.getExpiryDate().length()>20){
			xgm2.setExp_date(AES.getCarNo(cardinfo.getExpiryDate())
					.substring(2, 4)
					+ AES.getCarNo(cardinfo.getExpiryDate())
							.substring(0, 2));
		}else{
			xgm2.setExp_date(cardinfo.getExpiryDate().substring(2, 4)+cardinfo.getExpiryDate().substring(0, 2));
		}
		
		xgm2.setPosid(tradeinfo.getVIPTerminalNo());
		ClientBoc cb2 = new ClientBoc();
		cb2.setXmlGetMessage(xgm2);
		try {
			xgm2 = cb2.getMessage("0260");
		} catch (Exception e) {
			xgm2 = null;
		}
		return xgm2;
	}
	
	
	
	
}
