package com.ecpss.util;

public class CN3DPayResult {
	private static String name;
	private static String[] st = {
			"00;承兌或交易成功",
			"03;無效商戶",
			"04;沒收卡",
			"07;特殊條件下沒收卡",
			"12;無效交易",
			"13;無效金額",
			"14;無效卡號",
			"15;無此發卡行",
			"19;重新輸入交易",
			"30;格式錯誤",
			"33;過期的卡 (沒收卡)",
			"34;有作幣嫌疑 (沒收卡)",
			"35;商戶需與收單行保安部門聯系 (沒收卡)",
			"36;受限制的卡 (沒收卡)",
			"37;商戶呼收單行保安部門 (沒收卡)",
			"38;超過允許的PIN試輸入 (沒收卡)",
			"39;無此信用卡賬戶",
			"41;挂失卡 (沒收卡)",
			"43;被窈卡 (沒收卡)",
			"51;無足够的存款",
			"54;過期的卡",
			"55;不正確的PIN",
			"57;不允許持卡人進行的交易",
			"58;不允許終端進行的交易",
			"59;有作幣嫌疑",
			"60;商戶與保安部聯系",
			"61;超出取款金額限制",
			"62;受限制的卡",
			"63;違反安全保密規定",
			"66;商戶呼收單行保安部",
			"68;收到的回答太遲",
			"75;允許的輸入PIN次數超限",
			"76;請聯絡發卡銀行",
			"77;金額錯誤",
			"89;終端機代號錯誤",
			"91;發卡行或交換中心不能操作",
			"92;金融機構或中間網絡設施找不到或無法	達到",
			"93;交易違法，不能完成",
			"94;重覆交易",
			"95;調節控制錯",
			"96;系統失效",
			"98;交換中心收不到發卡行應答",
			"99;格式錯",
			"XX;沒法支持交易",
			"YX;Card Bin not allow DCC transaction",
			"YY;Card Bin allow DCC transaction",
			"YZ;雙幣卡拒付"
	};
	public static String getName(String code) {
	String tem="";
	for(int i=0;i<st.length;i++){
		if(code.equals(st[i].split(";")[0])){
			
	   name=st[i].split(";")[1];
		}
		
	}
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
