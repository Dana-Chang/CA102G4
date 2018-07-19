
package com.spotPhoto.controller;

import java.io.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import com.spot.model.SpotService;
import com.spot.model.SpotVO;
import com.spotPhoto.model.*;

@WebServlet("/frontend/query/frontSpotPhoto.do")
@MultipartConfig
public class FrontSpotPhotoServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		System.out.println(action);

//		if ("getOne_For_Display".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				String str = req.getParameter("spotPhotoNo");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("請輸入景點照片編號");
//				}
//				// 送出錯誤訊息
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/query/select_page.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				// 檢驗輸入景點照片編號的格式
//				Integer tripNo = null;
//				try {
//					tripNo = new Integer(str);
//				} catch (Exception e) {
//					errorMsgs.add("景點照片編號格式不正確");
//				}
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/query/select_page.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				// 查詢景點照片的資料
//				TripService tripSvc = new TripService();
//				TripVO tripVO = tripSvc.getOneTrip(tripNo);
//				if (tripVO == null) {
//					errorMsgs.add("查無資料");
//				}
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/query/select_page.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//
//				// 成功!!送出VO給顯示頁面
//				req.setAttribute("tripVO", tripVO);
//				String url = "/backend/tripMgt/listOneTrip.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//			} catch (Exception e) {
//				e.printStackTrace();
//				errorMsgs.add("請輸入正確格式:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/query/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}
//
//		if ("getOne_For_Update".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				Integer tripNo = new Integer(req.getParameter("tripNo"));
//
//				TripService tripSvc = new TripService();
//				TripVO tripVO = tripSvc.getOneTrip(tripNo);
//
//				req.setAttribute("tripVO", tripVO);
//				String url = "/frontend/query/update_trip_input.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//
//			} catch (Exception e) {
//				e.printStackTrace();
//				errorMsgs.add("景點照片編號格式輸入錯誤:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/query/trip_list.jsp.jsp");
//				failureView.forward(req, res);
//			}
//		}
//
//		if ("update".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				Integer tripNo = new Integer(req.getParameter("tripNo").trim());
//				Integer memId = new Integer(req.getParameter("memId").trim());
//				String tripName = req.getParameter("tripName").trim();
//				String tripType = req.getParameter("tripType").trim();
//				Integer tripRate = new Integer(req.getParameter("tripRate").trim());
//				String tripIsPublic = req.getParameter("tripIsPublic").trim();
//				Integer tripPrice = new Integer(req.getParameter("tripPrice").trim());
//
//				byte[] tripAdImg = null;
//				if ((int) (req.getPart("tripAdImg").getSize()) == 0) {
//					TripService tripSvc = new TripService();
//					TripVO tripVOOrigin = tripSvc.getOneTrip(tripNo);
//					tripAdImg = tripVOOrigin.getTripAdImg();
//				} else {
//					Part tripAdImgPart = req.getPart("tripAdImg");
//					InputStream in = tripAdImgPart.getInputStream();
//					tripAdImg = new byte[in.available()];
//					in.read(tripAdImg);
//					in.close();
//				}
//				String tripDesc = req.getParameter("tripDesc").trim();
//				String tripContent = req.getParameter("tripContent").trim();
//
//				java.sql.Timestamp departTime = null;
//				java.sql.Timestamp tripAddTime = null;
//				java.sql.Timestamp tripRmvTime = null;
//				try {
//					departTime = new java.sql.Timestamp(sdf.parse(req.getParameter("departTime").trim()).getTime());
//					tripAddTime = new java.sql.Timestamp(sdf.parse(req.getParameter("tripAddTime").trim()).getTime());
//					tripRmvTime = new java.sql.Timestamp(sdf.parse(req.getParameter("tripRmvTime").trim()).getTime());
//				} catch (ParseException e) {
//					departTime = null;
//					tripAddTime = null;
//					tripRmvTime = null;
//				}
//				TripVO tripVO = new TripVO();
//				tripVO.setTripNo(tripNo);
//				tripVO.setMemId(memId);
//				tripVO.setTripName(tripName);
//				tripVO.setDepartTime(departTime);
//				tripVO.setTripType(tripType);
//				tripVO.setTripAddTime(tripAddTime);
//				tripVO.setTripRmvTime(tripRmvTime);
//				tripVO.setTripRate(tripRate);
//				tripVO.setTripIsPublic(tripIsPublic);
//				tripVO.setTripPrice(tripPrice);
//				
//				tripVO.setTripDesc(tripDesc);
//				tripVO.setTripContent(tripContent);
//
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("tripVO", tripVO);
//					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/query/update_trip_input.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//
//				TripService tripSvc = new TripService();
//				tripVO = tripSvc.updateTrip(tripNo, memId, tripName, departTime, tripType, tripAddTime, tripRmvTime,
//						tripRate, tripIsPublic, tripPrice, tripAdImg, tripDesc, tripContent);
//
//				req.setAttribute("tripVO", tripVO);
//				String url = "/backend/tripMgt/listOneTrip.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//
//			} catch (Exception e) {
//
//				e.printStackTrace();
//				errorMsgs.add("格式有誤:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/query/update_trip_input.jsp");
//				failureView.forward(req, res);
//			}
//		}
//
//		if ("insert".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				Integer memId = new Integer(req.getParameter("memId").trim());
//				String tripName = req.getParameter("tripName").trim();
//				java.sql.Timestamp departTime = null;
//				try {
//					departTime = new java.sql.Timestamp((sdf.parse(req.getParameter("departTime").trim())).getTime());
//				} catch (IllegalArgumentException e) {
//					departTime = new java.sql.Timestamp(System.currentTimeMillis());
//					errorMsgs.add("時間格式不正確，已將出發時間設為目前時間");
//				}
//
//				String tripType = null;
//				try {
//					tripType = req.getParameter("tripType").trim();
//				} catch (NumberFormatException e) {
//					tripType = "1";
//					errorMsgs.add("請輸入景點照片類型，0為一般會員，1為店家景點照片");
//				}
//
//				java.sql.Timestamp tripAddTime = null;
//				try {
//					tripAddTime = new java.sql.Timestamp((sdf.parse(req.getParameter("tripAddTime").trim())).getTime());
//				} catch (IllegalArgumentException e) {
//					tripAddTime = new java.sql.Timestamp(System.currentTimeMillis());
//					errorMsgs.add("時間格式不正確，已將時間設為目前時間");
//				}
//
//				java.sql.Timestamp tripRmvTime = null;
//				try {
//					tripRmvTime = new java.sql.Timestamp((sdf.parse(req.getParameter("tripRmvTime").trim())).getTime());
//				} catch (IllegalArgumentException e) {
//					tripRmvTime = new java.sql.Timestamp(System.currentTimeMillis());
//					errorMsgs.add("時間格式不正確");
//				}
//
//				Integer tripRate = null;
//				try {
//					tripRate = new Integer(req.getParameter("tripRate").trim());
//				} catch (NumberFormatException e) {
//					errorMsgs.add("請輸入景點照片評分");
//				}
//
//				String tripIsPublic = null;
//				try {
//					tripIsPublic = req.getParameter("tripIsPublic").trim();
//				} catch (NumberFormatException e) {
//					errorMsgs.add("請輸入是否公開，0為私人，1為公開");
//				}
//
//				Integer tripPrice = null;
//				try {
//					tripPrice = new Integer(req.getParameter("tripPrice").trim());
//				} catch (NumberFormatException e) {
//					errorMsgs.add("請輸入景點照片價格");
//				}
//
//				byte[] tripAdImg = null;
//				try {
//					Part tripAdImgPart = req.getPart("tripAdImg");
//					InputStream in = tripAdImgPart.getInputStream();
//					tripAdImg = new byte[in.available()];
//					in.read(tripAdImg);
//					in.close();
//				} catch (NumberFormatException e) {
//					e.printStackTrace();
//				}
//
//				String tripDesc = null;
//				try {
//					tripDesc = req.getParameter("tripDesc").trim();
//				} catch (NumberFormatException e) {
//					errorMsgs.add("請輸入景點照片描述");
//				}
//
//				String tripContent = null;
//				try {
//					tripContent = req.getParameter("tripContent").trim();
//				} catch (NumberFormatException e) {
//					errorMsgs.add("請輸入景點照片內容");
//				}
//
//				TripVO tripVO = new TripVO();
//				tripVO.setMemId(memId);
//				tripVO.setTripName(tripName);
//				tripVO.setDepartTime(departTime);
//				tripVO.setTripType(tripType);
//				tripVO.setTripAddTime(tripAddTime);
//				tripVO.setTripRmvTime(tripRmvTime);
//				tripVO.setTripRate(tripRate);
//				tripVO.setTripIsPublic(tripIsPublic);
//				tripVO.setTripPrice(tripPrice);
//				tripVO.setTripAdImg(tripAdImg);
//				tripVO.setTripDesc(tripDesc);
//				tripVO.setTripContent(tripContent);
//
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("tripVO", tripVO);
//					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/query/addTrip.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				// 若無任何錯誤則送出新增
//				TripService tripSvc = new TripService();
//				tripVO = tripSvc.addTrip(memId, tripName, departTime, tripType, tripAddTime, tripRmvTime, tripRate,
//						tripIsPublic, tripPrice, tripAdImg, tripDesc, tripContent);
//				// 回到景點照片列表
//				String url = "/backend/tripMgt/trip_list.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//
//			} catch (Exception e) {
//				e.printStackTrace();
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/query/trip_list.jsp");
//				failureView.forward(req, res);
//			}
//		}
		
		if ("upload_photos".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			try {
				Integer spotNo = null;
				Integer memId = null;
				String photoTitle = null;
				try {
					spotNo = new Integer(req.getParameter("spotNo").trim());
					memId = new Integer(req.getParameter("memId").trim());
					photoTitle = req.getParameter("photoTitle").trim();
				} catch (NullPointerException e) {
					errorMsgs.add("值為空");
					e.printStackTrace();
				}
//				ServletRequestContext src = new ServletRequestContext(req); 
//				List<File> files = new ArrayList<File>();
//				DiskFileItemFactory factory = new DiskFileItemFactory();
//				ServletFileUpload upload = new ServletFileUpload(factory);
//				List<FileItem> list = upload.parseRequest(src);
//				System.out.println(list.size());
				byte[] spotPhoto = null;
				SpotPhotoVO spotPhotoVO = null;
				Part img = req.getPart("img");
				SpotPhotoService spotPhotoSvc = new SpotPhotoService();
				InputStream in = img.getInputStream();
				spotPhoto = new byte[in.available()];
				in.read(spotPhoto);
				in.close();
				spotPhotoVO = new SpotPhotoVO();
				spotPhotoVO.setMemId(memId);
				spotPhotoVO.setSpotNo(spotNo);
				spotPhotoVO.setSpotPhoto(spotPhoto);
				spotPhotoVO.setPhotoTitle(photoTitle);
				spotPhotoSvc.addSpotPhoto(spotNo, memId, spotPhoto, photoTitle);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("spotPhotoVO", spotPhotoVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/query/view_search.jsp");
					failureView.forward(req, res);
					return;
				}
				// 若無任何錯誤則送出新增
				// 回到景點照片列表
				SpotService spotSvc = new SpotService();
				SpotVO spotVO = spotSvc.getOneSpot(spotNo);
				req.setAttribute("spotVO", spotVO);
				String url = "/frontend/query/view_search.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/query/view_search.jsp");
				failureView.forward(req, res);
			}
		}

//		if ("delete".equals(action)) {
//
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				Integer tripNo = new Integer(req.getParameter("tripNo"));
//				TripService tripSvc = new TripService();
//				tripSvc.deleteTrip(tripNo);
//				// 回到景點照片列表
//				String url = "/frontend/query/trip_list.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//
//			} catch (Exception e) {
//				errorMsgs.add("刪除失敗?:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/query/trip_list.jsp.jsp");
//				failureView.forward(req, res);
//			}
//		}
	}
}
