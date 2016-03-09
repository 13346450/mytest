package com.dnake.service.system;
import java.util.List;

import com.dnake.domain.system.SysLogoSettings;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;

public interface SysLogoSettingsService {
Page<SysLogoSettings> listPage(int pageIndex, int rows, SearchParam searchParam);

String update(SysLogoSettings sysLogoSettings);

String insert(SysLogoSettings sysLogoSettings);

String deleteMulti(String deleteIds);
/**
 * 查询全部
 * @return
 *@author cqg 
 *2015年5月20日
 */
List<SysLogoSettings> queryAll();
/**
 * 删除图片
 * @param idKey
 * @return
 *@author cqg 
 *2015年5月20日
 */
String delete(Long idKey,String subFilepath);
/**
 * 根据type查询
 * @param type
 * @return
 *@author cqg 
 *2015年5月22日
 */
List<SysLogoSettings> queryByType(String type);
/**
 * 手机端获取logo
 * @param parms
 * @return
 *@author cqg 
 *2015年5月21日
 */
String mobileQueryLogo(MobileParms parms);

}
