package com.dnake.service.business;

import java.util.List;

import com.dnake.domain.business.BizAddrVO;
import com.dnake.domain.business.BizAddr;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;

/**
 *  @author zgj
 *	日期：2015年3月24日下午6:55:08
 *  描述：地址管理业务逻辑层
 */
public interface BizAddrService
{
	/**
	 * 分页查询
	 * @param pageIndex
	 * @param rows
	 * @param searchParam
	 * @return
	 */
	public Page<BizAddrVO> listPage(int pageIndex, int rows, SearchParam searchParam);

	/**
	 * 插入一条地址
	 * @param bizAddr
	 * @return
	 */
	public String insert(BizAddr bizAddr);

	/**
	 * 删除
	 * @param idKey
	 * @return
	 */
	public String delete(Long idKey);
	
	/**
	 * 手机端插入
	 * @return
	 */
	public String mobileInsert(MobileParms parms);
	
	/**
	 * 手机端查询
	 * @param parms
	 * @return
	 */
	public String mobileQueryAddr();
	/**
	 * 手机端更新一条地址
	 * @param bizAddr
	 * @return
	 */
	public String mobileUpdate(MobileParms parms);
	/**
	 * 手机端查询一条记录
	 * @param parms
	 * @return
	 */
	public String mobileUpdateDefalutAddr(MobileParms parms);
	
}
