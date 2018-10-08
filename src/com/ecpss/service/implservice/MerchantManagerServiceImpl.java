package com.ecpss.service.implservice;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sun.misc.BASE64Encoder;

import com.ecpss.dao.common.CommonDao;
import com.ecpss.model.shop.InternationalAgentsMerchant;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.model.shop.ShopOpera;
import com.ecpss.service.iservice.MerchantManagerService;
import com.ecpss.web.PageInfo;

@Service("merchantManagerService")
@Transactional
public class MerchantManagerServiceImpl implements MerchantManagerService {

	@Autowired
	@Qualifier("commonDao")
	private CommonDao commonDao;
	
	
	public InternationalMerchant getMerchantUser(Long merno, String username,
			String password) {
		String hql = "select m from InternationalMerchant m " +
				"where m.merno="+merno+" " +
						"and m.username='"+username.trim()+"' " ;
		if(StringUtils.isNotBlank(password)){
			hql+="and m.password='"+password.trim()+"'";
		}
		InternationalMerchant m = (InternationalMerchant) this.commonDao.uniqueResult(hql);
		return m;
	}

	public ShopOpera getMerchantOpera(Long merno, String username,
			String password) {
		String hql="select s from ShopOpera s,InternationalMerchant m where s.merchantId=m.id " +
				"and s.userName='"+username+"' " +
								"and m.merno="+merno;
		if(StringUtils.isNotBlank(password)){
			hql+="and s.password='"+password+"'";
		}
		ShopOpera s = (ShopOpera) this.commonDao.uniqueResult(hql);
		return s;
	}



	public List<InternationalAgentsMerchant> getAgentsMerchant(Long merno,
			String password) {
		String hql="select am from InternationalAgentsMerchant am " +
				"where am.agentsMerchantNo="+merno+" " +
						"and am.password='"+password+"' ";
		List<InternationalAgentsMerchant> list = this.commonDao.list(hql);
		return list;
	}

	public PageInfo getAgentsTradeQueryBy(Long merno) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<InternationalMerchant> getMerchantNoByAgents(Long agentsNO) {
		String hql = "select m from InternationalAgentsMerchant am," +
				"InternationalMerchant m where am.merchantId=m.id and am.agentsMerchantNo="+agentsNO;
		List<InternationalMerchant> list = this.commonDao.list(hql);
		return list;
	}

	public Long getMerchantChargeBackCount(Long merchangId) {
		String hql="select count(ti.id) from InternationalMerchant m,InternationalTradeinfo ti " +
				"where ti.merchantId=m.id " +
				"and substr(ti.tradeState,3,1)=1 " +
				"and substr(ti.tradeState,8,1)=0 " +
				"and m.id="+merchangId;
		Long count = (Long) this.commonDao.uniqueResult(hql);
		return count;
	}

	public Long getMerchantComplaintCount(Long merchangId) {
		String hql = "select count(m.id) from InternationalMerchant m,InternationalComplaintsManager cm" +
				" where cm.merchantNo=m.merno " +
				"and cm.processingResults=0 " +
				"and m.id="+merchangId;
		Long count = (Long) this.commonDao.uniqueResult(hql);
		return count;
	}
	
	
}
