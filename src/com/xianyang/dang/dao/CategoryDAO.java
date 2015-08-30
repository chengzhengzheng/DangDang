package com.xianyang.dang.dao;

import java.util.List;

import com.xianyang.dang.pojo.Category;

public interface CategoryDAO {
	public List<Category> findAll()throws Exception;
	public List<Category> findByParentId(int parentId)throws Exception;
	public String getCategoryNameById(int id)throws Exception;
}
