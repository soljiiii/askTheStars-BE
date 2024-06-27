package com.solji.star.community.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solji.star.community.mapper.CommunityMapper;
import com.solji.star.community.model.PostDTO;

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

}
