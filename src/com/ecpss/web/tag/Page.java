package com.ecpss.web.tag;

import java.io.IOException;
import java.io.Writer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.components.Component;

import com.ecpss.web.PageInfo;
import com.opensymphony.xwork2.util.ValueStack;

public class Page extends Component {

	private static final Log	logger	= LogFactory.getLog(Page.class);
	public Page(ValueStack arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	
	protected String styleClass = "";
	
	protected String formName = "" ;
	
	protected String beanName = "" ;
	
	protected String javaScript = "" ;
	
	protected String value;

	@Override
	public boolean start(Writer writer) {
		boolean result = super.start(writer);
		PageInfo pi = (PageInfo)this.getStack().findValue(value);
		String strFirstPage = "First";
		String strLastPage = "Last";
		String strNextPage = "Next";
		String strPrevPage = "Previous";
		String strCurrentPage = "  ";
		String strGoto = "To£º";
		StringBuffer sb = new StringBuffer();
		sb.append("<script language = 'JavaScript'> \r\n");
		sb.append("<!-- \r\n");
		sb.append("function ");
		sb.append(beanName);
		sb.append("_Jumping(i){\r\n\t");
		sb.append("document.getElementById('");
		sb.append(beanName);
		sb.append("_curPage').value = i ;\r\n\t");
		sb.append("document.");
		sb.append(formName);
		sb.append(".submit();\r\n\t");
		sb.append("return ; \r\n\t");
		sb.append("}\r\n\t");
		sb.append("function ");
		sb.append(beanName);
		sb.append("_gotoPage(pagenum){\r\n\t");
		sb.append("document.getElementById('");
		sb.append(beanName);
		sb.append("_curPage').value = pagenum;\r\n\t");
		sb.append("document.");
		sb.append(formName);
		sb.append(".submit();\r\n\t");
		sb.append("return ; \r\n\t");
		sb.append("}\r\n\t");
		sb.append("-->\r\n\t");
		sb.append("</script>\r\n\t");
		sb.append("<table border = '0' align = 'center' width='700' >\r\n\t");
		sb.append("<tr>\r\n\t<td>\r\n\t");
		sb.append("<b>Total <font color = 'blue'>");
		sb.append(pi.getTotalCount());
		sb.append(" </b>\r\n\t");
		if(pi.getTotalPageCount()==1 || pi.getTotalPageCount() == 0){
			sb.append("\t<font face=webdings color=blue>9</font><font color=blue><b>");
			sb.append(strFirstPage);
			sb.append("</b></font>\r\n\t");
			sb.append("\t<font face=webdings color=blue>7</font><font color=blue><b>");
			sb.append(strPrevPage);
			sb.append("</b></font>\r\n\t");
			sb.append("\t<font color=bule><b>");
			sb.append(strNextPage);
			sb.append("</b></font><font face=webdings color=blue>8</font>\r\n\t");
			sb.append("\t<font color=bule><b>");
			sb.append(strLastPage);
			sb.append("</b></font><font face=webdings color=blue>:</font>\r\n\t");
		} else if (pi.getTotalPageCount() > 1 && pi.getCurPage() == 1) {
			sb.append("\t<font face=webdings color=blue>9</font><font color=blue><b>");
			sb.append(strFirstPage);
			sb.append("</b></font>\r\n\t");
			sb.append("\t<font face=webdings color=blue>7</font><font color=blue><b>");
			sb.append(strPrevPage);
			sb.append("</b></font>\r\n\t");
			sb.append("\t<a href=\"javascript:");
			sb.append(beanName);
			sb.append("_gotoPage(");
			sb.append((pi.getCurPage() + 1));
			sb.append(")\"><font color=black><b>");
			sb.append(strNextPage);
			sb.append("</b></font></a><font face=webdings>8</font>\r\n\t");
			sb.append("\t<a href=\"javascript:");
			sb.append(beanName);
			sb.append("_gotoPage(");
			sb.append(pi.getTotalPageCount());
			sb.append(")\"><font color=black><b>");
			sb.append(strLastPage);
			sb.append("</b></font></a><font face=webdings>:</font>\r\n\t");
		} else if (pi.getTotalPageCount() > 1 && pi.getCurPage() < pi.getTotalPageCount()) {
			sb.append("\t<font face=webdings>9</font><a href=\"javascript:");
			sb.append(beanName);
			sb.append("_gotoPage(1)\"><font color=black><b>");
			sb.append(strFirstPage);
			sb.append("</b></font></a>\r\n\t");
			sb.append("\t<font face=webdings>7</font><a href=\"javascript:");
			sb.append(beanName);
			sb.append("_gotoPage(");
			sb.append(pi.getCurPage() - 1);
			sb.append(")\"><font color=black><b>");
			sb.append(strPrevPage);
			sb.append("</b></font></a>\r\n\t");
			sb.append("\t<a href=\"javascript:");
			sb.append(beanName);
			sb.append("_gotoPage(");
			sb.append(pi.getCurPage() + 1);
			sb.append(")\"><font color=black><b>");
			sb.append(strNextPage);
			sb.append("</b></font></a><font face=webdings>8</font>\r\n\t"); 
			sb.append("\t<a href=\"javascript:");
			sb.append(beanName);
			sb.append("_gotoPage(");
			sb.append(pi.getTotalPageCount());
			sb.append(")\"><font color=black><b>");
			sb.append(strLastPage);
			sb.append("</b></font></a><font face=webdings>:</font>\r\n\t");
		} else if (pi.getTotalPageCount() > 1 && pi.getCurPage() == pi.getTotalPageCount()) {
			sb.append("\t<font face=webdings>9</font><a href=\"javascript:");
			sb.append(beanName);
			sb.append("_gotoPage(1)\"><font color=black><b>");
			sb.append(strFirstPage);
			sb.append("</b></font></a>\r\n\t");
			sb.append("\t<font face=webdings>7</font><a href=\"javascript:");
			sb.append(beanName);
			sb.append("_gotoPage(");
			sb.append(pi.getCurPage() - 1);
			sb.append(")\"><font color=black><b>");
			sb.append(strPrevPage);
			sb.append("</b></font></a>\r\n\t");
			sb.append("\t<font color=blue><b>");
			sb.append(strNextPage);
			sb.append("</b></font><font face=webdings color=blue>8</font>\r\n\t");
			sb.append("\t<font color=blue><b>");
			sb.append(strLastPage);
			sb.append("</b></font><font face=webdings color=blue>:</font>\r\n\t");
		}
		sb.append("\t <font color=black ><b>");
		sb.append(strGoto);
		sb.append("</b></font>\r\n\t<select id = '");
		sb.append(beanName);
		sb.append("_curPage' name = '");
		sb.append(beanName);
		sb.append(".curPage' onchange='");
		sb.append(beanName);
		sb.append("_Jumping(this.value);'>");
		for(int i = 1; i <= pi.getTotalPageCount(); i ++ ){
			if(i==pi.getCurPage()){
				sb.append("\r\n\t<option selected value = " + i + " >The " + i + "Page</option>");
			}else{
				sb.append("\r\n\t<option value = " + i + " >The " + i + " Page</option>");
			}
		}
		sb.append("\r\n\t</select>\r\n\t");
		sb.append("\t <font color =black><b>");
		sb.append(strCurrentPage);
		sb.append("\uFF1A</b></font><font color=blue><b>");
		sb.append(pi.getCurPage());
		sb.append("</b></font>\r\n\t\t<font color=blue><b>/</b></font>\r\n\t\t<font color=blue><b>");
		sb.append(pi.getTotalPageCount());
		sb.append("</b></font><font color=blue><b>  </b></font>");
		sb.append("\r\n\t<td>\r\n<tr>\r\n\r\n</table>");
		try {
			writer.write(sb.toString());
		} catch (IOException e) {
			logger.error(e);
		}
		return result;
	}

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
	
	
	

}
