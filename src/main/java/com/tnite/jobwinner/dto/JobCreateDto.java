//package com.tnite.jobwinner.dto;
//
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
//import java.time.LocalDate;
//
//public class JobCreateDto {
//
//    @NotBlank(message = "Job title is required")
//    private String jobTitle;
//
//    private String description;   // optional
//
//    private String location;      // optional
//
//    @NotNull(message = "Application date is required")
//    private LocalDate applicationDate;
//
//    @NotBlank(message = "Company name is required")
//    private String companyName;
//
//    // if you also want to assign an HR person:
//    private String personId;
//
//    // Constructors
//    public JobCreateDto() {}
//
//    // Getters & setters
//    public String getJobTitle() { return jobTitle; }
//    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
//
//    public String getDescription() { return description; }
//    public void setDescription(String description) { this.description = description; }
//
//    public String getLocation() { return location; }
//    public void setLocation(String location) { this.location = location; }
//
//    public LocalDate getApplicationDate() { return applicationDate; }
//    public void setApplicationDate(LocalDate applicationDate) { this.applicationDate = applicationDate; }
//
//    public String getCompanyName() { return companyName; }
//    public void setCompanyName(String companyName) { this.companyName = companyName; }
//
//    public String getPersonId() { return personId; }
//    public void setPersonId(String personId) { this.personId = personId; }
//}
