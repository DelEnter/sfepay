package com.ecpss.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;


/**
 * @author T-08-D-120
 * 
 */
public class UploadUtils {
	private static Logger logger = Logger.getLogger(UploadUtils.class);
	private static Properties p = new Properties();
	static {
		InputStream stream = UploadUtils.class.getClassLoader().getResourceAsStream("ecpss.properties");
		try {
			p.load(stream);
			stream.close();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}
	/**
	 * 获得上传的根目录
	 * @return
	 */
	public static String getUploadDir(){
		return p.getProperty("upload_dir");
	}
	/**
	 * 获得退款上传的根目录
	 * @return
	 */
	public static String getRefundUploadDir(){
		return p.getProperty("refund_dir");
	}
	/**
	 * 获得上传文件的相对路�?
	 * @param fileName
	 * @return
	 */
	public static String getUploadFileOpPath(String fileName){
		return DateUtil.getDate()+ "/" + fileName;
	}
}
