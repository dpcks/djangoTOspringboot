package com.project.ofcourse.controller;

import com.project.ofcourse.dto.PageRequestDTO;
import com.project.ofcourse.dto.PageResponseDTO;
import com.project.ofcourse.dto.stack.StackDTO;
import com.project.ofcourse.service.StackService;
import com.project.ofcourse.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/stack")
@RequiredArgsConstructor
public class StackController {

    private final StackService stackService;

    // 스택 리스트
    @GetMapping
    public String getStackList(PageRequestDTO pageRequest, Model model) {
        int totalStacks = pageRequest.getAssort().equals("All")
                ? stackService.getTotalStacks()
                : stackService.countStacksByAssort(pageRequest.getAssort());

        List<StackDTO> stackList = pageRequest.getAssort().equals("All")
                ? stackService.getAllStacks(pageRequest.getPage(), pageRequest.getPageSize())
                :stackService.getStacksByAssort(pageRequest.getAssort(), pageRequest.getPage(), pageRequest.getPageSize());

        PageResponseDTO<StackDTO> pageResponse = PaginationUtil.buildPageResponse(
                stackList, totalStacks, pageRequest, 10);

        model.addAttribute("pageResponse",pageResponse);
        model.addAttribute("selectedAssort", pageRequest.getAssort());

        return "stack/stack_list";
    }

    // 카테고리 필터
    @GetMapping("/filter")
    public String filterByAssort(PageRequestDTO pageRequest, Model model) {
        pageRequest.setAssort(pageRequest.getAssort() == null ? "All" : pageRequest.getAssort());
        return getStackList(pageRequest, model);
    }

    // 스택 검색
    @GetMapping("/search")
    public String searchStack(@RequestParam String search, PageRequestDTO pageRequest, Model model) {
        pageRequest.setSearch(search);

        int totalStacks = stackService.TotalSearchStack(search);
        List<StackDTO> stackList = stackService.searchStack(search, pageRequest.getPage(), pageRequest.getPageSize());

        if (stackList.isEmpty()) {
            model.addAttribute("errorMessage", "검색 결과가 없습니다.");
            model.addAttribute("search", search);
            return "error/no_result_stack";
        }

        PageResponseDTO<StackDTO> pageResponse = PaginationUtil.buildPageResponse(
                stackList, totalStacks, pageRequest, 10);

        model.addAttribute("pageResponse", pageResponse);
        model.addAttribute("search", search);

        return "stack/stack_list";
    }
}
