package com.member.model;

import java.util.List;

import com.storeInf.model.StoreInfVO;

public class MemberService {
	private MemberDAO_interface dao;

	public MemberService() {
		dao = new MemberDAO();
	}

	public MemberVO addMember(String memName, String memEmail, String memPsw, Integer memGender, String memType,
			Integer memStatus, String memCell, String memTel, String memAdd, Integer memBop, Integer memCb,
			Integer memTdp, String memDm, Integer memNomd, String memPf, Integer memPtnor, String memTaiv,
			Integer memBp, Integer memPorder, Integer memSp, Integer memOrderm, Integer memMtv, byte[] memImg) {

		MemberVO memberVO = new MemberVO();
		memberVO.setMemName(memName);
		memberVO.setMemEmail(memEmail);
		memberVO.setMemPsw(memPsw);
		memberVO.setMemGender(memGender);
		memberVO.setMemType(memType);
		memberVO.setMemStatus(1);
		memberVO.setMemCell(memCell);
		memberVO.setMemTel(memTel);
		memberVO.setMemAdd(memAdd);
		memberVO.setMemBop(0);
		memberVO.setMemCb(0);
		memberVO.setMemTdp(0);
		memberVO.setMemDm("0");
		memberVO.setMemNomd(0);
		memberVO.setMemPf("0");
		memberVO.setMemPtnor(0);
		memberVO.setMemTaiv("1");
		memberVO.setMemBp(0);
		memberVO.setMemPorder(0);
		memberVO.setMemSp(0);
		memberVO.setMemOrderm(0);
		memberVO.setMemMtv(0);
		memberVO.setMemImg(memImg);

		dao.insert(memberVO);

		return memberVO;

	}

	public MemberVO updateMember(String memName, String memEmail, String memPsw, Integer memGender, String memType,
			Integer memStatus, String memCell, String memTel, String memAdd, Integer memBop, Integer memCb,
			Integer memTdp, String memDm, Integer memNomd, String memPf, Integer memPtnor, String memTaiv,
			Integer memBp, Integer memPorder, Integer memSp, Integer memOrderm, Integer memMtv, byte[] memImg,
			Integer memId) {

		MemberVO memberVO = new MemberVO();
		memberVO.setMemName(memName);
		memberVO.setMemEmail(memEmail);
		memberVO.setMemPsw(memPsw);
		memberVO.setMemGender(memGender);
		memberVO.setMemType(memType);
		memberVO.setMemStatus(1);
		memberVO.setMemCell(memCell);
		memberVO.setMemTel(memTel);
		memberVO.setMemAdd(memAdd);
		memberVO.setMemBop(0);
		memberVO.setMemCb(0);
		memberVO.setMemTdp(0);
		memberVO.setMemDm("0");
		memberVO.setMemNomd(0);
		memberVO.setMemPf("0");
		memberVO.setMemPtnor(0);
		memberVO.setMemTaiv("1");
		memberVO.setMemBp(0);
		memberVO.setMemPorder(0);
		memberVO.setMemSp(0);
		memberVO.setMemOrderm(0);
		memberVO.setMemMtv(0);
		memberVO.setMemImg(memImg);
		memberVO.setMemId(memId);

		dao.update(memberVO);
		return memberVO;
	}

	public void deleteMember(Integer memId) {
		dao.delete(memId);
	}

	public MemberVO getOneMember(Integer memId) {
		return dao.findByPrimaryKey(memId);
	}

	public List<MemberVO> getAll() {
		return dao.getAll();
	}
	public MemberVO getOneMemberEmail(String memEmail){
		return dao.findMemberEmail(memEmail);
		
	}
	public void insertWithStoreInf(MemberVO memberVO, StoreInfVO storeInfVO){
		dao.insertWithStoreInf(memberVO, storeInfVO);
		
	}
	public MemberVO updatePoint(MemberVO memberVO , java.sql.Connection con){
		dao.updatePoint(memberVO,con);
		return memberVO;
	}
}