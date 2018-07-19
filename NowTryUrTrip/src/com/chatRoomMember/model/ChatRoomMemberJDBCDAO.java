package com.chatRoomMember.model;

import java.util.*;
import java.sql.*;

public class ChatRoomMemberJDBCDAO implements ChatRoomMemberDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "tylou";
	String passwd = "123321";

	private static final String INSERT_STMT = 
		"INSERT INTO chatRoomMember (memId,chRoomNo,status) VALUES (?,?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT memId,chRoomNo,status FROM chatRoomMember order by memId";
	private static final String GET_ONE_STMT = 
		"SELECT memId,chRoomNo,status FROM chatRoomMember where memId = ? and chRoomNo = ? ";
	private static final String DELETE = 
		"DELETE FROM chatRoomMember where memId = ? and chRoomNo = ? ";
	private static final String UPDATE = 
		"UPDATE chatRoomMember set status=? where memId = ? and chRoomNo = ? ";

	@Override
	public void insert(ChatRoomMemberVO chatRoomMemberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, chatRoomMemberVO.getMemId());
			pstmt.setInt(2, chatRoomMemberVO.getChRoomNo());
			pstmt.setString(3, chatRoomMemberVO.getStatus());

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
	public void update(ChatRoomMemberVO chatRoomMemberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, chatRoomMemberVO.getStatus());
			pstmt.setInt(2, chatRoomMemberVO.getMemId());
			pstmt.setInt(3, chatRoomMemberVO.getChRoomNo());
	
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
	public void delete(Integer memId,Integer chRoomNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memId);
			pstmt.setInt(2, chRoomNo);

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
	public ChatRoomMemberVO findByPrimaryKey(Integer memId,Integer chRoomNo) {

		ChatRoomMemberVO articleReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, memId);
			pstmt.setInt(2, chRoomNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 查詢單筆
				articleReportVO = new ChatRoomMemberVO();
				articleReportVO.setMemId(rs.getInt("memId"));
				articleReportVO.setChRoomNo(rs.getInt("chRoomNo"));
				articleReportVO.setStatus(rs.getString("status"));

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
	public List<ChatRoomMemberVO> getAll() {
		List<ChatRoomMemberVO> list = new ArrayList<ChatRoomMemberVO>();
		ChatRoomMemberVO chatRoomMemberVO = null;

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
				chatRoomMemberVO = new ChatRoomMemberVO();
				
				chatRoomMemberVO = new ChatRoomMemberVO();
				chatRoomMemberVO.setMemId(rs.getInt("memId"));
				chatRoomMemberVO.setChRoomNo(rs.getInt("chRoomNo"));
				chatRoomMemberVO.setStatus(rs.getString("status"));

				list.add(chatRoomMemberVO); // Store the row in the list
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

		ChatRoomMemberJDBCDAO dao = new ChatRoomMemberJDBCDAO();

		// 新增
//		ChatRoomMemberVO chatRoomMemberVO1 = new ChatRoomMemberVO();
//		chatRoomMemberVO1.setMemId(1001);
//		chatRoomMemberVO1.setChRoomNo(2);
//		chatRoomMemberVO1.setStatus("1");
//
//		dao.insert(chatRoomMemberVO1);

		// 修改
//		ChatRoomMemberVO chatRoomMemberVO2 = new ChatRoomMemberVO();
//		chatRoomMemberVO2.setMemId(1002);
//		chatRoomMemberVO2.setChRoomNo(2);
//		chatRoomMemberVO2.setStatus("0");
//
//		dao.update(chatRoomMemberVO2);


		// 刪除
//		dao.delete(1001,1);

		// 查詢
//		ChatRoomMemberVO chatRoomMemberVO3 = dao.findByPrimaryKey(1002,2);
//		System.out.print(chatRoomMemberVO3.getMemId() + ",");
//		System.out.print(chatRoomMemberVO3.getChRoomNo() + ",");
//		System.out.print(chatRoomMemberVO3.getStatus() + ",");
//
//		System.out.println("---------------------");

//		// 查詢
//		List<ChatRoomMemberVO> list = dao.getAll();
//		for (ChatRoomMemberVO achatRoomMemberVO : list) {
//			System.out.print(achatRoomMemberVO.getMemId() + ",");
//			System.out.print(achatRoomMemberVO.getChRoomNo() + ",");
//			System.out.print(achatRoomMemberVO.getStatus() + ",");
//			System.out.println();
//		}
	}
}