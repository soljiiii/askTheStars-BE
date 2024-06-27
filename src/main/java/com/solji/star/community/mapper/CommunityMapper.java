package com.solji.star.community.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.solji.star.community.model.PostDTO;

@Mapper
public interface CommunityMapper {

	void updatePosting(PostDTO postDTO);

	List<PostDTO> getWriteList(int start);

	int getTotalWriteCount();

	PostDTO getWriteDetail(int postNo);

	void plusVwCnt(int postNo);


}
