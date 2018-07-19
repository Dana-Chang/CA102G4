package com.blogMsg.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BlogMsgDAO implements BlogMsgDAO_interface {

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
		"INSERT INTO blogMsg (msgNo, articleNo, msgMemId, msgContent, msgTime, reported, reviewed, isBlocked, blockedReason) VALUES (blogMsg_seq.NEXTVAL, ?, ?, ?,(select sysdate from dual), 0, 0, 0, '')";
	private static final String UPDATE = 
		"UPDATE blogMsg set articleNo=?, msgMemId=?, msgContent=?, reported=?, reviewed=?, isBlocked=?, blockedReason=? where msgNo = ?";
	private static final String DELETE = 
		"DELETE FROM BlogMsg where msgNo = ?";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM BlogMsg where msgNo = ?";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM BlogMsg order by msgNo";
	private static final String GET_ARTICLE_MSG = 
		"SELECT * FROM BlogMsg where ArticleNo = ?";

	@Override
	public void insert(BlogMsgVO blogMsgVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, blogMsgVO.getArticleNo());
			pstmt.setInt(2, blogMsgVO.getMsgMemId());
			pstmt.setString(3, blogMsgVO.getMsgContent());			

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
	public void update(BlogMsgVO blogMsgVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, blogMsgVO.getArticleNo());
			pstmt.setInt(2, blogMsgVO.getMsgMemId());
			pstmt.setString(3, blogMsgVO.getMsgContent());
			pstmt.setString(4, blogMsgVO.getReported());
			pstmt.setString(5, blogMsgVO.getReviewed());
			pstmt.setString(6, blogMsgVO.getIsBlocked());
			pstmt.setString(7, blogMsgVO.getBlockedReason());
			pstmt.setInt(8, blogMsgVO.getMsgNo());

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
	public void delete(Integer msgNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, msgNo);

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
	public BlogMsgVO findByPrimaryKey(Integer msgNo) {

		BlogMsgVO blogMsgVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, msgNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 單筆查詢
				blogMsgVO = new BlogMsgVO();
				blogMsgVO.setMsgNo(rs.getInt("msgNo"));
				blogMsgVO.setArticleNo(rs.getInt("articleNo"));
				blogMsgVO.setMsgMemId(rs.getInt("msgMemId"));
				blogMsgVO.setMsgContent(rs.getString("msgContent"));
				blogMsgVO.setMsgTime(rs.getTimestamp("msgTime"));
				blogMsgVO.setReported(rs.getString("reported"));
				blogMsgVO.setReviewed(rs.getString("reviewed"));
				blogMsgVO.setIsBlocked(rs.getString("isBlocked"));
				blogMsgVO.setBlockedReason(rs.getString("blockedReason"));
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
		return blogMsgVO;
	}

	@Override
	public List<BlogMsgVO> getAll() {
		List<BlogMsgVO> list = new ArrayList<BlogMsgVO>();
		BlogMsgVO blogMsgVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// blogMsgVO �]�٬� Domain objects
				blogMsgVO = new BlogMsgVO();

				blogMsgVO.setMsgNo(rs.getInt("msgNo"));
				blogMsgVO.setArticleNo(rs.getInt("articleNo"));
				blogMsgVO.setMsgMemId(rs.getInt("msgMemId"));
				blogMsgVO.setMsgContent(rs.getString("msgContent"));
				blogMsgVO.setMsgTime(rs.getTimestamp("msgTime"));
				blogMsgVO.setReported(rs.getString("reported"));
				blogMsgVO.setReviewed(rs.getString("reviewed"));
				blogMsgVO.setIsBlocked(rs.getString("isBlocked"));
				blogMsgVO.setBlockedReason(rs.getString("blockedReason"));
				list.add(blogMsgVO); // Store the row in the list

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