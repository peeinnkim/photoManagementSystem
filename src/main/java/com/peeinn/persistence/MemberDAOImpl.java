package com.peeinn.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.peeinn.domain.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Autowired
	private SqlSession sqlSession;
	private static final String namespace = "mappers.MemberMapper";
	
	@Override
	public void insertMember(MemberVO vo) {
		sqlSession.insert(namespace + ".insertMember", vo);
	}

	@Override
	public MemberVO selectMember(String mId) {
		return sqlSession.selectOne(namespace + ".selectMember", mId);
	}


	

}//MemberDAOImpl
