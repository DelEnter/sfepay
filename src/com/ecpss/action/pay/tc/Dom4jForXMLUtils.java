package com.ecpss.action.pay.tc;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class Dom4jForXMLUtils {
	//DOM4j解析XML   
    public static HashMap<String,String> parse(String protocolXML) {   
        try {
        	HashMap<String,String> hashMap = new HashMap<String,String>();

            Document doc=(Document)DocumentHelper.parseText(protocolXML);   
            Element books = doc.getRootElement();   
            //System.out.println("根节�?"+books.getName());   
            // Iterator users_subElements = books.elementIterator("UID");//指定获取那个元素   
            Iterator  Elements = books.elementIterator();    
            while(Elements.hasNext()){   
               Element user = (Element)Elements.next();   
               hashMap.put(user.getName(), user.getText());
               //System.out.println("节点"+user.getName()+"\ttext="+user.getText());   
               List  subElements = user.elements();    
               List user_subElements = user.elements("username");//指定获取那个元素   
	           //System.out.println("size=="+subElements.size());   
	           for( int i=0;i<subElements.size();i++){   
	             Element ele = (Element)subElements.get(i); 
	             
	             hashMap.put(ele.getName(), ele.getText());
	             //System.out.print(ele.getName()+" : "+ele.getText()+" ");   
	           }   
               //System.out.println();   
           } 
           return hashMap;
        } catch (Exception e) {   
            e.printStackTrace();   
        }
		return null;           
    }   

	public static void main(String[] args) {
		Dom4jForXMLUtils xml = new Dom4jForXMLUtils();
		String tranDataStr = "<?xml  version=\"1.0\" encoding=\"GBK\" standalone=\"no\" ?><B2CRes><interfaceName>ICBC_PERBANK_B2C</interfaceName><interfaceVersion>1.0.0.3</interfaceVersion><orderInfo><orderDate>20070725105014</orderDate><orderid>20070725105014-2134062548</orderid><amount>20</amount><curType>001</curType><merID>0200EC20000875</merID><merAcct>0200020409015029130</merAcct></orderInfo><custom><verifyJoinFlag>0</verifyJoinFlag><JoinFlag></JoinFlag><UserNum></UserNum></custom><bank><TranSerialNo></TranSerialNo><notifyDate>20070725110400</notifyDate><tranStat>1</tranStat><comment>交易成功�?</comment></bank></B2CRes>";
		System.out.println(xml.parse(tranDataStr).get("interfaceVersion"));
	}

}
