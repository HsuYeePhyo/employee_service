package com.hris.employee_service.controller;

        import com.hris.employee_service.model.Company;
        import com.hris.employee_service.model.Department;
        import com.hris.employee_service.model.Employee;
        import com.hris.employee_service.model.EmployeeRole;
        import com.hris.employee_service.service.CompanyService;
        import com.hris.employee_service.service.DepartmentService;
        import com.hris.employee_service.service.EmployeeService;
        import com.hris.employee_service.service.EmpRoleService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;
        import java.util.List;
        import java.util.Optional;

//API layer
@RestController
@RequestMapping("api") //no method at main api
public class EmployeeController {
    private final CompanyService companyService;
    @Autowired
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final EmpRoleService empRoleService;

    @Autowired
    public EmployeeController(CompanyService companyService, EmployeeService employeeService, DepartmentService departmentService, EmpRoleService empRoleService) {

        this.companyService = companyService;
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.empRoleService = empRoleService;
    }
    //Read
    @GetMapping("/company")
    public List<Company> getAllCompany(){
        return companyService.getAllCompany();
    }
    @GetMapping("/company/empRoles")
    public List<EmployeeRole> getAllEmpRoles(){
        return empRoleService.getAllEmpRoles();
    }
    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }
    @GetMapping("/employee/userid/{userid}")
    public Optional<Employee> getEmployeeByUserId(@PathVariable String userid){
        return employeeService.getEmployeeByUserId(userid);
    }
    @GetMapping("/employee/email/{email}")
    public Optional<Employee> getEmployeeByEmail(@PathVariable String email){
    return employeeService.getEmployeeByEmail(email);
    }
    @GetMapping("/company/{id}")
    public Optional<Company> getCompanyById(@PathVariable Long id){
        return companyService.getCompanyById(id);
    }
    //employees
    @GetMapping("/company/{id}/employees")
    public ResponseEntity<List<Employee>> getAllCompanyEmployees(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeByCompanyId(id));
    }
    @GetMapping("/company/{id}/{employeeId}")
    public Optional<Employee> getEmployee(@PathVariable String employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }
    //department
    @GetMapping("/company/{id}/departments")
    public List<Department> getDepartments(@PathVariable Long id){
        return departmentService.getDeptByCompanyId(id);
    }
    @GetMapping("/company/department/{deptId}")
    public Optional<Department> getDepartment(@PathVariable String deptId){
        return departmentService.getDepartment(deptId);
    }
    @GetMapping("/company/department/{deptId}/employees")
    public List<Employee> getEmployeeByDepartmentId(@PathVariable String deptId){
        return employeeService.getEmployeesByDepartmentId(deptId);
    }
    //same method as getEmployee
    @GetMapping("/company/department/employee/{employeeId}")
    public Optional<Employee> getDepartmentEmployee(@PathVariable String employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }
    //Write
    @PostMapping
    public Company saveCompany(@RequestBody Company com){
        return companyService.saveCompany(com);
    }
    @PostMapping("/company/{id}/employee")
    public Employee saveEmployee(@RequestBody Employee employee, @PathVariable Long id) {
        return employeeService.saveEmployee(employee, id);
    }
    @PutMapping("/company/{id}")
    public Company updateCompany(@PathVariable Long id,@RequestBody Company updateCom) {
        return companyService.updateCompany(id, updateCom);
    }
    @PutMapping("/company/{id}/{employeeId}")
    public Employee updateEmployee(@PathVariable Long id, @PathVariable String employeeId, @RequestBody Employee updateEmp) {
        return employeeService.updateEmployee(id, employeeId, updateEmp);
    }
    //department
    @PostMapping("/company/{id}/department")
    public Department saveDepartment(@RequestBody Department dept, @PathVariable Long id) {
        return departmentService.saveDepartment(dept, id);
    }

    //Delete
    @DeleteMapping("/company/{id}")
    public void deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
    }
    @DeleteMapping("/company/{id}/{employeeId}")
    public void deleteEmployee(@PathVariable String employeeId) {
        employeeService.deleteEmployee(employeeId);
    }
    @DeleteMapping("/company/{id}/{deptId}")
    public void deleteDepartment(@PathVariable String deptId){
        departmentService.deleteDepartment(deptId);
    }
}
