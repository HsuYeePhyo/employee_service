package com.hris.employee_service.model;

import com.hris.employee_service.repository.EmployeeRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Configuration
public class EmpRoleConfig {
    private final Logger logger = LoggerFactory.getLogger(EmpRoleConfig.class);

    @Bean
    CommandLineRunner commandLineRunner(EmployeeRoleRepository employeeRoleRepository) {
        return args -> {
//            logger.info("Initializing EmployeeRole entities...");
//            employeeRoleRepository.save(new EmployeeRole(EmpRole.EMPLOYEE_ROLE));
//            employeeRoleRepository.save(new EmployeeRole(EmpRole.HR_ADMIN_ROLE));
//            employeeRoleRepository.save(new EmployeeRole(EmpRole.RECRUITER_ROLE));
//            employeeRoleRepository.save(new EmployeeRole(EmpRole.MANAGER_ROLE));
//            logger.info("EmployeeRole entities initialized.");
        };
    }
}
