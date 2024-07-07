package com.solji.star.mainPage.model;

import java.util.Date;

import lombok.Data;

@Data
public class HotPostDTO {
	private String memberId;
	private String title;
	private int vwCnt;
	private Date wrtnDate;
	private int likedCnt;
}
