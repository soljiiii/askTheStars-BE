package com.solji.star.community.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("reply")
@Data
public class ReplyList {
	private int replyNo;
	private int postNo;
	private String memberId;
	private String replyContent;
	private Date wrtnDate;
}
