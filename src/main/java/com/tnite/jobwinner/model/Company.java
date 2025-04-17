package com.tnite.jobwinner.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.Objects;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(
    name = "companies",
    uniqueConstraints = @UniqueConstraint(columnNames = "name")
)
public class Company {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "company_id", updatable = false, nullable = false)
    private UUID companyId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "industry", nullable = false, length = 100)
    private String industry;


    // test for API
    public Company() {
        // Required for Jackson
    }

    public Company(String name, String industry) {
        this.name = name;
        this.industry = industry;
    }

    public UUID getCompanyId() {
        return companyId;
    }

    public String getName(){return this.name;}

    public void setName(String name){this.name = name;}

    public String getIndustry(){return this.industry;}

    @Override
    public String toString(){
        String s = "ID: " + getCompanyId() + "Company: " + getName() + "\n" + "Industry: " + getIndustry();
        return s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company that = (Company) o;
        return Objects.equals(companyId, that.companyId);
    }

    public static void main(String[] args){
        Company c1 = new Company("Ark", "IT");
        String res = c1.toString();
        System.out.println(res);
    }

}


//lacks the frequentCodingProblems field (previously a List<String> or MyQueue<String> in the class diagram)