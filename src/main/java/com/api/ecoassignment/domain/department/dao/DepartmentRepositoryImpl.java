package com.api.ecoassignment.domain.department.dao;

import com.api.ecoassignment.domain.country.entity.QCountry;
import com.api.ecoassignment.domain.department.dto.DepartmentResponseDto;
import com.api.ecoassignment.domain.department.entity.QDepartment;
import com.api.ecoassignment.domain.location.entity.QLocation;
import com.api.ecoassignment.domain.region.entity.QRegion;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class DepartmentRepositoryImpl implements DepartmentQueryRepository {

    private final JPAQueryFactory queryFactory;

    private QLocation ql = QLocation.location;

    private QCountry qc = QCountry.country;

    private QDepartment qd = QDepartment.department;

    private QRegion qr = QRegion.region;

    @Override
    public DepartmentResponseDto searchDepartment(Long id) {
        return queryFactory
                .select(
                        Projections.constructor(DepartmentResponseDto.class, qd.departmentId, qd.departmentName,
                                ql.streetAddress, ql.postalCode, ql.city, ql.stateProvince, qc.countryName,
                                qr.regionName))
                .from(qd)
                .leftJoin(ql).on(ql.locationId.eq(qd.location.locationId))
                .leftJoin(qc).on(qc.countryId.eq(ql.country.countryId))
                .leftJoin(qr).on(qr.regionId.eq(qc.region.regionId))
                .where(qd.departmentId.eq(id)).fetchOne();
    }
}