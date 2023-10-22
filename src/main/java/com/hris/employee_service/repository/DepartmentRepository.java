package com.hris.employee_service.repository;

import com.hris.employee_service.model.Department;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Hidden//hide swagger UI end point
@Repository
//@RepositoryRestResource(collectionResourceRel = "departments", path = "departments")
public interface DepartmentRepository  extends JpaRepository<Department, String> {
    @Query("select d from Department d where d.companyId = :companyId")
    List<Department> getDeptByCompanyId(@Param("companyId") Long companyId);
}
