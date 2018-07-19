package com.trip.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class TripService {

	private TripDAO_interface dao;

	public TripService() {
		dao = new TripJDBCDAO();
	}

	public TripVO addTrip(Integer memId, String tripName, Timestamp departTime, String tripType, Timestamp tripAddTime,
			Timestamp tripRmvTime, Double tripRate, String tripIsPublic, Integer tripPrice, byte[] tripAdImg,
			String tripDesc, String tripContent) {

		TripVO tripVO = new TripVO();

		tripVO.setMemId(memId);
		tripVO.setTripName(tripName);
		tripVO.setDepartTime(departTime);
		tripVO.setTripType(tripType);
		tripVO.setTripAddTime(tripAddTime);
		tripVO.setTripRmvTime(tripRmvTime);
		tripVO.setTripRate(tripRate);
		tripVO.setTripIsPublic(tripIsPublic);
		tripVO.setTripPrice(tripPrice);
		tripVO.setTripAdImg(tripAdImg);
		tripVO.setTripDesc(tripDesc);
		tripVO.setTripContent(tripContent);

		dao.insert(tripVO);

		return tripVO;
	}
	public TripVO addTripWithoutTime(Integer memId, String tripName, Timestamp departTime, String tripType, Double tripRate, String tripIsPublic, Integer tripPrice, byte[] tripAdImg,
			String tripDesc, String tripContent) {
		
		TripVO tripVO = new TripVO();
		
		tripVO.setMemId(memId);
		tripVO.setTripName(tripName);
		tripVO.setDepartTime(departTime);
		tripVO.setTripType(tripType);
		tripVO.setTripIsPublic(tripIsPublic);
		tripVO.setTripPrice(tripPrice);
		tripVO.setTripRate(tripRate);
		tripVO.setTripAdImg(tripAdImg);
		tripVO.setTripDesc(tripDesc);
		tripVO.setTripContent(tripContent);
		
		dao.insert(tripVO);
		
		return tripVO;
	}

	public TripVO updateTrip(Integer tripNo, Integer memId, String tripName, Timestamp departTime, String tripType,
			Timestamp tripAddTime, Timestamp tripRmvTime, Double tripRate, String tripIsPublic, Integer tripPrice,
			byte[] tripAdImg, String tripDesc, String tripContent) {

		TripVO tripVO = new TripVO();

		tripVO.setTripNo(tripNo);
		tripVO.setMemId(memId);
		tripVO.setTripName(tripName);
		tripVO.setDepartTime(departTime);
		tripVO.setTripType(tripType);
		tripVO.setTripAddTime(tripAddTime);
		tripVO.setTripRmvTime(tripRmvTime);
		tripVO.setTripRate(tripRate);
		tripVO.setTripIsPublic(tripIsPublic);
		tripVO.setTripPrice(tripPrice);
		tripVO.setTripAdImg(tripAdImg);
		tripVO.setTripDesc(tripDesc);
		tripVO.setTripContent(tripContent);

		dao.update(tripVO);

		return tripVO;
	}

	public void deleteTrip(Integer tripNo) {
		dao.delete(tripNo);
	}

	public TripVO getOneTrip(Integer tripNo) {
		return dao.findByPrimaryKey(tripNo);
	}

	public List<TripVO> getAll() {
		return dao.getAll();
	}
	
	public List<TripVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
}
