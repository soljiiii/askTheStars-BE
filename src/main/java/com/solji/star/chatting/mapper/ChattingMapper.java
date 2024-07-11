package com.solji.star.chatting.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.solji.star.chatting.model.ChatDTO;
import com.solji.star.chatting.model.ChatListDTO;

@Mapper
public interface ChattingMapper {

	void createRoom(String chatTitle);

	int getRoomNo(String chatTitle);

	List<ChatListDTO> getChatList();

	void chatStateCntDown();

	void chatStateCntUp();

	int getChatState(ChatDTO chatDTO);

	void deleteChat(ChatDTO chatDTO);

}
