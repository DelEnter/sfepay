import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;


public class dd {
	public static void main(String[] args) {
		try{
	         Service service = new Service();
	         Call call = (Call) service.createCall();
	         call.setTargetEndpointAddress(new java.net.URL("http://localhost:8080/Service/Hello"));
	         call.setOperationName(new QName("http://sfepay.com/","hehe"));//WSDL里面描述的接口名称
	         call.addParameter("arg0", org.apache.axis.encoding.XMLType.XSD_STRING,
                     javax.xml.rpc.ParameterMode.IN);//接口的参数
	         call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);//设置返回类型  
				// sb.append("<?xml version=\"1.0\" encoding=\"GBK\"?><BJPos><Head><TrnxDatetime>20140828153541</TrnxDatetime></Head><Body><CardNo>4168340000428334</CardNo><Amt>000000000000</Amt><PosFlwNo>308906</PosFlwNo><TermId>09220236</TermId><MerchId>922110157120109</MerchId><Cvv2>131</Cvv2><ExpiredDate>1606</ExpiredDate></Body></BJPos>");
				String result = (String)call.invoke(new Object[]{"jiahui"});
				System.out.println("返回报文：" + result);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
