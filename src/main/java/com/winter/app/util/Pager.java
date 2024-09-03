package com.winter.app.util;

import lombok.Data;

@Data
public class Pager {
	
	private Long perPage=10L; // 한 페이지당 보여줄 항목 수 (기본값: 10)
	private Long startRow;  // 시작 행 번호
	private Long page =	1L; // 현재 페이지 번호 (기본값: 1)
//	private Long totalPage; // 페이지 계산에 필요한 변수들
//	private Long totalCount; // 페이지 계산에 필요한 변수들
	
	private String kind;
	private String search;
	
//	// 페이지 계산을 위한 기본 생성자
//	public Pager() {
//		this.startRow=0L;
//	}
	 // 페이지 번호에 따라 시작 행을 계산하는 메서드
	public void makeRow() {
		this.startRow=(page-1)*perPage;
	}
	
	//getter(내부에있는걸 외부에 되돌려줌)
	public String getkind() {
		if(this.kind==null) {
			this.kind="k1";
		}
		return this.kind;
	}
	
	public String getsearch() {
	if(this.search==null) {
		this.search="";
	}
	return this.search;
	}
//	
//	// 총 데이터 수를 바탕으로 총 페이지 수 계산
//    public void makePage(Long totalCount) {
//        this.totalCount = totalCount;
//        this.totalPage = (this.totalCount + this.perPage - 1) / this.perPage;
//    }

}
