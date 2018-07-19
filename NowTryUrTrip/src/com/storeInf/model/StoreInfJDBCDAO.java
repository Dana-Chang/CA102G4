package com.storeInf.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class StoreInfJDBCDAO implements StoreInfDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "AA107G5";
	String passwd = "AA107G5";

	private static final String INSERT_STMT = "INSERT INTO storeInf (memId,uniformNum,principal,storCall,storAdd,postalCode,category) VALUES (?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT memId,uniformNum,principal,storCall,storAdd,postalCode,category FROM storeInf order by memid";
	private static final String GET_ONE_STMT = "SELECT memId,uniformNum,principal,storCall,storAdd,postalCode,category FROM storeInf where memid = ?";
	private static final String DELETE = "DELETE FROM storeInf where memid = ?";
	private static final String UPDATE = "UPDATE storeInf set uniformNum=?,principal=?,storCall=?,storAdd=?,postalCode=?,category=? where memId=?";

	@Override
	public void insert(StoreInfVO storeInfVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, storeInfVO.getMemId());
			pstmt.setInt(2, storeInfVO.getUniformNum());
			pstmt.setString(3, storeInfVO.getPrincipal());
			pstmt.setString(4, storeInfVO.getStorCall());
			pstmt.setString(5, storeInfVO.getStorAdd());
			pstmt.setInt(6, storeInfVO.getPostalCode());
			pstmt.setInt(7, storeInfVO.getCategory());
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
	public void update(StoreInfVO storeInfVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, storeInfVO.getUniformNum());
			pstmt.setString(2, storeInfVO.getPrincipal());
			pstmt.setString(3, storeInfVO.getStorCall());
			pstmt.setString(4, storeInfVO.getStorAdd());
			pstmt.setInt(5, storeInfVO.getPostalCode());
			pstmt.setInt(6, storeInfVO.getCategory());
			pstmt.setInt(7, storeInfVO.getMemId());
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
	public void delete(Integer memId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memId);
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
	public StoreInfVO findByPrimaryKey(Integer memid) {
		StoreInfVO storeInfVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, memid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				storeInfVO = new StoreInfVO();
				storeInfVO.setMemId(rs.getInt("memId"));
				storeInfVO.setUniformNum(rs.getInt("uniformNum"));
				storeInfVO.setPrincipal(rs.getString("principal"));
				storeInfVO.setStorCall(rs.getString("storCall"));
				storeInfVO.setStorAdd(rs.getString("storAdd"));
				storeInfVO.setPostalCode(rs.getInt("postalCode"));
				storeInfVO.setCategory(rs.getInt("category"));

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
		return storeInfVO;
	}

	@Override
	public List<StoreInfVO> getAll() {
		List<StoreInfVO> list = new ArrayList<StoreInfVO>();
		StoreInfVO storeInfVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				storeInfVO = new StoreInfVO();
				storeInfVO.setMemId(rs.getInt("memId"));
				storeInfVO.setUniformNum(rs.getInt("uniformNum"));
				storeInfVO.setPrincipal(rs.getString("principal"));
				storeInfVO.setStorCall(rs.getString("storCall"));
				storeInfVO.setStorAdd(rs.getString("storAdd"));
				storeInfVO.setPostalCode(rs.getInt("postalCode"));
				storeInfVO.setCategory(rs.getInt("category"));
				list.add(storeInfVO);

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

	public static void main(String[] args) {

		StoreInfJDBCDAO dao = new StoreInfJDBCDAO();

		// 新增

		StoreInfVO storeInfVO1 = new StoreInfVO();
		// 統一編號
		
		storeInfVO1.setUniformNum(26598563);
		// 負責人
		storeInfVO1.setPrincipal("愛搗蛋");
		storeInfVO1.setStorCall("0965895363");
		storeInfVO1.setStorAdd("桃園市中壢區中大路300號");
		storeInfVO1.setPostalCode(359);
		storeInfVO1.setCategory(2);
		System.out.println("新增完成");
		dao.insert(storeInfVO1);
//
//		MemberVO memVO1 = new MemberVO();
//		memVO1.setMemName("愛旅遊");
//		memVO1.setMemEmail("chenrice111@gmail.com");
//		memVO1.setMemPsw("123");
//		memVO1.setMemGender(2);
//		// 身分>一般會員、合作店家
//		memVO1.setMemType("2");
//		// 帳號狀態>1: 啟用、2: 停用（會員選擇停用）、3: 待審核（店家）
//		// 4: 審核未過（店家）、5: 違規次數過多停權、6: 重大交易違規停權（比如詐欺）
//		// 預設=1
//		memVO1.setMemStatus(1);
//		memVO1.setMemCell("0916822358");
//		memVO1.setMemTel("(20)-1234567");
//		memVO1.setMemAdd("新北市永和區中正路10巷100號");
//		// 點數餘額
//		memVO1.setMemBop(150000);
//		// 現金餘額
//		memVO1.setMemCb(30000);
//		// 暫扣點數
//		memVO1.setMemTdp(2000);
//		// 禁止留言 1:禁言 預設=0
//		memVO1.setMemDm("0");
//		// 禁止留言次數 達3次永久禁止
//		memVO1.setMemNomd(0);
//		// 禁止檢舉 1:禁止檢舉 預設=0
//		memVO1.setMemPf("0");
//		// 禁止檢舉次數 達3次永久禁止
//		memVO1.setMemPtnor(2);
//		// 所有文章可見 預設=1
//		memVO1.setMemTaiv("1");
//		// 買家積分
//		memVO1.setMemBp(5000);
//		// 請代購訂單數
//		memVO1.setMemPorder(9);
//		// 賣家積分
//		memVO1.setMemSp(2000);
//		// 幫代購訂單數
//		memVO1.setMemOrderm(2);
//		// 重大交易違規
//		memVO1.setMemMtv(2000);
//		memVO1.setMemImg(null);

		

		// StoreInfVO storeInfVO1 = new StoreInfVO();
		// storeInfVO1.setMemId(0004);
		// // 統一編號
		// storeInfVO1.setUniformNum(26598563);
		// // 負責人
		// storeInfVO1.setPrincipal("愛搗蛋");
		// storeInfVO1.setStorCall("0965895363");
		// storeInfVO1.setStorAdd("桃園市中壢區中大路300號");
		// storeInfVO1.setPostalCode(359);
		// storeInfVO1.setCategory(2);
		// dao.insert(storeInfVO1);
		// System.out.println("新增完成");

		// 修改

		// StoreInfVO storeInfVO2 = new StoreInfVO();
		// storeInfVO2.setMemId(1001);
		// // 統一編號
		// storeInfVO2.setUniformNum(65985363);
		// // 負責人
		// storeInfVO2.setPrincipal("愛搗蛋");
		// storeInfVO2.setStorCall("0965895363");
		// storeInfVO2.setStorAdd("桃園市中壢區中大路300號");
		// storeInfVO2.setPostalCode(359);
		// storeInfVO2.setCategory(3);
		// dao.update(storeInfVO2);
		// System.out.println("修改成功");

		// 刪除
		// dao.delete(1003);
		// System.out.println("刪除成功");

		// 查詢
		// StoreInfVO storeInfVO3 = dao.findByPrimaryKey(1);
		// System.out.print(storeInfVO3.getMemId() + ",");
		// System.out.print(storeInfVO3.getUniformNum() + ",");
		// System.out.print(storeInfVO3.getPrincipal() + ",");
		// System.out.print(storeInfVO3.getStorCall() + ",");
		// System.out.print(storeInfVO3.getStorAdd() + ",");
		// System.out.print(storeInfVO3.getPostalCode() + ",");
		// System.out.println(storeInfVO3.getCategory() + ",");
		// System.out.println("---------------------");
		//
		// // 查詢
		// List<StoreInfVO> list = dao.getAll();
		// for (StoreInfVO amember : list) {
		// System.out.print(amember.getMemId() + ",");
		// System.out.print(amember.getUniformNum() + ",");
		// System.out.print(amember.getPrincipal() + ",");
		// System.out.print(amember.getStorCall() + ",");
		// System.out.print(amember.getStorAdd() + ",");
		// System.out.print(amember.getPostalCode() + ",");
		// System.out.print(amember.getCategory() + ",");
		// System.out.println();
		//
		// }

	}

	

	@Override
	public void insert2(StoreInfVO storeInfVO, Connection con) {
		PreparedStatement pstmt = null;
		try {

			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, storeInfVO.getMemId());
			pstmt.setInt(2, storeInfVO.getUniformNum());
			pstmt.setString(3, storeInfVO.getPrincipal());
			pstmt.setString(4, storeInfVO.getStorCall());
			pstmt.setString(5, storeInfVO.getStorAdd());
			pstmt.setInt(6, storeInfVO.getPostalCode());
			pstmt.setInt(7, storeInfVO.getCategory());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-emp");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
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
		}
	}
}
