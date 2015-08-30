package com.xianyang.dang.action.order;

import java.util.List;

import com.xianyang.dang.action.BaseAction;
import com.xianyang.dang.dao.AddressDAO;
import com.xianyang.dang.dao.ItemDAO;
import com.xianyang.dang.dao.OrderDAO;
import com.xianyang.dang.pojo.Address;
import com.xianyang.dang.pojo.CartItem;
import com.xianyang.dang.pojo.Item;
import com.xianyang.dang.pojo.Order;
import com.xianyang.dang.pojo.Product;
import com.xianyang.dang.pojo.User;
import com.xianyang.dang.service.CartService;
import com.xianyang.dang.util.Constant;
import com.xianyang.dang.util.CookieUtil;
import com.xianyang.dang.util.Factory;

public class OrderOKAction extends BaseAction{
	//input
	private Address address;
	
	//output
	private Integer orderId;
	private Double totalCost;
	
	public String execute()throws Exception{
		CartService cart=(CartService) session.get(Constant.CART_KEY);
		User user=(User) session.get("user");
		address.setUserId(user.getId());
		
		//将新的收货地址信息写入d_receive_address表
		if(address.getId()==null){
			AddressDAO addressDAO=(AddressDAO) Factory.getInstance("AddressDAO");
			addressDAO.save(address);
		}
		
		
		//将订单信息写入d_order表
		Order order=new Order();
		order.setAddress(address);
		order.setOrderDesc("很好");
		order.setOrderTime(System.currentTimeMillis());
		order.setStatus(Constant.WAIT_PAY);
		order.setTotalPrice(cart.cost());
		OrderDAO orderDAO=(OrderDAO) Factory.getInstance("OrderDAO");
		orderDAO.save(order);
		
		orderId=order.getId();
		totalCost=order.getTotalPrice();
		
		//向d_item写入若干条记录(购买几种商品,就写几条记录)
		ItemDAO itemDAO=(ItemDAO)Factory.getInstance("ItemDAO");
		List<CartItem> buyPros=cart.getBuyPros();
		for(CartItem c:buyPros){
			Product pro=c.getPro();
			Item item=new Item();
			item.setOrderId(order.getId());
			item.setProductId(pro.getId());
			item.setProductName(pro.getProductName());
			item.setProductNum(c.getQty());
			item.setDangPrice(pro.getDangPrice());
			item.setAmount(c.getQty()*pro.getDangPrice());
			itemDAO.save(item);
		}
		
		//订单创建成功后,将Session和Cookie中的商品信息删除
		session.remove(Constant.CART_KEY);
		CookieUtil.delete(Constant.COOKIE_BUY,httpResponse);
		CookieUtil.delete(Constant.COOKIE_DEL,httpResponse);
		return "success";
	}
	
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}
	
}
