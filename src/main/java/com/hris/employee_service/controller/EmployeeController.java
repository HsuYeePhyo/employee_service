package com.hris.employee_service.controller;

        import com.hris.employee_service.model.Company;
        import com.hris.employee_service.model.Department;
        import com.hris.employee_service.model.Employee;
        import com.hris.employee_service.service.CompanyService;
        import com.hris.employee_service.service.DepartmentService;
        import com.hris.employee_service.service.EmployeeService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.ResponseEntity;
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
    private DepartmentService departmentService;

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
    public Optional<Company> getCompanyById(@PathVariable Long id){
        return companyService.getCompanyById(id);
    }
    //employees
    @GetMapping("/{id}/employees")
    public List<Employee> getAllCompanyEmployees(@PathVariable Long id) {
        return employeeService.getEmployeeByCompanyId(id);
    }
    @GetMapping("/{id}/{employeeId}")
    public Optional<Employee> getEmployee(@PathVariable String employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }
    //department
    @GetMapping("/{id}/departments")
    public List<Department> getDepartments(@PathVariable Long id){
        return departmentService.getDeptByCompanyId(id);
    }
    @GetMapping("/{id}/department/{deptId}/employees")
    public List<Employee> getEmployeeByDepartmentId(@PathVariable String deptId){
        return employeeService.getEmployeesByDepartmentId(deptId);
    }
    @GetMapping("/{id}/department/{deptId}/employee/{employeeId}")
    public Optional<Employee> getDepartmentEmployee(@PathVariable String employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }
    //Write
    @PostMapping
    public Company saveCompany(@RequestBody Company com){
        return companyService.saveCompany(com);
    }
    @PostMapping("/{id}")
    public Employee saveEmployee(@RequestBody Employee employee, @PathVariable Long id) {
        return employeeService.saveEmployee(employee, id);
    }
    @PutMapping("/{id}")
    public Company updateCompany(@PathVariable Long id,@RequestBody Company updateCom) {
        return companyService.updateCompany(id, updateCom);
    }
    @PutMapping("/{id}/{employeeId}")
    public Employee updateEmployee(@PathVariable Long id, String employeeId, @RequestBody Employee updateEmp) {
        return employeeService.updateEmployee(id, employeeId, updateEmp);
    }
    @PatchMapping("/{id}/{employeeId}")
    public Employee patchEmployee(@PathVariable Long id, String employeeId, @RequestBody Employee updateEmp) {
        return employeeService.patchEmployee(id, employeeId, updateEmp);
    }
    //department
    @PostMapping("/{id}/department")
    public Department saveDepartment(@RequestBody Department dept, @PathVariable Long compId) {
        return departmentService.saveDepartment(dept, compId);
    }

    //Delete
    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
    }
    @DeleteMapping("/{id}/{employeeId}")
    public void deleteEmployee(@PathVariable String employeeId) {
        employeeService.deleteEmployee(employeeId);
    }
    @DeleteMapping("{id}/{deptId}")
    public void delteDepartment(@PathVariable String deptId){
        departmentService.deleteDepartment(deptId);
    }
}
