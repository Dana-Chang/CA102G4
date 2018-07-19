package com.blogReplyReport.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
		     
public class BlogReplyReportDAO implements BlogReplyReportDAO_interface {

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
			"INSERT INTO blogReplyReport (replyReportNo,replyNo,memId,rpReason,rpTime) VALUES (blogReplyReport_seq.NEXTVAL,?,?,?,(select sysdate from dual))";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM blogReplyReport order by replyReportNo";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM blogReplyReport where replyReportNo = ?";
		private static final String DELETE = 
			"DELETE FROM blogReplyReport where replyReportNo = ?";
		private static final String UPDATE = 
			"UPDATE blogReplyReport set adminChecked=?, noRpReason=? where replyReportNo = ?";
	@Override
	public void insert(BlogReplyReportVO blogReplyReportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, blogReplyReportVO.getReplyNo());
			pstmt.setInt(2, blogReplyReportVO.getMemId());
			pstmt.setString(3, blogReplyReportVO.getRpReason());


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
					   
	public void update(BlogReplyReportVO blogReplyReportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, blogReplyReportVO.getAdminChecked());
			pstmt.setString(2, blogReplyReportVO.getNoRpReason());
			pstmt.setInt(3, blogReplyReportVO.getReplyReportNo());

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
	public void delete(Integer replyReportNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, replyReportNo);

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
	public BlogReplyReportVO findByPrimaryKey(Integer replyReportNo) {

		BlogReplyReportVO blogReplyReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, replyReportNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 單筆查詢
				blogReplyReportVO = new BlogReplyReportVO();
				blogReplyReportVO.setReplyReportNo(rs.getInt("replyReportNo"));
				blogReplyReportVO.setReplyNo(rs.getInt("replyNo"));
				blogReplyReportVO.setMemId(rs.getInt("memId"));
				blogReplyReportVO.setRpReason(rs.getString("rpReason"));
				blogReplyReportVO.setRpTime(rs.getTimestamp("rpTime"));
				blogReplyReportVO.setAdminChecked(rs.getString("adminChecked"));
				blogReplyReportVO.setNoRpReason(rs.getString("noRpReason"));				
			
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
		return blogReplyReportVO;
	}

	@Override
	public List<BlogReplyReportVO> getAll() {
		List<BlogReplyReportVO> list = new ArrayList<BlogReplyReportVO>();
		BlogReplyReportVO blogReplyReportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// blogReplyReportVO �]�٬� Domain objects
				blogReplyReportVO = new BlogReplyReportVO();
				blogReplyReportVO.setReplyReportNo(rs.getInt("replyReportNo"));
				blogReplyReportVO.setReplyNo(rs.getInt("replyNo"));
				blogReplyReportVO.setMemId(rs.getInt("memId"));
				blogReplyReportVO.setRpReason(rs.getString("rpReason"));
				blogReplyReportVO.setRpTime(rs.getTimestamp("rpTime"));
				blogReplyReportVO.setAdminChecked(rs.getString("adminChecked"));
				blogReplyReportVO.setNoRpReason(rs.getString("noRpReason"));				
				list.add(blogReplyReportVO);
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