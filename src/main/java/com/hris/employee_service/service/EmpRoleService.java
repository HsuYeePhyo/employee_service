package com.hris.employee_service.service;

import com.hris.employee_service.model.EmployeeRole;
import com.hris.employee_service.repository.EmployeeRoleRepository;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Hidden//hide swagger UI end point
@Service
@Slf4j
public class EmpRoleService {
    private final EmployeeRoleRepository employeeRoleRepository;

    @Autowired
    public EmpRoleService(EmployeeRoleRepository employeeRoleRepository){
        this.employeeRoleRepository = employeeRoleRepository;
    }
    public List<EmployeeRole> getAllEmpRoles() {return employeeRoleRepository.findAll();}
}
