package com.glife.web.servlet.test;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.glife.entity.User;

public class RequestServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");//设置请求正文的编码。只对POST有效
		String method = request.getMethod();//请求方式
		String uri = request.getRequestURI();//    /day08_01_request/servlet/RequestDemo1
		String url = request.getRequestURL().toString();//  http://localhost:8080/day08_01_request/servlet/RequestDemo1
		String protocal = request.getProtocol();//客户端使用的协议
		
		String remoteIp = request.getRemoteAddr();//来访者的ip
		int port = request.getRemotePort();// 随机的
		String queryString = request.getQueryString();//GET方式：/day08_01_request/servlet/RequestDemo1?username=abc&password=123
		//username=abc&password=123
		String username = request.getParameter("name");//是二进制数据  %E6%9D%8E%E5%9B%9B
		System.out.println(method);
		System.out.println(uri);
		System.out.println(url);
		System.out.println(protocal);
		System.out.println(remoteIp);
		System.out.println(port);
		System.out.println(queryString);
		Enumeration<String> e = request.getHeaderNames();
	
//		test1(request);
//		test2(request);
//		test3(request);
		test8(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
	
	//得到所有的消息头
		private void test3(HttpServletRequest request) {
			Enumeration<String> e = request.getHeaderNames();
			while(e.hasMoreElements()){
				String headerName = e.nextElement();//透明称
				Enumeration<String> headerValues = request.getHeaders(headerName);
				while(headerValues.hasMoreElements()){
					System.out.println(headerName+":"+headerValues.nextElement());
				}
			}
			
		}
		//http协议是允许头有重复的情况，多个重名的头
		private void test2(HttpServletRequest request) {
			Enumeration<String> e = request.getHeaders("Accept-Encoding");//得到的头值
			while(e.hasMoreElements()){
				String headerValue = e.nextElement();
				System.out.println(headerValue);
			}
			
		}
		//得到指定请求消息头的值。如果没有该头，返回null
		private void test1(HttpServletRequest request) {
			String supportEncoding = request.getHeader("Accept-Encoding");
			if(supportEncoding.contains("gzip")){
				System.out.println("支持gzip压缩");
			}else{
				System.out.println("不支持gzip压缩");
			}
		}
		
		//终极解决方案：借助BeanUtil框架
		private void test8(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			User user = new User();
			System.out.println("封装前："+user);
			try {
				BeanUtils.populate(user, request.getParameterMap());
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("封装后："+user);
		}
		//getParameterMap获取参数:封装到JavaBean中
		private void test7(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			//key:请求参数名 value:请求参数值数组
			Map<String,String[]> map = request.getParameterMap();
			User user = new User();
			System.out.println("封装前："+user);
			for(Map.Entry<String, String[]> me:map.entrySet()){
				String paramName = me.getKey();//参数名称
				String paramValues[] = me.getValue();//参数值
				try {
					PropertyDescriptor pd = new PropertyDescriptor(paramName, User.class);
					Method m = pd.getWriteMethod();//setter方法
					if(paramValues.length>1){
						m.invoke(user, (Object)paramValues);//参考补充视频：反射main方法
					}else{
						m.invoke(user, paramValues);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			System.out.println("封装后："+user);
		}
		//getParameterMap获取参数
		private void test6(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			//key:请求参数名 value:请求参数值数组
			Map<String,String[]> map = request.getParameterMap();
			for(Map.Entry<String, String[]> me:map.entrySet()){
				System.out.println(me.getKey()+"="+Arrays.asList(me.getValue()));
			}
		}
		
		//把请求参数的值封装到JavaBean中
		//约定优于编码：表单的输入域的name取值和JavaBean中的属性（getter和setter方法）保持一致
		private void test5(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			
			Enumeration<String> e = request.getParameterNames();//参数名
			User user = new User();
			System.out.println("封装前："+user);
			while(e.hasMoreElements()){
				String paramName = e.nextElement();//即是JavaBean中的属性名称
				String paramValue = request.getParameter(paramName);
//				setUsername(paramValue); setPassword(paramValue);
				//JavaBean的内省
				try {
					PropertyDescriptor pd = new PropertyDescriptor(paramName, User.class);
					Method m = pd.getWriteMethod();//setter方法
					m.invoke(user, paramValue);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			System.out.println("封装后："+user);
		}	
//		private void test4(HttpServletRequest request, HttpServletResponse response)
//				throws ServletException, IOException {
//			User user = new User();
//			System.out.println("封装前："+user);
//			String username = request.getParameter("username");
//			String password = request.getParameter("password");
//			String gender = request.getParameter("gender");
//			user.setUsername(username);
//			user.setPassword(password);
//			user.setGender(gender);
//			System.out.println("封装后："+user);
//		}	
		//以下内容只用简单的程序开发
		
		//获取所有的请求参数名和值
		private void test33(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			Enumeration<String> e = request.getParameterNames();//参数名
			while(e.hasMoreElements()){
				String paramName = e.nextElement();
				String values [] = request.getParameterValues(paramName);
				System.out.println(paramName+"="+Arrays.asList(values));
			}
		}
		//获取重名请求参数的值
		private void test22(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String username = request.getParameter("username");//不区分get还是post的
			String passwords[] = request.getParameterValues("password");//获取重名的请求参数值
			System.out.println(username+":"+Arrays.asList(passwords));
		}
		//获取单一的请求参数的值(用户所有的输入都是String)
		private void test11(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String username = request.getParameter("username");//不区分get还是post的
			String password = request.getParameter("password");
			System.out.println(username+":"+password);
		}

}
