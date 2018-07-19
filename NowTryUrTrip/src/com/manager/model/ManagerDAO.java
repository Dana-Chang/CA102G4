package com.manager.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ManagerDAO implements ManagerDAO_interface {
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

	private static final String INSERT_STMT = "INSERT INTO manager (mgrId,mgrName,mgrAccount,mgrPsw,mgrIdentity,mgrStatus) VALUES (manager_seq.NEXTVAL,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT mgrId,mgrName,mgrAccount,mgrPsw,mgrIdentity,mgrStatus FROM manager order by mgrid";
	private static final String GET_ONE_STMT = "SELECT mgrId,mgrName,mgrAccount,mgrPsw,mgrIdentity,mgrStatus FROM manager where mgrid = ?";
	private static final String DELETE = "DELETE FROM manager where mgrid = ?";
	private static final String UPDATE = "UPDATE manager set mgrName=?,mgrAccount=?,mgrPsw=?,mgrIdentity=?,mgrStatus=? where mgrId=?";
	private static final String GET_MANGER_ACCOUNT = "SELECT mgrId,mgrName,mgrAccount,mgrPsw,mgrIdentity,mgrStatus FROM manager where mgrAccount = ?";

	@Override
	public void insert(ManagerVO managerVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, managerVO.getMgrName());
			pstmt.setString(2, managerVO.getMgrAccount());
			pstmt.setString(3, managerVO.getMgrPsw());
			pstmt.setString(4, managerVO.getMgrIdentity());
			pstmt.setInt(5, managerVO.getMgrStatus());
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
	public void update(ManagerVO managerVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, managerVO.getMgrName());
			pstmt.setString(2, managerVO.getMgrAccount());
			pstmt.setString(3, managerVO.getMgrPsw());
			pstmt.setString(4, managerVO.getMgrIdentity());
			pstmt.setInt(5, managerVO.getMgrStatus());
			pstmt.setInt(6, managerVO.getMgrId());
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
	public void delete(Integer mgrId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, mgrId);
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
	public ManagerVO findByPrimaryKey(Integer mgrId) {
		ManagerVO managerVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, mgrId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				managerVO = new ManagerVO();
				managerVO.setMgrId(rs.getInt("mgrId"));
				managerVO.setMgrName(rs.getString("mgrName"));
				managerVO.setMgrAccount(rs.getString("mgrAccount"));
				managerVO.setMgrPsw(rs.getString("mgrPsw"));
				managerVO.setMgrIdentity(rs.getString("mgrIdentity"));
				managerVO.setMgrStatus(rs.getInt("mgrStatus"));

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
		return managerVO;
	}

	@Override
	public List<ManagerVO> getAll() {
		List<ManagerVO> list = new ArrayList<ManagerVO>();
		ManagerVO managerVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				managerVO = new ManagerVO();
				managerVO.setMgrId(rs.getInt("mgrId"));
				managerVO.setMgrName(rs.getString("mgrName"));
				managerVO.setMgrAccount(rs.getString("mgrAccount"));
				managerVO.setMgrPsw(rs.getString("mgrPsw"));
				managerVO.setMgrIdentity(rs.getString("mgrIdentity"));
				managerVO.setMgrStatus(rs.getInt("mgrStatus"));
				list.add(managerVO);

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

	@Override
	public ManagerVO findManagerAccount(String mgrAccount) {
		ManagerVO managerVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MANGER_ACCOUNT);

			pstmt.setString(1, mgrAccount);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				managerVO = new ManagerVO();
				managerVO.setMgrId(rs.getInt("mgrId"));
				managerVO.setMgrName(rs.getString("mgrName"));
				managerVO.setMgrAccount(rs.getString("mgrAccount"));
				managerVO.setMgrPsw(rs.getString("mgrPsw"));
				managerVO.setMgrIdentity(rs.getString("mgrIdentity"));
				managerVO.setMgrStatus(rs.getInt("mgrStatus"));

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
		return managerVO;
	}
}
