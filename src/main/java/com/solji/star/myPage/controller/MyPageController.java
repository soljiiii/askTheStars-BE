package com.solji.star.myPage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.solji.star.member.util.JwtUtil;
import com.solji.star.myPage.model.MyPostDTO;
import com.solji.star.myPage.model.MyReplyDTO;
import com.solji.star.myPage.service.MyPageService;

@RestController
public class MyPageController {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private MyPageService myPageService;
	
	//스탠다드 페이지
	
	//내가 쓴 글 목록
	@GetMapping("/getMyPost")
	public List<MyPostDTO> getMyPost(@RequestHeader("Authorization") String authorizationHeader) {
		
		String accessToken = authorizationHeader.substring("Bearer ".length()).trim();
		String memberId = jwtUtil.getUserId(accessToken);
		
		List<MyPostDTO> myPostList = myPageService.getMyPost(memberId);
		
		return myPostList;
		
	}
	
	//내가 쓴 댓글 목록
	@GetMapping("/getMyReply")
	public List<MyReplyDTO> getMyReply(@RequestHeader("Authorization") String authorizationHeader){
		
		String accessToken = authorizationHeader.substring("Bearer ".length()).trim();
		String memberId = jwtUtil.getUserId(accessToken);
		
		List<MyReplyDTO> myReplyList = myPageService.getMyReply(memberId);
		
		return myReplyList;
	}
	
	//내가 좋아한 글 목록
	@GetMapping("/getMyLikedPost")
	public List<MyPostDTO> getMyLikedPost(@RequestHeader("Authorization") String authorizationHeader){
		
		String accessToken = authorizationHeader.substring("Bearer ".length()).trim();
		String memberId = jwtUtil.getUserId(accessToken);
		
		List<MyPostDTO> myLikedList = myPageService.getMyLikedPost(memberId);
		
		return myLikedList;
	}
}
