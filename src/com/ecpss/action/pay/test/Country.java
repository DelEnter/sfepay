package com.ecpss.action.pay.test;

public class Country {
private String code;
private String name;
private String[] country={"110;HKD","116;JPY","121;MOP","129;PHP","132;SGD","142;CNY","300;EUR","303;GBP","331;CHF"
,"501;CAD","502;USD","601;AUD","609;NZD"		
};

public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public String getName() {
	String tem="";
	for(int i=0;i<country.length;i++){
		if(country[i].split(";")[0].equals(this.code)){
		       tem=country[i].split(";")[1];
		}
		
	}
	
	
	return tem;
}
public void setName(String name) {
	this.name = name;
}

}
