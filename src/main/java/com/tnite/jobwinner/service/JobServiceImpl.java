package com.tnite.jobwinner.service;

import com.tnite.jobwinner.model.GeneralJob;
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

  @Override
  public boolean createJob(GeneralJob job) {
    // Add business logic and validations if needed
    jobRepository.save(job);
    return true;
  }

  @Override
  public Job getJobById(UUID id) {
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
      job.setStatus(updatedJob.getStatus());
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
