package com.project.ofcourse.dto.stack;

import lombok.Data;

import java.util.List;

@Data
public class AssortDTO {
    private String name;
    private List<StackDTO> stackList;
}
