package com.peeinn.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.peeinn.domain.Criteria;
import com.peeinn.domain.PageMaker;
import com.peeinn.domain.PhotoVO;
import com.peeinn.service.PhotoService;
import com.peeinn.util.UploadFileUtils;

@Controller
@RequestMapping("/photo/*")
public class PhotoController {
	
	private static final Logger logger = LoggerFactory.getLogger(PhotoController.class);
	@Autowired
	PhotoService service;
	@Resource(name="uploadPath") //id로 주입(DI: Dependency Injection)을 받음
	private String uploadPath;
	
	@RequestMapping(value="photoMng", method=RequestMethod.GET)
	public void photoMngGet() {
		logger.info("--------- photoMngGet ---------");
		
		
	}
	
	@ResponseBody
	@RequestMapping(value="list/{page}", method=RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> photoList(String id, @PathVariable("page") int page) {
		logger.info("--------- photoList ---------");
		ResponseEntity<Map<String, Object>> entity = null;
		
		try {
			Criteria cri = new Criteria(page, 16);
			List<PhotoVO> list = service.selectPhotoList(id, cri);

			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(service.selectPhotoCount(id));
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", list);
			map.put("pageMaker", pageMaker);
			
			entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK); 
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	
	/*----------------------UPLOAD---------------------------------*/
	@ResponseBody
	@RequestMapping(value="upload", method=RequestMethod.POST)
	public ResponseEntity<String> upload(PhotoVO photo, List<MultipartFile> files) { //파일 업로드
		logger.info("-------------- upload");
		ResponseEntity<String> entity = null;
		List<PhotoVO> list = new ArrayList<PhotoVO>();
		
		try {
			for(MultipartFile f : files) {
				logger.info("size: " + f.getSize() + " / name: " + f.getOriginalFilename());
				
				//upload처리
				String thumb = UploadFileUtils.uploadFile(uploadPath, f.getOriginalFilename(), f.getBytes());
				
				//db에 저장
				PhotoVO vo = new PhotoVO();
				vo.setpWriter(photo.getpWriter());
				vo.setpOriginName(f.getOriginalFilename());
				vo.setpName(thumb);
				list.add(vo);
			}
			service.insertPhoto(list);
			
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();

			entity = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@RequestMapping(value="display", method=RequestMethod.GET)
	public ResponseEntity<byte[]> display(String filename){ //외부 파일 불러오기
		logger.info("--------- display ---------");
		logger.info("filename: " + filename);
		ResponseEntity<byte[]> entity = null;
		
		try {
			//확장자
			String formatName = filename.substring(filename.lastIndexOf(".") + 1);
			MediaType type = null;
			
			if(formatName.equalsIgnoreCase("jpg")) {
				type = MediaType.IMAGE_JPEG;
			} else if(formatName.equalsIgnoreCase("png")) {
				type = MediaType.IMAGE_PNG;
			} else if(formatName.equalsIgnoreCase("gif")) {
				type = MediaType.IMAGE_GIF;
			}
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(type);
			
			//파일을 읽어들임
			InputStream in = new FileInputStream(uploadPath + "/" + filename);
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), //바이트 배열을 리턴
												headers,
												HttpStatus.CREATED);
			in.close(); //InputStream은 사용 완료 후 꼭 close를 해줘야 다른 곳에서 이 파일에 접근 가능함.
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public ResponseEntity<String> delete(PhotoVO photo){ //파일 삭제
		logger.info("--------- delete ---------");
		logger.info("photo: " + photo);
		ResponseEntity<String> entity = null;
		
		try {
			UploadFileUtils.deleteFile(uploadPath, photo.getpName());
			service.deletePhoto(photo.getpNo());
			
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	
	
	
	
	
	
}//PhotoController
