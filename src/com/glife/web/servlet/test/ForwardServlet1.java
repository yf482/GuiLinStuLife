package com.glife.web.servlet.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForwardServlet1 extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.setHeader("Refresh", "1");
		System.out.println("转发前");
		response.getWriter().write("forward before");//转发前会清空响应对象中的正文内容
		request.getRequestDispatcher("/servlet/ForwardServlet2").forward(request, response);//转发
		System.out.println("转发后");
		response.getWriter().write("forward after");
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

}
