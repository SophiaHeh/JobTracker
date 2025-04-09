// package com.tnite.jobwinner.service;
//
//@Service
//public class OfferService {
//  public String test(){
//    return "Hehe123";
//
//  }
//}

package com.tnite.jobwinner.service;
import org.springframework.stereotype.Service;
import com.tnite.jobwinner.model.Job;
import java.util.List;

public interface JobService {

  // Create
  boolean createJob(Job job);

  // Read
  Job getJobById(String id);

  List<Job> listAllJobs();

  // Update
  boolean updateJob(Job updatedJob); // no id needed, already in Job

  // Delete
  boolean deleteJob(String id);
}
