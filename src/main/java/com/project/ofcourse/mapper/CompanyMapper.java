package com.project.ofcourse.mapper;

import com.project.ofcourse.dto.CompanyDTO;
import com.project.ofcourse.dto.StackDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Stack;

@Mapper
public interface CompanyMapper {

    /**
     * 모든 회사 정보를 가져옵니다.
     * 각 회사는 관련된 기술 스택과 함께 반환됩니다.
     * @return List<CompanyDTO>
     */
    List<CompanyDTO> getAllCompanies(@Param("offset") int offset, @Param("pageSize") int pageSize);

    List<StackDTO> selectStacksByCompanyId(@Param("companyId") List<Long> companyId);

    int countCompanies();
}
