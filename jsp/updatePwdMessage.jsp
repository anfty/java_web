<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="tom.bean.UpdatePassword"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>修改密码后信息显示</title>

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
		<jsp:useBean id="password" class="tom.bean.UpdatePassword"
			scope="request"></jsp:useBean>
			<jsp:getProperty property="backNews" name="password"/>
			<br/>您的新密码为：<jsp:getProperty property="newPassword1" name="password"/>
			<br/>您的旧密码为:<jsp:getProperty property="password" name="password"/>
	</center>
</body>
</html>
