package org.xkj.dao;

import java.util.List;

import org.xkj.entity.Message;
import org.xkj.util.Page;

public interface IMessageDao {
	public void addMessage(Message message);
	public void deleteMessage(int messageID);
	public void updateMessage(Message message);
	public List<Message> findAllMessage(Page page);
	public Message findMessageById(int messageID);
	public int findAllCount();
	public List<Message> findMessageByEmpID(int employeeID, Page page);
	public int findMyPublishAllCount(int employeeID);
}
