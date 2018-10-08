import java.security.Security;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Test3Des {
	private static Hashtable<String, Long> orderCache = new Hashtable<String, Long>();
	private static Thread orderExpiredThread;
   private static final Cipher cipher = initCipher();
   private static final BASE64Encoder base64 = new BASE64Encoder();
   private final static String deskey="1BoHbM2YrhsOeui0";
   private static final Cipher initCipher() {
       try {
           // 添加新安全算法:PKCS7
           Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
           String algorithm = "DESede/ECB/PKCS7Padding";
//           SecretKey desKey = new SecretKeySpec((new BASE64Decoder()).decodeBuffer(deskey), algorithm);
           SecretKey desKey = new SecretKeySpec(deskey.getBytes("ASCII"), algorithm);
           Cipher tcipher = Cipher.getInstance(algorithm);
           tcipher.init(Cipher.ENCRYPT_MODE, desKey);
           return tcipher;
       } catch (Exception e) {
           e.printStackTrace();
       }
       return null;
   }
 
   public static String encrypt(String src) {
       return base64.encode(encrypt(src.getBytes()));
   }
 
   public static byte[] encrypt(byte[] src) {
       try {
           return cipher.doFinal(src);
       } catch (Exception e) {
           e.printStackTrace();
       }
       return null;
   }
   public static void main(String[] args) throws InterruptedException {
	  /* Test3Des cWebService3DES = new Test3Des();
	String aa="123131313";
	System.out.println(cWebService3DES.encrypt(aa));*/
	   	String cardnum = "123";
		double Amount = 10d;
		String addIp = "127.0.0.1";
		String MerNo = "1234565";
		Test3Des t = new Test3Des();
		t.addOrder(cardnum, Amount, addIp, MerNo);
		Thread.sleep(8000);
		t.addOrder(cardnum, Amount, addIp, MerNo);
   }
   
   public void addOrder(String cardnum, double Amount, String addIp, String MerNo){
	   if (orderExpiredThread == null)
		{
			orderExpiredThread = new Thread(){
				public void run() {
					while (true){
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						//清除过期的缓存
						int expiredMin = 3;
						Iterator<Entry<String, Long>> it = orderCache.entrySet().iterator();
						while (it.hasNext()){
							Entry<String, Long> entry = it.next();
							long addTimeMill = entry.getValue();
							System.out.println((System.currentTimeMillis() - addTimeMill));
							if ((System.currentTimeMillis() - addTimeMill) > 10000)
							{
								
								orderCache.remove(entry.getKey());
								System.out.println("orderInfo 过期" + entry.getKey());
							}
						}
					}
				}
			};
			orderExpiredThread.start();
		}
		
		//缓存订单
		String orderInfo = cardnum + Amount + addIp + MerNo;
		if (orderCache.get(orderInfo) != null)
		{
			
			System.out.println("*********************支付结果返回码***************************"+5);
		}
		else
		{
			System.out.println("orderInfo : " + orderInfo);
			orderCache.put(orderInfo, System.currentTimeMillis());
		}
   }
}