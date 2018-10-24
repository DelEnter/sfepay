package com.ecpss.action.cardholders;

import java.io.OutputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ecpss.action.BaseAction;
import com.ecpss.model.channel.InternationalChannels;
import com.ecpss.model.log.SystemLog;
import com.ecpss.model.payment.InternationalCardholdersInfo;
import com.ecpss.model.payment.InternationalTradeinfo;
import com.ecpss.model.risk.InternationalBacklist;
import com.ecpss.model.risk.InternationalRiskItems;
import com.ecpss.service.iservice.ChannelService;
import com.ecpss.tools.TableExport;
import com.ecpss.tools.TableExportFactory;
import com.ecpss.util.AES;
import com.ecpss.util.DownloadUtils;
import com.ecpss.web.PageInfo;

public class CardholdersQueryAction extends BaseAction{
	
private PageInfo info = new PageInfo();
	
	private String orderNo;
	private Long merchantNo;
	private String cardnum;
	private String isresult;
	private String startDate;//时间
	private String endDate;
	private String email;
	private String six;
	private String four;
	private String nine;
	private String twelve;
	private Long minmaxmind;
	private Long maxmaxmind;
	private String ip;
	private String tradeUrl;
	private String remark;
	private String backCardNo;
	private String backEmail;
	private String backIp;
	private String msg;
	private String isProtest;
	private String istuikuan;
	private String customerName;
	private String phone;
	@Autowired
	@Qualifier("channelService")
	private ChannelService channelService;
	private List<InternationalChannels> channelList;
	private Long channelId;
	private String traderId;
	/**
	 * 下载使用判断
	 */
	private String isdownload;
	private String country;
	
	
	public String cardthree(){
		String sql="select card.*,trade.*,newweb.* from  International_CardholdersInfo card,International_Tradeinfo trade,"+     
                		"(select  ci.ip ip "+
                						"from International_CardholdersInfo ci,"+
                								"International_Tradeinfo ti "+             
                								"where ci.tradeId = ti.id "+
                								"and ti.tradeTime >="+
                								"to_date('2017-01-01 00:00:00','yyyy-MM-dd hh24:mi:ss')"+
                								"and ti.tradeTime <=to_date('2017-12-31 23:59:59','yyyy-MM-dd hh24:mi:ss')"+
                								"and substr(ti.tradestate, 1, 1) = '0' "+
                								"and ci.cardNo != 'd524b35dd5d10de482b1a1cfabae9b0811de716547a3169e5e7e4ff674209d27'"+
                								"group by ip having count(ci.ip) > 3) newweb "+
                								"where newweb.ip = card.ip and "+
                								"substr(trade.tradestate, 1, 1) = '0' and "+
                								"trade.tradeTime >="+
                								"to_date('2017-01-01 00:00:00','yyyy-MM-dd hh24:mi:ss')"+
                								"and trade.tradeTime <=to_date('2017-12-31 23:59:59','yyyy-MM-dd hh24:mi:ss') and "+               								
                								"trade.id = card.tradeId order by card.ip desc";
		List<Object[]> listing = this.commonService.getByList(sql.toString());	
		TableExport export = TableExportFactory.createExcelTableExport();
		export.addTitle(new String[]{"序号","支付结果","流水号","金额(外币)","金额(人民币)","产品信息","交易日期","收货国家","卡号","邮箱","IP","网站","持卡人姓名","电话",
				"备注"});
		export.setTableName("Trade");
		
		int i = 1;
		for (Object[] obj : listing) {
			  for (int q = 0; q < obj.length; q++) {
				
			  }	
				String cardno = AES.getCarNo(obj[4]+"");
				export.addRow(new Object[]{i,getStates().getStateNameEndown(obj[66]+"",1),obj[57],obj[63],
						obj[62],obj[16],obj[67],obj[19]
						,cardno,obj[9],obj[12],obj[68],obj[21],obj[22],obj[60]});	
				i++; 
		}

		OutputStream os = DownloadUtils.getResponseOutput("Trade.xls");
		export.export(os);
		DownloadUtils.closeResponseOutput();
		
		return SUCCESS;		
	}
	
	
	
	/**
	 * 持卡人信息显示
	 * @return
	 */
	public String cardholdersQuery(){
		channelList = channelService.getChannelList();
		HttpServletRequest request = ServletActionContext.getRequest();
		StringBuffer s = new StringBuffer("\n 角色为："+this.getUserBean().getRoleName()+"\n 登录用户名为："+this.getUserBean().getUserName()+"\n查询时间为:"+new Date()+
				"\n IP地址是："+this.getIpAddr(request)
				+ "\n查询条件:");
		StringBuffer sb = new StringBuffer();
		sb.append("select ti,m,ci,c from InternationalTradeinfo ti," +
						"InternationalMerchant m," +
						"InternationalCardholdersInfo ci," +
						"InternationalMerchantChannels mc," +
						"InternationalChannels c " +
				"where ti.merchantId=m.id " +     //交易表商户ID
				"and ci.tradeId=ti.id " +         //持卡人跟交易表
				"and ti.tradeChannel=mc.id " +    //交易表商户通道
				"and ci.cardNo != 'd524b35dd5d10de482b1a1cfabae9b0811de716547a3169e5e7e4ff674209d27'" +
				"and mc.channelId=c.id ");       //商户通道的通道
		if(merchantNo!=null){
			sb.append(" and m.merno="+merchantNo);
			s.append("\n 商户号:"+merchantNo);
		}
		if(StringUtils.isNotBlank(tradeUrl)){
			sb.append(" and ti.tradeUrl='"+tradeUrl+"'");
			s.append("\n tradeUrl:"+tradeUrl);
		}
		if(StringUtils.isNotBlank(orderNo)){
			sb.append(" and ti.orderNo like '%"+orderNo.trim()+"%'");
			s.append("\n 流水号:"+orderNo);
		}
		if(StringUtils.isNotBlank(cardnum)){
			sb.append(" and ci.cardNo='"+AES.setCarNo(cardnum.trim())+"'");
			s.append("\n 卡号:"+cardnum);
		}
		if(StringUtils.isNotBlank(six)){
			sb.append(" and ci.cardNo6='"+AES.setCarNo(six.trim())+"'");
			s.append("\n 卡号前6位:"+six);
		}
		if(StringUtils.isNotBlank(nine)){
			sb.append(" and ci.cardNo9='"+AES.setCarNo(nine.trim())+"'");
			s.append("\n 卡号前9位:"+nine);
		}
		if(StringUtils.isNotBlank(twelve)){
			sb.append(" and ci.cardNo12='"+AES.setCarNo(twelve.trim())+"'");
			s.append("\n 卡号前12位:"+twelve);
		}
		if(StringUtils.isNotBlank(four)){
			sb.append(" and ci.cardNo4='"+AES.setCarNo(four.trim())+"'");
			s.append("\n 卡号后4位:"+four);
		}
		if(StringUtils.isNotBlank(isresult)){   //支付情况
			sb.append(" and substr(ti.tradeState,1,1)='"+isresult+"'");
			s.append("\n 支付状态:"+isresult);
		}
		if(StringUtils.isNotBlank(startDate)){  //开始日期
			sb.append(" and ti.tradeTime>=to_date('"+startDate+"','yyyy-MM-dd hh24:mi:ss') ");
		}
		if(StringUtils.isNotBlank(endDate)){   //结束日期
			sb.append(" and ti.tradeTime<=to_date('"+endDate+"','yyyy-MM-dd hh24:mi:ss') ");
		}
		if(StringUtils.isNotBlank(email)){
			sb.append(" and lower(ci.email) like '%"+email.trim().toLowerCase()+"%'");
			s.append("\n Email:"+email);
		}
		if(StringUtils.isNotBlank(ip)){
			sb.append(" and ci.ip like'"+ip.trim()+"%'");
			s.append("\n ip:"+ip);
		}
		if(StringUtils.isNotBlank(customerName)){
			sb.append(" and (ci.firstName ||' '|| ci.lastName) like'%"+customerName.trim()+"%'");
			s.append("\n customerName:"+customerName);
		}
		if(StringUtils.isNotBlank(phone)){
			sb.append(" and ci.phone like'%"+phone.trim()+"%'");
			s.append("\n phone:"+phone);
		}
		if(StringUtils.isNotBlank(remark)){
			sb.append(" and ti.remark like'%"+remark.trim()+"%'");
			s.append("\n remark:"+remark);
		}
		if(StringUtils.isNotBlank(country)){	//国家
			sb.append(" and ci.country like'%"+country.trim()+"%'");
			s.append("\n country:"+country);
		}
		if(StringUtils.isNotBlank(isProtest)){   //拒付情况
			sb.append(" and substr(ti.tradeState,3,1)='"+isProtest+"'");
			s.append("\n isProtest:"+isProtest);
			}
		if(StringUtils.isNotBlank(istuikuan)){   //退款情况
			sb.append(" and substr(ti.tradeState,2,1)='"+istuikuan+"'");
			s.append("\n istuikuan:"+istuikuan);
			}
		if(channelId!=null){   //通道查询
			sb.append(" and c.id="+channelId);
			}
		if(minmaxmind!=null){
			sb.append(" and ci.maxmindValue>="+minmaxmind);
		}
		if(maxmaxmind!=null){
			sb.append(" and ci.maxmindValue<="+maxmaxmind);
		}
		sb.append(" order by ti.tradeTime desc");
		
		
		if(StringUtils.isNotBlank(isdownload)){
			List<Object[]> objList  = commonService.list(sb.toString());
			this.downloadTradeQuery(objList);
			return null;
		}else{
			if(StringUtils.isNotBlank(this.query) && this.query.equals("0")){
				query = "1";
			}else{
				info=commonService.listQueryResultByHql(sb.toString(), info);
				SystemLog sl=new SystemLog();
				//sl.setMerno("");
				sl.setOperTime(new Date());
				sl.setRemarks(s.toString());
				sl.setRescReow("查询持卡人信息");
				sl.setOperType("3");
				sl.setUserName(this.getUserBean().getUserName());
				this.commonService.save(sl);
			}
		}
		
		
		
		return SUCCESS;
	}
	
	
	/**
	 * 下载商户交易查询记录
	 * @return
	 */
	public void downloadTradeQuery(List<Object[]> oArray) {
		TableExport export = TableExportFactory.createExcelTableExport();
		export.addTitle(new String[]{"序号","支付结果","流水号","金额(外币)","金额(人民币)","授权号","交易日期","终端号","卡号","邮箱","IP",
				"备注"});
		export.setTableName("Trade");
		InternationalTradeinfo t = new InternationalTradeinfo();
		InternationalCardholdersInfo ci = new InternationalCardholdersInfo();
		int i=1;
		for(Object[] obj:oArray){
			t = (InternationalTradeinfo) obj[0];
			ci = (InternationalCardholdersInfo) obj[2];
			String ip="";
			String email="";
			Long currency=1L;
			if(StringUtils.isNotBlank(ci.getIp())){
				ip=ci.getIp();
			}
			if(StringUtils.isNotBlank(ci.getEmail())){
				email=ci.getEmail();
			}
			if(t.getMoneyType()!=null){
				currency=t.getMoneyType();
			}
			String cardNo=AES.getCarNo(ci.getCardNo());
			/*if(StringUtils.isNotBlank(cardNo)&&cardNo.length()>14){
				cardNo=cardNo.substring(0,6)+"******"+cardNo.substring(12);
			}*/
			export.addRow(new Object[]{i,getStates().getStateNameEndown(t.getTradeState(),1),t.getOrderNo(),t.getTradeAmount(),
					t.getRmbAmount(),t.getVIPAuthorizationNo(),t.getTradeTime(),t.getVIPTerminalNo()
					,cardNo,email,ip,t.getRemark()});	
			i++;
		}
		OutputStream os = DownloadUtils.getResponseOutput("Trade.xls");
		export.export(os);
		DownloadUtils.closeResponseOutput();
	}
	/*public void downloadTradeQuery(List<Object[]> oArray) {
		TableExport export = TableExportFactory.createExcelTableExport();
		export.addTitle(new String[]{"序号","支付结果","国家","姓名","电话","邮箱","IP","网站"
				});
		export.setTableName("Trade");
		InternationalTradeinfo t = new InternationalTradeinfo();
		InternationalCardholdersInfo ci = new InternationalCardholdersInfo();
		int i=1;
		for(Object[] obj:oArray){
			t = (InternationalTradeinfo) obj[0];
			ci = (InternationalCardholdersInfo) obj[2];
			String ip="";
			String email="";
			Long currency=1L;
			if(StringUtils.isNotBlank(ci.getIp())){
				ip=ci.getIp();
			}
			if(StringUtils.isNotBlank(ci.getEmail())){
				email=ci.getEmail();
			}
			if(t.getMoneyType()!=null){
				currency=t.getMoneyType();
			}
			String cardNo=AES.getCarNo(ci.getCardNo());
			if(StringUtils.isNotBlank(cardNo)&&cardNo.length()>14){
				cardNo=cardNo.substring(0,6)+"******"+cardNo.substring(12);
			}
			export.addRow(new Object[]{i,getStates().getStateNameEndown(t.getTradeState(),1),ci.getCountry(),ci.getShippingFullName(),ci.getShippingPhone(),email,ip,t.getTradeUrl()});	
			i++;
		}
		OutputStream os = DownloadUtils.getResponseOutput("Trade.xls");
		export.export(os);
		DownloadUtils.closeResponseOutput();
	}*/
	//添加黑名单
	public String addBackList(){
		InternationalBacklist bl=new InternationalBacklist();
			List<InternationalBacklist> list = this.commonService.list("from InternationalBacklist t where t.cardno='"+ backCardNo + "' and t.email='"+backEmail+"' and t.ip='"+backIp+"'");
			if(list!=null&&list.size()>0){
				msg="添加项已存在！";
				return cardholdersQuery();
			}else{
				bl.setCardno(backCardNo);
				bl.setEmail(backEmail);
				bl.setIp(backIp);
				commonService.save(bl);
			}
		msg="添加成功！";
		return cardholdersQuery();
	}
	//添加已中风险订单号标记
	public String addorDelSign(){
		if(StringUtils.isNotBlank(traderId)){
			InternationalTradeinfo trade=(InternationalTradeinfo) commonService.load(InternationalTradeinfo.class, new Long(traderId));
			if(trade.getOrderNo().indexOf("*",1)>0){
				trade.setOrderNo(trade.getOrderNo().substring(0, trade.getOrderNo().length()-1));
			}else{
			trade.setOrderNo(trade.getOrderNo()+"*");
			}
			commonService.update(trade);
		}
		msg="添加成功！";
		return cardholdersQuery();
	}
	
	public  String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		// System.out.println("ip----------------"+ip);
		return ip;

	}

	public PageInfo getInfo() {
		return info;
	}


	public void setInfo(PageInfo info) {
		this.info = info;
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


	public String getCardnum() {
		return cardnum;
	}


	public void setCardnum(String cardnum) {
		this.cardnum = cardnum;
	}


	public String getIsresult() {
		return isresult;
	}


	public void setIsresult(String isresult) {
		this.isresult = isresult;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSix() {
		return six;
	}


	public void setSix(String six) {
		this.six = six;
	}


	public String getFour() {
		return four;
	}


	public void setFour(String four) {
		this.four = four;
	}


	public Long getMinmaxmind() {
		return minmaxmind;
	}


	public void setMinmaxmind(Long minmaxmind) {
		this.minmaxmind = minmaxmind;
	}


	public Long getMaxmaxmind() {
		return maxmaxmind;
	}


	public void setMaxmaxmind(Long maxmaxmind) {
		this.maxmaxmind = maxmaxmind;
	}


	public String getIsdownload() {
		return isdownload;
	}


	public void setIsdownload(String isdownload) {
		this.isdownload = isdownload;
	}


	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}


	public String getTradeUrl() {
		return tradeUrl;
	}


	public void setTradeUrl(String tradeUrl) {
		this.tradeUrl = tradeUrl;
	}


	public String getNine() {
		return nine;
	}


	public void setNine(String nine) {
		this.nine = nine;
	}


	public String getTwelve() {
		return twelve;
	}


	public void setTwelve(String twelve) {
		this.twelve = twelve;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getBackCardNo() {
		return backCardNo;
	}


	public void setBackCardNo(String backCardNo) {
		this.backCardNo = backCardNo;
	}


	public String getBackEmail() {
		return backEmail;
	}


	public void setBackEmail(String backEmail) {
		this.backEmail = backEmail;
	}


	public String getBackIp() {
		return backIp;
	}


	public void setBackIp(String backIp) {
		this.backIp = backIp;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public String getIsProtest() {
		return isProtest;
	}


	public void setIsProtest(String isProtest) {
		this.isProtest = isProtest;
	}


	public String getIstuikuan() {
		return istuikuan;
	}


	public void setIstuikuan(String istuikuan) {
		this.istuikuan = istuikuan;
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


	public Long getChannelId() {
		return channelId;
	}


	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}


	public String getTraderId() {
		return traderId;
	}


	public void setTraderId(String traderId) {
		this.traderId = traderId;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}

}
