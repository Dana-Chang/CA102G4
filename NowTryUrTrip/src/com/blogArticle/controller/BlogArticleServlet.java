package com.blogArticle.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.blogArticle.model.*;

public class BlogArticleServlet extends HttpServlet {

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
				String str = req.getParameter("articleNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入文章編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/blogArticle/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer articleNo = null;
				try {
					articleNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/blogArticle/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				BlogArticleService blogArticleSvc = new BlogArticleService();
				BlogArticleVO blogArticleVO = blogArticleSvc.getOneBlogArticle(articleNo);
				if (blogArticleVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/blogArticle/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("blogArticleVO", blogArticleVO); // 資料庫取出的empVO物件,存入req
				String url = "/frontend/traveler/blogArticle/listOneBlogArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/blogArticle/select_page.jsp");
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
				Integer articleNo = new Integer(req.getParameter("articleNo"));
				
				/***************************2.開始查詢資料****************************************/
				BlogArticleService blogArticleSvc = new BlogArticleService();
				BlogArticleVO blogArticleVO = blogArticleSvc.getOneBlogArticle(articleNo);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("blogArticleVO", blogArticleVO);         // 資料庫取出的empVO物件,存入req
				String url = "/frontend/traveler/blogArticle/update_blogArticle_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/blogArticle/listAllBlogArticle.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
//			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer articleNo = new Integer(req.getParameter("articleNo").trim());
				Integer authorNo = new Integer(req.getParameter("authorNo").trim());
				
				java.sql.Timestamp articleTime = null;
				try {
					articleTime = java.sql.Timestamp.valueOf(req.getParameter("articleTime").trim());
				} catch (IllegalArgumentException e) {
					articleTime=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入時間!");
				}
				
				String viewable = req.getParameter("viewable").trim();		
				String reported = req.getParameter("reported").trim();
				String reviewed = req.getParameter("reviewed").trim();
				String isBlocked = req.getParameter("isBlocked").trim();		
				String blockedReason = req.getParameter("blockedReason").trim();
				String articleContent = req.getParameter("articleContent").trim();
				String articleTitle = req.getParameter("articleTitle").trim();

				BlogArticleVO blogArticleVO = new BlogArticleVO();
				blogArticleVO.setArticleNo(articleNo);
				blogArticleVO.setAuthorNo(authorNo);
				blogArticleVO.setArticleTime(articleTime);
				blogArticleVO.setViewable(viewable);
				blogArticleVO.setReported(reported);
				blogArticleVO.setReviewed(reviewed);
				blogArticleVO.setIsBlocked(isBlocked);
				blogArticleVO.setBlockedReason(blockedReason);
				blogArticleVO.setArticleContent(articleContent);
				blogArticleVO.setArticleTitle(articleTitle);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("blogArticleVO", blogArticleVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/blogArticle/update_blogArticle_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				BlogArticleService blogArticleSvc = new BlogArticleService();
				blogArticleVO = blogArticleSvc.updateBlogArticle(articleNo, authorNo, articleTime, viewable, reported, reviewed, isBlocked, blockedReason, articleContent, articleTitle);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("blogArticleVO", blogArticleVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/frontend/traveler/blogArticle/listOneBlogArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/blogArticle/update_blogArticle_input.jsp");
//				failureView.forward(req, res);
//			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer authorNo = new Integer(req.getParameter("authorNo").trim());							
				String viewable = req.getParameter("viewable").trim();		
				String articleContent = req.getParameter("articleContent").trim();
				String articleTitle = req.getParameter("articleTitle").trim();		

				BlogArticleVO blogArticleVO = new BlogArticleVO();
				blogArticleVO.setAuthorNo(authorNo);
				blogArticleVO.setViewable(viewable);
				blogArticleVO.setArticleContent(articleContent);
				blogArticleVO.setArticleTitle(articleTitle);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("blogArticleVO", blogArticleVO); // 含有輸入格式錯誤的empVO物件,也存入req					RequestDispatcher failureView = req
					RequestDispatcher failureView = req.getRequestDispatcher("/blogArticle/addBlogArticle.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				BlogArticleService blogArticleSvc = new BlogArticleService();
				blogArticleVO = blogArticleSvc.addBlogArticle(authorNo, viewable, articleContent, articleTitle);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/frontend/traveler/blogArticle/listAllBlogArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/blogArticle/addBlogArticle.jsp");
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
				Integer articleNo = new Integer(req.getParameter("articleNo"));
				
				/***************************2.開始刪除資料***************************************/
				BlogArticleService blogArticleSvc = new BlogArticleService();
				blogArticleSvc.deleteBlogArticle(articleNo);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/frontend/traveler/blogArticle/listAllBlogArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/blogArticle/listAllBlogArticle.jsp");
				failureView.forward(req, res);
			}
		}
		
		//搜尋功能
		if ("search".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				//Map<String, String[]> map = req.getParameterMap();
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				if (req.getParameter("whichPage") == null){
					HashMap<String, String[]> map1 = (HashMap<String, String[]>)req.getParameterMap();
					HashMap<String, String[]> map2 = new HashMap<String, String[]>();
					map2 = (HashMap<String, String[]>)map1.clone();
					session.setAttribute("map",map2);
					map = (HashMap<String, String[]>)req.getParameterMap();
				} 
				
				/***************************2.開始複合查詢***************************************/
				BlogArticleService blogArticleSvc = new BlogArticleService();
				List<BlogArticleVO> list  = blogArticleSvc.getAll(map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("search", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/frontend/traveler/blogArticle/listSearchBlogArticle.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}		
	}
}
