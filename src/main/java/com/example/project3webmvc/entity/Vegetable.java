package com.example.project3webmvc.entity;

import lombok.Data;

import jakarta.persistence.*;

import java.text.DecimalFormat;
import java.util.List;

@Data
@Entity
@Table(name = "vegetable")
public class Vegetable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vegetableID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryID", nullable = false)
    private Category category;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vegetable", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

    @Column(name = "vegetablename")
    private String vegetableName;

    @Column
    private String unit;

    @Column
    private int amount;

    @Column
    private String image;

    @Column
    private float price;

    public Vegetable() {}

    public Vegetable(int vegetableID, Category category, List<OrderDetail> orderDetails, String vegetableName, String unit, int amount, String image, float price) {
        this.vegetableID = vegetableID;
        this.category = category;
        this.orderDetails = orderDetails;
        this.vegetableName = vegetableName;
        this.unit = unit;
        this.amount = amount;
        this.image = image;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Vegetable{" +
                "vegetableID=" + vegetableID +
                ", category=" + category +
                ", vegetableName='" + vegetableName + '\'' +
                ", unit='" + unit + '\'' +
                ", amount=" + amount +
                ", image='" + image + '\'' +
                ", price=" + price +
                '}';
    }

    public String getPriceFormat() {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format((int) price);
    }
}
