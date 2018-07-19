package com.tripCmnt.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TripCmntJDBCDAO implements TripCmntDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "AA107G5";
	String passwd = "AA107G5";

	private static String INSERT = "INSERT INTO tripCmnt (tripCmntNo, memId, tripNo, tripCmntContent, tripCmntTime, tripRate, tripCmntIsHdl, tripCmntIsBlocked, tripCmntBlockedReason) VALUES (tripCmntNo_seq.NEXTVAL,?,?,?,?,?,?,?,?)";
	private static String DELETE = "DELETE FROM tripCmnt where tripCmntNo = ?";
	private static String UPDATE = "UPDATE tripCmnt set memId = ?,tripNo = ?,tripCmntContent = ?,tripCmntTime = ?, tripRate=?, tripCmntIsHdl = ?,tripCmntIsBlocked = ?,tripCmntBlockedReason = ? where tripCmntNo = ?";
	private static String GET_ONE_STMT = "SELECT * FROM tripCmnt WHERE tripCmntNo = ?";
	private static String GET_ALL_STMT = "SELECT * FROM tripCmnt ORDER BY tripCmntNo";

	@Override
	public void insert(TripCmntVO tripCmntVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT);

			pstmt.setInt(1, tripCmntVO.getMemId());
			pstmt.setInt(2, tripCmntVO.getTripNo());
			pstmt.setString(3, tripCmntVO.getTripCmntContent());
			pstmt.setTimestamp(4, tripCmntVO.getTripCmntTime());
			pstmt.setInt(5, tripCmntVO.getTripRate());
			pstmt.setString(6, tripCmntVO.getTripCmntIsHdl());
			pstmt.setString(7, tripCmntVO.getTripCmntIsBlocked());
			pstmt.setString(8, tripCmntVO.getTripCmntBlockedReason());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void update(TripCmntVO tripCmntVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, tripCmntVO.getMemId());
			pstmt.setInt(2, tripCmntVO.getTripNo());
			pstmt.setString(3, tripCmntVO.getTripCmntContent());
			pstmt.setTimestamp(4, tripCmntVO.getTripCmntTime());
			pstmt.setInt(5, tripCmntVO.getTripRate());
			pstmt.setString(6, tripCmntVO.getTripCmntIsHdl());
			pstmt.setString(7, tripCmntVO.getTripCmntIsBlocked());
			pstmt.setString(8, tripCmntVO.getTripCmntBlockedReason());
			pstmt.setInt(9, tripCmntVO.getTripCmntNo());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void delete(Integer tripCmntNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, tripCmntNo);
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public TripCmntVO findByPrimaryKey(Integer tripCmntNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TripCmntVO tripCmntVO = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, tripCmntNo);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				tripCmntVO = new TripCmntVO();
				tripCmntVO.setTripCmntNo(rs.getInt("tripCmntNo"));
				tripCmntVO.setMemId(rs.getInt("memId"));
				tripCmntVO.setTripNo(rs.getInt("tripNo"));
				tripCmntVO.setTripCmntContent(rs.getString("tripCmntContent"));
				tripCmntVO.setTripCmntTime(rs.getTimestamp("tripCmntTime"));
				tripCmntVO.setTripRate(rs.getInt("tripRate"));
				tripCmntVO.setTripCmntIsHdl(rs.getString("tripCmntIsHdl"));
				tripCmntVO.setTripCmntIsBlocked(rs.getString("tripCmntIsBlocked"));
				tripCmntVO.setTripCmntBlockedReason(rs.getString("tripCmntBlockedReason"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return tripCmntVO;
	}

	@Override
	public List<TripCmntVO> getAll() {
		List<TripCmntVO> list = new ArrayList<TripCmntVO>();
		TripCmntVO tripCmntVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				tripCmntVO = new TripCmntVO();
				tripCmntVO.setTripCmntNo(rs.getInt("tripCmntNo"));
				tripCmntVO.setMemId(rs.getInt("memId"));
				tripCmntVO.setTripNo(rs.getInt("tripNo"));
				tripCmntVO.setTripCmntContent(rs.getString("tripCmntContent"));
				tripCmntVO.setTripCmntTime(rs.getTimestamp("tripCmntTime"));
				tripCmntVO.setTripRate(rs.getInt("tripRate"));
				tripCmntVO.setTripCmntIsHdl(rs.getString("tripCmntIsHdl"));
				tripCmntVO.setTripCmntIsBlocked(rs.getString("tripCmntIsBlocked"));
				tripCmntVO.setTripCmntBlockedReason(rs.getString("tripCmntBlockedReason"));

				list.add(tripCmntVO);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	public static void main(String[] args) throws IOException {

		TripCmntJDBCDAO dao = new TripCmntJDBCDAO();

		// 插入第一筆資料
//		TripCmntVO tripCmntVO1 = new TripCmntVO();
//
//		tripCmntVO1.setMemId(1);
//		tripCmntVO1.setTripNo(1);
//		tripCmntVO1.setTripCmntContent("i think NCU is a nice place!");
//		tripCmntVO1.setTripCmntTime(java.sql.Timestamp.valueOf("2017-4-16 13:12:11"));
//		tripCmntVO1.setTripRate(8);
//		tripCmntVO1.setTripCmntIsHdl("0");
//		tripCmntVO1.setTripCmntIsBlocked("0");
//		tripCmntVO1.setTripCmntBlockedReason("");
//
//		dao.insert(tripCmntVO1);
//
//		// 刪除一筆
//		dao.delete(3);
//
//		// 查詢一筆
//		TripCmntVO tripCmntVO3 = dao.findByPrimaryKey(1);
//		System.out.print(tripCmntVO3.getTripCmntNo() + ",");
//		System.out.print(tripCmntVO3.getMemId() + ",");
//		System.out.print(tripCmntVO3.getTripNo() + ",");
//		System.out.print(tripCmntVO3.getTripCmntContent() + ",");
//		System.out.print(tripCmntVO3.getTripCmntTime() + ",");
//		System.out.print(tripCmntVO3.getTripRate()+ ",");
//		System.out.print(tripCmntVO3.getTripCmntIsHdl() + ",");
//		System.out.print(tripCmntVO3.getTripCmntIsBlocked() + ",");
//		System.out.println(tripCmntVO3.getTripCmntBlockedReason() );
//
//		System.out.println("---------------------");
//
//		// 查詢所有
//		List<TripCmntVO> list = dao.getAll();
//		for (TripCmntVO tripCmnt : list) {
//			System.out.print(tripCmnt.getTripCmntNo() + ",");
//			System.out.print(tripCmnt.getMemId() + ",");
//			System.out.print(tripCmnt.getTripNo() + ",");
//			System.out.print(tripCmnt.getTripCmntContent() + ",");
//			System.out.print(tripCmnt.getTripCmntTime() + ",");
//			System.out.print(tripCmnt.getTripRate() + ",");
//			System.out.print(tripCmnt.getTripCmntIsHdl() + ",");
//			System.out.print(tripCmnt.getTripCmntIsBlocked() + ",");
//			System.out.println(tripCmnt.getTripCmntBlockedReason());
//
//			System.out.println("---------------------");
//		}
	}
}
