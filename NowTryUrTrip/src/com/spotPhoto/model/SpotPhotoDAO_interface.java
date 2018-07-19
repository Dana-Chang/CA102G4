package com.spotPhoto.model;

import java.util.List;

public interface SpotPhotoDAO_interface {
	public void insert(SpotPhotoVO spotPhotoVO);
	public void update(SpotPhotoVO spotPhotoVO);
	public void delete(Integer spotPhotoNo);
	public SpotPhotoVO findByPrimaryKey(Integer spotPhotoNo);
	public List<SpotPhotoVO> getAll();
}
