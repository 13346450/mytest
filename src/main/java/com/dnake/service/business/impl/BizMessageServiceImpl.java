package com.dnake.service.business.impl;
import java.util.List;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dnake.common.GlobalLoader;
import com.dnake.common.SpringContextHolder;
import com.dnake.domain.business.BizMessage;
import com.dnake.mapper.business.BizMessageMapper;
import com.dnake.service.business.BizMessageService;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.service.common.CommonService;
import com.dnake.service.common.IHttpHandler;
import com.dnake.service.common.impl.HttpRepeatSendMessageImpl;
import com.dnake.utils.HttpDataTransferUtil;
/**
 * 消息管理实现类
*  BizMessageServiceImpl <br/> 
*  2014年10月21日 上午10:08:01 <br/> 
* @author chen qige 
* @version
 */
@Service
public class BizMessageServiceImpl extends BaseServiceImpl implements  BizMessageService {
	@Resource
	BizMessageMapper bizMessageMapper;

	@Resource
	CommonService commonService;

	@Override
	public void insert(BizMessage bizMessage) {
		bizMessageMapper.insert(bizMessage);

	}

	@Override
	public void delete(Long idKey) {
		bizMessageMapper.delete(idKey);
	}

	@Override
	public BizMessage selectById(Long idKey) {
		BizMessage bizMessage = bizMessageMapper.queryById(idKey);
		return bizMessage;
	}

	@Override
	public void timerSendMessage() {
		List<BizMessage> list = bizMessageMapper.queryAll();
		String param=null;
		for (BizMessage bizMessage : list) {
		 param = "username=" + bizMessage.getUserCd() + "&title=" + bizMessage.getTitle()
					+ "&uri=&message=" +  bizMessage.getMessage();
		 IHttpHandler httpRepeatSendMessageImpl = SpringContextHolder
					.getBean("httpRepeatSendMessageImpl");
		HttpDataTransferUtil.sendPost(GlobalLoader.getInstance().getAndroidpnAddr()+"/notification.do?action=send","broadcast=N&"+param,bizMessage.getIdKey(), httpRepeatSendMessageImpl);
		}
	}

}
