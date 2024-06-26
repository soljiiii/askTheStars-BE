package com.solji.star.community.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.solji.star.community.model.PostDTO;
import com.solji.star.community.service.CommunityService;
import com.solji.star.member.util.JwtUtil;

@RestController
public class CommunityController {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private CommunityService communityService;
	

	//글목록
//	@GetMapping("/writeList")
//	public List<PostDTO> writeList() {
//		List<PostDTO> writeList = ;
//		return writeList;
//	}
	
	//글상세보기
	
	
	//글쓰기
	@PostMapping("/postWrite")
	public String postWrite(@RequestHeader("Authorization") String authorizationHeader,
							@RequestBody PostDTO postDTO) {
		String accessToken = authorizationHeader.substring("Bearer ".length()).trim();
		String memberId = jwtUtil.getUserId(accessToken);
		
		Date now = new Date();
		System.out.println(now);
		System.out.println(memberId);
		System.out.println(accessToken);
		
		postDTO.setMemberId(memberId);
		postDTO.setWrtnDate(now);
		
		communityService.updatePosting(postDTO);
		
		return "글쓰기 성공";
	}
	
	//글삭제
	//글수정
	
	//댓글쓰기
	//댓글삭제
	//댓글수정
}
