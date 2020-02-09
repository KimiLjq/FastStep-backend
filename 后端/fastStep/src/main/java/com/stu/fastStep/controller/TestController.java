package com.stu.fastStep.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class TestController {
	
	@RequestMapping("/test")
	public @ResponseBody String test(HttpServletRequest request,HttpServletResponse response) {
		
		return "s";
	}
}
