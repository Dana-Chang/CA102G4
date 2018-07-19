package com.trip.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.trip.model.*;
//@WebServlet("/DBImgReader.do")
public class FrontTripImgReader extends HttpServlet {

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
			InputStream in = getServletContext().getResourceAsStream("/frontend/items/nopic.gif");
			byte[] buf = new byte[in.available()];
			in.read(buf);
			out.write(buf);
			in.close();
		}
	}
}
