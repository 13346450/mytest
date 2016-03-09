package com.dnake.service.system.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dnake.domain.system.SysAboutUS;
import com.dnake.mapper.system.SysAboutUSMapper;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.service.common.CommonService;
import com.dnake.service.system.SysAboutUSService;

@Service
public class SysAboutUSServiceImpl extends BaseServiceImpl implements SysAboutUSService {
	
	@Resource
	private SysAboutUSMapper sysAboutUSMapper;
	@Resource
	private CommonService commonService;

//	/**
//	 * 分页查询
//	 * @param pageIndex
//	 * @param rows
//	 * @param searchParam
//	 * @return
//	 */
//	@Override
//	public Page<SysAboutUSVO> listPage(int pageIndex, int rows, SearchParam searchParam) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("sort", searchParam.getSort());
//		map.put("order", searchParam.getOrder());
//		Page<SysAboutUSVO> page = new Page<SysAboutUSVO>();
//		page.setPageNo(pageIndex);
//		page.setPageSize(rows);
//		page.setParams(map);
//		page.setRows(bizAboutUSMapper.queryPage(page));
//		return page;
//	}
//
//	/**
//	 * 查询列表
//	 * @param searchParam
//	 * @return
//	 */
//	@Override
//	public List<SysAboutUSVO> list(SearchParam searchParam) {
//		List<SysAboutUSVO> bizAboutUSVOList = bizAboutUSMapper.list(searchParam);
//		return bizAboutUSVOList;
//	}
//	
//	/**
//	 * 根据idKey查询
//	 * @param idKey
//	 * @return
//	 */
//	@Override
//	public SysAboutUS queryByIdKey(Long idKey) {
//		SysAboutUS bizAboutUS = bizAboutUSMapper.queryByIdKey(idKey);
//		return bizAboutUS;
//	}
//
//	/**
//	 * 更新
//	 * @param bizAboutUS
//	 * @return
//	 */
//	@Override
//	public String update(SysAboutUS bizAboutUS) {
//		bizAboutUSMapper.update(bizAboutUS);
//		/*writeLog(Constants.FUNC_MENU_NM_ABOUTUS, 
//				 Constants.FUNC_OPER_NM_UPDATE, 
//				 "关于我们idKey:" + bizAboutUS.getIdKey() + ",aboutUSTitle:" + bizAboutUS.getAboutUSTitle());*/
//		return "{\"Msg\":\"更新成功！\",\"flag\":true}";
//	}
//
//	/**
//	 * 插入
//	 * @param bizAboutUS
//	 * @return
//	 */
//	@Override
//	public String insert(SysAboutUS bizAboutUS) {
//		bizAboutUSMapper.insert(bizAboutUS);
//		/*writeLog(Constants.FUNC_MENU_NM_ABOUTUS, 
//				 Constants.FUNC_OPER_NM_CREATE, 
//				 "关于我们idKey:" + bizAboutUS.getIdKey() + ",aboutUSTitle:" + bizAboutUS.getAboutUSTitle());*/
//		return "{\"Msg\":\"插入成功！\",\"flag\":true}";
//	}
//	
//	/**
//	 * 删除
//	 * @param idKey
//	 * @return
//	 */
//	@Override
//	public String delete(Long idKey) {
//		if (null == idKey) {
//			return "{\"Msg\":\"请选中需要删除的行！\",\"flag\":false}";
//		}
//		SysAboutUS bizAboutUS = bizAboutUSMapper.queryByIdKey(idKey);
//		bizAboutUSMapper.delete(idKey);
//		/*writeLog(Constants.FUNC_MENU_NM_ABOUTUS, 
//				 Constants.FUNC_OPER_NM_DELETE, 
//				 "关于我们idKey:" + bizAboutUS.getIdKey() + ",aboutUSTitle:" + bizAboutUS.getAboutUSTitle());*/
//		return "{\"Msg\":\"删除成功！\",\"idKey\":\"" + bizAboutUS.getIdKey() + "\",\"flag\":true}";
//	}
//
//	/**
//	 * 批量删除
//	 * @param deleteIds
//	 * @return
//	 */
//	@Override
//	public String deleteMulti(String deleteIds) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("deviceIdin", Arrays.asList(deleteIds.split(",")));
//		bizAboutUSMapper.deleteMulti(map);
//		/*writeLog(Constants.FUNC_MENU_NM_ABOUTUS, 
//				 Constants.FUNC_OPER_NM_DELETE, 
//				 "批量删除-关于我们deleteIds:" + deleteIds);*/
//		return "{\"Msg\":\"删除成功！\"}";
//	}

	@Override
	public SysAboutUS queryFirst() {
		return sysAboutUSMapper.queryFirst();
	}

	@Override
	public String createOrUpdate(SysAboutUS sysAboutUS) {
		SysAboutUS oldAboutUS = sysAboutUSMapper.queryFirst();
		if(oldAboutUS==null){
			sysAboutUSMapper.insert(sysAboutUS);
			return "{\"Msg\":\"创建成功！\",\"flag\":true}";
		}else{
			sysAboutUS.setIdKey(oldAboutUS.getIdKey());
			sysAboutUSMapper.update(sysAboutUS);
			return "{\"Msg\":\"更新成功！\",\"flag\":true}";
		}
	}
}
