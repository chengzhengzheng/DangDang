package com.xianyang.dang.action.cart;

import com.xianyang.dang.action.BaseAction;
import com.xianyang.dang.service.CartService;
import com.xianyang.dang.util.Constant;
import com.xianyang.dang.util.CookieUtil;

public class DeleteAction extends BaseAction{
	//input
	private int pid;
	
	public String execute()throws Exception{
		CartService cart=(CartService)session.get(Constant.CART_KEY);
		cart.delete(pid);
		CookieUtil.addCookie(Constant.COOKIE_BUY,cart.store(cart.getBuyPros()),httpResponse);
		CookieUtil.addCookie(Constant.COOKIE_DEL,cart.store(cart.getDelPros()),httpResponse);
		return "list";//购物车列表显示
	}
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
}
