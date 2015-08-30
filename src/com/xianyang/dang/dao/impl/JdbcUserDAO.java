package com.xianyang.dang.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import com.xianyang.dang.dao.UserDAO;
import com.xianyang.dang.pojo.User;
import com.xianyang.dang.util.DBUtil;

public class JdbcUserDAO implements UserDAO{
	private static final String save="insert into d_user(email,nickname,password" +
			",user_integral,is_email_verify,email_verify_code,last_login_time," +
			"last_login_ip) values(?,?,?,?,?,?,?,?)";
	private static final String find_By_Email="select * from d_user where email=?";
	private static final String find_By_IdAndVerifyCode="select * from d_user where id=? and email_verify_code=?";
	private static final String update_EmailVerify="update d_user set is_email_verify=? where id=?";
	private static final String find_By_Login="select * from d_user where email=? and password=?";
	private static final String update_Ip_Time="update d_user set last_login_time=?,last_login_ip=? where id=?";
	
	public void save(User user) throws SQLException {
		PreparedStatement pst=DBUtil.getConnection().prepareStatement(save,Statement.RETURN_GENERATED_KEYS);
		pst.setString(1,user.getEmail());
		pst.setString(2,user.getNickname());
		pst.setString(3,user.getPassword());
		pst.setInt(4,user.getUserIntegral());
		String emailVerify=user.getEmailVerify()?"Y":"N";
		pst.setString(5,emailVerify);
		pst.setString(6,user.getEmailVerifyCode());
		pst.setLong(7,user.getLastLoginTime());
		pst.setString(8,user.getLastLoginIp());
		pst.executeUpdate();
		//获取自动增长的id
		ResultSet rs=pst.getGeneratedKeys();
		rs.next();
		int id=rs.getInt(1);
		user.setId(id);
	}
	public User findByEmail(String email) throws SQLException {
		User user=null;
		PreparedStatement pst=DBUtil.getConnection().prepareStatement(find_By_Email);
		pst.setString(1,email);
		ResultSet rs=pst.executeQuery();
		if(rs.next()){
			user=new User();
			user.setId(rs.getInt("id"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			user.setNickname(rs.getString("nickname"));
			user.setUserIntegral(rs.getInt("user_integral"));
			Boolean emailVerify=rs.getString("is_email_verify").equals("Y")?true:false;
			user.setEmailVerify(emailVerify);
			user.setEmailVerifyCode(rs.getString("email_verify_code"));
			user.setLastLoginTime(rs.getLong("last_login_time"));
			user.setLastLoginIp(rs.getString("last_login_ip"));
		}
		return user;
	}
	public User checkEmailVerifyCode(int id,String verifyCode) throws SQLException {
		User user=null;
		PreparedStatement pst=DBUtil.getConnection().prepareStatement(find_By_IdAndVerifyCode);
		pst.setInt(1,id);
		pst.setString(2,verifyCode);
		ResultSet rs=pst.executeQuery();
		if(rs.next()){
			user=new User();
			user.setId(rs.getInt("id"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			user.setNickname(rs.getString("nickname"));
			user.setUserIntegral(rs.getInt("user_integral"));
			user.setEmailVerify(true);
			user.setEmailVerifyCode(rs.getString("email_verify_code"));
			user.setLastLoginTime(rs.getLong("last_login_time"));
			user.setLastLoginIp(rs.getString("last_login_ip"));
			pst=DBUtil.getConnection().prepareStatement(update_EmailVerify);
			pst.setString(1,"Y");
			pst.setInt(2,id);
			pst.executeUpdate();
		}
		return user;
	}
	public User findByLogin(String email, String password) throws SQLException {
		User user=null;
		PreparedStatement pst=DBUtil.getConnection().prepareStatement(find_By_Login);
		pst.setString(1,email);
		pst.setString(2,password);
		ResultSet rs=pst.executeQuery();
		if(rs.next()){
			user=new User();
			user.setId(rs.getInt("id"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			user.setNickname(rs.getString("nickname"));
			user.setUserIntegral(rs.getInt("user_integral"));
			Boolean emailVerify=rs.getString("is_email_verify").equals("Y")?true:false;
			user.setEmailVerify(emailVerify);
			user.setEmailVerifyCode(rs.getString("email_verify_code"));
			user.setLastLoginTime(rs.getLong("last_login_time"));
			user.setLastLoginIp(rs.getString("last_login_ip"));
		}
		return user;
	}
	public void updateIpTime(User user) throws SQLException {
		PreparedStatement pst=DBUtil.getConnection().prepareStatement(update_Ip_Time);
		pst.setLong(1,user.getLastLoginTime());
		pst.setString(2,user.getLastLoginIp());
		pst.setInt(3,user.getId());
		pst.executeUpdate();
	}
	
	public static void main(String[] args) throws SQLException {
		UserDAO dao=new JdbcUserDAO();
		
		User user=new User();
		user.setEmail("zhangsan1@163.com");
		user.setEmailVerify(false);
		user.setEmailVerifyCode("张三");
		user.setPassword("123");
		user.setLastLoginTime(System.currentTimeMillis());
		user.setLastLoginIp("localhost");
		user.setNickname("amao");
		user.setUserIntegral(5);
//		user.setId(1);
//		dao.updateIpTime(user);
		dao.save(user);
//		
//		User user=dao.findByEmail("zhangsanfeng@163.com");
//		System.out.println(user.getNickname());
		
//		User user=dao.checkEmailVerifyCode(12, "8932acf2-94ee-440a-b27f-a0c29098364c");
//		User user=dao.findByLogin("yangguo@163.com",EncryptUtil.md5Encrypt("123456"));
//		System.out.println(user.getEmailVerify());
	}
	
	
	
}
