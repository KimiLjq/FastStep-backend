package com.stu.fastStep.service;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.stu.fastStep.dao.CouponDao;
import com.stu.fastStep.dao.StepDao;
import com.stu.fastStep.domain.Coupon;
import com.stu.fastStep.domain.Step;

@Service(value="StepService")
public class StepService {
	@Resource(name="StepDao")
	private StepDao stepDao;
	
	@Resource(name="CouponDao")
	private CouponDao couponDao;
	
	public JSONArray getSteps(int userId) {
		List<Step> list=stepDao.getStepsById(userId);
		List<Coupon> couponList=new LinkedList<Coupon>();
		for(int i=0;i<list.size();i++) {
			Coupon coupon=couponDao.getCouponById(list.get(i).getCouponId());
			couponList.add(coupon);
		}
		JSONArray array= JSONArray.parseArray(JSON.toJSONString(couponList));
		return array;
	}
	public void newStep(int userId, int couponId) {
		Step step=new Step();
		step.setCouponId(couponId);
		step .setUserId(userId);
		
		Step testStep=stepDao.getStepByUserAndCoupon(step);
		if(testStep==null) {	
			step.setTime(new Timestamp(System.currentTimeMillis()));
			stepDao.insertStep(step);
		}else {
			testStep.setCouponId(couponId);
			testStep.setTime(new Timestamp(System.currentTimeMillis()));
			stepDao.updateStep(testStep);
		}
	}
}
