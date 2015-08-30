package com.xianyang.dang.action.cart;

import com.xianyang.dang.action.BaseAction;
import com.xianyang.dang.service.CartService;
import com.xianyang.dang.util.Constant;
import com.xianyang.dang.util.CookieUtil;
import com.xianyang.dang.util.Factory;

public class BuyAction extends BaseAction{
	//input
	private int pid;
	//output
	private boolean ok=true;
	
	public String execute()throws Exception{
		CartService cart=(CartService)session.get(Constant.CART_KEY);
		if(cart==null){
			cart=(CartService) Factory.getInstance("CartService");
			//有可能用户之前购买过一些商品,尝试恢复之前购买过的商品
			cart.load(CookieUtil.findCookie(Constant.COOKIE_BUY,httpRequest),cart.getBuyPros());
			cart.load(CookieUtil.findCookie(Constant.COOKIE_DEL,httpRequest),cart.getDelPros());
			session.put(Constant.CART_KEY,cart);
		}
		ok=cart.add(pid);
		if(ok){
			CookieUtil.addCookie(Constant.COOKIE_BUY,cart.store(cart.getBuyPros()),httpResponse);
			CookieUtil.addCookie(Constant.COOKIE_DEL,cart.store(cart.getDelPros()),httpResponse);
		}
		return "success";//利用json Result将ok返回
	}
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}
}
