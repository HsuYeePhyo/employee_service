package com.hris.employee_service.controller;

        import com.hris.employee_service.model.Company;
        import com.hris.employee_service.model.Employee;
        import com.hris.employee_service.service.CompanyService;
        import com.hris.employee_service.service.EmployeeService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;
        import java.util.Optional;

//API layer
@RestController
@RequestMapping("api/company")
public class EmployeeController {
    private final CompanyService companyService;
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(CompanyService companyService) {
        this.companyService = companyService;
    }
    //Read
    @GetMapping("")
    public List<Company> getAllCompany(){
        return companyService.getAllCompany();
    }
    @GetMapping("/{id}")
    public List<Employee> getAllCompanyEmployees(@PathVariable Long id) {
        return companyService.getEmployeesByCompanyId(id);
    }
    @GetMapping("/{id}/{employeeId}")
    public Optional<Employee> getEmployee(@PathVariable String employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }
    //Write
    @PostMapping
    public Company saveCompany(@RequestBody Company com){
        return companyService.saveCompany(com);
    }
    @PostMapping("/{id}")
    public Employee saveEmployee(@RequestBody Employee employee, @PathVariable String id) {
        employee.setCompanyId(id);
        return employeeService.saveEmployee(employee);
    }
//    @PutMapping("/{id}")
//    public Company updateCompany(@RequestBody Company updateCom) {
//        return companyService.updateCompany(updateCom);
//    }

    //Delete
    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
    }
    @DeleteMapping("/{id}/{employeeId}")
    public void deleteEmployee(@PathVariable String employeeId) {
        employeeService.deleteEmployee(employeeId);
    }
}
