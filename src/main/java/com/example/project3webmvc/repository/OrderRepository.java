package com.example.project3webmvc.repository;

import com.example.project3webmvc.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
