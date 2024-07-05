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
	
	@MessageMapping("/chat/join")
	@SendTo("/topic/chat")
	public String joinChat(String message) {
		
		
		return "User joined the chate: "+ message;
	}
	
	@MessageMapping("/chat/message")
	@SendTo("/topic/chat")
	public String sendMessage(String message) {
		return message;
	}
	
	@MessageMapping("/chat/leave")
	@SendTo("/topic/chat")
	public String leaveChat(String username) {
		return "UserLeft the chat: "+username;
	}
}
