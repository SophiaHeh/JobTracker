package com.tnite.jobwinner.service.impl;

import com.tnite.jobwinner.model.Company;
import com.tnite.jobwinner.model.GeneralJob;
import com.tnite.jobwinner.repository.CompanyRepository;
import com.tnite.jobwinner.service.JobService;
import jakarta.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tnite.jobwinner.model.Job;
import com.tnite.jobwinner.repository.JobRepository;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

  @Autowired
  private JobRepository jobRepository;
  @Autowired
  private CompanyRepository companyRepo;

//  @Override
//  public boolean createJob(GeneralJob job) {
//    // Add business logic and validations if needed
//    jobRepository.save(job);
//    return true;
//  }

  @Override
  @Transactional
  public GeneralJob createJob(GeneralJob job) {
    // 1) Extract the nested Company object from 'job'
    Company incoming = job.getCompany();
    Company company;

    if (incoming.getCompanyId() != null) {
      // Client sent an existing company id -> load it (404 if missing)
      company = companyRepo.findById(incoming.getCompanyId())
          .orElseThrow(() -> new ResourceNotFoundException(
              "Company not found: " + incoming.getCompanyId()));
    } else {
      // No id means new or “find by name”
      company = companyRepo.findByName(incoming.getName())
          .orElseGet(() -> {
            // Create & save a fresh Company row
            Company c = new Company(incoming.getName(), incoming.getIndustry());
            return companyRepo.save(c);
          });
    }

    // 2) Assign the managed company back on the job
    job.setCompany(company);

    // 3) (Optionally) do the same for Person if you allow nested Person

    // 4) Now save the Job; no cascade needed for Company
    GeneralJob saved = jobRepository.save(job);
    return saved;
  }




//  public boolean createJob(GeneralJob job) {
//    try {
//      // Extract company name
//      String companyName = job.getCompany() != null ?
//          job.getCompany().getName() :
//          null;
//
//      if (companyName == null) {
//        return false; // Company name is required
//      }
//
//      // Find or create the company
//      Company company = companyRepo.findByName(companyName)
//          .orElseGet(() -> {
//            Company newCompany = new Company();
//            newCompany.setName(companyName);
//            return companyRepo.save(newCompany);
//          });
//
//      // Set the actual company entity on the job
//      job.setCompany(company);
//
//      // Save the job
//      jobRepository.save(job);
//      return true;
//    } catch (Exception e) {
//      // Log the exception
//      e.printStackTrace();
//      return false;
//    }
//  }

  @Override
  public GeneralJob getJobById(UUID id) {
    return jobRepository.findById(id).orElse(null);
  }

  @Override
  public List<GeneralJob> listAllJobs() {
    return jobRepository.findAll();
  }

  @Override
  public boolean updateJob(UUID id, GeneralJob updatedJob) {
    Optional<GeneralJob> existing = jobRepository.findById(id);
    if (existing.isPresent()) {
      GeneralJob job = existing.get();
      job.setSalary(updatedJob.getSalary());
      job.setDescription(updatedJob.getDescription());
      job.setJobStatus(updatedJob.getJobStatus());
      jobRepository.save(job);
      return true;
    }
    return false;
  }


  @Override
  public boolean deleteJob(UUID id) {
    if (jobRepository.existsById(id)) {
      jobRepository.deleteById(id);
      return true;
    }
    return false;
  }
}
