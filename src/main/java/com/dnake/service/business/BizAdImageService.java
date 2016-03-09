package com.dnake.service.business;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.dnake.domain.business.BizAdImageVO;
import com.dnake.domain.business.BizAdImage;
import com.dnake.domain.business.BizCategory;
import com.dnake.domain.business.BizCategoryVO;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;

public interface BizAdImageService {
/**
 * 分页查询，以广告目录为分类，进行查询
 * @param pageIndex
 * @param rows
 * @param searchParam
 * @return
 *@author cqg 
 *2015年6月12日
 */
Page<BizCategoryVO> listPage(int pageIndex, int rows, SearchParam searchParam);

String update(BizAdImage bizAdImage);

String insert(String categoryId,String dictKey,String imageUrl,String communityId);

String deleteMulti(String deleteIds);
/**
 * 手机端获取广告图片
 * @param parms
 * @return
 *@author cqg 
 *2015年5月8日
 */
String mobileQuery(MobileParms parms);
/**
 * 根据类型查询广告
 * @param type
 * @return
 *@author cqg 
 *2015年5月26日
 */
List<BizAdImageVO> queryByType(String type,String community);
/**
 * 根据idKey删除
 * @param response
 * @param idKey
 * @return
 *@author cqg 
 *2015年5月26日
 */
String delete(HttpServletResponse response, Long idKey);
}
