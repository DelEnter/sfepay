package com.ecpss.action.permissions;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import sun.misc.BASE64Encoder;

import com.ecpss.action.BaseAction;
import com.ecpss.model.channel.InternationalChannels;
import com.ecpss.model.log.SystemLog;
import com.ecpss.model.permissions.Resource;
import com.ecpss.model.permissions.Role;
import com.ecpss.model.permissions.RoleResource;
import com.ecpss.model.permissions.RoleUser;
import com.ecpss.model.user.User;
import com.ecpss.service.iservice.PermissionsService;
import com.ecpss.vo.UserBean;
import com.opensymphony.xwork2.ActionContext;

public class PermissionsAction extends BaseAction {
    private UserBean userBean=new UserBean();	
	private String [] roleresourcecheck;
	private List<User> user;
	private List listOne;
	private List listTwo;
	private User users;
	private RoleUser roleUser;
	private List<Role> rolelist;
	public List<Resource> resourcelist;
	public List<RoleResource> roleResource;
	public Resource resouce;
	private String vercode;
	public Resource showresouce;
	public List<Resource> resourcelist2;
	@Autowired
	@Qualifier("permissionsService")
	private PermissionsService permissionsService;
	private Role showrole;
	private Role role;
	public List<InternationalChannels> channelList;
	private String vilify;	
	public String getVilify() {
		return vilify;
	}

	public void setVilify(String vilify) {
		this.vilify = vilify;
	}	
	
	
	public String loginSys() throws Exception{
		//获取验证码
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String ver2 = (String)session.getAttribute("rand");
		//如果用户存在就去验证密码
		
		MessageDigest md5;
		BASE64Encoder base64en = new BASE64Encoder();
		md5 = MessageDigest.getInstance("MD5");
		String passwords = base64en.encode(md5.digest(users.getPassword().getBytes("utf-8")));
		//商户后台密码加密整改 2015-04-13 jiahui
//		String passwords=getSha256(users.getPassword());
		
		//先验证用户是否存在 
		String hql = "from User u where u.userName='"+users.getUserName().trim()+"' ";
		User u = (User) commonService.uniqueResult(hql);
		if(u!=null){
			/// hql1 查询出当前登陆用户错误次数并且上次错误时间小与当前30分钟
			String hql1 = "from User u where u.userName='"+users.getUserName()+"' " +
					"and u.errorCount>=6 " +
					"and round(to_number(sysdate-u.loginTime)*1440)<30";
			int h = this.commonService.list(hql1).size();
			if(h==1){
				//错误次数超过5次
				this.messageAction="对不起,你输入错误密码次数过多,请30分钟以后再次登陆.";
				return "error";
			}else if(ver2!=vercode && !ver2.equals(vercode)){
				this.messageAction="对不起,你输入的验证码不正确,请重新输入.";
				return "error";
			}else{
				//如果用户名存在,验证密码
				int a =this.commonService.list("from User u where u.userName='"+users.getUserName().trim()+"' and u.password='"+passwords+"' ").size();
				if(a==1){
					//系统后台用户首次登陆修改密码
					if("jiahui".equals(u.getFullName())){
						this.messageAction="用户首次登陆，请修改密码！";
						return INPUT;
					}
					this.users=(User)this.commonService.list("from User u where u.userName='"+users.getUserName().trim()+"' and u.password='"+passwords+"'").get(0);
					Role role = (Role) this.commonService.uniqueResult("select ru.role from RoleUser ru where ru.user.id="+users.getId());
					
					this.userBean=new UserBean();
					this.userBean.setId(users.getId());
					this.userBean.setUserName(users.getUserName());
					if(role!=null){
						if(StringUtils.isNotBlank(role.getRoleName())){
							this.userBean.setRoleName(role.getRoleName());
						}else{
							this.userBean.setRoleName("超级管理员");
						}
					}else{
						this.userBean.setRoleName("超级管理员");
					}
					ActionContext.getContext().getSession().put("user", this.userBean);
					
					
					Date sdate=new Date();
					Date mdate=new Date();
					Long sd = sdate.getTime() / 86400000;
					if(users.getModifyPwdTime()!=null){
						mdate = users.getModifyPwdTime();
					}
					Long md = mdate.getTime() / 86400000;
					if(sd - md > 90){
						this.messageAction="您已经超过90天未修改过密码,请重新修改密码后登陆.谢谢.";
						
						return INPUT;
					}
					
					
					//如果登陆成功清除登陆次数,更新登陆时间
					users.setErrorCount(0L);
					users.setLoginTime(new Date());
					this.commonService.update(users);
					
					SystemLog sl=new SystemLog();
					sl.setMerno("管理员 ");
					sl.setOperTime(new Date());
					sl.setRemarks("管理员用角色为："+this.getUserBean().getRoleName()+"，登录用户名为："+this.getUserBean().getUserName()+"登录时间为:"+new Date()+"IP地址是："+this.getIpAddr(request));
					sl.setRescReow("管理员登陆");
					sl.setOperType("7");
					sl.setUserName(this.getUserBean().getUserName());
					this.commonService.save(sl);
//				this.role=this.commonService.list("from Role u where u.userName= and u.password=")
				}else{
					//密码不正确
					u.setLoginTime(new Date());
					u.setErrorCount(u.getErrorCount()+1L);
					this.commonService.update(u);
					this.messageAction="第"+u.getErrorCount()+"次密码或USB-KEY验证错误.输入次数超过6次将锁定30分钟.";
					return "error";
				}
			}
		}else{
			//用户名不存在
			this.messageAction="用户名不存在.";
			return "error";
		}
		return "success";
	}
	
	//获取系统菜单
	public String getSysMenu() {
   this. userBean	=(UserBean)ActionContext.getContext().getSession().get("user");
        if(userBean.getUserName().equals("admin")){
    		this.resourcelist = this.commonService.getByList("select so.FATHERNUMBER,so.RESOURCEURL,so.MENUNAME from  systemresource so where 1=1  order by so.FATHERNUMBER");
       	
        }else{
    		this.resourcelist = this.commonService.getByList("select so.FATHERNUMBER,so.RESOURCEURL,so.MENUNAME from  systemresource so where so.ID in ( select rs.RESOURCE_ID from  roleresource rs where rs.ROLE_ID=(select r.ROLE_ID from roleuser r where r.USER_ID='"+userBean.getId()+"') )  order by so.FATHERNUMBER");
       	
        }
		return "success";
	}
	
	private String username;
	private String pwd;
	private String name;
	
    //用户注册
	public String regeditUser() throws Exception {
		User u = new User();
		MessageDigest md5;
		BASE64Encoder base64en = new BASE64Encoder();
		md5 = MessageDigest.getInstance("MD5");
		String passwords = base64en.encode(md5.digest(pwd.getBytes("utf-8")));
		u.setPassword(passwords);
		u.setFullName(name);
		u.setUserName(username);
		u.setErrorCount(0L);
		u.setLoginTime(new Date());
		u.setModifyPwdTime(new Date());
		this.commonService.save(u);
		return "success";

	}

	public String checkUserName() {
		return SUCCESS;
	}

	// 角色列表
	public String toRole() {
		this.rolelist = this.permissionsService.getRoleList();
		this.resourcelist2 = this.commonService.list("from Resource ");
		this.getLoaction().setReload(true);
		return "success";
	}

	// 跳转到新增角色
	public String toaddRole() {

		return "success";
	}

	// 新增角色
	public String addrole() {
		this.permissionsService.addRole(this.role);
		this.messageAction = "新增角色成功";
		return this.OPERATE_SUCCESS;
	}

	// 跳转到更新角色
	public String toUpdateRole() {
		this.showrole = (Role) this.commonService.load(Role.class,
				this.showrole.getId());
		return SUCCESS;
	}

	// 更新角色
	public String updateRole() {
		System.out.println(this.showrole.getId());
		this.role = (Role) this.commonService.load(Role.class, this.showrole
				.getId());
		this.role.setRoleName(this.showrole.getRoleName());
		this.commonService.saveOrUpdate(role);
		this.messageAction = "修改角色成功";
		return this.OPERATE_SUCCESS;
	}

	// 删除角色
	public String deleteRole() {
		this.commonService.deleteBySql(" delete from roleuser r where r.ROLE_ID='"
						+ this.showrole.getId() + "'");
		this.commonService.deleteBySql(" delete from ROLERESOURCE r where r.ROLE_ID='"
				+ this.showrole.getId() + "'");		
		this.commonService.delete(this.showrole);
		this.messageAction = "删除资源成功";
		return this.OPERATE_SUCCESS;

	}

	// 跳转到新增资源
	public String toaddResouce() {

		return SUCCESS;
	}

	// 新增资源
	public String addResouce() {
		this.commonService.save(resouce);
		this.messageAction = "新增资源成功";
		return this.OPERATE_SUCCESS;
	}

	// 跳转到修改资源
	public String toUpdateResource() {
		this.showresouce = (Resource) this.commonService.load(Resource.class,
				this.resouce.getId());
		return this.SUCCESS;
	}

	// 修改资源
	public String updateResouce() {
		this.resouce = (Resource) this.commonService.load(Resource.class,
				this.showresouce.getId());
		this.resouce.setResourceUrl(this.showresouce.getResourceUrl());
		this.resouce.setMenuName(this.showresouce.getMenuName());
		this.resouce.setFatherNumber(this.showresouce.getFatherNumber());
		this.messageAction = "修改资源成功";
		this.commonService.update(this.resouce);
		return this.OPERATE_SUCCESS;
	}

	// 删除资源
	public String deleteResouce() {
		this.commonService.deleteBySql(" delete from ROLERESOURCE r where r.RESOURCE_ID='"
				+ this.resouce.getId() + "'");
		this.commonService.delete(resouce);
		this.messageAction = "删除资源成功";
		return this.OPERATE_SUCCESS;
	}

	// 跳转到配置用户
	public String tosetUserRole() {
		// select e.USER_NAME, w.ROLENAME from employee e left join roleuser r
		// on e.id=r.user_id ,systemrole w where w.id=r.role_id
		// this.user=this.commonService.list("select u ,ru from User u left join
		// RoleUser ru on u.id=ru.user.id");
		this.listOne = this.commonService
				.getByList("select e.USER_NAME,e.ID, w.ROLENAME ,W.ID from employee e left join  roleuser r  on e.id=r.user_id ,systemrole w where w.id=r.role_id");

		this.listTwo = this.commonService
				.getByList("select e.USER_NAME, e.ID  from employee e   where e.ID  not in (select r.USER_ID from roleuser r)");
		return this.SUCCESS;
	}

	// 跳转到配置用户角色
	public String tosetUserRoleFinish() {
		this.users = (User) this.commonService.load(User.class, users.getId());
		if (this.roleUser == null) {
			this.roleUser = new RoleUser();
		}
		if (this.commonService.list(
				"from RoleUser r where r.user.id='" + users.getId() + "'")
				.size() > 0) {
			this.roleUser = (RoleUser) this.commonService.list(
					"from RoleUser r where r.user.id='" + users.getId() + "'")
					.get(0);
		}
		this.rolelist = this.commonService.list("from Role ");

		return this.SUCCESS;
	}

	// 配置角色
	public String setUserRole() {

		this.roleUser.setUser(this.users);

		this.commonService.saveOrUpdate(roleUser);
		this.messageAction = "配置成功";
		return this.OPERATE_SUCCESS;
	}

	// 删除管理员账号
	public String deleteUser() {
		this.commonService
				.deleteBySql(" delete from roleuser r where r.user_id='"
						+ this.users.getId() + "'");
		this.commonService.deleteBySql(" delete from employee r where r.id='"
				+ this.users.getId() + "'");
		this.messageAction = "删除成功";
		return this.OPERATE_SUCCESS;
	}

	// 跳转到配置角色资源页面
	public String toSetRoleSource() {
		this.resourcelist = this.commonService.list("from Resource");
		this.roleResource = this.commonService
				.list("from RoleResource r where r.role.id='"
						+ this.role.getId() + "'");
		String[] roleresouce = new String[roleResource.size()];
		for (int i = 0; i < roleResource.size(); i++) {
			roleresouce[i] = roleResource.get(i).getResource().getId() + "";
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
    public String updateRoleResource(){
        this.commonService.deleteBySql("delete from roleresource r where r.ROLE_ID='"+this.role.getId()+"'");
        for(int i=0;i<this.roleresourcecheck.length;i++){
        	RoleResource rr=new RoleResource();
        	Resource  ros=   new Resource();
        	ros.setId(Long.valueOf(this.roleresourcecheck[i]));
        	rr.setResource(ros);
        	rr.setRole(this.role);
        	this.commonService.save(rr);
        }
        this.messageAction="保存成功";
    	return this.OPERATE_SUCCESS;
    }
    public static String getSha256(String strData) {
		 String output = "";
	     try {
	       MessageDigest digest = MessageDigest.getInstance("SHA-256");
	       byte[] hash = digest.digest(strData.getBytes("UTF-8"));
	       output = Hex.encodeHexString(hash);
	       System.out.println(output);
	      } catch (Exception e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	      }
	    return output;
	}
	public String SetRoleSource() {

		return this.OPERATE_SUCCESS;
	}

	public List<Role> getRolelist() {
		return rolelist;
	}

	public void setRolelist(List<Role> rolelist) {
		this.rolelist = rolelist;
	}

	public List<Resource> getResourcelist() {
		return resourcelist;
	}

	public void setResourcelist(List<Resource> resourcelist) {
		this.resourcelist = resourcelist;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<InternationalChannels> getChannelList() {
		return channelList;
	}

	public void setChannelList(List<InternationalChannels> channelList) {
		this.channelList = channelList;
	}

	public Resource getResouce() {
		return resouce;
	}

	public void setResouce(Resource resouce) {
		this.resouce = resouce;
	}

	public List<Resource> getResourcelist2() {
		return resourcelist2;
	}

	public void setResourcelist2(List<Resource> resourcelist2) {
		this.resourcelist2 = resourcelist2;
	}

	public Resource getShowresouce() {
		return showresouce;
	}

	public void setShowresouce(Resource showresouce) {
		this.showresouce = showresouce;
	}

	public Role getShowrole() {
		return showrole;
	}

	public void setShowrole(Role showrole) {
		this.showrole = showrole;
	}

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	public List getListOne() {
		return listOne;
	}

	public void setListOne(List listOne) {
		this.listOne = listOne;
	}

	public List getLostTwo() {
		return listTwo;
	}

	public void setLostTwo(List lostTwo) {
		this.listTwo = lostTwo;
	}

	public List getListTwo() {
		return listTwo;
	}

	public void setListTwo(List listTwo) {
		this.listTwo = listTwo;
	}

	public RoleUser getRoleUser() {
		return roleUser;
	}

	public void setRoleUser(RoleUser roleUser) {
		this.roleUser = roleUser;
	}

	public List<RoleResource> getRoleResource() {
		return roleResource;
	}

	public void setRoleResource(List<RoleResource> roleResource) {
		this.roleResource = roleResource;
	}

	public String[] getRoleresourcecheck() {
		return roleresourcecheck;
	}

	public void setRoleresourcecheck(String[] roleresourcecheck) {
		this.roleresourcecheck = roleresourcecheck;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public String getVercode() {
		return vercode;
	}

	public void setVercode(String vercode) {
		this.vercode = vercode;
	}
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		// System.out.println("ip----------------"+ip);
		return ip;

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}
