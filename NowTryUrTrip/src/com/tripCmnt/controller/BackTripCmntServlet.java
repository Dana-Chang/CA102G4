
package com.tripCmnt.controller;

import java.io.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.tripCmnt.model.*;

@WebServlet("/backend/tripMgt/backTripCmnt.do")
@MultipartConfig
public class BackTripCmntServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(action);
		
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 若沒有輸入查詢的景點評論編號
				String str = req.getParameter("tripCmntNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入景點評論編號");
				}
				// 送出錯誤訊息
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/tripMgt/tripCmntList.jsp");
					failureView.forward(req, res);
					return;
				}
				// 檢驗輸入行程編號的格式
				Integer tripCmntNo = null;
				try {
					tripCmntNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("景點評論編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/tripMgt/tripCmntList.jsp");
					failureView.forward(req, res);
					return;
				}
				// 查詢景點評論的資料
				TripCmntService tripCmntSvc = new TripCmntService();
				TripCmntVO tripCmntVO = tripCmntSvc.getOneTripCmnt(tripCmntNo);
				if (tripCmntVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/tripMgt/tripCmntList.jsp");
					failureView.forward(req, res);
					return;
				}
				// 成功!!送出VO給顯示頁面
				req.setAttribute("tripCmntVO", tripCmntVO);
				String url = "/backend/tripMgt/listOneTripCmnt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("請輸入正確格式:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/tripMgt/tripCmntList.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer tripCmntNo = new Integer(req.getParameter("tripCmntNo"));

				TripCmntService tripCmntSvc = new TripCmntService();
				TripCmntVO tripCmntVO = tripCmntSvc.getOneTripCmnt(tripCmntNo);

				req.setAttribute("tripCmntVO", tripCmntVO);
				String url = "/backend/tripMgt/update_tripCmnt_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("景點評論編號格式輸入錯誤:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/tripMgt/tripCmntList.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			Integer tripCmntNo = new Integer(req.getParameter("tripCmntNo").trim());
			Timestamp tripCmntTime = new TripCmntService().getOneTripCmnt(tripCmntNo).getTripCmntTime();
			try {
				Integer memId=null;
				try {
					memId = new Integer(req.getParameter("memId").trim());
				} catch (Exception e) {
					errorMsgs.add("會員編號格式輸入錯誤:" + e.getMessage());
					e.printStackTrace();
				}
				Integer tripNo=null;
				try {
					tripNo = new Integer(req.getParameter("tripNo").trim());
				} catch (Exception e) {
					e.printStackTrace();
				}
				Integer tripRate=null;
				try {
					tripRate = new Integer(req.getParameter("tripRate").trim());
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				String tripCmntContent = req.getParameter("tripCmntContent").trim();
				String tripCmntIsHdl = req.getParameter("tripCmntIsHdl").trim();
				String tripCmntIsBlocked = req.getParameter("tripCmntIsBlocked").trim();
				String tripCmntBlockedReason = req.getParameter("tripCmntBlockedReason").trim();

				TripCmntVO tripCmntVO = new TripCmntVO();
				tripCmntVO.setTripCmntNo(tripCmntNo);
				tripCmntVO.setMemId(memId);
				tripCmntVO.setTripNo(tripNo);
				tripCmntVO.setTripCmntContent(tripCmntContent);
				tripCmntVO.setTripCmntTime(tripCmntTime);
				tripCmntVO.setTripRate(tripRate);
				tripCmntVO.setTripCmntIsHdl(tripCmntIsHdl);
				tripCmntVO.setTripCmntIsBlocked(tripCmntIsBlocked);
				tripCmntVO.setTripCmntBlockedReason(tripCmntBlockedReason);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("tripCmntVO", tripCmntVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/tripMgt/update_tripCmnt_input.jsp");
					failureView.forward(req, res);
					return;
				}

				TripCmntService tripCmntSvc = new TripCmntService();
				tripCmntVO = tripCmntSvc.updateTripCmnt(tripCmntNo, memId, tripNo, tripCmntContent, tripCmntTime, tripRate, tripCmntIsHdl, tripCmntIsBlocked, tripCmntBlockedReason);

				req.setAttribute("stripCmntVO", tripCmntVO);
				String url = "/backend/tripMgt/listOneTripCmnt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("格式有誤:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/tripMgt/update_tripCmnt_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer memId = new Integer(req.getParameter("memId").trim());
				Integer tripNo = new Integer(req.getParameter("tripNo").trim());
				String tripCmntContent = req.getParameter("tripCmntContent").trim();
				System.out.println(req.getParameter("tripCmntTime"));
//				現在時間
				Date date = new Date();
				Timestamp tripCmntTime = new java.sql.Timestamp(date.getTime());
				
				Integer tripRate = new Integer(req.getParameter("tripRate").trim());
				String tripCmntIsHdl = req.getParameter("tripCmntIsHdl").trim();
				String tripCmntIsBlocked = req.getParameter("tripCmntIsBlocked").trim();
				String tripCmntBlockedReason = null;
				try {
					tripCmntBlockedReason = req.getParameter("tripCmntBlockedReason").trim();
				} catch (Exception e) {
					//沒有屏蔽理由
					System.out.println("沒有屏蔽理由");
					e.printStackTrace();
					tripCmntBlockedReason=null;
				}

				TripCmntVO tripCmntVO = new TripCmntVO();
				tripCmntVO.setMemId(memId);
				tripCmntVO.setTripNo(tripNo);
				tripCmntVO.setTripCmntContent(tripCmntContent);
				tripCmntVO.setTripCmntTime(tripCmntTime);
				tripCmntVO.setTripRate(tripRate);
				tripCmntVO.setTripCmntIsHdl(tripCmntIsHdl);
				tripCmntVO.setTripCmntIsBlocked(tripCmntIsBlocked);
				tripCmntVO.setTripCmntBlockedReason(tripCmntBlockedReason);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("tripCmntVO", tripCmntVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/tripMgt/addTripCmnt.jsp");
					failureView.forward(req, res);
					return;
				}
				// 若無任何錯誤則送出新增
				TripCmntService tripCmntSvc = new TripCmntService();
				tripCmntVO = tripCmntSvc.addTripCmnt(memId, tripNo, tripCmntContent, tripCmntTime, tripRate, tripCmntIsHdl, tripCmntIsBlocked, tripCmntBlockedReason);
				// 回到行程列表
				String url = "/backend/tripMgt/tripCmntList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/tripMgt/tripCmntList.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer tripCmntNo = new Integer(req.getParameter("tripCmntNo"));
				TripCmntService tripCmntSvc = new TripCmntService();
				tripCmntSvc.deleteTripCmnt(tripCmntNo);
				// 回到行程列表
				String url = "/backend/tripMgt/tripCmntList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("刪除失敗?:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/tripMgt/tripCmntList.jsp.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
}
