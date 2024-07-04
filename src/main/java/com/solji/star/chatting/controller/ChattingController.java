package com.solji.star.chatting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solji.star.chatting.service.ChattingService;


@RestController
public class ChattingController {
	
	@Autowired
	private ChattingService chattingService;

	@PostMapping("/createChat")
	public String createChat(String chatTitle) {
		
		return "ok";
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
