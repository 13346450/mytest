package com.dnake.service.business;



import java.util.List;

import com.dnake.domain.business.BizUinfoCollect;
import com.dnake.domain.business.BizUinfoCollectVO;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;

/**
 * 住户信息采集服务层
 * @ClassName BizUserInfoService
 * @author zgj
 * 2014年9月11日 下午5:22:31
 */
public interface BizUinfoCollectService
{
	/**
	 * 住户插入一条信息
	 * @param bizUserInfoVO
	 */
	public String insert(BizUinfoCollect bizUserInfo);
	/**
	 * 管理员删除一条住户信息
	 * @param idKey
	 */
	public String delete(Long idKey);
	/**
	 * 分页查询
	 * @param page
	 * @return
	 */
	public Page<BizUinfoCollectVO> listPage(int pageIndex, int rows, SearchParam searchParam);
	/**
	 * 手机端查询列表接口
	 * @return
	 */
	public String queryListForMobile(Long idKey);
	/**
	 * 手机端查询列表接口
	 * @return
	 */
	public List<BizUinfoCollectVO> queryListForWeb(Long idKey);
	/**
	 * 删除多条
	 * 
	 * @title deleteMulti
	 * @date 2014年9月15日
	 * @param deleteIds
	 * @return
	 */
	String deleteMulti(String deleteIds);
	
	/**
	 * 三方接口插入数据
	 * @param parms
	 * @return
	 */
	String insertTPP(MobileParms parms);
	/**
	 * 三方接口追加故障申报信息
	 * @param parms
	 * @return
	 */
	String insertAppendTPP(MobileParms parms);
	/**
	 * 查询三方接口数据
	 * @param parms
	 * @return
	 */
	String queryTPP(MobileParms parms);
	
	/**
	 * 手机端插入投诉建议
	 * @param parms
	 * @return
	 */
	String mobileInsert(MobileParms parms);
	/**
	 * 手机端查看投诉建议列表
	 * @param parms
	 * @return
	 */
	String mobileQueryList(MobileParms parms);
	/**
	 * 查询投诉建议详情
	 * @param parms
	 * @return
	 */
	String mobileQueryDetail(MobileParms parms);
	/**
	 * 投诉建议手机上传图片
	 * @param parms
	 * @return
	 */
	String mobileUploadImage(MobileParms parms);
}
