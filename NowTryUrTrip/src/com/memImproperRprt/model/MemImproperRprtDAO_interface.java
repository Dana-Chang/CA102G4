package com.memImproperRprt.model;

import java.util.List;

public interface MemImproperRprtDAO_interface {
	public void insert(MemImproperRprtVO memImproperRprtVO);
	public void update(MemImproperRprtVO memImproperRprtVO);
	public void delete(Integer irsnumber);
	public MemImproperRprtVO findByPrimaryKey(Integer irsnumber);
    public List<MemImproperRprtVO> getAll();
}
