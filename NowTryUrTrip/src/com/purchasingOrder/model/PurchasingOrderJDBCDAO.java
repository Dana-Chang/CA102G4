package com.purchasingOrder.model;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

import javax.servlet.http.Part;

import com.pointTransaction.model.*;


import sun.misc.BASE64Encoder;

public class PurchasingOrderJDBCDAO implements PurchasingOrderDAO_interface {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "aa107";
	String passwd = "aa107";

	
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
	private static final String GET_ONE_ALL_RES =
			"SELECT orderNo,orderReq,orderStatus,orderDescription,orderGoods,tripNo,memIdReq,"
			+ "deliveryPlaces,orderSetUp,orderSetUpTime,quotedPrice,memIdRes,deliveryTime,transactionCode,"
			+ "transactionSuccess,transFailureReqReason,transFailureComplaintReq,transFailComplaintReqReason,"
			+ "transFailComplaintReqTime,transFailResReason,transFailComplaintRes,transFailComplaintResReason,"
			+ "transFailComplaintResTime,reviewed,process,wrong,vlationReq,vlationRes,processMethod,evaluateReq,evaluateRes"
			+ ",CommentReq,CommentRes FROM PurchasingOrder where memIdReq = ? and orderSetUp = 0 and orderReq<> NULL order by orderNo";
	
			
	@Override
	public void insert(PurchasingOrderVO purchasingOrderVO) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
		
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			
		}catch (ClassNotFoundException e) {
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
	public void update(PurchasingOrderVO purchasingOrderVO) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void delete(String orderNo) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, orderNo);

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
	public PurchasingOrderVO findByPrimaryKey(String orderNo) {
		// TODO Auto-generated method stub
		
		PurchasingOrderVO purchasingOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	@Override
	public List<PurchasingOrderVO> getOneForAll(Integer memIdReq) {
		// TODO Auto-generated method stub
		List<PurchasingOrderVO> list = new ArrayList<PurchasingOrderVO>();
		PurchasingOrderVO purchasingOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	@Override
	public List<PurchasingOrderVO> getOneForAllRes(Integer memIdReq) {
		// TODO Auto-generated method stub
		List<PurchasingOrderVO> list = new ArrayList<PurchasingOrderVO>();
		PurchasingOrderVO purchasingOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			
			
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
	
	
	
	
	
	
	
	// 使用byte[]方式
		public static byte[] getPictureByteArray(String path) throws IOException {
								
			
			InputStream in = new FileInputStream(path);
			byte[] buffer = new byte[in.available()];
			in.read(buffer);
			in.close();
			return buffer;
		}
	
	
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		byte[] QR = getPictureByteArray("items/googleQR.png");
		byte[] kantai = getPictureByteArray("items/kantai.jpg");

		String QRbase64 = new BASE64Encoder().encode(QR);
		
		
		PurchasingOrderJDBCDAO dao = new PurchasingOrderJDBCDAO();

		// 新增
		PurchasingOrderVO purchasingOrderVO1 = new PurchasingOrderVO();
		
		purchasingOrderVO1.setOrderReq("");
		purchasingOrderVO1.setOrderStatus("0");
		purchasingOrderVO1.setOrderDescription("滿滿的香蕉");
		purchasingOrderVO1.setOrderGoods(null);
		purchasingOrderVO1.setTripNo(20170424);
		purchasingOrderVO1.setMemIdReq(7001);
		purchasingOrderVO1.setDeliveryPlaces("中央大學4");
		purchasingOrderVO1.setOrderSetUp(false);
		purchasingOrderVO1.setOrderSetUpTime(java.sql.Timestamp.valueOf("2017-04-30 18:13:55"));
		purchasingOrderVO1.setQuotedPrice(0);
		purchasingOrderVO1.setMemIdRes(0);
		purchasingOrderVO1.setDeliveryTime(null);
		purchasingOrderVO1.setTransactionCode(QR);
		purchasingOrderVO1.setTransactionSuccess(false);
		purchasingOrderVO1.setTransFailReqReason("");
		purchasingOrderVO1.setTransFailComplaintReq(false);
		purchasingOrderVO1.setTransFailComplaintReqReason("");
		purchasingOrderVO1.setTransFailComplaintReqTime(null);
		purchasingOrderVO1.setTransFailResReason("");
		purchasingOrderVO1.setTransFailComplaintRes(false);
		purchasingOrderVO1.setTransFailComplaintResReason("");
		purchasingOrderVO1.setTransFailComplaintResTime(null);
		purchasingOrderVO1.setReviewed(false);
		purchasingOrderVO1.setProcess(false);
		purchasingOrderVO1.setWrong(4);
		purchasingOrderVO1.setVlationReq(false);
		purchasingOrderVO1.setVlationRes(false);
		purchasingOrderVO1.setProcessMethod("");
		purchasingOrderVO1.setEvaluateReq(0);
		purchasingOrderVO1.setEvaluateRes(0);
		purchasingOrderVO1.setCommentReq("");
		purchasingOrderVO1.setCommentRes("");
		
		dao.insert(purchasingOrderVO1);

		
		//修改
		
		PurchasingOrderVO purchasingOrderVO2 = new PurchasingOrderVO();
		
		purchasingOrderVO2.setOrderReq("");
		purchasingOrderVO2.setOrderStatus("0");
		purchasingOrderVO2.setOrderDescription("5包豆干");
		purchasingOrderVO2.setOrderGoods(kantai);
		purchasingOrderVO2.setTripNo(20170424);
		purchasingOrderVO2.setMemIdReq(7001);
		purchasingOrderVO2.setDeliveryPlaces("中央大學");
		purchasingOrderVO2.setOrderSetUp(true);
		purchasingOrderVO2.setOrderSetUpTime(java.sql.Timestamp.valueOf("2017-04-28 18:13:55"));
		purchasingOrderVO2.setQuotedPrice(600);
		purchasingOrderVO2.setMemIdRes(7005);
		purchasingOrderVO2.setDeliveryTime(java.sql.Timestamp.valueOf("2017-04-28 20:13:55"));
		purchasingOrderVO2.setTransactionCode(QR);
		purchasingOrderVO2.setTransactionSuccess(true);
		purchasingOrderVO2.setTransFailReqReason("");
		purchasingOrderVO2.setTransFailComplaintReq(false);
		purchasingOrderVO2.setTransFailComplaintReqReason("");
		purchasingOrderVO2.setTransFailComplaintReqTime(null);
		purchasingOrderVO2.setTransFailResReason("");
		purchasingOrderVO2.setTransFailComplaintRes(false);
		purchasingOrderVO2.setTransFailComplaintResReason("");
		purchasingOrderVO2.setTransFailComplaintResTime(null);
		purchasingOrderVO2.setReviewed(false);
		purchasingOrderVO2.setProcess(false);
		purchasingOrderVO2.setWrong(3);
		purchasingOrderVO2.setVlationReq(false);
		purchasingOrderVO2.setVlationRes(false);
		purchasingOrderVO2.setProcessMethod("");
		purchasingOrderVO2.setEvaluateReq(5);
		purchasingOrderVO2.setEvaluateRes(5);
		purchasingOrderVO2.setCommentReq("便宜快速");
		purchasingOrderVO2.setCommentRes("爽快付款");
		purchasingOrderVO2.setOrderNo("20170425_000001");
		dao.update(purchasingOrderVO2);
		
		
		
		//刪除
		dao.delete("20170425_000004");
		
		
		//查詢單一
		PurchasingOrderVO purchasingOrderVO3 = dao.findByPrimaryKey("20170425_000001");
		System.out.print(purchasingOrderVO3.getOrderNo() + ",");
		System.out.print(purchasingOrderVO3.getOrderReq() + ",");
		System.out.print(purchasingOrderVO3.getOrderStatus() + ",");
		System.out.print(purchasingOrderVO3.getOrderDescription() + ",");
		System.out.print(purchasingOrderVO3.getOrderGoods() + ",");
		System.out.print(purchasingOrderVO3.getTripNo() + ",");
		System.out.print(purchasingOrderVO3.getMemIdReq() + ",");
		System.out.print(purchasingOrderVO3.getDeliveryPlaces() + ",");
		System.out.print(purchasingOrderVO3.getOrderSetUp() + ",");
		System.out.print(purchasingOrderVO3.getOrderSetUpTime() + ",");
		System.out.print(purchasingOrderVO3.getQuotedPrice() + ",");
		System.out.print(purchasingOrderVO3.getMemIdRes() + ",");
		System.out.print(purchasingOrderVO3.getDeliveryTime() + ",");
		System.out.print(purchasingOrderVO3.getTransactionCode() + ",");
		System.out.print(purchasingOrderVO3.getTransactionSuccess() + ",");
		System.out.print(purchasingOrderVO3.getTransFailReqReason() + ",");
		System.out.print(purchasingOrderVO3.getTransFailComplaintReq() + ",");
		System.out.print(purchasingOrderVO3.getTransFailComplaintReqReason() + ",");
		System.out.print(purchasingOrderVO3.getTransFailComplaintReqTime() + ",");
		System.out.print(purchasingOrderVO3.getTransFailResReason() + ",");
		System.out.print(purchasingOrderVO3.getTransFailComplaintRes() + ",");
		System.out.print(purchasingOrderVO3.getTransFailComplaintResReason() + ",");
		System.out.print(purchasingOrderVO3.getTransFailComplaintResTime() + ",");
		System.out.print(purchasingOrderVO3.getReviewed() + ",");
		System.out.print(purchasingOrderVO3.getProcess() + ",");
		System.out.print(purchasingOrderVO3.getWrong() + ",");
		System.out.print(purchasingOrderVO3.getVlationReq() + ",");
		System.out.print(purchasingOrderVO3.getVlationRes() + ",");
		System.out.print(purchasingOrderVO3.getProcessMethod() + ",");
		System.out.print(purchasingOrderVO3.getEvaluateReq() + ",");
		System.out.print(purchasingOrderVO3.getEvaluateRes() + ",");
		System.out.print(purchasingOrderVO3.getCommentReq() + ",");
		System.out.println(purchasingOrderVO3.getCommentRes());		
		
		System.out.println("----------------------------------------------------");
		
		//查詢All
		List<PurchasingOrderVO> list = dao.getAll();
		for (PurchasingOrderVO aPurchasingOrder : list) {
			System.out.print(aPurchasingOrder.getOrderNo() + ",");
			System.out.print(aPurchasingOrder.getOrderReq() + ",");
			System.out.print(aPurchasingOrder.getOrderStatus() + ",");
			System.out.print(aPurchasingOrder.getOrderDescription() + ",");
			System.out.print(aPurchasingOrder.getOrderGoods() + ",");
			System.out.print(aPurchasingOrder.getTripNo() + ",");
			System.out.print(aPurchasingOrder.getMemIdReq() + ",");
			System.out.print(aPurchasingOrder.getDeliveryPlaces() + ",");
			System.out.print(aPurchasingOrder.getOrderSetUp() + ",");
			System.out.print(aPurchasingOrder.getOrderSetUpTime() + ",");
			System.out.print(aPurchasingOrder.getQuotedPrice() + ",");
			System.out.print(aPurchasingOrder.getMemIdRes() + ",");
			System.out.print(aPurchasingOrder.getDeliveryTime() + ",");
			System.out.print(aPurchasingOrder.getTransactionCode() + ",");
			System.out.print(aPurchasingOrder.getTransactionSuccess() + ",");
			System.out.print(aPurchasingOrder.getTransFailReqReason() + ",");
			System.out.print(aPurchasingOrder.getTransFailComplaintReq() + ",");
			System.out.print(aPurchasingOrder.getTransFailComplaintReqReason() + ",");
			System.out.print(aPurchasingOrder.getTransFailComplaintReqTime() + ",");
			System.out.print(aPurchasingOrder.getTransFailResReason() + ",");
			System.out.print(aPurchasingOrder.getTransFailComplaintRes() + ",");
			System.out.print(aPurchasingOrder.getTransFailComplaintResReason() + ",");
			System.out.print(aPurchasingOrder.getTransFailComplaintResTime() + ",");
			System.out.print(aPurchasingOrder.getReviewed() + ",");
			System.out.print(aPurchasingOrder.getProcess() + ",");
			System.out.print(aPurchasingOrder.getWrong() + ",");
			System.out.print(aPurchasingOrder.getVlationReq() + ",");
			System.out.print(aPurchasingOrder.getVlationRes() + ",");
			System.out.print(aPurchasingOrder.getProcessMethod() + ",");
			System.out.print(aPurchasingOrder.getEvaluateReq() + ",");
			System.out.print(aPurchasingOrder.getEvaluateRes() + ",");
			System.out.print(aPurchasingOrder.getCommentReq() + ",");
			System.out.print(aPurchasingOrder.getCommentRes());		
			
			System.out.println();
		}
		
		
	}
	
}
