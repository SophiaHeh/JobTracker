package com.tnite.jobwinner.model;

public class Company {
  private String name;
  private String industry;
  private String website;

  public Company(String name, String industry, String website) {
    this.name = name;
    this.industry = industry;
    this.website = website;
  }
}

//lacks the frequentCodingProblems field (previously a List<String> or MyQueue<String> in the class diagram)