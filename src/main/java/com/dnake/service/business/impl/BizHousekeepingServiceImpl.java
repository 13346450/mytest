package com.dnake.service.business.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dnake.domain.business.BizHousekeeping;
import com.dnake.domain.business.BizHousekeepingVO;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.mapper.business.BizHousekeepingMapper;
import com.dnake.service.business.BizHousekeepingService;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.service.common.CommonService;

@Service
public class BizHousekeepingServiceImpl extends BaseServiceImpl implements BizHousekeepingService {
	
	@Resource
	private BizHousekeepingMapper bizHousekeepingMapper;
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
	public Page<BizHousekeepingVO> listPage(int pageIndex, int rows, SearchParam searchParam) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sort", searchParam.getSort());
		map.put("order", searchParam.getOrder());
		Page<BizHousekeepingVO> page = new Page<BizHousekeepingVO>();
		page.setPageNo(pageIndex);
		page.setPageSize(rows);
		page.setParams(map);
		page.setRows(bizHousekeepingMapper.queryPage(page));
		return page;
	}


	/**
	 * 更新
	 * @param bizAboutUSHonor
	 * @return
	 */
	@Override
	public String update(BizHousekeeping bizHousekeeping) {
		bizHousekeepingMapper.update(bizHousekeeping);
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
	public String insert(BizHousekeeping bizHousekeeping) {
		bizHousekeepingMapper.insert(bizHousekeeping);
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
		bizHousekeepingMapper.delete(idKey);
		/*writeLog(Constants.FUNC_MENU_NM_ABOUTUS, Constants.FUNC_OPER_NM_DELETE, 
				"企业荣誉idKey:" + bizAboutUSHonor.getIdKey() + ",honorName:" + bizAboutUSHonor.getHonorName());*/
		return "{\"Msg\":\"删除成功！\",\"idKey\":\"" + idKey + "\",\"flag\":true}";
	}


	@Override
	public String[] queryCategorysByCommuntityId(Long idKey) {
		return bizHousekeepingMapper.queryCategorysByCommuntityId(idKey);
	}
	@Override
	public Page<BizHousekeepingVO> search(int pageIndex, int rows, String names,Long communityId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("all", names==null||names.equals(""));
		if(names!=null){
			map.put("names",names.split(";"));
		}
		map.put("communityId", communityId);
		Page<BizHousekeepingVO> page = new Page<BizHousekeepingVO>();
		page.setPageNo(pageIndex);
		page.setPageSize(rows);
		page.setParams(map);
		page.setRows(bizHousekeepingMapper.searchByCategorys(page));
		return page;
	}
}
