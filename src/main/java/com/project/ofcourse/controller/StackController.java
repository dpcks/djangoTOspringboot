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

    @GetMapping
    public String getStackList(PageRequestDTO pageRequest, Model model) {
        int totalStacks = stackService.getTotalStacks();

        List<StackDTO> stackList = stackService.getAllStacks(pageRequest.getPage(), pageRequest.getPageSize());

        PageResponseDTO<StackDTO> pageResponse = PaginationUtil.buildPageResponse(
                stackList, totalStacks, pageRequest, 10);

        model.addAttribute("pageResponse",pageResponse);

        return "stack/stack_list";
    }
}
