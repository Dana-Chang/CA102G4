package com.pointTransaction.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.pointTransaction.model.*;

public class PointTransactionServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		

				
if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("transactionNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入點數交易編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/buyAgentMgt/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer transactionNo = null;
				try {
					transactionNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("點數交易編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/buyAgentMgt/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				PointTransactionService pointTransactionSvc = new PointTransactionService();
				PointTransactionVO pointTransactionVO = pointTransactionSvc.getOnePointTransaction(transactionNo);
				if (pointTransactionVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/buyAgentMgt/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("pointTransactionVO", pointTransactionVO); // 資料庫取出的empVO物件,存入req
				String url = "/backend/buyAgentMgt/listOnePointTransaction.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/buyAgentMgt/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer transactionNo = new Integer(req.getParameter("transactionNo"));
				
				/***************************2.開始查詢資料****************************************/
				PointTransactionService pointTransactionSvc = new PointTransactionService();
				PointTransactionVO pointTransactionVO = pointTransactionSvc.getOnePointTransaction(transactionNo);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("pointTransactionVO", pointTransactionVO);         // 資料庫取出的empVO物件,存入req
				String url = "/backend/buyAgentMgt/update_pointTransaction_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/buyAgentMgt/listAllPointTransaction.jsp");
				failureView.forward(req, res);
			}
		}

if ("getOne".equals(action)) { // 來自listAllEmp.jsp的請求

	List<String> errorMsgs = new LinkedList<String>();
	// Store this set in the request scope, in case we need to
	// send the ErrorPage view.
	req.setAttribute("errorMsgs", errorMsgs);
	
	try {
		/***************************1.接收請求參數****************************************/
		Integer memId = new Integer(req.getParameter("memId"));
		
		/***************************2.開始查詢資料****************************************/
		MemberService memSvc = new MemberService();
		MemberVO memberVO = memSvc.getOneMember(memId);
						
		/***************************3.查詢完成,準備轉交(Send the Success view)************/
		req.setAttribute("memberVO", memberVO);
		String url = "/backend/buyAgentMgt/addPointTransaction2.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url);
		successView.forward(req, res);

		/***************************其他可能的錯誤處理**********************************/
	} catch (Exception e) {
		errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
		RequestDispatcher failureView = req
				.getRequestDispatcher("/backend/buyAgentMgt/addPointTransaction.jsp");
		failureView.forward(req, res);
	}
}




if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer transactionNo = new Integer(req.getParameter("transactionNo").trim());
				
				Integer memId = new Integer(req.getParameter("memId").trim());
				Integer startPoint = new Integer(req.getParameter("startPoint").trim());
				Integer startCash = new Integer(req.getParameter("startCash").trim());
				String transactionDescription = req.getParameter("transactionDescription").trim();
				Integer changeCash = new Integer(req.getParameter("changeCash").trim());
				Integer changePoint = new Integer(req.getParameter("changePoint").trim());
	
				PointTransactionVO pointTransactionVO = new PointTransactionVO();
				pointTransactionVO.setTransactionNo(transactionNo);
				pointTransactionVO.setMemId(memId);
				pointTransactionVO.setStartPoint(startPoint);
				pointTransactionVO.setStartCash(startCash);
				pointTransactionVO.setTransactionDescription(transactionDescription);
				pointTransactionVO.setChangePoint(changePoint);
				pointTransactionVO.setChangeCash(changeCash);

				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("pointTransactionVO", pointTransactionVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/buyAgentMgt/update_pointTransaction_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				PointTransactionService pointTransactionSvc = new PointTransactionService();
				pointTransactionSvc.updatePointTransaction(memId, startPoint, startCash, transactionDescription, changeCash,changePoint, transactionNo);
				pointTransactionVO = pointTransactionSvc.getOnePointTransaction(transactionNo);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("pointTransactionVO", pointTransactionVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/backend/buyAgentMgt/listOnePointTransaction.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/buyAgentMgt/update_pointTransaction_input.jsp");
				failureView.forward(req, res);
			}
		}
if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
	
	List<String> errorMsgs = new LinkedList<String>();
	// Store this set in the request scope, in case we need to
	// send the ErrorPage view.
	req.setAttribute("errorMsgs", errorMsgs);
	
	try {
		/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
		
		Integer memId = new Integer(req.getParameter("memId").trim());
		Integer startPoint = new Integer(req.getParameter("startPoint").trim());		
		Integer startCash = new Integer(req.getParameter("startCash").trim());		
		String transactionDescription = req.getParameter("transactionDescription").trim();
		Integer changeCash = new Integer(req.getParameter("changeCash").trim());		
		Integer changePoint = new Integer(req.getParameter("changePoint").trim());		
		
			
		PointTransactionVO pointTransactionVO = new PointTransactionVO();		
		
		pointTransactionVO.setMemId(memId);
		pointTransactionVO.setStartPoint(startPoint);
		pointTransactionVO.setStartCash(startCash);
		pointTransactionVO.setTransactionDescription(transactionDescription);
		pointTransactionVO.setChangePoint(changePoint);
		pointTransactionVO.setChangeCash(changeCash);

		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			req.setAttribute("pointTransactionVO", pointTransactionVO); // 含有輸入格式錯誤的empVO物件,也存入req
			RequestDispatcher failureView = req
					.getRequestDispatcher("/backend/buyAgentMgt/addPointTransaction2.jsp");
			
			failureView.forward(req, res);
			return;
		}
		
		/***************************2.開始新增資料***************************************/	
		PointTransactionService pointTransactionSvc = new PointTransactionService();
		pointTransactionVO = pointTransactionSvc.addPointTransaction(memId, startPoint, startCash, transactionDescription, changeCash,changePoint);
		
		/***************************3.新增完成,Mail寄送,準備轉交(Send the Success view)***********/
		
		MemberService memberSvc = new MemberService();
				
		MemberVO memberVO = new MemberVO();
		memberVO = memberSvc.getOneMember(memId);
		
		String to = memberVO.getMemEmail();
	    String subject = "交易通知";
	    String ch_name = memberVO.getMemName();
	    
	    String messageText = "親愛的" + ch_name + "  您好！" + "\n" +" 感謝您"+ pointTransactionVO.getTransactionDescription() + "\n"
	    		+ "交易編號" + pointTransactionVO.getTransactionNo() + "\n"  + "交易日期: " + pointTransactionVO.getTransactionTime() + "\n"; 
//System.out.println(pointTransactionVO.getTransactionDescription());
//System.out.println(pointTransactionVO.getTransactionNo());
//System.out.println(pointTransactionVO.getTransactionTime());	    
	    	      MailService mailService = new MailService();

	      mailService.sendMail(to, subject, messageText);
		String url ="/backend/buyAgentMgt/listAllPointTransaction.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url); 
		successView.forward(req, res);				
		
		/***************************其他可能的錯誤處理**********************************/
	} catch (Exception e) {
		errorMsgs.add(e.getMessage());
		RequestDispatcher failureView = req
				.getRequestDispatcher("/backend/buyAgentMgt/addPointTransaction2.jsp");
		failureView.forward(req, res);
	}
}


if ("delete".equals(action)) { // 來自listAllEmp.jsp

	List<String> errorMsgs = new LinkedList<String>();
	// Store this set in the request scope, in case we need to
	// send the ErrorPage view.
	req.setAttribute("errorMsgs", errorMsgs);

	try {
		/***************************1.接收請求參數***************************************/
		Integer transactionNo = new Integer(req.getParameter("transactionNo"));
		
		/***************************2.開始刪除資料***************************************/
		PointTransactionService pointTransactionSvc = new PointTransactionService();
		pointTransactionSvc.deletePointTransaction(transactionNo);
		
		/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
		String url = "/backend/buyAgentMgt/listAllPointTransaction.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
		successView.forward(req, res);
		
		/***************************其他可能的錯誤處理**********************************/
	} catch (Exception e) {
		errorMsgs.add("刪除資料失敗:"+e.getMessage());
		RequestDispatcher failureView = req
				.getRequestDispatcher("/backend/buyAgentMgt/listAllPointTransaction.jsp");
		failureView.forward(req, res);
	}
}
		
		
		
	
}
}
