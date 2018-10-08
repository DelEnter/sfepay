package com.ecpss.util;

public class CalcuLate {

	public String getValue(double num1, double num2) {
		java.text.DecimalFormat df = new java.text.DecimalFormat("#.##");
		double value = num1 / (num1 + num2);
		String temp = df.format(value);
		value = Double.parseDouble(temp)*100;
		return df.format(value).toString() + "%";
	}
	
	public String getValue2(double num1, double num2) {
		java.text.DecimalFormat df = new java.text.DecimalFormat("#.##");
		double value = (num1 / num2) * 100;
		return df.format(value).toString() + "%";
	}
	public static void main(String[] args) {
		CalcuLate c = new CalcuLate();
		System.out.println(c.getValue(0, 3));
	}

}
