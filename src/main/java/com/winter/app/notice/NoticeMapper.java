package com.winter.app.notice;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeMapper {
	// 게시물 목록을 가져오는 메서드
	public List<NoticeVO> getList()throws Exception;
	// 게시물을 삽입하는 메서드
	public int insertNotice(NoticeVO noticeVO)throws Exception;

}
