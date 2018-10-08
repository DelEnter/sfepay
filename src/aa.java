import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;

import sun.misc.BASE64Encoder;
 
public class aa {  
    public static final String PKCS12 = "PKCS12";  
    public static final String JKS = "JKS";  
    public static final String PFX_KEYSTORE_FILE = "C:/aa/client.pfx";//pfx文件位置  
    public static final String KEYSTORE_PASSWORD = "123456";//为pfx文件的设的密码 
    public static final String JKS_KEYSTORE_FILE = "C:/aa/yinlian.jks";
    public static void coverTokeyStore() {  
        try {  
            KeyStore inputKeyStore = KeyStore.getInstance("PKCS12");  
            FileInputStream fis = new FileInputStream(PFX_KEYSTORE_FILE);  
            char[] nPassword = null;  
            if ((KEYSTORE_PASSWORD == null)  
                    || KEYSTORE_PASSWORD.trim().equals("")) {  
                nPassword = null;  
            } else {  
                nPassword = KEYSTORE_PASSWORD.toCharArray();  
            }  
            inputKeyStore.load(fis, nPassword);  
            fis.close();  
            KeyStore outputKeyStore = KeyStore.getInstance("JKS");  
            outputKeyStore.load(null, KEYSTORE_PASSWORD.toCharArray());  
            Enumeration enums = inputKeyStore.aliases();  
            while (enums.hasMoreElements()) { // we are readin just one  
                                                // certificate.  
                String keyAlias = (String) enums.nextElement();  
                System.out.println("alias=[" + keyAlias + "]");  
                if (inputKeyStore.isKeyEntry(keyAlias)) {  
                    Key key = inputKeyStore.getKey(keyAlias, nPassword);  
                    Certificate[] certChain = inputKeyStore  
                            .getCertificateChain(keyAlias);  
                    outputKeyStore.setKeyEntry(keyAlias, key, KEYSTORE_PASSWORD  
                            .toCharArray(), certChain);  
                }  
            }  
            FileOutputStream out = new FileOutputStream(JKS_KEYSTORE_FILE);  
            outputKeyStore.store(out, nPassword);  
            out.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
   
    public static void main(String[] args) throws Exception {  
	       // coverTokeyStore();    // pfx to jks  
	    //String password = "3604TEST12121442180.46ENwww.baidu.comnegyPVkv"; 
    	String password ="2025";
		BASE64Encoder base64en = new BASE64Encoder();
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		String passwords = base64en.encode(md5.digest(password.getBytes("utf-8")));
		System.out.println(passwords);	

    }  
} 