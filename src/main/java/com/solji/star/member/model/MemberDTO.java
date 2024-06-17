package com.solji.star.member.model;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("member")
public class MemberDTO {
	private String memberId;
	private String refreshToken;
	private String memberPw;
	private String memberNm;
	private String memberNickNm;
	private String phone1;
	private String phone2;
	private String phone3;
	private String identityNo1;
	private String identityNo2;
	private int starImage;
	private String email1;
	private String email2;
}
