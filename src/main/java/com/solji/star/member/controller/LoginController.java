package com.solji.star.member.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solji.star.member.model.MemberDTO;

@RestController
public class LoginController {

	//로그인
	//DB에 저장된 refresh token을 불러와 access token 생성
	//access token과 refresh token을 cookie에 저장
	@PostMapping("/login")
	public String login(MemberDTO memberDTO) {
		return"ok";
	}
	
	//로그아웃
	//cookie에 저장된 refresh token과 access token 삭제
}