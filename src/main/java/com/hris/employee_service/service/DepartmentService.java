package com.hris.employee_service.service;

import com.hris.employee_service.model.Department;
import com.hris.employee_service.repository.CompanyRepository;
import com.hris.employee_service.repository.DepartmentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private CompanyRepository companyRepository;
    @Autowired
    public DepartmentService (DepartmentRepository departmentRepository){
        this.departmentRepository = departmentRepository;
    }
    public List<Department> getDeptByCompanyId(Long compId){
        return departmentRepository.getDeptByCompanyId(compId);
    }
public Department saveDepartment(Department dept, Long companyId){
    if (companyRepository.findById(companyId).isPresent()) {
        dept.setCompanyId(companyId);
        return departmentRepository.save(dept);
    }else
        throw new EntityNotFoundException("Company with ID " + companyId + " not found for new department");
}
    public void deleteDepartment(String id){
        departmentRepository.deleteById(id);
        log.info("Department {} is deleted", departmentRepository.findById(id).get().getDepartmentName());
    }
}
