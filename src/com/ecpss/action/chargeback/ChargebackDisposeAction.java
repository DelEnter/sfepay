package com.ecpss.action.chargeback;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ecpss.action.BaseAction;
import com.ecpss.action.filemanager.FileUpLoadAction;
import com.ecpss.model.payment.InternationalCardholdersInfo;
import com.ecpss.model.payment.InternationalTradeinfo;
import com.ecpss.model.shop.InternationalChargeBack;
import com.ecpss.service.iservice.SystemManagerService;
import com.ecpss.tools.TableExport;
import com.ecpss.tools.TableExportFactory;
import com.ecpss.util.DateUtil;
import com.ecpss.util.DownloadUtils;
import com.ecpss.util.StatusUtil;
import com.ecpss.web.PageInfo;

public class ChargebackDisposeAction extends BaseAction{
	@Autowired
	@Qualifier("systemManagerService")
	private SystemManagerService systemManagerService;
	/**
	 * 分页使用的对象
	 */
	private PageInfo info = new PageInfo();
	/**
	 * 列出导入excel中的数据没有在ecpss系统中找到交易数据的拒付处理交易
	 */
	private List<InternationalChargeBack> cbByNoTradeIdList;

	/**
	 * 重复的数据
	 */
	private List sameList;
	/**
	 * 上传文件名
	 */
	private File fileName;  
	/**
	 * 交易表id
	 */
	private Long tradeId;
	/**
	 * 拒付操作表id
	 */
	private Long chargebackId;
	private InternationalChargeBack cb;
	private InternationalTradeinfo ti;
	private String isRickCard;
	private String isBackCard;
	
	/**
	 * 批量拒付条件
	 */
	private Long [] orders;
	
	/**
	 * 下载商户申诉文件
	 */
	private String inputPath; // 指定要被下载的文件路径 
	private InputStream inputStream;
	private String fileNameByup;
	/**
	 * 查询条件
	 */
	private String orderNo;
	private String cardNo;
	private Long merchantNo;
	private String startDate;
	private String endDate;
	private String startImportDate;  //导入日期
	private String endImportDate;    //导入日期
	private String batchno;
	private String isProtest;
	
	
	private List<String> batchnoList;
	
	
	/**
	 * 显示出拒付交易处理查询
	 */
	public String chargebackTradeQuery(){
		StringBuffer sb = new StringBuffer();
		sb.append("select cb,ti from InternationalChargeBack cb,InternationalTradeinfo ti " +
				"where cb.tradeId=ti.id " +
				" and cb.isChargeBack!=4 ");
		if(StringUtils.isNotBlank(orderNo)){
			sb.append(" and ti.orderNo='"+orderNo.trim()+"' ");
		}
		if(merchantNo!=null){
			sb.append(" and ti.orderNo like '"+merchantNo+"%'");
		}
		if(StringUtils.isNotBlank(cardNo)){
			sb.append(" and cb.cardNoBy like '%"+cardNo.trim()+"%' ");
		}
		if(StringUtils.isNotBlank(batchno)){
			sb.append(" and cb.upbatchno='"+batchno.trim()+"' ");
		}
		if(StringUtils.isNotBlank(isProtest)){   //拒付情况
			sb.append(" and substr(ti.tradeState,3,1)='"+isProtest+"'");
		}
		if(StringUtils.isNotBlank(startDate)){  //开始日期
			sb.append(" and ti.tradeTime>=to_date('"+startDate+"','yyyy-MM-dd') ");
		}
		if(StringUtils.isNotBlank(endDate)){   //结束日期
			sb.append(" and ti.tradeTime<=to_date('"+endDate+" 23:59:59','yyyy-MM-dd hh24:mi:ss') ");
		}
		if(StringUtils.isNotBlank(startImportDate)){  //开始日期
			sb.append(" and cb.importDate>=to_date('"+startImportDate+"','yyyy-MM-dd') ");
		}
		if(StringUtils.isNotBlank(endImportDate)){   //结束日期
			sb.append(" and cb.importDate<=to_date('"+endImportDate+" 23:59:59','yyyy-MM-dd hh24:mi:ss') ");
		}
		sb.append(" order by substr(ti.tradeState,3,4),ti.protestTime desc");
		
		if(StringUtils.isNotBlank(this.query) && this.query.equals("0")){
			query = "1";
			//isProtest = "0";
		}else{
		info = this.commonService.listQueryResultByHql(sb.toString(),info);
		}

		
		String hql3="select distinct cb.upbatchno from InternationalChargeBack cb where cb.upbatchno like '1%' and cb.isChargeBack!=4 order by cb.upbatchno desc";
		batchnoList = this.commonService.list(hql3);
		
		return SUCCESS;
	}
	
	
	/**
	 * 上传银行给的拒付交易文档
	 * @return
	 */
	public String importChargebackTrade(){
		try {
			//this.messageAction = systemManagerService.importChargeBack(fileName);
		} catch (Exception e) {
			e.printStackTrace();
			
			return SUCCESS;
		}
		return SUCCESS;
	}

	/**
	 * 修改交易拒付操作
	 * @return
	 */
	public String chargeBack(){
		InternationalTradeinfo ti = (InternationalTradeinfo) this.commonService.load(InternationalTradeinfo.class, this.tradeId);
		ti.setTradeState(StatusUtil.updateStatus(ti.getTradeState(), 3, "1"));
		ti.setProtestTime(new Date());
		this.commonService.update(ti);
		return chargebackTradeQuery();
	}

	/**
	 * 跳转到修改拒付操作
	 * @return
	 */
	public String toUpdateChargeback(){
		cb = (InternationalChargeBack) this.commonService.load(InternationalChargeBack.class, this.chargebackId);
		if(cb.getTradeId()!=null){
			ti = (InternationalTradeinfo) this.commonService.load(InternationalTradeinfo.class, cb.getTradeId());
		}
		return SUCCESS;
	}
	/**
	 * 修改拒付
	 * @return
	 */
	public String updateChargeBack(){
		String hql = "select ti.id from InternationalTradeinfo ti where ti.orderNo='"+ti.getOrderNo()+"' ";
		Long tid = (Long) this.commonService.uniqueResult(hql);
		if(tid!=null){
			String hql1 = "select c.cardNo from InternationalCardholdersInfo c where c.tradeId="+tid;
			String cardNo = (String) this.commonService.uniqueResult(hql1);
			InternationalChargeBack cb1 = (InternationalChargeBack) this.commonService.load(InternationalChargeBack.class, this.chargebackId);
			//根据交易表的id查询出这个交易对应的卡号,然后跟上传的拒付操作里面的卡号对比 
			//如果一直就更改拒付,然后更改此交易状态
			if(getCarNo(cardNo.trim()).equals(cb1.getCardNoBy().trim())){
				cb1.setRemark(cb.getRemark());
				cb1.setIsChargeBack("0");
				cb1.setTradeId(tid);
				this.messageAction = this.systemManagerService.chargeBackUpdate(cb1, tid, isBackCard, isRickCard);
			}else{
				this.messageAction = "你输入的交易流水号对应的卡号跟上传的拒付卡号不一致.";
			}
		}else{
			this.messageAction = "查不到对应的交易";
		}
		return this.OPERATE_SUCCESS;
	}
	
	/**
	 * 批量拒付
	 * @return
	 */
	public String batchChargeBack(){
		if(orders.length>0){
			for (int i = 0; i < orders.length; i++) {
				InternationalChargeBack cb1 = (InternationalChargeBack) this.commonService.load(InternationalChargeBack.class, orders[i]);
				String hql1 = "select c.cardNo from InternationalCardholdersInfo c where c.tradeId="+cb1.getTradeId();
				String cardNo = (String) this.commonService.uniqueResult(hql1);
//				if(getCarNo(cardNo.trim()).equals(cb1.getCardNoBy().trim())){
					cb1.setIsChargeBack("0");
					this.messageAction = this.systemManagerService.chargeBackUpdate(cb1, cb1.getTradeId(), "false", "false");
//				}
			}
		}
		return this.OPERATE_SUCCESS;
	}
	
	
	/**
	 * 删除非拒付交易
	 * @return
	 */
	public String deleteChargeBack(){
		this.commonService.delete(InternationalChargeBack.class,this.chargebackId);
		return OPERATE_SUCCESS;
	}
	
	/**
	 * 下载商户上传的申诉文件
	 * @return
	 * @throws IOException 
	 */
	public String downloadCom() throws IOException{
		InternationalChargeBack c = (InternationalChargeBack) this.commonService.load(InternationalChargeBack.class, chargebackId);
		InputStream stream = FileUpLoadAction.class.getClassLoader().getResourceAsStream("/ecpss.properties");
		Properties p = new Properties();
		p.load(stream);
		inputPath=p.getProperty("upload_dir")+"/"+c.getComplaintsFileroute();
		String orgName = FilenameUtils.getBaseName(c.getComplaintsFileName())+"."+FilenameUtils.getExtension(c.getComplaintsFileroute());
		fileNameByup = new String(orgName.getBytes("GBK"),"iso-8859-1");
		return SUCCESS;
	}
	public InputStream getInputStream() throws Exception {
		inputStream = new FileInputStream(inputPath);
		// 通过 ServletContext，也就是application 来读取数据 
		return inputStream ; 
	}
	
	
	private String cardnum;  //卡号
	private List chargebackList;
	
	/**
	 * 单个查询拒付
	 * @return
	 */
	public String toChargeBackList(){
		StringBuffer sb = new StringBuffer();
		sb.append("select ti,m,ci,c from InternationalTradeinfo ti," +
						"InternationalMerchant m," +
						"InternationalCardholdersInfo ci," +
						"InternationalMerchantChannels mc," +
						"InternationalChannels c " +
				"where ti.merchantId=m.id " +     //交易表商户ID
				"and ci.tradeId=ti.id " +         //持卡人跟交易表
				"and ti.tradeChannel=mc.id " +    //交易表商户通道
				"and mc.channelId=c.id ");       //商户通道的通道
		
		if(StringUtils.isNotBlank(orderNo)){
			sb.append(" and ti.orderNo like '"+orderNo.trim()+"%'");
		}
		if(StringUtils.isNotBlank(cardnum)){
			sb.append(" and ci.cardNo='"+setCarNo(cardnum.trim())+"'");
		}
		
		sb.append(" order by ti.tradeTime desc");
		if(StringUtils.isNotBlank(orderNo) || StringUtils.isNotBlank(cardnum)){
			chargebackList = this.commonService.list(sb.toString());
		}
		return SUCCESS;
	}
	
	/**
	 * 进行单笔拒付交易处理
	 * @return
	 */
	public String toSingleChargeback(){
		ti = (InternationalTradeinfo) this.commonService.load(InternationalTradeinfo.class, this.tradeId);
		return SUCCESS;
	}
	
	/**
	 * 进行单笔交易拒付
	 * @return
	 */
	public String singleChargeBack(){
		String hql2 = "select c.id from InternationalChargeBack c where c.tradeId="+tradeId;
		List list = this.commonService.list(hql2);
		if(list.size()>0){
			this.messageAction = "拒付表已存在此交易";
		}else{
			SimpleDateFormat time1=new SimpleDateFormat("yyMMddHHmmss");

			String batchno = time1.format(new Date());
			String hql1 = "select c.cardNo from InternationalCardholdersInfo c where c.tradeId="+tradeId;
			String cardNo = (String) this.commonService.uniqueResult(hql1);
			ti = (InternationalTradeinfo) this.commonService.load(InternationalTradeinfo.class, this.tradeId);
			cb.setTradeId(ti.getId());
			cb.setAuthorizationNoBy(ti.getVIPAuthorizationNo());
			cb.setTradeAmountBy(ti.getRmbAmount());
			cb.setTradeDateBy(ti.getTradeTime().toString());
			cb.setCardNoBy(getCarNo(cardNo));
			cb.setImportDate(new Date());
			cb.setIsChargeBack("0");
			cb.setUpbatchno(batchno);
			this.commonService.save(cb);
			InternationalChargeBack cb1 = (InternationalChargeBack) this.commonService.load(InternationalChargeBack.class, cb.getId());
			//根据交易表的id查询出这个交易对应的卡号,然后跟上传的拒付操作里面的卡号对比 
			//如果一直就更改拒付,然后更改此交易状态
			this.messageAction = this.systemManagerService.chargeBackUpdate(cb1, tradeId, isBackCard, isRickCard);
		}
		return this.OPERATE_SUCCESS;
	}
	
	private String isdownload;
	private List sameListBy;
	/**
	 * 一个批次的批量数据
	 * @return
	 */
	public String getBatchCB(){
		if(fileName!=null){
			SimpleDateFormat time1=new SimpleDateFormat("yyMMddHHmmss");
			batchno = time1.format(new Date());
			this.messageAction = systemManagerService.importChargeBack(fileName,batchno);
		}
		StringBuffer sb = new StringBuffer();
		sb.append("select cb,ti from InternationalChargeBack cb,InternationalTradeinfo ti " +
				"where cb.tradeId=ti.id " +
				" and cb.isChargeBack=4 " +
				" and substr(ti.tradeState,3,1)=0 ");
		if(StringUtils.isNotBlank(batchno)){
			sb.append(" and cb.upbatchno='"+batchno.trim()+"' ");
		}
		sb.append(" order by substr(ti.tradeState,2,1) desc,substr(ti.tradeState,3,1),ti.protestTime desc");
		//获取未拒付的正常需要拒付的交易
		if(StringUtils.isNotBlank(isdownload)){
			List<Object[]> objList  = commonService.list(sb.toString());
			this.downloadCB(objList);
			return null;
		}else{
			info = this.commonService.listQueryResultByHql(sb.toString(),info);
		}
		//获取已经拒付的交易
		StringBuffer sb1 = new StringBuffer();
		sb1.append("select cb,ti from InternationalChargeBack cb,InternationalTradeinfo ti " +
				"where cb.tradeId=ti.id " +
				" and cb.isChargeBack=4 " +
				" and substr(ti.tradeState,3,1)=1 ");
		if(StringUtils.isNotBlank(batchno)){
			sb1.append(" and cb.upbatchno='"+batchno.trim()+"' ");
		}
		sb1.append(" order by substr(ti.tradeState,3,1),ti.protestTime desc");
		this.sameList = this.commonService.list(sb1.toString());
		
		String hql="select cb from InternationalChargeBack cb where cb.tradeId is null and cb.isChargeBack=4 ";
		cbByNoTradeIdList = this.commonService.list(hql);
		//获取批次号列表
		String hql3="select distinct cb.upbatchno from InternationalChargeBack cb where cb.upbatchno like '1%' and cb.isChargeBack=4 order by cb.upbatchno desc";
		batchnoList = this.commonService.list(hql3);
		//获取重复数据
		StringBuffer sb4 = new StringBuffer();
		sb4.append("select cb,ti from InternationalChargeBack cb,InternationalTradeinfo ti " +
				"where 1=1  and ti.id=cb.tradeId and cb.isChargeBack=4 ");
		
		sb4.append(
				" and (cb.cardNoBy,cb.authorizationNoBy,cb.tradeAmountBy) in  " +
				"(select cardNoBy,authorizationNoBy,tradeAmountBy " +
					"from InternationalChargeBack " + 
				"group by cardNoBy,authorizationNoBy,tradeAmountBy having count(*) > 1) order by importDate desc");
		sameListBy = this.commonService.list(sb4.toString());
		
		
		this.getLoaction().setReload(true);
		return SUCCESS;
	}
	
	/**
	 * 下载商户交易查询记录
	 * @return
	 */
	public void downloadCB(List<Object[]> oArray) {
		TableExport export = TableExportFactory.createExcelTableExport();
		export.addTitle(new String[]{"卡号","交易金额","交易RMB金额","退款金额","日期","终端号",
				"授权号","档号","物品编号","是否退款","是否拒付","流水号","批次号","拒付理由"});
		export.setTableName("CB-"+DateUtil.getDateTime());
		InternationalTradeinfo t = new InternationalTradeinfo();
		InternationalChargeBack ci = new InternationalChargeBack();
		int i=1;
		Double refundamount=0D;
		for(Object[] obj:oArray){
			t = (InternationalTradeinfo) obj[1];
			cb = (InternationalChargeBack) obj[0];
			if(t.getTradeState().substring(1, 2).equals("2")){
				refundamount=0D;
			}else{
				refundamount=t.getRmbAmount();
			}
			export.addRow(new Object[]{cb.getCardNoBy(),t.getTradeAmount(),t.getRmbAmount(),refundamount,t.getTradeTime(),t.getVIPTerminalNo(),
					t.getVIPAuthorizationNo(),cb.getRef(),cb.getProductNo(),getStates().getStateName(t.getTradeState(), 2),getStates().getStateName(t.getTradeState(), 3),t.getOrderNo(),cb.getUpbatchno(),cb.getRemark()});	
			i++;
		}
		OutputStream os = DownloadUtils.getResponseOutput("CB-"+DateUtil.getDateTime()+".xls");
		export.export(os);
		DownloadUtils.closeResponseOutput();
	}
	
	
	public PageInfo getInfo() {
		return info;
	}


	public void setInfo(PageInfo info) {
		this.info = info;
	}


	public File getFileName() {
		return fileName;
	}


	public void setFileName(File fileName) {
		this.fileName = fileName;
	}


	public SystemManagerService getSystemManagerService() {
		return systemManagerService;
	}


	public void setSystemManagerService(SystemManagerService systemManagerService) {
		this.systemManagerService = systemManagerService;
	}


	public List<InternationalChargeBack> getCbByNoTradeIdList() {
		return cbByNoTradeIdList;
	}


	public void setCbByNoTradeIdList(List<InternationalChargeBack> cbByNoTradeIdList) {
		this.cbByNoTradeIdList = cbByNoTradeIdList;
	}


	public Long getTradeId() {
		return tradeId;
	}


	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}


	public Long getChargebackId() {
		return chargebackId;
	}


	public void setChargebackId(Long chargebackId) {
		this.chargebackId = chargebackId;
	}


	public InternationalChargeBack getCb() {
		return cb;
	}


	public void setCb(InternationalChargeBack cb) {
		this.cb = cb;
	}


	public InternationalTradeinfo getTi() {
		return ti;
	}


	public void setTi(InternationalTradeinfo ti) {
		this.ti = ti;
	}


	public String getInputPath() {
		return inputPath;
	}


	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}


	public String getFileNameByup() {
		return fileNameByup;
	}


	public void setFileNameByup(String fileNameByup) {
		this.fileNameByup = fileNameByup;
	}


	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}


	public String getIsRickCard() {
		return isRickCard;
	}


	public void setIsRickCard(String isRickCard) {
		this.isRickCard = isRickCard;
	}


	public String getIsBackCard() {
		return isBackCard;
	}


	public void setIsBackCard(String isBackCard) {
		this.isBackCard = isBackCard;
	}


	public String getOrderNo() {
		return orderNo;
	}


	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}


	public Long getMerchantNo() {
		return merchantNo;
	}


	public void setMerchantNo(Long merchantNo) {
		this.merchantNo = merchantNo;
	}


	public String getStartDate() {
		return startDate;
	}


	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	public String getCardNo() {
		return cardNo;
	}


	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}


	public Long[] getOrders() {
		return orders;
	}


	public void setOrders(Long[] orders) {
		this.orders = orders;
	}


	public List getSameList() {
		return sameList;
	}


	public void setSameList(List sameList) {
		this.sameList = sameList;
	}


	public String getCardnum() {
		return cardnum;
	}


	public void setCardnum(String cardnum) {
		this.cardnum = cardnum;
	}


	public List getChargebackList() {
		return chargebackList;
	}


	public void setChargebackList(List chargebackList) {
		this.chargebackList = chargebackList;
	}


	public String getBatchno() {
		return batchno;
	}


	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}


	public String getIsProtest() {
		return isProtest;
	}


	public void setIsProtest(String isProtest) {
		this.isProtest = isProtest;
	}


	public List<String> getBatchnoList() {
		return batchnoList;
	}


	public void setBatchnoList(List<String> batchnoList) {
		this.batchnoList = batchnoList;
	}


	public String getIsdownload() {
		return isdownload;
	}


	public void setIsdownload(String isdownload) {
		this.isdownload = isdownload;
	}


	public List getSameListBy() {
		return sameListBy;
	}


	public void setSameListBy(List sameListBy) {
		this.sameListBy = sameListBy;
	}


	public String getStartImportDate() {
		return startImportDate;
	}


	public void setStartImportDate(String startImportDate) {
		this.startImportDate = startImportDate;
	}


	public String getEndImportDate() {
		return endImportDate;
	}


	public void setEndImportDate(String endImportDate) {
		this.endImportDate = endImportDate;
	}


}
