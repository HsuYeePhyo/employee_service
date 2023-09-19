package com.hris.employee_service.repository;

import com.hris.employee_service.model.Company;
import com.hris.employee_service.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository   extends JpaRepository<Company, Long> {
    List<Employee> findByCompanyId(@Param("companyId") Long companyId);

   // @Query("select c from company c where c.company_id = :companyId")
   // Company getCompanyById(Long companyId);
}
