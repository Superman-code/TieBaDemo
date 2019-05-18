package org.xkj.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xkj.entity.Employee;

public class ExitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		Employee employee = (Employee) request.getSession().getAttribute("employee");
		
		if(employee != null) {
			request.getSession().removeAttribute("employee");
			request.getSession().removeAttribute("messages");
			response.sendRedirect("statusRecognise.jsp");
		} else {
			request.setAttribute("error", "您还没有身份识别，无法退出");
			request.getRequestDispatcher("statusRecognise.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
