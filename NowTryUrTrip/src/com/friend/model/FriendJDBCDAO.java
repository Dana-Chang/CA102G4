package com.friend.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FriendJDBCDAO implements FriendDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "AA107G5";
	String passwd = "AA107G5";

	private static final String INSERT_STMT = "INSERT INTO friend (memId,friendId,friendRp) VALUES (?,?,?)";
	private static final String GET_ALL_STMT = "SELECT memId,friendId,friendRp FROM friend order by memid";
	private static final String GET_ONE_STMT = "SELECT memId,friendId,friendRp FROM friend where memid = ?";
	private static final String DELETE = "DELETE FROM friend where memid = ? and friendId = ?";
	private static final String UPDATE = "UPDATE friend set friendRp=? where memId=? and friendId=?";

	@Override
	public void insert(FriendVO friendVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, friendVO.getMemId());
			pstmt.setInt(2, friendVO.getFriendId());
			pstmt.setInt(3, friendVO.getFriendRp());
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
	public void update(FriendVO friendVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, friendVO.getMemId());
			pstmt.setInt(2, friendVO.getFriendId());
			pstmt.setInt(3, friendVO.getFriendRp());
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
	public void delete(Integer memId, Integer friendId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memId);
			pstmt.setInt(2, friendId);
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
	public List<FriendVO> findByPrimaryKey(Integer memid) {
		List<FriendVO> list = new ArrayList<FriendVO>();
		FriendVO friendVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, memid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				friendVO = new FriendVO();
				friendVO.setMemId(rs.getInt("memId"));
				friendVO.setFriendId(rs.getInt("friendId"));
				friendVO.setFriendRp(rs.getInt("friendRp"));
				list.add(friendVO);
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

	@Override
	public List<FriendVO> getAll() {
		List<FriendVO> list = new ArrayList<FriendVO>();
		FriendVO friendVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				friendVO = new FriendVO();
				friendVO.setMemId(rs.getInt("memId"));
				friendVO.setFriendId(rs.getInt("friendId"));
				friendVO.setFriendRp(rs.getInt("friendRp"));
				list.add(friendVO);

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

		FriendJDBCDAO dao = new FriendJDBCDAO();

		// 新增
		// FriendVO FriendVO1 = new FriendVO();
		// FriendVO1.setMemId(1002);
		// FriendVO1.setFriendId(1004);
		// FriendVO1.setFriendRp(1);
		//
		// dao.insert(FriendVO1);
		// System.out.println("新增完成");

		// 修改
		// FriendVO friendVO2 = new FriendVO();
		// friendVO2.setMemId(1001);
		// friendVO2.setFriendId(1003);
		// friendVO2.setFriendRp(2);
		// dao.update(friendVO2);
		// System.out.println("修改成功");

		// 刪除
		// dao.delete(1006,1005);
		// System.out.println("刪除成功");

		// 查詢
		List<FriendVO> lost = dao.findByPrimaryKey(1002);
		for (FriendVO amember : lost ){
		System.out.print(amember.getMemId() + ",");
		System.out.print(amember.getFriendId() + ",");
		System.out.println(amember.getFriendRp());
		System.out.println("---------------------");
		}
		// 查詢
		List<FriendVO> list = dao.getAll();
		for (FriendVO amember : list) {
			System.out.print(amember.getMemId() + ",");
			System.out.print(amember.getFriendId() + ",");
			System.out.print(amember.getFriendRp() + ",");
			System.out.println();

		}
	}
}