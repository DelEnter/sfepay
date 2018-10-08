package com.ecpss.action.permissions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ecpss.action.BaseAction;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.model.shop.SettlementCycleTime;
import com.ecpss.service.iservice.SystemManagerService;
import com.ecpss.util.DateUtil;

public class SetllementCycle extends BaseAction {
	
	@Autowired
	@Qualifier("systemManagerService")
	private SystemManagerService systemManagerService;
	
	private List tradeList;
	private SettlementCycleTime settlementCycleTime;
	private InternationalMerchant tradeinfo = new InternationalMerchant();
	private Long merno;
	private int today_day;
	private List cycleDayList;
	
	public String tocyclelist() {
		if (this.tradeinfo.getMerno() != null) {
			this.tradeinfo.setMerno(this.tradeinfo.getMerno());
			tradeList = this.commonService
					.list(" select t,f from SettlementCycleTime t ,InternationalMerchant f where t.merchant=f.id and f.merno='"
							+ this.tradeinfo.getMerno() + "'");
		}

		return this.SUCCESS;
	}

	public String toAddCycle() {

		return this.SUCCESS;
	}

	public String addCycle() {
		if (this.settlementCycleTime.getCycleTime() != null) {
			List<InternationalMerchant> lis = this.commonService
					.list("from InternationalMerchant t where t.merno='"
							+ this.settlementCycleTime.getMerchant() + "'");
			if (lis.size() > 0) {
				this.settlementCycleTime.setMerchant(lis.get(0).getId());
				this.commonService.save(settlementCycleTime);
			}
		}
		return this.SUCCESS;
	}

	public String deletecycle() {
		this.commonService.delete(settlementCycleTime);
		return this.SUCCESS;
	}

	public String merchantCycleQuery(){
		today_day = DateUtil.getToDayOfMonth();
		String hql = "select distinct sc.cycleTime from SettlementCycleTime sc,InternationalMerchant m " +
				" where m.id=sc.merchant " ;
		if(merno!=null){
			hql+=" and m.merno="+merno ;
		}
		hql+=" group by sc.cycleTime order by sc.cycleTime ";
		cycleDayList = this.commonService.list(hql);
		return SUCCESS;
	}
	
	
	public List getTradeList() {
		return tradeList;
	}

	public void setTradeList(List tradeList) {
		this.tradeList = tradeList;
	}

	public SettlementCycleTime getSettlementCycleTime() {
		return settlementCycleTime;
	}

	public void setSettlementCycleTime(SettlementCycleTime settlementCycleTime) {
		this.settlementCycleTime = settlementCycleTime;
	}

	public InternationalMerchant getTradeinfo() {
		return tradeinfo;
	}

	public void setTradeinfo(InternationalMerchant tradeinfo) {
		this.tradeinfo = tradeinfo;
	}

	public int getToday_day() {
		return today_day;
	}

	public void setToday_day(int today_day) {
		this.today_day = today_day;
	}

	public List getCycleDayList() {
		return cycleDayList;
	}

	public void setCycleDayList(List cycleDayList) {
		this.cycleDayList = cycleDayList;
	}

	public SystemManagerService getSystemManagerService() {
		return systemManagerService;
	}

	public void setSystemManagerService(SystemManagerService systemManagerService) {
		this.systemManagerService = systemManagerService;
	}

	public Long getMerno() {
		return merno;
	}

	public void setMerno(Long merno) {
		this.merno = merno;
	}
}
