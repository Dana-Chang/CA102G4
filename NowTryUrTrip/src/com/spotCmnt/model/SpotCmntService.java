package com.spotCmnt.model;

import java.sql.Timestamp;
import java.util.List;

public class SpotCmntService {

	private SpotCmntDAO_interface dao;

	public SpotCmntService() {
		dao = new SpotCmntJDBCDAO();
	}

	public SpotCmntVO addSpotCmnt(Integer memId, Integer spotNo, String cmnt, Timestamp cmntTime, Integer rate, String isChecked, String isBlocked, String blockedReason) {

		SpotCmntVO spotCmntVO = new SpotCmntVO();

		spotCmntVO.setMemId(memId);
		spotCmntVO.setSpotNo(spotNo);
		spotCmntVO.setCmnt(cmnt);
		spotCmntVO.setCmntTime(cmntTime);
		spotCmntVO.setRate(rate);
		spotCmntVO.setIsChecked(isChecked);
		spotCmntVO.setIsBlocked(isBlocked);
		spotCmntVO.setBlockedReason(blockedReason);

		dao.insert(spotCmntVO);
		return spotCmntVO;
	}

	public SpotCmntVO updateSpotCmnt(Integer spotCmntNo, Integer memId, Integer spotNo, String cmnt, Timestamp cmntTime,
			Integer rate, String isChecked, String isBlocked, String blockedReason) {

		SpotCmntVO spotCmntVO = new SpotCmntVO();

		spotCmntVO.setSpotCmntNo(spotCmntNo);
		spotCmntVO.setMemId(memId);
		spotCmntVO.setSpotNo(spotNo);
		spotCmntVO.setCmnt(cmnt);
		spotCmntVO.setCmntTime(cmntTime);
		spotCmntVO.setRate(rate);
		spotCmntVO.setIsChecked(isChecked);
		spotCmntVO.setIsBlocked(isBlocked);
		spotCmntVO.setBlockedReason(blockedReason);

		dao.update(spotCmntVO);
		return spotCmntVO;
	}

	public void deleteSpotCmnt(Integer spotCmntNo) {
		dao.delete(spotCmntNo);
	}

	public SpotCmntVO getOneSpotCmnt(Integer spotCmntNo) {
		return dao.findByPrimaryKey(spotCmntNo);
	}

	public List<SpotCmntVO> getAll() {
		return dao.getAll();
	}
}
