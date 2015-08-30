package com.xianyang.dang.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.xianyang.dang.dao.CategoryDAO;
import com.xianyang.dang.pojo.Category;
import com.xianyang.dang.service.MainService;
import com.xianyang.dang.util.Factory;

public class MainServiceImpl implements MainService{
	/**
	 * 返回主页面左侧类别的数据
	 */
	public List<Category> findLeftCategory() throws Exception {
		CategoryDAO catDao=(CategoryDAO) Factory.getInstance("CategoryDAO");
		List<Category> all=catDao.findAll();
		//获取parentId等于1的集合
		List<Category> cats=findByParentId(all, 1);
		//为cats集合元素的subCats填充
		for(Category cat:cats){
			List<Category>subCats= findByParentId(all,cat.getId());
			//将子项集合给cat对象赋值
			cat.setSubCats(subCats);
		}
		return cats;
	}
	/**
	 * 根据parentId条件从all中集合过滤
	 * @param all 所有类别元素
	 * @param parentId 父类别的ID值
	 * @return
	 */
	private List<Category> findByParentId(List<Category> all,int parentId){
		//循环冲all集合查找parentId=?的集合
		List<Category> list=new ArrayList<Category>();
		for(Category cat:all){
			if(cat.getParentId().equals(parentId)){
				list.add(cat);
			}
		}
		return list;
	}
}
