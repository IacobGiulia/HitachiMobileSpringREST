package com.example.hitachimobilespringrest.model;

import jakarta.persistence.*;

@Entity
public class SimOffers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long offerId;

    private int callQty;
    private double cost;
    private double dataQty;
    private int duration; // in days
    private String offerName;

    @ManyToOne
    @JoinColumn(name = "sim_id", nullable = false)
    private SimDetails simDetails;

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public int getCallQty() {
        return callQty;
    }

    public void setCallQty(int callQty) {
        this.callQty = callQty;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getDataQty() {
        return dataQty;
    }

    public void setDataQty(double dataQty) {
        this.dataQty = dataQty;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public SimDetails getSimDetails() {
        return simDetails;
    }

    public void setSimDetails(SimDetails simDetails) {
        this.simDetails = simDetails;
    }
}
