package com.dnake.service.business;

import java.sql.Timestamp;

import javax.servlet.http.HttpSession;

import com.dnake.domain.business.BizGoods;
import com.dnake.domain.business.BizGoodsVO;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;

/**
 * 商品维护的接口 BizGoodsService <br/>
 * 2014年9月11日 上午11:45:52 <br/>
 * 
 * @author chen qige
 * @version
 */
public interface BizGoodsService {

	/**
	 * 编辑单个设备实体
	 * 
	 * @title edit
	 * @author ts
	 * @date 2014年2月19日
	 * @param idKey
	 * @return
	 */
	BizGoods edit(Long idKey);

	/**
	 * 保存修改设备
	 * 
	 * @title update
	 * @author ts
	 * @date 2014年2月19日
	 * @param bizGoods
	 * @return
	 */
	String update(BizGoods bizGoods);

	/**
	 * 删除一个设备
	 * 
	 * @Title: delete
	 * @author tw 2013-11-4
	 * @Description:
	 * @param @param idKey
	 * @return String
	 * @throws
	 */
	String delete(Long idKey);
	/**
	 * 删除无用的图片
	* @title      deleteImages 
	* @author  chen qige     
	* @date      2014年10月16日 
	*  @param idKey
	 */
	void deleteImages(BizGoods bizGoods);

	/**
	 * 插入一个设备
	 * 
	 * @title insert
	 * @author ts
	 * @date 2014年2月19日
	 * @param BizAdvert
	 *            bizGoods
	 * @return
	 */
	String insert(BizGoods bizGoods);

	/**
	 * 分页查询
	 * 
	 * @title listPage
	 * @author ts
	 * @date 2014年2月20日
	 * @param pageIndex
	 * @param rows
	 * @param searchParam
	 * @return
	 */
	Page<BizGoodsVO> listPage(int pageIndex, int rows, SearchParam searchParam);

	/**
	 * 手机端查询商品列表接口
	 * 
	 * @title mobileQueryGoodsList
	 * @author chen qige
	 * @date 2014年9月12日
	 * @param id
	 * @return
	 */
	String mobileQueryGoodsList(MobileParms parms);

	/**
	 * 手机端查询商品详细接口
	 * 
	 * @title mobileQueryGoodsDetails
	 * @author chen qige
	 * @date 2014年9月12日
	 * @param Goodsid
	 * @return
	 */
	String mobileQueryGoodsDetails(Long Goodsid);

	/**
	 * 删除多条商品
	 * 
	 * @title deleteMulti
	 * @author chen qige
	 * @date 2014年9月15日
	 * @param deleteIds
	 * @return
	 */
	String deleteMulti(String deleteIds);
	/**
	 * 手机端增加商品
	* @title      mobileInsertGoods 
	* @author  chen qige     
	* @date      2014年9月18日 
	* @param mp
	* @return
	 */
	String mobileInsertGoods(MobileParms parms);
	/**
	 * 手机修改商品信息
	* @title      mobileUpdateGoods 
	* @author  chen qige     
	* @date      2014年9月18日 
	* @param parms
	* @return
	 */
	String mobileUpdateGoods(MobileParms parms);
	/**
	 * 手机端删除商品信息
	* @title      mobileDeleteGoods 
	* @author  chen qige     
	* @date      2014年9月18日 
	* @param parms
	* @return
	 */
	String mobileDeleteGoods(MobileParms parms);
	/**
	 * 手机端上传图片
	 * @param session
	 * @param parms
	 * @return
	 */
	String mobileUploadImage(HttpSession session, MobileParms parms);
/**
 * 下架或上架多个商品
 * @param deleteIds
 * @return
 *@author cqg 
 *2015年6月12日
 */
	String pullOffMulti(String deleteIds,Integer goodsStatus,Timestamp timestamp);
	/**
	 * 手机端定时上架下架多个商品
	 * @param deleteIds
	 * @param goodsStatus
	 * @param autoPutOnTime
	 * @return
	 *@author cqg 
	 *2015年9月22日
	 */
	String mobilePullOffMulti(MobileParms parms);
}
