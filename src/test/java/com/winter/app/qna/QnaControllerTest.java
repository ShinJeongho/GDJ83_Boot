package com.winter.app.qna;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureMockMvc
class QnaControllerTest {

	//private WebApplicationContext ctx;
	@Autowired
	private MockMvc mockMvc;
	
//	@Test
//	void test() {
//		//this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
//	}
	@Test
	public void getListTest() throws Exception{
		
		//Map 키 중복 허용하지않음 a,1 => a,2
		//multimap은 중복 허용 함 a,1=>a,2 -> a{1,2}
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("page", "1");
		map.add("kind", "k1");
		map.add("search", "2");
		
//		Map<String, String> map2 = new HashMap<>();
//		map2.put("page", "1");
//		map2.put("kind", "k1");
//		map2.put("search", "2");
		
		mockMvc.perform(
				get("/qna/list")
				.params(map)
//				.param("page", "1")
//				.param("kind", "k1")
//				.param("search", "2")
				)
				
				.andDo(print());
			
		}
	
	
	
	@Test
	public void getDetailTest() throws Exception{
		mockMvc.perform(
				get("/qna/detail")
				.param("boardNum", "110")
				)
				.andExpect(status().isOk()) // 상세 페이지 요청에 대한 응답이 200 OK인지 확인
				.andDo(print()); // 요청 및 응답 내용을 콘솔에 출력
			
		}
	
	@Test
    public void addTest() throws Exception {
        
        mockMvc.perform(
                post("/qna/add")
                .param("boardTitle", "Test Title")
                .param("boardContents", "Test Content")
                .param("boardWriter", "Test Writer")
                .param("ref", "")
                .param("step", "0")
                .param("depth", "0")
            )
            .andExpect(status().is3xxRedirection())  // 게시글 추가 후 3xx 리다이렉션 상태인지 확인(리다이렉션(302)이 발생하는지 확인)
            .andExpect(redirectedUrl("./list")) // 추가 후 목록 페이지로 리다이렉션되는지 확인
            .andDo(print()); // 요청 및 응답 내용을 콘솔에 출력
    }
	
	
	
	}
