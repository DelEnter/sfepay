package com.ecpss.action.merchant;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.ecpss.action.BaseAction;
import com.ecpss.action.filemanager.FileUpLoadAction;
import com.ecpss.model.payment.InternationalCardholdersInfo;
import com.ecpss.model.payment.InternationalTradeinfo;
import com.ecpss.model.refund.InternationalRefundManager;
import com.ecpss.model.shop.InternationalChargeBack;
import com.ecpss.tools.TableExport;
import com.ecpss.tools.TableExportFactory;
import com.ecpss.util.AES;
import com.ecpss.util.DateUtil;
import com.ecpss.util.DownloadUtils;
import com.ecpss.util.UploadUtils;
import com.ecpss.web.PageInfo;

public class MerChargeBackManagerAction extends BaseAction{

	private static final int BUFFER_SIZE = 16 * 1024 ;
	/**
	 * 分页标签使用对象
	 */
	private PageInfo info = new PageInfo();
	
	private String inputPath; // 指定要被下载的文件路径 
    /**
     * 商户上传文件
     */
    private File myFile;
    /**
     * 上传的文件对应的文件名
     */
    private String fileName;
    private String imageFileName;
    private String caption;
	/**
	 * 拒付交易表id
	 */
    private Long chargeBackId;
	
    private InternationalChargeBack cb;
    
    private String orderNo;
    private String merchantOrderNo;
    private String startDate;
    private String endDate;
    private String isdownload;
	/**
	 * 获取该商户下的所有拒付信息
	 * @return
	 */
	public String merChargeBackQuery() throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append("select cb,ti,card from InternationalChargeBack cb," +
				"InternationalTradeinfo ti,InternationalCardholdersInfo card " +
				"where cb.tradeId=ti.id " +
				"and card.tradeId=ti.id " +				
				"and ti.merchantId="+ getMerchantBean().getMerchantId() + " "+
				"and substr(ti.tradeState,3,1)='1' ");
		if(StringUtils.isNotBlank(orderNo)){
			sb.append(" and ti.orderNo='"+orderNo.trim()+"' ");
		}
		if(StringUtils.isNotBlank(merchantOrderNo)){
			sb.append(" and ti.merchantOrderNo='"+merchantOrderNo.trim()+"' ");
		}
		if(StringUtils.isNotBlank(startDate)){  //开始日期
			sb.append(" and cb.importDate>=to_date('"+startDate+"','yyyy-MM-dd') ");
		}
		if(StringUtils.isNotBlank(endDate)){   //结束日期
			sb.append(" and cb.importDate<=to_date('"+endDate+"','yyyy-MM-dd') ");
		}
		sb.append(" order by cb.importDate desc");
		
		if(StringUtils.isNotBlank(isdownload)){
			List<Object[]> objList  = commonService.list(sb.toString());
			this.downloadChargeBackQuery(objList);
			return null;
		}else{
			info = this.commonService.listQueryResultByHql(sb.toString(),info);
			return SUCCESS;
		}
	}
	
	public void downloadChargeBackQuery(List<Object[]> oArray){
		TableExport export = TableExportFactory.createExcelTableExport();
		export.addTitle(new String[]{"序号","交易流水号","商户订单号","拒付日期","交易日期","订单金额","是否拒付","拒付理由","卡类型"});
		export.setTableName("拒付交易记录");
		InternationalTradeinfo t = new InternationalTradeinfo();
		InternationalChargeBack cb = new InternationalChargeBack();
		InternationalCardholdersInfo card =  new InternationalCardholdersInfo();
		int i=1;
		for(Object[] obj:oArray){
			t = (InternationalTradeinfo) obj[1];
			cb = (InternationalChargeBack) obj[0];
			card = (InternationalCardholdersInfo) obj[2];
			
			String cardNo=AES.getCarNo(card.getCardNo());
			if ((cardNo.startsWith("30")|| cardNo.startsWith("35"))&& cardNo.length() == 16) {
				cardNo = "JCB";
			}else if (cardNo.startsWith("4") && cardNo.length() == 16) {
				cardNo = "VISA";
			}else if (cardNo.startsWith("5") && cardNo.length() == 16) {
				cardNo = "Master";
			}else{
				cardNo = "ERROR";
			}			
			export.addRow(new Object[]{i,t.getOrderNo(),t.getMerchantOrderNo(),DateUtil.formatDate(cb.getImportDate()),DateUtil.formatDate(t.getTradeTime()),t.getTradeAmount(),
					getStates().getStateNameByClass(t.getTradeState(),3),
					cb.getRemark(),cardNo});	
			i++;
		}
		OutputStream os = DownloadUtils.getResponseOutput("拒付交易记录.xls");
		export.export(os);
		DownloadUtils.closeResponseOutput();
	}

	/**
	 * 商户提交申诉文件页面
	 * @return
	 */
	public String toMerSubmitCom(){
		cb = (InternationalChargeBack) this.commonService.load(InternationalChargeBack.class, chargeBackId);
		return SUCCESS;
	}

	/**
	 * 商户提交申诉文件
	 * @return
	 * @throws IOException 
	 */
	public String merSubmitCom() throws IOException{
		try {
			if(myFile!=null && myFile.exists()){
				InternationalChargeBack ccb = (InternationalChargeBack) this.commonService.load(InternationalChargeBack.class, chargeBackId);
				imageFileName = new Date().getTime() + "_"+ cb.getComplaintsFileName();
				InputStream stream = FileUpLoadAction.class.getClassLoader().getResourceAsStream("/ecpss.properties");
				Properties p = new Properties();
				p.load(stream);
			    File imageFile = new File(UploadUtils.getUploadDir()+"/"+ UploadUtils.getUploadFileOpPath(imageFileName));
			    System.out.println("copy file:"+myFile+" to "+imageFile);
			    FileUtils.copyFile(myFile, imageFile);
			    ccb.setComplaintsFiledate(new Date());
			    ccb.setComplaintsFileName(cb.getComplaintsFileName());
			    ccb.setIsChargeBack("1");
			    ccb.setComplaintsFileroute(UploadUtils.getUploadFileOpPath(imageFileName));
			    ccb.setComplaintsFilesize(imageFile.length());
				this.commonService.update(ccb);
	  	 	}
			this.messageAction = "Submit successful.";
			return this.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			this.messageAction = "Submit failed.";
			return this.SUCCESS;
		}
		
	}
	
	
	public PageInfo getInfo() {
		return info;
	}


	public void setInfo(PageInfo info) {
		this.info = info;
	}

	public String getInputPath() {
		return inputPath;
	}

	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}

	public File getMyFile() {
		return myFile;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public Long getChargeBackId() {
		return chargeBackId;
	}

	public void setChargeBackId(Long chargeBackId) {
		this.chargeBackId = chargeBackId;
	}

	public static int getBUFFER_SIZE() {
		return BUFFER_SIZE;
	}

	public InternationalChargeBack getCb() {
		return cb;
	}

	public void setCb(InternationalChargeBack cb) {
		this.cb = cb;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getIsdownload() {
		return isdownload;
	}

	public void setIsdownload(String isdownload) {
		this.isdownload = isdownload;
	}

	public String getMerchantOrderNo() {
		return merchantOrderNo;
	}

	public void setMerchantOrderNo(String merchantOrderNo) {
		this.merchantOrderNo = merchantOrderNo;
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
}
