package com.winter.app.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j // 로깅을 위한 Lombok 어노테이션
public class HomeController {

	@GetMapping("/") // 루트 경로로 요청이 들어오면 실행
	public String home() throws Exception {
		log.trace("TRACE");
		log.debug("DEBUG");
		log.info("INFO");
		log.warn("WARN");
		log.error("ERROR");
		
		return "index"; // index.jsp로 포워딩
	}
}
