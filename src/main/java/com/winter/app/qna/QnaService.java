package com.winter.app.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.util.FileManager;
import com.winter.app.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QnaService {
	
	@Autowired
	private QnaMapper qnaMapper;
	@Value("${app.upload}")
	private String upload;
	@Value("${board.qna}")
	private String name;
	
	@Autowired
	private FileManager fileManager;
	
	public List<QnaVO> getList(Pager pager)throws Exception{
		pager.makeRow();
		
		return qnaMapper.getList(pager);
	}
	
	public int add(QnaVO qnaVO, MultipartFile [] attaches)throws Exception{
		log.info("=========== Insert Before BoardNum:{}",qnaVO.getBoardNum());
		//int result = qnaMapper.add(qnaVO);
		log.info("=========== Insert After BoardNum:{}",qnaVO.getBoardNum());
		//result= qnaMapper.refUpdate(qnaVO);
		
		//파일을 hdd에 저장후 db에 정보를 추가
		for(MultipartFile mf: attaches) {
		if(mf==null || mf.isEmpty()) {
			continue;
		}
		String fileName = fileManager.fileSave(upload+name, mf); //D:/upload/qna
		log.info("저장된 파일명 : {}", fileName);
		}
		return 0; //result;
	}
	
	public QnaVO getDetail(QnaVO qnaVO)throws Exception{
		return qnaMapper.getDetail(qnaVO);
	}
	
	
}
