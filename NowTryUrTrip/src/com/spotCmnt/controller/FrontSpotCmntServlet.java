
package com.spotCmnt.controller;

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
import com.tripCmnt.model.TripCmntService;
import com.tripCmnt.model.TripCmntVO;

import net.sf.json.JSONObject;

@WebServlet("/frontend/query/frontSpotCmnt.do")
@MultipartConfig
public class FrontSpotCmntServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");

		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 若沒有輸入查詢的行程編號
				String str = req.getParameter("spotCmntNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入景點評論編號");
				}
				// 送出錯誤訊息
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spotComment/select_page.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spotComment/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				// 查詢行程的資料
				SpotCmntService spotCmntSvc = new SpotCmntService();
				SpotCmntVO spotCmntVO = spotCmntSvc.getOneSpotCmnt(spotCmntNo);
				if (spotCmntVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spotComment/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				// 成功!!送出VO給顯示頁面
				req.setAttribute("spotCmntVO", spotCmntVO);
				String url = "/frontend/spotComment/listOneSpot.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("請輸入正確格式:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spotComment/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getSpotCmnts_By_memId".equals(action)) {
System.out.println("test");
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 若沒有輸入查詢的行程編號
				String str = req.getParameter("memId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// 送出錯誤訊息
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spotComment/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				// 檢驗輸入行程編號的格式
				Integer memId = null;
				try {
					memId = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spotComment/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				// 查詢行程的資料
				List<SpotCmntVO> list = null;
				SpotCmntService spotCmntSvc = new SpotCmntService();
				if (list.isEmpty()) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spotComment/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				// 成功!!送出VO給顯示頁面
				req.setAttribute("spotCmntVOList", list);
				String url = "/frontend/spotComment/listSpotCmnts.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("請輸入正確格式:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spotComment/select_page.jsp");
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
				String url = "/frontend/spotComment/update_spotCmnt_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("行程編號格式輸入錯誤:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spotComment/spotCmnt_list.jsp.jsp");
				failureView.forward(req, res);
			}
		}

		
		
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer spotCmntNo = new Integer(req.getParameter("spotCmntNo").trim());
				Integer memId = new Integer(req.getParameter("memId").trim());
				Integer spotNo = new Integer(req.getParameter("spotNo").trim());
				String cmnt = req.getParameter("cmnt").trim();
				Integer rate = new Integer(req.getParameter("rate").trim());
				String isReported = req.getParameter("isReported").trim();
				String isChecked = req.getParameter("isChecked").trim();
				String isBlocked = req.getParameter("isBlocked").trim();
				String blockedReason = req.getParameter("blockedReason").trim();

				java.sql.Timestamp cmntTime = null;
				try {
					cmntTime = new java.sql.Timestamp(sdf.parse(req.getParameter("cmntTime").trim()).getTime());
				} catch (ParseException e) {
					cmntTime = null;
				}

				SpotCmntVO spotCmntVO = new SpotCmntVO();
				spotCmntVO.setSpotNo(spotCmntNo);
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
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/spotComment/update_spotCmnt_input.jsp");
					failureView.forward(req, res);
					return;
				}

				SpotCmntService spotCmntSvc = new SpotCmntService();
				spotCmntVO = spotCmntSvc.updateSpotCmnt(spotCmntNo, memId, spotNo, cmnt, cmntTime, rate, isChecked, isBlocked, blockedReason);

				req.setAttribute("spotCmntVO", spotCmntVO);
				String url = "/frontend/spotComment/listOneSpot.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {

				e.printStackTrace();
				errorMsgs.add("格式有誤:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/spotComment/update_spotCmnt_input.jsp");
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
				
				byte[] bytes = cmnt.getBytes(StandardCharsets.ISO_8859_1);
				cmnt = new String(bytes, StandardCharsets.UTF_8);
				
				java.sql.Timestamp cmntTime = new java.sql.Timestamp(new Date().getTime());
				Integer rate = new Integer(req.getParameter("rate").trim());

				SpotCmntVO spotCmntVO = new SpotCmntVO();
				spotCmntVO.setMemId(memId);
				spotCmntVO.setSpotNo(spotNo);
				spotCmntVO.setCmnt(cmnt);
				spotCmntVO.setRate(rate);
				spotCmntVO.setIsChecked("0");
				spotCmntVO.setIsBlocked("0");
				spotCmntVO.setBlockedReason("");
				spotCmntVO.setCmntTime(cmntTime);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("spotCmntVO", spotCmntVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spotComment/addSpot.jsp");
					failureView.forward(req, res);
					return;
				}
				// 若無任何錯誤則送出新增
				SpotCmntService spotCmntSvc = new SpotCmntService();
				spotCmntVO = spotCmntSvc.addSpotCmnt(memId, spotNo, cmnt, cmntTime, rate, "0", "0", "");
				// 回到景點列表
//				String url = "/frontend/spotComment/spotCmnt_list.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
				res.setContentType("text/plain");
				res.setCharacterEncoding("UTF-8");
				PrintWriter out = res.getWriter();
				
				JSONObject obj = new JSONObject();
				obj.put("memId", memId);
				obj.put("spotNo", spotNo);
				obj.put("cmnt", cmnt);
				obj.put("rate", rate);
				obj.put("cmntTime", cmntTime);
				
				out.write(obj.toString());
				out.flush();
				out.close();

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spotComment/spotCmnt_list.jsp");
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
				String url = "/frontend/spotComment/spotCmnt_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("刪除失敗?:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/spotComment/spotCmnt_list.jsp.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
