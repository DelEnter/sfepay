package com.ecpss.action.managevaluesetting;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.bouncycastle.asn1.ocsp.Request;

import com.ecpss.action.BaseAction;
import com.ecpss.model.log.SystemLog;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.model.shop.InternationalMerchantManager;
import com.ecpss.model.shop.InternationalWebchannels;
import com.ecpss.vo.log.InternationalMerchantManagerLog;

/**
 * 商户管理值设定
 * 
 * @author huhongguang
 * 
 */
public class ManageValueSettingAction extends BaseAction {

	/** 商户id */
	private Long[] merid;

	/** 商户管理值实体对象 */
	private InternationalMerchantManager imm;

	/** 商户管理值实体对象 */
	public List<InternationalMerchantManager> internationalMerchantManagerList;

	private InternationalMerchant merchant; // 商户实体

	private Long[] merno; // 商户号

	private Long[] iid; // 商户管理值Id

	private Long markValue[]; // 分值
	private Long confirmValue[]; // 分值

	private Long[] penQuota; // 单笔交易限额(上线）

	private Long[] penQuotaLower; // 单笔交易限额(下线）

	private Long[] dayQuota; // 天交易限额

	private Long[] monthQuota; // 月交易限额

	private Long[] urlCount; // 月交易限额

	private Long merchantNo;

	private int flag;

	private String sign = "0";

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	/**
	 * 根据商户id查询商户管理值(保留)
	 * 
	 * @return
	 */
	public String findManageValueByMerchantID() {
		// 根据商户id查询商户管理值实体对象
		imm = (InternationalMerchantManager) this.commonService
				.uniqueResult("select imm from InternationalMerchantManager imm where imm.merchantId = '"
						+ merid[0] + "' ");
		// 跟具商戶id查詢商戶
		merchant = (InternationalMerchant) this.commonService
				.uniqueResult("select im from InternationalMerchant im where im.id= '"
						+ merid[0] + "' ");
		if (imm == null) {
			// 查询出默认的一条，并赋值给商户管理值对象
			imm = (InternationalMerchantManager) this.commonService
					.uniqueResult("select imm from InternationalMerchantManager imm where imm.merchantId is null");

		}
		this.getLoaction().setReload(true);
		return SUCCESS;
	}

	/**
	 * 根据商户号查询商户管理值
	 * 
	 * @return
	 */
	public String findManageValueByMerchant() {
		System.out.println("==================" + sign);
		System.out.println("商户号: " + merchantNo);
		// for (int i = 0; i < merchantNo.length; i++) {
		if (merchantNo == null || "".equals(merchantNo)) {
			internationalMerchantManagerList = commonService
					.list("select imm,im from InternationalMerchantManager imm,InternationalMerchant im "
							+ "where imm.merchantId=im.id and imm.merchantId is not null");
			return SUCCESS;
		}
		merchant = (InternationalMerchant) commonService
				.uniqueResult("select im from InternationalMerchant im where im.merno = '"
						+ merchantNo + "' ");
		if (merchant == null) {
			messageAction = "商户号不存在";
			return SUCCESS;
		}

		Long merchantId = (Long) commonService
				.uniqueResult("select count(imm.id) from InternationalMerchantManager imm where imm.merchantId = '"
						+ merchant.getId() + "' ");
		// 加载默认的一条
		if (merchantId != 1) {
			internationalMerchantManagerList = commonService
					.list("select imm,im from InternationalMerchantManager imm,InternationalMerchant im where imm.merchantId is null and im.merno= '"
							+ merchantNo + "' ");
			flag = 1;
			return SUCCESS;
		}

		internationalMerchantManagerList = commonService
				.list("select imm,im from InternationalMerchantManager imm,InternationalMerchant im where imm.merchantId=im.id and im.merno= '"
						+ merchantNo + "' ");

		// }
		return SUCCESS;
	}

	/**
	 * 去商户管理页面
	 * 
	 * @return
	 */
	public String toManageValueByMerchant() {
		try {

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			this.messageAction = "跳转到商户管理页面错误!";
			return this.OPERATE_SUCCESS;
		}
	}

	/**
	 * 保存商户管理值(保留)
	 * 
	 * @return
	 */
	public String saveManageValue1() {
		System.out.println("******************保存商户管理值******************");
		InternationalMerchantManager immTemp = null;
		// 根据商户id查询商户管理值实体对象
		immTemp = (InternationalMerchantManager) this.commonService
				.uniqueResult("select imm from InternationalMerchantManager imm where imm.merchantId = '"
						+ merid[0] + "' ");
		// 商户id不存在
		if (immTemp == null) {
			// 查询出默认的一条，并赋值给商户管理值对象
			InternationalMerchantManager internationalMerchantManager = (InternationalMerchantManager) this.commonService
					.uniqueResult("select imm from InternationalMerchantManager imm where imm.merchantId is null ");
			// 創建商戶管理值vo對象
			InternationalMerchantManagerLog iml = new InternationalMerchantManagerLog();
			// 记录更新前的状态
			iml.setDayQuota(internationalMerchantManager.getDayQuota());
			iml.setMarkValue(internationalMerchantManager.getMarkValue());
			// iml.setMerchantAddress(internationalMerchantManager
			// .getMerchantAddress());
			iml.setMerchantId(internationalMerchantManager.getMerchantId());
			iml.setMonthQuota(internationalMerchantManager.getMonthQuota());
			iml.setPenQuota(internationalMerchantManager.getPenQuota());
			iml.setMerno(merno[0].toString());

			// 保存商户管理实体对象
			InternationalMerchantManager immSave = new InternationalMerchantManager();
			immSave.setMerchantId(merid[0]);
			// immSave.setMerchantAddress(imm.getMerchantAddress());
			immSave.setMarkValue((imm.getMarkValue()));
			immSave.setPenQuota(imm.getPenQuota());
			immSave.setDayQuota(imm.getDayQuota());
			immSave.setMonthQuota(imm.getMonthQuota());
			immSave.setRiskMoney(imm.getRiskMoney());
			immSave.setChannelMoney(imm.getChannelMoney());
			this.commonService.save(immSave);

			// 更新后
			iml.setDayQuotaafter(immSave.getDayQuota());
			iml.setMarkValueafter(immSave.getMarkValue());
			// iml.setMerchantAddressafter(immSave.getMerchantAddress());
			iml.setMerchantIdafter(immSave.getMerchantId());
			iml.setMonthQuotaafter(immSave.getMonthQuota());
			iml.setPenQuotaafter(immSave.getPenQuota());

			// 设置系统日志属性
			SystemLog sl = new SystemLog();
			sl.setUserName(getUserBean().getUserName());
			sl.setOperTime(new Date());
			sl.setOperType("1");
			sl.setRemarks(iml.getUpdateLog());
			sl.setRescReow("配置商户管理值");
			this.commonService.save(sl);
			this.messageAction = "商户管理值保存成功";
			return OPERATE_SUCCESS;
		} else {
			// 记录更新前的状态
			InternationalMerchantManagerLog iml = new InternationalMerchantManagerLog();
			iml.setDayQuota(immTemp.getDayQuota());
			iml.setMarkValue(immTemp.getMarkValue());
			// iml.setMerchantAddress(immTemp.getMerchantAddress());
			iml.setMerchantId(immTemp.getMerchantId());
			iml.setMonthQuota(immTemp.getMonthQuota());
			iml.setPenQuota(immTemp.getPenQuota());
			iml.setMerno(merno[0].toString());

			// 更新商户管理实体对象
			immTemp.setMerchantId(merid[0]);
			immTemp.setMerchantAddress(imm.getMerchantAddress());
			immTemp.setMarkValue((imm.getMarkValue()));
			immTemp.setPenQuota(imm.getPenQuota());
			immTemp.setDayQuota(imm.getDayQuota());
			immTemp.setMonthQuota(imm.getMonthQuota());
			immTemp.setRiskMoney(imm.getRiskMoney());
			immTemp.setChannelMoney(imm.getChannelMoney());
			this.commonService.update(immTemp);

			// 记录操作后的状态
			iml.setDayQuotaafter(immTemp.getDayQuota());
			iml.setMarkValueafter(immTemp.getMarkValue());
			// iml.setMerchantAddressafter(immTemp.getMerchantAddress());
			iml.setMerchantIdafter(immTemp.getMerchantId());
			iml.setMonthQuotaafter(immTemp.getMonthQuota());
			iml.setPenQuotaafter(immTemp.getPenQuota());

			// 设置日志属性值
			SystemLog sl = new SystemLog();
			sl.setUserName(getUserBean().getUserName());
			sl.setOperTime(new Date());
			sl.setOperType("2");
			sl.setRemarks(iml.getUpdateLog());
			sl.setRescReow("配置商户管理值");
			this.commonService.save(sl);
			this.getLoaction().setReload(true);
			this.messageAction = "商户管理值保存成功";
			return this.OPERATE_SUCCESS;
		}

	}

	/**
	 * 保存商户管理值
	 * 
	 * @return
	 */
	public String saveManageValue() {
		InternationalMerchantManager immTemp = null;
		if (iid != null && !"".equals(iid)) {
			// 創建商戶管理值vo對象
			InternationalMerchantManagerLog iml = new InternationalMerchantManagerLog();
			// 记录更新前的状态
			InternationalMerchantManager im = null;
			try {
				for (int i = 0; i < iid.length; i++) {
					if (merid.length != 0 && !merid.equals("")) {
						// 根据商户id查询商户管理值实体对象
						immTemp = (InternationalMerchantManager) this.commonService
								.uniqueResult("select imm from InternationalMerchantManager imm where imm.merchantId = '"
										+ merid[i] + "' ");
					}
					// 商户id不存在
					if (immTemp == null) {
						// 查询出默认的一条，并赋值给商户管理值对象
						InternationalMerchantManager internationalMerchantManager = (InternationalMerchantManager) this.commonService
								.uniqueResult("select imm from InternationalMerchantManager imm where imm.merchantId is null ");
						// 记录更新前的状态
						iml.setDayQuota(internationalMerchantManager
								.getDayQuota());
						iml.setMarkValue(internationalMerchantManager
								.getMarkValue());
						iml.setMerchantId(internationalMerchantManager
								.getMerchantId());
						iml.setMonthQuota(internationalMerchantManager
								.getMonthQuota());
						iml.setPenQuota(internationalMerchantManager
								.getPenQuota());
						iml.setPenQuotaLower(internationalMerchantManager
								.getPenQuotaLower());
						iml.setMerno(merchantNo.toString());

						// 保存商户管理实体对象
						InternationalMerchantManager immSave = new InternationalMerchantManager();
						immSave.setMerchantId(merid[i]);
						// immSave.setMerchantAddress(imm.getMerchantAddress());
						immSave.setMarkValue(markValue[i]);
						immSave.setPenQuota(penQuota[i]);
						immSave.setPenQuotaLower(penQuotaLower[i]);
						immSave.setDayQuota(dayQuota[i]);
						immSave.setMonthQuota(monthQuota[i]);
//						immSave.setRiskMoney(r[i]);
//						immSave.setChannelMoney(monthQuota[i]);
						immSave.setConfirmValue(confirmValue[i]);
						immSave.setUrlCount(urlCount[i]);
						commonService.save(immSave);
						sign = "1";
						// 更新后
						iml.setDayQuotaafter(immSave.getDayQuota());
						iml.setMarkValueafter(immSave.getMarkValue());
						iml.setPenQuotaLowerafter(immSave.getPenQuotaLower());
						iml.setMerchantIdafter(immSave.getMerchantId());
						iml.setMonthQuotaafter(immSave.getMonthQuota());
						iml.setPenQuotaafter(immSave.getPenQuota());

						// 设置系统日志属性
						SystemLog sl = new SystemLog();
						sl.setUserName(getUserBean().getUserName());
						sl.setMerno(merno[i].toString());
						sl.setOperTime(new Date());
						sl.setOperType("1");
						sl.setRemarks(iml.getUpdateLog());
						sl.setRescReow("配置商户管理值");
						commonService.save(sl);
						return SUCCESS;
					} else {
						// 记录更新前的状态
						iml.setDayQuota(immTemp.getDayQuota());
						iml.setMarkValue(immTemp.getMarkValue());
						iml.setPenQuotaLowerafter(immTemp.getPenQuotaLower());
						iml.setMerchantId(immTemp.getMerchantId());
						iml.setMonthQuota(immTemp.getMonthQuota());
						iml.setPenQuota(immTemp.getPenQuota());
						iml.setMerno(merno[i].toString());

						// 更新商户管理实体对象
						immTemp = (InternationalMerchantManager) commonService
								.load(InternationalMerchantManager.class,
										iid[i]);
						immTemp.setMerchantId(merid[i]);
						immTemp.setMarkValue(markValue[i]);
						immTemp.setPenQuota(penQuota[i]);
						immTemp.setPenQuotaLower(penQuotaLower[i]);
						immTemp.setDayQuota(dayQuota[i]);
						immTemp.setMonthQuota(monthQuota[i]);
						immTemp.setConfirmValue(confirmValue[i]);
						immTemp.setUrlCount(urlCount[i]);
						commonService.update(immTemp);
						if (iid.length == 1) {
							merchantNo = Long.valueOf(iml.getMerno());
							sign = "1";
						} else if (merid.length == iid.length) {
							merchantNo = null;
							sign = "1";
						}
						// 记录操作后的状态
						iml.setDayQuotaafter(immTemp.getDayQuota());
						iml.setMarkValueafter(immTemp.getMarkValue());
						iml.setMerchantIdafter(immTemp.getMerchantId());
						iml.setMonthQuotaafter(immTemp.getMonthQuota());
						iml.setPenQuotaafter(immTemp.getPenQuota());
						iml.setPenQuotaafter(immTemp.getPenQuota());

						// 设置日志属性值
						SystemLog sl = new SystemLog();
						sl.setUserName(getUserBean().getUserName());
						sl.setMerno(merno[i].toString());
						sl.setOperTime(new Date());
						sl.setOperType("2");
						sl.setRemarks(iml.getUpdateLog());
						sl.setRescReow("配置商户管理值");
						commonService.save(sl);
					}
				}
				return SUCCESS;
			} catch (RuntimeException e) {
				e.printStackTrace();
				sign = "1";
				return SUCCESS;
			}
		}
		return SUCCESS;
	}

	public InternationalMerchantManager getImm() {
		return imm;
	}

	public void setImm(InternationalMerchantManager imm) {
		this.imm = imm;
	}

	public List<InternationalMerchantManager> getInternationalMerchantManagerList() {
		return internationalMerchantManagerList;
	}

	public void setInternationalMerchantManagerList(
			List<InternationalMerchantManager> internationalMerchantManagerList) {
		this.internationalMerchantManagerList = internationalMerchantManagerList;
	}

	public InternationalMerchant getMerchant() {
		return merchant;
	}

	public void setMerchant(InternationalMerchant merchant) {
		this.merchant = merchant;
	}

	public Long[] getIid() {
		return iid;
	}

	public void setIid(Long[] iid) {
		this.iid = iid;
	}

	public Long[] getMarkValue() {
		return markValue;
	}

	public void setMarkValue(Long[] markValue) {
		this.markValue = markValue;
	}

	public Long[] getPenQuota() {
		return penQuota;
	}

	public void setPenQuota(Long[] penQuota) {
		this.penQuota = penQuota;
	}

	public Long[] getDayQuota() {
		return dayQuota;
	}

	public void setDayQuota(Long[] dayQuota) {
		this.dayQuota = dayQuota;
	}

	public Long[] getMonthQuota() {
		return monthQuota;
	}

	public void setMonthQuota(Long[] monthQuota) {
		this.monthQuota = monthQuota;
	}

	public Long[] getMerid() {
		return merid;
	}

	public void setMerid(Long[] merid) {
		this.merid = merid;
	}

	public Long[] getMerno() {
		return merno;
	}

	public void setMerno(Long[] merno) {
		this.merno = merno;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public Long getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(Long merchantNo) {
		this.merchantNo = merchantNo;
	}

	public Long[] getPenQuotaLower() {
		return penQuotaLower;
	}

	public void setPenQuotaLower(Long[] penQuotaLower) {
		this.penQuotaLower = penQuotaLower;
	}

	public Long[] getConfirmValue() {
		return confirmValue;
	}

	public void setConfirmValue(Long[] confirmValue) {
		this.confirmValue = confirmValue;
	}

	public Long[] getUrlCount() {
		return urlCount;
	}

	public void setUrlCount(Long[] urlCount) {
		this.urlCount = urlCount;
	}

}
