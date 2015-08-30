package com.xianyang.dang.dao;

import java.sql.SQLException;
import java.util.List;

import com.xianyang.dang.pojo.Order;

public interface OrderDAO {
	public void save(Order order)throws SQLException;
	public List<Order> findByUserId(int userId)throws SQLException;
}
