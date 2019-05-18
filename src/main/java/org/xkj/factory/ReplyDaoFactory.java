package org.xkj.factory;

import org.xkj.dao.IReplyDao;
import org.xkj.dao.impl.ReplyDaoImpl;

public class ReplyDaoFactory {
	public static IReplyDao getReplyDaoInstance() {
		return new ReplyDaoImpl();
	}
}
