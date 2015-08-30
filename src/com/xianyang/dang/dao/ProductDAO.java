package com.xianyang.dang.dao;

import java.sql.SQLException;
import java.util.List;

import com.xianyang.dang.pojo.Product;

public interface ProductDAO {
	public List<Product> findNewProduct(int size)throws SQLException;
	public List<Product> findHotProduct(int size) throws SQLException;
	public List<Product> findNewHotProduct(int size,int month)throws SQLException;
	public Product findById(int id)throws SQLException;
}
