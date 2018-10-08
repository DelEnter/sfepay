package com.ecpss.service.iservice;

import java.util.List;

import com.ecpss.model.channel.InternationalChannels;
import com.ecpss.model.permissions.Resource;
import com.ecpss.model.permissions.Role;
import com.ecpss.model.shop.InternationalMailInfo;

public interface ShopManagerService {
	
	/**
     * 获取商户卡种管理管理
     * @return
     */
    public List getMerCreditCardManagerList(Long merid);
    
    /**
     * 24小时把待确认交易变为取消交易
     */
    public void cancelHignRickTrade();
   
   
    
    /**
     * 添加等待发送邮件
     * @param cardholdsemail  持卡人email
     * @param sendemail       发送email	 
     * @param mailinfo        邮件内容
     * @param type :
     * 0   成功/失败发送的信息
	 * 1   退款
	 * 2   跟踪单号
     */
    public void addSendMessages(String cardholdsemail,String sendemail,String mailinfo,String type);
    
    /**
	 * 获取成功失败的邮件的第一条发送
	 * @return
	 */
    public InternationalMailInfo getResultMail(String type);
    
    /**
     * 删除当前发送的邮件内容
     */
    public void deleteInfo(InternationalMailInfo info);
    
    public void runGoudui();
    
    /**
     * 获取需要自动处理的交易
     * @return
     */
    public List getTransaction();
    
    /**
     * 获取终端号错误的交易
     * @return
     */
    public List getTransactionByAL();
    
    /**
     * 自动处理待处理交易
     */
    public void processTransactions(List list);
    public void addTemporaryTradInfo(String orderNo,String expirationYear,String expirationMonth,String cvv2,String country,String md5Info,String payIp,String userAgent,String remark);


    /**
     * 将重跑交易存入五天临时表中
     */
    public void addTraderun(String orderNo,String expirationYear,String expirationMonth,String cvv2,String country,String md5Info,String payIp,String userAgent,String remark);
}
