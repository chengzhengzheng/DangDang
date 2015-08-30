package com.xianyang.dang.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.xianyang.dang.dao.CategoryDAO;
import com.xianyang.dang.pojo.Category;
import com.xianyang.dang.util.DBUtil;

public class JdbcCategoryDAO implements CategoryDAO{
	private static final String findAll="select * from d_category";
	private static final String findByParentId="select dc.*,count(dcp.product_id) as cnt" +
			" from d_category dc left outer join d_category_product dcp" +
			" on(dc.id=dcp.cat_id) where parent_id=? group by dc.id";
	
	public List<Category> findAll() throws Exception {
		PreparedStatement pst=DBUtil.getConnection().prepareStatement(findAll);
		ResultSet rs=pst.executeQuery();
		List<Category> list=new ArrayList<Category>();
		while(rs.next()){
			Category cat=new Category();
			cat.setId(rs.getInt("id"));
			cat.setName(rs.getString("name"));
			cat.setEnName(rs.getString("en_name"));
			cat.setTurn(rs.getInt("turn"));
			cat.setDescription(rs.getString("description"));
			cat.setParentId(rs.getInt("parent_id"));
			list.add(cat);
		}
		return list;
	}
	public List<Category> findByParentId(int parentId) throws Exception {
		PreparedStatement pst=DBUtil.getConnection().prepareStatement(findByParentId);
		pst.setInt(1,parentId);
		ResultSet rs=pst.executeQuery();
		List<Category> list=new ArrayList<Category>();
		while(rs.next()){
			Category cat=new Category();
			cat.setId(rs.getInt("id"));
			cat.setName(rs.getString("name"));
			cat.setEnName(rs.getString("en_name"));
			cat.setTurn(rs.getInt("turn"));
			cat.setDescription(rs.getString("description"));
			cat.setParentId(rs.getInt("parent_id"));
			//设置统计的产品数量
			cat.setPnum(rs.getInt("cnt"));
			list.add(cat);
		}
		return list;
	}
	public String getCategoryNameById(int id) throws Exception {
		String getName="select name from d_category where id=?";
		String name=null;
		PreparedStatement pst=DBUtil.getConnection().prepareStatement(getName);
		pst.setInt(1,id);
		ResultSet rs=pst.executeQuery();
		if(rs.next()){
			name=rs.getString(1);
		}
		return name;
	}
}
