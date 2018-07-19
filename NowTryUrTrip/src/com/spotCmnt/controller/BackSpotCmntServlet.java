
package com.spotCmnt.controller;

import java.io.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.spotCmnt.model.*;

@WebServlet("/backend/spotMgt/backSpotCmnt.do")
@MultipartConfig
public class BackSpotCmntServlet extends HttpServlet {

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
				String str = req.getParameter("spotCmntNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入景點評論編號");
				}
				// 送出錯誤訊息
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/spotMgt/spotCmntList.jsp");
					failureView.forward(req, res);
					return;
				}
				// 檢驗輸入行程編號的格式
				Integer spotCmntNo = null;
				try {
					spotCmntNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("景點評論編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/spotMgt/spotCmntList.jsp");
					failureView.forward(req, res);
					return;
				}
				// 查詢景點評論的資料
				SpotCmntService spotCmntSvc = new SpotCmntService();
				SpotCmntVO spotCmntVO = spotCmntSvc.getOneSpotCmnt(spotCmntNo);
				if (spotCmntVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/spotMgt/spotCmntList.jsp");
					failureView.forward(req, res);
					return;
				}
				// 成功!!送出VO給顯示頁面
				req.setAttribute("spotCmntVO", spotCmntVO);
				String url = "/backend/spotMgt/listOneSpotCmnt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("請輸入正確格式:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/spotMgt/spotCmntList.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer spotCmntNo = new Integer(req.getParameter("spotCmntNo"));

				SpotCmntService spotCmntSvc = new SpotCmntService();
				SpotCmntVO spotCmntVO = spotCmntSvc.getOneSpotCmnt(spotCmntNo);

				req.setAttribute("spotCmntVO", spotCmntVO);
				String url = "/backend/spotMgt/update_spotCmnt_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("景點評論編號格式輸入錯誤:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/spotMgt/spotCmntList.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			Integer spotCmntNo = new Integer(req.getParameter("spotCmntNo").trim());
			Timestamp cmntTime = new SpotCmntService().getOneSpotCmnt(spotCmntNo).getCmntTime();
			try {
				Integer memId=null;
				try {
					memId = new Integer(req.getParameter("memId").trim());
				} catch (Exception e) {
					errorMsgs.add("會員編號格式輸入錯誤:" + e.getMessage());
					e.printStackTrace();
				}
				Integer spotNo=null;
				try {
					spotNo = new Integer(req.getParameter("spotNo").trim());
				} catch (Exception e) {
					e.printStackTrace();
				}
				Integer rate=null;
				try {
					rate = new Integer(req.getParameter("rate").trim());
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				String cmnt = req.getParameter("cmnt").trim();
				String isChecked = req.getParameter("isChecked").trim();
				String isBlocked = req.getParameter("isBlocked").trim();
				String blockedReason = req.getParameter("blockedReason").trim();

				SpotCmntVO spotCmntVO = new SpotCmntVO();
				spotCmntVO.setSpotCmntNo(spotCmntNo);
				spotCmntVO.setMemId(memId);
				spotCmntVO.setSpotNo(spotNo);
				spotCmntVO.setCmnt(cmnt);
				spotCmntVO.setCmntTime(cmntTime);
				spotCmntVO.setRate(rate);
				spotCmntVO.setIsChecked(isChecked);
				spotCmntVO.setIsBlocked(isBlocked);
				spotCmntVO.setBlockedReason(blockedReason);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("spotCmntVO", spotCmntVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/spotMgt/update_spotCmnt_input.jsp");
					failureView.forward(req, res);
					return;
				}

				SpotCmntService spotCmntSvc = new SpotCmntService();
				spotCmntVO = spotCmntSvc.updateSpotCmnt(spotCmntNo, memId, spotNo, cmnt, cmntTime, rate, isChecked, isBlocked, blockedReason);

				req.setAttribute("spotCmntVO", spotCmntVO);
				String url = "/backend/spotMgt/listOneSpotCmnt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("格式有誤:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/spotMgt/update_spotCmnt_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer memId = new Integer(req.getParameter("memId").trim());
				Integer spotNo = new Integer(req.getParameter("spotNo").trim());
				String cmnt = req.getParameter("cmnt").trim();
				System.out.println(req.getParameter("cmntTime"));
//				現在時間
				Date date = new Date();
				Timestamp cmntTime = new java.sql.Timestamp(date.getTime());
				
				Integer rate = new Integer(req.getParameter("rate").trim());
				String isChecked = req.getParameter("isChecked").trim();
				String isBlocked = req.getParameter("isBlocked").trim();
				String blockedReason = null;
				try {
					blockedReason = req.getParameter("blockedReason").trim();
				} catch (Exception e) {
					//沒有屏蔽理由
					System.out.println("沒有屏蔽理由");
					e.printStackTrace();
					blockedReason=null;
				}

				SpotCmntVO spotCmntVO = new SpotCmntVO();
				spotCmntVO.setMemId(memId);
				spotCmntVO.setSpotNo(spotNo);
				spotCmntVO.setCmnt(cmnt);
				spotCmntVO.setRate(rate);
				spotCmntVO.setIsChecked(isChecked);
				spotCmntVO.setIsBlocked(isBlocked);
				spotCmntVO.setBlockedReason(blockedReason);
				spotCmntVO.setCmntTime(cmntTime);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("spotCmntVO", spotCmntVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/spotMgt/addSpotCmnt.jsp");
					failureView.forward(req, res);
					return;
				}
				// 若無任何錯誤則送出新增
				SpotCmntService spotCmntSvc = new SpotCmntService();
				spotCmntVO = spotCmntSvc.addSpotCmnt(memId, spotNo, cmnt, cmntTime, rate, isChecked, isBlocked, blockedReason);
				// 回到行程列表
				String url = "/backend/spotMgt/spotCmntList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/spotMgt/spotCmntList.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer spotCmntNo = new Integer(req.getParameter("spotCmntNo"));
				SpotCmntService spotCmntSvc = new SpotCmntService();
				spotCmntSvc.deleteSpotCmnt(spotCmntNo);
				// 回到行程列表
				String url = "/backend/spotMgt/spotCmntList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("刪除失敗?:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/spotMgt/spotCmntList.jsp.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
