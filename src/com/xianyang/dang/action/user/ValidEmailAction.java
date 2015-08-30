package com.xianyang.dang.action.user;

import com.xianyang.dang.action.BaseAction;
import com.xianyang.dang.dao.UserDAO;
import com.xianyang.dang.pojo.User;
import com.xianyang.dang.util.Factory;

public class ValidEmailAction extends BaseAction{
	private String email;
	private boolean ok;
	
	public String execute()throws Exception{
		UserDAO dao=(UserDAO) Factory.getInstance("UserDAO");
		User user=dao.findByEmail(email);
		if(user==null){
			ok=true;//可以使用
		}else{
			ok=false;
		}
		return "success";
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}
}
