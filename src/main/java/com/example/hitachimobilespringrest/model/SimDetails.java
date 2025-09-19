package com.example.hitachimobilespringrest.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class SimDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long simId;

    @Column(nullable = false, length = 10)
    private String serviceNumber;

    @Column(nullable = false, length = 13, unique = true)
    private String simNumber;

    @Column(nullable = false)
    private String simStatus;

    @OneToMany(mappedBy = "simDetails", cascade = CascadeType.ALL)
    private List<SimOffers> offers;

    public Long getSimId() {
        return simId;
    }

    public void setSimId(Long simId) {
        this.simId = simId;
    }

    public String getServiceNumber() {
        return serviceNumber;
    }

    public void setServiceNumber(String serviceNumber) {
        this.serviceNumber = serviceNumber;
    }

    public String getSimNumber() {
        return simNumber;
    }

    public void setSimNumber(String simNumber) {
        this.simNumber = simNumber;
    }

    public String getSimStatus() {
        return simStatus;
    }

    public void setSimStatus(String simStatus) {
        this.simStatus = simStatus;
    }

    public List<SimOffers> getOffers() {
        return offers;
    }

    public void setOffers(List<SimOffers> offers) {
        this.offers = offers;
    }
}
