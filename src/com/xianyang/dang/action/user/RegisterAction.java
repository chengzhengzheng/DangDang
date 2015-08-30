package com.xianyang.dang.action.user;



import com.xianyang.dang.action.BaseAction;
import com.xianyang.dang.pojo.User;
import com.xianyang.dang.service.UserService;
import com.xianyang.dang.util.Factory;

public class RegisterAction extends BaseAction{
	//input 
	private User user;
	//output
	private String emailVerifyCode;
	private String email;
	
	public String execute() throws Exception{
		user.setLastLoginIp(httpRequest.getRemoteAddr());//最后登录IP
		UserService userService=(UserService) Factory.getInstance("UserService");
		userService.regist(user);
		emailVerifyCode=user.getEmailVerifyCode()+"-"+user.getId();
		email=user.getEmail();
		session.put("emailVerifyCode",emailVerifyCode);
		session.put("email",email);
		return "verify";
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
}
