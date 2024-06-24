package com.solji.star.member.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {

	public String doLogin(String loginId);

	public String getToken(String loginId);

	public void setRefreshToken(String refreshToken);

	public void deleteRefreshToken(String userId);
}
