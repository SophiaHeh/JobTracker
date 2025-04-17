package com.tnite.jobwinner.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@DiscriminatorValue("SWE")
public class SWE extends GeneralJob{

    @OneToMany(
        mappedBy = "job",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<CodingProblem> codingProblems = new ArrayList<>();// : One-to-many association - one Job to many CodingProblems

    private Company company;
    private Person person;

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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) { this.company = company; }

    public Person getPerson() {
        return person;
    }


    @Override
    public String toString() {
        return "SWE{" +
            "codingProblems=" + codingProblems +
            ", company=" + company +
            ", person=" + person +
            '}';
    }

//    @Override
//    public String displayDetailedInfo() {
//        String baseInfo = super.displayDetailedInfo();
//        String languageInfo = languages.isEmpty() ? "" : " | Languages: " + String.join(", ", languages);
//        return baseInfo + languageInfo;
//    }

}
