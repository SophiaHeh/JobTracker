package com.tnite.jobwinner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDate;
import java.util.UUID ;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(
    name = "jobs",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"job_title", "company_id"}      // business key
    )
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discriminator")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public abstract class Job{

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "job_title", nullable = false, length = 200)
    private String jobTitle;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "location", nullable = false, length = 100)
    private String location;

    @Column(name = "application_date", nullable = false)
    private LocalDate applicationDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;

    @Column(name = "salary")
    private double salary;

    @Enumerated(EnumType.STRING)
    @Column(name = "job_status", nullable = false, length = 50)
    private Status jobStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "job_type", nullable = false, length = 50)
    private Type jobType;

    /**
     * No-args constructor for JPA
     */
    protected Job(){}

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
    public Person getPerson(){return this.person;}
    public double getSalary() {return this.salary;}
    public Status getJobStatus() {return this.jobStatus;}
    public Type getJobType() {return this.jobType;}

    public void setJobTitle(String title) { this.jobTitle = title; }

    public void setPerson(Person person) { this.person = person; }

    public void setCompany(Company company) { this.company = company; }

    public void setSalary(double salary) {
        if (salary <= 0) {
            throw new IllegalArgumentException("Salary should be greater than 0.");
        }
        this.salary = salary;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String s){
        this.description = s;
    }
    public void setJobStatus(Status newStatus) {this.jobStatus = newStatus;}


    /**
     * Check that required fields are not null/empty
     * @return true if all field are full, false otherwise
     */
    public boolean validate() {
        return jobTitle != null && !jobTitle.trim().isEmpty() &&
            company != null &&
            applicationDate != null;
    }


    @Override
    public String toString() {
        return "Job{" +
            "id='" + id + '\'' +
            ", jobTitle='" + jobTitle + '\'' +
            ", description='" + description + '\'' +
            ", location='" + location + '\'' +
            ", applicationDate='" + applicationDate + '\'' +
            ", company='" + company + '\'' +
            ", person='" + person + '\'' +
            ", salary=" + salary +
            ", jobStatus=" + jobStatus +
            ", jobType=" + jobType +
            '}';
    }

}

