package com.pointTransaction.model;

import java.util.*;

import com.member.model.MemberVO;

import java.sql.*;

public class PointTransactionJDBCDAO implements PointTransactionDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "AA107G5";
	String passwd = "AA107G5";

	private static final String INSERT_STMT = 
		"INSERT INTO PointTransaction (transactionNo,memId,startPoint,startCash,transactionDescription,changeCash,transactionTime,changePoint) VALUES (PointTransaction_seq.NEXTVAL, ?, ?, ?, ?, ?,sysdate, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT transactionNo,memId,startPoint,startCash,transactionDescription,changeCash,transactionTime,changePoint FROM PointTransaction order by transactionNo";
	private static final String GET_ONE_STMT = 
		"SELECT transactionNo,memId,startPoint,startCash,transactionDescription,changeCash,transactionTime,changePoint FROM PointTransaction where transactionNo = ?";
	private static final String DELETE = 
		"DELETE FROM PointTransaction where transactionNo = ?";
	private static final String UPDATE = 
		"UPDATE PointTransaction set memId=?, startPoint=?, startCash=?, transactionDescription=?, changeCash=?, changePoint=? where transactionNo = ?";

	@Override
	public void insert(PointTransactionVO pointTransactionVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, pointTransactionVO.getMemId());
			pstmt.setInt(2, pointTransactionVO.getStartPoint());
			pstmt.setInt(3, pointTransactionVO.getStartCash());
			pstmt.setString(4, pointTransactionVO.getTransactionDescription());
			pstmt.setInt(5, pointTransactionVO.getChangeCash());
//			pstmt.setString(7, pointTransactionVO.getTransactionTime());
			pstmt.setInt(6, pointTransactionVO.getChangePoint());
			

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
	public void update(PointTransactionVO pointTransactionVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, pointTransactionVO.getMemId());
			pstmt.setInt(2, pointTransactionVO.getStartPoint());
			pstmt.setInt(3, pointTransactionVO.getStartCash());
			pstmt.setString(4, pointTransactionVO.getTransactionDescription());
			pstmt.setInt(5, pointTransactionVO.getChangeCash());
//			pstmt.setString(7, pointTransactionVO.getTransactionTime());
			pstmt.setInt(6, pointTransactionVO.getChangePoint());
			pstmt.setInt(7, pointTransactionVO.getTransactionNo());

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
	public void delete(Integer transactionNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, transactionNo);

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
	public PointTransactionVO findByPrimaryKey(Integer transactionNo) {

		PointTransactionVO pointTransactionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, transactionNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// PointTransactionVO 也稱為 Domain objects
				pointTransactionVO = new PointTransactionVO();
				pointTransactionVO.setTransactionNo(rs.getInt("transactionNo"));
				pointTransactionVO.setMemId(rs.getInt("memId"));
				pointTransactionVO.setStartPoint(rs.getInt("startPoint"));
				pointTransactionVO.setStartCash(rs.getInt("startCash"));
				pointTransactionVO.setTransactionDescription(rs.getString("transactionDescription"));
				pointTransactionVO.setChangeCash(rs.getInt("changeCash"));
				pointTransactionVO.setTransactionTime(rs.getTimestamp("transactionTime"));
				pointTransactionVO.setChangePoint(rs.getInt("changePoint"));
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
		return pointTransactionVO;
	}

	@Override
	public List<PointTransactionVO> getAll() {
		List<PointTransactionVO> list = new ArrayList<PointTransactionVO>();
		PointTransactionVO pointTransactionVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// PointTransactionVO 也稱為 Domain objects
				pointTransactionVO = new PointTransactionVO();
				pointTransactionVO.setTransactionNo(rs.getInt("transactionNo"));
				pointTransactionVO.setMemId(rs.getInt("memId"));
				pointTransactionVO.setStartPoint(rs.getInt("startPoint"));
				pointTransactionVO.setStartCash(rs.getInt("startCash"));
				pointTransactionVO.setTransactionDescription(rs.getString("transactionDescription"));
				pointTransactionVO.setChangeCash(rs.getInt("changeCash"));
				pointTransactionVO.setTransactionTime(rs.getTimestamp("transactionTime"));
				pointTransactionVO.setChangePoint(rs.getInt("changePoint"));
				list.add(pointTransactionVO); // Store the row in the list
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

		PointTransactionJDBCDAO dao = new PointTransactionJDBCDAO();

		// 新增
		PointTransactionVO pointTransactionVO1 = new PointTransactionVO();
		pointTransactionVO1.setMemId(7003);
		pointTransactionVO1.setStartPoint(200);
		pointTransactionVO1.setStartCash(300);		
		pointTransactionVO1.setTransactionDescription("測試除點");
		pointTransactionVO1.setChangeCash(70);
//		pointTransactionVO1.setTransactionTime();
		pointTransactionVO1.setChangePoint(10);
		dao.insert(pointTransactionVO1);

		// 修改
		PointTransactionVO pointTransactionVO2 = new PointTransactionVO();
		pointTransactionVO2.setMemId(7003);
		pointTransactionVO2.setStartPoint(600);
		pointTransactionVO2.setStartCash(700);		
		pointTransactionVO2.setTransactionDescription("測試兌換");
		pointTransactionVO2.setChangeCash(70);
//		pointTransactionVO2.setTransactionTime();
		pointTransactionVO2.setChangePoint(105);
		pointTransactionVO2.setTransactionNo(1);
		dao.update(pointTransactionVO2);

		// 刪除
		dao.delete(2);

		// 查詢單一
		PointTransactionVO pointTransactionVO3 = dao.findByPrimaryKey(1);
		System.out.print(pointTransactionVO3.getTransactionNo() + ",");
		System.out.print(pointTransactionVO3.getMemId() + ",");
		System.out.print(pointTransactionVO3.getStartPoint() + ",");
		System.out.print(pointTransactionVO3.getStartCash() + ",");
		System.out.print(pointTransactionVO3.getTransactionDescription() + ",");
		System.out.print(pointTransactionVO3.getChangeCash()+ ",");
		System.out.print(pointTransactionVO3.getTransactionTime()+ ",");
		System.out.println(pointTransactionVO3.getChangePoint());
		
		System.out.println("---------------------");

		// 查詢ALL
		List<PointTransactionVO> list = dao.getAll();
		for (PointTransactionVO aPointTransaction : list) {
			System.out.print(aPointTransaction.getTransactionNo() + ",");
			System.out.print(aPointTransaction.getMemId() + ",");
			System.out.print(aPointTransaction.getStartPoint() + ",");
			System.out.print(aPointTransaction.getTransactionDescription() + ",");
			System.out.print(aPointTransaction.getChangeCash()+ ",");
			System.out.print(aPointTransaction.getTransactionTime()+ ",");
			System.out.print(aPointTransaction.getChangePoint());
			System.out.println();
		}
	}

	@Override
	public void insert(PointTransactionVO pointTransactionVO, MemberVO memberVO) {
		// TODO Auto-generated method stub
		
	}


}