package com.stu.fastStep.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.stu.fastStep.service.SeckillService;

@Controller
public class SeckillController {
	@Resource(name="SeckillService")
	private SeckillService  seckillService;
	
	@RequestMapping("/getSeckills")
	public @ResponseBody String getSeckills(HttpServletRequest request,HttpServletResponse response)throws Exception {
		JSONArray json=seckillService.getSeckills();
		return json.toJSONString();
	}
}
