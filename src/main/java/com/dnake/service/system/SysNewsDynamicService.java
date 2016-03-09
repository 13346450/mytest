package com.dnake.service.system;

import java.util.List;

import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.domain.system.SysNewsDynamic;
import com.dnake.domain.system.SysNewsDynamicVO;

public interface SysNewsDynamicService {

	public Page<SysNewsDynamicVO> listPage(int pageIndex, int rows, SearchParam searchParam);
	
	public List<SysNewsDynamicVO> list(SearchParam searchParam);
	
	public SysNewsDynamic queryByIdKey(Long idKey);

	public String update(SysNewsDynamic bizNewsDynamic);

	public String insert(SysNewsDynamic bizNewsDynamic);
	
	public String delete(Long idKey);

	public String deleteMulti(String deleteIds);

	public Page<SysNewsDynamicVO> pageByType(int pageIndex, int i, SysNewsDynamic bizNewsDynamic);
	
}
