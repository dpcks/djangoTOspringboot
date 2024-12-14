package com.project.ofcourse.dto.stack;

import com.project.ofcourse.dto.company.CompanyDTO;
import com.project.ofcourse.dto.course.CourseDTO;
import lombok.Data;

import java.util.List;

@Data
public class StackDTO {
    private Long id;
    private String name;
    private String logo;
    private String described;
    private String assort; //스택의 카테고리
    private Long companyId;
    private Long courseId;
    private List<RelatedStackDTO> relatedStackList; // 관련스택 리스트
    private List<CompanyDTO> companyList; // 스택을 사용중인 회사 리스트
    private List<CourseDTO> bestCourseList; // 스택 베스트5 강의
}

