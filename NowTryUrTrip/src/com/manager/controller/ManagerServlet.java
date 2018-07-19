package com.manager.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.manager.model.ManagerService;
import com.manager.model.ManagerVO;

public class ManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("inster".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/******************** 1.接收請求參數 - 輸入格式的錯誤處理 ******************/
			try {
				String mgrName = req.getParameter("mgrName").trim();
				if (mgrName == null || (mgrName.trim()).length() == 0) {
					errorMsgs.add("請輸入管理員姓名");
				}
				String mgrAccount = req.getParameter("mgrAccount").trim();
				if (mgrAccount == null || (mgrAccount.trim()).length() == 0) {
					errorMsgs.add("請輸入管理員帳號");
				}
				String mgrPsw = req.getParameter("mgrPsw").trim();
				String mgrIdentity = req.getParameter("mgrIdentity").trim();
				if (mgrIdentity == null || (mgrIdentity.trim()).length() == 0) {
					errorMsgs.add("請選擇管理員身分");
				}
				Integer mgrStatus = null;

				ManagerVO managerVO = new ManagerVO();
				managerVO.setMgrName(mgrName);
				managerVO.setMgrAccount(mgrAccount);
				managerVO.setMgrPsw(mgrPsw);
				managerVO.setMgrIdentity(mgrIdentity);
				managerVO.setMgrStatus(1);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("manager", managerVO);
					// 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/admin/addManager.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始新增資料 ***************************************/
				ManagerService mgrSvc = new ManagerService();
				managerVO = mgrSvc.addManager(mgrName, mgrAccount, mgrPsw, mgrIdentity, mgrStatus);
				/********* 3.新增完成,準備轉交(Send the Success view) *********/
				String url = "/backend/admin/listAllManager.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/admin/addManager.jsp");
				failureView.forward(req, res);
			}
		}
		if("update".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/**************** 1.接收請求參數 - 輸入格式的錯誤處理 **************/
			try{
				Integer mgrId = new Integer(req.getParameter("mgrId").trim());
				System.out.println(mgrId);
				String mgrName = req.getParameter("mgrName").trim();
				System.out.println(mgrName);
				if (mgrName == null || (mgrName.trim()).length() == 0) {
					errorMsgs.add("請輸入管理員姓名");
				}
				String mgrAccount = req.getParameter("mgrAccount").trim();
				String mgrPsw = req.getParameter("mgrPsw").trim();
				String mgrIdentity = req.getParameter("mgrIdentity").trim();
				Integer mgrStatus = new Integer(req.getParameter("mgrStatus").trim());
				
				ManagerService managerSvc = new ManagerService();
				ManagerVO managerVO = managerSvc.getOneManager(mgrId);
				managerVO.setMgrId(mgrId);
				managerVO.setMgrName(mgrName);
				managerVO.setMgrAccount(mgrAccount);
				managerVO.setMgrPsw(mgrPsw);
				managerVO.setMgrIdentity(mgrIdentity);
				managerVO.setMgrStatus(mgrStatus);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("managerVO", managerVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/admin/updateManager.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始修改資料 *****************************************/
				ManagerService mgrSvc = new ManagerService();
				managerVO = mgrSvc.updateManager(mgrName, mgrAccount, mgrPsw, mgrIdentity, mgrStatus, mgrId);
				/************ 3.修改完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("managerVO", managerVO);
				
				String url = "/backend/admin/listAllManagerIdentity.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/admin/updateManager.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		HttpSession session = req.getSession();
		String url = "";
		if ("login".equals(action)) {
			/************ 1.接收請求參數 - 輸入格式的錯誤處理 ***************/
			String mgrAccount = req.getParameter("mgrAccount").trim();
			String mgrPsw = req.getParameter("mgrPsw").trim();
			/*************************** 2.開始查詢資料 *****************************************/
			ManagerService managerSvc = new ManagerService();
			ManagerVO managerVO = managerSvc.getOneManagerAccount(mgrAccount);
			if (managerVO == null) {
				errorMsgs.add("無此帳號!");
			} else if (managerVO.getMgrStatus() == 0) { // 判斷 帳號是否停權
				errorMsgs.add("帳號已停權,請重新註冊新帳號!");
			} else if (!managerVO.getMgrPsw().equals(mgrPsw)) { // 判斷 帳密是否正確
				errorMsgs.add("請確認密碼是否正確!");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/admin/Login/login.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			/********* 3.查詢完成,準備轉交(Send the Success view) ********/
			req.getSession().setAttribute("loginManagerVO", managerVO);
			url = "/backend/admin/Login/index.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
			// index.jsp
			successView.forward(req, res);
		}
		if ("logout".equals(action)) {
			session = req.getSession();
			session.invalidate();
			url = "/backend/admin/Login/login.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	}
}