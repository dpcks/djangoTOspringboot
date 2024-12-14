package com.project.ofcourse.dto.course;

import com.project.ofcourse.dto.stack.StackDTO;
import lombok.Data;

import java.util.List;

@Data
public class CourseDTO {
    private Long id;
    private String url;
    private String imgUrl;
    private String title;
    private String headline;
    private String level;
    private Double score;
    private String courseTime;
    private int studentCnt;
    private int recommend;
    private int reviewCnt;
    private Integer price; // null값을 그대로 null로 두기 위해서 타입을 Integer로
    private String teacher;
    private Double sentiment;
    private List<StackDTO> stackList; // 관련 스택 리스트
}
