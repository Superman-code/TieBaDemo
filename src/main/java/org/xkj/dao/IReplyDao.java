package org.xkj.dao;

import java.util.List;

import org.xkj.entity.Reply;
import org.xkj.util.Page;

public interface IReplyDao {
	public void addReply(Reply reply);
	public List<Reply> findReplyByMsgID(int messageID, Page page);
	public int findAllCountByMsgID(int messageID);
	public List<Integer> findMessageIDByEmpID(int employeeID, Page page);
	public int findMyReplyTotalCountByEmpID(int employeeID);
}
