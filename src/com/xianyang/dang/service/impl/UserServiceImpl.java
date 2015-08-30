package com.xianyang.dang.service.impl;


import com.xianyang.dang.dao.UserDAO;
import com.xianyang.dang.pojo.User;
import com.xianyang.dang.service.UserService;
import com.xianyang.dang.util.Constant;
import com.xianyang.dang.util.EmailUtil;
import com.xianyang.dang.util.EncryptUtil;
import com.xianyang.dang.util.Factory;
import com.xianyang.dang.util.VerifyUtil;

public class UserServiceImpl implements UserService{

	public void regist(User user) throws Exception {
		//设置非表单信息
		String pwd=EncryptUtil.md5Encrypt(user.getPassword());
		user.setPassword(pwd);
		user.setUserIntegral(Constant.NORMAL);//等级
		user.setEmailVerify(false);//email未通过验证
		user.setLastLoginTime(System.currentTimeMillis());//最后登录时间
		
		String emailVerifyCode=VerifyUtil.createVerifyCode();
		user.setEmailVerifyCode(emailVerifyCode);//邮箱验证码
		UserDAO userDAO=(UserDAO) Factory.getInstance("UserDAO");
		userDAO.save(user);
		//将邮箱验证码发送邮件,verifyCode-id
		EmailUtil.sendEmail(user.getEmail(), emailVerifyCode+"-"+user.getId());
	}

	public User checkEmailVerifyCode(String code) throws Exception {
		User user=null;
		int index=code.lastIndexOf("-");
		String verifyCode=code.substring(0,index);
		int id=Integer.parseInt(code.substring(index+1,code.length()));
		UserDAO dao=(UserDAO) Factory.getInstance("UserDAO");
		user=dao.checkEmailVerifyCode(id, verifyCode);
		return user;
	}

	
	
}
