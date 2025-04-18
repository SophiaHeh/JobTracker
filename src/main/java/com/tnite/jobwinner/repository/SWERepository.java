package com.tnite.jobwinner.repository;

import com.tnite.jobwinner.model.SWE;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SWERepository extends JpaRepository<SWE, UUID> {
    List<SWE> findByJobTitleAndCompanyName(String jobTitle, String companyName);
}