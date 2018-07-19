package com.member.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.storeInf.model.StoreInfJDBCDAO;
import com.storeInf.model.StoreInfVO;

public class MemberDAO implements MemberDAO_interface {
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/AA107G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

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

			con = ds.getConnection();
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

			con = ds.getConnection();
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
	
	public void updatePoint(MemberVO memVO , Connection con) {
		
		PreparedStatement pstmt = null;

		try {
			
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
		
		} catch (SQLException se) {
			
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
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
		return;
	}
	
	@Override
	public void delete(Integer memid) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memid);

			pstmt.executeUpdate();

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
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

			con = ds.getConnection();
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

			con = ds.getConnection();
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

			con = ds.getConnection();
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
	public void insertWithStoreInf(MemberVO memberVO, StoreInfVO storeInfVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

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
}
