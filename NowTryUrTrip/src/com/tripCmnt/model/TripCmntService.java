package com.tripCmnt.model;

import java.sql.Timestamp;
import java.util.List;

public class TripCmntService {

	private TripCmntDAO_interface dao;

	public TripCmntService() {
		dao = new TripCmntJDBCDAO();
	}

	public TripCmntVO addTripCmnt(Integer memId, Integer tripNo, String tripCmntContent, Timestamp tripCmntTime,Integer tripRate, String tripCmntIsHdl,String tripCmntIsBlocked,String tripCmntBLockedReason) {

		TripCmntVO tripCmntVO = new TripCmntVO();

		tripCmntVO.setMemId(memId);
		tripCmntVO.setTripNo(tripNo);
		tripCmntVO.setTripCmntContent(tripCmntContent);
		tripCmntVO.setTripCmntTime(tripCmntTime);
		tripCmntVO.setTripRate(tripRate);
		tripCmntVO.setTripCmntIsHdl(tripCmntIsHdl);
		tripCmntVO.setTripCmntIsBlocked(tripCmntIsBlocked);
		tripCmntVO.setTripCmntBlockedReason(tripCmntBLockedReason);

		dao.insert(tripCmntVO);
		return tripCmntVO;
	}

	public TripCmntVO updateTripCmnt(Integer tripCmntNo , Integer memId, Integer tripNo, String tripCmntContent, Timestamp tripCmntTime,Integer tripRate, String tripCmntIsHdl,String tripCmntIsBlocked,String tripCmntBLockedReason) {

		TripCmntVO tripCmntVO = new TripCmntVO();

		tripCmntVO.setTripCmntNo(tripCmntNo);
		tripCmntVO.setMemId(memId);
		tripCmntVO.setTripNo(tripNo);
		tripCmntVO.setTripCmntContent(tripCmntContent);
		tripCmntVO.setTripCmntTime(tripCmntTime);
		tripCmntVO.setTripRate(tripRate);
		tripCmntVO.setTripCmntIsHdl(tripCmntIsHdl);
		tripCmntVO.setTripCmntIsBlocked(tripCmntIsBlocked);
		tripCmntVO.setTripCmntBlockedReason(tripCmntBLockedReason);

		dao.update(tripCmntVO);
		return tripCmntVO;
	}

	public void deleteTripCmnt(Integer tripCmntNo) {
		dao.delete(tripCmntNo);
	}

	public TripCmntVO getOneTripCmnt(Integer tripCmntNo) {
		return dao.findByPrimaryKey(tripCmntNo);
	}

	public List<TripCmntVO> getAll() {
		return dao.getAll();
	}
	
//	public List<TripCmntVO> getAllSorted() {
//		return dao.getAllSorted();
//	}
}
