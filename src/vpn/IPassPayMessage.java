package vpn;

import java.util.HashMap;
import java.util.Map;

public class IPassPayMessage {
		// 返回属性
		private String mid;
		private String oid;
		private String site_id;
		private String order_amount;
		private String order_currency;
		private String hash_info;
		private String card_no;
		private String card_ex_month;
		private String card_ex_year;
		private String card_cvv;
		private String bill_email;
		private String bill_phone;
		private String bill_country;
		private String Billingstate;
		private String bill_city;
		private String bill_street;
		private String bill_zip;
		private String bill_firstname;
		private String bill_lastname;
		private String syn_url;
		private String asyn_url;
		private String source_ip;
		private String source_url;
		private String gateway_version;
		private String uuid;	
		
		private String info;//返回信息
		private String order_status;//订单状态
		private String pid;
		private String billing_desc;
		
		//private String amount;
		//private String respCode;
		//private String bankInfo;//银行辅助信息
		
		public String getBillingstate() {
			return Billingstate;
		}
	
		public String getMid() {
			return mid;
		}

		public void setMid(String mid) {
			this.mid = mid;
		}

		public String getOid() {
			return oid;
		}

		public void setOid(String oid) {
			this.oid = oid;
		}

		public String getSite_id() {
			return site_id;
		}

		public void setSite_id(String site_id) {
			this.site_id = site_id;
		}

		public String getOrder_amount() {
			return order_amount;
		}

		public void setOrder_amount(String order_amount) {
			this.order_amount = order_amount;
		}

		public String getOrder_currency() {
			return order_currency;
		}

		public void setOrder_currency(String order_currency) {
			this.order_currency = order_currency;
		}

		public String getHash_info() {
			return hash_info;
		}

		public void setHash_info(String hash_info) {
			this.hash_info = hash_info;
		}

		public String getCard_no() {
			return card_no;
		}

		public void setCard_no(String card_no) {
			this.card_no = card_no;
		}

		public String getCard_ex_month() {
			return card_ex_month;
		}

		public void setCard_ex_month(String card_ex_month) {
			this.card_ex_month = card_ex_month;
		}

		public String getCard_ex_year() {
			return card_ex_year;
		}

		public void setCard_ex_year(String card_ex_year) {
			this.card_ex_year = card_ex_year;
		}

		public String getCard_cvv() {
			return card_cvv;
		}

		public void setCard_cvv(String card_cvv) {
			this.card_cvv = card_cvv;
		}

		public String getBill_email() {
			return bill_email;
		}

		public void setBill_email(String bill_email) {
			this.bill_email = bill_email;
		}

		public String getBill_phone() {
			return bill_phone;
		}

		public void setBill_phone(String bill_phone) {
			this.bill_phone = bill_phone;
		}

		public String getBill_country() {
			return bill_country;
		}

		public void setBill_country(String bill_country) {
			this.bill_country = bill_country;
		}

		public String getBill_city() {
			return bill_city;
		}

		public void setBill_city(String bill_city) {
			this.bill_city = bill_city;
		}

		public String getBill_street() {
			return bill_street;
		}

		public void setBill_street(String bill_street) {
			this.bill_street = bill_street;
		}

		public String getBill_zip() {
			return bill_zip;
		}

		public void setBill_zip(String bill_zip) {
			this.bill_zip = bill_zip;
		}

		public String getBill_firstname() {
			return bill_firstname;
		}

		public void setBill_firstname(String bill_firstname) {
			this.bill_firstname = bill_firstname;
		}

		public String getBill_lastname() {
			return bill_lastname;
		}

		public void setBill_lastname(String bill_lastname) {
			this.bill_lastname = bill_lastname;
		}

		public String getSyn_url() {
			return syn_url;
		}

		public void setSyn_url(String syn_url) {
			this.syn_url = syn_url;
		}

		public String getAsyn_url() {
			return asyn_url;
		}

		public void setAsyn_url(String asyn_url) {
			this.asyn_url = asyn_url;
		}

		public String getSource_ip() {
			return source_ip;
		}

		public void setSource_ip(String source_ip) {
			this.source_ip = source_ip;
		}

		public String getSource_url() {
			return source_url;
		}

		public void setSource_url(String source_url) {
			this.source_url = source_url;
		}

		public String getGateway_version() {
			return gateway_version;
		}

		public void setGateway_version(String gateway_version) {
			this.gateway_version = gateway_version;
		}

		public String getUuid() {
			return uuid;
		}

		public void setUuid(String uuid) {
			this.uuid = uuid;
		}

		public String getInfo() {
			return info;
		}

		public void setInfo(String info) {
			this.info = info;
		}

		public String getOrder_status() {
			return order_status;
		}

		public void setOrder_status(String order_status) {
			this.order_status = order_status;
		}

		public String getPid() {
			return pid;
		}

		public void setPid(String pid) {
			this.pid = pid;
		}

		public String getBilling_desc() {
			return billing_desc;
		}

		public void setBilling_desc(String billing_desc) {
			this.billing_desc = billing_desc;
		}

		public void setBillingstate(String billingstate) {
			Map<String,String> USStates = new HashMap<String,String>();
			USStates.put("AL", "Alabama");
            USStates.put("AK", "Alaska");
            USStates.put("AS", "American Samoa");
            USStates.put("AZ", "Arizona");
            USStates.put("AR", "Arkansas");
            USStates.put("AF", "Armed Forces Africa");
            USStates.put("AA", "Armed Forces Americas");
            USStates.put("AC", "Armed Forces Canada");
            USStates.put("AE", "Armed Forces Europe");
            USStates.put("AM", "Armed Forces Middle East");
            USStates.put("AP", "Armed Forces Pacific");
            USStates.put("CA", "California");
            USStates.put("CO", "Colorado");
            USStates.put("CT", "Connecticut");
            USStates.put("DE", "Delaware");
            USStates.put("DC", "District of Columbia");
            USStates.put("FM", "Federated States of Micronesia");
            USStates.put("FL", "Florida");
            USStates.put("GA", "Georgia");
            USStates.put("GU", "Guam");
            USStates.put("HI", "Hawaii");
            USStates.put("ID", "Idaho");
            USStates.put("IL", "Illinois");
            USStates.put("IN", "Indiana");
            USStates.put("IA", "Iowa");
            USStates.put("KS", "Kansas");
            USStates.put("KY", "Kentucky");
            USStates.put("LA", "Louisiana");
            USStates.put("ME", "Maine");
            USStates.put("MH", "Marshall Islands");
            USStates.put("MD", "Maryland");
            USStates.put("MA", "Massachusetts");
            USStates.put("MI", "Michigan");
            USStates.put("MN", "Minnesota");
            USStates.put("MS", "Mississippi");
            USStates.put("MO", "Missouri");
            USStates.put("MT", "Montana");
            USStates.put("NE", "Nebraska");
            USStates.put("NV", "Nevada");
            USStates.put("NH", "New Hampshire");
            USStates.put("NJ", "New Jersey");
            USStates.put("NM", "New Mexico");
            USStates.put("NY", "New York");
            USStates.put("NC", "North Carolina");
            USStates.put("ND", "North Dakota");
            USStates.put("MP", "Northern Mariana Islands");
            USStates.put("OH", "Ohio");
            USStates.put("OK", "Oklahoma");
            USStates.put("OR", "Oregon");
            USStates.put("PW", "Palau");
            USStates.put("PA", "Pennsylvania");
            USStates.put("PR", "Puerto Rico");
            USStates.put("RI", "Rhode Island");
            USStates.put("SC", "South Carolina");
            USStates.put("SD", "South Dakota");
            USStates.put("TN", "Tennessee");
            USStates.put("TX", "Texas");
            USStates.put("UT", "Utah");
            USStates.put("VT", "Vermont");
            USStates.put("VI", "Virgin Islands");
            USStates.put("VA", "Virginia");
            USStates.put("WA", "Washington");
            USStates.put("WV", "West Virginia");
            USStates.put("WI", "Wisconsin");
            USStates.put("WY", "Wyoming");
            USStates.put("AB", "Alberta");
            USStates.put("BC", "British Columbia");
            USStates.put("MB", "Manitoba");
            USStates.put("NB", "New Brunswick");
            USStates.put("NL", "Newfoundland and Labrador");
            USStates.put("NS", "Nova Scotia");
            USStates.put("ON", "Ontario");
            USStates.put("PE", "Prince Edward Island");
            USStates.put("QC", "Quebec");
            USStates.put("SK", "Saskatchewan");
            USStates.put("NT", "Northwest Territories");
            USStates.put("NU", "Nunavut");
            USStates.put("YT", "Yukon Territory");
            for(String key : USStates.keySet()){ 
                String value = USStates.get(key); 
                if(billingstate.equals(value)){
                	Billingstate = key;
                	break;
                }else{
                	Billingstate = billingstate;
                }
            }           
		}
}
