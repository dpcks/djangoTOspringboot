package com.project.ofcourse.util;

import com.project.ofcourse.dto.PageRequestDTO;
import com.project.ofcourse.dto.PageResponseDTO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PaginationUtil {

    public static <T> PageResponseDTO<T> buildPageResponse(
            List<T> data, int totalItems, PageRequestDTO pageRequest, int groupSize){

        int totalPages = (int) Math.ceil((double) totalItems / pageRequest.getPageSize());
        int currentGroup = (int) Math.ceil((double) pageRequest.getPage() / groupSize);
        int startPage = (currentGroup - 1) * groupSize + 1;
        int endPage = Math.min(currentGroup * groupSize, totalPages);

        List<Integer> pageRange = IntStream.rangeClosed(startPage, endPage)
                .boxed()
                .collect(Collectors.toList());

        return new PageResponseDTO<>(
                data,
                pageRequest.getPage(),
                totalPages,
                pageRange,
                startPage > 1,
                endPage < totalPages,
                Math.max(1, startPage - groupSize),
                endPage + 1
        );

    }

}
