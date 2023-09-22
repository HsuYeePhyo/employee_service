package com.hris.employee_service.repository;

import com.hris.employee_service.model.EmployeeRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
//@RepositoryRestResource(collectionResourceRel = "companies", path = "companies")
public interface EmployeeRoleRepository extends JpaRepository<EmployeeRole, String> {
    @Query("select r from emp_role r where r.name = :role")
    Optional<EmployeeRole> findByEmpRole(@Param("role") String role);
}
