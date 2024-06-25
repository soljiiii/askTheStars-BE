package com.solji.star.member.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {

	public String doLogin(String loginId);

	public String getToken(String loginId);

	public void setRefreshToken(Map<String, String> param);

	public void deleteRefreshToken(String userId);
}
