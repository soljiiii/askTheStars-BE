package com.solji.star.chatting.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.solji.star.chatting.model.ChatDTO;
import com.solji.star.chatting.model.ChatListDTO;

@Mapper
public interface ChattingMapper {

	void createRoom(ChatListDTO chatListDTO);

	int getRoomNo(String chatTitle);

	List<ChatListDTO> getChatList();

	ChatDTO getChatInfo(int roomId);

	void exitChat(ChatDTO chatDTO);

	int getUserCnt(int chatNo);

	void joinGuest(ChatDTO chatDTO);

	void deleteRoom(ChatDTO chatDTO);

	int checkExistRoom(String chatTitle);


}
