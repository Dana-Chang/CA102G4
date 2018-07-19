package com.proprietorArea.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class PprService {

	private PprDAO_interface dao;

	public PprService() {
		dao = new PprDAO();
	
	}
	
//	public static byte[] getPictureByteArray(String path) throws IOException {		
//		FileInputStream in = new FileInputStream(path);
//		byte[] buffer = new byte[in.available()];
//		in.read(buffer);
//		in.close();
//		return buffer;			
//	}//簡易版

	public PprVO addPpr(java.sql.Timestamp pprCheckIn,java.sql.Timestamp pprCheckOut, 
			String pprMsgeCtx, byte[] pprMsgeImg ,int pprSpotNo) throws IOException {

		PprVO pprVO = new PprVO();
//		pprvo1.setPprMsgeNo(200);
		pprVO.setPprSpotNo(pprSpotNo);
		pprVO.setPprCheckIn(pprCheckIn);
		pprVO.setPprCheckOut(pprCheckOut);
		pprVO.setPprMsgeCtx(pprMsgeCtx);
		
		System.out.println("myservices1");
		pprVO.setPprMsgeImg(pprMsgeImg);
		System.out.println("myservices2");
		dao.insert(pprVO);
		System.out.println("myservices3");
		return pprVO;
	}

	public PprVO updatePpr(Integer pprMsgeNo, Integer pprSpotNo,java.sql.Timestamp pprCheckIn,java.sql.Timestamp pprCheckOut, 
			String pprMsgeCtx, byte[] pprMsgeImg) {

		PprVO pprVO = new PprVO();
		
		pprVO.setPprMsgeNo(pprMsgeNo);
		pprVO.setPprSpotNo(pprSpotNo);
		pprVO.setPprCheckIn(pprCheckIn);
		pprVO.setPprCheckOut(pprCheckOut);
		pprVO.setPprMsgeCtx(pprMsgeCtx);
		pprVO.setPprMsgeImg(null);
		dao.update(pprVO);
		return pprVO;
	}

	public void deletePpr(Integer pprMsageNo) {
		dao.delete(pprMsageNo);
	}

	public PprVO getOnePpr(Integer pprMsageNo) {
		return dao.findByPrimaryKey(pprMsageNo);
	}

	public List<PprVO> getAll() {
		return dao.getAll();
	}		
}
