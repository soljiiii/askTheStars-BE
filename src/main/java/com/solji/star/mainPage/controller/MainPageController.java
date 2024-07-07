package com.solji.star.mainPage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.solji.star.mainPage.model.HotPostDTO;
import com.solji.star.mainPage.service.MainPageService;

@RestController
public class MainPageController {

	@Autowired
	private MainPageService mainPageService;
	
	//인기글 가져오기
	@GetMapping("/getHotPost")
	public HotPostDTO getHotPost(@RequestParam("selectedOption") String selectedOption) {
		
		HotPostDTO hotPostDTO = new HotPostDTO();
		
		if(selectedOption=="vwCnt") {
			hotPostDTO = mainPageService.getHotPostVwCnt();
			return hotPostDTO;
		}
		else if(selectedOption=="likeCnt") {
			hotPostDTO = mainPageService.getHotPostLikeCnt();
			return hotPostDTO;
		}
		else if(selectedOption=="newPost") {
			hotPostDTO = mainPageService.getHotPostNewPost();
			return hotPostDTO;
		}
		else {
			System.out.println("select 오류");
			return hotPostDTO;
		}
	}

}
