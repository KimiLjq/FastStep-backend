package com.stu.fastStep.controller;

/*
 * date:2019-11-14
 * author:�
 * version��1.0
 * detail:�����û�ע���¼��controller���û���¼��Ҫ��¼��¼״̬��ʱ��Ϊһ�꣬
 * �û�ע�����ǰ�˷����û�id
 */
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.stu.fastStep.Exception.LogicException;
import com.stu.fastStep.service.Login_RegisterService;

@Controller
public class Login_RegisterController {
	@Resource(name="Login_Register")
	private Login_RegisterService loginRegister;
	
	@RequestMapping("/isLogin")
	public @ResponseBody String isLogin(HttpServletRequest request,HttpServletResponse response)throws Exception {
		HttpSession session = request.getSession();
		System.out.println("id"+session.getId());
		if(session.getAttribute("id")==null)	throw new LogicException(500,"��ǰ�û�δ��¼");
		else return new JSONObject().toJSONString();
	}
	
	@RequestMapping("/login")
	public @ResponseBody String Login(HttpServletRequest request,HttpServletResponse response)throws Exception {
		String number=request.getParameter("number");
		String password=request.getParameter("password");
		
		JSONObject json=loginRegister.login(number,password);
		
		//��¼�û��ĵ�¼״̬
		HttpSession session=request.getSession();
		
		int id=Integer.parseInt(String.valueOf(json.get("id")));
		HttpSession loginSession=request.getSession();
        loginSession.setMaxInactiveInterval(60*60);
		loginSession.setAttribute("id", id);
		System.out.println("id2"+loginSession.getId());
		return json.toJSONString();
	}
	
	@RequestMapping("/register")
	public @ResponseBody String Register(HttpServletRequest request,HttpServletResponse response)throws Exception {
		Object o=request.getSession().getAttribute("loginCode");
		String sessionMail=String.valueOf(o);
		String input=request.getParameter("code");
		if(input==null || input.equals("")) throw new LogicException(502,"�ղ���");
		if(!input.equals(sessionMail))  throw new LogicException(503,"��֤�����");
		
		String number=request.getParameter("number");
		String password=request.getParameter("password");
		JSONObject json=loginRegister.register(number,password);
		request.getSession().setAttribute("loginCode", null);
		return json.toJSONString();
	}
	
	@RequestMapping("/sendMail")
	public @ResponseBody String sendMail(HttpServletRequest request,HttpServletResponse response)throws Exception {
		String mail=request.getParameter("mail");
		loginRegister.sendMail(mail, request);
		return new JSONObject().toString();
	}
}
