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
import com.tnite.jobwinner.model.GeneralJob;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.tnite.jobwinner.model.Job;
import java.util.List;

public interface JobService {

  // Create
  boolean createJob(GeneralJob job);

  // Read
  Job getJobById(UUID id);

  List<GeneralJob> listAllJobs();

  // Update
  boolean updateJob(UUID id, GeneralJob updatedJob); // no id needed, already in Job

  // Delete
  boolean deleteJob(UUID id);
}
