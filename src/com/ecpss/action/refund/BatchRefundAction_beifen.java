package com.ecpss.action.refund;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import jxl.Workbook;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ecpss.action.BaseAction;
import com.ecpss.action.filemanager.FileUpLoadAction;
import com.ecpss.excel.builder.RowResult;
import com.ecpss.excel.builder.jexcel.JExcelRowObjectBuilder;
import com.ecpss.excel.cell.jexcel.NumberCellValueConvertor;
import com.ecpss.excel.rule.impl.CellRuleImpl;
import com.ecpss.excel.rule.impl.RowRuleImpl;
import com.ecpss.model.FileManager;
import com.ecpss.model.payment.InternationalCardholdersInfo;
import com.ecpss.model.payment.InternationalTradeinfo;
import com.ecpss.model.refund.InternationalRefundHistory;
import com.ecpss.model.refund.InternationalRefundManager;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.service.iservice.RefundService;
import com.ecpss.tools.TableExport;
import com.ecpss.tools.TableExportFactory;
import com.ecpss.util.DateUtil;
import com.ecpss.util.DownloadUtils;
import com.ecpss.util.UploadUtils;
import com.ecpss.vo.ImportRefund;

public class BatchRefundAction_beifen extends BaseAction{

	@Autowired
	@Qualifier("refundService")
	private RefundService refundService;
	
	private List<FileManager> fileList;
	/**
	 * 下载退款
	 */
	private Long fileId;
	private String inputPath; // 指定要被下载的文件路径 
	private InputStream inputStream;
	private String refunddownFileName;
	/**
	 * 补充交易数据
	 */
	private Long tradeId;
	private Object[] o;
	private String dccCurrency;
	private Double dccAmount;
	
	
	/**
	 * 批量生成退款文件
	 * 定时生成或者人工
	 */
	public String batchRefundDispose(){
		System.out.println("开始生成退款文件");
		String batch = refundService.batchRefund();
		if(StringUtils.isNotBlank(batch)){
			this.messageAction = "生成成功,请检查文件是否正确";
		}else{
			this.messageAction="生成失败,请检查数据是否正确";
		}
		String hql = "select f from FileManager f where f.usetype='0' order by f.filedate desc ";
		fileList = this.commonService.list(hql);
		return SUCCESS;
		
	}
	
	/**
	 * 下载批量退款文件
	 * @return
	 */
	public String todownloadBatchRefundByTxt(){
		String hql = "select f from FileManager f where f.usetype='0' order by f.filedate desc ";
		fileList = this.commonService.list(hql);
		return SUCCESS;
	}
	
	/**
	 * 下载退款文件
	 * @return
	 * @throws IOException 
	 */
	public String downbatchrefund() throws IOException{
		FileManager c = (FileManager) this.commonService.load(FileManager.class, fileId);
		InputStream stream = FileUpLoadAction.class.getClassLoader().getResourceAsStream("/ecpss.properties");
		Properties p = new Properties();
		p.load(stream);
		inputPath=p.getProperty("refund_dir")+"/"+c.getFileroute();
		String orgName = FilenameUtils.getBaseName(c.getFilename()+"."+FilenameUtils.getExtension(c.getFileroute()));
		refunddownFileName = new String(orgName.getBytes("GBK"),"iso-8859-1");
		if(c.getDownloadcount()==null){
			c.setDownloadcount(0L);
		}
		c.setDownloadcount(c.getDownloadcount()+1L);
		c.setDownloadDate(new Date());
		this.commonService.update(c);
		return SUCCESS;
	}
	public InputStream getInputStream() throws Exception {
		inputStream = new FileInputStream(inputPath);
		// 通过 ServletContext，也就是application 来读取数据 
		return inputStream ; 
	}

	
	/**
	 * 补充交易数据
	 * @return
	 */
	public String toinputFulInfos(){
		String hql = "select card.cardNo, card.expiryDate, card.cvv2, t.orderNo, t.rmbAmount, chann.channelName, t.id,t.VIPTerminalNo," +
				"t.VIPBatchNo,t.VIPAuthorizationNo" +
		" FROM InternationalTradeinfo t, InternationalCardholdersInfo card, InternationalMerchantChannels merchan, " +
		"InternationalChannels chann" +
		" WHERE t.id=card.tradeId " +
		"AND t.tradeChannel = merchan.id " +
		"and merchan.channelId=chann.id " +
		" AND t.id="+tradeId+"";
		o = (Object[])commonService.uniqueResult(hql);
		return SUCCESS;
	}
	
	/**
	 * 提交补充信息
	 * @return
	 */
	public String tijiaoinputfull(){
		InternationalTradeinfo ti = (InternationalTradeinfo) this.commonService.load(InternationalTradeinfo.class, tradeId);
		ti.setDCCTradeAmount(dccAmount);
		ti.setDCCTradeCurrency(dccCurrency);
		this.commonService.update(ti);
		this.messageAction="提交成功";
		return SUCCESS;
	}
	
	
	

	private File fileName;   //上传的文件
	
	/**
	 * 上传excel文件到处txt文件
	 * @return
	 */
	public String uploadExcel(){
		try{
			if(fileName!=null){
				Workbook workBook=Workbook.getWorkbook(fileName);
				RowRuleImpl rowRule = new RowRuleImpl();
				rowRule.addCellRule(new CellRuleImpl("A","cardNo"));
				rowRule.addCellRule(new CellRuleImpl("F", "tradeAmountRMB",
						new NumberCellValueConvertor()));
				rowRule.addCellRule(new CellRuleImpl("G", "refundAmountRMB",
						new NumberCellValueConvertor()));
				rowRule.addCellRule(new CellRuleImpl("E","terminalNo"));
				rowRule.addCellRule(new CellRuleImpl("F","authNO"));
				JExcelRowObjectBuilder reveBuilder = new JExcelRowObjectBuilder();
				reveBuilder.setSheet(workBook.getSheet(0));
				reveBuilder.setTargetClass(ImportRefund.class);
				reveBuilder.setRule(1, workBook.getSheet(0).getRows(), rowRule);
				
				RowResult<ImportRefund>[] cons = reveBuilder.parseExcel();
				ImportRefund in;
				InternationalTradeinfo ti;
				InternationalCardholdersInfo ci;
				String merchantidby = null;
				Boolean flag=true;
				String merchantId9 = null;
				StringBuffer write = new StringBuffer();
				for (RowResult<ImportRefund> rowResult : cons) {
					in = rowResult.getRowObject();
					StringBuffer sb = new StringBuffer();
					sb.append("select ti,ci from InternationalTradeinfo ti,InternationalCardholdersInfo ci  " +
							"WHERE ci.tradeId=ti.id " +
							"and substr(ti.tradeState,1,1)=1 " +
							"and ti.orderNo='"+in.getCardNo().trim()+"' ");
					//sb.append("  order by ti.tradetime desc ");
					System.out.println(sb.toString());
					List<Object[]> objList  = this.commonService.list(sb.toString());
						for(Object[] obj:objList){
							try{
								
							
							StringBuffer writeRefund = new StringBuffer();
							ti = (InternationalTradeinfo) obj[0];
							ci = (InternationalCardholdersInfo) obj[1];
							System.out.println(in.getCardNo());
							
							String aaaa = "select rm.id from InternationalRefundManager rm where rm.tradeId="+ti.getId();
							List list222 = this.commonService.list(aaaa);
							if(list222.size()>0){
								writeRefund.append("\n"+ti.getOrderNo()+"refundding\n");
							}else{
								if(ti.getVIPTerminalNo().startsWith("7777") || ti.getVIPTerminalNo().startsWith("7669")){
									String merchantId = (String) this.commonService.list("select tm.merchantNo from InternationalTerminalManager tm where tm.terminalNo='"+ti.getVIPTerminalNo()+"' ").get(0);
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
									writeRefund.append(ti.getVIPTerminalNo().trim());
									//第五位   1个空格
									writeRefund.append(" ");
									//第六位  卡号(16位)
									writeRefund.append(ci.getCardNo().trim());
									//第七位  3个空格
									writeRefund.append("   ");
									//第八位 有效期(4位)  YYMM
									writeRefund.append(ci.getExpiryDate().substring(2, 4)+ci.getExpiryDate().substring(0, 2));
									//第九位  1个空格
									writeRefund.append(" ");
									//第十位 销售/退款 交易人民币金额Decimal place(2)    
									writeRefund.append(return12Amount(in.getRefundAmountRMB().toString()));
									//第十一位 DCC销售交易外币金额Decimal place(2)  退款的外币交易金额
									if(ti.getVIPTerminalNo().startsWith("7777")){
										writeRefund.append(return12Amount(in.getTradeAmountRMB().toString()));
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
									writeRefund.append(time.format(ti.getTradeTime()));
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
									writeRefund.append(time3.format(ti.getTradeTime()));
									
									//一笔交易输入完成以后换行
									writeRefund.append("\n");
								}else{
									writeRefund.append("\n "+ti.getOrderNo()+"  3d refund   \n");
								}
							}
							//写入到退款文件中改变退款状态
							write.append(writeRefund.toString());
							
							
						}catch (Exception e) {
							e.printStackTrace();
							continue;
						}
					}
				}
				//结尾以9结果   
				write.append("9000000            000000");
				
				//生成txt文件
				try{   
					SimpleDateFormat time1=new SimpleDateFormat("yyyyMMddHHmmss");
					//文件名
					String imageFileName = "REFUND_"+merchantId9+"_"+time1.format(new Date())+"(excel生成的).txt";
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
					this.commonService.save(filemanager);
					out.close();
				}catch(Exception e) {
					System.out.println("失败");
					e.printStackTrace();
				}
			}
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return SUCCESS;
		}
				
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
	
	
	private File fileNameBytxt;

	/**
	 * 根据txt文件生成一个退款申请文件
	 * @return
	 * @throws Exception
	 */
	public String createExcelApplyRefund() throws Exception{
		//读取.txt退款文件
		InputStreamReader is = new InputStreamReader( new FileInputStream(fileNameBytxt));
		BufferedReader in = new BufferedReader(is);
		String line = in.readLine();
		line = in.readLine();       // 读取第一行
		List<InternationalRefundHistory> rhList = new ArrayList<InternationalRefundHistory>();
		while (line != null) {          // 如果 line 为空说明读完了
            if(line.length()>40){
            	//line.substring(28, 44)  //卡号
            	//line.substring(52, 64)  //交易金额
            	//line.substring(80, 92)  //退款金额
            	//line.substring(98, 104)  //交易日期
            	//line.substring(19, 27) //终端号
            	//line.substring(92, 98)  //授权号
            	//line.substring(4,19)
				InternationalRefundHistory r = new InternationalRefundHistory();
				r.setCardNo(line.substring(28, 44));
				r.setAuthorizationNo(line.substring(92, 98));
				r.setRefundAmount(Double.valueOf(line.substring(80, 92).trim())/100);
				r.setTerminalNo(line.substring(19, 27));
				r.setTradeTime(line.substring(98, 104));
				r.setTradeAmount(Double.valueOf(line.substring(52, 64).trim())/100);
				r.setCheckInman(line.substring(4,19));
				rhList.add(r);
            }
            line = in.readLine();   // 读取下一行
        }
        is.close();
        in.close();
        
        
        TableExport export = TableExportFactory.createExcelTableExport();
		export.addTitle(new String[]{"商户号","卡号","交易金额","退款金额","交易日期",	"终端号","授权号码"});
		export.setTableName("card");
		for (InternationalRefundHistory rh : rhList) {
			String date = rh.getTradeTime().substring(0,2)+"-"+rh.getTradeTime().substring(2,4)+"-"+rh.getTradeTime().substring(4,6);
			export.addRow(new Object[]{rh.getCheckInman(),rh.getCardNo(),
					rh.getTradeAmount(),rh.getRefundAmount(),
					DateUtil.formatDate(DateUtil.toDateTime("20"+date+" 00:00:00")),
					rh.getTerminalNo(),rh.getAuthorizationNo()});	
		}
		OutputStream os = DownloadUtils.getResponseOutput("申请退款.xls");
		export.export(os);
		DownloadUtils.closeResponseOutput();
		return SUCCESS;
	}
	
	
	public List<FileManager> getFileList() {
		return fileList;
	}

	public void setFileList(List<FileManager> fileList) {
		this.fileList = fileList;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public String getInputPath() {
		return inputPath;
	}

	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}

	public String getRefunddownFileName() {
		return refunddownFileName;
	}

	public void setRefunddownFileName(String refunddownFileName) {
		this.refunddownFileName = refunddownFileName;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

	public Object[] getO() {
		return o;
	}

	public void setO(Object[] o) {
		this.o = o;
	}

	public String getDccCurrency() {
		return dccCurrency;
	}

	public void setDccCurrency(String dccCurrency) {
		this.dccCurrency = dccCurrency;
	}

	public Double getDccAmount() {
		return dccAmount;
	}

	public void setDccAmount(Double dccAmount) {
		this.dccAmount = dccAmount;
	}

	public File getFileName() {
		return fileName;
	}

	public void setFileName(File fileName) {
		this.fileName = fileName;
	}

	public File getFileNameBytxt() {
		return fileNameBytxt;
	}

	public void setFileNameBytxt(File fileNameBytxt) {
		this.fileNameBytxt = fileNameBytxt;
	}
	
}
