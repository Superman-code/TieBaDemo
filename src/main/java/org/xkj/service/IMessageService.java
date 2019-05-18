package org.xkj.service;

import java.util.List;

import org.xkj.entity.Message;
import org.xkj.util.Page;

public interface IMessageService {
	public List<Message> findMessageByEmpID(int employeeID, Page page);
	public int findAllCount();
	public int findMyPublishAllCount(int employeeID);
	public List<Message> findMyCommentedMessageByEmpID(int employeeID, Page page);
}
