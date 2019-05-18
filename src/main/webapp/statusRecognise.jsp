<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>身份识别</title>
</head>
<body>
	<div id="shenfenshibie" style="text-align: center;height: 300px;">
		<h2>身份识别</h2>
		<font color="red">${requestScope.error }</font>
		<form action="StatusRecogniseServlet" method="post">
			<input type="hidden" name="everyPage" value="5">
			<p>
				员工编号：<input type="text" name="employeeID">
			</p>
			<p>
				密码：<input type="password" name="password">
			</p>
			<p>
				<input type="submit" value="提交">
				<input type="reset" value="重置">
			</p>
		</form>
	</div>
</body>
</html>