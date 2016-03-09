package com.dnake.service.system;

import java.util.List;

import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;
import com.dnake.domain.system.SysAboutUSCulture;
import com.dnake.domain.system.SysAboutUSCultureVO;

public interface SysAboutUSCultureService {

//	public Page<SysAboutUSCultureVO> listPage(int pageIndex, int rows, SearchParam searchParam);
//	
//	public List<SysAboutUSCultureVO> list(SearchParam searchParam);
//	
//	public SysAboutUSCulture queryByIdKey(Long idKey);
//
//	public String update(SysAboutUSCulture bizAboutUSCulture);
//
//	public String insert(SysAboutUSCulture bizAboutUSCulture);
//	
//	public String delete(Long idKey);
//
//	public String deleteMulti(String deleteIds);

	public String createOrUpdate(SysAboutUSCulture bizAboutUSCulture);

	public SysAboutUSCulture queryFirst();
	
}
