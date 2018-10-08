package com.ecpss.action.tradestatistics;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import com.ecpss.action.BaseAction;

public class ViewBalance extends BaseAction {
    private List viewList;
	private Long merNo;
	public String viewMoney() {		
		StringBuffer sb = new StringBuffer();
		sb
			 .append("select aa.amerno ,aa.atradeAmount ,bb.btradeamount,cc.ctradeamount from ( "
						+ "select m.merno amerno,sum(inf.tradeAmount) atradeAmount "
						+ "from  International_Tradeinfo inf ,international_merchant m "
						+ " where substr(inf.tradeState,1,1)='1'  "
						+ " and m.id=inf.merchantid "
						+ " and substr(inf.tradeState,10,1)='0' "
						+ "  and substr(inf.tradeState,2,1)='0' "
						+ "  and substr(inf.tradeState,3,1)='0'  "
						+ " and substr(inf.tradeState,4,1) in (0,2)  ");
		if(merNo!=null){
			sb.append(" and m.merno='"+merNo+"'  ");
		}

		sb
				.append("   group by m.merno "
						+ "   ) aa "
						+ "  left join ( "
						+ "   select m.merno bmerno,sum(inf.tradeAmount-inf.backCount) btradeamount "
						+ "   from  International_Tradeinfo inf ,international_merchant m "
						+ "   where substr(inf.tradeState,1,1)='1'  "
						+ " and m.id=inf.merchantid "
						+ " and substr(inf.tradeState,10,1)='0'  "
						+ " and substr(inf.tradeState,2,1)='2'  "
						+ "  and substr(inf.tradeState,3,1)='0'  "
						+ " and substr(inf.tradeState,4,1) in (0,2)   ");
		if(merNo!=null){
			sb.append(" and m.merno='"+merNo+"'  ");
		}

		sb
				.append("   group by m.merno "
						+ "  ) bb on aa.amerno=bb.bmerno  "
						+ "  left join ( select m.merno cmerno,sum(inf.tradeAmount) ctradeamount "
						+ "  from  International_Tradeinfo inf  ,international_merchant m "
						+ "  where substr(inf.tradeState,1,1)='1' "
						+ "  and substr(inf.tradeState,8,1)='0' "
						+ "   and m.id=inf.merchantid ");
		if(merNo!=null){
			sb.append(" and m.merno='"+merNo+"'  ");
		}

		sb.append("   group by m.merno " 
				+ "     ) cc on  aa.amerno=cc.cmerno  ");
		
		viewList=commonService.getByList(sb.toString());
		return "success";
	}

	public List getViewList() {
		return viewList;
	}

	public void setViewList(List viewList) {
		this.viewList = viewList;
	}

	public Long getMerNo() {
		return merNo;
	}

	public void setMerNo(Long merNo) {
		this.merNo = merNo;
	}

}
