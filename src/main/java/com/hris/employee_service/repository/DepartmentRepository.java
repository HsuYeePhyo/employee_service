package com.hris.employee_service.repository;


import com.hris.employee_service.model.Department;
import com.hris.employee_service.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "departments", path = "departments")
public interface DepartmentRepository  extends JpaRepository<Department, String> {
    @Query("select d from Department d where d.companyId = :companyId")
    List<Department> getDeptByCompanyId(@Param("companyId") Long companyId);
}
