package com.hris.employee_service;

import com.hris.employee_service.model.Company;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
class CompanyTest {
    private Company company;

    @BeforeEach
    void setUp() {
        // Create a Company instance for testing
        company = Company.builder()
                .companyId(1L)
                .companyName("Test Company")
                .information("Test Information")
                .contactNumber("123")
                .address("Test Address")
                .onBoardingDate(new Date())
                .offBoardingDate(new Date())
                .build();
    }

    @Test
    void testCompanyFields() {
        // Test the fields of the Company instance
        assertEquals(1L, company.getCompanyId());
        assertEquals("Test Company", company.getCompanyName());
        assertEquals("Test Information", company.getInformation());
        assertEquals("123", company.getContactNumber());
        assertEquals("Test Address", company.getAddress());
    }
}
