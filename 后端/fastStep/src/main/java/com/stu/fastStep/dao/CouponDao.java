package com.stu.fastStep.dao;

import java.util.List;

import com.stu.fastStep.domain.Coupon;

public interface CouponDao {
	public Coupon getCouponById(int id);
	public Coupon getCouponByGoodId(int id);
	public List<Coupon> getCouponLikeName(Coupon coupon);
	public Coupon insertCoupon(Coupon coupon);
	public boolean updateCoupon(Coupon coupon);
	public boolean deleteCouponById(int id);
}
