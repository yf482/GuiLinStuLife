package com.glife.entiry.dao.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.glife.entiry.dao.UserInfoDao;
import com.glife.entity.UserInfo;

public class UserInfoDaoTest {
	
	@Test
	public void queryUserByNameTest(){
		UserInfoDao dao = new UserInfoDao();
		List<UserInfo> list = null;
		String name = "yunfeng";
		list = dao.queryUserByName(name);
		System.out.println(list.toString());
	}
	
	
	@Test
	public void validatePasswdTest(){
		UserInfoDao dao = new UserInfoDao();
		boolean flag = dao.validatePasswd("yunfeng", "123");
		System.out.println("flag:" + flag);
		System.out.println("msg:" + dao.getMsg());
		boolean flag2 = dao.validatePasswd("yunfeng2", "123");
		System.out.println("flag2:" + flag2);
		System.out.println("msg:" + dao.getMsg());
	}

}
