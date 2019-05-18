package org.xkj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.xkj.dao.IEmployeeDao;
import org.xkj.entity.Employee;
import org.xkj.util.DBConnection;

public class EmployeeDaoImpl implements IEmployeeDao {

	public void addEmployee(Employee employee) {
		Connection conn = DBConnection.getConnection();
		String sql = "insert into tb_employee values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, employee.getEmployeeID());
			pstmt.setString(2, employee.getEmployeeName());
			pstmt.setBoolean(3, employee.isEmployeeSex());
			pstmt.setDate(4, employee.getEmployeeBirth());
			pstmt.setString(5, employee.getEmployeePhone());
			pstmt.setString(6, employee.getEmployeePlace());
			pstmt.setDate(7, employee.getJoinTime());
			pstmt.setString(8, employee.getPassword());
			pstmt.setBoolean(9, employee.isLead());
			
			int result = pstmt.executeUpdate();
			if(result > 0) {
				//凡是增，删，改操作，不需要手动提交，或者手动回滚
				//这些操作都是自动完成，如果写会报错
				//但是可以通过方法设置手动提交
				System.out.println(employee.getEmployeeID()+"插入成功！");
			} else {
				System.out.println(employee.getEmployeeID()+"插入失败！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn);
			DBConnection.close(pstmt);
		}
	}

	public void deleteEmployee(int employeeID) {
		Connection conn = DBConnection.getConnection();
		String sql = "delete from tb_employee where employeeID = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, employeeID);
			
			int result = pstmt.executeUpdate();
			if(result > 0) {
				conn.commit();
			} else {
				conn.rollback();
				System.out.println(employeeID+"删除失败！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn);
			DBConnection.close(pstmt);
		}
	}

	public void updateEmployee(Employee employee) {
		Connection conn = DBConnection.getConnection();
		String sql = "update tb_employee set employeeName=?,employeeSex=?,employeeBirth=?,employeePhone=?,employeePlace=?,joinTime=?,password=?,isLead=? where employeeID=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, employee.getEmployeeName());
			pstmt.setBoolean(2, employee.isEmployeeSex());
			pstmt.setDate(3, employee.getEmployeeBirth());
			pstmt.setString(4, employee.getEmployeePhone());
			pstmt.setString(5, employee.getEmployeePlace());
			pstmt.setDate(6, employee.getJoinTime());
			pstmt.setString(7, employee.getPassword());
			pstmt.setBoolean(8, employee.isLead());
			
			pstmt.setInt(9, employee.getEmployeeID());
			
			int result = pstmt.executeUpdate();
			if(result > 0) {
				conn.commit();
			} else {
				conn.rollback();
				System.out.println(employee.getEmployeeID()+"修改失败");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn);
			DBConnection.close(pstmt);
		}
	}

	public List<Employee> findAllEmployee() {
		List<Employee> employees = new ArrayList<Employee>();
		Employee emp = null;
		
		Connection conn = DBConnection.getConnection();
		String sql = "select * from tb_employee";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				emp = new Employee();
				emp.setEmployeeID(rs.getInt(1));
				emp.setEmployeeName(rs.getString(2));
				emp.setEmployeeSex(rs.getBoolean(3));
				emp.setEmployeeBirth(rs.getDate(4));
				emp.setEmployeePhone(rs.getString(5));
				emp.setEmployeePlace(rs.getString(6));
				emp.setJoinTime(rs.getDate(7));
				emp.setPassword(rs.getString(8));
				emp.setLead(rs.getBoolean(9));
				
				employees.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn);
			DBConnection.close(pstmt);
			DBConnection.close(rs);
		}
		return employees;
	}

	public Employee findEmployeeById(int employeeID) {
		Employee emp = null;
		Connection conn = DBConnection.getConnection();
		String sql = "select * from tb_employee where employeeID = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, employeeID);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				emp = new Employee();
				emp.setEmployeeID(rs.getInt(1));
				emp.setEmployeeName(rs.getString(2));
				emp.setEmployeeSex(rs.getBoolean(3));
				emp.setEmployeeBirth(rs.getDate(4));
				emp.setEmployeePhone(rs.getString(5));
				emp.setEmployeePlace(rs.getString(6));
				emp.setJoinTime(rs.getDate(7));
				emp.setPassword(rs.getString(8));
				emp.setLead(rs.getBoolean(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn);
			DBConnection.close(pstmt);
			DBConnection.close(rs);
		}
		return emp;
	}

	@Override
	public void updateEmployeePropertyById(int employeeID, String type, Object parameter) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "update tb_employee set "+type+" = ? where employeeID=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setObject(1, parameter);
			pstmt.setInt(2, employeeID);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn);
			DBConnection.close(pstmt);
		}
	}
	
}
