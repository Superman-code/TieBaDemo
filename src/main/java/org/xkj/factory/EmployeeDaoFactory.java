package org.xkj.factory;

import org.xkj.dao.IEmployeeDao;
import org.xkj.dao.impl.EmployeeDaoImpl;

public class EmployeeDaoFactory {
	public static IEmployeeDao getEmployeeDaoInstance() {
		return new EmployeeDaoImpl();
	}
}
