package com.example.project3webmvc.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.text.DecimalFormat;

@Data
@Entity
@Table(name = "orderdetail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderdetailid")
    private int orderDetailID;

    @ManyToOne
    @JoinColumn(name = "orderID", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vegetableID", nullable = false)
    private Vegetable vegetable;

    @Column
    private int quantity;

    @Column
    private float price;

    public OrderDetail() {}

    public OrderDetail(Order order, Vegetable vegetable, int quantity, float price) {
        this.order = order;
        this.vegetable = vegetable;
        this.quantity = quantity;
        this.price = price;
    }

    public String getPriceFormat() {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format((int) price);
    }
}
