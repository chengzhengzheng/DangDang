package com.xianyang.dang.action.order;

import java.util.List;

import com.xianyang.dang.action.BaseAction;
import com.xianyang.dang.pojo.CartItem;
import com.xianyang.dang.service.CartService;
import com.xianyang.dang.util.Constant;

public class ConfirmAction extends BaseAction{
	//output
	private List<CartItem> buyPros;
	private double totalCost=0;
	public String execute()throws Exception{
		CartService cart=(CartService) session.get(Constant.CART_KEY);
		buyPros=cart.getBuyPros();
		totalCost=cart.cost();
		return "success";
	}
	public List<CartItem> getBuyPros() {
		return buyPros;
	}
	public void setBuyPros(List<CartItem> buyPros) {
		this.buyPros = buyPros;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
}
