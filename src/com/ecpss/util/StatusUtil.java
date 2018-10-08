package com.ecpss.util;
//状态值的获取与更新
public class StatusUtil {
	/*
	 * 获取状态值 resource 状态 index 占位
	 */
	public static String getStatus(String resource, int index) {
		String str = resource.charAt(index-1) + "";
		return str;
	}

	/*
	 * 更新状态 resource 原状态，index 占位 ， updateStatus 更新的状态
	 * 
	 */
	public static String updateStatus(String resource, int index,
			String updateStatus) {
		char[] st = resource.toCharArray();
		st[index - 1] = updateStatus.toCharArray()[0];
		return String.valueOf(st);
	}
}
