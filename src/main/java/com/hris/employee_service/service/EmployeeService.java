package com.hris.employee_service.service;

        import com.hris.employee_service.model.Employee;
        import com.hris.employee_service.repository.EmployeeRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.List;
        import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Business logic methods

    public List<Employee> getAllEmployees() {
        return (List<Employee>) employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(String employeeId) {
        return employeeRepository.findById(employeeId);
    }

    public List<Employee> getEmployeesByCompanyId(String companyId) {
        return employeeRepository.findByCompanyId(companyId);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(String id) {
        employeeRepository.deleteById(id);
    }
}
