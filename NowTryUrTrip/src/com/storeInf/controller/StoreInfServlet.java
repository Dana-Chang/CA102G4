package com.storeInf.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.storeInf.model.StoreInfService;
import com.storeInf.model.StoreInfVO;

public class StoreInfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/******************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
			try {
				Integer uniformNum  = new Integer(req.getParameter("uniformNum").trim());
				String principal = req.getParameter("principal").trim();
				String storCall = req.getParameter("storCall").trim();
				String storAdd = req.getParameter("storAdd").trim();
				Integer postalCode = new Integer(req.getParameter("postalCode").trim());
				Integer category = new Integer(req.getParameter("category").trim());
				
				StoreInfVO storeInfVO = new StoreInfVO();
				storeInfVO.setUniformNum(uniformNum);
				storeInfVO.setPrincipal(principal);
				storeInfVO.setStorCall(storCall);
				storeInfVO.setStorAdd(storAdd);
				storeInfVO.setPostalCode(postalCode);
				storeInfVO.setCategory(category);
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("storeInfVO", storeInfVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/member/addMember.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始新增資料 ***************************************/
				StoreInfService memSvc = new StoreInfService();
				storeInfVO = memSvc.addStoreInf(uniformNum, principal, storCall, storAdd, postalCode, category);
				/********* 3.新增完成,準備轉交(Send the Success view) *********/
				String url = "/frontend/member/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/member/StoreInf/addStoreInf.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
