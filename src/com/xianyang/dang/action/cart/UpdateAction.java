package com.xianyang.dang.action.cart;

import com.xianyang.dang.action.BaseAction;
import com.xianyang.dang.service.CartService;
import com.xianyang.dang.util.Constant;
import com.xianyang.dang.util.CookieUtil;

public class UpdateAction extends BaseAction{
	//input
	private int id;
	private int qty;
	
	public String execute()throws Exception{
		CartService cart=(CartService) session.get(Constant.CART_KEY);
		cart.update(id, qty);
		CookieUtil.addCookie(Constant.COOKIE_BUY,cart.store(cart.getBuyPros()),httpResponse);
		CookieUtil.addCookie(Constant.COOKIE_DEL,cart.store(cart.getDelPros()),httpResponse);
		return "list";
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
}
