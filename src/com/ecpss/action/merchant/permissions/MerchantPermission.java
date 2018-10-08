package com.ecpss.action.merchant.permissions;

import java.security.MessageDigest;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import sun.misc.BASE64Encoder;

import com.ecpss.action.BaseAction;
import com.ecpss.action.pay.TradeManager;
import com.ecpss.model.permissions.Resource;
import com.ecpss.model.shop.OperaResource;
import com.ecpss.model.shop.ShopOpera;
public class MerchantPermission  extends BaseAction{
	@Autowired
	@Qualifier("tradeManager")
	private TradeManager tradeManager;
	private List opera;
	private ShopOpera  shopOpera;
	public List<Resource> resourcelist;
	private String [] roleresourcecheck;
	public List<OperaResource> roleResource;
    public List<Resource> getResourcelist() {
		return resourcelist;
	}
	public void setResourcelist(List<Resource> resourcelist) {
		this.resourcelist = resourcelist;
	}
	public List getOpera() {
		return opera;
	}
	public void setOpera(List opera) {
		this.opera = opera;
	}
	//跳转到操作员管理页面
	public  String toManagerOpera(){
	  this.opera =this.commonService.list("from ShopOpera t where t.merchantId='"+this.getMerchantBean().getMerchantId()+"' ");
		return this.SUCCESS;
	}
	//跳转到新增操作员
	public String toAddOpera(){
		
		return this.SUCCESS;
	}
	//新增操作员
	public String addOperator() throws Exception{
       int tem=0;
		tem=this.tradeManager.intBySql("select count(*) from shopopera t where t.merchantid='"+this.getMerchantBean().getMerchantId()+"' and t.user_name='"+this.shopOpera.getUserName()+"'");
        if(tem>0){
    		this.messageAction="用户名重复，请换一个用户名！";
    		return this.ERROR;       	
        }		
		//如果用户存在就去验证密码
		MessageDigest md5;
		BASE64Encoder base64en = new BASE64Encoder();
		md5 = MessageDigest.getInstance("MD5");
		String passwords = base64en.encode(md5.digest(shopOpera.getPassword().getBytes("utf-8")));
		this.shopOpera.setPassword(passwords);
		this.shopOpera.setErrorCount(0L);
		this.shopOpera.setLoginTime(new Date());
		this.shopOpera.setModifyPwdTime(new Date());
		this.shopOpera.setMerchantId(this.getMerchantBean().getMerchantId());
		this.commonService.save(this.shopOpera);
		this.messageAction="Add Operator Success!";
		return this.SUCCESS;
	}
	//删除操作员
	public String delOperator(){
        this.commonService.deleteBySql("delete from merchantoperaresource r where r.shopOpera='"+this.shopOpera.getId()+"'");
		this.commonService.delete(shopOpera);
		return this.SUCCESS;
	}
	//配置操作员
	public String operatorResour(){
		this.resourcelist = this.commonService.list("from Resource r where r.numberCode like '20%' ");
		this.roleResource = this.commonService
				.list(" select r from OperaResource r ,ShopOpera  s where r.shopOpera=s.id  and  s.id='"
						+ this.shopOpera.getId() + "'");
		String[] roleresouce = new String[roleResource.size()];
		for (int i = 0; i < roleResource.size(); i++) {
			roleresouce[i] = roleResource.get(i).getResources() + "";
		}
		for (int k = 0; k < resourcelist.size(); k++) {
			int flag = 0;
			for (int j = 0; j < roleresouce.length; j++) {
				if ((resourcelist.get(k).getId() + "").equals(roleresouce[j])) {
					flag++;
				}

			}
			if (flag == 1) {
				this.resourcelist.get(k).setCheckFlag("true");
				flag=0;
			} else {
				flag=0;
				this.resourcelist.get(k).setCheckFlag("false");
			}
		}
		return this.SUCCESS;		
	}
	//配置操作员
	public String operatorResourEn(){
		this.resourcelist = this.commonService.list("from Resource r where r.numberCode like '29%' ");
		this.roleResource = this.commonService
				.list(" select r from OperaResource r ,ShopOpera  s where r.shopOpera=s.id  and  s.id='"
						+ this.shopOpera.getId() + "'");
		String[] roleresouce = new String[roleResource.size()];
		for (int i = 0; i < roleResource.size(); i++) {
			roleresouce[i] = roleResource.get(i).getResources() + "";
		}
		for (int k = 0; k < resourcelist.size(); k++) {
			int flag = 0;
			for (int j = 0; j < roleresouce.length; j++) {
				if ((resourcelist.get(k).getId() + "").equals(roleresouce[j])) {
					flag++;
				}

			}
			if (flag == 1) {
				this.resourcelist.get(k).setCheckFlag("true");
				flag=0;
			} else {
				flag=0;
				this.resourcelist.get(k).setCheckFlag("false");
			}
		}
		return this.SUCCESS;		
	}	
	public String updateOperator(){
        this.commonService.deleteBySql("delete from merchantoperaresource r where r.shopOpera='"+this.shopOpera.getId()+"'");
       if(roleresourcecheck!=null){
        for(int i=0;i<this.roleresourcecheck.length;i++){
        	OperaResource rr=new OperaResource();
        	rr.setResources(Long.valueOf(this.roleresourcecheck[i]));
        	rr.setShopOpera(this.shopOpera.getId());
        	this.commonService.save(rr);
        }
        }
        this.messageAction="保存成功";
		return this.SUCCESS;
	}
	public ShopOpera getShopOpera() {
		return shopOpera;
	}
	public void setShopOpera(ShopOpera shopOpera) {
		this.shopOpera = shopOpera;
	}
	public List<OperaResource> getRoleResource() {
		return roleResource;
	}
	public void setRoleResource(List<OperaResource> roleResource) {
		this.roleResource = roleResource;
	}
	public String[] getRoleresourcecheck() {
		return roleresourcecheck;
	}
	public void setRoleresourcecheck(String[] roleresourcecheck) {
		this.roleresourcecheck = roleresourcecheck;
	}	
}
