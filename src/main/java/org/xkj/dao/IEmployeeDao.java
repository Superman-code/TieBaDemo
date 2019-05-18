package org.xkj.dao;

import java.sql.Date;
import java.util.List;

import org.xkj.entity.Employee;

public interface IEmployeeDao {
	public void addEmployee(Employee employee);
	public void deleteEmployee(int employeeID);
	public void updateEmployee(Employee employee);
	public List<Employee> findAllEmployee();
	public Employee findEmployeeById(int employeeID);
	public void updateEmployeePropertyById(int employeeID, String type, Object parameter);
}
