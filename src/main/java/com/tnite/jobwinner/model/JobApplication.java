package com.tnite.jobwinner.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface JobApplication {

  public List<Object> listAll();
  public void deleteApplications(String id);
  public void inputApplication(Map<String, String> applicationMap);
  public void updateStatus(String id, String newStatus);

  //public void updateResumeVersion(ApplicationStatus newStatus);
  //public void addCodingProblem(String id, CodingProblem problem); ---獨立

}