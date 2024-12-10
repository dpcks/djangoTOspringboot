package com.project.ofcourse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResponseDTO<T> {
    // 페이지네이션 결과와 메타데이터를 캡슐화

    private List<T> data; //데이터 리스트
    private int currentPage; // 현재 페이지
    private int totalPages; // 전체 페이지 수
    private List<Integer> pageRange; // 페이지 범위
    private boolean hasPreviousGroup; // 이전 그룹 존재 여부
    private boolean hasNextGroup; // 다음 그룹 존재 여부
    private int previousGroupStart; // 이전 그룹 시작 페이지
    private int nextGroupStart; // 다음 그룹 시작 페이지

}

