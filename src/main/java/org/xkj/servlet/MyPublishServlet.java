package org.xkj.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xkj.entity.Employee;
import org.xkj.entity.Message;
import org.xkj.service.IMessageService;
import org.xkj.service.impl.MessageServiceImpl;
import org.xkj.util.Page;
import org.xkj.util.PageUtil;

public class MyPublishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");

		int currentPage = 0;

		String currentPageStr = request.getParameter("currentPage");
		if (currentPageStr == null || "".equals(currentPageStr)) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(currentPageStr);
		}

		Employee employee = (Employee) request.getSession().getAttribute("employee");
		
		/*
		 * 这里假如没有登录的话，取到的employee肯定是空，所以如果不判断一下的话，会直接报错
		 */
		if(employee == null) {
			response.sendRedirect("MyPublish.jsp");
			return;
		}

		IMessageService messageService = new MessageServiceImpl();
		
		Page page = PageUtil.createPage(5, messageService.findMyPublishAllCount(employee.getEmployeeID()), currentPage);

		List<Message> messages = messageService.findMessageByEmpID(employee.getEmployeeID(), page);

		request.setAttribute("messages", messages);
		request.setAttribute("page", page);
		request.getRequestDispatcher("MyPublish.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
