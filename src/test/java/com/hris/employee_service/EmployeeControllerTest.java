package com.hris.employee_service;

import com.hris.employee_service.controller.EmployeeController;
import com.hris.employee_service.model.Company;
import com.hris.employee_service.model.Department;
import com.hris.employee_service.model.Employee;
import com.hris.employee_service.service.CompanyService;
import com.hris.employee_service.service.DepartmentService;
import com.hris.employee_service.service.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class EmployeeControllerTest {

    @Mock
    private CompanyService companyService;

    @Mock
    private EmployeeService employeeService;
    @Mock
    private DepartmentService departmentService;
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

        }
