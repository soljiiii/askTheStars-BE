package com.solji.star.member.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	
	//회원가입
	//refresh token을 생성하여 DB에 회원정보와 함께 저장
	
	//로그인
	//DB에 저장된 refresh token을 불러와 access token 생성
	//access token과 refresh token을 cookie에 저장
	
	//로그아웃
	//cookie에 저장된 refresh token과 access token 삭제
}