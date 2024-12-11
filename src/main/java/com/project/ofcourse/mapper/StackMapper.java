package com.project.ofcourse.mapper;

import com.project.ofcourse.dto.RelatedStackDTO;
import com.project.ofcourse.dto.StackDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StackMapper {

    int countStacks();

    List<StackDTO> getAllStacks(@Param("offset") int offset, @Param("pageSize") int pageSize);

    List<RelatedStackDTO> selectRelatedStackByStackId(@Param("stackId") List<Long> stackId);

}
