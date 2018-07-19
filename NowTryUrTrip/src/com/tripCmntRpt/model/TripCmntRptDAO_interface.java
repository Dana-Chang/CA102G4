package com.tripCmntRpt.model;

import java.util.List;

import com.spotCmntRpt.model.SpotCmntRptVO;

public interface TripCmntRptDAO_interface {
	public void insert(TripCmntRptVO tripCmntRptVO);
	public void update(TripCmntRptVO tripCmntRptVO);
	public void delete(Integer tripCmntRptNo);
	public TripCmntRptVO findByPrimaryKey(Integer tripCmntRptNo);
	public List<TripCmntRptVO> getAll();
	public List<TripCmntRptVO> getAllSorted();
}
