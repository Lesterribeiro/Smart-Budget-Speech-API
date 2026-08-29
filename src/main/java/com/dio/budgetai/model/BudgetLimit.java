package com.dio.budgetai.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BudgetLimit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String category;
    private Double monthlyLimit;
    private Double currentSpent;

    public BudgetLimit() {}

    public BudgetLimit(String category, Double monthlyLimit, Double currentSpent) {
        this.category = category;
        this.monthlyLimit = monthlyLimit;
        this.currentSpent = currentSpent;
    }

    public Long getId() { return id; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public Double getMonthlyLimit() { return monthlyLimit; }
    public void setMonthlyLimit(Double monthlyLimit) { this.monthlyLimit = monthlyLimit; }
    public Double getCurrentSpent() { return currentSpent; }
    public void setCurrentSpent(Double currentSpent) { this.currentSpent = currentSpent; }
}
