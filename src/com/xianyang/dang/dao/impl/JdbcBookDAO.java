package com.xianyang.dang.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.xianyang.dang.dao.BookDAO;
import com.xianyang.dang.pojo.Book;
import com.xianyang.dang.pojo.Product;
import com.xianyang.dang.util.DBUtil;
import com.xianyang.dang.util.Factory;

public class JdbcBookDAO implements BookDAO{
	private static final String findByCatId="select dp.*,db.*" +
			" from d_category_product dcp join d_product dp on(dcp.product_id=dp.id)" +
			" join d_book db on(dp.id=db.id) where dcp.cat_id=? limit?,?";
	private static final String findAll="select * from d_product dp join d_book db on(dp.id=db.id) where dp.has_deleted=0";
	private static final String findById="select * from d_product dp join d_book db on(dp.id=db.id) where dp.id=?";
	
	/**
	 * 分页查询类别包含的产品
	 */
	public List<Product> findByCatId(int catId,int page,int size) throws Exception {
		PreparedStatement pst=DBUtil.getConnection().prepareStatement(findByCatId);
		pst.setInt(1, catId);
		//根据要显示的页数,计算出抓取记录的起始点
		int begin=(page-1)*size;
		pst.setInt(2,begin);//设置抓取的起始点
		pst.setInt(3,size);//设置最多抓取多少条记录
		ResultSet rs=pst.executeQuery();
		List<Product> list=new ArrayList<Product>();
		while(rs.next()){
			//将查询的数据封装成Book对象
			Book pro=new Book();
			//设置dp.*的信息-->d_product表字段
			pro.setId(rs.getInt("id"));
			pro.setProductName(rs.getString("product_name"));
			pro.setDescription(rs.getString("description"));
			pro.setAddTime(rs.getLong("add_time"));
			pro.setFixedPrice(rs.getDouble("fixed_price"));
			pro.setDangPrice(rs.getDouble("dang_price"));
			pro.setKeywords(rs.getString("keywords"));
			pro.setHasDeleted(rs.getInt("has_deleted"));
			pro.setProductPic(rs.getString("product_pic"));
			//设置db.*的信息-->d_book表字段
			pro.setAuthor(rs.getString("author"));
			pro.setPublishing(rs.getString("publishing"));
			pro.setPublishTime(rs.getLong("publish_time"));
			pro.setWordNumber(rs.getString("word_number"));
			pro.setWhichEdtion(rs.getString("which_edtion"));
			pro.setTotalPage(rs.getString("total_page"));
			pro.setPrintTime(rs.getInt("print_time"));
			pro.setPrintNumber(rs.getString("print_number"));
			pro.setIsbn(rs.getString("isbn"));
			pro.setAuthorSummary(rs.getString("author_summary"));
			pro.setCatalogue(rs.getString("catalogue"));
			list.add(pro);
		}
		return list;
	}
	public int getTotalPage(int catId,int size)throws Exception{
		String sql="select count(*)" +
		" from d_category_product dcp join d_product dp on(dcp.product_id=dp.id)" +
		" join d_book db on(dp.id=db.id) where dcp.cat_id=?";;
		PreparedStatement pst=DBUtil.getConnection().prepareStatement(sql);
		pst.setInt(1, catId);
		int result=0;
		ResultSet rs=pst.executeQuery();
		if(rs.next()){
			result=rs.getInt(1);
		}
		if(result%size==0){
			return result/size;
		}else{
			return result/size+1;
		}
	}
	public List<Product> findAll() throws Exception {
		PreparedStatement pst=DBUtil.getConnection().prepareStatement(findAll);
		ResultSet rs=pst.executeQuery();
		List<Product> list=new ArrayList<Product>();
		while(rs.next()){
			//将查询的数据封装成Book对象
			Book pro=new Book();
			//设置dp.*的信息-->d_product表字段
			pro.setId(rs.getInt("id"));
			pro.setProductName(rs.getString("product_name"));
			pro.setDescription(rs.getString("description"));
			pro.setAddTime(rs.getLong("add_time"));
			pro.setFixedPrice(rs.getDouble("fixed_price"));
			pro.setDangPrice(rs.getDouble("dang_price"));
			pro.setKeywords(rs.getString("keywords"));
			pro.setHasDeleted(rs.getInt("has_deleted"));
			pro.setProductPic(rs.getString("product_pic"));
			//设置db.*的信息-->d_book表字段
			pro.setAuthor(rs.getString("author"));
			pro.setPublishing(rs.getString("publishing"));
			pro.setPublishTime(rs.getLong("publish_time"));
			pro.setWordNumber(rs.getString("word_number"));
			pro.setWhichEdtion(rs.getString("which_edtion"));
			pro.setTotalPage(rs.getString("total_page"));
			pro.setPrintTime(rs.getInt("print_time"));
			pro.setPrintNumber(rs.getString("print_number"));
			pro.setIsbn(rs.getString("isbn"));
			pro.setAuthorSummary(rs.getString("author_summary"));
			pro.setCatalogue(rs.getString("catalogue"));
			list.add(pro);
		}
		return list;
	}
	public Product findById(int id) throws Exception {
		PreparedStatement pst=DBUtil.getConnection().prepareStatement(findById);
		pst.setInt(1,id);
		ResultSet rs=pst.executeQuery();
		Book pro=null;
		if(rs.next()){
			//将查询的数据封装成Book对象
			pro=new Book();
			//设置dp.*的信息-->d_product表字段
			pro.setId(rs.getInt("id"));
			pro.setProductName(rs.getString("product_name"));
			pro.setDescription(rs.getString("description"));
			pro.setAddTime(rs.getLong("add_time"));
			pro.setFixedPrice(rs.getDouble("fixed_price"));
			pro.setDangPrice(rs.getDouble("dang_price"));
			pro.setKeywords(rs.getString("keywords"));
			pro.setHasDeleted(rs.getInt("has_deleted"));
			pro.setProductPic(rs.getString("product_pic"));
			//设置db.*的信息-->d_book表字段
			pro.setAuthor(rs.getString("author"));
			pro.setPublishing(rs.getString("publishing"));
			pro.setPublishTime(rs.getLong("publish_time"));
			pro.setWordNumber(rs.getString("word_number"));
			pro.setWhichEdtion(rs.getString("which_edtion"));
			pro.setTotalPage(rs.getString("total_page"));
			pro.setPrintTime(rs.getInt("print_time"));
			pro.setPrintNumber(rs.getString("print_number"));
			pro.setIsbn(rs.getString("isbn"));
			pro.setAuthorSummary(rs.getString("author_summary"));
			pro.setCatalogue(rs.getString("catalogue"));
		}
		return pro;
	}
	
	public static void main(String[] args) throws Exception {
		BookDAO bookDAO=(BookDAO) Factory.getInstance("BookDAO");
		//System.out.println(bookDAO.getTotalPage(9,4));
		//System.out.println(bookDAO.findAll().size());
		System.out.println(bookDAO.findById(1).getAddTime());
	}

	
}
