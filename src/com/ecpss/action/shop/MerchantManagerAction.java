package com.ecpss.action.shop;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ecpss.action.BaseAction;
import com.ecpss.model.shop.InternationalMerchantManager;
import com.ecpss.service.common.CommonService;

public class MerchantManagerAction extends BaseAction{
	@Autowired
	@Qualifier("commonService")
	private CommonService commonService;
	private InternationalMerchantManager merman;
	private List list = new ArrayList();
	private String hql;
	/**
	 * 跳转到商户管理分值页面
	 */
	public String toMerchantManagerValue(){
		try{
			return SUCCESS;
		}catch(Exception e)
		{
			e.printStackTrace();
			this.messageAction = "跳转到商户管理分值页面失败";
			return this.OPERATE_ERROR;
		}
	}
	/**
	 * 查询商户管理分值
	 */
	public String findManagerValue(){
		try{
			if(merman==null){
				merman = new InternationalMerchantManager();
			}
			long tem=merman.getMerchantId();
			hql = "FROM InternationalMerchantManager WHERE merchantid="+merman.getMerchantId()+"";
			list = commonService.list(hql);
			if(list.size()==0){
				hql = "FROM InternationalMerchantManager WHERE merchantid is null";
				list = commonService.list(hql);
				if(list.size()!=0){
					merman = (InternationalMerchantManager)list.get(0);
					merman.setMerchantId(tem);
				}
			}
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction="查询商户设置失败";
			return this.OPERATE_SUCCESS;
		}
	}
	/**
	 * 查询需要修改的商户管理分值
	 */
	public String findUpManagerValue(){
		try{
			Long merchantId = merman.getMerchantId();
			merman = (InternationalMerchantManager)
			commonService.load(InternationalMerchantManager.class, merman.getId());
			merman.setMerchantId(merchantId);
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction="系统出现异常!";
			return this.OPERATE_SUCCESS;
		}
	}
	/**
	 * 修改商户管理分值
	 * @return
	 */
	public String updateManagerValue(){
		try{
			//如果数据库有该商户的数据则直接修改，如果没有则直接添加
			hql = "FROM InternationalMerchantManager WHERE merchantid="+merman.getMerchantId()+"";
			list = commonService.list(hql);
			if(list.size()==0){
				commonService.save(merman);
			}else{
				InternationalMerchantManager mm = (InternationalMerchantManager)
				commonService.load(InternationalMerchantManager.class, merman.getId());
				mm.setMerchantId(merman.getMerchantId());
				mm.setDayQuota(merman.getDayQuota());
				mm.setMarkValue(merman.getMarkValue());
				mm.setMerchantAddress(merman.getMerchantAddress());
				mm.setMonthQuota(merman.getMonthQuota());
				mm.setPenQuota(merman.getPenQuota());
				commonService.update(mm);
			}
			this.messageAction="商户管理分值修改成功!";
			return this.OPERATE_SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction="商户管理分值修改失败!";
			return this.OPERATE_SUCCESS;
		}
	}
	
	public CommonService getCommonService() {
		return commonService;
	}
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public InternationalMerchantManager getMerman() {
		return merman;
	}
	public void setMerman(InternationalMerchantManager merman) {
		this.merman = merman;
	}
}
