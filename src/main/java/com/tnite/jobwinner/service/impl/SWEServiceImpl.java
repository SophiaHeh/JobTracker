package com.tnite.jobwinner.service.impl;


import com.tnite.jobwinner.model.*;
import com.tnite.jobwinner.repository.SWERepository;
import com.tnite.jobwinner.repository.CompanyRepository;
import com.tnite.jobwinner.repository.PersonRepository;
import com.tnite.jobwinner.repository.CodingProblemRepository;
import com.tnite.jobwinner.service.SWEService;
import jakarta.transaction.Transactional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class SWEServiceImpl implements SWEService {
    private static final Logger logger = LoggerFactory.getLogger(SWEServiceImpl.class);

    @Autowired
    private final SWERepository sweRepository;
    private final CompanyRepository companyRepository;
    private final PersonRepository personRepository;
    private final CodingProblemRepository codingProblemRepository;

    @Autowired
    public SWEServiceImpl(SWERepository sweRepository, CompanyRepository companyRepository,
                        PersonRepository personRepository, CodingProblemRepository codingProblemRepository) {
        this.sweRepository = sweRepository;
        this.companyRepository = companyRepository;
        this.personRepository = personRepository;
        this.codingProblemRepository = codingProblemRepository;
    }

    @Override
    @Transactional
    public SWE save(SWE swe) {
        /* -------- A) Basic validation -------- */
        if (swe.getCompany() == null) {
            throw new IllegalArgumentException("Company must not be null.");
        }
        if (swe.getSalary() <= 0.0) {
            throw new IllegalArgumentException("Salary should be greater than 0.");
        }

        /* -------- B) Canonicalise -------- */
        String canonicalJob     = swe.getJobTitle().trim().toLowerCase();
        String canonicalCompany = swe.getCompany().getName().trim().toLowerCase();

        swe.setJobTitle(canonicalJob);                  // <-- write back
        swe.getCompany().setName(canonicalCompany);     // <-- write back

        /* -------- C) Duplicate check -------- */
        if (sweRepository.existsByJobTitleIgnoreCaseAndCompany_NameIgnoreCase(
            canonicalJob, canonicalCompany)) {
            throw new IllegalArgumentException(
                "Job '" + canonicalJob + "' at '" + canonicalCompany + "' already exists");
        }

        /* -------- D) Re‑use or create Company -------- */
        Company company = companyRepository
            .findByNameIgnoreCase(canonicalCompany)
            .orElseGet(() -> companyRepository.save(
                new Company(canonicalCompany, swe.getCompany().getIndustry())));
        swe.setCompany(company);

        /* -------- E) Person (optional) -------- */
        if (swe.getPerson() != null) {
            String email = swe.getPerson().getEmail().trim().toLowerCase();
            swe.getPerson().setEmail(email);
            swe.getPerson().setCompany(company);

            Person person = personRepository
                .findByEmailIgnoreCase(email)
                .orElseGet(() -> personRepository.save(swe.getPerson()));
            swe.setPerson(person);
        }

        /* -------- F) Link coding problems -------- */
        if (swe.getCodingProblems() != null) {
            swe.getCodingProblems().forEach(cp -> cp.setJob(swe));
        }

        /* -------- G) Save once -------- */
        SWE saved = sweRepository.save(swe);
        logger.info("Saved SWE with ID: {}", saved.getId());
        return saved;
    }

    @Override
    public List<SWE> listAll() {
        return sweRepository.findAll();
    }


    /**
     * Retrieves a list of SWE objects from the database and filters them
     * based on properties in sweParam (e.g., jobType, jobTitle).
     * @param sweParam
     * @return
     */
    @Override
    public List<SWE> listFiltered(SWE sweParam) {
        List<SWE> filteredJobs = sweRepository.findAll(); // Get all SWE jobs from DB
        if (sweParam != null) {
            if (sweParam.getJobType() != null) {
                filteredJobs = filteredJobs.stream()
                    .filter(swe -> swe.getJobType() != null && swe.getJobType().equals(sweParam.getJobType()))
                    .collect(Collectors.toList());
            }
            if (sweParam.getJobTitle() != null) {
                filteredJobs = filteredJobs.stream()
                    .filter(swe -> swe.getJobTitle() != null && swe.getJobTitle().contains(sweParam.getJobTitle()))
                    .collect(Collectors.toList());
            }
        }

        // Assign related entities from the database
        for (SWE filteredJob : filteredJobs) {
            UUID companyId = filteredJob.getCompany().getCompanyId();
            Optional<Company> company = companyRepository.findById(companyId);
            company.ifPresent(filteredJob::setCompany);

            UUID personId = filteredJob.getPerson().getPersonId();
            Optional<Person> person = personRepository.findById(personId);
            person.ifPresent(filteredJob::setPerson);

            // Assuming CodingProblem has a jobId field linking to SWE
            List<CodingProblem> codingProblems = codingProblemRepository.findByJobId(filteredJob.getId());
            filteredJob.setCodingProblems(codingProblems);
        }

        return filteredJobs;
    }

    @Override
    public boolean updateById(SWE swe) {
        if (swe == null || swe.getId() == null) {
            throw new IllegalArgumentException("SWE or its ID cannot be null");
        }
        Optional<SWE> existingSWE = sweRepository.findById(swe.getId());
        if (existingSWE.isPresent()) {
            SWE managedSWE = existingSWE.get();

            managedSWE.setDescription(swe.getDescription());
            managedSWE.setLocation(swe.getLocation());
            managedSWE.setSalary(swe.getSalary());
            managedSWE.setJobStatus(swe.getJobStatus());
            managedSWE.setPerson(swe.getPerson());

            sweRepository.save(managedSWE);
            return true;
        }
        return false;
    }

    @Override
    public SWE getById(UUID id) {
        return sweRepository.findById(id).orElse(null);
    }

    @Override
    public boolean removeById(UUID id) {
        if (sweRepository.existsById(id)) {
            sweRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
