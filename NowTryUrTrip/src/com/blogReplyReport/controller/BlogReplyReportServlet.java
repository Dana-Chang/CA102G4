package com.blogReplyReport.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.blogArticle.model.BlogArticleService;
import com.blogArticle.model.BlogArticleVO;
import com.blogReplyReport.model.*;


public class BlogReplyReportServlet extends HttpServlet {

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
				String str = req.getParameter("replyReportNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入留言編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/blogReplyReport/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer replyReportNo = null;
				try {
					replyReportNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("留言編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/blogReplyReport/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				BlogReplyReportService blogReplyReportSvc = new BlogReplyReportService();
				BlogReplyReportVO blogReplyReportVO = blogReplyReportSvc.getOneBlogReplyReport(replyReportNo);
				if (blogReplyReportVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/blogReplyReport/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("blogReplyReportVO", blogReplyReportVO); // 資料庫取出的empVO物件,存入req
				String url = "/frontend/traveler/blogReplyReport/listOneBlogReplyReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/blogReplyReport/select_page.jsp");
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
				Integer replyReportNo = new Integer(req.getParameter("replyReportNo"));
				
				/***************************2.開始查詢資料****************************************/
				BlogReplyReportService blogReplyReportSvc = new BlogReplyReportService();
				BlogReplyReportVO blogReplyReportVO = blogReplyReportSvc.getOneBlogReplyReport(replyReportNo);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("blogReplyReportVO", blogReplyReportVO);         // 資料庫取出的empVO物件,存入req
				String url = "/frontend/traveler/blogReplyReport/update_blogReplyReport_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/blogReplyReport/listAllBlogReplyReport.jsp");
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
				Integer replyReportNo = new Integer(req.getParameter("replyReportNo").trim());
				String adminChecked = req.getParameter("adminChecked").trim();
				String noRpReason = req.getParameter("noRpReason").trim();
			

				BlogReplyReportVO blogReplyReportVO = new BlogReplyReportVO();
				blogReplyReportVO.setReplyNo(replyReportNo);
				blogReplyReportVO.setAdminChecked(adminChecked);
				blogReplyReportVO.setNoRpReason(noRpReason);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("blogReplyReportVO", blogReplyReportVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/blogReplyReport/update_blogReplyReport_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				BlogReplyReportService blogReplyReportSvc = new BlogReplyReportService();
				blogReplyReportVO = blogReplyReportSvc.updateBlogReplyReport(replyReportNo, adminChecked, noRpReason);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("blogReplyReportVO", blogReplyReportVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/backend/blog/message_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/blogReplyReport/update_blogReplyReport_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer articleNo = new Integer(req.getParameter("articleNo").trim());
				Integer replyNo = new Integer(req.getParameter("replyNo").trim());
				Integer memId = new Integer(req.getParameter("memId").trim());				
				String rpReason = req.getParameter("rpReason").trim();

				BlogReplyReportVO blogReplyReportVO = new BlogReplyReportVO();
				blogReplyReportVO.setReplyNo(replyNo);
				blogReplyReportVO.setMemId(memId);
				blogReplyReportVO.setRpReason(rpReason);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("blogReplyReportVO", blogReplyReportVO); // 含有輸入格式錯誤的empVO物件,也存入req					RequestDispatcher failureView = req
					RequestDispatcher failureView = req.getRequestDispatcher("/blogReplyReport/addBlogReplyReport.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				BlogReplyReportService blogReplyReportSvc = new BlogReplyReportService();
				blogReplyReportVO = blogReplyReportSvc.addBlogReplyReport(replyNo, memId, rpReason);
				BlogArticleService blogArticleSvc = new BlogArticleService(); 
				BlogArticleVO blogArticleVO = blogArticleSvc.getOneBlogArticle(articleNo);
				req.setAttribute("blogArticleVO",blogArticleVO);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/frontend/traveler/blogArticle/listOneBlogArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/blogReplyReport/addBlogReplyReport.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer replyReportNo = new Integer(req.getParameter("replyReportNo"));
				
				/***************************2.開始刪除資料***************************************/
				BlogReplyReportService blogReplyReportSvc = new BlogReplyReportService();
				blogReplyReportSvc.deleteBlogReplyReport(replyReportNo);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/frontend/traveler/blogReplyReport/listAllBlogReplyReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/blogReplyReport/listAllBlogReplyReport.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
