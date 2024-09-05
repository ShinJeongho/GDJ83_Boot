package com.winter.app.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/qna/*") // /qna/ 하위 경로를 처리하는 컨트롤러
@Slf4j
public class QnaController {

    @Autowired
    private QnaService qnaService; // QnA 서비스 주입
    @Value("${board.qna}")
    private String board;  // application.properties에 정의된 board.qna 값
    
    @ModelAttribute("board")
    public String getBoard() {
    	return this.board; 	// 게시판 타입을 반환 (QnA)
    }
    
 // QnA 게시글 목록 조회 (GET 요청)   
    @GetMapping("list")
    public String getList(Pager pager, Model model) throws Exception {
        List<QnaVO> ar = qnaService.getList(pager); // 게시글 목록 조회	
        model.addAttribute("list", ar);  // 게시글 목록을 모델에 추가
        model.addAttribute("pager", pager);  // 페이징 정보 추가
        log.info("Pager : {}", pager);
        return "qna/list"; // qna/list.jsp로 포워딩
    }
 // QnA 게시글 작성 화면 제공 (GET 요청)
    @GetMapping("add")
    public void add()throws Exception {
    	
    }
 // QnA 게시글 작성 처리 (POST 요청)
    @PostMapping("add")
    public String add(QnaVO qnaVO, MultipartFile [] attaches)throws Exception{
    	int result = qnaService.add(qnaVO, attaches); // 게시글 및 첨부파일 저장 처리
    	return "redirect:./list"; // 작성 완료 후 목록으로 리다이렉트
    }
 // QnA 게시글 상세 조회 (GET 요청)
    @GetMapping("detail")
    public void getDeatil(QnaVO qnaVO, Model model)throws Exception {
    	qnaVO = qnaService.getDetail(qnaVO); // 게시글 상세 정보 조회
    	model.addAttribute("vo", qnaVO); // 조회된 정보를 모델에 추가
    }
 // 첨부파일 다운로드 처리
    @GetMapping("fileDown")
    public String fileDown(QnaFileVO qnaFileVO, Model model)throws Exception {
    	qnaFileVO = qnaService.getFileDetail(qnaFileVO);// 파일 상세 정보 조회
    	model.addAttribute("file", qnaFileVO); // 파일 정보를 모델에 추가
    	
    	return "fileDownView";  // 파일 다운로드 뷰로 포워딩
    }
}
