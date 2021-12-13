<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="tom.bean.ShowResult" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>学生信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
<jsp:include page="head.html" flush="true"/>
<center class="myset">
<br/>显示学生信息
<jsp:useBean id="showResult" class="tom.bean.ShowResult" scope="request"></jsp:useBean>
<table width="800">
<tr><th>名字</th><th>性别</th><th>年龄</th><th>电话</th><th>电子邮件</th><th>简历</th><th>照片</th></tr>
<jsp:getProperty property="result" name="showResult"/>
</table>
</center>
  </body>
</html>
