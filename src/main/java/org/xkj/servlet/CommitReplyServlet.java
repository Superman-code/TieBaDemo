package org.xkj.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xkj.dao.IReplyDao;
import org.xkj.entity.Employee;
import org.xkj.entity.Reply;
import org.xkj.factory.ReplyDaoFactory;

public class CommitReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String replyContent = request.getParameter("replyContent");
		int messageID = Integer.parseInt(request.getParameter("messageID"));
		Employee employee = (Employee) request.getSession().getAttribute("employee");
		
		RequestDispatcher dispatcher = null;
		
		if(employee == null) {
			request.setAttribute("error", "要想回复必须先进行身份识别！");
			dispatcher = request.getRequestDispatcher("statusRecognise.jsp");
		} else {
			if(replyContent==null || "".equals(replyContent)) {
				request.setAttribute("error", "回复内容不能为空！");
				dispatcher = request.getRequestDispatcher("GetMsgServlet?messageID="+messageID);
			} else {
				Reply reply = new Reply();
				reply.setReplyContent(replyContent);
				reply.setMessageID(messageID);
				reply.setEmployeeID(employee.getEmployeeID());
				reply.setReplyTime(new Timestamp(new Date().getTime()));
				
				IReplyDao replyDao = ReplyDaoFactory.getReplyDaoInstance();
				replyDao.addReply(reply);
				
				dispatcher = request.getRequestDispatcher("GetMsgServlet?messageID="+messageID);
			}
		}
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
