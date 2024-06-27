package com.solji.star.community.model;

import java.util.List;

import lombok.Data;

@Data
public class WriteListResponseDTO {
    private List<PostDTO> writeList;
    private int totalItems;

    // 생성자 추가
    public WriteListResponseDTO(List<PostDTO> writeList, int totalItems) {
        this.writeList = writeList;
        this.totalItems = totalItems;
    }
}
