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
        int pageSize = 12; // 한 페이지당 데이터 개수
        //전체 데이터 개수 계산
        int totalCompanies = companyService.getTotalCompanies();
        int totalPages = (int) Math.ceil((double) totalCompanies / pageSize);

        //회사 리스트 가져오기
        List<CompanyDTO> companyList = companyService.getCompaniesWithStacks(page, pageSize);

        //페이지 번호 범위 계산 (10개씩 표기)
        int groupSize = 10; //한 번에 보여줄 페이지 번호 개수
        int currentGroup = (int) Math.ceil((double) page / groupSize); //현재 그룹 계산
        int startPage = (currentGroup - 1) * groupSize +1; // 그룹의 시작 페이지
        int endPage = Math.min(currentGroup * groupSize, totalPages); //그룹의 마지막 페이지
        List<Integer> pageRange = IntStream.rangeClosed(startPage, endPage)
                .boxed()
                .collect(Collectors.toList());

        //모델에 데이터 추가
        model.addAttribute("companyList", companyList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("pageRange", pageRange); // 현재 페이지 그룹 범위
        model.addAttribute("hasPreviousGroup", startPage > 1); // 이전 그룹이 있는지
        model.addAttribute("hasNextGroup", endPage < totalPages); // 다음 그룹이 있는지
        model.addAttribute("previousGroupStart", Math.max(1, startPage - groupSize)); // 이전 그룹 시작 페이지
        model.addAttribute("nextGroupStart", endPage + 1); // 다음 그룹 시작 페이지

        return "company/company_list";
    }

}