package com.xianyang.dang.action.main;

import java.util.List;

import com.xianyang.dang.action.BaseAction;
import com.xianyang.dang.pojo.Category;
import com.xianyang.dang.service.MainService;
import com.xianyang.dang.util.Factory;

public class CategoryAction extends BaseAction{
	private List<Category> cats;
	
	public String execute()throws Exception{
		//Thread.sleep(1000);//模拟延迟
		//获取左侧类别信息的集合
		MainService mainService=(MainService) Factory.getInstance("MainService");
		cats=mainService.findLeftCategory();
		return "success";
	}
	
	public List<Category> getCats() {
		return cats;
	}
	public void setCats(List<Category> cats) {
		this.cats = cats;
	}
}
