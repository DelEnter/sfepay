package com.ecpss.action.pay;

import java.io.Serializable;
import java.util.Date;

public class InternationalTradeinfoVo  implements Serializable {


	private Date lastDate;//最后修改时间

	private Long lastMan;//最后操作人
	private Long id;

	private String orderNo; // Ecpss流水订单号

	private String merchantOrderNo; // 商户流水订单号

	private Long merchantNo; // 商户号

	private Date tradeTime; // 交易时间

	private Double tradeAmount; // 交易金额（外币）

	private Double rmbAmount; // 人民币金额

	/**
	 * tradeState字段状态 第一位：交易成功状态 0:失败,1:成功,2待处理 第二位:
	 */

	private String tradeState; // 交易状态组合字段,比如: 012121

	private String tradeChannel; // 支付通道外键
}
