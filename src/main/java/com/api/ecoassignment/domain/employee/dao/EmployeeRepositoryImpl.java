package com.api.ecoassignment.domain.employee.dao;

import com.api.ecoassignment.domain.country.entity.QCountry;
import com.api.ecoassignment.domain.department.entity.QDepartment;
import com.api.ecoassignment.domain.employee.dto.response.EmployeeDetailsResponseDto;
import com.api.ecoassignment.domain.employee.entity.QEmployee;
import com.api.ecoassignment.domain.job.entity.QJob;
import com.api.ecoassignment.domain.location.entity.QLocation;
import com.api.ecoassignment.domain.region.entity.QRegion;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@RequiredArgsConstructor
@Repository
public class EmployeeRepositoryImpl implements EmployeeQueryRepository {

    private final JPAQueryFactory queryFactory;


    private QLocation ql = QLocation.location;

    private QEmployee qe = QEmployee.employee;

    private QCountry qc = QCountry.country;

    private QJob qj = QJob.job;

    private QDepartment qd = QDepartment.department;

    private QRegion qr = QRegion.region;

    @Override
    public EmployeeDetailsResponseDto searchSpecificEmployee(Long employeeId) {
        return queryFactory
                .select(
                        Projections.constructor(EmployeeDetailsResponseDto.class, qe.email, qe.firstName,
                                qe.commissionPct, qe.lastName, qe.phoneNumber,
                                qe.employeeId, qe.salary,
                                qj.jobId, qj.jobTitle, qj.maxSalary, qj.minSalary,
                                qd.departmentId, qd.departmentName,
                                ql.locationId, ql.city, ql.postalCode, ql.stateProvince, ql.streetAddress,
                                qc.countryId, qc.countryName,
                                qr.regionId, qr.regionName))
                .from(qe)
                .leftJoin(qj).on(qe.job.jobId.eq(qj.jobId))
                .leftJoin(qd).on(qe.department.departmentId.eq(qd.departmentId))
                .leftJoin(ql).on(qd.location.locationId.eq(ql.locationId))
                .leftJoin(qc).on(ql.country.countryId.eq(qc.countryId))
                .leftJoin(qr).on(qc.region.regionId.eq(qr.regionId))
                .where(qe.employeeId.eq(employeeId)).fetchOne();


    }

}
