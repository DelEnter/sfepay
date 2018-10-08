package com.ecpss.web.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class SecurityInterceptor implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void init() {
		// TODO Auto-generated method stub

	}

	public String intercept(ActionInvocation arg0) throws Exception {

		// ��ȡaction�󸽴����?
		String s = arg0.getInvocationContext().getParameters().toString();
		String[] filter = { "\\", "struts2", "apache", "u003", "(", ")", "%",
				"jsp", "asp", "php", "getServlet", "Request", "org",
				"OutputStream", "append", "toString", "classLoader" };
		for (String str : filter) {
			if (s.indexOf(str) > 0) {
				arg0.getInvocationContext().setParameters(null);

				return "timeout";
			}
		}

		return arg0.invoke();
	}

}
