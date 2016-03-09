package com.dnake.tasks.event;

import org.springframework.context.ApplicationEvent;

import com.dnake.domain.business.BizUinfoCollect;

public class BizAdviceCreatedEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BizAdviceCreatedEvent(BizUinfoCollect source) {
		super(source);
	}
	@Override
	public BizUinfoCollect getSource() {
		return (BizUinfoCollect) super.getSource();
	}
	public BizUinfoCollect getBizUinfoCollect() {
		return (BizUinfoCollect) super.getSource();
	}
}