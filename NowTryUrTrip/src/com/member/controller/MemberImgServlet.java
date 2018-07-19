package com.member.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.member.model.*;

public class MemberImgServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		byte[] b = null;
		try {
			MemberService memberSvc = new MemberService();
			String memId = req.getParameter("memId");
			MemberVO memberVO = memberSvc.getOneMember(Integer.valueOf(memId));
			b = memberVO.getMemImg();
			out.write(b);

		} catch (Exception e) {
			InputStream in = getServletContext().getResourceAsStream("/css/frontend/member/Login/avatar_2x.png");
			b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
		}
	}
}
