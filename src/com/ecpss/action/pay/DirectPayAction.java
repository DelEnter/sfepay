package com.ecpss.action.pay;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import sun.misc.BASE64Decoder;

import com.ecpss.action.BaseAction;
import com.ecpss.model.payment.InternationalTradeinfo;
import com.ecpss.model.shop.InternationalExchangerate;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.model.shop.InternationalMoneykindname;
import com.ecpss.util.GetBatchNo;
import com.ecpss.util.MD5;

public class DirectPayAction extends BaseAction {
	private static Hashtable<String, Long> orderCache = new Hashtable<String, Long>();//hashtable集合接收用
	private static Thread orderExpiredThread;
	@Autowired
	@Qualifier("tradeManager")
	private TradeManager tradeManager;
	// 抛过来的商户，订单，信息
	private String csid;
	private String MerNo; // 商户
	private String rorderno; // 交易流水号
	private Date tradetime; // 交易时间
	private Long merchantnoValue;
	private boolean checkMerch;// 交易商户是否存在
	private long isDredge;// 检验商户通道是否开通
	private String Amount; // 支付金额
	private Double tradeMoney;// 支付金额
	private double rmbmoney = 0; // 人民币交易金额
	private Double tradeAmount; // 交易金额（外币）
	private String channels; // 使用的商户通道F
	private String BillNo; // 订单编号
	private String Currency; // 币种
	private String currencyName;//币种名字（单位）
	private String Language = "en"; // 支付语言
	private String ReturnURL; // 返回地址
	private String md5src; // 网关加密md5
	private String cookie; // cookies
	private String cooknumber;
	private String MD5info; // MD5加密
	// 程序处理需要的参数
	private int responseCode;// 响应代号；
	private String MD5key; // MD5key值
	private String tradeAdd;
	private Long moneyType;//金额类型
	private String message;//信息
	// md5 数据校验
	private String tradeMoneyType;//金额类型
	private String ordercount; // 支付金额(转换为支付金额)
	private String md5Value; // 支付网关对商户信息进行加密
	private String merchantOrderNo;//商户订单号
	private String resultMd5;
	// Q 支付请求.校验顺序：1.交易商户是否存在 ，2.检验商户通道是否开通 ;3.返回网址必须注册 4.信息是否被篡改。
	// 持卡人信息带过来 billingAddress
	private String firstname;//持卡人姓名第一个字
	private String lastname;//持卡人姓名后面的字
	private String cardbank;//发卡行
	private String email;//邮箱
	private String phone;//电话
	private String zipcode;//邮编
	private String address;//地址
	private String city;//城市
	private String state;//区域
	private String country;//国家
	private String products;//物品信息
	// shippingAddress账单地址
	private String shippingFirstName;//持卡人姓名第一个字
	private String shippingLastName;//持卡人姓名后面的字
	private String shippingEmail;//邮箱
	private String shippingPhone;//电话
	private String shippingZipcode;//邮编
	private String shippingAddress;//地址
	private String shippingCity;//城市
	private String shippingSstate;//区域
	private String shippingCountry;//国家
	private String redirectPath;//
	private String cardnum;//卡号
	private String cvv2;//验证安全码
	private String year; // 年
	private String month; // 月
	private String newcardtype;//卡类型
	private String TradeInfo;//交易信息
	private String addIp;//
	private String sfeVersion;//接口版本控制
	Logger logger = Logger.getLogger(DirectPayAction.class.getName());//打印日志

	public String payRequest(){
		logger.error("*********************进入直连通道**************");//进入直连通道
		MerNo = "3734";//商户号赋值3734
		
		logger.info("*********************发现交易请求"+MerNo+"***************************"+this.getReturnURL());//发现交易请求
		MD5 md5 = new MD5();//md5加密
		try {
			HttpServletRequest request = ServletActionContext.getRequest();//获取request对象
			// HttpSession session = request.getSession();
			merchantOrderNo = BillNo;//商户订单号
			tradeMoneyType = Currency;//币种
			// 交易商户是否存在
			logger.error("*********************交易商户号**************"+MerNo);
			if(StringUtils.isBlank(MerNo)){//判断商户号是否为空
				MerNo="";
			}
			String sql1 = " from InternationalMerchant t where t.merno='"
								+ MerNo.trim() + "'";//sql语句根据商户号查询
						
				InternationalMerchant merchant = new InternationalMerchant();// 接收获取商户的资料
						List<InternationalMerchant> merchantList = this.commonService.list(sql1);
			if (merchantList == null || merchantList.size() == 0) {//接收过来的商户资料是为null或者不存在
					logger.error("******************交易请求商户号错误，请检查****************"+this.getReturnURL());//商户号错误+返回路径
						responseCode = 10;//返回10：商户未注册
						resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;//组建resultMd5（md5info）
						md5Value = md5.getMD5ofStr(resultMd5);//加密resultMd5（md5info）
						message = "Payment Declined";//
							// 不存在该商户号
						return INPUT;//xml配置的文件有相应的代码（对应的jsp）
						}
			if(StringUtils.isNotBlank(sfeVersion)){//如果接口版本不是null  
				String hqlv="from InternationalPayVersion where merchantId is null and version='"+sfeVersion+"'";//sql语句根据传过来的版本号和 商户号ID查询   查询的是禁用版本
				String hqlv2="from InternationalPayVersion pay,InternationalMerchant me where me.id=pay.merchantId and me.merno='"+MerNo+"' and pay.version='"+sfeVersion+"'";//两表联查从 1.商户信息表和2.接口版本表根据商户号版本号查询
				List allv = this.commonService.list(hqlv);//hqlv的查询结果
				if(allv==null||allv.size()==0){//如果没有查询到
					List onev = this.commonService.list(hqlv2);//没有查询带 调用hqlv2查询
					if(onev!=null&&onev.size()>0){//判断hqlv2有没有查询到
						responseCode = 36;//查询到返回状态码36：支付版本错误
						resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;//组建md5info
						md5Value = md5.getMD5ofStr(resultMd5);//加密md5info
						message = "Payment Declined";
						logger.error("******************支付版本禁止使用****************"+this.sfeVersion);//打印日志支付版本禁止使用+版本号
						return INPUT;//返回：xml配置的文件有相应的代码（对应的jsp）
					}
				}else{
					responseCode = 36;//查询到返回状态码36：支付版本错误
					resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;
					md5Value = md5.getMD5ofStr(resultMd5);//组建md5info
					message = "Payment Declined";
					logger.error("******************支付版本禁止使用****************"+this.sfeVersion);//打印日志支付版本禁止使用+版本号
					return INPUT;//返回：xml配置的文件有相应的代码（对应的jsp）
				}
			}else{//接口版本是null
				responseCode = 36;//返回状态码：36 ：支付版本错误
				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;//组建md5info
				md5Value = md5.getMD5ofStr(resultMd5);//加密md5info
				message = "Payment Declined";
				logger.error("******************支付版本禁止使用****************"+this.sfeVersion);//打印日志支付版本禁止使用+版本号
				return INPUT;//返回：xml配置的文件有相应的代码（对应的jsp）
			}
			logger.info("*****************打印订单信息******************");//打印日志打印订单信息
			logger.info("MerNo:"+MerNo);//商户号
			logger.info("newcardtype:"+newcardtype);//卡类型
			logger.info("cardnum:"+cardnum);//卡号
			logger.info("cardbank:"+cardbank);//发卡行
			logger.info("BillNo:"+BillNo);//订单号
			logger.info("Amount:"+Amount);//金额
			logger.info("Currency:"+Currency);//币种
			logger.info("Language:"+Language);//语言
			logger.info("MD5info:"+MD5info);//加密过的md5info
			logger.info("ReturnURL:"+ReturnURL);//返回路径
			logger.info("shippingFirstName:"+shippingFirstName);//客户账单名字的第一个字
			logger.info("shippingLastName:"+shippingLastName);//客户账单名的后面的字
			logger.info("shippingEmail:"+shippingEmail);//邮箱
			logger.info("shippingPhone:"+shippingPhone);//电话
			logger.info("shippingZipcode:"+shippingZipcode);//邮编
			logger.info("shippingAddress:"+shippingAddress);//地址
			logger.info("shippingCity:"+shippingCity);//城市
			logger.info("shippingSstate:"+shippingSstate);//区域
			logger.info("shippingCountry:"+shippingCountry);//国家
			logger.info("products:"+products);//商品信息
			logger.info("csid:"+csid);//
			logger.info("*****************打印订单信息结束******************");//打印日志打印订单信息结束
			
			BASE64Decoder base64=new BASE64Decoder();//加密编码工具
			if(StringUtils.isNotBlank(csid)){//如果csid为不为null
				csid=new String((base64.decodeBuffer(csid)));//解码
				csid=URLDecoder.decode(csid);//编码
			}
			logger.info("*****************打印csid信息******************："+csid);//打印csid信息
			if (orderExpiredThread == null)//如果线程为空
			{
				orderExpiredThread = new Thread(){//创建一个线程
					public void run() {//重写run方法
						while (true) {//循环
							try {
								Thread.sleep(60 * 1000);//休眠时间
							} catch (InterruptedException e) {   
								e.printStackTrace();
							}
							//清除过期的缓存
							int expiredMin = 3;//定义一个常量3
							Iterator<Entry<String, Long>> it = orderCache.entrySet().iterator();//创建迭代器
							List<String> expiredKeys = new ArrayList<String>();//创建一个list集合
							while (it.hasNext()) {//如果可以拿到值
								Entry<String, Long> entry = it.next();//获取这个元素
								long addTimeMill = entry.getValue();//获取这个元素的值
								if ((System.currentTimeMillis() - addTimeMill) >= expiredMin * 60 * 1000)//如果系统当前时间-添加时间=3分钟
								{
									expiredKeys.add(entry.getKey());//把这个元素的key添加到创建的里
								}
							}
							for (String expiredKey : expiredKeys) {//循环
								orderCache.remove(expiredKey);//移除超时的订单
								logger.info("移除超时的缓存订单：" + expiredKey);//打印日志
							}
							logger.info("缓存中的订单数：" + orderCache.size());//打印日志：缓存的订单数
						}
					}
				};
				orderExpiredThread.start();//启动线程
			}
			
			//缓存订单
			String orderInfo = cardnum + Amount + addIp + MerNo;//订单信息：卡号+金额+商务号+IP
			if (orderCache.get(orderInfo) != null)//如果不为null
			{
				logger.info("缓存中存在订单 " + orderInfo + "加入时间：" + orderCache.get(orderInfo));//打印日志：订单+加入时间
				responseCode = 5;//返回码 5：同一IP发生多次交易
				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;//组建md5info
				md5Value = md5.getMD5ofStr(resultMd5);//加密md5info
				message = "Payment Declined";
				logger.info("*********************支付结果返回码***************************"+responseCode);//打印日志 返回码：5：同一IP发生多次交易
				return INPUT;//返回：xml配置的文件有相应的代码（对应的jsp）
			}
			else//是null
			{
				logger.info("新订单 加入缓存" + orderInfo);//打印日志：新订单加入缓存
				orderCache.put(orderInfo, System.currentTimeMillis());//hashtable加入一个订单：订单+系统当前时间
			}
/*			ZH-CN%26%40ZH-CN%26%40MOZILLA%26%40%26%40NETSCAPE%26%405.0+%28WINDOWS+NT+6.1%3B+WOW64%29+APPLEWEBKIT%2F537.36+%28KHTML%2C+LIKE+GECKO%29+CHROME%2F50.0.2661.102+SAFARI%2F537.36%26%40TRUE%26%40C9AD750F48C6A7848E4A0E531A1B5C8F1506671385389%26%40E98D6DE1%26%40WIN32%26%40MOZILLA%2F5.0+%28WINDOWS+NT+6.1%3B+WOW64%29+APPLEWEBKIT%2F537.36+%28KHTML%2C+LIKE+GECKO%29+CHROME%2F50.0.2661.102+SAFARI%2F537.36%26%40%26%40UNDEFINED%26%401440X900%26%4024%26%408%26%40WWW.TRADE2015.NET%26%405B1888C5DA17BCC29FBCF10562F95A231506671385390
*/			
			
			if(StringUtils.isNotBlank(cardnum)){//如果卡号不是null
				cardnum=new String((base64.decodeBuffer(cardnum)));//编码
				if(cardnum.length()<16){//如果卡号长度小于16位
					this.responseCode = 37;//返回码 37：卡号不规范（卡号不规范，或者卡号不满16位，或者收不到卡号）
					MD5info = merchantOrderNo
							 + ordercount
							+ responseCode + MD5key;//组建MD5info
					md5Value = md5.getMD5ofStr(MD5info);//加密
					message = "Payment Declined,Card is error!";//返回信息 卡号错误
					logger.info("*********************错误的卡号："+cardnum);//打印日志 +错误的卡号
					logger.info("*********************支付结果返回码***************************"+responseCode);//打印日志：支付结果返回码37 ：卡号不规范，或者卡号不满16位，或者收不到卡号
					return INPUT;//返回：xml配置的文件有相应的代码（对应的jsp）
				}
			}else{//如果卡号是null
				this.responseCode = 37;//返回码 37：收不到卡号（卡号不规范，或者卡号不满16位，或者收不到卡号）
				MD5info = merchantOrderNo
						 + ordercount
						+ responseCode + MD5key;//组建MD5info
				md5Value = md5.getMD5ofStr(MD5info);//加密
				message = "Payment Declined,Card is error!";//返回信息 卡号错误
				logger.info("*********************错误的卡号："+cardnum);//打印日志 +错误的卡号
				logger.info("*********************支付结果返回码***************************"+responseCode);//打印日志：支付结果返回码37 ：卡号不规范，或者卡号不满16位，或者收不到卡号
				return INPUT;//返回：xml配置的文件有相应的代码（对应的jsp）
			}
			merchant = merchantList.get(0);//获取商户资料的第一个值
			// 获取mD5key值
			if (merchant != null) {//如果商户不为null
				if (merchant.getMd5key() == null) {//如果商户的MD5k为null
					responseCode = 11;//返回码 11：秘钥不存在
					resultMd5 = BillNo + Currency + Amount + responseCode
							+ MD5key;//组建MD5info
					md5Value = md5.getMD5ofStr(resultMd5);//加密
					message = "Payment Declined";//返回信息 
					logger.info("*********************支付结果返回码***************************"+responseCode);//打印日志：支付结果返回码 11：返回码 11：秘钥不存在
					return INPUT;//返回：xml配置的文件有相应的代码（对应的jsp）
				} else {//不为null
					MD5key = merchant.getMd5key();//获取MD5k

				}
			}
			logger.info("*********************发现交易请求***************************"+this.getReturnURL());//打印日志：发现交易请求
			if (StringUtils.isNotBlank(Amount)) { //如果金额不为null
				ordercount = Amount.replace(",", "");//抛过来的支付金额去除逗号
				ordercount = ordercount.replace(" 0", "");//去除0
				tradeMoney = Double.valueOf(ordercount);//字符型支付金额转换为数值型
			} else {//金额为null
			logger.error("******************交易请求支付金额为空，请检查****************"+this.getReturnURL());//打印日志：交易金额为null+返回路径	
				responseCode = 26;//返回码26 支付信息错误
				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;//组建MD5k
				md5Value = md5.getMD5ofStr(resultMd5);//加密
				message = "Payment Declined";
				logger.info("*********************支付结果返回码***************************"+responseCode);//打印日志：支付结果返回码 26：支付信息错误
				return INPUT;//返回：xml配置的文件有相应的代码（对应的jsp）
			}
			if (MerNo == null||MerNo.equals("")){//如果商户号为null
			logger.error("*********************交易请求商户号为空，请检查**************"+this.getReturnURL());//打印日志：商户号为空
					responseCode = 33;//返回码：33 商户号为null
					resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;//组建MD5k
					md5Value = md5.getMD5ofStr(resultMd5);//加密
					message = "Payment Declined";
					logger.info("*********************支付结果返回码***************************"+responseCode);//打印日志：支付结果返回码 33：商户号为null
				return INPUT;//返回：xml配置的文件有相应的代码（对应的jsp）
			}
			if (StringUtils.isBlank(BillNo)) {//如果订单号为null
				logger.error("******************交易请求商户订单号为空，请检查****************"+this.getReturnURL());//打印日志：订单号为空
				responseCode = 26;//返回码26 支付信息错误
				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;//组建MD5k
				md5Value = md5.getMD5ofStr(resultMd5);//加密
				message = "Payment Declined";
				// 不存在该商户号
				logger.info("*********************支付结果返回码***************************"+responseCode);//打印日志：支付结果返回码 26：商户号为null
				return INPUT;//返回：xml配置的文件有相应的代码（对应的jsp）
			}
			currencyName = getStates().getCurrencyTypeByNo(//根据币种的类型获取货币的单位（名字）
					Integer.valueOf(tradeMoneyType));
			Long begin = System.currentTimeMillis();//系统当前时间
			
			// 获取交易流水号
			GetBatchNo ut = new GetBatchNo();
			rorderno = ut.getOrderinfo(MerNo);//根据商户号获取交易流水号
			// 校验交易流水号是否重复
			String hql = "select count(*) from international_tradeinfo t where t.ORDERNO='"
					+ rorderno + "'";//根据订单流水号组建hql语句
			int trlist = this.tradeManager.intBySql(hql);//查询交易流水号是否存在
			if (trlist > 0) {//如果查询结果大于0
				this.responseCode = 24;//返回码：24 同一流水号出现多次交易
				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;//组建MD5info
				md5Value = md5.getMD5ofStr(resultMd5);//加密
				message = "Payment Declined";
				logger.info("*********************支付结果返回码***************************"+responseCode);//打印日志：返回码：24 同一流水号出现多次交易
				return INPUT;//返回：xml配置的文件有相应的代码（对应的jsp）
			}
			
			if (Double.valueOf(Amount) > 100000L) {//L为long类型
				this.responseCode = 26;//返回码：24 ：支付信息错误
				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;//组建MD5info
				md5Value = md5.getMD5ofStr(resultMd5);//加密
				message = "Payment Declined";
				logger.info("*********************支付结果返回码***************************"+responseCode);//打印日志：返回码：26 支付信息错误
				return INPUT;//返回：xml配置的文件有相应的代码（对应的jsp）
			}
			
			tradetime = ut.getTime();// 获取交易时间
			if (MerNo != null) {//商户号不为null
				merchantnoValue = Long.valueOf(MerNo.trim());//接收过来的商户号赋值
			} else {//为null
				merchantnoValue = 0l;//赋值01
			}

			if (merchant.getIsopen().equals("0")) {//判断商户是否开通 0 ：未开通 1 ：开通
				responseCode = 15;//返回码15：商户未开通
				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;//组建MD5info
				md5Value = md5.getMD5ofStr(resultMd5);//加密
				message = "Payment Declined";
				logger.info("*********************支付结果返回码***************************"+responseCode);//打印日志：返回码：15商户未开通
				return INPUT;//返回：xml配置的文件有相应的代码（对应的jsp）

			}

			// 检验商户通道是否开通
			String sql2 = "select count(*) from international_merchantchannels t where t.merchantid='"
					+ merchant.getId() + "' and t.onoff='1'";//根据商户ID  开启状态查询
			int isDredge = this.tradeManager.intBySql(sql2);//查询结果
			if (isDredge == 0) {//如果等于0
				responseCode = 16;//通道未开通
				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;
				md5Value = md5.getMD5ofStr(resultMd5);//组建MD5info
				message = "Payment Declined";
				logger.info("*********************支付结果返回码***************************"+responseCode);//打印日志：返回码：16 通道未开通
				return INPUT;//返回：xml配置的文件有相应的代码（对应的jsp）
			}

			// 币种 //select t.*, t.rowid from international_moneykindname t where
			// t.id=(select f.moneykindno from international_merchantcurrency f
			// where f.merchanid='1' )
			List<InternationalMoneykindname> moneykinds = this.commonService
					.list(" from InternationalMoneykindname t "
							+ "where t.id in (select f.moneyKindNo from InternationalMerchantCurrency f "
							+ "where f.merchanId='" + merchant.getId() + "' )");//根据商户ID查询币种类型
			if (moneykinds.size() == 0 || moneykinds == null) {//如果币种类型为空

				responseCode = 12;//交易币种不存在
				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;//组建MD5info
				md5Value = md5.getMD5ofStr(resultMd5);//加密
				message = "Payment Declined";
				logger.info("*********************支付结果返回码***************************"+responseCode);//打印日志：返回码：12 币种不存在
				return INPUT;//返回：xml配置的文件有相应的代码（对应的jsp）
			}
			InternationalMoneykindname moneykind = moneykinds.get(0);//获取第一个币种类型------------迁易通单币种 -------------星支付多币种
			// 检验币种
			if (!(Currency.equals(moneykind.getMoneykindno() + ""))) {//

				responseCode = 12;//交易币种不存在
				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;//组建MD5info
				md5Value = md5.getMD5ofStr(resultMd5);//加密
				message = "Payment Declined";
				logger.info("*********************支付结果返回码***************************"+responseCode);//打印日志：返回码：12 币种不存在
				return INPUT;//返回：xml配置的文件有相应的代码（对应的jsp）
			}
			// select t.*,t.rowid from international_exchangerate t where
			// t.executetime in (select max(f.executetime) from
			// international_exchangerate f where f.moneykindno=1 group by
			// f.type)
			// 获取交易汇率
			List changerates = this.commonService
					.getByList("select t.id,t.rate,t.type from international_exchangerate t,international_moneykindname m  where t.moneykindno=m.id "
							+ "and m.moneykindno="
							+ this.Currency
							+ " and t.executetime<sysdate-1   and t.type='1' order by t.executetime desc  "); // 交易汇率t.type='1'  两表联查 根据币种类型查询  一句执行时间降序
			// 获取结算汇率
			List changeratesbalance = this.commonService
					.getByList("select t.id,t.rate,t.type from international_exchangerate t,international_moneykindname m  where t.moneykindno=m.id "
							+ "and m.moneykindno="
							+ this.Currency
							+ " and t.executetime<sysdate-1   and t.type='2' order by t.executetime desc  "); // 结算汇率t.type='2      两表联查 根据币种类型查询  一句执行时间降序

			if (changerates.size() < 1 && changeratesbalance.size() < 1) {//如果没有查询到
				responseCode = 12;//交易币种不存在
				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;//组建MD5info
				md5Value = md5.getMD5ofStr(resultMd5);//加密
				message = "Payment Declined";
				logger.info("*********************支付结果返回码***************************"+responseCode);//打印日志：支付结果返回码 12：交易币种不存在
				return INPUT;//返回：xml配置的文件有相应的代码（对应的jsp）
			}
			InternationalExchangerate changerate = new InternationalExchangerate();//创建汇率对象1交易汇率
			InternationalExchangerate settlementrate = new InternationalExchangerate();//创建汇率对象2结算汇率
			for (int i = 0; i < changerates.size(); i++) {//循环交易汇率
				Object[] tem = (Object[]) changerates.get(0);//获取交易汇率
				changerate.setId(Long.valueOf(tem[0].toString()));//赋值给汇率对象1（ID）
				changerate.setRate(Double.valueOf(tem[1].toString()));//赋值给汇率对象1（汇率值）
				changerate.setType("1");//赋值给汇率对象1（类型）
			}
			for (int i = 0; i < changeratesbalance.size(); i++) {//循环结算汇率
				Object[] tem = (Object[]) changeratesbalance.get(0);//获取结算汇率
				settlementrate.setId(Long.valueOf(tem[0].toString()));//赋值给汇率对象1（ID）
				settlementrate.setRate(Double.valueOf(tem[1].toString()));//赋值给汇率对象1（汇率值）
				settlementrate.setType("2");//赋值给汇率对象1（类型）
			}
			// 获取某个币种当前的汇率
			Double traderate = Double.valueOf(changerate.getRate());
			if (traderate != null) {//如果汇率不为null
				rmbmoney = traderate * tradeMoney;//汇率*支付金额=人民币支付金额
				
				BigDecimal b = new BigDecimal(rmbmoney);  
				rmbmoney = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();  

			} else {
				// 商户币种没设置
				responseCode = 12;//币种不存在哦
				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;
				md5Value = md5.getMD5ofStr(resultMd5);//组建MD5info
				message = "Payment Declined";
				logger.info("*********************支付结果返回码***************************"+responseCode);//打印日志 返回码12：交易币种不存在
				return INPUT;//返回：xml配置的文件有相应的代码（对应的jsp）
			}

			// 验证交易网址是否存在
			String url = request.getHeader("Referer");//通过request对象获取交易网址
			if(StringUtils.isBlank(url)){//如果交易网址不为null
				url=ReturnURL;//赋值
			}
			String a[] = url.split("/");//以/分割字符串转换成数组的形式
			if(a.length>2){
				tradeAdd = a[2];
			}else{
				tradeAdd=url;
			}
			System.out.println("==========================================="
					+ tradeAdd);
			String sqlCheckWeb = " select count(*) from International_Webchannels t where t.tradeWebsite like '%"
					+ tradeAdd
					+ "' and t.merchanid='"
					+ merchant.getId()
					+ "' ";
			int checkWeb = this.tradeManager.intBySql(sqlCheckWeb);
//			String sqlCheckWebReturn = " select count(*) from International_Webchannels t where t.website='"
//					+ this.ReturnURL
//					+ "' and t.merchanid='"
//					+ merchant.getId()
//					+ "' ";
//			int checkWebReturn = this.tradeManager.intBySql(sqlCheckWebReturn);
			String sqlstatus="select count(*) from International_Webchannels t where t.tradeWebsite like '%"
				+ tradeAdd
				+ "'and t.isblack='0' and t.merchanid='"
				+ merchant.getId()
				+ "'";
			int checkurlstatus=this.tradeManager.intBySql(sqlstatus);
			// 交易网址必须注册
			if (checkWeb == 0) {
				responseCode = 22;
				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;
				md5Value = md5.getMD5ofStr(resultMd5);
				message = "Payment Declined";
				logger.info("*********************支付结果返回码***************************"+responseCode);
				return INPUT;
			}
			// 返回网址必须注册
//			if (checkWebReturn == 0) {
//				responseCode = 14;
//				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;
//				md5Value = md5.getMD5ofStr(resultMd5);
//				message = "Payment Declined";
//				logger.info("*********************支付结果返回码***************************"+responseCode);
//				return INPUT;
//			}
			// 交易网址必须是没有禁止交易的
			if (checkurlstatus == 0) {
				responseCode = 32;//交易网址禁止交易
				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;
				md5Value = md5.getMD5ofStr(resultMd5);
				message = "Payment Declined";
				logger.info("*********************支付结果返回码***************************"+responseCode);
				return INPUT;
			}
			// 获取通道卡种
			logger.info("********************************开始获取商户通道卡种*********************************");
			String cardlistsql = "select cct.shortName from InternationalMerchantChannels mc," +
					"InternationalMerchant m,InternationalMerCreditCard mcc,InternationalCreditCardType cct " +
					"where m.id=mc.merchantId " +
					"and mcc.merChannelId=mc.id " +
					"and cct.id=mcc.creditCardId " +
					"and mcc.onoff=1 and mc.onoff=1 " +
					"and m.merno="+MerNo;
			List<String> cardlist = this.commonService.list(cardlistsql);
			//新增直连所需
			boolean cType=false;
			if(StringUtils.isNotBlank(newcardtype)){
				for (String c : cardlist) {
					if(c.equals(newcardtype)){
						cType=true;
					}
				}
			}
			if(cType=false){
				responseCode = 34;//商户没有开通和输入卡相同的通道
				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;
				md5Value = md5.getMD5ofStr(resultMd5);
				message = "Payment Declined";
				logger.info("*********************支付结果返回码***************************"+responseCode);
				return INPUT;
			}
			
			logger.info("***************************获取商户通道卡种结束***************************");
			logger.info("***************************开始获取商户交易金额*******************************");
			
			// 获取交易金额
			md5src = merchantnoValue + BillNo + this.Currency 
					+ Amount + Language + ReturnURL + MD5key;
			logger.info("***************************获取商户交易金额结束*********************************");
			logger.info("***************************开始验证商户交易签名**********************************");
			logger.info("签名数据:"+md5src);
			md5src = md5.getMD5ofStr(md5src);
			logger.info("**********************传过来的签名："+MD5info);
			logger.info("**********************计算的签名："+md5src);
			// 信息被篡改
			// System.out.println("========"+merchantnoValue +"========"+
			// BillNo+"========" + moneykind.getMoneykindno()+"========" +
			// Amount+"========" + Language+"========" + ReturnURL+"========" +
			// MD5key+"========" +"========5555============"+md5src);
			// System.out.println("===============666666============="+MD5info);
			if (!(md5src.equals(MD5info))) 
			{
				responseCode = 13;
				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;
				md5Value = md5.getMD5ofStr(resultMd5);
				message = "Payment Declined";
				logger.info("**********************商户交易签名错误，请检查*********************************");
				return INPUT;
			}
			logger.info("**********************商户交易签名验证结束*********************************");
			logger.info("**********************开始插入商户交易数据**********************************");
			tradeMoney = (double) (Math.round((double) tradeMoney * 100) / 100.00);
			rmbmoney = (double) (Math.round((double) rmbmoney * 100) / 100.00);
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
			trade.setCsid(csid);
			this.commonService.saveOrUpdate(trade);
			responseCode = 0;
			logger.info("*****************商户交易数据插入结束********************");
			logger.info("*****************开始跳转到支付页面********************");
			return SUCCESS;
		} catch (Exception e){
			logger.error("系统接受交易请求出现未知错误：");
			logger.error(e);
			e.printStackTrace();
			responseCode = 35;
			resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;
			md5Value = md5.getMD5ofStr(resultMd5);
			message = "Payment Declined";
			logger.info("*********************支付结果返回码***************************"+responseCode);
			return INPUT;
		}
	}

	public TradeManager getTradeManager() {
		return tradeManager;
	}

	public void setTradeManager(TradeManager tradeManager) {
		this.tradeManager = tradeManager;
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

	public String getOrdercount() {
		return ordercount;
	}

	public void setOrdercount(String ordercount) {
		this.ordercount = ordercount;
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

	public String getMerchantOrderNo() {
		return merchantOrderNo;
	}

	public void setMerchantOrderNo(String merchantOrderNo) {
		this.merchantOrderNo = merchantOrderNo;
	}

	public String getCurrency() {
		return Currency;
	}

	public void setCurrency(String currency) {
		Currency = currency;
	}

	public String getLanguage() {
		return Language;
	}

	public void setLanguage(String language) {
		Language = language;
	}

	public String getReturnURL() {
		return ReturnURL;
	}

	public void setReturnURL(String returnURL) {
		ReturnURL = returnURL;
	}

	public String getMd5src() {
		return md5src;
	}

	public void setMd5src(String md5src) {
		this.md5src = md5src;
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
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

	public String getMD5key() {
		return MD5key;
	}

	public void setMD5key(String md5key) {
		MD5key = md5key;
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

	public String getResultMd5() {
		return resultMd5;
	}

	public void setResultMd5(String resultMd5) {
		this.resultMd5 = resultMd5;
	}

	public String getTradeMoneyType() {
		return tradeMoneyType;
	}

	public void setTradeMoneyType(String tradeMoneyType) {
		this.tradeMoneyType = tradeMoneyType;
	}

	public String getMd5Value() {
		return md5Value;
	}

	public void setMd5Value(String md5Value) {
		this.md5Value = md5Value;
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

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getRedirectPath() {
		return redirectPath;
	}

	public void setRedirectPath(String redirectPath) {
		this.redirectPath = redirectPath;
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

	public String getNewcardtype() {
		return newcardtype;
	}

	public void setNewcardtype(String newcardtype) {
		this.newcardtype = newcardtype;
	}

	public String getTradeInfo() {
		return TradeInfo;
	}

	public void setTradeInfo(String tradeInfo) {
		TradeInfo = tradeInfo;
	}

	public String getAddIp() {
		return addIp;
	}

	public void setAddIp(String addIp) {
		this.addIp = addIp;
	}

	public String getSfeVersion() {
		return sfeVersion;
	}

	public void setSfeVersion(String sfeVersion) {
		this.sfeVersion = sfeVersion;
	}

	public String getCsid() {
		return csid;
	}

	public void setCsid(String csid) {
		this.csid = csid;
	}
	
	public static void main(String[] args) {
		DirectPayAction dd = new DirectPayAction();
		dd.payRequest();
	}
}
	