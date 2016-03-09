package com.dnake.service.business;

import java.util.List;

import javax.servlet.http.HttpServletResponse;







import com.dnake.domain.business.BizNote;
import com.dnake.domain.business.BizNoteVO;
import com.dnake.domain.common.MobileParms;
import com.dnake.domain.common.Page;
import com.dnake.domain.common.SearchParam;

/**
 * 帖子服务层接口
 * @ClassName BizNoteService
 * @author zgj
 * 2014年9月16日 下午3:45:54
 */
public interface BizNoteService
{
	/**
	 * 手机端插入一条新帖子
	 * @param BizNote
	 */
	public void insert(BizNote bizNote);
	/**
	 * 更新一条帖子
	 * @param bizNote
	 */
	public String update(BizNote bizNote);
	/**
	 * 根据ID查询，点击状态专用
	 * @param idKey
	 * @return
	 */
	public BizNoteVO queryById(Long idKey);
	/**
	 * 根据ID删除一条帖子
	 * @param idKey
	 */
	public String delete(Long idKey);
	/**
	 * 分页查询
	 * @param page
	 * @return
	 */
	public Page<BizNoteVO> listPage(int pageIndex, int rows, SearchParam searchParam);
	/**
	 * 手机端查询帖子信息
	 * @param idKey
	 * @return
	 */
	public String queryForMobile(MobileParms parms);
	/**
	 * 查询单条帖子信息详细
	 * @param parentId
	 * @return
	 */
	public BizNoteVO queryForDetail(Long idKey);
	
	/**
	 * 删除多条记录
	 * @param deleteIds
	 * @return
	 */
	public String deleteMulti(String deleteIds);
	/**
	 * 客户端查询我的帖子
	 * @param idKey
	 * @return
	 */
	public List<BizNoteVO> queryForUserNote(Long idKey);
	/**
	 * 更新帖子的状态
	 */
	public void updateStatus(int status, String type, Long idKey);
	
	/**
	 * 手机端查询帖子列表
	 * @param parms
	 * @return
	 */
	public String mobileQueryNoteList(MobileParms parms);
	/**
	 * 手机端删除帖子
	 * @param parms
	 * @return
	 */
	public String mobileDeleteNote(MobileParms parms);
	/**
	 * 手机端插入帖子
	 * @param parms
	 * @return
	 */
	public String mobileInsertNote(MobileParms parms);
	/**
	 * 手机端回复帖子
	 * @param parms
	 * @return
	 */
	public String mobileInsertReplyNote(MobileParms parms);
	/**
	 * 手机端查询帖子详情
	 * @param parms
	 * @return
	 */
	public String mobileQueryNoteDetail(MobileParms parms);
}
