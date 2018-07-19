package com.spotRpt.model;

import java.util.*;

public interface SpotRptDAO_interface {
	public void insert(SpotRptVO spotRptVO);

	public void update(SpotRptVO spotRptVO);

	public void delete(Integer rptNo);

	public SpotRptVO findByPrimaryKey(Integer rptNo);

	public List<SpotRptVO> getAll();

	public List<SpotRptVO> getAllSorted();

}
