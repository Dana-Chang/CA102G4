package com.spotPhoto.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.trip.model.*;
@WebServlet("/backend/spotMgt/backSpotImgReader.do")
public class BackSpotPhotoReader extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException  {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			String tripNo = req.getParameter("tripNo");
			TripService tripSvc = new TripService();
			TripVO tripVO = tripSvc.getOneTrip(new Integer(tripNo));
			byte[] buf = tripVO.getTripAdImg();
			out.write(buf);
		} catch (Exception e) {
			InputStream in = getServletContext().getResourceAsStream("/backend/tripMgt/images/pic404.jpg");
			byte[] buf = new byte[in.available()];
			in.read(buf);
			out.write(buf);
			in.close();
		}
	}
}
