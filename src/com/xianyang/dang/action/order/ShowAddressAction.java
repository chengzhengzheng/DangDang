package com.xianyang.dang.action.order;

import com.xianyang.dang.action.BaseAction;
import com.xianyang.dang.dao.AddressDAO;
import com.xianyang.dang.pojo.Address;
import com.xianyang.dang.util.Factory;

public class ShowAddressAction extends BaseAction{
	//input
	private int id;
	//output
	private Address address;
	
	public String execute()throws Exception{
		AddressDAO addressDAO=(AddressDAO)Factory.getInstance("AddressDAO");
		address=addressDAO.findById(id);
		return "success";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
}
