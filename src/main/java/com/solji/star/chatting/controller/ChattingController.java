package com.solji.star.chatting.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.solji.star.chatting.service.ChattingService;


@RestController
public class ChattingController {
	
	@Autowired
	private ChattingService chattingService;

	//방생성 모달을 이용해 DB에 방 생성 정보 저장
	@PostMapping("/createChat")
	public Object createChat(@RequestBody Map<String, String> data) {
	    String chatTitle = data.get("chatTitle");
		System.out.println(chatTitle);
		
		try {
            chattingService.createRoom(chatTitle);
            int roomNo = chattingService.getRoomNo(chatTitle);
            return roomNo;
        } 
		catch (EmptyResultDataAccessException e) {
            // 결과가 없는 경우 (단일 결과가 아님)
            return "Multiple results found for chatTitle: " + chatTitle;
        } 
		catch (Exception e) {
            // 기타 예외 처리
            return "Server error occurred";
        }
	}
	
	//websocket 서버에 방 생성
	@MessageMapping("/createChatRoom")
	@SendTo("/topic/{roomId}")
	public void createChatRoom(@PathVariable String roomId) {
		System.out.println("[채팅방 생성] Controller 시작!");
//		chattingService.
	}
	
	//방 참여
	@MessageMapping("/joinChatRoom")
	@SendTo("/topic/{roomId}")
	public String joinChat(@PathVariable String roomId) {
		
		System.out.println("채팅방 참여");
		return "User joined the chate: ";
	}
	
	//메세지 전송
	@MessageMapping("/sendMessage")
	@SendTo("/topic/{roomId}")
	public String sendMessage(@PathVariable String roomId) {
		System.out.println("메세지전송");
		return "ㅎ";
	}
	
//	//방 퇴장
//	@MessageMapping("/chat/leave")
//	@SendTo("/topic/chat")
//	public String leaveChat(String username) {
//		return "UserLeft the chat: "+username;
//	}
}
