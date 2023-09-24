package com.hris.employee_service;

import com.hris.employee_service.controller.EmployeeController;
import com.hris.employee_service.model.Company;
import com.hris.employee_service.model.Employee;
import com.hris.employee_service.service.CompanyService;
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
import static org.mockito.Mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


class EmployeeControllerTest {

    @Mock
    private CompanyService companyService;

    @Mock
    private EmployeeService employeeService;
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
}
