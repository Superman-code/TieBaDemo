package org.xkj.servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xkj.entity.Employee;
import org.xkj.service.IEmployeeService;
import org.xkj.service.impl.EmployeeServiceImpl;

public class UpdateEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int employeeID = Integer.parseInt(request.getParameter("employeeID"));
		String employeeName = request.getParameter("employeeName");
		String employeeSex = request.getParameter("employeeSex");
		String employeeBirth = request.getParameter("employeeBirth");
		String employeePhone = request.getParameter("employeePhone");
		String employeePlace = request.getParameter("employeePlace");
		String isLead = request.getParameter("isLead");
		
		IEmployeeService employeeService = new EmployeeServiceImpl();
		Employee employee = (Employee) request.getSession().getAttribute("employee");
		
		if(employeeName != null) {
			employeeService.updateEmployeePropertyById(employeeID, "employeeName", employeeName);
			employee.setEmployeeName(employeeName);
		}
		if(employeeSex != null) {
			employeeService.updateEmployeePropertyById(employeeID, "employeeSex", Boolean.valueOf(employeeSex));
			employee.setEmployeeSex(Boolean.valueOf(employeeSex));
		}
		if(employeeBirth != null) {
			employeeService.updateEmployeePropertyById(employeeID, "employeeBirth", Date.valueOf(employeeBirth));
			employee.setEmployeeBirth(Date.valueOf(employeeBirth));
		}
		if(employeePhone != null) {
			employeeService.updateEmployeePropertyById(employeeID, "employeePhone", employeePhone);
			employee.setEmployeePhone(employeePhone);
		}
		if(employeePlace != null) {
			employeeService.updateEmployeePropertyById(employeeID, "employeePlace", employeePlace);
			employee.setEmployeePlace(employeePlace);
		}
		if(isLead != null) {
			employeeService.updateEmployeePropertyById(employeeID, "isLead", Boolean.valueOf(isLead));
			employee.setLead(Boolean.valueOf(isLead));
		}
		
		response.sendRedirect("index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
