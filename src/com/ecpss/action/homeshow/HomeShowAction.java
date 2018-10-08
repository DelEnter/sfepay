package com.ecpss.action.homeshow;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ecpss.action.BaseAction;

public class HomeShowAction extends BaseAction {

	@SuppressWarnings("unused")
	private Log logger = LogFactory.getLog(HomeShowAction.class);
	/**
	 * �?�?30天的提现,转账，还款，�?款，佣金金额
	 */
	Double drawMoneyAmount, transferAmount, returnAmount, refundAmount;
	Long drawMoneyCount, transferCount, returnCount, refundCount;
	private Map<String, List<String>> map;

	@SuppressWarnings("rawtypes")
	private List months = new ArrayList<>();
	//List months = new ArrayList<>();
	
	public String home(){
		
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "deprecation" })
	public String chartData() throws Exception {
		// 按月份查询每个月的提现金�?
		List drawMoneylist = this.commonService
				.list("select to_char(t.createDate,'yyyy-mm'),sum(t.amount) from InternationalOrderTradeinfo t where t.tradeType=4 group by to_char(t.createDate,'yyyy-mm')");

		// 按月份查询每个月的充值金�?
		List rechargeList = this.commonService
				.list("select to_char(t.createDate,'yyyy-mm'),sum(t.amount) from InternationalOrderTradeinfo t where t.tradeType=1 group by to_char(t.createDate,'yyyy-mm')");

		// 按月份查询每个月的转账金�?
		List transferList = this.commonService
				.list("select to_char(t.createDate,'yyyy-mm'),sum(t.amount) from InternationalOrderTradeinfo t where t.tradeType=2 group by to_char(t.createDate,'yyyy-mm')");

		// 按月份查询每个月的还款金�?
		List returnList = this.commonService
				.list("select to_char(t.createDate,'yyyy-mm'),sum(t.amount) from InternationalOrderTradeinfo t where t.tradeType=3 group by to_char(t.createDate,'yyyy-mm')");

		map = new HashMap<String, List<String>>();

		Date toDate = new Date();

		int month = toDate.getMonth() + 1;

		// 改为1�?
		toDate.setMonth(0);
		for (int j = 1; j <= month; j++) {
			String datestr = toDate(toDate);

			for (int i = 0; i < drawMoneylist.size(); i++) {
				Object[] drawMoneylistc = (Object[]) drawMoneylist.get(i);

				if (drawMoneylistc[0].equals(datestr)) {
					unmMap(map, datestr, drawMoneylistc[1].toString(), 0);
					break;
				}
			}

			for (int i = 0; i < rechargeList.size(); i++) {
				Object[] drawMoneylistc = (Object[]) rechargeList.get(i);

				if (drawMoneylistc[0].equals(datestr)) {
					unmMap(map, datestr, drawMoneylistc[1].toString(), 1);
					break;
				}
			}

			for (int i = 0; i < transferList.size(); i++) {
				Object[] drawMoneylistc = (Object[]) transferList.get(i);

				if (drawMoneylistc[0].equals(datestr)) {
					unmMap(map, datestr, drawMoneylistc[1].toString(), 2);
					break;
				}
			}

			for (int i = 0; i < returnList.size(); i++) {
				Object[] drawMoneylistc = (Object[]) returnList.get(i);

				if (drawMoneylistc[0].equals(datestr)) {
					unmMap(map, datestr, drawMoneylistc[1].toString(), 3);
					break;
				}
			}

		}

		// 查询�?�?30天内的提现金�?
		drawMoneyAmount = (Double) this.commonService
				.uniqueResult("select nvl(sum(t.amount),0) from InternationalOrderTradeinfo t where t.tradeType=4 and t.payState=1 and round(to_number(sysdate-t.createDate))<=30");
		// �?30天内提现次数
		drawMoneyCount = (Long) this.commonService
				.uniqueResult("select count(*) from InternationalOrderTradeinfo t where t.tradeType=4 and t.payState=1 and round(to_number(sysdate-t.createDate))<=30");
		// 转账金额
		transferAmount = (Double) this.commonService
				.uniqueResult("select nvl(sum(t.amount),0) from InternationalOrderTradeinfo t where t.tradeType=2 and t.payState=1 and round(to_number(sysdate-createDate))<=30");
		// �?30天内转账次数
		transferCount = (Long) this.commonService
				.uniqueResult("select count(*) from InternationalOrderTradeinfo t where t.tradeType=2  and t.payState=1 and round(to_number(sysdate-t.createDate))<=30");
		// 还款金额
		returnAmount = (Double) this.commonService
				.uniqueResult("select nvl(sum(t.amount),0) from InternationalOrderTradeinfo t where t.tradeType=3 and t.payState=1 and round(to_number(sysdate-createDate))<=30");
		// �?30天内还款次数
		returnCount = (Long) this.commonService
				.uniqueResult("select count(*) from InternationalOrderTradeinfo t where t.tradeType=3  and t.payState=1 and round(to_number(sysdate-t.createDate))<=30");
		// �?款金�?
		refundAmount = (Double) this.commonService
				.uniqueResult("select nvl(sum(t.amount),0) from InternationalOrderTradeinfo t where refund=1 or refund=2 and round(to_number(sysdate-createDate))<=30");
		// �?30天内�?款次�?
		refundCount = (Long) this.commonService
				.uniqueResult("select count(*) from InternationalOrderTradeinfo t where refund=1 or refund=2  and round(to_number(sysdate-t.createDate))<=30");

		return SUCCESS;
	}

	@SuppressWarnings("deprecation")
	private String toDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		String val = formatter.format(date);
		date.setMonth(date.getMonth() + 1);
		return val;
	}

	@SuppressWarnings({ "unchecked", "serial", "rawtypes" })
	private void unmMap(Map<String, List<String>> map, String datestr,
			String valuestr, int i) {
		
		if (map.containsKey(datestr)) {
			@SuppressWarnings("rawtypes")
			List listsd = map.get(datestr);
			listsd.set(i, valuestr);
		} else {
			List listsd = new ArrayList<String>() {
				{
					add("0");
					add("0");
					add("0");
					add("0");
				}
			};
			months.add(datestr);
			listsd.set(i, valuestr);
			map.put(datestr, listsd);
		}
	}

	public Double getDrawMoneyAmount() {
		return drawMoneyAmount;
	}

	public void setDrawMoneyAmount(Double drawMoneyAmount) {
		this.drawMoneyAmount = drawMoneyAmount;
	}

	public Double getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(Double transferAmount) {
		this.transferAmount = transferAmount;
	}

	public Double getReturnAmount() {
		return returnAmount;
	}

	public void setReturnAmount(Double returnAmount) {
		this.returnAmount = returnAmount;
	}

	public Double getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(Double refundAmount) {
		this.refundAmount = refundAmount;
	}

	public Map<String, List<String>> getMap() {
		return map;
	}

	public List getMonths() {
		return months;
	}

	public Long getDrawMoneyCount() {
		return drawMoneyCount;
	}

	public void setDrawMoneyCount(Long drawMoneyCount) {
		this.drawMoneyCount = drawMoneyCount;
	}

	public Long getTransferCount() {
		return transferCount;
	}

	public void setTransferCount(Long transferCount) {
		this.transferCount = transferCount;
	}

	public Long getReturnCount() {
		return returnCount;
	}

	public void setReturnCount(Long returnCount) {
		this.returnCount = returnCount;
	}

	public Long getRefundCount() {
		return refundCount;
	}

	public void setRefundCount(Long refundCount) {
		this.refundCount = refundCount;
	}

}
