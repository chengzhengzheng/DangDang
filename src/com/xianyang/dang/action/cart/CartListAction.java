package com.xianyang.dang.action.cart;

import java.util.List;

import com.xianyang.dang.action.BaseAction;
import com.xianyang.dang.pojo.CartItem;
import com.xianyang.dang.service.CartService;
import com.xianyang.dang.util.Constant;
import com.xianyang.dang.util.CookieUtil;
import com.xianyang.dang.util.Factory;

public class CartListAction extends BaseAction{
	//output
	private List<CartItem> buyPros;
	private List<CartItem> delPros;
	private double saving=0;
	private double totalCost=0;
	
	public String execute()throws Exception{
		CartService cart=(CartService) session.get(Constant.CART_KEY);
		if(cart==null){
			//有可能用户之前购买过一些商品,尝试恢复之前购买过的商品
			System.out.println("获得购物车");
			cart=(CartService) Factory.getInstance("CartService");
			cart.load(CookieUtil.findCookie(Constant.COOKIE_BUY,httpRequest),cart.getBuyPros());
			cart.load(CookieUtil.findCookie(Constant.COOKIE_DEL,httpRequest),cart.getDelPros());
			session.put(Constant.CART_KEY,cart);
		}
		buyPros=cart.getBuyPros();
		delPros=cart.getDelPros();
		totalCost=cart.cost();
		for(int i=0;i<buyPros.size();i++){
			CartItem item=buyPros.get(i);
			saving+=(item.getPro().getFixedPrice()-item.getPro().getDangPrice())*item.getQty();
		}
		return "list";
	}
	public List<CartItem> getBuyPros() {
		return buyPros;
	}
	public void setBuyPros(List<CartItem> buyPros) {
		this.buyPros = buyPros;
	}
	public List<CartItem> getDelPros() {
		return delPros;
	}
	public void setDelPros(List<CartItem> delPros) {
		this.delPros = delPros;
	}
	public double getSaving() {
		return saving;
	}
	public void setSaving(double saving) {
		this.saving = saving;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
}
