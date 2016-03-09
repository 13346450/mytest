package com.dnake.service.business;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.dnake.domain.business.BizChain;
import com.dnake.domain.business.BizChainVO;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;

/**
 * 链接管理接口
 * @ClassName BizChainService
 * @author zgj
 * 2014年9月2日 上午9:52:15
 */
public interface BizChainService
{
	/**
	 * 插入一条链接
	 */
	public String insert(BizChain bizChain);
	/**
	 * 更新一条链接
	 * @param bizChain
	 */
	public String update(BizChain bizChain);
	/**
	 * 根据ID查找一个链接
	 * @param idKey
	 * @return
	 */
	public BizChain queryById(Long idKey);
	/**
	 * 根据ID删除一条记录
	 * @param idKey
	 */
	public String delete(HttpServletResponse response,Long idKey);
	/**
	 * 分页查询
	 * @param page
	 * @return
	 */
	public Page<BizChainVO> listPage(int pageIndex, int rows, SearchParam searchParam);
	/**
	 * 获取手机端便民服务链接
	 * @return
	 */
	public String queryMobileChainInfoList(MobileParms parms);
}
