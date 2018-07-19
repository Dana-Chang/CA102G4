package com.spotRpt.model;

import java.sql.Timestamp;
import java.util.List;

public class SpotRptService {

	private SpotRptDAO_interface dao;

	public SpotRptService() {
		dao = new SpotRptJDBCDAO();
	}

	public SpotRptVO addSpotRpt(Integer memId, Integer spotNo, String rptReason, Timestamp rptTime) {

		SpotRptVO spotCmntRptVO = new SpotRptVO();

		spotCmntRptVO.setMemId(memId);
		spotCmntRptVO.setSpotNo(spotNo);
		spotCmntRptVO.setRptReason(rptReason);
		spotCmntRptVO.setRptTime(rptTime);

		dao.insert(spotCmntRptVO);
		return spotCmntRptVO;
	}

	public SpotRptVO updateSpotRpt(Integer rptNo, Integer memId, Integer spotNo, String rptReason, Timestamp rptTime) {

		SpotRptVO spotRptVO = new SpotRptVO();

		spotRptVO.setRptNo(rptNo);
		spotRptVO.setMemId(memId);
		spotRptVO.setSpotNo(spotNo);
		spotRptVO.setRptReason(rptReason);
		spotRptVO.setRptTime(rptTime);

		dao.update(spotRptVO);
		return spotRptVO;
	}

	public void deleteSpotRpt(Integer rptNo) {
		dao.delete(rptNo);
	}

	public SpotRptVO getOneSpotRpt(Integer rptNo) {
		return dao.findByPrimaryKey(rptNo);
	}

	public List<SpotRptVO> getAll() {
		return dao.getAll();
	}
	public List<SpotRptVO> getAllSorted() {
		return dao.getAllSorted();
	}
}
