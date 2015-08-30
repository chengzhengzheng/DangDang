package com.xianyang.dang.dao;

import java.sql.SQLException;

import com.xianyang.dang.pojo.Item;

public interface ItemDAO {
	public void save(Item item)throws SQLException;
}
