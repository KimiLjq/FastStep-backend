package com.stu.fastStep.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import com.stu.fastStep.dao.SeckillDao;
import com.stu.fastStep.domain.Collect;
import com.stu.fastStep.domain.Seckill;

@Repository("SeckillDao")
public class SeckillDaoImpl implements SeckillDao{
	@Resource(name="sqlSessionFactory")
	private SqlSessionFactory factory;
	
	public List<Seckill> getSeckills() {
		SqlSession sqlSession=factory.openSession();
		List<Seckill> result=sqlSession.selectList("YFMapper.selectSeckills");
		sqlSession.commit();
		sqlSession.close();
		return result;
		
	}

}
