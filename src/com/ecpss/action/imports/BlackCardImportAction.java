package com.ecpss.action.imports;

import java.io.File;

import jxl.Workbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ecpss.action.BaseAction;
import com.ecpss.excel.builder.RowResult;
import com.ecpss.excel.builder.jexcel.JExcelRowObjectBuilder;
import com.ecpss.excel.rule.impl.CellRuleImpl;
import com.ecpss.excel.rule.impl.RowRuleImpl;
import com.ecpss.service.iservice.ImportExcelService;
import com.ecpss.vo.ImportExcelBlackCard;

public class BlackCardImportAction extends BaseAction{
	
	private String message;   
	private File fileName;   //udate file
	private String importType;  //excel中的那一列
	
	@Autowired
	@Qualifier("importExcelService")
	private ImportExcelService importExcelService;
	
	public String importBlackCard(){
		try {
			if(fileName!=null){
				Workbook workBook=Workbook.getWorkbook(fileName);
				Long begin=System.currentTimeMillis();
				RowRuleImpl rowRule = new RowRuleImpl();
				// //////////////////////////////////////////////
				rowRule.addCellRule(new CellRuleImpl("A", "ip"));
				rowRule.addCellRule(new CellRuleImpl("B", "cardNo"));
				rowRule.addCellRule(new CellRuleImpl("C", "email"));
				rowRule.addCellRule(new CellRuleImpl("D", "remark"));
				JExcelRowObjectBuilder reveBuilder = new JExcelRowObjectBuilder();
				reveBuilder.setSheet(workBook.getSheet(0));
				reveBuilder.setTargetClass(ImportExcelBlackCard.class);
				reveBuilder.setRule(0, workBook.getSheet(0).getRows(), rowRule);
				
				RowResult<ImportExcelBlackCard>[] cons = reveBuilder.parseExcel();
				ImportExcelBlackCard in;
				int count=0;
				int scount=0;
				boolean r ;  //判断是否存在黑卡卡号
				for (RowResult<ImportExcelBlackCard> rowResult : cons) {
					in = rowResult.getRowObject();
					if(in.getCardNo().trim()!=null){
						r = importExcelService.getBackCardBean(in.getCardNo().trim());
						if(r){
							count++;
							importExcelService.saveBackCardInfo(in.getIp(), in.getCardNo().trim(), in.getEmail(), null, in.getRemark());
						}else{
							scount++;
						}
					}
				}
				Long end=System.currentTimeMillis() - begin;
				//System.out.println("上传完毕,耗时:"+end+"毫秒.上传"+count+"条");
				this.messageAction = "文件导入成功！耗时:"+end+"毫秒.成功上传了"+count+"条.重复"+scount;
			}
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			messageAction="导入失败";
		}
		return SUCCESS;
	}
	
	/**
	 * 流水号查询卡号
	 * @return
	 */
	public String ordernoToCardNo(){
		
		return SUCCESS;
	}
	
	public String toImportBlackCard(){
		
		return SUCCESS;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public File getFileName() {
		return fileName;
	}

	public void setFileName(File fileName) {
		this.fileName = fileName;
	}

	public String getImportType() {
		return importType;
	}

	public void setImportType(String importType) {
		this.importType = importType;
	}
}
