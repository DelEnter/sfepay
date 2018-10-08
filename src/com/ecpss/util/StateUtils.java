package com.ecpss.util;

import java.text.DecimalFormat;

public class StateUtils {
	/**
	 * 传入交易状态值和交易状态占位位置  
	 * 返回交易状态内容
	 * @param state
	 * @param local
	 * @return
	 */
	public String getStateName(String state,int local){
		int input = Integer.valueOf(state.substring(local-1, local));
		String stateName = null;
		if(local==1){  //交易状态
			switch (input) {
				case 0: stateName="<span style='color:red'>失败</span>";		break;
				case 1: stateName="<span style='color:blue'>成功</span>";		break;
				case 2: stateName="<span style='color:green'>待处理</span>";		break;
				case 3: stateName="取消";		break;
				case 4: stateName="<span style='color:green'>待确认</span>";		break;
				case 5: stateName="未返回";		break;
				case 6: stateName="<span style='color:green'>预授权</span>";		break;
				default:
					stateName="状态有误";
					break;
			}
		}
		//退款状态
		else if(local==2){ 
			switch (input) {
			case 0: stateName="未退款";		break;
			case 1: stateName="<span style='color:blue'>全额退款</span>";		break;
			case 2: stateName="<span style='color:green'>部分退款</span>";		break;
			default:
				stateName="状态有误";
				break;
			}
		}
		// 拒付状态
		else if(local==3){ 
			switch (input) {
			case 0: stateName="未拒付";		break;
			case 1: stateName="<span style='color:red'>已拒付</span>";		break;
			default:
				stateName="状态有误";
			break;
			}
		}
		//冻结状态
		else if(local==4){ 
			switch (input) {
			case 0: stateName="未冻结";		break;
			case 1: stateName="已冻结";		break;
			case 2: stateName="已解冻";		break; 
			case 3: stateName="解冻审核";		break; 
			default:
				stateName="状态有误";
			break;
			}
		}
		//勾兑
		else if(local==5){ 
			switch (input) {
			case 0: stateName="未勾兑";		break;
			case 1: stateName="<span style='color:red'>已勾兑</span>";		break;
			default:
				stateName="状态有误";
			break;
			}
		}
		//申请
		else if(local==6){ 
			switch (input) {
			case 0: stateName="未申请";		break;
			case 1: stateName="已申请";		break;
			default:
				stateName="状态有误";
			break;
			}
		}
		//审核
		else if(local==7){ 
			switch (input) {
			case 0: stateName="未审核";		break;
			case 1: stateName="已审核";		break;
			case 2: stateName="问题单";		break;
			default:
				stateName="状态有误";
			break;
			}
		}
		//是否划款
		else if(local==8){ 
			switch (input) {
			case 0: stateName="未划款";		break;
			case 1: stateName="<span style='color:blue'>已划款</span>";		break;
			default:
				stateName="状态有误";
			break;
			}
		}
		//保证金审核
		else if(local==9){ 
			switch (input) {
			case 0: stateName="未审核";		break;
			case 1: stateName="已审核";		break;
			default:
				stateName="状态有误";
			break;
			}
		}
		//保证金划款
		else if(local==10){ 
			switch (input) {
			case 0: stateName="未划款";		break;
			case 1: stateName="已划款";		break;
			default:
				stateName="状态有误";
			break;
			}
		}
		return stateName;
	}
	
	/**
	 * 根据退款明细中退款状态获取状态名称
	 * @return
	 */
	public String getRefundDetailState(int local){
		String stateName=null;
		switch (local) {
			case 0: stateName="未申请";		break;
			case 1: stateName="已申请";		break;
			case 2: stateName="已提交";		break;
			case 3: stateName="已审核";		break;
			case 4: stateName="全额退款";		break;
			case 5: stateName="部分退款";		break;
			case 6: stateName="已生成";		break;
			case 7: stateName="退款失败";		break;
			default:
				stateName="状态有误";
			break;
		}
		return stateName;
	}
	/**
	 * 根据退款明细中退款状态获取状态名称
	 * @return
	 */
	public String getRefundDetailStateEn(int local){
		String stateName=null;
		switch (local) {
		case 0: stateName="No";		break;
		case 1: stateName="Applied";		break;
		case 2: stateName="Submitted";		break;
		case 3: stateName="Audited";		break;
		case 4: stateName="Refund";		break;
		case 5: stateName="Partial refund";		break;
		default:
			stateName="Error";
		break;
		}
		return stateName;
	}
	
	/**
	 * 根据币种号码获取货币单位
	 * @return
	 */
	public String getCurrencyTypeByNo(int moneytypeno){
		String stateName=null;
		switch (moneytypeno) {
			case 1: stateName="USD";		break;
			case 2: stateName="EUR";		break;
			case 3: stateName="RMB";		break;
			case 4: stateName="GBP";		break;
			case 5: stateName="HKD";		break;
			case 6: stateName="JPY";		break;
			case 7: stateName="AUD";		break;
			case 8: stateName="NOK";		break;
			case 10: stateName="US Dollars";		break;
			case 11: stateName="CAD";		break;
			case 12: stateName="SEK";		break;
			case 13: stateName="DKK";		break;
			case 14: stateName="CHF";		break;
			case 15: stateName="SEK";		break;
			case 16: stateName="NZD";		break;
			case 17: stateName="AED";		break;
			default:
				stateName="状态有误";
			break;
		}
		return stateName;
	}
	
	/**
	 * class里面传入交易状态值和交易状态占位位置  
	 * 返回交易状态内容
	 * @param state
	 * @param local
	 * @return
	 */
	public String getStateNameByClass(String state,int local){
		int input = Integer.valueOf(state.substring(local-1, local));
		String stateName = null;
		if(local==1){  //交易状态
			switch (input) {
				case 0: stateName="失败";		break;
				case 1: stateName="成功";		break;
				case 2: stateName="待处理";		break;
				case 3: stateName="取消";		break;
				case 4: stateName="待确认";		break;
				case 5: stateName="未返回";		break;
				case 6: stateName="预授权";		break;
				default:
					stateName="状态有误";
					break;
			}
		}
		//退款状态
		else if(local==2){ 
			switch (input) {
			case 0: stateName="未退款";		break;
			case 1: stateName="全额退款";		break;
			case 2: stateName="部分退款";		break;
			default:
				stateName="状态有误";
				break;
			}
		}
		// 拒付状态
		else if(local==3){ 
			switch (input) {
			case 0: stateName="未拒付";		break;
			case 1: stateName="已拒付";		break;
			default:
				stateName="状态有误";
			break;
			}
		}
		//冻结状态
		else if(local==4){ 
			switch (input) {
			case 0: stateName="未冻结";		break;
			case 1: stateName="已冻结";		break;
			case 2: stateName="已解冻";		break;
			default:
				stateName="状态有误";
			break;
			}
		}
		//勾兑
		else if(local==5){ 
			switch (input) {
			case 0: stateName="未勾兑";		break;
			case 1: stateName="已勾兑";		break;
			default:
				stateName="状态有误";
			break;
			}
		}
		//申请
		else if(local==6){ 
			switch (input) {
			case 0: stateName="未申请";		break;
			case 1: stateName="已申请";		break;
			default:
				stateName="状态有误";
			break;
			}
		}
		//审核
		else if(local==7){ 
			switch (input) {
			case 0: stateName="未审核";		break;
			case 1: stateName="已审核";		break;
			case 2: stateName="问题单";		break;
			default:
				stateName="状态有误";
			break;
			}
		}
		//是否划款
		else if(local==8){ 
			switch (input) {
			case 0: stateName="未划款";		break;
			case 1: stateName="已划款";		break;
			default:
				stateName="状态有误";
			break;
			}
		}
		//保证金审核
		else if(local==9){ 
			switch (input) {
			case 0: stateName="未审核";		break;
			case 1: stateName="已审核";		break;
			default:
				stateName="状态有误";
			break;
			}
		}
		//保证金划款
		else if(local==10){ 
			switch (input) {
			case 0: stateName="未划款";		break;
			case 1: stateName="已划款";		break;
			default:
				stateName="状态有误";
			break;
			}
		}else if(local==14){
			switch (input) {
			case 0: stateName="等待完成";		break;
			case 2: stateName="已申请";		break;
			case 3: stateName="处理中";		break;
			default:
				stateName="状态有误";
			break;			
		}
		}
		return stateName;
	}
	
	
	
	/**
	 * 传入交易状态值和交易状态占位位置  
	 * 返回交易状态内容
	 * English
	 * @param state
	 * @param local
	 * @return
	 */
	public String getStateNameEn(String state,int local){
		int input = Integer.valueOf(state.substring(local-1, local));
		String stateName = null;
		if(local==1){  //交易状态
			switch (input) {
				case 0: stateName="<span style='color:red'>Failed</span>";		break;
				case 1: stateName="<span style='color:blue'>Successful</span>";		break;
				case 2: stateName="<span style='color:green'>Proceeding</span>";		break;
				case 3: stateName="Cancel";		break;
				case 4: stateName="<span style='color:green'>Pending</span>";		break;
				case 5: stateName="NoReturn";		break;
				default:
					stateName="Error";
					break;
			}
		}
		//退款状态
		else if(local==2){ 
			switch (input) {
			case 0: stateName="Unrefunded";		break;
			case 1: stateName="<span style='color:blue'>Refunded</span>";		break;
			case 2: stateName="<span style='color:green'>PartRefunded</span>";		break;
			default:
				stateName="Error";
				break;
			}
		}
		// 拒付状态
		else if(local==3){ 
			switch (input) {
			case 0: stateName="UnChargeback";		break;
			case 1: stateName="<span style='color:red'>Chargeback</span>";		break;
			default:
				stateName="Error";
			break;
			}
		}
		//冻结状态
		else if(local==4){ 
			switch (input) {
			case 0: stateName="Unfrozen";		break;
			case 1: stateName="Frozen";		break;
			case 2: stateName="Unfreeze";		break; 
			case 3: stateName="Audit";		break; 
			default:
				stateName="Error";
			break;
			}
		}
		//勾兑
		else if(local==5){ 
			switch (input) {
			case 0: stateName="UnCheck";		break;
			case 1: stateName="Check";		break;
			default:
				stateName="Error";
			break;
			}
		}
		//申请
		else if(local==6){ 
			switch (input) {
			case 0: stateName="UnApply";		break;
			case 1: stateName="Applied";		break;
			default:
				stateName="Error";
			break;
			}
		}
		//审核
		else if(local==7){ 
			switch (input) {
			case 0: stateName="Unaudited";		break;
			case 1: stateName="Audited";		break;
			case 2: stateName="Questionnaire";		break;
			default:
				stateName="Error";
			break;
			}
		}
		//是否划款
		else if(local==8){ 
			switch (input) {
			case 0: stateName="Settled";		break;
			case 1: stateName="<span style='color:blue'>Unsettled</span>";		break;
			default:
				stateName="Error";
			break;
			}
		}
		//保证金审核
		else if(local==9){ 
			switch (input) {
			case 0: stateName="Unaudited";		break;
			case 1: stateName="Audited";		break;
			default:
				stateName="Error";
			break;
			}
		}
		//保证金划款
		else if(local==10){ 
			switch (input) {
			case 0: stateName="Settled";		break;
			case 1: stateName="Unsettled";		break;
			default:
				stateName="Error";
			break;
			}
		}
		return stateName;
	}
	
	public String toDouble(String num){
		DecimalFormat decimalFormat = new DecimalFormat("###0.00");
		String doublenum=decimalFormat.format(Double.valueOf(num));
		return doublenum;
	}
	public String getStateNameEndown(String state,int local){
		int input = Integer.valueOf(state.substring(local-1, local));
		String stateName = null;
		if(local==1){  //交易状态
			switch (input) {
				case 0: stateName="Failed";		break;
				case 1: stateName="Successful";		break;
				case 2: stateName="Proceeding";		break;
				case 3: stateName="Cancel";		break;
				case 4: stateName="Pending";		break;
				case 5: stateName="NoReturn";		break;
				default:
					stateName="Error";
					break;
			}
		}
		//退款状态
		else if(local==2){ 
			switch (input) {
			case 0: stateName="Unrefunded";		break;
			case 1: stateName="Refunded</span>";		break;
			case 2: stateName="PartRefunded";		break;
			default:
				stateName="Error";
				break;
			}
		}
		// 拒付状态
		else if(local==3){ 
			switch (input) {
			case 0: stateName="UnChargeback";		break;
			case 1: stateName="Chargeback";		break;
			default:
				stateName="Error";
			break;
			}
		}
		//冻结状态
		else if(local==4){ 
			switch (input) {
			case 0: stateName="Unfrozen";		break;
			case 1: stateName="Frozen";		break;
			case 2: stateName="Unfreeze";		break; 
			case 3: stateName="Audit";		break; 
			default:
				stateName="Error";
			break;
			}
		}
		//勾兑
		else if(local==5){ 
			switch (input) {
			case 0: stateName="UnCheck";		break;
			case 1: stateName="Check";		break;
			default:
				stateName="Error";
			break;
			}
		}
		//申请
		else if(local==6){ 
			switch (input) {
			case 0: stateName="UnApply";		break;
			case 1: stateName="Applied";		break;
			default:
				stateName="Error";
			break;
			}
		}
		//审核
		else if(local==7){ 
			switch (input) {
			case 0: stateName="Unaudited";		break;
			case 1: stateName="Audited";		break;
			case 2: stateName="Questionnaire";		break;
			default:
				stateName="Error";
			break;
			}
		}
		//是否划款
		else if(local==8){ 
			switch (input) {
			case 0: stateName="Settled";		break;
			case 1: stateName=">Unsettled";		break;
			default:
				stateName="Error";
			break;
			}
		}
		//保证金审核
		else if(local==9){ 
			switch (input) {
			case 0: stateName="Unaudited";		break;
			case 1: stateName="Audited";		break;
			default:
				stateName="Error";
			break;
			}
		}
		//保证金划款
		else if(local==10){ 
			switch (input) {
			case 0: stateName="Settled";		break;
			case 1: stateName="Unsettled";		break;
			default:
				stateName="Error";
			break;
			}
		}
		return stateName;
	}		
	
}
