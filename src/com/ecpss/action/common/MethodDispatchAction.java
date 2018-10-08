package com.ecpss.action.common;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.Action;

/**
 *
 * @author 
 */
public class MethodDispatchAction implements Action {
	private static Log log = LogFactory.getLog(MethodDispatchAction.class);
	protected String method;
	public String execute() {
		try {
			if (StringUtils.isEmpty(method)) {
				method = "doDefault";
			}
			beforeMethodInovke(method);
			log.debug("invoke action:"+this.getClass().getName()+",method:" + method);
			String ret = (String) MethodUtils.invokeMethod(this, method, new Object[] {});
			afterMethodInovke(method);
			return ret;
		} catch (Exception e) {
			exceptionMethodInovke(e,method);
			throw new RuntimeException(e);
		}
	}

	protected void beforeMethodInovke(String method){

	}
	protected void afterMethodInovke(String method){

	}
	protected void exceptionMethodInovke(Exception e ,String method){

	}
	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}
	/**
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}

}
