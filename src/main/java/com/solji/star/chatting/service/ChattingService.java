package com.solji.star.chatting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solji.star.chatting.mapper.ChattingMapper;
import com.solji.star.chatting.model.ChatDTO;
import com.solji.star.chatting.model.ChatListDTO;

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

	public List<ChatListDTO> getChatList() {
		List<ChatListDTO> chatList = chattingMapper.getChatList();
		return chatList;
	}

	public void chatStateCntDown() {
		chattingMapper.chatStateCntDown();	
	}

	public void chatStateCntUp() {
		chattingMapper.chatStateCntUp();
	}

	public int getChatState(ChatDTO chatDTO) {
		int result = chattingMapper.getChatState(chatDTO);
		return result;
	}

	public void deleteChat(ChatDTO chatDTO) {
		chattingMapper.deleteChat(chatDTO);
	}

}
