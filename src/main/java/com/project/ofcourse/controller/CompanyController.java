package com.project.ofcourse.controller;

import com.project.ofcourse.dto.CompanyDTO;
import com.project.ofcourse.dto.PageRequestDTO;
import com.project.ofcourse.dto.PageResponseDTO;
import com.project.ofcourse.service.CompanyService;
import com.project.ofcourse.util.PaginationUtil;
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
    public String getCompanyList(PageRequestDTO pageRequest, Model model) {
        int totalCompanies = pageRequest.getCategory().equals("All")
                ? companyService.getTotalCompanies()
                : companyService.getTotalCompaniesByCategory(pageRequest.getCategory());

        List<CompanyDTO> companyList = pageRequest.getCategory().equals("All")
                ? companyService.getCompaniesWithStacks(pageRequest.getPage(), pageRequest.getPageSize())
                : companyService.getCompaniesByCategory(pageRequest.getCategory(), pageRequest.getPage(), pageRequest.getPageSize());

        PageResponseDTO<CompanyDTO> pageResponse = PaginationUtil.buildPageResponse(
                companyList, totalCompanies, pageRequest, 10);

//        model.addAttribute("companyList", companyList);
        model.addAttribute("pageResponse", pageResponse);
        model.addAttribute("selectedCategory", pageRequest.getCategory()); // 카테고리 정보 추가

        return "company/company_list";
    }

    // 카테고리 필터
    @GetMapping("/filter")
    public String filterByCategory(PageRequestDTO pageRequest, Model model) {
        pageRequest.setCategory(pageRequest.getCategory() == null ? "All" : pageRequest.getCategory());
        return getCompanyList(pageRequest, model);
    }

    // 회사 검색
    @GetMapping("/search")
    public String searchCompany(@RequestParam String search, PageRequestDTO pageRequest, Model model){
        pageRequest.setSearch(search);
        int totalCompanies = companyService.TotalSearchCompany(search);
        List<CompanyDTO> companyList = companyService.searchCompany(search, pageRequest.getPage(), pageRequest.getPageSize());

        if (companyList.isEmpty()) {
            model.addAttribute("errorMessage", "검색 결과가 없습니다.");
            model.addAttribute("search", search);
            return "error/no_result"; // 검색 결과 없음 시 오류 페이지
        }

        PageResponseDTO<CompanyDTO> pageResponse = PaginationUtil.buildPageResponse(
                companyList, totalCompanies, pageRequest, 10);

        //모델에 데이터 추가
        model.addAttribute("pageResponse", pageResponse);
        model.addAttribute("search", search);

        return "company/company_list";
    }

}