package com.example.hitachimobilespringrest.repository;

import com.example.hitachimobilespringrest.model.CustomerIdentity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CustomerIdentityRepository extends JpaRepository<CustomerIdentity, String> {

    Optional<CustomerIdentity> findByFirstNameAndLastNameAndEmailAddress(String firstName, String lastName, String emailAddress);
}
