package com.winter.app.qna;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.winter.app.util.Pager;

@Mapper
public interface QnaMapper {
	// 게시글 목록을 가져오는 메서드
    List<QnaVO> getList(Pager pager) throws Exception;
    
    int add(QnaVO qnaVO) throws Exception;
    
    public int refUpdate(QnaVO qnaVO)throws Exception;
    
    public QnaVO getDetail(QnaVO qnaVO)throws Exception;
}
