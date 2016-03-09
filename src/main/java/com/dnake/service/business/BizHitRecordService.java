package com.dnake.service.business;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.dnake.domain.business.BizChain;
import com.dnake.domain.business.BizChainVO;
import com.dnake.domain.business.BizHitRecord;
import com.dnake.domain.business.BizHitRecordVO;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;

/**
 * 链接业务接口
 * @ClassName BizHitRecordService
 * @author zgj
 * 2014年9月3日 下午2:16:41
 */
public interface BizHitRecordService
{
	/**
	 * 分页查询
	 */
	Page<BizHitRecordVO> listPage(int pageIndex, int rows, SearchParam searchParam);
	/**
	 * 插入一条链接
	 */
	public String insert(BizHitRecord bizHitRecord);
	/**
	 * 更新一条链接
	 * @param BizHitRecord 
	 */
	public String update(BizHitRecord bizHitRecord);
	/**
	 * 根据ID删除一条记录
	 * @param idKey
	 */
	public String delete(HttpServletResponse response,Long idKey);
	/**
	 * 根据ID查询
	 * @param idKey
	 * @return
	 */
	public String queryById(Long idKey);	
	/**
	 * 手机端插入一条点击记录数据
	 * @param bizHitRecord
	 */
	public void insertMobileClicksInfo(Long chainId);
	
}
