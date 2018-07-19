package com.blogMsgReply.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BlogMsgReplyDAO implements BlogMsgReplyDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/AA107G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = 
		"INSERT INTO blogMsgReply (replyNo, msgNo, msgMemId, msgContent, msgTime, reported, reviewed, isBlocked, blockedReason) VALUES (blogMsgReply_seq.NEXTVAL, ?, ?, ?,(select sysdate from dual), 0, 0, 0, '')";
	private static final String UPDATE = 
		"UPDATE blogMsgReply set reported=?, reviewed=?, isBlocked=?, blockedReason=? where replyNo = ?";
	private static final String DELETE = 
		"DELETE FROM BlogMsgReply where replyNo = ?";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM BlogMsgReply where replyNo = ?";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM BlogMsgReply order by replyNo";

	@Override
	public void insert(BlogMsgReplyVO blogMsgReplyVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, blogMsgReplyVO.getMsgNo());
			pstmt.setInt(2, blogMsgReplyVO.getMsgMemId());
			pstmt.setString(3, blogMsgReplyVO.getMsgContent());			

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(BlogMsgReplyVO blogMsgReplyVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, blogMsgReplyVO.getReported());
			pstmt.setString(2, blogMsgReplyVO.getReviewed());
			pstmt.setString(3, blogMsgReplyVO.getIsBlocked());
			pstmt.setString(4, blogMsgReplyVO.getBlockedReason());
			pstmt.setInt(5, blogMsgReplyVO.getReplyNo());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(Integer replyNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, replyNo);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public BlogMsgReplyVO findByPrimaryKey(Integer replyNo) {

		BlogMsgReplyVO blogMsgReplyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, replyNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 單筆查詢
				blogMsgReplyVO = new BlogMsgReplyVO();

				blogMsgReplyVO.setReplyNo(rs.getInt("replyNo"));
				blogMsgReplyVO.setMsgNo(rs.getInt("msgNo"));
				blogMsgReplyVO.setMsgMemId(rs.getInt("msgMemId"));
				blogMsgReplyVO.setMsgContent(rs.getString("msgContent"));
				blogMsgReplyVO.setMsgTime(rs.getTimestamp("msgTime"));
				blogMsgReplyVO.setReported(rs.getString("reported"));
				blogMsgReplyVO.setReviewed(rs.getString("reviewed"));
				blogMsgReplyVO.setIsBlocked(rs.getString("isBlocked"));
				blogMsgReplyVO.setBlockedReason(rs.getString("blockedReason"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return blogMsgReplyVO;
	}

	@Override
	public List<BlogMsgReplyVO> getAll() {
		List<BlogMsgReplyVO> list = new ArrayList<BlogMsgReplyVO>();
		BlogMsgReplyVO blogMsgReplyVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// blogMsgReplyVO �]�٬� Domain objects
				blogMsgReplyVO = new BlogMsgReplyVO();

				blogMsgReplyVO.setReplyNo(rs.getInt("replyNo"));
				blogMsgReplyVO.setMsgNo(rs.getInt("msgNo"));
				blogMsgReplyVO.setMsgMemId(rs.getInt("msgMemId"));
				blogMsgReplyVO.setMsgContent(rs.getString("msgContent"));
				blogMsgReplyVO.setMsgTime(rs.getTimestamp("msgTime"));
				blogMsgReplyVO.setReported(rs.getString("reported"));
				blogMsgReplyVO.setReviewed(rs.getString("reviewed"));
				blogMsgReplyVO.setIsBlocked(rs.getString("isBlocked"));
				blogMsgReplyVO.setBlockedReason(rs.getString("blockedReason"));
				list.add(blogMsgReplyVO); // Store the row in the list

			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
}