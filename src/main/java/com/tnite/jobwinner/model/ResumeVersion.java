package com.tnite.jobwinner.model;

import java.time.LocalDate;

public class ResumeVersion {
  private String fileName;
  private LocalDate lastUpdated;

  public ResumeVersion(String fileName, LocalDate lastUpdated) {
    this.fileName = fileName;
    this.lastUpdated = lastUpdated;
  }
}
