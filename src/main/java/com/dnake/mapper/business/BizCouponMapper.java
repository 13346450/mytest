package com.dnake.mapper.business;
import java.util.List;
import java.util.Map;
import com.dnake.domain.common.Page;
import com.dnake.domain.business.BizCoupon;
import com.dnake.domain.business.BizCouponVO;
public interface BizCouponMapper {
void insert(BizCoupon bizCoupon);

void delete(long idKey);

void update(BizCoupon bizCoupon);

BizCoupon queryByIdkey(long idKey);

void deleteMulti(Map<String, Object> map);

List<BizCouponVO> queryPage(Page<BizCouponVO> page);

void insertMulti(List<BizCoupon> list);
/**
 * 将团购券修改为已使用，用到参数有店铺id和优惠券验证码
 * @param bizCouponVO
 *@author cqg 
 *2015年4月13日
 */
Long updateToUsed(BizCouponVO bizCouponVO);
}
