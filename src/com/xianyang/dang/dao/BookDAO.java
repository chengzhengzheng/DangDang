package com.xianyang.dang.dao;

import java.util.List;

import com.xianyang.dang.pojo.Product;

public interface BookDAO {
	public List<Product> findByCatId(int catId,int page,int size) throws Exception;
	public int getTotalPage(int catId,int size)throws Exception;
	public List<Product> findAll() throws Exception;
	public Product findById(int id)throws Exception;
}
