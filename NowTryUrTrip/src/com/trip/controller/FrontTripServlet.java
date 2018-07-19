
package com.trip.controller;

import java.io.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.trip.model.*;


@WebServlet("/frontend/tripPlan/frontTrip.do")
@MultipartConfig
public class FrontTripServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		System.out.println("123132132131");
		System.out.println(action);

		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 若沒有輸入查詢的行程編號
				String str = req.getParameter("tripNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入行程編號");
				}
//				// 送出錯誤訊息
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/tripPlan/trip_index.jsp");
					failureView.forward(req, res);
					return;
				}
//				// 檢驗輸入行程編號的格式
				Integer tripNo = null;
				try {
					tripNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("行程編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/tripPlan/trip_index.jsp");
					failureView.forward(req, res);
					return;
				}
				// 查詢行程的資料
				TripService tripSvc = new TripService();
				TripVO tripVO = tripSvc.getOneTrip(tripNo);
				if (tripVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/tripPlan/trip_index.jsp");
					failureView.forward(req, res);
					return;
				}

				// 成功!!送出VO給顯示頁面
				req.setAttribute("tripVO", tripVO);
				String url = "/frontend/tripPlan/list_one_trip.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				
//				Integer memId = tripVO.getMemId();
//				String tripName = tripVO.getTripName();
//				Timestamp departTime = tripVO.getDepartTime();
//				String tripType = tripVO.getTripType();
//				Timestamp tripRmvTime = tripVO.getTripRmvTime();
//				Integer tripRate = tripVO.getTripRate();
//				String tripIsPublic = tripVO.getTripIsPublic();
//				Integer tripPrice = tripVO.getTripPrice();
////				tripVO.getTripAdImg();
//				String tripDesc = tripVO.getTripDesc();
//				String tripContent = tripVO.getTripContent();
//				
//				res.setContentType("text/plain");
//				res.setCharacterEncoding("UTF-8");
//				PrintWriter out = res.getWriter();
//				
//				JSONObject obj = new JSONObject();
//				obj.put("tripNo", tripNo);
//				obj.put("memId", memId);
//				obj.put("tripName", tripName);
//				obj.put("cmnt", cmnt);
//				obj.put("rate", rate);
//				obj.put("cmntTime", cmntTime);
//				
//				out.write(obj.toString());
//				out.flush();
//				out.close();
				
				
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("請輸入正確格式:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/tripMgt/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer tripNo = new Integer(req.getParameter("tripNo"));

				TripService tripSvc = new TripService();
				TripVO tripVO = tripSvc.getOneTrip(tripNo);

				req.setAttribute("tripVO", tripVO);
				String url = "/backend/tripMgt/update_trip_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("行程編號格式輸入錯誤:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/tripMgt/trip_list.jsp.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer tripNo = new Integer(req.getParameter("tripNo").trim());
				Integer memId = new Integer(req.getParameter("memId").trim());
				String tripName = req.getParameter("tripName").trim();
				String tripType = req.getParameter("tripType").trim();
				Double tripRate = new Double(req.getParameter("tripRate").trim());
				String tripIsPublic = req.getParameter("tripIsPublic").trim();
				Integer tripPrice = new Integer(req.getParameter("tripPrice").trim());

				byte[] tripAdImg = null;
				if ((int) (req.getPart("tripAdImg").getSize()) == 0) {
					TripService tripSvc = new TripService();
					TripVO tripVOOrigin = tripSvc.getOneTrip(tripNo);
					tripAdImg = tripVOOrigin.getTripAdImg();
				} else {
					Part tripAdImgPart = req.getPart("tripAdImg");
					InputStream in = tripAdImgPart.getInputStream();
					tripAdImg = new byte[in.available()];
					in.read(tripAdImg);
					in.close();
				}
				String tripDesc = req.getParameter("tripDesc").trim();
				String tripContent = req.getParameter("tripContent").trim();

				java.sql.Timestamp departTime = null;
				java.sql.Timestamp tripAddTime = null;
				java.sql.Timestamp tripRmvTime = null;
				try {
					departTime = new java.sql.Timestamp(sdf.parse(req.getParameter("departTime").trim()).getTime());
					tripAddTime = new java.sql.Timestamp(sdf.parse(req.getParameter("tripAddTime").trim()).getTime());
					tripRmvTime = new java.sql.Timestamp(sdf.parse(req.getParameter("tripRmvTime").trim()).getTime());
				} catch (ParseException e) {
					departTime = null;
					tripAddTime = null;
					tripRmvTime = null;
				}
				TripVO tripVO = new TripVO();
				tripVO.setTripNo(tripNo);
				tripVO.setMemId(memId);
				tripVO.setTripName(tripName);
				tripVO.setDepartTime(departTime);
				tripVO.setTripType(tripType);
				tripVO.setTripAddTime(tripAddTime);
				tripVO.setTripRmvTime(tripRmvTime);
				tripVO.setTripRate(tripRate);
				tripVO.setTripIsPublic(tripIsPublic);
				tripVO.setTripPrice(tripPrice);
				
				tripVO.setTripDesc(tripDesc);
				tripVO.setTripContent(tripContent);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("tripVO", tripVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/tripMgt/update_trip_input.jsp");
					failureView.forward(req, res);
					return;
				}

				TripService tripSvc = new TripService();
				tripVO = tripSvc.updateTrip(tripNo, memId, tripName, departTime, tripType, tripAddTime, tripRmvTime,
						tripRate, tripIsPublic, tripPrice, tripAdImg, tripDesc, tripContent);

				req.setAttribute("tripVO", tripVO);
				String url = "/backend/tripMgt/listOneTrip.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {

				e.printStackTrace();
				errorMsgs.add("格式有誤:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/tripMgt/update_trip_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer memId = new Integer(req.getParameter("memId").trim());
				String tripName = req.getParameter("tripName").trim();
				java.sql.Timestamp departTime = null;
				try {
					departTime = new java.sql.Timestamp((sdf.parse(req.getParameter("departTime").trim())).getTime());
				} catch (IllegalArgumentException e) {
					departTime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("時間格式不正確，已將出發時間設為目前時間");
				}

				String tripType = null;
				try {
					tripType = req.getParameter("tripType").trim();
				} catch (NumberFormatException e) {
					tripType = "1";
					errorMsgs.add("請輸入行程類型，0為一般會員，1為店家行程");
				}

				java.sql.Timestamp tripAddTime = null;
				try {
					tripAddTime = new java.sql.Timestamp((sdf.parse(req.getParameter("tripAddTime").trim())).getTime());
				} catch (IllegalArgumentException e) {
					tripAddTime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("時間格式不正確，已將時間設為目前時間");
				}

				java.sql.Timestamp tripRmvTime = null;
				try {
					tripRmvTime = new java.sql.Timestamp((sdf.parse(req.getParameter("tripRmvTime").trim())).getTime());
				} catch (IllegalArgumentException e) {
					tripRmvTime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("時間格式不正確");
				}

				Double tripRate = null;
				try {
					tripRate = new Double(req.getParameter("tripRate").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("請輸入行程評分");
				}

				String tripIsPublic = null;
				try {
					tripIsPublic = req.getParameter("tripIsPublic").trim();
				} catch (NumberFormatException e) {
					errorMsgs.add("請輸入是否公開，0為私人，1為公開");
				}

				Integer tripPrice = null;
				try {
					tripPrice = new Integer(req.getParameter("tripPrice").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("請輸入行程價格");
				}

				byte[] tripAdImg = null;
				try {
					Part tripAdImgPart = req.getPart("tripAdImg");
					InputStream in = tripAdImgPart.getInputStream();
					tripAdImg = new byte[in.available()];
					in.read(tripAdImg);
					in.close();
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}

				String tripDesc = null;
				try {
					tripDesc = req.getParameter("tripDesc").trim();
				} catch (NumberFormatException e) {
					errorMsgs.add("請輸入行程描述");
				}

				String tripContent = null;
				try {
					tripContent = req.getParameter("tripContent").trim();
				} catch (NumberFormatException e) {
					errorMsgs.add("請輸入行程內容");
				}

				TripVO tripVO = new TripVO();
				tripVO.setMemId(memId);
				tripVO.setTripName(tripName);
				tripVO.setDepartTime(departTime);
				tripVO.setTripType(tripType);
				tripVO.setTripAddTime(tripAddTime);
				tripVO.setTripRmvTime(tripRmvTime);
				tripVO.setTripRate(tripRate);
				tripVO.setTripIsPublic(tripIsPublic);
				tripVO.setTripPrice(tripPrice);
				tripVO.setTripAdImg(tripAdImg);
				tripVO.setTripDesc(tripDesc);
				tripVO.setTripContent(tripContent);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("tripVO", tripVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/tripMgt/addTrip.jsp");
					failureView.forward(req, res);
					return;
				}
				// 若無任何錯誤則送出新增
				TripService tripSvc = new TripService();
				tripVO = tripSvc.addTrip(memId, tripName, departTime, tripType, tripAddTime, tripRmvTime, tripRate,
						tripIsPublic, tripPrice, tripAdImg, tripDesc, tripContent);
				// 回到行程列表
				String url = "/backend/tripMgt/trip_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/tripMgt/trip_list.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer tripNo = new Integer(req.getParameter("tripNo"));
				TripService tripSvc = new TripService();
				tripSvc.deleteTrip(tripNo);
				// 回到行程列表
				String url = "/backend/tripMgt/trip_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("刪除失敗?:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/tripMgt/trip_list.jsp.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("get_composite_query".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				//Map<String, String[]> map = req.getParameterMap();
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				if (req.getParameter("whichPage") == null){
					HashMap<String, String[]> map1 = (HashMap<String, String[]>)req.getParameterMap();
					HashMap<String, String[]> map2 = new HashMap<String, String[]>();
					map2 = (HashMap<String, String[]>)map1.clone();
					session.setAttribute("map",map2);
					map = (HashMap<String, String[]>)req.getParameterMap();
				} 
				
				/***************************2.開始複合查詢***************************************/
				TripService tripSvc = new TripService();
				List<TripVO> list  = tripSvc.getAll(map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listTrips_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/frontend/tripPlan/listTrips_ByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/trip_index.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
