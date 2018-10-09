package com.ecpss.util;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
 
import java.util.Map;

import net.sf.json.JSONObject;
 
public class GetAddressByIp
{
     
     
    /**
     * 
     * @param IP
     * @return
     */
    public AddressIpInfo GetAddressByIp(String IP){
        String resout = "";
        AddressIpInfo ipInfo=new AddressIpInfo();
        try{
         String str = getJsonContent("http://ip-api.com/json/"+IP);
         System.out.println(str);
          
         JSONObject obj = JSONObject.fromObject(str);
         Map map = (Map)obj;
         ipInfo.setCity(map.get("city").toString());
         ipInfo.setCountry(map.get("country").toString());
         ipInfo.setCountryCode(map.get("countryCode").toString());
         ipInfo.setLonlat(map.get("lon").toString()+">>"+map.get("lat").toString());
         ipInfo.setRegionName(map.get("regionName").toString());
         ipInfo.setZip(map.get("zip").toString());
        }catch(Exception e){
             
            e.printStackTrace();
             resout = "��ȡIP��ַ�쳣��"+e.getMessage();
        }
        return ipInfo;
          
    }
    
    public static String getJsonContent(String urlStr)
    {
        try
        {// ��ȡHttpURLConnection���Ӷ���
            URL url = new URL(urlStr);
            HttpURLConnection httpConn = (HttpURLConnection) url
                    .openConnection();
            // ������������
            httpConn.setConnectTimeout(3000);
            httpConn.setDoInput(true);
            httpConn.setRequestMethod("POST");
            // ��ȡ��Ӧ��
            int respCode = httpConn.getResponseCode();
            if (respCode == 200)
            {
                return ConvertStream2Json(httpConn.getInputStream());
            }
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return "";
    }
 
    
    private static String ConvertStream2Json(InputStream inputStream)
    {
        String jsonStr = "";
        // ByteArrayOutputStream�൱���ڴ������
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        // ��������ת�Ƶ��ڴ��������
        try
        {
            while ((len = inputStream.read(buffer, 0, buffer.length)) != -1)
            {
                out.write(buffer, 0, len);
            }
            // ���ڴ���ת��Ϊ�ַ���
            jsonStr = new String(out.toByteArray());
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonStr;
    }
}