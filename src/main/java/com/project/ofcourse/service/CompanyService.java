package com.project.ofcourse.service;

import com.project.ofcourse.dto.stack.AssortDTO;
import com.project.ofcourse.dto.company.CompanyDTO;
import com.project.ofcourse.dto.company.CompanyInfoDTO;
import com.project.ofcourse.dto.stack.StackDTO;
import com.project.ofcourse.mapper.CompanyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class CompanyService {
    private final CompanyMapper companyMapper;

    public int getTotalCompanies() {
        return companyMapper.countCompanies();
    }
    // pageNumber -> 현재 요청한 페이지 번호, pageSize -> 한 페이지에 표시할 데이터의 개수
    public List<CompanyDTO> getCompaniesWithStacks(int pageNumber, int pageSize) {
        // 페이지 시작위치 계산
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

    // 검색어 자동완성
    public List<String> autoKeywordCompany(String keyword) {
        return companyMapper.autoKeywordCompany(keyword);
    }

    // 회사 세부정보
    public CompanyInfoDTO getCompanyInfoById(Long id) {
        //회사 기본 정보 가져오기
        CompanyInfoDTO companyInfo = companyMapper.getCompanyInfoById(id);

        //해당 회사와 연결된 스택 가져오기
        List<StackDTO> stacks = companyMapper.getStacksByCompanyId(id);

        //스택을 AssortDTO로 그룹화
        Map<String, List<StackDTO>> assortMap = stacks.stream()
                .collect(Collectors.groupingBy(StackDTO::getAssort));

//        log.info("assortMap ==> "+assortMap);

        List<AssortDTO> assortList = assortMap.entrySet().stream()
                .map(entry -> {
                    AssortDTO assort = new AssortDTO();
                    assort.setName(entry.getKey());
                    assort.setStackList(entry.getValue());
                    return assort;
                }).collect(Collectors.toList());

        companyInfo.setAssortList(assortList);
//        log.info("=================================================================");
//        log.info("assortList => "+assortList);

        return companyInfo;

    }

}

