package com.wbt.companyms.company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    Company create(final CompanyRequest request);

    List<Company> findAll();

    Optional<Company> findById(final Long companyId);

    Boolean delete(final Long companyId);

    Boolean exists(final Long id);

    Boolean update(final Long companyId, final CompanyRequest updateRequest);
}
