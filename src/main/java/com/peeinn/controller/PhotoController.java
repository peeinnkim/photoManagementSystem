package com.peeinn.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peeinn.domain.PhotoVO;
import com.peeinn.service.PhotoService;

@Controller
@RequestMapping("/photo/*")
public class PhotoController {
	
	private static final Logger logger = LoggerFactory.getLogger(PhotoController.class);
	@Autowired
	PhotoService service;
	
	@RequestMapping(value="photoMng", method=RequestMethod.GET)
	public void photoMngGet() {
		logger.info("--------- photoMngGet ---------");
	}
	
	@ResponseBody
	@RequestMapping(value="list", method=RequestMethod.POST)
	public ResponseEntity<List<PhotoVO>> photoList(String id) {
		logger.info("--------- photoList ---------");
		ResponseEntity<List<PhotoVO>> entity = null;
		System.out.println(id);
		
		try {
			List<PhotoVO> list = service.selectPhotoList(id);
			System.out.println(list);
			entity = new ResponseEntity<List<PhotoVO>>(list, HttpStatus.OK); 
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<List<PhotoVO>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	
	
	
	
	
	
	
	
}//PhotoController
