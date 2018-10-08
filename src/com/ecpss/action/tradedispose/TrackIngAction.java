package com.ecpss.action.tradedispose;

import java.util.List;

import com.ecpss.action.BaseAction;
import com.ecpss.model.shop.InternationalMerchant;

public class TrackIngAction extends BaseAction {
	private String merchantno = "";
	private String flag = "";
	private String contentflag = "";
	private String kefuflag = "";
	private String successtomerchant="";
	private String showrate="";
	private String chargebacktomerchant="";
	private String failureEVIP="";
	private String dccmessage;
	public String getDccmessage() {
		return dccmessage;
	}

	public void setDccmessage(String dccmessage) {
		this.dccmessage = dccmessage;
	}

	public String getMerchantno() {
		return merchantno;
	}

	public void setMerchantno(String merchantno) {
		this.merchantno = merchantno;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String toTrack() {

		return "success";
	}

	public String toupdateTrack() {
		if (!merchantno.equals("")) {
			List<InternationalMerchant> merchan = this.commonService
					.list(" from   InternationalMerchant t where t.merno='"
							+ this.merchantno + "' ");
			if (merchan.size() > 0) {
				this.flag = merchan.get(0).getStatutes().substring(0, 1);
				this.contentflag = merchan.get(0).getStatutes().substring(1, 2);
				this.kefuflag = merchan.get(0).getStatutes().substring(2, 3);
				this.successtomerchant = merchan.get(0).getStatutes().substring(4, 5);
				this.showrate = merchan.get(0).getStatutes().substring(3, 4);
				this.chargebacktomerchant = merchan.get(0).getStatutes().substring(5, 6);
				
				this.failureEVIP = merchan.get(0).getStatutes().substring(6,7);
				this.dccmessage = merchan.get(0).getStatutes().substring(7,8);
				this.merchantno = merchan.get(0).getMerno().toString();
			} else {
				this.merchantno = "不存在该商户号";
			}
		}

		return "success";
	}

	public String updateTrack() {
		if (flag.trim().equals("1") || flag.trim().equals("0")) {
			this.commonService
					.deleteBySql("update International_Merchant t set t.statutes='"
							+ flag
							+ "'||substr(t.statutes,2, length(t.statutes)-1) where t.merno='"
							+ this.merchantno + "'");
			
		}
		if (contentflag.trim().equals("1") || contentflag.trim().equals("0")) {
			this.commonService
					.deleteBySql("update International_Merchant t set t.statutes=substr(t.statutes,1,1)||'"
							+ contentflag
							+ "'||substr(t.statutes,3) where t.merno='"
							+ this.merchantno + "'");
	
		}
		if (kefuflag.trim().equals("1") || kefuflag.trim().equals("0")) {
			this.commonService
			.deleteBySql("update International_Merchant t set t.statutes=substr(t.statutes,1,2)||'"
					+ kefuflag
					+ "'||substr(t.statutes,4) where t.merno='"
					+ this.merchantno + "'");
			
		}
		if (successtomerchant.trim().equals("1") || successtomerchant.trim().equals("0")) {
			this.commonService
			.deleteBySql("update International_Merchant t set t.statutes=substr(t.statutes,1,4)||'"
					+ successtomerchant
					+ "'||substr(t.statutes,6) where t.merno='"
					+ this.merchantno + "'");
			
		}
		if (showrate.trim().equals("1") || showrate.trim().equals("0")) {
			this.commonService
			.deleteBySql("update International_Merchant t set t.statutes=substr(t.statutes,1,3)||'"
					+ showrate
					+ "'||substr(t.statutes,5) where t.merno='"
					+ this.merchantno + "'");
			
		}
		if (chargebacktomerchant.trim().equals("1") || chargebacktomerchant.trim().equals("0")) {
			this.commonService
			.deleteBySql("update International_Merchant t set t.statutes=substr(t.statutes,1,5)||'"
					+ chargebacktomerchant
					+ "'||substr(t.statutes,7) where t.merno='"
					+ this.merchantno + "'");
			
		}		
		if (this.failureEVIP.trim().equals("1") || failureEVIP.trim().equals("0")) {
			this.commonService
			.deleteBySql("update International_Merchant t set t.statutes=substr(t.statutes,1,6)||'"
					+ failureEVIP
					+ "'||substr(t.statutes,8) where t.merno='"
					+ this.merchantno + "'");
			
		}		
		if (this.dccmessage.trim().equals("1") || this.dccmessage.trim().equals("0")) {
			this.commonService
			.deleteBySql("update International_Merchant t set t.statutes=substr(t.statutes,1,7)||'"
					+ dccmessage
					+ "'||substr(t.statutes,9) where t.merno='"
					+ this.merchantno + "'");
			
		}		
		this.dccmessage=dccmessage;
		this.merchantno = merchantno;
		this.contentflag = contentflag;
		this.flag = flag;
		this.kefuflag = kefuflag;
		this.successtomerchant=successtomerchant;
		this.showrate=showrate;
		this.chargebacktomerchant=chargebacktomerchant;
		this.failureEVIP=failureEVIP;

		return "success";

	}

	public String getContentflag() {
		return contentflag;
	}

	public void setContentflag(String contentflag) {
		this.contentflag = contentflag;
	}

	public String getKefuflag() {
		return kefuflag;
	}

	public void setKefuflag(String kefuflag) {
		this.kefuflag = kefuflag;
	}

	public String getSuccesstomerchant() {
		return successtomerchant;
	}

	public void setSuccesstomerchant(String successtomerchant) {
		this.successtomerchant = successtomerchant;
	}

	public String getShowrate() {
		return showrate;
	}

	public void setShowrate(String showrate) {
		this.showrate = showrate;
	}

	public String getChargebacktomerchant() {
		return chargebacktomerchant;
	}

	public void setChargebacktomerchant(String chargebacktomerchant) {
		this.chargebacktomerchant = chargebacktomerchant;
	}

	public String getFailureEVIP() {
		return failureEVIP;
	}

	public void setFailureEVIP(String failureEVIP) {
		this.failureEVIP = failureEVIP;
	}

}
