package com.spot.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SpotJDBCDAO implements SpotDAO_interface {

	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/AA107G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "AA107G5";
	String passwd = "AA107G5";

	private static final String INSERT_STMT = "INSERT INTO spot (spotNo, spotName,spotAddr ,spotType, spotLng, spotLat, spotRate, "
			+ " spotOverview, spotPhoto,spotOwner,spotChk, spotHdl, spotIsBlocked, spotBlockedReason) values "
			+ "(spotNo_seq.NEXTVAL,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE spot set spotName=?,spotAddr=?, spotType=?, spotLng=?, spotLat=?,spotRate=?,"
			+ " spotOverview=?,spotPhoto=?, spotOwner=? ,spotChk=? , spotHdl=?"
			+ ",spotIsBlocked=?, spotBlockedReason=? where spotNo = ?";
	private static final String DELETE = "DELETE FROM spot where spotNo = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM spot order by spotNo";
	private static final String GET_ONE_STMT = "SELECT * FROM spot where spotNo = ?";
	

	@Override
	public void insert(SpotVO spotVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, spotVO.getSpotName());
			pstmt.setString(2, spotVO.getSpotAddr());
			pstmt.setString(3, spotVO.getSpotType());
			pstmt.setString(4, spotVO.getSpotLng());
			pstmt.setString(5, spotVO.getSpotLat());
			pstmt.setDouble(6, spotVO.getSpotRate());
			pstmt.setString(7, spotVO.getSpotOverview());
			pstmt.setBytes(8, spotVO.getSpotPhoto());
			pstmt.setInt(9, spotVO.getSpotOwner());
			pstmt.setString(10, spotVO.getSpotChk());
			pstmt.setString(11, spotVO.getSpotHdl());
			pstmt.setString(12, spotVO.getSpotIsBlocked());
			pstmt.setString(13, spotVO.getSpotBlockedReason());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
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
	public void update(SpotVO spotVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = ds.getConnection();
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, spotVO.getSpotName());
			pstmt.setString(2, spotVO.getSpotAddr());
			pstmt.setString(3, spotVO.getSpotType());
			pstmt.setString(4, spotVO.getSpotLng());
			pstmt.setString(5, spotVO.getSpotLat());
			pstmt.setDouble(6, spotVO.getSpotRate());
			pstmt.setString(7, spotVO.getSpotOverview());
			pstmt.setBytes(8, spotVO.getSpotPhoto());
			pstmt.setInt(9, spotVO.getSpotOwner());
			pstmt.setString(10, spotVO.getSpotChk());
			pstmt.setString(11, spotVO.getSpotHdl());
			pstmt.setString(12, spotVO.getSpotIsBlocked());
			pstmt.setString(13, spotVO.getSpotBlockedReason());
			pstmt.setInt(14, spotVO.getSpotNo());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

		} catch (SQLException se) {
			se.printStackTrace();
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
	public void delete(Integer spotNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, spotNo);

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
	public SpotVO findByPrimaryKey(Integer spotNo) {
		SpotVO spotVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, spotNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				spotVO = new SpotVO();
				spotVO.setSpotNo(rs.getInt("spotNo"));
				spotVO.setSpotName(rs.getString("spotName"));
				spotVO.setSpotAddr(rs.getString("spotAddr"));
				spotVO.setSpotType(rs.getString("spotType"));
				spotVO.setSpotLng(rs.getString("spotLng"));
				spotVO.setSpotLat(rs.getString("spotLat"));
				spotVO.setSpotRate(rs.getDouble("spotRate"));
				spotVO.setSpotOverview(rs.getString("spotOverview"));
				spotVO.setSpotPhoto(rs.getBytes("spotPhoto"));
				spotVO.setSpotOwner(rs.getInt("spotOwner"));
				spotVO.setSpotChk(rs.getString("spotChk"));
				spotVO.setSpotHdl(rs.getString("spotHdl"));
				spotVO.setSpotIsBlocked(rs.getString("spotIsBlocked"));
				spotVO.setSpotBlockedReason(rs.getString("spotBlockedReason"));
			}
			// Handle any driver errors
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
		return spotVO;
	}

	@Override
	public List<SpotVO> getAll() {
		List<SpotVO> list = new ArrayList<SpotVO>();
		SpotVO spotVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				spotVO = new SpotVO();
				spotVO.setSpotNo(rs.getInt("spotNo"));
				spotVO.setSpotName(rs.getString("spotName"));
				spotVO.setSpotAddr(rs.getString("spotAddr"));
				spotVO.setSpotType(rs.getString("spotType"));
				spotVO.setSpotLng(rs.getString("spotLng"));
				spotVO.setSpotLat(rs.getString("spotLat"));
				spotVO.setSpotRate(rs.getDouble("spotRate"));
				spotVO.setSpotOverview(rs.getString("spotOverview"));
				spotVO.setSpotPhoto(rs.getBytes("spotPhoto"));
				spotVO.setSpotOwner(rs.getInt("spotOwner"));
				spotVO.setSpotChk(rs.getString("spotChk"));
				spotVO.setSpotHdl(rs.getString("spotHdl"));
				spotVO.setSpotIsBlocked(rs.getString("spotIsBlocked"));
				spotVO.setSpotBlockedReason(rs.getString("spotBlockedReason"));
				list.add(spotVO); // Store the row in the list
			}

			// Handle any driver errors
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
	public List<SpotVO> getAll(Map<String, String[]> map) {
		List<SpotVO> list = new ArrayList<SpotVO>();
		SpotVO spotVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = DriverManager.getConnection(url, userid, passwd);
			String finalSQL = "select * from spot "
		          + jdbcUtil_CompositeQuery_Spot.get_WhereCondition(map)
		          + "order by spotNo";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				spotVO = new SpotVO();
				spotVO.setSpotNo(rs.getInt("spotNo"));
				spotVO.setSpotName(rs.getString("spotName"));
				spotVO.setSpotAddr(rs.getString("spotAddr"));
				spotVO.setSpotType(rs.getString("spotType"));
				spotVO.setSpotLng(rs.getString("spotLng"));
				spotVO.setSpotLat(rs.getString("spotLat"));
				spotVO.setSpotRate(rs.getDouble("spotRate"));
				spotVO.setSpotOverview(rs.getString("spotOverview"));
				spotVO.setSpotPhoto(rs.getBytes("spotPhoto"));
				spotVO.setSpotOwner(rs.getInt("spotOwner"));
				spotVO.setSpotChk(rs.getString("spotChk"));
				spotVO.setSpotHdl(rs.getString("spotHdl"));
				spotVO.setSpotIsBlocked(rs.getString("spotIsBlocked"));
				spotVO.setSpotBlockedReason(rs.getString("spotBlockedReason"));
				list.add(spotVO); // Store the row in the List
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

	public static void main(String[] args) throws IOException {

		SpotJDBCDAO dao = new SpotJDBCDAO();

		// 插入資料一
//		SpotVO spotVO1 = new SpotVO();
//		spotVO1.setSpotName("象山親山步道");
//		spotVO1.setSpotType("1");
//		spotVO1.setSpotLng("121.5686393");
//		spotVO1.setSpotLat("25.0273924");
//		spotVO1.setSpotRate(9.5);
//		spotVO1.setSpotOverview(
//				"象山因外形似象頭而得名，位於臺北盆地東南方的信義區，與虎山地質相同主要由砂岩組成，因造山作用步道中可見黃褐色陡峭的岩 壁與巨石，加上自然環境生物、鳥類種類繁多，使得整座山如同一座天然生態樂園般，成為民眾接近淺山、享受戶外綠林悠閒時光的好去處。");
//		byte[] pic = dao.getPictureByteArray("items/Xiangshan.jpg");
//		spotVO1.setSpotPhoto(pic);
//		spotVO1.setSpotOwner(1);
//		spotVO1.setSpotChk("1");
//		spotVO1.setSpotHdl("1");
//		spotVO1.setSpotIsBlocked("0");
//		spotVO1.setSpotBlockedReason(null);
//		dao.insert(spotVO1);

		// 插入資料二
		SpotVO spotVO2 = new SpotVO();
		spotVO2.setSpotName("台北101");
		spotVO2.setSpotType("1");
		spotVO2.setSpotLng("121.5623212");
		spotVO2.setSpotLat("25.0339031");
		spotVO2.setSpotRate(8.5);
		spotVO2.setSpotOverview(
				"台北101（TAIPEI 101）是位於中華民國臺北市信義區的摩天大樓，樓高509.2公尺（1,671英尺），地上樓層共有101層、另有地下5層，總樓地板面積37萬4千平方公尺，由李祖原聯合建築師事務所設計、KTRT團隊承造 (韓國三星物產（Samsung C&T）承攬公共區域裝修工程)，於1999年動工，2004年12月31日完工啟用；最初名稱為台北國際金融中心（Taipei World Financial Center），2003年改為現名，亦俗稱為101大樓。");
		byte[] pic2 = dao.getPictureByteArray("items/123.jpg");
		spotVO2.setSpotPhoto(pic2);
		spotVO2.setSpotOwner(1);
		spotVO2.setSpotChk("1");
		spotVO2.setSpotHdl("1");
		spotVO2.setSpotIsBlocked("0");
		spotVO2.setSpotBlockedReason(null);
		dao.insert(spotVO2);

		// 刪除資料
//		 dao.delete(1005);
		//
		// 找尋一筆資料
		SpotVO spotVO3 = dao.findByPrimaryKey(1);
		System.out.print(spotVO3.getSpotNo() + ",");
		System.out.print(spotVO3.getSpotName() + ",");
		System.out.print(spotVO3.getSpotType() + ",");
		System.out.print(spotVO3.getSpotLng() + ",");
		System.out.print(spotVO3.getSpotLat() + ",");
		System.out.print(spotVO3.getSpotRate() + ",");
		System.out.print(spotVO3.getSpotOverview() + ",");
		System.out.print(spotVO3.getSpotOwner() + ",");
		System.out.print(spotVO3.getSpotChk() + ",");
		System.out.print(spotVO3.getSpotHdl() + ",");
		System.out.print(spotVO3.getSpotIsBlocked() + ",");
		System.out.println(spotVO3.getSpotBlockedReason());
		
		// System.out.println("---------------------");

		// 找尋所有資料
		List<SpotVO> list = dao.getAll();
		for (SpotVO spot : list) {
			System.out.print(spot.getSpotNo() + ",");
			System.out.print(spot.getSpotName() + ",");
			System.out.print(spot.getSpotType() + ",");
			System.out.print(spot.getSpotLng() + ",");
			System.out.print(spot.getSpotLat() + ",");
			System.out.print(spot.getSpotRate() + ",");
			System.out.print(spot.getSpotOverview() + ",");
			System.out.print(spot.getSpotOwner() + ",");
			System.out.print(spot.getSpotChk() + ",");
			System.out.print(spot.getSpotHdl() + ",");
			System.out.print(spot.getSpotIsBlocked() + ",");
			System.out.println(spot.getSpotBlockedReason());
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
