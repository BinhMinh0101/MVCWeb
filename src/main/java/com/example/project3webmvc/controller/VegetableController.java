package com.example.project3webmvc.controller;

import com.example.project3webmvc.DTO.SearchForm;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class VegetableController {
    @Autowired
    private VegetableRepository vegetableRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("/vegetables/search")
    public String searchVegetable(
            Model model,
            @ModelAttribute SearchForm searchForm,
            HttpServletRequest request
            ) {
        List<Vegetable> vegetables = new ArrayList<>();
        String name = "%" + searchForm.getName() + "%";
        // Nếu tìm kiếm theo 2 điều kiện
        if (searchForm.getCategoryId() != null) {
            vegetables = vegetableRepository.findVegetableByVegetableNameLikeAndCategory_CategoryID(name, searchForm.getCategoryId());
        } else { // Nếu chỉ tìm kiếm theo name
            vegetables = vegetableRepository.findVegetableByVegetableNameLike(name);
        }
        Iterable<Category> categories = categoryRepository.findAll();
        Customer customer = null;
        if (request.getSession().getAttribute("customer") != null) {
            customer = (Customer) request.getSession().getAttribute("customer");
        }

        model.addAttribute("vegetables", vegetables);
        model.addAttribute("categories", categories);
        model.addAttribute("customer", customer);
        return "index";
    }

}
