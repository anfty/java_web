<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="tom.bean.MemberInformation" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'findMember.jsp' starting page</title>
    
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
<jsp:useBean id="memberInfo" class="tom.bean.MemberInformation" scope="request"></jsp:useBean>
<table>
<tr><img src='/upload/<jsp:getProperty property="pic" name="memberInfo"/>/<jsp:getProperty property="pic" name="memberInfo"/>' width="360" height="360"></tr>
<tr><td width="120"><jsp:getProperty property="logname" name="memberInfo"/></td>
<td width="180"><jsp:getProperty property="email" name="memberInfo"/></td></tr>
<tr>
<td width="180"><jsp:getProperty property="phone" name="memberInfo"/></td>
<td  width="120"><jsp:getProperty property="sex" name="memberInfo"/></td>
<td  width="120"><jsp:getProperty property="age" name="memberInfo"/></td>
</tr>
<tr><td><jsp:getProperty property="message" name="memberInfo"/></td></tr>
</table>
</center>
  </body>
</html>
