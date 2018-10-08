package com.ecpss.web.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

@SuppressWarnings("serial")
public class PageTag extends ComponentTagSupport {

	@Override
	public Component getBean(ValueStack arg0, HttpServletRequest arg1,
			HttpServletResponse arg2) {
		// TODO Auto-generated method stub
		return new Page(arg0);
	}
	protected String styleClass = "";
	
	protected String formName = "";
	protected String beanName = "froms(0)";
	protected String javaScript = "";
	protected String value;
	public String getBeanName() {
		return beanName;
	}
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public String getJavaScript() {
		return javaScript;
	}
	public void setJavaScript(String javaScript) {
		this.javaScript = javaScript;
	}
	public String getStyleClass() {
		return styleClass;
	}
	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	protected void populateParams() {
		super.populateParams();
		Page page  =(Page)component;
		page.setBeanName(beanName);
		page.setFormName(formName);
		page.setJavaScript(javaScript);
		page.setStyleClass(styleClass);
		page.setValue(value);
	}
	
	
	
	
	

}
