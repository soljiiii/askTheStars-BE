package com.solji.star.chatting.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChattingMapper {

	void createRoom(String chatTitle);

	int getRoomNo(String chatTitle);

}
