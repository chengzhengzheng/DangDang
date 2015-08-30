package com.xianyang.dang.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xianyang.dang.dao.ProductDAO;
import com.xianyang.dang.pojo.Product;
import com.xianyang.dang.util.DBUtil;

public class JdbcProductDAO implements ProductDAO{
	private String findNewProduct="select * from d_product where has_deleted=0 order by add_time desc limit ?";
	private String findHotProduct="select dp.*,sum(di.product_num) as cnt from d_item di join d_product dp on (di.product_id=dp.id) group by dp.id order by cnt desc limit ?";
	private String findNewHotProduct="select dp.*,sum(di.product_num) as cnt from d_item di join d_product dp on (di.product_id=dp.id) join d_order dod on(di.order_id=dod.id) where dod.order_time>? group by dp.id order by cnt desc limit ?";
	private String findById="select * from d_product where id=?";
	
	public List<Product> findNewProduct(int size) throws SQLException {
		PreparedStatement pst=DBUtil.getConnection().prepareStatement(findNewProduct);
		pst.setInt(1, size);
		ResultSet rs=pst.executeQuery();
		List<Product> list=new ArrayList<Product>();
		while(rs.next()){
			Product pro=new Product();
			pro.setId(rs.getInt("id"));
			pro.setProductName(rs.getString("product_name"));
			pro.setDescription(rs.getString("description"));
			pro.setAddTime(rs.getLong("add_time"));
			pro.setFixedPrice(rs.getDouble("fixed_price"));
			pro.setDangPrice(rs.getDouble("dang_price"));
			pro.setKeywords(rs.getString("keywords"));
			pro.setHasDeleted(rs.getInt("has_deleted"));
			pro.setProductPic(rs.getString("product_pic"));
			list.add(pro);
		}
		return list;
	}

	public List<Product> findHotProduct(int size) throws SQLException {
		PreparedStatement pst=DBUtil.getConnection().prepareStatement(findHotProduct);
		pst.setInt(1, size);
		ResultSet rs=pst.executeQuery();
		List<Product> list=new ArrayList<Product>();
		while(rs.next()){
			Product pro=new Product();
			pro.setId(rs.getInt("id"));
			pro.setProductName(rs.getString("product_name"));
			pro.setDescription(rs.getString("description"));
			pro.setAddTime(rs.getLong("add_time"));
			pro.setFixedPrice(rs.getDouble("fixed_price"));
			pro.setDangPrice(rs.getDouble("dang_price"));
			pro.setKeywords(rs.getString("keywords"));
			pro.setHasDeleted(rs.getInt("has_deleted"));
			pro.setProductPic(rs.getString("product_pic"));
			list.add(pro);
		}
		return list;
	}

	public List<Product> findNewHotProduct(int size, int month)
			throws SQLException {
		PreparedStatement pst=DBUtil.getConnection().prepareStatement(findNewHotProduct);
		long time=System.currentTimeMillis()-(long)month*30*24*60*60*1000;
		pst.setLong(1,time);
		pst.setInt(2, size);
		ResultSet rs=pst.executeQuery();
		List<Product> list=new ArrayList<Product>();
		while(rs.next()){
			Product pro=new Product();
			pro.setId(rs.getInt("id"));
			pro.setProductName(rs.getString("product_name"));
			pro.setDescription(rs.getString("description"));
			pro.setAddTime(rs.getLong("add_time"));
			pro.setFixedPrice(rs.getDouble("fixed_price"));
			pro.setDangPrice(rs.getDouble("dang_price"));
			pro.setKeywords(rs.getString("keywords"));
			pro.setHasDeleted(rs.getInt("has_deleted"));
			pro.setProductPic(rs.getString("product_pic"));
			list.add(pro);
		}
		return list;
	}
	public Product findById(int id) throws SQLException {
		PreparedStatement pst=DBUtil.getConnection().prepareStatement(findById);
		pst.setInt(1, id);
		ResultSet rs=pst.executeQuery();
		Product pro=null;
		if(rs.next()){
			pro=new Product();
			pro.setId(rs.getInt("id"));
			pro.setProductName(rs.getString("product_name"));
			pro.setDescription(rs.getString("description"));
			pro.setAddTime(rs.getLong("add_time"));
			pro.setFixedPrice(rs.getDouble("fixed_price"));
			pro.setDangPrice(rs.getDouble("dang_price"));
			pro.setKeywords(rs.getString("keywords"));
			pro.setHasDeleted(rs.getInt("has_deleted"));
			pro.setProductPic(rs.getString("product_pic"));
		}
		return pro;
	}
//	public static void main(String[] args) throws SQLException {
//		ProductDAO dao=new JdbcProductDAO();
//		List<Product> list=dao.findNewHotProduct(8,1);
//		System.out.println(list.size());
//		for(Product p:list){
//			System.out.println(p.getProductPic());
//		}
////		Product pro=dao.findById(1);
////		System.out.println(pro.getDescription());
//	}

	
}
