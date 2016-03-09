package com.dnake.service.system.impl;

import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dnake.domain.common.Page;
import com.dnake.domain.system.SysLogs;
import com.dnake.mapper.system.SysLogsMapper;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.service.system.SysLogsService;
import com.dnake.utils.SessionBean;

@Service
public class SysLogsServiceImpl extends BaseServiceImpl implements SysLogsService {

	@Resource
	private SysLogsMapper sysLogsMapper;
	
	public SysLogs edit(Long idKey){
		return sysLogsMapper.edit(idKey);
	}
	
	public String update(SysLogs sysLogs){
		sysLogsMapper.update(sysLogs);
		return "{\"successMsg\":\"更新成功！\"}";
	}
	
	public String delete(Long idKey){
		sysLogsMapper.delete(idKey);
		return "{\"successMsg\":\"删除成功！\"}";
	}
	
	public String insert(String funcMenuNm, String funcOperNm, String operRemark){
		SessionBean sessionBean = (SessionBean) session.getAttribute("sessionBean");
		SysLogs sysLogs = new SysLogs();
		sysLogs.setFuncMenuNm(funcMenuNm);
		sysLogs.setFuncOperNm(funcOperNm);
		sysLogs.setOperId(sessionBean.getUserId());
		sysLogs.setOperNm(sessionBean.getUserName());
		sysLogs.setOperRemark(operRemark);
		sysLogs.setOperDt(new Date());
		sysLogsMapper.insert(sysLogs);
		return "{\"successMsg\":\"添加成功！\"}";
	}
	public Page<SysLogs> listPage(int pageIndex, int rows,String dataTimeBegin,String dataTimeEnd,String operNm,String sort,String order){
		if(dataTimeBegin != null&&!dataTimeBegin.equals("")
				&& dataTimeEnd != null&& !dataTimeEnd.equals("")){
			dataTimeBegin=dataTimeBegin + " 00:00:00";
			dataTimeEnd=dataTimeEnd+ " 23:59:59";}
		Page<SysLogs> page = new Page<SysLogs>();
		page.setPageNo(pageIndex);
		page.setPageSize(rows);
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("dataTimeBegin", dataTimeBegin);
		params.put("dataTimeEnd", dataTimeEnd);
		params.put("operNm", operNm);
		params.put("sort",sort);
		params.put("order",order);
		page.setParams(params);
		page.setRows(sysLogsMapper.queryPage(page));
		return page;
	}
}
