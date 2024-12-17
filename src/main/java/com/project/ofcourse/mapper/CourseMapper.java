package com.project.ofcourse.mapper;

import com.project.ofcourse.dto.course.CourseDTO;
import com.project.ofcourse.dto.stack.StackDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseMapper {
    List<StackDTO> getStacksByAssort(@Param("assort") String assort);

    // 강의 개수
    int getTotalCourse();

    // 무료인 강의 개수
    int getTotalFreeCourse();

    //강의에서 쓰는 스택 가져오기
    List<StackDTO> selectStackByCourseId(@Param("courseId") List<Long> courseId);

    //강의 가져오기
    List<CourseDTO> getAllCourse(@Param("offset") int offset,
                                 @Param("pageSize") int pageSize,
                                 @Param("sort") String sort);

    // 스택별 강의 가져오기
    List<CourseDTO> getCourseByStackIds(@Param("stackId") List<Long> stackId,
                                        @Param("offset") int offset,
                                        @Param("pageSize") int pageSize);

    // 스택별 강의 개수
    int getTotalCourseByStackIds(@Param("stackId") List<Long> stackId);

    // 필터링한 스택 이름 가져오기
    List<String> getStackNamebyIds(List<Long> stackIds);

    // 강의 검색
    List<CourseDTO> searchCourse(@Param("search") String search,
                                 @Param("offset") int offset,
                                 @Param("pageSize") int pageSize);

    // 검색한 강의 개수
    int getTotalSearchCourse(@Param("search") String search);

    // 강의검색 자동완성 키워드
    List<String> autoKeywordCourse(@Param("keyword") String keyword);
}
