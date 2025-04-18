package com.tnite.jobwinner.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@DiscriminatorValue("SWE")
public class SWE extends GeneralJob{
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private UUID id;

    @OneToMany(mappedBy = "job",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.EAGER)
    private List<CodingProblem> codingProblems = new ArrayList<>();


//    private Company company;
//    private Person person;

    /**
     * Constructors
     */
    public SWE() { super(); }

    public SWE(String jobTitle, Company company, LocalDate applicationDate) {
        super(jobTitle, company, applicationDate);
    }

    public List<CodingProblem> getCodingProblems() {
        return codingProblems;
    }

    public void setCodingProblems(List<CodingProblem> codingProblems) {
        this.codingProblems = codingProblems != null ? codingProblems : new ArrayList<>();
    }

//    public Company getCompany() {
//        return company;
//    }
//
//    public void setCompany(Company company) { this.company = company; }
//
//    public Person getPerson() {
//        return person;
//    }


    @Override
    public String toString() {
        return "SWE{" +
            "codingProblems=" + codingProblems +
//            ", company=" + company +
//            ", person=" + person +
            '}';
    }

}
