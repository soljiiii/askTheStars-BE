package com.solji.star.community.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("post")
public class PostDTO {
	private int postNo;
	private String memberId;
	private String title;
	private String postContent;
	private int vwCnt;
	private Date wrtnDate;
	private String isDeleted;
	private int likedCnt;
}
