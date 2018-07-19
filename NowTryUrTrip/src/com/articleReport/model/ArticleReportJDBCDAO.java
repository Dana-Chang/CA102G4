package com.articleReport.model;

import java.util.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class ArticleReportJDBCDAO implements ArticleReportDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "tylou";
	String passwd = "123321";

	private static final String INSERT_STMT = 
		"INSERT INTO articleReport (reportNo,articleNo,memId,rpReason,rpTime,adminChecked,noRpReason) VALUES (articleReport_seq.NEXTVAL,?,?,?,(select sysdate from dual),?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM articleReport order by reportNo";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM articleReport where reportNo = ?";
	private static final String DELETE = 
		"DELETE FROM articleReport where reportNo = ? ";
	private static final String UPDATE = 
		"UPDATE articleReport set articleNo=?, memId=?, rpReason=?, adminChecked=?, noRpReason=? where reportNo = ?";

	@Override
	public void insert(ArticleReportVO articleReportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, articleReportVO.getArticleNo());
			pstmt.setInt(2, articleReportVO.getMemId());
			pstmt.setString(3, articleReportVO.getRpReason());
			pstmt.setString(4, articleReportVO.getAdminChecked());
			pstmt.setString(5, articleReportVO.getNoRpReason());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, articleReportVO.getArticleNo());
			pstmt.setInt(2, articleReportVO.getMemId());
			pstmt.setString(3, articleReportVO.getRpReason());
			pstmt.setString(4, articleReportVO.getAdminChecked());
			pstmt.setString(5, articleReportVO.getNoRpReason());
			pstmt.setInt(6, articleReportVO.getReportNo());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public void delete(Integer reportNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, reportNo);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public ArticleReportVO findByPrimaryKey(Integer reportNo) {

		ArticleReportVO articleReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, reportNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 查詢單筆資料
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 查詢全部
				articleReportVO = new ArticleReportVO();
				
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		ArticleReportJDBCDAO dao = new ArticleReportJDBCDAO();

		// 新增
//		ArticleReportVO articleReportVO1 = new ArticleReportVO();
//		articleReportVO1.setArticleNo(1);
//		articleReportVO1.setMemId(1001);
//		articleReportVO1.setRpReason("1");
//		articleReportVO1.setAdminChecked("1");
//		articleReportVO1.setNoRpReason("1");
//
//		dao.insert(articleReportVO1);

		// 修改
//		ArticleReportVO articleReportVO2 = new ArticleReportVO();
//		articleReportVO2.setReportNo(2);
//		articleReportVO2.setArticleNo(1);
//		articleReportVO2.setMemId(1001);
//		articleReportVO2.setRpReason("kokok");
//		articleReportVO2.setAdminChecked("1");
//		articleReportVO2.setNoRpReason("jiji");
//		dao.update(articleReportVO2);


		// 刪除
//		dao.delete(3);
//
		// 查詢
		ArticleReportVO articleReportVO3 = dao.findByPrimaryKey(3);
		System.out.print(articleReportVO3.getReportNo() + ",");
		System.out.print(articleReportVO3.getArticleNo() + ",");
		System.out.print(articleReportVO3.getMemId() + ",");
		System.out.print(articleReportVO3.getRpReason() + ",");
		System.out.print(sdf.format(articleReportVO3.getRpTime()) + ",");
		System.out.print(articleReportVO3.getAdminChecked() + ",");
		System.out.print(articleReportVO3.getNoRpReason() + ",");

		System.out.println("---------------------");

		// 查詢
//		List<ArticleReportVO> list = dao.getAll();
//		for (ArticleReportVO aArticleReport : list) {
//			System.out.print(aArticleReport.getReportNo() + ",");
//			System.out.print(aArticleReport.getArticleNo() + ",");
//			System.out.print(aArticleReport.getMemId() + ",");
//			System.out.print(aArticleReport.getRpReason() + ",");
//			System.out.print(sdf.format(aArticleReport.getRpTime()) + ",");
//			System.out.print(aArticleReport.getAdminChecked() + ",");
//			System.out.print(aArticleReport.getNoRpReason() + ",");
//			System.out.println();
//		}
	}
}