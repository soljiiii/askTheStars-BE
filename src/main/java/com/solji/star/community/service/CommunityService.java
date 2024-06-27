package com.solji.star.community.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.solji.star.community.mapper.CommunityMapper;
import com.solji.star.community.model.PostDTO;

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

}