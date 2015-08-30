package com.xianyang.dang.action.main;

import java.util.List;

import com.xianyang.dang.action.BaseAction;
import com.xianyang.dang.dao.BookDAO;
import com.xianyang.dang.dao.CategoryDAO;
import com.xianyang.dang.dao.impl.JdbcBookDAO;
import com.xianyang.dang.pojo.Category;
import com.xianyang.dang.pojo.Product;
import com.xianyang.dang.util.Factory;

public class BookListAction extends BaseAction{
	//input
	private Integer cid;//当前类别ID
	private Integer pid;//父类别ID
	private Integer page=1;//要显示第几页
	//属性注入
	private Integer size=5;//一页显示多少条记录
	//output
	private List<Category> cats;//左侧类别信息
	private List<Product> pros;//中间类别所包含的产品信息
	private int totalNum=0;//产品数量合计
	private int maxPage=1;//最大页数
	private String parentName;//父类别Name
	
	//根据input获取output
	public String execute()throws Exception{
		//根据pid找cats子类别集合
		CategoryDAO catDao=(CategoryDAO) Factory.getInstance("CategoryDAO");
		cats=catDao.findByParentId(pid);
		for(Category c:cats){
			totalNum+=c.getPnum();
		}
		//根据pid找父类别的name
		parentName=catDao.getCategoryNameById(pid);
		//根据cid找中间的产品信息
		BookDAO bookDAO=new JdbcBookDAO();
		pros=bookDAO.findByCatId(cid,page,size);
		//根据类别包含的产品数量统计maxPage数量
		maxPage=bookDAO.getTotalPage(cid, size);
		//进入book_list.jsp显示
		return "success";
	}
	
	
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public List<Category> getCats() {
		return cats;
	}
	public void setCats(List<Category> cats) {
		this.cats = cats;
	}
	public List<Product> getPros() {
		return pros;
	}
	public void setPros(List<Product> pros) {
		this.pros = pros;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}
