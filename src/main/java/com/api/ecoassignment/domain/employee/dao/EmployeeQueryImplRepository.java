package com.api.ecoassignment.domain.employee.dao;

import com.api.ecoassignment.domain.employee.dto.response.EmployeeDetailsResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@RequiredArgsConstructor
@Repository
public class EmployeeQueryImplRepository implements EmployeeQueryRepository {

    private final JPAQueryFactory queryFactory;


    @Override
    public EmployeeDetailsResponseDto findEmployeeDetail(Long employeeId) {
        return null;
    }
}
