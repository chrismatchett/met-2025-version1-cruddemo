package com.example.cruddemo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name="fine")
public class Fine {

    // Define fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @Pattern(regexp = "^[A-Z]{1,2}[0-9R][0-9A-Z]? [0-9][ABD-HJLNP-UW-Z]{2}$", message = "not a valid UK post code")
    @Column(name="post_code")
    private String postCode;

    @Column(name = "reference", unique = true)
    private String reference;    
    
    @Column(name="amount")
    private double amount;
    
    // Define constructors
    Fine() {

    }

    public Fine(String firstName, String lastName, String email, String postCode, String reference, double amount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.postCode = postCode;
        this.reference = reference;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostCode() { return postCode; }

    public void setPostCode(String postCode) { this.postCode = postCode; }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
    
    public double getAmount() { return amount; }

    public void setAmount(double amount) { this.amount = amount; }
    
    @Override
    public String toString() {
        return "Employee{" +
                "EmployeeId=" + id +
                ", FirstName='" + firstName + '\'' +
                ", LastName='" + lastName + '\'' +
                ", Email='" + email + '\'' +
                ", PostCode='" + postCode + '\'' +
                ", Reference='" + reference + '\'' +
                ", Amount='" + amount + '\'' +
                '}';
    }
}
