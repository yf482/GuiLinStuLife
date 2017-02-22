<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <title>登录界面</title>

    <script src="js/jquery-1.8.3.js"></script>

    <script src="js/login.js"></script>

    <link href="css/style.css" rel="stylesheet" type="text/css" />

  </head>
<html>
<!-- 
在情况一中：若在路径中以/开头，则这一/相当于http://localhost:8080/
1、login.html有个form表单有提交给servletA，那么action要填的路径：
绝对路径方式：action="/myapp/servlet/servletA"       ------http://localhost:8080/myapp/servlet/servletA
相对路径方式：action="servlet/servletA"                   ------http://localhost:8080/myapp/servlet/servletA
2、login.html有个<a>链接到index.jsp 那么
绝对路径方式:href="/myapp/index.jsp"                      ------http://localhost:8080/myapp/index.jsp
相对路径方式：action="index.jsp"                            ------http://localhost:8080/myapp/index.jsp
3、index.jsp中重定向到servletA
绝对路径方式:sendRedirect("/myapp/servlet/servletA");      ------http://localhost:8080/myapp/servlet/servletA
相对路径方式：sendRedirect("servlet/servletA");     ---http://localhost:8080/myapp/servlet/servletA
在情况二中：若在路径中以/开头，则这一/相当于http://localhost:8080/myapp/
1.servletA转发到servletB
绝对路径方式:request.getRequestDispatcher("/servlet/servletB").forward(request, response);
       --------http://localhost:8080/myapp/servlet/servletB
相对路径方式:request.getRequestDispatcher("servlet/servletB").forward(request, response);
       --------http://localhost:8080/myapp/servlet/servletB
       注意：
建议使用绝对路径，相对路径是相对于当前浏览器地址栏的路径（源地址）。
可能会出现：你在某个页面写了一个相对路径（目标路径），因为转发是不改变地址的，那么要是别人是通过转发到达你的这个页面的，那么地址栏的源地址就是不确定的，既然不确定你使用相对路径相对于这个不确定的路径就极有可能出错，所以建议使用绝对路径，这样可避免这种问题。
获得项目路径和绝对路径：
项目路径：String path=request.getContextPath();           ----                /myapp
String p=this.getServletContext().getRealPath("/");     -----   G:\environment\tomcat\webapps\myapp\

总结：
这里主要弄明白是指向外部的还内部的，外部时"/"就是代表主机路径，内部时"/"就是代表当前项目路径
 -->


<body>
<%=path %>
<%=basePath %>
    <div id="loginpanelwrap">
        <div class="loginheader">
            <div class="logintitle">登录</div>
        </div>
        <form id="lofin-form" action = "/GuiLinStuLife/servlet/LoginServlet" method="post">

            <div class="loginform">

                <div class="loginform_row">

                    <label>用户名:</label>

                    <input type="text" id="username" class="loginform_input" name="username" />

                </div>

                <div class="loginform_row">

                    <label>密码:</label>

                    <input type="password" id="password" class="loginform_input" name="password" />

                </div>

                <div class = "loginform_row">

                    <label>验证码:</label>

                    <input type = "text" id="validationCode" class="loginform_input_validationCode" name = "validationCode"/>

                    <img class = "validationCode_img" src="/GuiLinStuLife/servlet/ValidationCode">

                </div>

                <div class="loginform_row">

                    <span class = "returnInfo"></span>

                    <input type="submit" class="loginform_submit" value="登录" />

                </div>

                <div class="clear"></div>

            </div>

        </form>

    </div>
 <script type="text/javascript">
    	$(function(){

   $(".loginform_submit").click(function(){

       if(checkInput()) {

           $("form").action("/servlet/LoginServlet");

       }else{

           return false;

       }

   });

   $(".validationCode_img").click(function(){

       $(".validationCode_img").attr("src","/GuiLinStuLife/servlet/ValidationCode?"+Math.random());

   });

    function checkInput(){

        //判断用户名

        if($("input[name=username]").val() == null || $("input[name=username]").val() == ""){

            alert("用户名不能为空");

            $("input[name=username]").focus();

            return false;

        }

        //判断密码

        if($("input[name=password]").val() == null || $("input[name=password]").val() == ""){

            alert("密码不能为空");

            $("input[name=password]").focus();

            return false;

        }

        //判断验证码

        if($("input[name=validationCode]").val() == null || $("input[name=validationCode]").val() == ""){

            alert("验证码不能为空");

            $("input[name=validationCode]").focus();

            return false;

        }

        return true;

    }

});
</script>

</body>

</html>