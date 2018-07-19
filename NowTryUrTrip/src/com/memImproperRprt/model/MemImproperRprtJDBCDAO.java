package com.memImproperRprt.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemImproperRprtJDBCDAO implements MemImproperRprtDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "AA107";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO memImproperRprt (irsnumber,violationId,reportNumber) VALUES (NotPr_seq.NEXTVAL,?,report_seq.NEXTVAL)";
	private static final String GET_ALL_STMT = "SELECT irsnumber,violationId,reportNumber FROM memImproperRprt order by irsnumber";
	private static final String GET_ONE_STMT = "SELECT irsnumber,violationId,reportNumber FROM memImproperRprt where irsnumber = ?";
	private static final String DELETE = "DELETE FROM memImproperRprt where irsnumber = ?";
	private static final String UPDATE = "UPDATE memImproperRprt set violationId=?,reportNumber=? where irsnumber=?";

	@Override
	public void insert(MemImproperRprtVO memImproperRprtVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, memImproperRprtVO.getViolationId());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return;
	}

	@Override
	public void update(MemImproperRprtVO memImproperRprtVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, memImproperRprtVO.getViolationId());
			pstmt.setInt(2, memImproperRprtVO.getReportNumber());
			pstmt.setInt(3, memImproperRprtVO.getIrsnumber());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return;
	}

	@Override
	public void delete(Integer irsnumber) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, irsnumber);
			pstmt.executeUpdate();
			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			// Handle any driver errors

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return;
	}

	@Override
	public MemImproperRprtVO findByPrimaryKey(Integer irsnumber) {
		MemImproperRprtVO memImproperRprtVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, irsnumber);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				memImproperRprtVO = new MemImproperRprtVO();
				memImproperRprtVO.setIrsnumber(rs.getInt("irsnumber"));
				memImproperRprtVO.setViolationId(rs.getInt("violationId"));
				memImproperRprtVO.setReportNumber(rs.getInt("reportNumber"));

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return memImproperRprtVO;
	}

	@Override
	public List<MemImproperRprtVO> getAll() {
		List<MemImproperRprtVO> list = new ArrayList<MemImproperRprtVO>();
		MemImproperRprtVO memImproperRprtVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memImproperRprtVO = new MemImproperRprtVO();
				memImproperRprtVO.setIrsnumber(rs.getInt("irsnumber"));
				memImproperRprtVO.setViolationId(rs.getInt("violationId"));
				memImproperRprtVO.setReportNumber(rs.getInt("reportNumber"));
				list.add(memImproperRprtVO);

			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	public static void main(String[] args) throws IOException {

		MemImproperRprtJDBCDAO dao = new MemImproperRprtJDBCDAO();

		// // 新增
		// MemImproperRprtVO memImproperRprtVO1 = new MemImproperRprtVO();
		// memImproperRprtVO1.setViolationId(1);
		// dao.insert(memImproperRprtVO1);
		// System.out.println("新增完成");

		// 修改
		// MemImproperRprtVO memImproperRprtVO2 = new MemImproperRprtVO();
		// memImproperRprtVO2.setIrsnumber(1);
		// memImproperRprtVO2.setViolationId(1);
		// memImproperRprtVO2.setReportNumber(3);
		// dao.update(memImproperRprtVO2);
		// System.out.println("修改成功");

		// 查詢
		 MemImproperRprtVO memImproperRprtVO3 = dao.findByPrimaryKey(1);
		 System.out.print(memImproperRprtVO3.getIrsnumber() + ",");
		 System.out.print(memImproperRprtVO3.getViolationId() + ",");
		 System.out.println(memImproperRprtVO3.getReportNumber() + ",");
		 System.out.println("---------------------");
		
		 List<MemImproperRprtVO> list = dao.getAll();
		 for (MemImproperRprtVO amember : list) {
		 System.out.print(amember.getIrsnumber() + ",");
		 System.out.print(amember.getViolationId() + ",");
		 System.out.print(amember.getReportNumber() + ",");
		 System.out.println();
		 }
	}
}
