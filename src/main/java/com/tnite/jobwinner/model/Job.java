package com.tnite.jobwinner.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import java.time.LocalDate;
import java.util.UUID ;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discriminator")
public abstract class Job{

    @Id
    private UUID id = UUID.randomUUID();
    private String jobTitle;
    private String description;
    private String location;
    private LocalDate applicationDate;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "industry", column = @Column(name = "company_industry")),
        @AttributeOverride(name = "name", column = @Column(name = "company_name")),
        @AttributeOverride(name = "address", column = @Column(name = "company_address"))
    })
    private Company company;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "person_name")),
        @AttributeOverride(name = "email", column = @Column(name = "person_email"))
    })
    private Person person;
    private double salary;
    private Status jobStatus;
    private Type jobType;

    // test for API
    public Job(){}

    public Job(String jobTitle, String description, String location, LocalDate applicationDate,
        Company company, Person person, double salary, Status jobStatus, Type jobType){
        this.jobTitle = jobTitle;
        this.description = description;
        this.location = location;
        this.applicationDate = applicationDate;
        this.company = company;
        this.person = person;
        this.salary = salary;
        this.jobStatus = jobStatus;
        this.jobType = jobType;
    }

    public Job(String jobTitle, Company company, LocalDate applicationDate) {
        this(jobTitle,         // Required
            "",                // description (optional, default: empty string)
            "Unknown",         // location (optional, default: "Unknown")
            applicationDate,   // Required
            company,           // Required
            null,              // person (optional, default: null)
            0.0,               // salary (optional, default: 0.0)
            Status.Pending,    // jobStatus (optional, default: PENDING)
            Type.FULL_TIME);   // jobType (optional, default: FULL_TIME)
    }

    public UUID getId() {return id;}
    public String getJobTitle() {return this.jobTitle;}
    public String getDescription() {return this.description;}
    public String getLocation() {return this.location;}
    public LocalDate getApplicationDate() {return this.applicationDate;}
    public Company getCompany() {return this.company;}
    public Person getHRInfo(){return this.person;}
    public double getSalary() {return this.salary;}
    public Status getStatus() {return this.jobStatus;}
    public Type getJobType() {return this.jobType;}

    public void setPerson(Person person) { this.person = person; }
     // Change HR??

    public void setSalary(double salary) {
        if (salary <= 0) {
            throw new IllegalArgumentException("Salary should be greater than 0.");
        }
        this.salary = salary;
    }

    public void setDescription(String s){
        this.description = s;
    }
    public void setStatus(Status newStatus) {this.jobStatus = newStatus;}


    /**
     * Check that required fields are not null/empty
     * @return true if all field are full, false otherwise
     */
    public boolean validate() {
        return jobTitle != null && !jobTitle.trim().isEmpty() &&
            company != null &&
            applicationDate != null;
    }

    public String toString(){
        return String.format("Job: %s at %s, Status: %s", jobTitle, company.getName(), jobStatus);
    }

    public abstract String displayDetailedInfo();

}
