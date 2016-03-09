package com.dnake.mapper.system;

import java.util.List;
import java.util.Map;

import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.domain.system.SysAboutUS;
import com.dnake.domain.system.SysAboutUSVO;

public interface SysAboutUSMapper {

	public List<SysAboutUSVO> queryPage(Page<SysAboutUSVO> page);
	
	public List<SysAboutUSVO> list(SearchParam searchParam);
	
	public SysAboutUS queryByIdKey(Long idKey);
	
	public void update(SysAboutUS bizAboutUS);
	
	public void insert(SysAboutUS bizAboutUS);
	
	public void insertMulti(List<SysAboutUS> list);

	public void delete(Long idKey);

	public void deleteMulti(Map<String, Object> map);

	public SysAboutUS queryFirst();

}
