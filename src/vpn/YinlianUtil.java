package vpn;

import java.util.Iterator;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class YinlianUtil {
	private static String payUrl = "http://114.255.222.233:5004/preMOTO/PayPort";
	private static String refundUrl = "https://114.255.222.227/preMOTO/BalanceQueryPort";
	private static String balanceQuery= "https://114.255.222.227/preMOTO/BalanceQueryPort";
	Logger logger = Logger.getLogger(YinlianUtil.class.getName());
//	static {
//		javax.net.ssl.HttpsURLConnection
//				.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {
//
//					public boolean verify(String hostname,
//							javax.net.ssl.SSLSession sslSession) {
//						if (hostname.equals("114.255.222.227")) {
//							return true;
//						}
//						System.out.println("证书错误！");
//						return false;
//					}
//				});
//	}

	public void getYLPayMessage(YinlianMessage msg) {
		try{
	         Service service = new Service();
	         Call call = (Call) service.createCall();
	         call.setTargetEndpointAddress(payUrl);
	         call.setOperationName(new QName("http://service.preMOTO.bjpos.com/","pay"));//WSDL里面描述的接口名称
	         call.addParameter("arg0", org.apache.axis.encoding.XMLType.XSD_STRING,
	                       javax.xml.rpc.ParameterMode.IN);//接口的参数
	         call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);//设置返回类型  
			 StringBuffer sb = new StringBuffer();
			 sb.append("<?xml version=\"1.0\" encoding=\"GBK\"?>")
						.append("<BJPos>").append("<Head>")
						.append("<TrnxDatetime>").append(msg.getTrnxDatetime())
						.append("</TrnxDatetime>").append("</Head>")
						.append("<Body>").append("<CardNo>")
						.append(msg.getCardNo()).append("</CardNo>")
						.append("<Amt>").append(msg.getAmt()).append("</Amt>")
						.append("<PosFlwNo>").append(msg.getPosFlwNo())
						.append("</PosFlwNo>").append("<TermId>")
						.append(msg.getTermId()).append("</TermId>")
						.append("<MerchId>").append(msg.getMerchId())
						.append("</MerchId>").append("<Cvv2>")
						.append(msg.getCvv2()).append("</Cvv2>")
						.append("<ExpiredDate>").append(msg.getExpiredDate())
						.append("</ExpiredDate>").append("</Body>")
						.append("</BJPos>");
				// sb.append("<?xml version=\"1.0\" encoding=\"GBK\"?><BJPos><Head><TrnxDatetime>20140828153541</TrnxDatetime></Head><Body><CardNo>4168340000428334</CardNo><Amt>000000000000</Amt><PosFlwNo>308906</PosFlwNo><TermId>09220236</TermId><MerchId>922110157120109</MerchId><Cvv2>131</Cvv2><ExpiredDate>1606</ExpiredDate></Body></BJPos>");
				logger.info("上送报文：<?xml version=\"1.0\" encoding=\"GBK\"?><BJPos><Head><TrnxDatetime>" + msg.getTrnxDatetime()+"</TrnxDatetime></Head><Body><CardNo>"+msg.getCardNo().substring(0,6)+"******"+msg.getCardNo().substring(12)+"</CardNo><Amt>"+msg.getAmt()+"</Amt><PosFlwNo>"+msg.getPosFlwNo()+"</PosFlwNo><TermId>"+msg.getTermId()+"</TermId><MerchId>"+msg.getMerchId()+"</MerchId><Cvv2>***</Cvv2><ExpiredDate>****</ExpiredDate></Body></BJPos>");
				String result = (String)call.invoke(new Object[]{sb.toString()});
				logger.info("返回报文：" + result);
				try {
					Document dom = DocumentHelper.parseText(result);
	
					Element rootElement = dom.getRootElement();
					for (Iterator i = rootElement.elementIterator("Body"); i
							.hasNext();) {
						Element root = (Element) i.next();
						for (Iterator j = root.elementIterator(); j.hasNext();) {
							Element ro = (Element) j.next();
							if (ro.getName().equals("CardNo")) {
								msg.setRes_cardNo(ro.getText().trim());
							}
							if (ro.getName().equals("PosFlwNo")) {
								msg.setRes_posFlwNo(ro.getText().trim());
							}
							if (ro.getName().equals("SettlementDate")) {
								msg.setRes_settlementDate(ro.getText().trim());
							}
							if (ro.getName().equals("ReferenceNo")) {
								msg.setRes_referenceNo(ro.getText().trim());
							}
							if (ro.getName().equals("AuthResCode")) {
								msg.setRes_authResCode(ro.getText().trim());
							}
							if (ro.getName().equals("ResCode")) {
								msg.setRes_resCode(ro.getText().trim());
							}
							if (ro.getName().equals("TermId")) {
								msg.setRes_termId(ro.getText().trim());
							}
							if (ro.getName().equals("MerchId")) {
								msg.setRes_merchId(ro.getText().trim());
							}
						}
					}
				} catch (DocumentException e) {
					e.printStackTrace();
				}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void getYLbalanceQuery(YinlianMessage msg) {
		try{
	         Service service = new Service();
	         Call call = (Call) service.createCall();
	         call.setTargetEndpointAddress(balanceQuery);
	         call.setOperationName(new QName("http://service.preMOTO.bjpos.com/","balancQuery"));//WSDL里面描述的接口名称
	         call.addParameter("arg0", org.apache.axis.encoding.XMLType.XSD_STRING,
	                       javax.xml.rpc.ParameterMode.IN);//接口的参数
	         call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);//设置返回类型  
			 StringBuffer sb = new StringBuffer();
			 sb.append("<?xml version=\"1.0\" encoding=\"GBK\"?>")
						.append("<BJPos>").append("<Head>")
						.append("<TrnxDatetime>").append(msg.getTrnxDatetime())
						.append("</TrnxDatetime>").append("</Head>")
						.append("<Body>").append("<CardNo>")
						.append(msg.getCardNo()).append("</CardNo>")
						.append("<PosFlwNo>").append(msg.getPosFlwNo())
						.append("</PosFlwNo>").append("<TermId>")
						.append(msg.getTermId()).append("</TermId>")
						.append("<MerchId>").append(msg.getMerchId())
						.append("</MerchId>").append("<Cvv2>")
						.append(msg.getCvv2()).append("</Cvv2>")
						.append("<ExpiredDate>").append(msg.getExpiredDate())
						.append("</ExpiredDate>").append("</Body>")
						.append("</BJPos>");
				// sb.append("<?xml version=\"1.0\" encoding=\"GBK\"?><BJPos><Head><TrnxDatetime>20140828153541</TrnxDatetime></Head><Body><CardNo>4168340000428334</CardNo><Amt>000000000000</Amt><PosFlwNo>308906</PosFlwNo><TermId>09220236</TermId><MerchId>922110157120109</MerchId><Cvv2>131</Cvv2><ExpiredDate>1606</ExpiredDate></Body></BJPos>");
				logger.info("上送报文：<?xml version=\"1.0\" encoding=\"GBK\"?><BJPos><Head><TrnxDatetime>" + msg.getTrnxDatetime()+"</TrnxDatetime></Head><Body><CardNo>"+msg.getCardNo().substring(0,6)+"******"+msg.getCardNo().substring(12)+"</CardNo><Amt>"+msg.getAmt()+"</Amt><PosFlwNo>"+msg.getPosFlwNo()+"</PosFlwNo><TermId>"+msg.getTermId()+"</TermId><MerchId>"+msg.getMerchId()+"</MerchId><Cvv2>***</Cvv2><ExpiredDate>****</ExpiredDate></Body></BJPos>");
				String result = (String)call.invoke(new Object[]{sb.toString()});
				logger.info("返回报文：" + result);
				try {
					Document dom = DocumentHelper.parseText(result);
	
					Element rootElement = dom.getRootElement();
					for (Iterator i = rootElement.elementIterator("Body"); i
							.hasNext();) {
						Element root = (Element) i.next();
						for (Iterator j = root.elementIterator(); j.hasNext();) {
							Element ro = (Element) j.next();
							if (ro.getName().equals("CardNo")) {
								msg.setRes_cardNo(ro.getText().trim());
							}
							if (ro.getName().equals("PosFlwNo")) {
								msg.setRes_posFlwNo(ro.getText().trim());
							}
							if (ro.getName().equals("SettlementDate")) {
								msg.setRes_settlementDate(ro.getText().trim());
							}
							if (ro.getName().equals("ReferenceNo")) {
								msg.setRes_referenceNo(ro.getText().trim());
							}
							if (ro.getName().equals("AuthResCode")) {
								msg.setRes_authResCode(ro.getText().trim());
							}
							if (ro.getName().equals("ResCode")) {
								msg.setRes_resCode(ro.getText().trim());
							}
							if (ro.getName().equals("TermId")) {
								msg.setRes_termId(ro.getText().trim());
							}
							if (ro.getName().equals("MerchId")) {
								msg.setRes_merchId(ro.getText().trim());
							}
						}
					}
				} catch (DocumentException e) {
					e.printStackTrace();
				}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void getYLRefundMessage(YinlianMessage msg) {
		try{
	         Service service = new Service();
	         Call call = (Call) service.createCall();
	         call.setTargetEndpointAddress(refundUrl);
	         call.setOperationName(new QName("http://service.preMOTO.bjpos.com/","refund"));//WSDL里面描述的接口名称
	         call.addParameter("arg0", org.apache.axis.encoding.XMLType.XSD_STRING,
	                       javax.xml.rpc.ParameterMode.IN);//接口的参数
	         call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);//设置返回类型  
			 StringBuffer sb = new StringBuffer();
			 sb.append("<?xml version=\"1.0\" encoding=\"GBK\"?>")
						.append("<BJPos>").append("<Head>")
						.append("<TrnxDatetime>").append(msg.getTrnxDatetime())
						.append("</TrnxDatetime>").append("</Head>")
						.append("<Body>").append("<CardNo>")
						.append(msg.getCardNo()).append("</CardNo>")
						.append("<Amt>").append(msg.getAmt()).append("</Amt>")
						.append("<PosFlwNo>").append(msg.getPosFlwNo())
						.append("</PosFlwNo>").append("<ReferenceNo>")
						.append(msg.getReferenceNo()).append("</ReferenceNo>")
						.append("<AuthResCode>").append(msg.getAuthResCode()).append("</AuthResCode>")
						.append("<TermId>").append(msg.getTermId()).append("</TermId>")
						.append("<MerchId>").append(msg.getMerchId())
						.append("</MerchId>").append("<ResCode>")
						.append(msg.getResCode()).append("</ResCode>")
						.append("<OriginalPosNo>").append(msg.getOriginalPosNo())
						.append("</OriginalPosNo>").append("<OriginalDate>")
						.append(msg.getOriginalDate()).append("</OriginalDate>")
						.append("</Body>")
						.append("</BJPos>");
				// sb.append("<?xml version=\"1.0\" encoding=\"GBK\"?><BJPos><Head><TrnxDatetime>20140828153541</TrnxDatetime></Head><Body><CardNo>4168340000428334</CardNo><Amt>000000000000</Amt><PosFlwNo>308906</PosFlwNo><TermId>09220236</TermId><MerchId>922110157120109</MerchId><Cvv2>131</Cvv2><ExpiredDate>1606</ExpiredDate></Body></BJPos>");
				logger.info("上送报文：<?xml version=\"1.0\" encoding=\"GBK\"?><BJPos><Head><TrnxDatetime>" + msg.getTrnxDatetime()+"</TrnxDatetime></Head><Body><CardNo>"+msg.getCardNo().substring(0,6)+"******"+msg.getCardNo().substring(12)+"</CardNo><Amt>"+msg.getAmt()+"</Amt><PosFlwNo>"+msg.getPosFlwNo()+"</PosFlwNo><ReferenceNo>"+msg.getReferenceNo()+"</ReferenceNo><AuthResCode>"+msg.getAuthResCode()+"</AuthResCode><TermId>"+msg.getTermId()+"</TermId><MerchId>"+msg.getMerchId()+"</MerchId><ResCode>"+msg.getResCode()+"</ResCode><OriginalPosNo>"+msg.getOriginalPosNo()+"</OriginalPosNo><OriginalDate>"+msg.getOriginalDate()+"</OriginalDate></Body></BJPos>");
				String result = (String)call.invoke(new Object[]{sb.toString()});
				logger.info("返回报文：" + result);
				try {
					Document dom = DocumentHelper.parseText(result);
	
					Element rootElement = dom.getRootElement();
					for (Iterator i = rootElement.elementIterator("Body"); i
							.hasNext();) {
						Element root = (Element) i.next();
						for (Iterator j = root.elementIterator(); j.hasNext();) {
							Element ro = (Element) j.next();
							if (ro.getName().equals("CardNo")) {
								msg.setRes_cardNo(ro.getText().trim());
							}
							if (ro.getName().equals("Amt")) {
								msg.setRes_amt(ro.getText().trim());
							}
							if (ro.getName().equals("PosFlwNo")) {
								msg.setRes_posFlwNo(ro.getText().trim());
							}
							if (ro.getName().equals("SettlementDate")) {
								msg.setRes_settlementDate(ro.getText().trim());
							}
							if (ro.getName().equals("ReferenceNo")) {
								msg.setRes_referenceNo(ro.getText().trim());
							}
							if (ro.getName().equals("AuthResCode")) {
								msg.setRes_authResCode(ro.getText().trim());
							}
							if (ro.getName().equals("ResCode")) {
								msg.setRes_resCode(ro.getText().trim());
							}
							if (ro.getName().equals("TermId")) {
								msg.setRes_termId(ro.getText().trim());
							}
							if (ro.getName().equals("MerchId")) {
								msg.setRes_merchId(ro.getText().trim());
							}
						}
					}
				} catch (DocumentException e) {
					e.printStackTrace();
				}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
