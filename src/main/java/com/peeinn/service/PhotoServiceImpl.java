package com.peeinn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.peeinn.domain.Criteria;
import com.peeinn.domain.PhotoVO;
import com.peeinn.persistence.PhotoDAO;

@Service
public class PhotoServiceImpl implements PhotoService {
	
	@Autowired
	PhotoDAO dao;

	@Override
	public void insertPhoto(List<PhotoVO> photos) {
		for(PhotoVO p : photos) {
			dao.insertPhoto(p);
		}
	}

	@Override
	public List<PhotoVO> selectPhotoList(String id, Criteria cri) {
		return dao.selectPhotoList(id, cri);
	}

	@Override
	public int selectPhotoCount(String id) {
		return dao.selectPhotoCount(id);
	}

	@Override
	public void deletePhoto(int pNo) {
		dao.deletePhoto(pNo);
	}

}//PhotoServiceImpl
