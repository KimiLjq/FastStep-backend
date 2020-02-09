package com.stu.fastStep.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stu.fastStep.Exception.LogicException;
import com.stu.fastStep.dao.CouponDao;
import com.stu.fastStep.domain.Coupon;

@Service("CouponService")
public class CouponService {
	@Resource(name="CouponDao")
	private CouponDao couponDao;
	
	 
	public JSONArray getCouponArr() {
		JSONArray jsonArr=new JSONArray();
		int couponLength=11;
		for(int i=1;i<couponLength;i++) {
			Coupon coupon=couponDao.getCouponById(i);
			jsonArr.add((JSONObject)JSON.toJSON(coupon));
		}
		return jsonArr;
	}

	public JSONArray searchCoupon_ready(String input) throws LogicException {
		if(input==null || input.equals(""))		throw new LogicException(500,"空参数");
		
		Coupon coupon=new Coupon();
		coupon.setName(input);
		List<Coupon> list=couponDao.getCouponLikeName(coupon);
		JSONArray arr=new JSONArray();
		for(int i=0;i<list.size();i++){
			JSONObject json=new JSONObject();
			json.put("name",list.get(i).getName());
			arr.add(json);
			if(i>=5)  break;
		}
		return arr;
	}

	public JSONArray searchCoupon(String input) throws LogicException {
		if(input==null || input.equals(""))		throw new LogicException(500,"空参数");
		
		Coupon coupon=new Coupon();
		coupon.setName(input);
		List<Coupon> list=couponDao.getCouponLikeName(coupon);
		JSONArray array= JSONArray.parseArray(JSON.toJSONString(list));
		if(list.size()>20) {
			for(int i=20;i<list.size();i++)	list.remove(i);
		}
		return array;
	}
}
