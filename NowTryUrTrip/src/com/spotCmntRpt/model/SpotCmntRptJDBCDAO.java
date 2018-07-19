package com.spotCmntRpt.model;

import java.util.ArrayList;
import java.util.List;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

public class SpotCmntRptJDBCDAO implements SpotCmntRptDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "AA107G5";
	String passwd = "AA107G5";

	private static final String INSERT_STMT = "INSERT INTO spotCmntRpt (rptNo,memId,spotCmntNo,rptReason,rptTime) VALUES (spotCmntRpt_seq.NEXTVAL,  ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM spotCmntRpt order by rptNo";
	private static final String GET_ONE_STMT = "SELECT * FROM spotCmntRpt where rptNo = ?";
	private static final String DELETE = "DELETE FROM spotCmntRpt where rptNo =?";
	private static final String UPDATE = "UPDATE spotCmntRpt set  memId=?, spotCmntNo=?, rptReason=?, rptTime=? where rptNo = ?";
	private static final String GET_ALL_SORTED_STMT = "SELECT * FROM spotCmntRpt o join spotCmnt i on o.spotCmntNo = i.spotCmntNo order by i.isChecked, o.spotCmntNo, o.rptTime";
	
	@Override
	public void insert(SpotCmntRptVO spotCmntRptVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			// pstmt.setInt(1, spotCmntRptVO.getReportNo());
			pstmt.setInt(1, spotCmntRptVO.getMemId());
			pstmt.setInt(2, spotCmntRptVO.getSpotCmntNo());
			pstmt.setString(3, spotCmntRptVO.getRptReason());
			pstmt.setTimestamp(4, spotCmntRptVO.getRptTime());

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
	public void update(SpotCmntRptVO spotCmntRptVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, spotCmntRptVO.getMemId());
			pstmt.setInt(2, spotCmntRptVO.getSpotCmntNo());
			pstmt.setString(3, spotCmntRptVO.getRptReason());
			pstmt.setTimestamp(4, spotCmntRptVO.getRptTime());
			pstmt.setInt(5, spotCmntRptVO.getRptNo());

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
	public SpotCmntRptVO findByPrimaryKey(Integer rptNo) {
		SpotCmntRptVO spotCmntRptVO = null;
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
				spotCmntRptVO = new SpotCmntRptVO();
				spotCmntRptVO.setRptNo(rs.getInt("rptNo"));
				spotCmntRptVO.setMemId(rs.getInt("memId"));
				spotCmntRptVO.setSpotCmntNo(rs.getInt("spotCmntNo"));
				spotCmntRptVO.setRptReason(rs.getString("rptReason"));
				spotCmntRptVO.setRptTime(rs.getTimestamp("rptTime"));
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
		return spotCmntRptVO;
	}

	@Override
	public List<SpotCmntRptVO> getAll() {
		List<SpotCmntRptVO> list = new ArrayList<SpotCmntRptVO>();

		SpotCmntRptVO spotCmntRptVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// spotCmntRprtVO 也稱為 Domain objects
				spotCmntRptVO = new SpotCmntRptVO();
				spotCmntRptVO.setRptNo(rs.getInt("rptNo"));
				spotCmntRptVO.setMemId(rs.getInt("memId"));
				spotCmntRptVO.setSpotCmntNo(rs.getInt("spotCmntNo"));
				spotCmntRptVO.setRptReason(rs.getString("rptReason"));
				spotCmntRptVO.setRptTime(rs.getTimestamp("rptTime"));
				list.add(spotCmntRptVO);// Store the row in the list
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
	public List<SpotCmntRptVO> getAllSorted() {
		List<SpotCmntRptVO> list = new ArrayList<SpotCmntRptVO>();

		SpotCmntRptVO spotCmntRptVO = null;
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
				spotCmntRptVO = new SpotCmntRptVO();
				spotCmntRptVO.setRptNo(rs.getInt("rptNo"));
				spotCmntRptVO.setMemId(rs.getInt("memId"));
				spotCmntRptVO.setSpotCmntNo(rs.getInt("spotCmntNo"));
				spotCmntRptVO.setRptReason(rs.getString("rptReason"));
				spotCmntRptVO.setRptTime(rs.getTimestamp("rptTime"));
				list.add(spotCmntRptVO);// Store the row in the list
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
	
	public static void main(String[] args) {
		SpotCmntRptJDBCDAO dao = new SpotCmntRptJDBCDAO();

		// 新增
		 SpotCmntRptVO spotCmntRptVO1 = new SpotCmntRptVO();
		
		 spotCmntRptVO1.setMemId(4);
		 spotCmntRptVO1.setSpotCmntNo(1);
		 spotCmntRptVO1.setRptReason("insert test");
		 spotCmntRptVO1.setRptTime(new java.sql.Timestamp(new java.util.Date().getTime()));
		 dao.insert(spotCmntRptVO1);
		 System.out.println("finsh-insert");
		 System.out.println("---------------------");

		// 修改
//		 SpotCmntRptVO spotCmntRptVO2= new SpotCmntRptVO();
//		 spotCmntRptVO2.setRptNo(5);//SQL語法上此行忽略,因有seq
//		 spotCmntRptVO2.setMemId(2);
//		 spotCmntRptVO2.setSpotCmntNo(2);
//		 spotCmntRptVO2.setRptReason("update test");
//		 spotCmntRptVO2.setRptTime(new java.sql.Timestamp(new java.util.Date().getTime()));
//		 spotCmntRptVO2.setIsHandled("1");
//		 dao.update(spotCmntRptVO2);
//		 System.out.println("finsh-update");
//		 System.out.println("---------------------");

		// 刪除
		// dao.delete(7);
		// System.out.println("finish-delete");

		// 查詢
//		 SpotCmntRptVO spotCmntRptVO3 = dao.findByPrimaryKey(3);
//		 System.out.print(spotCmntRptVO3.getRptNo() + ",");
//		 System.out.print(spotCmntRptVO3.getMemId()+ ",");
//		 System.out.print(spotCmntRptVO3.getSpotCmntNo()+ ",");
//		 System.out.print(spotCmntRptVO3.getRptReason()+ ",");
//		 System.out.print(spotCmntRptVO3.getRptTime()+ ",");
//		 System.out.print(spotCmntRptVO3.getIsHandled()+ ",");
//		 System.out.println("finsh-selectfindbypk");
//		 System.out.println("---------------------");

		// 查詢全部
//		List<SpotCmntRptVO> list = dao.getAll();
//		for (SpotCmntRptVO spotCmntRptVO4 : list) {
//
//			System.out.print(spotCmntRptVO4.getRptNo() + ",");
//			System.out.print(spotCmntRptVO4.getMemId() + ",");
//			System.out.print(spotCmntRptVO4.getSpotCmntNo() + ",");
//			System.out.print(spotCmntRptVO4.getRptReason() + ",");
//			System.out.print(spotCmntRptVO4.getRptTime() + ",");
//			System.out.print(spotCmntRptVO4.getIsHandled() + ",");
//			System.out.println("finsh-getAll");
//			System.out.println("---------------------");
//		}
	}
}
