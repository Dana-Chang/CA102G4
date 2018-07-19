
package com.spotRpt.controller;

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

import com.spot.model.*;
import com.spotRpt.model.*;

@WebServlet("/backend/spotMgt/backSpotRpt.do")
@MultipartConfig
public class BackSpotRptServlet extends HttpServlet {

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
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/spotMgt/spotRptList.jsp");
					failureView.forward(req, res);
					return;
				}
				Integer rptNo = null;
				try {
					rptNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("景點評論編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/spotMgt/spotRptList.jsp");
					failureView.forward(req, res);
					return;
				}
				SpotRptService spotRptSvc = new SpotRptService();
				SpotRptVO spotRptVO = spotRptSvc.getOneSpotRpt(rptNo);
				if (spotRptVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/spotMgt/spotRptList.jsp");
					failureView.forward(req, res);
					return;
				}
				req.setAttribute("spotRptVO", spotRptVO);
				String url = "/backend/spotMgt/spotRptList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("請輸入正確格式:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/spotMgt/spotRptList.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer rptNo = new Integer(req.getParameter("rptNo"));

				SpotRptService spotRptSvc = new SpotRptService();
				SpotRptVO spotRptVO = spotRptSvc.getOneSpotRpt(rptNo);

				req.setAttribute("spotRptVO", spotRptVO);
				String url = "/backend/spotMgt/spotRptList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("景點評論編號格式輸入錯誤:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/spotMgt/spotRptList.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			Integer rptNo = new Integer(req.getParameter("rptNo").trim());
			Timestamp rptTime = new SpotRptService().getOneSpotRpt(rptNo).getRptTime();
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
				String rptReason=req.getParameter("rptReason").trim();
				
				SpotRptVO spotRptVO = new SpotRptVO();
				spotRptVO.setRptNo(rptNo);
				spotRptVO.setMemId(memId);
				spotRptVO.setSpotNo(spotNo);
				spotRptVO.setRptReason(rptReason);
				spotRptVO.setRptTime(rptTime);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("spotRptVO", spotRptVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/spotMgt/spotRptList.jsp");
					failureView.forward(req, res);
					return;
				}

				SpotRptService spotRptSvc = new SpotRptService();
				spotRptVO = spotRptSvc.updateSpotRpt(rptNo, memId, spotNo, rptReason, rptTime);

				req.setAttribute("spotRptVO", spotRptVO);
				String url = "/backend/spotMgt/spotRptList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("格式有誤:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/spotMgt/spotRptList.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer memId = new Integer(req.getParameter("memId").trim());
				Integer spotNo = new Integer(req.getParameter("spotNo").trim());
				String rptReason = req.getParameter("rptReason").trim();
				
//				現在時間
				Timestamp rptTime = new java.sql.Timestamp(new Date().getTime());

				SpotRptVO spotRptVO = new SpotRptVO();
				spotRptVO.setMemId(memId);
				spotRptVO.setSpotNo(spotNo);
				spotRptVO.setRptReason(rptReason);
				spotRptVO.setRptTime(rptTime);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("spotRptVO", spotRptVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/spotMgt/spotRptList.jsp");
					failureView.forward(req, res);
					return;
				}
				// 若無任何錯誤則送出新增
				SpotRptService spotRptSvc = new SpotRptService();
				spotRptVO = spotRptSvc.addSpotRpt(memId, spotNo, rptReason, rptTime);
				// 回到行程列表
				String url = "/backend/spotMgt/spotRptList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/spotMgt/spotRptList.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer rptNo = new Integer(req.getParameter("rptNo"));
				SpotRptService spotRptSvc = new SpotRptService();
				spotRptSvc.deleteSpotRpt(rptNo);
				// 回到行程列表
				String url = "/backend/spotMgt/spotRptList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("刪除失敗?:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/spotMgt/spotRptList.jsp");
				failureView.forward(req, res);
			}
		}
		if ("set_To_Block".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String spotIsBlocked = req.getParameter("spotIsBlocked");
			String spotHdl = req.getParameter("spotHdl");
			String spotBlockedReason=req.getParameter("spotBlockedReason");
			
			if(spotBlockedReason==null||spotBlockedReason.length() == 0){
				spotBlockedReason="";
			}else{
				byte[] bytes = spotBlockedReason.getBytes(StandardCharsets.ISO_8859_1);
				spotBlockedReason = new String(bytes, StandardCharsets.UTF_8);
			}
			
			try {
				Integer rptNo = new Integer(req.getParameter("rptNo"));

				SpotRptService spotRptSvc = new SpotRptService();
				SpotRptVO spotRptVO = spotRptSvc.getOneSpotRpt(rptNo);
				
				SpotRptVO spotRptVOUpdate = new SpotRptVO();
				spotRptVOUpdate.setRptNo(spotRptVO.getRptNo());
				spotRptVOUpdate.setMemId(spotRptVO.getMemId());
				spotRptVOUpdate.setSpotNo(spotRptVO.getSpotNo());
				spotRptVOUpdate.setRptReason(spotRptVO.getRptReason());
				spotRptVOUpdate.setRptTime(spotRptVO.getRptTime());

				// 存入資料庫
				spotRptVO = spotRptSvc.updateSpotRpt(rptNo, spotRptVO.getMemId(), spotRptVO.getSpotNo(),spotRptVO.getRptReason(), spotRptVO.getRptTime());
				req.setAttribute("spotRptVO", spotRptVO);
				
				SpotService spotSvc = new SpotService();
				SpotVO spotVO = spotSvc.getOneSpot(spotRptVO.getSpotNo());
				spotSvc.updateSpot(spotVO.getSpotNo(), spotVO.getSpotName(),spotVO.getSpotAddr(), spotVO.getSpotType(), spotVO.getSpotLng(), spotVO.getSpotLat(), spotVO.getSpotRate(), spotVO.getSpotOverview(), spotVO.getSpotPhoto(), spotVO.getSpotOwner(), spotVO.getSpotChk(), spotHdl,spotIsBlocked,spotBlockedReason);
				
				String url = "/backend/spotMgt/spotRptList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("景點評論編號格式輸入錯誤:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/spotMgt/spotRptList.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
