package com.solji.star.chatting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solji.star.chatting.mapper.ChattingMapper;

@Service
public class ChattingService {

	@Autowired
	private ChattingMapper chattingMapper;
	
	public void createRoom(String chatTitle) {
		chattingMapper.createRoom(chatTitle);
	}

	public int getRoomNo(String chatTitle) {
		int roomNo = chattingMapper.getRoomNo(chatTitle);
		return roomNo;
	}

}
