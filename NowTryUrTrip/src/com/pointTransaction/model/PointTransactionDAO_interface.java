package com.pointTransaction.model;

import java.util.List;

import com.member.model.MemberVO;


public interface PointTransactionDAO_interface {
	
	
        public void insert(PointTransactionVO pointTransactionVO, MemberVO memberVO);
        public void update(PointTransactionVO pointTransactionVO);
        public void delete(Integer transactionNo);
        public PointTransactionVO findByPrimaryKey(Integer transactionNo);
        public List<PointTransactionVO> getAll();
		void insert(PointTransactionVO pointTransactionVO);
       
//      public List<PointTransactionVO> getAll(Map<String, String[]> map); 

	
}
