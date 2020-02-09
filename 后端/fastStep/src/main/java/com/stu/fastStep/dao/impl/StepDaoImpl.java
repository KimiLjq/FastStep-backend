package com.stu.fastStep.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import com.stu.fastStep.dao.StepDao;
import com.stu.fastStep.domain.Step;

@Repository("StepDao")
public class StepDaoImpl implements StepDao{
	@Resource(name="sqlSessionFactory")
	private SqlSessionFactory factory;
	
	public Step getStepByUserAndCoupon(Step step) {
		SqlSession sqlSession=factory.openSession();
		Step result=sqlSession.selectOne("YFMapper.selectStepByUserAndCoupon",step);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}
	
	public List<Step> getStepsById(int userId) {
		SqlSession sqlSession=factory.openSession();
		List<Step> result=sqlSession.selectList("YFMapper.selectStepsByUserId",userId);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}

	
	public int updateStep(Step step) {
		SqlSession sqlSession=factory.openSession();
		int result=sqlSession.insert("YFMapper.updateStepByUserId",step);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}

	public Step insertStep(Step step) {
		SqlSession sqlSession=factory.openSession();
		sqlSession.insert("YFMapper.insertStep",step);
		sqlSession.commit();
		sqlSession.close();
		return step;
	}
	
}
