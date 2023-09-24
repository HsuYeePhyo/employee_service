package com.hris.employee_service.service;

import com.hris.employee_service.model.Department;
import com.hris.employee_service.repository.CompanyRepository;
import com.hris.employee_service.repository.DepartmentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final CompanyRepository companyRepository;
    @Autowired
    public DepartmentService (DepartmentRepository departmentRepository, CompanyRepository companyRepository){
        this.departmentRepository = departmentRepository;
        this.companyRepository = companyRepository;
    }
    public List<Department> getDeptByCompanyId(Long compId){
        return departmentRepository.getDeptByCompanyId(compId);
    }
    public Optional<Department> getDepartment(String id){
        return departmentRepository.findById(id);
    }
public Department saveDepartment(Department dept, Long companyId){
    if (companyRepository.findById(companyId).isPresent()) {
        dept.setCompanyId(companyId);
        log.info("Company" + dept);
        return departmentRepository.save(dept);
    }else
        throw new EntityNotFoundException("Company with ID " + companyId + " not found for new department");
}
    public void deleteDepartment(String id){
        departmentRepository.deleteById(id);
        log.info("Department {} is deleted", departmentRepository.findById(id).get().getDepartmentName());
    }
}
