package com.peeinn.service;

import com.peeinn.domain.MemberVO;

public interface MemberService {
	
	public void insertMember(MemberVO vo);
	public MemberVO selectMember(String mId); 
	
}//MemberService
