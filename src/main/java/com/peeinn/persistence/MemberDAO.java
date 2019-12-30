package com.peeinn.persistence;

import com.peeinn.domain.MemberVO;

public interface MemberDAO {

	public void insertMember(MemberVO vo); 
	public MemberVO selectMember(String mId); 
	
}//MemberDAO
