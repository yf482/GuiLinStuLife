package com.glife.web.util.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.glife.entity.UserInfo;
import com.glife.web.utils.JdbcUtils;

public class JdbcUtilsTest {
	JdbcUtils jdbcUtils = JdbcUtils.getInstance();
   
    @Test
    public void inset(){
        
        jdbcUtils.getConnection();  

        /*******************增*********************/  
        String sql = "insert into userinfo (username, pswd) values (?, ?), (?, ?), (?, ?)"; 
        List<Object> params = new ArrayList<Object>(); 
        params.add("小明"); 
        params.add("123xiaoming"); 
        params.add("张三"); 
        params.add("zhangsan"); 
        params.add("李四"); 
        params.add("lisi000"); 
        try { 
            boolean flag = jdbcUtils.updateByPreparedStatement(sql, params); 
            System.out.println(flag); 
        } catch (SQLException e) { 
            // TODO Auto-generated catch block 
            e.printStackTrace(); 
        }
        jdbcUtils.releaseConn();
    }
    
    @Test
    public void update(){
    	 /*******************改*********************/  
        //将名字为李四的密码改了  
    	jdbcUtils.getConnection();  
        String sql = "update userinfo set pswd = ? where username = ? "; 
        List<Object> params = new ArrayList<Object>(); 
        params.add("lisi88888"); 
        params.add("李四"); 
        boolean flag;
		try {
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
			System.out.println(flag);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        jdbcUtils.releaseConn();
    }
    
    public void delete(){
    	  /*******************删*********************/  
        //删除名字为张三的记录  
    	jdbcUtils.getConnection();  
        String sql = "delete from userinfo where username = ?"; 
        List<Object> params = new ArrayList<Object>(); 
        params.add("小明"); 
        try {
			boolean flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        jdbcUtils.releaseConn();
    }
    
    public void query(){
    	/*******************查*********************/  
        //不利用反射查询多个记录  
        String sql2 = "select * from userinfo "; 
        List<Map<String, Object>> list;
		try {
			list = jdbcUtils.findModeResult(sql2, null);
			System.out.println(list);  
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		jdbcUtils.releaseConn();
    }
    
    public void fanshe(){
    	//利用反射查询 单条记录  
      String sql = "select * from userinfo where username = ? ";  
      List<Object> params = new ArrayList<Object>();  
      params.add("李四");  
      UserInfo userInfo;  
      try {  
          userInfo = jdbcUtils.findSimpleRefResult(sql, params, UserInfo.class);  
          System.out.print(userInfo);  
      } catch (Exception e) {  
          // TODO Auto-generated catch block  
          e.printStackTrace();  
      }  

    }
}
