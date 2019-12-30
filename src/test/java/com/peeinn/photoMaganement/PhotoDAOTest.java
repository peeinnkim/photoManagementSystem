package com.peeinn.photoMaganement;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.peeinn.domain.PhotoVO;
import com.peeinn.persistence.PhotoDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class PhotoDAOTest {

	@Autowired
	PhotoDAO dao;
	
	@Test
	public void testList() {
		List<PhotoVO> list = dao.selectPhotoList("mlmlml");
		
		for(PhotoVO p : list) {
			System.out.println(p);
		}
	}
	
}//PhotoDAOTest
