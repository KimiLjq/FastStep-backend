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
 * info:ȫ�ֵ�controller�쳣���߼���������
 * 			1����ȡcontroller���ص�json����װ��restfulģʽ��
 * 			2��ɨ�����е�controller������controller�����쳣�򷵻�errcode��500
 * 			3�������û���logicException�߼��쳣
 */

@Aspect  //��ʶ������һ��֪ͨ
@Component  //��OverallAspect���뵽IOC������
public class OverallAspect {

	@Before("execution(* com.stu.fastStep.controller..*(..))")
	//ͨ��JoinPoint�����ȡĿ�꺯��������
	public void urlDeal(JoinPoint jp) {
		ServletRequestAttributes requestAttributes =
				(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request =  requestAttributes.getRequest();
		System.out.println(getHttp(request));
		HttpSession session=request.getSession();
		if(session.getAttribute("id")==null)	System.out.println("δ��¼");
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

			//����Ŀ�귽����ִ�У�������proceedĿ�귽�����ᱻִ��
			//result��Ŀ�귽���ķ���ֵ��
			//��proceed(��֮ǰ�Ĵ�����ǰ��֪ͨ
			result = pjp.proceed(args);
			//��proceed()֮��Ĵ����Ǻ���֪ͨ
		//��catch����Ĵ���ʱ�쳣֪ͨ
		}catch(LogicException logicE) {
			json.put("errcode", logicE.getErrCode());
			json.put("message", logicE.getInfo());
			json.put("data",new JSONObject());
			return json.toString();
		}catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.put("errcode", 500);
			json.put("message", "δ֪����!");
			json.put("data",new JSONObject());
			return json.toString();
		}
		json.put("errcode", 200);
		json.put("message", "����ɹ�!");
		json.put("data", JSONObject.parse((String)result));
		return json.toJSONString();
	}

	private String getHttp(HttpServletRequest request) {
		String rUrl = request.getRequestURI();//�õ�����URL
		String rMet = request.getMethod();//����ʽpost��get

		Enumeration enu  = request.getParameterNames();//��ȡ�����������Ϣ
		StringBuffer sb = new StringBuffer();
		sb.append(rUrl);
		String key="";
		while(enu.hasMoreElements()) {
			key = (String)enu.nextElement();//�õ�������
			sb.append("&"+key+"="+request.getParameter(key));//��ȡ����ֵ��ͬʱ����ƴװ
		}
		sb.append("----Method-��"+rMet+"��");
		return sb.toString();
	}
}
