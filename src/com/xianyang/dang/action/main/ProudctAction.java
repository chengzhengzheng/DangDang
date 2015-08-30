package com.xianyang.dang.action.main;

import com.xianyang.dang.action.BaseAction;
import com.xianyang.dang.dao.BookDAO;
import com.xianyang.dang.pojo.Product;
import com.xianyang.dang.util.Factory;

public class ProudctAction extends BaseAction{
	//input
	private Integer id;
	//output
	private Product pro;
	
	public String execute()throws Exception{
		BookDAO bookDAO=(BookDAO) Factory.getInstance("BookDAO");
		pro=bookDAO.findById(id);
		return "success";
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Product getPro() {
		return pro;
	}
	public void setPro(Product pro) {
		this.pro = pro;
	}
}
