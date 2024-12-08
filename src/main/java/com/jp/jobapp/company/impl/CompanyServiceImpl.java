package com.jp.jobapp.company.impl;

import com.jp.jobapp.company.Company;
import com.jp.jobapp.company.CompanyRepository;
import com.jp.jobapp.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company findCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public boolean updateCompany(Company company, Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()) {
            Company company1 = companyOptional.get();
            company1.setName(company.getName());
            company1.setDescription(company.getDescription());
            company1.setJobs(company.getJobs());
            companyRepository.save(company1);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        try {
            if (companyRepository.existsById(id)) {
                companyRepository.deleteById(id);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }


}
