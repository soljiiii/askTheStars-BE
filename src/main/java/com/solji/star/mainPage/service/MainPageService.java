package com.solji.star.mainPage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solji.star.mainPage.mapper.MainPageMapper;
import com.solji.star.mainPage.model.HotPostDTO;

@Service
public class MainPageService {

	@Autowired
	private MainPageMapper mainPageMapper;
	
	public HotPostDTO getHotPostVwCnt() {
		HotPostDTO hotPost = mainPageMapper.getHotPostVwCnt();
		return hotPost;
	}

	public HotPostDTO getHotPostLikeCnt() {
		HotPostDTO hotPost = mainPageMapper.getHotPostLikeCnt();
		return hotPost;
	}

	public HotPostDTO getHotPostNewPost() {
		HotPostDTO hotPost = mainPageMapper.getHotPostNewPost();
		return hotPost;
	}

}
