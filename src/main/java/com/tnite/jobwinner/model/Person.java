package com.tnite.jobwinner.model;

public class Person {
    private String firstName;
    private String lastName;
    private String email;
    private Company company;
    private int phoneNumber;

    public Person(String firstName, String lastName, String email, Company company, int phoneNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.company = company;
        this.phoneNumber = phoneNumber;
    }

    public String toString(){
        String s = "Company:" + company.getName() + "\n" +
                     firstName + " " + lastName;
        return s;
    }

    public static void main(String[] args){
        Company c1 = new Company("Ark", "IT", "....url...");
        Person p1 = new Person("Mary", "Smith", "ms@ark.mail.com", c1, 123456789);
        String s = p1.toString();
        System.out.println(s);
    }
}
