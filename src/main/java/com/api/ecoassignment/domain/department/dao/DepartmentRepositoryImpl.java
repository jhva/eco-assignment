package com.api.ecoassignment.domain.department.dao;

import com.api.ecoassignment.domain.country.entity.QCountry;
import com.api.ecoassignment.domain.department.dto.response.DepartmentAndEmployeeDto;
import com.api.ecoassignment.domain.department.dto.response.DepartmentResponseDto;
import com.api.ecoassignment.domain.department.entity.QDepartment;
import com.api.ecoassignment.domain.employee.entity.QEmployee;
import com.api.ecoassignment.domain.job.entity.QJob;
import com.api.ecoassignment.domain.location.entity.QLocation;
import com.api.ecoassignment.domain.region.entity.QRegion;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.math.BigDecimal;
import java.util.List;
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
    private QJob qj = QJob.job;
    private QEmployee qe = QEmployee.employee;


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

    @Override
    public List<DepartmentAndEmployeeDto> searchDepartmentByDepartmentName(String name) {
        List<DepartmentAndEmployeeDto> queryFindDepartment = queryFactory
                .select(Projections.constructor(DepartmentAndEmployeeDto.class, qe.employeeId, qd.departmentName,
                        qe.salary, qj.minSalary, qj.maxSalary))
                .from(qd)
                .leftJoin(qe).on(qd.departmentId.eq(qe.department.departmentId))
                .leftJoin(qj).on(qj.jobId.eq(qe.job.jobId))
                .where(qd.departmentName.eq(name)).fetch();

        return queryFindDepartment;
    }

    @Override
    public void updateDepartmentByDepartmentName(BigDecimal resultSum, Long id) {
        queryFactory.update(qe)
                .set(qe.salary, resultSum)
                .where(qe.employeeId.eq(id))
                .execute();
    }
}