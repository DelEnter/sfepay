package com.ecpss.service.iservice;

import java.util.List;
import java.util.Map;

import com.ecpss.model.permissions.Resource;
import com.ecpss.model.permissions.Role;
import com.ecpss.model.shop.InternationalMerchant;

public interface PermissionsService {
    public List<Resource> getResource();
    public List<Role> getRoleList() ;
    public void addRole(Role role);
	public List getRoleUser();
	//public void setTelement(Map map,String remark,Double riskRmbmoney,Double channelFee,Double rmbmoney);
	public void setTelement(Map map,String remark);
	public void passSettlement(Long beatchNo);
	public void noPassSettlement(Long beatchNo);
	public void createBailMoney(StringBuffer sb1,List list,InternationalMerchant merchant );
}
