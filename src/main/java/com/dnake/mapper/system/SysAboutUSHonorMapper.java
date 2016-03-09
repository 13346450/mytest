package com.dnake.mapper.system;

import java.util.List;
import java.util.Map;

import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.domain.system.SysAboutUSHonor;
import com.dnake.domain.system.SysAboutUSHonorVO;

public interface SysAboutUSHonorMapper {

	public List<SysAboutUSHonorVO> listPage(Page<SysAboutUSHonorVO> page);
	
	public List<SysAboutUSHonorVO> list(SearchParam searchParam);
	
	public SysAboutUSHonor queryByIdKey(Long idKey);
	
	public void update(SysAboutUSHonor bizAboutUSHonor);
	
	public void insert(SysAboutUSHonor bizAboutUSHonor);
	
	public void insertMulti(List<SysAboutUSHonor> list);

	public void delete(long idKey);

	public void deleteMulti(Map<String, Object> map);

}
