
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;


public class testThread extends Thread {
	   public volatile boolean exit = false; 
	   int i=0;
	    public void run() 
	    { 
	        while (!exit){
	        	System.out.println(i);
	        	if(i==12){
	        		exit=true;
	        	}
	        	i++;
	        }; 
	    } 
	    public static void main(String[] args) throws Exception 
	    { 
	    	testThread thread = new testThread(); 
	        thread.start(); 
	        
	        System.out.println("线程退出!"); 
	    } 


}
