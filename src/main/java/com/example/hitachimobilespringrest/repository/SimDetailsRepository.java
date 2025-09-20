package com.example.hitachimobilespringrest.repository;

import com.example.hitachimobilespringrest.model.SimDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SimDetailsRepository extends JpaRepository<SimDetails, Long> {
    Optional<SimDetails> findBySimNumberAndServiceNumber(String simNumber, String serviceNumber);
}
