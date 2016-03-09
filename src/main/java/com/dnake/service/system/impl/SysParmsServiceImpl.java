package com.dnake.service.system.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dnake.domain.system.SysParms;
import com.dnake.mapper.system.SysParmsMapper;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.service.system.SysParmsService;
import com.dnake.utils.Constants;
/**
 * 
 * @ClassName:SysParmsServiceImpl
 * @Description:
 * @author whm
 * @date 2013-10-22 上午11:21:19
 *
 */
@Service
public class SysParmsServiceImpl extends BaseServiceImpl implements SysParmsService{
    @Resource
	private SysParmsMapper sysParmsMapper;
	@Override
	public List<SysParms> queryAllList() {
		return sysParmsMapper.queryAll();
	}
	
	@Override
	public SysParms queryByIdKey(Long idKey) {
		return sysParmsMapper.queryByIdKey(idKey);
	}
	
	@Override
	public SysParms insert(SysParms sysParms) {
		sysParms.setChgDt(new Date());
		sysParmsMapper.insert(sysParms);
		writeLog(Constants.FUNC_MENU_NM_PARMS, Constants.FUNC_OPER_NM_CREATE,"idKey:" + sysParms.getIdKey()
				+ ",parmKey:" + sysParms.getParmKey() + ",parmValue:" + sysParms.getParmValue());
		return sysParms;
	}

	@Override
	public String delete(Long idKey) {
		SysParms sysParms = sysParmsMapper.queryByIdKey(idKey);
		writeLog(Constants.FUNC_MENU_NM_PARMS, Constants.FUNC_OPER_NM_DELETE,"idKey:" + sysParms.getIdKey()
				+ ",parmKey:" + sysParms.getParmKey() + ",parmValue:" + sysParms.getParmValue());
		sysParmsMapper.delete(idKey);
		return "{\"success\":\"true\"}";
	}

	@Override
	public SysParms update(SysParms sysParms) {
		sysParms.setChgDt(new Date());
		sysParmsMapper.update(sysParms);
		writeLog(Constants.FUNC_MENU_NM_PARMS, Constants.FUNC_OPER_NM_UPDATE,"idKey:" + sysParms.getIdKey()
				+ ",parmKey:" + sysParms.getParmKey() + ",parmValue:" + sysParms.getParmValue());
		return sysParms;
	}
}
