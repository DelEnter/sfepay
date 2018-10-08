package com.ecpss.action.merchant;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ecpss.action.BaseAction;
import com.ecpss.model.channel.InternationalChannels;
import com.ecpss.model.channel.InternationalMerchantChannels;
import com.ecpss.model.payment.InternationalCardholdersInfo;
import com.ecpss.model.payment.InternationalTradeinfo;
import com.ecpss.model.shop.InternationalMoneykindname;
import com.ecpss.model.shop.InternationalTerminalManager;
import com.ecpss.service.common.CommonService;
import com.ecpss.service.iservice.ShopManagerService;
import com.ecpss.util.CCSendMail;
import com.ecpss.util.EmailInfo;
import com.ecpss.util.StatusUtil;
import com.ecpss.web.PageInfo;
import com.opensymphony.xwork2.ActionContext;

public class ValidateTradeAction extends BaseAction {
	@Autowired
	@Qualifier("commonService")
	private CommonService commonService;
	@Qualifier("shopManagerService")
	private ShopManagerService shopManagerService;

	private PageInfo info;
	private String hql;
	private InternationalTradeinfo trade = new InternationalTradeinfo();
	private InternationalChannels chann;
	private InternationalMerchantChannels merchan;
	private InternationalMoneykindname money;
	private InternationalCardholdersInfo card;
	private String flag;

	/**
	 * 跳转到待确认交易页面
	 */
	public String toValidateTrade() {
		try {
			if (info == null) {
				info = new PageInfo();
			}
			hql = "FROM InternationalTradeinfo t, InternationalCardholdersInfo c, InternationalMoneykindname money, "
					+ "InternationalMerchant mm, InternationalChannels chan, InternationalMerchantChannels merchan "
					+ "WHERE t.tradeChannel = merchan.id AND merchan.channelId = chan.id AND t.merchantId = mm.id "
					+ "AND t.id=c.tradeId AND t.moneyType = money.moneykindno AND substr(t.tradeState,1,1)='4' "
					+ "AND mm.id=" + getMerchantBean().getMerchantId() + "";
			info.setPageSize(20);
			info = commonService.listQueryResultByHql(hql, info);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			this.messageAction = "跳转到待确认交易页面失败";
			return this.OPERATE_SUCCESS;
		}
	}

	/**
	 * 处理待确认交易
	 */
	public String validateTradeDispose() {
		try {
			String value = null;
			InternationalTradeinfo tradeinfo = (InternationalTradeinfo) commonService
					.load(InternationalTradeinfo.class, trade.getId());
			if(!tradeinfo.getTradeState().startsWith("4")){
				this.toValidateTrade();
				// this.messageAction = "处理待确认交易成功";
				// return this.OPERATE_SUCCESS;
				return SUCCESS;
			}
			
			if (trade.getTradeState().equals("2")) {
				value = "2";
				this.flag = "1";
			} else {
				value = "3";
				this.flag = "2";
			}
			tradeinfo.setTradeState(StatusUtil.updateStatus(tradeinfo
					.getTradeState(), 1, value));
			commonService.update(tradeinfo);
			
//			
//			String hql1 = "select ci from InternationalCardholdersInfo ci where ci.tradeId="
//					+ tradeinfo.getId();
//			InternationalCardholdersInfo ci = (InternationalCardholdersInfo) this.commonService
//					.uniqueResult(hql1);
//			// 判断卡类型
//			int cartype = 0;
//			if (ci.getCardNo() != null) {
//				if (getCarNo(ci.getCardNo()).matches("[4]\\d+")) {
//					cartype = 4;
//				}
//				if (getCarNo(ci.getCardNo()).matches("[5]\\d+")) {
//					cartype = 5;
//				}
//			}
//			String sql = "select a.channelname , d.id ,b.id as tid from international_channels a ,international_creditcardtype b,international_mercreditcard c ,international_merchantchannels d where a.id=d.channelid and d.merchantid='"
//					+ tradeinfo.getMerchantId()
//					+ "' and b.shortname='"
//					+ cartype
//					+ "' and d.id=c.merchannelid and b.id=c.creditcardid and d.onoff='1' and c.onoff='1'";
//			// 获取通道
//			List chanellist = this.commonService.getByList(sql);
//			// 商户通道ID
//			String merchanID = "";
//			// 通道名称
//			String chanelName = "";
//			// 卡种ID
//			Long carType = 0l;
//			if (chanellist.size() > 0 || chanellist.size() == 1) {
//				Object[] tem = (Object[]) chanellist.get(0);
//				merchanID = tem[1].toString();
//				chanelName = tem[0].toString();
//				carType = Long.valueOf(tem[2].toString());
//			}
//			InternationalMerchantChannels im = (InternationalMerchantChannels) this.commonService
//					.load(InternationalMerchantChannels.class, Long
//							.valueOf(merchanID));
//			String chnals = chanelName.split("-")[0];
//			if (chnals.equals("VIP")) {
//				if (trade.getTradeState().equals("2")) {
//					value = "2";
//					this.flag = "1";
//				} else {
//					value = "3";
//					this.flag = "2";
//				}
//				tradeinfo.setTradeState(StatusUtil.updateStatus(tradeinfo
//						.getTradeState(), 1, value));
//				commonService.update(tradeinfo);
//			} else if (chnals.equals("EVIP")) {
//				List<InternationalTerminalManager> tmm = this.commonService
//						.list("select tm from InternationalTerminalManager tm where tm.terminalNo='"
//								+ tradeinfo.getVIPTerminalNo()
//								+ "' and tm.channelId='"
//								+ im.getChannelId()
//								+ "' ");
//				InternationalTerminalManager tm = null;
//				InternationalTerminalManager tm1 = null;
//				if (tmm.size() > 0) {
//					tm = tmm.get(0);
//				}
//				List<InternationalTerminalManager> tmm1 = this.commonService
//						.list("select tm from InternationalTerminalManager tm where tm.terminalNo='"
//								+ tm.getAndterminalNo() + "' ");
//				if (tmm1.size() > 0) {
//					tm1 = tmm1.get(0);
//				}
//				try {
//					boolean f = this
//							.EVIPDispose(tradeinfo, tm ,tm1 , ci);
//					if (f) {
//						this.flag = "1";
//					}
//				} catch (Exception e) {
//					this.flag = "3";
//				}
//			}

			this.toValidateTrade();
			// this.messageAction = "处理待确认交易成功";
			// return this.OPERATE_SUCCESS;
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			this.flag = "3";
			return SUCCESS;
		}
	}

	/**
	 * 处理EVIP交易
	 * 
	 * @param tradeOrderNo
	 * @param cardNo
	 * @param cardCVV2
	 * @param tradeRMBAmount
	 * @param cardExDate
	 * @param posNumber
	 * @param posMerchantNo
	 * @param andPosNumber
	 * @param andposMerchantNo
	 * @return
	 */
	public boolean EVIPDispose(InternationalTradeinfo tradeinfo,InternationalTerminalManager tm ,InternationalTerminalManager tm1,
			InternationalCardholdersInfo ciinfo) {
		try {
			// ----------------给商户网站post数据-----------
			URL url;
			// String sendurl = "http://localhost:8888/ecpss/testinter";
			String sendurl = "https://security.sslepay.com/payinterface";
			url = new URL(sendurl);
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			OutputStreamWriter out = new OutputStreamWriter(connection
					.getOutputStream(), "8859_1");
			String parte = "tradeAmount=" + tradeinfo.getTradeAmount()
					+ "&cardNo=" + ciinfo.getCardNo() + "&cardCVV2="
					+ ciinfo.getCvv2() + "&cardExDate="
					+ ciinfo.getExpiryDate() + "&tradeOrderNo="
					+ tradeinfo.getOrderNo() + "&tradeRMBAmount="
					+ tradeinfo.getRmbAmount() + "&posNumber=" + tm.getTerminalNo()
					+ "&posMerchantNo=" + tm.getMerchantNo() + "&andPosNumber="
					+ tm1.getTerminalNo() + "&andposMerchantNo="
					+ tm1.getMerchantNo();
			out.write(parte); // 这里组织提交信息
			out.flush();
			out.close();
			// //获取返回数据
			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line = null;
			String result = null;
			while ((line = in.readLine()) != null) {
				if (line.length() > 0) {
					result = line;
				}
			}
			// 解析每一行交易数据
			StringTokenizer st = null;
			st = new StringTokenizer(result, ",");
			String str[] = new String[st.countTokens()];
			int i = 0;
			while (st.hasMoreTokens()) {
				String token = st.nextToken();
				str[i] = token;
				i++;
			}
			//----------------------获取数据---------------------
			String payresult = str[0]; // 支付结果 NC: 连接不上dcc 00:成功 01~99 : 失败
			String auno = str[1]; // 授权号
			String terminalno = str[2]; // 终端号
			String billaddress = "";

			if(terminalno.equals(tm.getTerminalNo())){
				billaddress = tm.getBillingAddress();
			}else{
				billaddress = tm1.getBillingAddress();
			}
			if (payresult.equals("NC")) {
				// 处理为待处理
				this.commonService
						.deleteBySql("update  international_tradeinfo t  set t.tradestate='2'||substr(t.tradestate,2,(length(t.tradestate)-1)) where t.id='"
								+ tradeinfo.getId() + "'");

				return Boolean.TRUE;
			} else if (payresult.equals("00")) {
				// 支付成功
				String message = "Payment Success!";
				this.commonService
						.deleteBySql("update  international_tradeinfo t  set t.tradestate='1'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
								+ message
								+ "',t.VIPTerminalNo='"
								+ terminalno
								+ "',t.VIPAuthorizationNo='"
								+ auno
								+ "' ,t.VIPBatchNo='XXXXXX' where t.id='"
								+ tradeinfo.getId() + "'");
				
				// /---------------给持卡人发送邮件-----------------------////
				EmailInfo emailinfo = new EmailInfo();
				String mailinfo = emailinfo.getPaymentResultEmail(ciinfo
						.getEmail(), tradeinfo.getTradeAmount(), getStates()
						.getCurrencyTypeByNo(tradeinfo.getMoneyType().intValue()),
						tradeinfo.getTradeUrl(), tradeinfo.getTradeTime(), billaddress, tradeinfo
								.getMerchantOrderNo(), tradeinfo.getOrderNo());
				try {
					// 发送邮件,如果发送失败插入数据库发送
					CCSendMail.setSendMail(ciinfo.getEmail(), mailinfo,
							"visamasterpay@visamasterpay.com");
					System.out.println("高风险确认成功邮件立马发出");
				} catch (Exception e) {
					// 往数据库插入等待发送邮件
					shopManagerService.addSendMessages(ciinfo.getEmail(),
							"visamasterpay@visamasterpay.com", mailinfo, "0");
					System.out.println("邮件等待稍后发出");
					return true;
				}
				return true;
			} else {
				String message = "Payment Declined! " + payresult;
				this.commonService
						.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
								+ message
								+ "' where t.id='" + tradeinfo.getId() + "'");
				return true;
			}
		} catch (Exception e) {
			// 连接不上为待处理
			// remark = "VIP待处理";
			this.commonService
					.deleteBySql("update  international_tradeinfo t  set t.tradestate='2'||substr(t.tradestate,2,(length(t.tradestate)-1)) where t.id='"
							+ tradeinfo.getId() + "'");
			try {
				CCSendMail.setSendMail("89610614@qq.com",
						"can not connect shanghai", "visamasterpay@visamasterpay.com");
			} catch (Exception ea) {
				return true;
			}
			return true;
		}

	}

	public String buzero(String refundRMBMoney) {
		String refundRMB = "000000000000";
		String zero_12 = "000000000000";
		DecimalFormat decimalFormat = new DecimalFormat("##############0.00");
		if (StringUtils.isNotBlank(refundRMBMoney)
				&& refundRMBMoney.replace(".", "").matches("\\d+")) {
			String refundRMBStr = Double.valueOf((decimalFormat.format(Double
					.valueOf(refundRMBMoney))))
					* 100 + "";
			String refundRMB_0 = zero_12
					+ refundRMBStr.substring(0, refundRMBStr.indexOf("."));
			refundRMB = refundRMB_0.substring(refundRMB_0.length() - 12,
					refundRMB_0.length());
		}
		return refundRMB;
	}

	public static HttpServletRequest getHttpServletRequest() {
		return (HttpServletRequest) ActionContext.getContext().get(
				ServletActionContext.HTTP_REQUEST);
	}

	/**
	 * @return the commonService
	 */
	public CommonService getCommonService() {
		return commonService;
	}

	/**
	 * @param commonService
	 *            the commonService to set
	 */
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	/**
	 * @return the info
	 */
	public PageInfo getInfo() {
		return info;
	}

	/**
	 * @param info
	 *            the info to set
	 */
	public void setInfo(PageInfo info) {
		this.info = info;
	}

	/**
	 * @return the trade
	 */
	public InternationalTradeinfo getTrade() {
		return trade;
	}

	/**
	 * @param trade
	 *            the trade to set
	 */
	public void setTrade(InternationalTradeinfo trade) {
		this.trade = trade;
	}

	/**
	 * @return the chann
	 */
	public InternationalChannels getChann() {
		return chann;
	}

	/**
	 * @param chann
	 *            the chann to set
	 */
	public void setChann(InternationalChannels chann) {
		this.chann = chann;
	}

	/**
	 * @return the merchan
	 */
	public InternationalMerchantChannels getMerchan() {
		return merchan;
	}

	/**
	 * @param merchan
	 *            the merchan to set
	 */
	public void setMerchan(InternationalMerchantChannels merchan) {
		this.merchan = merchan;
	}

	/**
	 * @return the money
	 */
	public InternationalMoneykindname getMoney() {
		return money;
	}

	/**
	 * @param money
	 *            the money to set
	 */
	public void setMoney(InternationalMoneykindname money) {
		this.money = money;
	}

	/**
	 * @return the card
	 */
	public InternationalCardholdersInfo getCard() {
		return card;
	}

	/**
	 * @param card
	 *            the card to set
	 */
	public void setCard(InternationalCardholdersInfo card) {
		this.card = card;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
