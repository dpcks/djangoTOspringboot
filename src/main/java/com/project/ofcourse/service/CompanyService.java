package com.project.ofcourse.service;

import com.project.ofcourse.dto.CompanyDTO;
import com.project.ofcourse.dto.StackDTO;
import com.project.ofcourse.mapper.CompanyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyMapper companyMapper;

    public int getTotalCompanies() {
        return companyMapper.countCompanies();
    }

    public List<CompanyDTO> getCompaniesWithStacks(int pageNumber, int pageSize) {
        // 페이지 시작위치 께산
        int offset = (pageNumber - 1) * pageSize;

        //1. 회사 정보 조회
        List<CompanyDTO> companies = companyMapper.getAllCompanies(offset, pageSize);
        List<Long> companyId = companies.stream()
                .map(CompanyDTO::getId)
                .collect(Collectors.toList());

        //2. 스택 정보 조회
        List<StackDTO> stacks = companyMapper.selectStacksByCompanyId(companyId);

//        System.out.println("stacks -> "+stacks);

        //3. 회사 ID별 Stack 리스트 매핑
        Map<Long, List<StackDTO>> stacksByCompanyId = stacks.stream()
                .collect(Collectors.groupingBy(StackDTO::getCompanyId));

//        System.out.println("stacksByCompanyId -> "+stacksByCompanyId);


        //4. CompanyDTO에  Stack 리스트 매핑
        for (CompanyDTO company : companies) {
            company.setStackList(stacksByCompanyId.getOrDefault(company.getId(), new ArrayList<>()));
        }

        return companies;
    }

    //카테고리별 회사 필터 로직
    public List<CompanyDTO> getCompaniesByCategory(String category, int pageNumber, int pageSize) {
        // 페이지 시작위치 계산
        int offset = (pageNumber - 1) * pageSize;

        //1. 회사 정보 조회
        List<CompanyDTO> companies = companyMapper.getCompaniesByCategory(category, offset, pageSize);
        List<Long> companyId = companies.stream()
                .map(CompanyDTO::getId)
                .collect(Collectors.toList());

        //2. 스택 정보 조회
        List<StackDTO> stacks = companyMapper.selectStacksByCompanyId(companyId);

        //3. 회사 ID별 Stack 리스트 매핑
        Map<Long, List<StackDTO>> stacksByCompanyId = stacks.stream()
                .collect(Collectors.groupingBy(StackDTO::getCompanyId));

        //4. CompanyDTO에  Stack 리스트 매핑
        for (CompanyDTO company : companies) {
            company.setStackList(stacksByCompanyId.getOrDefault(company.getId(), new ArrayList<>()));
        }

        return companies;
    }

    public int getTotalCompaniesByCategory(String category) {
        return companyMapper.countCompaniesByCategory(category);
    }


    // 회사 검색 로직
    public List<CompanyDTO> searchCompany(String search, int pageNumber, int pageSize) {
        // 페이지 시작위치 계산
        int offset = (pageNumber - 1) * pageSize;

        //1. 회사 정보 조회
        List<CompanyDTO> companies = companyMapper.searchCompany(search, offset, pageSize);
        List<Long> companyId = companies.stream()
                .map(CompanyDTO::getId)
                .collect(Collectors.toList());

        //2. 스택 정보 조회
        List<StackDTO> stacks = companyMapper.selectStacksByCompanyId(companyId);

        //3. 회사 ID별 Stack 리스트 매핑
        Map<Long, List<StackDTO>> stacksByCompanyId = stacks.stream()
                .collect(Collectors.groupingBy(StackDTO::getCompanyId));

        //4. CompanyDTO에  Stack 리스트 매핑
        for (CompanyDTO company : companies) {
            company.setStackList(stacksByCompanyId.getOrDefault(company.getId(), new ArrayList<>()));
        }

        return companies;
    }

    public int TotalSearchCompany(String search) {
        return companyMapper.countSearchCompany(search);
    }

}

