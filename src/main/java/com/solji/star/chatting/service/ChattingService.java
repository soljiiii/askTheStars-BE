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
	
	public void createRoom(ChatListDTO chatListDTO) {
		chattingMapper.createRoom(chatListDTO);
	}

	public int getRoomNo(String chatTitle) {
		int roomNo = chattingMapper.getRoomNo(chatTitle);
		return roomNo;
	}

	public List<ChatListDTO> getChatList() {
		List<ChatListDTO> chatList = chattingMapper.getChatList();
		return chatList;
	}

	public ChatDTO getChatInfo(int roomId) {
		ChatDTO chatDTO = chattingMapper.getChatInfo(roomId);
		return chatDTO;
	}

	public void exitChat(ChatDTO chatDTO) {
		chattingMapper.exitChat(chatDTO);		
	}

	public int getUeserCnt(int chatNo) {
		int userCnt = chattingMapper.getUserCnt(chatNo);
		return userCnt;
	}

	public void joinGuest(ChatDTO chatDTO) {
		chattingMapper.joinGuest(chatDTO);		
	}

	public void deleteRoom(ChatDTO chatDTO) {
		chattingMapper.deleteRoom(chatDTO);		
	}

}
