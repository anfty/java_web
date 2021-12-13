<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="tom.bean.Register"%>
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

<title>修改注册信息</title>

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
		<br />以下是<jsp:getProperty property="logname" name="register" />会员曾经注册的信息，您可以修改这些信息。
		<form action="updateRegisterMessage" method="post">
		<table>
		<tr><td>修改对象:</td><td><input type="text" name="aname" value='<jsp:getProperty property="logname" name="register"/>'/></td></tr>
		<tr><td>性别:<jsp:getProperty property="sex" name="register"/></td><td><input type="radio" value="男" name="newSex" checked="checked"/>男<input type="radio" value="女" name="newSex"/>女</td></tr>
		<tr><td>会员年龄:</td><td><input type="text" name="newAge" value='<jsp:getProperty property="age" name="register"/>'/></td></tr>
		<tr><td>电子邮件:</td><td><input type="text" name="newEmail" value='<jsp:getProperty property="email" name="register"/>'/></td></tr>
		<tr><td>联系电话:</td><td><input type="text" name="newPhone" value='<jsp:getProperty property="phone" name="register"/>'/></td></tr>
		<tr><td>输入您的个人简介:</td></tr>
		<tr><td><textarea rows="6" cols="30" name="newMessage"><jsp:getProperty property="message" name="register"/></textarea></td></tr>
		<tr><td><input type="submit" value="提交修改"/>&nbsp;&nbsp;<input type="reset" value="重置"/></td>
		</table>
		</form>
	</center>
</body>
</html>
