package com.dnake.service.system.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dnake.domain.common.JsonResult;
import com.dnake.domain.common.MobileParms;
import com.dnake.service.system.SysLocalTime;
import com.dnake.utils.DateTimeUtil;
import com.dnake.utils.ResultBuilderUtil;
@Service
public class SysLocalTimeImpl implements SysLocalTime {

	@Override
	public String getLocaltime(MobileParms parms) {
		Map<String,String> map=new HashMap<String, String>();
		map.put("serverTime", DateTimeUtil.getDateToStrFullFormat(new Date()));
		JsonResult js=new JsonResult();
		js.setData(map);
		String returnString=ResultBuilderUtil.jsonBuild(js);
		return returnString;
	}

}
