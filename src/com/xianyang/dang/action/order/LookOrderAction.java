package com.xianyang.dang.action.order;

import java.util.List;

import com.xianyang.dang.action.BaseAction;
import com.xianyang.dang.dao.OrderDAO;
import com.xianyang.dang.pojo.Order;
import com.xianyang.dang.util.Factory;

public class LookOrderAction extends BaseAction{
	//input
	private int userId;
	//output
	private List<Order> orders;
	
	public String execute()throws Exception{
		OrderDAO orderDAO=(OrderDAO)Factory.getInstance("OrderDAO");
		orders=orderDAO.findByUserId(userId);
		return "success";
	}
	
	
	
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
