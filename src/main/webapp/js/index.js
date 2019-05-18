/*var form = document.createElement("form");
var input = document.createElement("input");
var span_error = document.createElement("span");
var submit = document.createElement("input");

form.method = "post";
form.action = "UpdateEmployeeServlet";

input.type = "text";
input.name = "employeeName";
input.onkeyup = function(){
	var regExp = new RegExp("^\\S{2,20}$");
	if(regExp.test(input.value)){
		span_error.innerHTML = "√";
		span_error.style.color = "green";
		return true;
	} else {
		span_error.innerHTML = "只能是非空白字符字符且长度为2-20";
		span_error.style.color = "red";
		return false;
	}
};

submit.type = "submit";
submit.value = "确认修改";

form.onsubmit = function(){
	var regExp = new RegExp("^\\S{2,20}$");
	return regExp.test(input.value);
};

form.appendChild(input);
form.appendChild(span_error);
form.appendChild(submit);
employeeName.appendChild(form);
input.focus();

JSP是由服务端执行的，EL表达式自然也由服务端解析执行，因此如果EL所在的脚本
在JSP页面内，它是可以获取到值的，这个值在服务器端返回到浏览器端时已经解析
完毕，浏览器端只是呈现而已，但是如果在单独的JS文件中写EL，会怎么样呢？这个
时候是无法获取的，因为javascript是客户端执行，单独的JS文件不在服务器的解析
执行之中，EL是不起任何作用的，这个时候它就等同于普通的字符串

不能使用表单，原因：
必须将表单添加到body下，才能进行表单提交，但是添加到body下面就会导致布局混乱
所以只能使用超链接妥协
*/