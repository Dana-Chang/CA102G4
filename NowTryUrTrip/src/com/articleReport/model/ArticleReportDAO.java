package com.articleReport.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ArticleReportDAO implements ArticleReportDAO_interface {

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
			"INSERT INTO articleReport (reportNo,articleNo,memId,rpReason,rpTime,adminChecked,noRpReason) VALUES (articleReport_seq.NEXTVAL,?,?,?,(select sysdate from dual),?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM articleReport order by reportNo";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM articleReport where reportNo = ?";
		private static final String DELETE = 
			"DELETE FROM articleReport where reportNo = ? ";
		private static final String UPDATE = 
			"UPDATE articleReport set adminChecked=?, noRpReason=? where reportNo = ?";

	@Override
	public void insert(ArticleReportVO articleReportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, articleReportVO.getArticleNo());
			pstmt.setInt(2, articleReportVO.getMemId());
			pstmt.setString(3, articleReportVO.getRpReason());
			pstmt.setString(4, articleReportVO.getAdminChecked());
			pstmt.setString(5, articleReportVO.getNoRpReason());	

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
	public void update(ArticleReportVO articleReportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, articleReportVO.getAdminChecked());
			pstmt.setString(2, articleReportVO.getNoRpReason());
			pstmt.setInt(3, articleReportVO.getReportNo());

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
	public void delete(Integer reportNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, reportNo);

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
	public ArticleReportVO findByPrimaryKey(Integer reportNo) {

		ArticleReportVO articleReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, reportNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 單筆查詢
				articleReportVO = new ArticleReportVO();
				articleReportVO.setReportNo(rs.getInt("reportNo"));
				articleReportVO.setArticleNo(rs.getInt("articleNo"));
				articleReportVO.setMemId(rs.getInt("memId"));
				articleReportVO.setRpReason(rs.getString("rpReason"));
				articleReportVO.setRpTime(rs.getTimestamp("rpTime"));
				articleReportVO.setAdminChecked(rs.getString("adminChecked"));
				articleReportVO.setNoRpReason(rs.getString("noRpReason"));	
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
		return articleReportVO;
	}

	@Override
	public List<ArticleReportVO> getAll() {
		List<ArticleReportVO> list = new ArrayList<ArticleReportVO>();
		ArticleReportVO articleReportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// articleReportVO �]�٬� Domain objects
				articleReportVO = new ArticleReportVO();
				articleReportVO.setReportNo(rs.getInt("reportNo"));
				articleReportVO.setArticleNo(rs.getInt("articleNo"));
				articleReportVO.setMemId(rs.getInt("memId"));
				articleReportVO.setRpReason(rs.getString("rpReason"));
				articleReportVO.setRpTime(rs.getTimestamp("rpTime"));
				articleReportVO.setAdminChecked(rs.getString("adminChecked"));
				articleReportVO.setNoRpReason(rs.getString("noRpReason"));	
				list.add(articleReportVO); // Store the row in the list

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