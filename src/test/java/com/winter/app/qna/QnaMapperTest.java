package com.winter.app.qna;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.winter.app.util.Pager;


@SpringBootTest
@Transactional //모든 테스트 메서드 실행후 전부 롤백처리
class QnaMapperTest {
	@Autowired
	private QnaMapper qnaMapper;
	
	
	@Test
	@Rollback(false) //메서드 실행후 롤백처리 안함 
	void getDetailTest()throws Exception{
		QnaVO qnaVO = new QnaVO();
		qnaVO.setBoardNum(110L);
		qnaVO=qnaMapper.getDetail(qnaVO);
		
		assertNotNull(qnaVO);
	}
	
	@Test
	void addTest()throws Exception{
		for(int i=5;i<110;i++) {
		QnaVO qnaVO = new QnaVO();
		qnaVO.setBoardContents("c3"+i);
		qnaVO.setBoardTitle("t3"+i);
		qnaVO.setBoardWriter("w3"+i);
		qnaVO.setRef((long)i);
		qnaVO.setStep(0L);
		qnaVO.setDepth(0L);
		int result =qnaMapper.add(qnaVO);
		if(i%10==0) {
			Thread.sleep(500);
		}
	}
		
	}

	@Test
	void getListTest() throws Exception {
		Pager pager = new Pager();
		pager.setPage(1L); // 첫 번째 페이지 설정
		pager.setKind("k1");
		pager.setSearch("2");
        pager.makeRow(); // 시작 행 계산
        
        List<QnaVO> result = qnaMapper.getList(pager);
        
        assertNotEquals(0, result.size()); // 목록이 비어 있지 않아야 함
//        assertTrue(result.size() <= pager.getPerPage()); // 가져온 항목 수가 페이지당 항목 수 이하여야 함
        
        // 결과 확인 출력 (선택 사항)
//        result.forEach(qna -> System.out.println(qna.getBoardNum()));
    }
}


