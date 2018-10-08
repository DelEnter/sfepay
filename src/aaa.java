import java.io.BufferedReader; 
import java.io.BufferedWriter; 
import java.io.FileInputStream; 
import java.io.FileOutputStream; 
import java.io.FileWriter;
import java.io.InputStreamReader; 
import java.io.ObjectInputStream; 
import java.io.ObjectOutputStream; 
import java.io.PrintWriter;
import java.math.BigInteger; 
import java.security.KeyPair;  
import java.security.KeyPairGenerator;
import java.security.PrivateKey; 
import java.security.PublicKey;  
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey; 

import com.ecpss.util.AES;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class aaa {
	//得要公钥和私钥     
	public static void generateKey(){      
		try{       
			//KeyPairGenerator 类用于生成公钥和私钥对       
			KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");       
			//初始化确定密钥大小的密钥对生成器       
			kpg.initialize(1024);       
			//简单的密钥对（公钥和私钥）持有者      
			KeyPair kp = kpg.generateKeyPair();       
			//公钥       
			PublicKey pbkey = kp.getPublic(); 
		
			//私钥       
			PrivateKey prkey = kp.getPrivate();
			//保存公钥       
			FileOutputStream f1 = new FileOutputStream("C://pubkey.dat");       
			ObjectOutputStream b1 = new ObjectOutputStream(f1);       
			b1.writeObject(pbkey); 
            //保存私钥      
			FileOutputStream f2 = new FileOutputStream("C://privatekey.dat");       
			ObjectOutputStream b2 = new ObjectOutputStream(f2);       
			b2.writeObject(prkey);             
			}catch(Exception e){          
				e.printStackTrace();       
				}    
		}
	//加密      
	public static void encrypt() throws Exception{      
		AES aes =new AES();      
		String s = aes.getRawKey();
		//获取公钥即参数     
		FileInputStream f = new FileInputStream("D://pubkey.dat");      
		ObjectInputStream b = new ObjectInputStream(f);     
		RSAPublicKey pbk = (RSAPublicKey)b.readObject();     
		
		//公用指数      
		BigInteger e = pbk.getPublicExponent();      
		//系数      
		BigInteger n = pbk.getModulus();      
		//获取明文m      
		byte ptext[] = s.getBytes("UTF-8");     
		BigInteger m = new BigInteger(ptext);       
		//计算密文c      
		BigInteger c = m.modPow(e, n);      
		//保存密文      
		String cs = c.toString(); 
		String key1=cs.substring(0, 100);
		String key2=cs.substring(100, cs.length());
		//第二次加密生成两段放两个文件给两个人来管理
		for(int i=1;i<3;i++){
		PrintWriter out = new PrintWriter( new BufferedWriter(new FileWriter("D://sfepay"+i+".txt")));
		if(i==1){
		  out.println(key1);
		}else{
			out.println(key2);
		}
		  out.close();
		} 
	}
	//解密      
	public static void dencrypt() throws Exception{     
		//读密文      
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("D://encrypt.dat")));      
		String ctext = in.readLine();      
		BigInteger c = new BigInteger(ctext);      
		//读私钥      
		FileInputStream f = new FileInputStream("D://privateKey.dat");      
		ObjectInputStream b = new ObjectInputStream(f);     
		RSAPrivateKey prk = (RSAPrivateKey)b.readObject(); 
		BigInteger d = prk.getPrivateExponent(); 
		
		//获取私钥参数即解密     
		BigInteger n = prk.getModulus();      
		BigInteger m = c.modPow(d,n);                  
		byte mt[] = m.toByteArray();      
		for(int i=0;i<mt.length;i++){       
			System.out.print((char)mt[i]);     
			}                
		}  
	public static void main(String[] args) {   
		try{    
			generateKey();    
			encrypt();    
			dencrypt();   
			}catch(Exception e){    
				System.out.println(e.toString());   
				} 
		}  
	}
