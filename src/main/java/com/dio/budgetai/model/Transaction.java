package com.dio.budgetai.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Double amount;
    private String category;
    private LocalDateTime createdAt;

    public Transaction() {}

    public Transaction(String description, Double amount, String category) {
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getDescription() { return description; }
    public Double getAmount() { return amount; }
    public String getCategory() { return category; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
