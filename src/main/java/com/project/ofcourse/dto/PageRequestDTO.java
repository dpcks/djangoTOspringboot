package com.project.ofcourse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {
    // 페이지 요청 관련 데이터를 캡슐화

    private int page = 1; //현재 페이지
    private int pageSize = 12; // 한 페이지당 데이터 수
    private String category = "All"; // 카테고리 (기본값 All)
    private String search = null; // 검색 키워드 (옵션)

    public int getOffset() {
        return (page -1) * pageSize;
    }

}
