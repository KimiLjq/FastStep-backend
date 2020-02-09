package com.stu.fastStep.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import com.stu.fastStep.dao.CouponDao;
import com.stu.fastStep.domain.Coupon;

@Repository("CouponDao")
public class CouponDaoImpl implements CouponDao{
	@Resource(name="sqlSessionFactory")
	private SqlSessionFactory factory;
	
	public Coupon getCouponById(int id) {
		SqlSession sqlSession=factory.openSession();
		Coupon coupon = sqlSession.selectOne("YFMapper.selectCouponById", id);
		sqlSession.commit();
		sqlSession.close();
		return coupon;
	}
	public Coupon getCouponByGoodId(int id) {
		SqlSession sqlSession=factory.openSession();
		Coupon coupon = sqlSession.selectOne("YFMapper.selectCouponByGoodId", id);
		sqlSession.commit();
		sqlSession.close();
		return coupon;
	}
	
	public List<Coupon> getCouponLikeName(Coupon coupon) {
		SqlSession sqlSession=factory.openSession();
		List<Coupon> result = sqlSession.selectList("YFMapper.selectCouponLikeName", coupon);
		sqlSession.commit();
		sqlSession.close();
		return result;
		
	}

	public Coupon insertCoupon(Coupon coupon) {
		SqlSession sqlSession=factory.openSession();
		sqlSession.insert("YFMapper.insertCoupon",coupon);
		sqlSession.commit();
		sqlSession.close();
		return coupon;
	}

	public boolean updateCoupon(Coupon coupon) {
		SqlSession sqlSession=factory.openSession();
		boolean result=(1==sqlSession.insert("YFMapper.updateCoupon",coupon));
		sqlSession.commit();
		sqlSession.close();
		return result;
	}

	public boolean deleteCouponById(int id) {
		SqlSession sqlSession=factory.openSession();
		boolean result=sqlSession.selectOne("YFMapper.selectCouponById", id);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}
	

}
