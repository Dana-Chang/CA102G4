package com.proprietorArea.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

public class PprDAO implements PprDAO_interface{
	
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/AA107G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = 
			"INSERT INTO proprietorArea (pprMsgeNo,pprSpotNo,pprCheckIn,pprCheckOut,pprMsgeCtx,pprMsgeImg) VALUES (proprietorArea_seq.NEXTVAL, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT pprMsgeNo,pprSpotNo,pprCheckIn,pprCheckOut,pprMsgeCtx,pprMsgeImg FROM proprietorArea order by pprMsgeNo";
		private static final String GET_ONE_STMT = 
			"SELECT pprMsgeNo,pprSpotNo,pprCheckIn,pprCheckOut,pprMsgeCtx,pprMsgeImg FROM proprietorArea where pprMsgeNo = ?";
		private static final String DELETE = 
			"DELETE FROM proprietorArea where pprMsgeNo =?";
		private static final String UPDATE = 
			"UPDATE proprietorArea set  pprSpotNo=?, pprCheckIn=?, pprCheckOut=?, pprMsgeCtx=?, pprMsgeImg=?  where pprMsgeNo = ?";	
	
		// �ϥ�byte[]�覡
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
		
//		public static byte[] getPictureByteArray(String path) throws IOException {//�P�W�\��,��²�ƪ��g�k			
//			FileInputStream in = new FileInputStream(path);
//			byte[] buffer = new byte[in.available()];
//			in.read(buffer);
//			in.close();
//			return buffer;			
//		}//簡易版
		
	@Override
	public void insert(PprVO pprvo) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
System.out.println("1");


			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, pprvo.getPprSpotNo());
			pstmt.setTimestamp(2, pprvo.getPprCheckIn());
			pstmt.setTimestamp(3, pprvo.getPprCheckOut());
			pstmt.setString(4, pprvo.getPprMsgeCtx());
			pstmt.setBytes(5, pprvo.getPprMsgeImg());
			
System.out.println("11");
			pstmt.executeUpdate();
System.out.println("111");
			
		} 
			// Handle any SQL errors
		 catch (SQLException se) {
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
	public void update(PprVO pprvo) {
		System.out.println("update11111");
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			System.out.println("update222222");
			pstmt.setInt(1, pprvo.getPprSpotNo());
			pstmt.setTimestamp(2, pprvo.getPprCheckIn());
			pstmt.setTimestamp(3, pprvo.getPprCheckOut());
			pstmt.setString(4, pprvo.getPprMsgeCtx());
			pstmt.setBytes(5, pprvo.getPprMsgeImg());
			pstmt.setInt(6, pprvo.getPprMsgeNo());
			System.out.println("update");
			pstmt.executeUpdate();
			
			// Handle any driver errors
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
	public void delete(Integer pprvo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, pprvo);

			pstmt.executeUpdate();

			// Handle any driver errors		
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
	public PprVO findByPrimaryKey(Integer pprMsgeNo) {
		
		PprVO pprvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, pprMsgeNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// pprvo �]�٬� Domain objects
				pprvo = new PprVO();
				pprvo.setPprMsgeNo(rs.getInt("pprMsgeNo"));
				pprvo.setPprSpotNo(rs.getInt("pprSpotNo"));
				pprvo.setPprCheckIn(rs.getTimestamp("pprCheckIn"));
				pprvo.setPprCheckOut(rs.getTimestamp("pprCheckOut"));
				pprvo.setPprMsgeCtx(rs.getString("pprMsgeCtx"));
				pprvo.setPprMsgeImg(rs.getBytes("pprMsgeImg"));			
			}

			// Handle any driver errors		
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
		return pprvo;
	}

	@Override
	public List<PprVO> getAll() {
		List<PprVO> list = new ArrayList<PprVO>();
		
		
		PprVO pprvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// pprvo �]�٬� Domain objects
				pprvo = new PprVO();
				pprvo.setPprMsgeNo(rs.getInt("pprMsgeNo"));
				pprvo.setPprSpotNo(rs.getInt("pprSpotNo"));
				pprvo.setPprCheckIn(rs.getTimestamp("pprCheckIn"));
				pprvo.setPprCheckOut(rs.getTimestamp("pprCheckOut"));
				pprvo.setPprMsgeCtx(rs.getString("pprMsgeCtx"));
				pprvo.setPprMsgeImg(rs.getBytes("pprMsgeImg"));
				list.add(pprvo);// Store the row in the list
			}

			// Handle any driver errors		
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
}

	
