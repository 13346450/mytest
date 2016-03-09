package com.dnake.mapper.business;

import java.util.List;
import java.util.Map;
import com.dnake.domain.common.Page;
import com.dnake.domain.business.BizOrderNum;

/**
 *  @author zgj
 *	日期：2015年9月24日上午10:19:57
 *  描述：用于处理支付宝订单号与服务器订单号绑定的数据操作接口
 */
public interface BizOrderNumMapper
{
	void insert(BizOrderNum bizOrderNum);

	void delete(long idKey);

	void update(BizOrderNum bizOrderNum);

	BizOrderNum queryByIdkey(long idKey);

	void deleteMulti(Map<String, Object> map);

	void insertMulti(List<BizOrderNum> list);
	
	/**
	 * 根据系统订单号查询提交到支付宝的订单号
	 *  @author zgj
	 *	日期：2015年9月22日下午5:45:56
	 *  描述：@param orderNum
	 *  描述：@return
	 */
	BizOrderNum queryByOrderNum(String orderNum);
}
