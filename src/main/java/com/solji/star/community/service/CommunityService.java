package com.solji.star.community.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.solji.star.community.mapper.CommunityMapper;
import com.solji.star.community.model.LikeDTO;
import com.solji.star.community.model.PostDTO;
import com.solji.star.community.model.ReplyList;

@Transactional
@Service
public class CommunityService {

	@Autowired
	private CommunityMapper communityMapper;
	
	public void updatePosting(PostDTO postDTO) {
		communityMapper.updatePosting(postDTO);	
	}

	public List<PostDTO> getWriteList(int start) {
		List<PostDTO> writeList = communityMapper.getWriteList(start);
		return writeList;
	}

	public int getTotalWriteCount() {
		int result = communityMapper.getTotalWriteCount();
		return result;
	}

	public PostDTO getWrtieDetail(int postNo) {
		PostDTO result = communityMapper.getWriteDetail(postNo);
		return result;
	}

	public void plusVwCnt(int postNo) {
		System.out.println("Executing plusVwCnt for postNo: " + postNo);
		communityMapper.plusVwCnt(postNo);		
	}

	public List<ReplyList> getReplyList(int postNo) {
		List<ReplyList> replyList = communityMapper.getReplyList(postNo);
		return replyList;
	}

	public void wrtieReply(ReplyList replyList) {
		communityMapper.writeReply(replyList);
	}

	public void deletePost(int postNo) {
		communityMapper.deletePost(postNo);		
	}

	public void deleteUnderReply(int postNo) {
		communityMapper.deleteUnderReply(postNo);
	}

	public void modifyPost(PostDTO postDTO) {
		communityMapper.modifyPost(postDTO);		
	}

	public void modifyReply(ReplyList replyList) {
		communityMapper.modifyReply(replyList);		
	}

	public void deleteReply(int replyNo) {
		communityMapper.deleteReply(replyNo);		
	}

	public int getLikeState(LikeDTO likeDTO) {
		int result = communityMapper.getLikeState(likeDTO);
		return result;
	}

	public void postLike(LikeDTO likeDTO) {
		communityMapper.postLike(likeDTO);		
	}

	public void countLike(LikeDTO likeDTO) {
		communityMapper.countLike(likeDTO);		
	}

	public void deleteLike(LikeDTO likeDTO) {
		communityMapper.deleteLike(likeDTO);		
	}

	public void deleteLikeCount(LikeDTO likeDTO) {
		communityMapper.deleteLikeCount(likeDTO);		
	}

}
