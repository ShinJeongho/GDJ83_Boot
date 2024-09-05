package com.winter.app.util;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManager {
	// 파일을 저장하는 메서드
	public String fileSave(String path, MultipartFile multipartFile)throws Exception {
		//어디에 저장
		File file = new File(path);
		
		if(!file.exists()) {
			file.mkdirs(); // 경로가 존재하지 않으면 디렉토리 생성
		}
		//저장할 파일명 생성(중복x)
		String fileName= UUID.randomUUID().toString()+"_"+multipartFile.getOriginalFilename(); // 파일 이름 생성 (시간 기반)
		
		//파일을 hdd에 저장하자
		file = new File(file, fileName);  // 저장할 파일 객체 생성
		multipartFile.transferTo(file); // 파일 저장
		
		//저장된 파일명 리턴
		return fileName; // 저장된 파일 이름 반환
	}
}
