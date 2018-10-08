package com.ecpss.action.pay;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ecpss.dao.BaseHibernateDao;
@Repository("tradeManagerDao")
public class TradeManagerDaoImpl extends BaseHibernateDao implements TradeManagerDao {
     public int intBySql(String sql){
    	 BigDecimal bi=(BigDecimal)this.getSession().createSQLQuery(sql).uniqueResult();
    	 if(bi==null){
    		
    		 return -1;
  //  		 bi=new BigDecimal("0");
    	 }
    	 return bi.intValue();
     }

	public Double getAcount(Long merno) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar   c   =   Calendar.getInstance(); 
		c.set(c.YEAR,   2001);   
		c.set(c.MONTH,   1);    
        c.setTime(new    java.util.Date());   
        int    year      =    c.get(Calendar.YEAR);   
        int    month   =    c.get(Calendar.MONTH)+1; 
		//获取本月开始日期
		String startTime = year + "-"+month + "-" + c.getActualMinimum(c.DAY_OF_MONTH);
		//获取本月结束日期
		String endTime = year + "-" + month + "-" + c.getActualMaximum(c.DAY_OF_MONTH) + " 23:59:59";
		String sql="select sum(t.tradeamount) from  international_tradeinfo t  where t.merchantid='"+merno+"'and substr(t.tradestate,1,1) in(2,4,1) AND tradetime >= to_date('"+startTime+"','yyyy-mm-dd') AND tradetime <=to_date('"+endTime+"','yyyy-mm-dd hh24:mi:ss')";
		BigDecimal ds=(BigDecimal)this.getSession().createSQLQuery(sql).uniqueResult();
		Double tem=0d;
		if(ds!=null){
		tem=ds.doubleValue();
		}
		return tem;
	}
}
