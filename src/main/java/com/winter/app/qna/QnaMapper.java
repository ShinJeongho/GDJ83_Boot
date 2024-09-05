package com.winter.app.qna;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.winter.app.util.Pager;

@Mapper
public interface QnaMapper {
	// 게시글 목록을 가져오는 메서드
    List<QnaVO> getList(Pager pager) throws Exception;
    // 게시글 추가
    int add(QnaVO qnaVO) throws Exception;
    // 게시글 번호 갱신
    public int refUpdate(QnaVO qnaVO)throws Exception;
    // 게시글 상세 조회
    public QnaVO getDetail(QnaVO qnaVO)throws Exception;
    // 첨부파일 추가
    public int addFile(QnaFileVO qnaFileVO) throws Exception;
    // 첨부파일 상세 조회
    public QnaFileVO getFileDetail(QnaFileVO qnaFileVO) throws Exception;
}
