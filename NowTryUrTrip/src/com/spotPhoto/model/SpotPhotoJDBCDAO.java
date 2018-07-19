package com.spotPhoto.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpotPhotoJDBCDAO implements SpotPhotoDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "AA107G5";
	String passwd = "AA107G5";

	private static final String INSERT_STMT = "INSERT INTO spotPhoto (spotPhotoNo, spotNo,memId,spotPhoto,photoTitle) VALUES (spotPhoto_seq.NEXTVAL, ?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM spotPhoto order by spotPhotoNo";
	private static final String GET_ONE_STMT = "SELECT * FROM spotPhoto where spotPhotoNo = ?";
	private static final String DELETE = "DELETE FROM spotPhoto where spotPhotoNo = ?";
	private static final String UPDATE = "UPDATE spotPhoto set spotNo=?,memId=?,spotPhoto=?,photoTitle=? where spotPhotoNo = ?";

	@Override
	public void insert(SpotPhotoVO spotPhotoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, spotPhotoVO.getSpotNo());
			pstmt.setInt(2, spotPhotoVO.getMemId());
			pstmt.setBytes(3, spotPhotoVO.getSpotPhoto());
			pstmt.setString(4, spotPhotoVO.getPhotoTitle());

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
	public void update(SpotPhotoVO spotPhotoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, spotPhotoVO.getSpotNo());
			pstmt.setInt(2, spotPhotoVO.getMemId());
			pstmt.setBytes(3, spotPhotoVO.getSpotPhoto());
			pstmt.setInt(4, spotPhotoVO.getSpotPhotoNo());
			pstmt.setString(5, spotPhotoVO.getPhotoTitle());

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
	public void delete(Integer spotPhotoNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, spotPhotoNo);

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
	public SpotPhotoVO findByPrimaryKey(Integer spotPhotoNo) {
		SpotPhotoVO spotPhotoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, spotPhotoNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				spotPhotoVO = new SpotPhotoVO();
				spotPhotoVO.setSpotPhotoNo(rs.getInt("spotPhotoNo"));
				spotPhotoVO.setSpotNo(rs.getInt("spotNo"));
				spotPhotoVO.setMemId(rs.getInt("memId"));
				spotPhotoVO.setSpotPhoto(rs.getBytes("spotPhoto"));
				spotPhotoVO.setPhotoTitle(rs.getString("photoTitle"));
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
		return spotPhotoVO;
	}

	@Override
	public List<SpotPhotoVO> getAll() {
		SpotPhotoVO spotPhotoVO = null;
		List<SpotPhotoVO> list = new ArrayList<SpotPhotoVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				spotPhotoVO = new SpotPhotoVO();
				spotPhotoVO.setSpotPhotoNo(rs.getInt("spotPhotoNo"));
				spotPhotoVO.setSpotNo(rs.getInt("spotNo"));
				spotPhotoVO.setMemId(rs.getInt("memId"));
				spotPhotoVO.setSpotPhoto(rs.getBytes("spotPhoto"));
				spotPhotoVO.setPhotoTitle(rs.getString("photoTitle"));

				list.add(spotPhotoVO);
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

		SpotPhotoJDBCDAO dao = new SpotPhotoJDBCDAO();

		// // 插入第一筆資料
		// SpotPhotoVO spotPhotoVO1 = new SpotPhotoVO();
		//
		// spotPhotoVO1.setSpotNo(1);
		// spotPhotoVO1.setMemId(1);
		// spotPhotoVO1.setSpotPhoto(null);
		//
		// dao.insert(spotPhotoVO1);
		// // 插入第二筆資料
		// SpotPhotoVO spotPhotoVO2 = new SpotPhotoVO();
		//
		// spotPhotoVO2.setSpotNo(1);
		// spotPhotoVO2.setMemId(2);
		// spotPhotoVO2.setSpotPhoto(null);
		//
		// dao.insert(spotPhotoVO2);
		//
		// 刪除一筆資料
		// dao.delete(2);

		// 查詢一筆資料
		// SpotPhotoVO spotPhotoVO3 = dao.findByPrimaryKey(3);
		// System.out.print(spotPhotoVO3.getSpotPhotoNo() + ",");
		// System.out.print(spotPhotoVO3.getSpotNo() + ",");
		// System.out.print(spotPhotoVO3.getMemId() + ",");
		// System.out.println(spotPhotoVO3.getSpotPhoto());
		//
		// System.out.println("---------------------");

		// 查詢所有資料
		// List<SpotPhotoVO> list = dao.getAll();
		// for (SpotPhotoVO spotPhotoVO4 : list) {
		// System.out.print(spotPhotoVO4.getSpotPhotoNo() + ",");
		// System.out.print(spotPhotoVO4.getSpotNo() + ",");
		// System.out.print(spotPhotoVO4.getMemId() + ",");
		// System.out.println(spotPhotoVO4.getSpotPhoto());
		//
		// System.out.println("---------------------");
		// }
	}
}
