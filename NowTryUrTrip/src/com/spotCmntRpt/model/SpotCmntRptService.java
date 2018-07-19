package com.spotCmntRpt.model;

import java.sql.Timestamp;
import java.util.List;

public class SpotCmntRptService {

	private SpotCmntRptDAO_interface dao;

	public SpotCmntRptService() {
		dao = new SpotCmntRptJDBCDAO();
	}

	public SpotCmntRptVO addSpotCmntRpt(Integer memId, Integer spotCmntNo, String rptReason, Timestamp rptTime) {

		SpotCmntRptVO spotCmntRptVO = new SpotCmntRptVO();

		spotCmntRptVO.setMemId(memId);
		spotCmntRptVO.setSpotCmntNo(spotCmntNo);
		spotCmntRptVO.setRptReason(rptReason);
		spotCmntRptVO.setRptTime(rptTime);

		dao.insert(spotCmntRptVO);
		return spotCmntRptVO;
	}

	public SpotCmntRptVO updateSpotCmntRpt(Integer rptNo, Integer memId, Integer spotCmntNo, String rptReason, Timestamp rptTime) {

		SpotCmntRptVO spotCmntRptVO = new SpotCmntRptVO();

		spotCmntRptVO.setRptNo(rptNo);
		spotCmntRptVO.setMemId(memId);
		spotCmntRptVO.setSpotCmntNo(spotCmntNo);
		spotCmntRptVO.setRptReason(rptReason);
		spotCmntRptVO.setRptTime(rptTime);

		dao.update(spotCmntRptVO);
		return spotCmntRptVO;
	}

	public void deleteSpotCmntRpt(Integer rptNo) {
		dao.delete(rptNo);
	}

	public SpotCmntRptVO getOneSpotCmntRpt(Integer rptNo) {
		return dao.findByPrimaryKey(rptNo);
	}

	public List<SpotCmntRptVO> getAll() {
		return dao.getAll();
	}
	
	public List<SpotCmntRptVO> getAllSorted() {
		return dao.getAllSorted();
	}
}
