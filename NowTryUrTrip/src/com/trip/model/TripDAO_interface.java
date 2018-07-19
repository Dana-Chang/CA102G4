package com.trip.model;

import java.util.List;
import java.util.Map;

public interface TripDAO_interface{
	public void insert(TripVO tripVO);
	public void update(TripVO tripVO);
	public void delete(Integer TripNo);
	public TripVO findByPrimaryKey(Integer TripNo);
	public List<TripVO> getAll();
	List<TripVO> getAll(Map<String, String[]> map);
}
