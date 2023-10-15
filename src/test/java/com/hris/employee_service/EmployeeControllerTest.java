package com.hris.employee_service;

import com.hris.employee_service.controller.EmployeeController;
import com.hris.employee_service.model.*;
import com.hris.employee_service.service.CompanyService;
import com.hris.employee_service.service.DepartmentService;
import com.hris.employee_service.service.EmpRoleService;
import com.hris.employee_service.service.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Mock
    private CompanyService companyService;

    @Mock
    private EmployeeService employeeService;
    @Mock
    private DepartmentService departmentService;
    @Mock
    private EmpRoleService empRoleService;
    @InjectMocks
    private EmployeeController employeeController;
    private AutoCloseable closeable;
    @BeforeEach
    public void openMocks() {
        closeable = MockitoAnnotations.openMocks(this);
     }
    @AfterEach
    public void releaseMocks() throws Exception {
        closeable.close();
    }
    @Test
    void testGetAllCompany() {
        List<Company> companies = new ArrayList<>();
        when(companyService.getAllCompany()).thenReturn(companies);
        List<Company> result = employeeController.getAllCompany();
        verify(companyService).getAllCompany();
        assertEquals(companies, result);
    }
    @Test
    void testGetAllEmpRoles() {
        List<EmployeeRole> empRoles = new ArrayList<>();
        empRoles.add(new EmployeeRole("1", EmpRole.EMPLOYEE_ROLE));
        empRoles.add(new EmployeeRole("2", EmpRole.MANAGER_ROLE));
        when(empRoleService.getAllEmpRoles()).thenReturn(empRoles);
        List<EmployeeRole> response = employeeController.getAllEmpRoles();
        verify(empRoleService, times(1)).getAllEmpRoles();
        assertNotNull(response);
        assertEquals(empRoles, response);
    }
    @Test
    void testGetAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee ("1", "Test1"));
        employees.add(new Employee ("2", "Test2"));
        when(employeeService.getAllEmployees()).thenReturn(employees);
        List<Employee> response = employeeController.getAllEmployees();
        verify(employeeService).getAllEmployees();
        assertEquals(employees, response);
    }
    @Test
    void testGetEmployeeByUserId(){
        Employee emp = new Employee ("1", "Test1");
        String userId = "00";
        emp.setUserId("00");
        when(employeeService.getEmployeeByUserId(userId)).thenReturn(Optional.of(emp));
        Optional<Employee> response = employeeController.getEmployeeByUserId(emp.getUserId());
        if(response.isPresent()) {
            Employee e = response.get();
            assertEquals(emp.getFullName(), e.getFullName());
            assertEquals(emp.getEmployeeId(), e.getEmployeeId());
        }
    }
    @Test
    void testGetEmployeeByEmail(){
        Employee emp = new Employee ("1", "Test1", "test1@gmail.com");
        String email = "test1@gmail.com";
        when(employeeService.getEmployeeByEmail(email)).thenReturn(Optional.of(emp));
        Optional<Employee> response = employeeController.getEmployeeByEmail(emp.getEmail());
        if(response.isPresent()) {
            Employee e = response.get();
            assertEquals(emp.getFullName(), e.getFullName());
            assertEquals(emp.getEmployeeId(), e.getEmployeeId());
        }
    }
    @Test
    void testGetCompanyById() {
        Long companyId = 1L;
        Company company = new Company();
        company.setCompanyId(1L);
        company.setCompanyName("New Company");
        when(companyService.getCompanyById(companyId)).thenReturn(Optional.of(company));
        Optional<Company> response = employeeController.getCompanyById(companyId);
        if(response.isPresent()) {
            Company com = response.get();
            assertEquals("New Company", com.getCompanyName());
            assertEquals(1L, com.getCompanyId());
        }
    }
    @Test
    void testGetAllCompanyEmployees() {
        Long companyId = 1L;
        List<Employee> employees = new ArrayList<>();
        Employee emp = new Employee();
        emp.setCompanyId(1L);
        emp.setFullName("Test Employee");
        employees.add(emp);
        when(employeeService.getEmployeeByCompanyId(companyId)).thenReturn(employees);
        ResponseEntity<List<Employee>> response = employeeController.getAllCompanyEmployees(companyId);
        verify(employeeService).getEmployeeByCompanyId(companyId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employees, response.getBody());
    }
    @Test
    void testGetEmployee() {
        String employeeId = "12345";
        Employee expectedEmployee = new Employee();
        expectedEmployee.setEmployeeId(employeeId);
        expectedEmployee.setFullName("Test");
        expectedEmployee.setEmail("test@gmail.com");
        when(employeeService.getEmployeeById(employeeId)).thenReturn(Optional.of(expectedEmployee));
        Optional<Employee> result = employeeController.getEmployee(employeeId);
        verify(employeeService).getEmployeeById(employeeId);
        assertTrue(result.isPresent());
        assertEquals(expectedEmployee, result.get());
    }
    @Test
    void testGetDepartments(){
        Long companyId = 1L;
        Department dept = new Department();
        dept.setCompanyId(1L);
        dept.setDepartmentName("Test Department");
        dept.setDepartmentId("123");
        List<Department> expectedDept = new ArrayList<>();
        expectedDept.add(dept);
        when(departmentService.getDeptByCompanyId(companyId)).thenReturn(expectedDept);
        List<Department> result = employeeController.getDepartments(companyId);
        verify(departmentService).getDeptByCompanyId(companyId);
        assertEquals(expectedDept, result);
    }
    @Test
    void testGetDepartment(){
        String deptId = "123";
        Department dept = new Department();
        dept.setDepartmentId("123");
        dept.setCompanyId(1L);
        dept.setDepartmentName("Test Department");
        when(departmentService.getDepartment(deptId)).thenReturn(Optional.of(dept));
        Optional<Department> response = employeeController.getDepartment(deptId);
        verify(departmentService).getDepartment(deptId);
        assertTrue(response.isPresent());
        assertEquals(dept, response.get());
    }
    @Test
    void testGetEmployeeByDepartmentId(){
        String deptId = "123";
        Department dept = new Department();
        dept.setDepartmentId("123");
        dept.setCompanyId(1L);
        List<Employee> employees = new ArrayList<>();
        Employee emp = new Employee();
        emp.setCompanyId(1L);
        emp.setDepartment(dept);
        emp.setFullName("Test Employee");
        employees.add(emp);
        when(employeeService.getEmployeesByDepartmentId(deptId)).thenReturn(employees);
        List<Employee> response = employeeController.getEmployeeByDepartmentId(deptId);
        verify(employeeService).getEmployeesByDepartmentId(deptId);
        assertEquals(employees, response);
    }
    @Test
    void testGetDepartmentEmployee() {
        String employeeId = "12345";
        Department dept = new Department();
        dept.setDepartmentId("123");
        dept.setCompanyId(1L);
        Employee expectedEmployee = new Employee();
        expectedEmployee.setDepartment(dept);
        expectedEmployee.setEmployeeId(employeeId);
        expectedEmployee.setFullName("Test");
        expectedEmployee.setEmail("test@gmail.com");
        when(employeeService.getEmployeeById(employeeId)).thenReturn(Optional.of(expectedEmployee));
        Optional<Employee> result = employeeController.getDepartmentEmployee(employeeId);
        verify(employeeService).getEmployeeById(employeeId);
        assertTrue(result.isPresent());
        assertEquals(expectedEmployee, result.get());
    }
    @Test
    void testSaveCompany(){
        // Create a sample Company object
        Company com = new Company(1L, "Test Company");
        Mockito.when(companyService.saveCompany(Mockito.any(Company.class))).thenReturn(com);
        Company newCom = employeeController.saveCompany(com);
        assertEquals(com, newCom);
    }
    @Test
    void testSaveEmployee(){
        Employee emp = new Employee ("1", "Test Emp");
        emp.setCompanyId(1L);
        Mockito.when(employeeService.saveEmployee(Mockito.any(Employee.class), Mockito.anyLong())).thenReturn(emp);
        Employee newEmp = employeeController.saveEmployee(emp, 1L);
        assertEquals(emp, newEmp);
    }
    @Test
    void testUpdateCompany(){
        Company updateCom = new Company(1L, "Updated Company");
        //mock service
        when(companyService.updateCompany(Mockito.anyLong(), Mockito.any(Company.class)))
                .thenReturn(updateCom);
        Company result = employeeController.updateCompany(1L, updateCom);
        assertEquals(updateCom, result);
    }
    @Test
    void testUpdateEmployee(){
        Employee emp = new Employee("1", "Test Employee");
        emp.setCompanyId(1L);
        //mock service
        when(employeeService.updateEmployee(Mockito.anyLong(), Mockito.anyString(),Mockito.any(Employee.class)))
                .thenReturn(emp);
        Employee result = employeeController.updateEmployee(1L, emp.getEmployeeId(), emp);
        assertEquals(emp, result);
    }
    @Test
    void testSaveDepartment() {
        Department dep = new Department("1", "Test Dept");
        dep.setCompanyId(1L);
        when(departmentService.saveDepartment(any(Department.class), anyLong())).thenReturn(dep);
        Department newDep = employeeController.saveDepartment(dep, 1L);
        assertEquals(dep, newDep);
    }
    @Test
    void testDeleteCompany() {
        Long companyIdToDelete = 1L;
        doNothing().when(companyService).deleteCompany(companyIdToDelete);
        employeeController.deleteCompany(companyIdToDelete);
        verify(companyService, times(1)).deleteCompany(companyIdToDelete);
    }
    @Test
    void testDeleteEmployee(){
        String employeeIdToDelete = "1";
        doNothing().when(employeeService).deleteEmployee(employeeIdToDelete);
        employeeController.deleteEmployee(employeeIdToDelete);
        verify(employeeService,times(1)).deleteEmployee(employeeIdToDelete);
    }
    @Test
    void testDeleteDepartment(){
        String depIdToDelete = "1";
        doNothing().when(departmentService).deleteDepartment(depIdToDelete);
        employeeController.deleteDepartment(depIdToDelete);
        verify(departmentService,times(1)).deleteDepartment(depIdToDelete);
    }

        }
