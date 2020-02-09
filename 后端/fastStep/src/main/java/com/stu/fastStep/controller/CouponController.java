package com.stu.fastStep.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stu.fastStep.service.CouponService;

@Controller
public class CouponController {
	@Resource(name="CouponService")
	private CouponService couponService;
	
	@RequestMapping("/getCouponArr")
	public @ResponseBody String getCouponArr(HttpServletRequest request,HttpServletResponse response)throws Exception {
		JSONArray json=couponService.getCouponArr();
		return json.toJSONString();
	}
	
	@RequestMapping("/searchCoupon_ready")
	public @ResponseBody String searchCoupon_ready(HttpServletRequest request,HttpServletResponse response)throws Exception {
		String input=request.getParameter("inputReady");
		JSONArray json=couponService.searchCoupon_ready(input);
		return json.toJSONString();
	}
	
	@RequestMapping("/searchCoupon")
	public @ResponseBody String searchCoupon(HttpServletRequest request,HttpServletResponse response)throws Exception {
		String input=request.getParameter("input");
		JSONArray json=couponService.searchCoupon(input);
		System.out.println(json);
		return json.toJSONString();
	}
}
