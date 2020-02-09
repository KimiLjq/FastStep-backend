package com.stu.fastStep.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.stu.fastStep.dao.SeckillDao;
import com.stu.fastStep.domain.Seckill;
@Service(value="SeckillService")
public class SeckillService {
	@Resource(name="SeckillDao")
	private SeckillDao seckillDao;
	
	public JSONArray getSeckills() {
		List<Seckill> list=seckillDao.getSeckills();
		JSONArray array= JSONArray.parseArray(JSON.toJSONString(list));
		return array;
	}
}
