package com.tripCmnt.model;

import java.util.List;

public interface TripCmntDAO_interface {
	public void insert(TripCmntVO tripCmntVO);
	public void update(TripCmntVO tripCmntVO);
	public void delete(Integer tripCmntNo);
	public TripCmntVO findByPrimaryKey(Integer tripCmntNo);
	public List<TripCmntVO> getAll();
}
