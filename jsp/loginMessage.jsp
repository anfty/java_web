<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="tom.bean.Login" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录后详细信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
<jsp:include page="head.html" flush="true"/>
<center  class="myset">
    <jsp:useBean id="login" class="tom.bean.Login" scope="session"></jsp:useBean>
    <br/><jsp:getProperty property="logname" name="login"/>登录成功
    <%-- <br/>登录密码为:<jsp:getProperty property="password" name="login"/> --%>
    </center>
  </body>
</html>
