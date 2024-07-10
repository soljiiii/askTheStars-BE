package com.solji.star.chatting.model;

import lombok.Data;

@Data
public class ChatDTO {
	private int roomId;
	private String userId;
	private String message;
}
