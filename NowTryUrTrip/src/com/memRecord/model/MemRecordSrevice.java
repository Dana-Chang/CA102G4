package com.memRecord.model;

import java.sql.Timestamp;
import java.util.List;

public class MemRecordSrevice {
	private MemRecordDAO_interface dao;

	public MemRecordSrevice() {
		dao = new MemRecordDAO();
	}

	public MemRecordVO addMemRecord(Integer memId, Integer isSuspended, String suspCat, String suspReason,
			Timestamp suspStart, Integer suspDays, Integer rprtSrc, Integer mgrId) {
		MemRecordVO memRecordVO = new MemRecordVO();
		memRecordVO.setMemId(memId);
		memRecordVO.setIsSuspended(isSuspended);
		memRecordVO.setSuspCat(suspCat);
		memRecordVO.setSuspReason(suspReason);
		memRecordVO.setSuspStart(suspStart);
		memRecordVO.setSuspDays(suspDays);
		memRecordVO.setRprtSrc(rprtSrc);
		memRecordVO.setMgrId(mgrId);
		dao.insert(memRecordVO);
		return memRecordVO;
	}

	public MemRecordVO updateMemRecord(Integer memId, Integer isSuspended, String suspCat, String suspReason,
			Timestamp suspStart, Integer suspDays, Integer rprtSrc, Integer mgrId) {
		MemRecordVO memRecordVO = new MemRecordVO();
		memRecordVO.setMemId(memId);
		memRecordVO.setIsSuspended(isSuspended);
		memRecordVO.setSuspCat(suspCat);
		memRecordVO.setSuspReason(suspReason);
		memRecordVO.setSuspStart(suspStart);
		memRecordVO.setSuspDays(suspDays);
		memRecordVO.setRprtSrc(rprtSrc);
		memRecordVO.setMgrId(mgrId);
		dao.update(memRecordVO);
		return memRecordVO;
	}

	public void deleteMemRecord(Integer violationId) {
		dao.delete(violationId);
	}

	public MemRecordVO getOneMemRecord(Integer violationId) {
		return dao.findByPrimaryKey(violationId);

	}

	public List<MemRecordVO> getAll() {
		return dao.getAll();
	}
}