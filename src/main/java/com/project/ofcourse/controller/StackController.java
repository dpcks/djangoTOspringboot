package com.project.ofcourse.controller;

import com.project.ofcourse.dto.PageRequestDTO;
import com.project.ofcourse.dto.PageResponseDTO;
import com.project.ofcourse.dto.StackDTO;
import com.project.ofcourse.service.StackService;
import com.project.ofcourse.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
