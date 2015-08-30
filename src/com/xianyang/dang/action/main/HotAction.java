package com.xianyang.dang.action.main;

import java.util.List;

import com.xianyang.dang.action.BaseAction;
import com.xianyang.dang.dao.ProductDAO;
import com.xianyang.dang.pojo.Product;
import com.xianyang.dang.util.Factory;

public class HotAction extends BaseAction{
	//output销量最高的前8个
	private List<Product> pros;
	//在struts-main.xml中注入
	private int size=8;
	
	public String execute()throws Exception{
		ProductDAO proDao=(ProductDAO) Factory.getInstance("ProductDAO");
		pros=proDao.findHotProduct(size);
		return "success";
	}
	
	public List<Product> getPros() {
		return pros;
	}
	public void setPros(List<Product> pros) {
		this.pros = pros;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
}
