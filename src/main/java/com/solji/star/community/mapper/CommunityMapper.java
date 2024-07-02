package com.solji.star.community.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.solji.star.community.model.PostDTO;
import com.solji.star.community.model.ReplyList;

@Mapper
public interface CommunityMapper {

	void updatePosting(PostDTO postDTO);

	List<PostDTO> getWriteList(int start);

	int getTotalWriteCount();

	PostDTO getWriteDetail(int postNo);

	void plusVwCnt(int postNo);

	List<ReplyList> getReplyList(int postNo);

	void writeReply(ReplyList replyList);

	void deletePost(int postNo);

	void deleteUnderReply(int postNo);

	void modifyPost(PostDTO postDTO);

	void modifyReply(ReplyList replyList);

	void deleteReply(int replyNo);

}
