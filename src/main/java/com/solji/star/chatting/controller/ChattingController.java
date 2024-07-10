package com.solji.star.chatting.controller;

import java.util.Map;

import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.solji.star.chatting.model.ChatDTO;
import com.solji.star.chatting.service.ChattingService;


@RestController
public class ChattingController {
	
	@Autowired
	private ChattingService chattingService;
	
	private final SimpMessagingTemplate messagingTemplate;
	
    @Autowired
    public ChattingController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

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
	public void createChatRoom(@Payload ChatDTO chatDTO) {
		System.out.println("[채팅방 생성] Controller 시작!");
	}
	
	//방 참여
	@MessageMapping("/joinChatRoom")
	public void joinChat(@Payload ChatDTO chatDTO) {
		System.out.println("채팅방 참여");
		//참여 인원수 추가
	}
	
	//메세지 전송
	@MessageMapping("/sendMessage")
	public void sendMessage(@Payload ChatDTO chatDTO) {
		System.out.println("메세지전송");
		System.out.println(chatDTO);
		messagingTemplate.convertAndSend("/sub/"+ chatDTO.getRoomId(), chatDTO);
	}
	
	//방 퇴장
	@MessageMapping("/chat/leave")
	public void leaveChat(String username) {
	}
	
	//채팅방 목록 보기
}
