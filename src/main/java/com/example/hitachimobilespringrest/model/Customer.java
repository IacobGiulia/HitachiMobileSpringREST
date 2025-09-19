package com.example.hitachimobilespringrest.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Customer {

    @Id
    private String uniqueIdNumber;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false, unique = true)
    private String emailAddress;

    @Column(nullable = false, length = 15)
    private String firstName;

    @Column(nullable = false, length = 15)
    private String lastName;

    private String idType; // Aadhar, Passport, etc.

    @ManyToOne
    @JoinColumn(name = "customer_address_id")
    private CustomerAddress customerAddress;

    @ManyToOne
    @JoinColumn(name = "sim_id")
    private SimDetails simDetails;

    private String state;

    public String getUniqueIdNumber() {
        return uniqueIdNumber;
    }

    public void setUniqueIdNumber(String uniqueIdNumber) {
        this.uniqueIdNumber = uniqueIdNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
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

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public CustomerAddress getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(CustomerAddress customerAddress) {
        this.customerAddress = customerAddress;
    }

    public SimDetails getSimDetails() {
        return simDetails;
    }

    public void setSimDetails(SimDetails simDetails) {
        this.simDetails = simDetails;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
