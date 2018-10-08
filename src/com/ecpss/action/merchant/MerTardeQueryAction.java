package com.ecpss.action.merchant;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ecpss.action.BaseAction;
import com.ecpss.action.pay.TradeManager;
import com.ecpss.model.payment.InternationalCardholdersInfo;
import com.ecpss.model.payment.InternationalTradeinfo;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.tools.TableExport;
import com.ecpss.tools.TableExportFactory;
import com.ecpss.util.AES;
import com.ecpss.util.DownloadUtils;
import com.ecpss.web.PageInfo;

public class MerTardeQueryAction extends BaseAction {
	@Autowired
	@Qualifier("tradeManager")
	private TradeManager tradeManager;
	/**
	 * 分页标签使用对象
	 */
	private PageInfo info = new PageInfo();
	/**
	 * 获取商户交易总金额统计list
	 */
	private List amountStatistic;
	/**
	 * 下载使用判断
	 */
	private String isdownload;
	/**
	 * 显示交易详情使用的id
	 */
	private Long tradeId;
	private String showDcc;

	// ********交易查询条件******/
	private String startDate;
	private String endDate;
	private String orderNo;
	private String merchantOrderNo;
	private String isresult;
	private String istuikuan;
	private String isdongjie;
	private String isjufu;
	private String ishuakuan;
	private String isgoudui;
	private String tradeUrl;
	private String ispiccher;
	private String istrackno;
	private Double minamount;
	private Double maxamount;
	private String tradeEmail;

	/**
	 * 统计金额
	 */
	private List successfulAmount;
	private List failedAmount;
	private List totalAmount;
	/**
	 * 显示到页面的交易详情
	 */
	private Object[] tradeDetail;
	
	
	private String cardnum;
	
	/**
	 * 商户后台交易查询
	 * 
	 * @return
	 */
	// public String merTradeQuery() throws Exception{
	// // if(StringUtils.isBlank(startDate)){
	// // Calendar calendar = Calendar.getInstance();
	// // calendar.add(Calendar.DATE, -7); //得到前一周
	// // startDate = DateUtil.formatDateTime(calendar.getTime());
	// // }
	//		
	// StringBuffer sb = new StringBuffer();
	// String select = "select
	// ti,m.merno,ci.email,c.channelName,ci.address,ci.shippingAddress,ci ";
	// sb.append("from InternationalTradeinfo ti," +
	// "InternationalMerchant m," +
	// "InternationalCardholdersInfo ci," +
	// "InternationalMerchantChannels mc," +
	// "InternationalChannels c " +
	// "where ti.merchantId=m.id " + //交易表商户ID
	// "and ci.tradeId=ti.id " + //持卡人跟交易表
	// "and ti.tradeChannel=mc.id " + //交易表商户通道
	// "and mc.channelId=c.id " + //商户通道的通道
	// "and m.id="+getMerchantBean().getMerchantId()+" "+ //登陆商户下的交易查询
	// "and substr(ti.tradeState,1,1)!=3");
	// if(StringUtils.isNotBlank(startDate)){
	// sb.append(" and ti.tradeTime>=to_date('"+startDate+"','yyyy-MM-dd
	// hh24:mi:ss') ");
	// }
	// if(StringUtils.isNotBlank(endDate)){
	// sb.append(" and ti.tradeTime<=to_date('"+endDate+"
	// 23:59:59"+"','yyyy-MM-dd hh24:mi:ss') ");
	// }
	// if(StringUtils.isNotBlank(orderNo)){
	// sb.append(" and ti.orderNo like '"+orderNo+"%'");
	// }
	// if(minamount!=null){
	// sb.append(" and ti.tradeAmount>="+minamount+" ");
	// }
	// if(maxamount!=null){
	// sb.append(" and ti.tradeAmount<="+maxamount+" ");
	// }
	// if(StringUtils.isNotBlank(merchantOrderNo)){
	// sb.append(" and ti.merchantOrderNo like '"+merchantOrderNo+"%'");
	// }
	// if(StringUtils.isNotBlank(isgoudui)){ //勾兑情况
	// sb.append(" and substr(ti.tradeState,5,1)='"+isgoudui+"'");
	// }
	// if(StringUtils.isNotBlank(isdongjie)){ //冻结情况
	// sb.append(" and substr(ti.tradeState,4,1)='"+isdongjie+"'");
	// }
	// if(StringUtils.isNotBlank(istuikuan)){ //退款情况
	// sb.append(" and substr(ti.tradeState,2,1)='"+istuikuan+"'");
	// }
	// if(StringUtils.isNotBlank(ishuakuan)){ //划款情况
	// sb.append(" and substr(ti.tradeState,8,1)='"+ishuakuan+"'");
	// }
	// if(StringUtils.isNotBlank(isjufu)){ //拒付情况
	// sb.append(" and substr(ti.tradeState,3,1)='"+isjufu+"'");
	// }
	// if(StringUtils.isNotBlank(isresult)){ //支付情况
	// sb.append(" and substr(ti.tradeState,1,1)='"+isresult+"'");
	// }
	// if(StringUtils.isNotBlank(ispiccher)){
	// if(ispiccher.equals("0")){
	// sb.append(" and ti.isPicture is null ");
	// }else{
	// sb.append(" and ti.isPicture is not null ");
	// }
	// }
	// if(StringUtils.isNotBlank(istrackno)){
	// if(istrackno.equals("0")){
	// sb.append(" and ti.isTrackNo is null ");
	// }else{
	// sb.append(" and ti.isTrackNo is not null ");
	// }
	// }
	// if(StringUtils.isNotBlank(tradeUrl)){
	// sb.append(" and ti.tradeUrl like '%"+tradeUrl.trim()+"%' ");
	// }
	// //计算金额
	// String totalsql = "select sum(ti.tradeAmount),ti.moneyType
	// "+sb.toString()+" group by ti.moneyType ";
	// String successfulsql = "select sum(ti.tradeAmount),ti.moneyType
	// "+sb.toString()+" and substr(ti.tradeState,1,1)='1' group by
	// ti.moneyType";
	// String failedsql = "select sum(ti.tradeAmount),ti.moneyType
	// "+sb.toString()+" and substr(ti.tradeState,1,1)='0' group by
	// ti.moneyType";
	//		
	// totalAmount= commonService.list(totalsql);
	// successfulAmount= commonService.list(successfulsql);
	// failedAmount= commonService.list(failedsql);
	//		
	//		
	// String amount = "select
	// substr(ti.tradeState,1,1),sum(ti.tradeAmount),ti.moneyType ";
	// String groupby = " group by substr(ti.tradeState,1,1),ti.moneyType";
	// if(StringUtils.isNotBlank(isdownload)){
	// List<Object[]> objList = commonService.list(select+sb.toString()+" order
	// by ti.tradeTime desc ");
	// this.downloadTradeQuery(objList);
	// return null;
	// }else{
	// //amountStatistic =
	// this.commonService.list(amount+sb.toString()+groupby);
	// info=commonService.listQueryResultByHql(select+sb.toString()+" order by
	// ti.tradeTime desc ", info);
	// return SUCCESS;
	// }
	// }
	//商户后台交易查询
	public String merTradeQuery() throws Exception {

		StringBuffer sb = new StringBuffer();
		String totleCount = "select count(*)";
		// String selectquery="select ti,m,ci,c " ;
		String selectquery = "SELECT  * FROM (SELECT A.*, ROWNUM RN FROM "
				+ "(select ti.orderNo," + // 1
				"ti.merchantOrderNo," + // 2
				"m.merno," + // 3
				"to_char(ti.tradeTime,'yy-mm-dd hh24:mi:ss')," + // 4
				"ti.tradeAmount," + // 5
				"ti.moneyType," + // 6
				"ti.rmbAmount," + // 7
				"c.channelName," + // 8
				"ti.tradeState," + // 9
				"ti.backCount," + // 10
				"ti.isPicture," + // 11
				"ti.isTrackNo," + // 12
				"ti.remark," + // 13
				"ti.id," + // 14
				"ci.address," + // 15
				"ci.shippingAddress, " + // 16
				"ti.tradeUrl,ci.cardNo,ci.email,ci.ip"; // 17
		sb
				.append(" from International_Tradeinfo ti left join "
						+ "International_Merchant  m on ti.merchantId=m.id left join "
						+ "International_CardholdersInfo ci on  ci.tradeId=ti.id left join "
						+ "International_MerchantChannels mc on ti.tradeChannel=mc.id left join "
						+ "International_Channels c on mc.channelId=c.id "
						+ "where 1=1 and substr(ti.tradeState,1,1)!=3 and ti.remark not like '%1093%' and ti.remark not like '%1100%' "); // 商户通道的通道
		sb.append(" and  m.id='" + getMerchantBean().getMerchantId() + "' ");
		if (StringUtils.isNotBlank(startDate)) {
			sb.append(" and ti.tradeTime>=to_date('" + startDate
					+ " 00:00:00','yyyy-MM-dd hh24:mi:ss') ");
		}
		if (StringUtils.isNotBlank(endDate)) {
			sb.append(" and ti.tradeTime<=to_date('" + endDate + " 23:59:59"
					+ "','yyyy-MM-dd hh24:mi:ss') ");
		}
		if (StringUtils.isNotBlank(orderNo)) {
			sb.append(" and ti.orderNo like '" + orderNo + "%'");
		}
		if (minamount != null) {
			sb.append(" and ti.tradeAmount>=" + minamount + " ");
		}
		if (maxamount != null) {
			sb.append(" and ti.tradeAmount<=" + maxamount + " ");
		}
		if (StringUtils.isNotBlank(merchantOrderNo)) {
			sb
					.append(" and ti.merchantOrderNo like '" + merchantOrderNo
							+ "%'");
		}
		if (StringUtils.isNotBlank(isgoudui)) { // 勾兑情况
			sb.append(" and substr(ti.tradeState,5,1)='" + isgoudui + "'");
		}
		if (StringUtils.isNotBlank(isdongjie)) { // 冻结情况
			sb.append(" and substr(ti.tradeState,4,1)='" + isdongjie + "'");
		}
		if (StringUtils.isNotBlank(istuikuan)) { // 退款情况
			sb.append(" and substr(ti.tradeState,2,1)='" + istuikuan + "'");
		}
		if (StringUtils.isNotBlank(ishuakuan)) { // 划款情况
			sb.append(" and substr(ti.tradeState,8,1)='" + ishuakuan + "'");
		}
		if (StringUtils.isNotBlank(isjufu)) { // 拒付情况
			sb.append(" and substr(ti.tradeState,3,1)='" + isjufu + "'");
		}
		if (StringUtils.isNotBlank(isresult)) { // 支付情况
			sb.append(" and substr(ti.tradeState,1,1)='" + isresult + "'");
		}
		if (StringUtils.isNotBlank(ispiccher)) {
			if (ispiccher.equals("0")) {
				sb.append(" and ti.isPicture is null ");
			} else {
				sb.append(" and ti.isPicture is not null ");
			}
		}
		if (StringUtils.isNotBlank(istrackno)) {
			if (istrackno.equals("0")) {
				sb.append(" and ti.isTrackNo is null ");
			} else {
				sb.append(" and ti.isTrackNo is not null ");
			}
		}
		if (StringUtils.isNotBlank(tradeUrl)) {
			sb.append(" and ti.tradeUrl like '%" + tradeUrl.trim() + "%' ");
		}
		if (StringUtils.isNotBlank(tradeEmail)) {
			sb.append(" and ci.email like '%" + tradeEmail.trim() + "%' ");
		}
		sb.append(" order by ti.tradeTime desc");
		// sb.append(") A WHERE ROWNUM <= "+(startResult+1)*maxResult+")WHERE RN
		// >= " +(startResult)*maxResult+"");
		String listinfo = ")  A WHERE ROWNUM <= " + (info.getCurPage())
				* info.getPageSize() + ")WHERE RN > " + (info.getCurPage() - 1)
				* info.getPageSize() + "";
		String orderby = " order by ti.tradeTime desc";
		StringBuffer sb2 = new StringBuffer();

		sb2.append("from InternationalTradeinfo ti,"
				+ "InternationalMerchant m,"
				+ "InternationalCardholdersInfo ci,"
				+ "InternationalMerchantChannels mc,"
				+ "InternationalChannels c "
				+ "where m.id=ti.merchantId and m.id= '"
				+ getMerchantBean().getMerchantId() + // 交易表商户ID
				"' and ci.tradeId=ti.id " + // 持卡人跟交易表
				"and ti.tradeChannel=mc.id " + // 交易表商户通道
				"and mc.channelId=c.id and substr(ti.tradeState,1,1)!='3' and ti.remark not like '%1093%' and ti.remark not like '%1100%' "); // 商户通道的通道
		if (StringUtils.isNotBlank(startDate)) {
			sb2.append(" and ti.tradeTime>=to_date('" + startDate + " 00:00:00"
					+ "','yyyy-MM-dd hh24:mi:ss') ");
		}
		if (StringUtils.isNotBlank(endDate)) {
			sb2.append(" and ti.tradeTime<=to_date('" + endDate + " 23:59:59"
					+ "','yyyy-MM-dd hh24:mi:ss') ");
		}
		if (StringUtils.isNotBlank(orderNo)) {
			sb2.append(" and ti.orderNo like '" + orderNo + "%'");
		}
		if (minamount != null) {
			sb2.append(" and ti.tradeAmount>=" + minamount + " ");
		}
		if (maxamount != null) {
			sb2.append(" and ti.tradeAmount<=" + maxamount + " ");
		}
		if (StringUtils.isNotBlank(merchantOrderNo)) {
			sb2.append(" and ti.merchantOrderNo like '" + merchantOrderNo
					+ "%'");
		}
		if (StringUtils.isNotBlank(isgoudui)) { // 勾兑情况
			sb2.append(" and substr(ti.tradeState,5,1)='" + isgoudui + "'");
		}
		if (StringUtils.isNotBlank(isdongjie)) { // 冻结情况
			sb2.append(" and substr(ti.tradeState,4,1)='" + isdongjie + "'");
		}
		if (StringUtils.isNotBlank(istuikuan)) { // 退款情况
			sb2.append(" and substr(ti.tradeState,2,1)='" + istuikuan + "'");
		}
		if (StringUtils.isNotBlank(ishuakuan)) { // 划款情况
			sb2.append(" and substr(ti.tradeState,8,1)='" + ishuakuan + "'");
		}
		if (StringUtils.isNotBlank(isjufu)) { // 拒付情况
			sb2.append(" and substr(ti.tradeState,3,1)='" + isjufu + "'");
		}
		if (StringUtils.isNotBlank(isresult)) { // 支付情况
			sb2.append(" and substr(ti.tradeState,1,1)='" + isresult + "'");
		}
		if (StringUtils.isNotBlank(ispiccher)) {
			if (ispiccher.equals("0")) {
				sb2.append(" and ti.isPicture is null ");
			} else {
				sb2.append(" and ti.isPicture is not null ");
			}
		}
		if (StringUtils.isNotBlank(istrackno)) {
			if (istrackno.equals("0")) {
				sb2.append(" and ti.isTrackNo is null ");
			} else {
				sb2.append(" and ti.isTrackNo is not null ");
			}
		}
		if (StringUtils.isNotBlank(tradeUrl)) {
			sb2.append(" and ti.tradeUrl like '%" + tradeUrl.trim() + "%' ");
		}
		if (StringUtils.isNotBlank(tradeEmail)) {
			sb2.append(" and ci.email like '%" + tradeEmail.trim() + "%' ");
		}
		if (StringUtils.isNotBlank(isdownload)) {
			List<Object[]> objList = commonService.list(sb2.toString()
					+ " order by ti.tradeTime desc ");
			this.downloadTradeQuery(objList);
			return null;
		} else {
			if (StringUtils.isNotBlank(this.query) && this.query.equals("0")) {
				query = "1";
			} else {
				// 统计总计金额
				String totalsql = "select sum(ti.tradeAmount),ti.moneyType "
						+ sb2.toString() + " group by ti.moneyType ";
				String successfulsql = "select sum(ti.tradeAmount),ti.moneyType "
						+ sb2.toString()
						+ " and substr(ti.tradeState,1,1)='1' group by ti.moneyType";
				String failedsql = "select sum(ti.tradeAmount),ti.moneyType "
						+ sb2.toString()
						+ " and substr(ti.tradeState,1,1)='0' group by ti.moneyType";
				totalAmount = commonService.list(totalsql);
				successfulAmount = commonService.list(successfulsql);
				failedAmount = commonService.list(failedsql);
				List<Object[]> list = this.commonService.getByList(selectquery
						+ sb.toString() + listinfo);
				/*System.out.println(selectquery
						+ sb.toString() + listinfo);*/
				info.setTotalCount(this.tradeManager.intBySql(totleCount
						+ sb.toString()));
				if(list.size()>0){
				for(Object[] obj:list){
					AES aes=new AES();
					String cardNo=aes.getCarNo(obj[17].toString());
					if ((cardNo.startsWith("30")
								|| cardNo.startsWith("35"))&& cardNo.length() == 16) {
						obj[17] = "JCB";
					}
					else if (cardNo.startsWith("4") && cardNo.length() == 16) {
							obj[17] = "VISA";
					}
					else if (cardNo.startsWith("5") && cardNo.length() == 16) {
							obj[17] = "Master";
					}
					else if ((cardNo.startsWith("34") || cardNo
								.startsWith("37"))&&cardNo.length() == 15) {
							obj[17] = "AMEX";
					}
					else if ((cardNo.startsWith("36")
								|| cardNo.startsWith("300")
								|| cardNo.startsWith("305") || cardNo
								.startsWith("38"))
								&& cardNo.length() == 14) {
							obj[17] = "Dinners";
						}else{
							obj[17] = "Dinners";
						}
					}
				}
				info.setResult(list);
			}
			return SUCCESS;
		}

	}

	/**
	 * 下载商户交易查询记录
	 * 
	 * @return
	 */
	public void downloadTradeQuery(List<Object[]> oArray) {
		TableExport export = TableExportFactory.createExcelTableExport();
		
		
		export.addTitle(new String[] { "Index", "Order No.",
				"Merchant Order No.", "Merchant No.", "Trade Time", "Amount",
				"Currency", "Payment Result", "Freeze", "Refund", "ChargeBack",
				"Settlement", "Check", "Tracking No.", "Trade website",
				"Full Name", "Country", "City", "State", "Address", "zipcode",
				"phone", "email", "Remark", "Item Information","DCC Amount","DCC Currency" });
		export.setTableName("Trade");
		InternationalTradeinfo t = new InternationalTradeinfo();
		InternationalMerchant m = new InternationalMerchant();
		InternationalCardholdersInfo ci = new InternationalCardholdersInfo();
		int i = 1;
		for (Object[] obj : oArray) {
			t = (InternationalTradeinfo) obj[0];
			m = (InternationalMerchant) obj[1];
			ci = (InternationalCardholdersInfo) obj[2];
			String merno = t.getOrderNo().substring(0, 4);
			if (m.getStatutes().subSequence(7, 8).equals("1") && t.getDCCTradeAmount() != null) {
					export.addRow(new Object[] {
							i,
							t.getOrderNo(),
							t.getMerchantOrderNo(),
							merno,
							t.getTradeTime(),
							t.getTradeAmount(),
							getStates().getCurrencyTypeByNo(
									t.getMoneyType().intValue()),
							getStates().getStateNameEndown(t.getTradeState(), 1),
							getStates().getStateNameEndown(t.getTradeState(), 4),
							getStates().getStateNameEndown(t.getTradeState(), 2),
							getStates().getStateNameEndown(t.getTradeState(), 3),
							getStates().getStateNameEndown(t.getTradeState(), 8),
							getStates().getStateNameEndown(t.getTradeState(), 5),
							t.getIsTrackNo(), t.getTradeUrl(),
							ci.getShippingFullName(), ci.getShippingCountry(),
							ci.getShippingCity(), ci.getShippingState(),
							ci.getShippingAddress(), ci.getShippingZip(),
							ci.getShippingPhone(),ci.getShippingEmail(), t.getRemark(),
							ci.getProductInfo(),t.getDCCTradeAmount()/100,this.getCurrency(t.getDCCTradeCurrency()) });
			}else{
				export.addRow(new Object[] {
						i,
						t.getOrderNo(),
						t.getMerchantOrderNo(),
						merno,
						t.getTradeTime(),
						t.getTradeAmount(),
						getStates().getCurrencyTypeByNo(
								t.getMoneyType().intValue()),
						getStates().getStateNameEndown(t.getTradeState(), 1),
						getStates().getStateNameEndown(t.getTradeState(), 4),
						getStates().getStateNameEndown(t.getTradeState(), 2),
						getStates().getStateNameEndown(t.getTradeState(), 3),
						getStates().getStateNameEndown(t.getTradeState(), 8),
						getStates().getStateNameEndown(t.getTradeState(), 5),
						t.getIsTrackNo(), t.getTradeUrl(),
						ci.getShippingFullName(), ci.getShippingCountry(),
						ci.getShippingCity(), ci.getShippingState(),
						ci.getShippingAddress(), ci.getShippingZip(),
						ci.getShippingPhone(),ci.getShippingEmail(), t.getRemark(),
						ci.getProductInfo() });
			}
			
			i++;
		}
		OutputStream os = DownloadUtils.getResponseOutput("Trade.xls");
		export.export(os);
		DownloadUtils.closeResponseOutput();
	}

	/**
	 * 查看交易查询详情信息
	 * 
	 * @return
	 */
	public String viewMerTradeDetail() throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select ti,m.merno,c.channelName,ci,m ");
		sb.append("from InternationalTradeinfo ti,"
				+ "InternationalMerchant m,"
				+ "InternationalCardholdersInfo ci,"
				+ "InternationalMerchantChannels mc,"
				+ "InternationalChannels c " + "where ti.merchantId=m.id " + // 交易表商户ID
				"and ci.tradeId=ti.id " + // 持卡人跟交易表
				"and ti.tradeChannel=mc.id " + // 交易表商户通道
				"and mc.channelId=c.id " + // 商户通道的通道
				"and m.id='" + getMerchantBean().getMerchantId() + "' " + // 登陆商户下的交易查询
				"and ti.id=" + tradeId);
		tradeDetail = (Object[]) this.commonService.uniqueResult(sb.toString());
		InternationalMerchant mc = (InternationalMerchant) tradeDetail[4];
		if (mc.getStatutes().subSequence(7, 8).equals("1")) {
			InternationalTradeinfo inf = (InternationalTradeinfo) tradeDetail[0];
			if (inf.getDCCTradeAmount() != null) {
				this.showDcc = "DCCAmount:  " + inf.getDCCTradeAmount() / 100
						+ "  "
						+ this.getCurrency(inf.getDCCTradeCurrency().trim());
			}
		}
		InternationalCardholdersInfo cardHInfo=(InternationalCardholdersInfo) tradeDetail[3];
		//用卡号字段代替卡类型
		AES aes=new AES();
		String cardNo=aes.getCarNo(cardHInfo.getCardNo());
		cardnum=cardNo.substring(0,6)+"******"+cardNo.substring(12);
		if ((cardNo.startsWith("30")
					|| cardNo.startsWith("35"))&& cardNo.length() == 16) {
			cardHInfo.setCardNo("JCB");
		}
		else if (cardNo.startsWith("4") && cardNo.length() == 16) {
				cardHInfo.setCardNo("VISA");
		}
		else if (cardNo.startsWith("5") && cardNo.length() == 16) {
				cardHInfo.setCardNo("Master");
		}
		else if ((cardNo.startsWith("34") || cardNo
					.startsWith("37"))&&cardNo.length() == 15) {
				cardHInfo.setCardNo("AMEX");
		}
		else if ((cardNo.startsWith("36")
					|| cardNo.startsWith("300")
					|| cardNo.startsWith("305") || cardNo
					.startsWith("38"))
					&& cardNo.length() == 14) {
				cardHInfo.setCardNo("Dinners");
			}else{
				cardHInfo.setCardNo("Dinners");
			}
			tradeDetail[3]=cardHInfo;
		return SUCCESS;
	}

	/**
	 * 商户后台交易详情查询
	 * 
	 * @return
	 */
	public String merTradeQueryList() throws Exception {
		StringBuffer sb = new StringBuffer();
		String totleCount = "select count(*)";
		// String selectquery="select ti,m,ci,c " ;
		String selectquery = "SELECT  * FROM (SELECT A.*, ROWNUM RN FROM "
				+ "(select ti.orderNo," + // 1
				"ti.merchantOrderNo," + // 2
				// "m.merno," + //3
				"to_char(ti.tradeTime,'yy-mm-dd hh24:mi:ss')," + // 4
				"ti.tradeAmount," + // 5
				// "ti.moneyType," + //6
				// "ti.rmbAmount," + //7
				// "c.channelName," + //8
				"ti.tradeState," + // 9
				// "ti.backCount," + //10
				// "ti.isPicture," + //11
				// "ti.isTrackNo," + //12
				"ti.remark," + // 13
				"ti.id," + // 14
				// "ci.address," + //15
				// "ci.shippingAddress, " +//16
				"ti.tradeUrl "; // 17
		sb.append("from International_Tradeinfo ti " +
		// "International_Merchant m on ti.merchantId=m.id left join " +
				// "International_CardholdersInfo ci on ci.tradeId=ti.id left
				// join " +
				// "International_MerchantChannels mc on ti.tradeChannel=mc.id
				// left join " +
				// "International_Channels c on mc.channelId=c.id " +
				"where 1=1 and substr(ti.tradeState,1,1)!=3 "); // 商户通道的通道
		sb.append(" and  ti.merchantId='" + getMerchantBean().getMerchantId()
				+ "' ");
		if (StringUtils.isNotBlank(startDate)) {
			sb.append(" and ti.tradeTime>=to_date('" + startDate + " 00:00:00"
					+ "','yyyy-MM-dd hh24:mi:ss') ");
		}
		if (StringUtils.isNotBlank(endDate)) {
			sb.append(" and ti.tradeTime<=to_date('" + endDate + " 23:59:59"
					+ "','yyyy-MM-dd hh24:mi:ss') ");
		}
		if (StringUtils.isNotBlank(orderNo)) {
			sb.append(" and ti.orderNo like '" + orderNo + "%'");
		}
		if (minamount != null) {
			sb.append(" and ti.tradeAmount>=" + minamount + " ");
		}
		if (maxamount != null) {
			sb.append(" and ti.tradeAmount<=" + maxamount + " ");
		}
		if (StringUtils.isNotBlank(merchantOrderNo)) {
			sb
					.append(" and ti.merchantOrderNo like '" + merchantOrderNo
							+ "%'");
		}
		if (StringUtils.isNotBlank(isgoudui)) { // 勾兑情况
			sb.append(" and substr(ti.tradeState,5,1)='" + isgoudui + "'");
		}
		if (StringUtils.isNotBlank(isdongjie)) { // 冻结情况
			sb.append(" and substr(ti.tradeState,4,1)='" + isdongjie + "'");
		}
		if (StringUtils.isNotBlank(istuikuan)) { // 退款情况
			sb.append(" and substr(ti.tradeState,2,1)='" + istuikuan + "'");
		}
		if (StringUtils.isNotBlank(ishuakuan)) { // 划款情况
			sb.append(" and substr(ti.tradeState,8,1)='" + ishuakuan + "'");
		}
		if (StringUtils.isNotBlank(isjufu)) { // 拒付情况
			sb.append(" and substr(ti.tradeState,3,1)='" + isjufu + "'");
		}
		if (StringUtils.isNotBlank(isresult)) { // 支付情况
			sb.append(" and substr(ti.tradeState,1,1)='" + isresult + "'");
		}
		if (StringUtils.isNotBlank(ispiccher)) {
			if (ispiccher.equals("0")) {
				sb.append(" and ti.isPicture is null ");
			} else {
				sb.append(" and ti.isPicture is not null ");
			}
		}
		if (StringUtils.isNotBlank(istrackno)) {
			if (istrackno.equals("0")) {
				sb.append(" and ti.isTrackNo is null ");
			} else {
				sb.append(" and ti.isTrackNo is not null ");
			}
		}
		if (StringUtils.isNotBlank(tradeUrl)) {
			sb.append(" and ti.tradeUrl like '%" + tradeUrl.trim() + "%' ");
		}
		
		sb.append(" order by ti.tradeTime desc");
		// sb.append(") A WHERE ROWNUM <= "+(startResult+1)*maxResult+")WHERE RN
		// >= " +(startResult)*maxResult+"");
		String listinfo = ")  A WHERE ROWNUM <= " + (info.getCurPage())
				* info.getPageSize() + ")WHERE RN > " + (info.getCurPage() - 1)
				* info.getPageSize() + "";
		String orderby = " order by ti.tradeTime desc";
		StringBuffer sb2 = new StringBuffer();

		sb2.append("from InternationalTradeinfo ti,"
				+ "InternationalMerchant m,"
				+ "InternationalCardholdersInfo ci,"
				+ "InternationalMerchantChannels mc,"
				+ "InternationalChannels c "
				+ "where m.id=ti.merchantId and m.id= '"
				+ getMerchantBean().getMerchantId() + // 交易表商户ID
				"' and ci.tradeId=ti.id " + // 持卡人跟交易表
				"and ti.tradeChannel=mc.id " + // 交易表商户通道
				"and mc.channelId=c.id and substr(ti.tradeState,1,1)!=3 "); // 商户通道的通道
		if (StringUtils.isNotBlank(startDate)) {
			sb2.append(" and ti.tradeTime>=to_date('" + startDate + " 00:00:00"
					+ "','yyyy-MM-dd hh24:mi:ss') ");
		}
		if (StringUtils.isNotBlank(endDate)) {
			sb2.append(" and ti.tradeTime<=to_date('" + endDate + " 23:59:59"
					+ "','yyyy-MM-dd hh24:mi:ss') ");
		}
		if (StringUtils.isNotBlank(orderNo)) {
			sb2.append(" and ti.orderNo like '" + orderNo + "%'");
		}
		if (minamount != null) {
			sb2.append(" and ti.tradeAmount>=" + minamount + " ");
		}
		if (maxamount != null) {
			sb2.append(" and ti.tradeAmount<=" + maxamount + " ");
		}
		if (StringUtils.isNotBlank(merchantOrderNo)) {
			sb2.append(" and ti.merchantOrderNo like '" + merchantOrderNo
					+ "%'");
		}
		if (StringUtils.isNotBlank(isgoudui)) { // 勾兑情况
			sb2.append(" and substr(ti.tradeState,5,1)='" + isgoudui + "'");
		}
		if (StringUtils.isNotBlank(isdongjie)) { // 冻结情况
			sb2.append(" and substr(ti.tradeState,4,1)='" + isdongjie + "'");
		}
		if (StringUtils.isNotBlank(istuikuan)) { // 退款情况
			sb2.append(" and substr(ti.tradeState,2,1)='" + istuikuan + "'");
		}
		if (StringUtils.isNotBlank(ishuakuan)) { // 划款情况
			sb2.append(" and substr(ti.tradeState,8,1)='" + ishuakuan + "'");
		}
		if (StringUtils.isNotBlank(isjufu)) { // 拒付情况
			sb2.append(" and substr(ti.tradeState,3,1)='" + isjufu + "'");
		}
		if (StringUtils.isNotBlank(isresult)) { // 支付情况
			sb2.append(" and substr(ti.tradeState,1,1)='" + isresult + "'");
		}
		if (StringUtils.isNotBlank(ispiccher)) {
			if (ispiccher.equals("0")) {
				sb2.append(" and ti.isPicture is null ");
			} else {
				sb2.append(" and ti.isPicture is not null ");
			}
		}
		if (StringUtils.isNotBlank(istrackno)) {
			if (istrackno.equals("0")) {
				sb2.append(" and ti.isTrackNo is null ");
			} else {
				sb2.append(" and ti.isTrackNo is not null ");
			}
		}
		if (StringUtils.isNotBlank(tradeUrl)) {
			sb2.append(" and ti.tradeUrl like '%" + tradeUrl.trim() + "%' ");
		}

		if (StringUtils.isNotBlank(isdownload)) {
			List<Object[]> objList = commonService.list(sb2.toString()
					+ " order by ti.tradeTime desc ");
			this.downloadTradeQuery(objList);
			return null;
		} else {
			if (StringUtils.isNotBlank(this.query) && this.query.equals("0")) {
				query = "1";
			} else {
				// 统计总计金额
				// String totalsql = "select sum(ti.tradeAmount),ti.moneyType
				// "+sb2.toString()+" group by ti.moneyType ";
				// String successfulsql = "select
				// sum(ti.tradeAmount),ti.moneyType "+sb2.toString()+" and
				// substr(ti.tradeState,1,1)='1' group by ti.moneyType";
				// String failedsql = "select sum(ti.tradeAmount),ti.moneyType
				// "+sb2.toString()+" and substr(ti.tradeState,1,1)='0' group by
				// ti.moneyType";
				// totalAmount= commonService.list(totalsql);
				// successfulAmount= commonService.list(successfulsql);
				// failedAmount= commonService.list(failedsql);
				List list = this.commonService.getByList(selectquery
						+ sb.toString() + listinfo);
				info.setTotalCount(this.tradeManager.intBySql(totleCount
						+ sb.toString()));
				info.setResult(list);
			}
			return SUCCESS;
		}

	}
	
	public String getCardnum() {
		return cardnum;
	}

	public void setCardnum(String cardnum) {
		this.cardnum = cardnum;
	}

	public PageInfo getInfo() {
		return info;
	}

	public void setInfo(PageInfo info) {
		this.info = info;
	}

	public List getAmountStatistic() {
		return amountStatistic;
	}

	public void setAmountStatistic(List amountStatistic) {
		this.amountStatistic = amountStatistic;
	}

	public String getIsdownload() {
		return isdownload;
	}

	public void setIsdownload(String isdownload) {
		this.isdownload = isdownload;
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

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getMerchantOrderNo() {
		return merchantOrderNo;
	}

	public void setMerchantOrderNo(String merchantOrderNo) {
		this.merchantOrderNo = merchantOrderNo;
	}

	public String getIsresult() {
		return isresult;
	}

	public void setIsresult(String isresult) {
		this.isresult = isresult;
	}

	public String getIstuikuan() {
		return istuikuan;
	}

	public void setIstuikuan(String istuikuan) {
		this.istuikuan = istuikuan;
	}

	public String getIsdongjie() {
		return isdongjie;
	}

	public void setIsdongjie(String isdongjie) {
		this.isdongjie = isdongjie;
	}

	public String getIsjufu() {
		return isjufu;
	}

	public void setIsjufu(String isjufu) {
		this.isjufu = isjufu;
	}

	public String getIshuakuan() {
		return ishuakuan;
	}

	public void setIshuakuan(String ishuakuan) {
		this.ishuakuan = ishuakuan;
	}

	public String getIsgoudui() {
		return isgoudui;
	}

	public void setIsgoudui(String isgoudui) {
		this.isgoudui = isgoudui;
	}

	public String getTradeUrl() {
		return tradeUrl;
	}

	public void setTradeUrl(String tradeUrl) {
		this.tradeUrl = tradeUrl;
	}

	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

	public Object[] getTradeDetail() {
		return tradeDetail;
	}

	public void setTradeDetail(Object[] tradeDetail) {
		this.tradeDetail = tradeDetail;
	}

	public String getIspiccher() {
		return ispiccher;
	}

	public void setIspiccher(String ispiccher) {
		this.ispiccher = ispiccher;
	}

	public String getIstrackno() {
		return istrackno;
	}

	public void setIstrackno(String istrackno) {
		this.istrackno = istrackno;
	}

	public List getSuccessfulAmount() {
		return successfulAmount;
	}

	public void setSuccessfulAmount(List successfulAmount) {
		this.successfulAmount = successfulAmount;
	}

	public List getFailedAmount() {
		return failedAmount;
	}

	public void setFailedAmount(List failedAmount) {
		this.failedAmount = failedAmount;
	}

	public List getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(List totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getMinamount() {
		return minamount;
	}

	public void setMinamount(Double minamount) {
		this.minamount = minamount;
	}

	public Double getMaxamount() {
		return maxamount;
	}

	public void setMaxamount(Double maxamount) {
		this.maxamount = maxamount;
	}

	public String getTradeEmail() {
		return tradeEmail;
	}

	public void setTradeEmail(String tradeEmail) {
		this.tradeEmail = tradeEmail;
	}

	public String getCurrency(String str) {
		String tem = "";
		if (str.equals("840")) {
			tem = "USD";
		} else if (str.equals("344")) {
			tem = "HKD";
		} else if (str.equals("826")) {
			tem = "GBP";
		} else if (str.equals("392")) {
			tem = "JPY";
		} else if (str.equals("978")) {
			tem = "EUR";
		} else if (str.equals("036")) {
			tem = "AUD";
		} else if (str.equals("124")) {
			tem = "CAD";
		} else if (str.equals("702")) {
			tem = "SGD";
		} else if (str.equals("554")) {
			tem = "NZD";
		} else if (str.equals("901")) {
			tem = "TWD";
		} else if (str.equals("410")) {
			tem = "KRW";
		} else if (str.equals("208")) {
			tem = "DKK";
		} else if (str.equals("949")) {
			tem = "TRY";
		} else if (str.equals("458")) {
			tem = "MYR";

		} else if (str.equals("764")) {
			tem = "THB";

		} else if (str.equals("608")) {
			tem = "PHP";
		} else if (str.equals("756")) {
			tem = "CHF";
		} else if (str.equals("752")) {
			tem = "SEK";
		} else if (str.equals("376")) {
			tem = "ILS";
		} else if (str.equals("710")) {
			tem = "ZAR";
		} else if (str.equals("643")) {
			tem = "RUB";

		} else if (str.equals("578")) {
			tem = "NOK";

		} else if (str.equals("784")) {
			tem = "AED";

		}

		return tem;
	}

	public String getShowDcc() {
		return showDcc;
	}

	public void setShowDcc(String showDcc) {
		this.showDcc = showDcc;
	}
	//商户后台高风险订单
	public String highRiskOrder()
    throws Exception
  {
    StringBuffer sb = new StringBuffer();
    String totleCount = "select count(*)";

    String selectquery = "SELECT  * FROM (SELECT A.*, ROWNUM RN FROM (select ti.orderNo,ti.merchantOrderNo,to_char(ti.tradeTime,'yy-mm-dd hh24:mi:ss'),ti.tradeAmount,ti.tradeState,ti.remark,ti.id,ti.tradeUrl ";

    sb.append("from International_Tradeinfo ti where 1=1 and substr(ti.tradeState,1,1)!=3 and ti.remark like '%1093%' ");

    sb.append(" and  ti.merchantId='" + getMerchantBean().getMerchantId() + 
      "' ");
    if (StringUtils.isNotBlank(this.startDate)) {
      sb.append(" and ti.tradeTime>=to_date('" + this.startDate + " 00:00:00" + 
        "','yyyy-MM-dd hh24:mi:ss') ");
    }
    if (StringUtils.isNotBlank(this.endDate)) {
      sb.append(" and ti.tradeTime<=to_date('" + this.endDate + " 23:59:59" + 
        "','yyyy-MM-dd hh24:mi:ss') ");
    }
    if (StringUtils.isNotBlank(this.orderNo)) {
      sb.append(" and ti.orderNo like '" + this.orderNo + "%'");
    }
    if (this.minamount != null) {
      sb.append(" and ti.tradeAmount>=" + this.minamount + " ");
    }
    if (this.maxamount != null) {
      sb.append(" and ti.tradeAmount<=" + this.maxamount + " ");
    }
    if (StringUtils.isNotBlank(this.merchantOrderNo)) {
      sb
        .append(" and ti.merchantOrderNo like '" + this.merchantOrderNo + 
        "%'");
    }
    if (StringUtils.isNotBlank(this.isgoudui)) {
      sb.append(" and substr(ti.tradeState,5,1)='" + this.isgoudui + "'");
    }
    if (StringUtils.isNotBlank(this.isdongjie)) {
      sb.append(" and substr(ti.tradeState,4,1)='" + this.isdongjie + "'");
    }
    if (StringUtils.isNotBlank(this.istuikuan)) {
      sb.append(" and substr(ti.tradeState,2,1)='" + this.istuikuan + "'");
    }
    if (StringUtils.isNotBlank(this.ishuakuan)) {
      sb.append(" and substr(ti.tradeState,8,1)='" + this.ishuakuan + "'");
    }
    if (StringUtils.isNotBlank(this.isjufu)) {
      sb.append(" and substr(ti.tradeState,3,1)='" + this.isjufu + "'");
    }
    if (StringUtils.isNotBlank(this.isresult)) {
      sb.append(" and substr(ti.tradeState,1,1)='" + this.isresult + "'");
    }
    if (StringUtils.isNotBlank(this.ispiccher)) {
      if (this.ispiccher.equals("0"))
        sb.append(" and ti.isPicture is null ");
      else {
        sb.append(" and ti.isPicture is not null ");
      }
    }
    if (StringUtils.isNotBlank(this.istrackno)) {
      if (this.istrackno.equals("0"))
        sb.append(" and ti.isTrackNo is null ");
      else {
        sb.append(" and ti.isTrackNo is not null ");
      }
    }
    if (StringUtils.isNotBlank(this.tradeUrl)) {
      sb.append(" and ti.tradeUrl like '%" + this.tradeUrl.trim() + "%' ");
    }
    sb.append(" order by ti.tradeTime desc");

    String listinfo = ")  A WHERE ROWNUM <= " + this.info.getCurPage() * 
      this.info.getPageSize() + ")WHERE RN > " + (this.info.getCurPage() - 1) * 
      this.info.getPageSize();
    String orderby = " order by ti.tradeTime desc";
    StringBuffer sb2 = new StringBuffer();

    sb2.append("from InternationalTradeinfo ti,InternationalMerchant m,InternationalCardholdersInfo ci,InternationalMerchantChannels mc,InternationalChannels c where m.id=ti.merchantId and m.id= '" + 
      getMerchantBean().getMerchantId() + 
      "' and ci.tradeId=ti.id " + 
      "and ti.tradeChannel=mc.id " + 
      "and mc.channelId=c.id and substr(ti.tradeState,1,1)!=3 and ti.remark like '%1093%' "); 
    if (StringUtils.isNotBlank(this.startDate)) {
      sb2.append(" and ti.tradeTime>=to_date('" + this.startDate + " 00:00:00" + 
        "','yyyy-MM-dd hh24:mi:ss') ");
    }
    if (StringUtils.isNotBlank(this.endDate)) {
      sb2.append(" and ti.tradeTime<=to_date('" + this.endDate + " 23:59:59" + 
        "','yyyy-MM-dd hh24:mi:ss') ");
    }
    if (StringUtils.isNotBlank(this.orderNo)) {
      sb2.append(" and ti.orderNo like '" + this.orderNo + "%'");
    }
    if (this.minamount != null) {
      sb2.append(" and ti.tradeAmount>=" + this.minamount + " ");
    }
    if (this.maxamount != null) {
      sb2.append(" and ti.tradeAmount<=" + this.maxamount + " ");
    }
    if (StringUtils.isNotBlank(this.merchantOrderNo)) {
      sb2.append(" and ti.merchantOrderNo like '" + this.merchantOrderNo + 
        "%'");
    }
    if (StringUtils.isNotBlank(this.isgoudui)) {
      sb2.append(" and substr(ti.tradeState,5,1)='" + this.isgoudui + "'");
    }
    if (StringUtils.isNotBlank(this.isdongjie)) {
      sb2.append(" and substr(ti.tradeState,4,1)='" + this.isdongjie + "'");
    }
    if (StringUtils.isNotBlank(this.istuikuan)) {
      sb2.append(" and substr(ti.tradeState,2,1)='" + this.istuikuan + "'");
    }
    if (StringUtils.isNotBlank(this.ishuakuan)) {
      sb2.append(" and substr(ti.tradeState,8,1)='" + this.ishuakuan + "'");
    }
    if (StringUtils.isNotBlank(this.isjufu)) {
      sb2.append(" and substr(ti.tradeState,3,1)='" + this.isjufu + "'");
    }
    if (StringUtils.isNotBlank(this.isresult)) {
      sb2.append(" and substr(ti.tradeState,1,1)='" + this.isresult + "'");
    }
    if (StringUtils.isNotBlank(this.ispiccher)) {
      if (this.ispiccher.equals("0"))
        sb2.append(" and ti.isPicture is null ");
      else {
        sb2.append(" and ti.isPicture is not null ");
      }
    }
    if (StringUtils.isNotBlank(this.istrackno)) {
      if (this.istrackno.equals("0"))
        sb2.append(" and ti.isTrackNo is null ");
      else {
        sb2.append(" and ti.isTrackNo is not null ");
      }
    }
    if (StringUtils.isNotBlank(this.tradeUrl)) {
      sb2.append(" and ti.tradeUrl like '%" + this.tradeUrl.trim() + "%' ");
    }

    if (StringUtils.isNotBlank(this.isdownload)) {
      List objList = this.commonService.list(sb2.toString() + 
        " order by ti.tradeTime desc ", new Object[0]);
      downloadTradeQuery(objList);
      return null;
    }
    if ((StringUtils.isNotBlank(this.query)) && (this.query.equals("0"))) {
      this.query = "1";
    }
    else
    {
      List list = this.commonService.getByList(selectquery + 
        sb.toString() + listinfo);
      this.info.setTotalCount(this.tradeManager.intBySql(totleCount + 
        sb.toString()));
      this.info.setResult(list);
    }
    return "success";
  }
	//重复订单查询
	public String repeatOrder()
		    throws Exception
		  {
		    StringBuffer sb = new StringBuffer();
		    String totleCount = "select count(*)";

		    String selectquery = "SELECT  * FROM (SELECT A.*, ROWNUM RN FROM (select ti.orderNo,ti.merchantOrderNo,to_char(ti.tradeTime,'yy-mm-dd hh24:mi:ss'),ti.tradeAmount,ti.tradeState,ti.remark,ti.id,ti.tradeUrl ";

		    sb.append("from International_Tradeinfo ti where 1=1 and substr(ti.tradeState,1,1)!=3 and ti.remark like '%1100%' ");

		    sb.append(" and  ti.merchantId='" + getMerchantBean().getMerchantId() + 
		      "' ");
		    if (StringUtils.isNotBlank(this.startDate)) {
		      sb.append(" and ti.tradeTime>=to_date('" + this.startDate + " 00:00:00" + 
		        "','yyyy-MM-dd hh24:mi:ss') ");
		    }
		    if (StringUtils.isNotBlank(this.endDate)) {
		      sb.append(" and ti.tradeTime<=to_date('" + this.endDate + " 23:59:59" + 
		        "','yyyy-MM-dd hh24:mi:ss') ");
		    }
		    if (StringUtils.isNotBlank(this.orderNo)) {
		      sb.append(" and ti.orderNo like '" + this.orderNo + "%'");
		    }
		    if (this.minamount != null) {
		      sb.append(" and ti.tradeAmount>=" + this.minamount + " ");
		    }
		    if (this.maxamount != null) {
		      sb.append(" and ti.tradeAmount<=" + this.maxamount + " ");
		    }
		    if (StringUtils.isNotBlank(this.merchantOrderNo)) {
		      sb
		        .append(" and ti.merchantOrderNo like '" + this.merchantOrderNo + 
		        "%'");
		    }
		    if (StringUtils.isNotBlank(this.isgoudui)) {
		      sb.append(" and substr(ti.tradeState,5,1)='" + this.isgoudui + "'");
		    }
		    if (StringUtils.isNotBlank(this.isdongjie)) {
		      sb.append(" and substr(ti.tradeState,4,1)='" + this.isdongjie + "'");
		    }
		    if (StringUtils.isNotBlank(this.istuikuan)) {
		      sb.append(" and substr(ti.tradeState,2,1)='" + this.istuikuan + "'");
		    }
		    if (StringUtils.isNotBlank(this.ishuakuan)) {
		      sb.append(" and substr(ti.tradeState,8,1)='" + this.ishuakuan + "'");
		    }
		    if (StringUtils.isNotBlank(this.isjufu)) {
		      sb.append(" and substr(ti.tradeState,3,1)='" + this.isjufu + "'");
		    }
		    if (StringUtils.isNotBlank(this.isresult)) {
		      sb.append(" and substr(ti.tradeState,1,1)='" + this.isresult + "'");
		    }
		    if (StringUtils.isNotBlank(this.ispiccher)) {
		      if (this.ispiccher.equals("0"))
		        sb.append(" and ti.isPicture is null ");
		      else {
		        sb.append(" and ti.isPicture is not null ");
		      }
		    }
		    if (StringUtils.isNotBlank(this.istrackno)) {
		      if (this.istrackno.equals("0"))
		        sb.append(" and ti.isTrackNo is null ");
		      else {
		        sb.append(" and ti.isTrackNo is not null ");
		      }
		    }
		    if (StringUtils.isNotBlank(this.tradeUrl)) {
		      sb.append(" and ti.tradeUrl like '%" + this.tradeUrl.trim() + "%' ");
		    }
		    sb.append(" order by ti.tradeTime desc");

		    String listinfo = ")  A WHERE ROWNUM <= " + this.info.getCurPage() * 
		      this.info.getPageSize() + ")WHERE RN > " + (this.info.getCurPage() - 1) * 
		      this.info.getPageSize();
		    String orderby = " order by ti.tradeTime desc";
		    StringBuffer sb2 = new StringBuffer();

		    sb2.append("from InternationalTradeinfo ti,InternationalMerchant m,InternationalCardholdersInfo ci,InternationalMerchantChannels mc,InternationalChannels c where m.id=ti.merchantId and m.id= '" + 
		      getMerchantBean().getMerchantId() + 
		      "' and ci.tradeId=ti.id " + 
		      "and ti.tradeChannel=mc.id " + 
		      "and mc.channelId=c.id and substr(ti.tradeState,1,1)!=3 and ti.remark like '%1100%'  ");
		    if (StringUtils.isNotBlank(this.startDate)) {
		      sb2.append(" and ti.tradeTime>=to_date('" + this.startDate + " 00:00:00" + 
		        "','yyyy-MM-dd hh24:mi:ss') ");
		    }
		    if (StringUtils.isNotBlank(this.endDate)) {
		      sb2.append(" and ti.tradeTime<=to_date('" + this.endDate + " 23:59:59" + 
		        "','yyyy-MM-dd hh24:mi:ss') ");
		    }
		    if (StringUtils.isNotBlank(this.orderNo)) {
		      sb2.append(" and ti.orderNo like '" + this.orderNo + "%'");
		    }
		    if (this.minamount != null) {
		      sb2.append(" and ti.tradeAmount>=" + this.minamount + " ");
		    }
		    if (this.maxamount != null) {
		      sb2.append(" and ti.tradeAmount<=" + this.maxamount + " ");
		    }
		    if (StringUtils.isNotBlank(this.merchantOrderNo)) {
		      sb2.append(" and ti.merchantOrderNo like '" + this.merchantOrderNo + 
		        "%'");
		    }
		    if (StringUtils.isNotBlank(this.isgoudui)) {
		      sb2.append(" and substr(ti.tradeState,5,1)='" + this.isgoudui + "'");
		    }
		    if (StringUtils.isNotBlank(this.isdongjie)) {
		      sb2.append(" and substr(ti.tradeState,4,1)='" + this.isdongjie + "'");
		    }
		    if (StringUtils.isNotBlank(this.istuikuan)) {
		      sb2.append(" and substr(ti.tradeState,2,1)='" + this.istuikuan + "'");
		    }
		    if (StringUtils.isNotBlank(this.ishuakuan)) {
		      sb2.append(" and substr(ti.tradeState,8,1)='" + this.ishuakuan + "'");
		    }
		    if (StringUtils.isNotBlank(this.isjufu)) {
		      sb2.append(" and substr(ti.tradeState,3,1)='" + this.isjufu + "'");
		    }
		    if (StringUtils.isNotBlank(this.isresult)) {
		      sb2.append(" and substr(ti.tradeState,1,1)='" + this.isresult + "'");
		    }
		    if (StringUtils.isNotBlank(this.ispiccher)) {
		      if (this.ispiccher.equals("0"))
		        sb2.append(" and ti.isPicture is null ");
		      else {
		        sb2.append(" and ti.isPicture is not null ");
		      }
		    }
		    if (StringUtils.isNotBlank(this.istrackno)) {
		      if (this.istrackno.equals("0"))
		        sb2.append(" and ti.isTrackNo is null ");
		      else {
		        sb2.append(" and ti.isTrackNo is not null ");
		      }
		    }
		    if (StringUtils.isNotBlank(this.tradeUrl)) {
		      sb2.append(" and ti.tradeUrl like '%" + this.tradeUrl.trim() + "%' ");
		    }

		    if (StringUtils.isNotBlank(this.isdownload)) {
		      List objList = this.commonService.list(sb2.toString() + 
		        " order by ti.tradeTime desc ", new Object[0]);
		      downloadTradeQuery(objList);
		      return null;
		    }
		    if ((StringUtils.isNotBlank(this.query)) && (this.query.equals("0"))) {
		      this.query = "1";
		    }
		    else
		    {
		      List list = this.commonService.getByList(selectquery + 
		        sb.toString() + listinfo);
		      this.info.setTotalCount(this.tradeManager.intBySql(totleCount + 
		        sb.toString()));
		      this.info.setResult(list);
		    }
		    return "success";
		  }
}
