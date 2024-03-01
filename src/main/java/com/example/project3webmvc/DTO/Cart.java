package com.example.project3webmvc.DTO;

import com.example.project3webmvc.entity.Customer;
import com.example.project3webmvc.entity.OrderDetail;
import com.example.project3webmvc.entity.Vegetable;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private Customer customer;
    private List<OrderDetail> orderDetails;

    public Cart() {
        customer = null;
        orderDetails = new ArrayList<>();
    }

    public void addItem(Vegetable vegetable, int quantity) {
        for (OrderDetail orderDetail : orderDetails) {
            if (orderDetail.getVegetable().getVegetableID() == vegetable.getVegetableID()) {
                orderDetail.setQuantity(orderDetail.getQuantity() + quantity);
                orderDetail.setPrice(vegetable.getPrice() * quantity);
                return;
            }
        }
        // If vegetable do not have in cart
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setVegetable(vegetable);
        orderDetail.setQuantity(quantity);
        orderDetail.setPrice(vegetable.getPrice() * quantity);
        orderDetails.add(orderDetail);
    }

    public void removeItem(int vegetableId) {
        orderDetails.removeIf(orderDetail -> orderDetail.getVegetable().getVegetableID() == vegetableId);
    }

    public String getSumPrice() {
        int sum = 0;
        for (OrderDetail orderDetail : orderDetails) {
            sum += orderDetail.getPrice();
        }
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format((int) sum);
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
