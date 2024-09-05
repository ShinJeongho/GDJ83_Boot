package com.winter.app.qna;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class QnaVO {
	private Long boardNum;
	private String boardWriter;
	private String boardTitle;
	private String boardContents;
	private Date createDate;
	private Long ref; // 참조 번호
	private Long step;  // 단계 (답글 관련)
	private Long depth; // 깊이 (답글 관련)
	private List<QnaFileVO> ar; // 첨부파일 목록
	
}
