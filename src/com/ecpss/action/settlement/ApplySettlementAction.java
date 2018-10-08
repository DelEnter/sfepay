package com.ecpss.action.settlement;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ecpss.action.BaseAction;
import com.ecpss.action.pay.TradeManager;
import com.ecpss.action.tradestatistics.TemporaryTradInfoAction;
import com.ecpss.model.channel.InternationalChannels;
import com.ecpss.model.channel.InternationalMerchantChannels;
import com.ecpss.model.payment.InternationalSettlment;
import com.ecpss.model.payment.InternationalTradeinfo;
import com.ecpss.model.payment.ViewSettlement;
import com.ecpss.model.shop.InternationalExchangerate;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.model.shop.InternationalMerchantManager;
import com.ecpss.model.shop.InternationalMoneykindname;
import com.ecpss.service.iservice.PermissionsService;
import com.ecpss.tools.TableExport;
import com.ecpss.tools.TableExportFactory;
import com.ecpss.util.DownloadUtils;
import com.ecpss.util.GetBatchNo;
import com.ecpss.vo.InternationalHuakuan;
import com.ecpss.web.PageInfo;
import com.opensymphony.xwork2.ActionContext;


public class ApplySettlementAction extends BaseAction {
	// = new PageInfo();
	Logger logger = Logger.getLogger(ApplySettlementAction.class.getName());
	private String showMessage;

	@Autowired
	@Qualifier("tradeManager")
	private TradeManager tradeManager;
	private Double tradeMoney;
	private Double rmbmoney = 0d;
	@Autowired
	@Qualifier("permissionsService")
	private PermissionsService permissionsService;
	private Long batchNo;
	private String typesname = "";
	private Long merchantId;
	private Long[] id;
	private List tradeList;
	private List tradeList2;
	private List tradeList3;
	private Long merno;
	private InternationalMerchant tradeinfo = new InternationalMerchant();
	private Object[] freezeId;// 选中的ID
	private Object[] bankName;// 银行名称
	private Object[] depict = new Object[] {};// 备注
	private String freezdIds;// 选择的预划款选项
	private int totPage;
	private String remark;
	private String orderdno = "";
	private String kuaidi;
	private String startDate;
	private String endDate;
	private List<Long> merchantNoList;
	private String accountname;
	private String isProtest;
	private Double riskRmbmoney = 0d;
	private Double channelFee = 0d;
	public List listName;
	private PageInfo info;
	

	// 跳转到替客户申请划款
	// 正常划款状态：1 [02] 0 [02] 1 1 1 0 0 0 0 符合结算周期 上传跟踪单号 上传图片 1_0_1110000%
	// 1[02]0[02]0000000%
	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	public String getKuaidi() {
		return kuaidi;
	}

	public void setKuaidi(String kuaidi) {
		this.kuaidi = kuaidi;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getTotPage() {
		return totPage;
	}

	public void setTotPage(int totPage) {
		this.totPage = totPage;
	}

	public Object[] getFreezeId() {
		return freezeId;
	}



	public void setFreezeId(Object[] freezeId) {
		this.freezeId = freezeId;
	}


	public PageInfo getInfo() {
		return info;
	}

	public void setInfo(PageInfo info) {
		this.info = info;
	}
	
	
	public String toSettlement() {
		String temID = "";
		if (this.freezeId != null) {
			if (this.freezeId.length > 0) {
				StringBuffer sbb = new StringBuffer();
				for (int i = 0; i < freezeId.length; i++) {

					// if(tems[i]==null) continue;

					if (i == (freezeId.length - 1))
						sbb.append("'" + freezeId[i] + "'"); // SQL拼装，最后一条不加“,”。
					else if ((i % 500) == 0 && i > 0)
						sbb.append("'" + freezeId[i] + "' ) OR a.id IN ( "); // 解决ORA-01795问题
					else
						sbb.append("'" + freezeId[i] + "', ");

				}

				temID = sbb.toString();

				this.commonService
						.deleteBySql("update international_tradeinfo a set  a.tradestate=substr(a.tradestate,1,5)||'1'||substr(a.tradestate,7, length(a.tradestate)-6),a.lastMan='"+getUserBean().getUserName()+"',a.applyTime=sysdate where a.id in("
								+ temID + ")");

			}

		}
		this.tradeinfo.setMerno(this.tradeinfo.getMerno());

		StringBuffer sb = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		if ((this.tradeinfo.getMerno() != null)) {
			if (this.tradeinfo.getMerno() != 0) {
				int cycleTime = 0;
				cycleTime = this.tradeManager
						.intBySql("select min(t.cycletime)-to_char(sysdate,'dd') from settlementcycletime t ,International_Merchant f where  t.cycletime>=to_char(sysdate,'dd') and t.merchant=f.id and f.merno='"
								+ this.tradeinfo.getMerno() + "'");
				if (cycleTime == -1) {
					cycleTime = this.tradeManager
							.intBySql(" select  trunc(LAST_DAY(sysdate),'dd')-trunc(sysdate,'dd')+min(t.cycletime) from  settlementcycletime t  ,International_Merchant f where  t.merchant=f.id and f.merno='"
									+ this.tradeinfo.getMerno() + "'");
				}
				sb
						.append(" select t,f from InternationalTradeinfo t , InternationalMerchant f,  InternationalMerchantChannels g where g.id=t.tradeChannel and t.merchantId=f.id and t.tradeState like '1_0__000000%' ");
				sb.append(" and substr(t.tradeState,2,1)in(0,2) ");
				sb.append(" and substr(t.tradeState,4,1)in(0,2) ");
				//sb
						//.append(" and ( t.isTrackNo is not null or t.isPicture is not null) ");
				sb
						.append(" and  trunc(sysdate,'dd')-trunc(t.tradeTime)>=(g.balanceCycle-"
								+ cycleTime + ")");
				sb.append(" and  f.merno='" + this.tradeinfo.getMerno() + "'");
				if (!this.orderdno.equals("")) {
					sb.append("and t.orderNo='" + this.orderdno.trim() + "'");
				}
				this.tradeList = this.commonService.list(sb.toString());
				//info = commonService.listQueryResultByHql(sb.toString(), info);
				// sb2.append(" select sum(t.tradeAmount) from
				// InternationalTradeinfo t , InternationalMerchant f ,
				// InternationalMerchantChannels g where g.id=t.tradeChannel and
				// t.merchantId=f.id and t.tradeState like '1_0_0000000%' ");
				// sb2.append(" and substr(t.tradeState,2,1)in(0,2) ") ;
				// sb2.append(" and substr(t.tradeState,4,1)in(0,2) ") ;
				// sb2.append(" and ( t.isTrackNo is not null or t.isPicture is
				// not null) ");
				// sb2.append(" and
				// trunc(sysdate,'dd')-trunc(t.tradeTime)>=(g.balanceCycle-"+cycleTime+")");
				// sb2.append(" and f.merno='"+ this.tradeinfo.getMerno() +
				// "'");
				// //System.out.println("sb.toString()-----------------"+sb.toString());
				// //System.out.println("sb2.toString()----------"+sb2.toString());
				// this.tradeMoney=(Double)this.commonService.uniqueResult(sb2.toString());
			}
		}
		// else{
		// sb.append(" select t,f from InternationalTradeinfo t ,
		// InternationalMerchant f, InternationalMerchantChannels g where
		// g.id=t.tradeChannel and t.merchantId=f.id and t.tradeState like
		// '1_0_0000000%' ");
		// sb.append(" and substr(t.tradeState,2,1)in(0,2) ") ;
		// sb.append(" and substr(t.tradeState,4,1)in(0,2) ") ;
		// sb.append(" and ( t.isTrackNo <> null or t.isPicture <> null) ");
		// sb.append(" and
		// trunc(sysdate,'dd')-trunc(t.tradeTime)>=(g.balanceCycle-"+cycleTime+")");
		// this.tradeList = this.commonService.list(sb.toString());
		// sb2
		// .append(" select sum(t.tradeAmount) from InternationalTradeinfo t ,
		// InternationalMerchant f , InternationalMerchantChannels g where
		// g.id=t.tradeChannel and t.merchantId=f.id and t.tradeState like
		// '1_0_0000000%' ");
		// sb2.append(" and substr(t.tradeState,2,1)in(0,2) ") ;
		// sb2.append(" and substr(t.tradeState,4,1)in(0,2) ") ;
		// sb2.append(" and ( t.isTrackNo is not null or t.isPicture is not
		// null) ");
		// sb2.append(" and
		// trunc(sysdate,'dd')-trunc(t.tradeTime)>=(g.balanceCycle-"+cycleTime+")");
		//		
		// this.tradeMoney=(Double)this.commonService.uniqueResult(sb2.toString());
		// }

		return "success";
	}

	// 替客户申请划款
	public String applySettlement() {
		String temID = "";
		for (int i = 0; i < freezeId.length; i++) {

			Long id = Long.valueOf((String) freezeId[i]);
			temID = temID + id + ',';
			System.out.println("================================" + id + "/n");
		}
		temID = temID.substring(0, temID.length() - 1);
		this.commonService
				.deleteBySql("update international_tradeinfo a set a.tradestate=substr(a.tradestate,1,5)||'1'||substr(a.tradestate,7, length(a.tradestate)-6) where a.id in("
						+ temID + ")");
		this.messageAction = "申请成功";
		return this.OPERATE_SUCCESS;
	}

	// 审核管理列表
	public String toAuditTrade() {
		// 审核通过
		String temID = "";
		if (this.id != null) {
			if (this.id.length > 0) {
				for (int i = 0; i < id.length; i++) {
					temID = temID + id[i] + ',';
				}

				temID = temID.substring(0, temID.length() - 1);
				// 1 代表审核通过 2 代表审核不通过
				if (this.typesname.equals("1")) {
					this.commonService
							.deleteBySql("update international_tradeinfo a set a.tradestate=substr(a.tradestate,1,6)||'1'||substr(a.tradestate,8, length(a.tradestate)-7),a.auditingDate=sysdate where a.id in("
									+ temID + ")");
				} else if (this.typesname.equals("2")) {
					this.commonService
							.deleteBySql("update international_tradeinfo a set a.matterDepict='"
									+ this.remark
									+ "' , a.tradestate=substr(a.tradestate,1,6)||'2'||substr(a.tradestate,8, length(a.tradestate)-7) ,a.auditingDate=sysdate where a.id in("
									+ temID + ")");

				}

			}

		}
		if (this.info == null) {
			this.info = new PageInfo();
		}
		this.tradeinfo.setMerno(this.tradeinfo.getMerno());
		StringBuffer sb = new StringBuffer();
		sb
				.append(" select t,f,m from InternationalTradeinfo t , InternationalCardholdersInfo f,InternationalMerchant m  where m.id=t.merchantId and substr(t.tradeState,7,1)='0' and substr(t.tradeState,6,1)='1' and f.tradeId=t.id");
		if ((this.tradeinfo.getMerno() != null)) {
			if (this.tradeinfo.getMerno() != 0) {

				sb.append(" and  m.merno='" + this.tradeinfo.getMerno() + "'");
				// this.tradeList = this.commonService.list(sb.toString());

			}
		}
		if (StringUtils.isNotBlank(this.orderdno)) {
			sb.append(" and t.orderNo='" + this.orderdno.trim() + "'");
		}
		if (StringUtils.isNotBlank(kuaidi)) {
			sb.append(" and t.isTrackNo like '" + kuaidi.trim() + "%' ");
		}
		this.info.setPageSize(10);
		this.info = this.commonService
				.listQueryResultByHql(sb.toString(), info);
		return "success";

	}

	// 查看问题单列表
	public String toTroubleList() {
		String temID = "";
		if (this.id != null) {
			if (this.id.length > 0) {
				for (int i = 0; i < id.length; i++) {
					temID = temID + id[i] + ',';
				}

				temID = temID.substring(0, temID.length() - 1);

				this.commonService
						.deleteBySql("update international_tradeinfo a set a.lastDate=sysdate,a.lastMan='"
								+ this.getUserBean().getUserName()
								+ "', a.tradestate=substr(a.tradestate,1,6)||'1'||substr(a.tradestate,8, length(a.tradestate)-7) where a.id in("
								+ temID + ")");

			}

		}

		if (this.info == null) {
			this.info = new PageInfo();
		}
		this.tradeinfo.setMerno(this.tradeinfo.getMerno());
		StringBuffer sb = new StringBuffer();
		sb
				.append(" select t,f,m from InternationalTradeinfo t , InternationalCardholdersInfo f,InternationalMerchant m  where m.id=t.merchantId and substr(t.tradeState,7,1)='2' and f.tradeId=t.id");
		if ((this.tradeinfo.getMerno() != null)) {
			if (this.tradeinfo.getMerno() != 0) {

				sb.append("  and  m.merno='" + this.tradeinfo.getMerno() + "'");
				// this.tradeList = this.commonService.list(sb.toString());

			}
		}
		// 流水号
		if (StringUtils.isNotBlank(this.orderdno)) {
			sb.append(" and t.orderNo='" + this.orderdno.trim() + "'");
		}
		// 问题单类型
		if (StringUtils.isNotBlank(this.remark)) {
			sb.append(" and t.matterDepict='" + this.remark.trim() + "'");
		}
		if(StringUtils.isNotBlank(isProtest)){   //拒付情况
			sb.append(" and substr(t.tradeState,3,1)='"+isProtest+"' ");
		}
		sb.append(" order by t.tradeTime");
		this.info = this.commonService
				.listQueryResultByHql(sb.toString(), info);
		return this.SUCCESS;
	}

	/*
	 * select distinct f.merno from International_Tradeinfo t,
	 * International_Merchant f where substr(t.tradeState,7,2)='10' and
	 * t.merchantId=f.id and f.id not in(select g.merchantno from
	 * InternationalSettlment g where g.istrue='0') and f.id not in( select
	 * g.merchantno from internationalsettlment g where
	 * trunc(sysdate,'dd')-trunc(g.createtabletime,'dd')>=-3 and
	 * trunc(sysdate,'dd')-trunc(g.createtabletime,'dd')<=3 ) and f.id in(
	 * select ib.merchant from SettlementCycleTime ib where
	 * trunc(sysdate,'dd')-trunc(to_date(to_char(sysdate,'yyyymm')||ib.cycletime,'yyyymmdd'),'dd')<=3
	 * and
	 * trunc(sysdate,'dd')-trunc(to_date(to_char(sysdate,'yyyymm')||ib.cycletime,'yyyymmdd'),'dd')>=-3 )
	 * 
	 */
	/*
	 * select distinct f.merno from International_Merchant f where f.id not
	 * in(select g.merchantno from InternationalSettlment g where g.istrue='0')
	 * and f.id not in( select g.merchantno from InternationalSettlment g where
	 * trunc(sysdate,'dd')-trunc(g.createtabletime,'dd')>=-3 and
	 * trunc(sysdate,'dd')-trunc(g.createtabletime,'dd')<=3 ) and ( f.id in(
	 * select ib.merchant from SettlementCycleTime ib where
	 * trunc(sysdate,'dd')-trunc(to_date(to_char(sysdate,'yyyymm')||ib.cycleTime,'yyyymmdd'),'dd')<=3
	 * and
	 * trunc(sysdate,'dd')-trunc(to_date(to_char(sysdate,'yyyymm')||ib.cycleTime,'yyyymmdd'),'dd')>=-3 )
	 * or f.id in( select ib.merchant from SettlementCycleTime ib where
	 * trunc(to_date(to_char(sysdate,'yyyy')||to_char(sysdate,'mm')+1||ib.cycleTime,'yyyymmdd'),'dd')-trunc(sysdate,'dd')<=3
	 * and
	 * trunc(to_date(to_char(sysdate,'yyyy')||to_char(sysdate,'mm')+1||ib.cycleTime,'yyyymmdd'),'dd')-trunc(sysdate,'dd')>=-3 )
	 * or f.id in( select ib.merchant from SettlementCycleTime ib where
	 * trunc(sysdate,'dd')-trunc(to_date(to_char(sysdate,'yyyy')||to_char(sysdate,'mm')-1||ib.cycleTime,'yyyymmdd'),'dd')<=3
	 * and trunc(sysdate,'dd')-
	 * trunc(to_date(to_char(sysdate,'yyyy')||to_char(sysdate,'mm')+1||ib.cycleTime,'yyyymmdd'),'dd')>=-3 ) )
	 * 
	 * 
	 */

	// 预划款列表
	public String viewTransferMoney() {
//		Calendar calendar = Calendar.getInstance();// 此时打印它获取的是系统当前时间
//		calendar.add(Calendar.DATE, -7); // 得到前一天
//		String yestedayDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//				.format(calendar.getTime());
//		
		if(tradeinfo.getMerno()!=null){
			merchantNoList = (List<Long>) ActionContext.getContext().getSession().get("merchantNoList");
		}else{
			this.merchantNoList = this.commonService
			.list("select distinct f.merno from InternationalMerchant f ,InternationalTradeinfo t where substr(t.tradeState,7,2)='10'  and t.merchantId=f.id order by f.merno asc");
			
			ActionContext.getContext().getSession().put("merchantNoList", this.merchantNoList);
			
		}

		if ((this.tradeinfo.getMerno() != null) && (this.freezeId == null)) {
			int temint = 0;
			temint = this.tradeManager
					.intBySql("select count(1) from internationalsettlment t ,international_merchant f where t.merchantno=f.id and t.istrue='0' and f.merno='"
							+ this.tradeinfo.getMerno() + "'");
			if (temint > 0) {
				this.showMessage = "商户:" + this.getTradeinfo().getMerno()
						+ "已进入划款流程";
				this.getTradeinfo().setMerno(null);
				return this.SUCCESS;
			}
		}
		String temID = "";
		if (this.freezeId != null) {
			if (this.freezeId.length > 0) {
				for (int i = 0; i < freezeId.length; i++) {
					temID = temID + freezeId[i] + ',';
				}

				temID = temID.substring(0, temID.length() - 1);

				this.commonService
						.deleteBySql("update international_tradeinfo a set a.tradestate=substr(a.tradestate,1,6)||'2'||substr(a.tradestate,8, length(a.tradestate)-7) where a.id in("
								+ temID + ")");

			}

		}
		// 正常划款状态：1 [02] 0 [02] 1 1 1 0 0 0 0 符合结算周期 上传跟踪单号 上传图片 1_0_1110000%

		if ((this.tradeinfo.getMerno() != null)) {
			StringBuffer sb = new StringBuffer();
			StringBuffer sb2 = new StringBuffer();
			if (this.tradeinfo.getMerno() != 0) {

				if ((this.tradeinfo.getMerno() != null)) {
					if (this.tradeinfo.getMerno() != 0) {
						sb.append(" select t.orderNo,t.tradeAmount,to_char(t.tradeTime,'yyyy-MM-dd hh24:mi:ss'),to_char(t.auditingDate,'yyyy-MM-dd hh24:mi:ss'),t.tradeState,t.backCount,t.isTrackNo,t.remark,t.id " +
								" from International_Tradeinfo t , International_MerchantChannels ic " +
								"where t.tradeState like '1_0___10000%' " +
								"and substr(t.tradeState,4,1) in (0,2) " +
								"and substr(t.tradeState,2,1) in (0,2) ");
						sb.append(" and t.merchantId=(select f.id from International_Merchant f where f.merno='"
										+ this.tradeinfo.getMerno() + "')");
						sb.append(" and t.tradeChannel=ic.id and  (sysdate-t.tradeTime)>=ic.balanceCycle ");
						if (StringUtils.isNotBlank(this.orderdno)) {
							sb.append(" and t.orderNo='" + this.orderdno.trim()
									+ "'");
						}
						sb.append(" order by t.id desc  ");
					}
				}
			}
			this.tradeList = this.commonService.getByList(sb.toString());

			String sql2 = "select f.merchantorderno , f.orderno,f.tradeamount,f.tradetime,f.tradestate,f.istrackno,f.remark , e.tradeType "
					+ "from  huakuanedexception e left"
					+ " join international_tradeinfo f "
					+ "on e.tradeid=f.id "
					+ "where f.merchantid=(select g.id from INTERNATIONAL_MERCHANT g "
					+ "where g.merno='" + this.tradeinfo.getMerno() + "')";

			this.tradeList2 = this.commonService.getByList(sql2);
			String sql3 = " from InternationalTradeinfo t where (substr(t.tradeState,2,1)='1' and (substr(t.tradeState,8,1)='0') or (substr(t.tradeState,3,1)='1' ) and substr(t.tradeState,8,1)='0') and t.merchantId=(select f.id from InternationalMerchant f where f.merno='"
					+ this.tradeinfo.getMerno() + "')";
			this.tradeList3 = this.commonService.list(sql3);
		}
		return this.SUCCESS;
	}

	// 预览划款总表
	public String viewTransferAcount() {
		String temID = "";
		String[] tems = null;
		if (this.freezdIds != null) {
			tems = freezdIds.split(",");

		}
		StringBuffer sbb = new StringBuffer();
		for (int i = 0; i < tems.length; i++) {

			// if(tems[i]==null) continue;

			// 这里不要犯低级错误而写成：if(i == custNOs.length)
			if (i == (tems.length - 1))
				sbb.append("'" + tems[i] + "'"); // SQL拼装，最后一条不加“,”。
			else if ((i % 500) == 0 && i > 0)
				sbb.append("'" + tems[i] + "' ) OR a.id IN ( "); // 解决ORA-01795问题
			else
				sbb.append("'" + tems[i] + "', ");

		}
        Long begin=System.currentTimeMillis();
		temID = sbb.toString();
		this.tradeinfo = (InternationalMerchant) this.commonService
				.list(
						" from InternationalMerchant t where t.id=(select f.merchantId from InternationalTradeinfo f where f.id='"
								+ tems[0] + "')").get(0);
		// 获取批次号
		batchNo = Long.valueOf(new GetBatchNo().getBatchNo());
		// 正常划款的单子a
		String mormalSQL = " select r.id,count(*) , sum(a.tradeamount) ,a.tradeChannel from international_tradeinfo a ,international_exchangerate r where a.merchantId='"+tradeinfo.getId()+"' and ( a.id in ("
				+ temID
				+ ") ) and r.id=a.balancerate and substr(a.tradestate,2,1)in (0,2) group by r.id  ,a.tradeChannel";
		List mormal = this.commonService.getByList(mormalSQL);
		System.out.println("============1========="+(System.currentTimeMillis()-begin)+"\n");
		// 未划款拒付b
		String refuse1 = " select r.id,count(*),sum(inf.tradeamount) ,inf.tradeChannel from international_tradeinfo inf ,international_exchangerate r where inf.merchantid='"
				+ tradeinfo.getId()
				+ "' and substr(inf.tradestate,3,1)='1' and substr(inf.tradestate,8,1)='0' and r.id=inf.balancerate group by r.id ,inf.tradeChannel";
		List refulseList1 = this.commonService.getByList(refuse1);
		System.out.println("============2========="+(System.currentTimeMillis()-begin)+"\n");

		// 未划款全部退款c
		String reback1 = " select r.id,count(*),sum(inf.tradeamount) ,inf.tradeChannel from international_tradeinfo inf ,international_exchangerate r where inf.merchantid='"
				+ tradeinfo.getId()
				+ "' and substr(inf.tradestate,2,1)='1' and substr(inf.tradestate,8,1)='0' and r.id=inf.balancerate group by r.id ,inf.tradeChannel";

		List rebackList1 = this.commonService.getByList(reback1);
		System.out.println("============3========="+(System.currentTimeMillis()-begin)+"\n");

		// 未划款部分退款d
		String reback2 = " select r.id,count(*) , sum(a.backcount),a.tradeChannel from international_tradeinfo a ,international_exchangerate r where a.merchantId='" + tradeinfo.getId() + "' and (a.id in (" 
				+ temID
				+ ") ) and r.id=a.balancerate and substr(a.tradestate,2,1)='2' group by r.id ,a.tradeChannel ";
		List rebackList2 = this.commonService.getByList(reback2);
		System.out.println("============4========="+(System.currentTimeMillis()-begin)+"\n");

		// 已划款部分退款，e 1. 结算汇率 2 总条数 3.总金额 4.退款金额 5.类型. 6 商户通道
		String reback3 = " select a.balancerate, count(*),sum(a.tradeamount),sum(a.backcount), e.tradetype ,a.tradeChannel from huakuanedexception e left join international_tradeinfo a on e.tradeid=a.id where a.merchantid='" 
				+ tradeinfo.getId()
				+ "'   group by e.tradetype ,a.balancerate,a.tradeChannel ";
		List rebackList3 = this.commonService.getByList(reback3);
		System.out.println("============5========="+(System.currentTimeMillis()-begin)+"\n");

		// 未划款退款拒付 f
		String reback4 ="select r.id,count(*),sum(inf.backcount),inf.tradeChannel,sum(inf.tradeamount) from international_tradeinfo inf ,international_exchangerate r where inf.merchantid='"
				+ tradeinfo.getId()
				+ "' and substr(inf.tradestate,2,1) in(1,2) and substr(inf.tradestate,8,1)='0' and substr(inf.tradestate,3,1)='1' and r.id=inf.balancerate group by r.id ,inf.tradeChannel";
		List rebackList4 = this.commonService.getByList(reback4);
		System.out.println("============6========="+(System.currentTimeMillis()-begin)+"\n");

		Map temMap = new HashMap();

		// 已划款退款拒付 
		String reback5 = " select a.balancerate, count(*),sum(a.tradeamount),sum(a.backcount) ,a.tradeChannel from huakuanedexception e left join international_tradeinfo a on e.tradeid=a.id where a.merchantid='"
				+ tradeinfo.getId()
				+ "'   and e.tradeid in(select g.tradeid from  huakuanedexception g where 1=1 group by g.tradeid having count(g.tradeid)=2) group by a.balancerate,a.tradeChannel";
		List rebackList5 = this.commonService.getByList(reback5);
		System.out.println("============7========="+(System.currentTimeMillis()-begin)+"\n");

		// 未划款解冻交易 h
		String refree = " select r.id,count(*),sum(inf.tradeamount) ,inf.tradeChannel from international_tradeinfo inf ,international_exchangerate r where inf.merchantid='" 
				+ tradeinfo.getId()
				+ "' and substr(inf.tradestate,4,1)='2' and substr(inf.tradestate,8,1)='0' and r.id=inf.balancerate group by r.id ,inf.tradeChannel";
		List refreList = this.commonService.getByList(refree);
		System.out.println("============8========="+(System.currentTimeMillis()-begin)+"\n");
		
		// 正常划款的单子a
		for (int a = 0; a < mormal.size(); a++) {
			Object[] tem = (Object[]) mormal.get(a);
			if (temMap.containsKey(tem[0].toString() + "-" + tem[3].toString())) {
				InternationalHuakuan ih = (InternationalHuakuan) temMap
						.get(tem[0].toString() + "-" + tem[3].toString());
				ih.setTradeNumber(Integer.valueOf(tem[1].toString()));
				ih.setTradeMoney(Double.valueOf(tem[2].toString()));
				temMap.put(tem[0].toString() + "-" + tem[3].toString(), ih);
			} else {
				InternationalHuakuan ih = new InternationalHuakuan();
				ih.setTradeNumber(Integer.valueOf(tem[1].toString()));
				ih.setTradeMoney(Double.valueOf(tem[2].toString()));
				this.initMerchantValue(Long.valueOf(tem[0].toString()), Long
						.valueOf(tem[3].toString()), ih);
				temMap.put(tem[0].toString() + "-" + tem[3].toString(), ih);

			}

		}
		// 未划款拒付b
		for (int b = 0; b < refulseList1.size(); b++) {
			Object[] tem = (Object[]) refulseList1.get(b);
			if (temMap.containsKey(tem[0].toString() + "-" + tem[3].toString())) {
				InternationalHuakuan ih = (InternationalHuakuan) temMap
						.get(tem[0].toString() + "-" + tem[3].toString());
				ih.setNoProtestNumber(Integer.valueOf(tem[1].toString()));
				ih.setNoProtestMoney(Double.valueOf(tem[2].toString()));
				temMap.put(tem[0].toString() + "-" + tem[3].toString(), ih);
			} else {
				InternationalHuakuan ih = new InternationalHuakuan();
				ih.setNoProtestNumber(Integer.valueOf(tem[1].toString()));
				ih.setNoProtestMoney(Double.valueOf(tem[2].toString()));
				this.initMerchantValue(Long.valueOf(tem[0].toString()), Long
						.valueOf(tem[3].toString()), ih);
				temMap.put(tem[0].toString() + "-" + tem[3].toString(), ih);
			}

		}
		// 未划款全部退款c
		for (int c = 0; c < rebackList1.size(); c++) {
			Object[] tem = (Object[]) rebackList1.get(c);
			if (temMap.containsKey(tem[0].toString() + "-" + tem[3].toString())) {
				InternationalHuakuan ih = (InternationalHuakuan) temMap
						.get(tem[0].toString() + "-" + tem[3].toString());
				ih.setNoTuikuanNumberall(Integer.valueOf(tem[1].toString()));
				ih.setNoTuiKuanMoneyall(Double.valueOf(tem[2].toString()));
				temMap.put(tem[0].toString() + "-" + tem[3].toString(), ih);
			} else {
				InternationalHuakuan ih = new InternationalHuakuan();
				ih.setNoTuikuanNumberall(Integer.valueOf(tem[1].toString()));
				ih.setNoTuiKuanMoneyall(Double.valueOf(tem[2].toString()));
				this.initMerchantValue(Long.valueOf(tem[0].toString()), Long
						.valueOf(tem[3].toString()), ih);
				temMap.put(tem[0].toString() + "-" + tem[3].toString(), ih);

			}

		}
		// 未划款部分退款d
		for (int d = 0; d < rebackList2.size(); d++) {
			Object[] tem = (Object[]) rebackList2.get(d);
			if (temMap.containsKey(tem[0].toString() + "-" + tem[3].toString())) {
				InternationalHuakuan ih = (InternationalHuakuan) temMap
						.get(tem[0].toString() + "-" + tem[3].toString());
				ih.setNoTuikuanNumber(Integer.valueOf(tem[1].toString()));
				ih.setNoTuiKuanMoney(Double.valueOf(tem[2].toString()));
				temMap.put(tem[0].toString() + "-" + tem[3].toString(), ih);
			} else {
				InternationalHuakuan ih = new InternationalHuakuan();
				ih.setNoTuikuanNumber(Integer.valueOf(tem[1].toString()));
				ih.setNoTuiKuanMoney(Double.valueOf(tem[2].toString()));
				this.initMerchantValue(Long.valueOf(tem[0].toString()), Long
						.valueOf(tem[3].toString()), ih);
				temMap.put(tem[0].toString() + "-" + tem[3].toString(), ih);

			}

		}
		// 已划款e 1. 结算汇率 2 总条数 3.总金额 4.退款金额 5.类型
	
		for (int e = 0; e < rebackList3.size(); e++) {
			Object[] tem = (Object[]) rebackList3.get(e);
			// 3.已划款拒付，4 已划款部分退款、5 已划款全部退款 6.已划款冻结。7，已划款解冻
			logger.info("测试有没有到3");
			System.out.println(tem[4]);//5
			logger.info(Integer.valueOf(tem[1].toString())+"");//1
			logger.info(Double.valueOf(tem[3].toString())+"");//89.96
			//logger.info("测试2");
			logger.info(Long.valueOf(tem[0].toString())+"");
			logger.info(Long.valueOf(tem[5].toString())+"");
			
			if (tem[4].toString().equals("3")) {
				
				if (temMap.containsKey(tem[0].toString() + "-"
						+ tem[5].toString())) {
					InternationalHuakuan ih = (InternationalHuakuan) temMap
							.get(tem[0].toString() + "-" + tem[5].toString());
					ih.setProtestNumber(Integer.valueOf(tem[1].toString()));
					ih.setProtestMoney(Double.valueOf(tem[2].toString()));
					temMap.put(tem[0].toString() + "-" + tem[5].toString(), ih);
				} else {
					InternationalHuakuan ih = new InternationalHuakuan();
					ih.setProtestNumber(Integer.valueOf(tem[1].toString()));
					ih.setProtestMoney(Double.valueOf(tem[2].toString()));
					this.initMerchantValue(Long.valueOf(tem[0].toString()),
							Long.valueOf(tem[5].toString()), ih);
					temMap.put(tem[0].toString() + "-" + tem[5].toString(), ih);

				}
			} else if (tem[4].toString().equals("4")) {

				if (temMap.containsKey(tem[0].toString() + "-"
						+ tem[5].toString())) {
					InternationalHuakuan ih = (InternationalHuakuan) temMap
							.get(tem[0].toString() + "-" + tem[5].toString());
					ih.setTuiKuanNumber(Integer.valueOf(tem[1].toString()));
					ih.setTuiKuanMoney(Double.valueOf(tem[3].toString()));
					temMap.put(tem[0].toString() + "-" + tem[5].toString(), ih);
				} else {					
					logger.info("测试1");
					InternationalHuakuan ih = new InternationalHuakuan();
					ih.setTuiKuanNumber(Integer.valueOf(tem[1].toString()));
					logger.info(Integer.valueOf(tem[1].toString())+"");
					ih.setTuiKuanMoney(Double.valueOf(tem[3].toString()));
					logger.info(Double.valueOf(tem[3].toString())+"");
					logger.info("测试2");
					logger.info(Long.valueOf(tem[0].toString())+"");
					logger.info(Long.valueOf(tem[5].toString())+"");
					
					this.initMerchantValue(Long.valueOf(tem[0].toString()),
							Long.valueOf(tem[5].toString()), ih);
					System.out.println("测试3");
					temMap.put(tem[0].toString() + "-" + tem[5].toString(), ih);
					
				}
			} else if (tem[4].toString().equals("5")) {

				if (temMap.containsKey(tem[0].toString() + "-"
						+ tem[5].toString())) {
					InternationalHuakuan ih = (InternationalHuakuan) temMap
							.get(tem[0].toString() + "-" + tem[5].toString());
					ih.setTuiKuanNumberall(Integer.valueOf(tem[1].toString()));
					ih.setTuiKuanMoneyall(Double.valueOf(tem[3].toString()));
					temMap.put(tem[0].toString() + "-" + tem[5].toString(), ih);
				} else {
					InternationalHuakuan ih = new InternationalHuakuan();
					ih.setTuiKuanNumberall(Integer.valueOf(tem[1].toString()));
					ih.setTuiKuanMoneyall(Double.valueOf(tem[3].toString()));
					this.initMerchantValue(Long.valueOf(tem[0].toString()),
							Long.valueOf(tem[5].toString()), ih);
					temMap.put(tem[0].toString() + "-" + tem[5].toString(), ih);

				}
			} else if (tem[4].toString().equals("6")) {

				if (temMap.containsKey(tem[0].toString() + "-"
						+ tem[5].toString())) {
					InternationalHuakuan ih = (InternationalHuakuan) temMap
							.get(tem[0].toString() + "-" + tem[5].toString());
					ih.setFreezeNumber(Integer.valueOf(tem[1].toString()));
					ih.setFreezeMoney(Double.valueOf(tem[2].toString()));
					temMap.put(tem[0].toString() + "-" + tem[5].toString(), ih);
				} else {
					InternationalHuakuan ih = new InternationalHuakuan();
					ih.setFreezeNumber(Integer.valueOf(tem[1].toString()));
					ih.setFreezeMoney(Double.valueOf(tem[2].toString()));
					this.initMerchantValue(Long.valueOf(tem[0].toString()),
							Long.valueOf(tem[5].toString()), ih);
					temMap.put(tem[0].toString() + "-" + tem[5].toString(), ih);

				}
			} else if (tem[4].toString().equals("7")) {

				if (temMap.containsKey(tem[0].toString() + "-"
						+ tem[5].toString())) {
					InternationalHuakuan ih = (InternationalHuakuan) temMap
							.get(tem[0].toString() + "-" + tem[5].toString());
					ih.setYesthawNumber(Integer.valueOf(tem[1].toString()));
					ih.setYesthawMoney(Double.valueOf(tem[2].toString()));
					temMap.put(tem[0].toString() + "-" + tem[5].toString(), ih);
				} else {
					InternationalHuakuan ih = new InternationalHuakuan();
					ih.setYesthawNumber(Integer.valueOf(tem[1].toString()));
					ih.setYesthawMoney(Double.valueOf(tem[2].toString()));
					this.initMerchantValue(Long.valueOf(tem[0].toString()),
							Long.valueOf(tem[5].toString()), ih);
					temMap.put(tem[0].toString() + "-" + tem[5].toString(), ih);

				}
			}

		}
		// 未付款退款拒付 f
		for (int f = 0; f < rebackList4.size(); f++) {
			Object[] tem = (Object[]) rebackList4.get(f);
			if (temMap.containsKey(tem[0].toString() + "-" + tem[3].toString())) {
				InternationalHuakuan ih = (InternationalHuakuan) temMap
						.get(tem[0].toString() + "-" + tem[3].toString());
				ih.setRefuce(Integer.valueOf(tem[1].toString()));
				ih.setRefuceMoneyAll(Double.valueOf(tem[4].toString()));
				ih.setRefuceMoney(Double.valueOf(tem[2].toString()));
				temMap.put(tem[0].toString() + "-" + tem[3].toString(), ih);
			} else {
				InternationalHuakuan ih = new InternationalHuakuan();
				ih.setRefuce(Integer.valueOf(tem[1].toString()));
				ih.setRefuceMoney(Double.valueOf(tem[2].toString()));
				ih.setRefuceMoneyAll(Double.valueOf(tem[4].toString()));
				this.initMerchantValue(Long.valueOf(tem[0].toString()), Long
						.valueOf(tem[3].toString()), ih);
				temMap.put(tem[0].toString() + "-" + tem[3].toString(), ih);

			}

		}
		// 已划款退款拒付 g
		for (int g = 0; g < rebackList5.size(); g++) {
			Object[] tem = (Object[]) rebackList5.get(g);
			if (temMap.containsKey(tem[0].toString() + "-" + tem[4].toString())) {
				InternationalHuakuan ih = (InternationalHuakuan) temMap
						.get(tem[0].toString() + "-" + tem[4].toString());
				ih.setRefuceComplement(Integer.valueOf(tem[1].toString()) / 2);
				ih.setRefuceComplementMoneyAll(Double
						.valueOf(tem[2].toString()) / 2);
				ih
						.setRefuceComplementMoney(Double.valueOf(tem[3]
								.toString()) / 2);
				temMap.put(tem[0].toString() + "-" + tem[4].toString(), ih);
			} else {
				InternationalHuakuan ih = new InternationalHuakuan();
				ih.setRefuceComplement(Integer.valueOf(tem[1].toString()) / 2);
				ih.setRefuceComplementMoneyAll(Double
						.valueOf(tem[2].toString()) / 2);
				ih
						.setRefuceComplementMoney(Double.valueOf(tem[3]
								.toString()) / 2);
				this.initMerchantValue(Long.valueOf(tem[0].toString()), Long
						.valueOf(tem[4].toString()), ih);
				temMap.put(tem[0].toString() + "-" + tem[4].toString(), ih);

			}

		}
		// 未划款解冻交易h
		for (int h = 0; h < refreList.size(); h++) {
			Object[] tem = (Object[]) refreList.get(h);
			if (temMap.containsKey(tem[0].toString() + "-" + tem[3].toString())) {
				InternationalHuakuan ih = (InternationalHuakuan) temMap
						.get(tem[0].toString() + "-" + tem[3].toString());
				ih.setNothawNumber(Integer.valueOf(tem[1].toString()));
				ih.setNothawMoney(Double.valueOf(tem[2].toString()));
				temMap.put(tem[0].toString() + "-" + tem[3].toString(), ih);
			} else {
				InternationalHuakuan ih = new InternationalHuakuan();
				ih.setNothawNumber(Integer.valueOf(tem[1].toString()));
				ih.setNothawMoney(Double.valueOf(tem[2].toString()));
				this.initMerchantValue(Long.valueOf(tem[0].toString()), Long
						.valueOf(tem[3].toString()), ih);
				temMap.put(tem[0].toString() + "-" + tem[3].toString(), ih);
			}

		}
		System.out.println("============9========="+(System.currentTimeMillis()-begin)+"\n");

		listName = new ArrayList();
		List listKey = new ArrayList();
		Iterator it = temMap.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next().toString();
			listKey.add(key);
			listName.add(temMap.get(key));
		}
		int riskNum=0;
		for (int i = 0; i < listName.size(); i++) {
			InternationalHuakuan hf = (InternationalHuakuan) listName.get(i);
			this.rmbmoney += hf.getRmbmoney();
			riskNum+=hf.getTradeNumberAll();
		}
		InternationalMerchantManager imm = (InternationalMerchantManager) this.commonService
				.uniqueResult("select imm from InternationalMerchantManager imm where imm.merchantId = '"
						+ tradeinfo.getId()+ "' ");
		if (imm.getRiskMoney()==null) {
			// 查询出默认的一条，并赋值给商户管理值对象
			imm = (InternationalMerchantManager) this.commonService
					.uniqueResult("select imm from InternationalMerchantManager imm where imm.merchantId is null");
		}
		riskRmbmoney=riskNum*imm.getRiskMoney();
		for(int i=1;i<12;i++){
		Calendar calendar = Calendar.getInstance();// 此时打印它获取的是系统当前时间
		calendar.add(Calendar.MONTH, -i); // 得到前一个月
		String beforeMonth = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(calendar.getTime());
		List<InternationalSettlment> ls=commonService.list("from InternationalSettlment where merchantno='"+tradeinfo.getId()+"' and createtabletime>to_date('"
							+ beforeMonth + "','yyyy-MM-dd hh24:mi:ss')");
		if(ls.size()>0){
			if(imm.getChannelMoney()!=null){
			channelFee=(i-1)*imm.getChannelMoney();
			}else{
				channelFee=(i-1)*200.00;
			}
			break;
		}
		}
		this.rmbmoney=rmbmoney-riskRmbmoney-channelFee;
		System.out.println("Convert Finished !");
		this.totPage = listName.size();
		Map mapLis = new HashMap();
		mapLis.put("viewList", listName);
		mapLis.put("batchno", batchNo);
		mapLis.put("merchantId", tradeinfo.getId());
		mapLis.put("nurmalID", temID);
		ActionContext.getContext().getSession().put("settlement", mapLis);
		System.out.println("============10========="+(System.currentTimeMillis()-begin)+"\n");

		return this.SUCCESS;
	}

//	// 生成划款表
	public String createSetemlement() {
		this.permissionsService.setTelement((Map) ActionContext.getContext()
				.getSession().get("settlement"), remark);
		/*this.permissionsService.setTelement((Map) ActionContext.getContext()
				.getSession().get("settlement"), remark, riskRmbmoney, channelFee, rmbmoney);*/
		return this.SUCCESS;
	}
	// 已划款列表
	public String allAlreadyTransfer() {
		//isdownload  values (1,2,3) 1: download 2: submit 3:query
		if(StringUtils.isBlank(isdownload)){
			isdownload = "3";
		}
		if(this.isdownload.equals("2")){
			if (freezeId != null) {
				String temID = "";
				for (int i = 0; i < freezeId.length; i++) {
	
					Long id = Long.valueOf((String) freezeId[i]);
					String bankname = (String) bankName[i];
	
					String remark = "";
					if (depict.length > 0) {
						remark = (String) depict[i];
					}
					this.commonService
							.deleteBySql(" update InternationalSettlment t set t.huakuantime=sysdate,t.huakuanbankname='"
									+ bankname + "' where t.id='" + id + "'");
					if (!remark.equals("")) {
						this.commonService
								.deleteBySql(" update InternationalSettlment t set t.remark='"
										+ remark + "' where t.id='" + id + "'");
					}
				}
			}
		}
		if (this.info == null) {
			this.info = new PageInfo();
		}
		//this.tradeinfo.setMerno(this.tradeinfo.getMerno());
		StringBuffer sb = new StringBuffer();

		sb
				.append(" select t,m from InternationalSettlment t ,InternationalMerchant m where t.merchantno=m.id and t.istrue='1'");

		if (merno != null) {
			sb.append(" and m.merno='" + merno + "'");
		}
		// 批次号
		if (StringUtils.isNotBlank(this.orderdno)) {
			sb.append(" and t.batchno='" + this.orderdno.trim() + "'");
		}
		// 批次号
		if (StringUtils.isNotBlank(this.accountname)) {
			sb.append(" and m.accountname='" + accountname.trim() + "'");
		}
		if (StringUtils.isNotBlank(startDate)) { // 开始日期
			sb.append(" and t.huakuantime>=to_date('" + startDate
					+ "','yyyy-MM-dd hh24:mi:ss') ");
		}
		if (StringUtils.isNotBlank(endDate)) { // 结束日期
			sb.append(" and t.huakuantime<=to_date('" + endDate +" 23:59:59"
					+ "','yyyy-MM-dd hh24:mi:ss') ");
		}
		if(this.typesname.equals("true")){
			sb.append(" and t.huakuantime is null ");
			//sb.append(" order by t.createtabletime desc");
//			this.info.setPageSize(50);
//			this.info = this.commonService
//			.listQueryResultByHql(sb.toString(), info);
		}
		
		if(this.isdownload.equals("1")){
			sb.append(" order by t.createtabletime desc");
			List<Object[]> objList  = commonService.list(sb.toString());
			this.downloadTradeQuery(objList);
			return null;
		}
		else{
			sb.append(" order by t.createtabletime desc");
			this.info.setPageSize(30);
			this.info = this.commonService
			.listQueryResultByHql(sb.toString(), info);
		}
		
		return this.SUCCESS;
	}

	private String isdownload;

	/**
	 * 下载商户交易查询记录
	 * 
	 * @return
	 */
	public void downloadTradeQuery(List<Object[]> oArray) {
		TableExport export = TableExportFactory.createExcelTableExport();
		export.addTitle(new String[] { "商户号", "批次号", "交易金额","交易币种","结算金额", "结算人民币", "生成划款表日期","划款日期","开户人",
				"卡号",  "划款行" ,"备注"});
		export.setTableName("结算");
		InternationalSettlment t = new InternationalSettlment();
		InternationalMerchant ci = new InternationalMerchant();
		int i = 1;
		for (Object[] obj : oArray) {
			t = (InternationalSettlment) obj[0];
			ci = (InternationalMerchant) obj[1];
			export
					.addRow(new Object[] { ci.getMerno(), t.getBatchno(),
							t.getOrdercount(), t.getTrademoneyname(),
							t.getRefundmentmoney(), t.getFreezecount(),
							t.getCreatetabletime(), t.getHuakuantime(),
							ci.getAccountname(), ci.getCardno(),t.getHuakuanbankname(),t.getRemark() });
			i++;
		}
		OutputStream os = DownloadUtils.getResponseOutput("huakuan.xls");
		export.export(os);
		DownloadUtils.closeResponseOutput();
	}

	// 显示明细
	public String showMingxi() {

		this.tradeList2 = this.commonService
				.list(" select t,m,ic from TradeDetails t ,InternationalMerchant m , InternationalMerchantChannels mc, InternationalChannels ic where ic.id=mc.channelId and t.channels=mc.id and t.merchantno=m.id and t.batchno='"
						+ batchNo + "'");
		return this.SUCCESS;
	}

	public String finshSetemlement() {
		// this.batchNo=0l;

		this.tradeinfo = (InternationalMerchant) this.commonService
				.list(
						" from InternationalMerchant t where t.id='"
								+ merchantId + "'").get(0);
		this.listName = this.commonService
				.list("from ViewSettlement t where t.batchno='" + batchNo + "'");
		// for(int i=0;i<listName.size();i++){
		// InternationalHuakuan hf=(InternationalHuakuan)listName.get(i);
		//        	
		// this.rmbmoney+=hf.getRmbmoney();
		// }
		Double riskMoney=0.00;
		Double channelMoney=0.00;
		for (int i = 0; i < listName.size(); i++) {
			ViewSettlement hf = (ViewSettlement) listName.get(i);
			if(hf.getRiskFee()!=null){
				riskMoney=hf.getRiskFee();
			}
			if(hf.getChannelFee()!=null){
				channelMoney=hf.getChannelFee();
			}
			this.rmbmoney += hf.getRmbmoney();
		}
		this.remark = "";
		this.rmbmoney=rmbmoney-riskMoney-channelMoney;
		InternationalSettlment ist = (InternationalSettlment) this.commonService
				.list(
						"from  InternationalSettlment t where  t.batchno='"
								+ batchNo + "'").get(0);
		this.remark = ist.getRemark();
		this.totPage = listName.size();
		return this.SUCCESS;
	}

	// 审核划款列表
	public String toAuditList() {

		if (this.info == null) {
			this.info = new PageInfo();
		}
		this.tradeinfo.setMerno(this.tradeinfo.getMerno());

		StringBuffer sb = new StringBuffer();

		sb
				.append(" select t,m from InternationalSettlment t ,InternationalMerchant m where t.merchantno=m.id and t.istrue='0'");

		if ((this.tradeinfo.getMerno() != null)) {
			if (this.tradeinfo.getMerno() != 0) {
				sb.append(" and m.merno='" + this.tradeinfo.getMerno() + "'");
			}
		}
		// 批次号
		if (StringUtils.isNotBlank(this.orderdno)) {
			sb.append(" and t.batchno='" + this.orderdno.trim() + "'");
		}
		if (StringUtils.isNotBlank(startDate)) { // 开始日期
			sb.append(" and t.createtabletime>=to_date('" + startDate
					+ "','yyyy-MM-dd hh24:mi:ss') ");
		}
		if (StringUtils.isNotBlank(endDate)) { // 结束日期
			sb.append(" and t.createtabletime<=to_date('" + endDate
					+ "','yyyy-MM-dd hh24:mi:ss') ");
		}
		this.info.setPageSize(10);
		this.info = this.commonService
				.listQueryResultByHql(sb.toString(), info);
		return this.SUCCESS;

	}

	// 查看审核详细
	public String showAuditDetail() {
		// this.batchNo=0l;

		this.tradeinfo = (InternationalMerchant) this.commonService
				.list(
						" from InternationalMerchant t where t.id='"
								+ merchantId + "'").get(0);
		this.listName = this.commonService
				.list("from ViewSettlement t where t.batchno='" + batchNo + "'");
		// for(int i=0;i<listName.size();i++){
		// InternationalHuakuan hf=(InternationalHuakuan)listName.get(i);
		//        	
		// this.rmbmoney+=hf.getRmbmoney();
		// }
		Double riskMoney=0.00;
		Double channelMoney=0.00;
		for (int i = 0; i < listName.size(); i++) {
			ViewSettlement hf = (ViewSettlement) listName.get(i);
			if(hf.getRiskFee()!=null){
				riskMoney=hf.getRiskFee();
			}
			if(hf.getChannelFee()!=null){
				channelMoney=hf.getChannelFee();
			}
			this.rmbmoney += hf.getRmbmoney();
		}
		this.rmbmoney=rmbmoney-riskMoney-channelMoney;
		this.totPage = listName.size();
		this.remark = "";
		InternationalSettlment ist = (InternationalSettlment) this.commonService
				.list(
						"from  InternationalSettlment t where  t.batchno='"
								+ batchNo + "'").get(0);
		this.remark = ist.getRemark();
		this.totPage = listName.size();
		return this.SUCCESS;
	}

	// 审核通过
	public String auditSettlementPass() {

		this.permissionsService.passSettlement(this.batchNo);

		return this.SUCCESS;
	}

	// 审核不通过
	public String auditSettlementNotOk() {
		this.permissionsService.noPassSettlement(this.batchNo);
		return this.SUCCESS;
	}

	// 显示终端号分离
	public String showByPos() {
		this.tradeinfo = (InternationalMerchant) this.commonService.list(
				" from InternationalMerchant t where t.id='" + this.merchantId
						+ "')").get(0);

		return "success";
	}

	public List getTradeList() {
		return tradeList;
	}

	public void setTradeList(List tradeList) {
		this.tradeList = tradeList;
	}

	public void setTradeList2(List tradeList2) {
		this.tradeList2 = tradeList2;
	}

	public InternationalMerchant getTradeinfo() {
		return tradeinfo;
	}

	public void setTradeinfo(InternationalMerchant tradeinfo) {
		this.tradeinfo = tradeinfo;
	}

	public List<InternationalTradeinfo> getTradeList2() {
		return tradeList2;
	}

	public List getListName() {
		return listName;
	}

	public void setListName(List listName) {
		this.listName = listName;
	}

	public Long getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(Long batchNo) {
		this.batchNo = batchNo;
	}

	public Long[] getId() {
		return id;
	}

	public void setId(Long[] id) {
		this.id = id;
	}

	public String getTypesname() {
		return typesname;
	}

	public void setTypesname(String typesname) {
		this.typesname = typesname;
	}

	public void initMerchantValue(Long rateid, Long merchantchanelid,
			InternationalHuakuan ih) {
		InternationalMerchantChannels imc = (InternationalMerchantChannels) this.commonService
				.load(InternationalMerchantChannels.class, merchantchanelid);
		ih.setProcedureRate(imc.getBalanceCharge());
		ih.setBailrate(imc.getBailCharge());
		InternationalChannels ic = (InternationalChannels) this.commonService
				.load(InternationalChannels.class, imc.getChannelId());
		ih.setChannels(ic.getChannelName());
		InternationalExchangerate rate = (InternationalExchangerate) this.commonService
				.load(InternationalExchangerate.class, rateid);
		ih.setBalancerate(rate.getRate());
		ih.setExchangedate(rate.getExecutetime());
		InternationalMoneykindname ct = (InternationalMoneykindname) this.commonService
				.load(InternationalMoneykindname.class, rate.getMoneykindno());
		ih.setTradeMoneykindName(ct.getMoneykindname());
		// 获取结算汇率
		List changerates = this.commonService
				.getByList("select t.id,t.rate,t.type from international_exchangerate t,international_moneykindname m  where t.moneykindno=m.id "
						+ "and t.moneykindno=1 and t.executetime<sysdate-1   and t.type='2' order by t.executetime desc  "); // 结算汇率
		// List changerates=this.commonService.getByList("select
		// t.id,t.rate,t.type from international_exchangerate t where
		// t.executetime in (select max(f.executetime) from
		// international_exchangerate f where f.moneykindno='"+ct.getId()+"' and
		// f.type='2')");
		Double nowBalanceRate = 0d;
		for(int i = 0; i < changerates.size(); i++) {
			Object[] tem = (Object[]) changerates.get(i);
			nowBalanceRate = Double.valueOf(tem[1].toString());
		}
		ih.setNowBalanceRate(nowBalanceRate);
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Double getRmbmoney() {
		return rmbmoney;
	}

	public void setRmbmoney(Double rmbmoney) {
		this.rmbmoney = rmbmoney;
	}

	public String getFreezdIds() {
		return freezdIds;
	}

	public void setFreezdIds(String freezdIds) {
		this.freezdIds = freezdIds;
	}

	public Double getTradeMoney() {
		return tradeMoney;
	}

	public void setTradeMoney(Double tradeMoney) {
		this.tradeMoney = tradeMoney;
	}

	public PermissionsService getPermissionsService() {
		return permissionsService;
	}

	public void setPermissionsService(PermissionsService permissionsService) {
		this.permissionsService = permissionsService;
	}

	public Object[] getBankName() {
		return bankName;
	}

	public void setBankName(Object[] bankName) {
		this.bankName = bankName;
	}

	public Object[] getDepict() {
		return depict;
	}

	public void setDepict(Object[] depict) {
		this.depict = depict;
	}

	public List getTradeList3() {
		return tradeList3;
	}

	public void setTradeList3(List tradeList3) {
		this.tradeList3 = tradeList3;
	}

	public String getShowMessage() {
		return showMessage;
	}

	public void setShowMessage(String showMessage) {
		this.showMessage = showMessage;
	}

	public String getOrderdno() {
		return orderdno;
	}

	public void setOrderdno(String orderdno) {
		this.orderdno = orderdno;
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

	public String getIsdownload() {
		return isdownload;
	}

	public void setIsdownload(String isdownload) {
		this.isdownload = isdownload;
	}

	public Long getMerno() {
		return merno;
	}

	public void setMerno(Long merno) {
		this.merno = merno;
	}

	public String getIsProtest() {
		return isProtest;
	}

	public void setIsProtest(String isProtest) {
		this.isProtest = isProtest;
	}

	public Double getRiskRmbmoney() {
		return riskRmbmoney;
	}

	public void setRiskRmbmoney(Double riskRmbmoney) {
		this.riskRmbmoney = riskRmbmoney;
	}

	public Double getChannelFee() {
		return channelFee;
	}

	public void setChannelFee(Double channelFee) {
		this.channelFee = channelFee;
	}


}
