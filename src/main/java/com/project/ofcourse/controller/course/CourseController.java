package com.project.ofcourse.controller.course;

import com.project.ofcourse.dto.PageRequestDTO;
import com.project.ofcourse.dto.PageResponseDTO;
import com.project.ofcourse.dto.course.CourseDTO;
import com.project.ofcourse.dto.stack.StackDTO;
import com.project.ofcourse.service.CourseService;
import com.project.ofcourse.service.StackService;
import com.project.ofcourse.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    //강의 리스트
    @GetMapping
    public String getCourseList(PageRequestDTO pageRequest, Model model){
        int totalCousre = courseService.getTotalCourse();

        List<CourseDTO> courseList = courseService.getAllCourse(pageRequest.getPage(), pageRequest.getPageSize());


        PageResponseDTO<CourseDTO> pageResponse = PaginationUtil.buildPageResponse(
                courseList, totalCousre, pageRequest, 10);

        model.addAttribute("pageResponse", pageResponse);
        return "course/course_list";
    }

    //스택 리스트 (ajax 요청 처리)
    @GetMapping("/stacks")
    @ResponseBody
    public List<StackDTO> getStacksByAssort(@RequestParam String assort) {
        return courseService.getStacksByAssort(assort);
    }

}
