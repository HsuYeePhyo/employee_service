package com.hris.employee_service.repository;


import com.hris.employee_service.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DepartmentRepository  extends JpaRepository<Department, String> {
}
