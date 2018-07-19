package com.frontend.proprietorArea.model;

import java.util.*;



	public interface PprDAO_interface {
        public void insert(PprVo pprVo);
        public void update(PprVo pprVo);
        public void delete(Integer pprVo);
        public PprVo findByPrimaryKey(Integer pprVo);
        public List<PprVo> getAll();
      //萬用複合查詢(傳入參數型態Map)(回傳 List)
//      public List<EmpVO> getAll(Map<String, String[]> map); 
}
