package com.example.hitachimobilespringrest.service;

import com.example.hitachimobilespringrest.model.CustomerAddress;

import java.time.LocalDate;
import java.util.List;

public interface CustomerService {

    List<String> validateSimAndGetOffers(String simNumber, String serviceNumber);

    String validateCustomerBasics(String email, LocalDate dob);

    String validateCustomerPersonal(String firstName, String lastName, String confirmEmail);

    CustomerAddress updateCustomerAddress(String uniqueId, CustomerAddress newAddress);

    String validateIdProofAndActivateSim(String aadharId, String firstName, String lastName, LocalDate dob);
}
