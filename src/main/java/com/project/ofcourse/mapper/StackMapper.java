package com.project.ofcourse.mapper;

import com.project.ofcourse.dto.company.CompanyDTO;
import com.project.ofcourse.dto.course.CourseDTO;
import com.project.ofcourse.dto.stack.RelatedStackDTO;
import com.project.ofcourse.dto.stack.StackDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StackMapper {

    int countStacks();

    List<StackDTO> getAllStacks(@Param("offset") int offset, @Param("pageSize") int pageSize);

    List<RelatedStackDTO> selectRelatedStackByStackId(@Param("stackId") List<Long> stackId);

    List<StackDTO> getStacksByAssort(@Param("assort") String assort,
                                       @Param("offset") int offset,
                                       @Param("pageSize") int pageSize);

    int countStacksByAssort(@Param("assort") String assort);

    List<StackDTO> searchStack(@Param("search") String search,
                               @Param("offset") int offset,
                               @Param("pageSize") int pageSize);

    int countSearchStack(@Param("search") String search);

    // 스택이름 자동완성 키워드
    List<String> autoKeywordStack(@Param("keyword") String keyword);

    // 특정 스택 정보 가져오기
    StackDTO getStackInfoById(Long id);

    // 특정 스택 ID에 해당하는 모든 회사 정보를 가져옴.
    List<CompanyDTO> getCompaniesByStackId(Long stackId);

    // 특정 스택 ID에 해당하는 강의 5개를 가져옴.
    List<CourseDTO> getBestCourseByStackId(Long stackId);
}
