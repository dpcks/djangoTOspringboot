package com.project.ofcourse.dto;

import lombok.Data;

@Data
public class StackDTO {
    private Long id;
    private String name;
    private String logo;
    private String described;
    private String assort; //스택의 카테고리
    private Long companyId;
}
