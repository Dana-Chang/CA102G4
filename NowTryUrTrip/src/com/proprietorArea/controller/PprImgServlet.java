package com.proprietorArea.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.proprietorArea.model.*;

public class PprImgServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/jpeg");
		ServletOutputStream out = res.getOutputStream();
		byte[] b = null;
		
		System.out.println("pprimgrun");
		
		try {
			PprService pprSvc = new PprService();
			String pprNo = req.getParameter("pprMsgeNo");
			PprVO pprVO = pprSvc.getOnePpr(Integer.valueOf(pprNo));
			b = pprVO.getPprMsgeImg();
			out.write(b);

		} catch (Exception e) {
			InputStream in = getServletContext().getResourceAsStream("/css/frontend/proprietorArea/Imgs/no_photo.png");
			b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
		}
	}
}
