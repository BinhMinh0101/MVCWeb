package com.example.project3webmvc;

import com.example.project3webmvc.DTO.Cart;
import jakarta.servlet.http.HttpServletRequest;

public class MyUtils {
    public static Cart getCartInSession(HttpServletRequest request) {
        Cart cart = (Cart) request.getSession().getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }

        return cart;
    }

    public static void removeCartInSession(HttpServletRequest request) {
        request.getSession().removeAttribute("cart");
    }

    public static void setCartInSession(HttpServletRequest request, Cart cart) {
        request.getSession().setAttribute("cart", cart);
    }
}
