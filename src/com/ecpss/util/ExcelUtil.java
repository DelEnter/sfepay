package com.ecpss.util;
import java.io.File;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.PageOrientation;
import jxl.format.PaperSize;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.DateFormat;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import java.util.Date;
public class ExcelUtil {
	
	public void getBackFeeExcel(Long merchantNo, Long batchNo, Long merchantorderNo, String rorderNo, Date applyfortimes, Float orderCount, String isresultName,String isFreezeName, String isTuikuanName, String isHuakuanName, String remark){
		int count = 2;
		try {
			// 打开文件
			WritableWorkbook book1 = Workbook.createWorkbook(new File("E:/temp/output1.xls")); 
			//  生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet  sheet = book1.createSheet("Sheet1",0);
			//设置页面横向
			sheet.setPageSetup(PageOrientation.LANDSCAPE.LANDSCAPE,PaperSize.A4,0.5d,0.5d);
			sheet.setColumnView(120, 120);
			sheet.setRowView(120, 120);		
			/** 定格式 ***********************/
			Label labelthmerno = new Label(0, 0, "商户号");
			Label labelthbatchno = new Label(7, 0, "批次号");
			Label labelthmernoorder = new Label(0, 1, "流水号");
			Label labelthmeoorder = new Label(1, 1, "订单号");
			Label labelthapplyfortime = new Label(2, 1, "日期");
			Label labelthcount = new Label(3, 1, "金额");
			Label labelthstatus = new Label(4, 1, "状态");
			Label labelthisfreeze = new Label(5, 1, "是否冻结");
			Label labelthistuikuan = new Label(6, 1, "是否退款");
			Label labelthishuakuan = new Label(7, 1, "是否划款");
			Label labelthremark = new Label(8, 1, "备注");
			// 将定义好的单元格添加到工作表中
			sheet.addCell(labelthmerno);
			sheet.addCell(labelthbatchno);
			sheet.addCell(labelthmernoorder);
			sheet.addCell(labelthmeoorder);
			sheet.addCell(labelthapplyfortime);
			sheet.addCell(labelthcount);
			sheet.addCell(labelthstatus);
			sheet.addCell(labelthisfreeze);
			sheet.addCell(labelthistuikuan);
			sheet.addCell(labelthishuakuan);
			sheet.addCell(labelthremark);
		
			try {
				 int column=0;   
				 sheet.setColumnView(column++, 15);   
				 sheet.setColumnView(column++, 20);   
				 sheet.setColumnView(column++, 10);   
				 sheet.setColumnView(column++, 10); 
				 sheet.setColumnView(column++, 10);   
				 sheet.setColumnView(column++, 10);   
				 sheet.setColumnView(column++, 10);   
				 sheet.setColumnView(column++, 10);
				 sheet.setColumnView(column++, 10); 
				
				/** 定格式 ***********************/
				Label labelmerno = new Label(1,0,String.valueOf(merchantNo));
				Label lablebatchno =  new Label(8, 0, String.valueOf(batchNo));
				/** 写数据 ***********************/
				Label labelmernoorder = new Label(0, count, String.valueOf(merchantorderNo));
				Label labelmeoorder = new Label(1, count,rorderNo);
				//Label labelapplyfortime = new Label(2, 2+j, "日期");
				jxl.write.DateTime applyfortime = new jxl.write.DateTime(2,count,applyfortimes);
				//Label labelcount = new Label(3, 2+j, "金额");
				jxl.write.Number labelcount = new jxl.write.Number(3, count, orderCount);
				Label labelstatus = new Label(4, count, isresultName);
				Label labelisfreeze = new Label(5, count, isFreezeName);
				Label labelistuikuan = new Label(6, count, isTuikuanName);
				Label labelishuakuan = new Label(7, count, isHuakuanName);
				Label labelremark = new Label(8, count, remark);	
				// 将定义好的单元格添加到工作表中
				sheet.addCell(labelmerno);
				sheet.addCell(lablebatchno);
				sheet.addCell(labelmernoorder);
				sheet.addCell(labelmeoorder);
				sheet.addCell(applyfortime);
				sheet.addCell(labelcount);
				sheet.addCell(labelstatus);
				sheet.addCell(labelisfreeze);
				sheet.addCell(labelistuikuan);
				sheet.addCell(labelishuakuan);
				sheet.addCell(labelremark);	
				count++;
			}
			catch (Exception e) {
				System.out.println(e);
			}
	
		
			//把批次号插入到表中
		
			book1.write();
			book1.close();
			String cmd="rundll32 url.dll FileProtocolHandler file://E:/temp/output1.xls"; 
			Process p = Runtime.getRuntime().exec(cmd); 
		
		} catch (Exception e) {
			e.printStackTrace();
			// this.addFieldError("repeat","数据已经存在了！！");
		
		}
	
	}
	
	public void getExcel(Date huaKuanTime, Long batchNo , Long merchantNo, String accountName, String bank, String cardNo, Float tradeMoney, Integer tradeNumber, Float noTuiKuanMoney, Integer noTuikuanNumber, Float tuiKuanMoney, Integer tuiKuanNumber, Float freezeMoney, Integer freezeNumber, Float thawMoney, Integer thawNumber, Float procedureRate, Float procedureFee,Float countMoney, String depict, String remark)throws Exception{
		
			File tempFile=new File("e:/temp/output.xls");
			//File tempFile=new File("huaKuan.xls");
			WritableWorkbook workbook = Workbook.createWorkbook(tempFile);   
			WritableSheet sheet = workbook.createSheet("TestCreateExcel", 0);    
			  
			//一些临时变量，用于写到excel中   
			Label l=null;   
			jxl.write.Number n=null;   
			jxl.write.DateTime d=null;   
			
			//用于设置字体
			WritableFont BoldFont  =   new  WritableFont(WritableFont.ARIAL,  14 , WritableFont.BOLD);  
			WritableFont headerFont = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, 
			    false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLUE);    
			WritableCellFormat headerFormat = new WritableCellFormat (headerFont);    
			   
			WritableFont titleFont = new WritableFont(WritableFont.ARIAL, 14, WritableFont.NO_BOLD,
			     false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.RED);   
			WritableCellFormat titleFormat = new WritableCellFormat (titleFont);    
			   
			WritableFont detFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, 
			    false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);    
			WritableCellFormat detFormat = new WritableCellFormat (detFont);    
			NumberFormat nf=new NumberFormat("0.00000"); //用于Number的格式   
			WritableCellFormat priceFormat = new WritableCellFormat (detFont, nf);    
			   
			DateFormat df=new DateFormat("yyyy-MM-dd");//用于日期的   
			WritableCellFormat dateFormat = new WritableCellFormat (detFont, df);    
			   
			//用于标题    
			WritableCellFormat wcf_title  =   new  WritableCellFormat(BoldFont);   
	        wcf_title.setBorder(Border.NONE, BorderLineStyle.THIN);  //  线条    
	        wcf_title.setVerticalAlignment(VerticalAlignment.CENTRE);  //  垂直对齐    
	        wcf_title.setAlignment(Alignment.CENTRE);  //  水平对齐    
	        wcf_title.setWrap( true );  
			
	        WritableFont NormalFont  =   new  WritableFont(WritableFont.ARIAL,  13 ); 
			WritableCellFormat wcf_merge  =   new  WritableCellFormat(NormalFont);   
		    wcf_merge.setBorder(Border.ALL, BorderLineStyle.THIN);  //  线条    
		    wcf_merge.setVerticalAlignment(VerticalAlignment.CENTRE);  //  垂直对齐    
		    wcf_merge.setAlignment(Alignment.LEFT);   
		    wcf_merge.setWrap( true );  
			 //剩下的事情，就是用上面的内容和格式创建一些单元格，再加到sheet中   
	       
	        //标题
			 sheet.addCell( new  Label( 0 ,  0 ,  " 预览生成划款表 " ,   wcf_title));
			 //实现跨行
			 sheet.mergeCells( 0 ,  0 , 3 , 0 ); 
			 //设置高度
			 sheet.setRowView( 0 ,  1000 ,  false ); 
			 //add Title   
			 int column=0;   
			 int i=0;  
			 column=0;
			 l=new Label(column++, 1, "划款时间："+huaKuanTime+"                                     批次号："+batchNo+"", detFormat);
			 //设置高度
			 sheet.setRowView( 1 ,  500 ,  false ); 
			 //实现跨行
			 sheet.mergeCells( 0 ,  1 , 3 , 1 );   
			 sheet.addCell(l);
			 /**
			  * 商户号
			  */
			 column=0;
			 l=new Label(column++, 2, "商户号", wcf_merge);
			 sheet.addCell(l);   
			 n=new jxl.write.Number(column++, 2, merchantNo, wcf_merge);  
			 sheet.addCell(n);   
			 //实现跨行
			 sheet.mergeCells( 1 ,  2 , 3 , 2 ); 
			 //设置高度
			 sheet.setRowView( 2 ,  600 ,  false );
			 
			 /**
			  *收款人 
			  */
			 column=0;   
			 l=new Label(column++, i+3, "收款人", wcf_merge);   
			 //实现跨列
			 sheet.mergeCells( 0 ,  3 , 0 , 5 ); 
			 sheet.addCell(l);   
			 //设置高度
			 sheet.setRowView( 3 ,  600 ,  false );
			 
			 l=new Label(column++, i+3, "开户名", wcf_merge);   
			 sheet.addCell(l);   
			 l=new Label(column++, i+3, accountName, wcf_merge);   
			 sheet.addCell(l);   
			 //实现跨行
			 sheet.mergeCells( 2 ,  3 , 3 , 3 ); 
			 //设置高度
			 sheet.setRowView( 3 ,  600 ,  false );
	 
			 i++;
			 column=1;   
			 l=new Label(column++, i+3, "开户行 ", wcf_merge);   
			 sheet.addCell(l);   
			 l=new Label(column++, i+3, bank, wcf_merge);   
			 sheet.addCell(l);      
			 //实现跨行
			 sheet.mergeCells( 2 ,  4 , 3 , 4 ); 
			 //设置高度
			 sheet.setRowView( 4 ,  600 ,  false );
			 
			 i++;   
			 column=1;   
			 l=new Label(column++, i+3, "帐号 ", wcf_merge);   
			 sheet.addCell(l);   
			 l=new Label(column++, i+3, cardNo, wcf_merge);   
			 sheet.addCell(l);   
			 //实现跨行
			 sheet.mergeCells( 2 ,  5 , 3 , 5 ); 
			 //设置高度
			 sheet.setRowView( 5 ,  600 ,  false );

			 /**
			  * 项目
			  */
			
			 i++;
			 column=0;   
			 l=new Label(column++, i+3, "项目", wcf_merge);   
			 sheet.addCell(l); 
			 column=3;
			 l=new Label(column++, i+3, "备注", wcf_merge);   
			 sheet.addCell(l); 
			 //设置高度
			 sheet.setRowView( 6 ,  600 ,  false );

			 /**
			  * 本期结算交易
			  */
			
			 i++;
			 column=0;   
			 l=new Label(column++, i+3, "本期结算交易", wcf_merge);   
			 //实现跨列
			 sheet.mergeCells( 0 ,  7 , 0 , 8 );
			 sheet.addCell(l); 
			 //设置高度
			 sheet.setRowView( 7 ,  600 ,  false  );
			 
			 /*笔数*/   
			 l=new Label(column++, i+3, "笔数", wcf_merge);   
			 //实现跨列
			 sheet.mergeCells(1 ,  7 , 1 , 7 );
			 sheet.addCell(l); 
			 n=new jxl.write.Number(column++, i+3, tradeNumber, wcf_merge); 
			 sheet.addCell(n); 
			 l=new Label(column++, i+3, "", wcf_merge);   
			 sheet.addCell(l); 
			 
			 //设置高度
			 sheet.setRowView( 8 ,  600 ,  false  );
			 
			 /*金额*/ 
			 i++;
			 column=1;  
			 l=new Label(column++, i+3, "金额", wcf_merge);   
			 //实现跨列
			 sheet.mergeCells(1 ,  8 , 1 , 8 );
			 sheet.addCell(l); 
			 n=new jxl.write.Number(column++, i+3, tradeMoney, wcf_merge); 
			 sheet.addCell(n); 
			 l=new Label(column++, i+3, "", wcf_merge);   
			 sheet.addCell(l); 
			 //设置高度
			 sheet.setRowView( 9 ,  600 ,  false  );
			 
			 /**
			  * 未结算本期退款
			  */
			 
			 i++;
			 column=0;   
			 l=new Label(column++, i+3, "未结算本期退款", wcf_merge);   
			 //实现跨列
			 sheet.mergeCells( 0 ,  9 , 0 , 10 );
			 sheet.addCell(l); 
			 //设置高度
			 sheet.setRowView( 10 ,  600 ,  false  );
			 
			 
			 /*笔数*/   
			 l=new Label(column++, i+3, "笔数", wcf_merge);   
			 //实现跨列
			 sheet.mergeCells(1 ,  9 , 1 , 9 );
			 sheet.addCell(l); 
			 if(noTuikuanNumber!=null){
				 n=new jxl.write.Number(column++, i+3, noTuikuanNumber, wcf_merge); 
			 }else{
				 n=new jxl.write.Number(column++, i+3, 0, wcf_merge); 
			 }
			 sheet.addCell(n); 
			 l=new Label(column++, i+3, "", wcf_merge);   
			 sheet.addCell(l); 
			 //设置高度
			 sheet.setRowView( 10 ,  600 ,  false  );
			 
			 /*金额*/ 
			 i++;
			 column=1;  
			 l=new Label(column++, i+3, "金额", wcf_merge);   
			 //实现跨列
			 sheet.mergeCells(1 ,  10 , 1 , 10 );
			 sheet.addCell(l); 
			 if(noTuiKuanMoney!=null){
				 n=new jxl.write.Number(column++, i+3, noTuiKuanMoney, wcf_merge); 
			 }else{
				 n=new jxl.write.Number(column++, i+3, 0, wcf_merge); 
			 }
			
			 sheet.addCell(n); 
			 l=new Label(column++, i+3, "", wcf_merge);   
			 sheet.addCell(l); 
			 //设置高度
			 sheet.setRowView( 11 ,  600 ,  false  );
			 
			 /**
			  * 已经结算本期退款
			  */		 
			 i++;
			 column=0;   
			 l=new Label(column++, i+3, "已经算本期退款", wcf_merge);   
			 //实现跨列
			 sheet.mergeCells( 0 ,  11 , 0 , 12 );
			 sheet.addCell(l); 
			 //设置高度
			 sheet.setRowView( 12 ,  600 ,  false  );
			 
			 
			 /*笔数*/   
			 l=new Label(column++, i+3, "笔数", wcf_merge);   
			 //实现跨列
			 sheet.mergeCells(1 ,  113 , 1 , 11 );
			 sheet.addCell(l); 
			 if(tuiKuanNumber!=null){
				 n=new jxl.write.Number(column++, i+3, tuiKuanNumber, wcf_merge); 
			 }else{
				 n=new jxl.write.Number(column++, i+3, 0, wcf_merge); 
			 }
			
			 sheet.addCell(n); 
			 l=new Label(column++, i+3, "", wcf_merge);   
			 sheet.addCell(l); 
			 //设置高度
			 sheet.setRowView( 12 ,  600 ,  false  );
			 
			 /*金额*/ 
			 i++;
			 column=1;  
			 l=new Label(column++, i+3, "金额", wcf_merge);   
			 //实现跨列
			 sheet.mergeCells(1 ,  12 , 1 , 12 );
			 sheet.addCell(l); 
			 if(tuiKuanMoney!=null){
				 n=new jxl.write.Number(column++, i+3, tuiKuanMoney, wcf_merge); 
			 }else{
				 n=new jxl.write.Number(column++, i+3, 0, wcf_merge); 
			 }
			 
			 sheet.addCell(n); 
			 l=new Label(column++, i+3, "", wcf_merge);   
			 sheet.addCell(l); 
			 //设置高度
			 sheet.setRowView( 13 ,  600 ,  false  );
			 
			 /**
			  * 已经结算本期冻结
			  */		 
			 i++;
			 column=0;   
			 l=new Label(column++, i+3, "已经算本期冻结", wcf_merge);   
			 //实现跨列
			 sheet.mergeCells( 0 ,  13 , 0 , 14 );
			 sheet.addCell(l); 
			 //设置高度
			 sheet.setRowView( 14 ,  600 ,  false  );
			 
			 
			 /*笔数*/   
			 l=new Label(column++, i+3, "笔数", wcf_merge);   
			 //实现跨列
			 sheet.mergeCells(1 ,  13 , 1 , 13 );
			 sheet.addCell(l); 
			 if(tuiKuanNumber!=null){
				 n=new jxl.write.Number(column++, i+3, tuiKuanNumber, wcf_merge); 
			 }else{
				 n=new jxl.write.Number(column++, i+3, 0, wcf_merge); 
			 }
			 
			 sheet.addCell(n); 
			 l=new Label(column++, i+3, "", wcf_merge);   
			 sheet.addCell(l); 
			 //设置高度
			 sheet.setRowView( 14 ,  600 ,  false  );
			 
			 /*金额*/ 
			 i++;
			 column=1;  
			 l=new Label(column++, i+3, "金额", wcf_merge);   
			 //实现跨列
			 sheet.mergeCells(1 ,  14 , 1 , 14 );
			 sheet.addCell(l); 
			 if(freezeMoney!=null){
				 n=new jxl.write.Number(column++, i+3, freezeMoney, wcf_merge); 
			 }else{
				 n=new jxl.write.Number(column++, i+3, 0, wcf_merge); 
			 }
			
			 sheet.addCell(n); 
			 l=new Label(column++, i+3, "", wcf_merge);   
			 sheet.addCell(l); 
			 //设置高度
			 sheet.setRowView( 15 ,  600 ,  false  );
			 
			 /**
			  * 冻结交易
			  */		 
			 i++;
			 column=0;   
			 l=new Label(column++, i+3, " 冻结交易", wcf_merge);   
			 //实现跨列
			 sheet.mergeCells( 0 ,  15 , 0 , 16 );
			 sheet.addCell(l); 
			 
			 //设置高度
			 sheet.setRowView( 16 ,  600 ,  false  );
			 
			 
			 /*笔数*/   
			 l=new Label(column++, i+3, "笔数", wcf_merge);   
			 //实现跨列
			 sheet.mergeCells(1 ,  15 , 1 , 15 );
			 sheet.addCell(l); 
			 if(thawNumber!=null){
				 n=new jxl.write.Number(column++, i+3, thawNumber, wcf_merge); 
			 }else{
				 n=new jxl.write.Number(column++, i+3, 0, wcf_merge); 
			 }
			 
			 sheet.addCell(n); 
			 l=new Label(column++, i+3, "", wcf_merge);   
			 sheet.addCell(l); 
			 //设置高度
			 sheet.setRowView( 16 ,  600 ,  false  );
			 
			 /*金额*/ 
			 i++;
			 column=1;  
			 l=new Label(column++, i+3, "金额", wcf_merge);   
			 //实现跨列
			 sheet.mergeCells(1 ,  16 , 1 , 16 );
			 sheet.addCell(l); 
			 if(thawMoney!=null){
				 n=new jxl.write.Number(column++, i+3, thawMoney, wcf_merge); 
			 }else{
				 n=new jxl.write.Number(column++, i+3, 0, wcf_merge); 
			 }
			
			 sheet.addCell(n); 
			 l=new Label(column++, i+3, "", wcf_merge);   
			 sheet.addCell(l); 
			 //设置高度
			 sheet.setRowView( 17 ,  600 ,  false  );
			 
			 /**
			  * 手续费率
			  */
			 i++;
			 column=0;  
			 l=new Label(column++, i+3, "手续费率", wcf_merge);   
			 sheet.addCell(l); 
			 n=new jxl.write.Number(column++, i+3, procedureRate, wcf_merge);   
			 sheet.addCell(n); 
			 column=3;  
			
			 //实现跨列
			 sheet.mergeCells(1 ,  17 , 2 , 17 );
			 l=new Label(column++, i+3, "", wcf_merge);   
			 sheet.addCell(l); 
			 //设置高度
			 sheet.setRowView( 18 ,  600 ,  false  );
			 /**
			  * 手续费
			  */
			 i++;
			 column=0;  
			 l=new Label(column++, i+3, "手续费", wcf_merge);   
			 sheet.addCell(l); 
			 n=new jxl.write.Number(column++, i+3, procedureFee, wcf_merge);  
			 sheet.addCell(n);
			 column=3;  
			 //实现跨列
			 sheet.mergeCells(1 ,  18 , 2 , 18 );
			 l=new Label(column++, i+3, "", wcf_merge);   
			 sheet.addCell(l); 
			 //设置高度
			 sheet.setRowView( 19 ,  600 ,  false  );

			 
			 /**
			  * 应结算金额
			  */
			 i++;
			 column=0;  
			 l=new Label(column++, i+3, "应结算金额(人民币)", wcf_merge);   
			 sheet.addCell(l); 
			 n=new jxl.write.Number(column++, i+3, countMoney, wcf_merge);  
			 sheet.addCell(n);
			 //实现跨列
			 sheet.mergeCells(1 ,  19 ,3 , 19 ); 
			 /**
			  * 描述
			  */
			 i++;
			 column=0;  
			 l=new Label(column++, i+3, "描述", wcf_merge);   
			 sheet.addCell(l); 
			 if(depict!=null){
				 l=new Label(column++, i+3, depict, wcf_merge); 
			 }else{
				 l=new Label(column++, i+3, "", wcf_merge); 
			 }
			 
			 
			 //实现跨列
			 
			 sheet.mergeCells(1 ,  20 , 3 , 20 );
			 sheet.addCell(l);
			 //设置高度
			 sheet.setRowView( 20 ,  800 ,  false  );
			 
			 //设置列的宽度   
			 column=0;   
			 sheet.setColumnView(column++, 22);   
			 sheet.setColumnView(column++, 15);   
			 sheet.setColumnView(column++, 30);   
			 sheet.setColumnView(column++, 20);   
			 workbook.write();   
			 workbook.close();   
			 //自动打开Excel文档
			 String cmd="rundll32 url.dll FileProtocolHandler file://e:/temp/output.xls"; 
			 Process p = Runtime.getRuntime().exec(cmd); 

	}
}
