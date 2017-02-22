package com.glife.entiry.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.glife.entity.UserInfo;
import com.glife.utils.JdbcUtils;

public class UserInfoDao {
	
	JdbcUtils jdbcUtils = JdbcUtils.getInstance();
	private Connection conn;  
    private PreparedStatement pstmt;  
    private ResultSet rs;  
    private String msg;
    
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public UserInfoDao(){
		super();
		
	}
	/**
	 * 获取指定名字下用户
	 * @param name 用户姓名
	 * @return 多个用户
	 */
	public List<UserInfo> queryUserByName(String name){
		
		List<UserInfo> list = null;
		try {
			String sql = "select * from userinfo where username = ? order by username ";
			conn = jdbcUtils.getConnection();
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
//			ResultSetMetaData metaData = resultSet.getMetaData();  
			list = new ArrayList<UserInfo>();
			while(rs.next()){
				UserInfo user = new UserInfo();
				int id = rs.getInt(1);
				String username = rs.getString(2);
				String pswd = rs.getString(3);
				user.setId(id);
				user.setUsername(username);
				user.setPswd(pswd);
				list.add(user);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jdbcUtils.releaseConn(conn,pstmt,rs);
		}
		return list;
	}
	
	public boolean validatePasswd(String name,String pswd){
		boolean validate = false;
		try {
			String sql = "select count(id) from userinfo where username = ? ";
			conn = jdbcUtils.getConnection();
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			rs.next();
			int count = rs.getInt(1);
			if(count > 0) 
			{
				String sql2 = "select count(id) from userinfo where username = ? and pswd = ? ";
				pstmt= conn.prepareStatement(sql2);
				pstmt.setString(1, name);
				pstmt.setString(2, pswd);
				rs = pstmt.executeQuery();
				rs.next();
				int count2 = rs.getInt(1);
				if(count2 > 0 ){
					validate = true;
				}else{
					this.msg = "密码不正确";
				}
			}else{
				 this.msg = "用户名不存在";
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jdbcUtils.releaseConn(conn,pstmt,rs);
		}
		
		return validate;
	}

}
