package com.glife.front.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import com.glife.entity.service.UserInfoService;


import java.io.IOException;
import java.io.PrintWriter;

import java.io.OutputStream;

/**

* Created by zhang on 2014/9/13.

*/

public class LoginServlet extends HttpServlet{

    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    	 doPost(req,resp);

    }

    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      //是web容器生成的servlet代码中有out.write(""),这个和JSP中调用的 response.getOutputStream()产生冲突.即Servlet规范说明，
//    	不能既调用 response.getOutputStream()，又调用response.getWriter(),无论先调用哪一个，在调用第二个时候应会抛出 
//        OutputStream out = resp.getOutputStream();
//        PrintWriter write = resp.getWriter();
        
        String username = req.getParameter("username");

        String password = req.getParameter("password");

        String validationCode = req.getParameter("validationCode");

        HttpSession session = req.getSession();

        String validation_code = (String)session.getAttribute("validation_code");

        if(validationCode.equalsIgnoreCase(validation_code)){

            System.out.println("验证码正确");

        }else{

            System.out.println("验证码错误");

        }

//        ManageMysql mss = new ManageMysql();
        
        UserInfoService us = new UserInfoService();
        boolean loginFlag = us.checkUserLogin(username, password);

//        String result = mss.checkUser(username,password);

       /* if (result.equals("hasUserNameAndPasswordCorrect")) {

            System.out.println("用户名和密码均正确");

        } else if (result.equals("hasUserNameButPasswordInCorrect")) {

            System.out.println("用户名正确,密码不正确");

        } else if (result.equals("hasNoUserName")) {

            System.out.println("没有此用户");

        }*/
        
        if(loginFlag){
        	 //转发到result.jsp
            RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
            rd.forward(req, resp);
        }else{
        	//转发到result.jsp
            RequestDispatcher rd = req.getRequestDispatcher("/front/login/loginView.jsp");
            rd.forward(req, resp);
        }
        
     /*   ServletContext context = getServletContext();  
        RequestDispatcher dispatcher = context.getRequestDispatcher("/forwarddest");  
          
        // try to output something into response  
        PrintWriter out2 = resp.getWriter();  
        out2.println("Output from ForwardSourceServlet before forwarding request");  
        if (dispatcher != null){  
            dispatcher.forward(req, resp);  
        }  
        out2.println("Output from ForwardSourceServlet after forwarding request");  */
        
        
//        out.flush();
//        out.close();  

    }

}