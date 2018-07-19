package com.purchasingOrder.model;


import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.member.model.MemberVO;
import com.pointTransaction.model.*;


public class PurchasingOrderDAO implements PurchasingOrderDAO_interface {


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
			"INSERT INTO PurchasingOrder (orderNo,orderReq,orderStatus,orderDescription,orderGoods,tripNo,memIdReq,"
			+ "deliveryPlaces,orderSetUp,orderSetUpTime,quotedPrice,memIdRes,deliveryTime,transactionCode,"
			+ "transactionSuccess,transFailureReqReason,transFailureComplaintReq,transFailComplaintReqReason,"
			+ "transFailComplaintReqTime,transFailResReason,transFailComplaintRes,transFailComplaintResReason,"
			+ "transFailComplaintResTime,reviewed,process,wrong,vlationReq,vlationRes,processMethod,evaluateReq,evaluateRes"
			+ ",CommentReq,CommentRes) VALUES (to_char(sysdate,'yyyymmdd')||'_'||LPAD(PurchasingOrder_seq.NEXTVAL,6,'0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
			+ " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT orderNo,orderReq,orderStatus,orderDescription,orderGoods,tripNo,memIdReq,"
			+ "deliveryPlaces,orderSetUp,orderSetUpTime,quotedPrice,memIdRes,deliveryTime,transactionCode,"
			+ "transactionSuccess,transFailureReqReason,transFailureComplaintReq,transFailComplaintReqReason,"
			+ "transFailComplaintReqTime,transFailResReason,transFailComplaintRes,transFailComplaintResReason,"
			+ "transFailComplaintResTime,reviewed,process,wrong,vlationReq,vlationRes,processMethod,evaluateReq,evaluateRes"
			+ ",CommentReq,CommentRes FROM PurchasingOrder order by orderNo";
	private static final String GET_ONE_STMT =
			"SELECT orderNo,orderReq,orderStatus,orderDescription,orderGoods,tripNo,memIdReq,"
			+ "deliveryPlaces,orderSetUp,orderSetUpTime,quotedPrice,memIdRes,deliveryTime,transactionCode,"
			+ "transactionSuccess,transFailureReqReason,transFailureComplaintReq,transFailComplaintReqReason,"
			+ "transFailComplaintReqTime,transFailResReason,transFailComplaintRes,transFailComplaintResReason,"
			+ "transFailComplaintResTime,reviewed,process,wrong,vlationReq,vlationRes,processMethod,evaluateReq,evaluateRes"
			+ ",CommentReq,CommentRes FROM PurchasingOrder where orderNo = ?";
	private static final String DELETE =
			"DELETE FROM PurchasingOrder where orderNo = ?";
	private static final String UPDATE = 
			"UPDATE PurchasingOrder set orderReq=?,orderStatus=?,orderDescription=?,orderGoods=?,tripNo=?,memIdReq=?,"
			+ "deliveryPlaces=?,orderSetUp=?,orderSetUpTime=?,quotedPrice=?,memIdRes=?,deliveryTime=?,transactionCode=?,"
			+ "transactionSuccess=?,transFailureReqReason=?,transFailureComplaintReq=?,transFailComplaintReqReason=?,"
			+ "transFailComplaintReqTime=?,transFailResReason=?,transFailComplaintRes=?,transFailComplaintResReason=?,"
			+ "transFailComplaintResTime=?,reviewed=?,process=?,wrong=?,vlationReq=?,vlationRes=?,processMethod=?,evaluateReq=?,evaluateRes=?"
			+ ",CommentReq=?,CommentRes=? where orderNo = ?";
	
	private static final String GET_ONE_ALL =
			"SELECT orderNo,orderReq,orderStatus,orderDescription,orderGoods,tripNo,memIdReq,"
			+ "deliveryPlaces,orderSetUp,orderSetUpTime,quotedPrice,memIdRes,deliveryTime,transactionCode,"
			+ "transactionSuccess,transFailureReqReason,transFailureComplaintReq,transFailComplaintReqReason,"
			+ "transFailComplaintReqTime,transFailResReason,transFailComplaintRes,transFailComplaintResReason,"
			+ "transFailComplaintResTime,reviewed,process,wrong,vlationReq,vlationRes,processMethod,evaluateReq,evaluateRes"
			+ ",CommentReq,CommentRes FROM PurchasingOrder where memIdReq = ? and orderSetUp = 0 order by orderNo";
	private static final String GET_ONE_ALL_RES =
			"SELECT orderNo,orderReq,orderStatus,orderDescription,orderGoods,tripNo,memIdReq,"
			+ "deliveryPlaces,orderSetUp,orderSetUpTime,quotedPrice,memIdRes,deliveryTime,transactionCode,"
			+ "transactionSuccess,transFailureReqReason,transFailureComplaintReq,transFailComplaintReqReason,"
			+ "transFailComplaintReqTime,transFailResReason,transFailComplaintRes,transFailComplaintResReason,"
			+ "transFailComplaintResTime,reviewed,process,wrong,vlationReq,vlationRes,processMethod,evaluateReq,evaluateRes"
			+ ",CommentReq,CommentRes FROM PurchasingOrder where memIdReq = ? and orderSetUp = 0 and orderStatus = 1 order by orderNo";
	private static final String GET_ONE_ALL_REQ = 
			"SELECT orderNo,orderReq,orderStatus,orderDescription,orderGoods,tripNo,memIdReq,"
			+ "deliveryPlaces,orderSetUp,orderSetUpTime,quotedPrice,memIdRes,deliveryTime,transactionCode,"
			+ "transactionSuccess,transFailureReqReason,transFailureComplaintReq,transFailComplaintReqReason,"
			+ "transFailComplaintReqTime,transFailResReason,transFailComplaintRes,transFailComplaintResReason,"
			+ "transFailComplaintResTime,reviewed,process,wrong,vlationReq,vlationRes,processMethod,evaluateReq,evaluateRes"
			+ ",CommentReq,CommentRes FROM PurchasingOrder where memIdReq != ? and orderSetUp = 0 and orderStatus = 0 order by orderNo";
	
			
	@Override
	public void insert(PurchasingOrderVO purchasingOrderVO) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			
			pstmt.setString(1, purchasingOrderVO.getOrderReq());
			pstmt.setString(2, purchasingOrderVO.getOrderStatus());
			pstmt.setString(3, purchasingOrderVO.getOrderDescription());
			pstmt.setBytes(4, purchasingOrderVO.getOrderGoods());
			pstmt.setInt(5, purchasingOrderVO.getTripNo());
			pstmt.setInt(6, purchasingOrderVO.getMemIdReq());
			pstmt.setString(7, purchasingOrderVO.getDeliveryPlaces());
			pstmt.setBoolean(8, purchasingOrderVO.getOrderSetUp());
			pstmt.setTimestamp(9, purchasingOrderVO.getOrderSetUpTime());
			pstmt.setInt(10, purchasingOrderVO.getQuotedPrice());
			pstmt.setInt(11, purchasingOrderVO.getMemIdRes());
			pstmt.setTimestamp(12, purchasingOrderVO.getDeliveryTime());
			pstmt.setBytes(13, purchasingOrderVO.getTransactionCode());
			pstmt.setBoolean(14, purchasingOrderVO.getTransactionSuccess());
			pstmt.setString(15, purchasingOrderVO.getTransFailReqReason());
			pstmt.setBoolean(16, purchasingOrderVO.getTransFailComplaintReq());
			pstmt.setString(17, purchasingOrderVO.getTransFailComplaintReqReason());
			pstmt.setTimestamp(18, purchasingOrderVO.getTransFailComplaintReqTime());
			pstmt.setString(19, purchasingOrderVO.getTransFailResReason());
			pstmt.setBoolean(20, purchasingOrderVO.getTransFailComplaintRes());
			pstmt.setString(21, purchasingOrderVO.getTransFailComplaintResReason());
			pstmt.setTimestamp(22, purchasingOrderVO.getTransFailComplaintResTime());
			pstmt.setBoolean(23, purchasingOrderVO.getReviewed());
			pstmt.setBoolean(24, purchasingOrderVO.getProcess());
			pstmt.setInt(25, purchasingOrderVO.getWrong());
			pstmt.setBoolean(26, purchasingOrderVO.getVlationReq());
			pstmt.setBoolean(27, purchasingOrderVO.getVlationRes());
			pstmt.setString(28, purchasingOrderVO.getProcessMethod());
			pstmt.setInt(29, purchasingOrderVO.getEvaluateReq());
			pstmt.setInt(30, purchasingOrderVO.getEvaluateRes());
			pstmt.setString(31, purchasingOrderVO.getCommentReq());
			pstmt.setString(32, purchasingOrderVO.getCommentRes());
			
			
			
			pstmt.executeUpdate();
			
		}catch (SQLException se) {
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
	public void update(PurchasingOrderVO purchasingOrderVO) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			
			pstmt.setString(1, purchasingOrderVO.getOrderReq());
			pstmt.setString(2, purchasingOrderVO.getOrderStatus());
			pstmt.setString(3, purchasingOrderVO.getOrderDescription());
			pstmt.setBytes(4, purchasingOrderVO.getOrderGoods());
			pstmt.setInt(5, purchasingOrderVO.getTripNo());
			pstmt.setInt(6, purchasingOrderVO.getMemIdReq());
			pstmt.setString(7, purchasingOrderVO.getDeliveryPlaces());
			pstmt.setBoolean(8, purchasingOrderVO.getOrderSetUp());
			pstmt.setTimestamp(9, purchasingOrderVO.getOrderSetUpTime());
			pstmt.setInt(10, purchasingOrderVO.getQuotedPrice());
			pstmt.setInt(11, purchasingOrderVO.getMemIdRes());
			pstmt.setTimestamp(12, purchasingOrderVO.getDeliveryTime());
			pstmt.setBytes(13, purchasingOrderVO.getTransactionCode());
			pstmt.setBoolean(14, purchasingOrderVO.getTransactionSuccess());
			pstmt.setString(15, purchasingOrderVO.getTransFailReqReason());
			pstmt.setBoolean(16, purchasingOrderVO.getTransFailComplaintReq());
			pstmt.setString(17, purchasingOrderVO.getTransFailComplaintReqReason());
			pstmt.setTimestamp(18, purchasingOrderVO.getTransFailComplaintReqTime());
			pstmt.setString(19, purchasingOrderVO.getTransFailResReason());
			pstmt.setBoolean(20, purchasingOrderVO.getTransFailComplaintRes());
			pstmt.setString(21, purchasingOrderVO.getTransFailComplaintResReason());
			pstmt.setTimestamp(22, purchasingOrderVO.getTransFailComplaintResTime());
			pstmt.setBoolean(23, purchasingOrderVO.getReviewed());
			pstmt.setBoolean(24, purchasingOrderVO.getProcess());
			pstmt.setInt(25, purchasingOrderVO.getWrong());
			pstmt.setBoolean(26, purchasingOrderVO.getVlationReq());
			pstmt.setBoolean(27, purchasingOrderVO.getVlationRes());
			pstmt.setString(28, purchasingOrderVO.getProcessMethod());
			pstmt.setInt(29, purchasingOrderVO.getEvaluateReq());
			pstmt.setInt(30, purchasingOrderVO.getEvaluateRes());
			pstmt.setString(31, purchasingOrderVO.getCommentReq());
			pstmt.setString(32, purchasingOrderVO.getCommentRes());
			pstmt.setString(33, purchasingOrderVO.getOrderNo());
			
			
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
	public void delete(String orderNo) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, orderNo);

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
	public PurchasingOrderVO findByPrimaryKey(String orderNo) {
		// TODO Auto-generated method stub
		
		PurchasingOrderVO purchasingOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, orderNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// PointTransactionVO 也稱為 Domain objects
				purchasingOrderVO = new PurchasingOrderVO();
				
				purchasingOrderVO.setOrderNo(rs.getString("orderNo"));
				purchasingOrderVO.setOrderReq(rs.getString("orderReq"));
				purchasingOrderVO.setOrderStatus(rs.getString("orderStatus"));
				purchasingOrderVO.setOrderDescription(rs.getString("orderDescription"));
				purchasingOrderVO.setOrderGoods(rs.getBytes("orderGoods"));
				purchasingOrderVO.setTripNo(rs.getInt("tripNo"));
				purchasingOrderVO.setMemIdReq(rs.getInt("memIdReq"));
				purchasingOrderVO.setDeliveryPlaces(rs.getString("deliveryPlaces"));
				purchasingOrderVO.setOrderSetUp(rs.getBoolean("orderSetUp"));
				purchasingOrderVO.setOrderSetUpTime(rs.getTimestamp("orderSetUpTime"));
				purchasingOrderVO.setQuotedPrice(rs.getInt("quotedPrice"));
				purchasingOrderVO.setMemIdRes(rs.getInt("memIdRes"));
				purchasingOrderVO.setDeliveryTime(rs.getTimestamp("deliveryTime"));
				purchasingOrderVO.setTransactionCode(rs.getBytes("transactionCode"));
				purchasingOrderVO.setTransactionSuccess(rs.getBoolean("transactionSuccess"));
				purchasingOrderVO.setTransFailReqReason(rs.getString("transFailureReqReason"));
				purchasingOrderVO.setTransFailComplaintReq(rs.getBoolean("transFailureComplaintReq"));
				purchasingOrderVO.setTransFailComplaintReqReason(rs.getString("transFailComplaintReqReason"));
				purchasingOrderVO.setTransFailComplaintReqTime(rs.getTimestamp("transFailComplaintReqTime"));
				purchasingOrderVO.setTransFailResReason(rs.getString("transFailResReason"));
				purchasingOrderVO.setTransFailComplaintRes(rs.getBoolean("transFailComplaintRes"));
				purchasingOrderVO.setTransFailComplaintResReason(rs.getString("transFailComplaintResReason"));
				purchasingOrderVO.setTransFailComplaintResTime(rs.getTimestamp("transFailComplaintResTime"));
				purchasingOrderVO.setReviewed(rs.getBoolean("reviewed"));
				purchasingOrderVO.setProcess(rs.getBoolean("process"));
				purchasingOrderVO.setWrong(rs.getInt("wrong"));
				purchasingOrderVO.setVlationReq(rs.getBoolean("vlationReq"));
				purchasingOrderVO.setVlationRes(rs.getBoolean("vlationRes"));
				purchasingOrderVO.setProcessMethod(rs.getString("processMethod"));
				purchasingOrderVO.setEvaluateReq(rs.getInt("evaluateReq"));
				purchasingOrderVO.setEvaluateRes(rs.getInt("evaluateRes"));
				purchasingOrderVO.setCommentReq(rs.getString("commentReq"));
				purchasingOrderVO.setCommentRes(rs.getString("commentRes"));	
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
		return purchasingOrderVO;
	}

	@Override
	public List<PurchasingOrderVO> getAll() {
		// TODO Auto-generated method stub
		List<PurchasingOrderVO> list = new ArrayList<PurchasingOrderVO>();
		PurchasingOrderVO purchasingOrderVO = null;
			
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// PointTransactionVO 也稱為 Domain objects
				purchasingOrderVO = new PurchasingOrderVO();
				purchasingOrderVO.setOrderNo(rs.getString("orderNo"));
				purchasingOrderVO.setOrderReq(rs.getString("orderReq"));
				purchasingOrderVO.setOrderStatus(rs.getString("orderStatus"));
				purchasingOrderVO.setOrderDescription(rs.getString("orderDescription"));
				purchasingOrderVO.setOrderGoods(rs.getBytes("orderGoods"));
				purchasingOrderVO.setTripNo(rs.getInt("tripNo"));
				purchasingOrderVO.setMemIdReq(rs.getInt("memIdReq"));
				purchasingOrderVO.setDeliveryPlaces(rs.getString("deliveryPlaces"));
				purchasingOrderVO.setOrderSetUp(rs.getBoolean("orderSetUp"));
				purchasingOrderVO.setOrderSetUpTime(rs.getTimestamp("orderSetUpTime"));
				purchasingOrderVO.setQuotedPrice(rs.getInt("quotedPrice"));
				purchasingOrderVO.setMemIdRes(rs.getInt("memIdRes"));
				purchasingOrderVO.setDeliveryTime(rs.getTimestamp("deliveryTime"));
				purchasingOrderVO.setTransactionCode(rs.getBytes("transactionCode"));
				purchasingOrderVO.setTransactionSuccess(rs.getBoolean("transactionSuccess"));
				purchasingOrderVO.setTransFailReqReason(rs.getString("transFailureReqReason"));
				purchasingOrderVO.setTransFailComplaintReq(rs.getBoolean("transFailureComplaintReq"));
				purchasingOrderVO.setTransFailComplaintReqReason(rs.getString("transFailComplaintReqReason"));
				purchasingOrderVO.setTransFailComplaintReqTime(rs.getTimestamp("transFailComplaintReqTime"));
				purchasingOrderVO.setTransFailResReason(rs.getString("transFailResReason"));
				purchasingOrderVO.setTransFailComplaintRes(rs.getBoolean("transFailComplaintRes"));
				purchasingOrderVO.setTransFailComplaintResReason(rs.getString("transFailComplaintResReason"));
				purchasingOrderVO.setTransFailComplaintResTime(rs.getTimestamp("transFailComplaintResTime"));
				purchasingOrderVO.setReviewed(rs.getBoolean("reviewed"));
				purchasingOrderVO.setProcess(rs.getBoolean("process"));
				purchasingOrderVO.setWrong(rs.getInt("wrong"));
				purchasingOrderVO.setVlationReq(rs.getBoolean("vlationReq"));
				purchasingOrderVO.setVlationRes(rs.getBoolean("vlationRes"));
				purchasingOrderVO.setProcessMethod(rs.getString("processMethod"));
				purchasingOrderVO.setEvaluateReq(rs.getInt("evaluateReq"));
				purchasingOrderVO.setEvaluateRes(rs.getInt("evaluateRes"));
				purchasingOrderVO.setCommentReq(rs.getString("commentReq"));
				purchasingOrderVO.setCommentRes(rs.getString("commentRes"));			
							
				list.add(purchasingOrderVO); // Store the row in the list
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

	@Override
	public List<PurchasingOrderVO> getOneForAll(Integer memIdReq) {
		// TODO Auto-generated method stub
		List<PurchasingOrderVO> list = new ArrayList<PurchasingOrderVO>();
		PurchasingOrderVO purchasingOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(GET_ONE_ALL);
		
			pstmt.setInt(1,memIdReq);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// PointTransactionVO 也稱為 Domain objects
				purchasingOrderVO = new PurchasingOrderVO();
				purchasingOrderVO.setOrderNo(rs.getString("orderNo"));
				purchasingOrderVO.setOrderReq(rs.getString("orderReq"));
				purchasingOrderVO.setOrderStatus(rs.getString("orderStatus"));
				purchasingOrderVO.setOrderDescription(rs.getString("orderDescription"));
				purchasingOrderVO.setOrderGoods(rs.getBytes("orderGoods"));
				purchasingOrderVO.setTripNo(rs.getInt("tripNo"));
				purchasingOrderVO.setMemIdReq(rs.getInt("memIdReq"));
				purchasingOrderVO.setDeliveryPlaces(rs.getString("deliveryPlaces"));
				purchasingOrderVO.setOrderSetUp(rs.getBoolean("orderSetUp"));
				purchasingOrderVO.setOrderSetUpTime(rs.getTimestamp("orderSetUpTime"));
				purchasingOrderVO.setQuotedPrice(rs.getInt("quotedPrice"));
				purchasingOrderVO.setMemIdRes(rs.getInt("memIdRes"));
				purchasingOrderVO.setDeliveryTime(rs.getTimestamp("deliveryTime"));
				purchasingOrderVO.setTransactionCode(rs.getBytes("transactionCode"));
				purchasingOrderVO.setTransactionSuccess(rs.getBoolean("transactionSuccess"));
				purchasingOrderVO.setTransFailReqReason(rs.getString("transFailureReqReason"));
				purchasingOrderVO.setTransFailComplaintReq(rs.getBoolean("transFailureComplaintReq"));
				purchasingOrderVO.setTransFailComplaintReqReason(rs.getString("transFailComplaintReqReason"));
				purchasingOrderVO.setTransFailComplaintReqTime(rs.getTimestamp("transFailComplaintReqTime"));
				purchasingOrderVO.setTransFailResReason(rs.getString("transFailResReason"));
				purchasingOrderVO.setTransFailComplaintRes(rs.getBoolean("transFailComplaintRes"));
				purchasingOrderVO.setTransFailComplaintResReason(rs.getString("transFailComplaintResReason"));
				purchasingOrderVO.setTransFailComplaintResTime(rs.getTimestamp("transFailComplaintResTime"));
				purchasingOrderVO.setReviewed(rs.getBoolean("reviewed"));
				purchasingOrderVO.setProcess(rs.getBoolean("process"));
				purchasingOrderVO.setWrong(rs.getInt("wrong"));
				purchasingOrderVO.setVlationReq(rs.getBoolean("vlationReq"));
				purchasingOrderVO.setVlationRes(rs.getBoolean("vlationRes"));
				purchasingOrderVO.setProcessMethod(rs.getString("processMethod"));
				purchasingOrderVO.setEvaluateReq(rs.getInt("evaluateReq"));
				purchasingOrderVO.setEvaluateRes(rs.getInt("evaluateRes"));
				purchasingOrderVO.setCommentReq(rs.getString("commentReq"));
				purchasingOrderVO.setCommentRes(rs.getString("commentRes"));			
							
				list.add(purchasingOrderVO); // Store the row in the list
				
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
	@Override
	public List<PurchasingOrderVO> getOneForAllRes(Integer memIdReq) {
		// TODO Auto-generated method stub
		List<PurchasingOrderVO> list2 = new ArrayList<PurchasingOrderVO>();
		PurchasingOrderVO purchasingOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(GET_ONE_ALL_RES);
		
			pstmt.setInt(1,memIdReq);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// PointTransactionVO 也稱為 Domain objects
				purchasingOrderVO = new PurchasingOrderVO();
				purchasingOrderVO.setOrderNo(rs.getString("orderNo"));
				purchasingOrderVO.setOrderReq(rs.getString("orderReq"));
				purchasingOrderVO.setOrderStatus(rs.getString("orderStatus"));
				purchasingOrderVO.setOrderDescription(rs.getString("orderDescription"));
				purchasingOrderVO.setOrderGoods(rs.getBytes("orderGoods"));
				purchasingOrderVO.setTripNo(rs.getInt("tripNo"));
				purchasingOrderVO.setMemIdReq(rs.getInt("memIdReq"));
				purchasingOrderVO.setDeliveryPlaces(rs.getString("deliveryPlaces"));
				purchasingOrderVO.setOrderSetUp(rs.getBoolean("orderSetUp"));
				purchasingOrderVO.setOrderSetUpTime(rs.getTimestamp("orderSetUpTime"));
				purchasingOrderVO.setQuotedPrice(rs.getInt("quotedPrice"));
				purchasingOrderVO.setMemIdRes(rs.getInt("memIdRes"));
				purchasingOrderVO.setDeliveryTime(rs.getTimestamp("deliveryTime"));
				purchasingOrderVO.setTransactionCode(rs.getBytes("transactionCode"));
				purchasingOrderVO.setTransactionSuccess(rs.getBoolean("transactionSuccess"));
				purchasingOrderVO.setTransFailReqReason(rs.getString("transFailureReqReason"));
				purchasingOrderVO.setTransFailComplaintReq(rs.getBoolean("transFailureComplaintReq"));
				purchasingOrderVO.setTransFailComplaintReqReason(rs.getString("transFailComplaintReqReason"));
				purchasingOrderVO.setTransFailComplaintReqTime(rs.getTimestamp("transFailComplaintReqTime"));
				purchasingOrderVO.setTransFailResReason(rs.getString("transFailResReason"));
				purchasingOrderVO.setTransFailComplaintRes(rs.getBoolean("transFailComplaintRes"));
				purchasingOrderVO.setTransFailComplaintResReason(rs.getString("transFailComplaintResReason"));
				purchasingOrderVO.setTransFailComplaintResTime(rs.getTimestamp("transFailComplaintResTime"));
				purchasingOrderVO.setReviewed(rs.getBoolean("reviewed"));
				purchasingOrderVO.setProcess(rs.getBoolean("process"));
				purchasingOrderVO.setWrong(rs.getInt("wrong"));
				purchasingOrderVO.setVlationReq(rs.getBoolean("vlationReq"));
				purchasingOrderVO.setVlationRes(rs.getBoolean("vlationRes"));
				purchasingOrderVO.setProcessMethod(rs.getString("processMethod"));
				purchasingOrderVO.setEvaluateReq(rs.getInt("evaluateReq"));
				purchasingOrderVO.setEvaluateRes(rs.getInt("evaluateRes"));
				purchasingOrderVO.setCommentReq(rs.getString("commentReq"));
				purchasingOrderVO.setCommentRes(rs.getString("commentRes"));			
							
				list2.add(purchasingOrderVO); // Store the row in the list
				
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
		
		return list2;
		
	}

	@Override
	public List<PurchasingOrderVO> getOneForAllReq(Integer memIdReq) {
		// TODO Auto-generated method stub
		
		List<PurchasingOrderVO> list = new ArrayList<PurchasingOrderVO>();
		PurchasingOrderVO purchasingOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(GET_ONE_ALL_REQ);
		
			pstmt.setInt(1,memIdReq);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// PointTransactionVO 也稱為 Domain objects
				purchasingOrderVO = new PurchasingOrderVO();
				purchasingOrderVO.setOrderNo(rs.getString("orderNo"));
				purchasingOrderVO.setOrderReq(rs.getString("orderReq"));
				purchasingOrderVO.setOrderStatus(rs.getString("orderStatus"));
				purchasingOrderVO.setOrderDescription(rs.getString("orderDescription"));
				purchasingOrderVO.setOrderGoods(rs.getBytes("orderGoods"));
				purchasingOrderVO.setTripNo(rs.getInt("tripNo"));
				purchasingOrderVO.setMemIdReq(rs.getInt("memIdReq"));
				purchasingOrderVO.setDeliveryPlaces(rs.getString("deliveryPlaces"));
				purchasingOrderVO.setOrderSetUp(rs.getBoolean("orderSetUp"));
				purchasingOrderVO.setOrderSetUpTime(rs.getTimestamp("orderSetUpTime"));
				purchasingOrderVO.setQuotedPrice(rs.getInt("quotedPrice"));
				purchasingOrderVO.setMemIdRes(rs.getInt("memIdRes"));
				purchasingOrderVO.setDeliveryTime(rs.getTimestamp("deliveryTime"));
				purchasingOrderVO.setTransactionCode(rs.getBytes("transactionCode"));
				purchasingOrderVO.setTransactionSuccess(rs.getBoolean("transactionSuccess"));
				purchasingOrderVO.setTransFailReqReason(rs.getString("transFailureReqReason"));
				purchasingOrderVO.setTransFailComplaintReq(rs.getBoolean("transFailureComplaintReq"));
				purchasingOrderVO.setTransFailComplaintReqReason(rs.getString("transFailComplaintReqReason"));
				purchasingOrderVO.setTransFailComplaintReqTime(rs.getTimestamp("transFailComplaintReqTime"));
				purchasingOrderVO.setTransFailResReason(rs.getString("transFailResReason"));
				purchasingOrderVO.setTransFailComplaintRes(rs.getBoolean("transFailComplaintRes"));
				purchasingOrderVO.setTransFailComplaintResReason(rs.getString("transFailComplaintResReason"));
				purchasingOrderVO.setTransFailComplaintResTime(rs.getTimestamp("transFailComplaintResTime"));
				purchasingOrderVO.setReviewed(rs.getBoolean("reviewed"));
				purchasingOrderVO.setProcess(rs.getBoolean("process"));
				purchasingOrderVO.setWrong(rs.getInt("wrong"));
				purchasingOrderVO.setVlationReq(rs.getBoolean("vlationReq"));
				purchasingOrderVO.setVlationRes(rs.getBoolean("vlationRes"));
				purchasingOrderVO.setProcessMethod(rs.getString("processMethod"));
				purchasingOrderVO.setEvaluateReq(rs.getInt("evaluateReq"));
				purchasingOrderVO.setEvaluateRes(rs.getInt("evaluateRes"));
				purchasingOrderVO.setCommentReq(rs.getString("commentReq"));
				purchasingOrderVO.setCommentRes(rs.getString("commentRes"));			
							
				list.add(purchasingOrderVO); // Store the row in the list
				
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
	

