package com.ecpss.action.shop;

import java.io.File;
import java.util.Date;
import java.util.List;

import jxl.Workbook;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ecpss.action.BaseAction;
import com.ecpss.excel.builder.RowResult;
import com.ecpss.excel.builder.jexcel.JExcelRowObjectBuilder;
import com.ecpss.excel.rule.impl.CellRuleImpl;
import com.ecpss.excel.rule.impl.RowRuleImpl;
import com.ecpss.model.cardtype.InternationalCreditCardType;
import com.ecpss.model.channel.InternationalChannels;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.model.shop.InternationalMerchantTerminal;
import com.ecpss.model.shop.InternationalTerminalManager;
import com.ecpss.service.iservice.ChannelService;
import com.ecpss.service.iservice.SystemManagerService;
import com.ecpss.vo.ImportExcelBlackCard;
import com.ecpss.vo.ImportTerminalNo;
import com.ecpss.web.PageInfo;

public class TerminalManagerAction extends BaseAction{
	@Autowired
	@Qualifier("channelService")
	private ChannelService channelService;
	@Qualifier("systemManagerService")
	private SystemManagerService systemManagerService;
	
	public List<InternationalChannels> channelList;
	private List<InternationalCreditCardType> creditCardList;
	private PageInfo info = new PageInfo();
	
	private Long terminalId;
	private InternationalTerminalManager tm;
	
	private int pagesize = 20;
	private Long channelId;
	private Long creditCardId;
	private String terminalNo;
	private String onoff;
	
	
	private String merchantno;
	private Long merid;            //商户id
	private List merchantTerminalList;
	private List merchantNoList;
	private InternationalMerchant merchant;
	private List<InternationalTerminalManager> terminalList;
	private InternationalMerchantTerminal merchantTerminal;
	private Long merTerId;
	/**
	 * 进入终端管理页面
	 * @return
	 */
	public String toTerminalManager(){
		channelList = channelService.getChannelList();
		String hql="from InternationalCreditCardType";
		creditCardList = this.commonService.list(hql);
		
		//if(channelId!=null || creditCardId!=null || StringUtils.isNotBlank(terminalNo)){
			info.setPageSize(pagesize);
			StringBuffer sb = new StringBuffer();
			sb.append("select c.channelName,cct.cardName,tm from InternationalTerminalManager tm," +
					"InternationalChannels c," +
					"InternationalCreditCardType cct " +
					"where tm.channelId=c.id " +
					"and tm.creditCardId=cct.id ");
			if(channelId!=null){
				sb.append(" and tm.channelId="+channelId);
			}
			if(creditCardId!=null){
				sb.append(" and tm.creditCardId="+creditCardId);
			}
			if(StringUtils.isNotBlank(terminalNo)){
				sb.append(" and tm.terminalNo like '%"+terminalNo+"%' ");
			}
			if(StringUtils.isNotBlank(merchantno)){
				sb.append(" and tm.merchantNo like '%"+merchantno+"%' ");
			}
			sb.append("order by tm.channelId,tm.creditCardId ");
			this.commonService.listQueryResultByHql(sb.toString(), info);
			System.out.print("ttt------------"+sb.toString());
		//}
		return SUCCESS;
	}
	
	/**
	 * 新增/修改终端
	 * @return
	 */
	public String toTerminalManagerForm(){
		channelList = channelService.getChannelList();
		String hql="from InternationalCreditCardType";
		creditCardList = this.commonService.list(hql);
		if(terminalId!=null){
			tm = (InternationalTerminalManager) this.commonService.load(InternationalTerminalManager.class, terminalId);
		}
		return SUCCESS;
	}
	
	/**
	 * 新增修改终端
	 * @return
	 */
	public String terminalManager(){
		if(terminalId!=null){
			InternationalTerminalManager t = (InternationalTerminalManager) this.commonService.load(InternationalTerminalManager.class, terminalId);
			t.setChannelId(tm.getChannelId());
			t.setCreditCardId(tm.getCreditCardId());
			t.setTerminalNo(tm.getTerminalNo());
			t.setLastDate(new Date());
			t.setLastMan(getUserBean().getUserName());
			t.setBillingAddress(tm.getBillingAddress());
			t.setMerchantNo(tm.getMerchantNo());
			t.setTradeTimes(tm.getTradeTimes());
			t.setAndterminalNo(tm.getAndterminalNo());
			t.setNetstate(tm.getNetstate());
			t.setBanktype(tm.getBanktype());
			t.setHashcode(tm.getHashcode());
			t.setAuthcode(tm.getAuthcode());
			t.setBankBackRemark(tm.getBankBackRemark());
			this.commonService.update(t);
		}else{
			tm.setOnoff("1");//默认打开
			tm.setLastDate(new Date());
			tm.setLastMan(getUserBean().getUserName());
			tm.setIsuses("0");
			tm.setIsauto("0");
			tm.setAuthcode(tm.getAuthcode());
			tm.setHashcode(tm.getHashcode());
			tm.setBankBackRemark(tm.getBankBackRemark());
			commonService.save(tm);
		}
		this.messageAction="操作成功";		
		return this.OPERATE_SUCCESS;
	}
	
	public String terminalonoff(){
		if(onoff.equals("1")){
			onoff="0";
			this.messageAction="通道已关闭";
		}else if(onoff.equals("0")){
			onoff="1";
			this.messageAction="通道已开通";
		}
		InternationalTerminalManager t = (InternationalTerminalManager) this.commonService.load(InternationalTerminalManager.class, terminalId);
		t.setOnoff(onoff);
		t.setLastDate(new Date());
		t.setLastMan(getUserBean().getUserName());
		this.commonService.update(t);
		return this.OPERATE_SUCCESS;
	}
	/**
	 * 是否自动处理开关
	 * @return
	 */
	public String processonoff(){
		if(onoff.equals("1")){
			onoff="0";
			this.messageAction="已关闭";
		}else if(onoff.equals("0")){
			onoff="1";
			this.messageAction="已开通";
		}
		InternationalTerminalManager t = (InternationalTerminalManager) this.commonService.load(InternationalTerminalManager.class, terminalId);
		t.setIsauto(onoff);
		t.setLastDate(new Date());
		t.setLastMan(getUserBean().getUserName());
		this.commonService.update(t);
		return this.OPERATE_SUCCESS;
	}
	
	/**
	 * 显示商户终端设置详情
	 * @return
	 */
	public String showMerchantTerminalQuery(){
		StringBuffer sf = new StringBuffer();
		sf.append("select mt,tm,c from InternationalMerchantTerminal mt," +
				"InternationalMerchant m," +
				"InternationalTerminalManager tm," +
				"InternationalChannels c " +
				"where m.id=mt.merchantId " +
				"and tm.id=mt.terminalId " +
				"and c.id=mt.channelId ");
		//商户号
		if(StringUtils.isNotBlank(merchantno)){
			//终端号
			sf.append(" and m.merno='"+merchantno.trim()+"'");
			String hql=" and (c.channelName like 'VIP%' or c.channelName like 'EVIP%' or c.channelName like 'TC%' or c.channelName like 'CN3D%')";
			merchantTerminalList = this.commonService.list(sf.toString()+hql);
			//String hql1=" and c.channelName like 'VC%' ";
			
			StringBuffer sf1 = new StringBuffer();
			sf1.append("select mt,tm,c from InternationalMerchantTerminal mt," +
					"InternationalMerchant m," +
					"InternationalMigsMerchantNo tm," +
					"InternationalChannels c " +
					"where m.id=mt.merchantId " +
					"and tm.id=mt.terminalId " +
					"and c.id=mt.channelId ");
			sf1.append(" and (c.channelName like 'VC%' or c.channelName like 'VVIP%') ");
			sf1.append(" and m.merno='"+merchantno.trim()+"' ");
			merchantNoList = this.commonService.list(sf1.toString());
			
			String hlq="select m.id from InternationalMerchant m where m.merno="+merchantno;
			merid = (Long) this.commonService.uniqueResult(hlq);
		}
		this.getLoaction().setReload(true);
		return SUCCESS;
	}
	
	/**
	 * 跳转到添加商户通道,根据商户号ID
	 * @return
	 */
	public String toAddMerTerminal(){
		if(merTerId!=null){
			merchantTerminal = (InternationalMerchantTerminal) this.commonService.load(InternationalMerchantTerminal.class, merTerId);
		}
		merchant = (InternationalMerchant) this.commonService.load(InternationalMerchant.class, merid);
		//获取商户通道列表
		channelList = channelService.getChannelByMerid(merid);
		//获取终端号列表
		String hql="select tm from " +
			"InternationalMerchantChannels mc," +
			"InternationalTerminalManager tm " +
			"where mc.channelId=tm.channelId " +
			"and mc.merchantId="+merid;
		terminalList = this.commonService.list(hql);
		return SUCCESS;
	}
	/**
	 * 添加商户独立终端号
	 * @return
	 */
	public String addMerTerminal(){
		String hql="";
		if(terminalNo.startsWith("7777") || terminalNo.startsWith("7669") || terminalNo.startsWith("0788") || terminalNo.startsWith("3150")){
			hql = "select tm.id from InternationalTerminalManager tm " +
					"where tm.channelId="+merchantTerminal.getChannelId()+
					" and tm.terminalNo='"+terminalNo.trim()+"'";
		}else{
			hql ="select mmn.id from InternationalMigsMerchantNo mmn " +
					"where mmn.bankMerchantId='"+terminalNo.trim()+"'";
		}
		Long terid = 0L;
		List list = this.commonService.list(hql);
		if(list.size()>0){
			terid = (Long) list.get(0);
		}
		if(terid==0){
			this.messageAction="无此终端号";
		}
		merchantTerminal.setTerminalId(terid);
		merchantTerminal.setLastDate(new Date());
		merchantTerminal.setIsopen("1");
		merchantTerminal.setLastMan(getUserBean().getUserName());
		this.commonService.saveOrUpdate(merchantTerminal);
		this.messageAction="操作成功";
		return this.OPERATE_SUCCESS;
	}
	
	/**
	 * 是否自动处理开关
	 * @return
	 */
	public String terisopen(){
		if(onoff.equals("1")){
			onoff="0";
			this.messageAction="已关闭";
		}else if(onoff.equals("0")){
			onoff="1";
			this.messageAction="已开通";
		}
		InternationalMerchantTerminal t = (InternationalMerchantTerminal) this.commonService.load(InternationalMerchantTerminal.class, merTerId);
		t.setIsopen(onoff);
		t.setLastDate(new Date());
		t.setLastMan(getUserBean().getUserName());
		this.commonService.update(t);
		return this.OPERATE_SUCCESS;
	}
	private File fileName;   //上传的文件
	/**
	 * 批量导入终端号
	 * @return
	 */
	public String importTerminalNo(){

		try {
			if(fileName!=null){
				Workbook workBook=Workbook.getWorkbook(fileName);
				Long begin=System.currentTimeMillis();
				RowRuleImpl rowRule = new RowRuleImpl();
				rowRule.addCellRule(new CellRuleImpl("A", "terminalno"));
				rowRule.addCellRule(new CellRuleImpl("B", "merchantno"));
				rowRule.addCellRule(new CellRuleImpl("C", "terminalnounion"));
				rowRule.addCellRule(new CellRuleImpl("E", "billingaddress"));
				rowRule.addCellRule(new CellRuleImpl("F", "channelName"));
				rowRule.addCellRule(new CellRuleImpl("G", "cardtype"));
				rowRule.addCellRule(new CellRuleImpl("H", "netstate"));
				rowRule.addCellRule(new CellRuleImpl("I", "bankname"));
				rowRule.addCellRule(new CellRuleImpl("J", "hashcode"));
				rowRule.addCellRule(new CellRuleImpl("K", "authcode"));
				JExcelRowObjectBuilder reveBuilder = new JExcelRowObjectBuilder();
				reveBuilder.setSheet(workBook.getSheet(0));
				reveBuilder.setTargetClass(ImportTerminalNo.class);
				reveBuilder.setRule(1, workBook.getSheet(0).getRows(), rowRule);
				
				RowResult<ImportTerminalNo>[] cons = reveBuilder.parseExcel();
				ImportTerminalNo in;
				int count=0;
				int scount=0;
				
				for (RowResult<ImportTerminalNo> rowResult : cons) {
					in = rowResult.getRowObject();
					String channelsql="select c.id from InternationalChannels c  where c.channelName='"+in.getChannelName().trim()+"'";
					String cardsql="select c.id from InternationalCreditCardType c  where c.shortName='"+in.getCardtype().trim()+"'";
					Long channelid = (Long) this.commonService.uniqueResult(channelsql);
					Long cardid = (Long) this.commonService.uniqueResult(cardsql);
					String terminalsql = "select tm from InternationalTerminalManager tm where tm.terminalNo='"+in.getTerminalno().trim()+"' " +
							"and tm.channelId="+channelid+" and tm.creditCardId="+cardid;
					List list = this.commonService.list(terminalsql);
					if(list.size()==0){
						if (channelid!=null && cardid!=null){
							InternationalTerminalManager tm = new InternationalTerminalManager();
							tm.setChannelId(channelid);
							tm.setCreditCardId(cardid);
							tm.setLastDate(new Date());
							tm.setLastMan(getUserBean().getUserName());
							tm.setTerminalNo(in.getTerminalno());
							tm.setBillingAddress(in.getBillingaddress());
							if(StringUtils.isNotBlank(in.getTerminalnounion())){
								tm.setAndterminalNo(in.getTerminalnounion());
							}
							tm.setIsauto("0");
							tm.setIsuses("0");
							tm.setOnoff("0");
							tm.setTradeTimes(500L);
							tm.setMerchantNo(in.getMerchantno());
							tm.setNetstate(in.getNetstate());
							tm.setBanktype(in.getBankname());
							tm.setHashcode(in.getHashcode());
							tm.setAuthcode(in.getAuthcode());
							this.commonService.save(tm);
							count++;
						}else{
							scount++;
						}
					}else{
						scount++;
					}
				}
				Long end=System.currentTimeMillis() - begin;
				this.messageAction = "文件导入成功！耗时:"+end+"毫秒.成功上传了"+count+",失败"+scount;
			}
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			messageAction="导入失败";
		}
		return SUCCESS;
	}
	
	
	
	
	public ChannelService getChannelService() {
		return channelService;
	}

	public void setChannelService(ChannelService channelService) {
		this.channelService = channelService;
	}

	public List<InternationalChannels> getChannelList() {
		return channelList;
	}

	public void setChannelList(List<InternationalChannels> channelList) {
		this.channelList = channelList;
	}

	public List<InternationalCreditCardType> getCreditCardList() {
		return creditCardList;
	}

	public void setCreditCardList(List<InternationalCreditCardType> creditCardList) {
		this.creditCardList = creditCardList;
	}

	public PageInfo getInfo() {
		return info;
	}

	public void setInfo(PageInfo info) {
		this.info = info;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public Long getCreditCardId() {
		return creditCardId;
	}

	public void setCreditCardId(Long creditCardId) {
		this.creditCardId = creditCardId;
	}

	public String getTerminalNo() {
		return terminalNo;
	}

	public void setTerminalNo(String terminalNo) {
		this.terminalNo = terminalNo;
	}

	public Long getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(Long terminalId) {
		this.terminalId = terminalId;
	}

	public InternationalTerminalManager getTm() {
		return tm;
	}

	public void setTm(InternationalTerminalManager tm) {
		this.tm = tm;
	}

	public SystemManagerService getSystemManagerService() {
		return systemManagerService;
	}

	public void setSystemManagerService(SystemManagerService systemManagerService) {
		this.systemManagerService = systemManagerService;
	}

	public String getOnoff() {
		return onoff;
	}

	public void setOnoff(String onoff) {
		this.onoff = onoff;
	}

	public String getMerchantno() {
		return merchantno;
	}

	public void setMerchantno(String merchantno) {
		this.merchantno = merchantno;
	}

	public Long getMerid() {
		return merid;
	}

	public void setMerid(Long merid) {
		this.merid = merid;
	}

	public List getMerchantTerminalList() {
		return merchantTerminalList;
	}

	public void setMerchantTerminalList(List merchantTerminalList) {
		this.merchantTerminalList = merchantTerminalList;
	}

	public InternationalMerchant getMerchant() {
		return merchant;
	}

	public void setMerchant(InternationalMerchant merchant) {
		this.merchant = merchant;
	}

	public List<InternationalTerminalManager> getTerminalList() {
		return terminalList;
	}

	public void setTerminalList(List<InternationalTerminalManager> terminalList) {
		this.terminalList = terminalList;
	}

	public InternationalMerchantTerminal getMerchantTerminal() {
		return merchantTerminal;
	}

	public void setMerchantTerminal(InternationalMerchantTerminal merchantTerminal) {
		this.merchantTerminal = merchantTerminal;
	}

	public Long getMerTerId() {
		return merTerId;
	}

	public void setMerTerId(Long merTerId) {
		this.merTerId = merTerId;
	}

	public List getMerchantNoList() {
		return merchantNoList;
	}

	public void setMerchantNoList(List merchantNoList) {
		this.merchantNoList = merchantNoList;
	}

	public File getFileName() {
		return fileName;
	}

	public void setFileName(File fileName) {
		this.fileName = fileName;
	}
	
	
}
