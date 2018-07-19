
package com.spotCmntRpt.controller;

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

import com.spotCmnt.model.*;
import com.spotCmntRpt.model.SpotCmntRptService;
import com.spotCmntRpt.model.SpotCmntRptVO;

@WebServlet("/backend/spotMgt/backSpotCmntRpt.do")
@MultipartConfig
public class BackSpotCmntRptServlet extends HttpServlet {

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
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/spotMgt/spotCmntRptList.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/spotMgt/spotCmntRptList.jsp");
					failureView.forward(req, res);
					return;
				}
				SpotCmntRptService spotCmntRptSvc = new SpotCmntRptService();
				SpotCmntRptVO spotCmntRptVO = spotCmntRptSvc.getOneSpotCmntRpt(rptNo);
				if (spotCmntRptVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/spotMgt/spotCmntRptList.jsp");
					failureView.forward(req, res);
					return;
				}
				req.setAttribute("spotCmntRptVO", spotCmntRptVO);
				String url = "/backend/spotMgt/spotCmntRptList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("請輸入正確格式:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/spotMgt/spotCmntRptList.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer rptNo = new Integer(req.getParameter("rptNo"));

				SpotCmntRptService spotCmntRptSvc = new SpotCmntRptService();
				SpotCmntRptVO spotCmntRptVO = spotCmntRptSvc.getOneSpotCmntRpt(rptNo);

				req.setAttribute("spotCmntRptVO", spotCmntRptVO);
				String url = "/backend/spotMgt/spotCmntRptList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("景點評論編號格式輸入錯誤:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/spotMgt/spotCmntRptList.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			Integer rptNo = new Integer(req.getParameter("rptNo").trim());
			Timestamp rptTime = new SpotCmntRptService().getOneSpotCmntRpt(rptNo).getRptTime();
			try {
				Integer memId=null;
				try {
					memId = new Integer(req.getParameter("memId").trim());
				} catch (Exception e) {
					errorMsgs.add("會員編號格式輸入錯誤:" + e.getMessage());
					e.printStackTrace();
				}
				Integer spotCmntNo=null;
				try {
					spotCmntNo = new Integer(req.getParameter("spotCmntNo").trim());
				} catch (Exception e) {
					e.printStackTrace();
				}
				String rptReason=req.getParameter("rptReason").trim();
				
				SpotCmntRptVO spotCmntRptVO = new SpotCmntRptVO();
				spotCmntRptVO.setRptNo(rptNo);
				spotCmntRptVO.setMemId(memId);
				spotCmntRptVO.setSpotCmntNo(spotCmntNo);
				spotCmntRptVO.setRptReason(rptReason);
				spotCmntRptVO.setRptTime(rptTime);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("spotCmntRptVO", spotCmntRptVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/spotMgt/spotCmntRptList.jsp");
					failureView.forward(req, res);
					return;
				}

				SpotCmntRptService spotCmntRptSvc = new SpotCmntRptService();
				spotCmntRptVO = spotCmntRptSvc.updateSpotCmntRpt(rptNo, memId, spotCmntNo, rptReason, rptTime);

				req.setAttribute("spotCmntRptVO", spotCmntRptVO);
				String url = "/backend/spotMgt/spotCmntRptList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("格式有誤:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/spotMgt/spotCmntRptList.jsp");
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

				SpotCmntRptVO spotCmntRptVO = new SpotCmntRptVO();
				spotCmntRptVO.setMemId(memId);
				spotCmntRptVO.setSpotCmntNo(spotCmntNo);
				spotCmntRptVO.setRptReason(rptReason);
				spotCmntRptVO.setRptTime(rptTime);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("spotCmntRptVO", spotCmntRptVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/spotMgt/spotCmntRptList.jsp");
					failureView.forward(req, res);
					return;
				}
				// 若無任何錯誤則送出新增
				SpotCmntRptService spotCmntRptSvc = new SpotCmntRptService();
				spotCmntRptVO = spotCmntRptSvc.addSpotCmntRpt(memId, spotCmntNo, rptReason, rptTime);
				// 回到行程列表
				String url = "/backend/spotMgt/spotCmntRptList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/spotMgt/spotCmntRptList.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer rptNo = new Integer(req.getParameter("rptNo"));
				SpotCmntRptService spotCmntRptSvc = new SpotCmntRptService();
				spotCmntRptSvc.deleteSpotCmntRpt(rptNo);
				// 回到行程列表
				String url = "/backend/spotMgt/spotCmntRptList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("刪除失敗?:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/spotMgt/spotCmntRptList.jsp");
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
				blockedReason="";
			}else{
				blockedReason=req.getParameter("blockedReason");
				byte[] bytes = blockedReason.getBytes(StandardCharsets.ISO_8859_1);
				blockedReason = new String(bytes, StandardCharsets.UTF_8);
			}
			
			try {
				Integer rptNo = new Integer(req.getParameter("rptNo"));

				SpotCmntRptService spotCmntRptSvc = new SpotCmntRptService();
				SpotCmntRptVO spotCmntRptVO = spotCmntRptSvc.getOneSpotCmntRpt(rptNo);
				
				SpotCmntRptVO spotCmntRptVOUpdate = new SpotCmntRptVO();
				spotCmntRptVOUpdate.setRptNo(spotCmntRptVO.getRptNo());
				spotCmntRptVOUpdate.setMemId(spotCmntRptVO.getMemId());
				spotCmntRptVOUpdate.setSpotCmntNo(spotCmntRptVO.getSpotCmntNo());
				spotCmntRptVOUpdate.setRptReason(spotCmntRptVO.getRptReason());
				spotCmntRptVOUpdate.setRptTime(spotCmntRptVO.getRptTime());

				// 存入資料庫
				spotCmntRptVO = spotCmntRptSvc.updateSpotCmntRpt(rptNo, spotCmntRptVO.getMemId(), spotCmntRptVO.getSpotCmntNo(),spotCmntRptVO.getRptReason(), spotCmntRptVO.getRptTime());
				
				req.setAttribute("spotCmntRptVO", spotCmntRptVO);
				SpotCmntService spotCmntSvc = new SpotCmntService();
				SpotCmntVO spotCmntVO = spotCmntSvc.getOneSpotCmnt(spotCmntRptVO.getSpotCmntNo());
				spotCmntSvc.updateSpotCmnt(spotCmntVO.getSpotCmntNo(), spotCmntVO.getMemId(), spotCmntVO.getSpotCmntNo(), spotCmntVO.getCmnt(), spotCmntVO.getCmntTime(), spotCmntVO.getRate(), isChecked, isBlocked, blockedReason);
				
				String url = "/backend/spotMgt/spotCmntRptList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("景點評論編號格式輸入錯誤:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/spotMgt/spotCmntRptList.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
