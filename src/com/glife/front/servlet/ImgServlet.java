package com.glife.front.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImgServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
				//文件在哪儿？以不变应万变
				//利用ServletContext读取：1 2 3 文件
				//3.可以读取应用中任何位置上的资源。使用限制：只能在web应用中用
				ServletContext sc = getServletContext();
				String realPath = sc.getRealPath("/WEB-INF/classes/霉女.jpg");//  文件存放的真实绝对路径
//				System.out.println(realPath);
				//构建文件的输入流
				InputStream in = new FileInputStream(realPath);
				//告知客户端以下载的方式打开：Content-Disposition=attachment;filename=27.jpg
				
				//获取要下载的文件名
				
				String filename  = realPath.substring(realPath.lastIndexOf(File.separator)+1);
				String fileNameEd = URLEncoder.encode(filename,"UTF-8");
				//浏览器端图片展示
//				response.setContentType("text/html");
				response.setContentType("image/jpeg");
//				response.setHeader("Content-Type", "image/jpeg");
				//附件下载
//				response.setHeader("Content-Type", "application/octet-stream");
//				response.setHeader("Content-Disposition", "attachment;filename=" + fileNameEd);//中文属于不安全的字符，需要进行URL编码
				
				//用response的字节流进行输出
				OutputStream out = response.getOutputStream();
				
				int len = -1;
				byte b[] = new byte[1024];
				while((len=in.read(b))!=-1){
					out.write(b, 0, len);
				}
				in.close();
				out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();*/
	}

}
