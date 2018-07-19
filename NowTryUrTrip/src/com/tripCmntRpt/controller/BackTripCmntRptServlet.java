
package com.tripCmntRpt.controller;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.tripCmnt.model.*;
import com.tripCmntRpt.model.*;

@WebServlet("/backend/tripMgt/backTripCmntRpt.do")
@MultipartConfig
public class BackTripCmntRptServlet extends HttpServlet {

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
				String str = req.getParameter("rptNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入景點評論編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/tripMgt/tripCmntRptList.jsp");
					failureView.forward(req, res);
					return;
				}
				Integer tripCmntRptNo = null;
				try {
					tripCmntRptNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("景點評論編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/tripMgt/tripCmntRptList.jsp");
					failureView.forward(req, res);
					return;
				}
				TripCmntRptService tripCmntRptSvc = new TripCmntRptService();
				TripCmntRptVO tripCmntRptVO = tripCmntRptSvc.getOneTripCmntRpt(tripCmntRptNo);
				if (tripCmntRptVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/tripMgt/tripCmntRptList.jsp");
					failureView.forward(req, res);
					return;
				}
				req.setAttribute("tripCmntRptVO", tripCmntRptVO);
				String url = "/backend/tripMgt/tripCmntRptList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("請輸入正確格式:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/tripMgt/tripCmntRptList.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer tripCmntRptNo = new Integer(req.getParameter("tripCmntRptNo"));

				TripCmntRptService tripCmntRptSvc = new TripCmntRptService();
				TripCmntRptVO tripCmntRptVO = tripCmntRptSvc.getOneTripCmntRpt(tripCmntRptNo);

				req.setAttribute("tripCmntRptVO", tripCmntRptVO);
				String url = "/backend/tripMgt/tripCmntRptList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("景點評論編號格式輸入錯誤:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/tripMgt/tripCmntRptList.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			Integer tripCmntRptNo = new Integer(req.getParameter("tripCmntRptNo").trim());
			Timestamp rptTime = new TripCmntRptService().getOneTripCmntRpt(tripCmntRptNo).getRptTime();
			try {
				Integer memId=null;
				try {
					memId = new Integer(req.getParameter("memId").trim());
				} catch (Exception e) {
					errorMsgs.add("會員編號格式輸入錯誤:" + e.getMessage());
					e.printStackTrace();
				}
				Integer tripCmntNo=null;
				try {
					tripCmntNo = new Integer(req.getParameter("tripCmntNo").trim());
				} catch (Exception e) {
					e.printStackTrace();
				}
				String rptReason=req.getParameter("rptReason").trim();
				
				TripCmntRptVO tripCmntRptVO = new TripCmntRptVO();
				tripCmntRptVO.setTripCmntRptNo(tripCmntRptNo);
				tripCmntRptVO.setMemId(memId);
				tripCmntRptVO.setTripCmntNo(tripCmntNo);
				tripCmntRptVO.setRptReason(rptReason);
				tripCmntRptVO.setRptTime(rptTime);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("tripCmntRptVO", tripCmntRptVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/tripMgt/tripCmntRptList.jsp");
					failureView.forward(req, res);
					return;
				}

				TripCmntRptService tripCmntRptSvc = new TripCmntRptService();
				tripCmntRptVO = tripCmntRptSvc.updateTripCmntRpt(tripCmntRptNo, memId, tripCmntNo, rptReason, rptTime);

				req.setAttribute("tripCmntRptVO", tripCmntRptVO);
				String url = "/backend/tripMgt/tripCmntRptList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("格式有誤:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/tripMgt/tripCmntRptList.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer memId = new Integer(req.getParameter("memId").trim());
				Integer spotCmntNo = new Integer(req.getParameter("spotCmntNo").trim());
				String rptReason = req.getParameter("rptReason").trim();
				
//				現在時間
				Timestamp rptTime = new java.sql.Timestamp(new Date().getTime());

				TripCmntRptVO tripCmntRptVO = new TripCmntRptVO();
				tripCmntRptVO.setMemId(memId);
				tripCmntRptVO.setTripCmntNo(spotCmntNo);
				tripCmntRptVO.setRptReason(rptReason);
				tripCmntRptVO.setRptTime(rptTime);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("tripCmntRptVO", tripCmntRptVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/tripMgt/tripCmntRptList.jsp");
					failureView.forward(req, res);
					return;
				}
				// 若無任何錯誤則送出新增
				TripCmntRptService tripCmntRptSvc = new TripCmntRptService();
				tripCmntRptVO = tripCmntRptSvc.addTripCmntRpt(memId, spotCmntNo, rptReason, rptTime);
				// 回到行程列表
				String url = "/backend/tripMgt/tripCmntRptList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/tripMgt/tripCmntRptList.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer tripCmntRptNo = new Integer(req.getParameter("tripCmntRptNo"));
				TripCmntRptService tripCmntRptSvc = new TripCmntRptService();
				tripCmntRptSvc.deleteTripCmntRpt(tripCmntRptNo);
				// 回到行程列表
				String url = "/backend/tripMgt/tripCmntRptList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("刪除失敗?:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/tripMgt/tripCmntRptList.jsp");
				failureView.forward(req, res);
			}
		}
		if ("set_To_Block".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String isBlocked = req.getParameter("isBlocked");
			String isChecked = req.getParameter("isChecked");
			String blockedReason=null;
			if(req.getParameter("blockedReason")==null||(req.getParameter("blockedReason")).length() == 0){
				blockedReason=null;
			}else{
				blockedReason=req.getParameter("blockedReason");
				byte[] bytes = blockedReason.getBytes(StandardCharsets.ISO_8859_1);
				blockedReason = new String(bytes, StandardCharsets.UTF_8);
			}
			
			try {
				Integer tripCmntRptNo = new Integer(req.getParameter("tripCmntRptNo"));

				TripCmntRptService tripCmntRptSvc = new TripCmntRptService();
				TripCmntRptVO tripCmntRptVO = tripCmntRptSvc.getOneTripCmntRpt(tripCmntRptNo);
				
				TripCmntRptVO tripCmntRptVOUpdate = new TripCmntRptVO();
				tripCmntRptVOUpdate.setTripCmntRptNo(tripCmntRptVO.getTripCmntRptNo());
				tripCmntRptVOUpdate.setMemId(tripCmntRptVO.getMemId());
				tripCmntRptVOUpdate.setTripCmntNo(tripCmntRptVO.getTripCmntNo());
				tripCmntRptVOUpdate.setRptReason(tripCmntRptVO.getRptReason());
				tripCmntRptVOUpdate.setRptTime(tripCmntRptVO.getRptTime());

				// 存入資料庫
				tripCmntRptVO = tripCmntRptSvc.updateTripCmntRpt(tripCmntRptNo, tripCmntRptVO.getMemId(), tripCmntRptVO.getTripCmntNo(),tripCmntRptVO.getRptReason(), tripCmntRptVO.getRptTime());
				
				req.setAttribute("tripCmntRptVO", tripCmntRptVO);
				TripCmntService tripCmntSvc = new TripCmntService();
				TripCmntVO tripCmntVO = tripCmntSvc.getOneTripCmnt(tripCmntRptVO.getTripCmntNo());
				tripCmntSvc.updateTripCmnt(tripCmntVO.getTripCmntNo(), tripCmntVO.getMemId(), tripCmntVO.getTripCmntNo(), tripCmntVO.getTripCmntContent(), tripCmntVO.getTripCmntTime(), tripCmntVO.getTripRate(), isChecked, isBlocked, blockedReason);
				
				String url = "/backend/tripMgt/tripCmntRptList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("景點評論編號格式輸入錯誤:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/tripMgt/tripCmntRptList.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
