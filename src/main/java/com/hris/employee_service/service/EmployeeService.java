package com.hris.employee_service.service;

        import com.hris.employee_service.model.Employee;
        import com.hris.employee_service.repository.EmployeeRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.List;
        import java.util.Optional;

        import java.util.UUID;
        import java.util.logging.Logger;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Business logic methods

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(String employeeId) {
        return employeeRepository.findById(employeeId);
    }

    public List<Employee> getEmployeesByDepartmentId(String departmentId) {
        return employeeRepository.findByDepartmentId(departmentId);
    }
    public Employee saveEmployee(Employee employee) {
Optional<Employee> existingEmp = employeeRepository.findByEmail(employee.getEmail());
if(existingEmp.isPresent()){
    throw  new IllegalStateException("Email taken");
}
return employeeRepository.save(employee);
    }

    public void deleteEmployee(String id) {
        employeeRepository.deleteById(id);
    }
}
