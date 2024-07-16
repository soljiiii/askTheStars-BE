package com.solji.star.mainPage.controller;

import java.util.List;

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
	public List<HotPostDTO> getHotPost(@RequestParam("selectedOption") String selectedOption) {
		
		List<HotPostDTO> hotPostList = null;
		System.out.println(selectedOption);
		
		if(selectedOption.equals("vwCnt")) {
			hotPostList = mainPageService.getHotPostVwCnt();
			return hotPostList;
		}
		else if(selectedOption.equals("likeCnt")) {
			hotPostList = mainPageService.getHotPostLikeCnt();
			return hotPostList;
		}
		else if(selectedOption.equals("newPost")) {
			hotPostList = mainPageService.getHotPostNewPost();
			return hotPostList;
		}
		else {
			System.out.println("select 오류");
			return hotPostList;
		}
	}

}
