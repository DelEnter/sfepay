package com.ecpss.action.pay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import vpn.VpnUtil;

import com.ecpss.action.BaseAction;
import com.ecpss.action.pay.dcc.DCCMessage;
import com.ecpss.action.pay.dcc.DccUtil;
import com.ecpss.action.pay.tc.ClientBoc;
import com.ecpss.action.pay.tc.XMLGetMessage;
import com.ecpss.action.pay.util.CheckCardNo;
import com.ecpss.action.pay.util.MaxMindExample;
import com.ecpss.model.channel.InternationalChannels;
import com.ecpss.model.channel.InternationalMerchantChannels;
import com.ecpss.model.channel.InternationalMigsMerchantNo;
import com.ecpss.model.payment.InternationalCardholdersInfo;
import com.ecpss.model.payment.InternationalTradeinfo;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.model.shop.InternationalMerchantManager;
import com.ecpss.model.shop.InternationalMerchantTerminal;
import com.ecpss.model.shop.InternationalTerminalManager;
import com.ecpss.model.shop.InternationalTradecondition;
import com.ecpss.service.iservice.ShopManagerService;
import com.ecpss.util.AES;
import com.ecpss.util.BOCPayResult;
import com.ecpss.util.CCSendMail;
import com.ecpss.util.CN3DPayResult;
import com.ecpss.util.EmailInfo;
import com.ecpss.util.EvipPayResult;
import com.ecpss.util.GetBatchNo;
import com.ecpss.util.MD5;
import com.ecpss.web.VcpUtil;

public class CarderInfoAction3D extends BaseAction {
	@Autowired
	@Qualifier("tradeManager")
	private TradeManager tradeManager;
	@Autowired
	@Qualifier("shopManagerService")
	private ShopManagerService shopManagerService;
	private String thesame = "";
	private HashMap h = new HashMap();
	// 抛过来的持卡人信息
	private String rorderno = ""; // 商户流水号
	private Date birthday; // 生日日期
	private String year; // 年
	private String month; // 月
	private Long date; // 日
	private String firstname;
	private String lastname;
	private String country; // 国家
	private String state; // 州
	private String city; // 城市
	private String address; // 地址
	private String zipcode; // 邮编编号
	private String email;
	private String phone;
	private String cardbank;

	private String ReturnURL;
	private String MD5key;
	// 卡信息
	private String cardnum; // 卡号
	private String cvv2;
	private String ip;
	private String cookie;
	private String cookieId;

	// 定单
	private Double ordercount; // 订单金额

	// 返回参数
	private int responseCode;

	private String remark; // 备注
	private String MD5info; // MD5加密

	// md5 数据校验
	private String tradeMoneyType;
	private String merchantOrderNo;
	private String md5Value; // 支付网关对商户信息进行加密
	private String message;

	// 2.5方支付参数
	private String vpc_Card;
	// maxmin参数
	private String maxmindValue;
	String bankName = null;
	String bankCountry = null;
	String bankPhone = null;
	private String values;
	private String SECURE_SECRET;
	private MaxMindExample exam = new MaxMindExample();
	// shipping Address 信息
	// shippingAddress
	private String shippingFirstName;
	private String shippingLastName;
	private String shippingEmail;
	private String shippingPhone;
	private String shippingZipcode;
	private String shippingAddress;
	private String shippingCity;
	private String shippingSstate;
	private String shippingCountry;
	private String products;

	// 非三D参数
	private String virtualPaymentClientURL;// 请求地址
	private String accessCode;// 接入码

	private String merchantId;// 商户ID
	private String vpc_CardExp;// 卡有效期
	private int tradeOrder;// 交易金额
	private String connectURL;// 返回地址
	private String vpc_CardNum;// 卡号

	// dcc 参数
	private String amount_Transaction_Foreign;
	private String conversion_Rate;// 汇率
	private String currency_Code_Foreign;
	private String tradeType; // 交易类型

	// dcc 参数

	private String local_money;// 本地
	private String foreign_money;// 外币
	private String conversion_Rate_show;
	private String car_termanal;// 返回终端
	private String cardMessage;

	private String newcardtype;

	/**
	 * 添加持卡人信息 input vip success 非三D
	 * 
	 * @return添加流程 卡号，商户号确定通道，风控，mixmad， 通道抛人民币处理。
	 */
	public String addCardMessage() {
		try {
			if (this.thesame.equals("1")) {
				this.firstname = this.shippingFirstName;
				this.lastname = this.shippingLastName;
				this.country = this.shippingCountry;// 国家
				this.state = this.shippingSstate; // 州
				this.city = this.shippingCity; // 城市
				this.address = this.shippingAddress; // 地址
				this.zipcode = this.shippingZipcode; // 邮编编号
				this.email = this.shippingEmail;
				this.phone = this.shippingPhone;
			}

			MD5 md5 = new MD5();
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			// / 获取持卡人的系统信息
			String buyerinformation = "";
			try {
				String agent = request.getHeader("User-Agent");
				if (null != agent && "" != agent) {
					String agent2 = this.getNavigator(agent);
					String os = this.getOS(agent);
					buyerinformation = agent2 + " " + os;
				} else {
					agent = "";
				}
			} catch (Exception e) {
			}
			String expirydate = month + year;

			// 交易
			InternationalTradeinfo trade = new InternationalTradeinfo();
			String hql = "from InternationalTradeinfo t where t.orderNo='"
					+ rorderno + "'";

			List<InternationalTradeinfo> tradl = this.commonService.list(hql);
			// ECPSS流水号不唯一
			if (tradl == null) {
				this.responseCode = 0;
				return ERROR;
			} else if (tradl.size() != 1) {
				return ERROR;
			}
			trade = tradl.get(0);

			// 转换成标准金额
			ordercount = trade.getTradeAmount();

			DecimalFormat decimalFormat = new DecimalFormat(
					"##############0.00");
			String ordercountValue = decimalFormat.format(ordercount);

			tradeMoneyType = trade.getMoneyType() + "";
			merchantOrderNo = trade.getMerchantOrderNo() + "";

			// InternationalMerCreditCard
			this.ReturnURL = trade.getReturnUrl();
			InternationalMerchant merchant = (InternationalMerchant) this.commonService
					.load(InternationalMerchant.class, trade.getMerchantId());

			// 校验是否商户号对上交易
			if (merchant == null) {
				return ERROR;
			}

			// 获取ip
			ip = getIpAddr(request);
			GetBatchNo ut = new GetBatchNo();
			if (cookieId == null || cookieId.length() == 5) {
				cookie = "ecpss.com" + ut.getCookies();
				Cookie cook = new Cookie("ids", cookie);
				// 生命周期
				cook.setMaxAge(60 * 60 * 24 * 365);
				response.addCookie(cook);
			}
			/**
			 * 读cookie
			 */
			// cookie = (String)session.getAttribute("id");
			Cookie[] cookies = request.getCookies();

			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					Cookie c = cookies[i];
					if (c.getName().equalsIgnoreCase("ids")) {
						cookie = c.getValue();
					}
				}
			}

			/** ******************风险控制******************** */
			// 获取信用卡前6位号码
			String backCardnum6 = (new StringBuilder(cardnum)).delete(6,
					cardnum.length()).toString();
			String backCardnum9 = (new StringBuilder(cardnum)).delete(9,
					cardnum.length()).toString();

			// 判断卡类型
			int cartype = 0;
			if (StringUtils.isNotBlank(newcardtype)) {
				if (newcardtype.equals("3")
						&& (cardnum.startsWith("30") || cardnum
								.startsWith("35"))) {
					cartype = 3;
					vpc_Card = "Jcb";
				}
				if (newcardtype.equals("4") && cardnum.startsWith("4")) {
					if (cardnum.length() == 16) {
						cartype = 4;
						vpc_Card = "Visa";
					} else {
						remark = "visa卡信息错误" + cardnum;
						responseCode = 26;
						MD5info = trade.getMerchantOrderNo()
								+ trade.getMoneyType() + ordercountValue
								+ responseCode + MD5key;
						md5Value = md5.getMD5ofStr(MD5info);
						trade.setRemark(remark);
						this.commonService.update(trade);
						return this.INPUT;
					}
				}
				if (newcardtype.equals("5") && cardnum.startsWith("5")) {
					if (cardnum.length() == 16) {
						cartype = 5;
						vpc_Card = "Mastercard";
					} else {
						remark = "master卡信息错误" + cardnum;
						responseCode = 26;
						MD5info = trade.getMerchantOrderNo()
								+ trade.getMoneyType() + ordercountValue
								+ responseCode + MD5key;
						md5Value = md5.getMD5ofStr(MD5info);
						trade.setRemark(remark);
						this.commonService.update(trade);
						return this.INPUT;
					}
				}
				if (newcardtype.equals("6")
						&& (cardnum.startsWith("34") || cardnum
								.startsWith("37"))) {
					cartype = 6;
					vpc_Card = "Aecard";
				}
				if (newcardtype.equals("7")
						&& (cardnum.startsWith("36")
								|| cardnum.startsWith("300")
								|| cardnum.startsWith("305") || cardnum
									.startsWith("38"))) {
					cartype = 7;
					vpc_Card = "DcCard";
				}

			}
			if (cartype == 5) {
				this.cardMessage = "I declare that I have been offered a choice of payment currencies and my choice is final.";
			} else {
				this.cardMessage = "I declare that I have been offered a choice of payment currencies and my choice is final.I understand that the currency conversion is not provided by Visa.";

			}

			// 根据商户号,卡种信息获取通道
			String sql = "select a.channelname , d.id ,b.id as tid from international_channels a ,international_creditcardtype b,international_mercreditcard c ,international_merchantchannels d where a.id=d.channelid and d.merchantid='"
					+ merchant.getId()
					+ "' and b.shortname='"
					+ cartype
					+ "' and d.id=c.merchannelid and b.id=c.creditcardid and d.onoff='1' and c.onoff='1'";
			List chanellist = this.commonService.getByList(sql);
			// 商户通道ID
			String merchanID = "";
			// 通道名称
			String chanelName = "";
			// 卡种ID
			Long carType = 0l;
			if (chanellist.size() > 0 || chanellist.size() == 1) {
				Object[] tem = (Object[]) chanellist.get(0);
				merchanID = tem[1].toString();
				chanelName = tem[0].toString();
				carType = Long.valueOf(tem[2].toString());
			} else {
				message = "Payment Declined (MasterCard cannot be used at this moment, please use your VISA card. Thank you)";
				remark = "通道未设置";
				responseCode = 16;

				MD5info = trade.getMerchantOrderNo() + trade.getMoneyType()
						+ ordercountValue + responseCode + MD5key;

				md5Value = md5.getMD5ofStr(MD5info);
				trade.setRemark(remark);
				this.commonService.update(trade);
				return this.INPUT;
			}
			// 输入值验证
			if (checkAll()) {
				remark = "卡信息错误";
				responseCode = 26;

				MD5info = trade.getMerchantOrderNo() + trade.getMoneyType()
						+ ordercountValue + responseCode + MD5key;

				md5Value = md5.getMD5ofStr(MD5info);
				trade.setRemark(remark);
				this.commonService.update(trade);
				return this.INPUT;
			}
			// 商户通道
			InternationalMerchantChannels im = (InternationalMerchantChannels) this.commonService
					.load(InternationalMerchantChannels.class,
							Long.valueOf(merchanID));
			InternationalChannels ic = (InternationalChannels) this.commonService
					.load(InternationalChannels.class, im.getChannelId());
			// String cardTypeNum = chanelName.substring(0, 3).toString();
			String cardTypeNum = chanelName.substring(0, 3).toString();
			// 根据商户号,卡种信息获取通道
			System.out.println(cartype + "开头的卡走的通道是:" + cardTypeNum);
			System.out.println(trade.getId());
			String carlist = "select count(*) from international_cardholdersinfo t where t.tradeid='"
					+ trade.getId() + "'";
			int carInfoList = 0;
			carInfoList = this.tradeManager.intBySql(carlist);
			if (carInfoList > 0) {
				message = "Payment Declined";
				remark = "同一笔交易重复支付";
				responseCode = 31;

				MD5info = trade.getMerchantOrderNo() + trade.getMoneyType()
						+ ordercountValue + responseCode + MD5key;
				md5Value = md5.getMD5ofStr(MD5info);
				trade.setTradeState("0"
						+ trade.getTradeState().substring(1,
								trade.getTradeState().length()));
				trade.setRemark(remark);
				this.commonService.update(trade);
//				this.commonService
//						.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
//								+ remark
//								+ "' where t.id='"
//								+ trade.getId()
//								+ "'");

				return this.INPUT;
			}
			// this.commonService.deleteBySql("delete from
			// international_cardholdersinfo t where
			// t.tradeid='"+trade.getId()+"'");
			InternationalCardholdersInfo card = new InternationalCardholdersInfo();
			card.setTradeId(trade.getId());
			// card.setTradetime(trade.getTradeTime());
			card.setIp(ip);
			card.setCookie(cookie);
			// card.setRiskvalue(maxmind);
			card.setCardNo(AES.setCarNo(cardnum));
			card.setFirstName(firstname);
			card.setLastName(lastname);
			// card.setUserName(firstname+" "+lastname);
			card.setEmail(email);
			card.setPhone(phone);

			// card.setResulturl(ReturnURL);
			card.setCountry(country);
			card.setState(state);
			card.setCity(city);
			card.setAddress(address);
			card.setZipcode(zipcode);
			// card(cardbank);
			card.setShippingAddress(this.getShippingAddress());
			card.setShippingCity(this.getShippingCity());
			card.setShippingCountry(this.getShippingCountry());
			card.setShippingEmail(this.getShippingEmail());
			card.setShippingFullName(this.getShippingFirstName() + " "
					+ this.getShippingLastName());
			card.setShippingPhone(this.getShippingPhone());
			card.setShippingState(this.getState());
			card.setShippingZip(this.getShippingZipcode());
			card.setProductInfo(this.getProducts());
			this.commonService.save(card);
			 trade.setTradeChannel(Long.valueOf(merchanID));
			// this.commonService.update(trade);
//			this.commonService
//					.deleteBySql("update  international_tradeinfo t  set t.tradechannel='"
//							+ Long.valueOf(merchanID)
//							+ "' where t.id='"
//							+ trade.getId() + "'");

			// 黑卡,高风险卡宾

			// if (backCardValue6 > 0 || backCardValue9> 0) {
			// message = "Payment Declined";
			// remark = "黑卡bin";
			// responseCode = 3;
			//
			// MD5info = trade.getMerchantOrderNo() + trade.getMoneyType() +
			// ordercountValue + responseCode + MD5key;
			//
			// md5Value = md5.getMD5ofStr(MD5info);
			// this.commonService.deleteBySql("update international_tradeinfo t
			// set
			// t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"+remark+"'
			// where t.id='"+trade.getId()+"';");
			// return INPUT;
			// }
			// 黑卡，风险卡
			int backCardValue = this.tradeManager
					.intBySql("select count(1) from Internationalbacklist t where t.cardno='"
							+ cardnum + "' ");
			int riskCardValue = this.tradeManager
					.intBySql("select count(1) from InternationalRisklist t where t.cardno='"
							+ cardnum + "' ");
			Calendar calendar = Calendar.getInstance();// 此时打印它获取的是系统当前时间
			calendar.add(Calendar.DATE, -1); // 得到前一天
			String yestedayDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.format(calendar.getTime());
			// 根据ip或cardNumber或Email，COCKET 信息查询持卡人24小时内交易的次数(成功的，待处理，待确认的交易)
			// 获取交易条件设置信息
			List<InternationalTradecondition> localrisk;
			localrisk = this.commonService
					.list("from  InternationalTradecondition f where f.merchantId='"
							+ merchant.getId() + "'");
			if (localrisk.size() == 0) {
				localrisk = this.commonService
						.list("from  InternationalTradecondition f where f.merchantId is null");
			}
			Long localIP = 0l;
			Long localEMAIL = 0l;
			Long localCOCKET = 0l;
			Long localCarNO = 0l;
			Long localPhone = 0l;
			for (int i = 0; i < localrisk.size(); i++) {
				InternationalTradecondition tem = localrisk.get(i);
				if ("1".equals(tem.getItemno() + "")) {
					localIP = tem.getTradenumber();
				}
				if ("2".equals(tem.getItemno() + "")) {
					localEMAIL = tem.getTradenumber();
				}
				if ("3".equals(tem.getItemno() + "")) {
					localCarNO = tem.getTradenumber();
				}
				if ("4".equals(tem.getItemno() + "")) {
					localPhone = tem.getTradenumber();
				}
				if ("5".equals(tem.getItemno() + "")) {
					localCOCKET = tem.getTradenumber();
				}

			}
			// 获取商户管理值
			List<InternationalMerchantManager> merchantmanagers = new ArrayList();
			merchantmanagers = this.commonService
					.list("from  InternationalMerchantManager f where f.merchantId='"
							+ merchant.getId() + "'");
			if (merchantmanagers.size() == 0) {
				merchantmanagers = this.commonService
						.list("from  InternationalMerchantManager f where f.merchantId is null ");
			}
			InternationalMerchantManager merchantmanager = merchantmanagers
					.get(0);

			// 获取POS
			List<InternationalTerminalManager> it = this.commonService
					.list("select tm from InternationalMerchantTerminal mt,InternationalTerminalManager tm "
							+ "where tm.id=mt.terminalId "
							+ "and tm.channelId='"
							+ im.getChannelId()
							+ "' "
							+ "and mt.isopen=1 "
							+ "and mt.merchantId="
							+ merchant.getId());
			if (it.size() == 0) {
				it = this.commonService
						.list("from InternationalTerminalManager t where t.channelId='"
								+ im.getChannelId()
								+ "' and t.creditCardId='"
								+ carType
								+ "' and t.onoff='1' and t.isuses='0'");
				if (it.size() <= 0) {

					this.commonService
							.deleteBySql("update international_terminalmanager t set t.isuses='0' where  t.id in (select tf.id  from International_TerminalManager tf where tf.channelId='"
									+ im.getChannelId()
									+ "' and tf.creditCardId='"
									+ carType
									+ "')");
					it = this.commonService
							.list("from InternationalTerminalManager t where t.channelId='"
									+ im.getChannelId()
									+ "' and t.creditCardId='"
									+ carType
									+ "' and t.onoff='1' and t.isuses='0'");
					if (it.size() <= 0) {
						message = "Payment Declined";
						remark = "通道未分配终端";
						responseCode = 27;

						MD5info = trade.getMerchantOrderNo()
								+ trade.getMoneyType() + ordercountValue
								+ responseCode + MD5key;

						md5Value = md5.getMD5ofStr(MD5info);
						trade.setTradeState("0"
								+ trade.getTradeState().substring(1,
										trade.getTradeState().length()));
						trade.setRemark(remark);
						this.commonService.update(trade);
//						this.commonService
//								.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
//										+ remark
//										+ "' where t.id='"
//										+ trade.getId() + "'");
						// 清除持卡人cvv,有效期
						// this.commonService
						// .deleteBySql("update international_cardholdersinfo t
						// set t.cvv2='XXX',t.expiryDate='0000' where
						// t.tradeId='"
						// + trade.getId() + "'");
						return INPUT;
					}
				}
			}

			String posNumber = "";
			String posMerchantNo = "";
			Long terminalId = 0L;
			if (it.size() > 0) {
				// /面向开通多个终端号的商户
				List<InternationalTerminalManager> itmerchant = this.commonService
						.list("select tm from InternationalMerchantTerminal mt,InternationalTerminalManager tm "
								+ "where tm.id=mt.terminalId "
								+ "and tm.channelId='"
								+ im.getChannelId()
								+ "' "
								+ "and mt.isopen=1 "
								+ "and mt.merchantId=" + merchant.getId());
				if (itmerchant.size() > 1) {
					it = this.commonService
							.list("select tm from InternationalMerchantTerminal mt,InternationalTerminalManager tm "
									+ "where tm.id=mt.terminalId "
									+ "and tm.channelId='"
									+ im.getChannelId()
									+ "'  "
									+ "and mt.merchantId="
									+ merchant.getId() + " and tm.isuses='0'");

					if (it.size() == 0) {
						// /更新
						this.commonService
								.deleteBySql("update international_terminalmanager t set t.isuses='0' where  t.id in ("
										+ "select tm.id from International_Terminalmerchant mt,international_terminalmanager tm "
										+ "where tm.id=mt.terminalId "
										+ "and tm.channelId='"
										+ im.getChannelId()
										+ "' "
										+ "and mt.merchantId="
										+ merchant.getId() + " )");
						it = this.commonService
								.list("select tm from InternationalMerchantTerminal mt,InternationalTerminalManager tm "
										+ "where tm.id=mt.terminalId "
										+ "and tm.channelId='"
										+ im.getChannelId()
										+ "' "
										+ "and mt.isopen=1 "
										+ "and mt.merchantId="
										+ merchant.getId()
										+ " and tm.isuses='0'");
					}
				}

				// /获取最终使用终端
				posNumber = it.get(0).getTerminalNo();
				posMerchantNo = it.get(0).getMerchantNo();
				terminalId = it.get(0).getId();
			}
			System.out.println("pos==================" + posNumber);
			this.commonService
					.deleteBySql("update international_terminalmanager t set t.isuses='1' where  t.id='"
							+ it.get(0).getId() + "'");
			// 更新终端
			this.commonService
					.deleteBySql("update  international_tradeinfo t  set t.VIPTerminalNo='"
							+ posNumber
							+ "' where t.id='"
							+ trade.getId()
							+ "'");

			// 获取交易金额
			// 单笔交易默认不能大于600元

			int backCardValue6 = this.tradeManager
					.intBySql("select count(*) from Internationalbacklist t where t.cardno='"
							+ backCardnum6 + "' ");
			int backCardValue9 = this.tradeManager
					.intBySql("select count(*) from Internationalbacklist t where t.cardno='"
							+ backCardnum9 + "' ");
			String chnals = chanelName.split("-")[0];
			// 验证黑邮箱
			int backEmailValue = this.tradeManager
					.intBySql("select count(1) from Internationalbacklist t where lower(t.email)='"
							+ email.trim().toLowerCase() + "' ");
			// 验证黑IP
			int backIpValue = this.tradeManager
					.intBySql("select count(1) from Internationalbacklist t where t.ip='"
							+ ip + "' ");
			// 输入值验证
			if (checkAll()) {
				this.responseCode = 25;
				remark = "信息有误";
				System.out.println("返回状态码+++++++++" + responseCode);
				message = "Payment Declined";
				MD5info = trade.getMerchantOrderNo() + trade.getMoneyType()
						+ ordercountValue + responseCode + MD5key;

				md5Value = md5.getMD5ofStr(MD5info);
				trade.setTradeState("0"
						+ trade.getTradeState().substring(1,
								trade.getTradeState().length()));
				trade.setRemark(remark);
				this.commonService.update(trade);
//				this.commonService
//						.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
//								+ remark
//								+ "' where t.id='"
//								+ trade.getId()
//								+ "'");
				// 清除持卡人cvv,有效期
				// this.commonService
				// .deleteBySql("update international_cardholdersinfo t set
				// t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
				// + trade.getId() + "'");
				return INPUT;
			} else if (trade.getTradeAmount() > merchantmanager.getPenQuota()) {
				message = "Payment Declined";
				remark = "单笔超限";
				responseCode = 3;
				System.out.println("返回状态码+++++++++" + responseCode);
				MD5info = trade.getMerchantOrderNo() + trade.getMoneyType()
						+ ordercountValue + responseCode + MD5key;

				md5Value = md5.getMD5ofStr(MD5info);
				trade.setTradeState("0"
						+ trade.getTradeState().substring(1,
								trade.getTradeState().length()));
				trade.setRemark(remark);
				this.commonService.update(trade);
//				this.commonService
//						.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
//								+ remark
//								+ "' where t.id='"
//								+ trade.getId()
//								+ "'");
				// 清除持卡人cvv,有效期
				// this.commonService
				// .deleteBySql("update international_cardholdersinfo t set
				// t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
				// + trade.getId() + "'");
				return INPUT;
			}
			// 黑卡库
			else if (backCardValue > 0) {
				message = "Payment Declined";
				remark = "黑卡";
				responseCode = 2;
				System.out.println("返回状态码+++++++++" + responseCode);
				MD5info = trade.getMerchantOrderNo() + trade.getMoneyType()
						+ ordercountValue + responseCode + MD5key;
				md5Value = md5.getMD5ofStr(MD5info);
				trade.setTradeState("0"
						+ trade.getTradeState().substring(1,
								trade.getTradeState().length()));
				trade.setRemark(remark);
				this.commonService.update(trade);
//				this.commonService
//						.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
//								+ remark
//								+ "' where t.id='"
//								+ trade.getId()
//								+ "'");
				// 清除持卡人cvv,有效期
				// this.commonService
				// .deleteBySql("update international_cardholdersinfo t set
				// t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
				// + trade.getId() + "'");
				System.out.println("responseCode--------------" + responseCode);
				return INPUT;
			}
			// 黑邮箱
			else if (backEmailValue > 0) {
				message = "Payment Declined";
				remark = "黑邮箱";
				responseCode = 2;
				System.out.println("返回状态码+++++++++" + responseCode);
				MD5info = trade.getMerchantOrderNo() + trade.getMoneyType()
						+ ordercountValue + responseCode + MD5key;
				md5Value = md5.getMD5ofStr(MD5info);
				trade.setTradeState("0"
						+ trade.getTradeState().substring(1,
								trade.getTradeState().length()));
				trade.setRemark(remark);
				this.commonService.update(trade);
//				this.commonService
//						.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
//								+ remark
//								+ "' where t.id='"
//								+ trade.getId()
//								+ "'");
				// 清除持卡人cvv,有效期
				// this.commonService
				// .deleteBySql("update international_cardholdersinfo t set
				// t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
				// + trade.getId() + "'");
				System.out.println("responseCode--------------" + responseCode);
				return INPUT;
			}
			// IP
			else if (backIpValue > 0) {
				message = "Payment Declined";
				remark = "黑IP";
				responseCode = 2;
				System.out.println("返回状态码+++++++++" + responseCode);
				MD5info = trade.getMerchantOrderNo() + trade.getMoneyType()
						+ ordercountValue + responseCode + MD5key;
				md5Value = md5.getMD5ofStr(MD5info);
				trade.setTradeState("0"
						+ trade.getTradeState().substring(1,
								trade.getTradeState().length()));
				trade.setRemark(remark);
				this.commonService.update(trade);
//				this.commonService
//						.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
//								+ remark
//								+ "' where t.id='"
//								+ trade.getId()
//								+ "'");
				// 清除持卡人cvv,有效期
				// this.commonService
				// .deleteBySql("update international_cardholdersinfo t set
				// t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
				// + trade.getId() + "'");
				System.out.println("responseCode--------------" + responseCode);
				return INPUT;
			}
			// 黑卡bean
			else if (backCardValue6 > 0 || backCardValue9 > 0) {
				message = "Payment Declined";
				remark = "黑卡";
				responseCode = 17;
				System.out.println("返回状态码+++++++++" + responseCode);
				MD5info = trade.getMerchantOrderNo() + trade.getMoneyType()
						+ ordercountValue + responseCode + MD5key;
				md5Value = md5.getMD5ofStr(MD5info);
				trade.setTradeState("0"
						+ trade.getTradeState().substring(1,
								trade.getTradeState().length()));
				trade.setRemark(remark);
				this.commonService.update(trade);
//				this.commonService
//						.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
//								+ remark
//								+ "' where t.id='"
//								+ trade.getId()
//								+ "'");
				// 清除持卡人cvv,有效期
				// this.commonService
				// .deleteBySql("update international_cardholdersinfo t set
				// t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
				// + trade.getId() + "'");
				return INPUT;
				// response.sendRedirect(returnurl);
			}
			// 同一卡号
			int carno = 0;
			carno = this.tradeManager
					.intBySql("select count(1) from international_cardholdersinfo f, international_tradeinfo t where f.tradeid=t.id and f.cardno='"
							+ AES.setCarNo(this.cardnum)
							+ "' and t.merchantid='"
							+ merchant.getId()
							+ "' and substr(t.tradestate,1,1) in(1,2,4,5,6) and t.tradetime>to_date('"
							+ yestedayDate + "','yyyy-MM-dd hh24:mi:ss')");
			if (Long.valueOf(carno) >= localCarNO) {
				message = "Payment Declined";
				remark = "重复交易";
				responseCode = 7;
				System.out.println("返回状态码+++++++++" + responseCode);
				MD5info = trade.getMerchantOrderNo() + trade.getMoneyType()
						+ ordercountValue + responseCode + MD5key;
				md5Value = md5.getMD5ofStr(MD5info);
				trade.setTradeState("3"
						+ trade.getTradeState().substring(1,
								trade.getTradeState().length()));
				trade.setRemark(remark);
				this.commonService.update(trade);
//				this.commonService
//						.deleteBySql("update  international_tradeinfo t  set t.tradestate='3'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
//								+ remark
//								+ "' where t.id='"
//								+ trade.getId()
//								+ "'");
				// 清除持卡人cvv,有效期
				// this.commonService
				// .deleteBySql("update international_cardholdersinfo t set
				// t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
				// + trade.getId() + "'");
				return INPUT;

			}

			// 同一卡号失败次数
			int carnoerror = 0;
			carnoerror = this.tradeManager
					.intBySql("select count(1) from international_cardholdersinfo f, international_tradeinfo t where f.tradeid=t.id and f.cardno='"
							+ AES.setCarNo(this.cardnum)
							+ "' and t.merchantid='"
							+ merchant.getId()
							+ "' and substr(t.tradestate,1,1) ='0' and t.tradetime>to_date('"
							+ yestedayDate + "','yyyy-MM-dd hh24:mi:ss')");
			if (Long.valueOf(carnoerror) >= 2) {
				message = "Payment Declined";
				remark = "重复失败次数过多";
				responseCode = 7;
				System.out.println("返回状态码+++++++++" + responseCode);
				MD5info = trade.getMerchantOrderNo() + trade.getMoneyType()
						+ ordercountValue + responseCode + MD5key;
				md5Value = md5.getMD5ofStr(MD5info);
				trade.setTradeState("3"
						+ trade.getTradeState().substring(1,
								trade.getTradeState().length()));
				trade.setRemark(remark);
				this.commonService.update(trade);
//				this.commonService
//						.deleteBySql("update  international_tradeinfo t  set t.tradestate='3'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
//								+ remark
//								+ "' where t.id='"
//								+ trade.getId()
//								+ "'");
				// 清除持卡人cvv,有效期
				// this.commonService
				// .deleteBySql("update international_cardholdersinfo t set
				// t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
				// + trade.getId() + "'");
				return INPUT;
			}

			// 同一ip
			int ipcount = 0;
			ipcount = this.tradeManager
					.intBySql("select count(1) from international_cardholdersinfo f, international_tradeinfo t where f.tradeid=t.id and f.ip='"
							+ ip
							+ "' and t.merchantid='"
							+ merchant.getId()
							+ "' and substr(t.tradestate,1,1) in(1,2,4,5,6) and t.tradetime>to_date('"
							+ yestedayDate + "','yyyy-MM-dd hh24:mi:ss')");
			if (Long.valueOf(ipcount) >= localIP) {
				message = "Payment Declined";
				remark = "重复交易";
				responseCode = 5;
				System.out.println("返回状态码+++++++++" + responseCode);
				MD5info = trade.getMerchantOrderNo() + trade.getMoneyType()
						+ ordercountValue + responseCode + MD5key;
				md5Value = md5.getMD5ofStr(MD5info);
				trade.setTradeState("3"
						+ trade.getTradeState().substring(1,
								trade.getTradeState().length()));
				trade.setRemark(remark);
				this.commonService.update(trade);
//				this.commonService
//						.deleteBySql("update  international_tradeinfo t  set t.tradestate='3'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
//								+ remark
//								+ "' where t.id='"
//								+ trade.getId()
//								+ "'");
				// 清除持卡人cvv,有效期
				// this.commonService
				// .deleteBySql("update international_cardholdersinfo t set
				// t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
				// + trade.getId() + "'");
				return INPUT;
			}
			// 同一邮箱
			int emailcount = 0;
			emailcount = this.tradeManager
					.intBySql("select count(1) from international_cardholdersinfo f, international_tradeinfo t where f.tradeid=t.id and f.email='"
							+ this.email
							+ "' and t.merchantid='"
							+ merchant.getId()
							+ "' and substr(t.tradestate,1,1) in(1,2,4,5,6) and t.tradetime>to_date('"
							+ yestedayDate + "','yyyy-MM-dd hh24:mi:ss')");

			if (Long.valueOf(emailcount) >= localEMAIL) {
				message = "Payment Declined";
				remark = "重复交易";
				responseCode = 6;
				System.out.println("返回状态码+++++++++" + responseCode);
				MD5info = trade.getMerchantOrderNo() + trade.getMoneyType()
						+ ordercountValue + responseCode + MD5key;
				md5Value = md5.getMD5ofStr(MD5info);
				trade.setTradeState("0"
						+ trade.getTradeState().substring(1,
								trade.getTradeState().length()));
				trade.setRemark(remark);
				this.commonService.update(trade);
//				this.commonService
//						.deleteBySql("update  international_tradeinfo t  set t.tradestate='3'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
//								+ remark
//								+ "' where t.id='"
//								+ trade.getId()
//								+ "'");
				// 清除持卡人cvv,有效期
				// this.commonService
				// .deleteBySql("update international_cardholdersinfo t set
				// t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
				// + trade.getId() + "'");
				return INPUT;
			}
			// 同一电话
			int telcout = 0;
			telcout = this.tradeManager
					.intBySql("select count(1) from international_cardholdersinfo f, international_tradeinfo t where f.tradeid=t.id and f.phone='"
							+ this.phone
							+ "' and t.merchantid='"
							+ merchant.getId()
							+ "' and substr(t.tradestate,1,1) in(1,2,4,5,6) and t.tradetime>to_date('"
							+ yestedayDate + "','yyyy-MM-dd hh24:mi:ss')");
			if (Long.valueOf(telcout) >= localPhone) {
				message = "Payment Declined";
				remark = "重复交易";
				responseCode = 30;
				System.out.println("返回状态码+++++++++" + responseCode);
				MD5info = trade.getMerchantOrderNo() + trade.getMoneyType()
						+ ordercountValue + responseCode + MD5key;
				md5Value = md5.getMD5ofStr(MD5info);
				trade.setTradeState("3"
						+ trade.getTradeState().substring(1,
								trade.getTradeState().length()));
				trade.setRemark(remark);
				this.commonService.update(trade);
//				this.commonService
//						.deleteBySql("update  international_tradeinfo t  set t.tradestate='3'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
//								+ remark
//								+ "' where t.id='"
//								+ trade.getId()
//								+ "'");
				// 清除持卡人cvv,有效期
				// this.commonService
				// .deleteBySql("update international_cardholdersinfo t set
				// t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
				// + trade.getId() + "'");
				return INPUT;
			}
			// 同一cookies
			int cocketcount = 0;
			cocketcount = this.tradeManager
					.intBySql("select count(1) from international_cardholdersinfo f, international_tradeinfo t where f.tradeid=t.id and f.cookie='"
							+ this.cookie
							+ "' and t.merchantid='"
							+ merchant.getId()
							+ "' and substr(t.tradestate,1,1) in(1,2,4,5,6) and t.tradetime>to_date('"
							+ yestedayDate + "','yyyy-MM-dd hh24:mi:ss')");

			if (Long.valueOf(cocketcount) >= localCOCKET) {
				message = "Payment Declined";
				remark = "重复交易";
				responseCode = 8;
				System.out.println("返回状态码+++++++++" + responseCode);
				MD5info = trade.getMerchantOrderNo() + trade.getMoneyType()
						+ ordercountValue + responseCode + MD5key;
				md5Value = md5.getMD5ofStr(MD5info);
				trade.setTradeState("3"
						+ trade.getTradeState().substring(1,
								trade.getTradeState().length()));
				trade.setRemark(remark);
				this.commonService.update(trade);
//				this.commonService
//						.deleteBySql("update  international_tradeinfo t  set t.tradestate='3'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
//								+ remark
//								+ "' where t.id='"
//								+ trade.getId()
//								+ "'");
				// 清除持卡人cvv,有效期
				// this.commonService
				// .deleteBySql("update international_cardholdersinfo t set
				// t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
				// + trade.getId() + "'");
				return INPUT;
			}
			// 当商户交易的金额已超过商户交易条件设置金额则不能够再进行交易，并且已邮件的方式通知商户
			// // 获取月交易金额
			// Calendar calendarmonth = Calendar.getInstance();//此时打印它获取的是系统当前时间
			// calendarmonth.set(Calendar.DATE,1);//设为当前月的1号
			// String firstDay = new SimpleDateFormat("yyyy-MM-dd
			// HH:mm:ss").format(calendarmonth.getTime());
			// String monthsql="select sum(t.tradeAmount) from
			// InternationalTradeinfo t where
			// t.merchantId='"+merchant.getId()+"' " +
			// "and substr(t.tradeState,1,1) in(2,4,1) " +
			// "and tradeTime >= to_date('"+firstDay+"','yyyy-mm-dd hh24:mi:ss')
			// ";
			// monthTradeMoney = (Double)
			// this.commonService.uniqueResult(monthsql);
			// monthTradeMoney = this.tradeManager.getAcount(merchant.getId());
			if (merchant.getMonthTradeMoney() + ordercount > merchantmanager
					.getMonthQuota()) {
				// //先把需要发送邮件的信息保存到数据库
				// saveMailInfo(merEmail,num1,"ecpss@ecpss.com");
				message = "Payment Declined";
				remark = "月交易量超限";
				responseCode = 4;
				System.out.println("返回状态码+++++++++" + responseCode);
				MD5info = trade.getMerchantOrderNo() + trade.getMoneyType()
						+ ordercountValue + responseCode + MD5key;
				md5Value = md5.getMD5ofStr(MD5info);
				if (chnals.equals("VIP")
						|| (chnals.equals("EVIP") && merchant.getStatutes()
								.subSequence(6, 7).equals("0"))) {
					trade.setTradeState("4"
							+ trade.getTradeState().substring(1,
									trade.getTradeState().length()));
					trade.setRemark(remark);
					this.commonService.update(trade);
//					this.commonService
//							.deleteBySql("update  international_tradeinfo t  set t.tradestate='4'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
//									+ remark
//									+ "' where t.id='"
//									+ trade.getId()
//									+ "'");
				} else {
					trade.setTradeState("0"
							+ trade.getTradeState().substring(1,
									trade.getTradeState().length()));
					trade.setRemark(remark);
					this.commonService.update(trade);
//					this.commonService
//							.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
//									+ remark
//									+ "' where t.id='"
//									+ trade.getId()
//									+ "'");
					// 清除持卡人cvv,有效期
					// this.commonService
					// .deleteBySql("update international_cardholdersinfo t set
					// t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
					// + trade.getId() + "'");
				}

				// 超过限额给商户发送邮件提醒
				shopManagerService.addSendMessages(merchant.getMeremail(),
						"ecpss@ecpss.cc",
						merchant.getMerno() + " " + EmailInfo.getMoreMoney(),
						"0");
				return INPUT;

			}

			// 高风险卡 VIP为待确认 ，其他的为失败
			else if (riskCardValue > 0) {
				message = "Your Payment is being Processed! ";
				remark = "高风险卡";
				responseCode = 1;
				System.out.println("返回状态码+++++++++" + responseCode);
				MD5info = trade.getMerchantOrderNo() + trade.getMoneyType()
						+ ordercountValue + responseCode + MD5key;
				md5Value = md5.getMD5ofStr(MD5info);
				if (chnals.equals("VIP")) {
					trade.setTradeState("4"
							+ trade.getTradeState().substring(1,
									trade.getTradeState().length()));
					trade.setRemark(remark);
					this.commonService.update(trade);
//					this.commonService
//							.deleteBySql("update  international_tradeinfo t  set t.tradestate='4'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
//									+ remark
//									+ "' where t.id='"
//									+ trade.getId()
//									+ "'");
				} else {
					trade.setTradeState("0"
							+ trade.getTradeState().substring(1,
									trade.getTradeState().length()));
					trade.setRemark(remark);
					this.commonService.update(trade);
//					this.commonService
//							.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
//									+ remark
//									+ "' where t.id='"
//									+ trade.getId()
//									+ "'");
					// 清除持卡人cvv,有效期
					// this.commonService
					// .deleteBySql("update international_cardholdersinfo t set
					// t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
					// + trade.getId() + "'");
				}
				return INPUT;

			}
			// 当商户交易的金额达到商户交易条件设置金额的85%则以邮件的方式通知商户
			if (merchant.getMonthTradeMoney() + ordercount > merchantmanager
					.getMonthQuota() * 0.85) {
				// 发送邮件
				shopManagerService.addSendMessages(
						merchant.getMeremail(),
						"ecpss@ecpss.cc",
						merchant.getMerno()
								+ " "
								+ EmailInfo.getMoreMoneyPart(merchant
										.getMonthTradeMoney() + ordercount),
						"0");
				// this.saveMailInfo(merEmail, num2, "ecpss@ecpss.com");
			}
			// 与商户的风控制对比，然后进行对比

			// 连接maxmind系统
			try {
				HashMap hm = new HashMap();
				// 加密串 license_key : UxQh0mA4aLqw 调试和正式运行时要加上,才会返回分数
				// 上海key: CxsRZ1xPPRbR;
				// 广州key: UxQh0mA4aLqw
				int index = email.indexOf("@");
				String domian = email.substring(index + 1, email.length());
				hm.put("license_key", "CxsRZ1xPPRbR");
				hm.put("i", ip);
				hm.put("domain", domian);
				hm.put("emailMD5", md5.getMD5ofStr(email.toLowerCase()));
				hm.put("custPhone", phone);
				hm.put("country", country);
				hm.put("city", city);
				hm.put("region", state);
				hm.put("shipAddr", address);
				hm.put("postal", zipcode.toString());
				// hm.put("bin", cardnum);
				hm.put("bin", backCardnum6);
				hm.put("binName", cardbank);

				// standard 低级
				// premium 高级
				// 正式运行的时候要用这个 premium ; standard为调试用的标准
				hm.put("requested_type", "standard");

				Hashtable ht = getmmValue(hm);
				maxmindValue = (String) ht.get("values");
				bankName = (String) ht.get("bankName");
				bankCountry = (String) ht.get("bankCountry");
				bankPhone = (String) ht.get("bankPhone");
				System.out.println("maxmindValue--------------" + maxmindValue);
				// System.out.println("riskValue--------------"+riskValue);
			} catch (Exception ex) {
				try {
					CCSendMail.setSendMail("89610614@qq.com",
							"2.0 maxmind error", "ecpss@ecpss.cc");
				} catch (Exception e) {
					// 继续执行下去
				}
			}

			Double maxmind = 0d;
			// 返回分值
			if (maxmindValue != null && maxmindValue != "") {
				maxmind = Double.valueOf(maxmindValue);
			}
			this.commonService
					.deleteBySql(" update international_cardholdersinfo t set t.maxmindValue='"
							+ maxmind
							+ "',t.bankcountry='"
							+ bankCountry
							+ "',t.bankname='"
							+ bankName
							+ "',t.bankphone='"
							+ bankPhone
							+ "' where t.id='"
							+ card.getId()
							+ "' ");
			// 高风险卡 VIP为待确认 ，其他的为失败
			if ((maxmind >= im.getMaxmind_lv1())
					&& (maxmind <= im.getMaxmind_lv2())) {
				message = "Your Payment is being Processed! ";
				remark = "中风险！";
				responseCode = 9;
				System.out.println("返回状态码+++++++++" + responseCode);
				MD5info = trade.getMerchantOrderNo() + trade.getMoneyType()
						+ ordercountValue + responseCode + MD5key;
				md5Value = md5.getMD5ofStr(MD5info);
				if (chnals.equals("VIP")
						|| (chnals.equals("EVIP") && merchant.getStatutes()
								.subSequence(6, 7).equals("0"))
						|| chnals.equals("PRE")) {
					trade.setTradeState("4"
							+ trade.getTradeState().substring(1,
									trade.getTradeState().length()));
					trade.setRemark(remark);
					this.commonService.update(trade);
//					this.commonService
//							.deleteBySql("update  international_tradeinfo t  set t.tradestate='4'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
//									+ remark
//									+ "' where t.id='"
//									+ trade.getId()
//									+ "'");
					// -------更新商户月交易限额-----------------
					merchant.setMonthTradeMoney(merchant.getMonthTradeMoney()
							+ trade.getTradeAmount());
					this.commonService.update(merchant);
				} else {
//					this.commonService
//							.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
//									+ remark
//									+ "' where t.id='"
//									+ trade.getId()
//									+ "'");
					trade.setTradeState("0"
							+ trade.getTradeState().substring(1,
									trade.getTradeState().length()));
					trade.setRemark(remark);
					this.commonService.update(trade);
					// 清除持卡人cvv,有效期
					// this.commonService
					// .deleteBySql("update international_cardholdersinfo t set
					// t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
					// + trade.getId() + "'");

				}
				return INPUT;
			} else if (maxmind > im.getMaxmind_lv2()) {
				message = "Payment Declined";
				remark = "高风险！";
				responseCode = 0;
				System.out.println("返回状态码+++++++++" + responseCode);
				MD5info = trade.getMerchantOrderNo() + trade.getMoneyType()
						+ ordercountValue + responseCode + MD5key;
				md5Value = md5.getMD5ofStr(MD5info);
//				this.commonService
//						.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
//								+ remark
//								+ "' where t.id='"
//								+ trade.getId()
//								+ "'");
				trade.setTradeState("0"
						+ trade.getTradeState().substring(1,
								trade.getTradeState().length()));
				trade.setRemark(remark);
				this.commonService.update(trade);
				// 清除持卡人cvv,有效期
				// this.commonService
				// .deleteBySql("update international_cardholdersinfo t set
				// t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
				// + trade.getId() + "'");
				return INPUT;
			}

			String queryarea = "select m.id from MerchantRiskControl m where m.merchantId="
					+ merchant.getId()
					+ " and substr(m.area,1,2) like '"
					+ bankCountry + "'";
			System.out.println(queryarea);
			List queryarealist = this.commonService.list(queryarea);
			System.out.println(queryarealist.size());
			if (queryarealist.size() > 0) {
				message = "Payment Declined";
				remark = "禁止交易地区";
				responseCode = 31;
				System.out.println("返回状态码+++++++++" + responseCode);
				MD5info = trade.getMerchantOrderNo() + trade.getMoneyType()
						+ ordercountValue + responseCode + MD5key;
				md5Value = md5.getMD5ofStr(MD5info);
				trade.setTradeState("0"
						+ trade.getTradeState().substring(1,
								trade.getTradeState().length()));
				trade.setRemark(remark);
				this.commonService.update(trade);
//				this.commonService
//						.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
//								+ remark
//								+ "' where t.id='"
//								+ trade.getId()
//								+ "'");
				// 清除持卡人cvv,有效期
				// this.commonService
				// .deleteBySql("update international_cardholdersinfo t set
				// t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
				// + trade.getId() + "'");
				return INPUT;
			}
			if (chnals.equals("EVIP") || chnals.equals("CN3D")
					|| chnals.equals("SFE")) {
				// /连接ECPSS系统 s-to-s模式支付
				java.text.DateFormat format2 = new java.text.SimpleDateFormat(
						"yyyyMMddhhmmss");
				String dateTime = format2.format(trade.getTradeTime());

				String md5src; // 加密字符串
				md5src = "1002" + trade.getOrderNo() + "3"
						+ trade.getRmbAmount() + "en" + ReturnURL + dateTime
						+ "sfepaymd";
				String MD5info_; // MD5加密后的字符串
				MD5info_ = md5.getMD5ofStr(md5src);
				String returnurl = "https://security.sslepay.com/evippayment";
				URL url;
				System.out.println("post网址+++++++++++" + returnurl);
				try {
					url = new URL(returnurl);

					URLConnection connection = url.openConnection();
					connection.setDoOutput(true);
					OutputStreamWriter out = new OutputStreamWriter(
							connection.getOutputStream(), "8859_1");
					String parte = "cardnum=" + cardnum + "&cvv2=" + this.cvv2
							+ "&month=" + this.month + "&year=" + this.year
							+ "&cardbank=" + cardbank +

							"&MerNo=" + "1002" + "&BillNo="
							+ trade.getOrderNo() + "&Amount="
							+ trade.getRmbAmount() + "&Currency=" + "3"
							+ "&email=" + email + "&Language=" + "en"
							+ "&MD5info=" + MD5info_ +

							"&dateTime=" + dateTime + "&ip=" + ip
							+ "&firstname=" + this.firstname + "&lastname="
							+ this.lastname + "&phone=" + this.phone
							+ "&zipcode=" + this.zipcode + "&address="
							+ this.address + "&city=" + this.city + "&state="
							+ this.state + "&country=" + this.country
							+ "&products=" + buyerinformation +

							"&ReturnURL=" + ReturnURL;

					out.write(parte); // 这里组织提交信息
					out.flush();
					out.close();
					// 获取返回数据
					BufferedReader in = new BufferedReader(
							new InputStreamReader(connection.getInputStream()));
					String line = null;
					StringBuffer content = new StringBuffer();
					while ((line = in.readLine()) != null) {
						// line为返回值，这就可以判断是否成功、
						content.append(line);
						System.out.println(content);
					}
					Map responseFields = createMapFromResponse(content
							.toString().trim());
					// 金额:
					// String PaymentOrderNo =
					// VcpUtil.null2unknown("PaymentOrderNo",responseFields);
					String Succeed = VcpUtil.null2unknown("Succeed",
							responseFields);
					String paymentmessage = VcpUtil.null2unknown("Result",
							responseFields);
					String billaddress = VcpUtil.null2unknown("BillingAddress",
							responseFields);
					if (Succeed.equals("88")) {
						// 支付成功
						this.message = "Payment Success!";
						this.responseCode = 88;
						MD5info = trade.getMerchantOrderNo()
								+ trade.getMoneyType() + ordercountValue
								+ responseCode + MD5key;
						md5Value = md5.getMD5ofStr(MD5info);
						trade.setTradeState("1"
								+ trade.getTradeState().substring(1,
										trade.getTradeState().length()));
						trade.setRemark(message);
						this.commonService.update(trade);
//						this.commonService
//								.deleteBySql("update  international_tradeinfo t  set t.tradestate='1'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
//										+ message
//										+ "' where t.id='"
//										+ trade.getId() + "'");

						// ///---------------给持卡人发送邮件-----------------------////
						EmailInfo emailinfo = new EmailInfo();
						String mailinfo = emailinfo.getPaymentResultEmail(
								card.getEmail(),
								trade.getTradeAmount(),
								getStates().getCurrencyTypeByNo(
										trade.getMoneyType().intValue()),
								trade.getTradeUrl(), trade.getTradeTime(),
								billaddress, trade.getMerchantOrderNo(),
								trade.getOrderNo());
						try {
							// 发送邮件,如果发送失败插入数据库发送
							CCSendMail.setSendMail(card.getEmail(), mailinfo,
									"sfepay@sfepay.com");
							System.out.println("邮件立马发出");
						} catch (Exception e) {
							// 往数据库插入等待发送邮件
							shopManagerService.addSendMessages(card.getEmail(),
									"sfepay@sfepay.com", mailinfo, "0");
							System.out.println("邮件等待稍后发出");
							return INPUT;
						}
						return INPUT;
					} else if (Succeed.equals("19")) {
						// 支付成功
						this.responseCode = 19;

						MD5info = trade.getMerchantOrderNo()
								+ trade.getMoneyType() + ordercountValue
								+ responseCode + MD5key;
						md5Value = md5.getMD5ofStr(MD5info);
						trade.setTradeState("2"
								+ trade.getTradeState().substring(1,
										trade.getTradeState().length()));
						trade.setRemark(message);
						this.commonService.update(trade);
//						this.commonService
//						.deleteBySql("update  international_tradeinfo t  set t.tradestate='2'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
//								+ message
//								+ "' where t.id='"
//								+ trade.getId() + "'");
						return INPUT;
					} else {
						this.message = "Payment Declined!" + Succeed;
						this.responseCode = 0;
						MD5info = trade.getMerchantOrderNo()
								+ trade.getMoneyType() + ordercountValue
								+ responseCode + MD5key;
						md5Value = md5.getMD5ofStr(MD5info);
						trade.setTradeState("0"
								+ trade.getTradeState().substring(1,
										trade.getTradeState().length()));
						trade.setRemark(paymentmessage);
						this.commonService.update(trade);
//						this.commonService
//								.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
//										+ paymentmessage
//										+ "'  where t.id='"
//										+ trade.getId() + "'");
						return INPUT;
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					message = "Your payment is being processed";
					responseCode = 0;
					System.out.println("返回状态码+++++++++" + responseCode);
					MD5info = trade.getMerchantOrderNo() + trade.getMoneyType()
							+ ordercountValue + responseCode + MD5key;
					md5Value = md5.getMD5ofStr(MD5info);
					trade.setTradeState("0"
							+ trade.getTradeState().substring(1,
									trade.getTradeState().length()));
					this.commonService.update(trade);
//					this.commonService
//							.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)) where t.id='"
//									+ trade.getId() + "'");
					try {
						CCSendMail.setSendMail("89610614@qq.com",
								"sfepay can not connect to ecpss",
								"sfepay@sfepay.com");
					} catch (Exception ea) {
						return INPUT;
					}
					return INPUT;
				}

			} else if (chnals.equals("VIP")) {
				// remark = "VIP待处理";
				card.setCvv2(AES.setCarNo(cvv2));
				card.setExpiryDate(AES.setCarNo(expirydate));
				this.commonService.update(card);
				message = "Your payment is being processed";
				responseCode = 19;
				System.out.println("返回状态码+++++++++" + responseCode);
				MD5info = trade.getMerchantOrderNo() + trade.getMoneyType()
						+ ordercountValue + responseCode + MD5key;
				md5Value = md5.getMD5ofStr(MD5info);
				trade.setTradeState("2"
						+ trade.getTradeState().substring(1,
								trade.getTradeState().length()));
				this.commonService.update(trade);
//				this.commonService
//						.deleteBySql("update  international_tradeinfo t  set t.tradestate='2'||substr(t.tradestate,2,(length(t.tradestate)-1)) where t.id='"
//								+ trade.getId() + "'");
				return INPUT;

			} else if (chnals.equals("TC")) {

			} else if (chnals.equals("VC")) {

			} else if (chnals.equals("VVIP")) {

			} else if (chnals.equals("MC")) {

			} else if (chnals.equals("MVIP")) {

			} else if (chnals.equals("PRE")) {

			} else if (chnals.equals("VPN")) {

				vpn.DCCMessage dcc = new vpn.DCCMessage();
				dcc.setTrans_Type("enqrate");// 类型
				dcc.setMerchant_Id(posMerchantNo);// 42 商户编号
				dcc.setAuthor_Str(it.get(0).getAuthcode());
				dcc.setTerminal_Id(posNumber);// 41 商户终端号
				dcc.setInvoice_No(trade.getOrderNo().substring(
						trade.getOrderNo().length() - 6,
						trade.getOrderNo().length()));

				// 有效期
				dcc.setOrder_No(trade.getOrderNo());// 62
				dcc.setCustom(trade.getOrderNo());

				dcc.setHashCode(it.get(0).getHashcode());
				dcc.setCurrency_Code_T("156");// 货币代码
				dcc.setAmount_Loc(this.buzero(trade.getRmbAmount() + ""));// 4
				// 本地交易金额
				dcc.setCard_No(card.getCardNo());// 账号2
				dcc.setExp_Date(year + month);// 14
				VpnUtil vu = new VpnUtil();
				Long tim1 = System.currentTimeMillis();
				try {
					// type 1 汇率查询
					 dcc = vu.getDCCvalue(dcc, "1");
//					dcc.setResp_Code("99YY");
				} catch (Exception e) {
					this.responseCode = 19;
					message = "Your payment is being processed";
					MD5info = trade.getMerchantOrderNo() + trade.getMoneyType()
							+ ordercountValue + responseCode + MD5key;
					md5Value = md5.getMD5ofStr(MD5info);

					trade.setTradeState("0"
							+ trade.getTradeState().substring(1,
									trade.getTradeState().length()));
					trade.setRemark(message);
					trade.setVIPDisposePorson("System");
					trade.setVIPDisposeDate(new Date());
					this.commonService.update(trade);
					// this.commonService
					// .deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
					// + message
					// + "' ,t.VIPDisposePorson='System' "
					// + " ,t.VIPDisposeDate=sysdate "
					// + "  where t.id='" + trade.getId() + "'");
					return "input";
				}
				if (dcc.getResp_Code().equals("99YY")) {
					// 交易
					dcc.setTrans_Type("sales");
					dcc.setTrans_Model("E");
					dcc.setMerchant_Id(posMerchantNo);// 42 商户编号
					dcc.setTerminal_Id(posNumber);// 41 商户终端号
					
//					dcc3.setMerchant_Id("021817326000001");
//					dcc3.setTerminal_Id("07882349");

					dcc.setAuthor_Str(it.get(0).getAuthcode());
					dcc.setInvoice_No(trade.getOrderNo().substring(
							trade.getOrderNo().length() - 6,
							trade.getOrderNo().length()));
					dcc.setCurrency_Code_T("156");
					dcc.setAmount_Loc(this.buzero(trade.getRmbAmount() + ""));// 4
					dcc.setCSC(cvv2);
					dcc.setCard_No(card.getCardNo());// 账号2
					dcc.setExp_Date(year + month);// 14
					dcc.setOrder_No(trade.getOrderNo());// 62
					dcc.setCustom(trade.getOrderNo());
//					dcc3.setCurrency_Code(currency_Code);
//					dcc3.setAmount_For("100");
					dcc.setBocs_ReturnURL("http://172.20.66.2/ret");
					dcc.setEnd_ReturnURL("http://172.20.66.2/sfe");
					
					dcc.setHashCode("5gqddlw971e20c07edek4mcdnxhrst8j");    
					String buf = "rsn8h9p5fjrlk901vpnkm2dhvm80rm0uABCD1234";
					VpnUtil vpn = new VpnUtil();

					Long tim2 = System.currentTimeMillis();
					try {
						dcc = vpn.getDCCvalue(dcc, "12");
					} catch (Exception e) {
						responseCode = 19;
						message = "Your payment is being processed";
						MD5info = trade.getMerchantOrderNo()
								+ trade.getMoneyType() + ordercountValue
								+ responseCode + MD5key;
						md5Value = md5.getMD5ofStr(MD5info);

						trade.setTradeState("0"
								+ trade.getTradeState().substring(1,
										trade.getTradeState().length()));
						trade.setRemark(message);
						trade.setVIPDisposePorson("System");
						trade.setVIPDisposeDate(new Date());
						this.commonService.update(trade);
						// this.commonService
						// .deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
						// + message
						// + "' ,t.VIPDisposePorson='System' "
						// + " ,t.VIPDisposeDate=sysdate "
						// + "  where t.id='"
						// + trade.getId()
						// + "'");
						return INPUT;
					}
					if (dcc.getResp_Code().equals("1000")) {
						this.message = "Payment Success!";
						this.responseCode = 88;
						// 清除持卡人cvv,有效期
						// this.commonService
						// .deleteBySql("update  international_cardholdersinfo t  set t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
						// + trade.getId() + "'");
						trade.setTradeState("1"
								+ trade.getTradeState().substring(1,
										trade.getTradeState().length()));
						trade.setRemark(message);
						trade.setVIPDisposePorson("System");
						trade.setVIPDisposeDate(new Date());
						trade.setVIPBatchNo(dcc.getAuth_Code());
						trade.setVIPTerminalNo(posNumber);
						trade.setVIPAuthorizationNo(dcc.getInvoice_No());
						this.commonService.update(trade);
						card.setExpiryDate("0000");
						card.setCvv2("XXX");
						this.commonService.update(card);
						// this.commonService
						// .deleteBySql("update  international_tradeinfo t  set t.tradestate='1'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
						// + message
						// + "' ,t.VIPAuthorizationNo='"
						// + dcc2.getAuth_Code()
						// + "' ,t.VIPDisposePorson='System' "
						// + " ,t.VIPDisposeDate=sysdate "
						// + " ,t.VIPBatchNo='XXXXXX',t.VIPTerminalNo='"
						// + posNumber
						// + "' where t.id='"
						// + trade.getId() + "'");
						MD5info = trade.getMerchantOrderNo()
								+ trade.getMoneyType() + ordercountValue
								+ responseCode + MD5key;
						md5Value = md5.getMD5ofStr(MD5info);

						// 发送邮件
						List<InternationalTerminalManager> tmm = this.commonService
								.list("select tm from InternationalTerminalManager tm where tm.terminalNo='"
										+ posNumber.trim() + "' ");
						String billaddressby = null;
						if (tmm.size() > 0) {
							InternationalTerminalManager tm = tmm.get(0);
							billaddressby = tm.getBillingAddress();
						}
						String mailinfo = null;
						try {
							EmailInfo emailinfo = new EmailInfo();
							mailinfo = emailinfo.getPaymentResultEmail(
									card.getEmail(),
									trade.getTradeAmount(),
									getStates().getCurrencyTypeByNo(
											trade.getMoneyType().intValue()),
									trade.getTradeUrl(), trade.getTradeTime(),
									billaddressby, trade.getMerchantOrderNo(),
									trade.getOrderNo(), merchant);
							// 发送邮件,如果发送失败插入数据库发送
							if (merchant.getStatutes().substring(4, 5)
									.equals("0")) {
								CCSendMail.setSendMail(card.getEmail(),
										mailinfo, "sfepay@sfepay.com");
								System.out.println("邮件立马发出");
							}
						} catch (Exception e) {
							// 往数据库插入等待发送邮件
							shopManagerService.addSendMessages(card.getEmail(),
									"sfepay@sfepay.com", mailinfo, "0");
							System.out.println("邮件等待稍后发出");
							return INPUT;
						}
						return INPUT;
					} else {
						this.message = "Payment Declined!";
						this.responseCode = Integer
								.valueOf(dcc.getResp_Code());
						// this.commonService
						// .deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
						// + message
						// + "' where t.id='"
						// + trade.getId() + "'");
						// this.commonService
						// .deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
						// + message
						// + "' ,t.VIPDisposePorson='System' "
						// + " ,t.VIPDisposeDate=sysdate "
						// + "  where t.id='"
						// + trade.getId()
						// + "'");
						trade.setTradeState("0"
								+ trade.getTradeState().substring(1,
										trade.getTradeState().length()));
						trade.setRemark(message);
						trade.setVIPDisposePorson("System");
						trade.setVIPDisposeDate(new Date());
						this.commonService.update(trade);
						MD5info = trade.getMerchantOrderNo()
								+ trade.getMoneyType() + ordercountValue
								+ responseCode + MD5key;
						md5Value = md5.getMD5ofStr(MD5info);
						return INPUT;
					}

				} else if (dcc.getResp_Code().equals("99YX")) {

					// 交易
					dcc.setTrans_Type("sales");
					dcc.setTrans_Model("E");
					dcc.setMerchant_Id(posMerchantNo);// 42 商户编号
					dcc.setTerminal_Id(posNumber);// 41 商户终端号
					
//					dcc3.setMerchant_Id("021817326000001");
//					dcc3.setTerminal_Id("07882349");

					dcc.setAuthor_Str(it.get(0).getAuthcode());
					dcc.setInvoice_No(trade.getOrderNo().substring(
							trade.getOrderNo().length() - 6,
							trade.getOrderNo().length()));
					dcc.setCurrency_Code_T("156");
					dcc.setAmount_Loc(this.buzero(trade.getRmbAmount() + ""));// 4
					dcc.setCSC(cvv2);
					dcc.setCard_No(card.getCardNo());// 账号2
					dcc.setExp_Date(year + month);// 14
					dcc.setOrder_No(trade.getOrderNo());// 62
					dcc.setCustom(trade.getOrderNo());
//					dcc3.setCurrency_Code(currency_Code);
//					dcc3.setAmount_For("100");
					dcc.setBocs_ReturnURL("http://172.20.66.2/ret");
					dcc.setEnd_ReturnURL("http://172.20.66.2/sfe");
					
					dcc.setHashCode("5gqddlw971e20c07edek4mcdnxhrst8j");    
					String buf = "rsn8h9p5fjrlk901vpnkm2dhvm80rm0uABCD1234";

					VpnUtil vu3 = new VpnUtil();
					Long tim2 = System.currentTimeMillis();
					try {
						// type 3 edc交易
						dcc = vu3.getDCCvalue(dcc, "12");
					} catch (Exception e) {
						responseCode = Integer.valueOf(dcc.getResp_Code());
						message = "Your payment is being processed";
						MD5info = trade.getMerchantOrderNo()
								+ trade.getMoneyType() + ordercountValue
								+ responseCode + MD5key;
						md5Value = md5.getMD5ofStr(MD5info);
						// this.commonService
						// .deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
						// + message
						// + "' ,t.VIPDisposePorson='System' "
						// + " ,t.VIPDisposeDate=sysdate "
						// + "  where t.id='"
						// + trade.getId()
						// + "'");
						trade.setTradeState("0"
								+ trade.getTradeState().substring(1,
										trade.getTradeState().length()));
						trade.setRemark(message);
						trade.setVIPDisposePorson("System");
						trade.setVIPDisposeDate(new Date());
						this.commonService.update(trade);
						return INPUT;
					}
					if (dcc.getResp_Code().equals("1000")) {
						// 清除持卡人cvv,有效期
						// this.commonService
						// .deleteBySql("update  international_cardholdersinfo t  set t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
						// + trade.getId() + "'");
						// this.commonService
						// .deleteBySql("update  international_tradeinfo t  set t.tradestate='1'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
						// + message
						// + "' ,t.VIPAuthorizationNo='"
						// + dcc3.getAuth_Code()
						// + "' ,t.VIPDisposePorson='System' "
						// + " ,t.VIPDisposeDate=sysdate "
						// + " ,t.VIPBatchNo='XXXXXX',t.VIPTerminalNo='"
						// + posNumber
						// + "' where t.id='"
						// + trade.getId() + "'");
						trade.setTradeState("1"
								+ trade.getTradeState().substring(1,
										trade.getTradeState().length()));
						trade.setRemark(message);
						trade.setVIPDisposePorson("System");
						trade.setVIPDisposeDate(new Date());
						trade.setVIPBatchNo(dcc.getAuth_Code());
						trade.setVIPTerminalNo(posNumber);
						trade.setVIPAuthorizationNo(dcc.getInvoice_No());
						this.commonService.update(trade);
						card.setExpiryDate("0000");
						card.setCvv2("XXX");
						this.commonService.update(card);
						// 发送邮件
						List<InternationalTerminalManager> tmm = this.commonService
								.list("select tm from InternationalTerminalManager tm where tm.terminalNo='"
										+ posNumber.trim() + "' ");
						String billaddressby = null;
						if (tmm.size() > 0) {
							InternationalTerminalManager tm = tmm.get(0);
							billaddressby = tm.getBillingAddress();
						}
						String mailinfo = null;
						try {
							EmailInfo emailinfo = new EmailInfo();
							mailinfo = emailinfo.getPaymentResultEmail(
									card.getEmail(),
									trade.getTradeAmount(),
									getStates().getCurrencyTypeByNo(
											trade.getMoneyType().intValue()),
									trade.getTradeUrl(), trade.getTradeTime(),
									billaddressby, trade.getMerchantOrderNo(),
									trade.getOrderNo(), merchant);
							// 发送邮件,如果发送失败插入数据库发送
							if (merchant.getStatutes().substring(4, 5)
									.equals("0")) {
								CCSendMail.setSendMail(card.getEmail(),
										mailinfo, "sfepay@sfepay.com");
								System.out.println("邮件立马发出");
							}
						} catch (Exception e) {
							// 往数据库插入等待发送邮件
							shopManagerService.addSendMessages(card.getEmail(),
									"sfepay@sfepay.com", mailinfo, "0");
							System.out.println("邮件等待稍后发出");
							return INPUT;
						}
						this.message = "Payment Success!";
						this.responseCode = 88;
						// this.commonService
						// .deleteBySql("update  international_tradeinfo t  set t.tradestate='1'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
						// + message
						// + "' where t.id='"
						// + trade.getId() + "'");

						MD5info = trade.getMerchantOrderNo()
								+ trade.getMoneyType() + ordercountValue
								+ responseCode + MD5key;
						md5Value = md5.getMD5ofStr(MD5info);
						return SUCCESS;
					} else {
						this.message = "Payment Declined!";
						this.responseCode = Integer
								.valueOf(dcc.getResp_Code());
						// this.commonService
						// .deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
						// + message
						// + "' where t.id='"
						// + trade.getId() + "'");
						// this.commonService
						// .deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
						// + message
						// + "' ,t.VIPDisposePorson='System' "
						// + " ,t.VIPDisposeDate=sysdate "
						// + "  where t.id='" + trade.getId() + "'");
						trade.setTradeState("0"
								+ trade.getTradeState().substring(1,
										trade.getTradeState().length()));
						trade.setRemark(message);
						trade.setVIPDisposePorson("System");
						trade.setVIPDisposeDate(new Date());
						this.commonService.update(trade);
						MD5info = trade.getMerchantOrderNo()
								+ trade.getMoneyType() + ordercountValue
								+ responseCode + MD5key;
						md5Value = md5.getMD5ofStr(MD5info);
						return INPUT;
					}

				}
			}
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;

		}
	}

	public boolean checkAll() {
		boolean checkemail = false;
		boolean checkcvv = false;
		boolean checkmonth = false;
		boolean checkyear = false;
		boolean checkcardno = false;
		// 验证邮箱
		Pattern p = Pattern
				.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
		Matcher m = p.matcher(this.getEmail());
		// Matcher m1 = p.matcher(this.getShippingEmail());
		if (!m.find()) {
			checkemail = true;
		}
		// else if(!m1.find()){
		// check = true;
		// }
		if (this.getCvv2().length() != 3) {
			checkcvv = true;
		}
		if (!StringUtils.isNotBlank(this.getMonth())) {
			checkmonth = true;
		}
		if (!StringUtils.isNotBlank(this.getYear())) {
			checkyear = true;
		}
		if (!CheckCardNo.isValid(this.getCardnum())) {
			checkcardno = true;
		}
		if (!checkemail && !checkcvv && !checkmonth && !checkyear
				&& !checkcardno) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * ip
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		// System.out.println("ip----------------"+ip);
		return ip;

	}

	public Hashtable getmmValue(HashMap hm) {

		h = exam.maxMindScore(hm);
		// 把MaxMind返回的参数打印出来,
		for (Iterator i = h.keySet().iterator(); i.hasNext();) {
			String key = (String) i.next();
			maxmindValue = (String) h.get(key);
			if (key.equals("score")) {
				values = maxmindValue;

			}
			if (key.equals("binName")) {
				bankName = maxmindValue;
			}
			if (key.equals("binCountry")) {
				bankCountry = maxmindValue;
			}
			if (key.equals("binPhone")) {
				bankPhone = maxmindValue;
			}
		}
		Hashtable ht = new Hashtable();
		if (values == null) {
			values = "0";
		}
		ht.put("values", values);
		ht.put("bankName", bankName);
		ht.put("bankCountry", bankCountry);
		ht.put("bankPhone", bankPhone);
		return ht;
	}

	private Map createMapFromResponse(String queryString) {
		Map map = new HashMap();
		StringTokenizer st = new StringTokenizer(queryString, "&");
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			int i = token.indexOf('=');
			if (i > 0) {
				try {
					String key = token.substring(0, i);
					String value = URLDecoder.decode(token.substring(i + 1,
							token.length()));
					map.put(key, value);
				} catch (Exception ex) {
					// Do Nothing and keep looping through data
				}
			}
		}
		return map;
	}

	public String getRorderno() {
		return rorderno;
	}

	public void setRorderno(String rorderno) {
		this.rorderno = rorderno;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCardbank() {
		return cardbank;
	}

	public void setCardbank(String cardbank) {
		this.cardbank = cardbank;
	}

	public String getCardnum() {
		return cardnum;
	}

	public void setCardnum(String cardnum) {
		this.cardnum = cardnum;
	}

	public String getCvv2() {
		return cvv2;
	}

	public void setCvv2(String cvv2) {
		this.cvv2 = cvv2;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public String getCookieId() {
		return cookieId;
	}

	public void setCookieId(String cookieId) {
		this.cookieId = cookieId;
	}

	public String getVpc_Card() {
		return vpc_Card;
	}

	public void setVpc_Card(String vpc_Card) {
		this.vpc_Card = vpc_Card;
	}

	public String getMaxmindValue() {
		return maxmindValue;
	}

	public void setMaxmindValue(String maxmindValue) {
		this.maxmindValue = maxmindValue;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankCountry() {
		return bankCountry;
	}

	public void setBankCountry(String bankCountry) {
		this.bankCountry = bankCountry;
	}

	public String getBankPhone() {
		return bankPhone;
	}

	public void setBankPhone(String bankPhone) {
		this.bankPhone = bankPhone;
	}

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}

	public MaxMindExample getExam() {
		return exam;
	}

	public void setExam(MaxMindExample exam) {
		this.exam = exam;
	}

	public String getReturnURL() {
		return ReturnURL;
	}

	public void setReturnURL(String returnURL) {
		ReturnURL = returnURL;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getMD5key() {
		return MD5key;
	}

	public void setMD5key(String md5key) {
		MD5key = md5key;
	}

	public Double getOrdercount() {
		return ordercount;
	}

	public void setOrdercount(Double ordercount) {
		this.ordercount = ordercount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMD5info() {
		return MD5info;
	}

	public void setMD5info(String md5info) {
		MD5info = md5info;
	}

	public String getTradeMoneyType() {
		return tradeMoneyType;
	}

	public void setTradeMoneyType(String tradeMoneyType) {
		this.tradeMoneyType = tradeMoneyType;
	}

	public String getMerchantOrderNo() {
		return merchantOrderNo;
	}

	public void setMerchantOrderNo(String merchantOrderNo) {
		this.merchantOrderNo = merchantOrderNo;
	}

	public String getMd5Value() {
		return md5Value;
	}

	public void setMd5Value(String md5Value) {
		this.md5Value = md5Value;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getShippingEmail() {
		return shippingEmail;
	}

	public void setShippingEmail(String shippingEmail) {
		this.shippingEmail = shippingEmail;
	}

	public String getShippingPhone() {
		return shippingPhone;
	}

	public void setShippingPhone(String shippingPhone) {
		this.shippingPhone = shippingPhone;
	}

	public String getShippingZipcode() {
		return shippingZipcode;
	}

	public void setShippingZipcode(String shippingZipcode) {
		this.shippingZipcode = shippingZipcode;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getShippingCity() {
		return shippingCity;
	}

	public void setShippingCity(String shippingCity) {
		this.shippingCity = shippingCity;
	}

	public String getShippingSstate() {
		return shippingSstate;
	}

	public void setShippingSstate(String shippingSstate) {
		this.shippingSstate = shippingSstate;
	}

	public String getShippingCountry() {
		return shippingCountry;
	}

	public void setShippingCountry(String shippingCountry) {
		this.shippingCountry = shippingCountry;
	}

	public String getVpc_CardExp() {
		return vpc_CardExp;
	}

	public void setVpc_CardExp(String vpc_CardExp) {
		this.vpc_CardExp = vpc_CardExp;
	}

	public String getShippingFirstName() {
		return shippingFirstName;
	}

	public void setShippingFirstName(String shippingFirstName) {
		this.shippingFirstName = shippingFirstName;
	}

	public String getShippingLastName() {
		return shippingLastName;
	}

	public void setShippingLastName(String shippingLastName) {
		this.shippingLastName = shippingLastName;
	}

	public String getAmount_Transaction_Foreign() {
		return amount_Transaction_Foreign;
	}

	public void setAmount_Transaction_Foreign(String amount_Transaction_Foreign) {
		this.amount_Transaction_Foreign = amount_Transaction_Foreign;
	}

	public String getConversion_Rate() {
		return conversion_Rate;
	}

	public void setConversion_Rate(String conversion_Rate) {
		this.conversion_Rate = conversion_Rate;
	}

	public String getCurrency_Code_Foreign() {
		return currency_Code_Foreign;
	}

	public void setCurrency_Code_Foreign(String currency_Code_Foreign) {
		this.currency_Code_Foreign = currency_Code_Foreign;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public TradeManager getTradeManager() {
		return tradeManager;
	}

	public void setTradeManager(TradeManager tradeManager) {
		this.tradeManager = tradeManager;
	}

	public String getVirtualPaymentClientURL() {
		return virtualPaymentClientURL;
	}

	public void setVirtualPaymentClientURL(String virtualPaymentClientURL) {
		this.virtualPaymentClientURL = virtualPaymentClientURL;
	}

	public String getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public int getTradeOrder() {
		return tradeOrder;
	}

	public void setTradeOrder(int tradeOrder) {
		this.tradeOrder = tradeOrder;
	}

	public String getConnectURL() {
		return connectURL;
	}

	public void setConnectURL(String connectURL) {
		this.connectURL = connectURL;
	}

	public String getVpc_CardNum() {
		return vpc_CardNum;
	}

	public void setVpc_CardNum(String vpc_CardNum) {
		this.vpc_CardNum = vpc_CardNum;
	}

	public String getProducts() {
		return products;
	}

	public void setProducts(String products) {
		this.products = products;
	}

	public String buzero(String refundRMBMoney) {
		String refundRMB = "000000000000";
		String zero_12 = "000000000000";
		DecimalFormat decimalFormat = new DecimalFormat("##############0.00");
		if (StringUtils.isNotBlank(refundRMBMoney)
				&& refundRMBMoney.replace(".", "").matches("\\d+")) {
			String refundRMBStr = Double.valueOf((decimalFormat.format(Double
					.valueOf(refundRMBMoney)))) * 100 + "";
			String refundRMB_0 = zero_12
					+ refundRMBStr.substring(0, refundRMBStr.indexOf("."));
			refundRMB = refundRMB_0.substring(refundRMB_0.length() - 12,
					refundRMB_0.length());
		}
		return refundRMB;
	}

	public String getThesame() {
		return thesame;
	}

	public void setThesame(String thesame) {
		this.thesame = thesame;
	}

	public String getSECURE_SECRET() {
		return SECURE_SECRET;
	}

	public void setSECURE_SECRET(String secure_secret) {
		SECURE_SECRET = secure_secret;
	}

	public String getLocal_money() {
		return local_money;
	}

	public void setLocal_money(String local_money) {
		this.local_money = local_money;
	}

	public String getForeign_money() {
		return foreign_money;
	}

	public void setForeign_money(String foreign_money) {
		this.foreign_money = foreign_money;
	}

	public String getConversion_Rate_show() {
		return conversion_Rate_show;
	}

	public void setConversion_Rate_show(String conversion_Rate_show) {
		this.conversion_Rate_show = conversion_Rate_show;
	}

	public String getCar_termanal() {
		return car_termanal;
	}

	public void setCar_termanal(String car_termanal) {
		this.car_termanal = car_termanal;
	}

	public String getCardMessage() {
		return cardMessage;
	}

	public void setCardMessage(String cardMessage) {
		this.cardMessage = cardMessage;
	}

	public String getNewcardtype() {
		return newcardtype;
	}

	public void setNewcardtype(String newcardtype) {
		this.newcardtype = newcardtype;
	}

	private String getNavigator(String userAgent) {

		try {
			if (userAgent.indexOf("TencentTraveler") > 0)
				return "TT(QQ) Browser";
			if (userAgent.indexOf("Maxthon") > 0)
				return "Maxthon Browser";
			if (userAgent.indexOf("MyIE2") > 0)
				return "MyIE2 Browser";
			if (userAgent.indexOf("Opera") > 0) {
				int i = userAgent.indexOf("Opera");
				String tt = userAgent.substring(i, i + 10);
				return tt + " Browser";
			}
			if (userAgent.indexOf("Chrome") > 0
					&& userAgent.indexOf("Safari") > 0) {
				int i = userAgent.indexOf("Chrome");
				String tt = userAgent.substring(i, i + 10);
				return tt + " Browser";
			}
			if (userAgent.indexOf("Safari") > 0)
				return "Safari Browser";
			if (userAgent.indexOf("Firefox") > 0) {
				int i = userAgent.indexOf("Firefox");
				String tt = userAgent.substring(i, i + 12);
				return "Firefox Browser";
			}
			if (userAgent.indexOf("MSIE 6") > 0)
				return "IE6 Browser";
			if (userAgent.indexOf("MSIE 7") > 0)
				return "IE7 Browser";
			if (userAgent.indexOf("MSIE 8") > 0)
				return "IE8 Browser";
			if (userAgent.indexOf("MSIE 9") > 0)
				return "IE9 Browser";
		} catch (Exception e) {
			// 出错时使用另外的方式获取买家浏览器信息
			// UserAgent userAgentOther = UserAgent.parseUserAgentString(request
			// .getHeader("User-Agent"));
			// return userAgentOther.getBrowser().getName();
			return "unkown Browser";
		}
		return "unkown Browser";
	}

	private String getOS(String userAgent) {
		try {
			if (userAgent.indexOf("Windows NT 5.1") > 0)
				return "Windows XP";
			if (userAgent.indexOf("Windows NT 5.2") > 0)
				return "Windows 2003";
			if (userAgent.indexOf("Windows 98") > 0)
				return "Windows 98";
			if (userAgent.indexOf("Windows NT 5.0") > 0)
				return "Windows 2000";
			if (userAgent.indexOf("Linux") > 0)
				return "Linux";
			if (userAgent.indexOf("Mac") > 0)
				return "Mac OS";
			if (userAgent.indexOf("Windows NT 6.0") > 0)
				return "Windows Vista";
			if (userAgent.indexOf("Windows NT 6.1") > 0)
				return "Windows 7";
			if (userAgent.indexOf("Unix") > 0)
				return "Unix";
		} catch (Exception e) {
			// 出错时使用另外方式获取买家操作系统以及终端信息
			// UserAgent userAgentOther = UserAgent.parseUserAgentString(request
			// .getHeader("User-Agent"));
			// return userAgentOther.getOperatingSystem().getName()
			// + "##终端类型:"
			// + userAgentOther.getOperatingSystem().getDeviceType()
			// .getName();
			return "unkown OS";
		}
		return "unkown OS";
	}
}
