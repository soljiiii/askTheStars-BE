package com.solji.star.chatting.model;

import lombok.Data;

@Data
public class MessageDTO {
	private String userId;
	private int roomId;
	private String myMessage;
}
