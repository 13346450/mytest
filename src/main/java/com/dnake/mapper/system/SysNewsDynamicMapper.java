package com.dnake.mapper.system;

import java.util.List;
import java.util.Map;

import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.domain.system.SysNewsDynamic;
import com.dnake.domain.system.SysNewsDynamicVO;

public interface SysNewsDynamicMapper {

	public List<SysNewsDynamicVO> listPage(Page<SysNewsDynamicVO> page);
	
	public List<SysNewsDynamicVO> list(SearchParam searchParam);
	
	public SysNewsDynamic queryByIdKey(Long idKey);
	
	public void update(SysNewsDynamic bizNewsDynamic);
	
	public void insert(SysNewsDynamic bizNewsDynamic);
	
	public void insertMulti(List<SysNewsDynamic> list);

	public void delete(Long idKey);

	public void deleteMulti(Map<String, Object> map);

	public List<SysNewsDynamicVO> pageByType(Page<SysNewsDynamicVO> page);

	public int countByType(Page<SysNewsDynamicVO> page);

}
