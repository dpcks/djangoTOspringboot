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

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/course")
@RequiredArgsConstructor
@Log4j2
public class CourseController {

    private final CourseService courseService;

    //강의 리스트
    @GetMapping
    public String getCourseList(@RequestParam(defaultValue = "default") String sort,
                                PageRequestDTO pageRequest,
                                Model model) {
        // 정렬 기준 설정
        pageRequest.setSort(sort);

        // 총 강의 수 가져오기
        int totalCourse = sort.equals("free")
                ? courseService.getTotalFreeCourse()
                : courseService.getTotalCourse();

        // 강의 리스트 가져오기
        List<CourseDTO> courseList = courseService.getAllCourse(
                pageRequest.getPage(),
                pageRequest.getPageSize(),
                sort
        );

        // 페이지 응답 데이터 생성
        PageResponseDTO<CourseDTO> pageResponse = PaginationUtil.buildPageResponse(
                courseList,
                totalCourse,
                pageRequest,
                10
        );

        // 모델에 데이터 추가
        model.addAttribute("pageResponse", pageResponse);
        model.addAttribute("selectedSort", sort);

        return "course/course_list";
    }


    //스택 리스트 (ajax 요청 처리)
    @GetMapping("/stacks")
    @ResponseBody
    public List<StackDTO> getStacksByAssort(@RequestParam String assort) {
        return courseService.getStacksByAssort(assort);
    }

    // 스택별 강의 가져오기
    @GetMapping("/filter")
    public String getCourseByStackIds(@RequestParam List<Long> stackId, PageRequestDTO pageRequest, Model model) {
        int totalCourse = courseService.getTotalCourseByStackIds(stackId);

        //필터된 강의 리스트
        List<CourseDTO> filterCourseList = courseService.getCourseByStackIds(stackId, pageRequest.getPage(), pageRequest.getPageSize());

        //스택 이름 가져오기
        List<String> stackNames = courseService.getStackNamebyIds(stackId);

//        log.info("========================================================");
//        log.info("stackID ===============================> " + stackId);

        PageResponseDTO<CourseDTO> pageResponse = PaginationUtil.buildPageResponse(
                filterCourseList, totalCourse, pageRequest, 10
        );

        model.addAttribute("pageResponse", pageResponse);
        model.addAttribute("stackNames", stackNames);

        return "course/course_list";
    }

}
