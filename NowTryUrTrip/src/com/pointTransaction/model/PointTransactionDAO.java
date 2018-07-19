package com.pointTransaction.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.member.model.MemberService;
import com.member.model.MemberVO;

import java.sql.*;

public class PointTransactionDAO implements PointTransactionDAO_interface {
	
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
		"INSERT INTO PointTransaction (transactionNo,memId,startPoint,startCash,transactionDescription,changeCash,transactionTime,changePoint) VALUES (PointTransaction_seq.NEXTVAL, ?, ?, ?, ?, ?,sysdate, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT transactionNo,memId,startPoint,startCash,transactionDescription,changeCash,transactionTime,changePoint FROM PointTransaction order by transactionNo";
	private static final String GET_ONE_STMT = 
		"SELECT transactionNo,memId,startPoint,startCash,transactionDescription,changeCash,transactionTime,changePoint FROM PointTransaction where transactionNo = ?";
	private static final String DELETE = 
		"DELETE FROM PointTransaction where transactionNo = ?";
	private static final String UPDATE = 
		"UPDATE PointTransaction set memId=?, startPoint=?, startCash=?, transactionDescription=?, changeCash=?, changePoint=?,transactionTime=sysdate where transactionNo = ?";

	@Override
	public void insert(PointTransactionVO pointTransactionVO , MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, pointTransactionVO.getMemId());
			pstmt.setInt(2, pointTransactionVO.getStartPoint());
			pstmt.setInt(3, pointTransactionVO.getStartCash());
			pstmt.setString(4, pointTransactionVO.getTransactionDescription());
			pstmt.setInt(5, pointTransactionVO.getChangeCash());
//			pstmt.setString(7, pointTransactionVO.getTransactionTime());
			pstmt.setInt(6, pointTransactionVO.getChangePoint());
			
			pstmt.executeUpdate();
			
			MemberService memSvc = new MemberService();
			MemberVO member = memSvc.getOneMember(pointTransactionVO.getMemId());
			
			int point = 0;
			int cash = 0;
			
			if(pointTransactionVO.getTransactionDescription().equals("儲存點數")){
				point = pointTransactionVO.getStartPoint() + pointTransactionVO.getChangeCash();
				cash = pointTransactionVO.getStartCash() - pointTransactionVO.getChangeCash();
				
				member.setMemBop(point);
				member.setMemCb(cash);
				
			}else if(pointTransactionVO.getTransactionDescription().equals("兌換現金")){
				point = pointTransactionVO.getStartPoint() - pointTransactionVO.getChangeCash();
				cash = pointTransactionVO.getStartCash() + pointTransactionVO.getChangeCash();
					
					member.setMemBop(point);
					member.setMemCb(cash);
			}
			
			memSvc.updatePoint(member, con);
			
			con.commit();
			con.setAutoCommit(true);
			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					se.printStackTrace();
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			
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
	public void update(PointTransactionVO pointTransactionVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, pointTransactionVO.getMemId());
			pstmt.setInt(2, pointTransactionVO.getStartPoint());
			pstmt.setInt(3, pointTransactionVO.getStartCash());
			pstmt.setString(4, pointTransactionVO.getTransactionDescription());
			pstmt.setInt(5, pointTransactionVO.getChangeCash());
//			pstmt.setString(7, pointTransactionVO.getTransactionTime());
			pstmt.setInt(6, pointTransactionVO.getChangePoint());
			pstmt.setInt(7, pointTransactionVO.getTransactionNo());

			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
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
	public void delete(Integer transactionNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, transactionNo);

			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
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
	public PointTransactionVO findByPrimaryKey(Integer transactionNo) {

		PointTransactionVO pointTransactionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, transactionNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// PointTransactionVO 也稱為 Domain objects
				pointTransactionVO = new PointTransactionVO();
				pointTransactionVO.setTransactionNo(rs.getInt("transactionNo"));
				pointTransactionVO.setMemId(rs.getInt("memId"));
				pointTransactionVO.setStartPoint(rs.getInt("startPoint"));
				pointTransactionVO.setStartCash(rs.getInt("startCash"));
				pointTransactionVO.setTransactionDescription(rs.getString("transactionDescription"));
				pointTransactionVO.setChangeCash(rs.getInt("changeCash"));
				pointTransactionVO.setTransactionTime(rs.getTimestamp("transactionTime"));
				pointTransactionVO.setChangePoint(rs.getInt("changePoint"));
			}

			// Handle any driver errors
		}  catch (SQLException se) {
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
		return pointTransactionVO;
	}

	@Override
	public List<PointTransactionVO> getAll() {
		List<PointTransactionVO> list = new ArrayList<PointTransactionVO>();
		PointTransactionVO pointTransactionVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// PointTransactionVO 也稱為 Domain objects
				pointTransactionVO = new PointTransactionVO();
				pointTransactionVO.setTransactionNo(rs.getInt("transactionNo"));
				pointTransactionVO.setMemId(rs.getInt("memId"));
				pointTransactionVO.setStartPoint(rs.getInt("startPoint"));
				pointTransactionVO.setStartCash(rs.getInt("startCash"));
				pointTransactionVO.setTransactionDescription(rs.getString("transactionDescription"));
				pointTransactionVO.setChangeCash(rs.getInt("changeCash"));
				pointTransactionVO.setTransactionTime(rs.getTimestamp("transactionTime"));
				pointTransactionVO.setChangePoint(rs.getInt("changePoint"));
				list.add(pointTransactionVO); // Store the row in the list
			}

			// Handle any driver errors
		}  catch (SQLException se) {
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

	@Override
	public void insert(PointTransactionVO pointTransactionVO) {
		// TODO Auto-generated method stub
		
	}

	}


