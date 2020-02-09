package com.stu.fastStep.service;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.stu.fastStep.dao.CollectDao;
import com.stu.fastStep.dao.CouponDao;
import com.stu.fastStep.domain.Collect;
import com.stu.fastStep.domain.Coupon;

@Service(value="CollectService")
public class CollectService {
		
	@Resource(name="CollectDao")
	private CollectDao collectDao;
	
	@Resource(name="CouponDao")
	private CouponDao couponDao;
	
	public JSONArray getCollects(int userId) {
		List<Collect> list=collectDao.getCollectsById(userId);
		List<Coupon> couponList=new LinkedList<Coupon>();
		for(int i=0;i<list.size();i++) {
			Coupon coupon=couponDao.getCouponById(list.get(i).getCouponId());
			couponList.add(coupon);
		}
		JSONArray array= JSONArray.parseArray(JSON.toJSONString(couponList));
		return array;
	}
	public void newCollect(int userId, int couponId) {
		Collect collect=new Collect();
		collect.setCouponId(couponId);
		collect .setUserId(userId);
		
		Collect testCollect=collectDao.getCollectByUserAndCoupon(collect);
		if(testCollect==null) {	
			collect.setTime(new Timestamp(System.currentTimeMillis()));
			collectDao.insertCollect(collect);
		}else {
			testCollect.setCouponId(couponId);
			testCollect.setTime(new Timestamp(System.currentTimeMillis()));
			collectDao.updateCollect(testCollect);
		}
	}
	
}
