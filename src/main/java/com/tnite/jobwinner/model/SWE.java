package com.tnite.jobwinner.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



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
        fetch = FetchType.LAZY)
    private List<CodingProblem> codingProblems = new ArrayList<>();


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


    @Override
    public String toString() {
        return "SWE{" +
            "codingProblems=" + codingProblems +
            ", company=" + this.getCompany() +
            ", person=" + this.getPerson() +
            '}';
    }
}