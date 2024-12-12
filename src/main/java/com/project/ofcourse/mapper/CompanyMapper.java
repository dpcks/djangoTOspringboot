package com.project.ofcourse.mapper;

import com.project.ofcourse.dto.company.CompanyDTO;
import com.project.ofcourse.dto.company.CompanyInfoDTO;
import com.project.ofcourse.dto.stack.StackDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CompanyMapper {

    /**
     * 전체 회사의 개수를 반환합니다.
     *
     * @return 회사의 총 개수
     */
    int countCompanies();

    /**
     * 모든 회사 정보를 페이지네이션 적용하여 가져옵니다.
     *
     * @param offset   데이터 조회 시작 위치
     * @param pageSize 한 페이지에 포함될 데이터 개수
     * @return 페이징된 회사 리스트
     */
    List<CompanyDTO> getAllCompanies(@Param("offset") int offset, @Param("pageSize") int pageSize);

    /**
     * 특정 회사 ID 목록에 해당하는 스택 정보를 가져옵니다.
     *
     * @param companyId 회사 ID 목록
     * @return 해당 회사들과 연결된 스택 리스트
     */
    List<StackDTO> selectStacksByCompanyId(@Param("companyId") List<Long> companyId);

    /**
     * 특정 카테고리에 속하는 회사 정보를 페이지네이션 적용하여 가져옵니다.
     *
     * @param category 회사 카테고리
     * @param offset   데이터 조회 시작 위치
     * @param pageSize 한 페이지에 포함될 데이터 개수
     * @return 특정 카테고리의 페이징된 회사 리스트
     */
    List<CompanyDTO> getCompaniesByCategory(@Param("category") String category,
                                            @Param("offset") int offset,
                                            @Param("pageSize") int pageSize);

    /**
     * 특정 카테고리에 속하는 회사의 총 개수를 반환합니다.
     *
     * @param category 회사 카테고리
     * @return 특정 카테고리의 회사 개수
     */
    int countCompaniesByCategory(@Param("category") String category);

    /**
     * 검색어를 기준으로 회사 정보를 페이지네이션 적용하여 가져옵니다.
     *
     * @param search   검색 키워드
     * @param offset   데이터 조회 시작 위치
     * @param pageSize 한 페이지에 포함될 데이터 개수
     * @return 검색 결과로 필터링된 회사 리스트
     */
    List<CompanyDTO> searchCompany(@Param("search") String search,
                                   @Param("offset") int offset,
                                   @Param("pageSize") int pageSize);

    /**
     * 검색어를 기준으로 회사의 총 개수를 반환합니다.
     *
     * @param search 검색 키워드
     * @return 검색 결과에 해당하는 회사의 총 개수
     */
    int countSearchCompany(@Param("search") String search);

    /**
     * 회사 이름 자동완성을 위한 키워드를 반환합니다.
     *
     * @param keyword 자동완성에 사용되는 키워드
     * @return 키워드와 일치하는 회사 이름 리스트
     */
    List<String> autoKeywordCompany(@Param("keyword") String keyword);

    /**
     * 특정 회사의 상세 정보를 문자열 형태로 가져옵니다.
     *
     * @param companyId 회사 ID
     * @return 회사의 상세 정보 리스트
     */
//    List<String> detailCompany(@Param("companyId") int companyId);

    /**
     * 특정 회사의 기본 정보를 가져옵니다.
     *
     * @param id 회사 ID
     * @return 회사의 기본 정보를 담은 CompanyInfoDTO 객체
     */
    CompanyInfoDTO getCompanyInfoById(Long id);

    /**
     * 특정 회사 ID에 해당하는 모든 스택 정보를 가져옵니다.
     *
     * @param companyId 회사 ID
     * @return 해당 회사의 스택 리스트
     */
    List<StackDTO> getStacksByCompanyId(Long companyId);
}
