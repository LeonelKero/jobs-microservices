package com.wbt.companyms.company.impl;

import com.wbt.companyms.company.Company;
import com.wbt.companyms.company.CompanyRepository;
import com.wbt.companyms.company.CompanyRequest;
import com.wbt.companyms.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(final CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company create(final CompanyRequest request) {
        return this.companyRepository.save(new Company(request.name(), request.description()));
    }

    @Override
    public List<Company> findAll() {
        return this.companyRepository.findAll();
    }

    @Override
    public Optional<Company> findById(final Long companyId) {
        return this.companyRepository.findById(companyId);
    }

    @Override
    public Boolean delete(final Long companyId) {
        if (this.findById(companyId).isPresent()) {
            this.companyRepository.deleteById(companyId);
            return true;
        }
        return false;
    }

    @Override
    public Boolean exists(final Long companyId) {
        return this.companyRepository.existsById(companyId);
    }

    @Override
    public Boolean update(final Long companyId, final CompanyRequest updateRequest) {
        final var optionalCompany = this.findById(companyId);
        if (optionalCompany.isPresent()) {
            final var company = optionalCompany.get();
            company.setName(updateRequest.name());
            company.setDescription(updateRequest.description());
            this.companyRepository.save(company);
            return true;
        }
        return false;
    }
}
