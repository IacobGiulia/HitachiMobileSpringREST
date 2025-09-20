package com.example.hitachimobilespringrest.repository;

import com.example.hitachimobilespringrest.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    Optional<Customer> findByEmailAddressAndDateOfBirth(String emailAddress, LocalDate dateOfBirth);

    Optional<Customer> findByFirstNameAndLastName(String firstName, String lastName);
}
