package com.frontend.proprietorArea.model;

import java.util.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

public class PprJDBCDAO implements PprDAO_interface{
	String driver ="oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "AA107How";
	String passwd = "AA107How";
	
	private static final String INSERT_STMT = 
			"INSERT INTO proprietorArea (pprMsgeNo,pprSpotNo,pprCheckIn,pprCheckOut,pprMsgeCtx,pprMsgeImg) VALUES (proprietorArea_seq.NEXTVAL, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT pprMsgeNo,pprSpotNo,to_char(pprCheckIn,'yyyy-mm-dd') pprCheckIn,to_char(pprCheckOut,'yyyy-mm-dd') pprCheckOut,pprMsgeCtx,pprMsgeImg FROM proprietorArea order by pprMsgeNo";
		private static final String GET_ONE_STMT = 
			"SELECT pprMsgeNo,pprSpotNo,to_char(pprCheckIn,'yyyy-mm-dd') pprCheckIn,to_char(pprCheckOut,'yyyy-mm-dd') pprCheckOut,pprMsgeCtx,pprMsgeImg FROM proprietorArea where pprMsgeNo = ?";
		private static final String DELETE = 
			"DELETE FROM proprietorArea where pprMsgeNo =?";
		private static final String UPDATE = 
			"UPDATE proprietorArea set  pprSpotNo=?, pprCheckIn=?, pprCheckOut=?, pprMsgeCtx=?, pprMsgeImg=?  where pprMsgeNo = ?";	
	
		// 使用byte[]方式
//		public static byte[] getPictureByteArray(String path) throws IOException {
//			File file = new File(path);
//			FileInputStream fis = new FileInputStream(file);
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			byte[] buffer = new byte[8192];//8k
//			int i;
//			while ((i = fis.read(buffer)) != -1) {
//				baos.write(buffer, 0, i);
//			}
//			baos.close();
//			fis.close();
//
//			return baos.toByteArray();
//		}
		
		public static byte[] getPictureByteArray(String path) throws IOException {//同上功能,更簡化的寫法			
			FileInputStream in = new FileInputStream(path);
			byte[] buffer = new byte[in.available()];
			in.read(buffer);
			in.close();
			return buffer;			
		}
		
	@Override
	public void insert(PprVo pprVo) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, pprVo.getPprSpotNo());
			pstmt.setDate(2, pprVo.getPprCheckIn());
			pstmt.setDate(3, pprVo.getPprCheckOut());
			pstmt.setString(4, pprVo.getPprMsgeCtx());
			pstmt.setBytes(5, pprVo.getPprMsgeImg());			

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
	public void update(PprVo pprVo) {
		System.out.println("update11111");
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			System.out.println("update222222");
			pstmt.setInt(1, pprVo.getPprSpotNo());
			pstmt.setDate(2, pprVo.getPprCheckIn());
			pstmt.setDate(3, pprVo.getPprCheckOut());
			pstmt.setString(4, pprVo.getPprMsgeCtx());
			pstmt.setBytes(5, pprVo.getPprMsgeImg());
			pstmt.setInt(6, pprVo.getPprMsgeNo());
			System.out.println("update");
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
	public void delete(Integer pprVo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, pprVo);

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
	public PprVo findByPrimaryKey(Integer pprMsgeNo) {
		
		PprVo pprVo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, pprMsgeNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// pprVo 也稱為 Domain objects
				pprVo = new PprVo();
				pprVo.setPprMsgeNo(rs.getInt("pprMsgeNo"));
				pprVo.setPprSpotNo(rs.getInt("pprSpotNo"));
				pprVo.setPprCheckIn(rs.getDate("pprCheckIn"));
				pprVo.setPprCheckOut(rs.getDate("pprCheckOut"));
				pprVo.setPprMsgeCtx(rs.getString("pprMsgeCtx"));
				pprVo.setPprMsgeImg(rs.getBytes("pprMsgeImg"));
				
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
		return pprVo;
	}

	@Override
	public List<PprVo> getAll() {
		List<PprVo> list = new ArrayList<PprVo>();
		
		
		PprVo pprVo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// pprVo 也稱為 Domain objects
				pprVo = new PprVo();
				pprVo.setPprMsgeNo(rs.getInt("pprMsgeNo"));
				pprVo.setPprSpotNo(rs.getInt("pprSpotNo"));
				pprVo.setPprCheckIn(rs.getDate("pprCheckIn"));
				pprVo.setPprCheckOut(rs.getDate("pprCheckOut"));
				pprVo.setPprMsgeCtx(rs.getString("pprMsgeCtx"));
				pprVo.setPprMsgeImg(rs.getBytes("pprMsgeImg"));
				list.add(pprVo);// Store the row in the list
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
	public static void main(String[] args) throws IOException {
		PprJDBCDAO dao = new PprJDBCDAO();
		
		// 新增
		PprVo pprVo1 = new PprVo();
		pprVo1.setPprMsgeNo(200);//SQL語法上此行忽略,因有seq
		pprVo1.setPprSpotNo(15);
		pprVo1.setPprCheckIn(java.sql.Date.valueOf("2000-01-01"));
		pprVo1.setPprCheckOut(java.sql.Date.valueOf("2000-01-01"));
		pprVo1.setPprMsgeCtx("JDBCTEST200");
		pprVo1.setPprMsgeImg(getPictureByteArray("items/123.jpg"));				
		dao.insert(pprVo1);
		System.out.println("finsh-insert");
		System.out.println("---------------------");
		
		// 修改
//		PprVo pprVo2 = new PprVo();
//		pprVo2.setPprMsgeNo(112);
//		pprVo2.setPprSpotNo(20);
//		pprVo2.setPprCheckIn(java.sql.Date.valueOf("2011-11-11"));
//		pprVo2.setPprCheckOut(java.sql.Date.valueOf("2011-09-11"));
//		pprVo2.setPprMsgeCtx("JDBCTEST40");
//		pprVo2.setPprMsgeImg(null);
//		dao.update(pprVo2);
//		System.out.println("finsh-update");
//		System.out.println("---------------------");
//		 刪除
//		dao.delete(116);
//		System.out.println("go-updelete");
//		
//		// 查詢
//		PprVo pprVo3 = dao.findByPrimaryKey(112);
//		System.out.print(pprVo3.getPprMsgeNo() + ",");
//		System.out.print(pprVo3.getPprSpotNo() + ",");
//		System.out.print(pprVo3.getPprCheckIn() + ",");
//		System.out.print(pprVo3.getPprCheckOut() + ",");
//		System.out.print(pprVo3.getPprMsgeCtx() + ",");
//		System.out.print(pprVo3.getPprMsgeImg() + ",");
//		System.out.println("finsh-selectfindbypk");
//		System.out.println("---------------------");
		
		// 查詢全部
		List<PprVo> list = dao.getAll();
		for (PprVo pprVo4 : list) {
		System.out.print(pprVo4.getPprMsgeNo() + ",");
		System.out.print(pprVo4.getPprSpotNo() + ",");
		System.out.print(pprVo4.getPprCheckIn() + ",");
		System.out.print(pprVo4.getPprCheckOut() + ",");
		System.out.print(pprVo4.getPprMsgeCtx() + ",");
		System.out.print(pprVo4.getPprMsgeImg() + ",");
		System.out.println("---------------------");
		System.out.println();
		}			
	}
}

	
