package com.ecpss.action.tradequery;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ecpss.action.BaseAction;
import com.ecpss.action.pay.TradeManager;
import com.ecpss.web.PageInfo;

public class MerchantAllSucRateAction extends BaseAction {
	@Autowired
	@Qualifier("tradeManager")
	private TradeManager tradeManager;	
	private PageInfo info = new PageInfo();
	private Long merchantNo;
	private String startDate;
	private String endDate;
	//统计商户交易成功率
	public String toMerchantRate(){
		String hql="select m.tradeRemark from InternationalMerchantSucRate m where m.status='1' ";
		List sucRemark =commonService.list(hql);
		String totleCount="select count(*) from (";
		String selectSql="SELECT  * FROM (SELECT A.*, ROWNUM RN FROM (";
		String sql = "select m.merno,sum(case when substr(ti.tradeState,1,1)! = '3' then '1' else '0' end) tradeAll,sum(case when substr(ti.tradeState,1,1) = '1' then '1' else '0' end)  suc "
				+ "from International_Tradeinfo ti,International_Merchant m where ti.merchantId=m.id and m.merno>3600  ";
		if(sucRemark!=null&&sucRemark.size()>0){
			sql=sql+" and ( ti.remark like '%Success%' ";
			for(int j=0;j<sucRemark.size();j++){
					sql=sql+" or ti.remark like '%"+sucRemark.get(j).toString().trim()+"%'";
			}
			sql=sql+") ";
		}
		if(merchantNo!=null){
			sql=sql+" and m.merno="+merchantNo;
		}
		if(StringUtils.isNotBlank(startDate)){  //开始日期
			sql=sql+" and ti.tradeTime>=to_date('"+startDate+"','yyyy-MM-dd hh24:mi:ss') ";
		}
		if(StringUtils.isNotBlank(endDate)){   //结束日期
			sql=sql+" and ti.tradeTime<=to_date('"+endDate+" 23:59:59"+"','yyyy-MM-dd hh24:mi:ss') ";
		}
		sql+=" group by m.merno " +
			"order by m.merno desc ) ";
		String listinfo= " A WHERE ROWNUM <= "+(info.getCurPage())*info.getPageSize()+")WHERE RN > " +(info.getCurPage()-1)*info.getPageSize()+"";	
		List list = this.commonService.getByList(selectSql+sql+listinfo);
		List listRate=new ArrayList();
		for(int i=0;i<list.size();i++){
			Object[] objsum=(Object[]) list.get(i);
			int traderCount=Integer.parseInt(objsum[1].toString());
			int traderSucCount=Integer.parseInt(objsum[2].toString());
			NumberFormat numberFormat = NumberFormat.getInstance();  
	        // 设置精确到小数点后2位  
	        numberFormat.setMaximumFractionDigits(2);  
	        objsum[3] = numberFormat.format((float) traderSucCount / (float) traderCount * 100);
	        listRate.add(objsum);
		}
	    info.setTotalCount(this.tradeManager.intBySql(totleCount+sql));
	    info.setResult(listRate);
		return SUCCESS;
	}
	public String toTradeChart(){
		return SUCCESS;
	}

	public PageInfo getInfo() {
		return info;
	}

	public void setInfo(PageInfo info) {
		this.info = info;
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

	


}
