package com.dnake.service.business;

import com.dnake.domain.business.BizPropertiesRepairVO;
import com.dnake.domain.business.BizPropertiesRepair;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;

/**
 * 故障报修业务处理层
 *  @author zgj
 *	日期：2015年7月9日下午5:39:28
 *  描述：
 */
public interface BizPropertiesRepairService
{
	Page<BizPropertiesRepairVO> listPage(int pageIndex, int rows,
			SearchParam searchParam);

	String update(BizPropertiesRepair bizPropertyRepair);

	String insert(BizPropertiesRepair bizPropertyRepair);

	String deleteMulti(String deleteIds);
	
	/**
	 * 手机端查询故障申报列表
	 * @param parms
	 * @return
	 */
	String mobileQueryList(MobileParms parms);
	/**
	 * 手机端查询故障详情
	 * @param parms
	 * @return
	 */
	String mobileQueryDetails(MobileParms parms);
	/**
	 * 手机端多条删除
	 * @param parms
	 * @return
	 */
	String mobileDeleteMulti(MobileParms parms);
	/**
	 * 手机端插入数据
	 * @param parms
	 * @return
	 */
	String mobileInsert(MobileParms parms);
	/**
	 * 手机端上传保修信息图片
	 * @param parms
	 * @return
	 */
	String mobileUploadImage(MobileParms parms);
	
	/**
	 * 查询三方接口数据
	 * @param parms
	 * @return
	 */
	String queryTPPData(MobileParms parms);
}
