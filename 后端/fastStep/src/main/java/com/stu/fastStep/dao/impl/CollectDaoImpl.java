package com.stu.fastStep.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import com.stu.fastStep.dao.CollectDao;
import com.stu.fastStep.domain.Collect;

@Repository("CollectDao")
public class CollectDaoImpl implements CollectDao{
	@Resource(name="sqlSessionFactory")
	private SqlSessionFactory factory;
	
	public Collect getCollectByUserAndCoupon(Collect collect) {
		SqlSession sqlSession=factory.openSession();
		Collect result=sqlSession.selectOne("YFMapper.selectCollectByUserAndCoupon",collect);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}
	public List<Collect> getCollectsById(int userId) {
		SqlSession sqlSession=factory.openSession();
		List<Collect> result=sqlSession.selectList("YFMapper.selectCollectsByUserId",userId);
		sqlSession.commit();
		sqlSession.close();
		return result;
		
	}
	public int updateCollect(Collect collect) {
		SqlSession sqlSession=factory.openSession();
		int result=sqlSession.insert("YFMapper.updateCollectByUserId",collect);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}
	public Collect insertCollect(Collect collect) {
		SqlSession sqlSession=factory.openSession();
		sqlSession.insert("YFMapper.insertCollect",collect);
		sqlSession.commit();
		sqlSession.close();
		return collect;
	}
	
}
