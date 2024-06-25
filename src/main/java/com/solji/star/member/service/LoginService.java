package com.solji.star.member.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solji.star.member.mapper.LoginMapper;

@Service
public class LoginService {
	
	@Autowired 
	private LoginMapper loginMapper;

	public String doLogin(String loginId) {
		String result = loginMapper.doLogin(loginId);
		return result;
	}

	public String getToken(String loginId) {
		String result = loginMapper.getToken(loginId);
		return result;
	}

	public void setRefreshToken(String refreshToken, String loginId) {
		Map<String, String> params = new HashMap<>();
		params.put("refreshToken", refreshToken);
		params.put("memberId", loginId);
		loginMapper.setRefreshToken(params);
		
	}

	public void deleteRefreshToken(String userId) {
		loginMapper.deleteRefreshToken(userId);
		
	}

}
