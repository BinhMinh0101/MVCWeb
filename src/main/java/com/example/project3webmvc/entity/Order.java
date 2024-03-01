package com.example.project3webmvc.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerID", nullable = false)
    private Customer customer;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

    @Column
    private LocalDate date;

    @Column
    private float total;

    @Column
    private String note;

    public Order() {}

    public Order(LocalDate date, float total, String note) {
        this.date = date;
        this.total = total;
        this.note = note;
    }
}
