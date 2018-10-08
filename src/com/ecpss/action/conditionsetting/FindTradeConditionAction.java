package com.ecpss.action.conditionsetting;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ecpss.action.BaseAction;
import com.ecpss.model.log.SystemLog;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.model.shop.InternationalTradecondition;
import com.ecpss.vo.conditionsetting.InternationalTradeconditionLog;

public class FindTradeConditionAction extends BaseAction {

	private long merid; // 商户Id

	// 交易条件实体对象的集合
	private List<InternationalTradecondition> internationalTradeconditionList;

	private List<InternationalTradecondition> bigInternationalTradeconditionList;

	// 交易条件实体对象
	private InternationalTradecondition internationalTradecondition;

	private InternationalMerchant merchant; // 商户实体

	private String[] remark; // 备注

	private Long[] tradenumber; // 可交易笔数

	private Long[] cycle; // 周期

	private Long[] iid; // 交易条件表的id

	private Long[] itemno; // 交易类型

	private String[] itemName; // 交易名称

	private String merno; // 商户号

	private String flag = "0";

	/**
	 * 去交易条件设置页面
	 * 
	 * @return
	 */
	public String toConditionSetting() {
		return SUCCESS;
	}

	/**
	 * 去交易条件设置页面,并根据商户id查询(保留)
	 * 
	 * @return
	 */
	public String toConditionSetting1() {
		// 根据商户id查询交易条件跟商户的信息
		internationalTradeconditionList = this.commonService
				.list("select it,im from InternationalTradecondition it,InternationalMerchant im where it.merchantId= '"
						+ merid + "' and im.id= '" + merid + "' ");
		if (internationalTradeconditionList.size() == 0
				|| internationalTradeconditionList.isEmpty()) {
			// 查询默认的五条
			internationalTradeconditionList = this.commonService
					.list("select it,im from InternationalTradecondition it,InternationalMerchant im where it.merchantId is null and im.id= '"
							+ merid + "' ");
		}
		return SUCCESS;

	}

	/**
	 * 修改商户交易条件（保留）
	 * 
	 * @return
	 */
	public String updateCondition1() {
		// 根据商户id查询交易条件值 返回交易条件值集合对象
		internationalTradeconditionList = this.commonService
				.list("from InternationalTradecondition it where it.merchantId = "
						+ merid);
		if (iid != null && !iid.equals("")) {
			// 创建商户交易条件设置vo 记录操作前的状态
			InternationalTradeconditionLog itl = new InternationalTradeconditionLog();
			// 创建日志实体对象
			SystemLog sl = new SystemLog();
			if (internationalTradeconditionList.size() == 0
					|| internationalTradeconditionList.isEmpty()) {
				System.out.println("创建");
				for (int i = 0; i < iid.length; i++) {
					// 查询出默认的五条
					internationalTradecondition = (InternationalTradecondition) this.commonService
							.load(InternationalTradecondition.class, iid[i]);
					// 记录更新前的状态
					itl.setCycle(internationalTradecondition.getCycle());
					itl.setRemark(internationalTradecondition.getRemark());
					itl.setTradenumber(internationalTradecondition
							.getTradenumber());
					itl.setMerno(merno);
					// 更新商户交易条件
					internationalTradecondition.setMerchantId(merid);
					internationalTradecondition.setItemName(itemName[i]);
					internationalTradecondition.setItemno(itemno[i]);
					internationalTradecondition.setTradenumber(tradenumber[i]);
					internationalTradecondition.setCycle(cycle[i]);
					internationalTradecondition.setRemark(remark[i]);
					this.commonService.save(internationalTradecondition);

					// 记录操作后的状态
					itl.setCycleafter(internationalTradecondition.getCycle());
					itl.setRemarkafter(internationalTradecondition.getRemark());
					itl.setTradenumberafter(internationalTradecondition
							.getTradenumber());

					// 设置日志属性
					sl.setUserName(getUserBean().getUserName());
					sl.setOperTime(new Date());
					sl.setOperType("1");
					sl.setRemarks(itl.addInternationalTradecondit());
					sl.setRescReow("配置商户交易条件");
					this.commonService.save(sl);
				}
			} else {
				for (int i = 0; i < internationalTradeconditionList.size(); i++) {
					System.out.println("修改");
					InternationalTradecondition internationalTradecondition = internationalTradeconditionList
							.get(i);
					// 记录更新前的状态
					itl.setCycle(internationalTradecondition.getCycle());
					itl.setRemark(internationalTradecondition.getRemark());
					itl.setTradenumber(internationalTradecondition
							.getTradenumber());
					// 更新商户交易条件
					internationalTradecondition.setTradenumber(tradenumber[i]);
					internationalTradecondition.setCycle(cycle[i]);
					internationalTradecondition.setRemark(remark[i]);
					this.commonService.update(internationalTradecondition);

					// 记录操作后的状态
					itl.setCycleafter(internationalTradecondition.getCycle());
					itl.setRemarkafter(internationalTradecondition.getRemark());
					itl.setTradenumberafter(internationalTradecondition
							.getTradenumber());
					itl.setMerno(merno);

					// 设置相应属性
					sl.setUserName(this.getUserBean().getUserName());
					sl.setOperTime(new Date());
					sl.setOperType("2");
					sl.setRemarks(itl.getUpdateInternationalTradecondit());
					sl.setRescReow("配置商户交易条件");
					this.commonService.save(sl);
				}
			}
		}
		this.getLoaction().setReload(true);
		this.messageAction = "修改成功";
		return OPERATE_SUCCESS;
	}

	/**
	 * 查询商户条件设置
	 * 
	 * @return
	 */
	public String findCondition() {
		System.out.println("商户号: " + merno);
		if (merno != null && !merno.equals("")) {
			InternationalMerchant internationalMerchant = (InternationalMerchant) commonService
					.uniqueResult("select im from InternationalMerchant im where im.merno= '"
							+ merno + "' ");
			if (internationalMerchant == null) {
				messageAction = "商户号不存在!";
				return OPERATE_SUCCESS;
			}
			// 根据商户id查询交易条件跟商户的信息
			internationalTradeconditionList = this.commonService
					.list("select it,im from InternationalTradecondition it,InternationalMerchant im where it.merchantId= '"
							+ internationalMerchant.getId()
							+ "' and im.id= '"
							+ internationalMerchant.getId() + "' ");
			if (internationalTradeconditionList.size() == 0
					|| internationalTradeconditionList.isEmpty()) {
				// 查询默认的五条
				internationalTradeconditionList = this.commonService
						.list("select it,im from InternationalTradecondition it,InternationalMerchant im where it.merchantId is null and im.id= '"
								+ internationalMerchant.getId() + "' ");
			}
		}
		return SUCCESS;
	}

	/**
	 * 修改商户交易条件
	 * 
	 * @return
	 */
	public String updateCondition() {
		// 根据商户id查询交易条件值 返回交易条件值集合对象
		try {
			internationalTradeconditionList = this.commonService
					.list("from InternationalTradecondition it where it.merchantId = "
							+ merid);
			if (iid != null && !iid.equals("")) {
				// 创建商户交易条件设置vo 记录操作前的状态
				InternationalTradeconditionLog itl = new InternationalTradeconditionLog();
				// 创建日志实体对象
				SystemLog sl = new SystemLog();
				if (internationalTradeconditionList.size() == 0
						|| internationalTradeconditionList.isEmpty()) {
					System.out.println("创建");
					for (int i = 0; i < iid.length; i++) {
						// 查询出默认的五条
						internationalTradecondition = (InternationalTradecondition) this.commonService
								.load(InternationalTradecondition.class, iid[i]);
						// 记录更新前的状态
						itl.setCycle(internationalTradecondition.getCycle());
						itl.setRemark(internationalTradecondition.getRemark());
						itl.setTradenumber(internationalTradecondition
								.getTradenumber());
						itl.setMerno(merno);
						// 更新商户交易条件
						internationalTradecondition.setMerchantId(merid);
						internationalTradecondition.setItemName(itemName[i]);
						internationalTradecondition.setItemno(itemno[i]);
						internationalTradecondition
								.setTradenumber(tradenumber[i]);
						internationalTradecondition.setCycle(cycle[i]);
						internationalTradecondition.setRemark(remark[i]);
						this.commonService.save(internationalTradecondition);
						merno = itl.getMerno().toString();
						flag = "1";

						// 记录操作后的状态
						itl.setCycleafter(internationalTradecondition
								.getCycle());
						itl.setRemarkafter(internationalTradecondition
								.getRemark());
						itl.setTradenumberafter(internationalTradecondition
								.getTradenumber());

						// 设置日志属性
						sl.setUserName(getUserBean().getUserName());
						sl.setMerno(merno);
						sl.setOperTime(new Date());
						sl.setOperType("1");
						sl.setRemarks(itl.addInternationalTradecondit());
						sl.setRescReow("配置商户交易条件");
						this.commonService.save(sl);
					}
				} else {
					for (int i = 0; i < internationalTradeconditionList.size(); i++) {
						System.out.println("修改");
						InternationalTradecondition internationalTradecondition = internationalTradeconditionList
								.get(i);
						// 记录更新前的状态
						itl.setCycle(internationalTradecondition.getCycle());
						itl.setRemark(internationalTradecondition.getRemark());
						itl.setTradenumber(internationalTradecondition
								.getTradenumber());
						itl.setMerno(merno);
						// 更新商户交易条件
						internationalTradecondition
								.setTradenumber(tradenumber[i]);
						internationalTradecondition.setCycle(cycle[i]);
						internationalTradecondition.setRemark(remark[i]);
						this.commonService.update(internationalTradecondition);

						merno = itl.getMerno().toString();
						flag = "1";

						// 记录操作后的状态
						itl.setCycleafter(internationalTradecondition
								.getCycle());
						itl.setRemarkafter(internationalTradecondition
								.getRemark());
						itl.setTradenumberafter(internationalTradecondition
								.getTradenumber());

						// 设置相应属性
						sl.setUserName(getUserBean().getUserName());
						sl.setMerno(merno);
						sl.setOperTime(new Date());
						sl.setOperType("2");
						sl.setRemarks(itl.getUpdateInternationalTradecondit());
						sl.setRescReow("配置商户交易条件");
						this.commonService.save(sl);
					}
				}
			}
			return SUCCESS;
		} catch (RuntimeException e) {
			e.printStackTrace();
			flag = "2";
			return SUCCESS;
		}
	}

	public List<InternationalTradecondition> getInternationalTradeconditionList() {
		return internationalTradeconditionList;
	}

	public void setInternationalTradeconditionList(
			List<InternationalTradecondition> internationalTradeconditionList) {
		this.internationalTradeconditionList = internationalTradeconditionList;
	}

	public InternationalTradecondition getInternationalTradecondition() {
		return internationalTradecondition;
	}

	public void setInternationalTradecondition(
			InternationalTradecondition internationalTradecondition) {
		this.internationalTradecondition = internationalTradecondition;
	}

	public List<InternationalTradecondition> getBigInternationalTradeconditionList() {
		return bigInternationalTradeconditionList;
	}

	public void setBigInternationalTradeconditionList(
			List<InternationalTradecondition> bigInternationalTradeconditionList) {
		this.bigInternationalTradeconditionList = bigInternationalTradeconditionList;
	}

	public long getMerid() {
		return merid;
	}

	public void setMerid(long merid) {
		this.merid = merid;
	}

	public InternationalMerchant getMerchant() {
		return merchant;
	}

	public void setMerchant(InternationalMerchant merchant) {
		this.merchant = merchant;
	}

	public String[] getRemark() {
		return remark;
	}

	public void setRemark(String[] remark) {
		this.remark = remark;
	}

	public Long[] getTradenumber() {
		return tradenumber;
	}

	public void setTradenumber(Long[] tradenumber) {
		this.tradenumber = tradenumber;
	}

	public Long[] getCycle() {
		return cycle;
	}

	public Long[] getIid() {
		return iid;
	}

	public void setIid(Long[] iid) {
		this.iid = iid;
	}

	public void setCycle(Long[] cycle) {
		this.cycle = cycle;
	}

	public String[] getItemName() {
		return itemName;
	}

	public void setItemName(String[] itemName) {
		this.itemName = itemName;
	}

	public String getMerno() {
		return merno;
	}

	public void setMerno(String merno) {
		this.merno = merno;
	}

	public Long[] getItemno() {
		return itemno;
	}

	public void setItemno(Long[] itemno) {
		this.itemno = itemno;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
