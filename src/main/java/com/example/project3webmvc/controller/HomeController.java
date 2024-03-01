package com.example.project3webmvc.controller;

import com.example.project3webmvc.entity.Category;
import com.example.project3webmvc.entity.Customer;
import com.example.project3webmvc.entity.Vegetable;
import com.example.project3webmvc.repository.CategoryRepository;
import com.example.project3webmvc.repository.VegetableRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    private VegetableRepository vegetableRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/")
    public String homePage(Model model, HttpServletRequest request) {
        Iterable<Vegetable> vegetables = vegetableRepository.findAll();
        Iterable<Category> categories = categoryRepository.findAll();
        model.addAttribute("vegetables", vegetables);
        model.addAttribute("categories", categories);

        Customer customer = null;
        if (request.getSession().getAttribute("customer") != null) {
            customer = (Customer) request.getSession().getAttribute("customer");
        }
        model.addAttribute("customer", customer);
        return "index";
    }
}
