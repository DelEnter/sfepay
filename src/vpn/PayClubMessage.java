package vpn;

import java.util.HashMap;
import java.util.Map;

public class PayClubMessage {
		// 返回属性
		private String p_mid;
		private String p_account_num;
		private String p_transaction_type;
		private String p_order_num;
		private String p_currency;
		private String p_amount;
		private String p_card_num;
		private String p_card_expmonth;
		private String p_card_expyear;
		private String p_card_csc;
		private String p_card_issuingbank;
		private String p_firstname;
		private String p_lastname;
		private String p_user_email;
		private String p_user_phone;
		private String p_user_ipaddress;
		private String p_trans_url;
		private String p_return_url;
		private String p_bill_country;
		private String p_bill_state;
		private String p_bill_city;
		private String p_bill_address;
		private String p_bill_zip;
		private String p_ship_firstname;
		private String p_ship_lastname;	
		private String p_ship_country;
		private String p_ship_state;
		private String p_ship_city;
		private String p_ship_address;
		private String p_ship_zip;
		private String p_product_name;
		private String p_product_num;
		private String p_product_desc;
		private String p_signmsg;
		
		private String p_pay_result;//订单状态
		private String p_pay_info;//
		private String p_remark;//返回信息
		private String p_trans_num;//上游流水号
		public String getP_mid() {
			return p_mid;
		}
		public void setP_mid(String p_mid) {
			this.p_mid = p_mid;
		}
		public String getP_account_num() {
			return p_account_num;
		}
		public void setP_account_num(String p_account_num) {
			this.p_account_num = p_account_num;
		}
		public String getP_transaction_type() {
			return p_transaction_type;
		}
		public void setP_transaction_type(String p_transaction_type) {
			this.p_transaction_type = p_transaction_type;
		}
		public String getP_order_num() {
			return p_order_num;
		}
		public void setP_order_num(String p_order_num) {
			this.p_order_num = p_order_num;
		}
		public String getP_currency() {
			return p_currency;
		}
		public void setP_currency(String p_currency) {
			this.p_currency = p_currency;
		}
		public String getP_amount() {
			return p_amount;
		}
		public void setP_amount(String p_amount) {
			this.p_amount = p_amount;
		}
		public String getP_card_num() {
			return p_card_num;
		}
		public void setP_card_num(String p_card_num) {
			this.p_card_num = p_card_num;
		}
		public String getP_card_expmonth() {
			return p_card_expmonth;
		}
		public void setP_card_expmonth(String p_card_expmonth) {
			this.p_card_expmonth = p_card_expmonth;
		}
		public String getP_card_expyear() {
			return p_card_expyear;
		}
		public void setP_card_expyear(String p_card_expyear) {
			this.p_card_expyear = p_card_expyear;
		}
		public String getP_card_csc() {
			return p_card_csc;
		}
		public void setP_card_csc(String p_card_csc) {
			this.p_card_csc = p_card_csc;
		}
		public String getP_card_issuingbank() {
			return p_card_issuingbank;
		}
		public void setP_card_issuingbank(String p_card_issuingbank) {
			this.p_card_issuingbank = p_card_issuingbank;
		}
		public String getP_firstname() {
			return p_firstname;
		}
		public void setP_firstname(String p_firstname) {
			this.p_firstname = p_firstname;
		}
		public String getP_lastname() {
			return p_lastname;
		}
		public void setP_lastname(String p_lastname) {
			this.p_lastname = p_lastname;
		}
		public String getP_user_email() {
			return p_user_email;
		}
		public void setP_user_email(String p_user_email) {
			this.p_user_email = p_user_email;
		}
		public String getP_user_phone() {
			return p_user_phone;
		}
		public void setP_user_phone(String p_user_phone) {
			this.p_user_phone = p_user_phone;
		}
		public String getP_user_ipaddress() {
			return p_user_ipaddress;
		}
		public void setP_user_ipaddress(String p_user_ipaddress) {
			this.p_user_ipaddress = p_user_ipaddress;
		}
		public String getP_trans_url() {
			return p_trans_url;
		}
		public void setP_trans_url(String p_trans_url) {
			this.p_trans_url = p_trans_url;
		}
		public String getP_return_url() {
			return p_return_url;
		}
		public void setP_return_url(String p_return_url) {
			this.p_return_url = p_return_url;
		}
		public String getP_bill_country() {
			return p_bill_country;
		}
		public void setP_bill_country(String p_bill_country) {
			this.p_bill_country = p_bill_country;
		}
		public String getP_bill_state() {
			return p_bill_state;
		}
		public void setP_bill_state(String p_bill_state) {
			this.p_bill_state = p_bill_state;
		}
		public String getP_bill_city() {
			return p_bill_city;
		}
		public void setP_bill_city(String p_bill_city) {
			this.p_bill_city = p_bill_city;
		}
		public String getP_bill_address() {
			return p_bill_address;
		}
		public void setP_bill_address(String p_bill_address) {
			this.p_bill_address = p_bill_address;
		}
		public String getP_bill_zip() {
			return p_bill_zip;
		}
		public void setP_bill_zip(String p_bill_zip) {
			this.p_bill_zip = p_bill_zip;
		}
		public String getP_ship_firstname() {
			return p_ship_firstname;
		}
		public void setP_ship_firstname(String p_ship_firstname) {
			this.p_ship_firstname = p_ship_firstname;
		}
		public String getP_ship_lastname() {
			return p_ship_lastname;
		}
		public void setP_ship_lastname(String p_ship_lastname) {
			this.p_ship_lastname = p_ship_lastname;
		}
		public String getP_ship_country() {
			return p_ship_country;
		}
		public void setP_ship_country(String p_ship_country) {
			this.p_ship_country = p_ship_country;
		}
		public String getP_ship_state() {
			return p_ship_state;
		}
		public void setP_ship_state(String p_ship_state) {
			this.p_ship_state = p_ship_state;
		}
		public String getP_ship_city() {
			return p_ship_city;
		}
		public void setP_ship_city(String p_ship_city) {
			this.p_ship_city = p_ship_city;
		}
		public String getP_ship_address() {
			return p_ship_address;
		}
		public void setP_ship_address(String p_ship_address) {
			this.p_ship_address = p_ship_address;
		}
		public String getP_ship_zip() {
			return p_ship_zip;
		}
		public void setP_ship_zip(String p_ship_zip) {
			this.p_ship_zip = p_ship_zip;
		}
		public String getP_product_name() {
			return p_product_name;
		}
		public void setP_product_name(String p_product_name) {
			this.p_product_name = p_product_name;
		}
		public String getP_product_num() {
			return p_product_num;
		}
		public void setP_product_num(String p_product_num) {
			this.p_product_num = p_product_num;
		}
		public String getP_product_desc() {
			return p_product_desc;
		}
		public void setP_product_desc(String p_product_desc) {
			this.p_product_desc = p_product_desc;
		}
		public String getP_signmsg() {
			return p_signmsg;
		}
		public void setP_signmsg(String p_signmsg) {
			this.p_signmsg = p_signmsg;
		}
		public String getP_pay_result() {
			return p_pay_result;
		}
		public void setP_pay_result(String p_pay_result) {
			this.p_pay_result = p_pay_result;
		}
		public String getP_pay_info() {
			return p_pay_info;
		}
		public void setP_pay_info(String p_pay_info) {
			this.p_pay_info = p_pay_info;
		}
		public String getP_remark() {
			return p_remark;
		}
		public void setP_remark(String p_remark) {
			this.p_remark = p_remark;
		}
		public String getP_trans_num() {
			return p_trans_num;
		}
		public void setP_trans_num(String p_trans_num) {
			this.p_trans_num = p_trans_num;
		}
		
}
