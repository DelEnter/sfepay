package com.ecpss.service.iservice;

import java.util.List;

import com.ecpss.model.channel.InternationalChannels;
import com.ecpss.model.permissions.Resource;
import com.ecpss.model.permissions.Role;

public interface ChannelService {
    public List<Resource> getResource();
    public List<Role> getRoleList() ;
    public void addRole(Role role);
    /**
     * 获取通道列表
     * @return
     */
    public List<InternationalChannels> getChannelList();
    
    /**
     * 根据商户id获取该商户开通的通道列表
     * @param merid
     * @return
     */
    public List<InternationalChannels> getChannelByMerid(Long merid);
    
    public void addChannel(InternationalChannels channel);
    /**
     * 获取商户通道管理
     * @return
     */
    public List getMerChannelList(Long merno);
    
    
}
