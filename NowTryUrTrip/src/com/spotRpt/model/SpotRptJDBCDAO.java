package com.spotRpt.model;


import java.util.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class SpotRptJDBCDAO implements SpotRptDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "AA107G5";
	String passwd = "AA107G5";

	private static final String INSERT_STMT = 
		"INSERT INTO spotRpt (rptNo,memId,spotNo,rptReason,rptTime) VALUES (spotRpt_seq.NEXTVAL,?,?,?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM spotRpt order by rptNo";
	private static final String GET_ALL_SORTEDSTMT = 
		"SELECT * FROM spotRpt o join spot i on o.spotNo = i.spotNo order by i.spotHdl, i.spotNo, o.rptTime";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM spotRpt where rptNo = ?";
	private static final String DELETE = 
		"DELETE FROM spotRpt where rptNo = ? ";
	private static final String UPDATE = 
		"UPDATE spotRpt set memId=?, spotNo=?, rptReason=? where rptNo = ?";

	@Override
	public void insert(SpotRptVO spotRptVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, spotRptVO.getMemId());
			pstmt.setInt(2, spotRptVO.getSpotNo());
			pstmt.setString(3, spotRptVO.getRptReason());
			pstmt.setTimestamp(4, spotRptVO.getRptTime());

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
	public void update(SpotRptVO spotRptVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, spotRptVO.getMemId());
			pstmt.setInt(2, spotRptVO.getSpotNo());
			pstmt.setString(3, spotRptVO.getRptReason());
			pstmt.setInt(4, spotRptVO.getRptNo());
			
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
	public void delete(Integer rptNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, rptNo);

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
	public SpotRptVO findByPrimaryKey(Integer rptNo) {

		SpotRptVO spotRptVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, rptNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 查詢單筆
				spotRptVO = new SpotRptVO();
				spotRptVO.setRptNo(rs.getInt("rptNo"));
				spotRptVO.setMemId(rs.getInt("memId"));
				spotRptVO.setSpotNo(rs.getInt("spotNo"));				
				spotRptVO.setRptReason(rs.getString("rptReason"));
				spotRptVO.setRptTime(rs.getTimestamp("rptTime"));

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
		return spotRptVO;
	}

	@Override
	public List<SpotRptVO> getAll() {
		List<SpotRptVO> list = new ArrayList<SpotRptVO>();
		SpotRptVO spotRptVO = null;

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
				spotRptVO = new SpotRptVO();
				
				spotRptVO = new SpotRptVO();
				spotRptVO.setRptNo(rs.getInt("rptNo"));
				spotRptVO.setMemId(rs.getInt("memId"));
				spotRptVO.setSpotNo(rs.getInt("spotNo"));				
				spotRptVO.setRptReason(rs.getString("rptReason"));
				spotRptVO.setRptTime(rs.getTimestamp("rptTime"));

				list.add(spotRptVO); // Store the row in the list
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
	
	@Override
	public List<SpotRptVO> getAllSorted() {
		List<SpotRptVO> list = new ArrayList<SpotRptVO>();
		SpotRptVO spotRptVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_SORTEDSTMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 查詢全部
				spotRptVO = new SpotRptVO();
				
				spotRptVO = new SpotRptVO();
				spotRptVO.setRptNo(rs.getInt("rptNo"));
				spotRptVO.setMemId(rs.getInt("memId"));
				spotRptVO.setSpotNo(rs.getInt("spotNo"));				
				spotRptVO.setRptReason(rs.getString("rptReason"));
				spotRptVO.setRptTime(rs.getTimestamp("rptTime"));

				list.add(spotRptVO); // Store the row in the list
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SpotRptJDBCDAO dao = new SpotRptJDBCDAO();

		// 新增
//		SpotRptVO spotRptVO1 = new SpotRptVO();
//		spotRptVO1.setMemId(1);
//		spotRptVO1.setSpotNo(1);
//		spotRptVO1.setRptReason("這景點不實");
//		spotRptVO1.setRptTime(new java.sql.Timestamp(new java.util.Date().getTime()));
//		spotRptVO1.setIsChecked("1");
//
//		dao.insert(spotRptVO1);

		// 修改
//		SpotRptVO spotRptVO2 = new SpotRptVO();
//		spotRptVO2.setRptNo(2);
//		spotRptVO2.setMemId(1);
//		spotRptVO2.setSpotNo(1);
//		spotRptVO2.setRptReason("kokok");
//		spotRptVO2.setIsChecked("1");
//		
//		dao.update(spotRptVO2);


		// 刪除
//		dao.delete(3);
//
		// 查詢
//		SpotRptVO spotRptVO3 = dao.findByPrimaryKey(3);
//		System.out.print(spotRptVO3.getRptNo() + ",");		
//		System.out.print(spotRptVO3.getMemId() + ",");
//		System.out.print(spotRptVO3.getSpotNo() + ",");
//		System.out.print(spotRptVO3.getRptReason() + ",");
//		System.out.print(sdf.format(spotRptVO3.getRptTime()) + ",");
//		System.out.println(spotRptVO3.getIsChecked());
//
//		System.out.println("---------------------");

		// 查詢
//		List<SpotRptVO> list = dao.getAll();
//		for (SpotRptVO aSpotRpt : list) {
//			System.out.print(aSpotRpt.getRptNo() + ",");
//			System.out.print(aSpotRpt.getMemId() + ",");
//			System.out.print(aSpotRpt.getSpotNo() + ",");
//			System.out.print(aSpotRpt.getRptReason() + ",");
//			System.out.print(sdf.format(aSpotRpt.getRptTime()) + ",");
//			System.out.println(aSpotRpt.getIsChecked());
//			System.out.println("---------------------");
//		}
	}
}