import java.net.URL;
import java.net.URLDecoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.protocol.Protocol;

import com.ecpss.icbc.SSL.AuthSSLProtocolSocketFactory;
public class bb {
	public bb() {
		super();
	}

	public static void main(String[] args) throws Exception {
		Protocol authhttps = new Protocol("https",
		// AuthSSLProtocolSocketFactory参数含义：证书库，证书库，本地端口（建立https连接时使用的本地端口，需要空闲端口）
			new AuthSSLProtocolSocketFactory(
	
			new URL("file:///C:/yinlian.jks"), "123456",
			
			new URL("file:///C:/yinlian.jks"), "123456"), 8443);

		HttpClient client = new HttpClient();
		client.getHostConfiguration().setHost("114.255.222.227", 443, authhttps);
		//client.getHostConfiguration().setHost("corporbank3.dccnet.com.cn", 443, authhttps);
		// client.getHostConfiguration().setHost("83.252.30.98",8890);
		/* 只能使用相对路径 */

		PostMethod httpget = new PostMethod("preMOTO/PayPort");
		String reqdata="<?xml  version=\"1.0\" encoding=\"GBK\" standalone=\"no\" ?>" +
				"<BJPos>" +"<Head><TrnxDatetime>20140923101510</TrnxDatetime></Head>"+
					"<Body>" +
						"<CardNo>5274140087810109</CardNo>" +
						"<PosFlwNo>000001</PosFlwNo>" +
						"<TermId>00000008</TermId>" +
						"<MerchId>3604</MerchId>" +
						"<Cvv2>004</Cvv2>" +
						"<CredentialType></CredentialType>" +
						"<CredentialNo></CredentialNo>" +
						"<Name></Name>" +
					"</Body>" +
				"</BJPos>";
		httpget
				.setParameter(
						"parameters",reqdata);
		client.executeMethod(httpget);
		
		String bbb=URLDecoder.decode(httpget.getResponseBodyAsString(),"GBK");
		System.out.println(bbb);
		
		System.out.println("server responding body :"
				+ httpget.getResponseBodyAsString());

		System.out.println("server responding code :"
				+ httpget.getStatusLine().toString());
	}

}
