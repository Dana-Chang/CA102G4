package com.memRecord.model;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MemRecordJDBCDAO implements MemRecordDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "AA107";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO memRecord (violationId,memId,isSuspended,suspCat,suspReason,suspStart,suspDays,rprtSrc,mgrId) VALUES (record_seq.NEXTVAL,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT violationId,memId,isSuspended,suspCat,suspReason,to_char(suspStart,'yyyy-mm-dd hh:mm:ss') suspStart,suspDays,rprtSrc,mgrId FROM memRecord order by violationId";
	private static final String GET_ONE_STMT = "SELECT violationId,memId,isSuspended,suspCat,suspReason,to_char(suspStart,'yyyy-mm-dd hh:mm:ss') suspStart,suspDays,rprtSrc,mgrId FROM memRecord where violationId = ?";
	private static final String DELETE = "DELETE FROM memRecord where violationId = ?";
	private static final String UPDATE = "UPDATE memRecord set memId=?,isSuspended=?,suspCat=?,suspReason=?,suspStart=?,suspDays=?,rprtSrc=?,mgrId=? where violationId=?";

	@Override
	public void insert(MemRecordVO memRecordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, memRecordVO.getMemId());
			pstmt.setInt(2, memRecordVO.getIsSuspended());
			pstmt.setString(3, memRecordVO.getSuspCat());
			pstmt.setString(4, memRecordVO.getSuspReason());
			pstmt.setTimestamp(5, memRecordVO.getSuspStart());
			pstmt.setInt(6, memRecordVO.getSuspDays());
			pstmt.setInt(7, memRecordVO.getRprtSrc());
			pstmt.setInt(8, memRecordVO.getMgrId());
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
		return;
	}

	@Override
	public void update(MemRecordVO memRecordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, memRecordVO.getMemId());
			pstmt.setInt(2, memRecordVO.getIsSuspended());
			pstmt.setString(3, memRecordVO.getSuspCat());
			pstmt.setString(4, memRecordVO.getSuspReason());
			pstmt.setTimestamp(5, memRecordVO.getSuspStart());
			pstmt.setInt(6, memRecordVO.getSuspDays());
			pstmt.setInt(7, memRecordVO.getRprtSrc());
			pstmt.setInt(8, memRecordVO.getMgrId());
			pstmt.setInt(9, memRecordVO.getViolationId());
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
		return;
	}

	@Override
	public void delete(Integer violationId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, violationId);
			pstmt.executeUpdate();
			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
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
		return;
	}

	@Override
	public MemRecordVO findByPrimaryKey(Integer violationId) {
		MemRecordVO memRecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, violationId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				memRecordVO = new MemRecordVO();
				memRecordVO.setViolationId(rs.getInt("violationId"));
				memRecordVO.setMemId(rs.getInt("memId"));
				memRecordVO.setIsSuspended(rs.getInt("isSuspended"));
				memRecordVO.setSuspCat(rs.getString("suspCat"));
				memRecordVO.setSuspReason(rs.getString("suspReason"));
				memRecordVO.setSuspStart(rs.getTimestamp("suspStart"));
				memRecordVO.setSuspDays(rs.getInt("suspDays"));
				memRecordVO.setRprtSrc(rs.getInt("rprtSrc"));
				memRecordVO.setMgrId(rs.getInt("mgrId"));

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
		return memRecordVO;
	}

	@Override
	public List<MemRecordVO> getAll() {
		List<MemRecordVO> list = new ArrayList<MemRecordVO>();
		MemRecordVO memRecordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memRecordVO = new MemRecordVO();
				memRecordVO.setViolationId(rs.getInt("violationId"));
				memRecordVO.setMemId(rs.getInt("memId"));
				memRecordVO.setIsSuspended(rs.getInt("isSuspended"));
				memRecordVO.setSuspCat(rs.getString("suspCat"));
				memRecordVO.setSuspReason(rs.getString("suspReason"));
				memRecordVO.setSuspStart(rs.getTimestamp("suspStart"));
				memRecordVO.setSuspDays(rs.getInt("suspDays"));
				memRecordVO.setRprtSrc(rs.getInt("rprtSrc"));
				memRecordVO.setMgrId(rs.getInt("mgrId"));
				list.add(memRecordVO);

			}
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

	public static void main(String[] args) throws IOException, ParseException {

		MemRecordJDBCDAO dao = new MemRecordJDBCDAO();

		// 新增

		// MemRecordVO memRecordVO1 = new MemRecordVO();
		// memRecordVO1.setMemId(1004);
		// // 是否停權 0預設 1=停權
		// memRecordVO1.setIsSuspended(1);
		// // 停權種類>禁止留言、禁止檢舉、停權（禁止檢舉選項應該是由管理員開啟，
		// // 因為發現該會員常常檢舉，可是檢舉內容常常不適當，有浮濫情形）
		// memRecordVO1.setSuspCat("1");
		// memRecordVO1.setSuspReason("遭多人檢舉");
		// memRecordVO1.setSuspStart(Timestamp.valueOf("2012-02-24 13:12:11"));
		// memRecordVO1.setSuspDays(2);
		// // 檢舉來源>由於被檢舉來源的pk都不同，所以不能用一對多關係作關聯。關於檢舉來源數字代表的意義：
		// // 1: 部落格留言、2: 部落格文章、3: 會員地點評論留言、4: 套裝行程評論的留言（停權是因為什麼地方的發言被檢舉）5: 浮濫檢舉
		// memRecordVO1.setRprtSrc(3);
		// // 管理員編號
		// memRecordVO1.setMgrId(7002);
		// dao.insert(memRecordVO1);
		// System.out.println("新增完成");

		// 修改
		// MemRecordVO memRecordVO2 = new MemRecordVO();
		// memRecordVO2.setMemId(1004);
		// // 是否停權 0預設 1=停權
		// memRecordVO2.setIsSuspended(1);
		// // 停權種類>禁止留言、禁止檢舉、停權（禁止檢舉選項應該是由管理員開啟，
		// // 因為發現該會員常常檢舉，可是檢舉內容常常不適當，有浮濫情形）
		// memRecordVO2.setSuspCat("1");
		// memRecordVO2.setSuspReason("遭多人檢舉");
		// memRecordVO2.setSuspStart(Timestamp.valueOf("2012-12-24 22:12:11"));
		// memRecordVO2.setSuspDays(2);
		// // 檢舉來源>由於被檢舉來源的pk都不同，所以不能用一對多關係作關聯。關於檢舉來源數字代表的意義：
		// // 1: 部落格留言、2: 部落格文章、3: 會員地點評論留言、4: 套裝行程評論的留言（停權是因為什麼地方的發言被檢舉）5: 浮濫檢舉
		// memRecordVO2.setRprtSrc(2);
		// // 管理員編號
		// memRecordVO2.setMgrId(7003);
		// memRecordVO2.setViolationId(1);
		// dao.update(memRecordVO2);
		// System.out.println("修改成功");

		// 刪除
		// dao.delete(1);
		// System.out.println("刪除成功");

		// 查詢
		MemRecordVO memRecordVO3 = dao.findByPrimaryKey(1);
		System.out.print(memRecordVO3.getViolationId() + ",");
		System.out.print(memRecordVO3.getMemId() + ",");
		System.out.print(memRecordVO3.getIsSuspended() + ",");
		System.out.print(memRecordVO3.getSuspCat() + ",");
		System.out.print(memRecordVO3.getSuspReason() + ",");
		System.out.print(memRecordVO3.getSuspStart() + ",");
		System.out.print(memRecordVO3.getSuspDays() + ",");
		System.out.print(memRecordVO3.getRprtSrc() + ",");
		System.out.println(memRecordVO3.getMgrId() + ",");
		System.out.println("---------------------");
		//
		// // 查詢
		List<MemRecordVO> list = dao.getAll();
		for (MemRecordVO amember : list) {
			System.out.print(amember.getViolationId() + ",");
			System.out.print(amember.getMemId() + ",");
			System.out.print(amember.getIsSuspended() + ",");
			System.out.print(amember.getSuspCat() + ",");
			System.out.print(amember.getSuspReason() + ",");
			System.out.print(amember.getSuspStart() + ",");
			System.out.print(amember.getSuspDays() + ",");
			System.out.print(amember.getRprtSrc() + ",");
			System.out.print(amember.getMgrId() + ",");
			System.out.println();

		}
	}
}
