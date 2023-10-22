package com.hris.employee_service.repository;

import com.hris.employee_service.model.Company;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
@Hidden//hide swagger UI end point
//@RepositoryRestResource(collectionResourceRel = "companies", path = "companies")
public interface CompanyRepository   extends JpaRepository<Company, Long> {
}
