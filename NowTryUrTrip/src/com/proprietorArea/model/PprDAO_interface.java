package com.proprietorArea.model;

import java.util.*;



	public interface PprDAO_interface {
        public void insert(PprVO pprVo);
        public void update(PprVO pprVo);
        public void delete(Integer pprMsgeNo);
        public PprVO findByPrimaryKey(Integer pprMsgeNo);
        public List<PprVO> getAll();
      //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
      //public List<EmpVO> getAll(Map<String, String[]> map); 
}
