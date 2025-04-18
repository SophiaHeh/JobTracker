package com.tnite.jobwinner.repository;

import com.tnite.jobwinner.model.SWE;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SWERepository extends JpaRepository<SWE, UUID> {
    List<SWE> findByJobTitleAndCompany_Name(String jobTitle, String companyName);

    boolean existsByJobTitleIgnoreCaseAndCompany_NameIgnoreCase(String canonicalJob, String canonicalCompany);
//    List<SWE> findAll();
//    List<SWE> findById(UUID jobId);
}