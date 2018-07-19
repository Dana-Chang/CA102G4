package com.memImproperRprt.model;

import java.util.List;

public class MemImproperRprtService {
	private MemImproperRprtDAO_interface dao;

	public MemImproperRprtService() {
		dao = new MemImproperRprtDAO();
	}

	public MemImproperRprtVO addMemImproperRprt(Integer violationId) {
		MemImproperRprtVO memImproperRprtVO = new MemImproperRprtVO();

		memImproperRprtVO.setViolationId(violationId);
		dao.insert(memImproperRprtVO);
		return memImproperRprtVO;

	}

	public MemImproperRprtVO deleteMemImproperRprt(Integer irsnumber) {
		MemImproperRprtVO memImproperRprtVO = new MemImproperRprtVO();

		memImproperRprtVO.setReportNumber(irsnumber);
		dao.delete(irsnumber);
		return memImproperRprtVO;
	}

	public MemImproperRprtVO getOneMemImproperRprt(Integer irsnumber) {
		return dao.findByPrimaryKey(irsnumber);
	}

	public List<MemImproperRprtVO> getAll() {
		return dao.getAll();
	}

}