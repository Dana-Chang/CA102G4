package com.storeInf.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class StoreInfDAO implements StoreInfDAO_interface {
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

	private static final String INSERT_STMT = "INSERT INTO storeInf (memId,uniformNum,principal,storCall,storAdd,postalCode,category) VALUES (?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT memId,uniformNum,principal,storCall,storAdd,postalCode,category FROM storeInf order by memid";
	private static final String GET_ONE_STMT = "SELECT memId,uniformNum,principal,storCall,storAdd,postalCode,category FROM storeInf where memid = ?";
	private static final String DELETE = "DELETE FROM storeInf where memid = ?";
	private static final String UPDATE = "UPDATE storeInf set uniformNum=?,principal=?,storCall=?,storAdd=?,postalCode=?,category=? where memId=?";

	@Override
	public void insert(StoreInfVO storeInfVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, storeInfVO.getMemId());
			pstmt.setInt(2, storeInfVO.getUniformNum());
			pstmt.setString(3, storeInfVO.getPrincipal());
			pstmt.setString(4, storeInfVO.getStorCall());
			pstmt.setString(5, storeInfVO.getStorAdd());
			pstmt.setInt(6, storeInfVO.getPostalCode());
			pstmt.setInt(7, storeInfVO.getCategory());
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
	public void update(StoreInfVO storeInfVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, storeInfVO.getUniformNum());
			pstmt.setString(2, storeInfVO.getPrincipal());
			pstmt.setString(3, storeInfVO.getStorCall());
			pstmt.setString(4, storeInfVO.getStorAdd());
			pstmt.setInt(5, storeInfVO.getPostalCode());
			pstmt.setInt(6, storeInfVO.getCategory());
			pstmt.setInt(7, storeInfVO.getMemId());
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
	public void delete(Integer memId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memId);
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
	public StoreInfVO findByPrimaryKey(Integer memid) {
		StoreInfVO storeInfVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, memid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				storeInfVO = new StoreInfVO();
				storeInfVO.setMemId(rs.getInt("memId"));
				storeInfVO.setUniformNum(rs.getInt("uniformNum"));
				storeInfVO.setPrincipal(rs.getString("principal"));
				storeInfVO.setStorCall(rs.getString("storCall"));
				storeInfVO.setStorAdd(rs.getString("storAdd"));
				storeInfVO.setPostalCode(rs.getInt("postalCode"));
				storeInfVO.setCategory(rs.getInt("category"));

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
		return storeInfVO;
	}

	@Override
	public List<StoreInfVO> getAll() {
		List<StoreInfVO> list = new ArrayList<StoreInfVO>();
		StoreInfVO storeInfVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				storeInfVO = new StoreInfVO();
				storeInfVO.setMemId(rs.getInt("memId"));
				storeInfVO.setUniformNum(rs.getInt("uniformNum"));
				storeInfVO.setPrincipal(rs.getString("principal"));
				storeInfVO.setStorCall(rs.getString("storCall"));
				storeInfVO.setStorAdd(rs.getString("storAdd"));
				storeInfVO.setPostalCode(rs.getInt("postalCode"));
				storeInfVO.setCategory(rs.getInt("category"));
				list.add(storeInfVO);

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
	public void insert2(StoreInfVO storeInfVO, Connection con) {
		PreparedStatement pstmt = null;
		try {

			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, storeInfVO.getMemId());
			pstmt.setInt(2, storeInfVO.getUniformNum());
			pstmt.setString(3, storeInfVO.getPrincipal());
			pstmt.setString(4, storeInfVO.getStorCall());
			pstmt.setString(5, storeInfVO.getStorAdd());
			pstmt.setInt(6, storeInfVO.getPostalCode());
			pstmt.setInt(7, storeInfVO.getCategory());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-emp");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
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
		}

	}
}
