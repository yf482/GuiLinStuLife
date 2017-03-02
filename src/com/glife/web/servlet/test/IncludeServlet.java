package com.glife.web.servlet.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IncludeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//包含：源
		//只会包含目标的正文，头部信息包含前清空
//		response.getWriter().write("11111");
//		request.getRequestDispatcher("/servlet/ForwardServlet2").include(request, response);
		
		response.setIntHeader("Refresh", 1);
		response.getWriter().write("22222");
	
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
