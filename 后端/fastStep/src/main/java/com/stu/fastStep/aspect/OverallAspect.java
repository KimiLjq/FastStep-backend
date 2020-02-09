package com.stu.fastStep.aspect;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.stu.fastStep.Exception.LogicException;

/*
 * date:2019-11-8
 * author:yf
 * info:全局的controller异常和逻辑处理方法，
 * 			1、获取controller返回的json，封装成restful模式。
 * 			2、扫描所有的controller包，若controller出现异常则返回errcode：500
 * 			3、处理用户的logicException逻辑异常
 */

@Aspect  //标识此类是一个通知
@Component  //将OverallAspect加入到IOC容器中
public class OverallAspect {

	@Before("execution(* com.stu.fastStep.controller..*(..))")
	//通过JoinPoint对象获取目标函数的属性
	public void urlDeal(JoinPoint jp) {
		ServletRequestAttributes requestAttributes =
				(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request =  requestAttributes.getRequest();
		System.out.println(getHttp(request));
		HttpSession session=request.getSession();
		if(session.getAttribute("id")==null)	System.out.println("未登录");
		try {
		}catch(Exception e) {
			e.printStackTrace();
		}
	}



	@Around("execution(* com.stu.fastStep.controller..*(..))")
	public Object exceptionDeal(ProceedingJoinPoint pjp) {
		Object result=null;
		JSONObject json=new JSONObject();
		try {
			Object[] args=pjp.getArgs();

			//控制目标方法的执行，不调用proceed目标方法不会被执行
			//result是目标方法的返回值、
			//在proceed(）之前的代码是前置通知
			result = pjp.proceed(args);
			//在proceed()之后的代码是后置通知
		//在catch里面的代码时异常通知
		}catch(LogicException logicE) {
			json.put("errcode", logicE.getErrCode());
			json.put("message", logicE.getInfo());
			json.put("data",new JSONObject());
			return json.toString();
		}catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.put("errcode", 500);
			json.put("message", "未知错误!");
			json.put("data",new JSONObject());
			return json.toString();
		}
		json.put("errcode", 200);
		json.put("message", "处理成功!");
		json.put("data", JSONObject.parse((String)result));
		return json.toJSONString();
	}

	private String getHttp(HttpServletRequest request) {
		String rUrl = request.getRequestURI();//得到请求URL
		String rMet = request.getMethod();//请求方式post或get

		Enumeration enu  = request.getParameterNames();//获取请求参数名信息
		StringBuffer sb = new StringBuffer();
		sb.append(rUrl);
		String key="";
		while(enu.hasMoreElements()) {
			key = (String)enu.nextElement();//得到参数名
			sb.append("&"+key+"="+request.getParameter(key));//获取参数值，同时进行拼装
		}
		sb.append("----Method-【"+rMet+"】");
		return sb.toString();
	}
}
