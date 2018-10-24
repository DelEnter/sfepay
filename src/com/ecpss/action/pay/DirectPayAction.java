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
	private static Hashtable<String, Long> orderCache = new Hashtable<String, Long>();//hashtable���Ͻ�����
	private static Thread orderExpiredThread;
	@Autowired
	@Qualifier("tradeManager")
	private TradeManager tradeManager;
	// �׹������̻�����������Ϣ
	private String csid;
	private String MerNo; // �̻�
	private String rorderno; // ������ˮ��
	private Date tradetime; // ����ʱ��
	private Long merchantnoValue;
	private boolean checkMerch;// �����̻��Ƿ����
	private long isDredge;// �����̻�ͨ���Ƿ�ͨ
	private String Amount; // ֧�����
	private Double tradeMoney;// ֧�����
	private double rmbmoney = 0; // ����ҽ��׽��
	private Double tradeAmount; // ���׽���ң�
	private String channels; // ʹ�õ��̻�ͨ��F
	private String BillNo; // �������
	private String Currency; // ����
	private String currencyName;//�������֣���λ��
	private String Language = "en"; // ֧������
	private String ReturnURL; // ���ص�ַ
	private String md5src; // ���ؼ���md5
	private String cookie; // cookies
	private String cooknumber;
	private String MD5info; //��������MD5��������
	// ��������Ҫ�Ĳ���
	private int responseCode;// ��Ӧ���ţ�
	private String MD5key; // MD5keyֵ
	private String tradeAdd;//������ַ
	private Long moneyType;//�������
	private String message;//��Ϣ
	// md5 ����У��
	private String tradeMoneyType;//�������
	private String ordercount; // ֧�����(ת��Ϊ֧�����)
	private String md5Value; // ֧�����ض��̻���Ϣ���м���
	private String merchantOrderNo;//�̻�������
	private String resultMd5;
	// Q ֧������.У��˳��1.�����̻��Ƿ���� ��2.�����̻�ͨ���Ƿ�ͨ ;3.������ַ����ע�� 4.��Ϣ�Ƿ񱻴۸ġ�
	// �ֿ�����Ϣ������ billingAddress
	private String firstname;//�ֿ���������һ����
	private String lastname;//�ֿ��������������
	private String cardbank;//������
	private String email;//����
	private String phone;//�绰
	private String zipcode;//�ʱ�
	private String address;//��ַ
	private String city;//����
	private String state;//����
	private String country;//����
	private String products;//��Ʒ��Ϣ
	// shippingAddress�˵���ַ
	private String shippingFirstName;//�ֿ���������һ����
	private String shippingLastName;//�ֿ��������������
	private String shippingEmail;//����
	private String shippingPhone;//�绰
	private String shippingZipcode;//�ʱ�
	private String shippingAddress;//��ַ
	private String shippingCity;//����
	private String shippingSstate;//����
	private String shippingCountry;//����
	private String redirectPath;//
	private String cardnum;//����
	private String cvv2;//��֤��ȫ��
	private String year; // ��
	private String month; // ��
	private String newcardtype;//������
	private String TradeInfo;//������Ϣ
	private String addIp;//
	private String sfeVersion;//�ӿڰ汾����
	Logger logger = Logger.getLogger(DirectPayAction.class.getName());//��ӡ��־

	public String payRequest(){
		logger.error("*********************����ֱ��ͨ��**************");//����ֱ��ͨ��
		MerNo = "3734";//�̻��Ÿ�ֵ3734
		
		logger.info("*********************���ֽ�������"+MerNo+"***************************"+this.getReturnURL());//���ֽ�������
		MD5 md5 = new MD5();//md5����
		try {
			HttpServletRequest request = ServletActionContext.getRequest();//��ȡrequest����
			// HttpSession session = request.getSession();
			merchantOrderNo = BillNo;//�̻�������
			tradeMoneyType = Currency;//����
			// �����̻��Ƿ����
			logger.error("*********************�����̻���**************"+MerNo);
			if(StringUtils.isBlank(MerNo)){//�ж��̻����Ƿ�Ϊ��
				MerNo="";
			}
			String sql1 = " from InternationalMerchant t where t.merno='"
								+ MerNo.trim() + "'";//sql�������̻��Ų�ѯ
						
				InternationalMerchant merchant = new InternationalMerchant();// ���ջ�ȡ�̻�������
						List<InternationalMerchant> merchantList = this.commonService.list(sql1);
			if (merchantList == null || merchantList.size() == 0) {//���չ������̻�������Ϊnull���߲�����
					logger.error("******************���������̻��Ŵ�������****************"+this.getReturnURL());//�̻��Ŵ���+����·��
						responseCode = 10;//����10���̻�δע��
						resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;//�齨resultMd5��md5info��
						md5Value = md5.getMD5ofStr(resultMd5);//����resultMd5��md5info��
						message = "Payment Declined";//
							// �����ڸ��̻���
						return INPUT;//xml���õ��ļ�����Ӧ�Ĵ��루��Ӧ��jsp��
						}
			if(StringUtils.isNotBlank(sfeVersion)){//����ӿڰ汾����null  
				String hqlv="from InternationalPayVersion where merchantId is null and version='"+sfeVersion+"'";//sql�����ݴ������İ汾�ź� �̻���ID��ѯ   ��ѯ���ǽ��ð汾
				String hqlv2="from InternationalPayVersion pay,InternationalMerchant me where me.id=pay.merchantId and me.merno='"+MerNo+"' and pay.version='"+sfeVersion+"'";//��������� 1.�̻���Ϣ���2.�ӿڰ汾������̻��Ű汾�Ų�ѯ
				List allv = this.commonService.list(hqlv);//hqlv�Ĳ�ѯ���
				if(allv==null||allv.size()==0){//���û�в�ѯ��
					List onev = this.commonService.list(hqlv2);//û�в�ѯ�� ����hqlv2��ѯ
					if(onev!=null&&onev.size()>0){//�ж�hqlv2��û�в�ѯ��
						responseCode = 36;//��ѯ������״̬��36��֧���汾����
						resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;//�齨md5info
						md5Value = md5.getMD5ofStr(resultMd5);//����md5info
						message = "Payment Declined";
						logger.error("******************֧���汾��ֹʹ��****************"+this.sfeVersion);//��ӡ��־֧���汾��ֹʹ��+�汾��
						return INPUT;//���أ�xml���õ��ļ�����Ӧ�Ĵ��루��Ӧ��jsp��
					}
				}else{
					responseCode = 36;//��ѯ������״̬��36��֧���汾����
					resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;
					md5Value = md5.getMD5ofStr(resultMd5);//�齨md5info
					message = "Payment Declined";
					logger.error("******************֧���汾��ֹʹ��****************"+this.sfeVersion);//��ӡ��־֧���汾��ֹʹ��+�汾��
					return INPUT;//���أ�xml���õ��ļ�����Ӧ�Ĵ��루��Ӧ��jsp��
				}
			}else{//�ӿڰ汾��null
				responseCode = 36;//����״̬�룺36 ��֧���汾����
				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;//�齨md5info
				md5Value = md5.getMD5ofStr(resultMd5);//����md5info
				message = "Payment Declined";
				logger.error("******************֧���汾��ֹʹ��****************"+this.sfeVersion);//��ӡ��־֧���汾��ֹʹ��+�汾��
				return INPUT;//���أ�xml���õ��ļ�����Ӧ�Ĵ��루��Ӧ��jsp��
			}
			logger.info("*****************��ӡ������Ϣ******************");//��ӡ��־��ӡ������Ϣ
			logger.info("MerNo:"+MerNo);//�̻���
			logger.info("newcardtype:"+newcardtype);//������
			logger.info("cardnum:"+cardnum);//����
			logger.info("cardbank:"+cardbank);//������
			logger.info("BillNo:"+BillNo);//������
			logger.info("Amount:"+Amount);//���
			logger.info("Currency:"+Currency);//����
			logger.info("Language:"+Language);//����
			logger.info("MD5info:"+MD5info);//���ܹ���md5info
			logger.info("ReturnURL:"+ReturnURL);//����·��
			logger.info("shippingFirstName:"+shippingFirstName);//�ͻ��˵����ֵĵ�һ����
			logger.info("shippingLastName:"+shippingLastName);//�ͻ��˵����ĺ������
			logger.info("shippingEmail:"+shippingEmail);//����
			logger.info("shippingPhone:"+shippingPhone);//�绰
			logger.info("shippingZipcode:"+shippingZipcode);//�ʱ�
			logger.info("shippingAddress:"+shippingAddress);//��ַ
			logger.info("shippingCity:"+shippingCity);//����
			logger.info("shippingSstate:"+shippingSstate);//����
			logger.info("shippingCountry:"+shippingCountry);//����
			logger.info("products:"+products);//��Ʒ��Ϣ
			logger.info("csid:"+csid);//
			logger.info("*****************��ӡ������Ϣ����******************");//��ӡ��־��ӡ������Ϣ����
			
			BASE64Decoder base64=new BASE64Decoder();//���ܱ��빤��
			if(StringUtils.isNotBlank(csid)){//���csidΪ��Ϊnull
				csid=new String((base64.decodeBuffer(csid)));//����
				csid=URLDecoder.decode(csid);//����
			}
			logger.info("*****************��ӡcsid��Ϣ******************��"+csid);//��ӡcsid��Ϣ
			if (orderExpiredThread == null)//����߳�Ϊ��
			{
				orderExpiredThread = new Thread(){//����һ���߳�
					public void run() {//��дrun����
						while (true) {//ѭ��
							try {
								Thread.sleep(60 * 1000);//����ʱ��
							} catch (InterruptedException e) {   
								e.printStackTrace();
							}
							//������ڵĻ���
							int expiredMin = 3;//����һ������3
							Iterator<Entry<String, Long>> it = orderCache.entrySet().iterator();//����������
							List<String> expiredKeys = new ArrayList<String>();//����һ��list����
							while (it.hasNext()) {//��������õ�ֵ
								Entry<String, Long> entry = it.next();//��ȡ���Ԫ��
								long addTimeMill = entry.getValue();//��ȡ���Ԫ�ص�ֵ
								if ((System.currentTimeMillis() - addTimeMill) >= expiredMin * 60 * 1000)//���ϵͳ��ǰʱ��-���ʱ��=3����
								{
									expiredKeys.add(entry.getKey());//�����Ԫ�ص�key��ӵ���������
								}
							}
							for (String expiredKey : expiredKeys) {//ѭ��
								orderCache.remove(expiredKey);//�Ƴ���ʱ�Ķ���
								logger.info("�Ƴ���ʱ�Ļ��涩����" + expiredKey);//��ӡ��־
							}
							logger.info("�����еĶ�������" + orderCache.size());//��ӡ��־������Ķ�����
						}
					}
				};
				orderExpiredThread.start();//�����߳�
			}
			
			//���涩��
			String orderInfo = cardnum + Amount + addIp + MerNo;//������Ϣ������+���+�����+IP
			if (orderCache.get(orderInfo) != null)//�����Ϊnull
			{
				logger.info("�����д��ڶ��� " + orderInfo + "����ʱ�䣺" + orderCache.get(orderInfo));//��ӡ��־������+����ʱ��
				responseCode = 5;//������ 5��ͬһIP������ν���
				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;//�齨md5info
				md5Value = md5.getMD5ofStr(resultMd5);//����md5info
				message = "Payment Declined";
				logger.info("*********************֧�����������***************************"+responseCode);//��ӡ��־ �����룺5��ͬһIP������ν���
				return INPUT;//���أ�xml���õ��ļ�����Ӧ�Ĵ��루��Ӧ��jsp��
			}
			else//��null
			{
				logger.info("�¶��� ���뻺��" + orderInfo);//��ӡ��־���¶������뻺��
				orderCache.put(orderInfo, System.currentTimeMillis());//hashtable����һ������������+ϵͳ��ǰʱ��
			}
/*			ZH-CN%26%40ZH-CN%26%40MOZILLA%26%40%26%40NETSCAPE%26%405.0+%28WINDOWS+NT+6.1%3B+WOW64%29+APPLEWEBKIT%2F537.36+%28KHTML%2C+LIKE+GECKO%29+CHROME%2F50.0.2661.102+SAFARI%2F537.36%26%40TRUE%26%40C9AD750F48C6A7848E4A0E531A1B5C8F1506671385389%26%40E98D6DE1%26%40WIN32%26%40MOZILLA%2F5.0+%28WINDOWS+NT+6.1%3B+WOW64%29+APPLEWEBKIT%2F537.36+%28KHTML%2C+LIKE+GECKO%29+CHROME%2F50.0.2661.102+SAFARI%2F537.36%26%40%26%40UNDEFINED%26%401440X900%26%4024%26%408%26%40WWW.TRADE2015.NET%26%405B1888C5DA17BCC29FBCF10562F95A231506671385390
*/			
			
			if(StringUtils.isNotBlank(cardnum)){//������Ų���null
				cardnum=new String((base64.decodeBuffer(cardnum)));//����
				if(cardnum.length()<16){//������ų���С��16λ
					this.responseCode = 37;//������ 37�����Ų��淶�����Ų��淶�����߿��Ų���16λ�������ղ������ţ�
					MD5info = merchantOrderNo
							 + ordercount
							+ responseCode + MD5key;//�齨MD5info
					md5Value = md5.getMD5ofStr(MD5info);//����
					message = "Payment Declined,Card is error!";//������Ϣ ���Ŵ���
					logger.info("*********************����Ŀ��ţ�"+cardnum);//��ӡ��־ +����Ŀ���
					logger.info("*********************֧�����������***************************"+responseCode);//��ӡ��־��֧�����������37 �����Ų��淶�����߿��Ų���16λ�������ղ�������
					return INPUT;//���أ�xml���õ��ļ�����Ӧ�Ĵ��루��Ӧ��jsp��
				}
			}else{//���������null
				this.responseCode = 37;//������ 37���ղ������ţ����Ų��淶�����߿��Ų���16λ�������ղ������ţ�
				MD5info = merchantOrderNo
						 + ordercount
						+ responseCode + MD5key;//�齨MD5info
				md5Value = md5.getMD5ofStr(MD5info);//����
				message = "Payment Declined,Card is error!";//������Ϣ ���Ŵ���
				logger.info("*********************����Ŀ��ţ�"+cardnum);//��ӡ��־ +����Ŀ���
				logger.info("*********************֧�����������***************************"+responseCode);//��ӡ��־��֧�����������37 �����Ų��淶�����߿��Ų���16λ�������ղ�������
				return INPUT;//���أ�xml���õ��ļ�����Ӧ�Ĵ��루��Ӧ��jsp��
			}
			merchant = merchantList.get(0);//��ȡ�̻����ϵĵ�һ��ֵ
			// ��ȡmD5keyֵ
			if (merchant != null) {//����̻���Ϊnull
				if (merchant.getMd5key() == null) {//����̻���MD5kΪnull
					responseCode = 11;//������ 11����Կ������
					resultMd5 = BillNo + Currency + Amount + responseCode
							+ MD5key;//�齨MD5info
					md5Value = md5.getMD5ofStr(resultMd5);//����
					message = "Payment Declined";//������Ϣ 
					logger.info("*********************֧�����������***************************"+responseCode);//��ӡ��־��֧����������� 11�������� 11����Կ������
					return INPUT;//���أ�xml���õ��ļ�����Ӧ�Ĵ��루��Ӧ��jsp��
				} else {//��Ϊnull
					MD5key = merchant.getMd5key();//��ȡMD5k

				}
			}
			logger.info("*********************���ֽ�������***************************"+this.getReturnURL());//��ӡ��־�����ֽ�������
			if (StringUtils.isNotBlank(Amount)) { //�����Ϊnull
				ordercount = Amount.replace(",", "");//�׹�����֧�����ȥ������
				ordercount = ordercount.replace(" 0", "");//ȥ��0
				tradeMoney = Double.valueOf(ordercount);//�ַ���֧�����ת��Ϊ��ֵ��
			} else {//���Ϊnull
			logger.error("******************��������֧�����Ϊ�գ�����****************"+this.getReturnURL());//��ӡ��־�����׽��Ϊnull+����·��	
				responseCode = 26;//������26 ֧����Ϣ����
				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;//�齨MD5k
				md5Value = md5.getMD5ofStr(resultMd5);//����
				message = "Payment Declined";
				logger.info("*********************֧�����������***************************"+responseCode);//��ӡ��־��֧����������� 26��֧����Ϣ����
				return INPUT;//���أ�xml���õ��ļ�����Ӧ�Ĵ��루��Ӧ��jsp��
			}
			if (MerNo == null||MerNo.equals("")){//����̻���Ϊnull
			logger.error("*********************���������̻���Ϊ�գ�����**************"+this.getReturnURL());//��ӡ��־���̻���Ϊ��
					responseCode = 33;//�����룺33 �̻���Ϊnull
					resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;//�齨MD5k
					md5Value = md5.getMD5ofStr(resultMd5);//����
					message = "Payment Declined";
					logger.info("*********************֧�����������***************************"+responseCode);//��ӡ��־��֧����������� 33���̻���Ϊnull
				return INPUT;//���أ�xml���õ��ļ�����Ӧ�Ĵ��루��Ӧ��jsp��
			}
			if (StringUtils.isBlank(BillNo)) {//���������Ϊnull
				logger.error("******************���������̻�������Ϊ�գ�����****************"+this.getReturnURL());//��ӡ��־��������Ϊ��
				responseCode = 26;//������26 ֧����Ϣ����
				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;//�齨MD5k
				md5Value = md5.getMD5ofStr(resultMd5);//����
				message = "Payment Declined";
				// �����ڸ��̻���
				logger.info("*********************֧�����������***************************"+responseCode);//��ӡ��־��֧����������� 26���̻���Ϊnull
				return INPUT;//���أ�xml���õ��ļ�����Ӧ�Ĵ��루��Ӧ��jsp��
			}
			currencyName = getStates().getCurrencyTypeByNo(//���ݱ��ֵ����ͻ�ȡ���ҵĵ�λ�����֣�
					Integer.valueOf(tradeMoneyType));
			Long begin = System.currentTimeMillis();//ϵͳ��ǰʱ��
			
			// ��ȡ������ˮ��
			GetBatchNo ut = new GetBatchNo();
			rorderno = ut.getOrderinfo(MerNo);//�����̻��Ż�ȡ������ˮ��
			// У�齻����ˮ���Ƿ��ظ�
			String hql = "select count(*) from international_tradeinfo t where t.ORDERNO='"
					+ rorderno + "'";//���ݶ�����ˮ���齨hql���
			int trlist = this.tradeManager.intBySql(hql);//��ѯ������ˮ���Ƿ����
			if (trlist > 0) {//�����ѯ�������0
				this.responseCode = 24;//�����룺24 ͬһ��ˮ�ų��ֶ�ν���
				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;//�齨MD5info
				md5Value = md5.getMD5ofStr(resultMd5);//����
				message = "Payment Declined";
				logger.info("*********************֧�����������***************************"+responseCode);//��ӡ��־�������룺24 ͬһ��ˮ�ų��ֶ�ν���
				return INPUT;//���أ�xml���õ��ļ�����Ӧ�Ĵ��루��Ӧ��jsp��
			}
			
			if (Double.valueOf(Amount) > 100000L) {//LΪlong����
				this.responseCode = 26;//�����룺24 ��֧����Ϣ����
				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;//�齨MD5info
				md5Value = md5.getMD5ofStr(resultMd5);//����
				message = "Payment Declined";
				logger.info("*********************֧�����������***************************"+responseCode);//��ӡ��־�������룺26 ֧����Ϣ����
				return INPUT;//���أ�xml���õ��ļ�����Ӧ�Ĵ��루��Ӧ��jsp��
			}
			
			tradetime = ut.getTime();// ��ȡ����ʱ��
			if (MerNo != null) {//�̻��Ų�Ϊnull
				merchantnoValue = Long.valueOf(MerNo.trim());//���չ������̻��Ÿ�ֵ
			} else {//Ϊnull
				merchantnoValue = 0l;//��ֵ01
			}

			if (merchant.getIsopen().equals("0")) {//�ж��̻��Ƿ�ͨ 0 ��δ��ͨ 1 ����ͨ
				responseCode = 15;//������15���̻�δ��ͨ
				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;//�齨MD5info
				md5Value = md5.getMD5ofStr(resultMd5);//����
				message = "Payment Declined";
				logger.info("*********************֧�����������***************************"+responseCode);//��ӡ��־�������룺15�̻�δ��ͨ
				return INPUT;//���أ�xml���õ��ļ�����Ӧ�Ĵ��루��Ӧ��jsp��

			}

			// �����̻�ͨ���Ƿ�ͨ
			String sql2 = "select count(*) from international_merchantchannels t where t.merchantid='"
					+ merchant.getId() + "' and t.onoff='1'";//�����̻�ID  ����״̬��ѯ
			int isDredge = this.tradeManager.intBySql(sql2);//��ѯ���
			if (isDredge == 0) {//�������0
				responseCode = 16;//ͨ��δ��ͨ
				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;
				md5Value = md5.getMD5ofStr(resultMd5);//�齨MD5info
				message = "Payment Declined";
				logger.info("*********************֧�����������***************************"+responseCode);//��ӡ��־�������룺16 ͨ��δ��ͨ
				return INPUT;//���أ�xml���õ��ļ�����Ӧ�Ĵ��루��Ӧ��jsp��
			}

			// ���� //select t.*, t.rowid from international_moneykindname t where
			// t.id=(select f.moneykindno from international_merchantcurrency f
			// where f.merchanid='1' )
			List<InternationalMoneykindname> moneykinds = this.commonService
					.list(" from InternationalMoneykindname t "
							+ "where t.id in (select f.moneyKindNo from InternationalMerchantCurrency f "
							+ "where f.merchanId='" + merchant.getId() + "' )");//�����̻�ID��ѯ��������
			if (moneykinds.size() == 0 || moneykinds == null) {//�����������Ϊ��

				responseCode = 12;//���ױ��ֲ�����
				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;//�齨MD5info
				md5Value = md5.getMD5ofStr(resultMd5);//����
				message = "Payment Declined";
				logger.info("*********************֧�����������***************************"+responseCode);//��ӡ��־�������룺12 ���ֲ�����
				return INPUT;//���أ�xml���õ��ļ�����Ӧ�Ĵ��루��Ӧ��jsp��
			}
			InternationalMoneykindname moneykind = moneykinds.get(0);//��ȡ��һ����������------------Ǩ��ͨ������ -------------��֧�������
			// �������
			if (!(Currency.equals(moneykind.getMoneykindno() + ""))) {//

				responseCode = 12;//���ױ��ֲ�����
				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;//�齨MD5info
				md5Value = md5.getMD5ofStr(resultMd5);//����
				message = "Payment Declined";
				logger.info("*********************֧�����������***************************"+responseCode);//��ӡ��־�������룺12 ���ֲ�����
				return INPUT;//���أ�xml���õ��ļ�����Ӧ�Ĵ��루��Ӧ��jsp��
			}
			// select t.*,t.rowid from international_exchangerate t where
			// t.executetime in (select max(f.executetime) from
			// international_exchangerate f where f.moneykindno=1 group by
			// f.type)
			// ��ȡ���׻���
			List changerates = this.commonService
					.getByList("select t.id,t.rate,t.type from international_exchangerate t,international_moneykindname m  where t.moneykindno=m.id "
							+ "and m.moneykindno="
							+ this.Currency
							+ " and t.executetime<sysdate-1   and t.type='1' order by t.executetime desc  "); // ���׻���t.type='1'  �������� ���ݱ������Ͳ�ѯ  һ��ִ��ʱ�併��
			// ��ȡ�������
			List changeratesbalance = this.commonService
					.getByList("select t.id,t.rate,t.type from international_exchangerate t,international_moneykindname m  where t.moneykindno=m.id "
							+ "and m.moneykindno="
							+ this.Currency
							+ " and t.executetime<sysdate-1   and t.type='2' order by t.executetime desc  "); // �������t.type='2      �������� ���ݱ������Ͳ�ѯ  һ��ִ��ʱ�併��

			if (changerates.size() < 1 && changeratesbalance.size() < 1) {//���û�в�ѯ��
				responseCode = 12;//���ױ��ֲ�����
				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;//�齨MD5info
				md5Value = md5.getMD5ofStr(resultMd5);//����
				message = "Payment Declined";
				logger.info("*********************֧�����������***************************"+responseCode);//��ӡ��־��֧����������� 12�����ױ��ֲ�����
				return INPUT;//���أ�xml���õ��ļ�����Ӧ�Ĵ��루��Ӧ��jsp��
			}
			InternationalExchangerate changerate = new InternationalExchangerate();//�������ʶ���1���׻���
			InternationalExchangerate settlementrate = new InternationalExchangerate();//�������ʶ���2�������
			for (int i = 0; i < changerates.size(); i++) {//ѭ�����׻���
				Object[] tem = (Object[]) changerates.get(0);//��ȡ���׻���
				changerate.setId(Long.valueOf(tem[0].toString()));//��ֵ�����ʶ���1��ID��
				changerate.setRate(Double.valueOf(tem[1].toString()));//��ֵ�����ʶ���1������ֵ��
				changerate.setType("1");//��ֵ�����ʶ���1�����ͣ�
			}
			for (int i = 0; i < changeratesbalance.size(); i++) {//ѭ���������
				Object[] tem = (Object[]) changeratesbalance.get(0);//��ȡ�������
				settlementrate.setId(Long.valueOf(tem[0].toString()));//��ֵ�����ʶ���1��ID��
				settlementrate.setRate(Double.valueOf(tem[1].toString()));//��ֵ�����ʶ���1������ֵ��
				settlementrate.setType("2");//��ֵ�����ʶ���1�����ͣ�
			}
			// ��ȡĳ�����ֵ�ǰ�Ļ���
			Double traderate = Double.valueOf(changerate.getRate());
			if (traderate != null) {//������ʲ�Ϊnull
				rmbmoney = traderate * tradeMoney;//����*֧�����=�����֧�����
				
				BigDecimal b = new BigDecimal(rmbmoney);  
				rmbmoney = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();  

			} else {
				// �̻�����û����
				responseCode = 12;//���ֲ�����Ŷ
				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;
				md5Value = md5.getMD5ofStr(resultMd5);//�齨MD5info
				message = "Payment Declined";
				logger.info("*********************֧�����������***************************"+responseCode);//��ӡ��־ ������12�����ױ��ֲ�����
				return INPUT;//���أ�xml���õ��ļ�����Ӧ�Ĵ��루��Ӧ��jsp��
			}

			// ��֤������ַ�Ƿ����
			String url = request.getHeader("Referer");//ͨ��request�����ȡ������ַ
			if(StringUtils.isBlank(url)){//���������ַ��Ϊnull
				url=ReturnURL;//��ֵ
			}
			String a[] = url.split("/");//��/�ָ��ַ���ת�����������ʽ
			if(a.length>2){//���ȴ���2��������������
				tradeAdd = a[2];//��ȡ�����������󣨽�����ַ��
			}else{
				tradeAdd=url;//������ַ
			}
			System.out.println("==========================================="
					+ tradeAdd);//��������ַ
			String sqlCheckWeb = " select count(*) from International_Webchannels t where t.tradeWebsite like '%"
					+ tradeAdd
					+ "' and t.merchanid='"
					+ merchant.getId()
					+ "' ";//�����̻���ID�ͽ�����ַȥ��ѯ��ģ����ѯ��
			int checkWeb = this.tradeManager.intBySql(sqlCheckWeb);//��ѯ���1
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
				+ "'";//�����̻�ID������ַ�����״̬ȥ��Ѱ
			int checkurlstatus=this.tradeManager.intBySql(sqlstatus);//��ѯ���2
			// ������ַ����ע��
			if (checkWeb == 0) {//��ѯ���1=0��û�в鵽��
				responseCode = 22;//������ַ����ȷ
				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;
				md5Value = md5.getMD5ofStr(resultMd5);//�齨MD5info
				message = "Payment Declined";//����
				logger.info("*********************֧�����������***************************"+responseCode);//��ӡ��־�������� 22�������ַ����ȷ
				return INPUT;//���أ�xml���õ��ļ�����Ӧ�Ĵ��루��Ӧ��jsp��
			}
			// ������ַ����ע��
//			if (checkWebReturn == 0) {
//				responseCode = 14;
//				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;
//				md5Value = md5.getMD5ofStr(resultMd5);
//				message = "Payment Declined";
//				logger.info("*********************֧�����������***************************"+responseCode);
//				return INPUT;
//			}
			// ������ַ������û�н�ֹ���׵�
			if (checkurlstatus == 0) {//��ѯ���2=0��û�в鵽��
				responseCode = 32;//������ַ��ֹ����
				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;//�齨MD5info
				md5Value = md5.getMD5ofStr(resultMd5);//����
				message = "Payment Declined";
				logger.info("*********************֧�����������***************************"+responseCode);//��ӡ��־�������� 32��������ַ��ֹ����
				return INPUT;//���أ�xml���õ��ļ�����Ӧ�Ĵ��루��Ӧ��jsp��
			}
			// ��ȡͨ������
			logger.info("********************************��ʼ��ȡ�̻�ͨ������*********************************");//��ӡ��־����ʼ��ȡ�̻�ͨ������
			String cardlistsql = "select cct.shortName from InternationalMerchantChannels mc," +
					"InternationalMerchant m,InternationalMerCreditCard mcc,InternationalCreditCardType cct " +
					"where m.id=mc.merchantId " +
					"and mcc.merChannelId=mc.id " +
					"and cct.id=mcc.creditCardId " +
					"and mcc.onoff=1 and mc.onoff=1 " +
					"and m.merno="+MerNo;//�����̻����������� 
			List<String> cardlist = this.commonService.list(cardlistsql);//��ѯ���
			//����ֱ������
			boolean cType=false;//����һ��������Ϊfalse
			if(StringUtils.isNotBlank(newcardtype)){//�������Ͳ���null
				for (String c : cardlist) {//ѭ����ѯ���
					if(c.equals(newcardtype)){//��ѯ������п�������
						cType=true;//����������Ϊtrue
					}
				}
			}
			if(cType=false){//�����������Ϊfalse
				responseCode = 34;//�̻�û�п�ͨ�����뿨��ͬ��ͨ��
				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;//�齨MD5info
				md5Value = md5.getMD5ofStr(resultMd5);//����
				message = "Payment Declined";
				logger.info("*********************֧�����������***************************"+responseCode);//��ӡ��־ �����룺34 ���̻�û�п�ͨ�����뿨��ͬ��ͨ��
				return INPUT;//���أ�xml���õ��ļ�����Ӧ�Ĵ��루��Ӧ��jsp��
			}
			
			logger.info("***************************��ȡ�̻�ͨ�����ֽ���***************************");//��ӡ��־����ȡ��ͨ������
			logger.info("***************************��ʼ��ȡ�̻����׽��*******************************");//��ӡ��־����ȡ�̻����׽��
			
			// ��ȡ���׽��
			md5src = merchantnoValue + BillNo + this.Currency 
					+ Amount + Language + ReturnURL + MD5key;//�齨MD5info
			logger.info("***************************��ȡ�̻����׽�����*********************************");
			logger.info("***************************��ʼ��֤�̻�����ǩ��**********************************");
			logger.info("ǩ������:"+md5src);//��ӡ��־��ǩ������+md5src
			md5src = md5.getMD5ofStr(md5src);//���ܺ��ǩ������
			logger.info("**********************��������ǩ����"+MD5info);//��ӡ��־����������ǩ����MD5info
			logger.info("**********************�����ǩ����"+md5src);//��ӡ��־ �������ǩ�� ��md5src
			// ��Ϣ���۸�
			// System.out.println("========"+merchantnoValue +"========"+
			// BillNo+"========" + moneykind.getMoneykindno()+"========" +
			// Amount+"========" + Language+"========" + ReturnURL+"========" +
			// MD5key+"========" +"========5555============"+md5src);
			// System.out.println("===============666666============="+MD5info);
			if (!(md5src.equals(MD5info))) //�����ǩ���ʹ�������ǩ���ǲ���ͬ
			{
				responseCode = 13;//ǩ����ƥ�����ݷ����۸ģ����ֵ�ԭ��MD5info��˳�򣩻���MD5k��һ����
				resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;//�齨MD5info
				md5Value = md5.getMD5ofStr(resultMd5);//����
				message = "Payment Declined";
				logger.info("**********************�̻�����ǩ����������*********************************");//��ӡ��־�������룺13 ����ȡ�̻����׽��
				return INPUT;//���أ�xml���õ��ļ�����Ӧ�Ĵ��루��Ӧ��jsp��
			}
			logger.info("**********************�̻�����ǩ����֤����*********************************");//��ӡ��־���̻�ǩ����֤����
			logger.info("**********************��ʼ�����̻���������**********************************");//��ӡ��־����ʼ�����̻���������
			tradeMoney = (double) (Math.round((double) tradeMoney * 100) / 100.00);//֧�����
			rmbmoney = (double) (Math.round((double) rmbmoney * 100) / 100.00);//RMB���׽��
			InternationalTradeinfo trade = new InternationalTradeinfo();//��������
			trade.setOrderNo(rorderno);//��ˮ������
			trade.setMerchantOrderNo(merchantOrderNo);//�̻�������
			trade.setMerchantId(merchant.getId());//�̻�ID
			trade.setTradeTime(tradetime);//����ʱ��
			trade.setTradeAmount(Double.valueOf(this.Amount));//֧�����
			trade.setRmbAmount(this.rmbmoney);//RMB֧�����
			trade.setMoneyType(Long.valueOf(Currency));//����
			trade.setTradeState("30000000000000000000");//����״̬����ֶ�
			trade.setTradeRate(changerate.getId());//���׻���ID
			trade.setBalanceRate(settlementrate.getId());//�������
			trade.setTradeUrl(tradeAdd);//������ַ
			trade.setReturnUrl(this.ReturnURL);////������ַ
			trade.setLastDate(new Date());//���ʱ��
			trade.setBackCount(0d);//�˿���
			trade.setCsid(csid);//
			this.commonService.saveOrUpdate(trade);//��Ӷ���
			responseCode = 0;//ʧ��
			logger.info("*****************�̻��������ݲ������********************");//��ӡ��־�����뽻�����ݽ���
			logger.info("*****************��ʼ��ת��֧��ҳ��********************");//��ӡ��־����ת��֧��ҳ��
			return SUCCESS;//���أ�xml���õ��ļ�����Ӧ�Ĵ��루��Ӧ��jsp��
		} catch (Exception e){//���뵽catch��
			logger.error("ϵͳ���ܽ����������δ֪����");//��ӡ��־��ϵͳ���ܽ����������δ֪����
			logger.error(e);
			e.printStackTrace();
			responseCode = 35;//ϵͳλ�ô���һ��ָ�������
			resultMd5 = BillNo + Currency + Amount + responseCode + MD5key;//�齨
			md5Value = md5.getMD5ofStr(resultMd5);//����
			message = "Payment Declined";
			logger.info("*********************֧�����������***************************"+responseCode);//��ӡ��־�������룺0 ��ʧ��
			return INPUT;//���أ�xml���õ��ļ�����Ӧ�Ĵ��루��Ӧ��jsp��
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
	