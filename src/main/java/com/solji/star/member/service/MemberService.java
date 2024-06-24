package com.solji.star.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solji.star.member.mapper.MemberMapper;
import com.solji.star.member.model.MemberDTO;

@Service
public class MemberService {
	
	@Autowired
	private MemberMapper memberMapper; 

	//회원가입
	public void memberJoin(MemberDTO memberDTO) {
	}
	
	//중복확인
	public int joinIdCheck(String memberId) {
		int result = memberMapper.joinIdCheck(memberId);
		return result;
	}
	public int joinNickNmCheck(String memberNickNm) {
		int result = memberMapper.joinNickNmCheck(memberNickNm);
		return result;
	}

	public void joinUser(MemberDTO memberDTO) {
		memberMapper.joinUser(memberDTO);
		
	}
	
	//회원수정
	
	//회원탈퇴
}
