package com.spotCmnt.model;

import java.util.List;

public interface SpotCmntDAO_interface {
    public void insert(SpotCmntVO spotCmntVO);
    public void update(SpotCmntVO spotCmntVO);
    public void delete(Integer spotCmntNo);
    public SpotCmntVO findByPrimaryKey(Integer spotCmntNo);
    public List<SpotCmntVO> getAll();
}
