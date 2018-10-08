package vpn;

public class YinlianMessage {
	//交易提交数据
	private String TrnxDatetime;
	private String CardNo;
	private String Amt;
	private String PosFlwNo;
	private String TermId;
	private String MerchId;
	private String Cvv2;
	private String ExpiredDate;
	private String CredentialType;
	private String CredentialNO;
	private String Name;
	//退款提交数据
	private String ReferenceNo;
	private String AuthResCode;
	private String ResCode;
	private String OriginalPosNo;
	private String OriginalDate;
	//返回数据
	private String res_cardNo;
	private String res_amt;
	private String res_posFlwNo;
	private String res_settlementDate;
	private String res_referenceNo;
	private String res_authResCode;
	private String res_resCode;
	private String res_termId;
	private String res_merchId;
	public String getTrnxDatetime() {
		return TrnxDatetime;
	}
	public void setTrnxDatetime(String trnxDatetime) {
		TrnxDatetime = trnxDatetime;
	}
	public String getCardNo() {
		return CardNo;
	}
	public void setCardNo(String cardNo) {
		CardNo = cardNo;
	}
	public String getAmt() {
		return Amt;
	}
	public void setAmt(String amt) {
		Amt = amt;
	}
	public String getPosFlwNo() {
		return PosFlwNo;
	}
	public void setPosFlwNo(String posFlwNo) {
		PosFlwNo = posFlwNo;
	}
	public String getTermId() {
		return TermId;
	}
	public void setTermId(String termId) {
		TermId = termId;
	}
	public String getMerchId() {
		return MerchId;
	}
	public void setMerchId(String merchId) {
		MerchId = merchId;
	}
	public String getCvv2() {
		return Cvv2;
	}
	public void setCvv2(String cvv2) {
		Cvv2 = cvv2;
	}
	public String getExpiredDate() {
		return ExpiredDate;
	}
	public void setExpiredDate(String expiredDate) {
		ExpiredDate = expiredDate;
	}
	public String getCredentialType() {
		return CredentialType;
	}
	public void setCredentialType(String credentialType) {
		CredentialType = credentialType;
	}
	public String getCredentialNO() {
		return CredentialNO;
	}
	public void setCredentialNO(String credentialNO) {
		CredentialNO = credentialNO;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getRes_cardNo() {
		return res_cardNo;
	}
	public void setRes_cardNo(String res_cardNo) {
		this.res_cardNo = res_cardNo;
	}
	public String getRes_posFlwNo() {
		return res_posFlwNo;
	}
	public void setRes_posFlwNo(String res_posFlwNo) {
		this.res_posFlwNo = res_posFlwNo;
	}
	public String getRes_settlementDate() {
		return res_settlementDate;
	}
	public void setRes_settlementDate(String res_settlementDate) {
		this.res_settlementDate = res_settlementDate;
	}
	public String getRes_referenceNo() {
		return res_referenceNo;
	}
	public void setRes_referenceNo(String res_referenceNo) {
		this.res_referenceNo = res_referenceNo;
	}
	public String getRes_authResCode() {
		return res_authResCode;
	}
	public void setRes_authResCode(String res_authResCode) {
		this.res_authResCode = res_authResCode;
	}
	public String getRes_resCode() {
		return res_resCode;
	}
	public void setRes_resCode(String res_resCode) {
		this.res_resCode = res_resCode;
	}
	public String getRes_termId() {
		return res_termId;
	}
	public void setRes_termId(String res_termId) {
		this.res_termId = res_termId;
	}
	public String getRes_merchId() {
		return res_merchId;
	}
	public void setRes_merchId(String res_merchId) {
		this.res_merchId = res_merchId;
	}
	public String getReferenceNo() {
		return ReferenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		ReferenceNo = referenceNo;
	}
	public String getAuthResCode() {
		return AuthResCode;
	}
	public void setAuthResCode(String authResCode) {
		AuthResCode = authResCode;
	}
	public String getResCode() {
		return ResCode;
	}
	public void setResCode(String resCode) {
		ResCode = resCode;
	}
	public String getOriginalPosNo() {
		return OriginalPosNo;
	}
	public void setOriginalPosNo(String originalPosNo) {
		OriginalPosNo = originalPosNo;
	}
	public String getOriginalDate() {
		return OriginalDate;
	}
	public void setOriginalDate(String originalDate) {
		OriginalDate = originalDate;
	}
	public String getRes_amt() {
		return res_amt;
	}
	public void setRes_amt(String res_amt) {
		this.res_amt = res_amt;
	}
	
}
