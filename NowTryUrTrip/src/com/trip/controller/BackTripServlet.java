
package com.trip.controller;

import java.io.*;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.spotCmnt.model.SpotCmntService;
import com.spotCmnt.model.SpotCmntVO;
import com.trip.model.*;
import com.tripCmnt.model.TripCmntService;
import com.tripCmnt.model.TripCmntVO;

@WebServlet("/backend/tripMgt/BackTrip.do")
@MultipartConfig
public class BackTripServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		DecimalFormat df = new DecimalFormat("##.0");

		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 若沒有輸入查詢的行程編號
				String str = req.getParameter("tripNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入行程編號");
				}
				// 送出錯誤訊息
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/tripMgt/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				// 檢驗輸入行程編號的格式
				Integer tripNo = null;
				try {
					tripNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("行程編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/tripMgt/select_page.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/tripMgt/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				// 成功!!送出VO給顯示頁面
				req.setAttribute("tripVO", tripVO);
				String url = "/backend/tripMgt/listOneTrip.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
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
				} catch (Exception e) {
					tripAddTime = null;
				}

				java.sql.Timestamp tripRmvTime = null;
				try {
					tripRmvTime = new java.sql.Timestamp((sdf.parse(req.getParameter("tripRmvTime").trim())).getTime());
				} catch (Exception e) {
					tripRmvTime = null;
				}

				Double tripRate = 0.0;
				Integer tripCmntCount = 0;
				
				//從評論來計算想要放在排成
//				TripCmntService tripCmntSvc = new TripCmntService();
//				List<TripCmntVO> tripCmntVOList = tripCmntSvc.getAll();
//				for(TripCmntVO tripCmntVO:tripCmntVOList){
//					tripCmntCount++;
//					tripRate= tripRate+tripCmntVO.getTripRate();
//				}
//				
//				tripRate = Double.parseDouble(df.format(tripRate/tripCmntCount));
//				System.out.println(tripRate);
				
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
				if((tripAddTime!=null&&tripRmvTime!=null)){
					tripVO = tripSvc.addTrip(memId, tripName, departTime, tripType, tripAddTime, tripRmvTime, tripRate,
						tripIsPublic, tripPrice, tripAdImg, tripDesc, tripContent);
				}else{
					tripVO = tripSvc.addTripWithoutTime(memId, tripName, departTime, tripType, tripRate, tripIsPublic, tripPrice, tripAdImg, tripDesc, tripContent);
				}
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
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/tripMgt/trip_list.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
