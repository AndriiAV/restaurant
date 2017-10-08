package com.andrii.restaurant.controller;

import com.andrii.restaurant.model.Cart;
import com.andrii.restaurant.model.Order;
import com.andrii.restaurant.persistent.DaoFactory;
import com.andrii.restaurant.persistent.OrderDao;
import com.andrii.restaurant.services.OrderService;
import com.andrii.restaurant.services.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/order")
public class OrderController extends HttpServlet {

    private final OrderService orderService = ServiceFactory.getInstance().getOrderService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionHelper sessionHelper = new SessionHelper(req);

        Cart cart = sessionHelper.getCurrentCart();

        if (!cart.isEmpty()) {
            orderService.processOrder(cart);
            sessionHelper.clearCurrentCart();

            req.getRequestDispatcher("/WEB-INF/order-success.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/menu");
        }
    }
}
