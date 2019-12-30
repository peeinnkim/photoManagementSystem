package com.peeinn.photoMaganement;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.peeinn.domain.MemberVO;
import com.peeinn.persistence.MemberDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class MemberDAOTest {
	
	@Autowired
	MemberDAO dao;
	
	//@Test
	public void testInsert() {
		dao.insertMember(new MemberVO("klklkl", "klkl3434", "김킈킈", "klkl@klkl.kl", "010-1234-1234", null));
	}
	
	@Test
	public void testSelect() {
		MemberVO vo = dao.selectMember("klklkl");
		System.out.println(vo);
	}

}//MemberDAOTest
