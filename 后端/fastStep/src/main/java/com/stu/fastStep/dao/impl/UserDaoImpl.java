package com.stu.fastStep.dao.impl;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import com.stu.fastStep.dao.UserDao;
import com.stu.fastStep.domain.User;


@Repository("UserDao")
public class UserDaoImpl implements UserDao{
	@Resource(name="sqlSessionFactory")
	private SqlSessionFactory factory;

	public User getUserById(int id) {
		SqlSession sqlSession=factory.openSession();
		User result=sqlSession.selectOne("YFMapper.selectUserById", id);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}

	public User getUserByNumber(String number) {
		SqlSession sqlSession=factory.openSession();
		User result=sqlSession.selectOne("YFMapper.selectUserByNumber",number);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}

	public User insertUser(User user) {
		SqlSession sqlSession=factory.openSession();
		sqlSession.insert("YFMapper.insertUser",user);
		sqlSession.commit();
		sqlSession.close();
		return user;
	}

	public boolean updateUser(User user) {
		SqlSession sqlSession=factory.openSession();
		boolean result=(1==sqlSession.insert("YFMapper.updateUser",user));
		sqlSession.commit();
		sqlSession.close();
		return result;
	}
	
	

}
