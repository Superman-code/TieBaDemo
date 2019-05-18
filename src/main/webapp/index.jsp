<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<title>首页</title>

</head>
<body>
	<div class="row">
	  <div class="column1">
	    <ul>
		  <li><a href="index.jsp">员工信息</a></li>
		</ul>
		<div class="employee" id="employee" style="border: 0px;">
			<c:choose>
				<c:when test="${empty sessionScope.employee}">
					<span style="color: red;">没有进行身份识别，请先进行身份识别！</span><br>
				</c:when>
				<c:otherwise>
					<p>员工编号：${employee.employeeID}</p>
					<p>员工姓名：${employee.employeeName}</p>
					<p>员工性别：${employee.employeeSex?'男':'女'}</p>
					<p>出生日期：${employee.employeeBirth}</p>
					<p>办公室电话：${employee.employeePhone}</p>
					<p>住址：${employee.employeePlace}</p>
					<p>管理层领导：${employee.lead?'是':'否'}</p>
				</c:otherwise>
			</c:choose>
		</div>
	  </div>
	  
	  <div class="column2">
	     <ul>
		  <li><a href="index.jsp">最新消息</a></li>
		 </ul>
		 <h3 style="color: lightblue;text-align: center;">最新消息只会展示最近的五条消息，若想查看全部消息，点击消息列表即可</h3>
		 <c:choose>
		 	<c:when test="${empty messages}">
		 		<p style="color: red;text-align: center;margin-top: 20px;">请在身份识别后再次查看最新消息</p>
		 	</c:when>
		 	<c:otherwise>
		 		<dl>
					<c:forEach items="${messages}" var="message">
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
				<p style="text-align: right;color: lightgreen;">假如您想删除账号|修改密码|修改加入时间|删除、修改发布的留言及评论。请与管理员联系，敬请谅解！</p>
		 	</c:otherwise>
		 </c:choose>
	  </div>
	</div>
	
	<script type="text/javascript">
		function updateName(){
			var employeeNamePara = $("div#employee p:eq(1)");
			employeeNamePara.one("click",function(){
				$(this).html("员工姓名： ");
			
				var input = $("<input type='text'>");
				var span_error = $("<span id='error'></span>");
				var br = $("<br>");
				var submit = $("<a>确认修改</a>");
				input.css({
					"display":"block",
					"margin":"5px auto 0px auto"
				});
				submit.css({
					"cursor":"pointer",
					"color":"blue",
					"margin-right":"10px",
					"display":"inline-block"
				});
				var cancel = $("<a>取消修改</a>");
				cancel.css({
					"cursor":"pointer",
					"color":"blue",
					"margin-left":"10px",
					"display":"inline-block"
				});
				$(this).after(input,span_error,br,submit,cancel);
				input.keyup(function(){
					var regExp = new RegExp("^\\S{2,20}$");
					if(regExp.test(input.val())){
						span_error.html("√");
						span_error.css("color","green");
					} else {
						span_error.html("只能是非空白字符字符且长度为2-20");
						span_error.css("color","red");
					}
				});
				submit.click(function(){
					if($("span#error").html() == "√"){
						$(this).attr("href", "UpdateEmployeeServlet?employeeID=${employee.employeeID}&employeeName="+input.val());
					} else {
						$(this).attr("href", "#");
					}
				});
				cancel.click(function(){
					$(this).attr("href", "index.jsp");
				});
				$(this).click();
			});
		}
		$(function(){
			updateName();
			updateSex();
			updateBirth();
			updatePhone();
			updatePlace();
			updateLead();
		});
		function updateSex(){
			var employeeSexPara = $("div#employee p:eq(2)");
			employeeSexPara.one("click",function(){
				$(this).html("员工性别： ");
				var radio1 = $("<input type='radio' checked='checked' value='true' name='employeeSex'>");
				var radio2 = $("<input type='radio' value='false' name='employeeSex'>");
				var label1 = $("<label>男</label>"); 
				var label2 = $("<label>女</label>");
				var br = $("<br>");
				var submit = $("<a>确认修改</a>");
				submit.css({
					"cursor":"pointer",
					"color":"blue",
					"margin-right":"10px",
					"display":"inline-block"
				});
				var cancel = $("<a>取消修改</a>");
				cancel.css({
					"cursor":"pointer",
					"color":"blue",
					"margin-left":"10px",
					"display":"inline-block"
				});
				$(this).after(radio1,label1,radio2,label2,br,submit,cancel);
				submit.click(function(){
					$(this).attr("href", "UpdateEmployeeServlet?employeeID=${employee.employeeID}&employeeSex="+$("input[type='radio']:checked").val());
				});
				cancel.click(function(){
					$(this).attr("href", "index.jsp");
				});
			});
		}
		function updateBirth(){
			var employeeBirthPara = $("div#employee p:eq(3)");
			employeeBirthPara.one("click",function(){
				$(this).html("出生日期： ");
				
				var input = $("<input type='text'>");
				var span_error = $("<span id='error'></span>");
				var br = $("<br>");
				var submit = $("<a>确认修改</a>");
				input.css({
					"display":"block",
					"margin":"5px auto 0px auto"
				});
				submit.css({
					"cursor":"pointer",
					"color":"blue",
					"margin-right":"10px",
					"display":"inline-block"
				});
				var cancel = $("<a>取消修改</a>");
				cancel.css({
					"cursor":"pointer",
					"color":"blue",
					"margin-left":"10px",
					"display":"inline-block"
				});
				$(this).after(input,span_error,br,submit,cancel);
				input.keyup(function(){
					var regExp = new RegExp("^[0-9]{4}\\-[0-9]{2}\\-[0-9]{2}$");
					if(regExp.test(input.val())){
						span_error.html("√");
						span_error.css("color","green");
					} else {
						span_error.html("格式为yyyy-mm-dd");
						span_error.css("color","red");
					}
				});
				submit.click(function(){
					if($("span#error").html() == "√"){
						$(this).attr("href", "UpdateEmployeeServlet?employeeID=${employee.employeeID}&employeeBirth="+input.val());
					} else {
						$(this).attr("href", "#");
					}
				});
				cancel.click(function(){
					$(this).attr("href", "index.jsp");
				});
			});
		}
		function updatePhone(){
			var employeePhonePara = $("div#employee p:eq(4)");
			employeePhonePara.one("click",function(){
				$(this).html("办公室电话： ");
				
				var input = $("<input type='text'>");
				var span_error = $("<span id='error'></span>");
				var br = $("<br>");
				var submit = $("<a>确认修改</a>");
				input.css({
					"display":"block",
					"margin":"5px auto 0px auto"
				});
				submit.css({
					"cursor":"pointer",
					"color":"blue",
					"margin-right":"10px",
					"display":"inline-block"
				});
				var cancel = $("<a>取消修改</a>");
				cancel.css({
					"cursor":"pointer",
					"color":"blue",
					"margin-left":"10px",
					"display":"inline-block"
				});
				$(this).after(input,span_error,br,submit,cancel);
				input.keyup(function(){
					var regExp = new RegExp("^[0-9]{11}$");
					if(regExp.test(input.val())){
						span_error.html("√");
						span_error.css("color","green");
					} else {
						span_error.html("只能是11位数字");
						span_error.css("color","red");
					}
				});
				submit.click(function(){
					if($("span#error").html() == "√"){
						$(this).attr("href", "UpdateEmployeeServlet?employeeID=${employee.employeeID}&employeePhone="+input.val());
					} else {
						$(this).attr("href", "#");
					}
				});
				cancel.click(function(){
					$(this).attr("href", "index.jsp");
				});
			});
		}
		function updatePlace(){
			var employeePlacePara = $("div#employee p:eq(5)");
			employeePlacePara.one("click",function(){
				$(this).html("住址： ");
				
				var input = $("<input type='text'>");
				var span_error = $("<span id='error'></span>");
				var br = $("<br>");
				var submit = $("<a>确认修改</a>");
				input.css({
					"display":"block",
					"margin":"5px auto 0px auto"
				});
				submit.css({
					"cursor":"pointer",
					"color":"blue",
					"margin-right":"10px",
					"display":"inline-block"
				});
				var cancel = $("<a>取消修改</a>");
				cancel.css({
					"cursor":"pointer",
					"color":"blue",
					"margin-left":"10px",
					"display":"inline-block"
				});
				$(this).after(input,span_error,br,submit,cancel);
				input.keyup(function(){
					var regExp = new RegExp("^\\S{5,30}$");
					if(regExp.test(input.val())){
						span_error.html("√");
						span_error.css("color","green");
					} else {
						span_error.html("由5-30非空白字符构成");
						span_error.css("color","red");
					}
				});
				submit.click(function(){
					if($("span#error").html() == "√"){
						$(this).attr("href", "UpdateEmployeeServlet?employeeID=${employee.employeeID}&employeePlace="+input.val());
					} else {
						$(this).attr("href", "#");
					}
				});
				cancel.click(function(){
					$(this).attr("href", "index.jsp");
				});
			});
		}
		function updateLead(){
			var employeeLeadPara = $("div#employee p:eq(6)");
			employeeLeadPara.one("click",function(){
				$(this).html("管理层领导： ");
				var radio1 = $("<input type='radio' checked='checked' value='true' name='isLead'>");
				var radio2 = $("<input type='radio' value='false' name='isLead'>");
				var label1 = $("<label>是</label>"); 
				var label2 = $("<label>否</label>");
				var br = $("<br>");
				var submit = $("<a>确认修改</a>");
				submit.css({
					"cursor":"pointer",
					"color":"blue",
					"margin-right":"10px",
					"display":"inline-block"
				});
				var cancel = $("<a>取消修改</a>");
				cancel.css({
					"cursor":"pointer",
					"color":"blue",
					"margin-left":"10px",
					"display":"inline-block"
				});
				$(this).after(radio1,label1,radio2,label2,br,submit,cancel);
				submit.click(function(){
					$(this).attr("href", "UpdateEmployeeServlet?employeeID=${employee.employeeID}&isLead="+$("input[type='radio']:checked").val());
				});
				cancel.click(function(){
					$(this).attr("href", "index.jsp");
				});
			});
		}
	</script>
</body>
</html>