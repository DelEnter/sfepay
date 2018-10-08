package com.ecpss.action.affichemanager;

import java.util.Date;
import java.util.List;
import com.ecpss.action.BaseAction;
import com.ecpss.model.affiche.AfficheManager;
import com.ecpss.model.log.SystemLog;
import com.ecpss.vo.affichemanager.AfficheManagerLog;
import com.ecpss.web.PageInfo;

/**
 * 
 * 公告管理action
 * 
 * @author huhongguang
 * 
 */
public class AfficheActionManager extends BaseAction {

	private PageInfo info;

	private AfficheManager am; // 公告实体对象

	private List<AfficheManager> amList; // 公告实体对象集合

	private Long iid; // 公告id

	/**
	 * 去公告管理主界面
	 * 
	 * @return
	 */
	public String toAfficheActionManager() {
		if (this.info == null) {
			this.info = new PageInfo();
		}
		amList = this.commonService
				.list("from com.ecpss.model.affiche.AfficheManager");
		System.out.println(amList.size());
		this.info.setPageSize(2);
		return SUCCESS;
	}

	/**
	 * 去公告管理添加页面
	 * 
	 * @return
	 */
	public String toAddAfficheManager() {
		return SUCCESS;
	}

	/**
	 * 添加公告
	 * 
	 * @return
	 */
	AfficheManagerLog aml = new AfficheManagerLog();
	SystemLog sl = new SystemLog();

	/**
	 * 添加公告管理
	 * 
	 * @return
	 */
	public String addAfficheManager() {
		am.setAffichedate(new Date());
		this.commonService.save(am);
		// 记录更新前的状态
		aml.setAfficheContext(am.getAfficheContext());
		aml.setAffichedate(am.getAffichedate());
		aml.setUrl(am.getUrl());

		// 设置日志属性值
		sl.setUserName(getUserBean().getUserName());
		sl.setOperTime(new Date());
		sl.setOperType("1");
		sl.setRemarks(aml.getSaveAfficheLog());
		sl.setRescReow("新增公告");
		this.commonService.save(sl);
		this.messageAction = "新增公告成功";
		return this.OPERATE_SUCCESS;
	}

	/**
	 * 去修改公告页面
	 * 
	 * @return
	 */
	public String toUpdateAffiche() {
		am = (AfficheManager) this.commonService
				.load(AfficheManager.class, iid);
		return SUCCESS;
	}

	/**
	 * 修改公告管理
	 * 
	 * @return
	 */
	public String updateAffiche() {
		AfficheManager afficheManager = (AfficheManager) this.commonService
				.load(AfficheManager.class, iid);

		// 记录更新前的状态
		aml.setAfficheContext(afficheManager.getAfficheContext());
		aml.setAffichedate(afficheManager.getAffichedate());
		aml.setUrl(afficheManager.getUrl());

		afficheManager.setAfficheContext(am.getAfficheContext());
		afficheManager.setAffichedate(new Date());
		afficheManager.setUrl(am.getUrl());
		afficheManager.setLastDate(new Date());
		afficheManager.setLastMan(this.getUserBean().getUserName());
		this.commonService.update(afficheManager);
		// 更新后的状态
		aml.setAfficheContextafter(afficheManager.getAfficheContext());
		aml.setAffichedateafter(afficheManager.getAffichedate());
		aml.setUrlafter(afficheManager.getUrl());

		// 设置日志属性值
		sl.setUserName(this.getUserBean().getUserName());
		sl.setOperTime(new Date());
		sl.setOperType("2");
		sl.setRemarks(aml.getUpdateAfficheLog());
		sl.setRescReow("修改公告");
		this.commonService.save(sl);

		this.messageAction = "修改成功";
		return this.OPERATE_SUCCESS;
	}

	/**
	 * 删除公告
	 * 
	 * @return
	 */
	public String deleteAffiche() {
		am = (AfficheManager) this.commonService
				.load(AfficheManager.class, iid);
		this.commonService.delete(am);

		// 记录更新前的状态
		aml.setAfficheContext(am.getAfficheContext());
		aml.setAffichedate(am.getAffichedate());
		aml.setUrl(am.getUrl());

		// 设置日志属性值
		sl.setUserName(this.getUserBean().getUserName());
		sl.setOperTime(new Date());
		sl.setOperType("3");
		sl.setRemarks(aml.getDeleteAfficheLog());
		sl.setRescReow("删除公告");
		this.commonService.save(sl);

		this.messageAction = "删除网址成功";
		return this.OPERATE_SUCCESS;
	}

	public PageInfo getInfo() {
		return info;
	}

	public void setInfo(PageInfo info) {
		this.info = info;
	}

	public AfficheManager getAm() {
		return am;
	}

	public void setAm(AfficheManager am) {
		this.am = am;
	}

	public List<AfficheManager> getAmList() {
		return amList;
	}

	public void setAmList(List<AfficheManager> amList) {
		this.amList = amList;
	}

	public AfficheManagerLog getAml() {
		return aml;
	}

	public void setAml(AfficheManagerLog aml) {
		this.aml = aml;
	}

	public SystemLog getSl() {
		return sl;
	}

	public void setSl(SystemLog sl) {
		this.sl = sl;
	}

	public Long getIid() {
		return iid;
	}

	public void setIid(Long iid) {
		this.iid = iid;
	}

}
