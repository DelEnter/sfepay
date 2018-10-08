package com.ecpss.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecpss.vo.MerchantBean;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class MerchantLoginInterceptor implements Interceptor {

	public void destroy() {
		// TODO Auto-generated method stub
		
	}


	public void init() {
		// TODO Auto-generated method stub
		
	}


	public String intercept(ActionInvocation arg0) throws Exception {

		MerchantBean mb=  (MerchantBean)arg0.getInvocationContext().getSession().get("merchantBean");
		
		if(mb==null){
		return "timeout";	
		}
		return arg0.invoke();
	}

}
