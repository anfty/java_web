<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="tom.bean.Register"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>注册后信息显示</title>
    
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
    <jsp:useBean id="register" class="tom.bean.Register" scope="request"></jsp:useBean>
    <br/>注册成功
    <table>
    <tr><td>注册的会员名称：</td><td><jsp:getProperty property="logname" name="register"/></td></tr>
    <tr><td>注册的性别：</td><td><jsp:getProperty property="sex" name="register"/></td></tr>
    <tr><td>注册的会员年龄：</td><td><jsp:getProperty property="age" name="register"/></td></tr>
    <tr><td>注册的电子邮件：</td><td><jsp:getProperty property="email" name="register"/></td></tr>
    <tr><td>注册的联系电话：</td><td><jsp:getProperty property="phone" name="register"/></td></tr>
    </table>
    <table>
    <tr><td>您输入的个人简介：</td></tr>
    <tr><td><textarea rows="6" cols="30"><jsp:getProperty property="message" name="register"/></textarea></td></tr>
    </table>
    </center>
  </body>
</html>
