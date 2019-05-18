<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>留言内容</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
</head>
<body>
	<h2 align="center">${message.messageTitle}</h2>
	<p>${message.messageContent}</p>
	<div align="right" style="border: 0px;">
		发布人ID：${message.employeeID}&nbsp;&nbsp;&nbsp;
		发布人名字：${employee.employeeName}&nbsp;&nbsp;&nbsp;
		发布时间：${message.publishTime}
	</div>
	<div style="border: 0px;">
		<h3>评论区：</h3>
		<c:forEach items="${requestScope.replys}" var="reply">
			<div style="border: 0px;">
				<p>${reply.replyContent}</p>
				<div align="right" style="border: 0px;">
					<!-- el表达式对象取出来的值类型不会自动变成String，并且还是类中原始的属性！！！ -->
					回复人ID：${reply.employeeID}&nbsp;&nbsp;&nbsp;
					<!-- 为了将回复人的名字显示在前台，在转发时将回复人的ID和Name做成
					一个map，并在前台通过${replyEmployeeNameMap[reply.employeeID]}
					动态将其取出 -->
					回复人名字：${replyEmployeeNameMap[reply.employeeID]}&nbsp;&nbsp;&nbsp;
					回复时间：${reply.replyTime}
				</div>
			</div>
		</c:forEach>
	</div>
	<div align="center" style="border: 0px; margin-top: 20px; margin-bottom: 20px">
		第
		<c:forEach begin="1" end="${page.totalPage}" varStatus="status">
			<a href="GetMsgServlet?currentPage=${status.index}&messageID=${message.messageID}">${status.index}</a>
			&nbsp;&nbsp;
		</c:forEach>
		页
	</div>
	<div align="left">
		<h3>回复</h3>
		<font color="red">${error}</font>
		<form action="CommitReplyServlet" method="post">
			<input hidden="true" value="${message.messageID}" name="messageID">
			<textarea rows="30" cols="50" id="editor" name="replyContent"></textarea>
			<div style="text-align: center; border: 0px;">
				<input type="submit" value="提交">
				<input type="reset" value="重置">
			</div>
		</form>
	</div>
	
	<script type="text/javascript">
		CKEDITOR.replace("editor");
	</script>
</body>
</html>