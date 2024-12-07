package com.project.ofcourse.controller;

import com.project.ofcourse.dto.CompanyDTO;
import com.project.ofcourse.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping
    public String CompanyList(@RequestParam(defaultValue = "1") int page, Model model) {
        int pageSize = 15; // 한 페이지당 데이터 개수
        //전체 데이터 개수 계산
        int totalCompanies = companyService.getTotalCompanies();
        int totalPages = (int) Math.ceil((double) totalCompanies / pageSize);

        //회사 리스트 가져오기
        List<CompanyDTO> companyList = companyService.getCompaniesWithStacks(page, pageSize);

        //페이지 번호 범위 계산
        int startPage = Math.max(1, page - 10);
        int endPage = Math.min(totalPages, page + 10);
        List<Integer> pageRange = IntStream.rangeClosed(startPage, endPage)
                .boxed()
                .collect(Collectors.toList());

        //모델에 데이터 추가
        model.addAttribute("companyList", companyList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("pageRange", pageRange); //페이지 범위 추가

        return "company/company_list";
    }

}