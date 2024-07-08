package com.solji.star.mainPage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solji.star.mainPage.mapper.MainPageMapper;
import com.solji.star.mainPage.model.HotPostDTO;

@Service
public class MainPageService {

	@Autowired
	private MainPageMapper mainPageMapper;
	
	public List<HotPostDTO> getHotPostVwCnt() {
		List<HotPostDTO> hotPost = mainPageMapper.getHotPostVwCnt();
		return hotPost;
	}

	public List<HotPostDTO> getHotPostLikeCnt() {
		List<HotPostDTO> hotPost = mainPageMapper.getHotPostLikeCnt();
		return hotPost;
	}

	public List<HotPostDTO> getHotPostNewPost() {
		List<HotPostDTO> hotPost = mainPageMapper.getHotPostNewPost();
		return hotPost;
	}

}
