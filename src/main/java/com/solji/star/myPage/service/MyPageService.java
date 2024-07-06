package com.solji.star.myPage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solji.star.myPage.mapper.MyPageMapper;
import com.solji.star.myPage.model.MyPostDTO;
import com.solji.star.myPage.model.MyReplyDTO;

@Service
public class MyPageService {
	
	@Autowired
	private MyPageMapper myPageMapper;

	public List<MyPostDTO> getMyPost(String memberId) {
		List<MyPostDTO> myPostList = myPageMapper.getMyPost(memberId);
		return myPostList;
	}

	public List<MyReplyDTO> getMyReply(String memberId) {
		List<MyReplyDTO> myReplyList = myPageMapper.getMyReply(memberId);
		return myReplyList;
	}

	public List<MyPostDTO> getMyLikedPost(String memberId) {
		List<MyPostDTO> myLikedList = myPageMapper.getMyLikedPost(memberId);
		return myLikedList;
	}

}
