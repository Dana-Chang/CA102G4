package com.pointTransaction.model;

import java.util.List;

import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.pointTransaction.model.PointTransactionVO;

public class PointTransactionService {

	private PointTransactionDAO_interface dao;
	
	public PointTransactionService(){
		dao = new PointTransactionDAO();
	}
	
	public PointTransactionVO addPointTransaction(
			Integer memId,Integer startPoint,Integer startCash,
			String transactionDescription,Integer changeCash,
			Integer changePoint){
		
		PointTransactionVO pointTransactionVO = new PointTransactionVO();
		
		MemberService memSvc = new MemberService();
		MemberVO member = memSvc.getOneMember(memId);
		
		pointTransactionVO.setMemId(memId);
		pointTransactionVO.setStartPoint(startPoint);
		pointTransactionVO.setStartCash(startCash);
		pointTransactionVO.setTransactionDescription(transactionDescription);
		pointTransactionVO.setChangeCash(changeCash);
		pointTransactionVO.setChangePoint(changePoint);
		
		
		
		dao.insert(pointTransactionVO,member);
		
		return pointTransactionVO;
		
	}
	public PointTransactionVO updatePointTransaction(Integer memId,Integer startPoint,Integer startCash,
			String transactionDescription,Integer changeCash,
			Integer changePoint,Integer transactionNo) {

		PointTransactionVO pointTransactionVO = new PointTransactionVO();

		pointTransactionVO.setMemId(memId);
		pointTransactionVO.setStartPoint(startPoint);
		pointTransactionVO.setStartCash(startCash);
		pointTransactionVO.setTransactionDescription(transactionDescription);
		pointTransactionVO.setChangePoint(changePoint);
		pointTransactionVO.setChangeCash(changeCash);
		pointTransactionVO.setTransactionNo(transactionNo);
		
		dao.update(pointTransactionVO);

		return pointTransactionVO;
	}
	
	
	public void deletePointTransaction(Integer transactionNo){
		dao.delete(transactionNo);
	}
	
	public PointTransactionVO getOnePointTransaction(Integer transactionNo){
		return dao.findByPrimaryKey(transactionNo);
	}
	public List<PointTransactionVO> getAll(){
		return dao.getAll();
	}
	
	
	
	
	
	
	
}
