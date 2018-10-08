package com.ecpss.web.interceptor;

import com.ecpss.vo.UserBean;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LoginInterceptor implements Interceptor {

	public void destroy() {
		// TODO Auto-generated method stub
		
	}


	public void init() {
		// TODO Auto-generated method stub
		
	}


	public String intercept(ActionInvocation arg0) throws Exception {
		String actionValue = arg0.getInvocationContext().getParameters()+"";
		UserBean mb=  (UserBean)arg0.getInvocationContext().getSession().get("user");
		if(mb==null||actionValue.toLowerCase().indexOf("getWriter".toLowerCase())>=0||actionValue.toLowerCase().indexOf("getRealPath".toLowerCase())>=0||actionValue.toLowerCase().indexOf("Servlet".toLowerCase())>=0||actionValue.toLowerCase().indexOf("struts2".toLowerCase())>=0||actionValue.toLowerCase().indexOf("OutputStream".toLowerCase())>=0){
			if(mb!=null){
				arg0.getInvocationContext().setParameters(null);
				System.out.println("method : "+ actionValue);
			}
			return "timeout";	
		}
		return arg0.invoke();
	}

}
