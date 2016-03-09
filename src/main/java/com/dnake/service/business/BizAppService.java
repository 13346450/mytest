package com.dnake.service.business;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.dnake.domain.business.BizApp;
import com.dnake.domain.business.BizAppVO;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;

/**
 *  手机客户端版本管理
 *  BizAppService <br/>
 *  2014年4月9日 上午9:50:29 <br/>
 * @author ts
 * @version
 */
public interface BizAppService {

	/**
	 * 保存修改
	 * @title      update
	 * @author  ts    
	 * @date      2014年2月19日 
	 * @param BizApp
	 * @return
	 */
	String update(BizApp bizApp);
	
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
	String insert(BizApp bizApp);
	
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
	Page<BizAppVO> listPage(int pageIndex, int rows, SearchParam searchParam);
	
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
	/**
	 * 以json格式返回版本信息，包括版本号、下载地址和更新地址
	* @title      appVersion 
	* @author  chen qige     
	* @date      2015年1月26日 
	*  @return
	 */
	String getAppVersion(MobileParms parms);
	/**
	 * 上传文件
	 * @param mfile
	 * @param path
	 * @return
	 *@author cqg 
	 *2015年6月16日
	 */
	String uploadFile(MultipartFile mfile,String path);
	/**
	 * 下载最新app
	 * @param type
	 *@author cqg 
	 *2015年6月16日
	 */
	String downLoadLatestApp(String type);
}
