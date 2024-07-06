package com.solji.star.member.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.solji.star.member.model.MemberDTO;

@Mapper
public interface MemberMapper {

    // 중복확인
	public int joinIdCheck(String memberId);
    public int joinNickNmCheck(String memberNickNm);
	public void joinUser(MemberDTO memberDTO);

    // 회원가입, 회원수정, 회원탈퇴 관련 메소드 추가 가능
	public MemberDTO getMemberInfo(String memberId);
	public void memberModify(MemberDTO memberDTO);
}
