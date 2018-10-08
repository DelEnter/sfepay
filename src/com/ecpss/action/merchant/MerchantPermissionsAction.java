package com.ecpss.action.merchant;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ecpss.action.BaseAction;
import com.ecpss.model.permissions.Resource;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.model.shop.ShopOpera;
import com.ecpss.service.iservice.MerchantManagerService;
import com.opensymphony.xwork2.ActionContext;

public class MerchantPermissionsAction extends BaseAction {
	
	@Autowired
	@Qualifier("merchantManagerService")
	private MerchantManagerService merchantManagerService;
	
	public List<Resource> resourcelist;
	public Resource resource;

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public List<Resource> getResourcelist() {
		return resourcelist;
	}

	public void setResourcelist(List<Resource> resourcelist) {
		this.resourcelist = resourcelist;
	}

	public String indexMenu() {
		
		String username = getMerchantBean().getMerchantUserName();
		Long merno = getMerchantBean().getMerChantNo();
		InternationalMerchant m1 = this.merchantManagerService.getMerchantUser(merno, username,null);
		if(m1!=null){
			this.resourcelist = this.commonService
			.getByList("select so.numbercode,so.RESOURCEURL,so.MENUNAME,so.ID from  systemresource so where so.numbercode like '20%' order by so.numbercode ");
			//resource = (Resource) ActionContext.getContext().getSession().get("resource");
		}else{
			ShopOpera shopopera1 = this.merchantManagerService.getMerchantOpera(merno, username, null);
			String operaResource="select so.numbercode,so.RESOURCEURL,so.MENUNAME,so.ID from merchantoperaresource t, systemresource so where t.shopopera='"+shopopera1.getId()+"' and t.resources=so.id and  so.numbercode like '20%' order by so.numbercode";
			this.resourcelist = this.commonService
			.getByList(operaResource);
		}
		
		
		if (this.resource!= null) {
			this.resource = (Resource) this.commonService.load(Resource.class,
					this.resource.getId());
			getHttpServletRequest().getSession().setAttribute("resource", resource);
		}
		return "success";
	}
	public String indexMenuen() {
		
		String username = getMerchantBean().getMerchantUserName();
		Long merno = getMerchantBean().getMerChantNo();
		InternationalMerchant m1 = this.merchantManagerService.getMerchantUser(merno, username,null);
		if(m1!=null){
			this.resourcelist = this.commonService
			.getByList("select so.numbercode,so.RESOURCEURL,so.MENUNAME,so.ID from  systemresource so where so.numbercode like '29%' order by so.numbercode ");
			//resource = (Resource) ActionContext.getContext().getSession().get("resource");
		}else{
			ShopOpera shopopera1 = this.merchantManagerService.getMerchantOpera(merno, username, null);
			String operaResource="select so.numbercode,so.RESOURCEURL,so.MENUNAME,so.ID from merchantoperaresource t, systemresource so where t.shopopera='"+shopopera1.getId()+"' and t.resources=so.id and  so.numbercode like '29%' order by so.numbercode";
			this.resourcelist = this.commonService
			.getByList(operaResource);
		}
		
		
		if (this.resource!= null) {
			this.resource = (Resource) this.commonService.load(Resource.class,
					this.resource.getId());
			getHttpServletRequest().getSession().setAttribute("resource", resource);
		}
		return "success";
	}	
	public static HttpServletRequest getHttpServletRequest(){
		return (HttpServletRequest)ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	}
}
