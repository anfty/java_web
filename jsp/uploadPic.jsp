<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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

<title>上传图片页面</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
 <style>
    .myset{
      text-align: center;
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);
    }
  </style>
</head>

<body>
<jsp:include page="head.html" flush="true"/>
<div class="myset">
	<center class="myset">
		<p>存放路径/upload/
		<form action="upload" method="post" enctype="multipart/form-data">
			<input type="file" name="fileName" size="40" /> <br />
			<input type="submit" value="上传" name="upload" />
		</form>
	</center>
</div>
</body>
</html>
