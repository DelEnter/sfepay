package com.ecpss.action.pay;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

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
import com.ecpss.model.shop.InternationalExchangerate;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.model.shop.InternationalMerchantManager;
import com.ecpss.model.shop.InternationalMerchantTerminal;
import com.ecpss.model.shop.InternationalMoneykindname;
import com.ecpss.model.shop.InternationalTerminalManager;
import com.ecpss.model.shop.InternationalTradecondition;
import com.ecpss.service.iservice.ShopManagerService;
import com.ecpss.util.AES;
import com.ecpss.util.BOCPayResult;
import com.ecpss.util.CCSendMail;
import com.ecpss.util.CN3DPayResult;
import com.ecpss.util.DateUtil;
import com.ecpss.util.EmailInfo;
import com.ecpss.util.EvipPayResult;
import com.ecpss.util.GetBatchNo;
import com.ecpss.util.MD5;

public class StoSPaymentAction extends BaseAction {
	@Autowired
	@Qualifier("tradeManager")
	private TradeManager tradeManager;
	@Autowired
	@Qualifier("shopManagerService")
	private ShopManagerService shopManagerService;
	// 卡信息
	private String cardnum; // 卡号
	private String cvv2;
	private String ip;
	private String cookie;
	private String cookieId;

	private String ReturnURL;
	private String MD5key;

	private HashMap h = new HashMap();

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

	// 抛过来的商户，订单，信息
	private String MerNo; // 商户ID
	private String rorderno; // 交易流水号
	private Date tradetime; // 交易时间
	private Long merchantnoValue;
	private Long merchantno;
	private boolean checkMerch;// 交易商户是否存在
	private long isDredge;// 检验商户通道是否开通
	private String Amount; // 支付金额

	private Double tradeMoney;// 支付金额
	private double rmbmoney = 0; // 人民币交易金额

	private Double tradeAmount; // 交易金额（外币）

	private String channels; // 使用的商户通道F
	private String BillNo; // 订单编号

	private String Currency; // 币种
	private String currencyName;
	private String Language = "en"; // 支付语言
	private String md5src; // 网关加密md5
	private String cooknumber;
	private String MD5info; // MD5加密
	// 程序处理需要的参数
	private int responseCode;// 响应代号；
	private String tradeAdd;
	private Long moneyType;
	private String message;
	// md5 数据校验
	private String tradeMoneyType;
	private String ordercount; // 支付金额(转换为支付金额)
	private String md5Value; // 支付网关对商户信息进行加密
	private String merchantOrderNo;

	private String resultMd5;
	// Q 支付请求.校验顺序：1.交易商户是否存在 ，2.检验商户通道是否开通 ;3.返回网址必须注册 4.信息是否被篡改。
	// 持卡人信息带过来 billingAddress
	private String firstname;
	private String lastname;
	private String cardbank;
	private String email;
	private String phone;
	private String zipcode;
	private String address;
	private String city;
	private String state;
	private String country;
	private String products;
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

	private String year; // 年
	private String month; // 月
	private String remark; // 备注

	private String dateTime;

	/**
	 * 返回支付结果信息
	 */
	private String paymentMerNo;
	private String paymentBillNo;
	private String paymentAmount;
	private String paymentResult; // 返回支付结果 00:成功 , 01~99: 失败 , NC:连接不上dcc
	private String authorizationNo = ""; // 授权号
	private String batchno = ""; // 授权号
	private String paymentCurrency;
	private String paymentDate;
	private String paymentResultRemark;
	private String paymentMD5INFO;

	/**
	 * viigoo网站支付
	 * 
	 * @return
	 */
	public String viigooPays() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			// HttpSession session = request.getSession();
			System.out.println("returnurl---------------" + this.getReturnURL()
					+ " ");
			System.out.println("merno---------------" + this.getMerNo() + " ");
			System.out.println("cardno---------------" + this.getCardnum()
					+ " ");
			System.out
					.println("BillNo---------------" + this.getBillNo() + " ");
			System.out
					.println("Amount---------------" + this.getAmount() + " ");
			System.out.println("month---------------" + this.getMonth() + " ");
			System.out.println("year---------------" + this.getYear() + " ");
			if (MerNo == null) {
				return INPUT;
			}
		
			merchantno = Long.valueOf(MerNo);
			MD5 md5 = new MD5();
			if (StringUtils.isNotBlank(Amount)) {
				ordercount = Amount.replace(",", "");
				ordercount = ordercount.replace(" 0", "");
				tradeMoney = Double.valueOf(ordercount);
			} else {
				responseCode = 26;
				resultMd5 = MerNo + BillNo + Currency + Amount + responseCode
						+ dateTime + MD5key;
				md5Value = md5.getMD5ofStr(resultMd5);
				message = "Payment Declined";
				// 不存在该商户号
				return INPUT;
			}
			//验证卡号信息
			if (StringUtils.isBlank(this.getCardnum())
					|| StringUtils.isBlank(this.getMonth())
					|| StringUtils.isBlank(this.getYear())
					|| StringUtils.isBlank(this.getCvv2())) {
				responseCode = 26;
				resultMd5 = MerNo + BillNo + Currency + Amount + responseCode
						+ dateTime + MD5key;
				md5Value = md5.getMD5ofStr(resultMd5);
				message = "Credit card information is not completely";
				// 不存在该商户号
				return INPUT;
			}
			//
			if (StringUtils.isBlank(BillNo)) {
				responseCode = 26;
				resultMd5 = MerNo + BillNo + Currency + Amount + responseCode
						+ dateTime + MD5key;
				md5Value = md5.getMD5ofStr(resultMd5);
				message = "Payment Declined";
				// 不存在该商户号
				return INPUT;
			}
			merchantOrderNo = BillNo;
			tradeMoneyType = Currency;
			currencyName = getStates().getCurrencyTypeByNo(
					Integer.valueOf(tradeMoneyType));
			// 交易商户是否存在
			String sql1 = " from InternationalMerchant t where t.merno='"
					+ this.MerNo + "'";
			// 获取商户资料

			InternationalMerchant merchant = new InternationalMerchant();
			List<InternationalMerchant> merchantList = this.commonService
					.list(sql1);
			if (merchantList == null || merchantList.size() == 0) {
				responseCode = 10;
				resultMd5 = MerNo + BillNo + Currency + Amount + responseCode
						+ dateTime + MD5key;
				md5Value = md5.getMD5ofStr(resultMd5);
				message = "Payment Declined";
				// 不存在该商户号
				return INPUT;

			}
			merchant = merchantList.get(0);
			// 获取mD5key值
			if (merchant != null) {
				if (merchant.getMd5key() == null) {
					responseCode = 11;
					resultMd5 = BillNo + Currency + Amount + responseCode
							+ MD5key;
					md5Value = md5.getMD5ofStr(resultMd5);
					message = "Payment Declined";
					return INPUT;
				} else {
					MD5key = merchant.getMd5key();

				}
			}
			// 获取交易流水号
			GetBatchNo ut = new GetBatchNo();
			rorderno = ut.getOrderinfo(MerNo);
			// 校验交易流水号是否重复
			String hql = "select count(*) from international_tradeinfo t where t.ORDERNO='"
					+ rorderno + "'";
			int trlist = this.tradeManager.intBySql(hql);
			if (trlist > 0) {
				this.responseCode = 24;
				resultMd5 = MerNo + BillNo + Currency + Amount + responseCode
						+ dateTime + MD5key;
				md5Value = md5.getMD5ofStr(resultMd5);
				message = "Payment Declined";
				return INPUT;
			}

			if (Double.valueOf(Amount) > 100000L) {
				this.responseCode = 26;
				resultMd5 = MerNo + BillNo + Currency + Amount + responseCode
						+ dateTime + MD5key;
				md5Value = md5.getMD5ofStr(resultMd5);
				return INPUT;
			}

			// 获取交易时间
			tradetime = ut.getTime();
			if (MerNo != null) {
				merchantnoValue = Long.valueOf(MerNo);
			} else {
				merchantnoValue = 0l;
			}

			if (merchant.getIsopen().equals("0")) {
				// 商户未开通
				responseCode = 15;
				resultMd5 = MerNo + BillNo + Currency + Amount + responseCode
						+ dateTime + MD5key;
				md5Value = md5.getMD5ofStr(resultMd5);
				message = "Payment Declined";
				return INPUT;

			}

			// 检验商户通道是否开通
			String sql2 = "select count(*) from international_merchantchannels t where t.merchantid='"
					+ merchant.getId() + "' and t.onoff='1'";
			int isDredge = this.tradeManager.intBySql(sql2);
			if (isDredge == 0) {
				responseCode = 16;
				resultMd5 = MerNo + BillNo + Currency + Amount + responseCode
						+ dateTime + MD5key;
				md5Value = md5.getMD5ofStr(resultMd5);
				message = "Payment Declined (MasterCard cannot be used at this moment, please use your VISA card. Thank you)";
				return INPUT;
			}

			// 币种 //select t.*, t.rowid from international_moneykindname t where
			// t.id=(select f.moneykindno from international_merchantcurrency f
			// where f.merchanid='1' )
//			List<InternationalMoneykindname> moneykinds = this.commonService
//					.list(" from InternationalMoneykindname t "
//							+ "where t.id in (select f.moneyKindNo from InternationalMerchantCurrency f "
//							+ "where f.merchanId='" + merchant.getId() + "' )");
//			if (moneykinds.size() == 0 || moneykinds == null) {
//
//				responseCode = 12;
//				resultMd5 = MerNo + BillNo + Currency + Amount + responseCode
//						+ dateTime + MD5key;
//				md5Value = md5.getMD5ofStr(resultMd5);
//				message = "Payment Declined";
//				return INPUT;
//			}
//			InternationalMoneykindname moneykind = moneykinds.get(0);
//			// 检验币种
//			if (!(Currency.equals(moneykind.getMoneykindno() + ""))) {
//
//				responseCode = 12;
//				resultMd5 = MerNo + BillNo + Currency + Amount + responseCode
//						+ dateTime + MD5key;
//				md5Value = md5.getMD5ofStr(resultMd5);
//				message = "Payment Declined";
//				return INPUT;
//			}
			// select t.*,t.rowid from international_exchangerate t where
			// t.executetime in (select max(f.executetime) from
			// international_exchangerate f where f.moneykindno=1 group by
			// f.type)
			// 获取交易汇率
			List changerates = this.commonService
					.getByList("select t.id,t.rate,t.type from international_exchangerate t,international_moneykindname m  where t.moneykindno=m.id "
							+ "and m.moneykindno="
							+ this.Currency
							+ " and t.executetime<sysdate-1   and t.type='1' order by t.executetime desc  "); // 交易汇率
			// 获取结算汇率
			List changeratesbalance = this.commonService
					.getByList("select t.id,t.rate,t.type from international_exchangerate t,international_moneykindname m  where t.moneykindno=m.id "
							+ "and m.moneykindno="
							+ this.Currency
							+ " and t.executetime<sysdate-1   and t.type='2' order by t.executetime desc  "); // 结算汇率

			if (changerates.size() < 1 && changeratesbalance.size() < 1) {
				responseCode = 12;
				resultMd5 = MerNo + BillNo + Currency + Amount + responseCode
						+ dateTime + MD5key;
				md5Value = md5.getMD5ofStr(resultMd5);
				message = "Payment Declined";
				return INPUT;
			}
			InternationalExchangerate changerate = new InternationalExchangerate();
			InternationalExchangerate settlementrate = new InternationalExchangerate();
			for (int i = 0; i < changerates.size(); i++) {
				Object[] tem = (Object[]) changerates.get(0);
				changerate.setId(Long.valueOf(tem[0].toString()));
				changerate.setRate(Double.valueOf(tem[1].toString()));
				changerate.setType("1");
			}
			for (int i = 0; i < changeratesbalance.size(); i++) {
				Object[] tem = (Object[]) changeratesbalance.get(0);
				settlementrate.setId(Long.valueOf(tem[0].toString()));
				settlementrate.setRate(Double.valueOf(tem[1].toString()));
				settlementrate.setType("2");
			}
			// 获取某个币种当前的汇率
			Double traderate = Double.valueOf(changerate.getRate());
			if (traderate != null) {
				rmbmoney = traderate * tradeMoney;
			} else {
				// 商户币种没设置
				responseCode = 12;
				resultMd5 = MerNo + BillNo + Currency + Amount + responseCode
						+ dateTime + MD5key;
				md5Value = md5.getMD5ofStr(resultMd5);
				message = "Payment Declined";
				return INPUT;
			}

			// 获取交易金额
			md5src = merchantnoValue + BillNo + Currency
					+ Amount + Language + ReturnURL + dateTime + MD5key;

			md5src = md5.getMD5ofStr(md5src);
			// 获取通道

			// 信息被篡改
			// System.out.println("========"+merchantnoValue +"========"+
			// BillNo+"========" + moneykind.getMoneykindno()+"========" +
			// Amount+"========" + Language+"========" + ReturnURL+"========" +
			// MD5key+"========" +"========5555============"+md5src);
			// System.out.println("===============666666============="+MD5info);
			if (!(md5src.equals(MD5info))) {
				responseCode = 13;
				resultMd5 = MerNo + BillNo + Currency + Amount + responseCode
						+ dateTime + MD5key;
				md5Value = md5.getMD5ofStr(resultMd5);
				message = "Payment Declined";
				return INPUT;
			}

			tradeMoney = (double) (Math.round((double) tradeMoney * 100) / 100.00);
			rmbmoney = (double) (Math.round((double) rmbmoney * 100) / 100.00);
			
			
			String ReturnURLss[] = this.ReturnURL.split("/");
			tradeAdd = ReturnURLss[2];
			
			InternationalTradeinfo trade = new InternationalTradeinfo();
			trade.setOrderNo(rorderno);
			trade.setMerchantOrderNo(merchantOrderNo);
			trade.setMerchantId(merchant.getId());
			trade.setTradeTime(tradetime);
			trade.setTradeAmount(Double.valueOf(this.Amount));
			trade.setRmbAmount(this.rmbmoney);
			trade.setMoneyType(Long.valueOf(Currency));
			trade.setTradeState("30000000000000000000");
			trade.setTradeRate(changerate.getId());
			trade.setBalanceRate(settlementrate.getId());
			trade.setTradeUrl(tradeAdd);
			trade.setReturnUrl(this.ReturnURL);
			trade.setLastDate(new Date());
			trade.setBackCount(0d);
			trade.setLastMan(dateTime);
			this.commonService.saveOrUpdate(trade);

			String expirydate = month + year;
			// 交易
			String hql1 = "from InternationalTradeinfo t where t.orderNo='"
					+ trade.getOrderNo() + "'";

			List<InternationalTradeinfo> tradl = this.commonService.list(hql1);
			// ECPSS流水号不唯一
			if (tradl == null) {
				this.responseCode = 0;
				return ERROR;
			} else if (tradl.size() != 1) {
				return ERROR;
			}
			trade = tradl.get(0);

			// 转换成标准金额
			Double ordercount = trade.getTradeAmount();

			DecimalFormat decimalFormat = new DecimalFormat(
					"##############0.00");
			String ordercountValue = decimalFormat.format(ordercount);

			tradeMoneyType = trade.getMoneyType() + "";
			merchantOrderNo = trade.getMerchantOrderNo() + "";

			// InternationalMerCreditCard
			this.ReturnURL = trade.getReturnUrl();
			// 校验是否商户号对上交易
			if (merchant == null) {
				return ERROR;
			}
			/** ******************风险控制******************** */
			// 获取信用卡前6位号码
			String backCardnum6 = (new StringBuilder(cardnum)).delete(6,
					cardnum.length()).toString();
			String backCardnum9 = (new StringBuilder(cardnum)).delete(9,
					cardnum.length()).toString();

			// 判断卡类型
			int cartype = 0;
			if (cardnum != null) {
				if (cardnum.matches("[4]\\d+")) {
					cartype = 4;
					vpc_Card = "Visa";
				}
				if (cardnum.matches("[5]\\d+")) {
					cartype = 5;
					vpc_Card = "Mastercard";
				}
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
				message = "Payment Declined";
				remark = "通道未设置";
				responseCode = 16;

				MD5info = MerNo + trade.getMerchantOrderNo()
						+ trade.getMoneyType() + ordercountValue + responseCode
						+ dateTime + MD5key;

				md5Value = md5.getMD5ofStr(MD5info);
				return this.INPUT;
			}
			// 商户通道
			InternationalMerchantChannels im = (InternationalMerchantChannels) this.commonService
					.load(InternationalMerchantChannels.class, Long
							.valueOf(merchanID));
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

				MD5info = MerNo + trade.getMerchantOrderNo()
						+ trade.getMoneyType() + ordercountValue + responseCode
						+ dateTime + MD5key;
				md5Value = md5.getMD5ofStr(MD5info);
				this.commonService
						.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
								+ remark
								+ "' where t.id='"
								+ trade.getId()
								+ "'");

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
			card.setCvv2(AES.setCarNo(cvv2));
			card.setCardNo(AES.setCarNo(cardnum));
			card.setFirstName(firstname);
			card.setLastName(lastname);
			// card.setUserName(firstname+" "+lastname);
			card.setEmail(email);
			card.setPhone(phone);
			card.setExpiryDate(AES.setCarNo(expirydate));
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
			this.commonService
					.deleteBySql("update  international_tradeinfo t  set t.tradechannel='"
							+ merchanID
							+ "' where t.id='"
							+ trade.getId()
							+ "'");

			// 黑卡,高风险卡宾

			// if (backCardValue6 > 0 || backCardValue9> 0) {
			// message = "Payment Declined";
			// remark = "黑卡bin";
			// responseCode = 3;
			//	
			// MD5info = MerNo + trade.getMerchantOrderNo() +
			// trade.getMoneyType() +
			// ordercountValue + responseCode + dateTime + MD5key;
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

						MD5info = MerNo + trade.getMerchantOrderNo()
								+ trade.getMoneyType() + ordercountValue
								+ responseCode + dateTime + MD5key;

						md5Value = md5.getMD5ofStr(MD5info);
						this.commonService
								.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
										+ remark
										+ "' where t.id='"
										+ trade.getId() + "'");
						//清除持卡人cvv,有效期
//						this.commonService
//						.deleteBySql("update  international_cardholdersinfo t  set t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
//								+ trade.getId() + "'");
						return INPUT;
					}
				}
			}

			String posNumber = "";
			String posMerchantNo = "";
			Long terminalId = 0L;
			if (it.size() > 0) {
				///面向开通多个终端号的商户
				List<InternationalTerminalManager> itmerchant = this.commonService
				.list("select tm from InternationalMerchantTerminal mt,InternationalTerminalManager tm "
						+ "where tm.id=mt.terminalId "
						+ "and tm.channelId='"
						+ im.getChannelId()
						+ "' "
						+ "and mt.isopen=1 "
						+ "and mt.merchantId="
						+ merchant.getId());
				if(itmerchant.size()>1){
					it = this.commonService.list("select tm from InternationalMerchantTerminal mt,InternationalTerminalManager tm "
							+ "where tm.id=mt.terminalId "
							+ "and tm.channelId='"
							+ im.getChannelId()
							+ "'  "
							+ "and mt.merchantId="
							+ merchant.getId()+" and tm.isuses='0'");
					
					if(it.size()==0){
						///更新
						this.commonService.deleteBySql("update international_terminalmanager t set t.isuses='0' where  t.id in (" +
								"select tm.id from International_Terminalmerchant mt,international_terminalmanager tm "
							+ "where tm.id=mt.terminalId "
							+ "and tm.channelId='"
							+ im.getChannelId()
							+ "' "
							+ "and mt.merchantId="
							+ merchant.getId()+" )");
						it = this.commonService.list("select tm from InternationalMerchantTerminal mt,InternationalTerminalManager tm "
								+ "where tm.id=mt.terminalId "
								+ "and tm.channelId='"
								+ im.getChannelId()
								+ "' "
								+ "and mt.isopen=1 "
								+ "and mt.merchantId="
								+ merchant.getId()+" and tm.isuses='0'");
					}
				}
				
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
			//验证黑邮箱
			int backEmailValue = this.tradeManager
					.intBySql("select count(1) from Internationalbacklist t where lower(t.email)='"
							+ email.trim().toLowerCase() + "' ");
			//验证黑IP
			int backIpValue = this.tradeManager
					.intBySql("select count(1) from Internationalbacklist t where t.ip='"
							+ ip + "' ");
			String chnals = chanelName.split("-")[0];
			// 输入值验证
			boolean flags=true;
			try {
				flags=checkAll();
			} catch (Exception e) {
				this.responseCode = 25;
				remark = "信息有误";
				System.out.println("返回状态码+++++++++" + responseCode);
				message = "Payment Declined";
				MD5info = MerNo + trade.getMerchantOrderNo()
						+ trade.getMoneyType() + ordercountValue + responseCode
						+ dateTime + MD5key;

				md5Value = md5.getMD5ofStr(MD5info);
				this.commonService
						.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
								+ remark
								+ "' where t.id='"
								+ trade.getId()
								+ "'");
				//清除持卡人cvv,有效期
//				this.commonService
//				.deleteBySql("update  international_cardholdersinfo t  set t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
//						+ trade.getId() + "'");
				return INPUT;
			}
			if (flags) {
				this.responseCode = 25;
				remark = "Message error";
				System.out.println("返回状态码+++++++++" + responseCode);
				message = "Payment Declined";
				MD5info = MerNo + trade.getMerchantOrderNo()
						+ trade.getMoneyType() + ordercountValue + responseCode
						+ dateTime + MD5key;

				md5Value = md5.getMD5ofStr(MD5info);
				this.commonService
						.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
								+ remark
								+ "' where t.id='"
								+ trade.getId()
								+ "'");
				//清除持卡人cvv,有效期
//				this.commonService
//				.deleteBySql("update  international_cardholdersinfo t  set t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
//						+ trade.getId() + "'");
				return INPUT;
			} else if (trade.getTradeAmount() > merchantmanager.getPenQuota()) {
				message = "Payment Declined";
				remark = "单笔超限";
				responseCode = 3;
				System.out.println("返回状态码+++++++++" + responseCode);
				MD5info = MerNo + trade.getMerchantOrderNo()
						+ trade.getMoneyType() + ordercountValue + responseCode
						+ dateTime + MD5key;

				md5Value = md5.getMD5ofStr(MD5info);
				this.commonService
						.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
								+ remark
								+ "' where t.id='"
								+ trade.getId()
								+ "'");
				//清除持卡人cvv,有效期
//				this.commonService
//				.deleteBySql("update  international_cardholdersinfo t  set t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
//						+ trade.getId() + "'");
				return INPUT;
			}
			// 黑卡库
			else if (backCardValue > 0) {
				message = "Payment Declined";
				remark = "黑卡";
				responseCode = 2;
				System.out.println("返回状态码+++++++++" + responseCode);
				MD5info = MerNo + trade.getMerchantOrderNo()
						+ trade.getMoneyType() + ordercountValue + responseCode
						+ dateTime + MD5key;
				md5Value = md5.getMD5ofStr(MD5info);
				this.commonService
						.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
								+ remark
								+ "' where t.id='"
								+ trade.getId()
								+ "'");
				//清除持卡人cvv,有效期
//				this.commonService
//				.deleteBySql("update  international_cardholdersinfo t  set t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
//						+ trade.getId() + "'");
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
				this.commonService
				.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
						+ remark
						+ "' where t.id='"
						+ trade.getId()
						+ "'");
				//清除持卡人cvv,有效期
//				this.commonService
//				.deleteBySql("update  international_cardholdersinfo t  set t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
//						+ trade.getId() + "'");
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
				this.commonService
				.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
						+ remark
						+ "' where t.id='"
						+ trade.getId()
						+ "'");
				//清除持卡人cvv,有效期
//				this.commonService
//				.deleteBySql("update  international_cardholdersinfo t  set t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
//						+ trade.getId() + "'");
				System.out.println("responseCode--------------" + responseCode);
				return INPUT;
			}
			
			// 黑卡bean
			else if (backCardValue6 > 0 || backCardValue9 > 0) {
				message = "Payment Declined";
				remark = "黑卡";
				responseCode = 17;
				System.out.println("返回状态码+++++++++" + responseCode);
				MD5info = MerNo + trade.getMerchantOrderNo()
						+ trade.getMoneyType() + ordercountValue + responseCode
						+ dateTime + MD5key;
				md5Value = md5.getMD5ofStr(MD5info);
				this.commonService
						.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
								+ remark
								+ "' where t.id='"
								+ trade.getId()
								+ "'");
				//清除持卡人cvv,有效期
//				this.commonService
//				.deleteBySql("update  international_cardholdersinfo t  set t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
//						+ trade.getId() + "'");
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
							+ "' and substr(t.tradestate,1,1) in(1,2,4,5) and t.tradetime>to_date('"
							+ yestedayDate + "','yyyy-MM-dd hh24:mi:ss')");
			if (Long.valueOf(carno) >= localCarNO) {
				message = "Payment Declined";
				remark = "重复交易";
				responseCode = 7;
				System.out.println("返回状态码+++++++++" + responseCode);
				MD5info = MerNo + trade.getMerchantOrderNo()
						+ trade.getMoneyType() + ordercountValue + responseCode
						+ dateTime + MD5key;
				md5Value = md5.getMD5ofStr(MD5info);
				this.commonService
						.deleteBySql("update  international_tradeinfo t  set t.tradestate='3'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
								+ remark
								+ "' where t.id='"
								+ trade.getId()
								+ "'");
				//清除持卡人cvv,有效期
//				this.commonService
//				.deleteBySql("update  international_cardholdersinfo t  set t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
//						+ trade.getId() + "'");
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
				this.commonService
						.deleteBySql("update  international_tradeinfo t  set t.tradestate='3'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
								+ remark
								+ "' where t.id='"
								+ trade.getId()
								+ "'");
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
							+ "' and substr(t.tradestate,1,1) in(1,2,4,5) and t.tradetime>to_date('"
							+ yestedayDate + "','yyyy-MM-dd hh24:mi:ss')");
			if (Long.valueOf(ipcount) >= localIP) {
				message = "Payment Declined";
				remark = "重复交易";
				responseCode = 5;
				System.out.println("返回状态码+++++++++" + responseCode);
				MD5info = MerNo + trade.getMerchantOrderNo()
						+ trade.getMoneyType() + ordercountValue + responseCode
						+ dateTime + MD5key;
				md5Value = md5.getMD5ofStr(MD5info);
				this.commonService
						.deleteBySql("update  international_tradeinfo t  set t.tradestate='3'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
								+ remark
								+ "' where t.id='"
								+ trade.getId()
								+ "'");
				//清除持卡人cvv,有效期
//				this.commonService
//				.deleteBySql("update  international_cardholdersinfo t  set t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
//						+ trade.getId() + "'");
				return INPUT;
			}
			// 同一邮箱
			int emailcount = 0;
			emailcount = this.tradeManager
					.intBySql("select count(1) from international_cardholdersinfo f, international_tradeinfo t where f.tradeid=t.id and f.email='"
							+ this.email
							+ "' and t.merchantid='"
							+ merchant.getId()
							+ "' and substr(t.tradestate,1,1) in(1,2,4,5) and t.tradetime>to_date('"
							+ yestedayDate + "','yyyy-MM-dd hh24:mi:ss')");

			if (Long.valueOf(emailcount) >= localEMAIL) {
				message = "Payment Declined";
				remark = "重复交易";
				responseCode = 6;
				System.out.println("返回状态码+++++++++" + responseCode);
				MD5info = MerNo + trade.getMerchantOrderNo()
						+ trade.getMoneyType() + ordercountValue + responseCode
						+ dateTime + MD5key;
				md5Value = md5.getMD5ofStr(MD5info);
				this.commonService
						.deleteBySql("update  international_tradeinfo t  set t.tradestate='3'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
								+ remark
								+ "' where t.id='"
								+ trade.getId()
								+ "'");
				//清除持卡人cvv,有效期
//				this.commonService
//				.deleteBySql("update  international_cardholdersinfo t  set t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
//						+ trade.getId() + "'");
				return INPUT;
			}
			// 同一电话
			int telcout = 0;
			telcout = this.tradeManager
					.intBySql("select count(1) from international_cardholdersinfo f, international_tradeinfo t where f.tradeid=t.id and f.phone='"
							+ this.phone
							+ "' and t.merchantid='"
							+ merchant.getId()
							+ "' and substr(t.tradestate,1,1) in(1,2,4,5) and t.tradetime>to_date('"
							+ yestedayDate + "','yyyy-MM-dd hh24:mi:ss')");
			if (Long.valueOf(telcout) >= localPhone) {
				message = "Payment Declined";
				remark = "重复交易";
				responseCode = 30;
				System.out.println("返回状态码+++++++++" + responseCode);
				MD5info = MerNo + trade.getMerchantOrderNo()
						+ trade.getMoneyType() + ordercountValue + responseCode
						+ dateTime + MD5key;
				md5Value = md5.getMD5ofStr(MD5info);
				this.commonService
						.deleteBySql("update  international_tradeinfo t  set t.tradestate='3'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
								+ remark
								+ "' where t.id='"
								+ trade.getId()
								+ "'");
				//清除持卡人cvv,有效期
//				this.commonService
//				.deleteBySql("update  international_cardholdersinfo t  set t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
//						+ trade.getId() + "'");
				return INPUT;
			}
			// 同一cookies
			int cocketcount = 0;
			cocketcount = this.tradeManager
					.intBySql("select count(1) from international_cardholdersinfo f, international_tradeinfo t where f.tradeid=t.id and f.cookie='"
							+ this.cookie
							+ "' and t.merchantid='"
							+ merchant.getId()
							+ "' and substr(t.tradestate,1,1) in(1,2,4,5) and t.tradetime>to_date('"
							+ yestedayDate + "','yyyy-MM-dd hh24:mi:ss')");

			if (Long.valueOf(cocketcount) >= localCOCKET) {
				message = "Payment Declined";
				remark = "重复交易";
				responseCode = 8;
				System.out.println("返回状态码+++++++++" + responseCode);
				MD5info = MerNo + trade.getMerchantOrderNo()
						+ trade.getMoneyType() + ordercountValue + responseCode
						+ dateTime + MD5key;
				md5Value = md5.getMD5ofStr(MD5info);
				this.commonService
						.deleteBySql("update  international_tradeinfo t  set t.tradestate='3'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
								+ remark
								+ "' where t.id='"
								+ trade.getId()
								+ "'");
				//清除持卡人cvv,有效期
//				this.commonService
//				.deleteBySql("update  international_cardholdersinfo t  set t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
//						+ trade.getId() + "'");
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
			// if (merchant.getMonthTradeMoney() + ordercount > merchantmanager
			// .getMonthQuota()) {
			// // //先把需要发送邮件的信息保存到数据库
			// // saveMailInfo(merEmail,num1,"ecpss@ecpss.com");
			// message = "Payment Declined";
			// remark = "月交易量超限";
			// responseCode = 4;
			// System.out.println("返回状态码+++++++++" + responseCode);
			// MD5info = MerNo + trade.getMerchantOrderNo() +
			// trade.getMoneyType()
			// + ordercountValue + responseCode + dateTime + MD5key;
			// md5Value = md5.getMD5ofStr(MD5info);
			// if (chnals.equals("VIP") || chnals.equals("EVIP")) {
			// this.commonService
			// .deleteBySql("update international_tradeinfo t set
			// t.tradestate='4'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
			// + remark
			// + "' where t.id='"
			// + trade.getId()
			// + "'");
			// } else {
			// this.commonService
			// .deleteBySql("update international_tradeinfo t set
			// t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
			// + remark
			// + "' where t.id='"
			// + trade.getId()
			// + "'");
			//
			// }
			// // 超过限额给商户发送邮件提醒
			// shopManagerService.addSendMessages(merchant.getMeremail(),
			// "ecpss@ecpss.cc", merchant.getMerno() + " "
			// + EmailInfo.getMoreMoney(), "0");
			// return INPUT;
			//
			// }

			// 高风险卡 VIP为待确认 ，其他的为失败
			if (riskCardValue > 0) {
				message = "Your Payment is being Processed! ";
				remark = "高风险卡";
				responseCode = 1;
				System.out.println("返回状态码+++++++++" + responseCode);
				MD5info = MerNo + trade.getMerchantOrderNo()
						+ trade.getMoneyType() + ordercountValue + responseCode
						+ dateTime + MD5key;
				md5Value = md5.getMD5ofStr(MD5info);
				if (chnals.equals("VIP")) {
					this.commonService
							.deleteBySql("update  international_tradeinfo t  set t.tradestate='4'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
									+ remark
									+ "' where t.id='"
									+ trade.getId()
									+ "'");
				} else {
					this.commonService
							.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
									+ remark
									+ "' where t.id='"
									+ trade.getId()
									+ "'");

				}
				return INPUT;

			}
			// 当商户交易的金额达到商户交易条件设置金额的85%则以邮件的方式通知商户
			// if (merchant.getMonthTradeMoney() + ordercount >
			// merchantmanager.getMonthQuota() * 0.85) {
			// // 发送邮件
			// shopManagerService.addSendMessages(merchant.getMeremail(),
			// "ecpss@ecpss.cc", merchant.getMerno()
			// + " "
			// + EmailInfo.getMoreMoneyPart(merchant.getMonthTradeMoney()
			// + ordercount), "0");
			// // this.saveMailInfo(merEmail, num2, "ecpss@ecpss.com");
			// }
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
				hm.put("bin", cardnum);
				hm.put("binName", cardbank);

				// standard 低级
				// premium 高级
				// 正式运行的时候要用这个 premium ; standard为调试用的标准
				hm.put("requested_type", "premium");

				//Hashtable ht = getmmValue(hm);
//				maxmindValue = (String) ht.get("values");
//				bankName = (String) ht.get("bankName");
//				bankCountry = (String) ht.get("bankCountry");
//				bankPhone = (String) ht.get("bankPhone");
				System.out.println("maxmindValue--------------" + maxmindValue);
				// System.out.println("riskValue--------------"+riskValue);

			} catch (Exception ex) {
				try {
					CCSendMail.setSendMail("89610614@qq.com", "stos 2.0 maxmind error",
					"ecpss@ecpss.cc");
				} catch (Exception e) {
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
				MD5info = MerNo + trade.getMerchantOrderNo()
						+ trade.getMoneyType() + ordercountValue + responseCode
						+ dateTime + MD5key;
				md5Value = md5.getMD5ofStr(MD5info);
				if (chnals.equals("VIP") || chnals.equals("EVIP")) {
					this.commonService
							.deleteBySql("update  international_tradeinfo t  set t.tradestate='4'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
									+ remark
									+ "' where t.id='"
									+ trade.getId()
									+ "'");
					// -------更新商户月交易限额-----------------
					merchant.setMonthTradeMoney(merchant.getMonthTradeMoney()
							+ trade.getTradeAmount());
					this.commonService.update(merchant);
				} else {
					this.commonService
							.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
									+ remark
									+ "' where t.id='"
									+ trade.getId()
									+ "'");
					//清除持卡人cvv,有效期
//					this.commonService
//					.deleteBySql("update  international_cardholdersinfo t  set t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
//							+ trade.getId() + "'");
				}
				return INPUT;
			}else if (maxmind > im.getMaxmind_lv2()) {
				message = "Payment Declined";
				remark = "高风险！";
				responseCode = 0;
				System.out.println("返回状态码+++++++++" + responseCode);
				MD5info = MerNo + trade.getMerchantOrderNo()
						+ trade.getMoneyType() + ordercountValue + responseCode
						+ dateTime + MD5key;
				md5Value = md5.getMD5ofStr(MD5info);
				this.commonService
						.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
								+ remark
								+ "' where t.id='"
								+ trade.getId()
								+ "'");
				//清除持卡人cvv,有效期
//				this.commonService
//				.deleteBySql("update  international_cardholdersinfo t  set t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
//						+ trade.getId() + "'");

				return INPUT;
			}

			if (chnals.equals("EVIP") || chnals.equals("CN3D")) {
				// 交易查询
				DCCMessage dcc = new DCCMessage();
				dcc.setMessageType("0800");// 类型
				dcc.setPrimary_Account_Number(AES.getCarNo(card.getCardNo()));// 账号2
				dcc.setProcessing_Code("970000");// 处理代码3
				dcc.setAmount_Transaction_Local(this.buzero(trade
						.getRmbAmount()
						+ ""));// 4 本地交易金额
				dcc.setSYSTEMS_TRACE_AUDIT_NUMBER(trade.getOrderNo().substring(
						trade.getOrderNo().length() - 6,
						trade.getOrderNo().length()));// 11 交易流水号
				dcc.setDATE_EXPIRATION(AES.getCarNo(card.getExpiryDate()).substring(2, 4)
						+ AES.getCarNo(card.getExpiryDate()).substring(0, 2));// 14 有效期
				dcc.setPOINT_OF_SERVICE_ENTRY_CODE("0012");// 22 POS进入模式
				dcc.setNETWORK_INTL_IDENTIFIER("0098");// 24 收单商户号
				dcc.setPOS_CONDITION_CODE("00");// 25 商户编码
				dcc.setCARD_ACCEPTOR_TERMINAL_ID(posNumber);// 41 商户终端号
				dcc.setCARD_ACCEPTOR_ID_CODE(posMerchantNo);// 42 商户编号
				dcc.setInvoice_Number(trade.getOrderNo().substring(
						trade.getOrderNo().length() - 6,
						trade.getOrderNo().length()));// 62
				DccUtil dc = new DccUtil();
				dc.setDccMessage(dcc);

				try {
					dcc = dc.getDccMessage();
					if (AES.getCarNo(card.getCardNo()).startsWith("5")) {
						dcc.setRESPONSE_CODE("YX");
					}
				} catch (Exception ex) {
					// 传递的参数
					this.responseCode = 19;
					message = "Your payment is being processed";
					MD5info = MerNo + trade.getMerchantOrderNo()
							+ trade.getMoneyType() + ordercountValue
							+ responseCode + dateTime + MD5key;
					md5Value = md5.getMD5ofStr(MD5info);
					if(merchant.getStatutes().subSequence(6,7).equals("0")){
						this.commonService
						.deleteBySql("update  international_tradeinfo t  set t.tradestate='2'||substr(t.tradestate,2,(length(t.tradestate)-1)) where t.id='"
								+ trade.getId() + "'");
						// -------更新商户月交易限额-----------------
						merchant.setMonthTradeMoney(merchant.getMonthTradeMoney()
								+ trade.getTradeAmount());
						this.commonService.update(merchant);
					}else{
						this.responseCode = 0;
						this.commonService
						.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)) where t.id='"
								+ trade.getId() + "'");
					}
					
					return INPUT;
				}
				System.out.println("DCC走++++++++++  " + dcc.getRESPONSE_CODE());
				if (StringUtils.isBlank(dcc.getRESPONSE_CODE())) {
					// 传递的参数
					this.responseCode = 19;
					message = "Your payment is being processed";
					MD5info = MerNo + trade.getMerchantOrderNo()
							+ trade.getMoneyType() + ordercountValue
							+ responseCode + dateTime + MD5key;
					md5Value = md5.getMD5ofStr(MD5info);
					if(merchant.getStatutes().subSequence(6,7).equals("0")){
						this.commonService
						.deleteBySql("update  international_tradeinfo t  set t.tradestate='2'||substr(t.tradestate,2,(length(t.tradestate)-1)) where t.id='"
								+ trade.getId() + "'");
						// -------更新商户月交易限额-----------------
						merchant.setMonthTradeMoney(merchant.getMonthTradeMoney()
								+ trade.getTradeAmount());
						this.commonService.update(merchant);
					}else{
						this.responseCode = 0;
						this.commonService
						.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)) where t.id='"
								+ trade.getId() + "'");
					}
					return INPUT;
				}
				// 做DCC交易
				if (dcc.getRESPONSE_CODE().equals("YY")) {
					// 交易
					if(StringUtils.isNotBlank(dcc.getAmount_Transaction_Foreign()) && StringUtils.isNotBlank(dcc.getCurrency_Code_Foreign())){
						this.commonService
						.deleteBySql("update  international_tradeinfo t  set t.DCCTradeCurrency='"+dcc.getCurrency_Code_Foreign()+"' " +
								",t.DCCTradeAmount=" +Double.valueOf(dcc.getAmount_Transaction_Foreign())+
								" where t.id='"
								+ trade.getId() + "'");
					}
					DCCMessage msg2 = new DCCMessage();
					msg2.setMessageType("0200");// 类型
					msg2.setPrimary_Account_Number(AES.getCarNo(card.getCardNo()));// 账号2
					msg2.setProcessing_Code("000000");// 处理代码3
					msg2.setAmount_Transaction_Local(this.buzero(trade
							.getRmbAmount()
							+ ""));// 4 本地交易金额
					msg2.setAmount_Transaction_Foreign(dcc
							.getAmount_Transaction_Foreign());// 5
					// 0810
					msg2.setConversion_Rate(dcc.getConversion_Rate());// 9
					// 0810
					msg2.setSYSTEMS_TRACE_AUDIT_NUMBER(trade.getOrderNo()
							.substring(trade.getOrderNo().length() - 6,
									trade.getOrderNo().length()));// 11 交易流水号
					msg2.setDATE_EXPIRATION(AES.getCarNo(card.getExpiryDate())
							.substring(2, 4)
							+ AES.getCarNo(card.getExpiryDate()).substring(0, 2));// 14 有效期
					msg2.setPOINT_OF_SERVICE_ENTRY_CODE("0012");// 22 POS进入模式
					msg2.setNETWORK_INTL_IDENTIFIER("0017");// 24 收单商户号
					msg2.setPOS_CONDITION_CODE("00");// 25 商户编码
					// msg2.setRETRIEVAL_REFERENCE_NUMBER("");//37
					msg2.setCARD_ACCEPTOR_TERMINAL_ID(posNumber);// 41
					// 商户终端号
					msg2.setCARD_ACCEPTOR_ID_CODE(posMerchantNo);// 42 商户编号
					msg2.setCurrency_Code_Foreign(dcc
							.getCurrency_Code_Foreign());// 49
					// 货币代码-----0810
					msg2.setCVV2_OR_CVC2(AES.getCarNo(card.getCvv2()));// cv2
					msg2.setInvoice_Number(trade.getOrderNo().substring(
							trade.getOrderNo().length() - 6,
							trade.getOrderNo().length()));// 62
					DccUtil dc2 = new DccUtil();
					dc2.setDccMessage(msg2);
					try {
						msg2 = dc2.getDccMessage();
					} catch (Exception ex) {

						this.responseCode = 19;
						message = "Your payment is being processed";
						MD5info = trade.getMerchantOrderNo()
								+ trade.getMoneyType() + ordercountValue
								+ responseCode + dateTime + MD5key;
						md5Value = md5.getMD5ofStr(MD5info);
						if(merchant.getStatutes().subSequence(6,7).equals("0")){
							this.commonService
							.deleteBySql("update  international_tradeinfo t  set t.tradestate='2'||substr(t.tradestate,2,(length(t.tradestate)-1)) where t.id='"
									+ trade.getId() + "'");
							// -------更新商户月交易限额-----------------
							merchant.setMonthTradeMoney(merchant.getMonthTradeMoney()
									+ trade.getTradeAmount());
							this.commonService.update(merchant);
						}else{
							this.responseCode = 0;
							this.commonService
							.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)) where t.id='"
									+ trade.getId() + "'");
						}
						
						// 交易冲正
						DCCMessage msg6 = new DCCMessage();
						msg6.setMessageType("0400");// 类型
						msg6.setPrimary_Account_Number(AES.getCarNo(card.getCardNo()));// 账号2
						msg6.setProcessing_Code("000000");// 处理代码3
						msg6.setAmount_Transaction_Local(this.buzero(trade
								.getRmbAmount()
								+ ""));// 4 本地交易金额
						msg6.setAmount_Transaction_Foreign(dcc
								.getAmount_Transaction_Foreign());// 5 0810
						msg6.setConversion_Rate(dcc.getConversion_Rate());// 9
						// 0810
						msg6.setSYSTEMS_TRACE_AUDIT_NUMBER(trade.getOrderNo()
								.substring(trade.getOrderNo().length() - 6,
										trade.getOrderNo().length()));// 11
						// 交易流水号
						msg6.setDATE_EXPIRATION(AES.getCarNo(card.getExpiryDate()).substring(
								2, 4)
								+ AES.getCarNo(card.getExpiryDate()).substring(0, 2));// 14
						// 有效期
						msg6.setPOINT_OF_SERVICE_ENTRY_CODE("0012");// 22
						// POS进入模式
						msg6.setNETWORK_INTL_IDENTIFIER("0017");// 24 收单商户号
						msg6.setPOS_CONDITION_CODE("00");// 25 商户编码
						msg6.setRETRIEVAL_REFERENCE_NUMBER(msg2
								.getRETRIEVAL_REFERENCE_NUMBER());// 37
						msg6.setCARD_ACCEPTOR_TERMINAL_ID(trade
								.getVIPTerminalNo());// 41 商户终端号
						msg6.setCARD_ACCEPTOR_ID_CODE(posMerchantNo);// 42
						// 商户编号
						msg6.setCurrency_Code_Foreign(dcc
								.getCurrency_Code_Foreign());// 49
						// 货币代码-----0810
						msg6.setInvoice_Number(trade.getOrderNo().substring(
								trade.getOrderNo().length() - 6,
								trade.getOrderNo().length()));// 62
						DccUtil dc6 = new DccUtil();
						dc6.setDccMessage(msg6);

						msg6 = dc6.getDccMessage();
						System.out
								.println("===============================yy交易冲正(撤销冲正):"
										+ msg6.getRESPONSE_CODE());

						return INPUT;
					}

					if (msg2.getRESPONSE_CODE().equals("00")) {
						if (chnals.equals("CN3D")) {
							this.message = CN3DPayResult.getName(msg2
									.getRESPONSE_CODE());
						} else {
							this.message = "Payment Success!";
						}
						this.responseCode = 88;
						this.commonService
								.deleteBySql("update  international_tradeinfo t  set t.tradestate='1'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
										+ message
										+ "' ,t.VIPAuthorizationNo='"
										+ msg2
												.getAUTH_IDENTIFICATION_RESPONSE()
										+ "' ,t.VIPBatchNo='XXXXXX' where t.id='"
										+ trade.getId() + "'");
						authorizationNo = msg2
								.getAUTH_IDENTIFICATION_RESPONSE();
						batchno = "XXXXXX";
						//清除持卡人cvv,有效期
//						this.commonService
//						.deleteBySql("update  international_cardholdersinfo t  set t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
//								+ trade.getId() + "'");
						// -------更新商户月交易限额-----------------
						merchant.setMonthTradeMoney(merchant
								.getMonthTradeMoney()
								+ trade.getTradeAmount());
						this.commonService.update(merchant);
						MD5info = MerNo + trade.getMerchantOrderNo()
								+ trade.getMoneyType() + ordercountValue
								+ responseCode + dateTime + MD5key;
						md5Value = md5.getMD5ofStr(MD5info);

						// /---------------给持卡人发送邮件-----------------------////
						List<InternationalTerminalManager> tmm = this.commonService
								.list("select tm from InternationalTerminalManager tm where tm.terminalNo='"
										+ posNumber.trim() + "' ");
						String billaddressby = null;
						if (tmm.size() > 0) {
							InternationalTerminalManager tm = tmm.get(0);
							billaddressby = tm.getBillingAddress();
						}
						String mailinfo=null;
						try {
							EmailInfo emailinfo = new EmailInfo();
							mailinfo = emailinfo.getPaymentResultEmail(card
									.getEmail(), trade.getTradeAmount(),
									getStates().getCurrencyTypeByNo(
											trade.getMoneyType().intValue()), trade
											.getTradeUrl(), trade.getTradeTime(),
									billaddressby, trade.getMerchantOrderNo(),
									trade.getOrderNo(),merchant);
							// 发送邮件,如果发送失败插入数据库发送
							if(merchant.getStatutes().substring(4, 5).equals("0")){
								CCSendMail.setSendMail(card.getEmail(), mailinfo,
								"ecpss@ecpss.cc");
								System.out.println("邮件立马发出");
							}
						} catch (Exception e) {
							// 往数据库插入等待发送邮件
							shopManagerService.addSendMessages(card.getEmail(),
									"ecpss@ecpss.cc", mailinfo, "0");
							System.out.println("邮件等待稍后发出");
							return INPUT;
						}

						return INPUT;
					} else if (msg2.getRESPONSE_CODE().equals("03")
							|| msg2.getRESPONSE_CODE().equals("12")
							|| msg2.getRESPONSE_CODE().equals("13")
							|| msg2.getRESPONSE_CODE().equals("30")
							|| msg2.getRESPONSE_CODE().equals("63")
							|| msg2.getRESPONSE_CODE().equals("68")
							|| msg2.getRESPONSE_CODE().equals("76")
							|| msg2.getRESPONSE_CODE().equals("77")
							|| msg2.getRESPONSE_CODE().equals("89")
							|| msg2.getRESPONSE_CODE().equals("94")
							|| msg2.getRESPONSE_CODE().equals("95")
							|| msg2.getRESPONSE_CODE().equals("96")
							|| msg2.getRESPONSE_CODE().equals("98")
							|| msg2.getRESPONSE_CODE().equals("99")) {
						// 传递的参数
						this.responseCode = 19;
						message = "Your payment is being processed";
						MD5info = MerNo + trade.getMerchantOrderNo()
								+ trade.getMoneyType() + ordercountValue
								+ responseCode + dateTime + MD5key;
						md5Value = md5.getMD5ofStr(MD5info);
						if(merchant.getStatutes().subSequence(6,7).equals("0")){
							this.commonService
							.deleteBySql("update  international_tradeinfo t  set t.tradestate='2'||substr(t.tradestate,2,(length(t.tradestate)-1)) where t.id='"
									+ trade.getId() + "'");
							
						}else{
							this.responseCode = 0;
							this.commonService
							.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)) where t.id='"
									+ trade.getId() + "'");
						}
						
						return INPUT;
					} else {
						// 如果是CN3D通道,并且返回值不为空就返回给商户结果,并且显示详细失败原因
						if (chnals.equals("CN3D")
								&& StringUtils.isNotBlank(CN3DPayResult
										.getName(msg2.getRESPONSE_CODE()))) {
							this.message = CN3DPayResult.getName(msg2
									.getRESPONSE_CODE());
						}
						// 如果是CN3D通道,并且返回值为空就不返回结果到商户网站
						else if (chnals.equals("CN3D")
								&& StringUtils.isBlank(CN3DPayResult
										.getName(msg2.getRESPONSE_CODE()))) {
							this.message = "";
							this.responseCode = 0;
							this.commonService
									.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
											+ message
											+ "' where t.id='"
											+ trade.getId() + "'");
							
							//清除持卡人cvv,有效期
//							this.commonService
//							.deleteBySql("update  international_cardholdersinfo t  set t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
//									+ trade.getId() + "'");
							MD5info = MerNo + trade.getMerchantOrderNo()
									+ trade.getMoneyType() + ordercountValue
									+ responseCode + dateTime + MD5key;
							md5Value = md5.getMD5ofStr(MD5info);
							return INPUT;
						} else {
							this.message = "Payment Declined!"
									+ msg2.getRESPONSE_CODE();
						}

						this.responseCode = 0;
						this.commonService
								.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
										+ message
										+ "' where t.id='"
										+ trade.getId() + "'");
						this.message = EvipPayResult.getName(msg2.getRESPONSE_CODE());
						//清除持卡人cvv,有效期
//						this.commonService
//						.deleteBySql("update  international_cardholdersinfo t  set t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
//								+ trade.getId() + "'");
						MD5info = MerNo + trade.getMerchantOrderNo()
								+ trade.getMoneyType() + ordercountValue
								+ responseCode + dateTime + MD5key;
						md5Value = md5.getMD5ofStr(MD5info);
						return INPUT;
					}

				} else if (dcc.getRESPONSE_CODE().equals("YX")) {
					InternationalTerminalManager tm = (InternationalTerminalManager) this.commonService
							.load(InternationalTerminalManager.class,
									terminalId);
					String ter = "select tm from InternationalTerminalManager tm where tm.terminalNo='"
							+ tm.getAndterminalNo().trim() + "' ";
					List<InternationalTerminalManager> list = this.commonService
							.list(ter);
					System.out.println("终端号：+++++++++++++++"
							+ list.get(0).getTerminalNo());
					DCCMessage dcc3 = new DCCMessage();
					dcc3.setMessageType("0200");// 类型
					dcc3.setPrimary_Account_Number(AES.getCarNo(card.getCardNo()));// 账号2
					dcc3.setProcessing_Code("000000");// 处理代码3
					dcc3.setAmount_Transaction_Local(this.buzero(trade
							.getRmbAmount()
							+ ""));// 4 本地交易金额
					dcc3.setSYSTEMS_TRACE_AUDIT_NUMBER(trade.getOrderNo()
							.substring(trade.getOrderNo().length() - 6,
									trade.getOrderNo().length()));// 11 交易流水号
					dcc3.setDATE_EXPIRATION(AES.getCarNo(card.getExpiryDate())
							.substring(2, 4)
							+ AES.getCarNo(card.getExpiryDate()).substring(0, 2));// 14 有效期
					dcc3.setPOINT_OF_SERVICE_ENTRY_CODE("0012");// 22 POS进入模式
					dcc3.setNETWORK_INTL_IDENTIFIER("0017");// 24 收单商户号
					dcc3.setCARD_ACCEPTOR_TERMINAL_ID(list.get(0)
							.getTerminalNo());// 41 商户终端号
					dcc3.setCARD_ACCEPTOR_ID_CODE(list.get(0).getMerchantNo());// 42
					// 商户编号
					dcc3.setCVV2_OR_CVC2(AES.getCarNo(card.getCvv2()));// cv2 61
					dcc3.setInvoice_Number(trade.getOrderNo().substring(
							trade.getOrderNo().length() - 6,
							trade.getOrderNo().length()));// 62
					DccUtil dc3 = new DccUtil();
					dc3.setDccMessage(dcc3);
					try {
						dcc3 = dc3.getDccMessage();
					} catch (Exception ex) {

						this.responseCode = 19;
						message = "Your payment is being processed";
						MD5info = MerNo + trade.getMerchantOrderNo()
								+ trade.getMoneyType() + ordercountValue
								+ responseCode + dateTime + MD5key;
						md5Value = md5.getMD5ofStr(MD5info);
						if(merchant.getStatutes().subSequence(6,7).equals("0")){
							this.commonService
							.deleteBySql("update  international_tradeinfo t  set t.tradestate='2'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.VIPTerminalNo='"
									+ list.get(0).getTerminalNo()
									+ "' where t.id='"
									+ trade.getId()
									+ "'");
						}else{
							this.responseCode = 0;
							this.commonService
							.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.VIPTerminalNo='"
									+ list.get(0).getTerminalNo()
									+ "' where t.id='"
									+ trade.getId()
									+ "'");
						}
						
						// -------更新商户月交易限额-----------------
						merchant.setMonthTradeMoney(merchant
								.getMonthTradeMoney()
								+ trade.getTradeAmount());
						this.commonService.update(merchant);
						DCCMessage msg7 = new DCCMessage();
						msg7.setMessageType("0400");// 类型
						msg7.setPrimary_Account_Number(AES.getCarNo(card.getCardNo()));// 账号2
						msg7.setProcessing_Code("000000");// 处理代码3
						msg7.setAmount_Transaction_Local(this.buzero(trade
								.getRmbAmount()
								+ ""));// 4 本地交易金额
						// msg7.setAmount_Transaction_Foreign(dcc.getAmount_Transaction_Foreign());//5
						// 0810
						// msg7.setConversion_Rate(dcc.getConversion_Rate());//9
						// 0810
						msg7.setSYSTEMS_TRACE_AUDIT_NUMBER(trade.getOrderNo()
								.substring(trade.getOrderNo().length() - 6,
										trade.getOrderNo().length()));// 11
						// 交易流水号
						msg7.setDATE_EXPIRATION(AES.getCarNo(card.getExpiryDate()).substring(
								2, 4)
								+ AES.getCarNo(card.getExpiryDate()).substring(0, 2));// 14
						// 有效期
						msg7.setPOINT_OF_SERVICE_ENTRY_CODE("0012");// 22
						// POS进入模式
						msg7.setNETWORK_INTL_IDENTIFIER("0017");// 24 收单商户号
						msg7.setPOS_CONDITION_CODE("00");// 25 商户编码
						msg7.setRETRIEVAL_REFERENCE_NUMBER(dcc3
								.getRETRIEVAL_REFERENCE_NUMBER());// 37
						msg7.setCARD_ACCEPTOR_TERMINAL_ID(list.get(0)
								.getTerminalNo());// 41 商户终端号
						msg7.setCARD_ACCEPTOR_ID_CODE(list.get(0)
								.getMerchantNo());// 42 商户编号
						// msg7.setCurrency_Code_Foreign(dcc.getCurrency_Code_Foreign());//49
						// 货币代码-----0810
						// msg7.setCVV2_OR_CVC2(cvv2);//cv2
						msg7.setInvoice_Number(trade.getOrderNo().substring(
								trade.getOrderNo().length() - 6,
								trade.getOrderNo().length()));// 62
						DccUtil dc7 = new DccUtil();
						dc7.setDccMessage(msg7);

						msg7 = dc7.getDccMessage();
						System.out.println("=====================yx交易冲正:"
								+ msg7.getRESPONSE_CODE());
						CCSendMail.setSendMail("89610614@qq.com", "dcc连接不通走VIP"
								+ trade.getId(), "ecpss@ecpss.cc");
						return INPUT;
					}
					if (dcc3.getRESPONSE_CODE().equals("00")) {
						if (chnals.equals("CN3D")) {
							this.message = CN3DPayResult.getName(dcc3
									.getRESPONSE_CODE());
						} else {
							this.message = "Payment Success!";
						}
						this.responseCode = 88;
						this.commonService
								.deleteBySql("update  international_tradeinfo t  set t.tradestate='1'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
										+ message
										+ "',t.VIPTerminalNo='"
										+ list.get(0).getTerminalNo()
										+ "',t.VIPAuthorizationNo='"
										+ dcc3
												.getAUTH_IDENTIFICATION_RESPONSE()
										+ "' ,t.VIPBatchNo='XXXXXX' where t.id='"
										+ trade.getId() + "'");
						//清除持卡人cvv,有效期
//						this.commonService
//						.deleteBySql("update  international_cardholdersinfo t  set t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
//								+ trade.getId() + "'");
						authorizationNo = dcc3
								.getAUTH_IDENTIFICATION_RESPONSE();
						batchno = "XXXXXX";
						MD5info = trade.getMerchantOrderNo()
								+ trade.getMoneyType() + ordercountValue
								+ responseCode + dateTime + MD5key;
						md5Value = md5.getMD5ofStr(MD5info);
						// -------更新商户月交易限额-----------------
						merchant.setMonthTradeMoney(merchant
								.getMonthTradeMoney()
								+ trade.getTradeAmount());
						this.commonService.update(merchant);
						String mailinfo=null;
						try {
							// /---------------给持卡人发送邮件-----------------------////
							EmailInfo emailinfo = new EmailInfo();
							mailinfo = emailinfo.getPaymentResultEmail(card
									.getEmail(), trade.getTradeAmount(),
									getStates().getCurrencyTypeByNo(
											trade.getMoneyType().intValue()), trade
											.getTradeUrl(), trade.getTradeTime(),
									list.get(0).getBillingAddress(), trade
											.getMerchantOrderNo(), trade
											.getOrderNo(),merchant);
							// 发送邮件,如果发送失败插入数据库发送
							if(merchant.getStatutes().substring(4, 5).equals("0")){
								CCSendMail.setSendMail(card.getEmail(), mailinfo,
								"ecpss@ecpss.cc");
								System.out.println("邮件立马发出");
							}
						} catch (Exception e) {
							// 往数据库插入等待发送邮件
							shopManagerService.addSendMessages(card.getEmail(),
									"ecpss@ecpss.cc", mailinfo, "0");
							System.out.println("邮件等待稍后发出");
							return INPUT;
						}
						return INPUT;
					} else if (dcc3.getRESPONSE_CODE().equals("03")
							|| dcc3.getRESPONSE_CODE().equals("12")
							|| dcc3.getRESPONSE_CODE().equals("13")
							|| dcc3.getRESPONSE_CODE().equals("30")
							|| dcc3.getRESPONSE_CODE().equals("63")
							|| dcc3.getRESPONSE_CODE().equals("68")
							|| dcc3.getRESPONSE_CODE().equals("76")
							|| dcc3.getRESPONSE_CODE().equals("77")
							|| dcc3.getRESPONSE_CODE().equals("89")
							|| dcc3.getRESPONSE_CODE().equals("94")
							|| dcc3.getRESPONSE_CODE().equals("95")
							|| dcc3.getRESPONSE_CODE().equals("96")
							|| dcc3.getRESPONSE_CODE().equals("98")
							|| dcc3.getRESPONSE_CODE().equals("99")) {
						// 传递的参数
						this.responseCode = 19;
						message = "Your payment is being processed";
						MD5info = trade.getMerchantOrderNo()
								+ trade.getMoneyType() + ordercountValue
								+ responseCode + dateTime + MD5key;
						md5Value = md5.getMD5ofStr(MD5info);
						if(merchant.getStatutes().subSequence(6,7).equals("0")){
							this.commonService
							.deleteBySql("update  international_tradeinfo t  set t.tradestate='2'||substr(t.tradestate,2,(length(t.tradestate)-1)) where t.id='"
									+ trade.getId() + "'");
							
						}else{
							this.responseCode = 0;
							this.commonService
							.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)) where t.id='"
									+ trade.getId() + "'");
						}
						return INPUT;
					} else {
						// 如果是CN3D通道,并且返回值不为空就返回给商户结果,并且显示详细失败原因
						if (chnals.equals("CN3D")
								&& StringUtils.isNotBlank(CN3DPayResult
										.getName(dcc3.getRESPONSE_CODE()))) {
							this.message = CN3DPayResult.getName(dcc3
									.getRESPONSE_CODE());
						}// 如果是CN3D通道,并且返回值为空就不返回结果到商户网站
						else if (chnals.equals("CN3D")
								&& StringUtils.isBlank(CN3DPayResult
										.getName(dcc3.getRESPONSE_CODE()))) {
							this.message = "";
							this.responseCode = 0;
							this.commonService
									.deleteBySql("update  international_tradeinfo t  set t.tradestate='3'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
											+ message
											+ "',t.VIPTerminalNo='"
											+ list.get(0).getTerminalNo()
											+ "' where t.id='"
											+ trade.getId()
											+ "'");
							MD5info = MerNo + trade.getMerchantOrderNo()
									+ trade.getMoneyType() + ordercountValue
									+ responseCode + dateTime + MD5key;
							md5Value = md5.getMD5ofStr(MD5info);
							//清除持卡人cvv,有效期
//							this.commonService
//							.deleteBySql("update  international_cardholdersinfo t  set t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
//									+ trade.getId() + "'");
							return INPUT;
						} else {
							this.message = "Payment Declined!"
									+ dcc3.getRESPONSE_CODE();
						}
						this.responseCode = 0;
						this.commonService
								.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
										+ message
										+ "',t.VIPTerminalNo='"
										+ list.get(0).getTerminalNo()
										+ "' where t.id='"
										+ trade.getId()
										+ "'");
						//清除持卡人cvv,有效期
//						this.commonService
//						.deleteBySql("update  international_cardholdersinfo t  set t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
//								+ trade.getId() + "'");
						MD5info = MerNo + trade.getMerchantOrderNo()
								+ trade.getMoneyType() + ordercountValue
								+ responseCode + dateTime + MD5key;
						md5Value = md5.getMD5ofStr(MD5info);
						return INPUT;
					}

				}
				return INPUT;
			} else if (chnals.equals("TC")) {

				SimpleDateFormat dateformate = new SimpleDateFormat("yyyyMMdd");
				SimpleDateFormat timeformate = new SimpleDateFormat("HHmmss");
				// 交易
				XMLGetMessage xgm = new XMLGetMessage();
				xgm.setTxn_id("0200"); // 做交易
				xgm.setPan(cardnum);
				xgm.setCvv(cvv2);
				xgm.setExp_date(year + month);
				xgm.setSystrace(trade.getOrderNo().substring(
						trade.getOrderNo().length() - 6));
				xgm.setTxn_amt(trade.getRmbAmount() + "");
				xgm.setTxn_date(dateformate.format(trade.getTradeTime()));
				xgm.setTxn_time(timeformate.format(trade.getTradeTime()));
				xgm.setPosid(posNumber);
				ClientBoc cb = new ClientBoc();
				cb.setXmlGetMessage(xgm);
				try {
					xgm = cb.getMessage("0200");
				} catch (Exception ex) {
					// 传递的参数
					this.responseCode = 19;
					message = "Your payment is being processed";
					MD5info = MerNo + trade.getMerchantOrderNo()
					+ trade.getMoneyType() + ordercountValue
					+ responseCode + dateTime + MD5key;
					md5Value = md5.getMD5ofStr(MD5info);
					if (merchant.getStatutes().subSequence(6, 7).equals("0")) {
						this.commonService
								.deleteBySql("update  international_tradeinfo t  set t.tradestate='2'||substr(t.tradestate,2,(length(t.tradestate)-1)) where t.id='"
										+ trade.getId() + "'");
						// -------更新商户月交易限额-----------------
						merchant.setMonthTradeMoney(merchant
								.getMonthTradeMoney()
								+ trade.getTradeAmount());
						this.commonService.update(merchant);
					} else {
						this.responseCode = 0;
						this.commonService
								.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)) where t.id='"
										+ trade.getId() + "'");
					}
					return INPUT;
				}
				if (StringUtils.isBlank(xgm.getRespcode())) {
					// 传递的参数
					this.responseCode = 19;
					message = "Your payment is being processed";
					MD5info = MerNo + trade.getMerchantOrderNo()
					+ trade.getMoneyType() + ordercountValue
					+ responseCode + dateTime + MD5key;
					md5Value = md5.getMD5ofStr(MD5info);
					if (merchant.getStatutes().subSequence(6, 7).equals("0")) {
						this.commonService
								.deleteBySql("update  international_tradeinfo t  set t.tradestate='2'||substr(t.tradestate,2,(length(t.tradestate)-1)) where t.id='"
										+ trade.getId() + "'");
						// -------更新商户月交易限额-----------------
						merchant.setMonthTradeMoney(merchant
								.getMonthTradeMoney()
								+ trade.getTradeAmount());
						this.commonService.update(merchant);
					} else {
						this.responseCode = 0;
						this.commonService
								.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)) where t.id='"
										+ trade.getId() + "'");
					}
					return INPUT;
				}
				// 做DCC交易
				if (xgm.getRespcode().equals("00")) {
					// 交易
					System.out.println("RRN------"+xgm.getRrn());
					this.message = BOCPayResult.getName(xgm.getRespcode());
					this.responseCode = 88;
					this.commonService
							.deleteBySql("update  international_tradeinfo t  set t.tradestate='1'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
									+ message
									+ "' ,t.VIPAuthorizationNo='"
									+ xgm.getAuth_no()
									+ "' ,t.VIPBatchNo='"
									+ xgm.getInvoice()
									+ "',t.boc_date='"+xgm.getTxn_date()+"',t.boc_time='"+xgm.getTxn_time()+"'," +
											"t.boc_rrn='"+xgm.getRrn()+"' where t.id='"
									+ trade.getId() + "'");
					// -------更新商户月交易限额-----------------
					merchant.setMonthTradeMoney(merchant.getMonthTradeMoney()
							+ trade.getTradeAmount());
					this.commonService.update(merchant);
					MD5info = MerNo + trade.getMerchantOrderNo()
						+ trade.getMoneyType() + ordercountValue
						+ responseCode + dateTime + MD5key;
					md5Value = md5.getMD5ofStr(MD5info);

					// /---------------给持卡人发送邮件-----------------------////
					List<InternationalTerminalManager> tmm = this.commonService
							.list("select tm from InternationalTerminalManager tm where tm.terminalNo='"
									+ posNumber.trim() + "' ");
					String billaddressby = null;
					if (tmm.size() > 0) {
						InternationalTerminalManager tm = tmm.get(0);
						billaddressby = tm.getBillingAddress();
					}
					EmailInfo emailinfo = new EmailInfo();
					String mailinfo = emailinfo.getPaymentResultEmail(card
							.getEmail(), trade.getTradeAmount(), getStates()
							.getCurrencyTypeByNo(
									trade.getMoneyType().intValue()), trade
							.getTradeUrl(), trade.getTradeTime(),
							billaddressby, trade.getMerchantOrderNo(), trade
									.getOrderNo(), merchant);
					try {
						// 发送邮件,如果发送失败插入数据库发送
						if(merchant.getStatutes().substring(4, 5).equals("0")){
							CCSendMail.setSendMail(card.getEmail(), mailinfo,
							"visamasterpay@sitecomplaint.com");
							
						}
						System.out.println("TC邮件立马发出");
					} catch (Exception e) {
						// 往数据库插入等待发送邮件
						shopManagerService.addSendMessages(card.getEmail(),
								"ecpss@ecpss.cc", mailinfo, "0");
						System.out.println("TC邮件等待稍后发出");
						return INPUT;
					}
					return INPUT;
				} else if (xgm.getRespcode().equals("03")
						|| xgm.getRespcode().equals("12")
						|| xgm.getRespcode().equals("13")
						|| xgm.getRespcode().equals("30")
						|| xgm.getRespcode().equals("63")
						|| xgm.getRespcode().equals("76")
						|| xgm.getRespcode().equals("77")
						|| xgm.getRespcode().equals("89")
						|| xgm.getRespcode().equals("94")
						|| xgm.getRespcode().equals("95")
						|| xgm.getRespcode().equals("96")
						|| xgm.getRespcode().equals("98")
						|| xgm.getRespcode().equals("Z1")
						|| xgm.getRespcode().equals("99")) {
					// 传递的参数
					System.out.println("TC银行系统出错" + xgm.getRespcode());
					this.responseCode = 19;
					message = "Your payment is being processed";
					MD5info = trade.getMerchantOrderNo() + trade.getMoneyType()
							+ ordercountValue + responseCode + MD5key;
					md5Value = md5.getMD5ofStr(MD5info);

					if (merchant.getStatutes().subSequence(6, 7).equals("0")) {
						this.commonService
								.deleteBySql("update  international_tradeinfo t  set t.tradestate='2'||substr(t.tradestate,2,(length(t.tradestate)-1))," +
										"t.remark='"+xgm.getRespcode()+"' where t.id='"
										+ trade.getId() + "'");
					} else {
						this.responseCode = 0;
						this.commonService
								.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)) where t.id='"
										+ trade.getId() + "'");
					}
					return INPUT;
				}
				
				
				//返回以下代码 走EVIP 
				else if(xgm.getRespcode().equals("HH") || xgm.getRespcode().equals("UU") || xgm.getRespcode().equals("SW") 
						|| xgm.getRespcode().equals("FF") || xgm.getRespcode().equals("GG") || xgm.getRespcode().equals("SE")){
					System.out.println("BOC TC channel not connect,go to EVIP");
					List<Object[]> terminallist = this.commonService
					.list("select tm,mt from InternationalMerchantTerminal mt,InternationalTerminalManager tm "
							+ "where tm.id=mt.terminalId "
							+ "and (tm.terminalNo like '7777%' or tm.terminalNo like '0788%')  "
							+ "and mt.merchantId="
							+ merchant.getId());
					InternationalMerchantTerminal mterminal;
					InternationalTerminalManager terminalmgt;
					if(terminallist.size()>0 && cardnum.startsWith("4")){
						//-------------------更新通道为EVIP------------------
						Object[] obj = terminallist.get(0);
						mterminal = (InternationalMerchantTerminal) obj[1];
						terminalmgt = (InternationalTerminalManager) obj[0];
						
						String merchantChannel = "select mc.id from InternationalMerchantChannels mc where mc.merchantId="+merchant.getId()+" " +
								"and mc.channelId="+mterminal.getChannelId();
						List merchantchannellist = this.commonService.list(merchantChannel);
						Long merchantChannelid = (Long) merchantchannellist.get(0);
						this.commonService
						.deleteBySql("update  international_tradeinfo t set "
								+ " t.VIPTerminalNo='" + terminalmgt.getTerminalNo() + "',t.tradeChannel="+merchantChannelid
								+ " where t.id='" + trade.getId() + "' ");
						//重新赋值终端号给此交易
						posNumber = terminalmgt.getTerminalNo();
						posMerchantNo = terminalmgt.getMerchantNo();
						// 交易查询

						// 交易查询
						DCCMessage dcc = new DCCMessage();
						dcc.setMessageType("0800");// 类型
						dcc.setPrimary_Account_Number(AES.getCarNo(card.getCardNo()));// 账号2
						dcc.setProcessing_Code("970000");// 处理代码3
						dcc.setAmount_Transaction_Local(this.buzero(trade
								.getRmbAmount()
								+ ""));// 4 本地交易金额
						dcc.setSYSTEMS_TRACE_AUDIT_NUMBER(trade.getOrderNo().substring(
								trade.getOrderNo().length() - 6,
								trade.getOrderNo().length()));// 11 交易流水号
						dcc.setDATE_EXPIRATION(AES.getCarNo(card.getExpiryDate()).substring(2, 4)
								+ AES.getCarNo(card.getExpiryDate()).substring(0, 2));// 14 有效期
						dcc.setPOINT_OF_SERVICE_ENTRY_CODE("0012");// 22 POS进入模式
						dcc.setNETWORK_INTL_IDENTIFIER("0098");// 24 收单商户号
						dcc.setPOS_CONDITION_CODE("00");// 25 商户编码
						dcc.setCARD_ACCEPTOR_TERMINAL_ID(posNumber);// 41 商户终端号
						dcc.setCARD_ACCEPTOR_ID_CODE(posMerchantNo);// 42 商户编号
						dcc.setInvoice_Number(trade.getOrderNo().substring(
								trade.getOrderNo().length() - 6,
								trade.getOrderNo().length()));// 62
						DccUtil dc = new DccUtil();
						dc.setDccMessage(dcc);

						try {
							dcc = dc.getDccMessage();
							if (AES.getCarNo(card.getCardNo()).startsWith("5")) {
								dcc.setRESPONSE_CODE("YX");
							}
						} catch (Exception ex) {
							// 传递的参数
							this.responseCode = 19;
							message = "Your payment is being processed";
							MD5info = MerNo + trade.getMerchantOrderNo()
									+ trade.getMoneyType() + ordercountValue
									+ responseCode + dateTime + MD5key;
							md5Value = md5.getMD5ofStr(MD5info);
							if(merchant.getStatutes().subSequence(6,7).equals("0")){
								this.commonService
								.deleteBySql("update  international_tradeinfo t  set t.tradestate='2'||substr(t.tradestate,2,(length(t.tradestate)-1)) where t.id='"
										+ trade.getId() + "'");
								// -------更新商户月交易限额-----------------
								merchant.setMonthTradeMoney(merchant.getMonthTradeMoney()
										+ trade.getTradeAmount());
								this.commonService.update(merchant);
							}else{
								this.responseCode = 0;
								this.commonService
								.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)) where t.id='"
										+ trade.getId() + "'");
							}
							
							return INPUT;
						}
						System.out.println("DCC走++++++++++  " + dcc.getRESPONSE_CODE());
						if (StringUtils.isBlank(dcc.getRESPONSE_CODE())) {
							// 传递的参数
							this.responseCode = 19;
							message = "Your payment is being processed";
							MD5info = MerNo + trade.getMerchantOrderNo()
									+ trade.getMoneyType() + ordercountValue
									+ responseCode + dateTime + MD5key;
							md5Value = md5.getMD5ofStr(MD5info);
							if(merchant.getStatutes().subSequence(6,7).equals("0")){
								this.commonService
								.deleteBySql("update  international_tradeinfo t  set t.tradestate='2'||substr(t.tradestate,2,(length(t.tradestate)-1)) where t.id='"
										+ trade.getId() + "'");
								// -------更新商户月交易限额-----------------
								merchant.setMonthTradeMoney(merchant.getMonthTradeMoney()
										+ trade.getTradeAmount());
								this.commonService.update(merchant);
							}else{
								this.responseCode = 0;
								this.commonService
								.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)) where t.id='"
										+ trade.getId() + "'");
							}
							return INPUT;
						}
						// 做DCC交易
						if (dcc.getRESPONSE_CODE().equals("YY")) {
							// 交易
							if(StringUtils.isNotBlank(dcc.getAmount_Transaction_Foreign()) && StringUtils.isNotBlank(dcc.getCurrency_Code_Foreign())){
								this.commonService
								.deleteBySql("update  international_tradeinfo t  set t.DCCTradeCurrency='"+dcc.getCurrency_Code_Foreign()+"' " +
										",t.DCCTradeAmount=" +Double.valueOf(dcc.getAmount_Transaction_Foreign())+
										" where t.id='"
										+ trade.getId() + "'");
							}
							DCCMessage msg2 = new DCCMessage();
							msg2.setMessageType("0200");// 类型
							msg2.setPrimary_Account_Number(AES.getCarNo(card.getCardNo()));// 账号2
							msg2.setProcessing_Code("000000");// 处理代码3
							msg2.setAmount_Transaction_Local(this.buzero(trade
									.getRmbAmount()
									+ ""));// 4 本地交易金额
							msg2.setAmount_Transaction_Foreign(dcc
									.getAmount_Transaction_Foreign());// 5
							// 0810
							msg2.setConversion_Rate(dcc.getConversion_Rate());// 9
							// 0810
							msg2.setSYSTEMS_TRACE_AUDIT_NUMBER(trade.getOrderNo()
									.substring(trade.getOrderNo().length() - 6,
											trade.getOrderNo().length()));// 11 交易流水号
							msg2.setDATE_EXPIRATION(AES.getCarNo(card.getExpiryDate())
									.substring(2, 4)
									+ AES.getCarNo(card.getExpiryDate()).substring(0, 2));// 14 有效期
							msg2.setPOINT_OF_SERVICE_ENTRY_CODE("0012");// 22 POS进入模式
							msg2.setNETWORK_INTL_IDENTIFIER("0017");// 24 收单商户号
							msg2.setPOS_CONDITION_CODE("00");// 25 商户编码
							// msg2.setRETRIEVAL_REFERENCE_NUMBER("");//37
							msg2.setCARD_ACCEPTOR_TERMINAL_ID(posNumber);// 41
							// 商户终端号
							msg2.setCARD_ACCEPTOR_ID_CODE(posMerchantNo);// 42 商户编号
							msg2.setCurrency_Code_Foreign(dcc
									.getCurrency_Code_Foreign());// 49
							// 货币代码-----0810
							msg2.setCVV2_OR_CVC2(AES.getCarNo(card.getCvv2()));// cv2
							msg2.setInvoice_Number(trade.getOrderNo().substring(
									trade.getOrderNo().length() - 6,
									trade.getOrderNo().length()));// 62
							DccUtil dc2 = new DccUtil();
							dc2.setDccMessage(msg2);
							try {
								msg2 = dc2.getDccMessage();
							} catch (Exception ex) {

								this.responseCode = 19;
								message = "Your payment is being processed";
								MD5info = trade.getMerchantOrderNo()
										+ trade.getMoneyType() + ordercountValue
										+ responseCode + dateTime + MD5key;
								md5Value = md5.getMD5ofStr(MD5info);
								if(merchant.getStatutes().subSequence(6,7).equals("0")){
									this.commonService
									.deleteBySql("update  international_tradeinfo t  set t.tradestate='2'||substr(t.tradestate,2,(length(t.tradestate)-1)) where t.id='"
											+ trade.getId() + "'");
									// -------更新商户月交易限额-----------------
									merchant.setMonthTradeMoney(merchant.getMonthTradeMoney()
											+ trade.getTradeAmount());
									this.commonService.update(merchant);
								}else{
									this.responseCode = 0;
									this.commonService
									.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)) where t.id='"
											+ trade.getId() + "'");
								}
								
								// 交易冲正
								DCCMessage msg6 = new DCCMessage();
								msg6.setMessageType("0400");// 类型
								msg6.setPrimary_Account_Number(AES.getCarNo(card.getCardNo()));// 账号2
								msg6.setProcessing_Code("000000");// 处理代码3
								msg6.setAmount_Transaction_Local(this.buzero(trade
										.getRmbAmount()
										+ ""));// 4 本地交易金额
								msg6.setAmount_Transaction_Foreign(dcc
										.getAmount_Transaction_Foreign());// 5 0810
								msg6.setConversion_Rate(dcc.getConversion_Rate());// 9
								// 0810
								msg6.setSYSTEMS_TRACE_AUDIT_NUMBER(trade.getOrderNo()
										.substring(trade.getOrderNo().length() - 6,
												trade.getOrderNo().length()));// 11
								// 交易流水号
								msg6.setDATE_EXPIRATION(AES.getCarNo(card.getExpiryDate()).substring(
										2, 4)
										+ AES.getCarNo(card.getExpiryDate()).substring(0, 2));// 14
								// 有效期
								msg6.setPOINT_OF_SERVICE_ENTRY_CODE("0012");// 22
								// POS进入模式
								msg6.setNETWORK_INTL_IDENTIFIER("0017");// 24 收单商户号
								msg6.setPOS_CONDITION_CODE("00");// 25 商户编码
								msg6.setRETRIEVAL_REFERENCE_NUMBER(msg2
										.getRETRIEVAL_REFERENCE_NUMBER());// 37
								msg6.setCARD_ACCEPTOR_TERMINAL_ID(trade
										.getVIPTerminalNo());// 41 商户终端号
								msg6.setCARD_ACCEPTOR_ID_CODE(posMerchantNo);// 42
								// 商户编号
								msg6.setCurrency_Code_Foreign(dcc
										.getCurrency_Code_Foreign());// 49
								// 货币代码-----0810
								msg6.setInvoice_Number(trade.getOrderNo().substring(
										trade.getOrderNo().length() - 6,
										trade.getOrderNo().length()));// 62
								DccUtil dc6 = new DccUtil();
								dc6.setDccMessage(msg6);

								msg6 = dc6.getDccMessage();
								System.out
										.println("===============================yy交易冲正(撤销冲正):"
												+ msg6.getRESPONSE_CODE());

								return INPUT;
							}

							if (msg2.getRESPONSE_CODE().equals("00")) {
								if (chnals.equals("CN3D")) {
									this.message = CN3DPayResult.getName(msg2
											.getRESPONSE_CODE());
								} else {
									this.message = "Payment Success!";
								}
								this.responseCode = 88;
								this.commonService
										.deleteBySql("update  international_tradeinfo t  set t.tradestate='1'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
												+ message
												+ "' ,t.VIPAuthorizationNo='"
												+ msg2
														.getAUTH_IDENTIFICATION_RESPONSE()
												+ "' ,t.VIPBatchNo='XXXXXX' where t.id='"
												+ trade.getId() + "'");
								authorizationNo = msg2
										.getAUTH_IDENTIFICATION_RESPONSE();
								batchno = "XXXXXX";
								//清除持卡人cvv,有效期
//								this.commonService
//								.deleteBySql("update  international_cardholdersinfo t  set t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
//										+ trade.getId() + "'");
								// -------更新商户月交易限额-----------------
								merchant.setMonthTradeMoney(merchant
										.getMonthTradeMoney()
										+ trade.getTradeAmount());
								this.commonService.update(merchant);
								MD5info = MerNo + trade.getMerchantOrderNo()
										+ trade.getMoneyType() + ordercountValue
										+ responseCode + dateTime + MD5key;
								md5Value = md5.getMD5ofStr(MD5info);

								// /---------------给持卡人发送邮件-----------------------////
								List<InternationalTerminalManager> tmm = this.commonService
										.list("select tm from InternationalTerminalManager tm where tm.terminalNo='"
												+ posNumber.trim() + "' ");
								String billaddressby = null;
								if (tmm.size() > 0) {
									InternationalTerminalManager tm = tmm.get(0);
									billaddressby = tm.getBillingAddress();
								}
								String mailinfo=null;
								try {
									EmailInfo emailinfo = new EmailInfo();
									mailinfo = emailinfo.getPaymentResultEmail(card
											.getEmail(), trade.getTradeAmount(),
											getStates().getCurrencyTypeByNo(
													trade.getMoneyType().intValue()), trade
													.getTradeUrl(), trade.getTradeTime(),
											billaddressby, trade.getMerchantOrderNo(),
											trade.getOrderNo(),merchant);
									// 发送邮件,如果发送失败插入数据库发送
									if(merchant.getStatutes().substring(4, 5).equals("0")){
										CCSendMail.setSendMail(card.getEmail(), mailinfo,
										"ecpss@ecpss.cc");
										System.out.println("邮件立马发出");
									}
								} catch (Exception e) {
									// 往数据库插入等待发送邮件
									shopManagerService.addSendMessages(card.getEmail(),
											"ecpss@ecpss.cc", mailinfo, "0");
									System.out.println("邮件等待稍后发出");
									return INPUT;
								}

								return INPUT;
							} else if (msg2.getRESPONSE_CODE().equals("03")
									|| msg2.getRESPONSE_CODE().equals("12")
									|| msg2.getRESPONSE_CODE().equals("13")
									|| msg2.getRESPONSE_CODE().equals("30")
									|| msg2.getRESPONSE_CODE().equals("63")
									|| msg2.getRESPONSE_CODE().equals("68")
									|| msg2.getRESPONSE_CODE().equals("76")
									|| msg2.getRESPONSE_CODE().equals("77")
									|| msg2.getRESPONSE_CODE().equals("89")
									|| msg2.getRESPONSE_CODE().equals("94")
									|| msg2.getRESPONSE_CODE().equals("95")
									|| msg2.getRESPONSE_CODE().equals("96")
									|| msg2.getRESPONSE_CODE().equals("98")
									|| msg2.getRESPONSE_CODE().equals("99")) {
								// 传递的参数
								this.responseCode = 19;
								message = "Your payment is being processed";
								MD5info = MerNo + trade.getMerchantOrderNo()
										+ trade.getMoneyType() + ordercountValue
										+ responseCode + dateTime + MD5key;
								md5Value = md5.getMD5ofStr(MD5info);
								if(merchant.getStatutes().subSequence(6,7).equals("0")){
									this.commonService
									.deleteBySql("update  international_tradeinfo t  set t.tradestate='2'||substr(t.tradestate,2,(length(t.tradestate)-1)) where t.id='"
											+ trade.getId() + "'");
									
								}else{
									this.responseCode = 0;
									this.commonService
									.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)) where t.id='"
											+ trade.getId() + "'");
								}
								
								return INPUT;
							} else {
								// 如果是CN3D通道,并且返回值不为空就返回给商户结果,并且显示详细失败原因
								if (chnals.equals("CN3D")
										&& StringUtils.isNotBlank(CN3DPayResult
												.getName(msg2.getRESPONSE_CODE()))) {
									this.message = CN3DPayResult.getName(msg2
											.getRESPONSE_CODE());
								}
								// 如果是CN3D通道,并且返回值为空就不返回结果到商户网站
								else if (chnals.equals("CN3D")
										&& StringUtils.isBlank(CN3DPayResult
												.getName(msg2.getRESPONSE_CODE()))) {
									this.message = "";
									this.responseCode = 0;
									this.commonService
											.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
													+ message
													+ "' where t.id='"
													+ trade.getId() + "'");
									
									//清除持卡人cvv,有效期
//									this.commonService
//									.deleteBySql("update  international_cardholdersinfo t  set t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
//											+ trade.getId() + "'");
									MD5info = MerNo + trade.getMerchantOrderNo()
											+ trade.getMoneyType() + ordercountValue
											+ responseCode + dateTime + MD5key;
									md5Value = md5.getMD5ofStr(MD5info);
									return INPUT;
								} else {
									this.message = "Payment Declined!"
											+ msg2.getRESPONSE_CODE();
								}

								this.responseCode = 0;
								this.commonService
										.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
												+ message
												+ "' where t.id='"
												+ trade.getId() + "'");
								this.message = EvipPayResult.getName(msg2.getRESPONSE_CODE());
								//清除持卡人cvv,有效期
//								this.commonService
//								.deleteBySql("update  international_cardholdersinfo t  set t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
//										+ trade.getId() + "'");
								MD5info = MerNo + trade.getMerchantOrderNo()
										+ trade.getMoneyType() + ordercountValue
										+ responseCode + dateTime + MD5key;
								md5Value = md5.getMD5ofStr(MD5info);
								return INPUT;
							}

						} else if (dcc.getRESPONSE_CODE().equals("YX")) {
							InternationalTerminalManager tm = (InternationalTerminalManager) this.commonService
									.load(InternationalTerminalManager.class,
											terminalmgt.getId());
							String ter = "select tm from InternationalTerminalManager tm where tm.terminalNo='"
									+ tm.getAndterminalNo().trim() + "' ";
							List<InternationalTerminalManager> list = this.commonService
									.list(ter);
							System.out.println("终端号：+++++++++++++++"
									+ list.get(0).getTerminalNo());
							DCCMessage dcc3 = new DCCMessage();
							dcc3.setMessageType("0200");// 类型
							dcc3.setPrimary_Account_Number(AES.getCarNo(card.getCardNo()));// 账号2
							dcc3.setProcessing_Code("000000");// 处理代码3
							dcc3.setAmount_Transaction_Local(this.buzero(trade
									.getRmbAmount()
									+ ""));// 4 本地交易金额
							dcc3.setSYSTEMS_TRACE_AUDIT_NUMBER(trade.getOrderNo()
									.substring(trade.getOrderNo().length() - 6,
											trade.getOrderNo().length()));// 11 交易流水号
							dcc3.setDATE_EXPIRATION(AES.getCarNo(card.getExpiryDate())
									.substring(2, 4)
									+ AES.getCarNo(card.getExpiryDate()).substring(0, 2));// 14 有效期
							dcc3.setPOINT_OF_SERVICE_ENTRY_CODE("0012");// 22 POS进入模式
							dcc3.setNETWORK_INTL_IDENTIFIER("0017");// 24 收单商户号
							dcc3.setCARD_ACCEPTOR_TERMINAL_ID(list.get(0)
									.getTerminalNo());// 41 商户终端号
							dcc3.setCARD_ACCEPTOR_ID_CODE(list.get(0).getMerchantNo());// 42
							// 商户编号
							dcc3.setCVV2_OR_CVC2(AES.getCarNo(card.getCvv2()));// cv2 61
							dcc3.setInvoice_Number(trade.getOrderNo().substring(
									trade.getOrderNo().length() - 6,
									trade.getOrderNo().length()));// 62
							DccUtil dc3 = new DccUtil();
							dc3.setDccMessage(dcc3);
							try {
								dcc3 = dc3.getDccMessage();
							} catch (Exception ex) {

								this.responseCode = 19;
								message = "Your payment is being processed";
								MD5info = MerNo + trade.getMerchantOrderNo()
										+ trade.getMoneyType() + ordercountValue
										+ responseCode + dateTime + MD5key;
								md5Value = md5.getMD5ofStr(MD5info);
								if(merchant.getStatutes().subSequence(6,7).equals("0")){
									this.commonService
									.deleteBySql("update  international_tradeinfo t  set t.tradestate='2'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.VIPTerminalNo='"
											+ list.get(0).getTerminalNo()
											+ "' where t.id='"
											+ trade.getId()
											+ "'");
								}else{
									this.responseCode = 0;
									this.commonService
									.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.VIPTerminalNo='"
											+ list.get(0).getTerminalNo()
											+ "' where t.id='"
											+ trade.getId()
											+ "'");
								}
								
								// -------更新商户月交易限额-----------------
								merchant.setMonthTradeMoney(merchant
										.getMonthTradeMoney()
										+ trade.getTradeAmount());
								this.commonService.update(merchant);
								DCCMessage msg7 = new DCCMessage();
								msg7.setMessageType("0400");// 类型
								msg7.setPrimary_Account_Number(AES.getCarNo(card.getCardNo()));// 账号2
								msg7.setProcessing_Code("000000");// 处理代码3
								msg7.setAmount_Transaction_Local(this.buzero(trade
										.getRmbAmount()
										+ ""));// 4 本地交易金额
								// msg7.setAmount_Transaction_Foreign(dcc.getAmount_Transaction_Foreign());//5
								// 0810
								// msg7.setConversion_Rate(dcc.getConversion_Rate());//9
								// 0810
								msg7.setSYSTEMS_TRACE_AUDIT_NUMBER(trade.getOrderNo()
										.substring(trade.getOrderNo().length() - 6,
												trade.getOrderNo().length()));// 11
								// 交易流水号
								msg7.setDATE_EXPIRATION(AES.getCarNo(card.getExpiryDate()).substring(
										2, 4)
										+ AES.getCarNo(card.getExpiryDate()).substring(0, 2));// 14
								// 有效期
								msg7.setPOINT_OF_SERVICE_ENTRY_CODE("0012");// 22
								// POS进入模式
								msg7.setNETWORK_INTL_IDENTIFIER("0017");// 24 收单商户号
								msg7.setPOS_CONDITION_CODE("00");// 25 商户编码
								msg7.setRETRIEVAL_REFERENCE_NUMBER(dcc3
										.getRETRIEVAL_REFERENCE_NUMBER());// 37
								msg7.setCARD_ACCEPTOR_TERMINAL_ID(list.get(0)
										.getTerminalNo());// 41 商户终端号
								msg7.setCARD_ACCEPTOR_ID_CODE(list.get(0)
										.getMerchantNo());// 42 商户编号
								// msg7.setCurrency_Code_Foreign(dcc.getCurrency_Code_Foreign());//49
								// 货币代码-----0810
								// msg7.setCVV2_OR_CVC2(cvv2);//cv2
								msg7.setInvoice_Number(trade.getOrderNo().substring(
										trade.getOrderNo().length() - 6,
										trade.getOrderNo().length()));// 62
								DccUtil dc7 = new DccUtil();
								dc7.setDccMessage(msg7);

								msg7 = dc7.getDccMessage();
								System.out.println("=====================yx交易冲正:"
										+ msg7.getRESPONSE_CODE());
								CCSendMail.setSendMail("89610614@qq.com", "dcc连接不通走VIP"
										+ trade.getId(), "ecpss@ecpss.cc");
								return INPUT;
							}
							if (dcc3.getRESPONSE_CODE().equals("00")) {
								if (chnals.equals("CN3D")) {
									this.message = CN3DPayResult.getName(dcc3
											.getRESPONSE_CODE());
								} else {
									this.message = "Payment Success!";
								}
								this.responseCode = 88;
								this.commonService
										.deleteBySql("update  international_tradeinfo t  set t.tradestate='1'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
												+ message
												+ "',t.VIPTerminalNo='"
												+ list.get(0).getTerminalNo()
												+ "',t.VIPAuthorizationNo='"
												+ dcc3
														.getAUTH_IDENTIFICATION_RESPONSE()
												+ "' ,t.VIPBatchNo='XXXXXX' where t.id='"
												+ trade.getId() + "'");
								//清除持卡人cvv,有效期
//								this.commonService
//								.deleteBySql("update  international_cardholdersinfo t  set t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
//										+ trade.getId() + "'");
								authorizationNo = dcc3
										.getAUTH_IDENTIFICATION_RESPONSE();
								batchno = "XXXXXX";
								MD5info = trade.getMerchantOrderNo()
										+ trade.getMoneyType() + ordercountValue
										+ responseCode + dateTime + MD5key;
								md5Value = md5.getMD5ofStr(MD5info);
								// -------更新商户月交易限额-----------------
								merchant.setMonthTradeMoney(merchant
										.getMonthTradeMoney()
										+ trade.getTradeAmount());
								this.commonService.update(merchant);
								String mailinfo=null;
								try {
									// /---------------给持卡人发送邮件-----------------------////
									EmailInfo emailinfo = new EmailInfo();
									mailinfo = emailinfo.getPaymentResultEmail(card
											.getEmail(), trade.getTradeAmount(),
											getStates().getCurrencyTypeByNo(
													trade.getMoneyType().intValue()), trade
													.getTradeUrl(), trade.getTradeTime(),
											list.get(0).getBillingAddress(), trade
													.getMerchantOrderNo(), trade
													.getOrderNo(),merchant);
									// 发送邮件,如果发送失败插入数据库发送
									if(merchant.getStatutes().substring(4, 5).equals("0")){
										CCSendMail.setSendMail(card.getEmail(), mailinfo,
										"ecpss@ecpss.cc");
										System.out.println("邮件立马发出");
									}
								} catch (Exception e) {
									// 往数据库插入等待发送邮件
									shopManagerService.addSendMessages(card.getEmail(),
											"ecpss@ecpss.cc", mailinfo, "0");
									System.out.println("邮件等待稍后发出");
									return INPUT;
								}
								return INPUT;
							} else if (dcc3.getRESPONSE_CODE().equals("03")
									|| dcc3.getRESPONSE_CODE().equals("12")
									|| dcc3.getRESPONSE_CODE().equals("13")
									|| dcc3.getRESPONSE_CODE().equals("30")
									|| dcc3.getRESPONSE_CODE().equals("63")
									|| dcc3.getRESPONSE_CODE().equals("68")
									|| dcc3.getRESPONSE_CODE().equals("76")
									|| dcc3.getRESPONSE_CODE().equals("77")
									|| dcc3.getRESPONSE_CODE().equals("89")
									|| dcc3.getRESPONSE_CODE().equals("94")
									|| dcc3.getRESPONSE_CODE().equals("95")
									|| dcc3.getRESPONSE_CODE().equals("96")
									|| dcc3.getRESPONSE_CODE().equals("98")
									|| dcc3.getRESPONSE_CODE().equals("99")) {
								// 传递的参数
								this.responseCode = 19;
								message = "Your payment is being processed";
								MD5info = trade.getMerchantOrderNo()
										+ trade.getMoneyType() + ordercountValue
										+ responseCode + dateTime + MD5key;
								md5Value = md5.getMD5ofStr(MD5info);
								if(merchant.getStatutes().subSequence(6,7).equals("0")){
									this.commonService
									.deleteBySql("update  international_tradeinfo t  set t.tradestate='2'||substr(t.tradestate,2,(length(t.tradestate)-1)) where t.id='"
											+ trade.getId() + "'");
									
								}else{
									this.responseCode = 0;
									this.commonService
									.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)) where t.id='"
											+ trade.getId() + "'");
								}
								return INPUT;
							} else {
								// 如果是CN3D通道,并且返回值不为空就返回给商户结果,并且显示详细失败原因
								if (chnals.equals("CN3D")
										&& StringUtils.isNotBlank(CN3DPayResult
												.getName(dcc3.getRESPONSE_CODE()))) {
									this.message = CN3DPayResult.getName(dcc3
											.getRESPONSE_CODE());
								}// 如果是CN3D通道,并且返回值为空就不返回结果到商户网站
								else if (chnals.equals("CN3D")
										&& StringUtils.isBlank(CN3DPayResult
												.getName(dcc3.getRESPONSE_CODE()))) {
									this.message = "";
									this.responseCode = 0;
									this.commonService
											.deleteBySql("update  international_tradeinfo t  set t.tradestate='3'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
													+ message
													+ "',t.VIPTerminalNo='"
													+ list.get(0).getTerminalNo()
													+ "' where t.id='"
													+ trade.getId()
													+ "'");
									MD5info = MerNo + trade.getMerchantOrderNo()
											+ trade.getMoneyType() + ordercountValue
											+ responseCode + dateTime + MD5key;
									md5Value = md5.getMD5ofStr(MD5info);
									//清除持卡人cvv,有效期
//									this.commonService
//									.deleteBySql("update  international_cardholdersinfo t  set t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
//											+ trade.getId() + "'");
									return INPUT;
								} else {
									this.message = "Payment Declined!"
											+ dcc3.getRESPONSE_CODE();
								}
								this.responseCode = 0;
								this.commonService
										.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
												+ message
												+ "',t.VIPTerminalNo='"
												+ list.get(0).getTerminalNo()
												+ "' where t.id='"
												+ trade.getId()
												+ "'");
								//清除持卡人cvv,有效期
//								this.commonService
//								.deleteBySql("update  international_cardholdersinfo t  set t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
//										+ trade.getId() + "'");
								MD5info = MerNo + trade.getMerchantOrderNo()
										+ trade.getMoneyType() + ordercountValue
										+ responseCode + dateTime + MD5key;
								md5Value = md5.getMD5ofStr(MD5info);
								return INPUT;
							}

						}
						return INPUT;
					
					}else{
						//显示详细失败原因
						message = "Payment Declined!"+xgm.getRespcode();
						this.responseCode = 0;
						this.commonService
								.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
										+ message
										+ "' where t.id='"
										+ trade.getId() + "'");
						MD5info = MerNo + trade.getMerchantOrderNo()
							+ trade.getMoneyType() + ordercountValue
							+ responseCode + dateTime + MD5key;
						md5Value = md5.getMD5ofStr(MD5info);
						return INPUT;
					}
					
				}
				else {
					//显示详细失败原因
					message = "Payment Declined!"+xgm.getRespcode();
					this.responseCode = 0;
					this.commonService
							.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
									+ message
									+ "' where t.id='"
									+ trade.getId() + "'");
					MD5info = MerNo + trade.getMerchantOrderNo()
						+ trade.getMoneyType() + ordercountValue
						+ responseCode + dateTime + MD5key;
					md5Value = md5.getMD5ofStr(MD5info);
					return INPUT;
				}

			
			} else if (chnals.equals("VC")) {
				
			} else if (chnals.equals("MC")) {
				
			}

		} catch (Exception e) {
			return INPUT;
		}
		return INPUT;
	}

	public boolean checkAll() {
		boolean check = false;
		// 验证邮箱
		Pattern p = Pattern
				.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
		Matcher m = p.matcher(this.getEmail());
		// Matcher m1 = p.matcher(this.getShippingEmail());
		if (!m.find()) {
			check = true;
		}
		// else if(!m1.find()){
		// check = true;
		// }
		else if (this.getCvv2().length() != 3) {
			check = true;
		} else if (!StringUtils.isNotBlank(this.getMonth())) {
			check = true;
		} else if (!StringUtils.isNotBlank(this.getYear())) {
			check = true;
		} else if (!CheckCardNo.isValid(this.getCardnum())) {
			check = true;
		}
		return check;
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

	public TradeManager getTradeManager() {
		return tradeManager;
	}

	public void setTradeManager(TradeManager tradeManager) {
		this.tradeManager = tradeManager;
	}

	public ShopManagerService getShopManagerService() {
		return shopManagerService;
	}

	public void setShopManagerService(ShopManagerService shopManagerService) {
		this.shopManagerService = shopManagerService;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getReturnURL() {
		return ReturnURL;
	}

	public void setReturnURL(String returnURL) {
		ReturnURL = returnURL;
	}

	public String getMD5key() {
		return MD5key;
	}

	public void setMD5key(String md5key) {
		MD5key = md5key;
	}

	public HashMap getH() {
		return h;
	}

	public void setH(HashMap h) {
		this.h = h;
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

	public String getSECURE_SECRET() {
		return SECURE_SECRET;
	}

	public void setSECURE_SECRET(String secure_secret) {
		SECURE_SECRET = secure_secret;
	}

	public MaxMindExample getExam() {
		return exam;
	}

	public void setExam(MaxMindExample exam) {
		this.exam = exam;
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

	public String getVpc_CardExp() {
		return vpc_CardExp;
	}

	public void setVpc_CardExp(String vpc_CardExp) {
		this.vpc_CardExp = vpc_CardExp;
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

	public String getMerNo() {
		return MerNo;
	}

	public void setMerNo(String merNo) {
		MerNo = merNo;
	}

	public String getRorderno() {
		return rorderno;
	}

	public void setRorderno(String rorderno) {
		this.rorderno = rorderno;
	}

	public Date getTradetime() {
		return tradetime;
	}

	public void setTradetime(Date tradetime) {
		this.tradetime = tradetime;
	}

	public Long getMerchantnoValue() {
		return merchantnoValue;
	}

	public void setMerchantnoValue(Long merchantnoValue) {
		this.merchantnoValue = merchantnoValue;
	}

	public boolean isCheckMerch() {
		return checkMerch;
	}

	public void setCheckMerch(boolean checkMerch) {
		this.checkMerch = checkMerch;
	}

	public long getIsDredge() {
		return isDredge;
	}

	public void setIsDredge(long isDredge) {
		this.isDredge = isDredge;
	}

	public String getAmount() {
		return Amount;
	}

	public void setAmount(String amount) {
		Amount = amount;
	}

	public Double getTradeMoney() {
		return tradeMoney;
	}

	public void setTradeMoney(Double tradeMoney) {
		this.tradeMoney = tradeMoney;
	}

	public double getRmbmoney() {
		return rmbmoney;
	}

	public void setRmbmoney(double rmbmoney) {
		this.rmbmoney = rmbmoney;
	}

	public Double getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(Double tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	public String getChannels() {
		return channels;
	}

	public void setChannels(String channels) {
		this.channels = channels;
	}

	public String getBillNo() {
		return BillNo;
	}

	public void setBillNo(String billNo) {
		BillNo = billNo;
	}

	public String getCurrency() {
		return Currency;
	}

	public void setCurrency(String currency) {
		Currency = currency;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getLanguage() {
		return Language;
	}

	public void setLanguage(String language) {
		Language = language;
	}

	public String getMd5src() {
		return md5src;
	}

	public void setMd5src(String md5src) {
		this.md5src = md5src;
	}

	public String getCooknumber() {
		return cooknumber;
	}

	public void setCooknumber(String cooknumber) {
		this.cooknumber = cooknumber;
	}

	public String getMD5info() {
		return MD5info;
	}

	public void setMD5info(String md5info) {
		MD5info = md5info;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getTradeAdd() {
		return tradeAdd;
	}

	public void setTradeAdd(String tradeAdd) {
		this.tradeAdd = tradeAdd;
	}

	public Long getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(Long moneyType) {
		this.moneyType = moneyType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTradeMoneyType() {
		return tradeMoneyType;
	}

	public void setTradeMoneyType(String tradeMoneyType) {
		this.tradeMoneyType = tradeMoneyType;
	}

	public String getOrdercount() {
		return ordercount;
	}

	public void setOrdercount(String ordercount) {
		this.ordercount = ordercount;
	}

	public String getMd5Value() {
		return md5Value;
	}

	public void setMd5Value(String md5Value) {
		this.md5Value = md5Value;
	}

	public String getMerchantOrderNo() {
		return merchantOrderNo;
	}

	public void setMerchantOrderNo(String merchantOrderNo) {
		this.merchantOrderNo = merchantOrderNo;
	}

	public String getResultMd5() {
		return resultMd5;
	}

	public void setResultMd5(String resultMd5) {
		this.resultMd5 = resultMd5;
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

	public String getCardbank() {
		return cardbank;
	}

	public void setCardbank(String cardbank) {
		this.cardbank = cardbank;
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

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProducts() {
		return products;
	}

	public void setProducts(String products) {
		this.products = products;
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

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getAuthorizationNo() {
		return authorizationNo;
	}

	public void setAuthorizationNo(String authorizationNo) {
		this.authorizationNo = authorizationNo;
	}

	public String getBatchno() {
		return batchno;
	}

	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}

	public Long getMerchantno() {
		return merchantno;
	}

	public void setMerchantno(Long merchantno) {
		this.merchantno = merchantno;
	}
}
