package com.tripCmntRpt.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.spotCmntRpt.model.SpotCmntRptVO;

public class TripCmntRptJDBCDAO implements TripCmntRptDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "AA107G5";
	String passwd = "AA107G5";

	private static final String INSERT_STMT = "INSERT INTO tripCmntRpt (tripCmntRptNo, tripCmntNo,memId,rptReason,rptTime) VALUES (tripCmntRptNo_seq.NEXTVAL, ?,?,? ,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM tripCmntRpt order by tripCmntRptNo";
	private static final String GET_ONE_STMT = "SELECT * FROM tripCmntRpt where tripCmntRptNo = ?";
	private static final String DELETE = "DELETE FROM tripCmntRpt where tripCmntRptNo = ?";
	private static final String UPDATE = "UPDATE tripCmntRpt set tripCmntNo=?,memId=?,rptReason=?,rptTime=? where tripCmntRptNo = ?";
	private static final String GET_ALL_SORTED_STMT = "SELECT * FROM tripCmntRpt o join tripCmnt i on o.tripCmntNo = i.tripCmntNo order by i.tripCmntIsHdl, o.tripCmntNo, o.rptTime";

	@Override
	public void insert(TripCmntRptVO tripCmntRptVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, tripCmntRptVO.getTripCmntNo());
			pstmt.setInt(2, tripCmntRptVO.getMemId());
			pstmt.setString(3, tripCmntRptVO.getRptReason());
			pstmt.setTimestamp(4, tripCmntRptVO.getRptTime());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(TripCmntRptVO tripCmntRptVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, tripCmntRptVO.getTripCmntNo());
			pstmt.setInt(2, tripCmntRptVO.getMemId());
			pstmt.setString(3, tripCmntRptVO.getRptReason());
			pstmt.setTimestamp(4, tripCmntRptVO.getRptTime());
			pstmt.setInt(5, tripCmntRptVO.getTripCmntRptNo());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(Integer tripCmntRptNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, tripCmntRptNo);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public TripCmntRptVO findByPrimaryKey(Integer tripCmntRptNo) {
		TripCmntRptVO tripCmntRptVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, tripCmntRptNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				tripCmntRptVO = new TripCmntRptVO();
				tripCmntRptVO.setTripCmntRptNo(rs.getInt("tripCmntRptNo"));
				tripCmntRptVO.setTripCmntNo(rs.getInt("tripCmntNo"));
				tripCmntRptVO.setMemId(rs.getInt("memId"));
				tripCmntRptVO.setRptReason(rs.getString("rptReason"));
				tripCmntRptVO.setRptTime(rs.getTimestamp("rptTime"));
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return tripCmntRptVO;
	}

	@Override
	public List<TripCmntRptVO> getAll() {
		TripCmntRptVO tripCmntRptVO = null;
		List<TripCmntRptVO> list = new ArrayList<TripCmntRptVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				tripCmntRptVO = new TripCmntRptVO();
				tripCmntRptVO.setTripCmntRptNo(rs.getInt("tripCmntRptNo"));
				tripCmntRptVO.setTripCmntNo(rs.getInt("tripCmntNo"));
				tripCmntRptVO.setMemId(rs.getInt("memId"));
				tripCmntRptVO.setRptReason(rs.getString("rptReason"));
				tripCmntRptVO.setRptTime(rs.getTimestamp("rptTime"));
				list.add(tripCmntRptVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<TripCmntRptVO> getAllSorted() {
		List<TripCmntRptVO> list = new ArrayList<TripCmntRptVO>();

		TripCmntRptVO tripCmntRptVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_SORTED_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// spotCmntRprtVO 也稱為 Domain objects
				tripCmntRptVO = new TripCmntRptVO();
				tripCmntRptVO.setTripCmntRptNo(rs.getInt("tripCmntRptNo"));
				tripCmntRptVO.setTripCmntNo(rs.getInt("tripCmntNo"));
				tripCmntRptVO.setMemId(rs.getInt("memId"));
				tripCmntRptVO.setRptReason(rs.getString("rptReason"));
				tripCmntRptVO.setRptTime(rs.getTimestamp("rptTime"));
				list.add(tripCmntRptVO);// Store the row in the list
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

		TripCmntRptJDBCDAO dao = new TripCmntRptJDBCDAO();

		// 插入第一筆資料
//		 TripCmntRptVO tripCmntRptVO1 = new TripCmntRptVO();
//		
//		 tripCmntRptVO1.setTripCmntNo(1);
//		 tripCmntRptVO1.setMemId(1);
//		 tripCmntRptVO1.setRptReason("亂講話2");
//		 tripCmntRptVO1.setRptTime(java.sql.Timestamp.valueOf("2017-4-22 22:10:22"));
		
//		 dao.insert(tripCmntRptVO1);
		// // // 插入第二筆資料�
		// TripCmntRptVO tripCmntRptVO2 = new TripCmntRptVO();
		//
		// tripCmntRptVO2.setTripCmntNo(1355);
		// tripCmntRptVO2.setTripCmntRptMemId(1235);
		// tripCmntRptVO2.setTripCmntRptReason("我來亂的");
		// tripCmntRptVO2.setTripCmntRptTime(java.sql.Timestamp.valueOf("2017-4-24
		// 13:10:22"));
		// tripCmntRptVO2.setTripCmntRptChk("1");;
		// tripCmntRptVO2.setTripCmntChkReason("你來亂的");;
		//
		// dao.insert(tripCmntRptVO2);

		// 刪除一筆資料
//		 dao.delete(3);

		// 查詢一筆資料
//		 TripCmntRptVO tripCmntRptVO3 = dao.findByPrimaryKey(4);
//		 System.out.print(tripCmntRptVO3.getTripCmntRptNo() + ",");
//		 System.out.print(tripCmntRptVO3.getTripCmntNo() + ",");
//		 System.out.print(tripCmntRptVO3.getMemId() + ",");
//		 System.out.print(tripCmntRptVO3.getRptReason() + ",");
//		 System.out.println(tripCmntRptVO3.getRptTime());
//		
//		 System.out.println("---------------------");

		// 查詢所有資料
//		 List<TripCmntRptVO> list = dao.getAll();
//		 for (TripCmntRptVO tripCmntRptVO4 : list) {
//			 TripCmntRptVO tripCmntRptVO3 = dao.findByPrimaryKey(4);
//			 System.out.print(tripCmntRptVO3.getTripCmntRptNo() + ",");
//			 System.out.print(tripCmntRptVO3.getTripCmntNo() + ",");
//			 System.out.print(tripCmntRptVO3.getMemId() + ",");
//			 System.out.print(tripCmntRptVO3.getRptReason() + ",");
//			 System.out.println(tripCmntRptVO3.getRptTime());
//			
//			 System.out.println("---------------------");
//		 }
	}
}
