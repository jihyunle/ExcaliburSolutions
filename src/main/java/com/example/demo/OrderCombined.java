package com.example.demo;

import javax.persistence.*;

@Entity
public class OrderCombined {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id", unique = true)
    private long id;

    @Column(name = "order_date", nullable = false)
    private String date;

    @Column(name = "order_amount", nullable = false)
    private float amount;

    @Column(name = "order_description", nullable = false)
    private String description;

    public OrderCombined(String date, float amount, String description){
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    public OrderCombined() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
