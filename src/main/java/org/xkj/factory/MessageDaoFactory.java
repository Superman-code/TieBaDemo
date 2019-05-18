package org.xkj.factory;

import org.xkj.dao.IMessageDao;
import org.xkj.dao.impl.MessageDaoImpl;

public class MessageDaoFactory {
	public static IMessageDao getMessageDaoInstance() {
		return new MessageDaoImpl();
	}
}
