package com.spotPhoto.model;

import java.util.List;

public class SpotPhotoService {

	private SpotPhotoDAO_interface dao;

	public SpotPhotoService() {
		dao = new SpotPhotoJDBCDAO();
	}

	public SpotPhotoVO addSpotPhoto(Integer spotNo, Integer memId, byte[] spotPhoto, String photoTitle) {

		SpotPhotoVO spotPhotoVO = new SpotPhotoVO();

		spotPhotoVO.setSpotNo(spotNo);
		spotPhotoVO.setMemId(memId);
		spotPhotoVO.setSpotPhoto(spotPhoto);
		spotPhotoVO.setPhotoTitle(photoTitle);

		dao.insert(spotPhotoVO);

		return spotPhotoVO;
	}

	public SpotPhotoVO updateTrip(Integer spotPhotoNo, Integer spotNo, Integer memId, byte[] spotPhoto,	String photoTitle) {

		SpotPhotoVO spotPhotoVO = new SpotPhotoVO();

		spotPhotoVO.setSpotPhotoNo(spotPhotoNo);
		spotPhotoVO.setSpotNo(spotNo);
		spotPhotoVO.setMemId(memId);
		spotPhotoVO.setSpotPhoto(spotPhoto);
		spotPhotoVO.setPhotoTitle(photoTitle);

		dao.update(spotPhotoVO);

		return spotPhotoVO;
	}

	public void deleteTrip(Integer spotPhotoNo) {
		dao.delete(spotPhotoNo);
	}

	public SpotPhotoVO getOneSpotPhoto(Integer spotPhotoNo) {
		return dao.findByPrimaryKey(spotPhotoNo);
	}

	public List<SpotPhotoVO> getAll() {
		return dao.getAll();
	}
}
