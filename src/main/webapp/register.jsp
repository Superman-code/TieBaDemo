<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册页面</title>
</head>
<body>
	<div align="center">
		<h2>注册页面</h2>
		<form action="RegisterServlet" method="post">
			<font color="red">${requestScope.error}</font>
			<p>员工编号：<input type="text" onkeyup="checkID(this)" name="employeeID" id="employeeID" value="${employeeID}"><span id="employeeID_error"></span></p>
			<p>员工姓名：<input type="text" onkeyup="checkName(this)" name="employeeName" id="employeeName" value="${employeeName}"><span id="employeeName_error"></span></p>
			<p>
				员工性别：
				<input type="radio" checked="checked" id="employeeSex" name="employeeSex" value="true">男
				<input type="radio" id="employeeSex" name="employeeSex" value="false">女
				<span id="employeeSex_error"></span>
			</p>
			<p>出生日期：<input type="text" onkeyup="checkBirth(this)" id="employeeBirth" name="employeeBirth" placeholder="yyyy-mm-dd"  value="${employeeBirth}"><span id="employeeBirth_error"></span></p>
			<p>办公室电话：<input type="text" onkeyup="checkPhone(this)" id="employeePhone" name="employeePhone" placeholder="手机号码/座机号码" value="${employeePhone}"><span id="employeePhone_error"></span></p>
			<p>住址：<input type="text" onkeyup="checkPlace(this)" id="employeePlace" name="employeePlace" value="${employeePlace}"><span id="employeePlace_error"></span></p>
			<p>入职时间：<input type="text" onkeyup="checkJoinTime(this)" placeholder="yyyy-mm-dd" id="joinTime" name="joinTime" value="${joinTime}"><span id="joinTime_error"></span></p>
			<p>密码：<input type="password" onkeyup="checkPassword(this)" id="password" name="password" value="${password}"><span id="password_error"></span></p>
			<p>
				是否为领导：
				<input type="radio" id="lead" name="lead" value="true">是
				<input type="radio" checked="checked" id="lead" name="lead" value="false">否
				<span id="lead_error"></span>
			</p>
			<input type="submit" value="提交" onclick="return handle()">
			<input type="reset" value="重置">
		</form>
	</div>
	
	<script type="text/javascript">
		function getValue(id){
			return document.getElementById(id).value;
		}
		function getElement(id){
			return document.getElementById(id);
		}
		function handle(){
			var employeeID = getElement("employeeID");
			var employeeName = getElement("employeeName");
			var employeeBirth = getElement("employeeBirth");
			var employeePhone = getElement("employeePhone");
			var employeePlace = getElement("employeePlace");
			var joinTime = getElement("joinTime");
			var password = getElement("password");
			
			return checkID(employeeID) && checkName(employeeName) && checkBirth(employeeBirth) && checkPhone(employeePhone) && checkPlace(employeePlace) && checkJoinTime(joinTime) && checkPassword(password);
		}
		function checkID(employeeID){
			var regExp = new RegExp("^[1-9]{6,11}$");
			if(regExp.test(employeeID.value)){
				getElement("employeeID_error").innerHTML = "√";
				getElement("employeeID_error").style.color = "green";
				return true;
			} else {
				getElement("employeeID_error").innerHTML = "只能是数字[1-9]且长度为6-11";
				getElement("employeeID_error").style.color = "red";
				return false;
			}
		}
		function checkName(employeeName){
			var regExp = new RegExp("^\\S{2,20}$");
			if(regExp.test(employeeName.value)){
				getElement("employeeName_error").innerHTML = "√";
				getElement("employeeName_error").style.color = "green";
				return true;
			} else {
				getElement("employeeName_error").innerHTML = "只能是非空白字符字符且长度为2-20";
				getElement("employeeName_error").style.color = "red";
				return false;
			}
		}
		function checkBirth(employeeBirth){
			var regExp = new RegExp("^[0-9]{4}\\-[0-9]{2}\\-[0-9]{2}$");
			if(regExp.test(employeeBirth.value)){
				getElement("employeeBirth_error").innerHTML = "√";
				getElement("employeeBirth_error").style.color = "green";
				return true;
			} else {
				getElement("employeeBirth_error").innerHTML = "格式为yyyy-mm-dd";
				getElement("employeeBirth_error").style.color = "red";
				return false;
			}
		}
		function checkPhone(employeePhone){
			var regExp = new RegExp("^[0-9]{11}$");
			if(regExp.test(employeePhone.value)){
				getElement("employeePhone_error").innerHTML = "√";
				getElement("employeePhone_error").style.color = "green";
				return true;
			} else {
				getElement("employeePhone_error").innerHTML = "只能是11位数字";
				getElement("employeePhone_error").style.color = "red";
				return false;
			}
		}
		function checkPlace(employeePlace){
			var regExp = new RegExp("^\\S{5,30}$");
			if(regExp.test(employeePlace.value)){
				getElement("employeePlace_error").innerHTML = "√";
				getElement("employeePlace_error").style.color = "green";
				return true;
			} else {
				getElement("employeePlace_error").innerHTML = "由5-30非空白字符构成";
				getElement("employeePlace_error").style.color = "red";
				return false;
			}
		}
		function checkJoinTime(joinTime){
			var regExp = new RegExp("^[0-9]{4}\\-[0-9]{2}\\-[0-9]{2}$");
			if(regExp.test(joinTime.value)){
				getElement("joinTime_error").innerHTML = "√";
				getElement("joinTime_error").style.color = "green";
				return true;
			} else {
				getElement("joinTime_error").innerHTML = "格式为yyyy-mm-dd";
				getElement("joinTime_error").style.color = "red";
				return false;
			}
		}
		function checkPassword(password){
			var regExp = new RegExp("^\\S{6,10}$");
			if(regExp.test(password.value)){
				getElement("password_error").innerHTML = "√";
				getElement("password_error").style.color = "green";
				return true;
			} else {
				getElement("password_error").innerHTML = "由6-10非空白字符构成";
				getElement("password_error").style.color = "red";
				return false;
			}
		}
	</script>
</body>
</html>