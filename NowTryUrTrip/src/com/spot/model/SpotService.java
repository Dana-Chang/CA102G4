package com.spot.model;

import java.util.List;
import java.util.Map;

import com.spot.model.SpotVO;

public class SpotService {
	private SpotDAO_interface dao;

	public SpotService() {
		dao = new SpotJDBCDAO();
	}

	public SpotVO addSpot(String spotName, String spotAddr, String spotType, String spotLng, String spotLat, double spotRate,
			String spotOverview, byte[] spotPhoto, Integer spotOwner, String spotChk, String spotHdl,
			String spotIsBlocked, String spotBlockedReason) {
		SpotVO spotVO = new SpotVO();
		spotVO.setSpotName(spotName);
		spotVO.setSpotAddr(spotAddr);
		spotVO.setSpotType(spotType);
		spotVO.setSpotLng(spotLng);
		spotVO.setSpotLat(spotLat);
		spotVO.setSpotRate(spotRate);
		spotVO.setSpotOverview(spotOverview);
		spotVO.setSpotPhoto(spotPhoto);
		spotVO.setSpotOwner(spotOwner);
		spotVO.setSpotChk(spotChk);
		spotVO.setSpotHdl(spotHdl);
		spotVO.setSpotIsBlocked(spotIsBlocked);
		spotVO.setSpotBlockedReason(spotBlockedReason);
		dao.insert(spotVO);
		return spotVO;
	}
	
	//沒有spotRate,spotLng, String spotLat,spotOwner,spotChk,spotHdl,spotIsBlocked,spotBlockedReason
	public SpotVO addSpot(String spotName, String spotAddr, String spotType, String spotOverview, byte[] spotPhoto) {
		SpotVO spotVO = new SpotVO();
		spotVO.setSpotName(spotName);
		spotVO.setSpotAddr(spotAddr);
		spotVO.setSpotType(spotType);
		spotVO.setSpotOverview(spotOverview);
		spotVO.setSpotPhoto(spotPhoto);
		dao.insert(spotVO);
		return spotVO;
	}

	public SpotVO updateSpot(Integer spotNo, String spotName, String spotAddr, String spotType, String spotLng, String spotLat,
			double spotRate, String spotOverview, byte[] spotPhoto, Integer spotOwner, String spotChk,
			String spotHdl, String spotIsBlocked, String spotBlockedReason) {

		SpotVO spotVO = new SpotVO();

		spotVO.setSpotNo(spotNo);
		spotVO.setSpotName(spotName);
		spotVO.setSpotAddr(spotAddr);
		spotVO.setSpotType(spotType);
		spotVO.setSpotLng(spotLng);
		spotVO.setSpotLat(spotLat);
		spotVO.setSpotRate(spotRate);
		spotVO.setSpotOverview(spotOverview);
		spotVO.setSpotPhoto(spotPhoto);
		spotVO.setSpotOwner(spotOwner);
		spotVO.setSpotChk(spotChk);
		spotVO.setSpotHdl(spotHdl);
		spotVO.setSpotIsBlocked(spotIsBlocked);
		spotVO.setSpotBlockedReason(spotBlockedReason);
		dao.update(spotVO);
		return spotVO;
	}

	public void deleteSpot(Integer spotNo) {
		dao.delete(spotNo);
	}

	public SpotVO getOneSpot(Integer spotNo) {
		return dao.findByPrimaryKey(spotNo);
	}

	public List<SpotVO> getAll() {
		return dao.getAll();
	}
	
	public List<SpotVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
}
