package com.solji.star.myPage.model;

import java.util.Date;

import lombok.Data;

@Data
public class MyPostDTO {
	private int postNo;
	private String title;
	private Date wrtnDate;
}
