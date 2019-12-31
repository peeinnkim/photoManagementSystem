package com.peeinn.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.peeinn.domain.Criteria;
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
	public List<PhotoVO> selectPhotoList(String id, Criteria cri) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("cri", cri);
		
		return sqlSession.selectList(namespace + ".selectPhotoList", map);
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
