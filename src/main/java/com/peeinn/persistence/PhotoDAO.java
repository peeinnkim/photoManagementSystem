package com.peeinn.persistence;

import java.util.List;

import com.peeinn.domain.PhotoVO;

public interface PhotoDAO {
	
	public void insertPhoto(PhotoVO vo);
	public void deletePhoto(int pNo);
	
	
	public List<PhotoVO> selectPhotoList(String id);
	public int selectPhotoCount(String id);
	
}//PhotoDAO
