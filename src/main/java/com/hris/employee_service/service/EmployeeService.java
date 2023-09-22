package com.hris.employee_service.service;

        import com.hris.employee_service.model.Employee;
        import com.hris.employee_service.model.EmpRole;
        import com.hris.employee_service.model.EmployeeRole;
        import com.hris.employee_service.repository.CompanyRepository;
        import com.hris.employee_service.repository.EmployeeRepository;
        import jakarta.persistence.EntityNotFoundException;
        import lombok.extern.slf4j.Slf4j;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.Optional;

// Business logic methods
@Service
@Slf4j
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, CompanyRepository companyRepository) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
    }
    public Optional<Employee> getEmployeeById(String employeeId) {
        return employeeRepository.findById(employeeId);
    }
    public List<Employee> getEmployeeByCompanyId(Long id){
        return employeeRepository.findByCompanyId(id);
    }
    public List<Employee> getEmployeesByDepartmentId(String departmentId) {
        return employeeRepository.findByDepartmentId(departmentId);
    }
    public Employee saveEmployee(Employee employee, Long companyId) {
        if (companyRepository.findById(companyId).isPresent()) {
            Optional<Employee> existingEmp = employeeRepository.findByEmail(employee.getEmail());
            if (existingEmp.isPresent()) {
                throw new IllegalStateException("Email taken");
            } else {
                employee.setEmpRoles(new ArrayList<>());
                employee.getEmpRoles().add(new EmployeeRole(EmpRole.EMPLOYEE_ROLE));
                return employeeRepository.save(employee);
            }

        } else {
            throw new EntityNotFoundException("Company with ID " + companyId + " not found for new employee");
        }
    }
    public Employee updateEmployee(Long comId, String id, Employee updateEmp){
        Optional<Employee> upd = employeeRepository.findById(id);
        if(upd.isPresent()){
            Employee exist = upd.get();
            exist.setCompanyId(comId);
            exist.setDob(updateEmp.getDob());
            exist.setAddress(updateEmp.getAddress());
            exist.setContactNumber(updateEmp.getContactNumber());
            exist.setEmail(updateEmp.getEmail());
            exist.setBankAccount(updateEmp.getBankAccount());
            exist.setSalary(updateEmp.getSalary());
            exist.setEndDate(updateEmp.getEndDate());
            exist.setJobGradeId(updateEmp.getJobGradeId());
            exist.setEmpRoles(updateEmp.getEmpRoles());
            return employeeRepository.save(exist);
        } else {
            throw new EntityNotFoundException("Employee with ID " + updateEmp.getCompanyId() + " not found for update");
        }
    }
public Employee patchEmployee(Long comId, String id, Employee updateEmp) {
    Optional<Employee> upd = employeeRepository.findById(id);
    if (upd.isPresent()) {
        Employee exist = upd.get();
        exist.setCompanyId(comId);
        if (updateEmp.getDob() != null) {
            exist.setDob(updateEmp.getDob());
        }
        if(updateEmp.getAddress()!= null){
            exist.setAddress(updateEmp.getAddress());
        }
        if(updateEmp.getContactNumber()!= null){
            exist.setContactNumber(updateEmp.getContactNumber());
        }
        if(updateEmp.getEmail()!= null){
            exist.setEmail(updateEmp.getEmail());
        }
        if(updateEmp.getBankAccount()!= null){
            exist.setBankAccount(updateEmp.getBankAccount());
        }
        if(updateEmp.getSalary()!= null){
            exist.setSalary(updateEmp.getSalary());
        }
        if(updateEmp.getEndDate()!= null){
            exist.setEndDate(updateEmp.getEndDate());
        }
        if(updateEmp.getJobGradeId()!= null){
            exist.setJobGradeId(updateEmp.getJobGradeId());
        }
        if(!updateEmp.getEmpRoles().isEmpty()){
            List<EmployeeRole> empRoles = exist.getEmpRoles();
            List<EmployeeRole> newRoles = new ArrayList<>(updateEmp.getEmpRoles());
            newRoles.removeAll(empRoles);
            if(!newRoles.isEmpty()){
                empRoles.addAll(newRoles);
                exist.setEmpRoles(empRoles);
            }else
                log.info("No new employee role to update");
        }
        return employeeRepository.save(exist);
    }
    else{
        throw new EntityNotFoundException("Employee with ID " + updateEmp.getCompanyId() + " not found for patching");
    }
}
    public void deleteEmployee(String id) {
        employeeRepository.deleteById(id);
    }
}
