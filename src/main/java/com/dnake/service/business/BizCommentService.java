package com.dnake.service.business;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.dnake.domain.business.BizCommentVO;
import com.dnake.domain.business.BizComment;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;

/**
 *  @author zgj
 *	日期：2015年3月23日下午5:22:53
 *  描述：评论管理业务层
 */
public interface BizCommentService
{
	/**
	 * 分页查询
	 * @param pageIndex
	 * @param rows
	 * @param searchParam
	 * @return
	 */
	public Page<BizCommentVO> listPage(int pageIndex, int rows, SearchParam searchParam);

	/**
	 * 更新一条评论
	 * @param bizComment
	 * @return
	 */
	public String update(BizComment bizComment);

	/**
	 * 插入一条评论
	 * @param bizComment
	 * @return
	 */
	public String insert(BizComment bizComment);
	

	/**
	 * 删除一条评论
	 * @param deleteIds
	 * @return
	 */
	public String deleteMulti(String deleteIds);

	/**
	 * 手机端查询商品下的评论
	 * @param parms
	 * @return
	 */
	public String mobileQueryCommentByGoodsId(MobileParms parms);
	
	/**
	 * 商铺查询收到的评论
	 * @param parms
	 * @return
	 */
	public String mobileQueryCommentByShopId(MobileParms parms);
	
	/**
	 * 客户端插入评论
	 * @param parms
	 * @return
	 */
	public String mobileInsertComment(MobileParms parms);
	
	/**
	 * 客户端上传照片
	 * @return
	 */
	public String mobileUploadImage(HttpSession session, MobileParms parms);
}
