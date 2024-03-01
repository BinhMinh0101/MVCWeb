package com.example.project3webmvc.controller;

import com.example.project3webmvc.DTO.LoginForm;
import com.example.project3webmvc.entity.Customer;
import com.example.project3webmvc.repository.CustomerRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;




@Controller
public class LoginController {

    private final CustomerRepository customerRepository;

    @Autowired
    public LoginController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "Login/index"; // Trả về tên của trang đăng nhập (login.html, login.jsp, ...).
    }


    @PostMapping("/login")
    public String processLogin(
            @ModelAttribute LoginForm loginForm,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes
    ) {
        // Xử lý đăng nhập
        Customer customer = customerRepository.findCustomerByEmail(loginForm.getEmail());
        if (customer != null) {
            if (customer.getPassword().equals(loginForm.getPassword())) {
                request.getSession().setAttribute("customer", customer);
                return "redirect:/";
            }
        }
        redirectAttributes.addFlashAttribute("error", "Sai tên đăng nhập hoặc mật khẩu");
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("customer");
        return "redirect:/";
    }
}


