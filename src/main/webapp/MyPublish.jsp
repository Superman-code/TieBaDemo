<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的发布</title>
</head>
<body>
	<c:choose>
		<c:when test="${empty employee}">
			<h2 style="color: lightblue;text-align: center;margin-top: 20px;">您还没有进行身份识别，无法查看自己发布的消息</h2>
		</c:when>
		<c:otherwise>
			<dl>
				<c:forEach items="${requestScope.messages}" var="message">
					<dt>
						<a style="text-decoration: none;" href="GetMsgServlet?messageID=${message.messageID}">>>${message.messageTitle}</a>
					</dt>
					<dd>
						<div align="right" style="border: 0px;">
							发布人ID：${message.employeeID}&nbsp;&nbsp;&nbsp;
							发布时间：${message.publishTime}
						</div>
					</dd>
				</c:forEach>
			</dl>
			<div align="center" style="border: 0px; margin-bottom: 20px; margin-top: 20px;">
				<c:choose>
					<c:when test="${page.prePage}">
						<a href="MyPublishServlet?currentPage=1">首页</a>|
						<a href="MyPublishServlet?currentPage=${page.currentPage-1}">上一页</a>
					</c:when>
					<c:otherwise>
						首页|上一页
					</c:otherwise>
				</c:choose>
				&nbsp;&nbsp;&nbsp;
				<c:choose>
					<c:when test="${page.nextPage}">
						<a href="MyPublishServlet?currentPage=${page.currentPage+1}">下一页</a>|
						<a href="MyPublishServlet?currentPage=${page.totalPage}">最后一页</a>
					</c:when>
					<c:otherwise>
						下一页|最后一页
					</c:otherwise>
				</c:choose>
				&nbsp;&nbsp;&nbsp;
				当前为第${page.currentPage}页，共${page.totalPage}页
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>