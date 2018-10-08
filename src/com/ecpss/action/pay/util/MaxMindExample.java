package  com.ecpss.action.pay.util;
import java.util.*;

/*
 * 
 */
@SuppressWarnings("unchecked")
public class MaxMindExample {
	  
//	  HashMap h = new HashMap();
	  public HashMap maxMindScore(HashMap hm){
			boolean isSecure = true;
			CreditCardFraudDetection ccfs = new CreditCardFraudDetection(isSecure);
		//  uncomment to turn debugging on
		    ccfs.debug = false; //正式运行的时候改为false
		    ccfs.wsIpaddrRefreshTimeout = 1000 * 8;
		    ccfs.wsIpaddrCacheFile = "D://maxmind.ws.cache";///tmp/maxmind.ws.cache
		    ccfs.useDNS = false;
	
		//  Set timeout to ten seconds
		    ccfs.setTimeout(10);
		//  Set inputs and store them in a hash
		//  See http://www.maxmind.com/app/ccv for more details on the input fields
		    ccfs.input(hm);
		    ccfs.query();
		    hm = ccfs.output();
		    return hm;
	  }
  
}
