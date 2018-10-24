package com.ecpss.util;

import java.io.FileInputStream;

import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;


/**
 * 证书操作�?
 *

 */
public class MerchantX509Cert {
    /**
     * Java密钥�?(Java Key Store，JKS)KEY_STORE
     */
    public static final String KEY_STORE = "PKCS12";

    public static final String X509 = "X.509";


    /**
     * 签名
     * @param signSrc
     * @param keyStorePath
     * @param alias
     * @param keyStorePassword
     * @param aliasPassword
     * @return String
     * @throws Exception
     */
    public static String sign(String signSrc, String keyStorePath, String alias, String keyStorePassword, String aliasPassword) throws Exception {
        // 获得证书
        X509Certificate x509Certificate = (X509Certificate)getCertificate(keyStorePath, keyStorePassword, alias);
        // 取得私钥
        PrivateKey privateKey = getPrivateKey(keyStorePath, keyStorePassword, alias, aliasPassword);
        // 构建签名
        Signature signature = Signature.getInstance(x509Certificate.getSigAlgName());
        signature.initSign(privateKey);
        signature.update(signSrc.getBytes("UTF-8"));
        return new Hex().encodeHexString(signature.sign());
    }

    /**
     * 验证签名
     *
     * @param signSrc
     * @param sign
     * @param certificatePath
     * @return boolean
     * @throws Exception
     */
    public static boolean verifySign(String signSrc, String sign, String certificatePath) throws Exception {
        // 获得证书
        X509Certificate x509Certificate = (X509Certificate)getCertificate(certificatePath);
        // 获得公钥
        PublicKey publicKey = x509Certificate.getPublicKey();
        // 构建签名
        Signature signature = Signature.getInstance(x509Certificate.getSigAlgName());
        signature.initVerify(publicKey);
        signature.update(signSrc.getBytes("UTF-8"));
        byte[] signByte = new Hex().decode(sign.getBytes());

        boolean verifyStatus = false;
        verifyStatus = signature.verify(signByte);
        return verifyStatus;

    }


    /**
     * �? KeyStore获得私钥
     *
     * @param keyStorePath
     * @param keyStorePassword
     * @param alias
     * @param aliasPassword
     * @return
     * @throws Exception
     */
    private static PrivateKey getPrivateKey(String keyStorePath, String keyStorePassword, String alias, String aliasPassword) throws Exception {
        KeyStore ks = getKeyStore(keyStorePath, keyStorePassword);
        PrivateKey key = (PrivateKey)ks.getKey(alias, aliasPassword.toCharArray());
        return key;
    }

    /**
     * �? Certificate获得公钥
     *
     * @param certificatePath
     * @return
     * @throws Exception
     */
    private static PublicKey getPublicKey(String certificatePath) throws Exception {
        Certificate certificate = getCertificate(certificatePath);
        PublicKey key = certificate.getPublicKey();
        return key;
    }

    /**
     * 获得Certificate
     *
     * @param certificatePath
     * @return
     * @throws Exception
     */
    private static Certificate getCertificate(String certificatePath) throws Exception {
        CertificateFactory certificateFactory = CertificateFactory.getInstance(X509);
        FileInputStream in = new FileInputStream(certificatePath);

        Certificate certificate = certificateFactory.generateCertificate(in);
        in.close();

        return certificate;
    }

    /**
     * 获得Certificate
     *
     * @param keyStorePath
     * @param keyStorePassword
     * @param alias
     * @return
     * @throws Exception
     */
    private static Certificate getCertificate(String keyStorePath, String keyStorePassword, String alias) throws Exception {
        KeyStore ks = getKeyStore(keyStorePath, keyStorePassword);
        Certificate certificate = ks.getCertificate(alias);

        return certificate;
    }

    /**
     * 获得KeyStore
     *
     * @param keyStorePath
     * @param password
     * @return
     * @throws Exception
     */
    private static KeyStore getKeyStore(String keyStorePath, String password) throws Exception {
        FileInputStream is = new FileInputStream(keyStorePath);
        KeyStore ks = KeyStore.getInstance(KEY_STORE);
        ks.load(is, password.toCharArray());
        is.close();
        return ks;
    }


    public static void main(String[] args) throws DecoderException, Exception {

       /* String pfxFile = "D:/cfca_private_7606.pfx";//私钥文件
        String pfxPassword = "lh121121";
        String aliasPassword = "lh121121";
        String aliasName = "{3dfcbb2d-bb3d-4350-9010-33de8cdb076c}";

        String certPath = "D:/cfca_publickay_7606.cer";


        String src = "abcdefg1234567890测试中文";
        String sign = "";

        System.out.println("数据原串: " + src);


        sign = MerchantX509Cert.sign(src, pfxFile, aliasName, pfxPassword, aliasPassword);
        System.out.println("数据签名: " + sign);*/
    	
        /*数据原串: 20161116-11545-CZ1C76KQ
        v_sign: 001034034cd647529c557c2755fab7eb511ce7d1107a4601a344ad8fd8bb4ed71c8dc80e7b5e3e5ad8acb20b32c432eb9c92cb1776a36037042864f38604c6bb272e34d8bd967aceab6814c4fe7c83915f033b5952a68a2025395a0b799469390a2ea8e8268c2423836be1b121d5bb04dd01b38ed7c80e01131a7b10f45c86fc
        CFCA验签结果: false*/
    	String src = "20161116-11545-CZ1C76KQ150001";
    	String sign = "48f8087146a12269d0c9889e70966057e3df7b1ee29e83c50cb1574f2e5e240539bedf3e8ea70859a11f8c22a060f2453a6cf89a4e97bddba89b46a2a78566f9aba72316301afd06de88850f6db9734fb0c639be2d8e49561f52c824499f6d2d1d79667afaffd7af49064f13c4756992e9824e8321fbdf2391fca9dd5cb02136ced7f87256177ada43260eb1c77700001c16876a4f550a72b430fc3be109394d33860611e7f479d4706e28a4fc6b6e22115059fd506208c161631ad24eed";
    	String certPath = "D:/payease_cfca.cer";
        boolean verifyReuslt = MerchantX509Cert.verifySign(src, sign, certPath);
        System.out.println("验签结果: " + verifyReuslt);




    }


}
