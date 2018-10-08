/*package com.ecpss.action.currency;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;

import com.ecpss.action.BaseAction;
import com.ecpss.model.shop.InternationalMerchantCurrency;
import com.ecpss.service.common.CommonService;

public class MerchantRateAction extends BaseAction{
	@Qualifier("commonService")
	private CommonService commonService;
	private String hql;
	private List list = new ArrayList();
	private StringBuffer sb;
	private InternationalMerchantCurrency merchantCurrency;
	private InternationalMerchantCurrency mc;
	*//**
	 * 添加商户汇率
	 *//*
	public String saveMerchantCurrency(){
		try{
			//判断该商户是否已经设置了该交易币种
			hql = "FROM InternationalMerchantCurrency c WHERE c.moneyKindNo="+merchantCurrency.getMoneyKindNo()+" " +
					"AND c.merchanId="+merchantCurrency.getMerchanId()+"";
			list = commonService.list(hql);
			if(list.size()>0){
				this.messageAction = "该币种该商户已经设置过!";
				return this.OPERATE_SUCCESS;
			}
			if(merchantCurrency.getMerchanId()==null || merchantCurrency.getMerchanId().equals("")){
				this.messageAction = "商户号不能为空!";
				return this.OPERATE_SUCCESS;
			}
			commonService.save(merchantCurrency);
			this.messageAction="保存商户币种成功";
			return this.OPERATE_SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction="保存商户币种失败";
			return this.OPERATE_SUCCESS;
		}
	}
	
	*//**
	 * 删除商户汇率
	 *//*
	public String deleteMerchantCurrency(){
		try{
			commonService.delete(merchantCurrency);
			this.messageAction="删除商户币种成功";
			return this.OPERATE_SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction="删除商户币种成功";
			return this.OPERATE_SUCCESS;
		}
	}
	*//**
	 * 跳转到修改商户汇率页面
	 *//*
	public String toUpdateMerchantCurrency(){
		try{	
			mc = (InternationalMerchantCurrency)
			commonService.load(InternationalMerchantCurrency.class, merchantCurrency.getId());
			merchantCurrency = mc;
			hql = "FROM InternationalMoneykindname";
			list = commonService.list(hql);
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction="查询商户币种失败";
			return this.OPERATE_SUCCESS;
		}
	}
	*//**
	 * 修改商户汇率
	 *//*
	public String updateMerchantCurrency(){
		try{
			//判断该商户是否已经设置了该交易币种
			hql = "FROM InternationalMerchantCurrency c WHERE c.moneyKindNo="+merchantCurrency.getMoneyKindNo()+" " +
					"AND c.merchanId="+merchantCurrency.getMerchanId()+"";
			list = commonService.list(hql);
			if(list.size()>0){
				this.messageAction = "该币种该商户已经设置过!";
				return this.OPERATE_SUCCESS;
			}
			if(merchantCurrency.getMerchanId()==null || merchantCurrency.getMerchanId().equals("")){
				this.messageAction = "商户号不能为空!";
				return this.OPERATE_SUCCESS;
			}
			InternationalMerchantCurrency currency = (InternationalMerchantCurrency)
			commonService.load(InternationalMerchantCurrency.class, merchantCurrency.getId());
			
			currency.setMerchanId(merchantCurrency.getMerchanId());
			currency.setMoneyKindNo(merchantCurrency.getMoneyKindNo());
			commonService.update(currency);
			
			this.messageAction="商户币种修改成功";
			return this.OPERATE_SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction="商户币种修改成功";
			return this.OPERATE_SUCCESS;
		}
	}
	*//**
	 * 查询商户汇率
	 *//*
	public String findMerchantCurrency(){
		try{
			sb = new StringBuffer("FROM InternationalMerchantCurrency c, InternationalMoneykindname m" +
					" WHERE c.moneyKindNo= m.moneykindno");
			if(merchantCurrency!=null){
				if(merchantCurrency.getMerchanId()!=null){
					sb.append(" AND merchanId="+merchantCurrency.getMerchanId()+"");
				}
			}
			hql = sb.toString();
			list = commonService.list(hql);
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction="查询商户币种失败";
			return this.OPERATE_SUCCESS;
		}
	}
	*//**
	 * 跳转到保存商户汇率
	 *//*
	public String toMerchantCurrency(){
		try{
			hql = "FROM InternationalMoneykindname";
			list = commonService.list(hql);
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction="查询商户币种失败";
			return this.OPERATE_SUCCESS;
		}
	}
	
	*//**
	 * @return the commonService
	 *//*
	public CommonService getCommonService() {
		return commonService;
	}
	*//**
	 * @param commonService the commonService to set
	 *//*
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	*//**
	 * @return the merchantCurrency
	 *//*
	

	*//**
	 * @return the list
	 *//*
	public List getList() {
		return list;
	}

	*//**
	 * @param list the list to set
	 *//*
	public void setList(List list) {
		this.list = list;
	}

	*//**
	 * @return the mc
	 *//*
	public InternationalMerchantCurrency getMc() {
		return mc;
	}

	*//**
	 * @param mc the mc to set
	 *//*
	public void setMc(InternationalMerchantCurrency mc) {
		this.mc = mc;
	}

	*//**
	 * @return the merchantCurrency
	 *//*
	public InternationalMerchantCurrency getMerchantCurrency() {
		return merchantCurrency;
	}

	*//**
	 * @param merchantCurrency the merchantCurrency to set
	 *//*
	public void setMerchantCurrency(InternationalMerchantCurrency merchantCurrency) {
		this.merchantCurrency = merchantCurrency;
	}
}
*/