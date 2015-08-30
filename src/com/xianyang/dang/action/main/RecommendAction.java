package com.xianyang.dang.action.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.xianyang.dang.action.BaseAction;
import com.xianyang.dang.dao.BookDAO;
import com.xianyang.dang.pojo.Product;
import com.xianyang.dang.util.Factory;

public class RecommendAction extends BaseAction{
	//系统自动随机选择两个产品信息显示
	private List<Product> pros;
	
	public String execute()throws Exception{
		BookDAO bookDAO=(BookDAO) Factory.getInstance("BookDAO");
		List<Product> list=bookDAO.findAll();
		Random r=new Random();
		pros=new ArrayList<Product>();
		int n=2,i=0;
		while(true){
			int index=r.nextInt(list.size());
			if(pros.contains(list.get(index))){
				continue;
			}
			pros.add(list.get(index));
			i++;
			if(i==n){
				break;
			}
		}
		return "success";
	}
	public List<Product> getPros() {
		return pros;
	}
	public void setPros(List<Product> pros) {
		this.pros = pros;
	}
}
