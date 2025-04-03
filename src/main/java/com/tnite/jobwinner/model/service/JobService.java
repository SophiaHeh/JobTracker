package com.tnite.jobwinner.model.service;

import com.tnite.jobwinner.model.Job;
import java.util.List;

public interface JobService {

    // Create
    void createJob(Job job);

    // Read
    Job getJobById(String id);
    List<Job> listAllJobs();

    // Update
    void updateJob(String id, Job updatedJob);

    // Delete
    void deleteJob(String id);
}


