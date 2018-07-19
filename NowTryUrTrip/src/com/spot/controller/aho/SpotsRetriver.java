package com.spot.controller.aho;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONArray;
import org.json.JSONObject;

import com.spot.model.SpotVO;
import com.trip.model.TripService;
import com.trip.model.TripVO;

import tools.LocDistrict;

@WebServlet("/SpotsRetriver.do")
public class SpotsRetriver extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		res.setContentType("text/plain");
		res.setCharacterEncoding("UTF-8");

		List<SpotVO> spots = (List<SpotVO>) getServletContext().getAttribute("spots");
		
		System.out.println("spots size is:"+spots.size());
		
		List<SpotVO> spotsPicked = new ArrayList<SpotVO>();
		
		System.out.println("spotsPicked init size is:"+spotsPicked.size());

		List<LocDistrict> locs = (List<LocDistrict>) getServletContext().getAttribute("locs");
		List<LocDistrict> locsPicked = new ArrayList<LocDistrict>();
		//for spotQuery
		String spotRate = req.getParameter("spotRate");
		String spotLoc = req.getParameter("spotLoc");
		String spotType = req.getParameter("spotType");
		String keyword = req.getParameter("keyword");
		Pattern keywordP = null;
		//for spotQueryByNo
		String spotNo = req.getParameter("spotNo");
		//for comm use
		PrintWriter out = res.getWriter();
		JSONArray spotsJson = new JSONArray();
		JSONObject spotJson = null;
		
		if ("spotQuery".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				//建立pattern
				if (keyword == null || (keyword.trim()).length() == 0) {
					keywordP = Pattern.compile("");
				} else if (keyword != null || (keyword.trim()).length() != 0) {
					keyword = new String(keyword.getBytes("ISO-8859-1"), "UTF-8"); //找店名keyword
					keywordP = Pattern.compile(keyword.toUpperCase());
					 System.out.println("keywordP is:"+keywordP.pattern());
				} else {}
				
				//以下列出符合查詢條件的spots
				if (spotRate == null || (spotRate.trim()).length() == 0) { //TODO isblocked的應該是顯示不出來…
				} else if (spotRate != null || (spotRate.trim()).length() != 0) {
					for (SpotVO spot : spots) {
						if (spot.getSpotRate() >= Double.parseDouble(spotRate) && (regexChecker(keywordP, spot.getSpotName().toUpperCase())||regexChecker(keywordP, spot.getSpotAddr().toUpperCase())))
							spotsPicked.add(spot);
					}
					 System.out.println("spotsPicked by spotRate size is:"+spotsPicked.size());
				} else {}
				
				if (spotLoc == null || (spotLoc.trim()).length() == 0) {
				} else if (spotLoc != null || (spotLoc.trim()).length() != 0) {
					Integer spotLocN = Integer.parseInt(spotLoc);
//					System.out.println("spotsPicked in spotLoc size is:"+spotsPicked.size());

					//取得所有LocDistrict，內含compile過的中文正則表示式
					for (LocDistrict loc : locs) {
						if (loc.getLocNum() == spotLocN)
							locsPicked.add(loc);
					}
					for (SpotVO spot : spots) {
						for (LocDistrict loc : locsPicked) {
								if (loc.getDistName().matcher(spot.getSpotAddr()).find() && (regexChecker(keywordP, spot.getSpotName().toUpperCase())||regexChecker(keywordP, spot.getSpotAddr().toUpperCase()))){
									spotsPicked.add(spot);
//									System.out.println(loc.getDistName().toString());
									break;
								}
						}
					}
//					 System.out.println("spotsPicked by spotLoc size is:"+spotsPicked.size());
				} else {}
				
				if (spotType == null || (spotType.trim()).length() == 0) {
				} else if (spotType != null || (spotType.trim()).length() != 0) {
					for (SpotVO spot : spots) {
						if (spot.getSpotType().equals(spotType) && (regexChecker(keywordP, spot.getSpotName().toUpperCase())||regexChecker(keywordP, spot.getSpotAddr().toUpperCase())))
							spotsPicked.add(spot);
					}
					 System.out.println("spotsPicked by spotType size is:"+spotsPicked.size());
				} else {}
							
				if(spotsPicked.size()==0){
					out.write("context沒有符合地點！");
				} else{
					for(SpotVO spot : spotsPicked){
						// 因為在browser端不吃byte陣列，所以在送出前先將圖片轉成base64
						String spotPhoto;
						if(spot.getSpotPhoto() == null){
							spotPhoto = "";
						} else{
							spotPhoto = Base64.getEncoder().encodeToString(spot.getSpotPhoto());
						}
						spotJson = new JSONObject(spot);
						spotJson.put("spotPhoto", spotPhoto);
						spotsJson.put(spotJson);
//						System.out.println("spotPhoto is :" + spotPhoto);
//						System.out.println("s.get(\"spotPhoto\")"+ s.get("spotPhoto"));
					}
//					System.out.println("spotsJson.toString() is:"+spotsJson.toString());
					out.write(spotsJson.toString());
				}
				
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
				// RequestDispatcher failureView =
				// req.getRequestDispatcher("/backend/tripMgt/select_page.jsp");
				// failureView.forward(req, res);
			}
		}
		
		
		if ("spotQueryByNo".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				//以下列出符合查詢條件的spots
				if (spotNo == null || (spotNo.trim()).length() == 0) { //TODO isblocked的應該是顯示不出來…
					out.write("未傳spotNo");
				} else if (!spotNo.equals("myLoc")  && 
						(spotNo != null || (spotNo.trim()).length() != 0)
						) {
					for (SpotVO spot : spots) {
						System.out.println("!spotNo.equals(\"myLoc\") is :" +
								!spotNo.equals("myLoc"));
						System.out.println("(spotNo != null || (spotNo.trim()).length() != 0) is :" +
								(spotNo != null || (spotNo.trim()).length() != 0)
								);
						if (spot.getSpotNo() == Integer.parseInt(spotNo)) spotsPicked.add(spot);
					}
					 System.out.println("spotsPicked by spotRate size is:"+spotsPicked.size());
				} else if (spotNo == "myLoc"){
					
				} else {}
								
				if(spotsPicked.size()==0){
					out.write("context沒有符合地點！");
				} else{
					for(SpotVO spot : spotsPicked){
						// 因為在browser端不吃byte陣列，所以在送出前先將圖片轉成base64
						String spotPhoto;
						if(spot.getSpotPhoto() == null){
							spotPhoto = "";
						} else{
							spotPhoto = Base64.getEncoder().encodeToString(spot.getSpotPhoto());
						}
						spotJson = new JSONObject(spot);
						spotJson.put("spotPhoto", spotPhoto);
						spotsJson.put(spotJson);
//						System.out.println("spotPhoto is :" + spotPhoto);
//						System.out.println("s.get(\"spotPhoto\")"+ s.get("spotPhoto"));
					}
//					System.out.println("spotsJson.toString() is:"+spotsJson.toString());
					out.write(spotsJson.toString());
				}
				
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
				// errorMsgs.add("請輸入正確格式:" + e.getMessage());
				// RequestDispatcher failureView =
				// req.getRequestDispatcher("/backend/tripMgt/select_page.jsp");
				// failureView.forward(req, res);
			}
		}
	}

	private boolean regexChecker(Pattern keyword, String spotName){
		if("".equals(keyword.pattern())) {
			System.out.println("keyword.pattern()  is:" + keyword.pattern());
			return true;
		}
		System.out.println("keyword.pattern() is:" + keyword.pattern());
		return keyword.matcher(spotName).find();
	}

}
