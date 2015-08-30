package com.xianyang.dang.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.xianyang.dang.dao.OrderDAO;
import com.xianyang.dang.pojo.Address;
import com.xianyang.dang.pojo.Order;
import com.xianyang.dang.util.DBUtil;
import com.xianyang.dang.util.Factory;

public class JdbcOrderDAO implements OrderDAO{
	private static final String save="insert into d_order(user_id,status,order_time," +
			"order_desc,total_price,receive_name,full_address,postal_code,mobile," +
			"phone) values(?,?,?,?,?,?,?,?,?,?)";
	private static final String findByUserId="select * from d_order where user_id=?";
	public void save(Order order) throws SQLException {
		PreparedStatement ps=DBUtil.getConnection().prepareStatement(save,Statement.RETURN_GENERATED_KEYS);
		ps.setInt(1, order.getAddress().getUserId());
		ps.setInt(2,order.getStatus());
		ps.setLong(3,order.getOrderTime());
		ps.setString(4,order.getOrderDesc());
		ps.setDouble(5,order.getTotalPrice());
		ps.setString(6, order.getAddress().getReceiveName());
		ps.setString(7,order.getAddress().getFullAddress());
		ps.setString(8,order.getAddress().getPostalCode());
		ps.setString(9, order.getAddress().getMobile());
		ps.setString(10,order.getAddress().getPhone());
		ps.executeUpdate();
		ResultSet rs=ps.getGeneratedKeys();
		rs.next();
		int id=rs.getInt(1);
		order.setId(id);
	}
	public List<Order> findByUserId(int userId) throws SQLException {
		PreparedStatement ps=DBUtil.getConnection().prepareStatement(findByUserId);
		ps.setInt(1,userId);
		ResultSet rs=ps.executeQuery();
		List<Order> orders=new ArrayList<Order>();
		while(rs.next()){
			Order order=new Order();
			order.setId(rs.getInt("id"));
			order.setStatus(rs.getInt("status"));
			order.setOrderTime(rs.getLong("order_time"));
			order.setOrderDesc(rs.getString("order_desc"));
			order.setTotalPrice(rs.getDouble("total_price"));
			
			Address address=new Address();
			address.setUserId(rs.getInt("user_id"));
			address.setReceiveName(rs.getString("receive_name"));
			address.setFullAddress(rs.getString("full_address"));
			address.setPostalCode(rs.getString("postal_code"));
			address.setMobile(rs.getString("mobile"));
			address.setPhone(rs.getString("phone"));
			
			order.setAddress(address);
			
			orders.add(order);
		}
		return orders;
	}
	public static void main(String[] args) throws SQLException {
		OrderDAO dao=(OrderDAO) Factory.getInstance("OrderDAO");
//		Address address=new Address();
//		address.setUserId(2);
//		address.setReceiveName("zhangsan");
//		address.setFullAddress("beijing");
//		address.setPostalCode("100000");
//		address.setMobile("13800000000");
//		address.setPhone("029-88888888");
//		Order order=new Order();
//		order.setAddress(address);
//		order.setOrderDesc("");
//		order.setOrderTime(System.currentTimeMillis());
//		order.setStatus(Constant.WAIT_PAY);
//		order.setTotalPrice(0.0);
//		dao.save(order);
//		System.out.println(order.getId());
		
		List<Order> orders=dao.findByUserId(1);
		System.out.println(orders.size());
		System.out.println(orders.get(0).getTotalPrice());
	}
}
