package com.solji.star.community.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.solji.star.community.model.PostDTO;

@Mapper
public interface CommunityMapper {

	void updatePosting(PostDTO postDTO);

}
