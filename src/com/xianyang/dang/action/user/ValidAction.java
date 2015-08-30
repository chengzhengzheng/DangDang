package com.xianyang.dang.action.user;

import com.xianyang.dang.action.BaseAction;

public class ValidAction extends BaseAction{
	private String code;//input
	private boolean ok=false;//output-->json
	public String execute()throws Exception{
		Thread.sleep(1000);
		//比较
		String scode=(String)session.get("code");
		if(code.equals(scode)){
			ok=true;
		}else{
			ok=false;
		}
		return "success";//调用jsonResult输出ok
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}
}
