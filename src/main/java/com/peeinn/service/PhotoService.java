package com.peeinn.service;

import java.util.List;

import com.peeinn.domain.Criteria;
import com.peeinn.domain.PhotoVO;

public interface PhotoService {
	
	public void insertPhoto(List<PhotoVO> photos);
	public void deletePhoto(int pNo);
	
	
	public List<PhotoVO> selectPhotoList(String id);
	public int selectPhotoCount(String id);

}//PhotoService
