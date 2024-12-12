package com.project.ofcourse.service;

import com.project.ofcourse.dto.stack.RelatedStackDTO;
import com.project.ofcourse.dto.stack.StackDTO;
import com.project.ofcourse.mapper.StackMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class StackService {

    private final StackMapper stackMapper;

    public int getTotalStacks() {
        return stackMapper.countStacks();
    }

    public List<StackDTO> getAllStacks(int pageNumber, int pageSize){
        // 페이지 시작위치 계산
        int offset = (pageNumber - 1) * pageSize;

        //1. 스택 정보 조회
        List<StackDTO> stacks = stackMapper.getAllStacks(offset, pageSize);
        List<Long> stackId = stacks.stream()
                .map(StackDTO::getId)
                .collect(Collectors.toList());

        //2. 관련 스택 정보 조회
        List<RelatedStackDTO> relatedStacks = stackMapper.selectRelatedStackByStackId(stackId);

        //3. 스택 ID별 relatedStacks 리스트 매핑
        Map<Long, List<RelatedStackDTO>> relatedStackByStackId = relatedStacks.stream()
                .collect(Collectors.groupingBy(
                        relatedStack ->
                            // 키가 null이면 -1 등 특정 기본값 반환
                             Optional.ofNullable(relatedStack.getStackNameId()).orElse(-1L),
                        // groupingBy의 downstream 처리: 빈 리스트를 기본값으로 반환
                        Collectors.toCollection(ArrayList::new)
                ));

        //4. StackDTO에 relatedStack 매핑
        for (StackDTO stack : stacks) {
            stack.setRelatedStackList(relatedStackByStackId.getOrDefault(stack.getId(), new ArrayList<>()));
        }

        return stacks;
    }

    //카테고리별 스택 필터 로직
    public List<StackDTO> getStacksByAssort(String assort, int pageNumber, int pageSize) {
        //페이지 시작위치 계산
        int offset = (pageNumber - 1) * pageSize;

        //1. 스택 정보 조회
        List<StackDTO> stacks = stackMapper.getStacksByAssort(assort, offset, pageSize);
        List<Long> stackId = stacks.stream()
                .map(StackDTO::getId)
                .collect(Collectors.toList());

        //2. 관련 스택 정보 조회
        List<RelatedStackDTO> relatedStacks = stackMapper.selectRelatedStackByStackId(stackId);

        //3. 스택 ID별 relatedStacks 리스트 매핑
        Map<Long, List<RelatedStackDTO>> relatedStackByStackId = relatedStacks.stream()
                .collect(Collectors.groupingBy(
                        relatedStack -> Optional.ofNullable(relatedStack.getStackNameId()).orElse(-1L),
                        Collectors.toCollection(ArrayList::new)
                ));

        //4. StackDTO에 relatedStack 매핑
        for (StackDTO stack : stacks) {
            stack.setRelatedStackList(relatedStackByStackId.getOrDefault(stack.getId(), new ArrayList<>()));
        }

        return stacks;
    }

    public int countStacksByAssort(String assort) {
        return stackMapper.countStacksByAssort(assort);
    }

    // 스택 검색 로직
    public List<StackDTO> searchStack(String search, int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;

        List<StackDTO> stacks = stackMapper.searchStack(search, offset, pageSize);
        List<Long> stackId = stacks.stream()
                .map(StackDTO::getId)
                .collect(Collectors.toList());

        List<RelatedStackDTO> relatedStacks = stackMapper.selectRelatedStackByStackId(stackId);

        Map<Long, List<RelatedStackDTO>> relatedStackByStackId = relatedStacks.stream()
                .collect(Collectors.groupingBy(
                        relatedStack -> Optional.ofNullable(relatedStack.getStackNameId()).orElse(-1L),
                        Collectors.toCollection(ArrayList::new)
                ));

        for (StackDTO stack : stacks) {
            stack.setRelatedStackList(relatedStackByStackId.getOrDefault(stack.getId(), new ArrayList<>()));
        }

        return stacks;
    }

    public int TotalSearchStack(String search) {
        return stackMapper.countSearchStack(search);
    }

    // 검색어 자동완성
    public List<String> autoKeywordStack(String keyword) {
        return stackMapper.autoKeywordStack(keyword);
    }
}
