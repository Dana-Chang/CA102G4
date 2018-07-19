package com.tripCmntRpt.model;	

import java.sql.Timestamp;
import java.util.List;

public class TripCmntRptService {

	private TripCmntRptDAO_interface dao;

	public TripCmntRptService() {
		dao = new TripCmntRptJDBCDAO();
	}

	public TripCmntRptVO addTripCmntRpt(Integer memId, Integer tripCmntNo, String rptReason, Timestamp rptTime) {

		TripCmntRptVO tripCmntRptVO = new TripCmntRptVO();

		tripCmntRptVO.setMemId(memId);
		tripCmntRptVO.setTripCmntNo(tripCmntNo);
		tripCmntRptVO.setRptReason(rptReason);
		tripCmntRptVO.setRptTime(rptTime);

		dao.insert(tripCmntRptVO);
		return tripCmntRptVO;
	}

	public TripCmntRptVO updateTripCmntRpt(Integer tripCmntRptNo, Integer memId, Integer tripCmntNo, String rptReason, Timestamp rptTime) {

		TripCmntRptVO tripCmntRptVO = new TripCmntRptVO();

		tripCmntRptVO.setTripCmntRptNo(tripCmntRptNo);
		tripCmntRptVO.setMemId(memId);
		tripCmntRptVO.setTripCmntNo(tripCmntNo);
		tripCmntRptVO.setRptReason(rptReason);
		tripCmntRptVO.setRptTime(rptTime);

		dao.update(tripCmntRptVO);
		return tripCmntRptVO;
	}

	public void deleteTripCmntRpt(Integer tripCmntRptNo) {
		dao.delete(tripCmntRptNo);
	}

	public TripCmntRptVO getOneTripCmntRpt(Integer tripCmntRptNo) {
		return dao.findByPrimaryKey(tripCmntRptNo);
	}

	public List<TripCmntRptVO> getAll() {
		return dao.getAll();
	}
	
	public List<TripCmntRptVO> getAllSorted() {
		return dao.getAllSorted();
	}
}
