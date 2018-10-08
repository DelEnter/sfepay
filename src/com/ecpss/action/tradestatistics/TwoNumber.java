package com.ecpss.action.tradestatistics;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import com.ecpss.action.BaseAction;
import com.ecpss.model.channel.InternationalChannels;
import com.ecpss.model.shop.InternationalMerchantTerminal;
import com.ecpss.util.CalcuLate;

public class TwoNumber extends BaseAction {
	private List twoNumber;
	private String terminalno;

	private Long[] termanalids;
	private Long channelId;
	private String addTerNo;
	private List<InternationalChannels> chennelList;
	public String twoNumber() {
		
		String chennelsql = "select c from InternationalChannels c";
		chennelList = this.commonService.list(chennelsql);
		
		
		String tmp = "";
		if (StringUtils.isNotBlank(terminalno)) {
			tmp = "and tm.terminalno='" + terminalno + "'";
		}
		if(channelId!=null){
			tmp += " and c.id="+channelId+" ";
		}
		this.twoNumber = this.commonService
				.getByList("select m.merno,tm.terminalno,t.id,c.channelName "
						+ "from international_terminalmerchant t,international_merchant m," +
								"international_terminalmanager tm,international_channels c "
						+ "where tm.id=t.terminalid " +
								" and c.id=tm.channelId "
						+ " and m.id=t.merchantid " + tmp + " order by m.merno");
		return "success";
	}

	/**
	 * 批量选择商户好进行更新终端号
	 * 
	 * @return
	 */
	public String batchUpdateTer() {
		if (termanalids.length > 0) {
			for (Long terid : termanalids) {
				InternationalMerchantTerminal mt = (InternationalMerchantTerminal) this.commonService
						.load(InternationalMerchantTerminal.class, terid);
				String sql = "select tm.id from InternationalTerminalManager tm where tm.channelId="
						+ mt.getChannelId()
						+ " "
						+ "and tm.terminalNo='"
						+ addTerNo.trim() + "'";

				List list = this.commonService.list(sql);
				Long idt = 0L;
				if (list.size() > 0) {
					idt = (Long) list.get(0);
					mt.setTerminalId(idt);
					mt.setLastDate(new Date());
					mt.setLastMan(getUserBean().getUserName());
					this.commonService.update(mt);
				}

			}
		}

		return SUCCESS;
	}

	public List getTwoNumber() {
		return twoNumber;
	}

	public void setTwoNumber(List twoNumber) {
		this.twoNumber = twoNumber;
	}

	public String getTerminalno() {
		return terminalno;
	}

	public void setTerminalno(String terminalno) {
		this.terminalno = terminalno;
	}

	public Long[] getTermanalids() {
		return termanalids;
	}

	public void setTermanalids(Long[] termanalids) {
		this.termanalids = termanalids;
	}

	public String getAddTerNo() {
		return addTerNo;
	}

	public void setAddTerNo(String addTerNo) {
		this.addTerNo = addTerNo;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public List<InternationalChannels> getChennelList() {
		return chennelList;
	}

	public void setChennelList(List<InternationalChannels> chennelList) {
		this.chennelList = chennelList;
	}
}
