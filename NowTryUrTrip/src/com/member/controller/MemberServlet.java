package com.member.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.storeInf.model.StoreInfVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 500 * 1024 * 1024, maxRequestSize = 5 * 500 * 1024
		* 1024)

public class MemberServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 ******************/
			try {
				String str = req.getParameter("memId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				Integer memId = null;
				try {
					memId = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始查詢資料 *****************************************/
				MemberService memSvc = new MemberService();
				MemberVO memberVO = memSvc.getOneMember(memId);
				if (memberVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				/************* 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("memberVO", memberVO); // 資料庫取出的empVO物件,存入req
				String url = "/frontend/member/listOneMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/******************** 1.接收請求參數 - 輸入格式的錯誤處理 ******************/
			try {
				String memName = req.getParameter("memName").trim();
				if (memName == null || (memName.trim()).length() == 0) {
					errorMsgs.add("請輸入會員姓名");
				}
				String memEmail = req.getParameter("memEmail").trim();
				if (memEmail == null || (memEmail.trim()).length() == 0) {
					errorMsgs.add("請輸入會員帳號");
				}
				String memPsw = req.getParameter("memPsw").trim();
				if (memPsw == null || (memPsw.trim()).length() == 0) {
					errorMsgs.add("請輸入會員密碼");
				}
				Integer memGender = null;
				try{
					memGender = new Integer(req.getParameter("memGender").trim());
				} catch (NumberFormatException e) {
					memGender = null;
					errorMsgs.add("請選擇性別");
				}
				
				
				String memType = "1";
				String memCell = req.getParameter("memCell").trim();
				String memTel = req.getParameter("memTel").trim();
				String memAdd = req.getParameter("memAdd").trim();
				Integer memStatus = null;
				Integer memBop = null;
				Integer memCb = null;
				Integer memTdp = null;
				String memDm = null;
				Integer memNomd = null;
				String memPf = null;
				Integer memPtnor = null;
				String memTaiv = null;
				Integer memBp = null;
				Integer memPorder = null;
				Integer memSp = null;
				Integer memOrderm = null;
				Integer memMtv = null;

				Part part = req.getPart("memImg");
				String fileType = part.getContentType();
				System.out.println(fileType);
				InputStream in = part.getInputStream();
				byte[] memImg = new byte[in.available()];
				in.read(memImg);
				in.close();

				MemberVO memberVO = new MemberVO();
				memberVO.setMemName(memName);
				memberVO.setMemEmail(memEmail);
				memberVO.setMemPsw(memPsw);
				memberVO.setMemGender(memGender);
				memberVO.setMemType(memType);
				memberVO.setMemStatus(1);
				memberVO.setMemCell(memCell);
				memberVO.setMemTel(memTel);
				memberVO.setMemAdd(memAdd);
				memberVO.setMemBop(0);
				memberVO.setMemCb(0);
				memberVO.setMemTdp(0);
				memberVO.setMemDm("0");
				memberVO.setMemNomd(0);
				memberVO.setMemPf("0");
				memberVO.setMemPtnor(0);
				memberVO.setMemTaiv("1");
				memberVO.setMemBp(0);
				memberVO.setMemPorder(0);
				memberVO.setMemSp(0);
				memberVO.setMemOrderm(0);
				memberVO.setMemMtv(0);
				memberVO.setMemImg(memImg);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO);
					req.setAttribute("check", "1");
					RequestDispatcher failureView = req.getRequestDispatcher("/index.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				MemberService memSvc = new MemberService();
				memberVO = memSvc.addMember(memName, memEmail, memPsw, memGender, memType, memStatus, memCell, memTel,
						memAdd, memBop, memCb, memTdp, memDm, memNomd, memPf, memPtnor, memTaiv, memBp, memPorder,
						memSp, memOrderm, memMtv, memImg);

				/********* 3.新增完成,準備轉交(Send the Success view) *********/
				String url = "/frontend/member/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/index.jsp");
				failureView.forward(req, res);
			}

		}
		
		if ("insertStoreInf".equals(action)) {
			/******************** 1.接收請求參數 - 輸入格式的錯誤處理 ******************/
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String memName = req.getParameter("memName").trim();
				String memEmail = req.getParameter("memEmail").trim();
				String memPsw = req.getParameter("memPsw").trim();
				Integer memGender = new Integer(req.getParameter("memGender").trim());
				String memType = "2";
				String memCell = req.getParameter("memCell").trim();
				String memTel = req.getParameter("memTel").trim();
				String memAdd = req.getParameter("memAdd").trim();
				Integer memStatus = 1;
				Integer memBop = 0;
				Integer memCb = 0;
				Integer memTdp = 0;
				String memDm = "0";
				Integer memNomd = 0;
				String memPf = "0";
				Integer memPtnor = 0;
				String memTaiv = "1";
				Integer memBp = 0;
				Integer memPorder = 0;
				Integer memSp = 0;
				Integer memOrderm = 0;
				Integer memMtv = 0;

				Part part = req.getPart("memImg");
				String fileType = part.getContentType();
				System.out.println(fileType);
				InputStream in = part.getInputStream();
				byte[] memImg = new byte[in.available()];
				in.read(memImg);
				in.close();
				
				

				MemberVO memberVO = new MemberVO();
				memberVO.setMemName(memName);
				memberVO.setMemEmail(memEmail);
				memberVO.setMemPsw(memPsw);
				memberVO.setMemGender(memGender);
				memberVO.setMemType(memType);
				memberVO.setMemStatus(memStatus);
				memberVO.setMemCell(memCell);
				memberVO.setMemTel(memTel);
				memberVO.setMemAdd(memAdd);
				memberVO.setMemBop(memBop);
				memberVO.setMemCb(memCb);
				memberVO.setMemTdp(memTdp);
				memberVO.setMemDm(memDm);
				memberVO.setMemNomd(memNomd);
				memberVO.setMemPf(memPf);
				memberVO.setMemPtnor(memPtnor);
				memberVO.setMemTaiv(memTaiv);
				memberVO.setMemBp(memBp);
				memberVO.setMemPorder(memPorder);
				memberVO.setMemSp(memSp);
				memberVO.setMemOrderm(memOrderm);
				memberVO.setMemMtv(memMtv);
				memberVO.setMemImg(memImg);
				
				Integer uniformNum  = new Integer(req.getParameter("uniformNum").trim());
				String principal = req.getParameter("principal").trim();
				String storCall = req.getParameter("storCall").trim();
				String storAdd = req.getParameter("storAdd").trim();
				Integer postalCode = new Integer(req.getParameter("postalCode").trim());
				Integer category = new Integer(req.getParameter("category").trim());
				
				StoreInfVO storeInfVO = new StoreInfVO();
				storeInfVO.setUniformNum(uniformNum);
				storeInfVO.setPrincipal(principal);
				storeInfVO.setStorCall(storCall);
				storeInfVO.setStorAdd(storAdd);
				storeInfVO.setPostalCode(postalCode);
				storeInfVO.setCategory(category);
				
				/***************************2.開始新增資料***************************************/
				MemberService memSvc = new MemberService();
				memSvc.insertWithStoreInf(memberVO, storeInfVO);
				/********* 3.新增完成,準備轉交(Send the Success view) *********/
				String url = "/frontend/member/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/member/StoreInf/addStoreInf.jsp");
				failureView.forward(req, res);
			}
		}
		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer memId = new Integer(req.getParameter("memId"));

				/*************************** 2.開始刪除資料 ***************************************/
				MemberService memSvc = new MemberService();
				memSvc.deleteMember(memId);

				/************* 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");
			req.setAttribute("requestURL", requestURL);

			String whichPage = req.getParameter("whichPage");
			req.setAttribute("whichPage", whichPage);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer memId = new Integer(req.getParameter("memId"));

				/*************************** 2.開始查詢資料 ****************************************/
				MemberService memSvc = new MemberService();
				MemberVO memberVO = memSvc.getOneMember(memId);

				/************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("memberVO", memberVO);
				String url = "/frontend/member/update_member_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");

			req.setAttribute("requestURL", requestURL);
			String whichPage = req.getParameter("whichPage");
			req.setAttribute("whichPage", whichPage);

			/**************** 1.接收請求參數 - 輸入格式的錯誤處理 **************/
			try {

				Integer memId = new Integer(req.getParameter("memId").trim());
				String memName = req.getParameter("memName").trim();
				String memEmail = req.getParameter("memEmail").trim();
				String memPsw = req.getParameter("memPsw").trim();
				Integer memGender = new Integer(req.getParameter("memGender").trim());
				String memType = req.getParameter("memType").trim();
				String memCell = req.getParameter("memCell").trim();
				String memTel = req.getParameter("memTel").trim();
				String memAdd = req.getParameter("memAdd").trim();
				Integer memStatus = null;
				Integer memBop = null;
				Integer memCb = null;
				Integer memTdp = null;
				String memDm = null;
				Integer memNomd = null;
				String memPf = null;
				Integer memPtnor = null;
				String memTaiv = null;
				Integer memBp = null;
				Integer memPorder = null;
				Integer memSp = null;
				Integer memOrderm = null;
				Integer memMtv = null;

				Part part = req.getPart("memImg");
				String fileType = part.getContentType();
				System.out.println(fileType);
				InputStream in = part.getInputStream();
				byte[] memImg = new byte[in.available()];
				if (getFileNameFromPart(part) != null) {
					memImg = new byte[in.available()];
					in.read(memImg);
				} else {
					MemberService memberSvc = new MemberService();
					MemberVO memberVO = memberSvc.getOneMember(memId);
					memImg = memberVO.getMemImg();
					if (memImg != null)
						in.read(memImg);
				}

				in.close();

				MemberVO memberVO = new MemberVO();
				memberVO.setMemId(memId);
				memberVO.setMemName(memName);
				memberVO.setMemEmail(memEmail);
				memberVO.setMemPsw(memPsw);
				memberVO.setMemGender(memGender);
				memberVO.setMemType(memType);
				memberVO.setMemStatus(1);
				memberVO.setMemCell(memCell);
				memberVO.setMemTel(memTel);
				memberVO.setMemAdd(memAdd);
				memberVO.setMemBop(0);
				memberVO.setMemCb(0);
				memberVO.setMemTdp(0);
				memberVO.setMemDm("0");
				memberVO.setMemNomd(0);
				memberVO.setMemPf("0");
				memberVO.setMemPtnor(0);
				memberVO.setMemTaiv("1");
				memberVO.setMemBp(0);
				memberVO.setMemPorder(0);
				memberVO.setMemSp(0);
				memberVO.setMemOrderm(0);
				memberVO.setMemMtv(0);
				memberVO.setMemImg(memImg);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/member/update_member_input.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始修改資料 *****************************************/
				MemberService memSvc = new MemberService();
				memberVO = memSvc.updateMember(memName, memEmail, memPsw, memGender, memType, memStatus, memCell,
						memTel, memAdd, memBop, memCb, memTdp, memDm, memNomd, memPf, memPtnor, memTaiv, memBp,
						memPorder, memSp, memOrderm, memMtv, memImg, memId);
				/************ 3.修改完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("memberVO", memberVO);
				String url = "/frontend/member/listOneMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/admin/updateManager.jsp");
				failureView.forward(req, res);
			}
		}
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		HttpSession session = req.getSession();
		String url = "";
		if ("login".equals(action)) {
			/************ 1.接收請求參數 - 輸入格式的錯誤處理 ***************/
			String memEmail = req.getParameter("memEmail").trim();
			String memPsw = req.getParameter("memPsw").trim();

			/*************************** 2.開始查詢資料 *****************************************/
			MemberService memberSvc = new MemberService();
			MemberVO memberVO = memberSvc.getOneMemberEmail(memEmail);
			if (memberVO == null) {
				errorMsgs.add("無此帳號!");
			} else if (memberVO.getMemStatus() == 2) {
				errorMsgs.add("帳號已停權,請重新註冊新帳號!");
			} else if (!memberVO.getMemPsw().equals(memPsw)) {
				errorMsgs.add("請確認密碼是否正確!");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/index.jsp");
				failureView.forward(req, res);
				return;
			}
			/********* 3.查詢完成,準備轉交(Send the Success view) ********/
			req.getSession().setAttribute("memberVO", memberVO);
			url = "/index.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if ("logout".equals(action)) {
			session = req.getSession();
			session.invalidate();
			url = "/index.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

	}

	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		// System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		// System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}
