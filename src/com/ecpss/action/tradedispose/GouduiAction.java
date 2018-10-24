package com.ecpss.action.tradedispose;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ecpss.action.BaseAction;
import com.ecpss.model.channel.InternationalChannels;
import com.ecpss.model.channel.InternationalMerchantChannels;
import com.ecpss.model.payment.InternationalCardholdersInfo;
import com.ecpss.model.payment.InternationalTradeinfo;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.service.common.CommonService;
import com.ecpss.util.StatusUtil;
import com.ecpss.util.StringUtil;
import com.ecpss.web.PageInfo;


public class GouduiAction extends BaseAction{
	@Autowired
	@Qualifier("commonService")
	private CommonService commonService;
	private List list = new ArrayList();
	private List repeatList = new ArrayList();
	private String uploadType;
	private String hql;
	private StringBuffer sb;
	private PageInfo info;
	private Object[] disposeId; 
	private String flag;
	private String isGoudui = "0";

	String orderno = null;
	String isreuslt = null;
	String tradeAmount = null;
	String tradeTime = null;//交易时间
	String balancetime = null;//结束时间（结算时间）
	private String uploadFileName;
	private File upload;
	private InternationalMerchant merchant = new InternationalMerchant();
	private InternationalTradeinfo trade = new InternationalTradeinfo();
	private InternationalChannels chann = new InternationalChannels();
	private InternationalMerchantChannels merchan = new InternationalMerchantChannels();
	private InternationalCardholdersInfo card = new InternationalCardholdersInfo();
	private List<InternationalTradeinfo> lostList = new ArrayList<InternationalTradeinfo>();
	private List<InternationalTradeinfo> echoList = new ArrayList<InternationalTradeinfo>();
	private List<InternationalTradeinfo> excptionList = new ArrayList<InternationalTradeinfo>();
	
	private String atime;
	private String btime;
	/**
	 * 上传文件
	 */
	public String uploadGoudui(){
		try{
				
			FileOutputStream fos = null ; 
			FileInputStream fis = null;
			String realPath = "D:\\Excel"; //this.getSavePath();从配置文件读取地址
			File target = null;
			target = new File(realPath, uploadFileName);
			FileUtils.copyFile(upload, target);
			fos = new FileOutputStream(target);
			fis = new FileInputStream(getUpload());
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0)
			{
				fos.write(buffer , 0 , len);
			}
			//文件路径
			String filePath = target.toString();
			//this.dddGoudui(filePath);
			if(uploadType.equals("1")){
				this.VIPGoudui(filePath);
				return "VIPGoudui";
			}else{
				this.dddGoudui(filePath);
				return "3DGoudui";
			}
			
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction = "上传文件错误!";
			return this.OPERATE_SUCCESS;
		}
		//文件后缀类型控制
	}
	/**
	 * 跳转到非3D勾兑页面
	 */
	public String toDDDGoudui(){
		try{
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction = "跳转到非3D勾兑页面错误";
			return this.OPERATE_SUCCESS;
		}
	}
	
	/**
	 * 跳转到VIP勾兑页面
	 */
	public String toVIPGoudui(){
		try{
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction = "跳转到VIP勾兑页面错误";
			return this.OPERATE_SUCCESS;
		}
	}
	/**
	 * VIP半自动勾兑
	 */
	public String VIPGoudui(String filePath){
		try{
			   InternationalTradeinfo t = null;
			  
			   String cardno = null;
			   String authorizeno = null;
			   Workbook book =null;
			   InputStream is = null;
			   is = new FileInputStream(filePath); //写入到FileInputStream
			   //得到文件
			   book = Workbook.getWorkbook(is);
			   // 获得第一个工作表对象
			   Sheet sheet = book.getSheet(0);
			   int rows = sheet.getRows();//行数
			   int columns = sheet.getColumns();//列数
			   for(int i = 0;i<rows;i++){
				   for (int j = 0; j<columns;j++){
					   // 得到第 j 列,第 i 行的单元格
					   Cell cell1 = sheet.getCell(j, i);
					   // 得到第 j 列,第 i 行的单元格的内容
					   String result = cell1.getContents().toString().trim();
					   if(j==0){
						   authorizeno = result;
					   }if(j==1){
						   cardno = result;
					   }if(j==3){
						   tradeAmount = result;
					   }if(j==5){
						   tradeTime = result;
					   }
					  
				   }
				   
				   InternationalTradeinfo tradeinfo = new InternationalTradeinfo();
				   hql = "FROM InternationalTradeinfo t WHERE t.VIPAuthorizationNo='"+authorizeno+"' AND t.id in " +
				   		"(SELECT c.tradeId FROM InternationalCardholdersInfo c WHERE c.cardNo='"+cardno+"')";
				   trade = (InternationalTradeinfo)commonService.uniqueResult(hql);
				   int num = 0;
				   Date tradeDate = null;
				   Double amount = null;
				   if(tradeTime!=null && !tradeTime.equals("")){
						String date = tradeTime+" 00:00:00.0";
						Timestamp sx = Timestamp.valueOf(date);
						long sg = sx.getTime();
						tradeDate  = new Date (sg);
				   }
				   if(tradeAmount!=null && !tradeAmount.equals("")){
					   tradeAmount =StringUtil.deleteAll(tradeAmount, ',');
					   amount = Double.valueOf(tradeAmount);
				   }
					
					//获取支付状态
					//String tradeState = StatusUtil.updateStatus(tradeinfo.getTradeState(), 1, isreuslt);
					//获取重复单
					if(trade==null){
						trade = new InternationalTradeinfo();
					}else if(trade!=null){
						isreuslt = trade.getTradeState().substring(4, 5);
						num = this.repeatTradeNo(trade.getOrderNo());
						tradeinfo.setOrderNo(trade.getOrderNo());
						t = (InternationalTradeinfo)commonService.load(InternationalTradeinfo.class, trade.getId());
						isGoudui = StatusUtil.getStatus(trade.getTradeState(), 5);
					}
					System.out.println("authorizeno----------"+authorizeno);
					tradeinfo.setTradeAmount(amount);
					tradeinfo.setTradeTime(tradeDate);
					tradeinfo.setVIPAuthorizationNo(authorizeno);
					if(num>1){
						//装载重复交易
						echoList.add(tradeinfo);
					}else if(isGoudui.equals("0")){
						if(trade.getOrderNo()==null){
							//装载掉单数据（银行有的交易数据，但我们后台没有）
							lostList.add(tradeinfo);
						}else if(!(trade.getRmbAmount()).equals(Double.valueOf(tradeAmount)) || !trade.getVIPAuthorizationNo().equals(authorizeno)){
							//装载异常单 （银行的交易数据与支付网关的数据不一致）
							excptionList.add(tradeinfo);
						}else if(isreuslt.equals("0")){
							//掉单勾兑银行是成功的，但我们这边是失败的，要把失败的单改成成功的。
							String tem=StatusUtil.updateStatus(trade.getTradeState(), 5, "1");
						    tem=(StatusUtil.updateStatus(tem, 1, "1"));
							t.setTradeState(tem);
							commonService.update(t);
						}else{
							//直接勾兑
							t.setTradeState(StatusUtil.updateStatus(trade.getTradeState(), 5, "1"));
							commonService.update(t);
						}
					}
					
			   }
			   return SUCCESS;
		   }catch(Exception e){
			   e.printStackTrace();
			   this.messageAction = "VIP勾兑失败!";
			   return this.OPERATE_SUCCESS;
		   }
				
	}
	/**
	 * 非3D卡半自动勾兑
	 */
	public String dddGoudui(String filePath){
		try{
			File file = new File(filePath);
			InputStreamReader is = new InputStreamReader( new FileInputStream(file));
			BufferedReader in = new BufferedReader(is);
			
			List list = new ArrayList();
			String value = null;
			String line = in.readLine();
			InternationalTradeinfo t = null;
			String state = null;
			//设置银行数据的列数
			int columns ; 
			StringTokenizer st = null;
			int rows = 0;
			//根据银行数据格式,分别选择列数
		
			
			   while(line!=null) {
				   InternationalTradeinfo tradeinfo = new InternationalTradeinfo();
					columns = 14 ;
					st = new StringTokenizer(line , ",");
					for (int i = 0; i < columns; i++) {
						String temp = st.nextToken();
						//array[i] = temp;
						//System.out.println("temp----------"+temp);
						if(i==3){
							orderno = temp.trim();
						}else if(i==9){
							//tradeAmount = temp.trim();
							sb = new StringBuffer(temp.trim());
							sb.delete(0,1);
							tradeAmount = sb.toString();
						}else if(i==1){
							tradeTime = temp.trim();
						}else if(i==11){
							/**
							 * 非3D卡成功状态为0，金额是人民币金额
							 */
							sb = new StringBuffer(temp.trim());
							sb.delete(1, sb.length()); 
							isreuslt = sb.toString();
							if(isreuslt.equals("0")){
								isreuslt = "1";
							}else{
								isreuslt = "0";
							}
						}
						
					}
					line = in.readLine();
					
					tradeAmount =StringUtil.deleteAll(tradeAmount, ',');
					int num = this.repeatTradeNo(orderno);
					
					String date = tradeTime;
					Timestamp sx = Timestamp.valueOf(date);
					long sg = sx.getTime();
					Date tradeDate  = new Date (sg);
					hql = "FROM InternationalTradeinfo t WHERE t.orderNo='"+orderno+"'";
					trade = (InternationalTradeinfo)commonService.uniqueResult(hql);
					if(trade==null){
						trade = new	InternationalTradeinfo();	
						
						value = "0000";
					}else{
						tradeinfo.setVIPAuthorizationNo(trade.getVIPAuthorizationNo());
						state = trade.getTradeState().substring(0, 1);
						t = (InternationalTradeinfo)commonService.load(InternationalTradeinfo.class, trade.getId());
						value = trade.getTradeState();
					}
					
					tradeinfo.setOrderNo(orderno);
					tradeinfo.setTradeAmount(Double.valueOf(tradeAmount));
					tradeinfo.setTradeTime(tradeDate);
					tradeinfo.setTradeState(StatusUtil.updateStatus(value, 1, isreuslt));
					
					
					//System.out.println("tradeAmountsss----------"+Double.valueOf(tradeAmount));
					//System.out.println("getRmbAmount()----------"+trade.getRmbAmount().toString());
					if(trade.getOrderNo()==null){
						//掉单情况
						lostList.add(tradeinfo);
					}else if(!(trade.getRmbAmount()).equals(Double.valueOf(tradeAmount))){
						//异常单情况
						excptionList.add(tradeinfo);
					}else if(num>1){
						//重复单情况
						echoList.add(tradeinfo);
					}else if((state.equals("3") && isreuslt.equals("1")) || (state.equals("5") && isreuslt.equals("1")) ){
						String tem=StatusUtil.updateStatus(trade.getTradeState(), 5, "1");
					    tem=(StatusUtil.updateStatus(tem, 1, "1"));
						t.setTradeState(tem);
						commonService.update(t);
					}else{
						t.setTradeState(StatusUtil.updateStatus(trade.getTradeState(), 5, "1"));
						commonService.update(t);
					}
			   }
			   return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction = "非3D卡勾兑失败";
			return this.OPERATE_ERROR;
		}
	}
	
	/**
	 * 3D重复单
	 */
	 public int repeatTradeNo(String rorderno){
		 int repeatTimes = 0;
		 if(rorderno!=null){
			repeatList.add(rorderno);
			
			//每次添加记录都和前面的数据比较,出现重复的定单,把次数记录下来
			for (int i = 0; i < repeatList.size(); i++) {
				String str = (String) repeatList.get(i);
				if(rorderno.equals(str)){
					repeatTimes++;
				}
			}
		 }
		return repeatTimes;
	 }
	/**
	 * 跳转到勾兑页面  (成功，未勾兑)
	 * @return
	 */
	public String toHeadGoudui(){
		try{
			if(info==null){
				info = new PageInfo();
			}
			
			hql = "FROM InternationalChannels";
			list = commonService.list(hql);
			sb = new StringBuffer("FROM InternationalTradeinfo trade, InternationalMerchant merchant, InternationalChannels chan, " +
					"InternationalMerchantChannels merchan, InternationalCardholdersInfo card WHERE trade.merchantId = merchant.id " +
					"AND trade.tradeChannel = merchan.id AND merchan.channelId = chan.id AND card.tradeId=trade.id" +
					" AND substr(trade.tradeState,1,1) in (1) AND substr(trade.tradeState,5,1)='0'");
			if(merchant.getMerno()!=null){
				sb.append(" AND merchant.merno="+merchant.getMerno()+"");
			}
			if(atime!=null && btime!=null ){
				sb.append(" AND trade.tradeTime between to_date('"+atime+"','yyyy-MM-dd hh24:mi:ss') and to_date('"+btime+"','yyyy-MM-dd hh24:mi:ss')");//增加时间
			}
			if(trade.getTradeState()!=null && !trade.getTradeState().equals("")){
				sb.append(" AND substr(trade.tradeState,1,1)='"+trade.getTradeState()+"'");
			}
			if(trade.getOrderNo()!=null && !trade.getOrderNo().equals("")){
				sb.append(" AND trade.orderNo = '"+trade.getOrderNo().trim()+"'");
			}
			if(trade.getVIPAuthorizationNo()!=null && !trade.getVIPAuthorizationNo().equals("")){
				sb.append(" AND trade.VIPAuthorizationNo= '"+trade.getVIPAuthorizationNo().trim()+"'");
			}
			if(trade.getVIPTerminalNo()!=null && !trade.getVIPTerminalNo().equals("")){
				sb.append(" AND trade.VIPTerminalNo='"+trade.getVIPTerminalNo().trim()+"'");
			}
			if(chann.getId()!=null){
				sb.append(" AND chan.id="+chann.getId()+"");
			}
			if(card.getCardNo()!=null && !card.getCardNo().equals("")){
				sb.append(" AND card.cardNo='"+card.getCardNo().trim()+"'");
			}
			sb.append(" order by trade.tradeTime asc ");
			
			hql = sb.toString();
			info.setPageSize(20);
			info = commonService.listQueryResultByHql(hql,info);
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction = "跳转到勾兑页面失败";
			return this.OPERATE_SUCCESS;
		}
	}
	//勾兑查询数据
	public String goudui2(){
		try{
			if(merchant.getMerno()!=null){
				String sql="select b.id from INTERNATIONAL_MERCHANT b where b.merno='"+merchant.getMerno()+"'";
				InternationalMerchant mer=(InternationalMerchant) commonService.uniqueResult("from InternationalMerchant where merno='"+merchant.getMerno()+"'");
				if(mer!=null){
					long merchantId=mer.getId();
					String hql1="select count(*) from international_tradeinfo t "
							+ " where t.tradeTime between to_date('"+atime+"','yyyy-MM-dd hh24:mi:ss') and to_date('"+btime+"','yyyy-MM-dd hh24:mi:ss') "
									+ "and t.merchantId='"+merchantId+"'"
							+"and substr(t.tradeState,1,1) in (0,1,5) AND substr(t.tradeState,5,1)='0'";
					hql=hql1;
				}else{
					this.flag = "3";//商户号错误
					return SUCCESS;
				}
			}else{
				String hql2="select count(*) from international_tradeinfo t"
						+ " where t.tradeTime between to_date('"+atime+"','yyyy-MM-dd hh24:mi:ss') and to_date('"+btime+"','yyyy-MM-dd hh24:mi:ss')"
						+"and substr(t.tradeState,1,1) in (0,1,5) AND substr(t.tradeState,5,1)='0'";
				hql=hql2;
			}
			List<Object> merchantlist1=commonService.getByList(hql);
			String count=merchantlist1.get(0).toString();
			this.flag =count;//查询结果返回
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.flag = "2";//系统出现错误
			return SUCCESS;
		}
	}
	//勾兑操作(针对某个时间段某个用户的勾兑)
	public String goudui1(){
		try{
			if(merchant.getMerno()!=null){//根据商户号和时间段进行操作
				//查询商户号所对应的商户id
				String sql="select b.id from INTERNATIONAL_MERCHANT b where b.merno='"+merchant.getMerno()+"'";
				InternationalMerchant mer=(InternationalMerchant) commonService.uniqueResult("from InternationalMerchant where merno='"+merchant.getMerno()+"'");
				if(mer!=null){
					long merchantId=mer.getId();
					String hql1="update international_tradeinfo t set"
							+ " t.tradestate='0'||substr(t.tradestate,2,3)||'1'||substr(t.tradestate,6, length(t.tradestate)-5)"
							+ " where t.tradeTime between to_date('"+atime+"','yyyy-MM-dd hh24:mi:ss') and to_date('"+btime+"','yyyy-MM-dd hh24:mi:ss') "
									+ "and t.merchantId='"+merchantId+"'"
									+"and substr(t.tradeState,1,1) in (0,1,5) AND substr(t.tradeState,5,1)='0'";
					hql=hql1;
				}else{
					this.flag = "3";//商户号错误
					return SUCCESS;
				}
			}
			else{//只根据时间段进行操作
				String hql2="update international_tradeinfo t set"
						+ " t.tradestate='0'||substr(t.tradestate,2,3)||'1'||substr(t.tradestate,6, length(t.tradestate)-5)"
						+ " where t.tradeTime between to_date('"+atime+"','yyyy-MM-dd hh24:mi:ss') and to_date('"+btime+"','yyyy-MM-dd hh24:mi:ss')"
						+"and substr(t.tradeState,1,1) in (0,1,5) AND substr(t.tradeState,5,1)='0'";
				hql=hql2;
			}
			commonService.deleteBySql(hql);
			this.flag = "1";//修改成功
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.flag = "2";//系统出现错误
			return SUCCESS;
		}
	}
	/**
	 * 勾兑操作
	 */
	public String goudui(){
		try{
			StringBuffer sb1 = new StringBuffer();//创建字符对象1
			sb = new StringBuffer();//创建字符对象 2
			for(int i=0; i<disposeId.length; i++){//循环选中的数据（每循环一次  字符对象2加ID）
				sb.append(disposeId[i]+",");
			}
			String value = sb.toString();//转化成字符value
			//System.out.println("value-----------"+value.subSequence(0, value.length()-1));
			sb1.append(value.subSequence(0, value.length()-1));//去逗号赋值发给字符对象1
			hql = "FROM InternationalTradeinfo t WHERE t.id in("+sb1.toString()+")";//拼接更新条件
			List<InternationalTradeinfo> list = commonService.list(hql);//根据条件查询出来的数据list
			//ti.getTradeState().substring(7,8).equals("1")
			for(InternationalTradeinfo trade : list){//循环这个list每一个数据是trade
				if(trade.getTradeState().substring(0,1).equals("5")){//如果交易状态组合字段第一位是5
					hql = "update international_tradeinfo t set " +//更新语句
					"t.tradestate='0'||substr(t.tradestate,2,3)||'1'||substr(t.tradestate,6, length(t.tradestate)-5)" +//组建更新数据语句
					" where t.id in("+sb1.toString()+")";//更新条件语句
				}else{
					hql = "update international_tradeinfo t set " +//更新语句
					"t.tradestate=substr(t.tradestate,1,4)||'1'||substr(t.tradestate,6, length(t.tradestate)-5)" +//组建更新数据语句
					" where t.id in("+sb1.toString()+")";//更新条件语句
				}
				commonService.deleteBySql(hql);
			}
			this.flag = "1";
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.flag = "2";
			return SUCCESS;
		}
	}
	/**
	 * @return the commonService
	 */
	public CommonService getCommonService() {
		return commonService;
	}
	/**
	 * @param commonService the commonService to set
	 */
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	/**
	 * @return the list
	 */
	public List getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(List list) {
		this.list = list;
	}

	/**
	 * @return the disposeId
	 */
	public Object[] getDisposeId() {
		return disposeId;
	}

	/**
	 * @param disposeId the disposeId to set
	 */
	public void setDisposeId(Object[] disposeId) {
		this.disposeId = disposeId;
	}
	/**
	 * @return the merchan
	 */
	public InternationalMerchantChannels getMerchan() {
		return merchan;
	}

	/**
	 * @param merchan the merchan to set
	 */
	public void setMerchan(InternationalMerchantChannels merchan) {
		this.merchan = merchan;
	}

	/**
	 * @return the merchant
	 */
	public InternationalMerchant getMerchant() {
		return merchant;
	}

	/**
	 * @param merchant the merchant to set
	 */
	public void setMerchant(InternationalMerchant merchant) {
		this.merchant = merchant;
	}

	/**
	 * @return the trade
	 */
	public InternationalTradeinfo getTrade() {
		return trade;
	}

	/**
	 * @param trade the trade to set
	 */
	public void setTrade(InternationalTradeinfo trade) {
		this.trade = trade;
	}

	/**
	 * @return the chann
	 */
	public InternationalChannels getChann() {
		return chann;
	}

	/**
	 * @param chann the chann to sett
	 */
	public void setChann(InternationalChannels chann) {
		this.chann = chann;
	}

	/**
	 * @return the info
	 */
	public PageInfo getInfo() {
		return info;
	}

	/**
	 * @param info the info to set
	 */
	public void setInfo(PageInfo info) {
		this.info = info;
	}

	/**
	 * @return the 
	 */
	public String getUploadFileName() {
		return uploadFileName;
	}

	/**
	 * @param uploadFileName the uploadFileName to set
	 */
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	/**
	 * @return the upload
	 */
	public File getUpload() {
		return upload;
	}

	/**
	 * @param upload the upload to set
	 */
	public void setUpload(File upload) {
		this.upload = upload;
	}

	/**
	 * @return the uploadType
	 */
	public String getUploadType() {
		return uploadType;
	}

	/**
	 * @param uploadType the uploadType to set
	 */
	public void setUploadType(String uploadType) {
		this.uploadType = uploadType;
	}

	/**
	 * @return the lostList
	 */
	public List<InternationalTradeinfo> getLostList() {
		return lostList;
	}

	/**
	 * @param lostList the lostList to set
	 */
	public void setLostList(List<InternationalTradeinfo> lostList) {
		this.lostList = lostList;
	}

	/**
	 * @return the echoList
	 */
	public List<InternationalTradeinfo> getEchoList() {
		return echoList;
	}

	/**
	 * @param echoList the echoList to set
	 */
	public void setEchoList(List<InternationalTradeinfo> echoList) {
		this.echoList = echoList;
	}

	/**
	 * @return the excptionList
	 */
	public List<InternationalTradeinfo> getExcptionList() {
		return excptionList;
	}

	/**
	 * @param excptionList the excptionList to set
	 */
	public void setExcptionList(List<InternationalTradeinfo> excptionList) {
		this.excptionList = excptionList;
	}
	public InternationalCardholdersInfo getCard() {
		return card;
	}
	public void setCard(InternationalCardholdersInfo card) {
		this.card = card;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getAtime() {
		return atime;
	}
	public void setAtime(String atime) {
		this.atime = atime;
	}
	public String getBtime() {
		return btime;
	}
	public void setBtime(String btime) {
		this.btime = btime;
	}
	
}
