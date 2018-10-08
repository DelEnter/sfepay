package com.ecpss.action.currency;

import java.util.ArrayList;
import com.ecpss.util.DateUtil;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ecpss.action.BaseAction;
import com.ecpss.model.shop.InternationalExchangerate;
import com.ecpss.model.shop.InternationalMoneykindname;
import com.ecpss.service.common.CommonService;

public class RateAction extends BaseAction{
	@Autowired
	@Qualifier("commonService")
	private CommonService commonService;
	
	private String hql;
	private InternationalExchangerate rate;
	private InternationalMoneykindname money;
	private String excuteDate;

	private List list = new ArrayList();
	public InternationalMoneykindname getMoney() {
		return money;
	}
	public void setMoney(InternationalMoneykindname money) {
		this.money = money;
	}
	/**
	 * 保存交易汇率
	 */
	public String saveTradeRate(){
		try{
			//System.out.println(rate.getRate());
			if(rate.getRate()==null || rate.getRate().equals("")){
				this.messageAction="交易汇率不能为空!";
				return this.OPERATE_SUCCESS;
			}else if(excuteDate==null || excuteDate.equals("")){
				this.messageAction="执行时间不能为空!";
				return this.OPERATE_SUCCESS;
			}
			rate.setType("1");
			rate.setExecutetime(DateUtil.toDate(excuteDate));
			rate.setSettime(new Date());
			rate.setCreater(getUserBean().getUserName());
			commonService.save(rate);
			this.messageAction="交易汇率保存成功";
			return this.OPERATE_SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction="交易汇率保存失败";
			return this.OPERATE_SUCCESS;
		}
	}
	/**
	 * 保存结算汇率
	 */
	public String saveBalanceRate(){
		try{
			if(rate.getRate()==null || rate.getRate().equals("")){
				this.messageAction="结算汇率不能为空!";
				return this.OPERATE_SUCCESS;
			}else if(excuteDate==null || excuteDate.equals("")){
				this.messageAction="执行时间不能为空!";
				return this.OPERATE_SUCCESS;
			}
			rate.setType("2");
			rate.setExecutetime(DateUtil.toDate(excuteDate));
			rate.setCreater(getUserBean().getUserName());
			rate.setSettime(new Date());
			//rateService.saveRate(rate);
			commonService.save(rate);
			this.messageAction="结算汇率保存成功";
			return this.OPERATE_SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction="结算汇率保存失败";
			return this.OPERATE_SUCCESS;
		}
	}
	/**
	 * 删除交易汇率
	 */
	public String deleteBalanceRate(){
		try{
			//rateService.deleteRate(rate);
			commonService.delete(rate);
			this.messageAction="结算汇率删除成功";
			return this.OPERATE_SUCCESS;
		}catch(Exception e){
			this.messageAction="结算汇率删除失败";
			return this.OPERATE_SUCCESS;
		}
	}
	/**
	 * 查询需要修改的结算汇率
	 */
	public String findUpdateBalanceRate(){
		try{
			//rate = rateService.findUpdateRate(rate);
			rate = (InternationalExchangerate)commonService.load(InternationalExchangerate.class, rate.getId());
			hql = "FROM InternationalMoneykindname where id="+rate.getMoneykindno()+"";
			money = (InternationalMoneykindname)commonService.uniqueResult(hql);
			
			this.messageAction="结算汇率查询成功";
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction="结算汇率查询失败";
			return this.OPERATE_SUCCESS;
		}
	}
	/**
	 * 修改结算汇率
	 */
	public String updateBalanceRate(){
		try{
			if(rate.getRate()==null || rate.getRate().equals("")){
				this.messageAction="结算汇率不能为空!";
				return this.OPERATE_SUCCESS;
			}else if(rate.getExecutetime()==null || rate.equals("")){
				this.messageAction="执行时间不能为空!";
				return this.OPERATE_SUCCESS;
			}
			InternationalExchangerate exchange = (InternationalExchangerate)
			commonService.load(InternationalExchangerate.class, rate.getId());
			exchange.setExecutetime(rate.getExecutetime());
			exchange.setRate(rate.getRate());
			exchange.setCreater(getUserBean().getUserName());
			exchange.setSettime(new Date());
			//exchange.setType(rate.getType());
			//rateService.updateRate(exchange);
			commonService.update(exchange);
			this.messageAction="汇率修改成功";
			return this.OPERATE_SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction="汇率修改失败";
			return this.OPERATE_SUCCESS;
		}
	}
	/**
	 * 查询结算汇率
	 */
	public String findBalanceRate(){
		try{
			hql = "FROM InternationalExchangerate e, InternationalMoneykindname m" +
			" WHERE e.moneykindno=m.id AND e.type=2 ORDER BY e.moneykindno, e.executetime";
			list = commonService.list(hql);
		
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction="结算查询汇率失败";
			return this.OPERATE_SUCCESS;
		}
	}
	/**
	 * 跳转到保存汇率
	 * @return
	 */
	public String toSaveBalanceRate(){
		try{
			hql = "FROM InternationalMoneykindname";
			list = commonService.list(hql);
			//list =rateService.findCurrencyRate();
		//	System.out.println("list---------"+list.size());
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction="查询币种失败";
			return this.OPERATE_SUCCESS;
		}
	}
	
	/**
	 * 删除交易汇率
	 */
	public String deleteTradeRate(){
		try{
			//rateService.deleteRate(rate);
			commonService.delete(rate);
			this.messageAction="交易汇率删除成功";
			return this.OPERATE_SUCCESS;
		}catch(Exception e){
			this.messageAction="交易汇率删除失败";
			return this.OPERATE_SUCCESS;
		}
	}
	/**
	 * 查询需要修改交易的汇率
	 */
	public String findUpdateTradeRate(){
		try{
			//rate = rateService.findUpdateRate(rate);
			rate = (InternationalExchangerate)commonService.load(InternationalExchangerate.class, rate.getId());
			hql = "FROM InternationalMoneykindname where id="+rate.getMoneykindno()+"";
			money = (InternationalMoneykindname)commonService.uniqueResult(hql);
			this.messageAction="交易汇率查询成功";
			return SUCCESS;
		}catch(Exception e){
			this.messageAction="交易汇率查询失败";
			return this.OPERATE_SUCCESS;
		}
	}
	/**
	 * 修改交易汇率
	 */
	public String updateTradeRate(){
		try{
			if(rate.getRate()==null || rate.getRate().equals("")){
				this.messageAction="交易汇率不能为空!";
				return this.OPERATE_SUCCESS;
			}else if(rate.getExecutetime()==null || rate.equals("")){
				this.messageAction="执行时间不能为空!";
				return this.OPERATE_SUCCESS;
			}
			InternationalExchangerate exchange = (InternationalExchangerate)
			commonService.load(InternationalExchangerate.class, rate.getId());
			exchange.setExecutetime(rate.getExecutetime());
			exchange.setCreater(getUserBean().getUserName());
			exchange.setRate(rate.getRate());
			//exchange.setMoneykindno(rate.getMoneykindno());
			exchange.setSettime(new Date());
			commonService.update(exchange);
			this.messageAction="汇率修改成功";
			return this.OPERATE_SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction="汇率修改失败";
			return this.OPERATE_SUCCESS;
		}
	}
	/**
	 * 查询交易汇率
	 */
	public String findTradeRate(){
		try{
			hql = "FROM InternationalExchangerate e, InternationalMoneykindname m" +
					" WHERE e.moneykindno=m.id AND e.type=1 ORDER BY e.moneykindno, e.executetime";
			list = commonService.list(hql);
			//list = rateService.findRate();
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction="交易查询汇率失败";
			return this.OPERATE_SUCCESS;
		}
	}
	/**
	 * 跳转到保存交易汇率
	 * @return
	 */
	public String toSaveTradeRate(){
		try{
			hql = "FROM InternationalMoneykindname";
			list = commonService.list(hql);
		//	System.out.println("list---------"+list.size());
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction="查询币种失败";
			return this.OPERATE_SUCCESS;
		}
	}
	public InternationalExchangerate getRate() {
		return rate;
	}
	public void setRate(InternationalExchangerate rate) {
		this.rate = rate;
	}

	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
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
	 * @return the excuteDate
	 */
	public String getExcuteDate() {
		return excuteDate;
	}
	/**
	 * @param excuteDate the excuteDate to set
	 */
	public void setExcuteDate(String excuteDate) {
		this.excuteDate = excuteDate;
	}
}
