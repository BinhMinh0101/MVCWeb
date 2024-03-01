package com.example.project3webmvc.controller;

import com.example.project3webmvc.DTO.Cart;
import com.example.project3webmvc.MyUtils;
import com.example.project3webmvc.entity.Vegetable;
import com.example.project3webmvc.repository.VegetableRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class CartController {
    @Autowired
    private VegetableRepository vegetableRepository;

    @GetMapping("/cart")
    public String addToCart(
            @RequestParam(value = "vegetableId", required = true) int vegetableID,
            @RequestParam(value = "quantity", required = true) int quantity,
            HttpServletRequest request
    ) {
        Vegetable vegetable = vegetableRepository.findById(vegetableID).orElse(null);

        Cart cart = MyUtils.getCartInSession(request);
        cart.addItem(vegetable, quantity);
        MyUtils.setCartInSession(request, cart);

        return "redirect:/checkout";
    }

    @GetMapping("/cart/remove-item")
    public String removeItem(
            @RequestParam(value = "vegetableId", required = true) int vegetableID,
            HttpServletRequest request
    ) {
        Cart cart = MyUtils.getCartInSession(request);
        cart.removeItem(vegetableID);
        MyUtils.setCartInSession(request, cart);

        return "redirect:/checkout";
    }

}
