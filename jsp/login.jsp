<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>学生管理系统</title>
    
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
<div class="myset">
<form action="login" method="post">
<br/><span style="font-size:36px;">LOGIN</span>
<table>
<tr><td><input type="text" name="logname" width="21"/></td></tr>
<tr><td></td></tr>
<tr><td><input type="password" name="password" width="21"/></td></tr>
<tr><td></td></tr>
<tr><td></td></tr>
<tr><td><input type="submit" value="login" name="login" style="font-size: 16px;"><span style="font-size:1px;">学生管理系统</span><input style="font-size: 16px;" type="reset" value="reset" name="reset"></td></tr>
</table>
</form>
</div>
  </body>
</html>
