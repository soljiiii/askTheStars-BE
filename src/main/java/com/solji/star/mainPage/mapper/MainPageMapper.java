package com.solji.star.mainPage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.solji.star.mainPage.model.HotPostDTO;

@Mapper
public interface MainPageMapper {

	List<HotPostDTO> getHotPostVwCnt();

	List<HotPostDTO> getHotPostLikeCnt();

	List<HotPostDTO> getHotPostNewPost();

}
