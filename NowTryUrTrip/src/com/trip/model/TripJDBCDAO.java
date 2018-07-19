package com.trip.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class TripJDBCDAO implements TripDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "AA107G5";
	String passwd = "AA107G5";
	
	private static final String INSERT = "INSERT INTO trip (tripNo,memId,tripName,departTime,tripType,tripAddTime,tripRmvTime,tripRate,tripIsPublic,tripPrice,tripAdImg,tripDesc,tripContent) VALUES (tripno_seq.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String DELETE = "DELETE FROM trip where tripNo = ?";
	private static final String UPDATE = "UPDATE trip set memId=?,tripName=?,departTime=?,tripType=?,tripAddTime=?,tripRmvTime=?,tripRate=?,tripIsPublic=?,tripPrice=?,tripAdImg=?,tripDesc=?,tripContent=? where tripNo=?";
	private static final String GET_ONE_STMT = "SELECT * FROM trip where tripNo=?";
	private static final String GET_ALL_STMT = "SELECT * FROM trip ORDER BY tripNo";

	@Override
	public void insert(TripVO tripVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT);

			pstmt.setInt(1, tripVO.getMemId());
			pstmt.setString(2, tripVO.getTripName());
			pstmt.setTimestamp(3, tripVO.getDepartTime());
			pstmt.setString(4, tripVO.getTripType());
			pstmt.setTimestamp(5, tripVO.getTripAddTime());
			pstmt.setTimestamp(6, tripVO.getTripRmvTime());
			pstmt.setDouble(7, tripVO.getTripRate());
			pstmt.setString(8, tripVO.getTripIsPublic());
			pstmt.setInt(9, tripVO.getTripPrice());
			pstmt.setBytes(10, tripVO.getTripAdImg());
			pstmt.setString(11, tripVO.getTripDesc());
			pstmt.setString(12, tripVO.getTripContent());

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
	public void update(TripVO tripVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, tripVO.getMemId());
			pstmt.setString(2, tripVO.getTripName());
			pstmt.setTimestamp(3, tripVO.getDepartTime());
			pstmt.setString(4, tripVO.getTripType());
			pstmt.setTimestamp(5, tripVO.getTripAddTime());
			pstmt.setTimestamp(6, tripVO.getTripRmvTime());
			pstmt.setDouble(7, tripVO.getTripRate());
			pstmt.setString(8, tripVO.getTripIsPublic());
			pstmt.setInt(9, tripVO.getTripPrice());
			pstmt.setBytes(10, tripVO.getTripAdImg());
			pstmt.setString(11, tripVO.getTripDesc());
			pstmt.setString(12, tripVO.getTripContent());
			pstmt.setInt(13, tripVO.getTripNo());

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
	public void delete(Integer tripNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, tripNo);
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public TripVO findByPrimaryKey(Integer tripNo) {
		TripVO tripVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, tripNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				tripVO = new TripVO();
				tripVO.setTripNo(rs.getInt("tripNo"));
				tripVO.setMemId(rs.getInt("memId"));
				tripVO.setTripName(rs.getString("tripName"));
				tripVO.setDepartTime(rs.getTimestamp("departTime"));
				tripVO.setTripType(rs.getString("tripType"));
				tripVO.setTripAddTime(rs.getTimestamp("tripAddTime"));
				tripVO.setTripRmvTime(rs.getTimestamp("tripRmvTime"));
				tripVO.setTripRate(rs.getDouble("tripRate"));
				tripVO.setTripIsPublic(rs.getString("tripIsPublic"));
				tripVO.setTripPrice(rs.getInt("tripPrice"));
				tripVO.setTripAdImg(rs.getBytes("tripAdImg"));
				tripVO.setTripDesc(rs.getString("tripDesc"));
				tripVO.setTripContent(rs.getString("tripContent"));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
		return tripVO;
	}

	@Override
	public List<TripVO> getAll() {

		List<TripVO> list = new ArrayList<TripVO>();
		TripVO tripVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				tripVO = new TripVO();
				tripVO.setTripNo(rs.getInt("tripNo"));
				tripVO.setMemId(rs.getInt("memId"));
				tripVO.setTripName(rs.getString("tripName"));
				tripVO.setDepartTime(rs.getTimestamp("departTime"));
				tripVO.setTripType(rs.getString("tripType"));
				tripVO.setTripAddTime(rs.getTimestamp("tripAddTime"));
				tripVO.setTripRmvTime(rs.getTimestamp("tripRmvTime"));
				tripVO.setTripRate(rs.getDouble("tripRate"));
				tripVO.setTripIsPublic(rs.getString("tripIsPublic"));
				tripVO.setTripPrice(rs.getInt("tripPrice"));
				tripVO.setTripAdImg(rs.getBytes("tripAdImg"));
				tripVO.setTripDesc(rs.getString("tripDesc"));
				tripVO.setTripContent(rs.getString("tripContent"));

				list.add(tripVO);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
	
	@Override
	public List<TripVO> getAll(Map<String, String[]> map) {
		List<TripVO> list = new ArrayList<TripVO>();
		TripVO tripVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = DriverManager.getConnection(url, userid, passwd);
			String finalSQL = "select * from trip "
		          + CompositeQuery_Trip.get_WhereCondition(map);
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				tripVO = new TripVO();
				tripVO.setTripNo(rs.getInt("tripNo"));
				tripVO.setMemId(rs.getInt("memId"));
				tripVO.setTripName(rs.getString("tripName"));
				tripVO.setDepartTime(rs.getTimestamp("departTime"));
				tripVO.setTripType(rs.getString("tripType"));
				tripVO.setTripAddTime(rs.getTimestamp("tripAddTime"));
				tripVO.setTripRmvTime(rs.getTimestamp("tripRmvTime"));
				tripVO.setTripRate(rs.getDouble("tripRate"));
				tripVO.setTripIsPublic(rs.getString("tripIsPublic"));
				tripVO.setTripPrice(rs.getInt("tripPrice"));
				tripVO.setTripAdImg(rs.getBytes("tripAdImg"));
				tripVO.setTripDesc(rs.getString("tripDesc"));
				tripVO.setTripContent(rs.getString("tripContent"));

				list.add(tripVO);
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

	public static void main(String[] args) throws IOException, ParseException {

		TripJDBCDAO dao = new TripJDBCDAO();
		// 插入資料一

//		 TripVO tripVO2 = new TripVO();
//		
//		 tripVO2.setMemId(1287);
//		 tripVO2.setTripName("Taipei 5 days");
//		 tripVO2.setDepartTime(java.sql.Timestamp.valueOf("2017-4-15 10:00:00"));
//		 tripVO2.setTripType("1");
//		 tripVO2.setTripAddTime(java.sql.Timestamp.valueOf("2017-4-15 22:22:11"));
//		 tripVO2.setTripRmvTime(java.sql.Timestamp.valueOf("2017-4-22 22:10:22"));
//		 tripVO2.setTripRate(1);
//		 tripVO2.setTripIsPublic("1");
//		 tripVO2.setTripPrice(1000);
//		 byte[] tripAdImg = dao.getPictureByteArray("items/ncu.jpg");
//		 tripVO2.setTripAdImg(tripAdImg);
//		 tripVO2.setTripDesc(".....keep doing wrong.....QQ");
//		 tripVO2.setTripContent("well...I dont't know how to write a Clob code!!...damn!!");
//		 dao.insert(tripVO2);

		// // 刪除資料

		// dao.delete(2008);

		// 找尋一筆資料

//		 TripVO tripVO3 = dao.findByPrimaryKey(2001);
//		 System.out.print(tripVO3.getTripNo() + ",");
//		 System.out.print(tripVO3.getMemId() + ",");
//		 System.out.print(tripVO3.getTripName() + ",");
//		 System.out.print(tripVO3.getDepartTime() + ",");
//		 System.out.print(tripVO3.getTripType() + ",");
//		 System.out.print(tripVO3.getTripAddTime() + ",");
//		 System.out.print(tripVO3.getTripRmvTime() + ",");
//		 System.out.print(tripVO3.getTripRate() + ",");
//		 System.out.print(tripVO3.getTripIsPublic() + ",");
//		 System.out.print(tripVO3.getTripPrice() + ",");
//		 System.out.print(tripVO3.getTripAdImg() + ",");
//		 System.out.print(tripVO3.getTripDesc() + ",");
//		 System.out.println(tripVO3.getTripContent());
//		 System.out.println("---------------------");

		// 找尋所有資料
		List<TripVO> list = dao.getAll();
		for (TripVO trip : list) {
			System.out.print(trip.getTripNo() + ",");
			System.out.print(trip.getMemId() + ",");
			System.out.print(trip.getTripName() + ",");
			System.out.print(trip.getDepartTime() + ",");
			System.out.print(trip.getTripType() + ",");
			System.out.print(trip.getTripAddTime() + ",");
			System.out.print(trip.getTripRmvTime() + ",");
			System.out.print(trip.getTripRate() + ",");
			System.out.print(trip.getTripIsPublic() + ",");
			System.out.print(trip.getTripPrice() + ",");
			System.out.print(trip.getTripAdImg() + ",");
			System.out.print(trip.getTripDesc() + ",");
			System.out.println(trip.getTripContent());
			System.out.println("---------------------");
		}
	}

	public byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buff = new byte[1];
		while (fis.read(buff) != -1) {
			baos.write(buff);
		}

		baos.close();
		fis.close();
		return baos.toByteArray();
	}
}
