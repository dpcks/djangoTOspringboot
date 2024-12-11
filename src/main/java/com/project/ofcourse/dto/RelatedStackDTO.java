package com.project.ofcourse.dto;

import lombok.Data;

@Data
public class RelatedStackDTO {
    private Long id; // pk
    private String relatedStack; // 관련 스택 이름
    private String relatedStackLogo; // 관련스택 로고
    private int relatedStackPk; // stackNameId와 관련된 스택 id
    private Long stackNameId; // 스택 id
}
