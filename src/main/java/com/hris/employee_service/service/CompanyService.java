package com.hris.employee_service.service;

import com.hris.employee_service.model.Company;
import com.hris.employee_service.model.Employee;
import com.hris.employee_service.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompany() {
        return companyRepository.findAll();
    }
    public Optional<Company> getCompanyById (Long companyId){
        return companyRepository.findById(companyId);
    }
    public List<Employee> getEmployeesByCompanyId(Long companyId) {
        return companyRepository.findByCompanyId(companyId);
    }
    public Company saveCompany(Company com) {
        return companyRepository.save(com);
    }
//    public Company updateCompany (Company updateCom){
//        Company exist = companyRepository.getCompanyById(updateCom.getCompanyId());
//        if(exist != null){
//            exist.setCompanyName(updateCom.getCompanyName());
//            exist.setInformation(updateCom.getInformation());
//            exist.setAddress(updateCom.getAddress());
//            exist.setContactNumber(updateCom.getContactNumber());
//            exist.setOffboardingDate(updateCom.getOffboardingDate());
//            exist.setOnboardingDate(updateCom.getOnboardingDate());
//            return companyRepository.save(exist);
//        } else {
//            return null;
//        }
//    }
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }
}
