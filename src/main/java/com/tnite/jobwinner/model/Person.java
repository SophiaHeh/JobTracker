package com.tnite.jobwinner.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.UUID;

@Entity
public class Person {
    @Id
    private UUID personID = UUID.randomUUID();;
    private String firstName;
    private String lastName;
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    private String phoneNumber;

    // test for API
    public Person(){

    }

    public Person(String firstName, String lastName, String email, Company company, String phoneNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.company = company;
        this.phoneNumber = phoneNumber;
    }

    public UUID getPersonId() {
        return this.personID;
    }


    // Check logic
    public void setPersonID(UUID newID){
        if (getPersonId() == null){
            this.personID = newID;
        }
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Company getCompanyId() {
        return company;
    }


    @Override
    public String toString() {
        return "Person{" +
            "personId='" + personID + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", company='" + company + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            '}';
    }

    public static void main(String[] args){
        Company c1 = new Company("Ark", "IT");
        Person p1 = new Person("Mary", "Smith", "ms@ark.mail.com", c1, "123456789");
        String s = p1.toString();
        System.out.println(s);
    }
}
