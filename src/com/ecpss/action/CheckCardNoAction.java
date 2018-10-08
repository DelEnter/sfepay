package com.ecpss.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.ecpss.action.pay.util.CheckCardNo;
import com.ecpss.util.JsonUtils;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author Administrator
 * 
 */
public class CheckCardNoAction extends BaseAction {
	private String jsonData;

	/*
	 * 验证卡种
	 */
	/**
	 * @return
	 */
	public void getCardTypeByPay() {
		PrintWriter pw = null;
		Map<String, String> m = new HashMap<String, String>();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		try {
			pw = response.getWriter();
			String cardnum = ((String[]) ActionContext.getContext()
					.getParameters().get("cardnum"))[0];
			String merNo = ((String[]) ActionContext.getContext()
					.getParameters().get("merNo"))[0];
			String cardtypename = ((String[]) ActionContext.getContext()
					.getParameters().get("cardtypename"))[0];
			// Long carkType = 0L;

			if (StringUtils.isNotBlank(cardnum)
					&& StringUtils.isNotBlank(merNo)
					&& StringUtils.isNotBlank(cardtypename.toString())) {
				// 验证是否是正确卡号
				if (CheckCardNo.isValid(cardnum)) {
					// 3: jcb
					if (cardtypename.equals("3")) {
						if (cardnum.startsWith("30")
								|| cardnum.startsWith("35")) {
							jsonData = "";
						} else {
							jsonData = "JcbCard Required";
						}
					}
					// 4: visa
					if (cardtypename.equals("4")) {
						if (cardnum.startsWith("4") && cardnum.length() == 16) {
							jsonData = "";
						} else {
							jsonData = "VisaCard Required";
						}
					}
					// 5: master
					if (cardtypename.equals("5")) {
						if (cardnum.startsWith("5") && cardnum.length() == 16) {
							jsonData = "";
						} else {
							jsonData = "MasterCard Required";
						}
					}
					// 6: ae
					if (cardtypename.equals("6")) {
						if ((cardnum.startsWith("34") || cardnum
								.startsWith("37"))) {
							jsonData = "";
						} else {
							jsonData = "AMEX Card Required";
						}
					}
					// 7: dc
					if (cardtypename.equals("7")) {
						if ((cardnum.startsWith("36")
								|| cardnum.startsWith("300")
								|| cardnum.startsWith("305") || cardnum
								.startsWith("38"))
								&& cardnum.length() == 16) {
							jsonData = "";
						} else {
							jsonData = "Dinners Card Required";
						}
					}
				} else {
					jsonData = "Please input valid card number.";
				}
			}
			m.put("jsonData", jsonData);
			pw.print(JsonUtils.toJSONObject(m).toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pw.close();
			pw = null;
		}
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

}
