package com.ecpss.action.shop;

import java.io.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import sun.misc.BASE64Encoder;

import com.ecpss.action.BaseAction;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.model.shop.InternationalMerchantManager;
import com.ecpss.model.shop.InternationalTradecondition;
import com.ecpss.service.common.CommonService;
import com.ecpss.service.iservice.ChannelService;
import com.ecpss.service.iservice.ShopManagerService;
import com.ecpss.util.IOUitl;
import com.ecpss.util.ValidateCode;
import com.ecpss.web.PageInfo;

public class ShopManagerAction extends BaseAction {
	
	@Autowired
	@Qualifier("channelService")
	private ChannelService channelService;
	@Qualifier("shopManagerService")
	private ShopManagerService shopManagerService;
	private InternationalMerchant merchant=new InternationalMerchant();
	private String vercode;
	private String pass;
	private String passwd;
	private String hql;
	private PageInfo info;

	private String message;
	
	private String uploadFileNameno;

	private String register_code;
	public static final String REGISTER_CODE = "register_code";
	

	
	@Qualifier("commonService")
	private CommonService commonService;
	
	public String getUploadFileNameno() {
		return uploadFileNameno;
	}

	public void setUploadFileNameno(String uploadFileNameno) {
		this.uploadFileNameno = uploadFileNameno;
	}

	public InternationalMerchant getMerchant() {
		return merchant;
	}

	public void setMerchant(InternationalMerchant merchant) {
		this.merchant = merchant;
	}

	public String getVercode() {
		return vercode;
	}

	public void setVercode(String vercode) {
		this.vercode = vercode;
	}
	
	public String getRegister_code() {
		return register_code;
	}

	public void setRegister_code(String register_code) {
		this.register_code = register_code;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
		
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	// 商户注册
	public String regedit() {
		 HttpServletRequest request =  ServletActionContext.getRequest();
		 request.getParameter("uploadFileName");
		if(StringUtils.isNotBlank(merchant.getMd5key())&&merchant.getMd5key().length()>8){
			this.messageAction = "Data exception!";
			return INPUT;
		}
		try {
			hql  = "FROM InternationalMerchant m WHERE m.username='"+merchant.getUsername()+"' or m.md5key='"+merchant.getMd5key()+"'";
			List list = commonService.list(hql);
//			hql  = "FROM InternationalMerchant m WHERE m.username=?";
//			List list = commonService.list(hql,merchant.getUsername());
			if(list.size()>0){
				this.messageAction = "The user name has been registered and can not be registered!";
				return INPUT;
			}
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder base64en = new BASE64Encoder();
			// 加密后的字符串
			passwd = merchant.getPassword();
			pass = base64en.encode(md5.digest(merchant.getPassword().getBytes(
					"utf-8")));
			merchant.setPassword(pass);
			hql=" select max(im.merno) from InternationalMerchant im ";
			Long merNo= (Long)this.commonService.uniqueResult(hql);
			merchant.setMerno(merNo+1l);
			merchant.setIsopen("0");
			merchant.setLoginTime(new Date());
			merchant.setModifyPwdTime(new Date());
			merchant.setErrorCount(0L);
			merchant.setStatutes("00000000000000000000");
			merchant.setMonthTradeMoney(0D);
			String[] splitAddress=uploadFileNameno.split("/"); 
			String s2 = splitAddress[splitAddress.length-1];
			merchant.setUploadFileName(s2);
			
		
			commonService.save(merchant);

			//添加商户管理值
			List tem=this.commonService.list("from InternationalMerchantManager t where t.merchantId is null ");
			if(tem.size()>0){
				InternationalMerchantManager mi=new InternationalMerchantManager();
				mi.setMerchantId(merchant.getId());
				InternationalMerchantManager tems=(InternationalMerchantManager)tem.get(0);
				mi.setDayQuota(tems.getDayQuota());
				mi.setMarkValue(tems.getMarkValue());
				mi.setMonthQuota(tems.getMonthQuota());
				mi.setPenQuota(tems.getPenQuota());
				this.commonService.saveOrUpdate(mi);
				
			}
			//添加条件设置植
			List temList2=this.commonService.list(" from InternationalTradecondition t where t.merchantId is null ");
			if(temList2.size()>0){
			for(int i=0;i<temList2.size();i++){
				InternationalTradecondition itr=new InternationalTradecondition();
				InternationalTradecondition tem2=(InternationalTradecondition)temList2.get(i);
				itr.setCycle(tem2.getCycle());
				itr.setItemName(tem2.getItemName());
				itr.setItemno(tem2.getItemno());
				itr.setTradenumber(tem2.getTradenumber());
				itr.setMerchantId(merchant.getId());
				this.commonService.saveOrUpdate(itr);
			}	
			
				
				
			}
			
			// Long merchantno = merRegService.getMerNo(merchant.getUsername());
			// 权限表添加商户
			// System.out.println("merchant.getMerno()--------------"+merchant.getMerno());
			// merAuthService.addMerchant(merchant.getMerno(),
			// merchant.getUsername(), pass);
			// hql = "frsssom InternationalMerchant nm where nm.username =
			// '"+merchant.getUsername()+"'";
			// merchant = (InternationalMerchant)gatherService.findObject(hql);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return INPUT;
		}

	}
	/**
	 * 迁易通注册
	 * @return
	 */
	public String register(){
		String code = (String) ServletActionContext.getRequest().getSession().getAttribute(REGISTER_CODE);
		if (this.register_code == null || code == null ||
				register_code.equalsIgnoreCase(code) == false)
		{
			this.messageAction = "验证码错误";
			return INPUT;
		}
		if (merchant == null || merchant.getUsername() == null || 
				merchant.getUsername().trim().equals(""))
		{
			this.messageAction = "请输入用户名";
			return INPUT;
		}
		if (merchant.getPassword() == null || merchant.getPassword().length() < 6 || merchant.getPassword().length() > 16)
		{
			this.messageAction = "请输入6~16位密码";
			return INPUT;
		}
		if (merchant.getMermobile() == null || merchant.getMermobile().length() != 11)
		{
			this.messageAction = "请输入11位手机号";
			return INPUT;
		}
		hql  = "FROM InternationalMerchant m WHERE m.username= ? ";
		List list = commonService.list(hql, merchant.getUsername());
		if(list.size()>0){
			this.messageAction = "The user name has been registered and can not be registered!";
			return INPUT;
		}
		
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder base64en = new BASE64Encoder();
			// 加密后的字符串
			passwd = merchant.getPassword();
			pass = base64en.encode(md5.digest(merchant.getPassword().getBytes(
					"utf-8")));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			this.messageAction = "系统错误";
			return INPUT;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			this.messageAction = "系统错误";
			return INPUT;
		}
		
		merchant.setPassword(pass);
		hql=" select max(im.merno) from InternationalMerchant im ";
		Long merNo= (Long)this.commonService.uniqueResult(hql);
		merchant.setMerno(merNo+1l);
		merchant.setIsopen("0");
		merchant.setLoginTime(new Date());
		merchant.setModifyPwdTime(new Date());
		merchant.setErrorCount(0L);
		merchant.setStatutes("00000000000000000000");
		merchant.setMonthTradeMoney(0D);
		commonService.save(merchant);
		return SUCCESS;
	}
	// 跳转到商户管理页面
	public String toManagerShop() {

		if(this.info==null){
			this.info = new PageInfo();
		}

		StringBuffer hql=new StringBuffer();
		hql.append("from InternationalMerchant t  where 1=1 ");
		if(this.merchant.getMerno()!=null){
			hql.append(" and t.merno like '%"+merchant.getMerno()+"%'");
		}
		if((this.merchant.getUsername()!=null)&&(!this.merchant.getUsername().equals(""))){
			hql.append(" and t.username  like '%"+merchant.getUsername()+"%'");
		}
		hql.append(" order by t.id desc");
		this.info=this.commonService.listQueryResultByHql(hql.toString(), info);
		return SUCCESS;
	}
	/**
	 * 获取验证码图片
	 * @return
	 * @throws IOException 
	 */
	public String registerCode() throws IOException {
		//生成验证码
		ValidateCode validateCode = new ValidateCode();
		ServletActionContext.getRequest().getSession().setAttribute(REGISTER_CODE, validateCode.getCode());
		ByteArrayOutputStream os = new ByteArrayOutputStream();  
		ImageIO.write(validateCode.getBuffImg(), "png", os);  
		InputStream is = new ByteArrayInputStream(os.toByteArray());
		os.close();
		this.inputStream = is;
		return SUCCESS;
	}
	
	private InputStream inputStream;  
    
	public InputStream getInputStream() {  
	    return inputStream;  
	}  
	public void setInputStream(InputStream inputStream) {  
	    this.inputStream = inputStream;  
	}  
	
	private List merChannelList;
	private List merCreditCardList;
	private Long merno;
	private List listcurrency;
	private List merchantTerminalList;
	private List merchantNoList;
	
	/** 商户管理值实体对象 */
	public List<InternationalMerchantManager> internationalMerchantManagerList;
	private List<InternationalTradecondition> internationalTradeconditionList;
	private List tradeList;

	public List getTradeList() {
		return tradeList;
	}

	public void setTradeList(List tradeList) {
		this.tradeList = tradeList;
	}

	/**
	 * 获取所有商户设置信息
	 * @return
	 */
	public String getMerchantInfo(){
		//获取商户通道列表
		String hlq="select m.id from InternationalMerchant m where m.merno="+merno;
		Long merid = (Long) this.commonService.uniqueResult(hlq);
		merChannelList = channelService.getMerChannelList(merid);
		//卡种管理
//		String hlq2="select m.id from InternationalMerchant m where m.merno="+merno;
//		merid = (Long) this.commonService.uniqueResult(hlq2);
		merCreditCardList = shopManagerService.getMerCreditCardManagerList(merid);
		//商户币种
		String hql3 = "FROM InternationalMerchant mer, InternationalMerchantCurrency currency, InternationalMoneykindname money " +
	  	  "WHERE mer.id = currency.merchanId AND currency.moneyKindNo = money.id AND mer.merno="+merno;
		listcurrency = commonService.list(hql3);
		//商户风控制值设置 
		internationalMerchantManagerList = commonService
		.list("select imm,im from InternationalMerchantManager imm,InternationalMerchant im where imm.merchantId=im.id and im.merno="+ merno);
		//交易条件
		// 根据商户id查询交易条件跟商户的信息
		internationalTradeconditionList = this.commonService
				.list("select it,im from InternationalTradecondition it,InternationalMerchant im where it.merchantId=im.id and im.id="+merid);
		//结算日
		tradeList = this.commonService
		.list(" select t,f from SettlementCycleTime t ,InternationalMerchant f where t.merchant=f.id and f.merno="+merno);
		
		
		
		//商户单独终端
		StringBuffer sf = new StringBuffer();
		sf.append("select mt,tm,c from InternationalMerchantTerminal mt," +
				"InternationalMerchant m," +
				"InternationalTerminalManager tm," +
				"InternationalChannels c " +
				"where m.id=mt.merchantId " +
				"and tm.id=mt.terminalId " +
				"and c.id=mt.channelId ");
			//终端号
		//商户号
		if(merno!=null){
			sf.append(" and m.merno="+merno+" ");
			String hql=" and (c.channelName like 'VIP%' or c.channelName like 'EVIP%')";
			merchantTerminalList = this.commonService.list(sf.toString()+hql);
			//String hql1=" and c.channelName like 'VC%' ";
			
			StringBuffer sf1 = new StringBuffer();
			sf1.append("select mt,tm,c from InternationalMerchantTerminal mt," +
					"InternationalMerchant m," +
					"InternationalMigsMerchantNo tm," +
					"InternationalChannels c " +
					"where m.id=mt.merchantId " +
					"and tm.id=mt.terminalId " +
					"and c.id=mt.channelId ");
			sf1.append(" and c.channelName like 'VC%' ");
			sf1.append(" and m.merno="+merno+" ");
			merchantNoList = this.commonService.list(sf1.toString());
		}
		
		return SUCCESS;
	}
	
	
	public PageInfo getInfo() {
		return info;
	}

	public void setInfo(PageInfo info) {
		this.info = info;
	}

	public CommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	public ChannelService getChannelService() {
		return channelService;
	}

	public void setChannelService(ChannelService channelService) {
		this.channelService = channelService;
	}

	public List getMerChannelList() {
		return merChannelList;
	}

	public void setMerChannelList(List merChannelList) {
		this.merChannelList = merChannelList;
	}

	public Long getMerno() {
		return merno;
	}

	public void setMerno(Long merno) {
		this.merno = merno;
	}

	public ShopManagerService getShopManagerService() {
		return shopManagerService;
	}

	public void setShopManagerService(ShopManagerService shopManagerService) {
		this.shopManagerService = shopManagerService;
	}

	public String getHql() {
		return hql;
	}

	public void setHql(String hql) {
		this.hql = hql;
	}

	public List getMerCreditCardList() {
		return merCreditCardList;
	}

	public void setMerCreditCardList(List merCreditCardList) {
		this.merCreditCardList = merCreditCardList;
	}

	public List getListcurrency() {
		return listcurrency;
	}

	public void setListcurrency(List listcurrency) {
		this.listcurrency = listcurrency;
	}

	public List<InternationalMerchantManager> getInternationalMerchantManagerList() {
		return internationalMerchantManagerList;
	}

	public void setInternationalMerchantManagerList(
			List<InternationalMerchantManager> internationalMerchantManagerList) {
		this.internationalMerchantManagerList = internationalMerchantManagerList;
	}

	public List<InternationalTradecondition> getInternationalTradeconditionList() {
		return internationalTradeconditionList;
	}

	public void setInternationalTradeconditionList(
			List<InternationalTradecondition> internationalTradeconditionList) {
		this.internationalTradeconditionList = internationalTradeconditionList;
	}

	public List getMerchantTerminalList() {
		return merchantTerminalList;
	}

	public void setMerchantTerminalList(List merchantTerminalList) {
		this.merchantTerminalList = merchantTerminalList;
	}

	public List getMerchantNoList() {
		return merchantNoList;
	}

	public void setMerchantNoList(List merchantNoList) {
		this.merchantNoList = merchantNoList;
	}
	
	private static final String folderPath = "upload\\authentication";
	private static final String folderPathTemp = "upload\\authentication_temp";
	
	
	// 封装单个上传文件域的属性
	private File file;

	// 封装单个上传文件名的属性
	private String fileFileName;
	
	 
	 public void uploadcard() throws IOException{
		 HttpServletResponse response =  ServletActionContext.getResponse();
		 String root = ServletActionContext.getServletContext().getRealPath("\\"+folderPathTemp);
	        String distinctName = UUID.randomUUID().toString();
	        if (fileFileName == null || fileFileName.equals(""))
	        {
	        	message = "_error_";
	        	response.getOutputStream().print(message);
	        }
	        String expandName = fileFileName.substring(fileFileName.lastIndexOf("."));
	        distinctName += expandName;
	        System.out.println("fileFileName: " + fileFileName);
	        InputStream is = new FileInputStream(file);
	        OutputStream os = new FileOutputStream(new File(root, distinctName));
	        
	        byte[] buffer = new byte[500];
	        int length = 0;
	        
	        while(-1 != (length = is.read(buffer, 0, buffer.length)))
	        {
	            os.write(buffer);
	        }
	        
	        os.close();
	        is.close();
	        message = "_"+distinctName+"_";
	        response.getOutputStream().print(message);		
	}
	 
	 private String saveFile(String path)
		{
			
			String root = ServletActionContext.getServletContext().getRealPath("");
			String fileName =path.substring(path.lastIndexOf("/") + 1);
			String target = root+"\\"+folderPath+"\\"+fileName;
		//	root = root.substring(0,root.lastIndexOf("\\"));
			String source = root+ "\\"+ path; //正面
			try {
				IOUitl.copyFile(source, target);
			} catch (IOException e) {
				message = "保存照片时出现异常";
				e.printStackTrace();
			}
			return folderPath+"\\"+fileName;
		}
	 
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	
	public String see(){
		InternationalMerchant mer = (InternationalMerchant) commonService.uniqueResult("from InternationalMerchant where id='"+merid+"'");
		photosrc = "/upload/authentication_temp/"+mer.getUploadFileName();
		return SUCCESS;
	}
	
	public String merid;
	public String photosrc;
	public String getMerid() {
		return merid;
	}

	public void setMerid(String merid) {
		this.merid = merid;
	}

	public String getPhotosrc() {
		return photosrc;
	}

	public void setPhotosrc(String photosrc) {
		this.photosrc = photosrc;
	}
	
}
