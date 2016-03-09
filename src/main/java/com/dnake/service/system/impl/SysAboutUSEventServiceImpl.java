package com.dnake.service.system.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.domain.system.SysAboutUSEvent;
import com.dnake.domain.system.SysAboutUSEventVO;
import com.dnake.mapper.system.SysAboutUSEventMapper;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.service.common.CommonService;
import com.dnake.service.system.SysAboutUSEventService;

@Service
public class SysAboutUSEventServiceImpl extends BaseServiceImpl implements SysAboutUSEventService {
	
	@Resource
	private SysAboutUSEventMapper bizAboutUSEventMapper;
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
	public Page<SysAboutUSEventVO> listPage(int pageIndex, int rows, SearchParam searchParam) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sort", searchParam.getSort());
		map.put("order", searchParam.getOrder());
		Page<SysAboutUSEventVO> page = new Page<SysAboutUSEventVO>();
		page.setPageNo(pageIndex);
		page.setPageSize(rows);
		page.setParams(map);
		page.setRows(bizAboutUSEventMapper.listPage(page));
		return page;
	}

	/**
	 * 查询列表
	 * @param searchParam
	 * @return
	 */
	@Override
	public List<SysAboutUSEventVO> list(SearchParam searchParam) {
		List<SysAboutUSEventVO> bizAboutUSEventVOList = bizAboutUSEventMapper.list(searchParam);
		return bizAboutUSEventVOList;
	}

	/**
	 * 根据idKey查询
	 * @param idKey
	 * @return
	 */
	@Override
	public SysAboutUSEvent queryByIdKey(Long idKey) {
		SysAboutUSEvent bizAboutUSEvent = bizAboutUSEventMapper.queryByIdKey(idKey);
		return bizAboutUSEvent;
	}

	/**
	 * 更新
	 * @param bizAboutUSEvent
	 * @return
	 */
	@Override
	public String update(SysAboutUSEvent bizAboutUSEvent) {
		bizAboutUSEventMapper.update(bizAboutUSEvent);
		/*writeLog(Constants.FUNC_MENU_NM_ABOUTUS, 
				 Constants.FUNC_OPER_NM_UPDATE, 
				 "大事记idKey:" + bizAboutUSEvent.getIdKey() + ",aboutUSTitle:" + bizAboutUSEvent.getEventTitle());*/
		return "{\"Msg\":\"更新成功！\",\"flag\":true}";
	}

	/**
	 * 插入
	 * @param bizAboutUSEvent
	 * @return
	 */
	@Override
	public String insert(SysAboutUSEvent bizAboutUSEvent) {
		bizAboutUSEventMapper.insert(bizAboutUSEvent);
		/*writeLog(Constants.FUNC_MENU_NM_ABOUTUS, 
				 Constants.FUNC_OPER_NM_CREATE, 
				 "大事记idKey:" + bizAboutUSEvent.getIdKey() + ",aboutUSTitle:" + bizAboutUSEvent.getEventTitle());*/
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
		SysAboutUSEvent bizAboutUSEvent = bizAboutUSEventMapper.queryByIdKey(idKey);
		bizAboutUSEventMapper.delete(idKey);
		/*writeLog(Constants.FUNC_MENU_NM_ABOUTUS, 
				 Constants.FUNC_OPER_NM_DELETE, 
				 "大事记idKey:" + bizAboutUSEvent.getIdKey() + ",aboutUSTitle:" + bizAboutUSEvent.getEventTitle());*/
		return "{\"Msg\":\"删除成功！\",\"idKey\":\"" + bizAboutUSEvent.getIdKey() + "\",\"flag\":true}";
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
		bizAboutUSEventMapper.deleteMulti(map);
		/*writeLog(Constants.FUNC_MENU_NM_ABOUTUS, Constants.FUNC_OPER_NM_DELETE, "批量删除-大事记deleteIds:" + deleteIds);*/
		return "{\"Msg\":\"删除成功！\"}";
	}
}
