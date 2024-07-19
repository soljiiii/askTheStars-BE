package com.solji.star.chatting.controller;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.solji.star.chatting.model.ChatDTO;
import com.solji.star.chatting.model.ChatListDTO;
import com.solji.star.chatting.model.MessageDTO;
import com.solji.star.chatting.service.ChattingService;
import com.solji.star.member.util.JwtUtil;


@RestController
public class ChattingController {
	
	@Autowired
	private ChattingService chattingService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	private final SimpMessagingTemplate messagingTemplate;
	
    @Autowired
    public ChattingController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    
	//방생성 모달을 이용해 DB에 방 생성 정보 저장
	@PostMapping("/createChat")
	public Object createChat(@RequestHeader("Authorization") String authorizationHeader,
							@RequestBody Map<String, String> data) {
		
		String accessToken = authorizationHeader.substring("Bearer ".length()).trim();
		String memberId = jwtUtil.getUserId(accessToken);
		
	    String chatTitle = data.get("chatTitle");
		System.out.println(chatTitle);
		
		ChatListDTO chatListDTO = new ChatListDTO();
		
		chatListDTO.setChatTitle(chatTitle);
		
		 try {
		        int exist = chattingService.checkExistRoom(chatTitle);
		        System.out.println(exist);

		        if (exist == 0) {
		            chattingService.createRoom(chatListDTO);
		            int roomNo = chattingService.getRoomNo(chatTitle);
		            System.out.println("roomNo" + roomNo);
		            return roomNo;
		        } else {
		            return "이미 존재하는 방 이름입니다."; // 클라이언트에서 처리할 메시지 반환
		        }
		        
		    } catch (EmptyResultDataAccessException e) {
		        // 결과가 없는 경우 (단일 결과가 아님)
		        return "Multiple results found for chatTitle: " + chatTitle;
		    } catch (Exception e) {
		        // 기타 예외 처리
		        return "Server error occurred";
		    }
	}

	
	//websocket 서버에 방 생성
	@MessageMapping("/createChatRoom")
	public void createChatRoom(@Payload ChatDTO chatDTO) {
		chattingService.joinGuest(chatDTO);
		System.out.println("채팅방 생성"+chatDTO);
	}
	
	//채팅방 사용자 수 불러오기
	@GetMapping("/getUserCnt/{chatNo}")
	public int getUserCnt(@PathVariable("chatNo") int chatNo) {
		int userCnt = chattingService.getUeserCnt(chatNo);
		return userCnt;
	}
	
	//방 참여
	@MessageMapping("/joinChatRoom")
	public void joinChat(@Payload ChatDTO chatDTO) {
		System.out.println("채팅방 참여");
		chatDTO.setType("join");

		messagingTemplate.convertAndSend("/sub/"+chatDTO.getRoomId(), chatDTO);
	}
	
	//메세지 전송
	@MessageMapping("/sendMessage")
	public void sendMessage(@Payload MessageDTO messageDTO) {
		System.out.println("메세지전송");
		System.out.println(messageDTO);
		
		messagingTemplate.convertAndSend("/sub/"+ messageDTO.getRoomId(), messageDTO);
	}
	
	//방 퇴장
	@MessageMapping("/exitRoom")
	public void leaveChat(@Payload ChatDTO chatDTO) {
		
		System.out.println("방나가기");
		System.out.println(chatDTO);
		chatDTO.setType("exit");
		
		//나가기
		chattingService.exitChat(chatDTO);
		
		int userCnt = chattingService.getUeserCnt(chatDTO.getRoomId());
		System.out.println("userCnt"+userCnt);
		if(userCnt==0) {
			chattingService.deleteRoom(chatDTO);
		}
		
		//퇴장 메세지
		messagingTemplate.convertAndSend("/sub/"+chatDTO.getRoomId(), chatDTO);
	}
	
	//채팅방 목록 보기
	@GetMapping("/getChatList")
	public List<ChatListDTO> getChatList(){
		
		List<ChatListDTO> chatList = chattingService.getChatList();
		return chatList;
	}
	
	//채팅방 정보 가저오기
	@GetMapping("/getChatInfo/{roomId}")
	public ChatDTO getChatInfo(@PathVariable("roomId") int roomId) {
		
		ChatDTO chatDTO = chattingService.getChatInfo(roomId);
		chatDTO.setRoomId(roomId);
		System.out.println("roomId"+roomId);
		return chatDTO;
	}
	
	
}
