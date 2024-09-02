package com.winter.app.notice;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeMapper {
	List<NoticeVO> getList() throws Exception;
	
	 int insertNotice(NoticeVO noticeVO) throws Exception;

}
