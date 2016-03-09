package com.dnake.mapper.system;

import java.util.List;
import java.util.Map;

import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.domain.system.SysAboutUSCulture;
import com.dnake.domain.system.SysAboutUSCultureVO;

public interface SysAboutUSCultureMapper {

	public List<SysAboutUSCultureVO> listPage(Page<SysAboutUSCultureVO> page);
	
	public List<SysAboutUSCultureVO> list(SearchParam searchParam);
	
	public SysAboutUSCulture queryByIdKey(Long idKey);
	
	public void update(SysAboutUSCulture bizAboutUSCulture);
	
	public void insert(SysAboutUSCulture bizAboutUSCulture);
	
	public void insertMulti(List<SysAboutUSCulture> list);

	public void delete(Long idKey);

	public void deleteMulti(Map<String, Object> map);

	public SysAboutUSCulture queryFirst();

}
