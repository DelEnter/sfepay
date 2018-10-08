package com.ecpss.service.implservice;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecpss.dao.common.CommonDao;
import com.ecpss.model.refund.InternationalRefundHistory;
import com.ecpss.model.risk.InternationalBacklist;
import com.ecpss.model.risk.InternationalRisklist;
import com.ecpss.service.iservice.ImportExcelService;
import com.ecpss.vo.ImportRefundHistory;

@Service("importExcelService")
@Transactional
public class ImportExcelServiceImpl implements ImportExcelService {

	@Autowired
	@Qualifier("commonDao")
	private CommonDao commonDao;
	
	public boolean getBackCardBean(String card) {
		String hql="select b.cardno from InternationalBacklist b where b.cardno='"+card+"' ";
		List cardno = this.commonDao.list(hql);
		//如果不为空就返回false
		if(cardno.size()>0){
			return false;
		}else{
			return true;
		}
		
	}

	public void saveBackCardInfo(String IP, String cardNo, String Email,
			String cookie, String remark) {
		InternationalBacklist b = new InternationalBacklist();
		b.setIp(IP);
		b.setCardno(cardNo);
		b.setEmail(Email);
		b.setCookie(cookie);
		b.setRemark(remark);
		this.commonDao.save(b);
		
	}

	public void saveRickCardInfo(String IP, String cardNo, String Email,
			String cookie, String remark) {
		InternationalRisklist r = new InternationalRisklist();
		r.setIp(IP);
		r.setCardno(cardNo);
		r.setEmail(Email);
		r.setCookie(cookie);
		r.setRemark(remark);
		this.commonDao.save(r);
		
	}

	public void saveRefundByBank(ImportRefundHistory rh,String fileName,String batchno) {
		InternationalRefundHistory r = new InternationalRefundHistory();
		r.setCardNo(rh.getCardNo());
		r.setAuthorizationNo(rh.getAuthorizationNo());
		r.setRefundAmount(Double.valueOf(rh.getRefundAmount().trim()));
		r.setTerminalNo(rh.getTerminalNo());
		r.setTradeTime(rh.getTradeTime());
		r.setTradeAmount(Double.valueOf(rh.getTradeAmount().trim()));
		r.setCheckInman(rh.getCheckInman());
		r.setPhone(rh.getPhone());
		r.setReason(rh.getReason());
		r.setFileName(fileName);
		r.setPhone(batchno);
		this.commonDao.save(r);
	}

	public Boolean getFileNameByRefund(String filename) {
		String hql = "select rh.id from InternationalRefundHistory rh where rh.fileName='"+filename.trim()+"' ";
		if(this.commonDao.list(hql).size()>0){
			return false;
		}else{
			return true;
		}
	}
	
	
}
