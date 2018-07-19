package com.purchasingOrder.controller;

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
import com.purchasingOrder.model.*;

public class PurchasingOrderServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		


if ("getOneALL".equals(action)) { // 來自listAllEmp.jsp的請求

	List<String> errorMsgs = new LinkedList<String>();
	// Store this set in the request scope, in case we need to
	// send the ErrorPage view.
	List<PurchasingOrderVO> purchasingOrderVOList = new LinkedList<PurchasingOrderVO>();
	List<PurchasingOrderVO> purchasingOrderVOList2 = new LinkedList<PurchasingOrderVO>();
	req.setAttribute("errorMsgs", errorMsgs);
	
	try {
		/***************************1.接收請求參數****************************************/
		Integer memIdReq = new Integer(req.getParameter("memId"));		
		/***************************2.開始查詢資料****************************************/
		PurchasingOrderService purchasingOrderSvc = new PurchasingOrderService();				
	    purchasingOrderVOList =  purchasingOrderSvc.getOneForAll(memIdReq);
	    purchasingOrderVOList2 =  purchasingOrderSvc.getOneForAllRes(memIdReq);	
		/***************************3.查詢完成,準備轉交(Send the Success view)************/
		req.setAttribute("purchasingOrderVO", purchasingOrderVOList);
		req.setAttribute("purchasingOrderVO2", purchasingOrderVOList2);
		String url = "/frontend/buyAgent/buyAgent.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url);
		successView.forward(req, res);

		/***************************其他可能的錯誤處理**********************************/
	} catch (Exception e) {
		errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
		RequestDispatcher failureView = req
				.getRequestDispatcher("/frontend/buyAgent/buyAgent.jsp");
		failureView.forward(req, res);
	}
}



	
}
}
