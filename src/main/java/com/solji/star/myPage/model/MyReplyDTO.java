package com.solji.star.myPage.model;

import java.util.Date;

import lombok.Data;

@Data
public class MyReplyDTO {
	private int postNo;
	private String replyContent;
	private Date wrtnDate;
}
