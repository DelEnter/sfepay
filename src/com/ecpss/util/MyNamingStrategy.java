package com.ecpss.util;

import java.util.Calendar;

import org.hibernate.cfg.DefaultNamingStrategy;

public class MyNamingStrategy extends DefaultNamingStrategy { 
    public static final MyNamingStrategy INSTANCE = new MyNamingStrategy(); 
    public String classToTableName(String className) { 
    return "biz_" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH); 
    } 
} 