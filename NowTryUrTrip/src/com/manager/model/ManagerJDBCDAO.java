package com.manager.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManagerJDBCDAO implements ManagerDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "AA107G5";
	String passwd = "AA107G5";

	private static final String INSERT_STMT = "INSERT INTO manager (mgrId,mgrName,mgrAccount,mgrPsw,mgrIdentity,mgrStatus) VALUES (manager_seq.NEXTVAL,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT mgrId,mgrName,mgrAccount,mgrPsw,mgrIdentity,mgrStatus FROM manager order by mgrid";
	private static final String GET_ONE_STMT = "SELECT mgrId,mgrName,mgrAccount,mgrPsw,mgrIdentity,mgrStatus FROM manager where mgrid = ?";
	private static final String DELETE = "DELETE FROM manager where mgrid = ?";
	private static final String UPDATE = "UPDATE manager set mgrName=?,mgrAccount=?,mgrPsw=?,mgrIdentity=?,mgrStatus=? where mgrId=?";

	@Override
	public void insert(ManagerVO managerVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, managerVO.getMgrName());
			pstmt.setString(2, managerVO.getMgrAccount());
			pstmt.setString(3, managerVO.getMgrPsw());
			pstmt.setString(4, managerVO.getMgrIdentity());
			pstmt.setInt(5, managerVO.getMgrStatus());
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
	public void update(ManagerVO managerVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, managerVO.getMgrName());
			pstmt.setString(2, managerVO.getMgrAccount());
			pstmt.setString(3, managerVO.getMgrPsw());
			pstmt.setString(4, managerVO.getMgrIdentity());
			pstmt.setInt(5, managerVO.getMgrStatus());
			pstmt.setInt(6, managerVO.getMgrId());
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
	public void delete(Integer mgrId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, mgrId);
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
	public ManagerVO findByPrimaryKey(Integer mgrId) {
		ManagerVO managerVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

		ManagerJDBCDAO dao = new ManagerJDBCDAO();

		// 新增

		 ManagerVO managerVO1 = new ManagerVO();
		
		 managerVO1.setMgrName("愛搗蛋");
		 managerVO1.setMgrAccount("HIHI");
		 managerVO1.setMgrPsw("123");
		 managerVO1.setMgrIdentity("4");
		 managerVO1.setMgrStatus(3);
		 dao.insert(managerVO1);
		 System.out.println("新增完成");

		// 修改
		//
		// ManagerVO managerVO2 = new ManagerVO();
		// managerVO2.setMgrName("愛搗蛋");
		// managerVO2.setMgrAccount("HIHI");
		// managerVO2.setMgrPsw("123");
		// managerVO2.setMgrIdentity("4");
		// managerVO2.setMgrStatus(3);
		// managerVO2.setMgrId(7003);
		// dao.update(managerVO2);
		// System.out.println("修改成功");

		// 查詢
//		ManagerVO managerVO3 = dao.findByPrimaryKey(001);
//		System.out.print(managerVO3.getMgrId() + ",");
//		System.out.print(managerVO3.getMgrName() + ",");
//		System.out.print(managerVO3.getMgrAccount() + ",");
//		System.out.print(managerVO3.getMgrPsw() + ",");
//		System.out.print(managerVO3.getMgrIdentity() + ",");
//		System.out.println(managerVO3.getMgrStatus() + ",");
//		System.out.println("---------------------");
//
//		// 查詢
//		List<ManagerVO> list = dao.getAll();
//		for (ManagerVO amember : list) {
//			System.out.print(amember.getMgrId() + ",");
//			System.out.print(amember.getMgrName() + ",");
//			System.out.print(amember.getMgrAccount() + ",");
//			System.out.print(amember.getMgrPsw() + ",");
//			System.out.print(amember.getMgrIdentity() + ",");
//			System.out.print(amember.getMgrStatus() + ",");
//			System.out.println();
//
//		}
	}

	@Override
	public ManagerVO findManagerAccount(String mgrAccount) {
		// TODO Auto-generated method stub
		return null;
	}
}
