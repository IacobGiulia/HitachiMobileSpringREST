package com.example.hitachimobilespringrest.repository;

import com.example.hitachimobilespringrest.model.SimOffers;
import com.example.hitachimobilespringrest.model.SimDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SimOffersRepository extends JpaRepository<SimOffers, Long> {
    List<SimOffers> findBySimDetails(SimDetails simDetails);
}
