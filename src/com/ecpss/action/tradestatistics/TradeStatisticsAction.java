package com.ecpss.action.tradestatistics;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.ecpss.action.BaseAction;
import com.ecpss.model.payment.InternationalTradeinfo;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.util.CalcuLate;
import com.ecpss.web.PageInfo;

public class TradeStatisticsAction extends BaseAction {
	/**
	 * 分页对象
	 */
	private PageInfo info = new PageInfo();

	private CalcuLate caclulate = new CalcuLate();
	
	private InternationalTradeinfo tradeinfo = new InternationalTradeinfo();

	private InternationalMerchant merchant = new InternationalMerchant();

	private List<Long> merchantNoList;

	private List tradeList = new ArrayList();

	private List tradeSecondList = new ArrayList();

	private List tradeThirdList = new ArrayList();
	
	private List tradeFourthList = new ArrayList();
	
	private List tradeFourthListurl = new ArrayList();
	
	private String jsonTradeSecondList;
	
	private String jsonTradeFourthList;
	
	private String jsonTradeFourthListurl;

	private String flag = "0";

	private List<String> terminalList;
	/**
	 * 查询条件参数
	 */
	private String startDate;
	private String endDate;
	/**
	 * 统计金额
	 */
	private List totalAmount; // 总交易金额
	private List successfulAmount; // 成功交易金额
	private List failedAmount; // 失败交易金额
	private List tradestateAmount; // 交易状态
	private List drawbackAmount; // 退款
	private List protestAmount; // 拒付
	private List freezeAmount; // 冻结
	private List blendAmount; // 冻结
	private List applyAmount; // 申请
	private List auditAmount; // 审核
	private List transfermoneyAmount; // 是否划款
	private List bailauditAmount; // 保证金审核
	private List bailtransfermoneyAmount; // 保证金划款
	private String tradeUrl;
	public String getJsonTradeSecondList() {
		return jsonTradeSecondList;
	}

	public void setJsonTradeSecondList(String jsonTradeSecondList) {
		this.jsonTradeSecondList = jsonTradeSecondList;
	}

	public String getTradeUrl() {
		return tradeUrl;
	}

	public void setTradeUrl(String tradeUrl) {
		this.tradeUrl = tradeUrl;
	}

	/**
	 * 交易统计
	 * 
	 * @return
	 */
	public String tradeStatistics() {
		StringBuffer sb = new StringBuffer();
		String selectquery = "select ti,m ";
		sb
				.append("from InternationalTradeinfo ti,InternationalMerchant m "
						+ "where ti.merchantId=m.id ");
		if (merchant.getMerno() != null) {
			sb.append(" and m.merno='" + merchant.getMerno() + "'");
		}
		if (!("".equals(tradeinfo.getOrderNo()))
				&& tradeinfo.getOrderNo() != null) {
			sb
					.append(" and ti.orderNo='" + tradeinfo.getOrderNo().trim()
							+ "'");
		}
		if (!("".equals(tradeinfo.getMerchantOrderNo()))
				&& tradeinfo.getMerchantOrderNo() != null) {
			sb.append(" and ti.merchantOrderNo='"
					+ tradeinfo.getMerchantOrderNo() + "'");
		}
		if (merchant.getMerno() != null) {
			sb.append(" and m.merno=" + merchant.getMerno());
		}
		if (StringUtils.isNotBlank(startDate)) { // 开始日期
			sb.append(" and ti.tradeTime>=to_date('" + startDate
					+ "','yyyy-MM-dd hh24:mi:ss') ");
		}
		if (StringUtils.isNotBlank(endDate)) { // 结束日期
			sb.append(" and ti.tradeTime<=to_date('" + endDate
					+ " 23:59:59','yyyy-MM-dd hh24:mi:ss') ");
		}
		String orderby = " order by ti.tradeTime desc";
		// 交易状态
		String tradestatesql = "select substr(ti.tradeState,1,1), sum(ti.tradeAmount),ti.moneyType,count(*) "
				+ sb.toString()
				+ "group by substr(ti.tradeState,1,1),ti.moneyType";
		// 退款
		String drawbacksql = "select substr(ti.tradeState,2,1), sum(ti.tradeAmount),sum(ti.backCount),ti.moneyType,count(*) "
				+ sb.toString()
				+ "group by substr(ti.tradeState,2,1),ti.moneyType";
		// 拒付
		String protestsql = "select substr(ti.tradeState,3,1), sum(ti.tradeAmount),ti.moneyType,count(*) "
				+ sb.toString()
				+ "group by substr(ti.tradeState,3,1),ti.moneyType";
		// 冻结
		String freezesql = "select substr(ti.tradeState,4,1), sum(ti.tradeAmount),ti.moneyType,count(*) "
				+ sb.toString()
				+ "group by substr(ti.tradeState,4,1),ti.moneyType";
		// 勾兑
		String blendsql = "select substr(ti.tradeState,5,1), sum(ti.tradeAmount),ti.moneyType,count(*) "
				+ sb.toString()
				+ "group by substr(ti.tradeState,5,1),ti.moneyType";
		// 申请
		String applysql = "select substr(ti.tradeState,6,1), sum(ti.tradeAmount),ti.moneyType,count(*) "
				+ sb.toString()
				+ "group by substr(ti.tradeState,6,1),ti.moneyType";
		// 审核
		String auditsql = "select substr(ti.tradeState,7,1), sum(ti.tradeAmount),ti.moneyType,count(*) "
				+ sb.toString()
				+ "group by substr(ti.tradeState,7,1),ti.moneyType";
		// 是否划款
		String istransfermoneytsql = "select substr(ti.tradeState,8,1), sum(ti.tradeAmount),ti.moneyType,count(*) "
				+ sb.toString()
				+ "group by substr(ti.tradeState,8,1),ti.moneyType";
		// 保证金审核
		String bailauditsql = "select substr(ti.tradeState,9,1), sum(ti.tradeAmount),ti.moneyType,count(*) "
				+ sb.toString()
				+ "group by substr(ti.tradeState,9,1),ti.moneyType";
		// 保证金划款
		String bailtransfermoneysql = "select substr(ti.tradeState,10,1), sum(ti.tradeAmount),ti.moneyType,count(*) "
				+ sb.toString()
				+ "group by substr(ti.tradeState,10,1),ti.moneyType";

		// 交易状态
		tradestateAmount = commonService.list(tradestatesql);
		// 退款
		drawbackAmount = commonService.list(drawbacksql);
		// 拒付
		protestAmount = commonService.list(protestsql);
		// 冻结
		freezeAmount = commonService.list(freezesql);
		// 勾兑
		blendAmount = commonService.list(blendsql);
		// 申请
		applyAmount = commonService.list(applysql);
		// 审核
		auditAmount = commonService.list(auditsql);
		// 是否划款
		transfermoneyAmount = commonService.list(istransfermoneytsql);
		// 保证金审核
		bailauditAmount = commonService.list(bailauditsql);
		// 保证金划款
		bailtransfermoneyAmount = commonService.list(bailtransfermoneysql);

		info = commonService.listQueryResultByHql(selectquery + sb.toString()
				+ orderby, info);
		return SUCCESS;
	}

	/**
	 * 交易信息
	 * 
	 * @return
	 */
	public String tradeInfo() {
		String merno = "";
		String tmp = "";
		if (merchant.getMerno() != null) {
			merno = " and im.merno='" + merchant.getMerno() + "'";

		}
		if (StringUtils.isNotBlank(startDate)) { // 开始日期
			tmp = " and t.tradetime>=to_date('" + startDate
					+ "','yyyy-MM-dd hh24:mi:ss')";
		}
		if (StringUtils.isNotBlank(endDate)) { // 结束日期
			tmp += " and t.tradetime<=to_date('" + endDate.trim() + " 23:59:59','yyyy-MM-dd hh24:mi:ss') ";
		}
		
		String tmp1 = "";
		
		if (StringUtils.isNotBlank(startDate)) { // 开始日期
			tmp1 = " and t.protesttime>=to_date('" + startDate
					+ "','yyyy-MM-dd hh24:mi:ss')";
		}
		if (StringUtils.isNotBlank(endDate)) { // 结束日期
			tmp1 += " and t.protesttime<=to_date('" + endDate.trim() + " 23:59:59','yyyy-MM-dd hh24:mi:ss') ";
		}
		
		String tmp2 = "";
		
		if (StringUtils.isNotBlank(startDate)) { // 开始日期
			tmp2 = " and r.refundDate>=to_date('" + startDate
			+ "','yyyy-MM-dd hh24:mi:ss')";
		}
		if (StringUtils.isNotBlank(endDate)) { // 结束日期
			tmp2 += " and r.refundDate<=to_date('" + endDate.trim() + " 23:59:59','yyyy-MM-dd hh24:mi:ss') ";
		}
		
		
		StringBuffer sb = new StringBuffer();
		sb
				.append("select aa.merno,aa.merchantid,aa.tradeamount aatradeamount,aa.tradecount aatradecount,bb.tradeamount bbtradeamount,bb.tradecount bbtradecount,"
						+ "cc.tradeamount cctradeamount,cc.tradecount cctradecount,dd.tradeamount ddtradeamount,dd.tradecount ddtradecount,"
						+ "ee.tradeamount eetradeamount,ee.tradecount eetradecount,ff.tradeamount fftradeamount,ff.tradecount fftradecount,"
						+ "gg.tradeamount ggtradeamount,gg.tradecount ggtradecount,hh.backcountmoney hhbackcountmoney,hh.backcount hhbackcount,"
						+ "ii.tradeamount iitradeamount,ii.tradecount iitradecount,jj.tradeamount jjtradeamount,jj.tradecount jjtradecount," +
								"yp.tradeamount yptradeamount,yp.tradecount yptradecount,aa.moneytype," +
								"rf.tradeamount rftradeamount,rf.tradecount rftradecount "
						+ " from (select im.merno,t.merchantid,sum(t.tradeamount)  tradeamount ,count(*)  tradecount,t.moneyType moneytype from international_tradeinfo t,"
						+ "international_merchant im "
						+ "where t.merchantid=im.id "
						+ tmp
						+ " "
						+ merno
						+ " group by  im.merno,t.merchantid,t.moneyType) aa");
		sb
				.append(" left join (select t.merchantid,sum(t.tradeamount)  tradeamount ,count(*)  tradecount from international_tradeinfo t,"
						+ "international_merchant im where t.merchantid=im.id "
						+ "and substr(t.tradestate,1,1)='0' "
						+ tmp
						+ " "
						+ merno
						+ "group by  t.merchantid) bb  on aa.merchantid=bb.merchantid ");
		sb
				.append(" left join (select t.merchantid, sum(t.tradeamount) as tradeamount ,count(*) as tradecount from international_tradeinfo t,international_merchant im where t.merchantid=im.id and substr(t.tradestate,1,1)='1' "
						+ tmp
						+ " "
						+ merno
						+ "  group by  t.merchantid) cc on aa.merchantid=cc.merchantid ");
		sb
				.append(" left join (select  t.merchantid,sum(t.tradeamount) as tradeamount ,count(*) as tradecount from international_tradeinfo t,international_merchant im where t.merchantid=im.id and substr(t.tradestate,1,1)='2' "
						+ tmp
						+ " "
						+ merno
						+ " group by  t.merchantid) dd  on aa.merchantid=dd.merchantid ");
		sb
				.append(" left join (select  t.merchantid,sum(t.tradeamount) as tradeamount ,count(*) as tradecount from international_tradeinfo t,international_merchant im where t.merchantid=im.id and substr(t.tradestate,1,1)='3' "
						+ tmp
						+ " "
						+ merno
						+ " group by  t.merchantid) ee on aa.merchantid=ee.merchantid ");
		sb
				.append(" left join (select  t.merchantid,sum(t.tradeamount) as tradeamount ,count(*) as tradecount from international_tradeinfo t,international_merchant im where t.merchantid=im.id and substr(t.tradestate,1,1)='4'  "
						+ tmp
						+ " "
						+ merno
						+ " group by  t.merchantid) ff  on aa.merchantid=ff.merchantid");
		sb
				.append(" left join (select  t.merchantid,sum(t.tradeamount) as tradeamount ,count(*) as tradecount from international_tradeinfo t, international_merchant im where t.merchantid=im.id and substr(t.tradestate,1,1)='5' "
						+ tmp
						+ " "
						+ merno
						+ " group by  t.merchantid) gg  on aa.merchantid=gg.merchantid");
		sb
				.append(" left join (select  t.merchantid,sum(t.backcount) as backcountmoney ,count(*) as backcount from international_tradeinfo t,international_merchant im where t.merchantid=im.id and substr(t.tradestate,2,1) in (1,2)  "
						+ tmp
						+ " "
						+ merno
						+ " group by  t.merchantid) hh  on aa.merchantid=hh.merchantid");
		sb
				.append(" left join (select  t.merchantid,sum(t.tradeamount) as tradeamount ,count(*) as tradecount from international_tradeinfo t,international_merchant im where t.merchantid=im.id and substr(t.tradestate,3,1)='1' "
						+ tmp
						+ " "
						+ merno
						+ "  group by  t.merchantid) ii on aa.merchantid=ii.merchantid");
		sb
				.append(" left join (select  t.merchantid,sum(t.tradeamount) as tradeamount ,count(*) as tradecount from international_tradeinfo t,international_merchant im where t.merchantid=im.id and substr(t.tradestate,4,1)='1' "
						+ tmp

						+ " "
						+ merno
						+ "  group by  t.merchantid) jj  on aa.merchantid=jj.merchantid");
		sb.append(" left join (select  t.merchantid,sum(r.refundamount) as tradeamount ,count(*) as tradecount from international_tradeinfo t,international_merchant im ,international_refundmanager r where t.merchantid=im.id   and r.tradeid=t.id and r.refundstate in (4,5) "
						+ tmp2
						+ " "
						+ merno
						+" group by  t.merchantid) rf  on aa.merchantid=rf.merchantid  ");
		sb
				.append(" left join (select  t.merchantid,sum(t.tradeamount) as tradeamount ,count(*) as tradecount from international_tradeinfo t,international_merchant im where t.merchantid=im.id and substr(t.tradestate,3,1)='1' "
						+ tmp1
						+ " "
						+ merno
						+ "  group by  t.merchantid) yp  on aa.merchantid=yp.merchantid  order by aa.merno");
	
		tradeList = commonService.getByList(sb.toString());
		return SUCCESS;
	}

	public String tradeinfoSecond() {
		String merno = "";
		String tmp = "";
		if (tradeUrl == null) 
		{
			return SUCCESS;
		}
		if (merchant.getMerno() != null) {
			merno = " and im.merno='" + merchant.getMerno() + "'";

		}
		//查询网站
		if (StringUtils.isNotBlank(this.tradeUrl))
		{
			merno += " and t.tradeUrl  = '"+tradeUrl+"'";
		}
		
		if (StringUtils.isNotBlank(startDate)) { // 开始日期
			tmp += " and t.tradetime>=to_date('" + startDate
					+ "','yyyy-MM-dd hh24:mi:ss')";
		}
		if (StringUtils.isNotBlank(endDate)) { // 结束日期
			tmp += " and t.tradetime<=to_date('" + endDate
					+ " 23:59:59','yyyy-MM-dd hh24:mi:ss') ";
		}
		if (StringUtils.isBlank(startDate) && StringUtils.isBlank(endDate)) {
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DAY_OF_MONTH, -30);
			tmp = " and t.tradetime>=to_date('" + c.getTime().toLocaleString()
					+ "','yyyy-MM-dd hh24:mi:ss') "
					+ "and t.tradetime<=sysdate ";
		}
		StringBuffer sb = new StringBuffer();
		sb
				.append("select aa.aatradetime,aa.tradeamount aatradeamount,aa.tradecount aatradecount,bb.tradeamount bbtradeamount,bb.tradecount bbtradecount,cc.tradeamount cctradeamount,cc.tradecount cctradecount,dd.tradeamount ddtradeamount,dd.tradecount ddtradecount, ee.tradeamount eetradeamount,ee.tradecount eetradecount");
		sb
				.append(" from (select to_char(t.tradetime,'YYYY-MM-dd') aatradetime,sum(t.tradeamount) tradeamount ,count(*) tradecount from international_tradeinfo t,international_merchant im where t.merchantid=im.id "
						+ merno
						+ " "
						+ tmp
						+ " group by to_char(t.tradetime,'YYYY-MM-dd')) aa");
		sb
				.append(" left join (select  to_char(t.tradetime,'YYYY-MM-dd') bbtradetime,sum(t.tradeamount) tradeamount ,count(*)  tradecount from international_tradeinfo t,international_merchant im where t.merchantid=im.id  and substr(t.tradestate,1,1)='0'"
						+ merno
						+ " "
						+ tmp
						+ " group by to_char(t.tradetime,'YYYY-MM-dd')) bb  on aa.aatradetime=bb.bbtradetime");
		sb
				.append(" left join (select  to_char(t.tradetime,'YYYY-MM-dd') cctradetime,sum(t.tradeamount) tradeamount ,count(*)  tradecount from international_tradeinfo t,international_merchant im where t.merchantid=im.id and substr(t.tradestate,1,1)='1'"
						+ merno
						+ " "
						+ tmp
						+ " group by to_char(t.tradetime,'YYYY-MM-dd')) cc  on aa.aatradetime=cc.cctradetime");
		sb
				.append(" left join (select  to_char(t.tradetime,'YYYY-MM-dd') ddtradetime,sum(t.tradeamount) tradeamount ,count(*)  tradecount from international_tradeinfo t,international_merchant im where t.merchantid=im.id and substr(t.tradestate,3,1)='1'"
						+ merno
						+ " "
						+ tmp
						+ " group by to_char(t.tradetime,'YYYY-MM-dd')) dd  on aa.aatradetime=dd.ddtradetime");
		sb
				.append(" left join (select  to_char(t.tradetime,'YYYY-MM-dd') eetradetime,sum(t.tradeamount) tradeamount ,count(*)  tradecount from international_tradeinfo t,international_merchant im where t.merchantid=im.id and (substr(t.tradestate,2,1)='1' or substr(t.tradestate,2,1)='2')"
						+ merno
						+ " "
						+ tmp
						+ " group by to_char(t.tradetime,'YYYY-MM-dd')) ee  on aa.aatradetime=ee.eetradetime order by aa.aatradetime desc");
			//
		tradeSecondList = commonService.getByList(sb.toString());
		
		jsonTradeSecondList = JSONArray.fromObject(tradeSecondList).toString();
		return SUCCESS;
	}

	public String tradeinfoThird() {
		String select = "select distinct imt.terminalNo from InternationalTerminalManager imt";
		terminalList = this.commonService.list(select);

		String merno = "";
		String tmp = "";
		if ("".equals(tradeinfo.getVIPTerminalNo())
				|| tradeinfo.getVIPTerminalNo() == null) {
			return SUCCESS;
		}
		if (tradeinfo.getVIPTerminalNo() != null) {
			merno = " and t.vipterminalno='" + tradeinfo.getVIPTerminalNo()
					+ "'";

		}
		if (StringUtils.isNotBlank(startDate)) { // 开始日期
			tmp = " and t.tradetime>=to_date('" + startDate
					+ "','yyyy-MM-dd hh24:mi:ss')";
		}
		if (StringUtils.isNotBlank(endDate)) { // 结束日期
			tmp = " and t.tradetime<=to_date('" + endDate
					+ " 23:59:59','yyyy-MM-dd hh24:mi:ss') ";
		}
		if (StringUtils.isBlank(startDate) && StringUtils.isBlank(endDate)) {
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DAY_OF_MONTH, -30);
			tmp = " and t.tradetime>=to_date('" + c.getTime().toLocaleString()
					+ "','yyyy-MM-dd hh24:mi:ss') "
					+ "and t.tradetime<=sysdate ";
		}
		StringBuffer sb = new StringBuffer();
		sb
				.append("select aa.aatradetime,aa.tradeamount aatradeamount,aa.tradecount aatradecount,bb.tradeamount bbtradeamount,bb.tradecount bbtradecount,cc.tradeamount cctradeamount,cc.tradecount cctradecount,dd.tradeamount ddtradeamount,dd.tradecount ddtradecount");
		sb
				.append(" from (select to_char(t.tradetime,'YYYY-MM-dd') aatradetime,sum(t.tradeamount) tradeamount ,count(*) tradecount from international_tradeinfo t where 1=1 "
						+ merno
						+ " "
						+ tmp
						+ " group by to_char(t.tradetime,'YYYY-MM-dd')) aa");
		sb
				.append(" left join (select  to_char(t.tradetime,'YYYY-MM-dd') bbtradetime,sum(t.tradeamount) tradeamount ,count(*)  tradecount from international_tradeinfo t where substr(t.tradestate,1,1)='0'"
						+ merno
						+ " "
						+ tmp
						+ " group by to_char(t.tradetime,'YYYY-MM-dd')) bb  on aa.aatradetime=bb.bbtradetime");
		sb
				.append(" left join (select  to_char(t.tradetime,'YYYY-MM-dd') cctradetime,sum(t.tradeamount) tradeamount ,count(*)  tradecount from international_tradeinfo t where substr(t.tradestate,1,1)='1'"
						+ merno
						+ " "
						+ tmp
						+ " group by to_char(t.tradetime,'YYYY-MM-dd')) cc  on aa.aatradetime=cc.cctradetime");
		sb
				.append(" left join (select  to_char(t.tradetime,'YYYY-MM-dd') ddtradetime,sum(t.tradeamount) tradeamount ,count(*)  tradecount from international_tradeinfo t where substr(t.tradestate,3,1)='1'"
						+ merno
						+ " "
						+ tmp
						+ " group by to_char(t.tradetime,'YYYY-MM-dd')) dd  on aa.aatradetime=dd.ddtradetime  order by aa.aatradetime desc");
	
		tradeThirdList = commonService.getByList(sb.toString());
		return SUCCESS;
	}

	//各地区国家拒付
	public String tradeinfoFourth() {
		String merno = "";
		String tmp = "";
		String tmp2 = "and t.id = ic.tradeid";

		if (merchant.getMerno() != null) {
			merno = " and im.merno='" + merchant.getMerno() + "'";

		}

		if (StringUtils.isNotBlank(startDate)) { // 开始日期
			tmp += " and t.tradetime>=to_date('" + startDate
					+ "','yyyy-MM-dd hh24:mi:ss')";
		}
		if (StringUtils.isNotBlank(endDate)) { // 结束日期
			tmp += " and t.tradetime<=to_date('" + endDate
					+ " 23:59:59','yyyy-MM-dd hh24:mi:ss') ";
		}
		if (StringUtils.isBlank(startDate) && StringUtils.isBlank(endDate)) {
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DAY_OF_MONTH, -30);
			tmp = " and t.tradetime>=to_date('" + c.getTime().toLocaleString()
					+ "','yyyy-MM-dd hh24:mi:ss') "
					+ "and t.tradetime<=sysdate ";
		}
		
		//下列是国家地区拒付
		StringBuffer sb = new StringBuffer();
		sb						//国家                   // 总金额                                                        //  总交易条数                                    //失败交易金额                                        //失败交易条数                                      //成功交易金额                                        //成功交易条数				//拒付金额                                             //拒付条数                                              //已退款和部分退款金额                  //已退款和部分退款条数   
				.append("select aa.aacountry,aa.tradeamount aatradeamount,aa.tradecount aatradecount,bb.tradeamount bbtradeamount,bb.tradecount bbtradecount,cc.tradeamount cctradeamount,cc.tradecount cctradecount,dd.tradeamount ddtradeamount,coalesce(dd.tradecount,0) ddtradecount, ee.tradeamount eetradeamount,ee.tradecount eetradecount");
		sb
				.append(" from (select ic.shippingCountry aacountry,sum(t.tradeamount) tradeamount ,count(*) tradecount from international_tradeinfo t,international_merchant im,international_cardholdersinfo ic where t.merchantid=im.id "
						+ merno
						+ " "
						+ tmp
						+ tmp2
						//+ " group by to_char(t.tradetime,'YYYY-MM-dd')) aa");
						+ " group by ic.shippingCountry) aa");
		sb
				.append(" left join (select ic.shippingCountry bbcountry,sum(t.tradeamount) tradeamount ,count(*)  tradecount from international_tradeinfo t,international_merchant im,international_cardholdersinfo ic where t.merchantid=im.id  and substr(t.tradestate,1,1)='0'"
						+ merno
						+ " "
						+ tmp
						+ tmp2
						+ " group by ic.shippingCountry) bb  on aa.aacountry=bb.bbcountry");
		sb
				.append(" left join (select  ic.shippingCountry cccountry,sum(t.tradeamount) tradeamount ,count(*)  tradecount from international_tradeinfo t,international_merchant im,international_cardholdersinfo ic where t.merchantid=im.id and substr(t.tradestate,1,1)='1'"
						+ merno
						+ " "
						+ tmp
						+ tmp2
						+ " group by ic.shippingCountry) cc  on aa.aacountry=cc.cccountry");
		sb
				.append(" left join (select ic.shippingCountry ddcountry,sum(t.tradeamount) tradeamount ,count(*)  tradecount from international_tradeinfo t,international_merchant im,international_cardholdersinfo ic where t.merchantid=im.id and substr(t.tradestate,3,1)='1'"
						+ merno
						+ " "
						+ tmp
						+ tmp2
						+ " group by ic.shippingCountry) dd  on aa.aacountry=dd.ddcountry");
		sb
				.append(" left join (select ic.shippingCountry eecountry,sum(t.tradeamount) tradeamount ,count(*)  tradecount from international_tradeinfo t,international_merchant im,international_cardholdersinfo ic where t.merchantid=im.id and (substr(t.tradestate,2,1)='1' or substr(t.tradestate,2,1)='2')"
						+ merno
						+ " "
						+ tmp
						+ tmp2
						+ " group by ic.shippingCountry) ee  on aa.aacountry=ee.eecountry order by ddtradecount desc");
			//
		

		tradeFourthList = commonService.getByList(sb.toString());
		
		jsonTradeFourthList = JSONArray.fromObject(tradeFourthList).toString();
		return SUCCESS;
	}	
	
	
	//各网站的拒付
	public String tradeinfoFifth() {
		String merno = "";
		String tmp = "";
		String tmp2 = "and t.id = ic.tradeid";

		if (merchant.getMerno() != null) {
			merno = " and im.merno='" + merchant.getMerno() + "'";

		}
	
		if (StringUtils.isNotBlank(startDate)) { // 开始日期
			tmp += " and t.tradetime>=to_date('" + startDate
					+ "','yyyy-MM-dd hh24:mi:ss')";
		}
		if (StringUtils.isNotBlank(endDate)) { // 结束日期
			tmp += " and t.tradetime<=to_date('" + endDate
					+ " 23:59:59','yyyy-MM-dd hh24:mi:ss') ";
		}
		if (StringUtils.isBlank(startDate) && StringUtils.isBlank(endDate)) {
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DAY_OF_MONTH, -30);
			tmp = " and t.tradetime>=to_date('" + c.getTime().toLocaleString()
					+ "','yyyy-MM-dd hh24:mi:ss') "
					+ "and t.tradetime<=sysdate ";
		}
		
		
		//下列是查询各个网站拒付
				StringBuffer sb2 = new StringBuffer();
				sb2						//网站                       // 总金额                                                        //  总交易条数                                    //失败交易金额                                        //失败交易条数                                      //成功交易金额                                        //成功交易条数				//拒付金额                                             //拒付条数                                              //已退款和部分退款金额                  //已退款和部分退款条数   
						.append("select aa.aatradeurl,aa.tradeamount aatradeamount,aa.tradecount aatradecount,bb.tradeamount bbtradeamount,bb.tradecount bbtradecount,cc.tradeamount cctradeamount,cc.tradecount cctradecount,dd.tradeamount ddtradeamount,coalesce(dd.tradecount,0) ddtradecount, ee.tradeamount eetradeamount,ee.tradecount eetradecount");
				sb2
						.append(" from (select t.tradeurl aatradeurl,sum(t.tradeamount) tradeamount ,count(*) tradecount from international_tradeinfo t,international_merchant im where t.merchantid=im.id "
								+ merno
								+ " "
								+ tmp
								+ " group by t.tradeurl) aa");
				sb2
						.append(" left join (select t.tradeurl bbtradeurl,sum(t.tradeamount) tradeamount ,count(*)  tradecount from international_tradeinfo t,international_merchant im where t.merchantid=im.id  and substr(t.tradestate,1,1)='0'"
								+ merno
								+ " "
								+ tmp
								+ " group by t.tradeurl) bb  on aa.aatradeurl=bb.bbtradeurl");
				sb2
						.append(" left join (select t.tradeurl cctradeurl,sum(t.tradeamount) tradeamount ,count(*)  tradecount from international_tradeinfo t,international_merchant im where t.merchantid=im.id and substr(t.tradestate,1,1)='1'"
								+ merno
								+ " "
								+ tmp
								+ " group by t.tradeurl) cc  on aa.aatradeurl=cc.cctradeurl");
				sb2
						.append(" left join (select t.tradeurl ddtradeurl,sum(t.tradeamount) tradeamount ,count(*)  tradecount from international_tradeinfo t,international_merchant im where t.merchantid=im.id and substr(t.tradestate,3,1)='1'"
								+ merno
								+ " "
								+ tmp
								+ " group by t.tradeurl) dd  on aa.aatradeurl=dd.ddtradeurl");
				sb2
						.append(" left join (select  t.tradeurl eetradeurl,sum(t.tradeamount) tradeamount ,count(*)  tradecount from international_tradeinfo t,international_merchant im where t.merchantid=im.id and (substr(t.tradestate,2,1)='1' or substr(t.tradestate,2,1)='2')"
								+ merno
								+ " "
								+ tmp
								+ " group by t.tradeurl) ee  on aa.aatradeurl=ee.eetradeurl order by ddtradecount desc");
		
		
		tradeFourthListurl = commonService.getByList(sb2.toString());
		
		jsonTradeFourthListurl = JSONArray.fromObject(tradeFourthListurl).toString();
		
		return SUCCESS;
	}	
	
	
	
	public PageInfo getInfo() {
		return info;
	}

	public void setInfo(PageInfo info) {
		this.info = info;
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

	public List<Long> getMerchantNoList() {
		return merchantNoList;
	}

	public void setMerchantNoList(List<Long> merchantNoList) {
		this.merchantNoList = merchantNoList;
	}

	public InternationalMerchant getMerchant() {
		return merchant;
	}

	public void setMerchant(InternationalMerchant merchant) {
		this.merchant = merchant;
	}

	public List getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(List totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List getTradestateAmount() {
		return tradestateAmount;
	}

	public void setTradestateAmount(List tradestateAmount) {
		this.tradestateAmount = tradestateAmount;
	}

	public List getDrawbackAmount() {
		return drawbackAmount;
	}

	public void setDrawbackAmount(List drawbackAmount) {
		this.drawbackAmount = drawbackAmount;
	}

	public List getProtestAmount() {
		return protestAmount;
	}

	public void setProtestAmount(List protestAmount) {
		this.protestAmount = protestAmount;
	}

	public List getFreezeAmount() {
		return freezeAmount;
	}

	public void setFreezeAmount(List freezeAmount) {
		this.freezeAmount = freezeAmount;
	}

	public List getBlendAmount() {
		return blendAmount;
	}

	public void setBlendAmount(List blendAmount) {
		this.blendAmount = blendAmount;
	}

	public List getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(List applyAmount) {
		this.applyAmount = applyAmount;
	}

	public List getAuditAmount() {
		return auditAmount;
	}

	public void setAuditAmount(List auditAmount) {
		this.auditAmount = auditAmount;
	}

	public List getTransfermoneyAmount() {
		return transfermoneyAmount;
	}

	public void setTransfermoneyAmount(List transfermoneyAmount) {
		this.transfermoneyAmount = transfermoneyAmount;
	}

	public List getBailauditAmount() {
		return bailauditAmount;
	}

	public void setBailauditAmount(List bailauditAmount) {
		this.bailauditAmount = bailauditAmount;
	}

	public List getBailtransfermoneyAmount() {
		return bailtransfermoneyAmount;
	}

	public void setBailtransfermoneyAmount(List bailtransfermoneyAmount) {
		this.bailtransfermoneyAmount = bailtransfermoneyAmount;
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

	public InternationalTradeinfo getTradeinfo() {
		return tradeinfo;
	}

	public void setTradeinfo(InternationalTradeinfo tradeinfo) {
		this.tradeinfo = tradeinfo;
	}

	public List getTradeList() {
		return tradeList;
	}

	public void setTradeList(List tradeList) {
		this.tradeList = tradeList;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public List getTradeSecondList() {
		return tradeSecondList;
	}

	public void setTradeSecondList(List tradeSecondList) {
		this.tradeSecondList = tradeSecondList;
	}

	public List<String> getTerminalList() {
		return terminalList;
	}

	public void setTerminalList(List<String> terminalList) {
		this.terminalList = terminalList;
	}

	public List getTradeThirdList() {
		return tradeThirdList;
	}

	public void setTradeThirdList(List tradeThirdList) {
		this.tradeThirdList = tradeThirdList;
	}

	public CalcuLate getCaclulate() {
		return caclulate;
	}

	public void setCaclulate(CalcuLate caclulate) {
		this.caclulate = caclulate;
	}

	public List getTradeFourthList() {
		return tradeFourthList;
	}

	public void setTradeFourthList(List tradeFourthList) {
		this.tradeFourthList = tradeFourthList;
	}

	public String getJsonTradeFourthList() {
		return jsonTradeFourthList;
	}

	public void setJsonTradeFourthList(String jsonTradeFourthList) {
		this.jsonTradeFourthList = jsonTradeFourthList;
	}

	public List getTradeFourthListurl() {
		return tradeFourthListurl;
	}

	public void setTradeFourthListurl(List tradeFourthListurl) {
		this.tradeFourthListurl = tradeFourthListurl;
	}

	public String getJsonTradeFourthListurl() {
		return jsonTradeFourthListurl;
	}

	public void setJsonTradeFourthListurl(String jsonTradeFourthListurl) {
		this.jsonTradeFourthListurl = jsonTradeFourthListurl;
	}

}
