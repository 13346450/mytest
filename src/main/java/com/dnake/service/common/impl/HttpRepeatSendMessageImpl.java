package com.dnake.service.common.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dnake.domain.business.BizMessage;
import com.dnake.service.business.BizMessageService;
import com.dnake.service.common.IHttpHandler;

@Service
public class HttpRepeatSendMessageImpl implements IHttpHandler {
	
	@Resource
	private BizMessageService bizMessageService;

	@Override
	public void failedHandler(String parms) {
	}

	@Override
	public void successHandler(Long idKey) {
		bizMessageService.delete(idKey);
	}

	

}
