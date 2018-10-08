package com.ecpss.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import java.util.*;

import java.util.ArrayList;

import java.util.regex.Matcher;

import java.util.regex.Pattern;

import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;

import java.text.DateFormat;


public class StringUtil {
	
	/**
	 * 
	 * 删除特定字符
	 */
	 public static String deleteAll(String source,char oldc)
     {
           StringBuffer sbu = new StringBuffer();
           //字符串的长度
          int lenOfsource=source.length();
          //指定字符在字符串中的位置
          int i;
          //从指定位置开始寻找
           int posStart;
           //int indexOf(char ch,int intStart)
          //如果ch在字符串中的位置大于intStart
           //返回实际的位置
           //否则返回0或者-1
           //此方法原理:
           //从字符串的第一个位置开始找起
          //如果找到oldc,就截取从posStart开始到i结束的字符串
            //substring(int begin,int end)不包括结束时的字符
          //这样就删除了第一次找到的指定字符
          //由于后面的指定字符肯定在第一次找到的指定字符后面
          //所以再对指定位置posStart在第一次找到的位置上加1
           //posStart=i+1
           for(posStart=0;(i=source.indexOf(oldc,posStart))>=0;posStart=i+1)
          {
                   sbu.append(source.substring(posStart,i));
            }
 
           //经过上一次循环删除了所有的指定字符
            //但如果在此字符后面还有字符串
            //则也要截取到
            //此时posStart的值是最后一个指定字符的位置+1
            //如果posStart小于字符串长度,则肯定还有未添加的字符串
           //所以再加上一个判断
            if(posStart<lenOfsource)
             {
                         sbu.append(source.substring(posStart));
             }
 
             return sbu.toString();
 
      }
	
	
	/** 空构造*/
	private StringUtil() {
	}
	
	/**
	 * 截取有空格的参数去除空格
	 */
	
	public static String removeNullString(String value){
		String string = null;
		if(value==null){
			string = null;
		}else if(value!=null){
			StringTokenizer statSt = new StringTokenizer(value+""," ");
			while(statSt.hasMoreTokens()){		
				string = statSt.nextToken();
			}
		}
		return string;
	}
	
	/*public static void main(String args[]){
	
		System.out.println("----------"+removeNullString("d df"));
	}*/
	
	/**  
	 
	 * 将一个字串的首字母大写  
	 
	 * @param s String 源字串  
	 
	 * @return String 首字母大写后的字串  
	 
	 */

	public static String toUpperCaseFirstLetter(String s) {

		return isNullStr(s)

		? s

		: s.substring(0, 1).toUpperCase() + s.substring(1);

	}

	/**  
	 
	 * 把空字符串转换为empty   
	 
	 * @param s  
	 
	 * @return  
	 
	 * @deprecated  
	 
	 * @see getNotNullStr  
	 
	 */

	public static final String nullToEmptyOfStr(String s) {

		if (s != null)

			return s.trim();

		else

			return "";

	}


	/**  
	 
	 * 取指定字符串的指定长度子字串  
	 
	 * @param strAll  
	 
	 * @param strLen  
	 
	 * @return  
	 
	 */

	public static final String subStr(String strAll, int strLen) {

		String strNew = nullToEmptyOfStr(strAll);

		String myStr = "";

		if (strNew.length() >= strLen) {

			myStr = strNew.substring(0, strLen);

		} else {

			myStr = strNew;

		}

		return myStr;

	}

	


	//字符串替换 s 搜索字符串 s1 要查找字符串 s2 要替换字符串    
	public static String replace(String s, String s1, String s2) {

		if (s == null)

			return null;

		int i = 0;

		if ((i = s.indexOf(s1, i)) >= 0) {

			char ac[] = s.toCharArray();

			char ac1[] = s2.toCharArray();

			int j = s1.length();

			StringBuffer stringbuffer = new StringBuffer(ac.length);

			stringbuffer.append(ac, 0, i).append(ac1);

			i += j;

			int k;

			for (k = i; (i = s.indexOf(s1, i)) > 0; k = i) {

				stringbuffer.append(ac, k, i - k).append(ac1);

				i += j;

			}

			stringbuffer.append(ac, k, ac.length - k);

			return stringbuffer.toString();

		} else {

			return s;

		}

	}

	
	

	/**  
	 
	 * convert Array to ArrayList  
	 
	 * @param sz String[]  
	 
	 * @param len int  
	 
	 * @return ArrayList  
	 
	 */

	public static ArrayList getArrayListFromArray(String[] sz, int len) {

		ArrayList aTmp = new ArrayList();

		if (isNullStr(sz)) {

			for (int i = 0; i < len; i++) {

				aTmp.add("");

			}

		} else {

			for (int i = 0; i < sz.length; i++) {

				aTmp.add(sz[i]);

			}

		}

		return aTmp;

	}

	/**  
	 
	 * Convert to int Base200312291149  
	 
	 * @param o Object  
	 
	 * @return int  
	 
	 */

	public static int cNum(Object o) {

		try {

			return Integer.parseInt(cStr(o));

		} catch (Exception ex) {

			return 0;

		}

	}

	/**  
	 
	 * Convert to String from object Base200312291148  
	 
	 * @param o Object  
	 
	 * @return String  
	 
	 */

	public static String cStr(Object o) {

		return o == null ? "" : o.toString();

	}

	/**  
	 
	 * 判断此字符串是否为空、空字符串，或"null"  
	 
	 * @param str  
	 
	 * @return  
	 
	 */

	public static boolean isNullStr(String s) {

		return (s == null || s.equals("null") || s.equals("")) ? true : false;

	}

	/**  
	 
	 * 如果字符串为空、空字符串，或"null"时返回"0"  
	 
	 * @param str  
	 
	 * @return String  
	 
	 */

	public static String emptyToZero(String str) {

		if (isNullStr(str))

			return "0";

		else

			return str.trim();

	}

	/**  
	 
	 * 判断此字符串是否为空、空字符串，或"null"  
	 
	 * @param str  
	 
	 * @return  
	 
	 */

	public static boolean isNullStr(Object o) {

		return (

		o == null

		|| o.toString().equals("null")

		|| o.toString().equals(""))

		?

		//return (o==null||o.toString().equals(""))?   

		true
				: false;

	}

	/**  
	 
	 * 如果字符串str为空则转换为str1  
	 
	 * @param str  
	 
	 * @param str1  
	 
	 * @return  
	 
	 */

	public static String getNullStr(String str, String str1) {

		if (isNullStr(str))

			return str1;

		else

			return str;

	}

	/**  
	 
	 * 将字符串str转换为GBK编码格式  
	 
	 * @param str  
	 
	 * @return  
	 
	 */

	public static String getGBKStr(String str) {

		try {

			String temp_p;

			temp_p = str;

			//temp_p = getNullStr(temp_p, "");    

			temp_p = nullToEmptyOfStr(temp_p);

			byte[] temp_t = temp_p.getBytes("ISO8859_1");

			String temp = new String(temp_t, "GBK");

			return temp;

		} catch (Exception e) {

			return "";

		}

	}

	public static String getISOStr(String str) {

		try {

			String temp_p;

			temp_p = str;

			temp_p = getNullStr(temp_p, "");

			byte[] temp_t = temp_p.getBytes("ISO8859_1");

			String temp = new String(temp_t);

			return temp;

		} catch (Exception e) {
		}

		return "null";

	}

	/**  
	 
	 * 将字串转成日期，字串格式: yyyy/MM/dd  
	 
	 * @author Base200312111725  
	 
	 * @param string String  
	 
	 * @return Date  
	 
	 */

	public static Date toDate(String string) {

		try {

			DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

			return (Date) formatter.parse(string);

		} catch (Exception ex) {

			System.err.println(

			"[ com.netgate.web.util.StringUtil.toDate(string) ]"

			+ ex.getMessage());

			return null;

		}

	}

	/**  
	 
	 * 将字串转成日期和时间，字串格式: yyyy/MM/dd HH:mm:ss  
	 
	 * @author Base200312111726  
	 
	 * @param string String  
	 
	 * @return Date  
	 
	 */

	public static Date toDatetime(String string) {

		try {

			DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

			return (Date) formatter.parse(string);

		} catch (Exception ex) {

			System.err.println(

			"[ com.netgate.web.util.StringUtil.toDatetime(string) ]"

			+ ex.getMessage());

			return null;

		}

	}

	/**  
	 
	 * 判断单选框是否选中  
	 
	 * @author tempnc20031222  
	 
	 * @param inparam  
	 
	 * @param val  
	 
	 * @return 是否选中  
	 
	 */

	public static String isChecked(String inparam, String val) {

		try {

			if (inparam.trim().equals(val.trim()))

				return "checked";

			else

				return "";

		} catch (Exception ex) {

			System.err.println(

			"[ com.netgate.web.util.StringUtil.toDatetime(string) ]"

			+ ex.getMessage());

			return null;

		}

	}

	/**  
	 
	 * 此方法将给出的字符串source使用delim划分为单词数组。  
	 
	 * @param source 需要进行划分的原字符串  
	 
	 * @param delim 单词的分隔字符串  
	 
	 * @return 划分以后的数组，如果source为null的时候返回以source为唯一元素的数组，  
	 
	 *         如果delim为null则使用逗号作为分隔字符串。  
	 
	 * @since  0.1  
	 
	 */

	public static String[] split(String source, String delim) {

		String[] wordLists;

		if (source == null) {

			wordLists = new String[1];

			wordLists[0] = source;

			return wordLists;

		}

		if (delim == null) {

			delim = ",";

		}

		StringTokenizer st = new StringTokenizer(source, delim);

		int total = st.countTokens();

		wordLists = new String[total];

		for (int i = 0; i < total; i++) {

			wordLists[i] = st.nextToken();

		}

		return wordLists;

	}

	/**  
	 
	 * 此方法将给出的字符串source使用delim划分为单词数组。  
	 
	 * @param source 需要进行划分的原字符串  
	 
	 * @param delim 单词的分隔字符  
	 
	 * @return 划分以后的数组，如果source为null的时候返回以source为唯一元素的数组。  
	 
	 * @since  0.2  
	 
	 */

	public static String[] split(String source, char delim) {

		return split(source, String.valueOf(delim));

	}

	/**  
	 
	 * 此方法将给出的字符串source使用逗号划分为单词数组。  
	 
	 * @param source 需要进行划分的原字符串  
	 
	 * @return 划分以后的数组，如果source为null的时候返回以source为唯一元素的数组。  
	 
	 * @since  0.1  
	 
	 */

	public static String[] split(String source) {

		return split(source, ",");

	}

	/**  
	 
	 * 将set的所有记录并成一个以 delim 分隔的字符串  
	 
	 * @param set  
	 
	 * @param delim  
	 
	 * @return  
	 
	 */

	public static String combine(Set set, String delim) {

		if (set == null || set.size() == 0) {

			return "";

		}

		if (delim == null) {

			delim = "";

		}

		StringBuffer sb = new StringBuffer(100);

		for (Iterator iter = set.iterator(); iter.hasNext();) {

			sb.append(iter.next());

			sb.append(delim);

		}

		if (sb.length() >= delim.length()) {

			sb.delete(sb.length() - 1 - delim.length(), sb.length() - 1);

		}

		return sb.toString();

	}

	/**  
	 
	 * 将set的所有记录并成一个以 , 分隔的字符串  
	 
	 * @param set  
	 
	 * @param delim  
	 
	 * @return  
	 
	 */

	public static String combine(Set set) {

		return combine(set, ",");

	}

	/**  
	 
	 * 将字符串数组合并成一个以 delim 分隔的字符串  
	 
	 * @param array 字符串数组  
	 
	 * @param delim 分隔符，为null的时候使用""作为分隔符（即没有分隔符）  
	 
	 * @return 合并后的字符串  
	 
	 */

	public static String combineArray(String[] array, String delim) {

		if (array == null || array.length == 0) {

			return "";

		}

		int length = array.length - 1;

		if (delim == null) {

			delim = "";

		}

		StringBuffer result = new StringBuffer(length * 8);

		for (int i = 0; i < length; i++) {

			result.append(array[i]);

			result.append(delim);

		}

		result.append(array[length]);

		return result.toString();

	}

	/**  
	 
	 * 将字符串数组合并成一个以,号分隔的字符串  
	 
	 * @param array 字符串数组  
	 
	 * @return 合并后的字符串  
	 
	 */

	public static String combineArray(String[] array) {

		return combineArray(array, ",");

	}

	/**  
	 
	 * 将字符串数组使用指定的分隔符合并成一个字符串。  
	 
	 * @param array 字符串数组  
	 
	 * @param delim 分隔符，为null的时候使用""作为分隔符（即没有分隔符）  
	 
	 * @return 合并后的字符串  
	 
	 * @deprecated  
	 
	 */

	public static String combineStringArray(String[] array, String delim) {

		return combineArray(array, delim);

	}

	/**  
	 
	 * 将int数组使用指定的分隔符合并成一个字符串  
	 
	 * @param array int[] int 数组  
	 
	 * @param delim String 分隔符，为null的时候使用""作为分隔符（即没有分隔符）  
	 
	 * @return String 合并后的字符串  
	 
	 */

	public static String combineArray(int[] array, String delim) {

		if (array == null || array.length == 0) {

			return "";

		}

		int length = array.length - 1;

		if (delim == null) {

			delim = "";

		}

		StringBuffer result = new StringBuffer();

		for (int i = 0; i < length; i++) {

			result.append(Integer.toString(array[i]));

			result.append(delim);

		}

		result.append(Integer.toString(array[length]));

		return result.toString();

	}

	/**  
	 
	 * 将int数组合并成一个以,号分隔的字符串  
	 
	 * @param array 字符串数组  
	 
	 * @return 合并后的字符串  
	 
	 */

	public static String combineArray(int[] array) {

		return combineArray(array, ",");

	}

	/**  
	 
	 * 将字符串List使用指定的分隔符合并成一个字符串。  
	 
	 * @param list List  
	 
	 * @param delim String  
	 
	 * @return String  
	 
	 */

	public static String combineList(List list, String delim) {

		if (list == null || list.size() == 0) {

			return "";

		} else {

			StringBuffer result = new StringBuffer();

			for (int i = 0; i < list.size() - 1; i++) {

				result.append(list.get(i));

				result.append(delim);

			}

			result.append(list.get(list.size() - 1));

			return result.toString();

		}

	}

	/**  
	 
	 * 将字符串List使用 , 合并成一个字符串。  
	 
	 * @param list List  
	 
	 * @return String  
	 
	 */

	public static String combineList(List list) {

		return combineList(list, ",");

	}

	/**  
	 
	 * 将字符串List使用指定的分隔符合并成一个字符串。  
	 
	 * @param list List  
	 
	 * @param delim String  
	 
	 * @return String  
	 
	 * @deprecated see combineList(list, delim)  
	 
	 */

	public static String combineStringList(List list, String delim) {

		return combineList(list, delim);

	}

	/**  
	 
	 * 以指定的字符和长度生成一个该字符的指定长度的字符串。  
	 
	 * @param c 指定的字符  
	 
	 * @param length 指定的长度  
	 
	 * @return 最终生成的字符串  
	 
	 * @since  0.6  
	 
	 */

	public static String fillString(char c, int length) {

		String ret = "";

		for (int i = 0; i < length; i++) {

			ret += c;

		}

		return ret;

	}

	/**  
	 
	 * 字符串数组中是否包含指定的字符串。  
	 
	 * @param strings 字符串数组  
	 
	 * @param string 字符串  
	 
	 * @param caseSensitive 是否大小写敏感  
	 
	 * @return 包含时返回true，否则返回false  
	 
	 * @since  0.4  
	 
	 */

	public static boolean contains(String[] strings, String string,

	boolean caseSensitive) {

		for (int i = 0; i < strings.length; i++) {

			if (caseSensitive == true) {

				if (strings[i].equals(string)) {

					return true;

				}

			} else {

				if (strings[i].equalsIgnoreCase(string)) {

					return true;

				}

			}

		}

		return false;

	}

	/**  
	 
	 * 字符串数组中是否包含指定的字符串。大小写敏感。  
	 
	 * @param strings 字符串数组  
	 
	 * @param string 字符串  
	 
	 * @return 包含时返回true，否则返回false  
	 
	 * @since  0.4  
	 
	 */

	public static boolean contains(String[] strings, String string) {

		return contains(strings, string, true);

	}

	/**  
	 
	 * 不区分大小写判定字符串数组中是否包含指定的字符串。  
	 
	 * @param strings 字符串数组  
	 
	 * @param string 字符串  
	 
	 * @return 包含时返回true，否则返回false  
	 
	 * @since  0.4  
	 
	 */

	public static boolean containsIgnoreCase(String[] strings, String string) {

		return contains(strings, string, false);

	}

	/**  
	 
	 * 得到字符串的字节长度  
	 
	 * @param source 字符串  
	 
	 * @return 字符串的字节长度  
	 
	 * @since  0.6  
	 
	 */

	public static int getByteLength(String source) {

		int len = 0;

		for (int i = 0; i < source.length(); i++) {

			char c = source.charAt(i);

			int highByte = c >>> 8;

			len += highByte == 0 ? 1 : 2;

		}

		return len;

	}

	/**  
	 
	 * 判断字符是否为双字节字符，如中文  
	 
	 * @param c char  
	 
	 * @return boolean  
	 
	 */

	public static boolean isDoubleByte(char c) {

		return !((c >>> 8) == 0);

	}

	/**  
	 
	 * 输出固定字节长度的字符串  
	 
	 * @param source String  
	 
	 * @param len int  
	 
	 * @param exChar String  
	 
	 * @param exStr String  
	 
	 * @return String  
	 
	 */

	public static String getSubStr(String source, int len, String exChar,

	String exStr) {

		if (source == null || getByteLength(source) <= len) {

			return source;

		}

		StringBuffer result = new StringBuffer();

		char c = '\u0000';

		int i = 0, j = 0;

		for (; i < len; j++) {

			result.append(c);

			c = source.charAt(j);

			i += isDoubleByte(c) ? 2 : 1;

		}

		/**  
		 
		 * 到这里i有两种情况：等于len或是len+1，如果是len+1，说明是双字节，并多出一个字节  
		 
		 * 这时候就只能append(exChar)，否则就append(c)  
		 
		 */

		if (i > len) {

			result.append(exChar);

		} else {

			result.append(c);

		}

		result.append(exStr);

		return result.toString();

	}

	public static String getSubStr(String source, int len) {

		return getSubStr(source, len, ".", "...");

	}

	/**  
	 
	 * 判断输入参数是否为null返回一个非null的值  
	 
	 * @param s String 判断的值  
	 
	 * @return String  
	 
	 */

	public static String getNotNullStr(String s) {

		return (s == null) ? "" : s.trim();

	}

	

	/**  
	 
	 * 将字符串的第一个字母大写  
	 
	 * @param s String  
	 
	 * @return String  
	 
	 */

	public static String firstCharToUpperCase(String s) {

		if (s == null || s.length() < 1) {

			return "";

		}

		char[] arrC = s.toCharArray();

		arrC[0] = Character.toUpperCase(arrC[0]);

		return String.copyValueOf(arrC);

		/*  
		 
		 char c = s.charAt(0);  
		 
		 c = Character.toUpperCase(c);  
		 
		 return Character.toString(c) + s.substring(1);  
		 
		 */

	}

	/**  
	 
	 * 根据当前字节长度取出加上当前字节长度不超过最大字节长度的子串  
	 
	 * @param str  
	 
	 * @param currentLen  
	 
	 * @param MAX_LEN  
	 
	 * @return  
	 
	 */

	public static String getSubStr(String str, int currentLen, int MAX_LEN) {

		int i;

		for (i = 0; i < str.length(); i++) {

			if (str.substring(0, i + 1).getBytes().length + currentLen > MAX_LEN) {

				break;

			}

		}

		if (i == 0) {

			return "";

		} else {

			return str.substring(0, i);

		}

	}

	private static String[] arrAntiKeys = new String[] { "抗日", "日货", "游行",
			"示威", "抗议", "钓鱼岛", "法轮", "网络警察", "WWTTJJLL" };

	/**  
	 
	 * 关键字替换成??  
	 
	 * @param keys  
	 
	 * @param arg  
	 
	 * @return  
	 
	 */

	public static String replaceByKeys(String[] keys, String arg) {

		String sbf = arg;

		String[] getarrAntiKeys = arrAntiKeys;

		if ((keys != null) && (keys.length > 0))
			getarrAntiKeys = keys;

		for (int i = 0; i < getarrAntiKeys.length; i++) {

			sbf = sbf.replaceAll(getarrAntiKeys[i], "??");

		}

		return sbf;

	}

	/**  
	 
	 * 关键字删除  
	 
	 * @param parm  
	 
	 * @return  
	 
	 */

	public static String unicodeReplDel(String parm) {

		int area1min = 9601;

		int area1max = 9794;

		int area2min = 12288;

		int area2max = 12311;

		StringBuffer result = new StringBuffer("");

		if (parm.trim().length() > 0) {

			for (int i = 0; i < parm.length(); i++) {

				char c = parm.charAt(i);

				if (!(((int) c >= area1min && (int) c <= area1max) || ((int) c >= area2min && (int) c <= area2max))) {

					result.append(c);

				}

			}

		}

		return result.toString();

	}
	
	
	/**
	 * 分割字符串
	 * @param str 要分割的字符串
	 * @param spilit_sign 字符串的分割标志
	 * @return 分割后得到的字符串数组
	 */
	public static String[] stringSpilit(String str, String spilit_sign) {
		String[] spilit_string = str.split(spilit_sign);
		if (spilit_string[0].equals("")) {
			String[] new_string = new String[spilit_string.length - 1];
			for (int i = 1; i < spilit_string.length; i++)
				new_string[i - 1] = spilit_string[i];
			return new_string;
		} else
			return spilit_string;
	}
	private static String[] FilterChars = {"<","'",">",","," ","%"};
	
	
	/**
	 * 验证非法字符
	 * @return
	 */
	public static boolean validateIllegal(String s){
		for (int j = 0; j < FilterChars.length; j++) {
			int i = s.indexOf(FilterChars[j]);
			if(i>=0){
				return false;
			}
		}
//		String[] str_arr = stringSpilit(s, "");
//		String temp;
//		for (int i = 0; i < str_arr.length; i++) {
//			for (int j = 0; j < FilterChars.length; j++) {
//				if(FilterChars[j].length() > 1){
//					s.substring(0, FilterChars[j].length()-1);
//				}
//				if(FilterChars[j].equals(str_arr[i])){
//					//返回false表示有非法字符
//					return  false;
//				}
//			}
//		}
		return true;
	}
	
	public static void main(String[] args) {
		
		if(!StringUtil.validateIllegal("aaaaa'")){
			System.out.println("aaa");
		}else{
			System.out.println("bbb");
		}
	}
	/**
	 * 验证是否有非法字符
	 * @param s
	 * @return
	 */
	public static boolean validateStr(String s){
		if(s.replaceAll("[a-z]*[A-Z]*\\d*-*_*\\s*", "").length()==0){
		  return true;
		}
		else{
		  return false;
		}
	}
	public static Map createMapFromResponse(String queryString) {
		Map map = new HashMap();
		StringTokenizer st = new StringTokenizer(queryString, "&");
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			int i = token.indexOf('=');
			if (i > 0) {
				try {
					String key = token.substring(0, i);
					String value = URLDecoder.decode(token.substring(i + 1,
							token.length()));
					map.put(key, value);
				} catch (Exception ex) {
					// Do Nothing and keep looping through data
				}
			}
		}
		return map;
	}
	public static String Md5(String plainText ) { 
		String tem="";
		try { 
		MessageDigest md = MessageDigest.getInstance("MD5"); 
		md.update(plainText.getBytes()); 
		byte b[] = md.digest(); 

		int i; 

		StringBuffer buf = new StringBuffer(""); 
		for (int offset = 0; offset < b.length; offset++) { 
		i = b[offset]; 
		if(i<0) i+= 256; 
		if(i<16) 
		buf.append("0"); 
		buf.append(Integer.toHexString(i)); 
		} 

		tem= buf.toString();
		
//		System.out.println("result: " + buf.toString());//32位的加密 
//
//		System.out.println("result: " + buf.toString().substring(8,24));//16位的加密 

		} catch (NoSuchAlgorithmException e) { 
		// TODO Auto-generated catch block 
		e.printStackTrace(); 
		} 
		
		return tem;
		} 
}
