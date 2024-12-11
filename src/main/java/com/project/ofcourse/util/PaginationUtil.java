package com.project.ofcourse.util;

import com.project.ofcourse.dto.PageRequestDTO;
import com.project.ofcourse.dto.PageResponseDTO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PaginationUtil {

    public static <T> PageResponseDTO<T> buildPageResponse(
            List<T> data, int totalItems, PageRequestDTO pageRequest, int groupSize){

        // 전체 페이지 수 계산 (총 데이터 수 / 페이지 크기, 올림 처리)
        int totalPages = (int) Math.ceil((double) totalItems / pageRequest.getPageSize());

        // 현재 그룹 계산 (현재 페이지 번호 / 그룹 크기, 올림 처리)
        int currentGroup = (int) Math.ceil((double) pageRequest.getPage() / groupSize);

        // 현재 그룹의 시작 페이지 계산
        int startPage = (currentGroup - 1) * groupSize + 1;

        // 현재 그룹의 끝 페이지 계산 (총 페이지 수를 넘지 않도록 최소값 설정)
        int endPage = Math.min(currentGroup * groupSize, totalPages);

        // 시작 페이지부터 끝 페이지까지의 범위 생성
        List<Integer> pageRange = IntStream.rangeClosed(startPage, endPage)
                .boxed()
                .collect(Collectors.toList());

        // PageResponseDTO 객체 생성 및 반환
        return new PageResponseDTO<>(
                data, // 현재 페이지의 데이터 리스트
                pageRequest.getPage(), // 현재 페이지 번호
                totalPages, // 전체 페이지 수
                pageRange, // 현재 그룹의 페이지 범위
                startPage > 1, // 이전 그룹이 존재하는지 여부
                endPage < totalPages, // 다음 그룹이 존재하는지 여부
                Math.max(1, startPage - groupSize), // 이전 그룹의 시작 페이지
                endPage + 1 // 다음 그룹의 시작 페이지
        );
    }
}

//예제
//총 데이터(totalItems): 100개
//현재 페이지 요청(pageRequest.getPage()): 7페이지
//페이지 크기(pageRequest.getPageSize()): 10개 (한 페이지에 10개씩 표시)
//그룹 크기(groupSize): 5페이지 (한 번에 표시되는 페이지 번호가 5개씩)