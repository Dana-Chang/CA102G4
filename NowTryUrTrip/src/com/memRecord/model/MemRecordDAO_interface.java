package com.memRecord.model;

import java.util.List;

public interface MemRecordDAO_interface {
	public void insert(MemRecordVO memRecordVO);
	public void update(MemRecordVO memRecordVO);
	public void delete(Integer menId);
	public MemRecordVO findByPrimaryKey(Integer violationId);
    public List<MemRecordVO> getAll();

}
