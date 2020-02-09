package com.stu.fastStep.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.stu.fastStep.Exception.LogicException;
import com.stu.fastStep.dao.UserDao;
import com.stu.fastStep.domain.User;


@Service("UserService")
public class UserService {
	@Resource(name="UserDao")
	private UserDao userDao;
	
	public JSONObject getInfo(int id)  throws Exception{
		User user=userDao.getUserById(id);
		if(user==null)	throw new LogicException(501,"∏√’À∫≈≤ª¥Ê‘⁄");
		JSONObject json=(JSONObject)JSONObject.toJSON(user);
		json.remove("password");
		return json;
	}
}
