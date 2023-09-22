package com.hris.employee_service.repository;

import com.hris.employee_service.model.Company;
import com.hris.employee_service.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
//@RepositoryRestResource(collectionResourceRel = "companies", path = "companies")
public interface CompanyRepository   extends JpaRepository<Company, Long> {
    Optional<Company> findById(@Param("companyId") Long companyId);
}