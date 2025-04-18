package com.tnite.jobwinner.controller;

import com.tnite.jobwinner.model.GeneralJob;
import com.tnite.jobwinner.model.Job;
import com.tnite.jobwinner.service.JobService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

/**
 * This controller is for GeneralJob class and service.
 */
@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    public ResponseEntity<GeneralJob> createJob(@RequestBody GeneralJob job) {
        GeneralJob saved = jobService.createJob(job);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

//    public ResponseEntity<String> createJob(@RequestBody GeneralJob job) {
//        boolean created = jobService.createJob(job);
//        if (created) {
//            return ResponseEntity.ok("Job created successfully");
//        }
//        return ResponseEntity.badRequest().body("Failed to create job");
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable UUID id) {
        Job job = jobService.getJobById(id);
        if (job != null) {
            return ResponseEntity.ok(job);
        }
        return ResponseEntity.notFound().build();
    }

    // list all jobs
    @GetMapping
    public ResponseEntity<List<GeneralJob>> getAllJobs() {
        List<GeneralJob> jobs = jobService.listAllJobs();
        return ResponseEntity.ok(jobs);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable UUID id, @RequestBody GeneralJob updatedJob) {
        boolean updated = jobService.updateJob(id, updatedJob);  // Pass id separately
        if (updated) {
            return ResponseEntity.ok("Job updated successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job not found"); // Correct
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable UUID id) {
        boolean deleted = jobService.deleteJob(id);
        if (deleted) {
            return ResponseEntity.ok("Job deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job not found");  // Correct
    }
}