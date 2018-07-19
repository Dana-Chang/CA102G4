package com.member.model;

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

import com.storeInf.model.StoreInfJDBCDAO;
import com.storeInf.model.StoreInfVO;

public class MemberJDBCDAO implements MemberDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "AA107G5";
	String passwd = "AA107G5";

	private static final String INSERT_STMT = "INSERT INTO member (memId,memName,memEmail,memPsw,memGender,memType,memStatus,memCell,memTel,memAdd,memBop,memCb,memTdp,memDm,memNomd,memPf,memPtnor,memTaiv,memBp,memPorder,memSp,memOrderm,memMtv,memImg) VALUES (member_seq.NEXTVAL, ?, ?, ?, ?, ?, ?,"
			+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT memId,memName,memEmail,memPsw,memGender,memType,memStatus,memCell,memTel,memAdd,memBop,memCb,memTdp,memDm,memNomd,memPf,memPtnor,memTaiv,memBp,memPorder,memSp,memOrderm,memMtv,memImg FROM member order by memid";
	private static final String GET_ONE_STMT = "SELECT memId,memName,memEmail,memPsw,memGender,memType,memStatus,memCell,memTel,memAdd,memBop,memCb,memTdp,memDm,memNomd,memPf,memPtnor,memTaiv,memBp,memPorder,memSp,memOrderm,memMtv,memImg FROM member where memid = ?";
	private static final String DELETE = "DELETE FROM member where memid = ?";
	private static final String UPDATE = "UPDATE member set memName=?,memEmail=?,memPsw=?,memGender=?,memType=?,memStatus=?,memCell=?,memTel=?,memAdd=?,memBop=?,memCb=?,memTdp=?,memDm=?,memNomd=?,memPf=?,memPtnor=?,memTaiv=?,memBp=?,memPorder=?,memSp=?,memOrderm=?,memMtv=?,memImg=? where memId=?";
	private static final String GET_ONE_MEMBER_EMAIL = "SELECT memId,memName,memEmail,memPsw,memGender,memType,memStatus,memCell,memTel,memAdd,memBop,memCb,memTdp,memDm,memNomd,memPf,memPtnor,memTaiv,memBp,memPorder,memSp,memOrderm,memMtv,memImg FROM member where memEmail = ?";

	@Override
	public void insert(MemberVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, memVO.getMemName());
			pstmt.setString(2, memVO.getMemEmail());
			pstmt.setString(3, memVO.getMemPsw());
			pstmt.setInt(4, memVO.getMemGender());
			pstmt.setString(5, memVO.getMemType());
			pstmt.setInt(6, memVO.getMemStatus());
			pstmt.setString(7, memVO.getMemCell());
			pstmt.setString(8, memVO.getMemTel());
			pstmt.setString(9, memVO.getMemAdd());
			pstmt.setInt(10, memVO.getMemBop());
			pstmt.setInt(11, memVO.getMemCb());
			pstmt.setInt(12, memVO.getMemTdp());
			pstmt.setString(13, memVO.getMemDm());
			pstmt.setInt(14, memVO.getMemNomd());
			pstmt.setString(15, memVO.getMemPf());
			pstmt.setInt(16, memVO.getMemPtnor());
			pstmt.setString(17, memVO.getMemTaiv());
			pstmt.setInt(18, memVO.getMemBp());
			pstmt.setInt(19, memVO.getMemPorder());
			pstmt.setInt(20, memVO.getMemSp());
			pstmt.setInt(21, memVO.getMemOrderm());
			pstmt.setInt(22, memVO.getMemMtv());
			pstmt.setBytes(23, memVO.getMemImg());

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
		return;
	}

	@Override
	public void update(MemberVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, memVO.getMemName());
			pstmt.setString(2, memVO.getMemEmail());
			pstmt.setString(3, memVO.getMemPsw());
			pstmt.setInt(4, memVO.getMemGender());
			pstmt.setString(5, memVO.getMemType());
			pstmt.setInt(6, memVO.getMemStatus());
			pstmt.setString(7, memVO.getMemCell());
			pstmt.setString(8, memVO.getMemTel());
			pstmt.setString(9, memVO.getMemAdd());
			pstmt.setInt(10, memVO.getMemBop());
			pstmt.setInt(11, memVO.getMemCb());
			pstmt.setInt(12, memVO.getMemTdp());
			pstmt.setString(13, memVO.getMemDm());
			pstmt.setInt(14, memVO.getMemNomd());
			pstmt.setString(15, memVO.getMemPf());
			pstmt.setInt(16, memVO.getMemPtnor());
			pstmt.setString(17, memVO.getMemTaiv());
			pstmt.setInt(18, memVO.getMemBp());
			pstmt.setInt(19, memVO.getMemPorder());
			pstmt.setInt(20, memVO.getMemSp());
			pstmt.setInt(21, memVO.getMemOrderm());
			pstmt.setInt(22, memVO.getMemMtv());
			pstmt.setBytes(23, memVO.getMemImg());
			pstmt.setInt(24, memVO.getMemId());

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
		return;
	}

	@Override
	public void delete(Integer memid) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memid);

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
	public MemberVO findByPrimaryKey(Integer memid) {

		MemberVO memVO = null;
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
				memVO = new MemberVO();
				memVO.setMemId(rs.getInt("memId"));
				memVO.setMemName(rs.getString("memName"));
				memVO.setMemEmail(rs.getString("memEmail"));
				memVO.setMemPsw(rs.getString("memPsw"));
				memVO.setMemGender(rs.getInt("memGender"));
				memVO.setMemType(rs.getString("memType"));
				memVO.setMemStatus(rs.getInt("memStatus"));
				memVO.setMemCell(rs.getString("memCell"));
				memVO.setMemTel(rs.getString("memTel"));
				memVO.setMemAdd(rs.getString("memAdd"));
				memVO.setMemBop(rs.getInt("memBop"));
				memVO.setMemCb(rs.getInt("memCb"));
				memVO.setMemTdp(rs.getInt("memTdp"));
				memVO.setMemDm(rs.getString("memDm"));
				memVO.setMemNomd(rs.getInt("memNomd"));
				memVO.setMemPf(rs.getString("memPf"));
				memVO.setMemPtnor(rs.getInt("memPtnor"));
				memVO.setMemTaiv(rs.getString("memTaiv"));
				memVO.setMemBp(rs.getInt("memBp"));
				memVO.setMemPorder(rs.getInt("memPorder"));
				memVO.setMemSp(rs.getInt("memSp"));
				memVO.setMemOrderm(rs.getInt("memOrderm"));
				memVO.setMemMtv(rs.getInt("memMtv"));
				memVO.setMemImg(rs.getBytes("memImg"));
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
		return memVO;
	}

	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				memVO = new MemberVO();
				memVO.setMemId(rs.getInt("memId"));
				memVO.setMemName(rs.getString("memName"));
				memVO.setMemEmail(rs.getString("memEmail"));
				memVO.setMemPsw(rs.getString("memPsw"));
				memVO.setMemGender(rs.getInt("memGender"));
				memVO.setMemType(rs.getString("memType"));
				memVO.setMemStatus(rs.getInt("memStatus"));
				memVO.setMemCell(rs.getString("memCell"));
				memVO.setMemTel(rs.getString("memTel"));
				memVO.setMemAdd(rs.getString("memAdd"));
				memVO.setMemBop(rs.getInt("memBop"));
				memVO.setMemCb(rs.getInt("memCb"));
				memVO.setMemTdp(rs.getInt("memTdp"));
				memVO.setMemDm(rs.getString("memDm"));
				memVO.setMemNomd(rs.getInt("memNomd"));
				memVO.setMemPf(rs.getString("memPf"));
				memVO.setMemPtnor(rs.getInt("memPtnor"));
				memVO.setMemTaiv(rs.getString("memTaiv"));
				memVO.setMemBp(rs.getInt("memBp"));
				memVO.setMemPorder(rs.getInt("memPorder"));
				memVO.setMemSp(rs.getInt("memSp"));
				memVO.setMemOrderm(rs.getInt("memOrderm"));
				memVO.setMemMtv(rs.getInt("memMtv"));
				memVO.setMemImg(rs.getBytes("memImg"));
				list.add(memVO); // Store the row in the vector
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
	public MemberVO findMemberEmail(String memEmail) {
		MemberVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_MEMBER_EMAIL);

			pstmt.setString(1, memEmail);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				memVO = new MemberVO();
				memVO.setMemId(rs.getInt("memId"));
				memVO.setMemName(rs.getString("memName"));
				memVO.setMemEmail(rs.getString("memEmail"));
				memVO.setMemPsw(rs.getString("memPsw"));
				memVO.setMemGender(rs.getInt("memGender"));
				memVO.setMemType(rs.getString("memType"));
				memVO.setMemStatus(rs.getInt("memStatus"));
				memVO.setMemCell(rs.getString("memCell"));
				memVO.setMemTel(rs.getString("memTel"));
				memVO.setMemAdd(rs.getString("memAdd"));
				memVO.setMemBop(rs.getInt("memBop"));
				memVO.setMemCb(rs.getInt("memCb"));
				memVO.setMemTdp(rs.getInt("memTdp"));
				memVO.setMemDm(rs.getString("memDm"));
				memVO.setMemNomd(rs.getInt("memNomd"));
				memVO.setMemPf(rs.getString("memPf"));
				memVO.setMemPtnor(rs.getInt("memPtnor"));
				memVO.setMemTaiv(rs.getString("memTaiv"));
				memVO.setMemBp(rs.getInt("memBp"));
				memVO.setMemPorder(rs.getInt("memPorder"));
				memVO.setMemSp(rs.getInt("memSp"));
				memVO.setMemOrderm(rs.getInt("memOrderm"));
				memVO.setMemMtv(rs.getInt("memMtv"));
				memVO.setMemImg(rs.getBytes("memImg"));
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
		return memVO;
	}

	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();
		return baos.toByteArray();
	}

	public static void main(String[] args) throws IOException {

		MemberJDBCDAO dao = new MemberJDBCDAO();

		// 新增
		MemberVO memVO1 = new MemberVO();
		memVO1.setMemName("愛旅遊");
		memVO1.setMemEmail("chenrice111@gmail.com");
		memVO1.setMemPsw("123");
		memVO1.setMemGender(2);
		// 身分>一般會員、合作店家
		memVO1.setMemType("2");
		// 帳號狀態>1: 啟用、2: 停用（會員選擇停用）、3: 待審核（店家）
		// 4: 審核未過（店家）、5: 違規次數過多停權、6: 重大交易違規停權（比如詐欺）
		// 預設=1
		memVO1.setMemStatus(1);
		memVO1.setMemCell("0916822358");
		memVO1.setMemTel("(20)-1234567");
		memVO1.setMemAdd("新北市永和區中正路10巷100號");
		// 點數餘額
		memVO1.setMemBop(150000);
		// 現金餘額
		memVO1.setMemCb(30000);
		// 暫扣點數
		memVO1.setMemTdp(2000);
		// 禁止留言 1:禁言 預設=0
		memVO1.setMemDm("0");
		// 禁止留言次數 達3次永久禁止
		memVO1.setMemNomd(0);
		// 禁止檢舉 1:禁止檢舉 預設=0
		memVO1.setMemPf("0");
		// 禁止檢舉次數 達3次永久禁止
		memVO1.setMemPtnor(2);
		// 所有文章可見 預設=1
		memVO1.setMemTaiv("1");
		// 買家積分
		memVO1.setMemBp(5000);
		// 請代購訂單數
		memVO1.setMemPorder(9);
		// 賣家積分
		memVO1.setMemSp(2000);
		// 幫代購訂單數
		memVO1.setMemOrderm(2);
		// 重大交易違規
		memVO1.setMemMtv(2000);
		memVO1.setMemImg(getPictureByteArray("WebContent/img/icon/nowtryurtrip_logo.png"));

		StoreInfVO storeInfVO1 = new StoreInfVO();
		 // 統一編號
		 storeInfVO1.setUniformNum(26598563);
		 // 負責人
		 storeInfVO1.setPrincipal("愛搗蛋");
		 storeInfVO1.setStorCall("0965895363");
		 storeInfVO1.setStorAdd("桃園市中壢區中大路300號");
		 storeInfVO1.setPostalCode(359);
		 storeInfVO1.setCategory(2);
		 
		 dao.insertWithStoreInf(memVO1, storeInfVO1);

		System.out.println("新增完成");

		// 修改
		// MemVO memVO2 = new MemVO();
		// memVO2.setMemId(1007);
		// memVO2.setMemName("林家緯11");
		// memVO2.setMemEmail("chenrice111@gmail.com");
		// memVO2.setMemPsw("123");
		// memVO2.setMemGender(2);
		// // 身分>1:一般會員、2:合作店家
		// memVO2.setMemType("2");
		// // 帳號狀態>1: 啟用、2: 停用（會員選擇停用）、3: 待審核（店家）
		// // 4: 審核未過（店家）、5: 違規次數過多停權、6: 重大交易違規停權（比如詐欺）
		// // 預設=1
		// memVO2.setMemStatus(1);
		// memVO2.setMemCell("0916822358");
		// memVO2.setMemTel("(20)-1234567");
		// memVO2.setMemAdd("新北市永和區中正路10巷100號");
		// // 點數餘額
		// memVO2.setMemBop(150000);
		// // 現金餘額
		// memVO2.setMemCb(30000);
		// // 暫扣點數
		// memVO2.setMemTdp(2000);
		// // 禁止留言 1:禁言 預設=0
		// memVO2.setMemDm("0");
		// // 禁止留言次數 達3次永久禁止
		// memVO2.setMemNomd(0);
		// // 禁止檢舉 1:禁止檢舉 預設=0
		// memVO2.setMemPf("1");
		// // 禁止檢舉次數 達3次永久禁止
		// memVO2.setMemPtnor(2);
		// // 所有文章可見 預設=1
		// memVO2.setMemTaiv("1");
		// // 買家積分
		// memVO2.setMemBp(5000);
		// // 請代購訂單數
		// memVO2.setMemPorder(9);
		// // 賣家積分
		// memVO2.setMemSp(2000);
		// // 幫代購訂單數
		// memVO2.setMemOrderm(2);
		// // 重大交易違規
		// memVO2.setMemMtv(2000);
		// memVO2.setMemImg(null);
		// dao.update(memVO2);
		// System.out.println("修改成功");

		// 刪除
		// dao.delete(1004);
		// System.out.println("刪除成功");

		// 查詢
		// MemberVO memVO3 = dao.findByPrimaryKey(0002);
		// System.out.print(memVO3.getMemId() + ",");
		// System.out.print(memVO3.getMemName() + ",");
		// System.out.print(memVO3.getMemEmail() + ",");
		// System.out.print(memVO3.getMemPsw() + ",");
		// System.out.print(memVO3.getMemGender() + ",");
		// System.out.print(memVO3.getMemType() + ",");
		// System.out.print(memVO3.getMemStatus() + ",");
		// System.out.print(memVO3.getMemCell() + ",");
		// System.out.print(memVO3.getMemTel() + ",");
		// System.out.print(memVO3.getMemAdd() + ",");
		// System.out.print(memVO3.getMemBop() + ",");
		// System.out.print(memVO3.getMemCb() + ",");
		// System.out.print(memVO3.getMemTdp() + ",");
		// System.out.print(memVO3.getMemDm() + ",");
		// System.out.print(memVO3.getMemNomd() + ",");
		// System.out.print(memVO3.getMemPf() + ",");
		// System.out.print(memVO3.getMemPtnor() + ",");
		// System.out.print(memVO3.getMemTaiv() + ",");
		// System.out.print(memVO3.getMemBp() + ",");
		// System.out.print(memVO3.getMemPorder() + ",");
		// System.out.print(memVO3.getMemSp() + ",");
		// System.out.print(memVO3.getMemOrderm() + ",");
		// System.out.print(memVO3.getMemMtv() + ",");
		// System.out.println(memVO3.getMemImg());
		// System.out.println("---------------------");
		//
		// // 查詢
		// List<MemberVO> list = dao.getAll();
		// for (MemberVO amember : list) {
		// System.out.print(amember.getMemId() + ",");
		// System.out.print(amember.getMemName() + ",");
		// System.out.print(amember.getMemEmail() + ",");
		// System.out.print(amember.getMemPsw() + ",");
		// System.out.print(amember.getMemGender() + ",");
		// System.out.print(amember.getMemType() + ",");
		// System.out.print(amember.getMemStatus() + ",");
		// System.out.print(amember.getMemCell() + ",");
		// System.out.print(amember.getMemTel() + ",");
		// System.out.print(amember.getMemAdd() + ",");
		// System.out.print(amember.getMemBop() + ",");
		// System.out.print(amember.getMemCb() + ",");
		// System.out.print(amember.getMemTdp() + ",");
		// System.out.print(amember.getMemDm() + ",");
		// System.out.print(amember.getMemNomd() + ",");
		// System.out.print(amember.getMemPf() + ",");
		// System.out.print(amember.getMemPtnor() + ",");
		// System.out.print(amember.getMemTaiv() + ",");
		// System.out.print(amember.getMemBp() + ",");
		// System.out.print(amember.getMemPorder() + ",");
		// System.out.print(amember.getMemSp() + ",");
		// System.out.print(amember.getMemOrderm() + ",");
		// System.out.print(amember.getMemMtv() + ",");
		// System.out.print(amember.getMemImg());
		// System.out.println();
		// }
		// MemberVO memVO4 = dao.findMemberEmail("leekv820309@gmail.com");
		// System.out.print(memVO4.getMemId() + ",");
		// System.out.print(memVO4.getMemName() + ",");
		// System.out.print(memVO4.getMemEmail() + ",");
		// System.out.print(memVO4.getMemPsw() + ",");
		// System.out.print(memVO4.getMemGender() + ",");
		// System.out.print(memVO4.getMemType() + ",");
		// System.out.print(memVO4.getMemStatus() + ",");
		// System.out.print(memVO4.getMemCell() + ",");
		// System.out.print(memVO4.getMemTel() + ",");
		// System.out.print(memVO4.getMemAdd() + ",");
		// System.out.print(memVO4.getMemBop() + ",");
		// System.out.print(memVO4.getMemCb() + ",");
		// System.out.print(memVO4.getMemTdp() + ",");
		// System.out.print(memVO4.getMemDm() + ",");
		// System.out.print(memVO4.getMemNomd() + ",");
		// System.out.print(memVO4.getMemPf() + ",");
		// System.out.print(memVO4.getMemPtnor() + ",");
		// System.out.print(memVO4.getMemTaiv() + ",");
		// System.out.print(memVO4.getMemBp() + ",");
		// System.out.print(memVO4.getMemPorder() + ",");
		// System.out.print(memVO4.getMemSp() + ",");
		// System.out.print(memVO4.getMemOrderm() + ",");
		// System.out.print(memVO4.getMemMtv() + ",");
		// System.out.println(memVO4.getMemImg());
		// System.out.println("---------------------");
	}

	@Override
	public void insertWithStoreInf(MemberVO memberVO, StoreInfVO storeInfVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, userid, passwd);

			con.setAutoCommit(false);
			String cols[] = { "MEMID" };
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setString(1, memberVO.getMemName());
			pstmt.setString(2, memberVO.getMemEmail());
			pstmt.setString(3, memberVO.getMemPsw());
			pstmt.setInt(4, memberVO.getMemGender());
			pstmt.setString(5, memberVO.getMemType());
			pstmt.setInt(6, memberVO.getMemStatus());
			pstmt.setString(7, memberVO.getMemCell());
			pstmt.setString(8, memberVO.getMemTel());
			pstmt.setString(9, memberVO.getMemAdd());
			pstmt.setInt(10, memberVO.getMemBop());
			pstmt.setInt(11, memberVO.getMemCb());
			pstmt.setInt(12, memberVO.getMemTdp());
			pstmt.setString(13, memberVO.getMemDm());
			pstmt.setInt(14, memberVO.getMemNomd());
			pstmt.setString(15, memberVO.getMemPf());
			pstmt.setInt(16, memberVO.getMemPtnor());
			pstmt.setString(17, memberVO.getMemTaiv());
			pstmt.setInt(18, memberVO.getMemBp());
			pstmt.setInt(19, memberVO.getMemPorder());
			pstmt.setInt(20, memberVO.getMemSp());
			pstmt.setInt(21, memberVO.getMemOrderm());
			pstmt.setInt(22, memberVO.getMemMtv());
			pstmt.setBytes(23, memberVO.getMemImg());
			pstmt.executeUpdate();

			String next_memId = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_memId = rs.getString(1);
				System.out.println("自增主鍵值= " + next_memId + "(剛新增成功的會員編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();

			StoreInfJDBCDAO dao = new StoreInfJDBCDAO();
			storeInfVO.setMemId(new Integer(next_memId));
			dao.insert2(storeInfVO, con);
			
			con.commit();
			con.setAutoCommit(true);
			
			// Handle any driver errors
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
		return ;
	}

	@Override
	public void updatePoint(MemberVO memberVO, Connection con) {
		// TODO Auto-generated method stub
		
	}
}
