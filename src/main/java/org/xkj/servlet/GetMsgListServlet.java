package org.xkj.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xkj.dao.IMessageDao;
import org.xkj.entity.Message;
import org.xkj.factory.MessageDaoFactory;
import org.xkj.util.Page;
import org.xkj.util.PageUtil;

public class GetMsgListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int currentPage = 0;
		
		String currentPageStr = request.getParameter("currentPage");
		if(currentPageStr==null || "".equals(currentPageStr)) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(currentPageStr);
		}
		
		IMessageDao messageDao = MessageDaoFactory.getMessageDaoInstance();
		Page page = PageUtil.createPage(5, messageDao.findAllCount(), currentPage);
		List<Message> messages = messageDao.findAllMessage(page);
		request.setAttribute("messages", messages);
		request.setAttribute("page", page);
		request.getRequestDispatcher("msgList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
