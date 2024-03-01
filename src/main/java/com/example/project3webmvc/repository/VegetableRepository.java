package com.example.project3webmvc.repository;

import com.example.project3webmvc.entity.Customer;
import com.example.project3webmvc.entity.Vegetable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VegetableRepository extends JpaRepository<Vegetable, Integer> {
    List<Vegetable> findVegetableByVegetableNameLikeAndCategory_CategoryID(String name, Integer categoryId);
    List<Vegetable> findVegetableByVegetableNameLike(String name);
    List<Vegetable> findVegetableByCategory_CategoryID(Integer categoryId);
}
