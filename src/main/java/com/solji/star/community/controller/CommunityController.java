package com.solji.star.community.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.solji.star.community.model.PostDTO;
import com.solji.star.community.model.ReplyList;
import com.solji.star.community.model.WriteListResponseDTO;
import com.solji.star.community.service.CommunityService;
import com.solji.star.member.util.JwtUtil;

@RestController
public class CommunityController {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private CommunityService communityService;
	

	// 글목록
	@GetMapping("/writeList")
	public WriteListResponseDTO writeList(@RequestParam(name="page") int page) {
	    int pageSize = 20; // 페이지당 데이터 개수
	    int start = (page - 1) * pageSize; // 시작 위치 계산

	    List<PostDTO> writeList = communityService.getWriteList(start);
	    int totalItems = communityService.getTotalWriteCount();
	    
	    return new WriteListResponseDTO(writeList, totalItems);
	}

	
	//글상세보기
	@GetMapping("/postDetail/{postNo}")
	public PostDTO getWriteDetail(@PathVariable(name="postNo") int postNo) {
		
		communityService.plusVwCnt(postNo);
		PostDTO postDTO = communityService.getWrtieDetail(postNo);
		
		System.out.println(postDTO);
		return postDTO;
	}
	
	
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
	
	//id구하기
	@GetMapping("/getMemberId")
	public String getMemberId(@RequestHeader("Authorization") String authorizationHeader) {
		
		String accessToken = authorizationHeader.substring("Bearer ".length()).trim();
		String memberId = jwtUtil.getUserId(accessToken);
		
		System.out.println("accessToken:"+accessToken);
		System.out.println("memberId:"+memberId);
		
		return memberId;
	}
	
	//글삭제
	@DeleteMapping("/deletePost/{postNo}")
	public String deletePost(@PathVariable(name="postNo") int postNo) {
		
		communityService.deleteUnderReply(postNo);
		communityService.deletePost(postNo);
		
		return "ok";
	}
	
	//글수정
	@PostMapping("/modifyPost")
	public String modifyPost(@RequestBody PostDTO postDTO) {
		
		Date now = new Date();
		System.out.println(now);
		postDTO.setWrtnDate(now);
		
		communityService.modifyPost(postDTO);
		System.out.println(postDTO);
		
		return "ok";
	}
	
	//댓글목록
	@GetMapping("/replyList/{postNo}")
	public List<ReplyList> getReplyList(@PathVariable(name="postNo") int postNo) {
		List<ReplyList> replyList = communityService.getReplyList(postNo);
		return replyList;
	}
	
	//댓글쓰기
	@PostMapping("/writeReply")
	public String writeReply(@RequestHeader("Authorization") String authorizationHeader,
						   @RequestBody ReplyList replyList) {
		String accessToken = authorizationHeader.substring("Bearer ".length()).trim();
		String memberId = jwtUtil.getUserId(accessToken);
		
		Date now = new Date();
		System.out.println(now);
		System.out.println(memberId);
		System.out.println(accessToken);
		System.out.println(replyList);
		
		replyList.setMemberId(memberId);
		replyList.setWrtnDate(now);
		
		communityService.wrtieReply(replyList);
		
		return "글쓰기 성공";
	}
	
	//댓글삭제
	@DeleteMapping("/deleteReply/{replyNo}")
	public String deleteReply(@PathVariable(name="replyNo") int replyNo) {
		
		communityService.deleteReply(replyNo);
		
		return "ok";
	}
	
	
	//댓글수정
	@PostMapping("/modifyReply")
	public String modifyReply(@RequestBody ReplyList replyList) {
		
		Date now = new Date();
		replyList.setWrtnDate(now);
		
		communityService.modifyReply(replyList);
		System.out.println(replyList);
		
		return "ok";
	}
}
