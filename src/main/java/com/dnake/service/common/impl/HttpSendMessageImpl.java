package com.dnake.service.common.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dnake.domain.business.BizMessage;
import com.dnake.service.business.BizMessageService;
import com.dnake.service.common.IHttpHandler;

@Service
public class HttpSendMessageImpl implements IHttpHandler {
	@Resource
	private BizMessageService bizMessageService;

	@Override
	public void failedHandler(String parms) {
		BizMessage bizMessage=new BizMessage();
		bizMessage.setUserCd(parms.substring(parms.indexOf("username")+"username".length()+1,parms.indexOf("&",parms.indexOf("username"))));
		bizMessage.setTitle(parms.substring(parms.indexOf("title")+"title".length()+1,parms.indexOf("&",parms.indexOf("title"))));
		bizMessage.setMessage(parms.substring(parms.indexOf("message")+"message".length()+1,parms.indexOf("&",parms.indexOf("message"))));
		bizMessage.setOrderDt(new Date());
		bizMessageService.insert(bizMessage);
	}

	@Override
	public void successHandler(Long id) {
	}
	
}
