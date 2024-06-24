package com.solji.star.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.solji.star.member.model.MemberDTO;
import com.solji.star.member.service.LoginService;
import com.solji.star.member.util.JwtUtil;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtUtil jwtUtil;

	//로그인
	//DB에 저장된 refresh token을 불러와 access token 생성
	//access token과 refresh token을 cookie에 저장
	@PostMapping("/login")
	public String login(@RequestBody MemberDTO memberDTO, HttpServletResponse response) {
		
		String loginId = memberDTO.getMemberId();
		String result = loginService.doLogin(loginId);
		
		if(memberDTO==null||!passwordEncoder.matches(memberDTO.getMemberPw(), result)) {
			return "Invalid credentials";
		}
		
		String refreshToken = jwtUtil.createRefreshToken(loginId);
		String accessToken = jwtUtil.createAccessToken(loginId);
		
		loginService.setRefreshToken(refreshToken);
		
		System.out.println("엑세스"+accessToken);
		System.out.println("리프레시"+refreshToken);
		
		//쿠키에 저장
		Cookie accessTokenCookie = new Cookie("accessToken",accessToken);
		accessTokenCookie.setHttpOnly(true);
		accessTokenCookie.setSecure(false);
		accessTokenCookie.setPath("/");
		accessTokenCookie.setMaxAge(60*60);
		
		response.addCookie(accessTokenCookie);
		
		return accessToken;
	}
	
	//access 토큰 갱신
	@PostMapping("/refresh-token")
	public String refreshAccessToken(@RequestBody String refreshToken, HttpServletResponse response) {
	    if (jwtUtil.validateToken(refreshToken)) {
	        String userId = jwtUtil.getUserId(refreshToken);
	        String storedRefreshToken = loginService.getToken(userId);
	        
	        if (storedRefreshToken != null && storedRefreshToken.equals(refreshToken)) {
	            String newAccessToken = jwtUtil.createAccessToken(userId);
	            
	            Cookie newAccessTokenCookie = new Cookie("accessToken", newAccessToken);
	            newAccessTokenCookie.setHttpOnly(true);
	            newAccessTokenCookie.setPath("/");
	            newAccessTokenCookie.setMaxAge(60 * 60); // 1시간 유효
	            
	            response.addCookie(newAccessTokenCookie);
	            
	            return "Access token refreshed";
	        }
	    }
	    
	    return "Invalid refresh token";
	}

	
	//로그아웃
	//cookie에 저장된 refresh token과 access token 삭제
	@PostMapping("/logout")
	public String logout(@RequestBody String refreshToken,HttpServletResponse response) {
		
        Cookie accessTokenCookie = new Cookie("accessToken", null);
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setSecure(false);
        accessTokenCookie.setPath("/");
        accessTokenCookie.setMaxAge(0); // 쿠키 삭제

        response.addCookie(accessTokenCookie);
        
        String userId = jwtUtil.getUserId(refreshToken);
        loginService.deleteRefreshToken(userId);
        
		return "logout successful";
	}
}





