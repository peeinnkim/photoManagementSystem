package com.peeinn.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {

	// upload함수. 원본 업로드, thumbnail 업로드
	// 년/월/일 폴더 만들기
	// uploadPath: c:/zzz/upload
	// originalFileName: 파일 이름
	// data: 이미지 데이터
	// return 값: thumbnail 파일명
	public static String uploadFile(String uploadPath, String originalFileName, byte[] data) throws IOException {
		
		//원본 업로드
		UUID uid = UUID.randomUUID();
		String savedName = uid.toString() + "_" + originalFileName;
		String datePath = calculatorPath(uploadPath); // -> /년/월/일 폴더를 만든 후 경로 리턴 (ex: /2019/12/13)
		File newFile = new File(uploadPath + datePath, savedName);
		FileCopyUtils.copy(data, newFile);

		//thumbnail 업로드
		String thumbnail = makeThumbnail(uploadPath + datePath, savedName);
		
		return datePath + thumbnail;
	}//uploadFile
	
	private static String makeThumbnail(String uploadPath, String fileName) throws IOException {
		//BufferedImage: 실제 이미지가 아닌 메모리상의 이미지를 의미하는 객체
		//↓원본 이미지를 가져와서 메모리 상에 넣어둠
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath + "/" + fileName));
		//↓작은 이미지용으로 리사이징 시킴. 높이 100을 기준으로 가로는 자동 조정
		BufferedImage destImg = Scalr.resize(sourceImg, 
											 Scalr.Method.AUTOMATIC, 
											 Scalr.Mode.FIT_TO_HEIGHT,
											 100);	
		
		//이미지 데이터가 들어갈 파일 만들기
		String thumbnailName = uploadPath + "/s_" + fileName; //썸네일에는 s_가 붙음
		File newFile = new File(thumbnailName);
		
		//파일 확장자 뽑아내기
		String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
		
		//작은 이미지 데이터를 작은 이미지 파일에 복사
		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
		
		return thumbnailName.substring(uploadPath.length()); //파일명
	}//makeThumbnail
	
	private static String calculatorPath(String uploadPath) {
		Calendar cal = Calendar.getInstance();
		String yearPath = "/" + cal.get(Calendar.YEAR); // -> /2019
		String monthPath = String.format("%s/%02d", yearPath, cal.get(Calendar.MONTH)+1); // -> /2019/12
		String datePath = String.format("%s/%02d", monthPath, cal.get(Calendar.DATE)); // -> /2019/12/23
		
		makeDir(uploadPath, yearPath, monthPath, datePath);
		
		return datePath;
	}//calculatorPath
	
	//paths = {yearPath, monthpath, datePath}
	private static void makeDir(String uploadPath, String... paths) {
		for(String path : paths) {
			File dir = new File(uploadPath + path);
			if(dir.exists() == false) {
				dir.mkdir();
			}
		}
	}//makeDir
	
	public static void deleteFile(String uploadPath, String filename) {
		//썸네일 삭제
		File file = new File(uploadPath + "/" + filename); 
		file.delete(); //파일 삭제
		
		//원본 삭제
		String file1 = filename.substring(0, 12);
		String file2 = filename.substring(14);
		file = new File(uploadPath + file1 + file2);
		file.delete();
	}

}// UploadFileUtils
