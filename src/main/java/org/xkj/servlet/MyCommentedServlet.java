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
import org.xkj.service.IReplyService;
import org.xkj.service.impl.MessageServiceImpl;
import org.xkj.service.impl.ReplyServiceImpl;
import org.xkj.util.Page;
import org.xkj.util.PageUtil;

public class MyCommentedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		/*
		 * 这里评论总数需要用replyService获取
		 * 然后再根据这个评论总数获取评论总页数
		 * 但是一个人可能在一个消息有多个评论，所以在计算评论总数时，需要用到distinct
		 */
		
		IReplyService replyService = new ReplyServiceImpl();
		
		Page page = PageUtil.createPage(5, replyService.findMyReplyTotalCountByEmpID(employee.getEmployeeID()), currentPage);

		List<Message> messages = messageService.findMyCommentedMessageByEmpID(employee.getEmployeeID(), page);

		request.setAttribute("messages", messages);
		request.setAttribute("page", page);
		request.getRequestDispatcher("myCommented.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
