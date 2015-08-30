package com.xianyang.dang.dao;

import java.sql.SQLException;

import com.xianyang.dang.pojo.User;

public interface UserDAO {
	public void save(User user) throws SQLException;
	public User findByEmail(String email) throws SQLException;
	public User checkEmailVerifyCode(int id,String verifyCode)throws SQLException;
	public User findByLogin(String email,String password)throws SQLException;
	public void updateIpTime(User user)throws SQLException;
}
