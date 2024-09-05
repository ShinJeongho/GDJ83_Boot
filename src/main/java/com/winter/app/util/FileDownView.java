package com.winter.app.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.winter.app.qna.QnaFileVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FileDownView extends AbstractView { //리턴할떄 대문자 이름을 소문자로 바꿔서 찾아서 일로옴 없으면 jsp로감 뷰에관련된 클래스 // 파일 다운로드를 처리하는 뷰
	@Value("${app.upload}")
	private String path; //D:upload/ // 파일 저장 경로 (application.properties에서 설정)
	
	
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		QnaFileVO qnaFileVO = (QnaFileVO) model.get("file"); // 파일 정보 객체 가져오기
		String directory = (String) model.get("board"); // 게시판 타입 가져오기

		// 1. 폴더 경로 준비 // 파일 경로 설정 (예: D:/upload/qna/)
		String path = this.path+directory; //D://upload/qna

		// 2. 파일 준비 // 파일 객체 생성
		File file = new File(path, qnaFileVO.getFileName()); 

		// 3. 응답시 인코딩 처리(Filter로 처리 했으면 선택)
		response.setCharacterEncoding("UTF-8");

		// 4. 파일의 크기 지정
		response.setContentLength((int) file.length());

		// 5. 다운로드시 파일이름지정, 인코딩 설정
		String name = qnaFileVO.getOriName();
		name = URLEncoder.encode(name, "UTF-8");

		// 6. Header 정보 설정  // 응답 헤더 설정 (파일 다운로드에 필요한 정보 설정)
		response.setHeader("Content-Disposition", "attachment;fileName=\"" + name + "\"");
		response.setHeader("Content-Transfer-Encoding", "binary");

		// 7. Client 전송
		// HDD에서 파일을 읽어와서 Client로 output
		FileInputStream fi = new FileInputStream(file); // 파일 입력 스트림 생성
		OutputStream os = response.getOutputStream(); // 출력 스트림 생성

		FileCopyUtils.copy(fi, os); // 파일 복사 (파일 전송)

		// 8. 해제
		os.close();
		fi.close();

		System.out.println("Flie Down View");

	}


		
		
	}
	
	
	
