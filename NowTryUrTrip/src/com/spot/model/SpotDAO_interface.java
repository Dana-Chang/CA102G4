package com.spot.model;

import java.util.List;
import java.util.Map;

public interface SpotDAO_interface {

	public void insert(SpotVO spotVO);

	public void update(SpotVO spotVO);

	public void delete(Integer spotNo);

	public SpotVO findByPrimaryKey(Integer spotNo);

	public List<SpotVO> getAll();
	
	public List<SpotVO> getAll(Map<String, String[]> map);
}
