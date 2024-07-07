package com.solji.star.member.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.solji.star.community.service.CommunityService;
import com.solji.star.member.model.MemberDTO;
import com.solji.star.member.service.LoginService;
import com.solji.star.member.service.MemberService;
import com.solji.star.member.util.JwtUtil;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private CommunityService communityService;
	
	

	
	//회원가입
	//refresh token을 생성하여 DB에 회원정보와 함께 저장
	@PostMapping("/memberJoin")
	public String memberJoin(@RequestBody MemberDTO memberDTO) {
		System.out.println("DTO"+memberDTO);
		
		Random random = new Random();
		int randomStar = random.nextInt(5);
		System.out.println(randomStar);
		
		//이미지 랜덤 번호
		memberDTO.setStarImage(randomStar);
		//비밀번호 암호화
		memberDTO.setMemberPw(passwordEncoder.encode(memberDTO.getMemberPw()));
//		//토큰 저장
//		String refreshToken = jwtUtil.createRefreshToken(memberDTO.getMemberId());
//		memberDTO.setRefreshToken(refreshToken);		
		
		memberService.joinUser(memberDTO);
		return "ok";
	}
	/*----------------------------------------------------*/
	
	//중복확인(id 중복, nickNm 중복)
	@GetMapping("/idCheck")
	public int joinIdCheck(@RequestParam("memberId") String memberId) {
		int result = memberService.joinIdCheck(memberId);
		System.out.println(memberId);
		return result;
	}
	
	@GetMapping("/nickNmCheck")
	public int joinNickNmCheck(@RequestParam("memberNickNm") String memberNickNm) {
		int result = memberService.joinNickNmCheck(memberNickNm);
		return result;
	}
	
	//회원정보 가져오기
	@GetMapping("/getMemberInfo")
	public MemberDTO getMemberInfo(@RequestHeader("Authorization") String authorizationHeader) {
		
		String accessToken = authorizationHeader.substring("Bearer ".length()).trim();
		String memberId = jwtUtil.getUserId(accessToken);
		
		MemberDTO memberDTO = memberService.getMemberInfo(memberId);
		
		return memberDTO;
	}
	
	//회원정보 수정하기
	@PostMapping("/memberModify")
	public String memberModify(@RequestBody MemberDTO memberDTO) {
		
		memberDTO.setMemberPw(passwordEncoder.encode(memberDTO.getMemberPw()));
		memberService.memberModify(memberDTO);
		
		return "ok";
	}
	
	//회원 탈퇴하기
	@PostMapping("/deleteMember")
	public String deleteMemeber(@RequestHeader("Authorization") String authorizationHeader,
								@RequestParam ("memberPw") String memberPw,
								HttpServletResponse response) {
		
		String accessToken = authorizationHeader.substring("Bearer ".length()).trim();
		String memberId = jwtUtil.getUserId(accessToken);
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setMemberId(memberId);
		memberDTO.setMemberPw(memberPw);
		
		System.out.println("id:"+memberId);
		System.out.println("memberPw:"+memberPw);
		
		String loginId = memberDTO.getMemberId();
		String result = loginService.doLogin(loginId);
		
		System.out.println("result:"+result);
		
		if(memberDTO==null||!passwordEncoder.matches(memberDTO.getMemberPw(), result)) {			
			System.out.println("일치하지않음");
			return"no";
		}
		else {
			
			memberService.deleteMemberReply(memberId);
			memberService.deleteMemberLike(memberId);
			
			int [] arr = memberService.getPostNoList(memberId);
			for(int i=0 ; i<arr.length ; i++) {
				communityService.deleteUnderReply(arr[i]);
				communityService.deletePost(arr[i]);
			}
			System.out.println("postno"+arr); 
			
			
			memberService.deleteMember(memberId);
			
	        Cookie accessTokenCookie = new Cookie("accessToken", null);
	        accessTokenCookie.setHttpOnly(false);
	        accessTokenCookie.setSecure(false);
	        accessTokenCookie.setPath("/");
	        accessTokenCookie.setMaxAge(0); // 쿠키 삭제
	        
	        Cookie refreshTokenCookie = new Cookie("refreshToken", null);
	        refreshTokenCookie.setHttpOnly(true);
	        refreshTokenCookie.setSecure(false);
	        refreshTokenCookie.setPath("/");
	        refreshTokenCookie.setMaxAge(0); // 쿠키 삭제
	        
	        response.addCookie(accessTokenCookie);
	        response.addCookie( refreshTokenCookie);
	        
			return "ok";
		}		
	}
}








