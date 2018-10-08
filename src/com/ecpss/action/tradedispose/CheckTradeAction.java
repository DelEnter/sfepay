package com.ecpss.action.tradedispose;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import jxl.Workbook;

import org.apache.commons.lang.StringUtils;

import com.ecpss.action.BaseAction;
import com.ecpss.excel.builder.RowResult;
import com.ecpss.excel.builder.jexcel.JExcelRowObjectBuilder;
import com.ecpss.excel.rule.impl.CellRuleImpl;
import com.ecpss.excel.rule.impl.RowRuleImpl;
import com.ecpss.model.payment.InternationalTradeinfo;
import com.ecpss.util.AES;
import com.ecpss.util.DateUtil;
import com.ecpss.util.StatusUtil;
import com.ecpss.util.StringUtil;
import com.ecpss.vo.ImportCheck;

public class CheckTradeAction extends BaseAction{
	
	private List<ImportCheck> lostTradeList = new ArrayList<ImportCheck>(); //掉单交易---> 银行有,我们没有
	private List<ImportCheck> someTradeList = new ArrayList<ImportCheck>(); //重复交易---> 
	private List<ImportCheck> exceptionTradeList = new ArrayList<ImportCheck>(); //异常交易---> 授权号+卡号  查询出交易...判断金额是否一致
	private List<ImportCheck> noCheckTradeList = new ArrayList<ImportCheck>(); //已勾兑交易,再次上传未勾兑---> 已勾兑状态的交易,再次上传为
	
	/**
	 * 上传文件名
	 */
	private File VIPFileName;  
	
	private File nanyangFillName;
	
	/**
	 * VIP上传勾兑页面
	 * @return
	 */
	public String toVIPCheckPage(){
		return SUCCESS;
	}
	
	/**
	 * vip通道勾兑
	 * @return
	 */
	public String VIPCheck(){
		try {
			if(VIPFileName!=null){
				Long begin=System.currentTimeMillis();
				Workbook workBook=Workbook.getWorkbook(VIPFileName);
				RowRuleImpl rowRule = new RowRuleImpl();
				rowRule.addCellRule(new CellRuleImpl("A", "authorizationNo"));
				rowRule.addCellRule(new CellRuleImpl("B", "cardNo"));
				rowRule.addCellRule(new CellRuleImpl("D", "amount"));
				rowRule.addCellRule(new CellRuleImpl("E", "terminalNo"));
				JExcelRowObjectBuilder reveBuilder = new JExcelRowObjectBuilder();
				reveBuilder.setSheet(workBook.getSheet(0));
				reveBuilder.setTargetClass(ImportCheck.class);
				reveBuilder.setRule(0, workBook.getSheet(0).getRows(), rowRule);
				
				RowResult<ImportCheck>[] cons = reveBuilder.parseExcel();
				ImportCheck ic;
				int count = 0;  //成功勾兑
				int gouduiguo = 0; //勾兑过的
				int exceptionc = 0;
				String query = "";
				//InternationalTradeinfo ti;
				List<InternationalTradeinfo> tiList;
				List<String> someTrade = new ArrayList<String>();
				for (RowResult<ImportCheck> rowResult : cons) {
					ic = rowResult.getRowObject();
					if(StringUtils.isNotBlank(ic.getAmount())){
						ic.setAmount(StringUtil.deleteAll(ic.getAmount(), ','));
						ic.setTradeAmount(Double.valueOf(ic.getAmount()));
					}
					query="select ti from InternationalTradeinfo ti,InternationalCardholdersInfo ci " +
							"where ci.tradeId=ti.id " +
							"and ti.VIPAuthorizationNo='"+ic.getAuthorizationNo().trim()+"' " +
									"and ci.cardNo='"+AES.setCarNo(ic.getCardNo().trim())+"'";
					tiList = this.commonService.list(query);
					if(tiList.size()>0){
						for (InternationalTradeinfo ti : tiList) {
							//不等于null..进行验证
							if(ti!=null){
								ic.setOrderNo(ti.getOrderNo());
								ic.setTradeDate(ti.getTradeTime());
								//未勾兑,成功交易--->修改状态为已勾兑
								if(ti.getTradeState().substring(4, 5).equals("0") && ti.getTradeState().substring(0, 1).equals("1")){
									int[] indexArray = reduplicateIndex(someTrade, ic.getOrderNo());
						    		if(indexArray.length>0){
						    			this.someTradeList.add(ic);
						    		}
						    		someTrade.add(ic.getOrderNo());
									//金额相同
									if(ti.getRmbAmount()==ic.getTradeAmount().doubleValue() || CheckTradeAction.validateAmount(ti.getRmbAmount(), ic.getTradeAmount())){
										//正常勾兑
										ti.setTradeState(StatusUtil.updateStatus(ti.getTradeState(), 5, "1"));
										ti.setGouduiTime(new Date());
										this.commonService.update(ti);
										count++;
										continue;
									}
									exceptionTradeList.add(ic);
									exceptionc++;
								}
								//查询到交易,但是已勾兑
								else if(ti.getTradeState().substring(4, 5).equals("1")){
									noCheckTradeList.add(ic);
									gouduiguo++;
								}
								//后台失败  银行成功
								else if(ti.getTradeState().substring(0, 1).equals("0")){
									exceptionTradeList.add(ic);
									exceptionc++;
								}
							}else{
								//等与null则属于掉单
								lostTradeList.add(ic);
							}
						}
					}
				}
				Long end=System.currentTimeMillis() - begin;
				this.messageAction="上传完毕,耗时:"+end+"毫秒.勾兑成功"+count+"条.已经勾兑过本次未勾兑"+gouduiguo+"条.异常单"+exceptionc+"条";
			}
			return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return INPUT;
		}
	}
	
	/**
	 * 南阳商业银行交易勾兑
	 * 终端号是:0788开头
	 * @return
	 */
	public String nanyangBankCheck(){
		try{
			if(nanyangFillName!=null){
				Long begin=System.currentTimeMillis();
				InputStreamReader is = new InputStreamReader( new FileInputStream(nanyangFillName));
				BufferedReader in = new BufferedReader(is);
				String line = in.readLine();
				line = in.readLine();       // 读取第一行
				String query="";
				int count = 0;  //成功勾兑
				int gouduiguo = 0; //勾兑过的
				int exceptionc = 0;
				InternationalTradeinfo ti = null;
				List<InternationalTradeinfo> tiList;
				List<String> someTrade = new ArrayList<String>();
		        while (line != null) {          // 如果 line 为空说明读完了
		        	if(line.trim().startsWith("07")){
		        		//解析每一行交易数据	
			        	StringTokenizer st = null;
			    		st = new StringTokenizer(line , " ");
			    		String str []  = new String[st.countTokens()];
			    		for (int i = 0; i < str.length; i++) {
			    			str[i]=st.nextToken();
			    		}
			    		String posid=str[0]; //终端号
			    		String tardedate=str[3]; //终端号
			    		String ordernum=str[4]; //卡号
			    		String orderamountby=str[5]; //勾兑金额
			    		String authno=str[7]; //授权号
			    		
			    		ImportCheck ic = new ImportCheck();
			    		ic.setTradeAmount(Double.valueOf(orderamountby));
			    		
			    		ic.setAuthorizationNo(authno);
			    		ic.setTerminalNo(posid);
			    		ic.setTradeDate(DateUtil.toDate(tardedate));
			    		ic.setCardNo(ordernum);
			    		ic.setAmount(orderamountby);
			    		
			    		query = "select ti from InternationalTradeinfo ti where ti.VIPTerminalNo='"+posid.trim()+"'" +
			    				"  and ti.VIPAuthorizationNo='"+ic.getAuthorizationNo()+"'";
			    		tiList = this.commonService.list(query);
			    		
			    		
			    		// // 输出查找重复元素的内容
			    		if(tiList.size()>0){
			    			for (int i = 0; i < tiList.size(); i++) {
			    				ti = tiList.get(i);
			    				//验证是否重复
				    			ic.setOrderNo(tiList.get(0).getOrderNo());
					    		int[] indexArray = reduplicateIndex(someTrade, ic.getOrderNo());
					    		if(indexArray.length>0){
					    			this.someTradeList.add(ic);
					    		}
					    		someTrade.add(ic.getOrderNo());
				    			//银行成功的话
					    		if(ti.getTradeState().substring(4, 5).equals("0") && ti.getTradeState().substring(0, 1).equals("1")){
					    			if(ti.getRmbAmount()!=ic.getTradeAmount().doubleValue()){
				    					//金额不相同划归为异常单
				    					this.exceptionTradeList.add(ic);
				    				}else{
				    					//我们也成功的就勾兑
					    				if(ti.getTradeState().substring(0, 1).equals("1")){
					    					//正常勾兑
											ti.setTradeState(StatusUtil.updateStatus(ti.getTradeState(), 5, "1"));
											ti.setGouduiTime(new Date());
											this.commonService.update(ti);
											System.out.println(ti.getOrderNo()+"----已勾兑");
											count++;
					    				}else{
					    					//把我们后台的数据勾兑成成功状态
					    					ti.setTradeState(StatusUtil.updateStatus(ti.getTradeState(), 1, "1"));
					    					ti.setTradeState(StatusUtil.updateStatus(ti.getTradeState(), 5, "1"));
											ti.setGouduiTime(new Date());
											this.commonService.update(ti);
					    				}
				    				}
					    		}
					    		//查询到交易,但是已勾兑
								else if(ti.getTradeState().substring(4, 5).equals("1")){
									noCheckTradeList.add(ic);
									gouduiguo++;
								}//后台失败  银行成功
								else if(ti.getTradeState().substring(0, 1).equals("0")){
									exceptionTradeList.add(ic);
									exceptionc++;
								}
			    				
							}
			    		}else{
			    			//等与null则属于掉单
							lostTradeList.add(ic);
			    		}
						
		        	}
		        	line = in.readLine();   // 读取下一行
		        }
		        Long end=System.currentTimeMillis() - begin;
				this.messageAction="上传完毕,耗时:"+end+"毫秒.勾兑成功"+count+"条.已经勾兑过本次未勾兑"+gouduiguo+"条.异常单"+exceptionc+"条";
		        is.close();
		        in.close();
			}
			return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return INPUT;
		}
	}
	
	private File bocTCFile ;
	
	/**
	 * 中行3150终端号勾兑
	 * @return
	 */
	public String BocTCCheck(){
		try{
			if(bocTCFile!=null){
				Long begin=System.currentTimeMillis();
				InputStreamReader is = new InputStreamReader( new FileInputStream(bocTCFile));
				BufferedReader in = new BufferedReader(is);
				String line = in.readLine();
				line = in.readLine();       // 读取第一行
				int count = 0;
				int gouduiguo = 0; //勾兑过的
				String query="";
				InternationalTradeinfo ti = null;
				List<InternationalTradeinfo> tiList;
				List<String> someTrade = new ArrayList<String>();
		        while (line != null) {          // 如果 line 为空说明读完了
		        	if(line.trim().startsWith("31")){
		        		//解析每一行交易数据	
			        	StringTokenizer st = null;
			    		st = new StringTokenizer(line , " ");
			    		String str []  = new String[st.countTokens()];
			    		for (int i = 0; i < str.length; i++) {
			    			str[i]=st.nextToken();
			    		}
			    		String posid=str[0]; //终端号
			    		String tardedate=str[3].substring(4, 8); //交易日期
			    		String tardetime=str[4]; //交易时间
			    		String ordernum=str[2]; //卡号
			    		String orderamountby=str[5]; //勾兑金额
			    		String authno=str[8]; //授权号
			    		String rrn=str[12];  //rrn
			    		
			    		ImportCheck ic = new ImportCheck();
			    		ic.setTradeAmount(Double.valueOf(orderamountby.replace(",","")));
			    		ic.setAmount(orderamountby);
			    		ic.setCardNo(ordernum);
			    		ic.setAuthorizationNo(authno);
			    		ic.setTerminalNo(posid);
			    		ic.setTradeDate(DateUtil.toDate(tardedate));
			    		
			    		
			    		query = "select ti from InternationalTradeinfo ti where ti.VIPTerminalNo='"+posid.trim()+"'" +
			    				"  and ti.VIPAuthorizationNo='"+ic.getAuthorizationNo()+"' and ti.boc_rrn='"+rrn.trim()+"' " +
			    						"and ti.boc_date='"+tardedate+"' and ti.boc_time='"+tardetime+"' ";
			    		tiList = this.commonService.list(query);
			    		
			    		
			    		// // 输出查找重复元素的内容
			    		if(tiList.size()>0){
			    			for (int i = 0; i < tiList.size(); i++) {
			    				ti = tiList.get(i);
			    				//验证是否重复
				    			ic.setOrderNo(tiList.get(0).getOrderNo());
					    		int[] indexArray = reduplicateIndex(someTrade, ic.getOrderNo());
					    		if(indexArray.length>0){
					    			this.someTradeList.add(ic);
					    		}
					    		someTrade.add(ic.getOrderNo());
					    		if(ti.getTradeState().substring(4, 5).equals("0") && ti.getTradeState().substring(0, 1).equals("1")){
					    			//银行成功的话
				    				if(ti.getRmbAmount()!=ic.getTradeAmount().doubleValue()){
				    					//金额不相同划归为异常单
				    					this.exceptionTradeList.add(ic);
				    				}else{
				    					//我们也成功的就勾兑
					    				if(ti.getTradeState().substring(0, 1).equals("1")){
					    					//正常勾兑
											ti.setTradeState(StatusUtil.updateStatus(ti.getTradeState(), 5, "1"));
											ti.setGouduiTime(new Date());
											this.commonService.update(ti);
											System.out.println(ti.getOrderNo()+"----已勾兑");
											count++;
					    				}else{
					    					//把我们后台的数据勾兑成成功状态
					    					ti.setTradeState(StatusUtil.updateStatus(ti.getTradeState(), 1, "1"));
					    					ti.setTradeState(StatusUtil.updateStatus(ti.getTradeState(), 5, "1"));
											ti.setGouduiTime(new Date());
											this.commonService.update(ti);
					    				}
				    				}
					    		}
					    		//查询到交易,但是已勾兑
								else if(ti.getTradeState().substring(4, 5).equals("1")){
									noCheckTradeList.add(ic);
									gouduiguo++;
								}//后台失败  银行成功
								else if(ti.getTradeState().substring(0, 1).equals("0")){
									exceptionTradeList.add(ic);
								}
							}
			    		}else{
			    			//等与null则属于掉单
			    			
							lostTradeList.add(ic);
			    		}
						
		        	}
		        	line = in.readLine();   // 读取下一行
		            
		        }
		        Long end=System.currentTimeMillis() - begin;
				this.messageAction="上传完毕,耗时:"+end+"毫秒.勾兑成功"+count+"条.已经勾兑过本次未勾兑"+gouduiguo+"条";
		        System.out.println(messageAction);
		        is.close();
		        in.close();
			}
			  
			return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return INPUT;
		}
	}
	
	
	/**
	 * 非3D上传勾兑页面
	 * @return
	 */
	public String toF3DCheckPage(){
		return SUCCESS;
	}
	/**
	 * 非3D通道勾兑
	 * @return
	 */
	public String F3DCheck(){
		try {
			InputStreamReader is = new InputStreamReader( new FileInputStream(VIPFileName));
			BufferedReader in = new BufferedReader(is);
			String line = in.readLine();
			line = in.readLine();       // 读取第一行
			int count = 0;
			String query="";
			InternationalTradeinfo ti = null;
			List<String> someTrade = new ArrayList<String>();
	        while (line != null) {          // 如果 line 为空说明读完了
	            //解析每一行交易数据	
	        	StringTokenizer st = null;
	    		st = new StringTokenizer(line , ",");
	    		String str []  = new String[st.countTokens()];
	    		for (int i = 0; i < 14; i++) {
	    			str[i]=st.nextToken();
	    		}
	    		ImportCheck ic = new ImportCheck();
	    		
	    		String tradetimenoby=str[1]; //勾兑交易日期
	    		String ordernoby=str[3]; //勾兑流水号
	    		String orderamountby=str[9]; //勾兑金额
	    		String resultby=str[11]; //勾兑状态
	    		query = "select ti from InternationalTradeinfo ti where ti.orderNo='"+ordernoby.trim()+"' ";
	    		ti = (InternationalTradeinfo) this.commonService.uniqueResult(query);
	    		
	    		ic.setTradeAmount(Double.valueOf(orderamountby.substring(1, orderamountby.length())));
	    		ic.setOrderNo(ordernoby);
	    		ic.setTradeDate(DateUtil.toDate(tradetimenoby));
	    		
//	    		int[] indexArray = reduplicateIndex(someTrade, ic.getOrderNo());
//	    		if(indexArray.length>0){
//	    			this.someTradeList.add(ic);
//	    		}
	    		//someTrade.add(ic.getOrderNo());
	    		// // 输出查找重复元素的内容
	    		if(ti!=null){
	    			//银行成功的话
	    			if(resultby.startsWith("0")){
	    				if(ti.getRmbAmount()!=ic.getTradeAmount().doubleValue()){
	    					//金额不相同划归为异常单
	    					this.exceptionTradeList.add(ic);
	    				}else{
	    					//我们也成功的就勾兑
		    				if(ti.getTradeState().substring(0, 1).equals("1")){
		    					//正常勾兑
								ti.setTradeState(StatusUtil.updateStatus(ti.getTradeState(), 5, "1"));
								ti.setGouduiTime(new Date());
								this.commonService.update(ti);
								count++;
		    				}else{
		    					//把我们后台的数据勾兑成成功状态
		    					ti.setTradeState(StatusUtil.updateStatus(ti.getTradeState(), 1, "1"));
		    					ti.setTradeState(StatusUtil.updateStatus(ti.getTradeState(), 5, "1"));
								ti.setGouduiTime(new Date());
								this.commonService.update(ti);
		    				}
	    				}
	    			}else{
	    				//正常勾兑
	    				ti.setTradeState(StatusUtil.updateStatus(ti.getTradeState(), 1, "0"));
						ti.setTradeState(StatusUtil.updateStatus(ti.getTradeState(), 5, "1"));
						ti.setGouduiTime(new Date());
						this.commonService.update(ti);
	    			}
	    		}else{
	    			//等与null则属于掉单
					lostTradeList.add(ic);
	    		}
				
	            line = in.readLine();   // 读取下一行
	        }
	        is.close();
	        in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	
	public static <T> int[] reduplicateIndex(List<T> list, T str) throws Exception {
		List<T> tmp = new ArrayList<T>(list);
		int[] index = new int[Collections.frequency(list, str)];
		int start = tmp.indexOf(str);
		int end = tmp.lastIndexOf(str);
		int i = 0;
		if (start > 0) {
		
		index[i] = start;
		while (start != end) {
			index[++i] = end;
			tmp = tmp.subList(0, end);
			end = tmp.lastIndexOf(str);
			Arrays.sort(index);
			return index;
		}
		}
		return index;
	}
	
	/**
	 * 验证两个金额是否相差0.01
	 * @param amount
	 * @param vamount
	 * @return
	 */
	public static boolean validateAmount(Double amount,Double vamount){
		if((amount+0.01)==vamount.doubleValue()){
			return true;
		}else if((amount-0.01)==vamount.doubleValue()){
			return true;
		}else{
			return false;
		}
	}
	public List<ImportCheck> getLostTradeList() {
		return lostTradeList;
	}

	public void setLostTradeList(List<ImportCheck> lostTradeList) {
		this.lostTradeList = lostTradeList;
	}

	public List<ImportCheck> getSomeTradeList() {
		return someTradeList;
	}

	public void setSomeTradeList(List<ImportCheck> someTradeList) {
		this.someTradeList = someTradeList;
	}

	public List<ImportCheck> getExceptionTradeList() {
		return exceptionTradeList;
	}

	public void setExceptionTradeList(List<ImportCheck> exceptionTradeList) {
		this.exceptionTradeList = exceptionTradeList;
	}

	public List<ImportCheck> getNoCheckTradeList() {
		return noCheckTradeList;
	}

	public void setNoCheckTradeList(List<ImportCheck> noCheckTradeList) {
		this.noCheckTradeList = noCheckTradeList;
	}

	public File getVIPFileName() {
		return VIPFileName;
	}

	public void setVIPFileName(File fileName) {
		VIPFileName = fileName;
	}

	public File getNanyangFillName() {
		return nanyangFillName;
	}

	public void setNanyangFillName(File nanyangFillName) {
		this.nanyangFillName = nanyangFillName;
	}

	public File getBocTCFile() {
		return bocTCFile;
	}

	public void setBocTCFile(File bocTCFile) {
		this.bocTCFile = bocTCFile;
	}
}
