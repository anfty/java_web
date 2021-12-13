<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>浏览会员页面</title>
    
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
<center class="myset">
<form action="showMember" method="post">
	<input type="submit" value="显示会员信息"/>
</form>
<form>
	<input type="button" onclick="location.href='http://localhost:8080/showAllMember.jsp'" value="分页显示学生信息"/>
</form>

<form action="findMember" method="post">
	<br/>输入要查找的会员名:<input type="text" name="logname"/>
	<input type="submit" value="显示"/>
</form>
</center>
  </body>
</html>
