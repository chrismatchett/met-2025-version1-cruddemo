package com.example.cruddemo.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FineAmountDTO {

    @NotBlank(message = "is required")
    private String reference;
    private double amount;

    // Default constructor is needed for JSON parsing
    public FineAmountDTO() {
    }

    // Getter and setter methods
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}

