package org.xkj.servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xkj.dao.IEmployeeDao;
import org.xkj.entity.Employee;
import org.xkj.factory.EmployeeDaoFactory;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String employeeID = request.getParameter("employeeID");
		String employeeName = request.getParameter("employeeName");
		String employeeSex = request.getParameter("employeeSex");
		String employeeBirth = request.getParameter("employeeBirth");
		String employeePhone = request.getParameter("employeePhone");
		String employeePlace = request.getParameter("employeePlace");
		String joinTime = request.getParameter("joinTime");
		String password = request.getParameter("password");
		String lead = request.getParameter("lead");
		
		IEmployeeDao employeeDao = EmployeeDaoFactory.getEmployeeDaoInstance();
		Employee e = employeeDao.findEmployeeById(Integer.parseInt(employeeID));
		if(e != null) {
			request.setAttribute("error", "员工编号已存在，请重新填写");
			request.setAttribute("employeeName", employeeName);
			request.setAttribute("employeeBirth", employeeBirth);
			request.setAttribute("employeePhone", employeePhone);
			request.setAttribute("employeePlace", employeePlace);
			request.setAttribute("joinTime", joinTime);
			request.setAttribute("password", password);
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		String[] dateTime = employeeBirth.split("-");
		
		Employee employee = new Employee();
		
		employee.setEmployeeID(Integer.parseInt(employeeID));
		employee.setEmployeeName(employeeName);
		employee.setEmployeeSex(Boolean.valueOf(employeeSex));
		
		if(Integer.parseInt(dateTime[1]) < 1 || Integer.parseInt(dateTime[1]) > 12) {
			request.setAttribute("error", "您填写的出生日期月份不符合，请重新填写");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		if(Integer.parseInt(dateTime[2]) < 1 || Integer.parseInt(dateTime[2]) > 31) {
			request.setAttribute("error", "您填写的出生日期天数不符合，请重新填写");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		employee.setEmployeeBirth(Date.valueOf(employeeBirth));
		employee.setEmployeePhone(employeePhone);
		employee.setEmployeePlace(employeePlace);
		
		dateTime = joinTime.split("-");
		if(Integer.parseInt(dateTime[1]) < 1 || Integer.parseInt(dateTime[1]) > 12) {
			request.setAttribute("error", "您填写的入职时间月份不符合，请重新填写");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		if(Integer.parseInt(dateTime[2]) < 1 || Integer.parseInt(dateTime[2]) > 31) {
			request.setAttribute("error", "您填写的入职时间天数不符合，请重新填写");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		employee.setJoinTime(Date.valueOf(joinTime));
		employee.setPassword(password);
		employee.setLead(Boolean.valueOf(lead));
		
		employeeDao.addEmployee(employee);
		
		response.sendRedirect("index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
