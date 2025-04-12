package com.tnite.jobwinner.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Company {
    private String name;
    private String industry;


    // test for API
    public Company() {
        // Required for Jackson
    }

    public Company(String name, String industry) {
        this.name = name;
        this.industry = industry;
    }

    public String getName(){return this.name;}

    public String getIndustry(){return this.industry;}

    public String toString(){
        String s = "Company: " + getName() + "\n" + "Industry: " + getIndustry();
        return s;
    }

    public static void main(String[] args){
        Company c1 = new Company("Ark", "IT");
        String res = c1.toString();
        System.out.println(res);
    }

}


//lacks the frequentCodingProblems field (previously a List<String> or MyQueue<String> in the class diagram)