package com.solji.star.myPage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.solji.star.member.model.MemberDTO;
import com.solji.star.myPage.model.MyPostDTO;
import com.solji.star.myPage.model.MyReplyDTO;

@Mapper
public interface MyPageMapper {

	List<MyPostDTO> getMyPost(String memberId);

	List<MyReplyDTO> getMyReply(String memberId);

	List<MyPostDTO> getMyLikedPost(String memberId);

	MemberDTO getUserInfo(String memberId);

}
