package com.project.ofcourse.controller.course;

import com.project.ofcourse.dto.PageRequestDTO;
import com.project.ofcourse.dto.PageResponseDTO;
import com.project.ofcourse.dto.course.CourseDTO;
import com.project.ofcourse.dto.stack.StackDTO;
import com.project.ofcourse.service.CourseService;
import com.project.ofcourse.service.StackService;
import com.project.ofcourse.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/course")
@RequiredArgsConstructor
@Log4j2
public class CourseController {

    private final CourseService courseService;

    // 강의 리스트 (필터 기능 , 정렬 기능 포함)
    @GetMapping("/filter")
    public String getCourseList(@RequestParam(value = "sort", required = false, defaultValue = "default") String sort,
                                @RequestParam(value = "stackId", required = false) List<Long> stackIds,
                                PageRequestDTO pageRequest, Model model){
        // 강의 개수 조회
        int totalCourses = stackIds == null || stackIds.isEmpty()
                ? courseService.getTotalCourse()
                : courseService.getTotalCourseByStackIds(stackIds);

        // 강의 목록 조회
        List<CourseDTO> courses = stackIds == null || stackIds.isEmpty()
                ? courseService.getAllCourse(pageRequest.getPage(), pageRequest.getPageSize(), sort)
                : courseService.getCourseByStackIds(stackIds, pageRequest.getPage(), pageRequest.getPageSize());

        // 스택 이름 조회
        List<String> stackNames = stackIds == null || stackIds.isEmpty()
                ? new ArrayList<>()
                : courseService.getStackNamebyIds(stackIds);

        // 페이지네이션 정보 생성
        PageResponseDTO<CourseDTO> pageResponse = PaginationUtil.buildPageResponse(
                courses, totalCourses, pageRequest, 10
        );

        // 모델에 데이터 추가
        model.addAttribute("pageResponse", pageResponse);
        model.addAttribute("stackNames", stackNames); // 필터링된 스택 이름
        model.addAttribute("selectedSort", sort);
        model.addAttribute("stackIds", stackIds); // 필터링된 스택 ID 유지

        return "course/course_list";
    }

    //스택 리스트 (ajax 요청 처리)
    @GetMapping("/stacks")
    @ResponseBody
    public List<StackDTO> getStacksByAssort(@RequestParam String assort) {
        return courseService.getStacksByAssort(assort);
    }

}
