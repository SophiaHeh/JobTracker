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

    public String getName(){return this.name;}

    public String getIndustry(){return this.industry;}

    public String toString(){
        String s = "Company: " + getName() + "\n" + "Industry: " + getIndustry();
        return s;
    }

    public static void main(String[] args){
        Company c1 = new Company("Ark", "IT", "....url...");
        String res = c1.toString();
        System.out.println(res);
    }

}
