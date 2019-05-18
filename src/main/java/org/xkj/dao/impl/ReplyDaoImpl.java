package org.xkj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.xkj.dao.IReplyDao;
import org.xkj.entity.Reply;
import org.xkj.util.DBConnection;
import org.xkj.util.Page;

public class ReplyDaoImpl implements IReplyDao {

	@Override
	public void addReply(Reply reply) {
		Connection conn = DBConnection.getConnection();
		String sql = "insert into tb_reply values(?,?,?,?,?)";
		PreparedStatement pstmt = null;
		int result = -1;
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, reply.getReplyID());
			pstmt.setString(2, reply.getReplyContent());
			pstmt.setInt(3, reply.getEmployeeID());
			pstmt.setTimestamp(4, reply.getReplyTime());
			pstmt.setInt(5, reply.getMessageID());
			
			result = pstmt.executeUpdate();
			if(result > 0) {
				System.out.println(reply.getMessageID()+"---"+reply.getEmployeeID()+"回复成功！");
			} else {
				System.out.println(reply.getMessageID()+"---"+reply.getEmployeeID()+"回复失败！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn);
			DBConnection.close(pstmt);
		}
	}

	@Override
	public List<Reply> findReplyByMsgID(int messageID, Page page) {
		List<Reply> replys = new ArrayList<Reply>();
		
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from tb_reply where messageID=? order by replyTime asc limit ?,?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, messageID);
			pstmt.setInt(2, page.getBeginIndex());
			pstmt.setInt(3, page.getEveryPage());
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Reply reply = new Reply();
				reply.setReplyID(rs.getInt(1));
				reply.setReplyContent(rs.getString(2));
				reply.setEmployeeID(rs.getInt(3));
				reply.setReplyTime(rs.getTimestamp(4));
				reply.setMessageID(rs.getInt(5));
				
				replys.add(reply);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn);
			DBConnection.close(pstmt);
			DBConnection.close(rs);
		}
		return replys;
	}

	@Override
	public int findAllCountByMsgID(int messageID) {
		int totalCount = 0;
		Connection conn = DBConnection.getConnection();
		String sql = "select count(*) from tb_reply where messageID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, messageID);
			
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
	public List<Integer> findMessageIDByEmpID(int employeeID, Page page) {
		List<Integer> messageIDs = new ArrayList<Integer>();
		
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select distinct messageID from tb_reply where employeeID=? order by replyTime desc limit ?,?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, employeeID);
			pstmt.setInt(2, page.getBeginIndex());
			pstmt.setInt(3, page.getEveryPage());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Integer messageID = new Integer(rs.getInt(1));
				
				messageIDs.add(messageID);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn);
			DBConnection.close(pstmt);
			DBConnection.close(rs);
		}
		
		return messageIDs;
	}

	@Override
	public int findMyReplyTotalCountByEmpID(int employeeID) {
		int totalCount = 0;
		
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select count(distinct messageID) from tb_reply where employeeID = ?";
		
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
