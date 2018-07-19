package com.spotPhoto.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.spotPhoto.model.SpotPhotoService;
import com.spotPhoto.model.SpotPhotoVO;
import com.trip.model.*;
@WebServlet("/frontSpotPhotoReader.do")
public class FrontSpotPhotoReader extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException  {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			String spotPhotoNo = req.getParameter("spotPhotoNo");
			SpotPhotoService spotPhotoSvc = new SpotPhotoService();
			SpotPhotoVO spotPhotoVO = spotPhotoSvc.getOneSpotPhoto(new Integer(spotPhotoNo));
			byte[] buf = spotPhotoVO.getSpotPhoto();
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
