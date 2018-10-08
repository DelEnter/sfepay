package com.ecpss.action.currency;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;

import com.ecpss.action.BaseAction;
import com.ecpss.model.shop.InternationalMoneykindname;
import com.ecpss.service.common.CommonService;

 
public class CurrencyAction extends BaseAction {
	private InternationalMoneykindname moneykind;
	public List li = new ArrayList();
	private String hql;
	
	//private CurrencyService currencyService;
	@Qualifier("commonService")
	private CommonService commonService;
	/**
	 * 跳转到添加币种
	 */
	public String toAddCurrency(){
		try{
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction="跳转失败";
			return this.OPERATE_SUCCESS;
		}
		
	}
	/**
	 * 保存币种
	 */
	public String addCurrency(){
		try{
			//currencyService.addCurrency(moneykind);
			
			if(moneykind.getMoneykindname()==null || moneykind.getMoneykindname().equals("")){
				this.messageAction = "币种名称不能为空!";
				return this.OPERATE_SUCCESS;
			}
			hql = "FROM InternationalMoneykindname m WHERE m.moneykindno="+moneykind.getMoneykindno()+"";
			List list = commonService.list(hql);
			if(list.size()>0){
				this.messageAction = "该币种已经存在!";
				return this.OPERATE_SUCCESS;
			}
			moneykind.setLastMan(getUserBean().getUserName());
			moneykind.setLastDate(new Date());
			commonService.save(moneykind);
			//return SUCCESS;
			this.messageAction = "保存币种成功";
			return this.OPERATE_SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction = "保存币种失败";
			return this.OPERATE_ERROR;
			//return ERROR;
		}
	}
	/**
	 * 查询币种
	 */
	public String findCurrency(){
		try{
			hql = "FROM InternationalMoneykindname";
			li = commonService.list(hql);
			//li = currencyService.findCurrency();
			//System.out.println("list-------------"+li.size());
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * 查询需要修改的币种
	 */
	public String findUpdateCurrency(){
		try{
			//moneykind = currencyService.findUpdateCurrency(moneykind);
			moneykind = (InternationalMoneykindname)
			commonService.load(InternationalMoneykindname.class, moneykind.getId());
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * 修改币种
	 */
	public String updateCurrency(){
		try{
			//InternationalMoneykindname money = currencyService.findUpdateCurrency(moneykind);
			InternationalMoneykindname money = (InternationalMoneykindname)
			commonService.load(InternationalMoneykindname.class, moneykind.getId());
			money.setLastDate(new Date());
			if(moneykind.getMoneykindno()==null || moneykind.getMoneykindno().equals("")){
				this.messageAction = "币种编号不能为空!";
				return this.OPERATE_SUCCESS;
			}else if(moneykind.getMoneykindname()==null || moneykind.getMoneykindname().equals("")){
				this.messageAction = "币种名称不能为空!";
				return this.OPERATE_SUCCESS;
			}
			money.setLastMan(getUserBean().getUserName());
			money.setMoneykindname(moneykind.getMoneykindname());
			money.setMoneykindno(moneykind.getMoneykindno());
			commonService.update(money);
			this.messageAction="修改币种成功!";
			return this.OPERATE_SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * 删除币种
	 */
	public String deleteCurrency(){
		try{
			//currencyService.deleteCurrency(moneykind);
			commonService.delete(moneykind);
			this.messageAction="币种删除成功!";
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	public InternationalMoneykindname getMoneykind(){
		return moneykind;
	}
	public void setMoneykind(InternationalMoneykindname moneykind) {
		this.moneykind = moneykind;
	}


	public List getLi() {
		return li;
	}

	public void setLi(List li) {
		this.li = li;
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

}
