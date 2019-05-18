package org.xkj.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xkj.dao.IEmployeeDao;
import org.xkj.dao.IMessageDao;
import org.xkj.dao.IReplyDao;
import org.xkj.entity.Employee;
import org.xkj.entity.Message;
import org.xkj.entity.Reply;
import org.xkj.factory.EmployeeDaoFactory;
import org.xkj.factory.MessageDaoFactory;
import org.xkj.factory.ReplyDaoFactory;
import org.xkj.util.Page;
import org.xkj.util.PageUtil;

public class GetMsgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int messageID = 0;
		if(request.getParameter("messageID") == null) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			messageID = Integer.parseInt(request.getParameter("messageID"));
		}
		
		int currentPage = 0;
		String currentPageStr = request.getParameter("currentPage");
		if(currentPageStr==null || "".equals(currentPageStr)) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(currentPageStr);
		}
		
		IMessageDao messageDao = MessageDaoFactory.getMessageDaoInstance();
		
		/*
		 * 接受一个messageID，然后根据messageID找到employeeID，就是发布人的信息
		 * 再将信息存到request域，不能存在session域中，要不然会覆盖登录人的信息
		 */
		IEmployeeDao employeeDao = EmployeeDaoFactory.getEmployeeDaoInstance();
		Employee employee = employeeDao.findEmployeeById(messageDao.findMessageById(messageID).getEmployeeID());
		request.setAttribute("employee", employee);
		
		/*
		 * 创造一个默认分页，并根据messageID和默认分页page取出与其有关的所有评论
		 */
		IReplyDao replyDao = ReplyDaoFactory.getReplyDaoInstance();
		Page page = PageUtil.createPage(5, replyDao.findAllCountByMsgID(messageID), currentPage);
		List<Reply> replys = replyDao.findReplyByMsgID(messageID, page);
		
		/*
		 * 在这里创造一个map，为了就是显示评论人的名字，所以类型为Map<Integer, String>
		 * 用employeeID映射评论人的名字
		 * 而employeeID就是来自上面取出的所有评论
		 */
		Map<Integer, String> replyEmployeeNameMap = new HashMap<Integer, String>();
		for(Reply reply:replys) {
			/*
			 * 取出评论的employeeID
			 */
			Integer employeeID = reply.getEmployeeID();
			/*
			 * 这里先尝试取出employeeID对应的名字，假如不为空就直接跳过，不需要覆盖
			 */
			if(replyEmployeeNameMap.get(employeeID) != null) {
				continue;
			}
			/*
			 * 这里是取出的为空，即map里没有这个键值对，所以需要put一下
			 * 键就是上一步取出的employeeID
			 * 值就是用employeeDao根据employeeID取出的employee，再获取他的名字
			 */
			replyEmployeeNameMap.put(employeeID, employeeDao.findEmployeeById(employeeID).getEmployeeName());
		}
		request.setAttribute("replyEmployeeNameMap", replyEmployeeNameMap);
		
		request.setAttribute("replys", replys);
		request.setAttribute("page", page);
		
		Message message = messageDao.findMessageById(messageID);
		request.setAttribute("message", message);
		
		request.getRequestDispatcher("showMessage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
