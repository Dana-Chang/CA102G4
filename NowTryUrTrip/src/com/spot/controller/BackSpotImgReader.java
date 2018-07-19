package com.spot.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.spot.model.*;
public class BackSpotImgReader extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			String spotNo = req.getParameter("spotNo");
			SpotService spotSvc = new SpotService();
			SpotVO spotVO = spotSvc.getOneSpot(new Integer(spotNo));
			byte[] buf = spotVO.getSpotPhoto();
			out.write(buf);
		} catch (Exception e) {
			InputStream in = getServletContext().getResourceAsStream("/backend/items/nopic.gif");
			byte[] buf = new byte[in.available()];
			in.read(buf);
			out.write(buf);
			in.close();
		}
	}
}
