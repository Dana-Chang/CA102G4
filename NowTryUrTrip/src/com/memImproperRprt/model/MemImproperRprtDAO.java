package com.memImproperRprt.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemImproperRprtDAO implements MemImproperRprtDAO_interface {
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, memImproperRprtVO.getViolationId());
			pstmt.executeUpdate();
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, memImproperRprtVO.getViolationId());
			pstmt.setInt(2, memImproperRprtVO.getReportNumber());
			pstmt.setInt(3, memImproperRprtVO.getIrsnumber());
			pstmt.executeUpdate();
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, irsnumber);
			pstmt.executeUpdate();
			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			// Handle any driver errors

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

			con = ds.getConnection();
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memImproperRprtVO = new MemImproperRprtVO();
				memImproperRprtVO.setIrsnumber(rs.getInt("irsnumber"));
				memImproperRprtVO.setViolationId(rs.getInt("violationId"));
				memImproperRprtVO.setReportNumber(rs.getInt("reportNumber"));
				list.add(memImproperRprtVO);

			}
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
}
