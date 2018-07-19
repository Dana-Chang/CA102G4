package com.chatroom.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.chatroom.model.*;

public class ChatroomServlet extends HttpServlet {

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
				String str = req.getParameter("chRoomNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入聊天室編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/chatroom/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer chRoomNo = null;
				try {
					chRoomNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("聊天室編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/chatroom/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ChatroomService chatroomSvc = new ChatroomService();
				ChatroomVO chatroomVO = chatroomSvc.getOneChatroom(chRoomNo);
				if (chatroomVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/traveler/chatroom/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("chatroomVO", chatroomVO); // 資料庫取出的empVO物件,存入req
				String url = "/chatroom/listOneChatroom.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/chatroom/select_page.jsp");
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

				ChatroomVO chatroomVO = new ChatroomVO();

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("chatroomVO", chatroomVO); // 含有輸入格式錯誤的empVO物件,也存入req					RequestDispatcher failureView = req
					RequestDispatcher failureView = req.getRequestDispatcher("/chatroom/addChatroom.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ChatroomService chatroomSvc = new ChatroomService();
				chatroomVO = chatroomSvc.addChatroom();
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/frontend/traveler/chatroom/listAllChatroom.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/chatroom/addChatroom.jsp");
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
				Integer chRoomNo = new Integer(req.getParameter("chRoomNo"));
				
				/***************************2.開始刪除資料***************************************/
				ChatroomService chatroomSvc = new ChatroomService();
				chatroomSvc.deleteChatroom(chRoomNo);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/frontend/traveler/chatroom/listAllChatroom.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/chatroom/listAllChatroom.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
