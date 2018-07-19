package com.spotCmntRpt.model;
import java.util.*;

public interface SpotCmntRptDAO_interface {
    public void insert(SpotCmntRptVO spotCmntRptVO);
    public void update(SpotCmntRptVO spotCmntRptVO);
    public void delete(Integer rptNo);
    public SpotCmntRptVO findByPrimaryKey(Integer rptNo);
    public List<SpotCmntRptVO> getAll();
    public List<SpotCmntRptVO> getAllSorted();
  //萬用複合查詢(傳入參數型態Map)(回傳 List)
  //public List<EmpVO> getAll(Map<String, String[]> map); 
}
