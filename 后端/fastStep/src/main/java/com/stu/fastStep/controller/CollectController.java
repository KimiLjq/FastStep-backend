package com.stu.fastStep.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stu.fastStep.service.CollectService;
import com.stu.fastStep.service.StepService;

@Controller
public class CollectController {
	@Resource(name="CollectService")
	private CollectService collectRegister;
	
	@RequestMapping("/getCollects")
	public @ResponseBody String getCollects(HttpServletRequest request,HttpServletResponse response)throws Exception {
		Object userId_o=request.getSession().getAttribute("id");
		int userId=Integer.parseInt(String.valueOf(userId_o));
		JSONArray json=collectRegister.getCollects(userId);
		return json.toJSONString();
	}
	
	@RequestMapping("/newCollect")
	public @ResponseBody String newCollect(HttpServletRequest request,HttpServletResponse response)throws Exception {
		String couponId_s=request.getParameter("couponId");
		int couponId=Integer.parseInt(couponId_s);
		
		Object userId_o=request.getSession().getAttribute("id");
		int userId=Integer.parseInt(String.valueOf(userId_o));
		collectRegister.newCollect(userId,couponId);
		JSONObject json=new JSONObject();
		return json.toJSONString();
	}
}
