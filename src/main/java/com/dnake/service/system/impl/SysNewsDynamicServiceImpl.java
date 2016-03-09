package com.dnake.service.system.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.domain.system.SysNewsDynamic;
import com.dnake.domain.system.SysNewsDynamicVO;
import com.dnake.mapper.system.SysNewsDynamicMapper;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.service.common.CommonService;
import com.dnake.service.system.SysNewsDynamicService;

@Service
public class SysNewsDynamicServiceImpl extends BaseServiceImpl implements SysNewsDynamicService {
	
	@Resource
	private SysNewsDynamicMapper bizNewsDynamicMapper;
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
	public Page<SysNewsDynamicVO> listPage(int pageIndex, int rows, SearchParam searchParam) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sort", searchParam.getSort());
		map.put("order", searchParam.getOrder());
		Page<SysNewsDynamicVO> page = new Page<SysNewsDynamicVO>();
		page.setPageNo(pageIndex);
		page.setPageSize(rows);
		page.setParams(map);
		page.setRows(bizNewsDynamicMapper.listPage(page));
		return page;
	}
	
	/**
	 * 查询列表
	 * @param searchParam
	 * @return
	 */
	@Override
	public List<SysNewsDynamicVO> list(SearchParam searchParam) {
		List<SysNewsDynamicVO> bizNewsDynamicVOList = bizNewsDynamicMapper.list(searchParam);
		return bizNewsDynamicVOList;
	}

	/**
	 * 根据idKey查询
	 * @param idKey
	 * @return
	 */
	@Override
	public SysNewsDynamic queryByIdKey(Long idKey) {
		SysNewsDynamic bizNewsDynamic = bizNewsDynamicMapper.queryByIdKey(idKey);
		return bizNewsDynamic;
	}

	/**
	 * 更新
	 * @param bizNewsDynamic
	 * @return
	 */
	@Override
	public String update(SysNewsDynamic bizNewsDynamic) {
		bizNewsDynamicMapper.update(bizNewsDynamic);
		/*writeLog(Constants.FUNC_MENU_NM_NEWSDYNAMIC, 
				 Constants.FUNC_OPER_NM_UPDATE, 
				 "新闻动态idKey:" + bizNewsDynamic.getIdKey() + ",newsTitle:" + bizNewsDynamic.getNewsTitle());*/
		return "{\"Msg\":\"更新成功！\",\"flag\":true}";
	}

	/**
	 * 插入
	 * @param bizNewsDynamic
	 * @return
	 */
	@Override
	public String insert(SysNewsDynamic bizNewsDynamic) {
		bizNewsDynamicMapper.insert(bizNewsDynamic);
		/*writeLog(Constants.FUNC_MENU_NM_NEWSDYNAMIC, 
				 Constants.FUNC_OPER_NM_CREATE, 
				 "新闻动态idKey:" + bizNewsDynamic.getIdKey() + ",newsTitle:" + bizNewsDynamic.getNewsTitle());*/
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
		SysNewsDynamic bizNewsDynamic = bizNewsDynamicMapper.queryByIdKey(idKey);
		bizNewsDynamicMapper.delete(idKey);
		/*writeLog(Constants.FUNC_MENU_NM_NEWSDYNAMIC, 
				 Constants.FUNC_OPER_NM_DELETE, 
				 "新闻动态idKey:" + bizNewsDynamic.getIdKey() + ",newsTitle:" + bizNewsDynamic.getNewsTitle());*/
		return "{\"Msg\":\"删除成功！\",\"idKey\":\"" + bizNewsDynamic.getIdKey() + "\",\"flag\":true}";
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
		bizNewsDynamicMapper.deleteMulti(map);
		/*writeLog(Constants.FUNC_MENU_NM_NEWSDYNAMIC, 
				 Constants.FUNC_OPER_NM_DELETE, 
				 "批量删除-新闻动态deleteIds:" + deleteIds);*/
		return "{\"Msg\":\"删除成功！\"}";
	}
	/**
	 * 新闻类型分页查询
	 */
	@Override
	public Page<SysNewsDynamicVO> pageByType(int pageIndex, int size, SysNewsDynamic dynamic) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("newsType", dynamic.getNewsType());
		Page<SysNewsDynamicVO> page = new Page<SysNewsDynamicVO>();
		page.setPageNo(pageIndex);
		page.setPageSize(size);
		page.setParams(params);
		//page.setTotal(bizNewsDynamicMapper.countByType(page));
		page.setRows(bizNewsDynamicMapper.pageByType(page));
		return page;
	}
}
