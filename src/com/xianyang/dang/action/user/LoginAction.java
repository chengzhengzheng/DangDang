package com.xianyang.dang.action.user;

import com.xianyang.dang.action.BaseAction;
import com.xianyang.dang.dao.UserDAO;
import com.xianyang.dang.pojo.User;
import com.xianyang.dang.util.EncryptUtil;
import com.xianyang.dang.util.Factory;

public class LoginAction extends BaseAction{
	//input
	private String email;
	private String password;
	
	//output  转向verify_form.jsp时显示
	private String emailVerifyCode;
	//在登录页面显示错误信息
	private String errorMsg;
	
	public String execute()throws Exception{
		String pwd=EncryptUtil.md5Encrypt(password);
		UserDAO dao=(UserDAO) Factory.getInstance("UserDAO");
		User user=dao.findByLogin(email,pwd);
		if(user==null){
			errorMsg="邮箱或密码错误";
			return "error";
		}else if(user.getEmailVerify()){
			session.put("user",user);
			//更新最后登录时间和IP
			user.setLastLoginTime(System.currentTimeMillis());
			user.setLastLoginIp(httpRequest.getRemoteAddr());
			dao.updateIpTime(user);
			return "success";
		}else{
			emailVerifyCode=user.getEmailVerifyCode()+"-"+user.getId();
			session.put("emailVerifyCode",emailVerifyCode);
			session.put("email",email);
			return "verify";
		}
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailVerifyCode() {
		return emailVerifyCode;
	}
	public void setEmailVerifyCode(String emailVerifyCode) {
		this.emailVerifyCode = emailVerifyCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
