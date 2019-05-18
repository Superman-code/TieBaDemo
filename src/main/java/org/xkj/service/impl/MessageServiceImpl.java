package org.xkj.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.xkj.dao.IMessageDao;
import org.xkj.dao.IReplyDao;
import org.xkj.dao.impl.MessageDaoImpl;
import org.xkj.dao.impl.ReplyDaoImpl;
import org.xkj.entity.Message;
import org.xkj.service.IMessageService;
import org.xkj.util.Page;

public class MessageServiceImpl implements IMessageService {
	private IMessageDao messageDao;
	private IReplyDao replyDao;
	
	public MessageServiceImpl() {
		messageDao = new MessageDaoImpl();
		replyDao = new ReplyDaoImpl();
	}
	
	@Override
	public List<Message> findMessageByEmpID(int employeeID, Page page) {
		return messageDao.findMessageByEmpID(employeeID, page);
	}

	@Override
	public int findAllCount() {
		return messageDao.findAllCount();
	}

	@Override
	public int findMyPublishAllCount(int employeeID) {
		return messageDao.findMyPublishAllCount(employeeID);
	}

	@Override
	public List<Message> findMyCommentedMessageByEmpID(int employeeID, Page page) {
		List<Integer> messageIDs = replyDao.findMessageIDByEmpID(employeeID, page);
		
		List<Message> messages = new ArrayList<Message>();
		
		for(Integer messageID:messageIDs) {
			Message message = messageDao.findMessageById(messageID);
			messages.add(message);
		}
		return messages;
	}

}
