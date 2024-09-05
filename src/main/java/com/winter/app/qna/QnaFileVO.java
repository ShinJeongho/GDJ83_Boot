package com.winter.app.qna;

import lombok.Data;

@Data
public class QnaFileVO {
	
	private Long fileNum;  // 파일 번호
	private String fileName;  // 서버에 저장된 파일 이름
	private String oriName; // 원본 파일 이름
	private Long boardNum; // 게시글 번호 (파일이 첨부된 게시글과 연결)

}
