package com.peeinn.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.peeinn.domain.PhotoVO;

@Repository
public class PhotoDAOImpl implements PhotoDAO {

	@Autowired
	private SqlSession sqlSession;
	private static final String namespace = "mappers.PhotoMapper";
	
	@Override
	public void insertPhoto(PhotoVO vo) {
		sqlSession.insert(namespace + ".insertPhoto", vo);
	}

	@Override
	public List<PhotoVO> selectPhotoList(String id) {
		return sqlSession.selectList(namespace + ".selectPhotoList", id);
	}

	@Override
	public int selectPhotoCount(String id) {
		return sqlSession.selectOne(namespace + ".selectPhotoCount", id);
	}

	@Override
	public void deletePhoto(int pNo) {
		sqlSession.delete(namespace + ".deletePhoto", pNo);
	}

}
