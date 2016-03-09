package com.dnake.service.business;

import javax.servlet.http.HttpServletResponse;

import com.dnake.domain.business.BizAdvert;
import com.dnake.domain.business.BizAdvertVO;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;

/**
 * 广告业务接口
 *  BizAdvertService <br/>
 *  2014年3月19日 下午4:37:57 <br/>
 * @author ts
 * @version
 */
public interface BizAdvertService {

	/**
	 * 保存修改广告
	 * @title      update
	 * @author  ts    
	 * @date      2014年2月19日 
	 * @param BizAdvert
	 * @return
	 */
	String update(BizAdvert bizAdvert);
	
	/**
	 * 删除一个广告
	* @Title: delete 
	* @author tw  2013-11-4
	* @Description: 
	* @param @param idKey    
	* @return String    
	* @throws
	 */
	String delete(HttpServletResponse response, Long idKey);
	
	/**
	 * 插入一个广告
	 * @title      insert
	 * @author  ts    
	 * @date      2014年2月19日 
	 * @param BizAdvert bstDevice
	 * @return
	 */
	String insert(BizAdvert bizAdvert);
	
	/**
	 * 分页查询
	 * @title      listPage
	 * @author  ts    
	 * @date      2014年2月20日 
	 * @param pageIndex
	 * @param rows
	 * @param searchParam
	 * @return
	 */
	Page<BizAdvertVO> listPage(int pageIndex, int rows, SearchParam searchParam);
	
	/**
	 * 广告状态修改
	 * @title      updateStatus
	 * @author  ts    
	 * @date      2014年3月31日 
	 * @param response
	 * @param idKey
	 * @return
	 */
	String updateStatus(Long idKey, String type);
	
}
