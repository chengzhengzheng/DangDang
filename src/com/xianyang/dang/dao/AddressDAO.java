package com.xianyang.dang.dao;

import java.sql.SQLException;
import java.util.List;

import com.xianyang.dang.pojo.Address;

public interface AddressDAO {
	public void save(Address address)throws SQLException;
	public List<Address> findAddressByUserId(int userId)throws SQLException;
	public Address findById(int id)throws SQLException;
}
