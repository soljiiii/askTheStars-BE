package com.solji.star.mainPage.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.solji.star.mainPage.model.HotPostDTO;

@Mapper
public interface MainPageMapper {

	HotPostDTO getHotPostVwCnt();

	HotPostDTO getHotPostLikeCnt();

	HotPostDTO getHotPostNewPost();

}
