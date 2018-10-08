package com.ecpss.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URLDecoder;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import com.sun.net.ssl.SSLContext;
import com.sun.net.ssl.X509TrustManager;

public class VcpUtil {
	public static X509TrustManager s_x509TrustManager = null;
	public static SSLSocketFactory s_sslSocketFactory = null;

	static {
		s_x509TrustManager = new X509TrustManager() {
			public X509Certificate[] getAcceptedIssuers() {
				return new X509Certificate[] {};
			}

			public boolean isClientTrusted(X509Certificate[] chain) {
				return true;
			}

			public boolean isServerTrusted(X509Certificate[] chain) {
				return true;
			}
		};

		java.security.Security
				.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		try {
			SSLContext context = SSLContext.getInstance("TLS");
			context.init(null, new X509TrustManager[] { s_x509TrustManager },
					null);
			s_sslSocketFactory = context.getSocketFactory();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	public static String doPost(String vpc_Host, String data, boolean useProxy,
			String proxyHost, int proxyPort) throws IOException {

		InputStream is;
		OutputStream os;
		int vpc_Port = 443;
		String fileName = "";
		boolean useSSL = true;

		// determine if SSL encryption is being used
		if (vpc_Host.substring(0, 8).equalsIgnoreCase("HTTPS://")) {
			useSSL = true;
			// remove 'HTTPS://' from host URL
			vpc_Host = vpc_Host.substring(8);
			// get the filename from the last section of vpc_URL
			fileName = vpc_Host.substring(vpc_Host.lastIndexOf("/"));
			// get the IP address of the VPC machine
			vpc_Host = vpc_Host.substring(0, vpc_Host.lastIndexOf("/"));
		}

		// use the next block of code if using a proxy server
		if (useProxy) {
			Socket s = new Socket(proxyHost, proxyPort);
			os = s.getOutputStream();
			is = s.getInputStream();
			// use next block of code if using SSL encryption
			if (useSSL) {
				String msg = "CONNECT " + vpc_Host + ":" + vpc_Port
						+ " HTTP/1.0\r\n" + "User-Agent: HTTP Client\r\n\r\n";
				os.write(msg.getBytes());
				byte[] buf = new byte[4096];
				int len = is.read(buf);
				String res = new String(buf, 0, len);

				// check if a successful HTTP connection
				if (res.indexOf("200") < 0) {
					throw new IOException("Proxy would now allow connection - "
							+ res);
				}

				// write output to VPC
				SSLSocket ssl = (SSLSocket) s_sslSocketFactory.createSocket(s,
						vpc_Host, vpc_Port, true);
				ssl.startHandshake();
				os = ssl.getOutputStream();
				// get response data from VPC
				is = ssl.getInputStream();
				// use the next block of code if NOT using SSL encryption
			} else {
				fileName = vpc_Host;
			}
			// use the next block of code if NOT using a proxy server
		} else {
			// use next block of code if using SSL encryption
			if (useSSL) {
				Socket s = s_sslSocketFactory.createSocket(vpc_Host, vpc_Port);
				os = s.getOutputStream();
				is = s.getInputStream();
				// use next block of code if NOT using SSL encryption
			} else {
				Socket s = new Socket(vpc_Host, vpc_Port);
				os = s.getOutputStream();
				is = s.getInputStream();
			}
		}

		String req = "POST " + fileName + " HTTP/1.0\r\n"
				+ "User-Agent: HTTP Client\r\n"
				+ "Content-Type: application/x-www-form-urlencoded\r\n"
				+ "Content-Length: " + data.length() + "\r\n\r\n" + data;

		os.write(req.getBytes());
		String res = new String(readAll(is));

		// check if a successful connection
		if (res.indexOf("200") < 0) {
			throw new IOException("Connection Refused - " + res);
		}

		if (res.indexOf("404 Not Found") > 0) {
			throw new IOException("File Not Found Error - " + res);
		}

		int resIndex = res.indexOf("\r\n\r\n");
		String body = res.substring(resIndex + 4, res.length());
		System.out.println(body);
		return body;
		
	}

	private static byte[] readAll(InputStream is) throws IOException {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];

		while (true) {
			int len = is.read(buf);
			if (len < 0) {
				break;
			}
			baos.write(buf, 0, len);
		}
		return baos.toByteArray();
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
                    String value = URLDecoder.decode(token.substring(i + 1, token.length()));
                    map.put(key, value);
                } catch (Exception ex) {
                    // Do Nothing and keep looping through data
                }
            }
        }
        return map;
    }
    public static String null2unknown(String in, Map responseFields) {
        if (in == null || in.length() == 0 || (String)responseFields.get(in) == null) {
              return "No Value Returned";
          } else {
              return (String)responseFields.get(in);
          }
      } // null2unknown()    
    
}
