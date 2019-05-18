package org.xkj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.xkj.dao.IMessageDao;
import org.xkj.entity.Message;
import org.xkj.util.DBConnection;
import org.xkj.util.Page;
import org.xkj.util.PageUtil;

public class MessageDaoImpl implements IMessageDao {

	@Override
	public void addMessage(Message message) {
		Connection conn = DBConnection.getConnection();
		String sql = "insert into tb_message values(?,?,?,?,?)";
		PreparedStatement pstmt = null;
		int result = -1;
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, message.getMessageID());
			pstmt.setString(2, message.getMessageTitle());
			pstmt.setString(3, message.getMessageContent());
			pstmt.setInt(4, message.getEmployeeID());
			pstmt.setTimestamp(5, message.getPublishTime());
			
			result = pstmt.executeUpdate();
			if(result > 0) {
				System.out.println(message.getEmployeeID()+"---"+message.getPublishTime()+"消息插入成功！");
			} else {
				System.out.println(message.getEmployeeID()+"---"+message.getPublishTime()+"消息插入失败！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn);
			DBConnection.close(pstmt);
		}
	}

	@Override
	public void deleteMessage(int messageID) {
		Connection conn = DBConnection.getConnection();
		String sql = "delete from tb_message where messageID = ?";
		PreparedStatement pstmt = null;
		int result = -1;
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, messageID);
			
			result = pstmt.executeUpdate();
			if(result > 0) {
				conn.commit();
			} else {
				conn.rollback();
				System.out.println(messageID + "消息删除失败！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn);
			DBConnection.close(pstmt);
		}
	}

	@Override
	public void updateMessage(Message message) {
		Connection conn = DBConnection.getConnection();
		String sql = "update tb_message set messageTitle=?,messageContent=?,employeeID=?,publishTime=? where messageID=?";
		PreparedStatement pstmt = null;
		int result = -1;
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, message.getMessageTitle());
			pstmt.setString(2, message.getMessageContent());
			pstmt.setInt(3, message.getEmployeeID());
			pstmt.setTimestamp(4, message.getPublishTime());
			pstmt.setInt(5, message.getMessageID());
			
			result = pstmt.executeUpdate();
			if(result > 0) {
				conn.commit();
			} else {
				conn.rollback();
				System.out.println(message.getMessageID()+"消息更改失败！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn);
			DBConnection.close(pstmt);
		}
	}

	@Override
	public List<Message> findAllMessage(Page page) {
		List<Message> messages = new ArrayList<Message>();
		Message message = null;
		Connection conn = DBConnection.getConnection();
		String sql = "select * from tb_message order by publishTime desc limit ?,?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, page.getBeginIndex());
			pstmt.setInt(2, page.getEveryPage());
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				message = new Message();
				
				message.setMessageID(rs.getInt(1));
				message.setMessageTitle(rs.getString(2));
				message.setMessageContent(rs.getString(3));
				message.setEmployeeID(rs.getInt(4));
				message.setPublishTime(rs.getTimestamp(5));
				
				messages.add(message);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn);
			DBConnection.close(pstmt);
			DBConnection.close(rs);
		}
		return messages;
	}

	@Override
	public Message findMessageById(int messageID) {
		Message message = null;
		Connection conn = DBConnection.getConnection();
		String sql = "select * from tb_message where messageID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, messageID);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				message = new Message();
				
				message.setMessageID(rs.getInt(1));
				message.setMessageTitle(rs.getString(2));
				message.setMessageContent(rs.getString(3));
				message.setEmployeeID(rs.getInt(4));
				message.setPublishTime(rs.getTimestamp(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn);
			DBConnection.close(pstmt);
			DBConnection.close(rs);
		}
		return message;
	}

	@Override
	public int findAllCount() {
		int totalCount = 0;
		Connection conn = DBConnection.getConnection();
		String sql = "select count(*) from tb_message";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn);
			DBConnection.close(pstmt);
			DBConnection.close(rs);
		}
		return totalCount;
	}

	@Override
	public List<Message> findMessageByEmpID(int employeeID, Page page) {
		List<Message> messages = new ArrayList<Message>();
		
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from tb_message where employeeID=? order by publishTime desc limit ?,?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, employeeID);
			pstmt.setInt(2, page.getBeginIndex());
			pstmt.setInt(3, page.getEveryPage());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Message message = new Message();
				
				message.setMessageID(rs.getInt(1));
				message.setMessageTitle(rs.getString(2));
				message.setMessageContent(rs.getString(3));
				message.setEmployeeID(rs.getInt(4));
				message.setPublishTime(rs.getTimestamp(5));
				
				messages.add(message);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn);
			DBConnection.close(pstmt);
			DBConnection.close(rs);
		}
		return messages;
	}

	@Override
	public int findMyPublishAllCount(int employeeID) {
		int totalCount = 0;
		Connection conn = DBConnection.getConnection();
		String sql = "select count(*) from tb_message where employeeID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, employeeID);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn);
			DBConnection.close(pstmt);
			DBConnection.close(rs);
		}
		return totalCount;
	}

}
