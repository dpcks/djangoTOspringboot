package com.project.ofcourse.mapper;

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


}
