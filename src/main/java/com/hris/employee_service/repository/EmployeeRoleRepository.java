package com.hris.employee_service.repository;

import com.hris.employee_service.model.EmpRole;
import com.hris.employee_service.model.EmployeeRole;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Hidden//hide swagger UI end point
@Repository
//@RepositoryRestResource(collectionResourceRel = "companies", path = "companies")
public interface EmployeeRoleRepository extends JpaRepository<EmployeeRole, EmpRole> {
    @Query("select r from EmployeeRole r where r.name = :role")
    Optional<EmployeeRole> findByEmpRole(@Param("role") EmpRole role);
}
