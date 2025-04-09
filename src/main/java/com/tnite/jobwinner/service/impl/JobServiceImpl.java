package com.tnite.jobwinner.service.impl;

import com.tnite.jobwinner.model.Job;
import com.tnite.jobwinner.service.JobService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService {

  @Override
  public boolean createJob(Job job) {

  }

  @Override
  public Job getJobById(String id) {
    return null;
  }

  @Override
  public List<Job> listAllJobs() {
    return List.of();
  }

  @Override
  public boolean updateJob(Job updatedJob) {

  }

  @Override
  public boolean deleteJob(String id) {

  }
}
