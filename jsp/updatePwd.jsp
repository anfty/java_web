<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改密码页面</title>
    
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
  <!--<![endif]-->
</object>
<form action="updatePwd" method="post">
<p>输入您的密码:
<table>
<tr><td>当前密码:</td><td><input type="password" name="password"/></td></tr>
<tr><td>新密码:</td><td><input type="password" name="newpassword1"/></td></tr>
<tr><td>确认密码:</td><td><input type="password" name="newpassword2"/></td></tr>
</table>
<table>
<tr><td><input type="submit" value="提交"/></td><td><input type="reset" value="重置"/></td></tr>
</table>
</form>
</center>
  </body>
</html>
