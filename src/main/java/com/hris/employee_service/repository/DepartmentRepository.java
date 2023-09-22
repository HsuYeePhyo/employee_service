package com.hris.employee_service.repository;


import com.hris.employee_service.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@RepositoryRestResource(collectionResourceRel = "departments", path = "departments")
public interface DepartmentRepository  extends JpaRepository<Department, String> {
    @Query("select d from Department d where d.companyId = :companyId")
    List<Department> getDeptByCompanyId(@Param("companyId") Long companyId);
}
