package vpn;

import java.util.HashMap;
import java.util.Map;

public class ZMTTradeurlMessage {
		// 返回属性
		//private String tradeurl;		
		private String webSite;
		
		private String mid;//商户号
		private String trade_status;//网站提交审核状态
		

		public String getMid() {
			return mid;
		}
		public void setMid(String mid) {
			this.mid = mid;
		}
		public String getTrade_status() {
			return trade_status;
		}
		public void setTrade_status(String trade_status) {
			this.trade_status = trade_status;
		}
		public String getWebSite() {
			return webSite;
		}
		public void setWebSite(String webSite) {
			this.webSite = webSite;
		}
	
		
}
