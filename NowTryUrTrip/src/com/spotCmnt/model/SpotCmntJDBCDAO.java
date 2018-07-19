package com.spotCmnt.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SpotCmntJDBCDAO implements SpotCmntDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "AA107G5";
	String passwd = "AA107G5";

	private static final String INSERT_STMT = "INSERT INTO SPOTCMNT (spotCmntNo, memId, spotNo, cmnt, cmntTime, rate, isChecked, isBlocked, blockedReason)"
			+ " VALUES (spotCmnt_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE SPOTCMNT set memId=?, spotNo=?, cmnt=?, cmntTime=?, rate=?, isChecked=?, isBlocked=?, blockedReason=? where spotCmntNo = ?";
	private static final String DELETE_STMT = "DELETE FROM SPOTCMNT where spotCmntNo = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM SPOTCMNT where spotCmntNo = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM SPOTCMNT order by spotCmntNo";

	@Override
	public void insert(SpotCmntVO spotCmntVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, spotCmntVO.getMemId());
			pstmt.setInt(2, spotCmntVO.getSpotNo());
			pstmt.setString(3, spotCmntVO.getCmnt());
			pstmt.setTimestamp(4, spotCmntVO.getCmntTime());
			pstmt.setInt(5, spotCmntVO.getRate());
			pstmt.setString(6, spotCmntVO.getIsChecked());
			pstmt.setString(7, spotCmntVO.getIsBlocked());
			pstmt.setString(8, spotCmntVO.getBlockedReason());

			pstmt.executeUpdate();
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
	}

	@Override
	public void update(SpotCmntVO spotCmntVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, spotCmntVO.getMemId());
			pstmt.setInt(2, spotCmntVO.getSpotNo());
			pstmt.setString(3, spotCmntVO.getCmnt());
			pstmt.setTimestamp(4, spotCmntVO.getCmntTime());
			pstmt.setInt(5, spotCmntVO.getRate());
			pstmt.setString(6, spotCmntVO.getIsChecked());
			pstmt.setString(7, spotCmntVO.getIsBlocked());
			pstmt.setString(8, spotCmntVO.getBlockedReason());
			pstmt.setInt(9, spotCmntVO.getSpotCmntNo());

			pstmt.executeUpdate();
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
	}

	@Override
	public void delete(Integer spotCmntNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, spotCmntNo);

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
	}

	@Override
	public SpotCmntVO findByPrimaryKey(Integer spotCmntNo) {

		SpotCmntVO spotCmntVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, spotCmntNo);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				// deptVO 也稱為 Domain objects
				spotCmntVO = new SpotCmntVO();

				spotCmntVO.setSpotCmntNo(rs.getInt("spotCmntNo"));
				spotCmntVO.setMemId(rs.getInt("MemId"));
				spotCmntVO.setSpotNo(rs.getInt("SpotNo"));
				spotCmntVO.setCmnt(rs.getString("Cmnt"));
				spotCmntVO.setCmntTime(rs.getTimestamp("CmntTime"));
				spotCmntVO.setRate(rs.getInt("Rate"));
				spotCmntVO.setIsChecked(rs.getString("IsChecked"));
				spotCmntVO.setIsBlocked(rs.getString("IsBlocked"));
				spotCmntVO.setBlockedReason(rs.getString("BlockedReason"));

			}
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
		return spotCmntVO;
	}

	@Override
	public List<SpotCmntVO> getAll() {
		List<SpotCmntVO> list = new ArrayList<SpotCmntVO>();
		SpotCmntVO spotCmntVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				// deptVO 也稱為 Domain objects
				spotCmntVO = new SpotCmntVO();

				spotCmntVO.setSpotCmntNo(rs.getInt("spotCmntNo"));
				spotCmntVO.setMemId(rs.getInt("MemId"));
				spotCmntVO.setSpotNo(rs.getInt("SpotNo"));
				spotCmntVO.setCmnt(rs.getString("Cmnt"));
				spotCmntVO.setCmntTime(rs.getTimestamp("CmntTime"));
				spotCmntVO.setRate(rs.getInt("Rate"));
				spotCmntVO.setIsChecked(rs.getString("IsChecked"));
				spotCmntVO.setIsBlocked(rs.getString("IsBlocked"));
				spotCmntVO.setBlockedReason(rs.getString("BlockedReason"));
				list.add(spotCmntVO); // Store the row in the list

			}

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
		return list;
	}


	public static void main(String[] args) throws ParseException {
		 SpotCmntJDBCDAO dao = new SpotCmntJDBCDAO();
		
		 // 設定時間格式
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		 Timestamp testTime1 = new Timestamp(sdf.parse("2017-04-24 18:18:20").getTime());
		
		 // 新增
//		 SpotCmntVO scVO1 = new SpotCmntVO();
//		
//		 scVO1.setMemId(1);
//		 scVO1.setSpotNo(1);
//		 scVO1.setCmnt("個人化你的社團!!");
//		 scVO1.setRate(4);
//		 scVO1.setIsChecked("0");
//		 scVO1.setCmntTime(testTime1);
//		 scVO1.setIsBlocked("0");
//		 scVO1.setBlockedReason("你知道可以在這裡新增相片嗎？");
//		
//		 dao.insert(scVO1);
		//
		// // 修改
//		 SpotCmntVO scVO2 = new SpotCmntVO();
//		
//		 scVO2.setMemId(1);
//		 scVO2.setSpotNo(1);
//		 scVO2.setCmnt("挑選一張可以凸顯社團特性的相片。");
//		 scVO2.setRate(9);
//		 scVO2.setIsChecked("0");
//		 scVO2.setIsBlocked("0");
//		 scVO2.setCmntTime(testTime1);
//		 scVO2.setBlockedReason("要改的東西太多了，不如改天吧…");
//		 scVO2.setSpotCmntNo(1);
//		
//		 dao.update(scVO2);
		//
		// // 刪除
//		 dao.delete(2);
		
		 // 查詢一筆資料
//		 SpotCmntVO scVO3 = dao.findByPrimaryKey(3);
//		 System.out.println(scVO3.getSpotCmntNo());
//		 System.out.println(scVO3.getMemId());
//		 System.out.println(scVO3.getSpotNo());
//		 System.out.println(scVO3.getCmnt());
//		 System.out.println(scVO3.getCmntTime());
//		 System.out.println(scVO3.getRate());
//		 System.out.println(scVO3.getIsChecked());
//		 System.out.println(scVO3.getIsBlocked());
//		 System.out.println(scVO3.getBlockedReason());
		//
		// // 查詢全部資料
//		 List<SpotCmntVO> scs = dao.getAll();
//		 for (SpotCmntVO sc : scs) {
//		 System.out.println(sc.getSpotCmntNo());
//		 System.out.println(sc.getMemId());
//		 System.out.println(sc.getSpotNo());
//		 System.out.println(sc.getCmnt());
//		 System.out.println(sc.getCmntTime());
//		 System.out.println(sc.getRate());
//		 System.out.println(sc.getIsChecked());
//		 System.out.println(sc.getIsBlocked());
//		 System.out.println(sc.getBlockedReason());
//		 System.out.println("---------------------------------------------------");
//		 }
	}

}
