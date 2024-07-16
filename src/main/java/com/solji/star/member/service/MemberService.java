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

	public MemberDTO getMemberInfo(String memberId) {
		MemberDTO memberDTO = memberMapper.getMemberInfo(memberId);
		return memberDTO;
	}

	public void memberModify(MemberDTO memberDTO) {
		memberMapper.memberModify(memberDTO);
	}

	public void deleteMember(String memberId) {
		memberMapper.deleteMember(memberId);
		System.out.println("system"+memberId);
	}

	public int[] getPostNoList(String memberId) {
		int [] arr = memberMapper.getPostNoList(memberId);
		return arr;
	}

	public void deleteMemberReply(String memberId) {
		memberMapper.deleteMemberReply(memberId);
	}

	public void deleteMemberLike(String memberId) {
		memberMapper.deleteMemberLike(memberId);
	}
	
	//회원수정
	
	//회원탈퇴
}
