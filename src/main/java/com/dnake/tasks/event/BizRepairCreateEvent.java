package com.dnake.tasks.event;

import org.springframework.context.ApplicationEvent;

import com.dnake.domain.business.BizPropertiesRepair;

public class BizRepairCreateEvent extends ApplicationEvent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BizRepairCreateEvent(BizPropertiesRepair biz) {
		super(biz);
	}

	public BizPropertiesRepair getBizPropertiesRepair() {
		return (BizPropertiesRepair) getSource();
	}
}