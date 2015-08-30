package com.xianyang.dang.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.xianyang.dang.dao.ItemDAO;
import com.xianyang.dang.pojo.Item;
import com.xianyang.dang.util.DBUtil;

public class JdbcItemDAO implements ItemDAO{
	private static final String save="insert into d_item" +
			" (order_id,product_id,product_name,dang_price," +
			"product_num,amount) values(?,?,?,?,?,?)";
	public void save(Item item) throws SQLException {
		PreparedStatement ps=DBUtil.getConnection().prepareStatement(save);
		ps.setInt(1,item.getOrderId());
		ps.setInt(2,item.getProductId());
		ps.setString(3,item.getProductName());
		ps.setDouble(4, item.getDangPrice());
		ps.setInt(5,item.getProductNum());
		ps.setDouble(6,item.getAmount());
		ps.executeUpdate();
	}
	
}
