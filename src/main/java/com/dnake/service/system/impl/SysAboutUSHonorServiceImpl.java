package com.dnake.service.system.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.domain.system.SysAboutUSHonor;
import com.dnake.domain.system.SysAboutUSHonorVO;
import com.dnake.mapper.system.SysAboutUSHonorMapper;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.service.common.CommonService;
import com.dnake.service.system.SysAboutUSHonorService;

@Service
public class SysAboutUSHonorServiceImpl extends BaseServiceImpl implements SysAboutUSHonorService {
	
	@Resource
	private SysAboutUSHonorMapper sysAboutUSHonorMapper;
	@Resource
	private CommonService commonService;

	/**
	 * 分页查询
	 * @param pageIndex
	 * @param rows
	 * @param searchParam
	 * @return
	 */
	@Override
	public Page<SysAboutUSHonorVO> listPage(int pageIndex, int rows, SearchParam searchParam) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sort", searchParam.getSort());
		map.put("order", searchParam.getOrder());
		Page<SysAboutUSHonorVO> page = new Page<SysAboutUSHonorVO>();
		page.setPageNo(pageIndex);
		page.setPageSize(rows);
		page.setParams(map);
		page.setRows(sysAboutUSHonorMapper.listPage(page));
		return page;
	}
	
	/**
	 * 查询列表
	 * @param searchParam
	 * @return
	 */
	@Override
	public List<SysAboutUSHonorVO> list(SearchParam searchParam) {
		List<SysAboutUSHonorVO> bizAboutUSHonorVOList = sysAboutUSHonorMapper.list(searchParam);
		return bizAboutUSHonorVOList;
	}

	/**
	 * 根据idKey查询
	 * @param idKey
	 * @return
	 */
	@Override
	public SysAboutUSHonor queryByIdKey(Long idKey) {
		SysAboutUSHonor bizAboutUSHonor = sysAboutUSHonorMapper.queryByIdKey(idKey);
		return bizAboutUSHonor;
	}

	/**
	 * 更新
	 * @param bizAboutUSHonor
	 * @return
	 */
	@Override
	public String update(SysAboutUSHonor bizAboutUSHonor) {
		sysAboutUSHonorMapper.update(bizAboutUSHonor);
		/*writeLog(Constants.FUNC_MENU_NM_ABOUTUS, 
				 Constants.FUNC_OPER_NM_UPDATE, 
				 "企业荣誉idKey:" + bizAboutUSHonor.getIdKey() + ",honorName:" + bizAboutUSHonor.getHonorName());*/
		return "{\"Msg\":\"更新成功！\",\"flag\":true}";
	}

	/**
	 * 插入
	 * @param bizAboutUSHonor
	 * @return
	 */
	@Override
	public String insert(SysAboutUSHonor bizAboutUSHonor) {
		sysAboutUSHonorMapper.insert(bizAboutUSHonor);
		/*writeLog(Constants.FUNC_MENU_NM_ABOUTUS, 
				 Constants.FUNC_OPER_NM_CREATE, 
				 "企业荣誉idKey:" + bizAboutUSHonor.getIdKey() + ",honorName:" + bizAboutUSHonor.getHonorName());*/
		return "{\"Msg\":\"插入成功！\",\"flag\":true}";
	}

	/**
	 * 删除
	 * @param idKey
	 * @return
	 */
	@Override
	public String delete(Long idKey) {
		if (null == idKey) {
			return "{\"Msg\":\"请选中需要删除的行！\",\"flag\":false}";
		}
		SysAboutUSHonor bizAboutUSHonor = sysAboutUSHonorMapper.queryByIdKey(idKey);
		sysAboutUSHonorMapper.delete(idKey);
		/*writeLog(Constants.FUNC_MENU_NM_ABOUTUS, Constants.FUNC_OPER_NM_DELETE, 
				"企业荣誉idKey:" + bizAboutUSHonor.getIdKey() + ",honorName:" + bizAboutUSHonor.getHonorName());*/
		return "{\"Msg\":\"删除成功！\",\"idKey\":\"" + bizAboutUSHonor.getIdKey() + "\",\"flag\":true}";
	}
	
	/**
	 * 批量删除
	 * @param deleteIds
	 * @return
	 */
	@Override
	public String deleteMulti(String deleteIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deviceIdin", Arrays.asList(deleteIds.split(",")));
		sysAboutUSHonorMapper.deleteMulti(map);
		/*writeLog(Constants.FUNC_MENU_NM_ABOUTUS, Constants.FUNC_OPER_NM_DELETE, "批量删除-企业荣誉deleteIds:" + deleteIds);*/
		return "{\"Msg\":\"删除成功！\"}";
	}
}
