package com.hris.employee_service.controller;

        import com.hris.employee_service.model.Employee;
        import com.hris.employee_service.service.EmployeeService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;
        import java.util.Optional;

        //API layer
@RestController
@RequestMapping("api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable String id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/byCompanyId/{companyId}")
    public List<Employee> getEmployeesByCompanyId(@PathVariable String companyId) {
        return employeeService.getEmployeesByCompanyId(companyId);
    }

    @PostMapping
    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable String id) {
        employeeService.deleteEmployee(id);
    }
}
