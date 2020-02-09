package com.stu.fastStep.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stu.fastStep.service.Login_RegisterService;
import com.stu.fastStep.service.StepService;
@Controller
public class StepController {
	@Resource(name="StepService")
	private StepService stepRegister;
	
	@RequestMapping("/getSteps")
	public @ResponseBody String getSteps(HttpServletRequest request,HttpServletResponse response)throws Exception {
		Object userId_o=request.getSession().getAttribute("id");
		int userId=Integer.parseInt(String.valueOf(userId_o));
		JSONArray json=stepRegister.getSteps(userId);
		return json.toJSONString();
	}
	
	@RequestMapping("/newStep")
	public @ResponseBody String newStep(HttpServletRequest request,HttpServletResponse response)throws Exception {
		String couponId_s=request.getParameter("couponId");
		int couponId=Integer.parseInt(couponId_s);
		
		Object userId_o=request.getSession().getAttribute("id");
		int userId=Integer.parseInt(String.valueOf(userId_o));
		stepRegister.newStep(userId,couponId);
		JSONObject json=new JSONObject();
		return json.toJSONString();
	}
}
