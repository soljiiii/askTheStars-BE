package com.solji.star.member.service;

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

	public void setRefreshToken(String refreshToken) {
		loginMapper.setRefreshToken(refreshToken);
		
	}

	public void deleteRefreshToken(String userId) {
		loginMapper.deleteRefreshToken(userId);
		
	}

}
