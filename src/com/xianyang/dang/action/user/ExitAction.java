package com.xianyang.dang.action.user;

import com.xianyang.dang.action.BaseAction;

public class ExitAction extends BaseAction{
	public String execute()throws Exception{
		session.remove("user");
		return "success";
	}
}
