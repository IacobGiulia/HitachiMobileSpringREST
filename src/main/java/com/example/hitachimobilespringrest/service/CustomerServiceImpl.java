package com.example.hitachimobilespringrest.service;

import com.example.hitachimobilespringrest.exceptions.*;
import com.example.hitachimobilespringrest.model.*;
import com.example.hitachimobilespringrest.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private SimDetailsRepository simDetailsRepository;

    @Autowired
    private SimOffersRepository simOffersRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerAddressRepository customerAddressRepository;

    @Autowired
    private CustomerIdentityRepository customerIdentityRepository;

    @Override
    public List<String> validateSimAndGetOffers(String simNumber, String serviceNumber) {
        if (simNumber == null || serviceNumber == null
                || !simNumber.matches("\\d{13}")
                || !serviceNumber.matches("\\d{10}")) {
            throw new InvalidDetailsException(
                    "Invalid details, please check again Subscriber Identity Module (SIM)number/Service number!",
                    HttpStatus.NOT_FOUND);
        }

        SimDetails simDetails = simDetailsRepository
                .findBySimNumberAndServiceNumber(simNumber, serviceNumber)
                .orElseThrow(() -> new InvalidDetailsException(
                        "Invalid details, please check again Subscriber Identity Module (SIM)number/Service number!",
                        HttpStatus.NOT_FOUND));

        if ("active".equalsIgnoreCase(simDetails.getSimStatus())) {
            throw new InvalidDetailsException("Subscriber Identity Module (SIM) already active", HttpStatus.BAD_REQUEST);
        }

        List<SimOffers> offers = simOffersRepository.findBySimDetails(simDetails);

        return offers.stream()
                .map(offer -> offer.getCallQty() + " calls + "
                        + offer.getDataQty() + " GB for Rs."
                        + offer.getCost() + ", Validity: "
                        + offer.getDuration() + " days.")
                .collect(Collectors.toList());
    }

    @Override
    public String validateCustomerBasics(String email, LocalDate dob) {
        if (email == null || dob == null) {
            throw new InvalidDetailsException("Email/dob value is required", HttpStatus.BAD_REQUEST);
        }

        // Email regex simplu: un @, un punct, 2-3 caractere după
        if (!Pattern.matches("^[^@]+@[^@]+\\.[a-zA-Z]{2,3}$", email)) {
            throw new InvalidDetailsException("Invalid email", HttpStatus.BAD_REQUEST);
        }

        Optional<Customer> customerOpt = customerRepository.findByEmailAddressAndDateOfBirth(email, dob);
        if (customerOpt.isEmpty()) {
            throw new DetailsDoesNotExistException("No request placed for you.", HttpStatus.NOT_FOUND);
        }

        return "success";
    }

    @Override
    public String validateCustomerPersonal(String firstName, String lastName, String confirmEmail) {
        if (!firstName.matches("^[A-Za-z]{1,15}$") || !lastName.matches("^[A-Za-z]{1,15}$")) {
            throw new InvalidDetailsException("Firstname/Lastname should be a maximum of 15 characters", HttpStatus.BAD_REQUEST);
        }

        Customer customer = customerRepository
                .findByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(() -> new InvalidDetailsException("No customer found for the provided details", HttpStatus.NOT_FOUND));

        if (!customer.getEmailAddress().equalsIgnoreCase(confirmEmail)) {
            throw new InvalidEmailException("Invalid email details!!", HttpStatus.NOT_FOUND);
        }

        return "success";
    }

    @Override
    public CustomerAddress updateCustomerAddress(String uniqueId, CustomerAddress newAddress) {
        if (newAddress.getAddress().length() > 25) {
            throw new InvalidDetailsException("Address should be maximum of 25 characters", HttpStatus.BAD_REQUEST);
        }
        if (!newAddress.getStateNr().matches("\\d{6}")) {
            throw new InvalidDetailsException("Pin should be 6 digit number", HttpStatus.BAD_REQUEST);
        }
        if (!newAddress.getCity().matches("^[A-Za-z ]+$")
                || !newAddress.getState().matches("^[A-Za-z ]+$")) {
            throw new InvalidDetailsException("City/State should not contain any special characters except space", HttpStatus.BAD_REQUEST);
        }

        Customer customer = customerRepository.findById(uniqueId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found message", HttpStatus.NOT_FOUND));

        CustomerAddress address = customer.getCustomerAddress();
        if (address == null) {
            address = new CustomerAddress();
        }

        address.setAddress(newAddress.getAddress());
        address.setCity(newAddress.getCity());
        address.setState(newAddress.getState());
        address.setStateNr(newAddress.getStateNr());

        CustomerAddress savedAddress = customerAddressRepository.save(address);
        customer.setCustomerAddress(savedAddress);
        customerRepository.save(customer);

        return savedAddress;
    }

    @Override
    public String validateIdProofAndActivateSim(String aadharId, String firstName, String lastName, LocalDate dob) {
        if (aadharId == null || !aadharId.matches("\\d{16}")) {
            throw new InvalidDetailsException("Id should be 16 digits", HttpStatus.BAD_REQUEST);
        }
        if (firstName == null || lastName == null || dob == null) {
            throw new InvalidDetailsException("Invalid details", HttpStatus.BAD_REQUEST);
        }

        Customer customer = customerRepository.findByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found message", HttpStatus.NOT_FOUND));

        if (!customer.getDateOfBirth().equals(dob)) {
            throw new InvalidDetailsException("Invalid details", HttpStatus.BAD_REQUEST);
        }

        SimDetails simDetails = customer.getSimDetails();
        if (simDetails == null) {
            throw new InvalidDetailsException("No SIM associated with this customer", HttpStatus.BAD_REQUEST);
        }

        simDetails.setSimStatus("active");
        simDetailsRepository.save(simDetails);

        return "success";
    }
}
