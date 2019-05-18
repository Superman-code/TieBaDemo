package org.xkj.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xkj.dao.IEmployeeDao;
import org.xkj.dao.IMessageDao;
import org.xkj.entity.Employee;
import org.xkj.entity.Message;
import org.xkj.factory.EmployeeDaoFactory;
import org.xkj.factory.MessageDaoFactory;
import org.xkj.util.Page;
import org.xkj.util.PageUtil;

public class StatusRecogniseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String employeeID = request.getParameter("employeeID");
		String password = request.getParameter("password");
		String everyPage = request.getParameter("everyPage");
		
		if(employeeID==null || "".equals(employeeID)) {
			request.setAttribute("error", "请输入员工编号！");
			request.getRequestDispatcher("statusRecognise.jsp").forward(request, response);
		} else {
			if(password==null || "".equals(password)) {
				request.setAttribute("error", "请输入系统口令！");
				request.getRequestDispatcher("statusRecognise.jsp").forward(request, response);
			} else {
				IEmployeeDao dao = EmployeeDaoFactory.getEmployeeDaoInstance();
				Employee employee = dao.findEmployeeById(Integer.parseInt(employeeID));
				if(employee == null) {
					request.setAttribute("error", "该员工不存在！");
					request.getRequestDispatcher("statusRecognise.jsp").forward(request, response);
				} else {
					if(password.equals(employee.getPassword())) {
						request.getSession().setAttribute("employee", employee);
						if(everyPage != null) {
							IMessageDao messageDao = MessageDaoFactory.getMessageDaoInstance();
							Page page = PageUtil.createPage(Integer.parseInt(everyPage), messageDao.findAllCount(), 1);
							List<Message> messages = messageDao.findAllMessage(page);
							request.getSession().setAttribute("messages", messages);
						}
						request.getRequestDispatcher("index.jsp").forward(request, response);
					} else {
						request.setAttribute("error", "系统口令不正确！");
						request.getRequestDispatcher("statusRecognise.jsp").forward(request, response);
					}
				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
