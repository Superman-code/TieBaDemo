<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
	* {
	  box-sizing: border-box;
	}	
	body{
		margin: 0;
	}
	div{
		border: 1px solid #e7e7e7;
	}
	div.header span{
		font-size: 12px;
		color: #999;
		padding-left: 60px;
	}
	div.header img{
		width: 100%;
		display: block;
		height: 80px;
	}
	ul {
	    list-style-type: none;
	    margin: 0;
	    padding: 0;
	    overflow: hidden;
	}
	li {
	    float: right;
	}
	li a {
	    display: block;
	    color: white;
	    text-align: center;
	    padding: 14px 20px;
	    text-decoration: none;
	    background-color: #4CAF50;
	}
	/* 创建三个相等的列 */
	.column1 {
	  float: left;
	  width: 30%;
	  height: 590px;
	}
	.column1 ul{
		list-style-type: none;
	    margin: 0;
	    padding: 0;
	    overflow: hidden;
	}
	.column1 ul li{
		float: left;
	}
	.column1 ul li a {
	    display: block;
	    color: white;
	    text-align: center;
	    padding: 10px 20px;
	    text-decoration: none;
	    background-color: #4CAF50;
	}
	.column2 {
	  float: left;
	  width: 70%;
	  height: 590px;
	}
	.column2 ul{
		list-style-type: none;
	    margin: 0;
	    padding: 0;
	    overflow: hidden;
	}
	.column2 ul li{
		float: left;
	}
	.column2 ul li a {
	    display: block;
	    color: white;
	    text-align: center;
	    padding: 10px 20px;
	    text-decoration: none;
	    background-color: #4CAF50;
	}
	 
	/* 列后清除浮动 */
	.row:after {
	  content: "";
	  display: table;
	  clear: both;
	}
	 
	/* 响应式布局 - 小于 600 px 时改为上下布局 */
	@media screen and (max-width: 600px) {
	  .column {
	    width: 100%;
	  }
	}
	div.employee{
		margin-top: 50px;
		text-align: center;
	}
</style>
<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
</head>
<body>
	<div class="header">
		<div><span>企业日常事务管理系统，为企业内部通信提供更简便的服务</span></div>
		<div class="imgDiv"><img alt="图片" src="${pageContext.request.contextPath}/images/header.jpg"></div>
	</div>

	<nav class="navigator">
		<ul>
		  <li><a href="ExitServlet">退出</a></li>
		  <li><a href="register.jsp">注册身份</a></li>
		  <li><a href="statusRecognise.jsp">身份识别</a></li>
		  <li><a href="MyPublishServlet">我的发布</a></li>
		  <li><a href="MyCommentedServlet">我评论过的消息</a></li>
		  <li><a href="publishNewMsg.jsp">发布新消息</a></li>
		  <li><a href="GetMsgListServlet">消息列表</a></li>
		  <li><a href="index.jsp">首页</a></li>
		</ul>
	</nav>
	
</body>
</html>