package com.ecpss.action.currency;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ecpss.action.BaseAction;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.model.shop.InternationalMerchantCurrency;
import com.ecpss.model.shop.InternationalMoneykindname;
import com.ecpss.service.common.CommonService;

public class MerCurrency extends BaseAction{
	@Autowired
	@Qualifier("commonService")
	private CommonService commonService;
	private String hql;
	private List list = new ArrayList();
	private InternationalMoneykindname money = new InternationalMoneykindname();
	private InternationalMerchantCurrency merCurrency = new InternationalMerchantCurrency();
	private InternationalMerchant mer = new InternationalMerchant();
	private Object[] obj;
	
	/**
	 * 跳转到添加商户币种页面
	 */
	public String toSaveMerCurrency(){
		try{
			//hql = "FROM InternationalMerchant WHERE id="+mer.getId()+"";
			//mer = (InternationalMerchant)commonService.uniqueResult(hql);
			
			hql = "FROM InternationalMoneykindname";
			list = commonService.list(hql);
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction = "跳转到添加商户币种失败!";
			return this.OPERATE_ERROR;
		}
	}
	/**
	 * 添加商户币种
	 */
	public String saveMerCurrency(){
		try{
			 //判断商户号是否为空
			if(mer.getMerno()==null || mer.getMerno().equals("")){
				this.messageAction = "商户号不能为空!";
				return this.OPERATE_SUCCESS;
			}
			hql = "FROM InternationalMerchant m WHERE m.merno="+mer.getMerno()+"";
			InternationalMerchant merchant = (InternationalMerchant)commonService.uniqueResult(hql);
			/*//hql = "FROM InternationalMerchantCurrency WHERE merchanId="+merCurrency.getMerchanId()+"";
			list = commonService.list(hql);
			if(list.size()>0){
				this.messageAction = "该商户交易币种已存在";
				return this.OPERATE_ERROR;
			}*/
			//判断该商户是否已经设置了该交易币种
			hql = "FROM InternationalMerchantCurrency c WHERE c.moneyKindNo="+merCurrency.getMoneyKindNo()+" " +
			"AND c.merchanId="+merchant.getId()+"";
			list = commonService.list(hql);
			if(list.size()>0){
				this.messageAction = "该币种该商户已经设置过!";
				return this.OPERATE_SUCCESS;
			}
			merCurrency.setMerchanId(merchant.getId());
			commonService.save(merCurrency);
			this.messageAction = "添加商户币种成功!";
			return this.OPERATE_SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction = "添加商户币种失败!";
			return this.OPERATE_SUCCESS;
		}
	}
	
	
	/**
	 * 删除商户币种
	 */
	public String deleteMerCurrency(){
		try{
			commonService.delete(merCurrency);
			this.messageAction = "删除商户币种成功";
			return this.OPERATE_SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction = "删除商户币种失败";
			return this.OPERATE_SUCCESS;
		}
	}
	/**
	 * 跳转到修改商户币种页面
	 */
	public String toUpdateMerCurrency(){
		try{
		
			hql = "SELECT mer.merno, currency.moneyKindNo FROM InternationalMerchant mer, InternationalMerchantCurrency currency " +
				   "WHERE mer.id= currency.merchanId AND currency.id="+merCurrency.getId()+"";
			obj = (Object[])commonService.uniqueResult(hql);
			
			hql = "FROM InternationalMoneykindname";
			list = commonService.list(hql);
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction = "跳转到商户币种页面失败";
			return this.OPERATE_ERROR;
		}
	}
	/**
	 * 修改商户币种
	 */
	public String updateMerCurrency(){
		try{
			if(mer.getMerno()==null || mer.getMerno().equals("")){
				this.messageAction = "商户号不能为空!";
				return this.OPERATE_SUCCESS;
			}
			hql = "FROM InternationalMerchant m WHERE m.merno="+mer.getMerno()+"";
			InternationalMerchant merchant = (InternationalMerchant)commonService.uniqueResult(hql);
			hql = "FROM InternationalMerchantCurrency c WHERE c.id="+merCurrency.getMoneyKindNo()+" " +
			"AND c.merchanId="+merchant.getId()+"";
			list = commonService.list(hql);
			if(list.size()>0){
				this.messageAction = "该币种该商户已经设置过!";
				return this.OPERATE_SUCCESS;
			}
			
			InternationalMerchantCurrency currency = (InternationalMerchantCurrency)
			commonService.load(InternationalMerchantCurrency.class, merCurrency.getId());
			currency.setMoneyKindNo(merCurrency.getMoneyKindNo());
			commonService.update(currency);
			this.messageAction = "修改商户币种成功!";
			return this.OPERATE_ERROR;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction = "修改商户币种失败!";
			return this.OPERATE_ERROR;
		}
	}
	/**.........................................
	 * 查询商户币种
	 */
	public String findMerCurrency(){
		try{
			//hql = "FROM InternationalMerchant mer, InternationalMerchantCurrency currency, InternationalMoneykindname money " +
			//	  "WHERE mer.id = currency.merchanId AND currency.moneyKindNo = money.moneykindno AND currency.merchanId="+merCurrency.getMerchanId()+"";
			hql = "FROM InternationalMerchant mer, InternationalMerchantCurrency currency, InternationalMoneykindname money " +
			  	  "WHERE mer.id = currency.merchanId AND currency.moneyKindNo = money.id AND mer.merno="+mer.getMerno()+"";
			list = commonService.list(hql);
			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction = "查询商户币种失败!";
			return this.OPERATE_ERROR;
		}
	}
	/**
	 * @return the list
	 */
	public List getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
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
	 * @return the money
	 */
	public InternationalMoneykindname getMoney() {
		return money;
	}

	/**
	 * @param money the money to set
	 */
	public void setMoney(InternationalMoneykindname money) {
		this.money = money;
	}

	/**
	 * @return the merCurrency
	 */
	public InternationalMerchantCurrency getMerCurrency() {
		return merCurrency;
	}

	/**
	 * @param merCurrency the merCurrency to set
	 */
	public void setMerCurrency(InternationalMerchantCurrency merCurrency) {
		this.merCurrency = merCurrency;
	}

	/**
	 * @return the mer
	 */
	public InternationalMerchant getMer() {
		return mer;
	}

	/**
	 * @param mer the mer to set
	 */
	public void setMer(InternationalMerchant mer) {
		this.mer = mer;
	}
	/**
	 * @return the obj
	 */
	public Object[] getObj() {
		return obj;
	}
	/**
	 * @param obj the obj to set
	 */
	public void setObj(Object[] obj) {
		this.obj = obj;
	}
}
