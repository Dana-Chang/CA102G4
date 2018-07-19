package com.member.model;

import java.sql.Connection;
import java.util.List;

import com.storeInf.model.StoreInfVO;



public interface MemberDAO_interface {

	public void insert(MemberVO memVO);
	public void update(MemberVO memVO);
	public void delete(Integer memid);
	public MemberVO findByPrimaryKey(Integer memid);
    public List<MemberVO> getAll();
    public MemberVO findMemberEmail(String memEmail);
    
    public void insertWithStoreInf(MemberVO memberVO ,StoreInfVO storeInfVO);
	public void updatePoint(MemberVO memberVO, Connection con);
  
}
