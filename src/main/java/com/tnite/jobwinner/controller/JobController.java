package com.tnite.jobwinner.controller;

import com.tnite.jobwinner.model.Job;
import com.tnite.jobwinner.service.JobService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/offer")
@RestController
public class JobController {
@Autowired
  private JobService jobService;


//  @PostMapping ("/create")
//  public boolean createJob() {
//    return jobService.createJob();
//  }

//@GetMapping("/getId")
//public Job getJobById(@RequestParam("id") String id) {
//  return jobService.getJobById(id);
//}
//
//  @GetMapping("/listJob")
//  public List<Job> listAllJobs() {
//    return jobService.listAllJobs();
//  }

//  @PostMapping ("/updateJob")
//  public boolean updateJobs(@RequestBody Job job) {
//     return jobService.updateJob(job);
//  }

  @GetMapping ("/delete")
  public boolean deleteJob(@RequestParam("id") String id) {
    return jobService.deleteJob(id);
  }


}