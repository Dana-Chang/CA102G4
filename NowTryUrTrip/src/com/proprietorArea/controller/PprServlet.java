package com.proprietorArea.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.proprietorArea.model.*;
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 500 * 1024 * 1024, maxRequestSize = 5 * 500 * 1024
* 1024)

public class PprServlet extends HttpServlet {
     

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			doPost(req,res);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");		
		System.out.println(action);
		System.out.println("no1");
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("pprMsageNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/ppr/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer pprMsageNo = null;
				try {
					pprMsageNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				PprService pprSvc = new PprService();
				PprVO pprVO = pprSvc.getOnePpr(pprMsageNo);
				if (pprVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("empVO", pprMsageNo); // 資料庫取出的empVO物件,存入req
				String url = "/emp/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/emp/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】
			try {
				/***************************1.接收請求參數****************************************/
				Integer pprMsgeNo = new Integer(req.getParameter("pprMsgeNo"));
				
				/***************************2.開始查詢資料****************************************/
				PprService pprSvc = new PprService();
				PprVO pprVO = pprSvc.getOnePpr(pprMsgeNo);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("pprVO", pprVO);         // 資料庫取出的empVO物件,存入req
				String url = "/ppr/update_ppr_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/ppr/listAllPpr.jsp");
				failureView.forward(req, res);
			}
		}
		
		
//		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//		
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				Integer empno = new Integer(req.getParameter("empno").trim());
//				String ename = req.getParameter("ename").trim();
//				String job = req.getParameter("job").trim();				
//				
//				java.sql.Date hiredate = null;
//				try {
//					hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//				} catch (IllegalArgumentException e) {
//					hiredate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
//
//				Double sal = null;
//				try {
//					sal = new Double(req.getParameter("sal").trim());
//				} catch (NumberFormatException e) {
//					sal = 0.0;
//					errorMsgs.add("薪水請填數字.");
//				}
//
//				Double comm = null;
//				try {
//					comm = new Double(req.getParameter("comm").trim());
//				} catch (NumberFormatException e) {
//					comm = 0.0;
//					errorMsgs.add("獎金請填數字.");
//				}
//
//				Integer pprSpotNo = new Integer(req.getParameter("pprSpotNo").trim());
//
//				PprVO pprVO = new PprVO();
////				pprVO.setPprMsgeNo(pprMsgeNo);
////				pprVO.setEname(ename);
////				pprVO.setJob(job);
////				pprVO.setHiredate(hiredate);
////				pprVO.setSal(sal);
////				pprVO.setComm(comm);
////				pprVO.setDeptno(deptno);
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("pprVO", pprVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/ppr/update_emp_input.jsp");
//					failureView.forward(req, res);
//					return; //程式中斷
//				}
//				
//				/***************************2.開始修改資料*****************************************/
//				PprService pprSvc = new PprService();
//				
////				pprVO = pprSvc.updatePpr(pprMsgeNo, pprSpotNo, pprCheckIn, pprCheckOut, pprMsgeCtx, pprMsgeImg);
//				
//				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("pprVO", pprVO); // 資料庫update成功後,正確的的empVO物件,存入req
//				String url = "/emp/listOneEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/update_ppr_input.jsp");
//				failureView.forward(req, res);
//			}
//		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  			
        	List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("no2");
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String pprMsgeCtx = req.getParameter("pprMsgeCtx").trim();				
				java.sql.Timestamp pprCheckIn = null;
				java.sql.Timestamp pprCheckOut = null;
				Calendar cal1 = Calendar.getInstance();
				Calendar cal2 = Calendar.getInstance();
				Calendar now = Calendar.getInstance();
				
				try {
					pprCheckIn = new java.sql.Timestamp(sdf.parse(req.getParameter("pprCheckIn").trim()).getTime());
					cal1.setTime(pprCheckIn);
					now.setTime(new java.sql.Timestamp(System.currentTimeMillis()));
//					System.out.println(cal1+"原上架時間");
					pprCheckOut = new java.sql.Timestamp(sdf.parse(req.getParameter("pprCheckOut").trim()).getTime());
					cal2.setTime(pprCheckOut);
					if (cal1.getTime().before(now.getTime())){
						System.out.println("廣告上架日期不得小於現在時間");
						errorMsgs.add("廣告上架日期不得小於現在時間");
					}else{
						cal1.add(cal1.DAY_OF_MONTH, +3);
//						System.out.println(cal1.get(cal1.DAY_OF_MONTH));
//						System.out.println(cal1+"被改過的上架時間");
//						System.out.println(cal2+"下架時間");
						if(cal2.getTime().after(cal1.getTime())){
						System.out.println("下架日期大於上架日期3天! 正確!");	
						}else{
						System.out.println("下架日期不得小於上架日期3天");	
						errorMsgs.add("下架日期不得小於上架日期3天");	
						}
					}
				} catch (IllegalArgumentException e) {
					pprCheckIn= new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入checkIn日期!");	
					pprCheckOut=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入checkOut日期!");
				}
				
				Integer pprSpotNo = new Integer(req.getParameter("pprSpotNo").trim());				
				Part part = req.getPart("pprMsgeImg");
				String fileType = part.getContentType();
				System.out.println(fileType);
				InputStream in = part.getInputStream();
				byte[] pprMsgeImg = new byte[in.available()];
				in.read(pprMsgeImg);
				in.close();
				
				PprVO pprVO = new PprVO();				
				pprVO.setPprMsgeCtx(pprMsgeCtx);
				pprVO.setPprCheckIn(pprCheckIn);
				pprVO.setPprCheckOut(pprCheckOut);
				pprVO.setPprSpotNo(pprSpotNo);
				pprVO.setPprMsgeImg(pprMsgeImg);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("pprVO", pprVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/proprietorArea/proprietorArea_add.jsp");
					failureView.forward(req, res);
					System.out.println("errormsgs1");
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				PprService pprSvc = new PprService();
				System.out.println("okready");
				pprVO = pprSvc.addPpr(pprCheckIn, pprCheckOut, pprMsgeCtx, pprMsgeImg, pprSpotNo );
				System.out.println("ok1");
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/frontend/proprietorArea/listAllPpr.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				System.out.println("ok2");
				successView.forward(req, res);				
				System.out.println("ok3");
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/proprietorArea/proprietorArea_add.jsp");
				failureView.forward(req, res);
				System.out.println("errormsgs2");
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer pprMsgeNo = new Integer(req.getParameter("pprMsgeNo"));
				
				/***************************2.開始刪除資料***************************************/
				PprService pprSvc = new PprService();
				pprSvc.deletePpr(pprMsgeNo);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/frontend/proprietorArea/listAllPpr.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/proprietorArea/listAllPpr.jsp");
				failureView.forward(req, res);
			}
		}
        System.out.println("no3");
	}
		
}



