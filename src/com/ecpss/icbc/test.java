package com.ecpss.icbc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class test {
	private IcbcUtils icbcUtils = new IcbcUtils();

	// public static void main(String[] args) {
	// test t = new test();
	// t.t();
	// }

	public void t() {

		// String tranData = "<?xml version=\"1.0\" encoding=\"GBK\"
		// standalone=\"no\"?><B2CReq><interfaceName>ICBC_PERBANK_B2C</interfaceName><interfaceVersion>1.0.0.3</interfaceVersion><orderInfo><orderDate>20070725105014</orderDate><orderid>20070725105014-2134062548</orderid><amount>20</amount><curType>001</curType><merID>0200EC20000875</merID><merAcct>0200020409015029130</merAcct></orderInfo><custom><verifyJoinFlag>0</verifyJoinFlag><Language>ZH_CN</Language></custom><message><goodsID>001</goodsID><goodsName>威尼熊</goodsName><goodsNum>2</goodsNum><carriageAmt>20</carriageAmt><merHint>请保留包装</merHint><remark1></remark1><remark2></remark2><merURL>http://localhost:9081/Newb2c_Pay_Mer.jsp</merURL><merVAR>test</merVAR></message></B2CReq>";
		// //String mersign = icbcUtils.getCertMsgBase64(tranData,"12345678");
		// // String mersign =
		// "PD94bWwgIHZlcnNpb249IjEuMCIgZW5jb2Rpbmc9IkdCSyIgc3RhbmRhbG9uZT0ibm8iID8+PEIyQ1Jlcz48aW50ZXJmYWNlTmFtZT5JQ0JDX1BFUkJBTktfQjJDPC9pbnRlcmZhY2VOYW1lPjxpbnRlcmZhY2VWZXJzaW9uPjEuMC4wLjM8L2ludGVyZmFjZVZlcnNpb24+PG9yZGVySW5mbz48b3JkZXJEYXRlPjIwMDcwNzI1MTA1MDE0PC9vcmRlckRhdGU+PG9yZGVyaWQ+MjAwNzA3MjUxMDUwMTQtMjEzNDA2MjU0ODwvb3JkZXJpZD48YW1vdW50PjIwPC9hbW91bnQ+PGN1clR5cGU+MDAxPC9jdXJUeXBlPjxtZXJJRD4wMjAwRUMyMDAwMDg3NTwvbWVySUQ+PG1lckFjY3Q+MDIwMDAyMDQwOTAxNTAyOTEzMDwvbWVyQWNjdD48L29yZGVySW5mbz48Y3VzdG9tPjx2ZXJpZnlKb2luRmxhZz4wPC92ZXJpZnlKb2luRmxhZz48Sm9pbkZsYWc+PC9Kb2luRmxhZz48VXNlck51bT48L1VzZXJOdW0+PC9jdXN0b20+PGJhbms+PFRyYW5TZXJpYWxObz48L1RyYW5TZXJpYWxObz48bm90aWZ5RGF0ZT4yMDA3MDcyNTExMDQwMDwvbm90aWZ5RGF0ZT48dHJhblN0YXQ+MTwvdHJhblN0YXQ+PGNvbW1lbnQ+vbvS17PJuaajoTwvY29tbWVudD48L2Jhbms+PC9CMkNSZXM+";
		// //
		// // System.out.println("加密:"+mersign);
		// // System.out.println("解码:"+icbcUtils.signdec(mersign));
		// //
		// String tranDataStr = "<?xml version=\"1.0\" encoding=\"GBK\"
		// standalone=\"no\"
		// ?><B2CRes><interfaceName>ICBC_PERBANK_B2C</interfaceName><interfaceVersion>1.0.0.3</interfaceVersion><orderInfo><orderDate>20110228093814</orderDate><orderid>005</orderid><amount>10</amount><curType>001</curType><merID>2102EC20000767</merID><merAcct>2102108019300057896</merAcct></orderInfo><custom><verifyJoinFlag>0</verifyJoinFlag><JoinFlag></JoinFlag><UserNum></UserNum></custom><bank><TranSerialNo></TranSerialNo><notifyDate>20110228094027</notifyDate><tranStat>2</tranStat><comment>客户放弃交易！</comment></bank></B2CRes>";
		// String noti =
		// "PD94bWwgIHZlcnNpb249IjEuMCIgZW5jb2Rpbmc9IkdCSyIgc3RhbmRhbG9uZT0ibm8iID8+PEIyQ1Jlcz48aW50ZXJmYWNlTmFtZT5JQ0JDX1BFUkJBTktfQjJDPC9pbnRlcmZhY2VOYW1lPjxpbnRlcmZhY2VWZXJzaW9uPjEuMC4wLjM8L2ludGVyZmFjZVZlcnNpb24+PG9yZGVySW5mbz48b3JkZXJEYXRlPjIwMTEwMjI4MDkzODE0PC9vcmRlckRhdGU+PG9yZGVyaWQ+MDA1PC9vcmRlcmlkPjxhbW91bnQ+MTA8L2Ftb3VudD48Y3VyVHlwZT4wMDE8L2N1clR5cGU+PG1lcklEPjIxMDJFQzIwMDAwNzY3PC9tZXJJRD48bWVyQWNjdD4yMTAyMTA4MDE5MzAwMDU3ODk2PC9tZXJBY2N0Pjwvb3JkZXJJbmZvPjxjdXN0b20+PHZlcmlmeUpvaW5GbGFnPjA8L3ZlcmlmeUpvaW5GbGFnPjxKb2luRmxhZz48L0pvaW5GbGFnPjxVc2VyTnVtPjwvVXNlck51bT48L2N1c3RvbT48YmFuaz48VHJhblNlcmlhbE5vPjwvVHJhblNlcmlhbE5vPjxub3RpZnlEYXRlPjIwMTEwMjI4MDk0MDI3PC9ub3RpZnlEYXRlPjx0cmFuU3RhdD4yPC90cmFuU3RhdD48Y29tbWVudD6/zbunt8XG+r270tejoTwvY29tbWVudD48L2Jhbms+PC9CMkNSZXM+";
		// String sign =
		// "quWsGErJ208HzWbZFPLcV+IqdfjD2jO64xMkXZir6D8bD8t0jSpHk9VG0yJnybyDcoCkqwg6W0egjqB5j//VDoBmadMWj7+5y3FgN0Lrhn2oBtfBiiis11OxauZwFhx2GS5tsQJfOYf3KP/ezVk+M758EnK32kD0C46mN9bsY4M=";
		// icbcUtils.verifySignres(tranDataStr,noti,sign);
		// String
		// aaa="%3C%3Fxml++version%3D%221.0%22+encoding%3D%22GB2312%22+standalone%3D%22no%22+%3F%3E%0A%3CICBCAPI%3E%0A%3Cpub%3E%0A%3CAPIName%3EEAPI%3C%2FAPIName%3E%0A%3CAPIVersion%3E001.001.002.001%3C%2FAPIVersion%3E%0A%3C%2Fpub%3E%0A%3Cin%3E%0A%3CorderNum%3E005%3C%2ForderNum%3E%0A%3CtranDate%3E20110302%3C%2FtranDate%3E%0A%3CShopCode%3E2102EC23720701%3C%2FShopCode%3E%0A%3CShopAccount%3E2102102019300750985%3C%2FShopAccount%3E%0A%3C%2Fin%3E%0A%3Cout%3E%0A%3CtranSerialNum%3EHFG000002020401199%3C%2FtranSerialNum%3E%0A%3CtranStat%3E1%3C%2FtranStat%3E%0A%3CbankRem%3E%3C%2FbankRem%3E%0A%3Camount%3E1%3C%2Famount%3E%0A%3CcurrType%3E001%3C%2FcurrType%3E%0A%3CtranTime%3E111754%3C%2FtranTime%3E%0A%3CShopAccount%3E2102102019300750985%3C%2FShopAccount%3E%0A%3CPayeeName%3E%C4%CF%C4%FE%CA%D0%B0%B2%D1%B8%BF%C6%BC%BC%D3%D0%CF%DE%B9%AB%CB%BE%3C%2FPayeeName%3E%0A%3CJoinFlag%3E0%3C%2FJoinFlag%3E%0A%3CMerJoinFlag%3E%3C%2FMerJoinFlag%3E%0A%3CCustJoinFlag%3E0%3C%2FCustJoinFlag%3E%0A%3CCustJoinNum%3E%3C%2FCustJoinNum%3E%0A%3CCertID%3EANXUN88.e.2102%3C%2FCertID%3E%0A%3C%2Fout%3E%0A%3C%2FICBCAPI%3E%0A";
		// try {
		// String bbb=URLDecoder.decode(aaa,"GBK");
		// System.out.println(bbb);
		// } catch (UnsupportedEncodingException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// String
		// notifyData="PD94bWwgIHZlcnNpb249IjEuMCIgZW5jb2Rpbmc9IkdCSyIgc3RhbmRhbG9uZT0ibm8iID8+PEIyQ1Jlcz48aW50ZXJmYWNlTmFtZT5JQ0JDX1BFUkJBTktfQjJDPC9pbnRlcmZhY2VOYW1lPjxpbnRlcmZhY2VWZXJzaW9uPjEuMC4wLjM8L2ludGVyZmFjZVZlcnNpb24+PG9yZGVySW5mbz48b3JkZXJEYXRlPjIwMTEwMzAyMTExNDQ3PC9vcmRlckRhdGU+PG9yZGVyaWQ+MDA1PC9vcmRlcmlkPjxhbW91bnQ+MTwvYW1vdW50PjxjdXJUeXBlPjAwMTwvY3VyVHlwZT48bWVySUQ+MjEwMkVDMjM3MjA3MDE8L21lcklEPjxtZXJBY2N0PjIxMDIxMDIwMTkzMDA3NTA5ODU8L21lckFjY3Q+PC9vcmRlckluZm8+PGN1c3RvbT48dmVyaWZ5Sm9pbkZsYWc+MDwvdmVyaWZ5Sm9pbkZsYWc+PEpvaW5GbGFnPjwvSm9pbkZsYWc+PFVzZXJOdW0+PC9Vc2VyTnVtPjwvY3VzdG9tPjxiYW5rPjxUcmFuU2VyaWFsTm8+SEZHMDAwMDAyMDIwNDAxMTk5PC9UcmFuU2VyaWFsTm8+PG5vdGlmeURhdGU+MjAxMTAzMDIxMTE4MTA8L25vdGlmeURhdGU+PHRyYW5TdGF0PjE8L3RyYW5TdGF0Pjxjb21tZW50Pr270tezybmmo6E8L2NvbW1lbnQ+PC9iYW5rPjwvQjJDUmVzPg==";
		// HashMap<String,String> payResultHashMap = new
		// HashMap<String,String>();
		// Dom4jForXMLUtils dom4j = new Dom4jForXMLUtils();
		//
		// String xmlnotifyData = icbcUtils.signdec(notifyData);
		// payResultHashMap = dom4j.parse(xmlnotifyData);
	}

	public static void main(String[] args) throws ParseException {
		String start = "2011-01-27";
		String end = "2011-03-04";
		// 字符串转换成日期
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = format.parse(start);
		Calendar startTime = Calendar.getInstance();
		startTime.clear();
		startTime.setTime(startDate);
		int startYear = startTime.get(Calendar.YEAR);
		int startMonth = startTime.get(Calendar.MONTH);
		Date endDate = format.parse(end);
		Calendar endTime = Calendar.getInstance();
		endTime.clear();
		endTime.setTime(endDate);
		int endYear = endTime.get(Calendar.YEAR);
		int endMonth = endTime.get(Calendar.MONTH);
		System.out.println("start date : " + start);
		System.out.println("end date : " + end);
		List<String> list = new ArrayList<String>();
		for (int x = startYear; x <= endYear; x++) {
			// 循环每个月
			// 如果在日期范围内月份循环时自增到了一年的最后一个月就将月份初始化到一月份
			int y = 0;
			// 如果是开始日期的第一个年的月数就从开始月数循环
			if (x == startYear) {
				y = startMonth;
			}
			for (; y < 12; y++) {
				int ty = y + 1;
				System.out.println(x + "-" + ty + "");
				String d =x + "-" + ty;
				list.add(d);
				// 如果已经遍历过了截至日期的最后月份就中止月份的循环
				if (x == endYear && y == endMonth) {
					break;
				}
			}
		}
		String[] tem = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			tem[i] = list.get(i);
		}
	}

}
