
package com.spot.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.spot.model.*;

@WebServlet("/frontend/query/frontSpot.do")
@MultipartConfig
public class FrontSpotServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);

		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 若沒有輸入查詢的地點編號
				String str = req.getParameter("spotNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入地點編號");
				}
				// 送出錯誤訊息
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/query/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				// 檢驗輸入地點編號的格式
				Integer spotNo = null;
				try {
					spotNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("地點編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/query/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				// 查詢地點的資料
				SpotService spotSvc = new SpotService();
				SpotVO spotVO = spotSvc.getOneSpot(spotNo);
				if (spotVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/query/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				// 成功!!送出VO給顯示頁面
				req.setAttribute("spotVO", spotVO);
				String url = "/frontend/query/view_search.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/query/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println(req.getParameter("spotNo"));
			try {
				Integer spotNo = new Integer(req.getParameter("spotNo"));
				SpotService spotSvc = new SpotService();
				SpotVO spotVO = spotSvc.getOneSpot(spotNo);
				req.setAttribute("spotVO", spotVO);
				String url = "/frontend/query/update_spot_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("地點編號格式輸入錯誤:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/query/listAllSpot.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer spotNo = new Integer(req.getParameter("spotNo").trim());
				String spotName = req.getParameter("spotName").trim();
				String spotAddr = req.getParameter("spotAddr").trim();
				String spotType = req.getParameter("spotType").trim();
				String spotLng = req.getParameter("spotLng").trim();
				String spotLat = req.getParameter("spotLat").trim();
				Integer spotRate = new Integer(req.getParameter("spotRate").trim());
				String spotOverview = req.getParameter("spotOverview").trim();
				// 判斷是否有圖片，有的話新增沒有的話保持原來的圖片
				byte[] spotPhoto = null;
				if ((int) (req.getPart("spotPhoto").getSize()) == 0) {
					SpotService spotSvc = new SpotService();
					SpotVO spotVOOrigin = spotSvc.getOneSpot(spotNo);
					spotPhoto = spotVOOrigin.getSpotPhoto();
				} else {
					Part spotImgPart = req.getPart("spotPhoto");
					InputStream in = spotImgPart.getInputStream();
					spotPhoto = new byte[in.available()];
					in.read(spotPhoto);
					in.close();
				}

				Integer spotOwner = new Integer(req.getParameter("spotOwner").trim());
				String spotChk = req.getParameter("spotChk").trim();
				String spotHdl = req.getParameter("spotHdl").trim();
				String spotIsBlocked = req.getParameter("spotIsBlocked").trim();
				String spotBlockedReason = req.getParameter("spotBlockedReason").trim();

				SpotVO spotVO = new SpotVO();
				spotVO.setSpotNo(spotNo);
				spotVO.setSpotName(spotName);
				spotVO.setSpotAddr(spotAddr);
				spotVO.setSpotType(spotType);
				spotVO.setSpotLng(spotLng);
				spotVO.setSpotLat(spotLat);
				spotVO.setSpotRate(spotRate);
				spotVO.setSpotOverview(spotOverview);
				spotVO.setSpotPhoto(spotPhoto);
				spotVO.setSpotOwner(spotOwner);
				spotVO.setSpotChk(spotChk);
				spotVO.setSpotHdl(spotHdl);
				spotVO.setSpotIsBlocked(spotIsBlocked);
				spotVO.setSpotBlockedReason(spotBlockedReason);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("spotVO", spotVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/query/update_spot_input.jsp");
					failureView.forward(req, res);
					return;
				}

				SpotService spotSvc = new SpotService();
				spotVO = spotSvc.updateSpot(spotNo, spotName, spotAddr, spotType, spotLng, spotLat, spotRate,
						spotOverview, spotPhoto, spotOwner, spotChk, spotHdl, spotIsBlocked, spotBlockedReason);

				req.setAttribute("spotVO", spotVO);
				String url = "/frontend/query/listOneSpot.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/query/update_spot_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) {
			System.out.println("11111");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String spotName = req.getParameter("spotName").trim();
				String spotAddr = req.getParameter("spotAddr").trim();
				String spotLng = req.getParameter("spotLng").trim();
				String spotLat = req.getParameter("spotLat").trim();
				String spotOverview = req.getParameter("spotOverview").trim();
				String spotChk = req.getParameter("spotChk").trim();
				String spotHdl = req.getParameter("spotHdl").trim();
				String spotIsBlocked = req.getParameter("spotIsBlocked").trim();
				String spotBlockedReason = req.getParameter("spotBlockedReason").trim();

				Integer spotRate = null;
				try {
					spotRate = new Integer(req.getParameter("spotRate").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("評分請填數字");
					e.printStackTrace();
				}

				Integer spotOwner = null;
				try {
					spotOwner = new Integer(req.getParameter("spotOwner").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("擁有者請填會員編號");
					e.printStackTrace();
				}

				String spotType = null;
				if (!(req.getParameter("spotType").trim().equals("0") | req.getParameter("spotType").trim().equals("1")
						| req.getParameter("spotType").trim().equals("2"))) {
					errorMsgs.add("沒有此種類型的景點");
				} else {
					spotType = req.getParameter("spotType").trim();
				}

				byte[] spotPhoto = null;
				try {
					Part spotPhotoPart = req.getPart("spotPhoto");
					InputStream in = spotPhotoPart.getInputStream();
					spotPhoto = new byte[in.available()];
					in.read(spotPhoto);
					in.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				SpotVO spotVO = new SpotVO();
				spotVO.setSpotName(spotName);
				spotVO.setSpotAddr(spotAddr);
				spotVO.setSpotType(spotType);
				spotVO.setSpotLng(spotLng);
				spotVO.setSpotLat(spotLat);
				spotVO.setSpotRate(spotRate);
				spotVO.setSpotOverview(spotOverview);
				spotVO.setSpotPhoto(spotPhoto);
				spotVO.setSpotOwner(spotOwner);
				spotVO.setSpotChk(spotChk);
				spotVO.setSpotHdl(spotHdl);
				spotVO.setSpotIsBlocked(spotIsBlocked);
				spotVO.setSpotBlockedReason(spotBlockedReason);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("spotVO", spotVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/query/addSpot.jsp");
					failureView.forward(req, res);
					return;
				}
				// 若無任何錯誤則送出新增
				SpotService spotSvc = new SpotService();
				spotVO = spotSvc.addSpot(spotName, spotAddr, spotType, spotLng, spotLat, spotRate, spotOverview,
						spotPhoto, spotOwner, spotChk, spotHdl, spotIsBlocked, spotBlockedReason);
				// 回到地點列表
				String url = "/frontend/query/listAllSpot.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/query/addSpot.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer spotNo = new Integer(req.getParameter("spotNo"));
				SpotService spotSvc = new SpotService();
				spotSvc.deleteSpot(spotNo);
				String url = "/frontend/query/listAllSpot.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/query/listAllSpot.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getSpotQuery".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String spotNo = req.getParameter("spotNo");
				
				
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");

				if (req.getParameter("whichPage") == null) {
					HashMap<String, String[]> map1 = (HashMap<String, String[]>) req.getParameterMap();
					HashMap<String, String[]> map2 = new HashMap<String, String[]>();
					map2 = (HashMap<String, String[]>) map1.clone();
					session.setAttribute("map", map2);
					map = (HashMap<String, String[]>) req.getParameterMap();
				}

				SpotService spotSvc = new SpotService();
				List<SpotVO> list = spotSvc.getAll(map);
				SpotVO spotVO=spotSvc.getOneSpot(new Integer(spotNo));

				req.setAttribute("listSpots_ByCompositeQuery", list);
				req.setAttribute("spotVO", spotVO);
				RequestDispatcher successView = req.getRequestDispatcher("/frontend/query/view_search.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
