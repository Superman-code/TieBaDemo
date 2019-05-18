<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>发布新消息</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
</head>
<body>
	<div id="messageBox">
		<c:choose>
			<c:when test="${empty employee}">
				<h2 style="color: lightblue;text-align: center;margin-top: 20px;">您还没有进行身份识别，无法发布新消息</h2>
			</c:when>
			<c:otherwise>
				<p><font color="red">${requestScope.error}</font></p>
				<form action="MsgPublishServlet" method="post">
					<p>消息标题：<input type="text" name="title" size="50"></p>
					<p>消息内容：</p>
					<p>
						<textarea id="editor" name="content"></textarea>
					</p>
					<p align="center">
						<input type="submit" value="提交">
						<input type="reset" value="重置">
					</p>
				</form>
			</c:otherwise>
		</c:choose>
		
		
		<script type="text/javascript">
			CKEDITOR.replace('editor');
		</script>
	</div>
</body>
</html>