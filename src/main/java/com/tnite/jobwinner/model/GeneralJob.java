package com.tnite.jobwinner.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

@Entity
public class GeneralJob extends Job {

    @Column(name = "interview_date")
    private LocalDate interviewDate;

    /**
     * No-args constructor for JPA and JSON deserialization
     */
    public GeneralJob() {
        super();
    }

    public GeneralJob(String jobTitle, String description, String location, LocalDate applicationDate,
        Company company, Person person, double salary, Status jobStatus, Type jobType){
        super(jobTitle, description, location, applicationDate, company, person, salary, jobStatus, jobType);
        this.interviewDate = null;
    }

    public GeneralJob(String jobTitle, Company company, LocalDate applicationDate) {
        super(jobTitle, company, applicationDate); // Calls Job's required-fields constructor
        this.interviewDate = null;
    }

    public void setInterviewDate(LocalDate interviewDate) {
        this.interviewDate = interviewDate;
        if (interviewDate != null) {
            setJobStatus(Status.Interview_Scheduled);
        }
    }

    public LocalDate getInterviewDate() {
        return this.interviewDate;
    }

    /**
     * If we haven't received feedback after 30 days since we applied, update job status
     */
    public void checkAndMarkGhosted() {
        long daysSinceApplication = ChronoUnit.DAYS.between(this.getApplicationDate(), LocalDate.now());
        if (daysSinceApplication >= 30 && getJobStatus() != Status.Ghosted) {
            setJobStatus(Status.Ghosted);
        }
    }

//
//    @Override
//    public String displayDetailedInfo() {
//        String info = this.toString();
//        if (interviewDate != null) {
//            info += String.format(" | Interview Date: %s", interviewDate);
//        }
//        if (!notes.isEmpty()) {
//            info += " | Notes: " + String.join(", ", notes);
//        }
//        return info;
//    }
}
