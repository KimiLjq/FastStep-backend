package com.stu.fastStep.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.stu.fastStep.Exception.LogicException;
import com.stu.fastStep.service.UserService;
@Controller
public class UserController {
	@Resource(name="UserService")
	private UserService  userService;
	
	@RequestMapping("/getInfo")
	public @ResponseBody String GetInfo(HttpServletRequest request,HttpServletResponse response)throws Exception {
		HttpSession session=request.getSession();
		if(session.getAttribute("id")==null)	throw new LogicException(501,"Î´µÇÂ¼");
		int id=Integer.valueOf(String.valueOf(session.getAttribute("id")));
		
		JSONObject json=userService.getInfo(id);
		
		return json.toJSONString();
	}
}
