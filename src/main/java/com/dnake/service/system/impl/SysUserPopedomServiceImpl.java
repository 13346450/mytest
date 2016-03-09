package com.dnake.service.system.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.domain.system.SysUserPopedom;
import com.dnake.domain.system.SysUserPopedomVO;
import com.dnake.mapper.system.SysUserPopedomMapper;
import com.dnake.service.system.SysUserPopedomService;
import com.dnake.service.common.BaseServiceImpl;

@Service
public class SysUserPopedomServiceImpl extends BaseServiceImpl implements
		SysUserPopedomService {
	@Resource
	private SysUserPopedomMapper sysUserPopedomMapper;

	@Override
	public String update(SysUserPopedom sysUserPopedom) {
		// sysUserPopedom.setChgDt(new Date());
		sysUserPopedomMapper.update(sysUserPopedom);
		return "{\"successMsg\":\"更新成功！\",\"flag\":true}";
	}

	@Override
	public String insert(SysUserPopedom sysUserPopedom) {
		// sysUserPopedom.setChgDt(new Date());
		sysUserPopedomMapper.insert(sysUserPopedom);
		return "{\"successMsg\":\"插入成功！\",\"flag\":true}";
	}

	@Override
	public String deleteMulti(String deleteIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deviceIdin", Arrays.asList(deleteIds.split(",")));
		sysUserPopedomMapper.deleteMulti(map);
		return "{\"successMsg\":\"删除成功！\",\"flag\":true}";
	}

	@Override
	public String queryCommunityIds(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Page<SysUserPopedomVO> listPage(int pageIndex, int rows,
			SearchParam searchParam) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", searchParam.getIdKey());
		Page<SysUserPopedomVO> page = new Page<SysUserPopedomVO>();
		page.setPageNo(pageIndex);
		page.setPageSize(rows);
		page.setParams(map);
		page.setRows(sysUserPopedomMapper.queryPage(page));
		return page;
	}

	@Override
	public Integer getMinType() {
		return sysUserPopedomMapper.getMinType(getUserBean().getUserId());
	}

	@Override
	public List<SysUserPopedomVO> getProvince() {
		return sysUserPopedomMapper.queryProvince(getUserBean().getUserId());
	}

	@Override
	public String insert(String depts, long userId, int type) {
		List<SysUserPopedom> list=new LinkedList<SysUserPopedom>();
		String[] a =depts.split(",");
		for (String string : a) {
			SysUserPopedom s=new SysUserPopedom();
			s.setDeptId(Long.valueOf(string));
			s.setType(type);
			s.setUserId(userId);
			list.add(s);
		}
		sysUserPopedomMapper.insertMulti(list);
		return "{\"successMsg\":\"插入成功！\",\"flag\":true}";
	}

	@Override
	public List<SysUserPopedomVO> getCity(String depts) {
		return sysUserPopedomMapper.queryCity(getUserBean().getUserId(),depts);
	}

	@Override
	public List<SysUserPopedomVO> getCommunity(String depts) {
		return  sysUserPopedomMapper.queryCommunity(getUserBean().getUserId(),depts);
	}
}
