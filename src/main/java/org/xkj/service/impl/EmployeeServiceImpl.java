package org.xkj.service.impl;

import java.sql.Date;

import org.xkj.dao.IEmployeeDao;
import org.xkj.factory.EmployeeDaoFactory;
import org.xkj.service.IEmployeeService;

public class EmployeeServiceImpl implements IEmployeeService {
	
	private IEmployeeDao employeeDao;

	public EmployeeServiceImpl() {
		employeeDao = EmployeeDaoFactory.getEmployeeDaoInstance();
	}
	
	@Override
	public void updateEmployeePropertyById(int employeeID, String type, Object parameter) {
		employeeDao.updateEmployeePropertyById(employeeID, type, parameter);
	}
	
}
