package org.xkj.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xkj.dao.IMessageDao;
import org.xkj.entity.Employee;
import org.xkj.entity.Message;
import org.xkj.factory.MessageDaoFactory;
import org.xkj.util.Page;
import org.xkj.util.PageUtil;

public class MsgPublishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		RequestDispatcher dispatcher = null;
		Employee employee = (Employee) request.getSession().getAttribute("employee");
		
		if(employee == null) {
			request.setAttribute("error", "要发布消息必须首先进行身份识别！");
			dispatcher = request.getRequestDispatcher("publishNewMsg.jsp");
		} else {
			if(title==null || "".equals(title)) {
				request.setAttribute("error", "必须输入消息标题！");
				dispatcher = request.getRequestDispatcher("publishNewMsg.jsp");
			} else {
				Message message = new Message();
				
				message.setMessageTitle(title);
				message.setMessageContent(content);
				message.setEmployeeID(employee.getEmployeeID());
				message.setPublishTime(new Timestamp(new Date().getTime()));
				
				IMessageDao messageDao = MessageDaoFactory.getMessageDaoInstance();
				messageDao.addMessage(message);
				
				//增加一个新消息后，在首页就应该同步
				Page page = PageUtil.createPage(5, messageDao.findAllCount(), 1);
				List<Message> messages = messageDao.findAllMessage(page);
				request.getSession().setAttribute("messages", messages);
				dispatcher = request.getRequestDispatcher("GetMsgListServlet");
			}
		}
		
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
