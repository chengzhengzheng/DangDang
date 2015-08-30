package com.xianyang.dang.action.user;

import com.xianyang.dang.action.BaseAction;
import com.xianyang.dang.pojo.User;
import com.xianyang.dang.service.UserService;
import com.xianyang.dang.util.Factory;

public class ValidEmailVerifyCodeAction extends BaseAction{
	private String code;//input
	
	private String errorMsg;//验证失败,在页面显示错误信息
	private String emailVerifyCode;
	private String email;
	//用于输入验证码出错,将验证码继续显示在页面上,若采用从邮箱获取验证码信息,此变量不必要
	private User user;
	
	
	public String execute()throws Exception{
		UserService userService=(UserService) Factory.getInstance("UserService");
		user=userService.checkEmailVerifyCode(code);
		if(user!=null){
			return "success";
		}else{
			emailVerifyCode=(String)session.get("emailVerifyCode");
			email=(String)session.get("email");
			errorMsg="验证码错误!";
			return "error";
		}	
	}
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getEmailVerifyCode() {
		return emailVerifyCode;
	}
	public void setEmailVerifyCode(String emailVerifyCode) {
		this.emailVerifyCode = emailVerifyCode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
