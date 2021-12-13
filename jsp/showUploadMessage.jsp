<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="tom.bean.UploadPic" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>图片效果</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
<jsp:include page="head.html" flush="true"/>  
	<center class="myset">
	<jsp:useBean id="upload" class="tom.bean.UploadPic" scope="request"></jsp:useBean>
	<br/><jsp:getProperty property="backNews" name="upload"/>
	<br/>上传的文件名字:<jsp:getProperty property="fileName" name="upload"/>
		保存后的文件名字:<jsp:getProperty property="savedFileName" name="upload"/>
		<br/>图像效果为:<img src="/upload/<jsp:getProperty name="upload" property="savedFileName"/>/<jsp:getProperty name="upload" property="savedFileName"/>" width="100" height="100"/>
	</center>
  </body>
</html>
