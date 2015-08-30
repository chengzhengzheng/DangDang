package com.xianyang.dang.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.xianyang.dang.pojo.CartItem;
import com.xianyang.dang.service.CartService;

public class CartServiceImpl1 implements CartService{
	private List<CartItem> pros=new ArrayList<CartItem>();
	public boolean add(int pid) {
		// TODO 将产品对象封装成Item,存入pros
		return false;
	}

	public double cost() {
		// TODO 将pros集合中item对象buy=true状态的元素,统计其金额
		return 0;
	}

	public void delete(int pid) {
		// TODO 将pros集合中的item对象的buy设置成false
		
	}

	public List<CartItem> getBuyPros() {
		// TODO 将pros集合中buy=true元素返回
		return null;
	}

	public List<CartItem> getDelPros() {
		// TODO 将pros集合中buy=false元素返回
		return null;
	}

	public void recovery(int pid) {
		// TODO 将pros集合中的item对象的buy设置成true
		
	}

	public void update(int pid, int pnum) {
		// TODO 将pros集合中的Item对象的qty修改为pnum
	}
	public List<CartItem> getPros() {
		return pros;
	}
	public void setPros(List<CartItem> pros) {
		this.pros = pros;
	}

	public void load(String content,List<CartItem> Pros) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public String store(List<CartItem> Pros) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
