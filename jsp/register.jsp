<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>学生注册页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script>
    function check(){
    　　var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"); //正则表达式
    　　var obj = document.getElementById("email"); //要验证的对象
    　　if(obj.value === ""){ //输入不能为空
    　　　　alert("输入不能为空!");
    　　　　return false;
    　　}else if(!reg.test(obj.value)){ //正则验证不通过，格式不对
    　　　　alert("验证不通过!");
    　　　　return false;
    　　}else{
    　　　　alert("通过！");
    　　　　return true;
    　　}
    }
</script>
  </head>
  
  <body>
<jsp:include page="head.html" flush="true"/>
<center class="myset">
  <form action="register" method="post">
    <br/>输入你的信息，*号项必须填写
    <table>
	<tr><td>会员名称：</td><td><input type="text"  name="logname"/>*</td></tr>
    <tr><td>设置密码：</td><td><input type="password"  name="password"/>*</td></tr>
    <tr><td>确认密码：</td><td><input type="password"  name="password1"/></td></tr>
    <tr><td>性别：</td><td><input name="sex" type="radio" checked="checked" align="middle" value="男"/>男
     <input type="radio" name="sex" align="middle" value="女"/>女</td></tr>
    <tr><td>会员年龄：</td><td><input type="text"  name="age"/></td></tr>
    <tr><td>电子邮件：</td><td><input type="text"  name="email"/></td></tr>
    <tr><td>联系电话：</td><td><input type="text"  name="phone"/></tr></td>
    </table>
    <table>
    <tr><td>输入你的个人简历：</td></tr>
    <tr><td><textarea name="message" rows="6" cols="30"></textarea></td></tr>
    <tr><td><input onclick="check();" type="submit" name="g" value="提交"/></td></tr>
    </table>
  </form>
</center>
  </body>
</html>
