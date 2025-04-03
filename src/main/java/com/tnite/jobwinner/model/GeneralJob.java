package com.tnite.jobwinner.model;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class GeneralJob extends Job {
    private List<String> notes;
    private LocalDate interviewDate; // should it be instance variables?

    /**
     * Constructors
     */
    public GeneralJob(String jobTitle, String description, String location, LocalDate applicationDate,
        Company company, Person person, double salary, Status jobStatus, Type jobType){
        super(jobTitle, description, location, applicationDate, company, person, salary, jobStatus, jobType);
        this.notes = new ArrayList<>();
        this.interviewDate = null;
    }

    public GeneralJob(String jobTitle, Company company, LocalDate applicationDate) {
        super(jobTitle, company, applicationDate); // Calls Job's required-fields constructor
        this.notes = new ArrayList<>();
        this.interviewDate = null;
    }


    public void addNote(String note) {
        if (note != null && !note.trim().isEmpty()) { // Prevent null or empty notes
            notes.add(note);
        }
    }

    public List<String> getNotes() {
        return notes;
    }

    public void resumeVersion(String resumeVersion) {
        addNote("Resume version: " + resumeVersion);
    }


    public void setInterviewDate(LocalDate interviewDate) {
        this.interviewDate = interviewDate;
        if (interviewDate != null) {
            updateStatus(Status.Interview_Scheduled);
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
        if (daysSinceApplication >= 30 && getJobStatus() != Status.Archived) {
            updateStatus(Status.Archived);
        }
    }

    @Override
    public String displayDetailedInfo() {
        String info = this.toString();
        if (interviewDate != null) {
            info += String.format(" | Interview Date: %s", interviewDate);
        }
        if (!notes.isEmpty()) {
            info += " | Notes: " + String.join(", ", notes);
        }
        return info;
    }
}
