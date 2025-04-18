package com.tnite.jobwinner.repository;

import com.tnite.jobwinner.model.Company;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CompanyRepository extends JpaRepository<Company, UUID>{
    Optional<Company> findByName(String name);
    Optional<Company> findByNameIgnoreCase(String name);
}
