package com.xianyang.dang.service;



import com.xianyang.dang.pojo.User;

public interface UserService {
	public void regist(User user) throws Exception;
	public User checkEmailVerifyCode(String code)throws Exception;
}
