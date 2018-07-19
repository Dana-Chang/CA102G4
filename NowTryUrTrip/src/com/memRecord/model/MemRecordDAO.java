package com.memRecord.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemRecordDAO implements MemRecordDAO_interface {
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

	private static final String INSERT_STMT = "INSERT INTO memRecord (violationId,memId,isSuspended,suspCat,suspReason,suspStart,suspDays,rprtSrc,mgrId) VALUES (record_seq.NEXTVAL,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT violationId,memId,isSuspended,suspCat,suspReason,to_char(suspStart,'yyyy-mm-dd hh:mm:ss') suspStart,suspDays,rprtSrc,mgrId FROM memRecord order by violationId";
	private static final String GET_ONE_STMT = "SELECT violationId,memId,isSuspended,suspCat,suspReason,to_char(suspStart,'yyyy-mm-dd hh:mm:ss') suspStart,suspDays,rprtSrc,mgrId FROM memRecord where violationId = ?";
	private static final String DELETE = "DELETE FROM memRecord where violationId = ?";
	private static final String UPDATE = "UPDATE memRecord set memId=?,isSuspended=?,suspCat=?,suspReason=?,suspStart=?,suspDays=?,rprtSrc=?,mgrId=? where violationId=?";

	@Override
	public void insert(MemRecordVO memRecordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, memRecordVO.getMemId());
			pstmt.setInt(2, memRecordVO.getIsSuspended());
			pstmt.setString(3, memRecordVO.getSuspCat());
			pstmt.setString(4, memRecordVO.getSuspReason());
			pstmt.setTimestamp(5, memRecordVO.getSuspStart());
			pstmt.setInt(6, memRecordVO.getSuspDays());
			pstmt.setInt(7, memRecordVO.getRprtSrc());
			pstmt.setInt(8, memRecordVO.getMgrId());
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
	public void update(MemRecordVO memRecordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, memRecordVO.getMemId());
			pstmt.setInt(2, memRecordVO.getIsSuspended());
			pstmt.setString(3, memRecordVO.getSuspCat());
			pstmt.setString(4, memRecordVO.getSuspReason());
			pstmt.setTimestamp(5, memRecordVO.getSuspStart());
			pstmt.setInt(6, memRecordVO.getSuspDays());
			pstmt.setInt(7, memRecordVO.getRprtSrc());
			pstmt.setInt(8, memRecordVO.getMgrId());
			pstmt.setInt(9, memRecordVO.getViolationId());
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
	public void delete(Integer violationId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, violationId);
			pstmt.executeUpdate();
			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
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
	public MemRecordVO findByPrimaryKey(Integer violationId) {
		MemRecordVO memRecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, violationId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				memRecordVO = new MemRecordVO();
				memRecordVO.setViolationId(rs.getInt("violationId"));
				memRecordVO.setMemId(rs.getInt("memId"));
				memRecordVO.setIsSuspended(rs.getInt("isSuspended"));
				memRecordVO.setSuspCat(rs.getString("suspCat"));
				memRecordVO.setSuspReason(rs.getString("suspReason"));
				memRecordVO.setSuspStart(rs.getTimestamp("suspStart"));
				memRecordVO.setSuspDays(rs.getInt("suspDays"));
				memRecordVO.setRprtSrc(rs.getInt("rprtSrc"));
				memRecordVO.setMgrId(rs.getInt("mgrId"));

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
		return memRecordVO;
	}

	@Override
	public List<MemRecordVO> getAll() {
		List<MemRecordVO> list = new ArrayList<MemRecordVO>();
		MemRecordVO memRecordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memRecordVO = new MemRecordVO();
				memRecordVO.setViolationId(rs.getInt("violationId"));
				memRecordVO.setMemId(rs.getInt("memId"));
				memRecordVO.setIsSuspended(rs.getInt("isSuspended"));
				memRecordVO.setSuspCat(rs.getString("suspCat"));
				memRecordVO.setSuspReason(rs.getString("suspReason"));
				memRecordVO.setSuspStart(rs.getTimestamp("suspStart"));
				memRecordVO.setSuspDays(rs.getInt("suspDays"));
				memRecordVO.setRprtSrc(rs.getInt("rprtSrc"));
				memRecordVO.setMgrId(rs.getInt("mgrId"));
				list.add(memRecordVO);

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
