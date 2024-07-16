package com.solji.star.community.model;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("like")

public class LikeDTO {
	private int likeNo;
	private String memberId;
	private int postNo;
}
