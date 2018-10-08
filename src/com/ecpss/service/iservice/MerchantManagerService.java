package com.ecpss.service.iservice;

import java.util.List;

import com.ecpss.model.shop.InternationalAgentsMerchant;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.model.shop.ShopOpera;
import com.ecpss.web.PageInfo;

public interface MerchantManagerService {

	/**
	 * 获取商户登陆信息
	 * @param merno
	 * @param username
	 * @param password
	 * @return
	 */
	public InternationalMerchant getMerchantUser(Long merno,String username,String password);
	
	/**
	 * 查询该商户下其他用户
	 * @return
	 */
	public ShopOpera getMerchantOpera(Long merno,String username,String password);
	
	/**
	 * 获取代理商户号信息
	 * @param merno
	 * @param password
	 * @return
	 */
	public List<InternationalAgentsMerchant> getAgentsMerchant(Long merno,String password);
	
	/**
	 * 根据代理商户号获取该代理商户下的所有交易记录
	 * @param merno
	 * @return
	 */
	public PageInfo getAgentsTradeQueryBy(Long merno);
	
	/**
	 * 根据代理商户号获取子商户号
	 * @param agentsNO
	 * @return
	 */
	public List<InternationalMerchant> getMerchantNoByAgents(Long agentsNO);
	
	/**
	 * 根据商户ID获取该商户下所有拒付笔数
	 * @param merchangId
	 * @return
	 */
	public Long getMerchantChargeBackCount(Long merchangId);
	/**
	 * 根据商户ID获取该商户下未处理持卡人投诉
	 * @param merchangId
	 * @return
	 */
	public Long getMerchantComplaintCount(Long merchangId);
	
	
	
	
	
	
}
