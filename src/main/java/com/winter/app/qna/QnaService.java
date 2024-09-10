package com.winter.app.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.util.FileManager;
import com.winter.app.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class) //예외처리  // 트랜잭션 설정 (예외 발생 시 롤백)
public class QnaService {
	
	@Autowired
	private QnaMapper qnaMapper; // QnA Mapper 주입
	@Value("${app.upload}")
	private String upload; // 파일 업로드 경로
	@Value("${board.qna}")
	private String name; // QnA 게시판 타입 이름
	
	@Autowired
	private FileManager fileManager; // 파일 매니저 주입
	
	// QnA 게시글 목록 조회
	public List<QnaVO> getList(Pager pager)throws Exception{
		pager.makeRow(); // 페이징 처리용 데이터 설정
		
		return qnaMapper.getList(pager); // 게시글 목록 조회
	}
	
	// QnA 게시글 및 첨부파일 추가
	public int add(QnaVO qnaVO, MultipartFile [] attaches)throws Exception{
				//log.info("================insert before boardNum: {} ", qnaVO.getBoardNum());
				int result=qnaMapper.add(qnaVO); // 게시글 저장
				//log.info("================insert after boardNum: {} ", qnaVO.getBoardNum());
				result = qnaMapper.refUpdate(qnaVO); // 게시글 번호 갱신
		
		if(result==0) {
			throw new Exception();
		}		
				
		 // 첨부파일 처리		
		//파일을 hdd에 저장후 db에 정보를 추가
		if(attaches != null) {
		for(MultipartFile mf: attaches) {
		if(mf==null || mf.isEmpty()) {
			continue; // 파일이 비어있으면 처리하지 않음
		} 
		String fileName = fileManager.fileSave(upload+name, mf); //D:/upload/qna  // 파일 저장
		
		QnaFileVO qnaFileVO = new QnaFileVO();
		qnaFileVO.setFileName(fileName); // 저장된 파일 이름 설정
		qnaFileVO.setOriName(mf.getOriginalFilename()); // 원본 파일 이름 설정
		qnaFileVO.setBoardNum(qnaVO.getBoardNum()); // 게시글 번호 설정
		
		result= qnaMapper.addFile(qnaFileVO);// 파일 정보 DB에 저장
		
		}
		
	}
		return result;
	}
	// QnA 게시글 상세 조회
	public QnaVO getDetail(QnaVO qnaVO)throws Exception{
		return qnaMapper.getDetail(qnaVO);
	}
	// 첨부파일 상세 정보 조회
	public QnaFileVO getFileDetail(QnaFileVO qnaFileVO) throws Exception{
		return qnaMapper.getFileDetail(qnaFileVO);
	}
	
	
}
