package com.solji.star.member.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.solji.star.member.model.MemberDTO;
import com.solji.star.member.service.MemberService;
import com.solji.star.member.util.JwtUtil;

@RestController
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtUtil jwtUtil;
	
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
	/*----------------------------------------------------*/
	
	//회원수정
	@PutMapping("/memberModify")
	public void memberModify() {
		
	}
	/*----------------------------------------------------*/
	
	//회원탈퇴
	@DeleteMapping("/memberDelete")
	public void memberDelete() {
		
	}
	
	
}








