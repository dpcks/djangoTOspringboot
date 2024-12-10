package com.project.ofcourse.controller;

import com.project.ofcourse.dto.CompanyDTO;
import com.project.ofcourse.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping
    public String getCompanyList(
            @RequestParam(defaultValue = "All") String category,
            @RequestParam(defaultValue = "1") int page,
            Model model) {
        int pageSize = 12;
        int totalCompanies = category.equals("All")
                ? companyService.getTotalCompanies()
                : companyService.getTotalCompaniesByCategory(category);

        int totalPages = (int) Math.ceil((double) totalCompanies / pageSize);
        List<CompanyDTO> companyList = category.equals("All")
                ? companyService.getCompaniesWithStacks(page, pageSize)
                : companyService.getCompaniesByCategory(category, page, pageSize);

        int groupSize = 10;
        int currentGroup = (int) Math.ceil((double) page / groupSize);
        int startPage = (currentGroup - 1) * groupSize + 1;
        int endPage = Math.min(currentGroup * groupSize, totalPages);
        List<Integer> pageRange = IntStream.rangeClosed(startPage, endPage)
                .boxed()
                .collect(Collectors.toList());

        model.addAttribute("companyList", companyList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("pageRange", pageRange);
        model.addAttribute("hasPreviousGroup", startPage > 1);
        model.addAttribute("hasNextGroup", endPage < totalPages);
        model.addAttribute("previousGroupStart", Math.max(1, startPage - groupSize));
        model.addAttribute("nextGroupStart", endPage + 1);
        model.addAttribute("selectedCategory", category); // 카테고리 정보 추가

        return "company/company_list";
    }

    // 카테고리 필터
    @GetMapping("/filter")
    public String filterByCategory(@RequestParam String category, Model model,
                                   @RequestParam(defaultValue = "1") int page) {
        int pageSize = 12; // 한 페이지당 데이터 개수
        int totalCompanies = companyService.getTotalCompaniesByCategory(category);
        int totalPages = (int) Math.ceil((double) totalCompanies / pageSize);

        List<CompanyDTO> companyList = companyService.getCompaniesByCategory(category, page, pageSize);

        int groupSize = 10;
        int currentGroup = (int) Math.ceil((double) page / groupSize);
        int startPage = (currentGroup - 1) * groupSize + 1;
        int endPage = Math.min(currentGroup * groupSize, totalPages);
        List<Integer> pageRange = IntStream.rangeClosed(startPage, endPage)
                .boxed()
                .collect(Collectors.toList());

        model.addAttribute("companyList", companyList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("pageRange", pageRange);
        model.addAttribute("hasPreviousGroup", startPage > 1);
        model.addAttribute("hasNextGroup", endPage < totalPages);
        model.addAttribute("previousGroupStart", Math.max(1, startPage - groupSize));
        model.addAttribute("nextGroupStart", endPage + 1);
        model.addAttribute("selectedCategory", category); // 현재 선택된 카테고리 추가

        return "company/company_list";
    }

    // 회사 검색
    @GetMapping("/search")
    public String searchCompany(
            @RequestParam String search,
            @RequestParam(defaultValue = "1") int page,
            Model model){
        int pageSize = 12;

        //검색 결과 가져오기
        List<CompanyDTO> companyList =companyService.searchCompany(search, page, pageSize );
        int totalCompanies = companyService.TotalSearchCompany(search);
        int totalPages = (int) Math.ceil((double) totalCompanies / pageSize);

        //페이지네이션 처리
        int groupSize = 10;
        int currentGroup = (int) Math.ceil((double) page / groupSize);
        int startPage = (currentGroup - 1) * groupSize + 1;
        int endPage = Math.min(currentGroup * groupSize, totalPages);
        List<Integer> pageRange = IntStream.rangeClosed(startPage, endPage).boxed().collect(Collectors.toList());

        //모델에 데이터 추가
        model.addAttribute("companyList", companyList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("pageRange", pageRange);
        model.addAttribute("hasPreviousGroup", startPage > 1);
        model.addAttribute("hasNextGroup", endPage < totalPages);
        model.addAttribute("previousGroupStart", Math.max(1, startPage - groupSize));
        model.addAttribute("nextGroupStart", endPage + 1);
        model.addAttribute("search", search);

        return "company/company_list";
    }

}