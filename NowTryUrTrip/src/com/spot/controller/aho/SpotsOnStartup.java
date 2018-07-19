package com.spot.controller.aho;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import com.spot.model.*;
import com.trip.model.*;
import tools.LocDistrict;

@WebServlet(value="/SpotsOnStartup.do", loadOnStartup=1)
public class SpotsOnStartup extends HttpServlet{
		
		List<SpotVO> spots;
		List<TripVO> trips;
		List<LocDistrict> locs = new ArrayList<LocDistrict>();
	
	public void init(){
		spots = new SpotService().getAll();
		trips = new TripService().getAll();
		locs.add(new LocDistrict(0,Pattern.compile("基隆")));
		locs.add(new LocDistrict(0,Pattern.compile("[台臺]北")));
		locs.add(new LocDistrict(0,Pattern.compile("新北")));
		locs.add(new LocDistrict(0,Pattern.compile("桃園")));
		locs.add(new LocDistrict(0,Pattern.compile("新竹")));
		locs.add(new LocDistrict(0,Pattern.compile("苗栗")));
		locs.add(new LocDistrict(1,Pattern.compile("[台臺]中")));
		locs.add(new LocDistrict(1,Pattern.compile("彰化")));
		locs.add(new LocDistrict(1,Pattern.compile("雲林")));
		locs.add(new LocDistrict(1,Pattern.compile("南投")));
		locs.add(new LocDistrict(2,Pattern.compile("嘉義")));
		locs.add(new LocDistrict(2,Pattern.compile("[台臺]南")));
		locs.add(new LocDistrict(2,Pattern.compile("高雄")));
		locs.add(new LocDistrict(2,Pattern.compile("屏東")));
		locs.add(new LocDistrict(3,Pattern.compile("宜蘭")));
		locs.add(new LocDistrict(3,Pattern.compile("花蓮")));
		locs.add(new LocDistrict(3,Pattern.compile("[台臺]東")));
		locs.add(new LocDistrict(4,Pattern.compile("澎湖")));
		locs.add(new LocDistrict(4,Pattern.compile("金門")));
		locs.add(new LocDistrict(4,Pattern.compile("馬祖")));
		
		ServletContext context = getServletContext();
		context.setAttribute("spots", spots);
		context.setAttribute("trips", trips);
		context.setAttribute("locs", locs);

//		測試是否有塞進去
//		System.out.println("the amount of spots is :"+((List<SpotVO>) context.getAttribute("spots")).size());
	}
	
	public void destroy(){
		spots = null;
		trips = null;
	}

}
