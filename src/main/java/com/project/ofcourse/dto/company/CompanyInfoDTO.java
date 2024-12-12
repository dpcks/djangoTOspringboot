package com.project.ofcourse.dto.company;

import com.project.ofcourse.dto.stack.AssortDTO;
import lombok.Data;

import java.util.List;

@Data
public class CompanyInfoDTO {
    private Long id; // 회사 ID
    private String name; // 회사 이름
    private String logo; // 회사 로고 URL
    private String stackInfo; // 스택 관련 요약 정보
    private String companyLink; // 회사 웹사이트 링크
    private String companyRecruitLink; // 채용 링크
    private String category; // 회사 카테고리
    private List<AssortDTO> assortList; //스택 카테고리 리스트
}