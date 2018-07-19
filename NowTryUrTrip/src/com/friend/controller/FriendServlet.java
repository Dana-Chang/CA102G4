package com.friend.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.friend.model.FriendService;
import com.friend.model.FriendVO;
import com.member.model.*;

public class FriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/******************** 1.接收請求參數 - 輸入格式的錯誤處理 ******************/
			try {
				Integer memId = ((MemberVO) (session.getAttribute("memberVO"))).getMemId();
				Integer friendId = new Integer(req.getParameter("friendId").trim());
				Integer friendRp = 0;

				FriendVO friendVO = new FriendVO();
				friendVO.setMemId(memId);
				friendVO.setFriendId(friendId);
				friendVO.setFriendRp(friendRp);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("friendVO", friendVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/member/MemberHome.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始新增資料 ***************************************/
				FriendService friendSvc = new FriendService();
				friendVO = friendSvc.addFriend(memId, friendId, friendRp);

				/********* 3.新增完成,準備轉交(Send the Success view) *********/
				String url = "/frontend/member/MemberHome.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/member/MemberHome.jsp");
				failureView.forward(req, res);
			}
		}
		if ("befriend".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				Integer memId = ((MemberVO) (session.getAttribute("memberVO"))).getMemId();
				Integer friendId = new Integer(req.getParameter("friendId").trim());
				Integer friendRp = 1;

				FriendVO friendVO = new FriendVO();
				friendVO.setMemId(memId);
				friendVO.setFriendId(friendId);
				friendVO.setFriendRp(friendRp);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("friendVO", friendVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/member/MemberHome.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始修改資料 ***************************************/
				FriendService friendSvc = new FriendService();
				friendVO = friendSvc.befriend(memId, friendId,friendRp);

				/********* 3.新增完成,準備轉交(Send the Success view) *********/
				String url = "/frontend/member/MemberHome.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/member/MemberHome.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
