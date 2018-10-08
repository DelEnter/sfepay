package com.ecpss.service.implservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecpss.dao.common.CommonDao;
import com.ecpss.model.channel.InternationalChannels;
import com.ecpss.model.permissions.Resource;
import com.ecpss.model.permissions.Role;
import com.ecpss.service.BaseService;
import com.ecpss.service.iservice.ChannelService;

@Service("channelService")
@Transactional
public class ChannelServiceImpl extends BaseService implements ChannelService {
	@Autowired
	@Qualifier("commonDao")
	private CommonDao commonDao;

	public List<Resource> getResource() {
		List<Resource> list = this.commonDao.list("from  Resource");
		return list;
	}

	public List<Role> getRoleList() {
//		List<Role> getrole = this.commonDao.getSql("select {SYSTEMROLE.*} from SYSTEMROLE_2010_02 {SYSTEMROLE}","SYSTEMROLE",new Role());
		List<Role> getrole2 = this.commonDao.getSql("select {SYSTEMROLE.*} from SYSTEMROLE {SYSTEMROLE}","SYSTEMROLE",new Role());
//		List<Role> getrole3 = this.commonDao.getSql("select {payst.*} from SYSTEMROLE {payst}","payst",new Role());

		return getrole2;

	}
	public void addRole(Role role){
		this.commonDao.save(role);
	}
	
	public List<InternationalChannels> getChannelList(){
		String hql = "from InternationalChannels";
		List<InternationalChannels> list = this.commonDao.list(hql);
		return list;
	}
	
	public List<InternationalChannels> getChannelByMerid(Long merid) {
		String hql="select c from " +
				"InternationalMerchantChannels mc," +
				"InternationalChannels c " +
				"where mc.channelId=c.id " +
				"and mc.merchantId="+merid;
		List<InternationalChannels> list = this.commonDao.list(hql);
		return list;
	}

	public void addChannel(InternationalChannels channel) {
		this.commonDao.save(channel);
	}
	
	public List getMerChannelList(Long merno) {
		String hql = "select m.merno," +
				"c.channelName," +
				"mc " +
				"from InternationalMerchant m," +
				"InternationalMerchantChannels mc," +
				"InternationalChannels c where " +
				"mc.merchantId=m.id and " +
				"mc.channelId=c.id and " +
				"m.id="+merno;
		List list = commonDao.list(hql);
		return list;
	}
	
	
}
