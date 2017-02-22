package com.glife.entity.service;

import com.glife.entiry.dao.UserInfoDao;

public class UserInfoService {
	
	private String msg;
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	private UserInfoDao dao = new UserInfoDao();
	
	public boolean checkUserLogin(String username, String passwd){
		boolean flag = dao.validatePasswd(username, passwd);
		this.msg = dao.getMsg();
		return  flag;
	}

}
