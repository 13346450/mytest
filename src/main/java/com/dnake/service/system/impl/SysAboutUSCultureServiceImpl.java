package com.dnake.service.system.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dnake.domain.system.SysAboutUSCulture;
import com.dnake.mapper.system.SysAboutUSCultureMapper;
import com.dnake.service.common.BaseServiceImpl;
import com.dnake.service.common.CommonService;
import com.dnake.service.system.SysAboutUSCultureService;

@Service
public class SysAboutUSCultureServiceImpl extends BaseServiceImpl implements SysAboutUSCultureService {
	
	@Resource
	private SysAboutUSCultureMapper sysAboutUSCultureMapper;
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
//	public Page<SysAboutUSCultureVO> listPage(int pageIndex, int rows, SearchParam searchParam) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("sort", searchParam.getSort());
//		map.put("order", searchParam.getOrder());
//		Page<SysAboutUSCultureVO> page = new Page<SysAboutUSCultureVO>();
//		page.setPageNo(pageIndex);
//		page.setPageSize(rows);
//		page.setParams(map);
//		page.setRows(bizAboutUSCultureMapper.listPage(page));
//		return page;
//	}
//
//	/**
//	 * 查询列表
//	 * @param searchParam
//	 * @return
//	 */
//	@Override
//	public List<SysAboutUSCultureVO> list(SearchParam searchParam) {
//		List<SysAboutUSCultureVO> bizAboutUSCultureVOList = bizAboutUSCultureMapper.list(searchParam);
//		return bizAboutUSCultureVOList;
//	}
//
//	/**
//	 * 根据idKey查询
//	 * @param idKey
//	 * @return
//	 */
//	@Override
//	public SysAboutUSCulture queryByIdKey(Long idKey) {
//		SysAboutUSCulture bizAboutUSCulture = bizAboutUSCultureMapper.queryByIdKey(idKey);
//		return bizAboutUSCulture;
//	}
//	
//	/**
//	 * 更新
//	 * @param bizAboutUSCulture
//	 * @return
//	 */
//	@Override
//	public String update(SysAboutUSCulture bizAboutUSCulture) {
//		bizAboutUSCultureMapper.update(bizAboutUSCulture);
//		/*writeLog(Constants.FUNC_MENU_NM_ABOUTUS, 
//				 Constants.FUNC_OPER_NM_UPDATE, 
//				 "企业文化idKey:" + bizAboutUSCulture.getIdKey() + ",cultureTitle:" + bizAboutUSCulture.getCultureTitle());*/
//		return "{\"Msg\":\"更新成功！\",\"flag\":true}";
//	}
//
//	/**
//	 * 插入
//	 * @param bizAboutUSCulture
//	 * @return
//	 */
//	@Override
//	public String insert(SysAboutUSCulture bizAboutUSCulture) {
//		bizAboutUSCultureMapper.insert(bizAboutUSCulture);
//		/*writeLog(Constants.FUNC_MENU_NM_ABOUTUS, 
//				 Constants.FUNC_OPER_NM_CREATE, 
//				 "企业文化idKey:" + bizAboutUSCulture.getIdKey() + ",cultureTitle:" + bizAboutUSCulture.getCultureTitle());*/
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
//		SysAboutUSCulture bizAboutUSCulture = bizAboutUSCultureMapper.queryByIdKey(idKey);
//		bizAboutUSCultureMapper.delete(idKey);
//		/*writeLog(Constants.FUNC_MENU_NM_ABOUTUS, 
//				 Constants.FUNC_OPER_NM_DELETE, 
//				 "企业文化idKey:" + bizAboutUSCulture.getIdKey() + ",cultureTitle:" + bizAboutUSCulture.getCultureTitle());*/
//		return "{\"Msg\":\"删除成功！\",\"idKey\":\"" + bizAboutUSCulture.getIdKey() + "\",\"flag\":true}";
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
//		bizAboutUSCultureMapper.deleteMulti(map);
//		/*writeLog(Constants.FUNC_MENU_NM_ABOUTUS, Constants.FUNC_OPER_NM_DELETE, "批量删除-企业文化deleteIds:" + deleteIds);*/
//		return "{\"Msg\":\"删除成功！\"}";
//	}

	@Override
	public String createOrUpdate(SysAboutUSCulture sysAboutUSCulture) {
		SysAboutUSCulture oldAboutUSCulture = queryFirst();
		if(oldAboutUSCulture==null){
			sysAboutUSCultureMapper.insert(sysAboutUSCulture);
			return "{\"Msg\":\"创建成功！\",\"flag\":true}";
		}else{
			sysAboutUSCulture.setIdKey(oldAboutUSCulture.getIdKey());
			sysAboutUSCultureMapper.update(sysAboutUSCulture);
			return "{\"Msg\":\"更新成功！\",\"flag\":true}";
		}
	}

	@Override
	public SysAboutUSCulture queryFirst() {
		return sysAboutUSCultureMapper.queryFirst();
	}
}
