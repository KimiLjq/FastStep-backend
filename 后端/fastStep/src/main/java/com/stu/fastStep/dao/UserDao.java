package com.stu.fastStep.dao;

import com.stu.fastStep.domain.User;

public interface UserDao {
	public User getUserById(int id);
	public User getUserByNumber(String number);
	public User insertUser(User user);
	public boolean updateUser(User user);
}
