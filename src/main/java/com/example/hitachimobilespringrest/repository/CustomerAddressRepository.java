package com.example.hitachimobilespringrest.repository;

import com.example.hitachimobilespringrest.model.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Long> {

}
