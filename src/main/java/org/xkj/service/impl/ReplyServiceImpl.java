package org.xkj.service.impl;

import org.xkj.dao.IReplyDao;
import org.xkj.dao.impl.ReplyDaoImpl;
import org.xkj.service.IReplyService;

public class ReplyServiceImpl implements IReplyService {
	private IReplyDao replyDao;
	
	public ReplyServiceImpl() {
		replyDao = new ReplyDaoImpl();
	}
	
	@Override
	public int findMyReplyTotalCountByEmpID(int employeeID) {
		return replyDao.findMyReplyTotalCountByEmpID(employeeID);
	}

}
