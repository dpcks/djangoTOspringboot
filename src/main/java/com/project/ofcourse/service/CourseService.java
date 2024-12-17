package com.project.ofcourse.service;

import com.project.ofcourse.dto.course.CourseDTO;
import com.project.ofcourse.dto.stack.StackDTO;
import com.project.ofcourse.mapper.CourseMapper;
import com.project.ofcourse.mapper.StackMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class CourseService {

    private final CourseMapper courseMapper;
    private final StackMapper stackMapper;

    //강의리스트에 스택 assort 필터
    public List<StackDTO> getStacksByAssort(String assort) {
        return courseMapper.getStacksByAssort(assort);
    }

    public int getTotalCourse(){
        return courseMapper.getTotalCourse();
    }

    // 전체 강의 리스트 가져오기
    public List<CourseDTO> getAllCourse(int pageNumber, int pageSize, String sort) {
        int offset = (pageNumber - 1) * pageSize;

        // 강의정보조회
        List<CourseDTO> courses = courseMapper.getAllCourse(offset, pageSize, sort);
        List<Long> courseId = courses.stream()
                .map(CourseDTO::getId)
                .collect(Collectors.toList());

        // 강의 스택 조회
        List<StackDTO> stacks = courseMapper.selectStackByCourseId(courseId);


        //강의 id별 stack리스트 그룹핑
        Map<Long, List<StackDTO>> stacksbyCourseId = stacks.stream()
                .collect(Collectors.groupingBy(StackDTO::getCourseId));

        for (CourseDTO course : courses) {
            course.setStackList(stacksbyCourseId.getOrDefault(course.getId(), new ArrayList<>()));
        }

        for (CourseDTO course : courses) {
            if (course.getSentiment() != null) {
                //소수점 둘째자리 반올림
                course.setSentiment(Math.round(course.getSentiment() * 100.0) / 100.0);
            }
        }
        return courses;
    }

    // 무료인 강의 개수
    public  int getTotalFreeCourse() {
        return courseMapper.getTotalFreeCourse();
    }

    // 스택별 강의 조회
    public List<CourseDTO> getCourseByStackIds(List<Long> stackIds, int pageNumber, int pageSize) {

        int offset = (pageNumber - 1) * pageSize;

        List<CourseDTO> courses = courseMapper.getCourseByStackIds(stackIds, offset, pageSize);

        if (courses.isEmpty()) {
            return new ArrayList<>();
        }

        //강의 ID 목록 추출
        List<Long> courseIds = courses.stream()
                .map(CourseDTO::getId)
                .collect(Collectors.toList());

        //강의 ID별 스택 조회
        List<StackDTO> stacks = courseMapper.selectStackByCourseId(courseIds);

        //강의 ID별 스택 리스트 그룹핑
        Map<Long, List<StackDTO>> stacksByCourseId = stacks.stream()
                .collect(Collectors.groupingBy(StackDTO::getCourseId));

        // 각 강의에 스택 리스트 설정
        for (CourseDTO course : courses) {
            course.setStackList(stacksByCourseId.getOrDefault(course.getId(), new ArrayList<>()));
        }

        // 감정값(sentiment)을 소수점 둘째 자리까지 반올림
        courses.forEach(course -> {
            if (course.getSentiment() != null) {
                course.setSentiment(Math.round(course.getSentiment() * 100.0) / 100.0);
            }
        });
        return courses;
    }

    // 스택별 강의 개수
    public int getTotalCourseByStackIds(List<Long> stackId){
        return courseMapper.getTotalCourseByStackIds(stackId);
    }

    // 스택 이름 가져오기
    public List<String> getStackNamebyIds(List<Long> stackIds) {
        return courseMapper.getStackNamebyIds(stackIds);
    }

    // 강의 검색
    public List<CourseDTO> searchCourse(String search, int pageNumber, int pageSize) {

        int offset = (pageNumber - 1) * pageSize;

        List<CourseDTO> courses = courseMapper.searchCourse(search, offset, pageSize);

        if (courses.isEmpty()) {
            return new ArrayList<>();
        }

        //강의 ID 목록 추출
        List<Long> courseIds = courses.stream()
                .map(CourseDTO::getId)
                .collect(Collectors.toList());

        //강의 ID별 스택 조회
        List<StackDTO> stacks = courseMapper.selectStackByCourseId(courseIds);

        //강의 ID별 스택 리스트 그룹핑
        Map<Long, List<StackDTO>> stacksByCourseId = stacks.stream()
                .collect(Collectors.groupingBy(StackDTO::getCourseId));

        // 각 강의에 스택 리스트 설정
        for (CourseDTO course : courses) {
            course.setStackList(stacksByCourseId.getOrDefault(course.getId(), new ArrayList<>()));
        }

        // 감정값(sentiment)을 소수점 둘째 자리까지 반올림
        courses.forEach(course -> {
            if (course.getSentiment() != null) {
                course.setSentiment(Math.round(course.getSentiment() * 100.0) / 100.0);
            }
        });
        return courses;
    }

    // 검색한 강의 개수
    public int getTotalSearchCourse(String search) {
        return courseMapper.getTotalSearchCourse(search);
    }
}
