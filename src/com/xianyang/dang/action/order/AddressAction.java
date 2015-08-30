package com.xianyang.dang.action.order;

import java.util.List;

import com.xianyang.dang.action.BaseAction;
import com.xianyang.dang.dao.AddressDAO;
import com.xianyang.dang.pojo.Address;
import com.xianyang.dang.pojo.User;
import com.xianyang.dang.util.Factory;

public class AddressAction extends BaseAction{
	private List<Address> adds;
	
	public String execute()throws Exception{
		AddressDAO addressDAO=(AddressDAO)Factory.getInstance("AddressDAO"); 
		User user=(User) session.get("user");
		adds=addressDAO.findAddressByUserId(user.getId());
		return "success";
	}
	public List<Address> getAdds() {
		return adds;
	}
	public void setAdds(List<Address> adds) {
		this.adds = adds;
	}
}
