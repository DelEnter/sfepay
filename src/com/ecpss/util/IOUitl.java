package com.ecpss.util;

import java.io.*;

import org.apache.commons.io.IOUtils;


public class IOUitl 
{
	/**
	 * 
	 * @param source source file path
	 * @param target target file path
	 * @throws IOException
	 */
	public static void copyFile(String source, String target) throws IOException
	{
		InputStream is = new FileInputStream(new File(source));
        OutputStream os = new FileOutputStream(new File(target));
        IOUtils.copy(is, os);
        is.close();
        os.close();
	}
}
