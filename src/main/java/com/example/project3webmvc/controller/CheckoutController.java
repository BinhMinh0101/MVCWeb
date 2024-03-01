package com.example.project3webmvc.controller;

import com.example.project3webmvc.DTO.Cart;
import com.example.project3webmvc.MyUtils;
import com.example.project3webmvc.entity.Customer;
import com.example.project3webmvc.entity.Order;
import com.example.project3webmvc.entity.OrderDetail;
import com.example.project3webmvc.entity.Vegetable;
import com.example.project3webmvc.repository.CustomerRepository;
import com.example.project3webmvc.repository.OrderDetailRepository;
import com.example.project3webmvc.repository.OrderRepository;
import com.example.project3webmvc.repository.VegetableRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CheckoutController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private VegetableRepository vegetableRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/checkout")
    public String checkoutPage(Model model, HttpServletRequest request) {
        Cart cart = MyUtils.getCartInSession(request);
        model.addAttribute("orderDetails", cart.getOrderDetails());
        model.addAttribute("sumPrice", cart.getSumPrice());
        return "checkout/index";
    }

    @PostMapping("/checkout")
    public String checkout(Model model, HttpServletRequest request) {
        Cart cart = MyUtils.getCartInSession(request);

        // Chưa có đăng nhập nên code cứng như vậy
        Customer customer = customerRepository.findById(1).orElse(null);

        Order order = new Order();
        order.setCustomer(customer);
        order.setDate(LocalDate.now());
        order.setNote("IDK");
        order.setTotal(0);
        Order savedOrder = orderRepository.saveAndFlush(order);

        List<OrderDetail> orderDetails = new ArrayList<>();
        for (OrderDetail orderDetail : cart.getOrderDetails()) {
            orderDetail.setOrder(savedOrder);
            orderDetails.add(orderDetail);
            // Update amount of vegetable when buy
            orderDetail.getVegetable().setAmount(orderDetail.getVegetable().getAmount() - orderDetail.getQuantity());;
            vegetableRepository.saveAndFlush(orderDetail.getVegetable());
        }
        orderDetailRepository.saveAll(orderDetails);


        return "redirect:/";
    }
}
