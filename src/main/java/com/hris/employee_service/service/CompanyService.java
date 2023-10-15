package com.hris.employee_service.service;

import com.hris.employee_service.model.Company;
import com.hris.employee_service.repository.CompanyRepository;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Hidden//hide swagger UI end point
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
    public Optional<Company> getCompanyById (Long id){
        return companyRepository.findById(id);
    }
    public Company saveCompany(Company com) {
        return companyRepository.save(com);
    }
    public Company updateCompany (Long companyId, Company updateCom){
        Optional<Company> upd = companyRepository.findById(companyId);
        if(upd.isPresent()){
            Company exist = upd.get();
            exist.setCompanyName(updateCom.getCompanyName());
            exist.setInformation(updateCom.getInformation());
            exist.setAddress(updateCom.getAddress());
            exist.setContactNumber(updateCom.getContactNumber());
            exist.setOnBoardingDate(updateCom.getOnBoardingDate());
            exist.setOffBoardingDate(updateCom.getOffBoardingDate());
            return companyRepository.save(exist);
        } else {
            throw new EntityNotFoundException("Company with ID " + updateCom.getCompanyId() + " not found");
        }
    }
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }
}
