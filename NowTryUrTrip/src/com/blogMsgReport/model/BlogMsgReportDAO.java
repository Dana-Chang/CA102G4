package com.blogMsgReport.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
		     
public class BlogMsgReportDAO implements BlogMsgReportDAO_interface {

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
			"INSERT INTO blogMsgReport (cmtReportNo,msgNo,memId,rpReason,rpTime) VALUES (blogMsgReport_seq.NEXTVAL,?,?,?,(select sysdate from dual))";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM blogMsgReport order by cmtReportNo";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM blogMsgReport where cmtReportNo = ?";
		private static final String DELETE = 
			"DELETE FROM blogMsgReport where cmtReportNo = ?";
		private static final String UPDATE = 
			"UPDATE blogMsgReport set adminChecked=?, noRpReason=? where cmtReportNo = ?";
	@Override
	public void insert(BlogMsgReportVO blogMsgReportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, blogMsgReportVO.getMsgNo());
			pstmt.setInt(2, blogMsgReportVO.getMemId());
			pstmt.setString(3, blogMsgReportVO.getRpReason());


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
					   
	public void update(BlogMsgReportVO blogMsgReportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, blogMsgReportVO.getAdminChecked());
			pstmt.setString(2, blogMsgReportVO.getNoRpReason());
			pstmt.setInt(3, blogMsgReportVO.getCmtReportNo());

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
	public void delete(Integer cmtReportNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, cmtReportNo);

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
	public BlogMsgReportVO findByPrimaryKey(Integer cmtReportNo) {

		BlogMsgReportVO blogMsgReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, cmtReportNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 單筆查詢
				blogMsgReportVO = new BlogMsgReportVO();
				blogMsgReportVO.setCmtReportNo(rs.getInt("cmtReportNo"));
				blogMsgReportVO.setMsgNo(rs.getInt("msgNo"));
				blogMsgReportVO.setMemId(rs.getInt("memId"));
				blogMsgReportVO.setRpReason(rs.getString("rpReason"));
				blogMsgReportVO.setRpTime(rs.getTimestamp("rpTime"));
				blogMsgReportVO.setAdminChecked(rs.getString("adminChecked"));
				blogMsgReportVO.setNoRpReason(rs.getString("noRpReason"));				
			
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
		return blogMsgReportVO;
	}

	@Override
	public List<BlogMsgReportVO> getAll() {
		List<BlogMsgReportVO> list = new ArrayList<BlogMsgReportVO>();
		BlogMsgReportVO blogMsgReportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// blogMsgReportVO �]�٬� Domain objects
				blogMsgReportVO = new BlogMsgReportVO();
				blogMsgReportVO.setCmtReportNo(rs.getInt("cmtReportNo"));
				blogMsgReportVO.setMsgNo(rs.getInt("msgNo"));
				blogMsgReportVO.setMemId(rs.getInt("memId"));
				blogMsgReportVO.setRpReason(rs.getString("rpReason"));
				blogMsgReportVO.setRpTime(rs.getTimestamp("rpTime"));
				blogMsgReportVO.setAdminChecked(rs.getString("adminChecked"));
				blogMsgReportVO.setNoRpReason(rs.getString("noRpReason"));				
				list.add(blogMsgReportVO);
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