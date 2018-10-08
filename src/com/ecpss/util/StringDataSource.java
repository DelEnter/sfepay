package com.ecpss.util;
//mymail.jaf.StringDataSource.Java   
//import   javax.activation.*;   
public   class   StringDataSource   implements   javax.activation.DataSource{   
  private   java.lang.String   data;   
  private   java.lang.String   type;     
  public   StringDataSource(java.lang.String   data,java.lang.String   type)   
{   
          this.data   =   data;   
          this.type   =   type;   
  }     
public   java.io.InputStream   getInputStream()   throws   java.io.IOException   
{   
    return   new   java.io.StringBufferInputStream(data);   
}     
public   java.io.OutputStream   getOutputStream()   throws   java.io.IOException   
{   
    throw   new   java.io.IOException("it   does   not   support   this   method   now!");   
    }     
    public   java.lang.String   getContentType(){   
                return   type;   
}     
  public   java.lang.String   getName(){   
      return   "   mymail   ";   
}   
}   
  
