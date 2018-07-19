package com.groupMem.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class GroupMemJDBCDAO implements GroupMemDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "test";
	String passwd = "123456";

	private static final String INSERT = "INSERT INTO groupMem (tripNo,memId,isLeader) VALUES (?,?,?)";
	private static final String DELETE = "DELETE FROM groupMem where tripNo = ? AND memId=?";
	private static final String UPDATE = "UPDATE groupMem set isLeader=? where tripNo=? AND memId=?";
	private static final String GET_ONE_STMT = "SELECT * FROM groupMem where tripNo=? AND memId=?";
	private static final String GET_ALL_STMT = "SELECT * FROM groupMem ORDER BY tripNo";

	@Override
	public void insert(GroupMemVO groupMemVO) {
		try {
			Connection con = null;
			PreparedStatement pstmt = null;

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT);

			pstmt.setInt(1, groupMemVO.getTripNo());
			pstmt.setInt(2, groupMemVO.getMemId());
			pstmt.setString(3, groupMemVO.getIsLeader());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(GroupMemVO groupMemVO) {
		try {
			Connection con = null;
			PreparedStatement pstmt = null;

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, groupMemVO.getIsLeader());
			pstmt.setInt(2, groupMemVO.getTripNo());
			pstmt.setInt(3, groupMemVO.getMemId());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Integer tripNo, Integer memId) {
		try {
			Connection con = null;
			PreparedStatement pstmt = null;

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, tripNo);
			pstmt.setInt(2, memId);

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public GroupMemVO findByPrimaryKey(Integer tripNo, Integer memId) {
		GroupMemVO groupMemVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, tripNo);
			pstmt.setInt(2, memId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				groupMemVO = new GroupMemVO();
				groupMemVO.setTripNo(rs.getInt("tripNo"));
				groupMemVO.setMemId(rs.getInt("memId"));
				groupMemVO.setIsLeader(rs.getString("isLeader"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return groupMemVO;

	}

	@Override
	public List<GroupMemVO> getAll() {
		List<GroupMemVO> list = new ArrayList<GroupMemVO>();
		GroupMemVO groupMemVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				groupMemVO = new GroupMemVO();
				groupMemVO.setTripNo(rs.getInt("tripNo"));
				groupMemVO.setMemId(rs.getInt("memId"));
				groupMemVO.setIsLeader(rs.getString("isLeader"));
				list.add(groupMemVO);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void main(String[] args) throws IOException, ParseException {

		GroupMemJDBCDAO dao = new GroupMemJDBCDAO();
		// 插入資料一

		// GroupMemVO groupMemVO1 = new GroupMemVO();
		//
		// groupMemVO1.setTripNo(1002);
		// groupMemVO1.setMemId(2003);
		// groupMemVO1.setIsLeader("1");
		//
		// dao.insert(groupMemVO1);
		//
		// // 插入資料二
		//
		// GroupMemVO groupMemVO2 = new GroupMemVO();
		//
		// groupMemVO2.setTripNo(1002);
		// groupMemVO2.setMemId(2004);
		// groupMemVO2.setIsLeader("0");
		//
		// dao.insert(groupMemVO2);
		//
		// 插入資料三

		// GroupMemVO groupMemVO3 = new GroupMemVO();
		//
		// groupMemVO3.setTripNo(1002);
		// groupMemVO3.setMemId(2005);
		// groupMemVO3.setIsLeader("0");
		//
		// dao.insert(groupMemVO3);

		// // 刪除資料

		// dao.delete(1002,2005);

		// 找尋一筆資料

		// GroupMemVO groupMemVO4 = dao.findByPrimaryKey(1001, 2001);
		// System.out.println(groupMemVO4.getTripNo());
		// System.out.println(groupMemVO4.getMemId());
		// System.out.println(groupMemVO4.getIsLeader());
		// System.out.println("---------------------");

		// 找尋所有資料
		// List<GroupMemVO> list = dao.getAll();
		// for (GroupMemVO groupMemVO : list) {
		// System.out.print(groupMemVO.getTripNo() + ",");
		// System.out.print(groupMemVO.getMemId() + ",");
		// System.out.println(groupMemVO.getIsLeader());
		// System.out.println("---------------------");
		// }
	}
}
